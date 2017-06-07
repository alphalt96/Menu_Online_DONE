package com.example.admin.menu_online.controller;

import android.content.Context;

import com.example.admin.menu_online.Database.MenuOnlineDatabase;
import com.example.admin.menu_online.R;
import com.example.admin.menu_online.models.MonAn;

import java.util.ArrayList;

/**
 * Created by Admin on 4/13/2017.
 */

//singleton chi cho phep tao 1 the hien cua no chu khong cho tao ra nhieu doi tuong
public class MonAnManager {
    private static MonAnManager sInstance = null;
    private ArrayList<MonAn> danhSachMonAn, danhSachMonMoi, danhSachNoiBat;
    private ArrayList<String> danhSachTenMonAn;
    private MenuOnlineDatabase db;
    private Context context;

    private MonAnManager(Context context){
        danhSachMonAn = new ArrayList<MonAn>();
        danhSachMonMoi = new ArrayList<MonAn>();
        danhSachNoiBat = new ArrayList<MonAn>();
        danhSachTenMonAn = new ArrayList<>();
        db = new MenuOnlineDatabase(context);
        this.context = context;
    }
    public static MonAnManager getsInstance(Context context){
        if(sInstance == null)
            sInstance = new MonAnManager(context);
        return sInstance;
    }
    public ArrayList<MonAn> getDanhSachMonAn(){
        if(danhSachMonAn.size() == 0) {
            danhSachMonAn.clear();
            danhSachMonAn = db.getAllMonAn();
        }
        return danhSachMonAn;
    }
    public ArrayList<MonAn> getDanhSachMonMoi(){
        if(danhSachMonMoi.size() == 0) {
            danhSachMonMoi.clear();
            danhSachMonMoi = db.getNewMonAn();
        }
        return danhSachMonMoi;
    }
    public ArrayList<String> getDanhSachTenMonAn(){
        if (danhSachTenMonAn.size() == 0){
            danhSachTenMonAn.clear();
            danhSachTenMonAn = db.getNameMonAn();
        }
        return danhSachTenMonAn;
    }
    public ArrayList<MonAn> getDanhSachNoiBat(){
        if (danhSachNoiBat.size() == 0){
            danhSachNoiBat.clear();
            danhSachNoiBat = db.getMonAnNoiBat();
        }
        return danhSachNoiBat;
    }

