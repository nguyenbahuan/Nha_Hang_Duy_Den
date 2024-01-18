package com.example.nha_hang_duy_den.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nha_hang_duy_den.database.entity.Table;

import java.util.List;

@Dao
public interface TableDao {
    @Query("SELECT * FROM `table`")
    List<Table> getAllTable();

    @Insert
    void insertTable(Table table);
    @Update
    void updateTable(Table table);
    @Delete
    void deleteTable(Table table);

}
