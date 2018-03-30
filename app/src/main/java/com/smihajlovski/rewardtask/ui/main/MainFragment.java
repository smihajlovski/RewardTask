package com.smihajlovski.rewardtask.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smihajlovski.rewardtask.R;
import com.smihajlovski.rewardtask.common.Constants;
import com.smihajlovski.rewardtask.data.model.Employee;
import com.smihajlovski.rewardtask.databinding.FragmentMainBinding;
import com.smihajlovski.rewardtask.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Stefan on 29-Mar-18.
 */

public class MainFragment extends BaseFragment<MainViewModel, FragmentMainBinding> implements EmployeeAdapter.OnItemClickListener {

    private EmployeeAdapter employeeAdapter;
    private List<Employee> testEmployeeList = new ArrayList<>();

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    public Class<MainViewModel> getViewModel() {
        return MainViewModel.class;
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binder = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        init();
        return binder.getRoot();
    }

    @Override
    public void onDestroyView() {
        employeeAdapter.removeOnItemClickListener();
        super.onDestroyView();
    }

    @Override
    public void onItemClick(Employee employee) {
        Toast.makeText(getContext(), "Employee: " + employee.getName(), Toast.LENGTH_SHORT).show();
    }

    private void init() {
        binder.setViewModel(viewModel);
        binder.executePendingBindings();
        employeeAdapter = new EmployeeAdapter(getContext(), testEmployeeList);
        employeeAdapter.addOnItemClickListener(this);
        binder.rvEmployees.setLayoutManager(new LinearLayoutManager(getContext()));
        getEmployees();
    }

    private void getEmployees() {
        viewModel.loadEmployees();
        viewModel.mutableEmployeesList.observe(this, employeeList -> {
            Timber.d(Constants.APP_REWARD_TAG + " size of employees " + employeeList.size());
            testEmployeeList.clear();
            testEmployeeList.addAll(employeeList);
            employeeAdapter.notifyDataSetChanged();
            binder.rvEmployees.setAdapter(employeeAdapter);
        });
    }
}
