package com.smihajlovski.rewardtask.ui.details;

import android.arch.lifecycle.ViewModel;

import com.smihajlovski.rewardtask.data.repository.EmployeeRepository;
import com.smihajlovski.rewardtask.utils.managers.SchedulerProviderManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Stefan on 30-Mar-18.
 */

public class DetailsViewModel extends ViewModel {

    private final EmployeeRepository employeeRepository;
    private final SchedulerProviderManager schedulerProviderManager;
    private final CompositeDisposable compositeDisposable;

    @Inject
    DetailsViewModel(EmployeeRepository employeeRepository, SchedulerProviderManager schedulerProviderManager, CompositeDisposable compositeDisposable) {
        this.employeeRepository = employeeRepository;
        this.schedulerProviderManager = schedulerProviderManager;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
