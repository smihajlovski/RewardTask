package com.smihajlovski.rewardtask.utils.managers;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Stefan on 29-Mar-18.
 */

public class SchedulerProviderManager {

    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    public Scheduler io() {
        return Schedulers.io();
    }
}
