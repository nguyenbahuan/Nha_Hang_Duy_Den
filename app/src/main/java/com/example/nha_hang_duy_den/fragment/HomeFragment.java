package com.example.nha_hang_duy_den.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nha_hang_duy_den.R;


public class HomeFragment extends Fragment {
    ImageButton imgBtnShow,imgBtnMenu,btnTable,btnStaff;
    TextView textShow;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        imgBtnShow = view.findViewById(R.id.imgBtnShow);
        imgBtnMenu = view.findViewById(R.id.img_btn_menu);
        btnTable = view.findViewById(R.id.img_btn_table);
        btnStaff = view.findViewById(R.id.img_btn_staff);
        textShow = view.findViewById(R.id.txtShow);

        imgBtnMenu.setOnClickListener(v -> toMenuFragment());
        textShow.setOnClickListener(v -> toMenuFragment());
        imgBtnShow.setOnClickListener(v -> toMenuFragment());

        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TableFragment();

//                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_layout,fragment,null);
//                fragmentTransaction.addToBackStack(null).commit();
                toFragment(fragment);
            }
        });
        btnStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new StaffFragment();
//                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_layout,fragment,null);
//                fragmentTransaction.addToBackStack(null).commit();
                toFragment(fragment);
            }
        });

        return view;
    }
    public void toMenuFragment() {
        Fragment menuF = new MenuFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_layout,menuF,null);
        fragmentTransaction.addToBackStack(null).commit();
    }
    public void toFragment(Fragment fragment) {

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_layout,fragment,null);
        fragmentTransaction.addToBackStack(null).commit();
    }
}