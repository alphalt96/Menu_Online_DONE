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
import com.example.admin.menu_online.models.QuanAn;

import java.util.ArrayList;

/**
 * Created by Yep on 29/4/2017.
 */

public class MenuQuanAnAdapter extends ArrayAdapter<QuanAn> {
    Context context;
    int resource;
    ArrayList<QuanAn> objects;
    public MenuQuanAnAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<QuanAn> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(this.context).inflate(this.resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgQuanAn = (ImageView) convertView.findViewById(R.id.imgQuanAn);
            viewHolder.txtTenQuanAn = (TextView) convertView.findViewById(R.id.txtTenQuanAn);
            viewHolder.txtDiaChiQuanAn = (TextView) convertView.findViewById(R.id.txtDiaChiQuanAn);
            viewHolder.txtViewNum = (TextView) convertView.findViewById(R.id.txtViewNum);
            convertView.setTag(viewHolder);
        }
        else viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.imgQuanAn.setBackgroundResource(objects.get(position).getImg());
        viewHolder.txtTenQuanAn.setText(objects.get(position).getTenQuan());
        viewHolder.txtDiaChiQuanAn.setText(objects.get(position).getDiaChi());
        viewHolder.txtViewNum.setText(String.valueOf(objects.get(position).getViewNum()));
        return convertView;
    }
    private class ViewHolder{
        ImageView imgQuanAn;
        TextView txtTenQuanAn;
        TextView txtDiaChiQuanAn;
        TextView txtViewNum;
    }
}
