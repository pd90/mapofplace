package com.example.parasdhanta.mapofcontacts.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String emailid;
    @SerializedName("lat")
    @Expose
    private String latcontact;

    @SerializedName("long")
    @Expose
    private String longcontact;

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatcontact() {
        return latcontact;
    }

    public void setLatcontact(String latcontact) {
        this.latcontact = latcontact;
    }

    public String getLongcontact() {
        return longcontact;
    }

    public void setLongcontact(String longcontact) {
        this.longcontact = longcontact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
