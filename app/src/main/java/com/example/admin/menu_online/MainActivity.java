package com.example.admin.menu_online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import controller.LoaiMonAnAdapter;
import controller.MonAnManager;
import controller.MyAdapter;
import models.MonAn;

public class MainActivity extends AppCompatActivity {


    ArrayList<MonAn> monAnNoiBat, locMonAn;
    private String[] cityList, loaimonanList;
    private int[] arrImgLoaiMonAn;
    private MyAdapter myAdapter;
    private LoaiMonAnAdapter loaiMonAnAdapter;
    private ArrayAdapter<String> adapterCity;
    private ListView lvHienThiMonAn, lvCity, lvLoaiMonAn;
    private TextView txtTitle;
    private Button btnNewMonAn, btnNewQuanAn, btnMenu;

    String loaiMonAn="", thanhPho="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, btnMenu);
                popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if(id == R.id.monAnList){
                            Intent intent = new Intent(MainActivity.this, MenuMonAn.class);
                            startActivity(intent);
                        }
                        else if(id == R.id.quanAnList){
                            Toast.makeText(getApplicationContext(), "Hiện chưa có chức năng này", Toast.LENGTH_SHORT).show();
                        }
                        else if(id == R.id.ranking){
                            Toast.makeText(getApplicationContext(), "Hiện chưa có chức năng này", Toast.LENGTH_SHORT).show();
                        }
                        else if(id == R.id.info){
                            Toast.makeText(getApplicationContext(), "Version 1.1.3.1415926535897932", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        lvHienThiMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ChiTietMonAn.class);
                Bundle bundle = new Bundle();
                if(loaiMonAn == "" && thanhPho == "")
                    bundle.putSerializable("detail",MonAnManager.getsInstance().getDanhSachMonMoi().get(position));
                else
                    bundle.putSerializable("detail",locMonAn.get(position));
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
        //Button lọc món theo thành phố
        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                monAnNoiBat.clear();
//                myAdapter.notifyDataSetChanged();
                locMonAn = new ArrayList<MonAn>();
                thanhPho = cityList[position];
                for(int i=0; i<MonAnManager.getsInstance().getDanhSachMonAn().size(); i++){
                    if(loaiMonAn == "") {
                        if (MonAnManager.getsInstance().getDanhSachMonAn().get(i).getViTri().equals(thanhPho)) {
                            locMonAn.add(MonAnManager.getsInstance().getDanhSachMonAn().get(i));
                            txtTitle.setText("Món ăn tại "+thanhPho);
                        }
                    }
                    else{
                        if (MonAnManager.getsInstance().getDanhSachMonAn().get(i).getViTri().equals(thanhPho) && MonAnManager.getsInstance().getDanhSachMonAn().get(i).getLoaiMonAn().equals(loaiMonAn)) {
                            locMonAn.add(MonAnManager.getsInstance().getDanhSachMonAn().get(i));
                            txtTitle.setText("Món ăn "+loaiMonAn+" tại "+thanhPho);
                        }
                    }
                }
                if(locMonAn.size() == 0) txtTitle.setText("Khong co du lieu");
                myAdapter = new MyAdapter(MainActivity.this, R.layout.item, locMonAn);
                lvHienThiMonAn.setAdapter(myAdapter);
            }
        });
        //Button lọc món theo phân loại
        lvLoaiMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                monAnNoiBat.clear();
