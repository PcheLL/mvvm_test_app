package com.example.testprojectmvvm.viewmodels;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testprojectmvvm.models.UserEntrys;
import com.example.testprojectmvvm.repositories.DatabaseRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<UserEntrys>> listUserEntrys;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();
    private DatabaseRepository databaseRepository;
    public void init() {
        if (listUserEntrys != null){
            return;
        }
        databaseRepository = DatabaseRepository.getInstance();
        listUserEntrys = databaseRepository.getUserEntrys();
    }

    public MutableLiveData<List<UserEntrys>> getListUserEntrys() {
        return listUserEntrys;
    }

    public MutableLiveData<Boolean> getIsUpdating() {
        return isUpdating;
    }

    @SuppressLint("StaticFieldLeak")
    public void addNewEntry(UserEntrys userEntrys) {
        isUpdating.setValue(true);
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                databaseRepository.addNewItemToDatabase(userEntrys.getText());
                List<UserEntrys> entrysList = listUserEntrys.getValue();
                entrysList.add(userEntrys);
                listUserEntrys.postValue(entrysList);
                isUpdating.setValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();
    }
}
