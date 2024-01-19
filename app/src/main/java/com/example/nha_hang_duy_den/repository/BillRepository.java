package com.example.nha_hang_duy_den.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.nha_hang_duy_den.database.AppDatabase;
import com.example.nha_hang_duy_den.database.entity.Bill;
import com.example.nha_hang_duy_den.database.entity.Menu;
import com.example.nha_hang_duy_den.database.entity.Table;

import java.util.List;
import java.util.Map;

public class BillRepository {
    public static List<Bill> billArrayList;
    public static List<Table> tableArrayList;
    public static List<Map<Menu,Integer>> foodArrayList;
    public static Boolean check = true;

    public static List<Bill> getBillArrayList() {
        return billArrayList;
    }

    public static void setBillArrayList(List<Bill> billArrayList) {
        BillRepository.billArrayList = billArrayList;
    }

    public static List<Table> getTableArrayList() {
        return tableArrayList;
    }

    public static void setTableArrayList(List<Table> tableArrayList) {
        BillRepository.tableArrayList = tableArrayList;
    }

    public static  List<Map<Menu,Integer>> getFoodArrayList() {
        return foodArrayList;
    }

    public static void setFoodArrayList( List<Map<Menu,Integer>> foodArrayList) {
        BillRepository.foodArrayList = foodArrayList;
    }

    static public void addFoodToTable(int idTable , Menu menu,int quantity){

        for(int i = 0; i< tableArrayList.size(); i++) {
            if(tableArrayList.get(i).getId() == idTable){
                foodArrayList.get(i).put(menu,quantity);
                Log.d("huanba",menu.toString()+"//"+quantity);
                Log.d("huanba",foodArrayList.toString());
            }
        }

    }
    public Map<Menu,Integer> getBillTable(int idTable) {
        int index = -1;
        for(int i = 0; i< tableArrayList.size(); i++) {
            if(tableArrayList.get(i).getId() == idTable){
                index = i;
            }
        }
        if (index != -1){
            return foodArrayList.get(index);
        } return null;
    }
    public void clearTable(int idTable) {
        int index = -1;
        for(int i = 0; i< tableArrayList.size(); i++) {
            if(tableArrayList.get(i).getId() == idTable){
                index = i;
            }
        }
        if (index != -1){
            foodArrayList.get(index).clear();
        }
    }
    public void getBill(String table,String date,String total) {
        Bill bill1 = new Bill();
        bill1.setTable(table);
        bill1.setDate(date);
        bill1.setTotal(total);
    }

    public void insertBill(Context context, Bill bill) {
        AppDatabase db = AppDatabase.getInstance(context);
        db.billDao().insertBill(bill);
        Toast.makeText(context,"Thanh toán thành công",Toast.LENGTH_SHORT).show();
    }
}
