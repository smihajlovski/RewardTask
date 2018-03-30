package com.smihajlovski.rewardtask.ui.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.smihajlovski.rewardtask.R;
import com.smihajlovski.rewardtask.common.Constants;
import com.smihajlovski.rewardtask.data.model.Employee;
import com.smihajlovski.rewardtask.databinding.ActivityDetailsBinding;
import com.smihajlovski.rewardtask.ui.base.BaseActivity;
import com.smihajlovski.rewardtask.ui.base.FragmentInteractionCallback;

import org.parceler.Parcels;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding> implements
        FragmentInteractionCallback,
        FragmentManager.OnBackStackChangedListener {

    private FragmentManager fragmentManager;
    private int backStackCounter = 0;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
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
        if (action.equals(DetailsFragment.ACTION_BACK)) {
            finish();
        }
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        Employee employee = Parcels.unwrap(bundle.getParcelable(Constants.DATA_KEY_1));
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);
        replaceFragment(DetailsFragment.newInstance(employee));
    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, fragment.getTag())
                .addToBackStack(null)
                .commit();
    }
}
