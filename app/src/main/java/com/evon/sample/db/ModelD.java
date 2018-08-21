package com.evon.sample.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public interface ModelD {

    @Query("select * from Employee")
    LiveData<List<Employee>> getAllItems();

    @Query("select * from Employee where salary = (select max(salary) from Employee where salary < (select max(salary)  from Employee))")
    LiveData<List<Employee>> getSecondHighestSalary();

    @Query("select * from Employee where salary>7000")
    LiveData<List<Employee>> getSalaryGraterThan10k();

    @Query("select *,max(salary) from Employee inner join Department on depID = Department.depID")
    LiveData<List<Employee>> getHighestSalaryInSales();

    @Query("select * from Department")
    LiveData<List<Department>> getAllDep();

    @Query("select * from Department where id = :id")
    Department getDepartmentbyId(String id);

    @Insert(onConflict = REPLACE)
    void addItem(Department employee);

    @Insert(onConflict = REPLACE)
    void addItem(Employee employee);

    @Delete
    void deleteItem(Employee employee);

}
