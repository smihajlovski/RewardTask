package com.smihajlovski.rewardtask.ui.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smihajlovski.rewardtask.R;
import com.smihajlovski.rewardtask.data.model.Employee;
import com.smihajlovski.rewardtask.databinding.ItemEmployeeBinding;

import java.util.List;

/**
 * Adapter for showing list of employees
 * <p>
 * Created by Stefan on 29-Mar-18.
 */

public class EmployeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Employee> employeeList;
    private OnItemClickListener listener;

    EmployeeAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindEmployee((ViewHolder) holder, employeeList.get(position));
    }

    private void bindEmployee(ViewHolder holder, Employee employee) {
        holder.getBinder().setEmployee(employee);
        setEmployeeAvatar(holder, employee);
        holder.getBinder().employeeItemHolder.setOnClickListener(v -> listener.onItemClick(employee));
    }

    private void setEmployeeAvatar(ViewHolder holder, Employee employee) {
        if (!TextUtils.isEmpty(employee.getAvatar())) {
            Uri avatarUri = Uri.parse(employee.getAvatar());
            holder.getBinder().draweeEmployeeAvatar.setImageURI(avatarUri);
        }
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemEmployeeBinding binder;

        ViewHolder(View itemView) {
            super(itemView);
            binder = DataBindingUtil.bind(itemView);
        }

        ItemEmployeeBinding getBinder() {
            return binder;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Employee employee);
    }

    void addOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    void removeOnItemClickListener() {
        this.listener = null;
    }
}
