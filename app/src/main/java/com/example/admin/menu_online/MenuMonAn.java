package com.example.admin.menu_online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

import controller.MenuMonAnAdapter;
import controller.MonAnManager;
import models.MonAn;

public class MenuMonAn extends AppCompatActivity {

    ArrayList<MonAn> monAnList;
    MenuMonAnAdapter menuMonAnAdapter;
    GridView gridMonAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_mon_an);

        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
//        monAnList = new ArrayList<>();
        monAnList = MonAnManager.getsInstance().getDanhSachMonAn();
        menuMonAnAdapter = new MenuMonAnAdapter(this, R.layout.activity_menu_mon_an, monAnList);
        gridMonAn = (GridView) findViewById(R.id.gridMonAn);
        gridMonAn.setAdapter(menuMonAnAdapter);
    }
}
