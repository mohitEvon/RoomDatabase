package com.evon.sample.listItems;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.evon.sample.R;
import com.evon.sample.addItem.AddActivity;
import com.evon.sample.db.Department;
import com.evon.sample.db.Employee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    private ListViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<Employee>(),new ArrayList<Department>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);

        viewModel.getItemAndPersonList().observe(MainActivity.this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> itemAndPeople) {
                recyclerViewAdapter.addItems(itemAndPeople);
            }
        });


        viewModel.getItemDepartment().observe(MainActivity.this, new Observer<List<Department>>() {
            @Override
            public void onChanged(@Nullable List<Department> departments) {
                recyclerViewAdapter.addDepItems(departments);
            }
        });

    }

    @Override
    public boolean onLongClick(View v) {
        Employee employee = (Employee) v.getTag();
        viewModel.deleteItem(employee);
        return true;
    }
}
