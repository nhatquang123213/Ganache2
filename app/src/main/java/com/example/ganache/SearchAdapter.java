package com.example.ganache;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    ArrayList<Product_Search>arrayList;
    Context context;
    public SearchAdapter(ArrayList<Product_Search> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_search,parent,false);


        return new SearchAdapter.ViewHolder(view) ;

    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Picasso.with(context).load(arrayList.get(position).getIdimg()).placeholder(R.drawable.donthaveacc).error(R.drawable.deleteiconn).into(holder.image);
        holder.tensp.setText(arrayList.get(position).getName());
        holder.gia.setText(arrayList.get(position).getPrice());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailproductActivity.class);
                intent.putExtra("Product_key", (Serializable) arrayList.get(position));
//                intent.putExtra("id_cate_key",String.valueOf(categoryArrayList.get(position).getCateId()));
//                intent.putExtra("name_cate_key",(String) categoryArrayList.get(position).getCateName());
                intent.putExtra("thaotac_key","search_to_detail");
                intent.putExtra("thaotac_key","search_to_detail");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tensp;
        private ImageView image;
        private TextView gia;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            anhxa(itemView);
        }

        private void anhxa(View itemView) {
            tensp = (TextView) itemView.findViewById(R.id.name_search);
            image = (ImageView) itemView.findViewById(R.id.img_search);
            relativeLayout=(RelativeLayout) itemView.findViewById(R.id.rl_search);
            gia = (TextView) itemView.findViewById(R.id.price_search);
        }
    }
}
