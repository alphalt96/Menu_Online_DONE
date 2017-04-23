package controller;

import com.example.admin.menu_online.R;

import java.util.ArrayList;

import models.QuanAn;

/**
 * Created by Anh on 4/22/2017.
 */

public class QuanAnManager {
    private static QuanAnManager sInstance = null;
    private ArrayList<QuanAn> danhSachQuan, danhSachQuanMoi;
    private QuanAnManager(){
        danhSachQuan = new ArrayList<>();
        danhSachQuanMoi = new ArrayList<>();
    }
    public static QuanAnManager getsInstance(){
        if(sInstance == null)
            sInstance = new QuanAnManager();
        return sInstance;
    }
    public void load(){
        danhSachQuan.clear();
        fakeData();
    }
    public void loadNew(){
        danhSachQuanMoi.clear();
        fakeDataNew();
    }
    public ArrayList<QuanAn> getDanhSachQuan(){
        if(danhSachQuan.size() == 0)
            load();
        return danhSachQuan;
    }
    public ArrayList<QuanAn> getDanhSachQuanMoi(){
        if(danhSachQuanMoi.size() == 0)
            loadNew();
        return danhSachQuanMoi;
    }


    private void fakeDataNew(){
        danhSachQuanMoi.add(new QuanAn("Quán 1", "Địa chỉ 1", "Đà Nẵng", R.drawable.quan1));
        danhSachQuanMoi.add(new QuanAn("Quán 2", "Địa chỉ 2", "Hà Nội",R.drawable.quan2));
        danhSachQuanMoi.add(new QuanAn("Quán 3", "Địa chỉ 3", "Huế", R.drawable.quan3));
        danhSachQuanMoi.add(new QuanAn("Quán 4", "Địa chỉ 4", "Hồ Chí Minh", R.drawable.quan4));
        danhSachQuanMoi.add(new QuanAn("Quán 5", "Địa chỉ 5", "Huế", R.drawable.quan5));
    }
    private void fakeData(){
        danhSachQuan.add(new QuanAn("Quán 1", "Địa chỉ 1", "Đà Nẵng", R.drawable.quan1));
        danhSachQuan.add(new QuanAn("Quán 2", "Địa chỉ 2", "Hà Nội",R.drawable.quan2));
        danhSachQuan.add(new QuanAn("Quán 3", "Địa chỉ 3", "Huế", R.drawable.quan3));
        danhSachQuan.add(new QuanAn("Quán 4", "Địa chỉ 4", "Hồ Chí Minh", R.drawable.quan4));
        danhSachQuan.add(new QuanAn("Quán 5", "Địa chỉ 5", "Huế", R.drawable.quan5));
        danhSachQuan.add(new QuanAn("Quán 6", "Địa chỉ 6", "Hà Nội", R.drawable.quan6));
        danhSachQuan.add(new QuanAn("Quán 7", "Địa chỉ 7", "Đà Nẵng", R.drawable.quan7));
        danhSachQuan.add(new QuanAn("Quán 8", "Địa chỉ 8", "Hà Nội", R.drawable.quan8));
        danhSachQuan.add(new QuanAn("Quán 9", "Địa chỉ 9", "Hồ Chí Minh", R.drawable.quan9));
        danhSachQuan.add(new QuanAn("Quán 10", "Địa chỉ 10", "Đà Nẵng", R.drawable.quan10));
        danhSachQuan.add(new QuanAn("Quán 11", "Địa chỉ 11", "Hồ Chí Minh", R.drawable.quan1));
        danhSachQuan.add(new QuanAn("Quán 12", "Địa chỉ 12", "Hà Nội", R.drawable.quan2));
        danhSachQuan.add(new QuanAn("Quán 13", "Địa chỉ 13", "Hồ Chí Minh", R.drawable.quan3));
        danhSachQuan.add(new QuanAn("Quán 14", "Địa chỉ 14", "Huế", R.drawable.quan4));
        danhSachQuan.add(new QuanAn("Quán 15", "Địa chỉ 15", "Huế", R.drawable.quan7));
        danhSachQuan.add(new QuanAn("Quán 16", "Địa chỉ 16", "Đà Nẵng", R.drawable.quan5));
        danhSachQuan.add(new QuanAn("Quán 17", "Địa chỉ 17", "Hà Nội", R.drawable.quan1));
        danhSachQuan.add(new QuanAn("Quán 18", "Địa chỉ 18", "Hồ Chí Minh", R.drawable.quan6));
        danhSachQuan.add(new QuanAn("Quán 19", "Địa chỉ 19", "Hà Nội", R.drawable.quan2));
        danhSachQuan.add(new QuanAn("Quán 20", "Địa chỉ 20", "Đà Nẵng", R.drawable.quan9));
        danhSachQuan.add(new QuanAn("Quán 21", "Địa chỉ 21", "Huế", R.drawable.quan8));
        danhSachQuan.add(new QuanAn("Quán 22", "Địa chỉ 22", "Hồ Chí Mih", R.drawable.quan10));
        danhSachQuan.add(new QuanAn("Quán 23", "Địa chỉ 23", "Vũng Tàu", R.drawable.quan2));
        danhSachQuan.add(new QuanAn("Quán 24", "Địa chỉ 24", "Vũng Tàu", R.drawable.quan5));
    }
}
