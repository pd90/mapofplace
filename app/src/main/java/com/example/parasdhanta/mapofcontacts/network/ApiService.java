package com.example.parasdhanta.mapofcontacts.network;

import com.example.parasdhanta.mapofcontacts.data.Contact;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("5b4328ea2e0000830022317c")
    Single <List<Contact>> fetchContacts();
}
