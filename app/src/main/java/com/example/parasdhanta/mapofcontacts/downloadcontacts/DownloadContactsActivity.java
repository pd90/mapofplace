package com.example.parasdhanta.mapofcontacts.downloadcontacts;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parasdhanta.mapofcontacts.R;
import com.example.parasdhanta.mapofcontacts.app.MyApp;
import com.example.parasdhanta.mapofcontacts.data.Contact;
import com.example.parasdhanta.mapofcontacts.displaycontacts.DisplayContactsActivity;
import com.example.parasdhanta.mapofcontacts.network.ApiClient;
import com.example.parasdhanta.mapofcontacts.network.ApiService;
import com.example.parasdhanta.mapofcontacts.network.ShowNotificationJob;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

public class DownloadContactsActivity extends AppCompatActivity implements DownloadDataContract.View{


    @Inject

    DownloadPresenter downloadPresenter;
    ApiService apiService;
    private TextView tView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        MyApp.setmContext(this);
        //set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actioBar = getSupportActionBar();
        actioBar.setTitle("Main");
        actioBar.setDisplayHomeAsUpEnabled(true);
        actioBar.setDisplayShowHomeEnabled(true);
        apiService = ApiClient.getClient(getApplicationContext()).create(ApiService.class);

        ((MyApp) getApplication()).getApiComponent().inject(this);
        tView = findViewById(R.id.textView);

        ShowNotificationJob.schedulePeriodic();

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showCompleteStatus(List<Contact> contacts) {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            writePhoneContact(contact.getName(),contact.getLatcontact(),contact.getLongcontact(),this);
        }
    }


    @Override
    public void ShowSuccess() {
        Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.download:
                downloadPresenter.downloadContacts(apiService);
                break;
            case R.id.add:
                startActivity(new Intent(DownloadContactsActivity.this, DisplayContactsActivity.class));
                break;
            case R.id.map:
                break;
        }
    }

    public void writePhoneContact(String displayName, String number,String homeNo, Context cntx /*App or Activity Ctx*/) {
        Context context = cntx; //Application's context or Activity's context
        String strDisplayName = displayName; // Name of the Person to add
        String strNumber = number; //number of the person to add with the Contact
        ArrayList<ContentProviderOperation> cntProOper = new ArrayList<>();
        int contactIndex = cntProOper.size();//ContactSize

        //Newly Inserted contact
        // A raw contact will be inserted ContactsContract.RawContacts table in contacts database.
        cntProOper.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)//Step1
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());

        //Display name will be inserted in ContactsContract.Data table
        cntProOper.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)//Step2
                .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, contactIndex)
                .withValue(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, strDisplayName) // Name of the contact
                .build());
        //Mobile number will be inserted in ContactsContract.Data table
        cntProOper.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)//Step 3
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, contactIndex)
                .withValue(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, strNumber) // Number to be added
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).build()); //Type like HOME, MOBILE etc

        //Mobile number will be inserted in ContactsContract.Data table
        cntProOper.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)//Step 4
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, contactIndex)
                .withValue(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, homeNo) // Number to be added
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME).build()); //Type like HOME, MOBILE etc
        ShowSuccess();
        try {
            // We will do batch operation to insert all above data
            //Contains the output of the app of a ContentProviderOperation.
            //It is sure to have exactly one of uri or count set
            ContentProviderResult[] contentProresult = null;
            contentProresult = context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, cntProOper); //apply above data insertion into contacts list
        } catch (RemoteException exp) {
            //logs;
        } catch (OperationApplicationException exp) {
            //logs
        }
    }
}
