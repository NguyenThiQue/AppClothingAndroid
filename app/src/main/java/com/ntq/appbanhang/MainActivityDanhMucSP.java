package com.ntq.appbanhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.internal.zzx;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivityDanhMucSP extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private int currentFragment = R.id.nav_danhmuc;
    FrameLayout contentApp;

    //menu
    DrawerLayout layoutMenu;
    NavigationView navView;
    Toolbar toolBar;
    NotificationBadge notificationBadge;
    ImageView circleImageViewUser;
    TextView txtNameUser;

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

        showUserInformation();

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
                Intent intent = new Intent(MainActivityDanhMucSP.this, GioHangActivity.class);
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
        circleImageViewUser = navView.getHeaderView(0).findViewById(R.id.imgAvatar);
        txtNameUser = navView.getHeaderView(0).findViewById(R.id.txtNameUser);
        notificationBadge=findViewById(R.id.slcart);
        if(Server.listGioHang!=null){
            int total=0;
            for (int i=0; i<Server.listGioHang.size();i++){
                total=total+Server.listGioHang.get(i).getSoLuong();
            }
            notificationBadge.setText(String.valueOf(total));
        }
        if(Server.listGioHang==null){
            Server.listGioHang=new ArrayList<>();
        }

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
            case R.id.nav_home:
                if(currentFragment!=R.id.nav_chat){
                    Intent intent = new Intent(MainActivityDanhMucSP.this, MainActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.nav_out:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, Clothing.class);
                startActivity(intent);
                finish();
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

    private void showUserInformation() {
        //get đối tượng user từ Firebase trả về
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser == null) {
            return;
        }
        String name = firebaseUser.getDisplayName();
        Uri photoUrl = firebaseUser.getPhotoUrl();
        if(name == null) {
            txtNameUser.setVisibility(View.GONE);
        }
        else {
            txtNameUser.setVisibility(View.INVISIBLE);
            txtNameUser.setText(name);
        }
        Glide.with(this).load(photoUrl).error(R.drawable.user_circle).into(circleImageViewUser);
    }


}