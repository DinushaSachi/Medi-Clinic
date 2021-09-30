package com.example.mediclinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class JoinClinicActivity extends AppCompatActivity {
    Button join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_clinic);
        join = findViewById(R.id.joinBtn);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.example.mediclinic.JoinClinicActivity.this, com.example.mediclinic.Pat_dashboard.class);
                startActivity(i);
            }
        });
    }
}
