package com.example.nha_hang_duy_den.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nha_hang_duy_den.fragment.BillFragment;
import com.example.nha_hang_duy_den.R;
import com.example.nha_hang_duy_den.database.entity.Table;
import com.example.nha_hang_duy_den.fragment.MenuFragment;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder>{
    List<Table> tableArrayList;
    Context context;
    BillFoodAdapter billFoodAdapter;
    public TableAdapter(Context context){
        this.context = context;
    }
    public void setData(List<Table> list) {
        this.tableArrayList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_table,parent,false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        Table table = tableArrayList.get(position);
        holder.imageTable.setImageResource(R.drawable.ic_table);
        holder.txtTable.setText(table.getTableName());

        holder.relativelayoutTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),String.valueOf(table.getTableName()) ,Toast.LENGTH_SHORT).show();
            }
        });
        holder.imageTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.linearLayout.setVisibility(View.VISIBLE);
            }
        });
        holder.btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.linearLayout.setVisibility(View.GONE);
            }
        });
        holder.addFoodToTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////
                Fragment fragment = new MenuFragment();
                Bundle bundle = new Bundle();
                bundle.putString("Key",String.valueOf(table.getId()));
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout, fragment, null);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
        holder.btnShowBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                BillRepository billRepository = new BillRepository();
//                Map<Menu, Integer> billTable = billRepository.getBillTable(table.getId());
//                billFoodAdapter = new BillFoodAdapter(v.getContext());
//                billFoodAdapter.setData(billTable);

                Fragment fragment = new BillFragment();
                Bundle bundle = new Bundle();
                bundle.putString("food",String.valueOf(table.getId()));
                bundle.putString("table",String.valueOf(table.getTableName()));
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout, fragment, null);
                fragmentTransaction.addToBackStack(null).commit();
//
//                int total = 0;
//                for (Map.Entry<Menu, Integer> entry : billTable.entrySet()) {
//                    Menu menu = entry.getKey();
//                    int quantity = entry.getValue();
//
//                    int price = menu.getPriceFood();
//                    int subtotal = price * quantity;
//
//                    total += subtotal;
//
//                    Log.d("huanba", "Subtotal for " + menu.getNameFood() + ": " + subtotal);
//                }

//                Log.d("huanba", "Total: " + total);
//                Log.d("huanba", "Bill Table keys: " + billTable.keySet().toString());
//                Log.d("huanba", billTable.keySet().toArray() + "hehe");
            }
        });
    }

    @Override
    public int getItemCount() {
        return tableArrayList.size();
    }

    public class TableViewHolder extends RecyclerView.ViewHolder {
        ImageView imageTable;
        ImageButton btn_close,addFoodToTable,btnShowBill;
        LinearLayout linearLayout;
        TextView txtTable;
        RelativeLayout relativelayoutTable;
        public TableViewHolder(@NonNull View itemView) {
            super(itemView);



            imageTable = itemView.findViewById(R.id.img_table);
            txtTable = itemView.findViewById(R.id.name_table);
            relativelayoutTable = itemView.findViewById(R.id.relativelayoutTable);
            linearLayout = itemView.findViewById(R.id.handle_table);
            btn_close = itemView.findViewById(R.id.close_handle_table);
            addFoodToTable = itemView.findViewById(R.id.add_food_to_table);
            btnShowBill = itemView.findViewById(R.id.btn_pay);

        }
    }
}
