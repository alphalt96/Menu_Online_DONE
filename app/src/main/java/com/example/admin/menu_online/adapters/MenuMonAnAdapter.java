package com.example.admin.menu_online.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.menu_online.R;

import java.util.ArrayList;

import com.example.admin.menu_online.models.MonAn;

/**
 * Created by Admin on 4/21/2017.
 */

public class MenuMonAnAdapter extends ArrayAdapter<MonAn> {
    Context context;
    int resource;
    ArrayList<MonAn> objects;
    public MenuMonAnAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<MonAn> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        MonAn monAn = objects.get(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.imgMonAn);
            viewHolder.txtTenMonAn = (TextView) convertView.findViewById(R.id.txtTenMonAn);
            viewHolder.txtGiaTien = (TextView) convertView.findViewById(R.id.txtGiaTien);
            convertView.setTag(viewHolder);
        }
        else viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.img.setBackgroundResource(monAn.getImage());
        viewHolder.txtTenMonAn.setText(monAn.getTenMonAn());
        viewHolder.txtGiaTien.setText(String.valueOf(monAn.getGiaTien()));

        return convertView;
    }
    private class ViewHolder{
        ImageView img;
        TextView txtTenMonAn, txtGiaTien;
    }
}
