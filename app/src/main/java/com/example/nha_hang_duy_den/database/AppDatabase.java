package com.example.nha_hang_duy_den.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.nha_hang_duy_den.database.dao.BillDao;
import com.example.nha_hang_duy_den.database.dao.MenuDao;
import com.example.nha_hang_duy_den.database.dao.TableDao;
import com.example.nha_hang_duy_den.database.entity.Bill;
import com.example.nha_hang_duy_den.database.entity.Menu;
import com.example.nha_hang_duy_den.database.entity.Table;

@Database(entities = {Menu.class, Table.class, Bill.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "ql_nha_hang";
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public abstract MenuDao menuDao();
    public abstract TableDao tableDao();
    public abstract BillDao billDao();
}
