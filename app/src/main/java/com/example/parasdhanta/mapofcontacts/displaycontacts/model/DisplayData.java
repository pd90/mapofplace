package com.example.parasdhanta.mapofcontacts.displaycontacts.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DisplayData extends RealmObject{

    private String name,phonenumber;

    @PrimaryKey
    private String id;

    public DisplayData(String name, String phonenumber, String id) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.id = id;
    }

    public DisplayData() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
