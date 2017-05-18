package com.example.admin.menu_online.models;

/**
 * Created by Yep on 18/5/2017.
 */

public class ItemMenu {
    private int iconImg;
    private String itemName;

    public ItemMenu() {
    }

    public ItemMenu(int iconImg, String itemName) {
        this.iconImg = iconImg;
        this.itemName = itemName;
    }

    public int getIconImg() {
        return iconImg;
    }

    public void setIconImg(int iconImg) {
        this.iconImg = iconImg;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
