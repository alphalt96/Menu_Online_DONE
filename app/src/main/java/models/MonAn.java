package models;

import java.io.Serializable;

/**
 * Created by Admin on 4/13/2017.
 */

public class MonAn implements Serializable {
    private String tenMonAn;
    private int image;
    private int soLuong;
    private int rating;
    private String viTri;
    private String quanAn;
    private String loaiMonAn;
    private float giaTien;

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getLoaiMonAn() {
        return loaiMonAn;
    }

    public void setLoaiMonAn(String loaiMonAn) {
        this.loaiMonAn = loaiMonAn;
    }

    public float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(float giaTien) {
        this.giaTien = giaTien;
    }

    public MonAn() {
    }

    public MonAn(String tenMonAn, int image,int soLuong, int rating, String viTri, String loaiMonAn, float giaTien) {
        this.tenMonAn = tenMonAn;
        this.image = image;
        this.soLuong = soLuong;
        this.rating = rating;
        this.viTri = viTri;
        this.loaiMonAn = loaiMonAn;
        this.giaTien = giaTien;
    }
}
