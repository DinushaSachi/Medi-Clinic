package com.example.mediclinic;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateDoctorProfileActivity extends AppCompatActivity {

    EditText docName, Gender, ContactNo, email, DOB, Address, Password;
    Button create;
    TextView loginbtn;
    ProgressDialog loadingBar;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_doctor_profile);


        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container),true);
        fade.excludeTarget(android.R.id.statusBarBackground,true);
        fade.excludeTarget(android.R.id.navigationBarBackground,true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        docName = findViewById(R.id.docCreateName);
        Gender = findViewById(R.id.docCreateGender);
        ContactNo = findViewById(R.id.docCreatePhone);
        email = findViewById(R.id.docCreateEmail);
        DOB = findViewById(R.id.docCreateBirth);
        Address = findViewById(R.id.docCreateAddress);
        Password = findViewById(R.id.docCreatePass);
        create = findViewById(R.id.create_btn);
        loginbtn = findViewById(R.id.loginbtn);
        loadingBar = new ProgressDialog(CreateDoctorProfileActivity.this);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String docN = docName.getText().toString().trim();
                String ContNo = ContactNo.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String dob = DOB.getText().toString().trim();
                String address = Address.getText().toString().trim();
                String gender = Gender.getText().toString().trim();
                String password = Password.getText().toString().trim();


                if (TextUtils.isEmpty(docN)) {
                    Toast.makeText(CreateDoctorProfileActivity.this, "Please Enter your user name", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(ContNo)) {
                    Toast.makeText(CreateDoctorProfileActivity.this, "Please Enter your phone number", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(CreateDoctorProfileActivity.this, "Please Enter your email", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(dob)) {
                    Toast.makeText(CreateDoctorProfileActivity.this, "Please Enter your date of birth", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(address)) {
                    Toast.makeText(CreateDoctorProfileActivity.this, "Please Enter your Address", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(gender)) {
                    Toast.makeText(CreateDoctorProfileActivity.this, "Please Enter your gender", Toast.LENGTH_LONG).show();
                } else if  (TextUtils.isEmpty(password)){
                    Toast.makeText(CreateDoctorProfileActivity.this, "Please Enter your password", Toast.LENGTH_LONG).show();
                }else if (password.length() < 8) {
                    Password.setError("Password should have minimum 8 characters");
                    return;
                } else if (ContNo.length() < 10) {
                    ContactNo.setError("Contact number should have 10 numbers");
                    return;
                } else if (ContNo.length() > 10) {
                    ContactNo.setError("Contact number cannot have more than 10 numbers");
                    return;


                }else {



                    loadingBar.setTitle("Create Account");
                    loadingBar.setMessage("Please wait, while we are checking the account");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("Doctor");

                    Doctor doctor = new Doctor(docN, ContNo, gender, dob, address, password, Email);
                    ref.child(docN).setValue(doctor);

                    docName.setText("");
                    Gender.setText("");
                    ContactNo.setText("");
                    email.setText("");
                    DOB.setText("");
                    Address.setText("");
                    Password.setText("");
                    Toast.makeText(CreateDoctorProfileActivity.this, "Doctor Profile Created!", Toast.LENGTH_LONG).show();

                    Intent in = new Intent(CreateDoctorProfileActivity.this, signDoctor.class);
                    startActivity(in);

                }

            }
        });

    }

    public void vieSign(View view){
        Intent in = new Intent(CreateDoctorProfileActivity.this, signDoctor.class);
        startActivity(in);
    }
    public void vieHome(View view){
        Intent in = new Intent(CreateDoctorProfileActivity.this, app1page.class);
        startActivity(in);
    }
}