package models;

import java.io.Serializable;

/**
 * Created by Anh on 4/22/2017.
 */

public class QuanAn implements Serializable {
    private String tenQuan;
    private String diaChi;
    private String thanhPho;
    private int img;

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

    public QuanAn(String tenQuan, String diaChi, String thanhPho, int img) {
        this.tenQuan = tenQuan;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
        this.img = img;
    }
}
