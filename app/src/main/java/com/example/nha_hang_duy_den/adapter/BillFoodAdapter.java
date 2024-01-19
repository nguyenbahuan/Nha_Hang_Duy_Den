package com.example.nha_hang_duy_den.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nha_hang_duy_den.R;
import com.example.nha_hang_duy_den.database.entity.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BillFoodAdapter  extends RecyclerView.Adapter<BillFoodAdapter.BillViewHolder>{

    Context context;
    public BillFoodAdapter(Context context) {
        this.context = context;
    }

    Map<Menu, Integer> billTable;
    public void setData(Map<Menu, Integer> billTable) {
        this.billTable = billTable;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_food_table,parent,false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        int total = 0;
        int quantity;
        int subtotal = 0;

        List<Menu> food = new ArrayList<>(billTable.keySet());
        Menu menuFood = food.get(position);

        List<Integer> list = new ArrayList<>(billTable.values());
        int amount = list.get(position);
        for (Map.Entry<Menu, Integer> entry : billTable.entrySet()) {
            Menu menu = entry.getKey();
            quantity = entry.getValue();

            int price = menu.getPriceFood();
            subtotal = price * quantity;

            total += subtotal;


        }
        Glide.with(context).load(menuFood.getImgPathFood()).override(200, 200) // resizing
                .centerCrop()
                .into(holder.imageFood);  // imageview object
        holder.txtTotal.setText(String.valueOf(amount * menuFood.getPriceFood()));
        holder.txtName.setText(menuFood.getNameFood());
        holder.txtQuantity.setText(String.valueOf(amount));

    }

    @Override
    public int getItemCount() {
        return billTable.size();
    }

    public class BillViewHolder extends RecyclerView.ViewHolder {
        ImageView imageFood;
        TextView txtName,txtQuantity,txtTotal;
        RelativeLayout relativelayoutTable;
        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            imageFood = itemView.findViewById(R.id.img_food_table);
            txtName = itemView.findViewById(R.id.name_food_table);
            txtQuantity = itemView.findViewById(R.id.amount_food_table);
            txtTotal = itemView.findViewById(R.id.total_food_table);



        }
    }
}
