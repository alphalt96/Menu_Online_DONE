package com.example.admin.menu_online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.menu_online.adapters.DsMonAnTrongQuan;
import com.example.admin.menu_online.adapters.MyAdapter;
import com.example.admin.menu_online.controller.MonAnManager;
import com.example.admin.menu_online.models.MonAn;
import com.example.admin.menu_online.models.QuanAn;

import java.util.ArrayList;

public class ChiTietQuanAn extends AppCompatActivity {

    private ImageView img;
    private TextView txtTenQuanAn;
    private TextView txtDiachi;

    private ArrayList<MonAn> monAnList;
    private DsMonAnTrongQuan monanAdapter;
    private MyAdapter myAdapter;
    private ArrayAdapter arrayAdapter;
    private GridView gridMonAnTrongQuan;

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
        monAnList = quanAn.getMonAnList();
//        monAn = MonAnManager.getsInstance().getDanhSachMonMoi();
        monanAdapter = new DsMonAnTrongQuan(getApplicationContext(), R.layout.item_monan_trong_quanan, monAnList);
//        myAdapter = new MyAdapter(getApplicationContext(), R.layout.item_monan_trong_quanan, monAnList);
        gridMonAnTrongQuan = (GridView) findViewById(R.id.gridMonAnTrongQuan);
        gridMonAnTrongQuan.setAdapter(monanAdapter);
    }
}
