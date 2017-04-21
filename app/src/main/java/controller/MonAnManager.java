package controller;

import com.example.admin.menu_online.R;

import java.util.ArrayList;

import models.MonAn;

/**
 * Created by Admin on 4/13/2017.
 */

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
        danhSachMonMoi.add(new MonAn("Bánh tráng trộn", R.drawable.mon_an_5, 69, 9, "Đà Nẵng", "Việt Nam", 30000));
        danhSachMonMoi.add(new MonAn("Gan rim", R.drawable.mon_an_1, 96, 8, "Hà Nội", "Nhật Bản", 69000));
        danhSachMonMoi.add(new MonAn("Ếch xào xả ớt", R.drawable.mon_an_2, 69, 5, "Hồ Chí Minh", "Việt Nam", 20000));
        danhSachMonMoi.add(new MonAn("Thịt nướng lu", R.drawable.mon_an_3, 96, 7, "Đà Nẵng", "Nhật Bản", 10000));
        danhSachMonMoi.add(new MonAn("Bánh tráng kẹp", R.drawable.mon_an_4, 69, 2, "Hà Nội", "Nhật Bản", 50000));
        danhSachMonMoi.add(new MonAn("Bánh tráng nướng", R.drawable.mon_an_6, 96, 69, "Huế", "Mỹ", 120000));
        danhSachMonMoi.add(new MonAn("Ốc bưu", R.drawable.mon_an_7, 96, 69, "Vũng Tàu", "Mỹ", 200000));
        danhSachMonMoi.add(new MonAn("Phá lấu", R.drawable.mon_an_8, 96, 69, "Đà Nẵng", "Mỹ", 50000));
        danhSachMonMoi.add(new MonAn("Bột chiên", R.drawable.mon_an_9, 96, 69, "Hà Nội", "Nhật Bản", 20000));
        danhSachMonMoi.add(new MonAn("Xiên nướng", R.drawable.mon_an_10, 96, 69, "Hồ Chí Minh", "Ý", 15000));
    }
    private void fakeData(){
        danhSachMonAn.add(new MonAn("Bánh tráng trộn", R.drawable.mon_an_5, 69, 9, "Đà Nẵng", "Việt Nam", 40000));
        danhSachMonAn.add(new MonAn("Gan rim", R.drawable.mon_an_1, 96, 8, "Hà Nội", "Nhật Bản", 50000));
        danhSachMonAn.add(new MonAn("Ếch xào xả ớt", R.drawable.mon_an_2, 69, 5, "Hồ Chí Minh", "Việt Nam", 150000));
        danhSachMonAn.add(new MonAn("Thịt nướng lu", R.drawable.mon_an_3, 96, 7, "Đà Nẵng", "Nhật Bản", 40000));
        danhSachMonAn.add(new MonAn("Bánh tráng kẹp", R.drawable.mon_an_4, 69, 2, "Hà Nội", "Nhật Bản", 15000));
        danhSachMonAn.add(new MonAn("Bánh tráng nướng", R.drawable.mon_an_6, 96, 69, "Huế", "Mỹ", 10000));
        danhSachMonAn.add(new MonAn("Ốc bưu", R.drawable.mon_an_7, 96, 69, "Vũng Tàu", "Mỹ", 20000));
        danhSachMonAn.add(new MonAn("Phá lấu", R.drawable.mon_an_8, 96, 69, "Đà Nẵng", "Mỹ", 20000));
        danhSachMonAn.add(new MonAn("Bột chiên", R.drawable.mon_an_9, 96, 69, "Hà Nội", "", 16000));
        danhSachMonAn.add(new MonAn("Xiên nướng", R.drawable.mon_an_10, 96, 69, "Hồ Chí Minh", "Ý", 70000));
        danhSachMonAn.add(new MonAn("Chè thái", R.drawable.mon_an_11, 96, 69, "Huế", "Thái Lan", 70000));
        danhSachMonAn.add(new MonAn("Súp cua", R.drawable.mon_an_12, 96, 69, "Hồ Chí Minh", "Mỹ", 15000));
        danhSachMonAn.add(new MonAn("Bò bía", R.drawable.mon_an_13, 96, 69, "Đà Nẵng", "Mỹ", 100000));
        danhSachMonAn.add(new MonAn("Chim cút chiên bơ", R.drawable.mon_an_14, 96, 69, "Hà Nội", "Mỹ", 20000));
        danhSachMonAn.add(new MonAn("Gỏi bò khô", R.drawable.mon_an_15, 96, 69, "Hồ Chí Minh", "Mỹ", 50000));
        danhSachMonAn.add(new MonAn("Bắp xào", R.drawable.mon_an_16, 96, 69, "Vũng Tàu", "Mỹ", 80000));
        danhSachMonAn.add(new MonAn("Hủ tiếu", R.drawable.mon_an_17, 96, 69, "Hà Nội", "Mỹ", 70000));
        danhSachMonAn.add(new MonAn("Chuối nướng bọc nếp", R.drawable.mon_an_18, 96, 69, "Huế", "Mỹ", 20000));
        danhSachMonAn.add(new MonAn("Chè Sài Gòn", R.drawable.mon_an_19, 96, 69, "Hồ Chí Minh", "Việt Nam", 15000));
        danhSachMonAn.add(new MonAn("Bánh mì kem", R.drawable.mon_an_20, 96, 69, "Huế", "Tiệm bánh", 50000));
        danhSachMonAn.add(new MonAn("Bánh Tai Yến", R.drawable.mon_an_21, 96, 69, "Đà Nẵng", "Tiệm bánh", 40000));
        danhSachMonAn.add(new MonAn("Cút lộn xào me", R.drawable.mon_an_22, 96, 69, "Hà Nội", "Việt Nam", 30000));
        danhSachMonAn.add(new MonAn("Trứng vịt lộn", R.drawable.mon_an_23, 96, 69, "Hà Nội", "Việt Nam",20000));
        danhSachMonAn.add(new MonAn("Bánh tráng cuốn", R.drawable.mon_an_24, 96, 69, "Hồ Chí Minh", "Mỹ", 60000));
        danhSachMonAn.add(new MonAn("Tàu hũ xe lơm", R.drawable.mon_an_25, 96, 69, "Huế", "Ý", 100000));
        danhSachMonAn.add(new MonAn("Bánh cay", R.drawable.mon_an_26, 96, 69, "Đà Nẵng", "Trung Hoa", 10000));
        danhSachMonAn.add(new MonAn("Susi Nhật Bản", R.drawable.mon_an_27, 96, 69, "Hồ Chí Minh", "Mỹ", 40000));
        danhSachMonAn.add(new MonAn("Bánh gô Hàn Xẻng", R.drawable.mon_an_28, 96, 69, "Hồ Chí Minh", "Mỹ", 30000));
        danhSachMonAn.add(new MonAn("Bánh bao trà xanh nhân trứng muối", R.drawable.mon_an_29, 96, 69, "Vũng Tàu", "Tiệm bánh", 15000));
        danhSachMonAn.add(new MonAn("Bánh paparoti trà xanh", R.drawable.mon_an_30, 96, 69, "Hà Nội", "Mỹ", 20000));
        danhSachMonAn.add(new MonAn("Bánh rán mặn ngọt", R.drawable.mon_an_31, 96, 69, "Huế", "Nhật Bản", 30000));
        danhSachMonAn.add(new MonAn("Bánh căn nhân thịt", R.drawable.mon_an_32, 96, 69, "Đà Nẵng", "Ấn độ", 50000));
        danhSachMonAn.add(new MonAn("Bánh cá", R.drawable.mon_an_33, 96, 69, "Vũng Tàu", "Nhật bản", 60000));
        danhSachMonAn.add(new MonAn("Bánh canh ruộng", R.drawable.mon_an_34, 96, 69, "Hà Nội", "Việt Nam", 120000));
        danhSachMonAn.add(new MonAn("Mì không cay", R.drawable.mon_an_35, 96, 69, "Đà Nẵng", "Hàn Quốc", 70000));
        danhSachMonAn.add(new MonAn("Bánh mật ong", R.drawable.mon_an_36, 96, 69, "Huế", "Mỹ", 10000));
        danhSachMonAn.add(new MonAn("Khoai tây chiên", R.drawable.mon_an_37, 96, 69, "Vũng Tàu", "Mỹ", 50000));
        danhSachMonAn.add(new MonAn("Kimbap", R.drawable.mon_an_38, 96, 69, "Hồ Chí Minh", "Hàn Quốc", 70000));
        danhSachMonAn.add(new MonAn("Mì tôm", R.drawable.mon_an_39, 96, 69, "Đà Nẵng", "Hàn Quốc", 40000));
        danhSachMonAn.add(new MonAn("Mì cay", R.drawable.mon_an_40, 96, 69, "Hà nội", "Hàn Quốc", 70000));
    }
}
