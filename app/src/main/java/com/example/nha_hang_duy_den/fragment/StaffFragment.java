package com.example.nha_hang_duy_den.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nha_hang_duy_den.HelperClass;
import com.example.nha_hang_duy_den.R;
import com.example.nha_hang_duy_den.adapter.UserAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class StaffFragment extends Fragment {

    Context context;
    RecyclerView recyclerUser;
    DatabaseReference databaseReference;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_staff, container, false);
        context = container.getContext();
        recyclerUser = view.findViewById(R.id.rcv_user);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        List<HelperClass> userList = new ArrayList<>();
        UserAdapter userAdapter = new UserAdapter(context);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerUser.setLayoutManager(layoutManager);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    for (DataSnapshot item : snapshot.getChildren()) {
                        // Lấy dữ liệu từ mỗi child node
                        HelperClass user = item.getValue(HelperClass.class);
                        if (user != null) {
                            userList.add(user);
                            String name = user.getFullname();
                            String phone = user.getPhone();
                            Log.d("Firebase", "Name: " + name + ", Age: " + phone);
                        }

                    }
                    Log.d("Firebase", "Value: " + userList);
                    userAdapter.setListUser(userList);
                    recyclerUser.setAdapter(userAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Firebase", "Value: " + error.getMessage());
            }
        });

        return view;
    }
}