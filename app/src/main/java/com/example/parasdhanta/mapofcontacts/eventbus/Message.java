package com.example.parasdhanta.mapofcontacts.eventbus;

public class Message<T> {

    private T message = null;

    public Message(T message){
        this.message =message;
    }

    public T getMessage(){
        return  message;
    }
}
