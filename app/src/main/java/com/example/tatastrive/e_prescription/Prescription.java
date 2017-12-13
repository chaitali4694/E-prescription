package com.example.tatastrive.e_prescription;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Prescription extends AppCompatActivity implements View.OnClickListener{

    EditText form,name,strength,dosage,days,instruction;
    TextView Tform,Tname,Tstrength,Tdosage,Tdays,Tinstruction;
    Button Createbtn;
    Database data;

    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);
        form= (EditText) findViewById(R.id.Eform);
        name= (EditText) findViewById(R.id.EMname);
        strength= (EditText) findViewById(R.id.EMstrength);
        dosage= (EditText) findViewById(R.id.EMDosage);
        days= (EditText) findViewById(R.id.EMdays);
        instruction= (EditText) findViewById(R.id.EmInstructions);

        Tform= (TextView) findViewById(R.id.MediForm);
        Tname= (TextView) findViewById(R.id.MediName);
        Tstrength= (TextView) findViewById(R.id.MediStrength);
        Tdosage= (TextView) findViewById(R.id.MediDosage);
        Tdays= (TextView) findViewById(R.id.MediDays);
        Tinstruction= (TextView) findViewById(R.id.MediInstruction);

        Createbtn= (Button) findViewById(R.id.GPresc);
        Createbtn.setOnClickListener(this);

        data = new Database(this, "Prescription", null, 100);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("View Prescription");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getTitle().toString().equals("View Prescription"))
        {
            Intent i=new Intent(this,PrescriptionList.class);
            startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {

        String Form=form.getText().toString();
        String Name=name.getText().toString();
        String Strength=strength.getText().toString();
        String Dosage=dosage.getText().toString();
        int Days= Integer.parseInt(days.getText().toString());
        String Instruction=instruction.getText().toString();


        if(Form.equals("")||Name.equals("")||Strength.equals("")||Dosage.equals("")||Instruction.equals(""))
        {
            Toast.makeText(Prescription.this,"Enter Details",Toast.LENGTH_LONG).show();

        }

        System.out.println(Form + Name + Strength + Dosage + Days + Instruction);
        
        data.addPrescriptionInfo(Form, Name, Strength, Dosage, Days, Instruction);

        form.setText("");
        name.setText("");
        strength.setText("");
        dosage.setText("");
        days.setText("");
        instruction.setText("");


        Toast.makeText(this, "Prescription created successfully", Toast.LENGTH_LONG).show();

        data.ReadPrescInfo();



    }
}
