package com.example.parasdhanta.mapofcontacts.DI;

import com.example.parasdhanta.mapofcontacts.displaycontacts.DisplayContactsPresenter;
import com.example.parasdhanta.mapofcontacts.downloadcontacts.DownloadPresenter;
import com.example.parasdhanta.mapofcontacts.util.Util;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private DownloadPresenter presenter;

    private DisplayContactsPresenter displayContactsPresenter;

    public AppModule(DownloadPresenter downloadPresenter){
        this.presenter= downloadPresenter;
    }

    public AppModule(DisplayContactsPresenter displayContactsPresenter){
        this.displayContactsPresenter =displayContactsPresenter;
    }
    @Provides
    @Singleton

    DownloadPresenter provideApplication(){
        return  presenter;
    }
    @Provides
    @Singleton

    DisplayContactsPresenter provideDisplayPresetner(){
        return  this.displayContactsPresenter;
    }

    @Provides
    @Singleton
    public Util getUtilInstance(){
       return new Util();
    }

}
