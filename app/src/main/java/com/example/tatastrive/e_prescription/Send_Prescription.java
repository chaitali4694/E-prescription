package com.example.tatastrive.e_prescription;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Send_Prescription extends AppCompatActivity implements View.OnClickListener {

    Database data;
    Button sendbtn;

    EditText to, sub, msg;
    private String emailRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send__prescription);
        to = (EditText) findViewById(R.id.editTextEmailTo);
        sub = (EditText) findViewById(R.id.editTextEmailSubject);
        msg = (EditText) findViewById(R.id.editTextEmailContent);
        sendbtn = (Button) findViewById(R.id.buttonSend);
        sendbtn.setOnClickListener(this);

        data = new Database(this, "Prescription", null, 100);

        Intent i = getIntent();

        System.out.println(i.getStringExtra("id"));
        String id1 = i.getStringExtra("id");
        int value = Integer.parseInt(id1);
        int value1=Integer.parseInt(id1);
        Cursor rec = data.getPrescriptionId(value);
        Cursor rec1 = data.getPatientId(value1);


        rec.moveToFirst();
        to.setText(rec1.getString(3));
        msg.setText("Form of Medicine:" + rec.getString(1) + "\n\n" + "Name of Medicine:" + rec.getString(2) + "\n\n" + "Strength:" + rec.getString(3) + "\n\n"
                + "Dosage:" + rec.getString(4) + "\n\n" + "Days:" + rec.getString(5) + "\n\n" + "Instructions(if any):" + rec.getString(6));




    }

    @Override
    public void onClick(View v)

    {


        String emailTo = to.getText().toString();
        String emailSubject = sub.getText().toString();
        String emailContent = msg.getText().toString();



        if(!emailTo.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
        {

            Toast.makeText(Send_Prescription.this,"Enter Correct Mail id",Toast.LENGTH_LONG).show();

        }

        else {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailTo});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, emailContent);

            emailIntent.setType("message/rfc822");

            startActivity(Intent.createChooser(emailIntent, "Select an Email Client:"));

        }
    }
}