package com.evon.sample.listItems;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evon.sample.R;
import com.evon.sample.db.Department;
import com.evon.sample.db.Employee;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Employee> employeeList;
    private List<Department> department;
    private View.OnLongClickListener longClickListener;

    public RecyclerViewAdapter(List<Employee> employeeList, ArrayList<Department> department, View.OnLongClickListener longClickListener) {
        this.employeeList = employeeList;
        this.department = department;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.firstTextView.setText(employee.getFirstName() + employee.getLastName());
        holder.lastTextView.setText(employee.getSalary()+"");
        for (int i = 0; i < department.size(); i++) {
            if (department.get(i).getDepID().equals(employee.getDepartment_id())) {
                holder.depTextView.setText(department.get(i).getDepName());
            }
        }

        holder.itemView.setTag(employee);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public void addItems(List<Employee> employeeList) {
        this.employeeList = employeeList;
        notifyDataSetChanged();
    }

    public void addDepItems(List<Department> departments) {
        this.department = departments;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView firstTextView;
        private TextView lastTextView;
        private TextView salaryTextView;
        private TextView depTextView;

        RecyclerViewHolder(View view) {
            super(view);
            firstTextView = view.findViewById(R.id.itemTextView);
            lastTextView = view.findViewById(R.id.nameTextView);
            salaryTextView = view.findViewById(R.id.dateTextView);
            depTextView = view.findViewById(R.id.dateTextView);
        }
    }
}