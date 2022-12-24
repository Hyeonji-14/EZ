package com.example.hongstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GongJi extends AppCompatActivity {
    Button gong1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sahang);

        gong1 = (Button) findViewById(R.id.gong1);

        gong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( GongJi.this, Gong1.class);
                startActivity(intent);
            }

        });
    }
}
