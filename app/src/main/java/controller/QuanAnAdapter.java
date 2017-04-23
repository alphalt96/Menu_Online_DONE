package controller;

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
import java.util.List;

import models.QuanAn;

/**
 * Created by Anh on 4/22/2017.
 */

public class QuanAnAdapter extends ArrayAdapter<QuanAn> {
    private Context context;
    private int resource;
    private ArrayList<QuanAn> objects;
    public QuanAnAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<QuanAn> objects) {
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
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(this.resource, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.imgMonAn);
            viewHolder.txtTen = (TextView) convertView.findViewById(R.id.txtRenTen);
            viewHolder.txtDiaChi = (TextView) convertView.findViewById(R.id.txtRenSoLuong);
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.img.setImageResource(objects.get(position).getImg());
        viewHolder.txtTen.setText(objects.get(position).getTenQuan());
        viewHolder.txtDiaChi.setText(objects.get(position).getDiaChi());

        return convertView;
    }
    private class ViewHolder{
        ImageView img;
        TextView txtTen, txtDiaChi;
    }
}
