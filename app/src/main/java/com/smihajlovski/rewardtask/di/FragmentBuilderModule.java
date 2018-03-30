package com.smihajlovski.rewardtask.di;

import com.smihajlovski.rewardtask.ui.details.DetailsFragment;
import com.smihajlovski.rewardtask.ui.main.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Stefan on 29-Mar-18.
 */

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract MainFragment bindMainFragment();

    @ContributesAndroidInjector
    abstract DetailsFragment bindEmployeeDetailsFragment();
}
