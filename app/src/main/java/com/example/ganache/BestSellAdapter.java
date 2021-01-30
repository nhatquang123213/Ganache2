package com.example.ganache;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BestSellAdapter extends RecyclerView.Adapter <BestSellAdapter.ViewHolder>{
    ArrayList<SanPham> sanPhamArrayList;
    Context context;

    public BestSellAdapter(ArrayList<SanPham> sanPhamArrayList, Context context) {
        this.sanPhamArrayList = sanPhamArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_listview_bestsell,parent,false);


        return new ViewHolder(view) ;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context).load(sanPhamArrayList.get(position).getImgSp()).placeholder(R.drawable.donthaveacc).error(R.drawable.deleteiconn).into(holder.image);
        holder.tensanpham.setText(sanPhamArrayList.get(position).getTen());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailproductActivity.class);
                intent.putExtra("Product_key_bestsell", (Serializable) sanPhamArrayList.get(position));
                intent.putExtra("thaotac_key","bestsell_to_detail");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tensanpham;
        private ImageView image;
        private LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            anhxa(itemView);
        }

        private void anhxa(View itemView) {
            tensanpham = (TextView) itemView.findViewById(R.id.name_bestsell);
            image = (ImageView) itemView.findViewById(R.id.img_bestsell);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.ln_bestsell);
        }
    }
}
