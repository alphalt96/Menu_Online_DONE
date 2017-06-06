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
import com.example.admin.menu_online.models.MonAn;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Admin on 4/13/2017.
 */

public class MyAdapter extends ArrayAdapter<MonAn> {

    Context context;
    int resource;
    ArrayList<MonAn> objects;
    public MyAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<MonAn> objects) {
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

        if(convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(this.resource, parent, false);
            viewHolder.imgMonAn = (ImageView) convertView.findViewById(R.id.imgMonAn);
            viewHolder.txtRenTen = (TextView) convertView.findViewById(R.id.txtRenTen);
            viewHolder.txtRenGiaTien = (TextView) convertView.findViewById(R.id.txtRenGiaTien);
            viewHolder.txtRenViews = (TextView) convertView.findViewById(R.id.txtRenViews);
            viewHolder.txtScore = (TextView) convertView.findViewById(R.id.txtScore);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imgMonAn.setBackgroundResource(monAn.getImage());
        viewHolder.txtRenTen.setText(monAn.getTenMonAn());
        viewHolder.txtRenGiaTien.setText(String.valueOf(monAn.getGiaTien()));
        viewHolder.txtRenViews.setText(String.valueOf(monAn.getLuotView()));
        viewHolder.txtScore.setText(new formular(monAn.getLuotView(), monAn.getLuotThich()).score());
        return convertView;

    }
    private class ViewHolder{
        public ImageView imgMonAn;
        public TextView txtRenTen, txtRenGiaTien, txtRenViews, txtScore;
    }
    private class formular{
        private int views, like;

        public int getView() {
            return views;
        }

        public void setView(int views) {
            this.views = views;
        }

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public formular() {
        }

        public formular(int view, int like) {
            this.views = view;
            this.like = like;
        }
        private float scoreCal(){
            float score = 0;
            float score1 = (this.views+this.like)/2;
            if(score1 > 0 && score1 <= 100){
                score = 1;
            } else if(score1 > 100 && score1 <= 200){
                score = 2;
            } else if(score1 > 200 && score1 <= 300){
                score = 3;
            } else if(score1 > 300 && score1 <= 400){
                score = 4;
            } else if(score1 > 400 && score1 <= 500){
                score = 5;
            } else if(score1 > 500 && score1 <= 600){
                score = 6;
            } else if(score1 > 600 && score1 <= 700){
                score = 7;
            } else if(score1 > 700 && score1 <= 800){
                score = 8;
            } else if(score1 > 800 && score1 <= 900){
                score = 9;
            } else if(score1 > 800 && score1 <= 1000){
                score = 10;
            }

            if(score < 10){
                score = score + divNum(this.views) + divNum(this.like);
                if(score > 10)
                    score = 10;
            }

            return score;
        }

        public String score(){
            return new DecimalFormat("#.#").format(scoreCal());
        }

        private float divNum(int viewlike){
            int div=1;
            if(viewlike > 0 && viewlike <= 10)
                div = 10;
            else if (viewlike > 10 && viewlike <= 100)
                div = 100;
            else if(viewlike > 100 && viewlike <= 1000)
                div = 1000;
            return (float)viewlike/div;
        }
    }
}
