package com.example.nha_hang_duy_den.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nha_hang_duy_den.R;
import com.example.nha_hang_duy_den.adapter.BillAdapter;
import com.example.nha_hang_duy_den.database.AppDatabase;
import com.example.nha_hang_duy_den.database.entity.Bill;

import java.util.List;


public class StatisticalFragment extends Fragment {


    Context context;

    RecyclerView rcvBill;
    BillAdapter billAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistical, container, false);
        context = container.getContext();
        rcvBill = view.findViewById(R.id.rcv_statistical);
        RecyclerView.LayoutManager  layoutManager = new LinearLayoutManager(context);
        rcvBill.setLayoutManager(layoutManager);

        AppDatabase db = AppDatabase.getInstance(context);
        List<Bill> billList = db.billDao().getAllBills();
        billAdapter = new BillAdapter(context);

        billAdapter.setData(billList);

        rcvBill.setAdapter(billAdapter);


        return view;
    }
}