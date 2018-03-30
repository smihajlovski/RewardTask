package com.smihajlovski.rewardtask.ui.details;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smihajlovski.rewardtask.R;
import com.smihajlovski.rewardtask.data.model.Employee;
import com.smihajlovski.rewardtask.databinding.FragmentDetailsBinding;
import com.smihajlovski.rewardtask.ui.base.BaseFragment;

import org.parceler.Parcels;

/**
 * Created by Stefan on 30-Mar-18.
 */

public class DetailsFragment extends BaseFragment<DetailsViewModel, FragmentDetailsBinding> {

    public static final String ARG_PARAM1 = "param1";
    private Employee employee;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_details;
    }

    @Override
    public Class<DetailsViewModel> getViewModel() {
        return DetailsViewModel.class;
    }

    public DetailsFragment() {

    }

    public static DetailsFragment newInstance(Employee employee) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, Parcels.wrap(employee));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Parcelable parcelable = getArguments().getParcelable(ARG_PARAM1);
            employee = Parcels.unwrap(parcelable);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binder = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        init();
        return binder.getRoot();
    }

    private void init() {
        binder.setEmployee(employee);
        binder.executePendingBindings();
        binder.tvEmployeeBio.setText(Html.fromHtml(employee.getBio()));
        setEmployeeAvatar();
    }

    private void setEmployeeAvatar() {
        Uri avatarUri = Uri.parse(employee.getAvatar());
        binder.draweeEmployeeAvatar.setImageURI(avatarUri);
    }
}
