package com.example.ganache;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {
ImageButton back;
Button proceed;
TextView txtcost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        anhxa();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent =getIntent();
        txtcost.setText(intent.getStringExtra("Cost")+"$");
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
                builder.setMessage("You have successfully ordered..")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(view.getContext(),Index.class);
                                startActivity(intent);
                            }
                        })
                        ;
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void anhxa() {
        back = (ImageButton) findViewById(R.id.back_pay);
        proceed = (Button) findViewById(R.id.paymenttt);
        txtcost = (TextView) findViewById(R.id.txt_total_pay);
    }

}