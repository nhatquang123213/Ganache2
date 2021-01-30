package com.example.ganache;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {
    TextView user_name,gmail,address;
    ImageButton btn1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (inflater.inflate(R.layout.fragment_user, container, false));
        anhxa(view);
        user_name.setText(Users.getInstance().getF_name());
        gmail.setText(Users.getInstance().getEmail());
        address.setText(Users.getInstance().getAddress());
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Login.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void anhxa(View view) {
        user_name = (TextView) view.findViewById(R.id.myprofile_name);
        gmail = (TextView) view.findViewById(R.id.myprofile_gmail);
        address = (TextView) view.findViewById(R.id.myprofile_address);
        btn1 = (ImageButton) view.findViewById(R.id.logout);
    }
}
