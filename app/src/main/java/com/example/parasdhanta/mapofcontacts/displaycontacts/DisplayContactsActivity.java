package com.example.parasdhanta.mapofcontacts.displaycontacts;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.parasdhanta.mapofcontacts.R;
import com.example.parasdhanta.mapofcontacts.app.MyApp;
import com.example.parasdhanta.mapofcontacts.base.BaseActivity;
import com.example.parasdhanta.mapofcontacts.displaycontacts.model.DisplayData;
import com.example.parasdhanta.mapofcontacts.eventbus.Message;
import com.example.parasdhanta.mapofcontacts.util.Util;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Adds contact data to the contacts
 */
public class DisplayContactsActivity extends BaseActivity implements DisplayContactsContract.View {
    @Inject
    DisplayContactsPresenter displayContactsPresenter;

    @Inject
    Util util;
    List<DisplayData> storeContacts  =new ArrayList<>();
    Cursor cursor;
    String name , phonenumber,id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        MyApp.setmContext(this);
        setSupportActionBar(toolbar);
        ActionBar actioBar = getSupportActionBar();
        actioBar.setTitle("Contact");
        actioBar.setDisplayHomeAsUpEnabled(true);
        actioBar.setDisplayShowHomeEnabled(true);

        ((MyApp)getApplication()).getDisplayDataComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayContactsPresenter.fetchContacts();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //display fetched contacts on the list screen


    @Override
    public void fetchDisplayOnScreen() {


         cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
         while (cursor.moveToNext()) {
             DisplayData data = new DisplayData();
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            id = cursor.getString((cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NAME_RAW_CONTACT_ID)));

            data.setId(id);
            data.setPhonenumber(phonenumber);
            data.setName(name);
            storeContacts.add(data);

            util.realmInstance().beginTransaction();

            DisplayData dbData = util.realmInstance().createObject(DisplayData.class, UUID.randomUUID().toString());

            dbData.setPhonenumber(phonenumber);
            dbData.setName(name);
            util.realmInstance().commitTransaction();
             

        }
        EventBus.getDefault().postSticky(new Message(storeContacts));
        cursor.close();
        loadFragment(new DisplayFragment());
        readData();
    }

    private void readData() {
        RealmResults<DisplayData> results = util.realmInstance().where(DisplayData.class).findAll();

        for(DisplayData result : results){
            Timber.d(result.getId()+" "+result.getName()+" "+result.getPhonenumber());
        }
    }

    @Override
    public void showError() {

    }

    //show success
    @Override
    public void showSuccess() {

    }

    public void getContactsIntoList(){
    }
}
