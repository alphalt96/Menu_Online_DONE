package com.example.admin.menu_online.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.R;
import com.example.admin.menu_online.models.MonAn;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Admin on 4/13/2017.
 */

public class DsMonAnTrongQuan extends ArrayAdapter<MonAn> {
    private MenuOnlineDatabase menuOnlineDatabase;

    Context context;
    int resource;
    ArrayList<MonAn> objects;
    public DsMonAnTrongQuan(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<MonAn> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        menuOnlineDatabase = new MenuOnlineDatabase(this.context);
        ViewHolder viewHolder;
        final MonAn monAn = objects.get(position);

        if(convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(this.resource, parent, false);
            viewHolder.imgMonAn = (ImageView) convertView.findViewById(R.id.imgMonAn);
            viewHolder.txtRenTen = (TextView) convertView.findViewById(R.id.txtRenTen);
            viewHolder.btnAdd = (Button) convertView.findViewById(R.id.btnAddCart);
            viewHolder.txtRenGiaTien = (TextView) convertView.findViewById(R.id.txtRenGiaTien);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imgMonAn.setBackgroundResource(monAn.getImage());
        viewHolder.txtRenTen.setText(monAn.getTenMonAn());
        viewHolder.txtRenGiaTien.setText(String.valueOf(monAn.getGiaTien()));

        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(context.getSharedPreferences("userinfo", MODE_PRIVATE).getInt("USERID", 0) != 0) {
                    if(menuOnlineDatabase.checkDatHang(monAn.getMaMonAn())) {
                        int maMonAn = monAn.getMaMonAn();
                        String tenMonAn = monAn.getTenMonAn();
                        int img = monAn.getImage();
                        String viTri = monAn.getViTri();
                        String loaiMonAn = monAn.getLoaiMonAn();
                        float giaTien = monAn.getGiaTien();
                        menuOnlineDatabase.insertDatHang(maMonAn, tenMonAn, img, 1, viTri, loaiMonAn, giaTien);
                        Toast.makeText(context, "Them mon an vao gio hang thanh cong", Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(context, "Mon nay da co san trong gio hang", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(context, "Ban can dang nhap de thuc hien chuc nang nay", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;

    }
    private class ViewHolder{
        private ImageView imgMonAn;
        private TextView txtRenTen, txtRenGiaTien;
        private Button btnAdd;
    }
}
