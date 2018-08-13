package com.example.parasdhanta.mapofcontacts.displaycontacts;

public class DisplayContactsContract {

    interface View{
        void fetchDisplayOnScreen();

        void showError();

        void showSuccess();

    }

    interface UsersActionsListener{

        void fetchContacts();
    }
}
