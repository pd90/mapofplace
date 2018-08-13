package com.example.parasdhanta.mapofcontacts.util;

import io.realm.Realm;

public class Util {
    public Realm realmInstance() {
        return Realm.getDefaultInstance();
    }
}
