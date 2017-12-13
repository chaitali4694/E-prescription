package com.example.tatastrive.e_prescription;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PatientDetails extends AppCompatActivity implements View.OnClickListener {
    EditText Pname,PhnNo,Weight,Age,Disease,email;
    TextView Pname1,PhnNo1,Weight1,Age1,Disease1,gender;
    Spinner Psp;
    Button Psubmit,edit;
    Database data;
    String selectedPatientId;
    Cursor c;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        Pname= (EditText) findViewById(R.id.Patientname);
        PhnNo= (EditText) findViewById(R.id.PatientContact);
        Weight= (EditText) findViewById(R.id.PatientWeight);
        Age= (EditText) findViewById(R.id.PatientAge);
        Disease= (EditText) findViewById(R.id.patientDisease);
        email= (EditText) findViewById(R.id.PatientEmail1);




        data = new Database(this, "Prescription", null, 100);

        Psp= (Spinner) findViewById(R.id.spinner2);
        Psubmit= (Button) findViewById(R.id.Psubmit);

        Psubmit.setOnClickListener(this);
//        edit.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("View Info");


        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getTitle().toString().equals("View Info"))
        {
            Intent i=new Intent(this,Patient_info.class);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onClick(View v)
    {
        String pname=Pname.getText().toString();
        String phnno= PhnNo.getText().toString();
        String email1=email.getText().toString();
        int weight=Integer.parseInt(Weight.getText().toString());
        int age=Integer.parseInt(Age.getText().toString());
        String disease=Disease.getText().toString();
        String gender= Psp.getSelectedItem().toString();


       if(pname.isEmpty()||phnno.isEmpty()||email1.isEmpty()||disease.isEmpty()||Weight.length()==0||Age.length()==0)
       {
           Toast.makeText(PatientDetails.this,"Enter Details",Toast.LENGTH_LONG).show();

       }

        else if(!email1.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
        {
            Toast.makeText(PatientDetails.this,"Enter correct email  Details",Toast.LENGTH_LONG).show();
        }
        else
        {
            data.addPatientInfo(pname, phnno, email1, gender, age, weight, disease);

            Pname.setText("");
            PhnNo.setText("");
            email.setText("");
            Weight.setText("");
            Age.setText("");
            Disease.setText("");




            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();

            data.viewPatientinfo();

        }

    }
}
