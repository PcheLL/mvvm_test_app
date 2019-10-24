package com.example.testprojectmvvm.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserEntrys  {
    private String text;

    public UserEntrys(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
