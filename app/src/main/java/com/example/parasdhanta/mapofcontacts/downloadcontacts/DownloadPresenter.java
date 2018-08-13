package com.example.parasdhanta.mapofcontacts.downloadcontacts;

import android.util.Log;

import com.example.parasdhanta.mapofcontacts.app.MyApp;
import com.example.parasdhanta.mapofcontacts.data.Contact;
import com.example.parasdhanta.mapofcontacts.network.ApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DownloadPresenter implements DownloadDataContract.UserActionsListener {


    CompositeDisposable disposable = new CompositeDisposable();
    // download the data here write Rx business logic here

    DownloadDataContract.View mDownloadView;


    @Override
    public void downloadContacts(ApiService apiService) {
        mDownloadView= (DownloadDataContract.View) MyApp.getmContext();

        mDownloadView.showProgress();

        disposable.add(apiService.fetchContacts().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DisposableSingleObserver<List<Contact>>() {
            @Override
            public void onSuccess(List<Contact> contactLists) {
                 // store the data in the
                mDownloadView.showCompleteStatus(contactLists);
                disposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("error",e.getMessage());
            }
        }));
    }

    @Override
    public void downloadError() {

    }

}
