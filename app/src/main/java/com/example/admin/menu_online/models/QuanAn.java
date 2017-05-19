package com.example.admin.menu_online.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Anh on 4/22/2017.
 */

public class QuanAn implements Serializable {
    private String tenQuan;
    private String diaChi;
    private String thanhPho;
    private int img;
    private int viewNum;
    private ArrayList<MonAn> MonAnList;

    public String getTenQuan() {
        return tenQuan;
    }

    public void setTenQuan(String tenQuan) {
        this.tenQuan = tenQuan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public QuanAn() {
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public ArrayList<MonAn> getMonAnList() {
        return MonAnList;
    }

    public void setMonAnList(ArrayList<MonAn> monAnList) {
        MonAnList = monAnList;
    }

    public QuanAn(String tenQuan, String diaChi, String thanhPho, int img, int viewNum) {
        this.tenQuan = tenQuan;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
        this.img = img;
        this.MonAnList = new ArrayList<>();
        this.viewNum = viewNum;
    }
}
