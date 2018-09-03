package com.example.melo.services_startandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnService_92, btnService_93, btnService_94, getBtnService_95s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnService_92 = (Button) findViewById(R.id.btnService_92);
        btnService_93 =  (Button) findViewById(R.id.btnService_93);
        btnService_94 =  (Button) findViewById(R.id.btnService_93);

        btnService_92.setOnClickListener(this);
        btnService_93.setOnClickListener(this);
        btnService_94.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnService_92:
                intent = new Intent(this, Service92.class);
                startActivity(intent);
                break;
            case R.id.btnService_93:
                intent = new Intent(this, Service93_1.class);
                startActivity(intent);
                break;
            case R.id.btnService_94:
                intent = new Intent(this, Service94.class);
                startActivity(intent);
                break;
        }
    }
}