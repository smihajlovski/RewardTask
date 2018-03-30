package com.smihajlovski.rewardtask.data.repository;

import com.smihajlovski.rewardtask.common.Constants;
import com.smihajlovski.rewardtask.data.local.EmployeeDao;
import com.smihajlovski.rewardtask.data.model.Employee;
import com.smihajlovski.rewardtask.data.remote.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Class representing Application's Repository
 * <p>
 * Created by Stefan on 29-Mar-18.
 */

public class EmployeeRepository {

    private final ApiService apiService;
    private final EmployeeDao employeeDao;

    @Inject
    EmployeeRepository(ApiService apiService, EmployeeDao employeeDao) {
        this.apiService = apiService;
        this.employeeDao = employeeDao;
    }

    public Observable<List<Employee>> getEmployeesFromApi() {
        return apiService.getEmployeeList().toObservable()
                .doOnNext(employeeDao::insertAll)
                .doOnComplete(() -> Timber.d(Constants.APP_REWARD_TAG + "Fetching employees from API completed"));
    }

    public Observable<List<Employee>> getEmployeesFromDb() {
        return employeeDao.getAllEmployees().toObservable();
    }
}
