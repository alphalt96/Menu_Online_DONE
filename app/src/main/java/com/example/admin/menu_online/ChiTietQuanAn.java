package com.example.admin.menu_online;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.adapters.DsMonAnTrongQuan;
import com.example.admin.menu_online.adapters.MyAdapter;
import com.example.admin.menu_online.models.MonAn;
import com.example.admin.menu_online.models.QuanAn;

import java.util.ArrayList;

public class ChiTietQuanAn extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView img;
    private TextView txtTenQuanAn;
    private TextView txtDiachi;

    private Button btnDonHang, btnMenu;

    private ArrayList<MonAn> monAnList;
    private DsMonAnTrongQuan monanAdapter;
    private MyAdapter myAdapter;
    private ArrayAdapter arrayAdapter;
    private GridView gridMonAnTrongQuan;

    private MenuOnlineDatabase menuOnlineDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_quan_an);

        addControls();
        addEvents();
    }

    private void addEvents() {
        gridMonAnTrongQuan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                if(sharedPreferences.getInt("USERID", 0)!=0) {
                    if (menuOnlineDatabase.checkDatHang(monAnList.get(position).getMaMonAn())) {
                        int maMonAn = monAnList.get(position).getMaMonAn();
                        String tenMon = monAnList.get(position).getTenMonAn();
                        int img = monAnList.get(position).getImage();
                        int soLuong = monAnList.get(position).getSoLuong();
                        String viTri = monAnList.get(position).getViTri();
                        String loaiMonAn = monAnList.get(position).getLoaiMonAn();
                        float giaTien = monAnList.get(position).getGiaTien();
                        menuOnlineDatabase.insertDatHang(maMonAn, tenMon, img, 1, viTri, loaiMonAn, giaTien);
                        Toast.makeText(ChiTietQuanAn.this, "Da them mon thanh cong", Toast.LENGTH_SHORT).show();
                        btnDonHang.setVisibility(View.VISIBLE);
                    } else
                        Toast.makeText(ChiTietQuanAn.this, "Da co san mon an nay", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChiTietQuanAn.this, "Ban can dang nhap de thuc hien chuc nang nay", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ChiTietQuanAn.this, UserLogin.class));
                }
                return false;
            }
        });
        btnDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChiTietQuanAn.this, UserInfo.class));
            }
        });
    }

    private void addControls() {
        menuOnlineDatabase = new MenuOnlineDatabase(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quán ăn");
        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setVisibility(View.GONE);
        img = (ImageView) findViewById(R.id.imgQuanAn);
        txtTenQuanAn = (TextView) findViewById(R.id.txtTenQuanAn);
        txtDiachi = (TextView) findViewById(R.id.txtViTri);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        QuanAn quanAn = (QuanAn) bundle.getSerializable("detail");
        img.setBackgroundResource(quanAn.getImg());
        txtTenQuanAn.setText(quanAn.getTenQuan());
        txtDiachi.setText(quanAn.getDiaChi());
        btnDonHang = (Button) findViewById(R.id.btnDonHang);
        btnDonHang.setVisibility(View.INVISIBLE);
        monAnList = quanAn.getMonAnList();
//        monAn = MonAnManager.getsInstance().getDanhSachMonMoi();
        monanAdapter = new DsMonAnTrongQuan(getApplicationContext(), R.layout.item_monan_trong_quanan, monAnList);
//        myAdapter = new MyAdapter(getApplicationContext(), R.layout.item_monan_trong_quanan, monAnList);
        gridMonAnTrongQuan = (GridView) findViewById(R.id.gridMonAnTrongQuan);
        gridMonAnTrongQuan.setAdapter(monanAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
}
