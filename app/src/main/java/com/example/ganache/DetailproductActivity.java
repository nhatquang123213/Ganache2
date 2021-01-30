package com.example.ganache;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class DetailproductActivity extends AppCompatActivity {
    TextView name,detail,price;
    ImageView img;
    Product_Search product_search;
    SanPham sanPham;
    Intent intent;
    Button btnaddtocart,buynow;
    ImageButton back;
    int amount,idsp;
    String price111,checklogin;
    Float f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailproduct);
            anhxa();

        amount =1;
        intent = getIntent();
        String thaotac = intent.getStringExtra("thaotac_key");
        if(thaotac.equals("search_to_detail")){
            searchtodetail();
        }else {
            bestselltodetail();
        }

        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.number_choose);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Toast.makeText(DetailproductActivity.this, "amount:"+i1, Toast.LENGTH_SHORT).show();
                amount = i1;
            }
        });
        checklogin = Users.getInstance().getAddress();
        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checklogin.length()==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailproductActivity.this);
                    builder.setMessage("You must be logged in to do that.")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(view.getContext(),Login.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else {
                    Addtocart(Server.url_Insertcart);
                }
                }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checklogin.length()==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailproductActivity.this);
                    builder.setMessage("You must be logged in to do that.")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(view.getContext(),Login.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else {
                    Intent intent = new Intent(DetailproductActivity.this,DeliveryActivity.class);
                    f = Float.parseFloat(price111);
                    f=(Float) f*amount;
                    intent.putExtra("Cost",String.valueOf(f));
                    startActivity(intent);
                }
            }
        });

    }
    private void bestselltodetail() {
        sanPham = (SanPham) intent.getSerializableExtra("Product_key_bestsell");
        name.setText(sanPham.getTen());
        detail.setText(sanPham.getDetail());
        price111 = sanPham.getPrice_sp();
        price.setText(sanPham.getPrice_sp()+"$");
        idsp = sanPham.getIdSp();
        Picasso.with(this)
                .load(sanPham.getImgSp())
                .into(img);
    }

    private void searchtodetail() {
        product_search = (Product_Search) intent.getSerializableExtra("Product_key");
        name.setText(product_search.getName());
        detail.setText(product_search.getDetail());
        price.setText(product_search.getPrice()+"$");
        price111 = product_search.getPrice();
        idsp = product_search.getId();
        Picasso.with(this)
                .load(product_search.getIdimg())
                .into(img);
    }

    private void Addtocart(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(DetailproductActivity.this, "Thanhcong", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DetailproductActivity.this, "", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailproductActivity.this, "Loi qua trinh", Toast.LENGTH_SHORT).show();
                        Log.d("AAA","Loi!!!"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id_user",String.valueOf(Users.getInstance().getCNIC()));
                params.put("id_product",String.valueOf(idsp));
                params.put("amount",String.valueOf(amount));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void anhxa() {
        name = (TextView) findViewById(R.id.txt_name_pro_detail);
        back = (ImageButton) findViewById(R.id.back_detail_pro);
        detail = (TextView) findViewById(R.id.txt_detail);
        price = (TextView) findViewById(R.id.txt_price_detail);
        img = (ImageView) findViewById(R.id.img_detail);
        btnaddtocart = (Button) findViewById(R.id.add_cart);
        buynow = (Button) findViewById(R.id.buynow);
    }
}