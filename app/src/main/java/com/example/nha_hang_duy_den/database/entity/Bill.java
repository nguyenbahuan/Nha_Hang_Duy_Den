package com.example.nha_hang_duy_den.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bill")
public class Bill {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "table_name")
    String table;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @ColumnInfo(name = "total")
    String total;
    @ColumnInfo(name = "date")
    String date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
