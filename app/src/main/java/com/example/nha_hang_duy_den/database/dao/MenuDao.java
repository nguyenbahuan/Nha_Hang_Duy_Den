package com.example.nha_hang_duy_den.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nha_hang_duy_den.database.entity.Menu;

import java.util.List;

@Dao
public interface MenuDao {
    @Query("SELECT * FROM menu")
    List<Menu> getAllMenus();
    @Insert
    void insertMenu(Menu menu);
    @Update
    void updateMenu(Menu menu);
    @Delete
    void deleteMenu(Menu menu);
}
