package com.example.admin.menu_online;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.adapters.MyAdapter;
import com.example.admin.menu_online.adapters.QuanAnAdapter;
import com.example.admin.menu_online.controller.MonAnManager;
import com.example.admin.menu_online.controller.QuanAnManager;

public class bestchoice extends AppCompatActivity {

    private MenuOnlineDatabase menuOnlineDatabase;
    private boolean siteCheck = true;
    private Toolbar toolbar;

    private LinearLayout layoutChinh, chooseLayout;
    private RelativeLayout backLayout;

    private Button btnNewMonAn, btnNewQuanAn;
    private Button btnBack, btnMenu;
    private MyAdapter monAnNoiBat;
    private QuanAnAdapter quanAnNoiBat;
    private ListView lvBestChoice;
    private TextView cartNum, txtTitle;
    private ImageView imgBackgroundCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestchoice);

        setControls();
        setEvents();
    }

    private void setControls() {
        data();
        setupToolbar();
        getWidgets();

    }

    private void getWidgets() {
        btnNewMonAn = (Button) findViewById(R.id.btnNewMonAn);
        btnNewQuanAn = (Button) findViewById(R.id.btnNewQuanAn);
        btnBack = (Button) findViewById(R.id.btnBack);
        layoutChinh = (LinearLayout) findViewById(R.id.layoutChinh);
        chooseLayout = (LinearLayout) findViewById(R.id.chooseLayout);
        backLayout = (RelativeLayout) findViewById(R.id.backLayout);
        lvBestChoice = (ListView) findViewById(R.id.lvBestChoice);
        imgBackgroundCover = (ImageView) findViewById(R.id.imgBackgroundCover);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
    }

    private void data() {
        menuOnlineDatabase = new MenuOnlineDatabase(this);
    }

    private void setEvents() {
        btnNewMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideChoose();
                monAnNoiBat = new MyAdapter(bestchoice.this, R.layout.item_monan_noibat, MonAnManager.getsInstance(bestchoice.this).getDanhSachNoiBat());
                lvBestChoice.setAdapter(monAnNoiBat);
                siteCheck = true;
                txtTitle.setText("Top món ăn hot trong tuần");
            }
        });
        btnNewQuanAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideChoose();
                quanAnNoiBat = new QuanAnAdapter(bestchoice.this, R.layout.item_quanan, QuanAnManager.getsInstance(bestchoice.this).getDanhSachQuanMoi());
                lvBestChoice.setAdapter(quanAnNoiBat);
                siteCheck = false;
                txtTitle.setText("Top quán ăn nổi bật");
            }
        });
        lvBestChoice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(siteCheck == true){
                    Intent intent = new Intent(bestchoice.this, ChiTietMonAn.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("detail", MonAnManager.getsInstance(bestchoice.this).getDanhSachMonMoi().get(position));
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(bestchoice.this, ChiTietQuanAn.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("detail", QuanAnManager.getsInstance(bestchoice.this).getDanhSachQuanMoi().get(position));
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoose();
                txtTitle.setText("");
            }
        });
    }
    //Toolbar
    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.toolBarReturnHome));
        getSupportActionBar().setTitle("Nổi bật");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_png);
        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setVisibility(View.GONE);

        cartNum = (TextView) findViewById(R.id.txtCartNum);
        if(getSharedPreferences("userinfo", MODE_PRIVATE).getInt("USERID", 0) != 0 && menuOnlineDatabase.getDonHang().size() > 0){
            cartNum.setVisibility(View.VISIBLE);
            cartNum.setText(String.valueOf(menuOnlineDatabase.getDonHang().size()));
        }
    }
    private void showChoose(){
        chooseLayout.setVisibility(View.VISIBLE);
        lvBestChoice.setVisibility(View.GONE);
        backLayout.setVisibility(View.GONE);
        layoutChinh.setBackgroundResource(R.drawable.bestchoice_background);
        imgBackgroundCover.setBackgroundColor(Color.parseColor("#66000000"));
    }
    private void hideChoose(){
        chooseLayout.setVisibility(View.GONE);
        lvBestChoice.setVisibility(View.VISIBLE);
        backLayout.setVisibility(View.VISIBLE);
        layoutChinh.setBackgroundColor(Color.parseColor("#ffffff"));
        imgBackgroundCover.setBackgroundResource(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        else if (item.getItemId() == R.id.cart) {
            if (getSharedPreferences("userinfo", MODE_PRIVATE).getInt("USERID", 0) != 0)
                startActivity(new Intent(bestchoice.this, UserInfo.class).putExtra("CART", true));
            else
                Toast.makeText(bestchoice.this, "Ban can dang nhap de thuc hien chuc nang nay", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
