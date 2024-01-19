package com.example.nha_hang_duy_den.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nha_hang_duy_den.R;
import com.example.nha_hang_duy_den.adapter.TableAdapter;
import com.example.nha_hang_duy_den.database.AppDatabase;
import com.example.nha_hang_duy_den.database.entity.Menu;
import com.example.nha_hang_duy_den.database.entity.Table;
import com.example.nha_hang_duy_den.repository.BillRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableFragment extends Fragment {


    Context context;

    RecyclerView rcvTable;
    TableAdapter tableAdapter;
    FloatingActionButton btnAddTable,btnShowBill;
    BillRepository billRepository;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        context = container.getContext();
        rcvTable = view.findViewById(R.id.rcv_table);
        btnAddTable = view.findViewById(R.id.btn_to_add_table);
        btnShowBill  = view.findViewById(R.id.btn_to_add_table);
        //db
        AppDatabase db = AppDatabase.getInstance(context);


        RecyclerView.LayoutManager  layoutManager = new GridLayoutManager(context.getApplicationContext(),2);
        rcvTable.setLayoutManager(layoutManager);
//
        List<Table> tableList = db.tableDao().getAllTable();

        tableAdapter = new TableAdapter(context);
        tableAdapter.setData(tableList);



        if(billRepository.check ){
            billRepository = new BillRepository();
            billRepository.setTableArrayList(tableList);
            List<Map<Menu,Integer>> a = new ArrayList<>();
            billRepository.setFoodArrayList(a);
            billRepository.check = false;
            for (int i = 0; i < billRepository.tableArrayList.size();i++) {
                billRepository.foodArrayList.add(new HashMap<>());
            }
            Log.d("huanba",billRepository.foodArrayList.toString() );

        }



//
        rcvTable.setAdapter(tableAdapter);



        btnAddTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment addTablbeFragment = new AddTableFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout, addTablbeFragment, null);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
        return view;
    }
}