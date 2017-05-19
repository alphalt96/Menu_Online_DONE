package com.example.admin.menu_online.models;

import java.io.Serializable;

/**
 * Created by Admin on 4/13/2017.
 */

public class MonAn implements Serializable {
    private int maMonAn;
    private String tenMonAn;
    private int image;
    private int soLuong;
    private int rating;
    private String viTri;
    private String quanAn;
    private String loaiMonAn;
    private float giaTien;
    private int luotView;
    private int luotThich;

    public int getMaMonAn() {
        return maMonAn;
    }

    public void setMaMonAn(int maMonAn) {
        this.maMonAn = maMonAn;
    }

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

    public int getLuotView() {
        return luotView;
    }

    public void setLuotView(int luotView) {
        this.luotView = luotView;
    }

    public int getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(int luotThich) {
        this.luotThich = luotThich;
    }

    public MonAn() {
    }

    public MonAn(int maMonAn ,String tenMonAn, int image,int soLuong, int rating, String viTri, String loaiMonAn, float giaTien, int luotView, int luotThich) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.image = image;
        this.soLuong = soLuong;
        this.rating = rating;
        this.viTri = viTri;
        this.loaiMonAn = loaiMonAn;
        this.giaTien = giaTien;
        this.luotView = luotView;
        this.luotThich = luotThich;
    }
}
