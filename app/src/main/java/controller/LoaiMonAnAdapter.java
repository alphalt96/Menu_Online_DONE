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

/**
 * Created by Admin on 4/20/2017.
 */

public class LoaiMonAnAdapter extends ArrayAdapter<String> {
    Context context;
    int resource;
    String[] objects;
    int[] arrImgLoaiMonAn;
    public LoaiMonAnAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull String[] objects, int[] arrImgLoaiMonAn) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.arrImgLoaiMonAn = arrImgLoaiMonAn;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(this.resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgLoaiMonAn = (ImageView) convertView.findViewById(R.id.imgLoaiMonAn);
            viewHolder.txtLoaiMonAn = (TextView) convertView.findViewById(R.id.txtLoaiMonAn);
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.imgLoaiMonAn.setBackgroundResource(arrImgLoaiMonAn[position]);
        viewHolder.txtLoaiMonAn.setText(this.objects[position]);
        return convertView;
    }
    private class ViewHolder{
        private ImageView imgLoaiMonAn;
        private TextView txtLoaiMonAn;
    }

}
