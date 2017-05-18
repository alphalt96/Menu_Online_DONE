package com.example.admin.menu_online;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.adapters.ItemMenuAdapter;
import com.example.admin.menu_online.adapters.LoaiMonAnAdapter;
import com.example.admin.menu_online.adapters.MyAdapter;
import com.example.admin.menu_online.adapters.QuanAnAdapter;
import com.example.admin.menu_online.controller.LoadDatabaseControl;
import com.example.admin.menu_online.controller.MonAnManager;
import com.example.admin.menu_online.controller.QuanAnManager;
import com.example.admin.menu_online.models.ItemMenu;
import com.example.admin.menu_online.models.MonAn;
import com.example.admin.menu_online.models.QuanAn;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MenuOnlineDatabase menuOnlineDatabase;

    DrawerLayout drawerLayout;
    ArrayList<ItemMenu> itemList;
    ArrayList<MonAn> monAnNoiBat, locMonAn;
    private String[] cityList, loaimonanList;
    private int[] arrImgLoaiMonAn;
    private ItemMenuAdapter itemMenuAdapter;
    private MyAdapter myAdapter;
    private QuanAnAdapter quanAnAdapter;
    private LoaiMonAnAdapter loaiMonAnAdapter;
    private ArrayAdapter<String> adapterCity;
    private ListView lvMenuDrawer;
    private ListView lvHienThiMonAn, lvCity, lvLoaiMonAn;
    private TextView txtTitle;
    private EditText txtSearch;
    private Button btnNewMonAn, btnNewQuanAn, btnMenu, btnSearch;
    private ImageView imgApp;
    private Toolbar toolbar;

    TabHost tabHost;
    String loaiMonAn="", thanhPho="";

    Boolean siteCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setControl();
        setEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tabHost.setCurrentTab(0);
    }

    private void setEvent() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PopupMenu popupMenu = new PopupMenu(MainActivity.this, btnMenu);
