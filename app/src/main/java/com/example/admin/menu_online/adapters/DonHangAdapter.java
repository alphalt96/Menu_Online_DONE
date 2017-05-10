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

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.R;
import com.example.admin.menu_online.models.MonAn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yep on 10/5/2017.
 */

public class DonHangAdapter extends ArrayAdapter<MonAn> {
    Context context;
    int resource;
    ArrayList<MonAn> objects;
    public DonHangAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<MonAn> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final int inc=1;
        final ViewHolder viewHolder;
        final MenuOnlineDatabase menuOnlineDatabase = new MenuOnlineDatabase(context);
        final MonAn monAn = this.objects.get(position);
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            viewHolder.imgDonHang = (ImageView) convertView.findViewById(R.id.imgDonHang);
            viewHolder.txtTenDonHang = (TextView) convertView.findViewById(R.id.txtTenDonHang);
            viewHolder.txtGiaDonHang = (TextView) convertView.findViewById(R.id.txtGiaDonhang);
            viewHolder.txtCount = (TextView) convertView.findViewById(R.id.txtCount);
            viewHolder.btnDelete = (Button) convertView.findViewById(R.id.btnDelete);
            viewHolder.btnInc = (Button) convertView.findViewById(R.id.btnInc);
            viewHolder.btnDec = (Button) convertView.findViewById(R.id.btnDec);
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.imgDonHang.setBackgroundResource(monAn.getImage());
        viewHolder.txtTenDonHang.setText(monAn.getTenMonAn());
        viewHolder.txtGiaDonHang.setText(String.valueOf(monAn.getGiaTien()));
        viewHolder.txtCount.setText(String.valueOf(menuOnlineDatabase.getCount(monAn.getMaMonAn())));

//        menuOnlineDatabase.updateDonHang(monAn.getMaMonAn(), Integer.parseInt(viewHolder.txtCount.getText().toString()));
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOnlineDatabase.deleteDonHang(monAn.getMaMonAn());
                objects.remove(position);
                notifyDataSetChanged();
            }
        });
        viewHolder.btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int inc = menuOnlineDatabase.getCount(monAn.getMaMonAn())+1;
                menuOnlineDatabase.updateDonHang(monAn.getMaMonAn(), inc);
//                viewHolder.txtCount.setText(String.valueOf(inc));
                notifyDataSetChanged();
            }
        });
        viewHolder.btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dec = menuOnlineDatabase.getCount(monAn.getMaMonAn())-1;
                menuOnlineDatabase.updateDonHang(monAn.getMaMonAn(), dec);
//                viewHolder.txtCount.setText(String.valueOf(dec));
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
    private class ViewHolder{
        private ImageView imgDonHang;
        private TextView txtTenDonHang;
        private TextView txtGiaDonHang;
        private TextView txtCount;
        private Button btnDelete, btnInc, btnDec;
    }
}
