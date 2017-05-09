package com.example.admin.menu_online.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.admin.menu_online.models.MonAn;
import com.example.admin.menu_online.models.QuanAn;
import com.example.admin.menu_online.models.User;

import java.util.ArrayList;

/**
 * Created by Yep on 4/5/2017.
 */

public class MenuOnlineDatabase extends SQLiteOpenHelper {
    int oldVersion = 1, newVersion = 2;

    public static final String DATABASE_NAME = "MENUONLINEDB";
    public static final String MONAN_TABLE = "MONAN";
    public static final String MONAN_COLUMN_maMonAn = "maMonAn";
    public static final String MONAN_COLUMN_tenMonAn = "tenMonAn";
    public static final String MONAN_COLUMN_soLuong = "soLuong";
    public static final String MONAN_COLUMN_image = "image";
    public static final String MONAN_COLUMN_viTri = "viTri";
    public static final String MONAN_COLUMN_loaiMonAn = "loaiMonAn";
    public static final String MONAN_COLUMN_giaTien = "giaTien";

    public static final String QUANAN_TABLE = "QUANAN";
    public static final String QUANAN_COLUMN_maQuan = "maQuan";
    public static final String QUANAN_COLUMN_tenQuan = "tenQuan";
    public static final String QUANAN_COLUMN_diaChi = "diaChi";
    public static final String QUANAN_COLUMN_thanhPho = "thanhPho";
    public static final String QUANAN_COLUMN_img = "img";
    public static final String QUANAN_COLUMN_monAnList = "monAnList";

    public static final String USER_TABLE = "USER";
    public static final String USER_COLUMN_id = "id";
    public static final String USER_COLUMN_username = "username";
    public static final String USER_COlUMN_password = "password";
    public static final String USER_COLUMN_address = "address";

