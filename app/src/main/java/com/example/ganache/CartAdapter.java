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

public class CartAdapter extends RecyclerView.Adapter <CartAdapter.ViewHolder>{
    ArrayList<Cart> cartArrayList;
    Context context;

    public CartAdapter(ArrayList<Cart> cartArrayList, Context context) {
        this.cartArrayList = cartArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_listview_cart,parent,false);


        return new CartAdapter.ViewHolder(view) ;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context).load(cartArrayList.get(position).getCartidImage()).placeholder(R.drawable.donthaveacc).error(R.drawable.deleteiconn).into(holder.image);
        holder.tensp.setText(cartArrayList.get(position).getCartName());
        holder.amount.setText(String.valueOf(cartArrayList.get(position).getCartNumber()));
        holder.gia.setText(cartArrayList.get(position).getCartPrice());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "chuyen"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tensp,gia;
        private ImageView image;
        private TextView amount;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            anhxa(itemView);
        }

        private void anhxa(View itemView) {
            tensp = (TextView) itemView.findViewById(R.id.name_cart);
            gia = (TextView) itemView.findViewById(R.id.price_cart);
            image = (ImageView) itemView.findViewById(R.id.img_cart);
            relativeLayout=(RelativeLayout) itemView.findViewById(R.id.ln_cart);
            amount = (TextView) itemView.findViewById(R.id.number_cart);
        }
    }
}
