package com.example.admin.menu_online;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.adapters.DonHangAdapter;
import com.example.admin.menu_online.models.MonAn;

import java.util.ArrayList;

public class UserInfo extends AppCompatActivity {

    private MenuOnlineDatabase menuOnlineDatabase;
    private TabHost tabHost;
    private EditText txtEditUsername, txtEditPassword, txtEditAddress, txtEditSoDienThoai, txtEditEmail;
    private Button btnEdit, btnCart, btnLogout, btnBack, btnSave, btnCancel, btnMenu;
    private TextView txtHienThiUsername, txtHienThiPassword, txtHienThiAddress, txtHienThiSoDienThoai, txtHienThiEmail;

    private Toolbar toolbar;
    private Button btnShip, btnClear;
    private TextView txtTotalCost, cartNum;

    private ArrayList<MonAn> monAnArrayList;
    private DonHangAdapter donHangAdapter;
    private ListView lvDonHang;

    private boolean CHECK=false;

    float tongTien=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        getWidgets();
        setControls();
        setEvents();

        checkActivity();

        SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        if(sharedPreferences.getInt("USERID", 0)!=0) {
            txtHienThiUsername.setText(sharedPreferences.getString("USERNAME", ""));
            txtHienThiPassword.setText(sharedPreferences.getString("PASSWORD", ""));
            txtHienThiAddress.setText(sharedPreferences.getString("ADDRESS", ""));
            txtHienThiSoDienThoai.setText(sharedPreferences.getString("SODIENTHOAI", ""));
            txtHienThiEmail.setText(sharedPreferences.getString("EMAIL", ""));
            //An di phan edit profile
            HideEdit();
        }
    }

    private void checkActivity() {
        if(getIntent().getBooleanExtra("CART", false) == true) {
            tabHost.setCurrentTab(1);
            CHECK = true;
        }
    }

    private void setControls() {
        data();
        setupToolbar();
        setupTabhost();

        setupCart();

    }

    private void data() {
        menuOnlineDatabase = new MenuOnlineDatabase(this);
    }

    private void setupCart() {
        monAnArrayList = menuOnlineDatabase.getDonHang();
        donHangAdapter = new DonHangAdapter(UserInfo.this, R.layout.item_donhang, monAnArrayList, txtTotalCost);
        lvDonHang = (ListView) findViewById(R.id.lvDonHang);
        lvDonHang.setAdapter(donHangAdapter);
    }

    private void setupTabhost() {
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setIndicator("Thông tin");
        tab1.setContent(R.id.userTab1);
        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setIndicator("Giỏ hàng");
        tab2.setContent(R.id.userTab2);
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);

        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.pressed_effect); // selected
        TextView textColor = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title);
        textColor.setTextColor(Color.parseColor("#ffffff"));
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.toolBarReturnHome));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_png);
        getSupportActionBar().setTitle("Người dùng");

        cartNum = (TextView) findViewById(R.id.txtCartNum);
        cartNum.setVisibility(View.GONE);
    }

    private void getWidgets() {
        txtEditUsername = (EditText) findViewById(R.id.txtEditUsername);
        txtEditPassword = (EditText) findViewById(R.id.txtEditPassword);
        txtEditAddress = (EditText) findViewById(R.id.txtEditAddress);
        txtEditSoDienThoai = (EditText) findViewById(R.id.txtEditSoDienThoai);
        txtEditEmail = (EditText) findViewById(R.id.txtEditEmail);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnCart = (Button) findViewById(R.id.btnCart);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setVisibility(View.GONE);
        txtHienThiUsername = (TextView) findViewById(R.id.txtHienThiUsername);
        txtHienThiPassword = (TextView) findViewById(R.id.txtHienThiPassword);
        txtHienThiAddress = (TextView) findViewById(R.id.txtHienThiAddress);
        txtHienThiSoDienThoai = (TextView) findViewById(R.id.txtHienThiSoDienThoai);
        txtHienThiEmail = (TextView) findViewById(R.id.txtHienThiEmail);
        txtTotalCost = (TextView) findViewById(R.id.txtTotalcost);
        btnShip = (Button) findViewById(R.id.btnShip);
        btnClear = (Button) findViewById(R.id.btnClear);
    }

    private void setEvents(){
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for(int i=0;i<tabHost.getTabWidget().getChildCount();i++) {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#F47B68")); //unselected
                    TextView textColor = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    textColor.setTextColor(Color.parseColor("#000000"));
                }
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.pressed_effect); // selected
                TextView textColor = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title);
                textColor.setTextColor(Color.parseColor("#ffffff"));
            }
        });
        userinfoEvents();
        cartEvents();
    }

    private void cartEvents() {
        btnShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monAnArrayList.size()>0) {
                    menuOnlineDatabase.deleteAllDonHang();
                    monAnArrayList.clear();
                    donHangAdapter.notifyDataSetChanged();
                    Toast.makeText(UserInfo.this, "Cam on ban da mua hang", Toast.LENGTH_SHORT).show();
                    txtTotalCost.setText("0.0");
                } else Toast.makeText(UserInfo.this, "Ban chua co mat hang nao hien tai", Toast.LENGTH_SHORT).show();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOnlineDatabase.deleteAllDonHang();
                monAnArrayList.clear();
                donHangAdapter.notifyDataSetChanged();
                txtTotalCost.setText("0.0");
            }
        });
        for(int i=0; i<menuOnlineDatabase.getDonHang().size(); i++){
            tongTien += menuOnlineDatabase.getDonHang().get(i).getGiaTien()*menuOnlineDatabase.getDonHang().get(i).getSoLuong();
        }
        txtTotalCost.setText(String.valueOf(tongTien));
    }

    private void userinfoEvents() {
        final SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("USERID", 0);
                editor.putString("USERNAME", "");
                editor.putString("PASSWORD", "");
                editor.putString("ADDRESS", "");
                editor.putString("SODIENTHOAI", "");
                editor.putString("EMAIL", "");
                editor.commit();
                startActivity(new Intent(UserInfo.this, UserLogin.class));
                finish();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEditUsername.setText(sharedPreferences.getString("USERNAME", ""));
                txtEditPassword.setText(sharedPreferences.getString("PASSWORD", ""));
                txtEditAddress.setText(sharedPreferences.getString("ADDRESS", ""));
                txtEditSoDienThoai.setText(sharedPreferences.getString("SODIENTHOAI", ""));
                txtEditEmail.setText(sharedPreferences.getString("EMAIL", ""));
                HideInfoText();
                ShowEdit();
                tabHost.setCurrentTab(0);
                tabHost.getTabWidget().getChildTabViewAt(1).setEnabled(false);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOnlineDatabase.updateUser(txtEditUsername.getText().toString(), txtEditPassword.getText().toString(), txtEditAddress.getText().toString(), txtEditSoDienThoai.getText().toString(), txtEditEmail.getText().toString());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("USERNAME", txtEditUsername.getText().toString());
                editor.putString("PASSWORD", txtEditPassword.getText().toString());
                editor.putString("ADDRESS", txtEditAddress.getText().toString());
                editor.putString("SODIENTHOAI", txtEditSoDienThoai.getText().toString());
                editor.putString("EMAIL", txtEditEmail.getText().toString());
                editor.commit();

                txtHienThiUsername.setText(txtEditUsername.getText().toString());
                txtHienThiPassword.setText(txtEditPassword.getText().toString());
                txtHienThiAddress.setText(txtEditAddress.getText().toString());
                txtHienThiSoDienThoai.setText(txtEditSoDienThoai.getText().toString());
                txtHienThiEmail.setText(txtEditEmail.getText().toString());

                txtEditUsername.setText("");
                txtEditPassword.setText("");
                txtEditAddress.setText("");
                txtEditSoDienThoai.setText("");
                txtHienThiEmail.setText("");

                HideEdit();
                ShowInfoText();

                tabHost.getTabWidget().getChildTabViewAt(1).setEnabled(true);
                Toast.makeText(UserInfo.this, "Luu thong tin thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEditUsername.setText("");
                txtEditPassword.setText("");
                txtEditAddress.setText("");
                txtEditSoDienThoai.setText("");
                txtEditEmail.setText("");
                HideEdit();
                ShowInfoText();
                tabHost.getTabWidget().getChildTabViewAt(1).setEnabled(true);
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabHost.setCurrentTab(1);
            }
        });
    }

    private void HideEdit(){
        btnSave.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
        txtEditUsername.setVisibility(View.GONE);
        txtEditPassword.setVisibility(View.GONE);
        txtEditAddress.setVisibility(View.GONE);
        txtEditSoDienThoai.setVisibility(View.GONE);
        txtEditEmail.setVisibility(View.GONE);
    }
    private void ShowEdit(){
        btnSave.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
        txtEditUsername.setVisibility(View.VISIBLE);
        txtEditPassword.setVisibility(View.VISIBLE);
        txtEditAddress.setVisibility(View.VISIBLE);
        txtEditSoDienThoai.setVisibility(View.VISIBLE);
        txtEditEmail.setVisibility(View.VISIBLE);
    }
    private void HideInfoText(){
        txtHienThiUsername.setVisibility(View.GONE);
        txtHienThiPassword.setVisibility(View.GONE);
        txtHienThiAddress.setVisibility(View.GONE);
        txtHienThiSoDienThoai.setVisibility(View.GONE);
        txtHienThiEmail.setVisibility(View.GONE);
        btnCart.setVisibility(View.GONE);
        btnLogout.setVisibility(View.GONE);
    }
    private void ShowInfoText(){
        txtHienThiUsername.setVisibility(View.VISIBLE);
        txtHienThiPassword.setVisibility(View.VISIBLE);
        txtHienThiAddress.setVisibility(View.VISIBLE);
        txtHienThiSoDienThoai.setVisibility(View.VISIBLE);
        txtHienThiEmail.setVisibility(View.VISIBLE);
        btnCart.setVisibility(View.VISIBLE);
        btnLogout.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            if(CHECK == true) finish();
            else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(CHECK == true) finish();
        else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            super.onBackPressed();
        }
    }
}
