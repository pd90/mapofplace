package com.example.parasdhanta.mapofcontacts.downloadcontacts;

import com.example.parasdhanta.mapofcontacts.data.Contact;
import com.example.parasdhanta.mapofcontacts.network.ApiService;

import java.util.List;

public class DownloadDataContract {

     interface View{
        void showProgress();

        void hideProgress();

        void showCompleteStatus(List<Contact> contacts);

        void ShowSuccess();

        void showError();
    }

    interface  UserActionsListener{
        void downloadContacts(ApiService apiService);

        void downloadError();
    }
}
