package com.example.testprojectmvvm.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.testprojectmvvm.models.UserEntrys;
import com.example.testprojectmvvm.repositories.models.DatabaseEntrys;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;

public class DatabaseRepository {
    private static DatabaseRepository instance;
    private ArrayList<DatabaseEntrys> databaseEntrys = new ArrayList<>();

    public static DatabaseRepository getInstance(){
        if(instance == null){
            instance = new DatabaseRepository();
        }
        return instance;
    }

    public MutableLiveData<List<UserEntrys>> getUserEntrys() {
        MutableLiveData<List<UserEntrys>> data = new MutableLiveData<>();
        List<UserEntrys> list = new ArrayList<>();
        getItemFromDatabase();
        for(int i = 0; i < databaseEntrys.size();i++){
            list.add(new UserEntrys(databaseEntrys.get(i).getText()));
        }
        data.setValue(list);
        return data;
    }

    public void addNewItemToDatabase(String text) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        DatabaseEntrys userNote = realm.createObject(DatabaseEntrys.class, UUID.randomUUID().toString());
        userNote.setText(text);
        realm.commitTransaction();
    }

    public void getItemFromDatabase (){
        Realm realm = Realm.getDefaultInstance();
        databaseEntrys.addAll(realm.where(DatabaseEntrys.class).findAll());
    }

}
