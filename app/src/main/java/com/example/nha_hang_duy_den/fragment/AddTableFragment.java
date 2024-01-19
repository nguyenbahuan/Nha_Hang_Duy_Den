package com.example.nha_hang_duy_den.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nha_hang_duy_den.R;
import com.example.nha_hang_duy_den.database.AppDatabase;
import com.example.nha_hang_duy_den.database.entity.Table;


public class AddTableFragment extends Fragment {

    Button btnAdd;
    TextView textName;
    Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_table, container, false);
        btnAdd = view.findViewById(R.id.btn_add_table);
        textName = view.findViewById(R.id.ip_table_name);
        context = container.getContext();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Table table = new Table();
                table.setTableName(String.valueOf(textName.getText()));

                AppDatabase database = AppDatabase.getInstance(context);
                database.tableDao().insertTable(table);
                Toast.makeText(context,"ok",Toast.LENGTH_SHORT).show();
                textName.setText("");
            }

        });

        return view;
    }
}