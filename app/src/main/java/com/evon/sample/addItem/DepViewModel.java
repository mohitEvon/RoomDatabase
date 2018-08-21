package com.evon.sample.addItem;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import com.evon.sample.db.AppDatabase;
import com.evon.sample.db.Department;
import com.evon.sample.db.Employee;


public class DepViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public DepViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

    }

    public void addItem(final Department department) {
        new addAsyncTask(appDatabase).execute(department);
    }

    private static class addAsyncTask extends AsyncTask<Department, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Department... params) {
            db.itemAndPersonModel().addItem(params[0]);
            return null;
        }

    }
}
