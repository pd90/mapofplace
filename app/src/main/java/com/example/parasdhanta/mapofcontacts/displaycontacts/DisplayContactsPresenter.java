package com.example.parasdhanta.mapofcontacts.displaycontacts;

import com.example.parasdhanta.mapofcontacts.app.MyApp;

/**
 * presenter class for adding contacts
 * Listens to user actions from the UI ({@link DisplayContactsActivity}), retrieves the data and updates
 * the UI as required.
 */
public class DisplayContactsPresenter implements DisplayContactsContract.UsersActionsListener {

    DisplayContactsContract.View displauView;
    @Override
    public void fetchContacts() {
        displauView = (DisplayContactsContract.View) MyApp.getmContext();
        displauView.fetchDisplayOnScreen();
    }
}
