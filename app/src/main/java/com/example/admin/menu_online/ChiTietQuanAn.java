package com.example.admin.menu_online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import models.QuanAn;

public class ChiTietQuanAn extends AppCompatActivity {

    ImageView img;
    TextView txtTenQuanAn;
    TextView txtDiachi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_quan_an);

        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        img = (ImageView) findViewById(R.id.imgQuanAn);
        txtTenQuanAn = (TextView) findViewById(R.id.txtTenQuanAn);
        txtDiachi = (TextView) findViewById(R.id.txtViTri);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        QuanAn quanAn = (QuanAn) bundle.getSerializable("detail");
        img.setBackgroundResource(quanAn.getImg());
        txtTenQuanAn.setText(quanAn.getTenQuan());
        txtDiachi.setText(quanAn.getDiaChi());

    }
}
