package com.smihajlovski.rewardtask.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;

import com.smihajlovski.rewardtask.common.Constants;
import com.smihajlovski.rewardtask.data.model.Employee;
import com.smihajlovski.rewardtask.data.repository.EmployeeRepository;
import com.smihajlovski.rewardtask.utils.managers.SchedulerProviderManager;
import com.smihajlovski.rewardtask.utils.managers.UtilsManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

/**
 * Created by Stefan on 29-Mar-18.
 */

public class MainViewModel extends AndroidViewModel {

    private final EmployeeRepository employeeRepository;
    private final SchedulerProviderManager schedulerProviderManager;
    private final CompositeDisposable compositeDisposable;
    private ObservableField<Boolean> isLoading = new ObservableField<>(false);
    private ObservableField<Boolean> isErrorHolder = new ObservableField<>(false);
    MutableLiveData<List<Employee>> mutableEmployeesList = new MutableLiveData<>();

    @Inject
    MainViewModel(Application application, EmployeeRepository employeeRepository, SchedulerProviderManager schedulerProviderManager, CompositeDisposable compositeDisposable) {
        super(application);
        this.employeeRepository = employeeRepository;
        this.schedulerProviderManager = schedulerProviderManager;
        this.compositeDisposable = compositeDisposable;
    }

    void loadEmployees() {
        if (UtilsManager.hasActiveNetworkConnection(getApplication())) {
            loadEmployeesFromApi();
        } else {
            loadEmployeesFromDb();
        }
    }

    private void loadEmployeesFromApi() {
        isLoading.set(true);
        compositeDisposable.addAll(employeeRepository
                .getEmployeesFromApi()
                .subscribeOn(schedulerProviderManager.io())
                .observeOn(schedulerProviderManager.ui())
                .subscribeWith(new DisposableObserver<List<Employee>>() {
                    @Override
                    public void onNext(List<Employee> employeeList) {
                        if (employeeList.isEmpty()) {
                            isErrorHolder.set(true);
                        } else {
                            mutableEmployeesList.setValue(employeeList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d(Constants.APP_REWARD_TAG + " loadEmployeesFromApi onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        isLoading.set(false);
                    }
                }));
    }

    private void loadEmployeesFromDb() {
        isLoading.set(true);
        compositeDisposable.addAll(employeeRepository
                .getEmployeesFromDb()
                .subscribeOn(schedulerProviderManager.io())
                .observeOn(schedulerProviderManager.ui())
                .subscribeWith(new DisposableObserver<List<Employee>>() {

                    @Override
                    public void onNext(List<Employee> employeeList) {
                        if (employeeList.isEmpty()) {
                            isErrorHolder.set(true);
                        } else {
                            mutableEmployeesList.setValue(employeeList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d(Constants.APP_REWARD_TAG + " loadEmployeesFromDb onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        isLoading.set(false);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
