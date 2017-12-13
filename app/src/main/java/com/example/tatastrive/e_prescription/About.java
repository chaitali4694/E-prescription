package com.example.tatastrive.e_prescription;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {
TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        t1= (TextView) findViewById(R.id.AboutText1);
     String s=new String("eRx  Prescription  is a computer based electronic generation and filling of a medical" +
             " prescription by replacing the papers .eRx Prescription allows a physician to write digital " +
             "prescription and give a digital copy to the patient. It gives the ability to send error free, " +
             "accurate and understandable prescriptions from the healthcare provider to the pharmacy through patient. " +
             "eRx Presciption reduces the risks associated with traditional prescription script writing.  \n" +
             "Here physician can be able to register to the system. Physician can be able to list all patients list and " +
             "can capture patients problems and prescribe accordingly. App is  " +
             "easy to use." +
             "this is changed \n");

        t1.setText(s);


    }
}
