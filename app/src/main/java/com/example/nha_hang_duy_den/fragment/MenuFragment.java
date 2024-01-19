package com.example.nha_hang_duy_den.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nha_hang_duy_den.R;
import com.example.nha_hang_duy_den.adapter.MenuAdapter;
import com.example.nha_hang_duy_den.database.AppDatabase;
import com.example.nha_hang_duy_den.database.entity.Menu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MenuFragment extends Fragment {

    Context context;
    MenuAdapter menuAdapter;
    RecyclerView recViewMenu;
    FloatingActionButton btnAddMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        recViewMenu = view.findViewById(R.id.menuView);
        btnAddMenu = view.findViewById(R.id.btnAddMenu);
        context = container.getContext();
        //even to AddFoodFragment
        btnAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment addFoodFragment = new AddFoodFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout, addFoodFragment, null);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
        //Show menu item
        RecyclerView.LayoutManager layoutManager =new GridLayoutManager(context.getApplicationContext(),2);
        recViewMenu.setLayoutManager(layoutManager);
        //
        AppDatabase db = AppDatabase.getInstance(context);
        List<Menu> menuList = db.menuDao().getAllMenus();
        menuAdapter = new MenuAdapter(context);

        Bundle bundle = getArguments();
        String data;
        if(bundle != null) {
            data = bundle.getString("Key");
            menuAdapter.setBundle(data);
            menuAdapter.setMenuList(menuList);
            recViewMenu.setAdapter(menuAdapter);
        }else {
            menuAdapter.setBundle("Chưa chọn");
            menuAdapter.setMenuList(menuList);
            recViewMenu.setAdapter(menuAdapter);
        }
        return view;
    }
}