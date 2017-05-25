package com.example.admin.menu_online;

import android.content.Intent;
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

public class ChiTietMonAn extends AppCompatActivity {

    private MenuOnlineDatabase menuOnlineDatabase;

    private TextView txtRenTen, txtRenGiaTien, txtRenViTri;
    private ImageView imgMonAn;
    private Button  btnMenu, btnAddCart;
    private Toolbar toolbar;
    private TextView txtLuotXem, txtLuotThich;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon_an);

        setControl();
        setEvent();
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
        btnAddCart = (Button) findViewById(R.id.btnAddCart);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        MonAn monAn = (MonAn) bundle.getSerializable("detail");

        txtRenTen.setText(monAn.getTenMonAn());
        imgMonAn.setBackgroundResource(monAn.getImage());
        txtRenGiaTien.setText(String.valueOf(monAn.getGiaTien()));
        txtRenViTri.setText(monAn.getDiaChi());
        txtLuotXem.setText(String.valueOf(monAn.getLuotView()));
        txtLuotThich.setText(String.valueOf(monAn.getLuotThich()));
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
}
