package com.example.admin.menu_online.controller;

import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Yep on 11/5/2017.
 */

public class LoadDatabaseControl {
    private static LoadDatabaseControl sInstance = null;
    private int countStartup = 0;

    private LoadDatabaseControl(){

    }

    public static LoadDatabaseControl getsInstance(){
        if(sInstance == null){
            sInstance = new LoadDatabaseControl();
        }
        return sInstance;
    }
    public void Count(){
        countStartup++;
    }
}
