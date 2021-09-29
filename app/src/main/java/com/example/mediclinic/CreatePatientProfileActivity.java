package com.example.mediclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreatePatientProfileActivity extends AppCompatActivity {

    EditText patientName, Address, ContactNo, DOB, email, Password, Gender;
    Button createpatient;
    TextView loginbtn;
    ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient_profile);

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container),true);
        fade.excludeTarget(android.R.id.statusBarBackground,true);
        fade.excludeTarget(android.R.id.navigationBarBackground,true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);


        patientName = findViewById(R.id.patientName);
        Gender = findViewById(R.id.patientGender);
        ContactNo = findViewById(R.id.patientNumber);
        email = findViewById(R.id.patientEmail);
        DOB = findViewById(R.id.patientDOB);
        Address = findViewById(R.id.patientAddress);
        Password = findViewById(R.id.patientPassword);
        createpatient = findViewById(R.id.patientProfileCreate);
        loadingBar = new ProgressDialog(CreatePatientProfileActivity.this);

        createpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientN = patientName.getText().toString().trim();
                String ContNo = ContactNo.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String dob = DOB.getText().toString().trim();
                String address = Address.getText().toString().trim();
                String gender = Gender.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (TextUtils.isEmpty(patientN)) {
                    Toast.makeText(CreatePatientProfileActivity.this, "Please Enter user name", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(ContNo)) {
                    Toast.makeText(CreatePatientProfileActivity.this, "Please Enter your phone number", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(CreatePatientProfileActivity.this, "Please Enter your email", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(dob)) {
                    Toast.makeText(CreatePatientProfileActivity.this, "Please Enter your date of birth", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(address)) {
                    Toast.makeText(CreatePatientProfileActivity.this, "Please Enter your Address", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(gender)) {
                    Toast.makeText(CreatePatientProfileActivity.this, "Please Enter your gender", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(CreatePatientProfileActivity.this, "Please Enter your password", Toast.LENGTH_LONG).show();
                } else if (password.length() < 8) {
                    Password.setError("Password should have minimum 8 characters");
                    return;
                } else if (ContNo.length() < 10) {
                    ContactNo.setError("Contact number should have 10 numbers");
                    return;
                } else if (ContNo.length() > 10) {
                    ContactNo.setError("Contact number cannot have more than 10 numbers");
                    return;


                } else {

                    loadingBar.setTitle("Create Account");
                    loadingBar.setMessage("Please wait, while we are checking the account");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("Patient");
                    Patient patient = new Patient(patientN, address, ContNo, dob, Email, password, gender);
                    ref.child(patientN).setValue(patient);

                    patientName.setText("");
                    Gender.setText("");
                    ContactNo.setText("");
                    email.setText("");
                    DOB.setText("");
                    Address.setText("");
                    Password.setText("");
                    Toast.makeText(CreatePatientProfileActivity.this, "Patient Profile Created!", Toast.LENGTH_LONG).show();

                    Intent in = new Intent(CreatePatientProfileActivity.this, signPatient.class);
                    startActivity(in);
                }
            }
        });
    }

    public void vieHome(View view){
        Intent in = new Intent(CreatePatientProfileActivity.this, app1page.class);
        startActivity(in);
    }

    public void vieSign(View view){
        Intent in = new Intent(CreatePatientProfileActivity.this, signPatient.class);
        startActivity(in);
    }


