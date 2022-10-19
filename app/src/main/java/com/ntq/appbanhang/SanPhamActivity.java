package com.ntq.appbanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SanPhamActivity extends AppCompatActivity {
    RecyclerView recyclerViewSanPham;
    Toolbar toolbarLoai;
    ImageView cartSP;
    ArrayList<SanPham> mangSP;
    SanPhamAdapter sanPhamAdapter;
    NotificationBadge notificationBadge;
    int idLoai=0;
    String tenLoai="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        recyclerViewSanPham= findViewById(R.id.recyclerViewSanPham);
        toolbarLoai= findViewById(R.id.tenLoaiSP);
        notificationBadge=findViewById(R.id.slcart);
        idLoai=getIntent().getIntExtra("idloaisanpham",-1);
        tenLoai=getIntent().getStringExtra("tenloaisanpham");
        if(Server.listGioHang!=null){
            int total=0;
            for (int i=0; i<Server.listGioHang.size();i++){
                total=total+Server.listGioHang.get(i).getSoLuong();
            }
            notificationBadge.setText(String.valueOf(total));
        }
        setSupportActionBar(toolbarLoai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLoai.setTitle(tenLoai);
        toolbarLoai.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mangSP= new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(getApplicationContext(), mangSP,R.layout.item_sanpham);
        recyclerViewSanPham.setHasFixedSize(true);
        recyclerViewSanPham.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewSanPham.setAdapter(sanPhamAdapter);
            DuLieuSanPham();

    }



    private void DuLieuSanPham() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Server.urlSanPhamTheoLoai, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int ID = 0;
                    String TenSP = "";
                    Integer GiaSP = 0;
                    Integer GiaSPSale = 0;
                    String HinhAnhSP = "";
                    String MoTaSP = "";
                    String Star1 = "";
                    String Star2 = "";
                    String Star3 = "";
                    String Star4 = "";
                    String Star5 = "";
                    String Heart = "";
                    int IdSP = 0;
                    if(response!=null){
                        try {
                            JSONArray jsonArray= new JSONArray(response);
                            for(int i = 0; i <jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            TenSP = jsonObject.getString("tensanpham");
                            GiaSP = jsonObject.getInt("giasanpham");
                            GiaSPSale = jsonObject.getInt("pricesale");
                            HinhAnhSP = jsonObject.getString("hinhanhsanpham");
                            MoTaSP = jsonObject.getString("motasanpham");
                            Star1 = jsonObject.getString("star");
                            Star2 = jsonObject.getString("star");
                            Star3 = jsonObject.getString("star");
                            Star4 = jsonObject.getString("star");
                            Star5 = jsonObject.getString("star");
                            Heart = jsonObject.getString("heart");
                            IdSP = jsonObject.getInt("idsanpham");
                            mangSP.add(new SanPham(ID, TenSP, GiaSPSale, GiaSP, HinhAnhSP, MoTaSP, Star1, Star2, Star3, Star4, Star5, Heart, IdSP));
                            sanPhamAdapter.notifyDataSetChanged();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param= new HashMap<String,String>();
                param.put("idloai", String.valueOf(idLoai));
                return param;
            }
        };
        requestQueue.add(stringRequest);
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
                Intent intent = new Intent(this, GioHangActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
//    public void themGioHang() {
//        cartSP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(server.listGioHang.size()>0){
//
//                }else {
//                    int soluong=1;
        //              int gia= Integer.parseInt(product)
//                }
//            }
//        });

 //   }

}