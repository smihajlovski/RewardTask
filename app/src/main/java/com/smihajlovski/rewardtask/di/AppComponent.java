package com.smihajlovski.rewardtask.di;

import android.app.Application;

import com.smihajlovski.rewardtask.common.RewardTaskApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Stefan on 29-Mar-18.
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilderModule.class})

public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(RewardTaskApplication rewardTaskApplication);
}