    public void LoadData(){
//        db.releaseData();
        if(LoadDatabaseControl.getsInstance(context).isFirstLoadApp()) {
            db.insertMonAn("Bánh tráng trộn", R.drawable.mon_an_5, 69, "Hải Châu", "Việt Nam", 30000, 12, 23, "43 Phần Lăng 8");
            db.insertMonAn("Gan rim", R.drawable.mon_an_1, 96, "Thanh Khê", "Nhật Bản", 69000, 34, 45, "929 Trần Cao Vân, Đà Nẵng");
            db.insertMonAn("Ếch xào xả ớt", R.drawable.mon_an_2, 69, "Sơn Trà", "Việt Nam", 20000,56, 67, "320/6 Hoàng Diệu, Đà Nẵng");
            db.insertMonAn("Thịt nướng lu", R.drawable.mon_an_3, 96, "Ngũ Hành Sơn", "Nhật Bản", 10000, 78,89, "k280/23 Hoàng Diệu, Đà Nẵng");
            db.insertMonAn("Bánh tráng kẹp", R.drawable.mon_an_4, 69, "Liên Chiểu", "Nhật Bản", 50000, 90, 100, "90 Âu Cơ, Đà Nẵng");
            db.insertMonAn("Bánh tráng nướng", R.drawable.mon_an_6, 96, "Hải Châu", "Mỹ", 120000, 12, 23, "39 Ông Ích Khiêm, Đà Nẵng");
            db.insertMonAn("Ốc bưu", R.drawable.mon_an_7, 96, "Thanh Khê", "Mỹ", 200000, 34, 45, "112/71 Trần Cao Vân, Đà Nẵng");
            db.insertMonAn("Phá lấu", R.drawable.mon_an_8, 96, "Sơn Trà", "Mỹ", 50000, 56, 67, "73 Phan Châu Trinh, Đà Nẵng");
            db.insertMonAn("Bột chiên", R.drawable.mon_an_9, 96, "Liên Chiểu", "Nhật Bản", 20000, 78, 89, "32 Hoàng Hoa Thám, Đà Nẵng");
            db.insertMonAn("Xiên nướng", R.drawable.mon_an_10, 96, "Hòa Vang", "Ý", 15000, 90, 100, "39 Nguyễn Trãi, Đà Nẵng");
            db.insertMonAn("Chè thái", R.drawable.mon_an_11, 96, "Thanh Khê", "Thái Lan", 70000, 12, 23, "176 Trần Phú, Đà Nẵng");
            db.insertMonAn("Súp cua", R.drawable.mon_an_12, 96, "Hải Châu", "Mỹ", 15000, 34, 45, "62/2A Núi Thành, Đà Nẵng");
            db.insertMonAn("Bò bía", R.drawable.mon_an_13, 96, "Hòa Vang", "Mỹ", 100000, 56, 67, "88 Trần Bình Trọng, Đà Nẵng");
            db.insertMonAn("Chim cút chiên bơ", R.drawable.mon_an_14, 96, "Liên Chiểu", "Mỹ", 20000, 78, 89, "703 Trần Cao Vân, Đà Nẵng");
            db.insertMonAn("Gỏi bò khô", R.drawable.mon_an_15, 96, "Hòa Vang", "Mỹ", 50000, 90, 100, "36 Nguyễn Bá Học, Đà Nẵng");
            db.insertMonAn("Bắp xào", R.drawable.mon_an_16, 96, "Hải Châu", "Mỹ", 80000, 12, 23, "87 Trần Bình Trọng, Đà Nẵng");
            db.insertMonAn("Hủ tiếu", R.drawable.mon_an_17, 96, "Thanh Khê", "Mỹ", 70000, 34, 45, "78 Đống Đa, Đà Nẵng");
            db.insertMonAn("Chuối nướng bọc nếp", R.drawable.mon_an_18, 96, "Sơn Trà", "Mỹ", 20000, 56, 67, "14 Trần Quý Cáp, Đà Nẵng");
            db.insertMonAn("Chè Sài Gòn", R.drawable.mon_an_19, 96, "Liên Chiểu", "Việt Nam", 15000, 78, 89, "41A Nguyễn Du, Đà Nẵng");
            db.insertMonAn("Bánh mì kem", R.drawable.mon_an_20, 96, "Ngũ Hành Sơn", "Tiệm bánh", 50000, 90, 100, "68/2 Lê Đình Lý, Đà Nẵng");
            db.insertMonAn("Bánh Tai Yến", R.drawable.mon_an_21, 96, "Thanh Khê", "Tiệm bánh", 40000, 111, 112, "278 Trưng Nữ Vương, Đà Nẵng");
            db.insertMonAn("Cút lộn xào me", R.drawable.mon_an_22, 96, "Hải Châu", "Việt Nam", 30000, 123, 134, "31 Lê Duẫn, Đà Nẵng");
            db.insertMonAn("Trứng vịt lộn", R.drawable.mon_an_23, 96, "Sơn Trà", "Việt Nam", 20000, 345, 236, "122 Nguyễn Chí Thanh, Đà Nẵng");
            db.insertMonAn("Bánh tráng cuốn", R.drawable.mon_an_24, 96, "Liên Chiểu", "Mỹ", 60000, 99, 88, "k586/34 Ông Ích Khiêm, Đà Nẵng");
            db.insertMonAn("Tàu hũ xe lơm", R.drawable.mon_an_25, 96, "Hòa Vang", "Ý", 100000, 23, 89, "33 Đầm Rong, Đà Nẵng");
            db.insertMonAn("Bánh cay", R.drawable.mon_an_26, 96, "Hải Châu", "Trung Hoa", 10000, 22, 27, "02 Phan Thành Tài, Đà Nẵng");
            db.insertMonAn("Susi Nhật Bản", R.drawable.mon_an_27, 96, "Thanh Khê", "Mỹ", 40000, 90, 100, "100 Hoàng Văn Thụ, Đà Nẵng");
            db.insertMonAn("Bánh gô Hàn Xẻng", R.drawable.mon_an_28, 96, "Sơn Trà", "Mỹ", 30000, 69, 96, "59/5 Nguyễn Tri Phương, Đà Nẵng");
            db.insertMonAn("Bánh bao trà xanh nhân trứng muối", R.drawable.mon_an_29, 96, "Liên Chiểu", "Tiệm bánh", 15000, 36, 234, "104 Trưng Nữ Vương, Đà Nẵng");
            db.insertMonAn("Bánh paparoti trà xanh", R.drawable.mon_an_30, 96, "Ngũ Hành Sơn", "Mỹ", 20000, 45, 90, "65/22 Hàm Nghi, Đà Nẵng");
            db.insertMonAn("Bánh rán mặn ngọt", R.drawable.mon_an_31, 96, "Thanh Khê", "Nhật Bản", 30000, 89, 98, "22 Trưng Nữ Vương, Đà Nẵng");
            db.insertMonAn("Bánh căn nhân thịt", R.drawable.mon_an_32, 96, "Hải Châu", "Ấn độ", 50000, 100, 1, "149/5 Lê Đình Lý, Đà Nẵng");
            db.insertMonAn("Bánh cá", R.drawable.mon_an_33, 96, "Hòa Vang", "Nhật bản", 60000, 100, 300, "46B Hoàng Hoa Thám, Đà Nẵng");
            db.insertMonAn("Bánh canh ruộng", R.drawable.mon_an_34, 96, "Liên Chiểu", "Việt Nam", 120000, 67, 89, "138 Bạch Đằng, Đà Nẵng");
            db.insertMonAn("Mì không cay", R.drawable.mon_an_35, 96, "Hòa Vang", "Hàn Quốc", 70000, 90, 20, "159 Phan Thanh, Đà Nẵng");
            db.insertMonAn("Bánh mật ong", R.drawable.mon_an_36, 96, "Thanh Khê", "Mỹ", 10000, 100, 90, "190 Bạch Đằng, Đà Nẵng");
            db.insertMonAn("Khoai tây chiên", R.drawable.mon_an_37, 96, "Hải Châu", "Mỹ", 50000, 80, 70, "26 Chu Văn An, Đà Nẵng");
            db.insertMonAn("Kimbap", R.drawable.mon_an_38, 96, "Sơn Trà", "Hàn Quốc", 70000, 77, 90, "k254/1 Trần Cao Vân, Đà Nẵng");
            db.insertMonAn("Mì tôm", R.drawable.mon_an_39, 96, "Liên Chiểu", "Hàn Quốc", 40000, 68, 22, "K142/46/09 Điện Biên Phủ, Đà Nẵng");
            db.insertMonAn("Mì cay", R.drawable.mon_an_40, 96, "Ngũ Hành Sơn", "Hàn Quốc", 70000, 90, 100, "325/21 Hùng Vương, Đà Nẵng");
        }
    }
}
