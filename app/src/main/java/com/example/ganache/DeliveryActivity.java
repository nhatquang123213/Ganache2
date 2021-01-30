package com.example.ganache;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class DeliveryActivity extends AppCompatActivity {
    ImageButton back;
    Button proced;
    TextView cost,name,mail,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        anhxa();
        name.setText(Users.getInstance().getF_name()+" "+Users.getInstance().getL_name());
        mail.setText(Users.getInstance().getEmail());
        address.setText(Users.getInstance().getAddress());
        Intent intent = getIntent();
        cost.setText(intent.getStringExtra("Cost"));
        proced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DeliveryActivity.this,PaymentActivity.class);
                intent1.putExtra("Cost",intent.getStringExtra("Cost"));
                startActivity(intent1);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhxa() {
        back = (ImageButton) findViewById(R.id.back_deli);
        proced = (Button) findViewById(R.id.proceed_deli);
        cost = (TextView) findViewById(R.id.txt_total_deli);
        name = (TextView) findViewById(R.id.deli_name);
        mail = (TextView) findViewById(R.id.deli_mail);
        address = (TextView) findViewById(R.id.deli_add);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_pirates:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radio_ninjas:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }
}