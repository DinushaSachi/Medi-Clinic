package com.example.mediclinic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateClinicActivity extends AppCompatActivity {
    EditText clinicName,dName,hospName,date,desc;
    Button add;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_clinic);
        clinicName = findViewById(R.id.cCname);
        dName = findViewById(R.id.cDname);
        hospName = findViewById(R.id.cHosName);
        date = findViewById(R.id.cDate);
        desc = findViewById(R.id.cDesc);
        add = findViewById(R.id.saveBtn);

        database = FirebaseDatabase.getInstance().getReference().child("Clinic Data");
        // add
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  userId ,clinic,doctor,hosp,dTime,descript;

                userId = database.push().getKey();
                clinic = clinicName.getText().toString();
                doctor = dName.getText().toString();
                hosp =  hospName.getText().toString();
                dTime =  date.getText().toString();
                descript = desc.getText().toString();

                // validation
                if(clinic.equals("")){
                    Toast.makeText(com.example.mediclinic.CreateClinicActivity.this, "User Name Required!", Toast.LENGTH_SHORT).show();
                }
                else if(doctor.equals("")){
                    Toast.makeText(com.example.mediclinic.CreateClinicActivity.this, "User SureName Required!", Toast.LENGTH_SHORT).show();
                }
                else if(hosp.equals("")){
                    Toast.makeText(com.example.mediclinic.CreateClinicActivity.this, "Hospital Name Required!", Toast.LENGTH_SHORT).show();
                }
                else if(dTime.equals("")){
                    Toast.makeText(com.example.mediclinic.CreateClinicActivity.this, "Date and time Required!", Toast.LENGTH_SHORT).show();
                }
                else if(descript.equals("")){
                    Toast.makeText(com.example.mediclinic.CreateClinicActivity.this, "Description Required!", Toast.LENGTH_SHORT).show();
                }
                else{
                    com.example.mediclinic.Clinic clinic1 = new com.example.mediclinic.Clinic(userId,clinic,doctor,hosp,dTime,descript);
                    database.child(userId).setValue(clinic1);
                    Toast.makeText(com.example.mediclinic.CreateClinicActivity.this, "done", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(com.example.mediclinic.CreateClinicActivity.this, com.example.mediclinic.Doc_dashboard.class);
                    startActivity(i);
                }
            }
        });
    }

    public void vieHome(View view){
        Intent in = new Intent(CreateClinicActivity.this, app1page.class);
        startActivity(in);
    }

    public void profile(View view){
        Intent in = new Intent(CreateClinicActivity.this, DoctorViewProfile.class);
        startActivity(in);
    }

}