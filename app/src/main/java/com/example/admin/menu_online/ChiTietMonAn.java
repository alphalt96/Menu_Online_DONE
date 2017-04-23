package com.example.admin.menu_online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import models.MonAn;

public class ChiTietMonAn extends AppCompatActivity {

    TextView txtRenTen, txtRenSoLuong, txtRenViTri;
    ImageView imgMonAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon_an);

        setControl();
        setEvent();
    }

    private void setEvent() {
    }

    private void setControl() {
        txtRenTen = (TextView) findViewById(R.id.txtRenTen);
        imgMonAn = (ImageView) findViewById(R.id.imgMonAn);
        txtRenSoLuong = (TextView) findViewById(R.id.txtRenSoLuong);
        txtRenViTri = (TextView) findViewById(R.id.txtRenViTri);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        MonAn monAn = (MonAn) bundle.getSerializable("detail");
        txtRenTen.setText(monAn.getTenMonAn());
        imgMonAn.setBackgroundResource(monAn.getImage());
        txtRenSoLuong.setText(String.valueOf(monAn.getSoLuong()));
        txtRenViTri.setText(monAn.getViTri());
    }
}
