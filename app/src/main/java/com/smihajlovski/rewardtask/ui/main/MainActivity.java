package com.smihajlovski.rewardtask.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.smihajlovski.rewardtask.R;
import com.smihajlovski.rewardtask.common.Constants;
import com.smihajlovski.rewardtask.data.model.Employee;
import com.smihajlovski.rewardtask.databinding.ActivityMainBinding;
import com.smihajlovski.rewardtask.ui.base.BaseActivity;
import com.smihajlovski.rewardtask.ui.base.FragmentInteractionCallback;
import com.smihajlovski.rewardtask.ui.details.DetailsActivity;

import org.parceler.Parcels;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements
        FragmentInteractionCallback,
        FragmentManager.OnBackStackChangedListener {

    private FragmentManager fragmentManager;
    private int backStackCounter = 0;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        replaceFragment(MainFragment.newInstance());
    }

    @Override
    public void onBackStackChanged() {
        int countBackStack = fragmentManager.getBackStackEntryCount() - 1;
        if (countBackStack >= 0) backStackCounter = countBackStack;
    }

    @Override
    public void onBackPressed() {
        if (backStackCounter > 0) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        fragmentManager.removeOnBackStackChangedListener(this);
        super.onDestroy();
    }

    @Override
    public void onFragmentInteractionCallback(Bundle bundle) {
        String action = bundle.getString(Constants.ACTION);
        if (action.equals(MainFragment.ACTION_EMPLOYEE_DETAILS)) {
            Parcelable parcelable = bundle.getParcelable(Constants.DATA_KEY_1);
            Employee employee = Parcels.unwrap(parcelable);
            getEmployeeDetails(employee);
        }
    }

    private void init() {
        setActionBarTitle();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);
    }

    private void setActionBarTitle() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.employees_overview);
        }
    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, fragment.getTag())
                .addToBackStack(null)
                .commit();
    }

    private void getEmployeeDetails(Employee employee) {
        Intent employeeDetailsIntent = new Intent(getApplicationContext(), DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.DATA_KEY_1, Parcels.wrap(employee));
        employeeDetailsIntent.putExtras(bundle);
        startActivity(employeeDetailsIntent);
    }
}

