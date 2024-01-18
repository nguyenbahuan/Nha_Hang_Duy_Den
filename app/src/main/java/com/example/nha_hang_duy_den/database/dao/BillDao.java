package com.example.nha_hang_duy_den.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nha_hang_duy_den.database.entity.Bill;

import java.util.List;

@Dao
public interface BillDao {
    @Query("SELECT * FROM bill")
    List<Bill> getAllBills();
    @Insert
    void insertBill(Bill bill);
    @Update
    void updateBill(Bill bill);
    @Delete
    void deleteBill(Bill bill);
}
