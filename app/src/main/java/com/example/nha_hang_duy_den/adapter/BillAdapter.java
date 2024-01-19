package com.example.nha_hang_duy_den.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nha_hang_duy_den.R;
import com.example.nha_hang_duy_den.database.entity.Bill;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder>{
    Context context;
    List<Bill> billList;
    public BillAdapter(Context context)  {
        this.context = context;
    }
    public void setData(List<Bill> billList) {
        this.billList = billList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_bill,parent,false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        Bill bill = billList.get(position);
        holder.txtId.setText(String.valueOf(bill.getId()));
        holder.txtDate.setText(String.valueOf(bill.getDate()) );
        holder.txtTable.setText(String.valueOf(bill.getTable()) );
        holder.txtTotal.setText("Thành tiền: "+(bill.getTotal()) + " đ");
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    public class BillViewHolder extends RecyclerView.ViewHolder{
        TextView txtId, txtTable,txtDate,txtTotal;
        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.bill_id);
            txtTable = itemView.findViewById(R.id.bill_table);
            txtDate = itemView.findViewById(R.id.bill_date);
            txtTotal = itemView.findViewById(R.id.bill_total);
        }
    }
}
