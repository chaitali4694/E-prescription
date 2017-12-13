package com.example.tatastrive.e_prescription;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display_Info extends AppCompatActivity implements View.OnClickListener {
    TextView t1,t2,t3,t4,t5,t6,t7;
    Database data;
    Button prescriptionbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__info);
        t1= (TextView) findViewById(R.id.Ptxtview1);
        t2= (TextView) findViewById(R.id.Ptxtview2);

        t3= (TextView) findViewById(R.id.Ptxtview3);
        t4= (TextView) findViewById(R.id.Ptxtview4);
        t5= (TextView) findViewById(R.id.Ptxtview5);
        t6= (TextView) findViewById(R.id.Ptxtview6);
        t7= (TextView) findViewById(R.id.Ptxtview7);

        prescriptionbtn= (Button) findViewById(R.id.Prescriptionbtn);
        prescriptionbtn.setOnClickListener(this);


        data = new Database(this, "Prescription", null, 100);

        Intent i=getIntent();

        System.out.println(i.getStringExtra("id"));
        String id1= i.getStringExtra("id");
        int value=Integer.parseInt(id1);
        Cursor rec=data.getPatientId(value);
        rec.moveToFirst();
        t1.setText(rec.getString(1));
        t2.setText(rec.getString(2));
        t7.setText(rec.getString(3));
        t3.setText(rec.getString(4));
        t4.setText(rec.getString(5));
        t5.setText(rec.getString(6));
        t6.setText(rec.getString(7));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("View Existing Prescription");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent i=new Intent(Display_Info.this,PrescriptionList.class);
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        Intent i=new Intent(Display_Info.this,Prescription.class);
        startActivity(i);
    }
}
