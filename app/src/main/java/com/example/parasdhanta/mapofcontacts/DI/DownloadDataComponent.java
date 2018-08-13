package com.example.parasdhanta.mapofcontacts.DI;

import com.example.parasdhanta.mapofcontacts.downloadcontacts.DownloadContactsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface DownloadDataComponent {
    void inject(DownloadContactsActivity downloadContactsActivity);

}
