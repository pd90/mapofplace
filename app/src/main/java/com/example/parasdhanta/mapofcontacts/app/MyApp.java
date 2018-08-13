package com.example.parasdhanta.mapofcontacts.app;

import android.app.Application;
import android.content.Context;

import com.evernote.android.job.JobManager;
import com.example.parasdhanta.mapofcontacts.BuildConfig;
import com.example.parasdhanta.mapofcontacts.DI.AppModule;
import com.example.parasdhanta.mapofcontacts.DI.DaggerDisplayDataComponent;
import com.example.parasdhanta.mapofcontacts.DI.DaggerDownloadDataComponent;
import com.example.parasdhanta.mapofcontacts.DI.DisplayDataComponent;
import com.example.parasdhanta.mapofcontacts.DI.DownloadDataComponent;
import com.example.parasdhanta.mapofcontacts.displaycontacts.DisplayContactsPresenter;
import com.example.parasdhanta.mapofcontacts.downloadcontacts.DownloadPresenter;
import com.example.parasdhanta.mapofcontacts.network.DemoJobCreater;

import io.realm.Realm;
import timber.log.Timber;

public class MyApp extends Application {

    private DownloadDataComponent dataDownloadComponent;

    private DisplayDataComponent displayDataComponent,utilComponent;

    private static Context mContext;

    public static Context getmContext() {
        return mContext;
    }

    public static void setmContext(Context mContext) {
        MyApp.mContext = mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dataDownloadComponent = DaggerDownloadDataComponent.builder().appModule(new AppModule(new DownloadPresenter())).build();

        displayDataComponent = DaggerDisplayDataComponent.builder().appModule(new AppModule(new DisplayContactsPresenter())).build();

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
        else{
            //production
        }

        Realm.init(this);

        JobManager.create(this).addJobCreator(new DemoJobCreater());
    }

    public DownloadDataComponent getApiComponent(){
        return dataDownloadComponent;
    }

    public DisplayDataComponent getDisplayDataComponent(){
        return  displayDataComponent;
    }



}
