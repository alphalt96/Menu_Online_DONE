package com.example.admin.menu_online.controller;

import com.example.admin.menu_online.R;

import java.util.ArrayList;

import com.example.admin.menu_online.models.MonAn;

/**
 * Created by Admin on 4/13/2017.
 */

//singleton chi cho phep tao 1 the hien cua no chu khong cho tao ra nhieu doi tuong
public class MonAnManager {
    private static MonAnManager sInstance = null;
    private ArrayList<MonAn> danhSachMonAn, danhSachMonMoi;

    private MonAnManager(){
        danhSachMonAn = new ArrayList<MonAn>();
        danhSachMonMoi = new ArrayList<MonAn>();
    }
    public static MonAnManager getsInstance(){
        if(sInstance == null)
            sInstance = new MonAnManager();
        return sInstance;
    }
    public void load(){
        danhSachMonAn.clear();
        fakeData();
    }
    public void loadMonMoi(){
        danhSachMonMoi.clear();
        fakeDataNew();
    }
    public ArrayList<MonAn> getDanhSachMonAn(){
        if(danhSachMonAn.size() == 0)
            load();
        return danhSachMonAn;
    }
    public ArrayList<MonAn> getDanhSachMonMoi(){
        if(danhSachMonMoi.size() == 0)
            loadMonMoi();
        return danhSachMonMoi;
    }


