package com.smihajlovski.rewardtask.data.remote;

import com.smihajlovski.rewardtask.data.model.Employee;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Stefan on 29-Mar-18.
 */

public interface ApiService {

    @GET("list")
    Single<List<Employee>> getEmployeeList();

}
