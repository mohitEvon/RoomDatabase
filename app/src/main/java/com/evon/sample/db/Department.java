package com.evon.sample.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Department {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String depName;
    private String depID;

    public Department(String depName,String depID) {
        this.depName = depName;
        this.depID = depID;

    }


    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepID() {
        return depID;
    }

    public void setDepID(String depID) {
        this.depID = depID;
    }
}
