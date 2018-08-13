package com.example.parasdhanta.mapofcontacts.DI;

import com.example.parasdhanta.mapofcontacts.displaycontacts.DisplayContactsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface DisplayDataComponent {
    void inject(DisplayContactsActivity displayContactsActivity);
}
