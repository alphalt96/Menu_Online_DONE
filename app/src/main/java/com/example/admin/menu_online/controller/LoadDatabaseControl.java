package com.example.admin.menu_online.controller;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Yep on 11/5/2017.
 */

public class LoadDatabaseControl {
    private static LoadDatabaseControl sInstance = null;
    private Context context;
    private int countStartup;

    private LoadDatabaseControl(Context context){
        this.context = context;
        this.countStartup = context.getSharedPreferences("LAUNCH_APP", Context.MODE_PRIVATE).getInt("COUNT_START_UP", 0);
    }

    public int getCountStartup() {
        return countStartup;
    }

    public static LoadDatabaseControl getsInstance(Context context){
        if(sInstance == null){
            sInstance = new LoadDatabaseControl(context);
        }
        return sInstance;
    }
    public void increaseCountStartUpApp(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LAUNCH_APP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        this.countStartup++;
        editor.putInt("COUNT_START_UP", countStartup);
        editor.commit();
    }
    public boolean isFirstLoadApp(){
        return countStartup == 1;
    }
}
