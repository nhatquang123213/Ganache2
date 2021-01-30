package com.example.ganache;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Product_Search> arrayList;
    SearchAdapter searchAdapter;
    String id_cate;
    String name_cate;
    TextView namecate;
    String thaotac,value_search;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Anhxa();
        Intent intent = getIntent();
        thaotac = intent.getStringExtra("thaotac_key");
        if(thaotac.equals("Category")){
            id_cate = intent.getStringExtra("id_cate_key");
            name_cate = intent.getStringExtra("name_cate_key");
            namecate.setText(name_cate);
            createData_by_cate();
        }
//        if(thaotac.equals("Search")){
//            value_search = intent.getStringExtra("search");
//            namecate.setText(value_search);
//            createData_by_search();
//        }

        searchAdapter = new SearchAdapter(arrayList,this);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2,LinearLayoutManager.VERTICAL,false));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void createData_by_search() {
        arrayList = new ArrayList<>();
        RequestQueue requestQueue  = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.url_Getsp, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i =0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        if(object.getString("c_id").indexOf(value_search)>=0){
                            arrayList.add(new Product_Search(
                                    object.getInt("D_ID"),
                                    object.getInt("c_id"),
                                    object.getString("FName"),
                                    object.getString("price"),
                                    object.getString("img"),
                                    object.getString("Description")
                            ));
                        }

//                        Toast.makeText(ClassActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                searchAdapter.notifyDataSetChanged();
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

    private void createData_by_cate() {
        arrayList = new ArrayList<>();
        RequestQueue requestQueue  = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.url_Getsp, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i =0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        if(object.getString("c_id").equals(id_cate)){
                            arrayList.add(new Product_Search(
                                    object.getInt("D_ID"),
                                    object.getInt("c_id"),
                                    object.getString("FName"),
                                    object.getString("price"),
                                    object.getString("img"),
                                    object.getString("Description")
                            ));
                        }

//                        Toast.makeText(ClassActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                searchAdapter.notifyDataSetChanged();
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

    private void Anhxa() {
        recyclerView = (RecyclerView)  findViewById(R.id.search);
        namecate = (TextView) findViewById(R.id.editSearch);
        back = (ImageButton) findViewById(R.id.back_search);
    }
}