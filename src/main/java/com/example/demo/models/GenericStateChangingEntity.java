package com.example.demo.models;

public interface GenericStateChangingEntity<T> extends GenericEntity<T>{

    String getStateName();

    void nextState();

    void prevState();

}