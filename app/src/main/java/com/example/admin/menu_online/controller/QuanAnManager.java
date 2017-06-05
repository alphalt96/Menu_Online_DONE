package com.example.admin.menu_online.controller;

import android.content.Context;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.R;
import com.example.admin.menu_online.models.QuanAn;

import java.util.ArrayList;

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
//            for (int i = 0; i < 17; i++) {
//                ArrayList<MonAn> list = MonAnManager.getsInstance(context).getDanhSachMonAn();
////            ArrayList<MonAn> res = new ArrayList<>();
//                String idStr = "";
//                for (int j = 0; j < 5; j++) {
//                    Random random = new Random();
//                    int ran = random.nextInt(list.size());
//                    //random de chon 1 mon trong ds mon man cua MonAnManager
////                res.add(list.get(ran));
//                    if (j != 4) idStr += (ran + 1) + " ";
//                    else idStr += (ran + 1);
//                }
////            JSONArray jsonArray = new JSONArray(res);
//                //chon ra thanh pho ngau nhien de them vao
//                Random ran1 = new Random();
//                Random ran2 = new Random();
//                Random ran3 = new Random();
//                String choose = city[ran1.nextInt(city.length)];
//                //chon ra hinh anh quan an ngau nhien
//                int img = imgList[ran2.nextInt(imgList.length)];
//                int viewNum = ran3.nextInt(1000);
//                db.insertQuanAn("Quán " + (i + 1), "Địa chỉ " + (i + 1), choose, img, idStr, viewNum);
//            }
            db.insertQuanAn("Ngọc Nhị", "69 Phần Lăng 8", "Hải Châu", R.drawable.quan1, "1 2 3 4 5", 400);
            db.insertQuanAn("KFC", "104 Nguyễn Văn Linh, Nam Dương, Quận Hải Châu", "Thanh Khê", R.drawable.quan2, "6 7 8 9 10", 400);
            db.insertQuanAn("Zé Food - Thiên Đường Ăn Vặt Online", "112/71 Trần Cao Vân, Quận Thanh Khê, Đà Nẵng", "Sơn Trà", R.drawable.quan3, "11 12 13 14 15", 400);
            db.insertQuanAn("Highlands Coffee - One Opera Hotel", "01 Nguyễn Văn Linh, Nam Dương, Quận Hải Châu", "Liên Chiểu", R.drawable.quan4, "16 17 18 19 20", 400);
            db.insertQuanAn("EZI Coffee", "30 Ông Ích Khiêm, Quận Hải Châu, Đà Nẵng", "Ngũ Hành Sơn", R.drawable.quan5, "21 22 23 24 25", 400);
            db.insertQuanAn("Chói's Kitchen - Món Ăn Hàn Quốc", "02 Hoàng Hoa Thám, Quận Thanh Khê", "Thanh Khê", R.drawable.quan6, "26 27 28 29 30", 400);
            db.insertQuanAn("Sasin", "315 Nguyễn Văn Linh, Quận Thanh Khê", "Hải Châu", R.drawable.quan7, "31 32 33 34 35", 400);
            db.insertQuanAn("The Coffee House", "435 Lê Duẩn, Quận Thanh Khê", "Liên Chiểu", R.drawable.quan8, "36 37 38 39 40", 400);
            db.insertQuanAn("Bingsu - Pizza Đà Nẵng", "73 Đặng Thai Mai, Quận Hải Châu", "Hòa Vang", R.drawable.quan9, "1 2 3 4 5", 400);
            db.insertQuanAn("Ahihi - Trà Sữa & Ăn Vặt", "107 Lê Đình Lý, Quận Thanh Khê", "Hòa Vang", R.drawable.quan10, "6 7 8 9 10", 400);
        }
    }
}
