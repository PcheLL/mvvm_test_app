package com.example.testprojectmvvm.repositories.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DatabaseEntrys extends RealmObject {
    @PrimaryKey
    private String primaryKey;
    private String text;

    public String getPrimaryKey() {
        return primaryKey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
