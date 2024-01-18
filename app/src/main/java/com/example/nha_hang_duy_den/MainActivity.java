package com.example.nha_hang_duy_den;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.nha_hang_duy_den.fragment.HomeFragment;
import com.example.nha_hang_duy_den.fragment.MenuFragment;
import com.example.nha_hang_duy_den.fragment.StaffFragment;
import com.example.nha_hang_duy_den.fragment.StatisticalFragment;
import com.example.nha_hang_duy_den.fragment.TableFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set Home
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_layout, new HomeFragment())
                    .commit();
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.nav_home) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    fragmentR(new HomeFragment());
                }else if(id == R.id.nav_statistical) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    fragmentR(new StatisticalFragment());
                }else if(id == R.id.nav_menu) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    fragmentR(new MenuFragment());
                }else if(id == R.id.nav_table) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    fragmentR(new TableFragment());
                }else if(id == R.id.nav_staff) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    fragmentR(new StaffFragment());
                }else if (id == R.id.nav_logout) {

                }
                return true;
            }});
    }
    public void fragmentR(Fragment fragment ) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout,fragment,null);
        fragmentTransaction.addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if(count == 0 ) {
            super.onBackPressed();
        }else  {
            getSupportFragmentManager().popBackStack();
        }
    }
}