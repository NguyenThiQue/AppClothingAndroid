package com.ntq.appbanhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    Context context;
    List<GioHang> listGioHang;

    public GioHangAdapter(Context context, List<GioHang> listGioHang) {
        this.context = context;
        this.listGioHang = listGioHang;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GioHang item= listGioHang.get(position);
        holder.txtTenGio.setText(item.getTenSP());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        holder.txtGiaGio.setText(decimalFormat.format((item.getGiaSP()*item.getSoLuong()))+"Đ");
        holder.txtsl.setText(item.getSoLuong()+"");
        Glide.with(context).load(item.getHinhSP())
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.no_image)
                .into(holder.imgGio);
    }

    @Override
    public int getItemCount() {
        if(listGioHang!=null){
            return listGioHang.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgGio;
        TextView txtTenGio,txtGiaGio,txtsl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGio=itemView.findViewById(R.id.imgGio);
            txtsl=itemView.findViewById(R.id.txtsl);
            txtGiaGio=itemView.findViewById(R.id.txtGiaGio);
            txtTenGio=itemView.findViewById(R.id.txtTenGio);
        }
    }
}
