package com.example.parasdhanta.mapofcontacts.displaycontacts;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parasdhanta.mapofcontacts.R;
import com.example.parasdhanta.mapofcontacts.adapter.Displaycontactadapter;
import com.example.parasdhanta.mapofcontacts.displaycontacts.model.DisplayData;
import com.example.parasdhanta.mapofcontacts.eventbus.Message;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class DisplayFragment extends Fragment {

    RecyclerView mRecyclerView;

    Displaycontactadapter mAdapter;

    public List<DisplayData> getStoreContacts() {
        return storeContacts;
    }

    public void setStoreContacts(List<DisplayData> storeContacts) {
        this.storeContacts = storeContacts;
    }

    List<DisplayData> storeContacts;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.items_contacts,container,false);

        mRecyclerView =view.findViewById(R.id.display);


        //set the recyclerview params
        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getActivity().getApplicationContext());

        mRecyclerView.setLayoutManager(mLayoutmanager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStop() {

        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageReceive(Message event){
        setStoreContacts((List<DisplayData>) event.getMessage());
        mAdapter = new Displaycontactadapter(getStoreContacts());

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