//                myAdapter.notifyDataSetChanged();
                locMonAn = new ArrayList<MonAn>();
                loaiMonAn = loaimonanList[position];
                for(int i=0; i<MonAnManager.getsInstance().getDanhSachMonAn().size(); i++){
                    if(thanhPho == "") {
                        if (MonAnManager.getsInstance().getDanhSachMonAn().get(i).getLoaiMonAn().equals(loaiMonAn)) {
                            locMonAn.add(MonAnManager.getsInstance().getDanhSachMonAn().get(i));
                            txtTitle.setText("Món ăn "+loaiMonAn);
                        }
                    }
                    else{
                        if (MonAnManager.getsInstance().getDanhSachMonAn().get(i).getViTri().equals(thanhPho) && MonAnManager.getsInstance().getDanhSachMonAn().get(i).getLoaiMonAn().equals(loaiMonAn)) {
                            locMonAn.add(MonAnManager.getsInstance().getDanhSachMonAn().get(i));
                            txtTitle.setText("Món ăn "+loaiMonAn+" tại "+thanhPho);
                        }
                    }
                }
                if(locMonAn.size() == 0) txtTitle.setText("Khong co du lieu");
                myAdapter = new MyAdapter(MainActivity.this, R.layout.item, locMonAn);
                lvHienThiMonAn.setAdapter(myAdapter);
            }
        });
        //button khi nhan vào sẽ reset trạng thái về những món mới
        btnNewMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                monAnNoiBat = MonAnManager.getsInstance().getDanhSachMonMoi();
                myAdapter = new MyAdapter(MainActivity.this, R.layout.item, monAnNoiBat);
                lvHienThiMonAn.setAdapter(myAdapter);
                thanhPho = "";
                loaiMonAn = "";
                txtTitle.setText("Món ăn nổi bật");
            }
        });
    }

    private void setControl() {
        //Khoi tao tabhost chứa các tab con
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        //tao cac tab con
        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setIndicator("Mới");
        tab1.setContent(R.id.tab1);
        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setIndicator("Phân loại");
        tab2.setContent(R.id.tab2);
        TabHost.TabSpec tab3 = tabHost.newTabSpec("t3");
        tab3.setIndicator("Thành phố");
        tab3.setContent(R.id.tab3);
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        btnNewMonAn = (Button) findViewById(R.id.btnNewMonAn);
        btnNewQuanAn = (Button) findViewById(R.id.btnNewQuanAn);
        btnMenu = (Button) findViewById(R.id.btnMenu);
        MonAnManager.getsInstance().load();
        monAnNoiBat = MonAnManager.getsInstance().getDanhSachMonMoi();

        myAdapter = new MyAdapter(this, R.layout.item, monAnNoiBat);
        lvHienThiMonAn = (ListView) findViewById(R.id.lvHienThiMonAn);
        lvHienThiMonAn.setAdapter(myAdapter);

        //setup city list
        cityList = getResources().getStringArray(R.array.city);
        adapterCity = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityList);
        lvCity = (ListView) findViewById(R.id.lvCity);
        lvCity.setAdapter(adapterCity);

        //tao arr img loai mon an
        arrImgLoaiMonAn = new int[]{
                R.drawable.loaimonan_vietnam,
                R.drawable.loaimonan_chaumy,
                R.drawable.loaimonan_chaumy,
                R.drawable.loaimonan_chauau,
                R.drawable.loaimonan_y,
                R.drawable.loaimonan_phap,
                R.drawable.loaimonan_duc,
                R.drawable.loaimonan_taybannha,
                R.drawable.loaimonan_china,
                R.drawable.loaimonan_ando,
                R.drawable.loaimonan_thai,
                R.drawable.loaimonan_nhatban,
                R.drawable.loaimonan_hanquoc,
                R.drawable.loaimonan_banhmy,
                R.drawable.loaimonan_quananle,
                R.drawable.loaimonan_cafe,
                R.drawable.loaimonan_douong
        };
//        addArrImgLoaiMonAn(arrImgLoaiMonAn);

        //setup loaimonan list
        loaimonanList = getResources().getStringArray(R.array.loaiMonAn);
        loaiMonAnAdapter = new LoaiMonAnAdapter(this, R.layout.item_loaimonan, loaimonanList, arrImgLoaiMonAn);
        lvLoaiMonAn = (ListView) findViewById(R.id.lvLoaiMonAn);
        lvLoaiMonAn.setAdapter(loaiMonAnAdapter);
    }
}
