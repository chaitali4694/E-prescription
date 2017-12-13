package com.example.tatastrive.e_prescription;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username, password;
    TextView spinner, register, fpass;

    Button loginbtn;
    Database data;
    Cursor c;
    String username1;
    String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        register = (TextView) findViewById(R.id.txtregitration);


        loginbtn = (Button) findViewById(R.id.button);
        data = new Database(this, "Prescription", null, 100);


        register.setOnClickListener(this);
        loginbtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String uname = username.getText().toString();
        String pass1 = password.getText().toString();


        /* if (uname.equals("") && pass1.equals("") || uname.equals(" ") && pass.equals(" ")) {
            Toast.makeText(MainActivity.this, "Please Enter Username and Password", Toast.LENGTH_LONG).show();

        }*/


          if (v.getId() == R.id.txtregitration) {
            Intent i = new Intent(MainActivity.this, DoctorRegistration.class);
            startActivity(i);


        }

        else if (v.getId() == R.id.button)

        {
            data.readInfo();

            if (v.getId() == R.id.button) {
                c = data.check(username.getText().toString(), password.getText().toString());

                if (c.getCount() >= 1) {
                    Intent i = new Intent(MainActivity.this, PatientDetails.class);
                    startActivity(i);
                    Toast.makeText(this, "Login Successful..!!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Enter correct credentials", Toast.LENGTH_LONG).show();
                }



            }
        }

    }
}
