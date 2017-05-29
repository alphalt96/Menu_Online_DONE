package com.example.admin.menu_online;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.adapters.MenuMonAnAdapter;
import com.example.admin.menu_online.controller.MonAnManager;
import com.example.admin.menu_online.models.MonAn;

import java.util.ArrayList;

public class MenuMonAn extends AppCompatActivity {

    private MenuOnlineDatabase menuOnlineDatabase;
    private boolean CHECK = false;

    private Toolbar toolbar;
    private Button btnMenu;
    private ArrayList<MonAn> monAnList;
    private MenuMonAnAdapter menuMonAnAdapter;
    private ListView lvMenuMonAn;
    private TextView cartNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_mon_an);

        addControls();
        addEvents();
    }

    private void addEvents() {
        lvMenuMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MenuMonAn.this, ChiTietMonAn.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("detail", monAnList.get(position));
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        data();
        //setup toolbar
        setupToolbar();

        monAnList = MonAnManager.getsInstance(this).getDanhSachMonAn();
        menuMonAnAdapter = new MenuMonAnAdapter(this, R.layout.item_menu_monan, monAnList, cartNum);
        lvMenuMonAn = (ListView) findViewById(R.id.lvMenuMonAn);
        lvMenuMonAn.setAdapter(menuMonAnAdapter);
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.toolBarReturnHome));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_png);
        getSupportActionBar().setTitle("Menu món ăn");
        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setVisibility(View.GONE);

        cartNum = (TextView) findViewById(R.id.txtCartNum);
        if(getSharedPreferences("userinfo", MODE_PRIVATE).getInt("USERID", 0) != 0 && menuOnlineDatabase.getDonHang().size() > 0){
            cartNum.setVisibility(View.VISIBLE);
            cartNum.setText(String.valueOf(menuOnlineDatabase.getDonHang().size()));
        }
    }

    private void data() {
        menuOnlineDatabase = new MenuOnlineDatabase(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.title_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String name) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String name) {
                monAnList = menuOnlineDatabase.getAllMonAnSearch(name);
                menuMonAnAdapter = new MenuMonAnAdapter(MenuMonAn.this, R.layout.item_menu_monan, monAnList, cartNum);
                lvMenuMonAn.setAdapter(menuMonAnAdapter);
                menuMonAnAdapter.notifyDataSetChanged();
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if(getSharedPreferences("userinfo", MODE_PRIVATE).getInt("USERID", 0) != 0 && menuOnlineDatabase.getDonHang().size() > 0){
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();
        else if(item.getItemId() == R.id.cart) {
            if(getSharedPreferences("userinfo", MODE_PRIVATE).getInt("USERID", 0) != 0)
                startActivity(new Intent(MenuMonAn.this, UserInfo.class).putExtra("CART", true));
            else Toast.makeText(MenuMonAn.this, "Ban can dang nhap de thuc hien chuc nang nay", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
