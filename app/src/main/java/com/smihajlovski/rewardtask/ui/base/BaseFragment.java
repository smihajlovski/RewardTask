package com.smihajlovski.rewardtask.ui.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Stefan on 29-Mar-18.
 */

public abstract class BaseFragment<VM extends ViewModel, DB extends ViewDataBinding> extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    protected DB binder;
    protected VM viewModel;
    protected FragmentInteractionCallback fragmentInteractionCallback;

    public abstract Class<VM> getViewModel();

    @LayoutRes
    public abstract int getLayoutRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binder = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        return binder.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentInteractionCallback = (FragmentInteractionCallback) context;
        } catch (ClassCastException e) {
            throw new RuntimeException(context.toString() + " must implement " + FragmentInteractionCallback.class.getName());
        }
    }
}

