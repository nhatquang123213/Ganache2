package com.example.ganache;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class HomeFragment  extends Fragment {
    RecyclerView recyclerView,rc_cate;
    ArrayList<SanPham> arrayList_bestsell;
    ArrayList<Category> arrayList_cate;
    CategoryAdapter adapter_cate;
    BestSellAdapter adapter_bestsell;
    EditText edittext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (inflater.inflate(R.layout.fragment_home, container, false));
        Anhxa(view);
        createData();
        adapter_bestsell = new BestSellAdapter(arrayList_bestsell,getContext());
        recyclerView.setAdapter(adapter_bestsell);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        createData_cate();
        adapter_cate = new CategoryAdapter(arrayList_cate,getContext());
        rc_cate.setAdapter(adapter_cate);
        rc_cate.setLayoutManager(new GridLayoutManager(getActivity(), 2,LinearLayoutManager.HORIZONTAL,false));
//        edittext = (EditText) view.findViewById(R.id.edt_search);
//        edittext.setOnKeyListener(new View.OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                // If the event is a key-down event on the "enter" button
//                if (
//                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    // Perform action on key press
//                    Toast.makeText(getContext(), edittext.getText(), Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getContext(),SearchActivity.class);
//                    intent.putExtra("search",edittext.getText());
//                    intent.putExtra("thaotac_key","Search");
//                    getContext().startActivity(intent);
//                    return true;
//                }
//                return false;
//            }
//        });
        return view;
    }

    private void createData_cate() {
        arrayList_cate = new ArrayList<>();
        RequestQueue requestQueue  = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.url_Cate, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i =0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                            arrayList_cate.add(new Category(
                                    object.getInt("c_id"),
                                    object.getString("c_name"),
                                    object.getString("img_cate"),
                                    object.getString("detail_cate")
                            ));

//                        Toast.makeText(ClassActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter_cate.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void Anhxa(View view) {
        recyclerView = (RecyclerView)  view.findViewById(R.id.rc_bestsell);
        rc_cate = (RecyclerView) view.findViewById(R.id.rc_cate);

    }
    private void createData() {
        arrayList_bestsell = new ArrayList<>();
        RequestQueue requestQueue  = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.url_Getsp, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i =0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        if(object.getString("bestsell").trim().equals("true")){
                            arrayList_bestsell.add(new SanPham(
                                    object.getInt("D_ID"),
                                    object.getInt("c_id"),
                                    object.getString("FName"),
                                    object.getString("Description"),
                                    object.getString("price"),
                                    object.getString("img"),
                                    object.getString("bestsell")
                            ));
                        }

//                        Toast.makeText(ClassActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter_bestsell.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }
}
