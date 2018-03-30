package com.smihajlovski.rewardtask.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.smihajlovski.rewardtask.BuildConfig;
import com.smihajlovski.rewardtask.common.Constants;
import com.smihajlovski.rewardtask.data.local.EmployeeDao;
import com.smihajlovski.rewardtask.data.local.EmployeeDatabase;
import com.smihajlovski.rewardtask.data.remote.ApiService;
import com.smihajlovski.rewardtask.data.remote.BasicAuthInterceptor;
import com.smihajlovski.rewardtask.utils.managers.SchedulerProviderManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Stefan on 29-Mar-18.
 */

@Module(includes = {ViewModelModule.class})
class AppModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    request = request.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build();
                    return chain.proceed(request);
                })
                .addInterceptor(interceptor)
                .addInterceptor(new BasicAuthInterceptor(Constants.USERNAME, Constants.PASSWORD))
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    EmployeeDatabase provideRoomDatabase(Application application) {
        return Room.databaseBuilder(application, EmployeeDatabase.class, Constants.EMPLOYEE_DATABASE_NAME).build();
    }

    @Provides
    @Singleton
    EmployeeDao provideHrDao(EmployeeDatabase employeeDatabase) {
        return employeeDatabase.employeeDao();
    }

    @Provides
    @Singleton
    SchedulerProviderManager provideSchedulerProviderManager() {
        return new SchedulerProviderManager();
    }
}
