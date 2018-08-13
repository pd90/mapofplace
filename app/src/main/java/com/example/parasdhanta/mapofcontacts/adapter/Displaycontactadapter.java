package com.example.parasdhanta.mapofcontacts.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parasdhanta.mapofcontacts.R;
import com.example.parasdhanta.mapofcontacts.displaycontacts.model.DisplayData;

import java.util.List;

public class Displaycontactadapter extends RecyclerView.Adapter<Displaycontactadapter.ViewHolder> {
    List<DisplayData> mList;

    public Displaycontactadapter(List<DisplayData> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_row,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DisplayData mContact = mList.get(position);
        holder.name.setText(mContact.getName());
        holder.phonenumber.setText(mContact.getPhonenumber());
        holder.id.setText(mContact.getId());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView name, phonenumber, id;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phonenumber = itemView.findViewById(R.id.phone);
            id = itemView.findViewById(R.id.id);
        }
    }
}
