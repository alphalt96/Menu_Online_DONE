package com.example.admin.menu_online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.adapters.DonHangAdapter;
import com.example.admin.menu_online.models.MonAn;

import java.util.ArrayList;

public class DonHang extends AppCompatActivity {

    Button btnShip, btnClear;
    TextView txtTotalCost;

    ArrayList<MonAn> monAnArrayList;
    DonHangAdapter donHangAdapter;
    ListView lvDonHang;

    float tongTien=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);

        final MenuOnlineDatabase menuOnlineDatabase = new MenuOnlineDatabase(this);

        txtTotalCost = (TextView) findViewById(R.id.txtTotalcost);
        btnShip = (Button) findViewById(R.id.btnShip);
        btnClear = (Button) findViewById(R.id.btnClear);

        monAnArrayList = menuOnlineDatabase.getDonHang();
        donHangAdapter = new DonHangAdapter(DonHang.this, R.layout.item_donhang, monAnArrayList, txtTotalCost);
        lvDonHang = (ListView) findViewById(R.id.lvDonHang);
        lvDonHang.setAdapter(donHangAdapter);


        btnShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monAnArrayList.size()>0) {
                    menuOnlineDatabase.deleteAllDonHang();
                    monAnArrayList.clear();
                    donHangAdapter.notifyDataSetChanged();
                    Toast.makeText(DonHang.this, "Cam on ban da mua hang", Toast.LENGTH_SHORT).show();
                    txtTotalCost.setText("0");
                } else Toast.makeText(DonHang.this, "Ban chua co mat hang nao hien tai", Toast.LENGTH_SHORT).show();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOnlineDatabase.deleteAllDonHang();
                monAnArrayList.clear();
                donHangAdapter.notifyDataSetChanged();
                txtTotalCost.setText("0");
            }
        });
        for(int i=0; i<menuOnlineDatabase.getDonHang().size(); i++){
            tongTien += menuOnlineDatabase.getDonHang().get(i).getGiaTien()*menuOnlineDatabase.getDonHang().get(i).getSoLuong();
        }
        txtTotalCost.setText(String.valueOf(tongTien));
    }
}
