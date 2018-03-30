package com.smihajlovski.rewardtask.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smihajlovski.rewardtask.R;
import com.smihajlovski.rewardtask.common.Constants;
import com.smihajlovski.rewardtask.data.model.Employee;
import com.smihajlovski.rewardtask.databinding.FragmentMainBinding;
import com.smihajlovski.rewardtask.ui.base.BaseFragment;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Stefan on 29-Mar-18.
 */

public class MainFragment extends BaseFragment<MainViewModel, FragmentMainBinding> implements
        EmployeeAdapter.OnItemClickListener {

    public static final String ACTION_EMPLOYEE_DETAILS = MainFragment.class.getName() + "action.employee_details";
    private EmployeeAdapter employeeAdapter;
    private List<Employee> initialEmployeeList = new ArrayList<>();

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    public Class<MainViewModel> getViewModel() {
        return MainViewModel.class;
    }

    public MainFragment() {

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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        employeeAdapter.removeOnItemClickListener();
        super.onDestroyView();
    }

    @Override
    public void onItemClick(Employee employee) {
        sendActionToActivity(ACTION_EMPLOYEE_DETAILS, employee);
    }

    private void init() {
        binder.setViewModel(viewModel);
        binder.executePendingBindings();
        employeeAdapter = new EmployeeAdapter(getContext(), initialEmployeeList);
        employeeAdapter.addOnItemClickListener(this);
        binder.rvEmployees.setLayoutManager(new LinearLayoutManager(getContext()));
        setListeners();
        getEmployees();
    }

    private void setListeners() {
        binder.fabRefresh.setOnClickListener(v -> viewModel.loadEmployees());
        binder.rvEmployees.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && binder.fabRefresh.isShown()) binder.fabRefresh.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) binder.fabRefresh.show();
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void getEmployees() {
        viewModel.loadEmployees();
        viewModel.mutableEmployeesList.observe(this, employeeList -> {
            Timber.d(Constants.APP_REWARD_TAG + " size of employees " + employeeList.size());
            initialEmployeeList.clear();
            initialEmployeeList.addAll(employeeList);
            employeeAdapter.notifyDataSetChanged();
            binder.rvEmployees.setAdapter(employeeAdapter);
        });
    }

    private void sendActionToActivity(String action, Employee employee) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ACTION, action);
        bundle.putParcelable(Constants.DATA_KEY_1, Parcels.wrap(employee));
        fragmentInteractionCallback.onFragmentInteractionCallback(bundle);
    }
}
