package com.example.admin.menu_online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import com.example.admin.menu_online.adapters.MenuMonAnAdapter;
import com.example.admin.menu_online.controller.MonAnManager;
import com.example.admin.menu_online.models.MonAn;

public class MenuMonAn extends AppCompatActivity {

    private ArrayList<MonAn> monAnList;
    private MenuMonAnAdapter menuMonAnAdapter;
    private GridView gridMonAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_mon_an);

        addControls();
        addEvents();
    }

    private void addEvents() {
        gridMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonAn monAn = MonAnManager.getsInstance().getDanhSachMonAn().get(position);
                Intent intent = new Intent(MenuMonAn.this, ChiTietMonAn.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("detail", monAn);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        monAnList = MonAnManager.getsInstance().getDanhSachMonAn();
        menuMonAnAdapter = new MenuMonAnAdapter(this, R.layout.item_menu_monan, monAnList);
        gridMonAn = (GridView) findViewById(R.id.gridMonAn);
        gridMonAn.setAdapter(menuMonAnAdapter);
    }
}
