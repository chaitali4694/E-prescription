package com.example.tatastrive.e_prescription;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PrescriptionList extends AppCompatActivity implements View.OnClickListener {

    ListView list;
    Database data;
    Cursor c;
    EditText e1, e2, e3, e4, e5, e6;
    MyCursorAdapter CurAdp;
    View xyz;
    Button editbtn, cancelbtn;
    AlertDialog abc;
    int selectedPrescriptionId;

    String form;
    String name;
    String strength;
    String dosage;
    int days;
    String instruction;

    TextView t1, t2, t3, t4, t5, t6, tform, tName, tStrength, tDosage, tDays, tInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_list);
        list = (ListView) findViewById(R.id.PrescriptionList);

        data = new Database(this, "Prescription", null, 100);
        c = data.ReadPrescInfo();

        CurAdp = new MyCursorAdapter(this, c);
        list.setAdapter(CurAdp);

        registerForContextMenu(list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Send Prescription");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i = new Intent(this, Send_Prescription.class);
        selectedPrescriptionId = c.getInt(0);
        System.out.println(selectedPrescriptionId);


        i.putExtra("id", selectedPrescriptionId + "");


        startActivity(i);


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Edit Prescription");
        menu.add("Delete Prescription");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().toString().equals("Delete Prescription")) {

            data.deletePrescription(c.getInt(0));
            Toast.makeText(this, c.getString(1) + "   is deleted succesfully!!", Toast.LENGTH_LONG).show();

            c = data.ReadPrescInfo();

            CurAdp = new MyCursorAdapter(this, c);
            list.setAdapter(CurAdp);

        } else if (item.getTitle().toString().equals("Edit Prescription")) {


            AlertDialog.Builder a = new AlertDialog.Builder(this);
            a.setTitle("Update Information");
            xyz = getLayoutInflater().inflate(R.layout.prescription_dialog, null);


            e1 = (EditText) xyz.findViewById(R.id.EMediForm);
            e2 = (EditText) xyz.findViewById(R.id.EMediName);
            e3 = (EditText) xyz.findViewById(R.id.EMediStrength);
            e4 = (EditText) xyz.findViewById(R.id.EMediDosage);
            e5 = (EditText) xyz.findViewById(R.id.EMediDays);
            e6 = (EditText) xyz.findViewById(R.id.EMediInstruction);


            editbtn = (Button) xyz.findViewById(R.id.PrescUpdate);
            cancelbtn = (Button) xyz.findViewById(R.id.PrescCancel);


            editbtn.setOnClickListener(this);
            cancelbtn.setOnClickListener(this);

            a.setView(xyz);


            abc = a.create();
            abc.show();

            selectedPrescriptionId = c.getInt(0);//get 0th column(id)
            Cursor record = data.getPrescriptionId(selectedPrescriptionId);//call getbookid method
            record.moveToFirst();

            e1.setText(record.getString(1));
            e2.setText(record.getString(2));
            e3.setText(record.getString(3));
            e4.setText(record.getString(4));
            e5.setText(record.getString(5));
            e6.setText(record.getString(6));

            form = e1.getText().toString();
            name = e2.getText().toString();
            strength = e3.getText().toString();
            dosage = e4.getText().toString();
            days = Integer.parseInt(e5.getText().toString());
            instruction = e6.getText().toString();



        }


        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (form.equals(null) || name.equals(null) || strength.equals(null) || dosage.equals(null) || e5.equals(null) || instruction.equals(null) )
        {
            Toast.makeText(PrescriptionList.this,"Update information",Toast.LENGTH_LONG).show();

        } else {
            if (v.getId() == R.id.PrescUpdate) {


                data.updatePrescription(selectedPrescriptionId, e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), e4.getText().toString(),
                        Integer.parseInt(e5.getText().toString()), e6.getText().toString());

                c = data.ReadPrescInfo();

                CurAdp = new MyCursorAdapter(this, c);
                list.setAdapter(CurAdp);

                abc.dismiss();


            } else {
                abc.dismiss();


            }
        }

    }


    class MyCursorAdapter extends CursorAdapter {


        public MyCursorAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View OneRow = inflater.inflate(R.layout.presc_textview, parent, false);
            return OneRow;

        }

        @Override
        public void bindView(View v, Context context, Cursor cursor) {


            t1 = (TextView) v.findViewById(R.id.MedicineForm);
            t2 = (TextView) v.findViewById(R.id.MedicineName);
            t3 = (TextView) v.findViewById(R.id.MedicineStrength);
            t4 = (TextView) v.findViewById(R.id.MedicineDosage);
            t5 = (TextView) v.findViewById(R.id.MedicineDays);
            t6 = (TextView) v.findViewById(R.id.MedicineInstructn);
            tform = (TextView) v.findViewById(R.id.FormText);
            tName = (TextView) v.findViewById(R.id.NameText);
            tStrength = (TextView) v.findViewById(R.id.StrengthText);
            tDosage = (TextView) v.findViewById(R.id.DosageText);
            tDays = (TextView) v.findViewById(R.id.DaysText);
            tInstructions = (TextView) v.findViewById(R.id.InstructionText);


            String form = cursor.getString(cursor.getColumnIndex("Form"));
            String name = cursor.getString(cursor.getColumnIndex("Name"));
            String strength = cursor.getString(cursor.getColumnIndex("Strength"));
            String dosage = cursor.getString(cursor.getColumnIndex("Dosage"));
            String days = cursor.getString(cursor.getColumnIndex("Days"));
            String instruction = cursor.getString(cursor.getColumnIndex("Instruction"));




            tform.setText("" + form);
            tName.setText("" + name);
            tStrength.setText("" + strength);
            tDosage.setText("" + dosage);
            tDays.setText("" + days);
            tInstructions.setText("" + instruction);


        }
    }


}
