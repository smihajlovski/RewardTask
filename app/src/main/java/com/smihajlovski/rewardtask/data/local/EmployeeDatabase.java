package com.smihajlovski.rewardtask.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.smihajlovski.rewardtask.common.Constants;
import com.smihajlovski.rewardtask.data.model.Employee;

/**
 * Created by Stefan on 29-Mar-18.
 */

@Database(entities = {Employee.class}, version = Constants.DATABASE_VERSION)
public abstract class EmployeeDatabase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();
}
