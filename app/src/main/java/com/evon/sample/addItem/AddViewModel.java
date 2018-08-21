package com.evon.sample.addItem;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import com.evon.sample.db.AppDatabase;
import com.evon.sample.db.Employee;


public class AddViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

    }

    public void addItem(final Employee employee) {
        new addAsyncTask(appDatabase).execute(employee);
    }

    private static class addAsyncTask extends AsyncTask<Employee, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Employee... params) {
            db.itemAndPersonModel().addItem(params[0]);
            return null;
        }

    }
}
