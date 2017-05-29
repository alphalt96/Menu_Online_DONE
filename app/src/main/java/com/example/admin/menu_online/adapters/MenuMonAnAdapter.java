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
 * Created by Admin on 4/21/2017.
 */

public class MenuMonAnAdapter extends ArrayAdapter<MonAn> {
    Context context;
    int resource;
    ArrayList<MonAn> objects;
    TextView cartNum;
    public MenuMonAnAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<MonAn> objects, TextView cartNum) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.cartNum = cartNum;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final MenuOnlineDatabase menuOnlineDatabase = new MenuOnlineDatabase(this.context);
        ViewHolder viewHolder;
        final MonAn monAn = objects.get(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.imgMonAn);
            viewHolder.txtTenMonAn = (TextView) convertView.findViewById(R.id.txtTenMonAn);
            viewHolder.txtGiaTien = (TextView) convertView.findViewById(R.id.txtGiaTien);
            viewHolder.txtLuotXem = (TextView) convertView.findViewById(R.id.txtLuotXem);
            viewHolder.txtLuotThich = (TextView) convertView.findViewById(R.id.txtLuotThich);
            viewHolder.btnAdd = (Button) convertView.findViewById(R.id.btnAddCart);
            convertView.setTag(viewHolder);
        }
        else viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.img.setBackgroundResource(monAn.getImage());
        viewHolder.txtTenMonAn.setText(monAn.getTenMonAn());
        viewHolder.txtGiaTien.setText(String.valueOf(monAn.getGiaTien()));
        viewHolder.txtLuotXem.setText(String.valueOf(monAn.getLuotView()));
        viewHolder.txtLuotThich.setText(String.valueOf(monAn.getLuotThich()));

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
                        cartNum.setVisibility(View.VISIBLE);
                        cartNum.setText(String.valueOf(menuOnlineDatabase.getDonHang().size()));
                    } else Toast.makeText(context, "Mon nay da co san trong gio hang", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(context, "Ban can dang nhap de thuc hien chuc nang nay", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
    private class ViewHolder{
        ImageView img;
        TextView txtTenMonAn, txtGiaTien, txtLuotXem, txtLuotThich;
        Button btnAdd;
    }
}