    private void fakeDataNew(){
        danhSachMonMoi.add(new MonAn(1,"Bánh tráng trộn", R.drawable.mon_an_5, 69, 9, "Đà Nẵng", "Việt Nam", 30000));
        danhSachMonMoi.add(new MonAn(2,"Gan rim", R.drawable.mon_an_1, 96, 8, "Hà Nội", "Nhật Bản", 69000));
        danhSachMonMoi.add(new MonAn(3,"Ếch xào xả ớt", R.drawable.mon_an_2, 69, 5, "Hồ Chí Minh", "Việt Nam", 20000));
        danhSachMonMoi.add(new MonAn(4,"Thịt nướng lu", R.drawable.mon_an_3, 96, 7, "Đà Nẵng", "Nhật Bản", 10000));
        danhSachMonMoi.add(new MonAn(5,"Bánh tráng kẹp", R.drawable.mon_an_4, 69, 2, "Hà Nội", "Nhật Bản", 50000));
        danhSachMonMoi.add(new MonAn(6,"Bánh tráng nướng", R.drawable.mon_an_6, 96, 69, "Huế", "Mỹ", 120000));
        danhSachMonMoi.add(new MonAn(7,"Ốc bưu", R.drawable.mon_an_7, 96, 69, "Vũng Tàu", "Mỹ", 200000));
        danhSachMonMoi.add(new MonAn(8,"Phá lấu", R.drawable.mon_an_8, 96, 69, "Đà Nẵng", "Mỹ", 50000));
        danhSachMonMoi.add(new MonAn(9,"Bột chiên", R.drawable.mon_an_9, 96, 69, "Hà Nội", "Nhật Bản", 20000));
        danhSachMonMoi.add(new MonAn(10,"Xiên nướng", R.drawable.mon_an_10, 96, 69, "Hồ Chí Minh", "Ý", 15000));
    }
    private void fakeData(){
        danhSachMonMoi.add(new MonAn(1,"Bánh tráng trộn", R.drawable.mon_an_5, 69, 9, "Đà Nẵng", "Việt Nam", 30000));
        danhSachMonMoi.add(new MonAn(2,"Gan rim", R.drawable.mon_an_1, 96, 8, "Hà Nội", "Nhật Bản", 69000));
        danhSachMonMoi.add(new MonAn(3,"Ếch xào xả ớt", R.drawable.mon_an_2, 69, 5, "Hồ Chí Minh", "Việt Nam", 20000));
        danhSachMonMoi.add(new MonAn(4,"Thịt nướng lu", R.drawable.mon_an_3, 96, 7, "Đà Nẵng", "Nhật Bản", 10000));
        danhSachMonMoi.add(new MonAn(5,"Bánh tráng kẹp", R.drawable.mon_an_4, 69, 2, "Hà Nội", "Nhật Bản", 50000));
        danhSachMonMoi.add(new MonAn(6,"Bánh tráng nướng", R.drawable.mon_an_6, 96, 69, "Huế", "Mỹ", 120000));
        danhSachMonMoi.add(new MonAn(7,"Ốc bưu", R.drawable.mon_an_7, 96, 69, "Vũng Tàu", "Mỹ", 200000));
        danhSachMonMoi.add(new MonAn(8,"Phá lấu", R.drawable.mon_an_8, 96, 69, "Đà Nẵng", "Mỹ", 50000));
        danhSachMonMoi.add(new MonAn(9,"Bột chiên", R.drawable.mon_an_9, 96, 69, "Hà Nội", "Nhật Bản", 20000));
        danhSachMonMoi.add(new MonAn(10,"Xiên nướng", R.drawable.mon_an_10, 96, 69, "Hồ Chí Minh", "Ý", 15000));
        danhSachMonAn.add(new MonAn(11,"Chè thái", R.drawable.mon_an_11, 96, 69, "Huế", "Thái Lan", 70000));
        danhSachMonAn.add(new MonAn(12,"Súp cua", R.drawable.mon_an_12, 96, 69, "Hồ Chí Minh", "Mỹ", 15000));
        danhSachMonAn.add(new MonAn(13,"Bò bía", R.drawable.mon_an_13, 96, 69, "Đà Nẵng", "Mỹ", 100000));
        danhSachMonAn.add(new MonAn(14,"Chim cút chiên bơ", R.drawable.mon_an_14, 96, 69, "Hà Nội", "Mỹ", 20000));
        danhSachMonAn.add(new MonAn(15,"Gỏi bò khô", R.drawable.mon_an_15, 96, 69, "Hồ Chí Minh", "Mỹ", 50000));
        danhSachMonAn.add(new MonAn(16,"Bắp xào", R.drawable.mon_an_16, 96, 69, "Vũng Tàu", "Mỹ", 80000));
        danhSachMonAn.add(new MonAn(17,"Hủ tiếu", R.drawable.mon_an_17, 96, 69, "Hà Nội", "Mỹ", 70000));
        danhSachMonAn.add(new MonAn(18,"Chuối nướng bọc nếp", R.drawable.mon_an_18, 96, 69, "Huế", "Mỹ", 20000));
        danhSachMonAn.add(new MonAn(19,"Chè Sài Gòn", R.drawable.mon_an_19, 96, 69, "Hồ Chí Minh", "Việt Nam", 15000));
        danhSachMonAn.add(new MonAn(20,"Bánh mì kem", R.drawable.mon_an_20, 96, 69, "Huế", "Tiệm bánh", 50000));
        danhSachMonAn.add(new MonAn(21,"Bánh Tai Yến", R.drawable.mon_an_21, 96, 69, "Đà Nẵng", "Tiệm bánh", 40000));
        danhSachMonAn.add(new MonAn(22,"Cút lộn xào me", R.drawable.mon_an_22, 96, 69, "Hà Nội", "Việt Nam", 30000));
        danhSachMonAn.add(new MonAn(23,"Trứng vịt lộn", R.drawable.mon_an_23, 96, 69, "Hà Nội", "Việt Nam",20000));
        danhSachMonAn.add(new MonAn(24,"Bánh tráng cuốn", R.drawable.mon_an_24, 96, 69, "Hồ Chí Minh", "Mỹ", 60000));
        danhSachMonAn.add(new MonAn(25,"Tàu hũ xe lơm", R.drawable.mon_an_25, 96, 69, "Huế", "Ý", 100000));
        danhSachMonAn.add(new MonAn(26,"Bánh cay", R.drawable.mon_an_26, 96, 69, "Đà Nẵng", "Trung Hoa", 10000));
        danhSachMonAn.add(new MonAn(27,"Susi Nhật Bản", R.drawable.mon_an_27, 96, 69, "Hồ Chí Minh", "Mỹ", 40000));
        danhSachMonAn.add(new MonAn(28,"Bánh gô Hàn Xẻng", R.drawable.mon_an_28, 96, 69, "Hồ Chí Minh", "Mỹ", 30000));
        danhSachMonAn.add(new MonAn(29,"Bánh bao trà xanh nhân trứng muối", R.drawable.mon_an_29, 96, 69, "Vũng Tàu", "Tiệm bánh", 15000));
        danhSachMonAn.add(new MonAn(30,"Bánh paparoti trà xanh", R.drawable.mon_an_30, 96, 69, "Hà Nội", "Mỹ", 20000));
        danhSachMonAn.add(new MonAn(31,"Bánh rán mặn ngọt", R.drawable.mon_an_31, 96, 69, "Huế", "Nhật Bản", 30000));
        danhSachMonAn.add(new MonAn(32,"Bánh căn nhân thịt", R.drawable.mon_an_32, 96, 69, "Đà Nẵng", "Ấn độ", 50000));
        danhSachMonAn.add(new MonAn(33,"Bánh cá", R.drawable.mon_an_33, 96, 69, "Vũng Tàu", "Nhật bản", 60000));
        danhSachMonAn.add(new MonAn(34,"Bánh canh ruộng", R.drawable.mon_an_34, 96, 69, "Hà Nội", "Việt Nam", 120000));
        danhSachMonAn.add(new MonAn(35,"Mì không cay", R.drawable.mon_an_35, 96, 69, "Đà Nẵng", "Hàn Quốc", 70000));
        danhSachMonAn.add(new MonAn(36,"Bánh mật ong", R.drawable.mon_an_36, 96, 69, "Huế", "Mỹ", 10000));
        danhSachMonAn.add(new MonAn(37,"Khoai tây chiên", R.drawable.mon_an_37, 96, 69, "Vũng Tàu", "Mỹ", 50000));
        danhSachMonAn.add(new MonAn(38,"Kimbap", R.drawable.mon_an_38, 96, 69, "Hồ Chí Minh", "Hàn Quốc", 70000));
        danhSachMonAn.add(new MonAn(39,"Mì tôm", R.drawable.mon_an_39, 96, 69, "Đà Nẵng", "Hàn Quốc", 40000));
        danhSachMonAn.add(new MonAn(40,"Mì cay", R.drawable.mon_an_40, 96, 69, "Hà nội", "Hàn Quốc", 70000));
    }
}
