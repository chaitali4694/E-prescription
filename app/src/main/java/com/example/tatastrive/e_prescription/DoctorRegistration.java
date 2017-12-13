package com.example.tatastrive.e_prescription;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorRegistration extends AppCompatActivity implements View.OnClickListener {
    EditText DUsername, DPassword, CPassword, Phone;
    Button submitbtn;
    Database data;
    Cursor c;
    String pass1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);
        DUsername = (EditText) findViewById(R.id.Dusername);
        DPassword = (EditText) findViewById(R.id.Dpassword);
        CPassword = (EditText) findViewById(R.id.ConfirmPass);
        Phone = (EditText) findViewById(R.id.DPhone);
        submitbtn = (Button) findViewById(R.id.Submit);
        data = new Database(this, "Prescription", null, 100);
        submitbtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        {


            String name = DUsername.getText().toString();
            String pass = DPassword.getText().toString();
            String Cpass = CPassword.getText().toString();
            String phone = Phone.getText().toString();



            if (name.equals("") || Cpass.equals("") || pass.equals("") || phone.equals("")) {
                Toast.makeText(DoctorRegistration.this, "Fill the fields", Toast.LENGTH_LONG).show();

            }
            else if(pass.equals(Cpass))
            {
                data.addInfo(name, pass, Cpass, phone);

                DUsername.setText("");
                DPassword.setText("");
                CPassword.setText("");
                Phone.setText("");


                Toast.makeText(this, "Registration successful..Please Login to continue..!!", Toast.LENGTH_LONG).show();


                data.readInfo();


                Intent i1 = new Intent(this, MainActivity.class);

                startActivity(i1);


            }
        else
                Toast.makeText(DoctorRegistration.this,"Enter the same password",Toast.LENGTH_LONG).show();


        }

    }
    }
