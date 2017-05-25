package com.example.admin.menu_online;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.adapters.DsMonAnTrongQuan;
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
    private ListView lvMenuMonAn;

    private QuanAn quanAn;
    private MenuOnlineDatabase menuOnlineDatabase;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_quan_an);

        addControls();
        addEvents();
    }

    private void addEvents() {
        lvMenuMonAn.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        data();
        //setup toolbar
        setupToolbar();

        img = (ImageView) findViewById(R.id.imgQuanAn);
        txtTenQuanAn = (TextView) findViewById(R.id.txtTenQuanAn);
        txtDiachi = (TextView) findViewById(R.id.txtViTri);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        quanAn = (QuanAn) bundle.getSerializable("detail");

        img.setBackgroundResource(quanAn.getImg());
        txtTenQuanAn.setText(quanAn.getTenQuan());
        txtDiachi.setText(quanAn.getDiaChi());
        btnDonHang = (Button) findViewById(R.id.btnDonHang);
        btnDonHang.setVisibility(View.INVISIBLE);

        setupMenuMonAn();
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.toolBarReturnHome));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_png);
        getSupportActionBar().setTitle("Quán ăn");
        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setVisibility(View.GONE);
    }
    private void setupMenuMonAn(){
        drawerLayout = (DrawerLayout) findViewById(R.id.menu_monan);
        monAnList = quanAn.getMonAnList();
        monanAdapter = new DsMonAnTrongQuan(getApplicationContext(), R.layout.item_monan_trong_quanan, monAnList);
        lvMenuMonAn = (ListView) findViewById(R.id.lvMonAnDrawer);
        lvMenuMonAn.setAdapter(monanAdapter);
    }
    private void data() {
        menuOnlineDatabase = new MenuOnlineDatabase(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.title_menu, menu);
        menu.findItem(R.id.search).setVisible(false);
        menu.findItem(R.id.cart).setVisible(false);
        menu.findItem(R.id.list).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();
        else if(item.getItemId() == R.id.list)
            drawerLayout.openDrawer(GravityCompat.END);
        return super.onOptionsItemSelected(item);
    }
}
