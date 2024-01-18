package com.example.nha_hang_duy_den.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "menu")
public class Menu {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name_food")
    private String nameFood;
    @ColumnInfo(name = "price_food")
    private int priceFood;
    @ColumnInfo(name = "img_path_food")
    private String imgPathFood;
    @ColumnInfo(name = "img_key_food")
    private String imgKeyFood;

    public String getImgPathFood() {
        return imgPathFood;
    }

    public String getImgKeyFood() {
        return imgKeyFood;
    }

    public void setImgPathFood(String imgPathFood) {
        this.imgPathFood = imgPathFood;
    }

    public void setImgKeyFood(String imgKeyFood) {
        this.imgKeyFood = imgKeyFood;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public int getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(int priceFood) {
        this.priceFood = priceFood;
    }
}
