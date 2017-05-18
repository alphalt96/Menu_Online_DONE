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
import com.example.admin.menu_online.models.ItemMenu;

import java.util.ArrayList;

/**
 * Created by Yep on 18/5/2017.
 */

public class ItemMenuAdapter extends ArrayAdapter<ItemMenu> {
    private Context context;
    private int resource;
    private ArrayList<ItemMenu> objects;
    public ItemMenuAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<ItemMenu> objects) {
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
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(this.resource, parent, false);
            viewHolder.imgItemMenu = (ImageView) convertView.findViewById(R.id.imgItemMenu);
            viewHolder.txtItemMenu = (TextView) convertView.findViewById(R.id.txtNameItemMenu);
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.imgItemMenu.setBackgroundResource(this.objects.get(position).getIconImg());
        viewHolder.txtItemMenu.setText(this.objects.get(position).getItemName());
        return convertView;
    }
    private class ViewHolder{
        ImageView imgItemMenu;
        TextView txtItemMenu;
    }
}
