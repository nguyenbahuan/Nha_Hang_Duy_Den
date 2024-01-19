package com.example.nha_hang_duy_den.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nha_hang_duy_den.HelperClass;
import com.example.nha_hang_duy_den.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    Context context;
    List<HelperClass> listUser;
    public UserAdapter(Context context){
        this.context = context;
    }
    public void setListUser( List<HelperClass> listUser){
        this.listUser = listUser;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        HelperClass user = listUser.get(position);
        holder.txtName.setText(user.getFullname().toString());
        holder.txtPhone.setText(user.getPhone().toString());
        holder.imagePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("tel:"+user.getPhone());
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(uri);
                startActivity(context,intent,null);
            }


        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtPhone;
        ImageView imagePhone;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.name_user);
            txtPhone = itemView.findViewById(R.id.phone_user);
            imagePhone = itemView.findViewById(R.id.icon_user);

        }
    }
}