    //version o day la de update CSDL, CSDL chi duoc chap nhan thay doi neu thay doi version
    public MenuOnlineDatabase(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table MONAN " +
                "(maMonAn integer primary key, tenMonAn text,soLuong integer,image integer, viTri text,loaiMonAn text, giaTien real)"
        );
        db.execSQL("create table QUANAN " +
                "(maQuan integer primary key, tenQuan text,diaChi text,thanhPho text, img integer, monAnList text)"
        );
        db.execSQL("create table USER " +
                "(id integer primary key, username text, password text, address text)"
        );
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void releaseData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+MONAN_TABLE);
    }
    public void releaseDataQuanAn(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+QUANAN_TABLE);
    }
    public void dropQuanAn(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + QUANAN_TABLE);
        onCreate(db);
    }
    //insert mon an
    public void insertMonAn(String tenMonAn,int image, int soLuong, String viTri, String loaiMonAn, float giaTien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenMonAn", tenMonAn);
        values.put("soLuong", soLuong);
        values.put("image", image);
        values.put("viTri", viTri);
        values.put("loaiMonAn", loaiMonAn);
        values.put("giaTien", giaTien);
        db.insert(MONAN_TABLE, null, values);
    }

    //insert quan an
    public void insertQuanAn(String tenQuan, String diaChi, String thanhPho, int img, String monAnList){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenQuan", tenQuan);
        values.put("diaChi", diaChi);
        values.put("thanhPho", thanhPho);
        values.put("img", img);
        values.put("monAnList", monAnList);
        db.insert(QUANAN_TABLE, null, values);
    }

    //check trung username
    public boolean checkUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        //cursor khi select ra ma khong co record tra ve thi van duoc xem la != null
        Cursor cursor = db.rawQuery("select * from "+USER_TABLE+" where username = '"+username+"'", null);
        //cursor.getCount de dem so dong record tra ve, khong kiem tra null duoc vi luc nao cung khac null
        if(cursor.getCount()>0) return false;
        return true;
    }

    //insert nguoi dung
    public void insertUser(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("address", "none");
        db.insert("USER", null, values);
    }
    public User getUser(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+USER_TABLE+" where "+USER_COLUMN_username+" = '"+username+"' AND "+USER_COlUMN_password+" = '"+password+"'", null);
        cursor.moveToFirst();
        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndex(USER_COLUMN_id)));
        user.setUsername(cursor.getString(cursor.getColumnIndex(USER_COLUMN_username)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(USER_COlUMN_password)));
        user.setAddress(cursor.getString(cursor.getColumnIndex(USER_COLUMN_address)));
        return user;
    }

    //get toan bo mon an
    public ArrayList<MonAn> getAllMonAn(){
        ArrayList<MonAn> list  = new ArrayList<>();
        MonAn monAn;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+MONAN_TABLE, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            monAn = new MonAn();
            monAn.setTenMonAn(cursor.getString(cursor.getColumnIndex(MONAN_COLUMN_tenMonAn)));
            monAn.setSoLuong(cursor.getInt(cursor.getColumnIndex(MONAN_COLUMN_soLuong)));
            monAn.setImage(cursor.getInt(cursor.getColumnIndex(MONAN_COLUMN_image)));
            monAn.setViTri(cursor.getString(cursor.getColumnIndex(MONAN_COLUMN_viTri)));
            monAn.setLoaiMonAn(cursor.getString(cursor.getColumnIndex(MONAN_COLUMN_loaiMonAn)));
            monAn.setGiaTien(cursor.getFloat(cursor.getColumnIndex(MONAN_COLUMN_giaTien)));
            list.add(monAn);
            cursor.moveToNext();
        }
        return list;
    }
    public ArrayList<QuanAn> getAllQuanAn(){
        ArrayList<QuanAn> list  = new ArrayList<>();
        QuanAn quanAn;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+QUANAN_TABLE, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            try {
                quanAn = new QuanAn();
                quanAn.setTenQuan(cursor.getString(cursor.getColumnIndex(QUANAN_COLUMN_tenQuan)));
                quanAn.setDiaChi(cursor.getString(cursor.getColumnIndex(QUANAN_COLUMN_diaChi)));
                quanAn.setThanhPho(cursor.getString(cursor.getColumnIndex(QUANAN_COLUMN_thanhPho)));
                quanAn.setImg(cursor.getInt(cursor.getColumnIndex(QUANAN_COLUMN_img)));
                // cach 1
//                ArrayList<MonAn> monAnList = new ArrayList<>();
//                MonAn monAn;
//                String get = cursor.getString(cursor.getColumnIndex(QUANAN_COLUMN_monAnList));
//                JSONObject jsonObject = new JSONObject(get);
//                for (int i = 0; i < 5; i++) {
//                    int vt = jsonObject.getInt("mon" + (i + 1));
//                    Cursor value = db.rawQuery("select * from MONAN where maMonAn = " + vt, null);
//                    monAn = new MonAn();
//                    monAn.setTenMonAn(value.getString(value.getColumnIndex(MONAN_COLUMN_tenMonAn)));
//                    monAn.setSoLuong(value.getInt(cursor.getColumnIndex(MONAN_COLUMN_soLuong)));
//                    monAn.setImage(cursor.getInt(value.getColumnIndex(MONAN_COLUMN_image)));
//                    monAn.setViTri(value.getString(value.getColumnIndex(MONAN_COLUMN_viTri)));
//                    monAn.setLoaiMonAn(value.getString(value.getColumnIndex(MONAN_COLUMN_loaiMonAn)));
//                    monAn.setGiaTien(value.getFloat(value.getColumnIndex(MONAN_COLUMN_giaTien)));
//                    monAnList.add(monAn);
//                }
                // cach 2
//                String get = cursor.getString(cursor.getColumnIndex(QUANAN_COLUMN_monAnList));
//                ArrayList<MonAn> monAnList = new Gson().fromJson(get, new TypeToken<ArrayList<MonAn>>(){}.getType());
                // cach 3
                ArrayList<MonAn> monAnList = new ArrayList<>();
                MonAn monAn;
                String get = cursor.getString(cursor.getColumnIndex(QUANAN_COLUMN_monAnList));
                String[] arr = get.split(" ");
                Cursor ahihi;
                for (int i = 0; i < arr.length; i++) {
                    ahihi = db.rawQuery("select * from MONAN where maMonAn = "+arr[i], null);
                    ahihi.moveToFirst();
                    monAn = new MonAn();
                    monAn.setTenMonAn(ahihi.getString(ahihi.getColumnIndex(MONAN_COLUMN_tenMonAn)));
                    monAn.setSoLuong(ahihi.getInt(ahihi.getColumnIndex(MONAN_COLUMN_soLuong)));
                    monAn.setImage(ahihi.getInt(ahihi.getColumnIndex(MONAN_COLUMN_image)));
                    monAn.setViTri(ahihi.getString(ahihi.getColumnIndex(MONAN_COLUMN_viTri)));
                    monAn.setLoaiMonAn(ahihi.getString(ahihi.getColumnIndex(MONAN_COLUMN_loaiMonAn)));
                    monAn.setGiaTien(ahihi.getFloat(ahihi.getColumnIndex(MONAN_COLUMN_giaTien)));
                    monAnList.add(monAn);
                }
                quanAn.setMonAnList(monAnList);
                list.add(quanAn);
                cursor.moveToNext();
            } catch (Exception e) {

            }
        }
        return list;
    }
}
