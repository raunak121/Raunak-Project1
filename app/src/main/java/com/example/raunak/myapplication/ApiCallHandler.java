package com.example.raunak.myapplication;

public interface ApiCallHandler {

    void onApiSuccess();
    void onApiFailure(Exception c);
    void onNoConnection();



}
