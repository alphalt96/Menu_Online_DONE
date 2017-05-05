package com.example.admin.menu_online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.admin.menu_online.adapters.MenuQuanAnAdapter;
import com.example.admin.menu_online.controller.QuanAnManager;
import com.example.admin.menu_online.models.QuanAn;

import java.util.ArrayList;

public class MenuQuanAn extends AppCompatActivity {

    ArrayList<QuanAn> quanAnList;
    MenuQuanAnAdapter menuQuanAnAdapter;
    GridView gridMenuQuanAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_quan_an);
        addControls();
        addEvents();
    }

    private void addEvents() {
        gridMenuQuanAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MenuQuanAn.this, ChiTietQuanAn.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("detail", quanAnList.get(position));
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        quanAnList = QuanAnManager.getsInstance(this).getDanhSachQuan();
        menuQuanAnAdapter = new MenuQuanAnAdapter(this, R.layout.item_menu_quanan, quanAnList);
        gridMenuQuanAn = (GridView) findViewById(R.id.gridMenuQuanAn);
        gridMenuQuanAn.setAdapter(menuQuanAnAdapter);
    }
}
