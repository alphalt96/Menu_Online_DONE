package com.example.admin.menu_online;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChiTietQuanAn extends AppCompatActivity implements OnMapReadyCallback {

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

    private GoogleMap mMap;
    private location area;
    private String diaChi;
    private Button btnCloseMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_quan_an);

        addControls();
        addEvents();

        diaChi = quanAn.getDiaChi().toString();
        area = new location(diaChi);
        area.getLocation();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.foodmap);
        mapFragment.getMapAsync(this);
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
        btnCloseMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(GravityCompat.END);
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
        btnCloseMaps = (Button) findViewById(R.id.btnCloseMaps);
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
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        monAnList = quanAn.getMonAnList();
        monanAdapter = new DsMonAnTrongQuan(getApplicationContext(), R.layout.item_monan_trong_quanan, monAnList);
        lvMenuMonAn = (ListView) findViewById(R.id.lvMonAn);
        lvMenuMonAn.setAdapter(monanAdapter);
    }
    private void data() {
        menuOnlineDatabase = new MenuOnlineDatabase(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.title_menu, menu);
        menu.findItem(R.id.search).setVisible(false);
        menu.findItem(R.id.cart);
        menu.findItem(R.id.maps).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();
        else if(item.getItemId() == R.id.cart)
            if(getSharedPreferences("userinfo", MODE_PRIVATE).getInt("USERID", 0) != 0)
                startActivity(new Intent(ChiTietQuanAn.this, UserInfo.class).putExtra("CART", true));
            else Toast.makeText(ChiTietQuanAn.this, "Ban can dang nhap de thuc hien chuc nang nay", Toast.LENGTH_SHORT).show();
        else if(item.getItemId() == R.id.maps)
            drawerLayout.openDrawer(GravityCompat.END);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        location loc = new location(monAn.getDiaChi());
//        loc.getLocation();
        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(area.getLat(), area.getLng());
        mMap.addMarker(new MarkerOptions().position(location).title(diaChi));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        //zoom vị trí trỏ đến lên 16x
        float zoomLevel = 16.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));
    }
    private class location{
        double lat, lng;
        String address;

        public location(String address) {
            this.address = address;
        }
        public location(){}

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
        public void getLocation(){
            Geocoder coder = new Geocoder(ChiTietQuanAn.this);
            List<Address> address;

            try {
                address = coder.getFromLocationName(this.address,1);
                if(address.size() > 0) {
                    Address location = address.get(0);
                    this.lat = location.getLatitude();
                    this.lng = location.getLongitude();
                } else Toast.makeText(ChiTietQuanAn.this, "Loi load map", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