//                popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        int id = item.getItemId();
//                        if(id == R.id.monAnList){
//                            Intent intent = new Intent(MainActivity.this, MenuMonAn.class);
//                            startActivityn(intet);
//                        }
//                        else if(id == R.id.quanAnList){
//                            startActivity(new Intent(MainActivity.this, MenuQuanAn.class));
//                        }
//                        else if(id == R.id.ranking){
//                            Toast.makeText(getApplicationContext(), "Hiện chưa có chức năng này", Toast.LENGTH_SHORT).show();
//                        }
//                        else if(id == R.id.info){
//                            Toast.makeText(getApplicationContext(), "Version 1.1.3.1415926535897932", Toast.LENGTH_SHORT).show();
//                        }
//                        else if(id == R.id.user){
//                            SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
//                            if(sharedPreferences.getInt("USERID", 0) == 0)
//                                startActivity(new Intent(MainActivity.this, UserLogin.class));
//                            else startActivity(new Intent(MainActivity.this, UserInfo.class));
//                        }
//                        return false;
//                    }
//                });
//                popupMenu.show();
                btnMenu.setVisibility(View.GONE);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        //nut search
        lvHienThiMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(siteCheck == true){
                    Intent intent = new Intent(MainActivity.this, ChiTietMonAn.class);
                    Bundle bundle = new Bundle();
                    if(loaiMonAn == "" && thanhPho == "")
                        bundle.putSerializable("detail",MonAnManager.getsInstance(MainActivity.this).getDanhSachMonMoi().get(position));
                    else
                        bundle.putSerializable("detail",locMonAn.get(position));
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, ChiTietQuanAn.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("detail", QuanAnManager.getsInstance(MainActivity.this).getDanhSachQuanMoi().get(position));
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
            }
        });
        lvHienThiMonAn.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                if(sharedPreferences.getInt("USERID", 0)!=0) {
                    if(thanhPho == "" && loaiMonAn == "") {
                        if (menuOnlineDatabase.checkDatHang(monAnNoiBat.get(position).getMaMonAn())) {
                            int maMonAn = monAnNoiBat.get(position).getMaMonAn();
                            String tenMon = monAnNoiBat.get(position).getTenMonAn();
                            int img = monAnNoiBat.get(position).getImage();
                            int soLuong = monAnNoiBat.get(position).getSoLuong();
                            String viTri = monAnNoiBat.get(position).getViTri();
                            String loaiMonAn = monAnNoiBat.get(position).getLoaiMonAn();
                            float giaTien = monAnNoiBat.get(position).getGiaTien();
                            menuOnlineDatabase.insertDatHang(maMonAn, tenMon, img, 1, viTri, loaiMonAn, giaTien);
                            Toast.makeText(MainActivity.this, "Da them mon thanh cong", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(MainActivity.this, "Da co san mon an nay", Toast.LENGTH_SHORT).show();
                    } else {
                        if (menuOnlineDatabase.checkDatHang(locMonAn.get(position).getMaMonAn())) {
                            int maMonAn = locMonAn.get(position).getMaMonAn();
                            String tenMon = locMonAn.get(position).getTenMonAn();
                            int img = locMonAn.get(position).getImage();
                            int soLuong = locMonAn.get(position).getSoLuong();
                            String viTri = locMonAn.get(position).getViTri();
                            String loaiMonAn = locMonAn.get(position).getLoaiMonAn();
                            float giaTien = locMonAn.get(position).getGiaTien();
                            menuOnlineDatabase.insertDatHang(maMonAn, tenMon, img, 1, viTri, loaiMonAn, giaTien);
                            Toast.makeText(MainActivity.this, "Da them mon thanh cong", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(MainActivity.this, "Da co san mon an nay", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Ban can dang nhap de thuc hien chuc nang nay", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, UserLogin.class));
                }
                return false;
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
                siteCheck = true;
                for(int i=0; i<MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().size(); i++){
                    if(loaiMonAn == "") {
                        if (MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i).getViTri().equals(thanhPho)) {
                            locMonAn.add(MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i));
                            txtTitle.setText("Món ăn tại "+thanhPho);
                        }
                    }
                    else{
                        if (MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i).getViTri().equals(thanhPho) && MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i).getLoaiMonAn().equals(loaiMonAn)) {
                            locMonAn.add(MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i));
                            txtTitle.setText("Món ăn "+loaiMonAn+" tại "+thanhPho);
                        }
                    }
                }
                if(locMonAn.size() == 0) txtTitle.setText("Khong co du lieu");
                myAdapter = new MyAdapter(MainActivity.this, R.layout.item_monan, locMonAn);
                lvHienThiMonAn.setAdapter(myAdapter);
                tabHost.setCurrentTab(0);
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
                siteCheck = true;
                for(int i=0; i<MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().size(); i++){
                    if(thanhPho == "") {
                        if (MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i).getLoaiMonAn().equals(loaiMonAn)) {
                            locMonAn.add(MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i));
                            txtTitle.setText("Món ăn "+loaiMonAn);
                        }
                    }
                    else{
                        if (MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i).getViTri().equals(thanhPho) && MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i).getLoaiMonAn().equals(loaiMonAn)) {
                            locMonAn.add(MonAnManager.getsInstance(MainActivity.this).getDanhSachMonAn().get(i));
                            txtTitle.setText("Món ăn "+loaiMonAn+" tại "+thanhPho);
                        }
                    }
                }
                if(locMonAn.size() == 0) txtTitle.setText("Khong co du lieu");
                myAdapter = new MyAdapter(MainActivity.this, R.layout.item_monan, locMonAn);
                lvHienThiMonAn.setAdapter(myAdapter);
                tabHost.setCurrentTab(0);
            }
        });
        //button khi nhan vào sẽ reset trạng thái về những món mới
        btnNewMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                monAnNoiBat = MonAnManager.getsInstance().getDanhSachMonMoi();
                myAdapter = new MyAdapter(MainActivity.this, R.layout.item_monan, monAnNoiBat);
                lvHienThiMonAn.setAdapter(myAdapter);
                thanhPho = "";
                loaiMonAn = "";
                txtTitle.setText("Món ăn nổi bật");
                siteCheck = true;
            }
        });
        btnNewQuanAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ArrayList<QuanAn> quanAnNoiBat = QuanAnManager.getsInstance(MainActivity.this).getDanhSachQuanMoi();
                quanAnAdapter = new QuanAnAdapter(MainActivity.this, R.layout.item_quanan, quanAnNoiBat);
                lvHienThiMonAn.setAdapter(quanAnAdapter);
                txtTitle.setText("Quán ăn mới");
                siteCheck = false;
            }
        });
    }

    private void setControl() {
        //Dem so lan start up app
        LoadDatabaseControl.getsInstance(this).increaseCountStartUpApp();
        menuOnlineDatabase = new MenuOnlineDatabase(this);
        //Nap du lieu cho lan run app dau tien
        MonAnManager.getsInstance(this).LoadData();
        QuanAnManager.getsInstance(this).load();
        //Khoi tao tabhost chứa các tab con
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        //tao cac tab con
        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setIndicator("Home");
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

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setupMenu();

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        btnNewMonAn = (Button) findViewById(R.id.btnNewMonAn);
        btnNewQuanAn = (Button) findViewById(R.id.btnNewQuanAn);
        btnMenu = (Button) findViewById(R.id.btnMenu);
        monAnNoiBat = MonAnManager.getsInstance(MainActivity.this).getDanhSachMonMoi();

        toolbar = (Toolbar) findViewById(R.id.toolbar);


        myAdapter = new MyAdapter(this, R.layout.item_monan, monAnNoiBat);
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
                R.drawable.loaimonan_my,
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

    //khoi tao danh sach menu
    public void addItemList(){
        String[] nameItem = new String[]{"Món ăn", "Quán ăn", "User", "Rating", "Thông tin"};
        int[] imgItem = new int[]{R.drawable.food_item_menu_icon_png_2,
                R.drawable.restaurant_item_menu_icon_png_2,
                R.drawable.user_item_menu_icon_png_2,
                R.drawable.rating_menu_item_icon_png,
                R.drawable.information_item_icon_png_2
        };
        ItemMenu itemMenu;
        for(int i=0; i<5; i++){
            itemMenu = new ItemMenu();
            itemMenu.setIconImg(imgItem[i]);
            itemMenu.setItemName(nameItem[i]);
            itemList.add(itemMenu);
        }
    }
    private void setupMenu(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        itemList = new ArrayList<>();
        addItemList();
        itemMenuAdapter = new ItemMenuAdapter(this, R.layout.drawer_menu_item, itemList);
        lvMenuDrawer = (ListView) findViewById(R.id.lvMenuDrawer);
        lvMenuDrawer.setAdapter(itemMenuAdapter);

        ActionBarDrawerToggle slideAction =  new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                btnMenu.setVisibility(View.GONE);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                btnMenu.setVisibility(View.VISIBLE);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }; drawerLayout.setDrawerListener(slideAction);

        lvMenuDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 : startActivity(new Intent(MainActivity.this, MenuMonAn.class)); break;
                    case 1: startActivity(new Intent(MainActivity.this, MenuQuanAn.class)); break;
                    case 2:
                        if(getSharedPreferences("userinfo", MODE_PRIVATE).getInt("USERID", 0) == 0)
                            startActivity(new Intent(MainActivity.this, UserLogin.class));
                        else startActivity(new Intent(MainActivity.this, UserInfo.class));
                        break;
                    case 3: Toast.makeText(MainActivity.this, "Hiện chưa có chức năng này", Toast.LENGTH_SHORT).show(); break;
                    case 4: Toast.makeText(MainActivity.this, "2.1.3.1415926535897932", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.title_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        item.setVisible(false);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        if (id == R.id.search) {
            startActivity(new Intent(MainActivity.this, MenuMonAn.class));

        } else if (id == R.id.cart) {
            if(getSharedPreferences("userinfo", MODE_PRIVATE).getInt("USERID", 0) != 0)
                startActivity(new Intent(MainActivity.this, UserInfo.class).putExtra("CART", true));
            else Toast.makeText(MainActivity.this, "Ban can dang nhap de thuc hien chuc nang nay", Toast.LENGTH_SHORT).show();
        } else if (id == android.R.id.home){
            drawerLayout.closeDrawer(GravityCompat.START);
            btnMenu.setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        return super.onOptionsItemSelected(item);
    }
}
