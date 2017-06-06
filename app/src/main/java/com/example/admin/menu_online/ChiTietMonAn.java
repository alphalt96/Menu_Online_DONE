package com.example.admin.menu_online;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.models.MonAn;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class ChiTietMonAn extends AppCompatActivity implements OnMapReadyCallback {

    private MenuOnlineDatabase menuOnlineDatabase;

    private TextView txtRenTen, txtRenGiaTien, txtRenViTri;
    private ImageView imgMonAn;
    private Button  btnMenu, btnAddCart;
    private Toolbar toolbar;
    private TextView txtLuotXem, txtLuotThich, txtHello, cartNum, txtScore;
    private MonAn monAn;

    private GoogleMap mMap;
    private String diaChi;
    private double lat =16.0595379;
    private double lng = 108.1821648;
    private location area;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon_an);
        setControl();
        setEvent();

        // khoi tao toa do cua mon an
        //phai khoi tao toa do truoc trong ham onCreate, vi luc khoi tao toa do trong onMapReady se bi loi null
        diaChi = monAn.getDiaChi().toString();
        area = new location(diaChi);
        area.getLocation();
//        Geocoder coder = new Geocoder(ChiTietMonAn.this);
//        List<Address> address;
//
//        try {
//            address = coder.getFromLocationName(diaChi,1);
//            if(address.size() > 0) {
//                Address location = address.get(0);
//                lat = location.getLatitude();
//                lng = location.getLongitude();
//            } else Toast.makeText(ChiTietMonAn.this, "Loi load map", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        FragmentActivity fragmentActivity = new FragmentActivity();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.foodmap);
        mapFragment.getMapAsync(this);

    }

    private void setEvent() {
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getSharedPreferences("userinfo", MODE_PRIVATE).getInt("USERID", 0) != 0) {
                    MonAn monAn = (MonAn) getIntent().getBundleExtra("bundle").getSerializable("detail");
                    if(menuOnlineDatabase.checkDatHang(monAn.getMaMonAn())) {
                        int maMonAn = monAn.getMaMonAn();
                        String tenMonAn = monAn.getTenMonAn();
                        int img = monAn.getImage();
                        String viTri = monAn.getViTri();
                        String loaiMonAn = monAn.getLoaiMonAn();
                        float giaTien = monAn.getGiaTien();
                        menuOnlineDatabase.insertDatHang(maMonAn, tenMonAn, img, 1, viTri, loaiMonAn, giaTien);
                        Toast.makeText(ChiTietMonAn.this, "Them mon an vao gio hang thanh cong", Toast.LENGTH_SHORT).show();
                        cartNum.setVisibility(View.VISIBLE);
                        cartNum.setText(String.valueOf(menuOnlineDatabase.getDonHang().size()));
                    } else Toast.makeText(ChiTietMonAn.this, "Mon nay da co san trong gio hang", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(ChiTietMonAn.this, "Ban can dang nhap de thuc hien chuc nang nay", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        data();

        //setup toolbar
        setupToolbar();

        txtRenTen = (TextView) findViewById(R.id.txtRenTen);
        imgMonAn = (ImageView) findViewById(R.id.imgMonAn);
        txtRenGiaTien = (TextView) findViewById(R.id.txtGiaTien);
        txtRenViTri = (TextView) findViewById(R.id.txtRenViTri);
        txtLuotXem = (TextView) findViewById(R.id.txtLuotXem);
        txtLuotThich = (TextView) findViewById(R.id.txtLuotThich);
        txtScore = (TextView) findViewById(R.id.txtScore);
        txtHello = (TextView) findViewById(R.id.txtHello);
        btnAddCart = (Button) findViewById(R.id.btnAddCart);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        monAn = (MonAn) bundle.getSerializable("detail");

        if(getSharedPreferences("userinfo", MODE_PRIVATE).getInt("USERID", 0) != 0)
            txtHello.setText(getSharedPreferences("userinfo", MODE_PRIVATE).getString("USERNAME", ""));
        else txtHello.setText("Bạn chưa đăng nhập");

        txtRenTen.setText(monAn.getTenMonAn());
        imgMonAn.setBackgroundResource(monAn.getImage());
        txtRenGiaTien.setText(String.valueOf(monAn.getGiaTien()));
        txtRenViTri.setText(monAn.getDiaChi());
        txtLuotXem.setText(String.valueOf(monAn.getLuotView()));
        txtLuotThich.setText(String.valueOf(monAn.getLuotThich()));
        txtScore.setText(new formular(monAn.getLuotView(), monAn.getLuotThich()).score());
    }

    private void data() {
        menuOnlineDatabase = new MenuOnlineDatabase(this);
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.toolBarReturnHome));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("Món ăn");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_png);
        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setVisibility(View.GONE);

        cartNum = (TextView) findViewById(R.id.txtCartNum);
        if(getSharedPreferences("userinfo", MODE_PRIVATE).getInt("USERID", 0) != 0 && menuOnlineDatabase.getDonHang().size() > 0){
            cartNum.setVisibility(View.VISIBLE);
            cartNum.setText(String.valueOf(menuOnlineDatabase.getDonHang().size()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.title_menu, menu);
        menu.findItem(R.id.search).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();
        else if(item.getItemId() == R.id.cart){
            if(getSharedPreferences("userinfo", MODE_PRIVATE).getInt("USERID", 0) != 0)
                startActivity(new Intent(ChiTietMonAn.this, UserInfo.class).putExtra("CART", true));
            else Toast.makeText(ChiTietMonAn.this, "Ban can dang nhap de thuc hien chuc nang nay", Toast.LENGTH_SHORT).show();
        }
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
            Geocoder coder = new Geocoder(ChiTietMonAn.this);
            List<Address> address;

            try {
                address = coder.getFromLocationName(this.address,1);
                if(address.size() > 0) {
                    Address location = address.get(0);
                    this.lat = location.getLatitude();
                    this.lng = location.getLongitude();
                } else Toast.makeText(ChiTietMonAn.this, "Loi load map", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private class formular{
        private int views, like;

        public int getView() {
            return views;
        }

        public void setView(int views) {
            this.views = views;
        }

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public formular() {
        }

        public formular(int view, int like) {
            this.views = view;
            this.like = like;
        }
        private float scoreCal(){
            float score = 0;
            float score1 = (this.views+this.like)/2;
            if(score1 > 0 && score1 <= 100){
                score = 1;
            } else if(score1 > 100 && score1 <= 200){
                score = 2;
            } else if(score1 > 200 && score1 <= 300){
                score = 3;
            } else if(score1 > 300 && score1 <= 400){
                score = 4;
            } else if(score1 > 400 && score1 <= 500){
                score = 5;
            } else if(score1 > 500 && score1 <= 600){
                score = 6;
            } else if(score1 > 600 && score1 <= 700){
                score = 7;
            } else if(score1 > 700 && score1 <= 800){
                score = 8;
            } else if(score1 > 800 && score1 <= 900){
                score = 9;
            } else if(score1 > 800 && score1 <= 1000){
                score = 10;
            }

            if(score < 10){
                score = score + divNum(this.views) + divNum(this.like);
                if(score > 10)
                    score = 10;
            }

            return score;
        }

        public String score(){
            return new DecimalFormat("#.#").format(scoreCal());
        }

        private float divNum(int viewlike){
            int div=1;
            if(viewlike > 0 && viewlike <= 10)
                div = 10;
            else if (viewlike > 10 && viewlike <= 100)
                div = 100;
            else if(viewlike > 100 && viewlike <= 1000)
                div = 1000;
            return (float)viewlike/div;
        }
    }
}
