package com.example.admin.menu_online;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.admin.menu_online.adapters.MenuQuanAnAdapter;
import com.example.admin.menu_online.controller.QuanAnManager;
import com.example.admin.menu_online.models.QuanAn;

import java.util.ArrayList;

public class MenuQuanAn extends AppCompatActivity {

    Toolbar toolbar;
    Button btnMenu;
    ArrayList<QuanAn> quanAnList;
    MenuQuanAnAdapter menuQuanAnAdapter;
    ListView lvMenuQuanAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_quan_an);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvMenuQuanAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        setupToolbar();

        quanAnList = QuanAnManager.getsInstance(this).getDanhSachQuan();
        menuQuanAnAdapter = new MenuQuanAnAdapter(this, R.layout.item_menu_quanan, quanAnList);
        lvMenuQuanAn = (ListView) findViewById(R.id.lvMenuQuanAn);
        lvMenuQuanAn.setAdapter(menuQuanAnAdapter);
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.toolBarReturnHome));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_png);
        getSupportActionBar().setTitle("Menu quán ăn");
        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
}
