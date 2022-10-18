package com.ntq.appbanhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class GioHangActivity extends AppCompatActivity {
    TextView gioTrong,txtTien;
    Toolbar backGio;
    RecyclerView recyclerViewGio;
    Button btnTaoDon;
    GioHangAdapter gioHangAdapter;
    List<GioHang> dsGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        gioTrong=findViewById(R.id.gioTrong);
        backGio=findViewById(R.id.backGio);
        recyclerViewGio=findViewById(R.id.listGioHang);
        txtTien=findViewById(R.id.txtTien);
        btnTaoDon=findViewById(R.id.btnTaoDon);
        if(Server.listGioHang!=null){
            long money=0;
            for (int i=0; i<Server.listGioHang.size();i++){
                money=money+Server.listGioHang.get(i).getGiaSP();
            }
            txtTien.setText("Tổng tiền: "+String.valueOf(money));
        }
        setSupportActionBar(backGio);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        backGio.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerViewGio.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerViewGio.setLayoutManager(layoutManager);

        if(Server.listGioHang.size()==0){
            gioTrong.setVisibility(View.VISIBLE);
        }else{
            gioHangAdapter=new GioHangAdapter(getApplicationContext(), Server.listGioHang);
            recyclerViewGio.setAdapter(gioHangAdapter);
        }

    }
}