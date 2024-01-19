package com.example.nha_hang_duy_den.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nha_hang_duy_den.R;
import com.example.nha_hang_duy_den.adapter.BillFoodAdapter;
import com.example.nha_hang_duy_den.database.AppDatabase;
import com.example.nha_hang_duy_den.database.entity.Bill;
import com.example.nha_hang_duy_den.database.entity.Menu;
import com.example.nha_hang_duy_den.database.entity.Table;
import com.example.nha_hang_duy_den.repository.BillRepository;

import java.time.LocalDate;
import java.util.Map;


public class BillFragment extends Fragment {


    Context context;

    RecyclerView rcvFoodTable;
    Button btnPay;
    TextView txtTotal;
    BillFoodAdapter billFoodAdapter;
    Bill bill;
    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_bill, container, false);
        context = container.getContext();
        rcvFoodTable = view.findViewById(R.id.rcv_bill_table);
        btnPay = view.findViewById(R.id.btn_pay_table);
        txtTotal = view.findViewById(R.id.txt_total);
        billFoodAdapter = new BillFoodAdapter(context);
        BillRepository billRepository = new BillRepository();
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(context.getApplicationContext());
        rcvFoodTable.setLayoutManager(layoutManager);

        Bundle bundle = getArguments();
        String data ;
        if(bundle != null) {
            data = bundle.getString("food");
            billFoodAdapter.setData(billRepository.getBillTable(Integer.valueOf(data)));
            rcvFoodTable.setAdapter(billFoodAdapter);

        }

        String id = bundle.getString("food");
        int quantity = 0;
        int total = 0;
        Map<Menu,Integer> billTable = billRepository.getBillTable(Integer.valueOf(id));
        for (Map.Entry<Menu, Integer> entry : billTable.entrySet()) {
            Menu menu = entry.getKey();
            quantity = entry.getValue();

            int price = menu.getPriceFood();
            int subtotal = price * quantity;

            total += subtotal;
        }
        txtTotal.setText(String.valueOf(total) + " đ");
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String table = bundle.getString("table");
                LocalDate currentDate = LocalDate.now();
                int quantity = 0;
                int total = 0;
                Map<Menu,Integer> billTable = billRepository.getBillTable(Integer.valueOf(id));
                if(billTable.isEmpty()){
                    Toast.makeText(context,"Bàn này chưa có món",Toast.LENGTH_SHORT).show();
                }else {
                    for (Map.Entry<Menu, Integer> entry : billTable.entrySet()) {
                        Menu menu = entry.getKey();
                        quantity = entry.getValue();

                        int price = menu.getPriceFood();
                        int subtotal = price * quantity;

                        total += subtotal;
                    }
                    Log.d("huanba",currentDate.toString());
                    Log.d("huanba",table+" : " + total);
                    Bill bill = new Bill();
                    bill.setTable(table);
                    bill.setDate(currentDate.toString());
                    bill.setTotal(String.valueOf(total));
                    bill.setListFood(billTable.toString());
                    Log.d("huanba",billTable.toString());
                    billRepository.insertBill(context,bill);
                    billRepository.clearTable(Integer.valueOf(id));
                    Log.d("huanba","da clear: "+billTable.toString());

                }


            }
        });


        return view;

    }
}