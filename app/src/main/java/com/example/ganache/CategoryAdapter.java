package com.example.ganache;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.content.ContextCompat.startActivity;

public class CategoryAdapter extends RecyclerView.Adapter <CategoryAdapter.ViewHolder>{
    ArrayList<Category> categoryArrayList;
    Context context;

    public CategoryAdapter(ArrayList<Category> categoryArrayList, Context context) {
        this.categoryArrayList = categoryArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_gridview_cate,parent,false);


        return new ViewHolder(view) ;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context).load(categoryArrayList.get(position).getCateidImage()).placeholder(R.drawable.donthaveacc).error(R.drawable.deleteiconn).into(holder.image);
        holder.tentheloai.setText(categoryArrayList.get(position).getCateName());
        holder.mota.setText(categoryArrayList.get(position).getCateIntro());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SearchActivity.class);
                intent.putExtra("id_cate_key",String.valueOf(categoryArrayList.get(position).getCateId()));
                intent.putExtra("name_cate_key",(String) categoryArrayList.get(position).getCateName());
                intent.putExtra("thaotac_key","Category");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tentheloai;
        private ImageView image;
        private TextView mota;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            anhxa(itemView);
        }

        private void anhxa(View itemView) {
            tentheloai = (TextView) itemView.findViewById(R.id.name_cate);
            image = (ImageView) itemView.findViewById(R.id.img_cate);
            relativeLayout=(RelativeLayout) itemView.findViewById(R.id.rl_cate);
            mota = (TextView) itemView.findViewById(R.id.detail_cate);
        }
    }
}
