package com.example.mediclinic;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pat_dashboard extends AppCompatActivity {

    View btn;
    Button join,refresh;
    private RecyclerView recyclerView;
    private ArrayList<Clinic> clinic1;
    private P_ClinicAdapter clinicAdapter;
    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_dashboard);
        refresh=findViewById(R.id.pClinicBtn);
        join = findViewById(R.id.joinBtn1);
        btn = findViewById(R.id.imageView17);
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        clinic1 = new  ArrayList<Clinic>();


        dbRef = FirebaseDatabase.getInstance().getReference().child("Clinic Data");
        dbRef.addListenerForSingleValueEvent(valueEventListener);


        Intent profileIntent = getIntent();

        String DoctorName = profileIntent.getStringExtra("_patientName");
        String DoctorContactNo = profileIntent.getStringExtra("_patientcontactNo");
        String DoctorEmail = profileIntent.getStringExtra("_patientemail");
        String DoctorDOB = profileIntent.getStringExtra("_patientdob");
        String DoctorAddress = profileIntent.getStringExtra("_patientaddress");
        String DoctorGender = profileIntent.getStringExtra("_patientgender");

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pat_dashboard.this,JoinClinicActivity.class);
                startActivity(i);
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pat_dashboard.this, Pat_dashboard.class);
                finish();
                overridePendingTransition(0,0);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Pat_dashboard.this, PatientViewProfile.class);
                in.putExtra("_patientName", DoctorName);
                in.putExtra("_patientcontactNo", DoctorContactNo);
                in.putExtra("_patientemail", DoctorEmail);
                in.putExtra("_patientdob", DoctorDOB);
                in.putExtra("_patientaddress", DoctorAddress);
                in.putExtra("_patientgender", DoctorGender);
                startActivity(in);
            }
        });

    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override

        public void onDataChange(@NonNull DataSnapshot snapshot) {
            clinic1.clear();
            for (DataSnapshot snapshot1: snapshot.getChildren()){
                Clinic uData = snapshot1.getValue(Clinic.class);
                clinic1.add(uData);
            }
            clinicAdapter= new P_ClinicAdapter(Pat_dashboard.this,clinic1);
            recyclerView.setAdapter(clinicAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };



    public void clinicView(View view){
        Toast.makeText(getApplicationContext(), "Showing Clinic...", Toast.LENGTH_SHORT).show();
    }

    public void vieHome(View view){
        Intent in = new Intent(Pat_dashboard.this, app1page.class);
        startActivity(in);
    }
    public void profile(View view){
        Intent in = new Intent(Pat_dashboard.this, PatientViewProfile.class);
        startActivity(in);
    }

    public void alerm(View view){
        Intent in = new Intent(Pat_dashboard.this, reminderPatient.class);
        startActivity(in);
    }
}
