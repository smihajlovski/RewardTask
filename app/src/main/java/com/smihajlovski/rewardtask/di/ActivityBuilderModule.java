package com.smihajlovski.rewardtask.di;

import com.smihajlovski.rewardtask.ui.details.DetailsActivity;
import com.smihajlovski.rewardtask.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Stefan on 29-Mar-18.
 */

@Module(includes = {FragmentBuilderModule.class})
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract DetailsActivity bindDetailsActivity();
}
