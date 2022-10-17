package com.ntq.appbanhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class MainActivityDanhMucSP extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private int currentFragment = R.id.nav_danhmuc;
    FrameLayout contentApp;

    //menu
    DrawerLayout layoutMenu;
    NavigationView navView;
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dmsp);

        setControl();
        //toolbar
        setSupportActionBar(toolBar);
        navView.bringToFront();
        //navigation
        ActionBarDrawerToggle tongle =
                new ActionBarDrawerToggle(this, layoutMenu, toolBar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        layoutMenu.addDrawerListener(tongle);
        tongle.syncState();

        navView.setNavigationItemSelectedListener(this);

        replaceFragment(new LoaiSPFragment());
        navView.getMenu().findItem(R.id.nav_danhmuc).setChecked(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cart:
                CheckConnection.ShowToast_Short(this,"chọn cart");
                Intent intent = new Intent(MainActivityDanhMucSP.this, GioHang.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if ( layoutMenu.isDrawerOpen(GravityCompat.START)){
            layoutMenu.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    private void setControl() {
        layoutMenu = findViewById(R.id.layoutMenu);
        navView = findViewById(R.id.navView);
        toolBar = findViewById(R.id.toolBar);
        contentApp = findViewById(R.id.contentFrame);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        switch (id)
        {
            case R.id.nav_danhmuc:
                if(currentFragment!=R.id.nav_danhmuc){
                    replaceFragment(new LoaiSPFragment());
                    currentFragment = R.id.nav_danhmuc;
                    toolBar.setTitle("Danh mục sản phẩm");
                }
                break;
            case R.id.nav_chat:
                if(currentFragment!=R.id.nav_chat){
                    replaceFragment(new ChatFragment());
                    currentFragment = R.id.nav_chat;
                    toolBar.setTitle("Chat");
                }
                break;
        }
        layoutMenu.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFrame,fragment);
        transaction.commit();

    }


}