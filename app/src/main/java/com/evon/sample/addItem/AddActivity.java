package com.evon.sample.addItem;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.evon.sample.R;
import com.evon.sample.db.Department;
import com.evon.sample.db.Employee;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private EditText itemEditText;
    private EditText nameEditText;

    private AddViewModel addViewModel;
    private DepViewModel department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itemEditText = findViewById(R.id.itemName);
        nameEditText = findViewById(R.id.personName);

        calendar = Calendar.getInstance();
        addViewModel = ViewModelProviders.of(this).get(AddViewModel.class);
        department = ViewModelProviders.of(this).get(DepViewModel.class);

        datePickerDialog = new DatePickerDialog(this, AddActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        FloatingActionButton fab = findViewById(R.id.fab);
       /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemEditText.getText() == null || nameEditText.getText() == null)
                    Toast.makeText(AddActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();
                else {
                    addViewModel.addItem(new Employee(nameEditText.getText().toString(), nameEditText.getText().toString(), nameEditText.getText().toString(), nameEditText.getText().toString()   ));
                    finish();
                }
            }
        });*/


        for (int i = 0; i < 9; i++) {
            if (i == 0) {
                department.addItem(new Department("it",i+""));
            } else {
                department.addItem(new Department("sales",i+""));

            }
            addViewModel.addItem(new Employee("User", "" + i,  i * 1000, ""+i));

        }

        findViewById(R.id.dateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

    }


}
