package com.smihajlovski.rewardtask.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.smihajlovski.rewardtask.common.Constants;
import com.smihajlovski.rewardtask.data.model.Employee;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Stefan on 29-Mar-18.
 */
@Dao
public interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Employee> employeeList);

    @Query("SELECT * FROM " + Constants.EMPLOYEE_TABLE_NAME)
    Single<List<Employee>> getAllEmployees();
}
