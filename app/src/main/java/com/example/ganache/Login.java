package com.example.ganache;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private ImageButton button,button_login;
    private ImageView donthaveacc;
    private EditText inputEmail, inputPassword;
    private RequestQueue requestQueue;
    private StringRequest request;
//    private Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.edt_Email_login);
        inputPassword = (EditText) findViewById(R.id.edit_Pass_Login);
        requestQueue = Volley.newRequestQueue(this);

        button = findViewById(R.id.back_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backLogin();
            }
        });
        donthaveacc = findViewById(R.id.donthaveacc);
        donthaveacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSignup();
            }
        });
        button_login = (ImageButton) findViewById(R.id.bt_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
//                Intent intent = new Intent(Login.this, Index.class);
//                startActivity(intent);
            }
        });
    }

    public  void backLogin(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    public  void gotoSignup(){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
    private void check() {
        final String email = inputEmail.getText().toString().trim();
        final String pass = inputPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestQueue requestQueue  = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.url_Getuser, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i =0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        if((object.getString("username").equals(email))&&(object.getString("password").equals(pass))) {
                            Users.getInstance().setCNIC(object.getInt("CNIC"));
                            Users.getInstance().setAddress(object.getString("address"));
                            Users.getInstance().setUsername(object.getString("username"));
                            Users.getInstance().setEmail(object.getString("email"));
                            Users.getInstance().setF_name(object.getString("f_name"));
                            Users.getInstance().setL_name(object.getString("l_name"));
                            Users.getInstance().setPassword(object.getString("password"));
                            Users.getInstance().setPhone(object.getString("phone"));
                            Intent intent = new Intent(Login.this, Index.class);
                            startActivity(intent);
                            finish();
                        }
//                        Toast.makeText(ClassActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
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

}