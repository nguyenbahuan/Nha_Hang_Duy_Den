package com.example.nha_hang_duy_den.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nha_hang_duy_den.R;
import com.example.nha_hang_duy_den.database.AppDatabase;
import com.example.nha_hang_duy_den.database.entity.Menu;
import com.example.nha_hang_duy_den.fragment.EditFoodFragment;
import com.example.nha_hang_duy_den.fragment.MenuFragment;
import com.example.nha_hang_duy_den.repository.BillRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{
    private Context context;

    BillRepository billRepository;
    private List<Menu> menuList;
    String tableAdd;

    public void setBundle(String a) {
        this.tableAdd = a;
    }
    public MenuAdapter (Context context ) {
        this.context = context;
//        this.bundle = bundle;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
        notifyDataSetChanged();
    }
    public void removeFood(int pos){
        menuList.remove(pos);
    }
    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_food,parent,false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu menu = menuList.get(position);
//
        holder.txtName.setText(menu.getNameFood());
        holder.txtPrice.setText(String.valueOf(menu.getPriceFood()));
        Glide.with(context).load(menu.getImgPathFood()).override(200, 200) // resizing
                .centerCrop()
                .into(holder.imageView);  // imageview object
        holder.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,holder.btnShow);
                popupMenu.inflate(R.menu.flow_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if(id == R.id.edit){

                            Fragment fragment = new EditFoodFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("id", String.valueOf(menu.getId()));
                            bundle.putString("name", String.valueOf(menu.getNameFood()));
                            bundle.putString("price", String.valueOf(menu.getPriceFood()));
                            bundle.putString("path", String.valueOf(menu.getImgPathFood()));
                            fragment.setArguments(bundle);
                            FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_layout, fragment, null);
                            fragmentTransaction.addToBackStack(null).commit();
                        }else if(id == R.id.delete){
                            AppDatabase database = AppDatabase.getInstance(context);
                            database.menuDao().deleteMenu(menu);
                            removeFood(position);
                            Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tableAdd.equals("Chưa chọn")) {

                }else {

                    Toast.makeText(v.getContext(),tableAdd,Toast.LENGTH_SHORT).show();
                    final Dialog dialog = new Dialog(v.getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_add_food);


                    Window window = dialog.getWindow();
                    if (window == null){
                        return;
                    }
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    WindowManager.LayoutParams layoutParams = window.getAttributes();
                    layoutParams.gravity = Gravity.CENTER;
                    window.setAttributes(layoutParams);

                    dialog.setCancelable(true);

                    EditText editNumber = dialog.findViewById(R.id.dia_quantity);
                    Button cancel =  dialog.findViewById(R.id.dia_to_back);
                    Button btnOk =  dialog.findViewById(R.id.dia_add);

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //handle add food to bill
                            billRepository.addFoodToTable(Integer.valueOf(tableAdd),menu,Integer.valueOf(editNumber.getText().toString()));
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView img, txtName, txtPrice;
        ImageView imageView,btnShow;
        RelativeLayout relativeLayout;
        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

//            img = itemView.findViewById(R.id.img);
            imageView = itemView.findViewById(R.id.img_food);
            txtName = itemView.findViewById(R.id.name_food);
            txtPrice = itemView.findViewById(R.id.price_food);
            relativeLayout = itemView.findViewById(R.id.relativelayoutFood);
            btnShow= itemView.findViewById(R.id.show_menu);


        }
    }
}
