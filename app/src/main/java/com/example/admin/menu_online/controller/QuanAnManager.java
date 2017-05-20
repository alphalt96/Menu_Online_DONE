package com.example.admin.menu_online.controller;

import android.content.Context;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.R;
import com.example.admin.menu_online.models.MonAn;
import com.example.admin.menu_online.models.QuanAn;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Anh on 4/22/2017.
 */

public class QuanAnManager {
    private static QuanAnManager sInstance = null;
    private ArrayList<QuanAn> danhSachQuan, danhSachQuanMoi;
    private int[] imgList = new int[]{
                R.drawable.quan1,
                R.drawable.quan2,
                R.drawable.quan3,
                R.drawable.quan4,
                R.drawable.quan5,
                R.drawable.quan6,
                R.drawable.quan7,
                R.drawable.quan8,
                R.drawable.quan9,
                R.drawable.quan10
    };
    private String[] city = {"Hà Nội, Huế, Đà Nẵng, Hồ Chí Minh"};
    private MenuOnlineDatabase db;
    private Context context;
    private QuanAnManager(Context context){
        danhSachQuan = new ArrayList<>();
        danhSachQuanMoi = new ArrayList<>();
        db = new MenuOnlineDatabase(context);
        this.context =context;
    }
    public static QuanAnManager getsInstance(Context context){
        if(sInstance == null)
            sInstance = new QuanAnManager(context);
        return sInstance;
    }
    public ArrayList<QuanAn> getDanhSachQuan(){
        if(danhSachQuan.size() == 0) {
            danhSachQuan.clear();
            danhSachQuan = db.getAllQuanAn();
        }
        return danhSachQuan;
    }
    public ArrayList<QuanAn> getDanhSachQuanMoi(){
        if(danhSachQuanMoi.size() == 0) {
            danhSachQuanMoi.clear();
            danhSachQuanMoi = db.getNewQuanAn();
        }
        return danhSachQuanMoi;
    }


    public void loadData(){
        if(LoadDatabaseControl.getsInstance(context).isFirstLoadApp()) {
            for (int i = 0; i < 17; i++) {
                ArrayList<MonAn> list = MonAnManager.getsInstance(context).getDanhSachMonAn();
//            ArrayList<MonAn> res = new ArrayList<>();
                String idStr = "";
                for (int j = 0; j < 5; j++) {
                    Random random = new Random();
                    int ran = random.nextInt(list.size());
                    //random de chon 1 mon trong ds mon man cua MonAnManager
//                res.add(list.get(ran));
                    if (j != 4) idStr += (ran + 1) + " ";
                    else idStr += (ran + 1);
                }
//            JSONArray jsonArray = new JSONArray(res);
                //chon ra thanh pho ngau nhien de them vao
                Random ran1 = new Random();
                Random ran2 = new Random();
                Random ran3 = new Random();
                String choose = city[ran1.nextInt(city.length)];
                //chon ra hinh anh quan an ngau nhien
                int img = imgList[ran2.nextInt(imgList.length)];
                int viewNum = ran3.nextInt(1000);
                db.insertQuanAn("Quán " + (i + 1), "Địa chỉ " + (i + 1), choose, img, idStr, viewNum);
            }
        }
    }
}
