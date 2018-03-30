package com.smihajlovski.rewardtask.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.smihajlovski.rewardtask.R;
import com.smihajlovski.rewardtask.common.Constants;
import com.smihajlovski.rewardtask.databinding.ActivityMainBinding;
import com.smihajlovski.rewardtask.ui.base.BaseActivity;
import com.smihajlovski.rewardtask.ui.base.FragmentInteractionCallback;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements
        FragmentInteractionCallback,
        FragmentManager.OnBackStackChangedListener {

    FragmentManager fragmentManager;
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

    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);
    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, Integer.toString(getFragmentCount()))
                .addToBackStack(null)
                .commit();
    }

    private int getFragmentCount() {
        return fragmentManager.getBackStackEntryCount();
    }

    private Fragment getFragmentAt(int index) {
        return getFragmentCount() > 0 ? fragmentManager.findFragmentByTag(Integer.toString(index)) : null;
    }

    private Fragment getCurrentFragment() {
        return getFragmentAt(getFragmentCount() - 1);
    }
}

