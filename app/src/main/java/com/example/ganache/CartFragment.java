package com.example.ganache;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Cart> arrayList;
    CartAdapter adapter;
    ImageView complete;
    TextView total;
    float f;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (inflater.inflate(R.layout.fragment_cart, container, false));
        Anhxa(view);
        getcart();
        adapter = new CartAdapter(arrayList,getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        String ff = String.valueOf(f);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),DeliveryActivity.class);
                intent.putExtra("Cost",String.valueOf(f));
                startActivity(intent);
            }
        });
        return view;
    }

    private void getcart() {
        f=0;
        arrayList = new ArrayList<>();
        RequestQueue requestQueue  = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.url_Getcart, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i =0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        if(object.getInt("id_user")==Users.getInstance().getCNIC()){
                            createData_cart(object.getInt("id_cart"),object.getInt("id_product"),object.getInt("amount"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }

    private void createData_cart(Integer id_cart,Integer id_pro,Integer amount) {

        RequestQueue requestQueue  = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.url_Getsp, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i =0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        if(object.getInt("D_ID")==id_pro){
                            f=(Float)f+amount*Float.parseFloat(object.getString("price"));
                            arrayList.add(new Cart(
                                    id_cart,
                                    object.getString("FName"),
                                    object.getString("img"),
                                    object.getString("price"),
                                    amount
                            ));
                        }

//                        Toast.makeText(ClassActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
        total.setText("Total: "+f+"$");
    }

    private void Anhxa(View view) {
        recyclerView = (RecyclerView)  view.findViewById(R.id.rc_cart);
        total = (TextView) view.findViewById(R.id.txttotal);
        complete = (ImageView) view.findViewById(R.id.complorder);
    }

}
