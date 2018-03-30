package com.smihajlovski.rewardtask.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.smihajlovski.rewardtask.ui.details.DetailsViewModel;
import com.smihajlovski.rewardtask.ui.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Stefan on 29-Mar-18.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel.class)
    abstract ViewModel bindDetailsViewModel(DetailsViewModel detailsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}
