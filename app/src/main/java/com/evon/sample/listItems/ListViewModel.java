package com.evon.sample.listItems;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.evon.sample.db.AppDatabase;
import com.evon.sample.db.Department;
import com.evon.sample.db.Employee;

import java.util.List;


public class ListViewModel extends AndroidViewModel {

    private final LiveData<List<Employee>> itemAndPersonList;
   // private final LiveData<List<Employee>> itemAndPersonLists;
    private final LiveData<List<Department>> itemDepartment;

    private AppDatabase appDatabase;

    public ListViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

       // itemAndPersonList = appDatabase.itemAndPersonModel().getAllItems();
       // itemAndPersonList = appDatabase.itemAndPersonModel().getSecondHighestSalary();
       // itemAndPersonList = appDatabase.itemAndPersonModel().getSalaryGraterThan10k();
        itemAndPersonList = appDatabase.itemAndPersonModel().getHighestSalaryInSales();
        itemDepartment = appDatabase.itemAndPersonModel().getAllDep();
    }


    public LiveData<List<Employee>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public LiveData<List<Department>> getItemDepartment() {
        return itemDepartment;
    }

    public void deleteItem(Employee employee) {
        new deleteAsyncTask(appDatabase).execute(employee);
    }

    private static class deleteAsyncTask extends AsyncTask<Employee, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Employee... params) {
            db.itemAndPersonModel().deleteItem(params[0]);
            return null;
        }

    }

}
