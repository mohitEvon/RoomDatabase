package com.evon.sample.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Employee {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String firstName;
    private String lastName;
    private int salary;
    private String department_id;

    public Employee(String firstName, String lastName, int salary, String department_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department_id = department_id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_it) {
        this.department_id = department_id;
    }


}
