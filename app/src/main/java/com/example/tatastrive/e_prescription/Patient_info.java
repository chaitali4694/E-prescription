package com.example.tatastrive.e_prescription;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Patient_info extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener{
    ListView lv;
    EditText e1,e2,e3,e4,e5,e6;
    Button editbtn,cancelbtn;
    MyCursorAdapter CurAdp;
    Cursor c;
    Database data;
    int selectedPatientId;
    View xyz;
    AlertDialog abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);



        lv= (ListView) findViewById(R.id.PatientInfoList);
        data = new Database(this, "Prescription", null, 100);
        c=data.viewPatientinfo();

        CurAdp=new MyCursorAdapter(this,c);
        lv.setAdapter(CurAdp);
        lv.setOnItemClickListener(this);
        registerForContextMenu(lv);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Edit Record");
        menu.add("Delete Record");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        if(item.getTitle().toString().equals("Delete Record")){
            //AdapterContextMenuInfo info=(AdapterContextMenuInfo) item.getMenuInfo();

            //int pos=info.position;
            data.deleteRecord(c.getInt(0));
            Toast.makeText(this, c.getString(1) + "is deleted succesfully!!", Toast.LENGTH_LONG).show();

            c=data.viewPatientinfo();

            CurAdp=new MyCursorAdapter(this,c);
            lv.setAdapter(CurAdp);

        }
        else if(item.getTitle().toString().equals("Edit Record"))
        {



            AlertDialog.Builder a=new AlertDialog.Builder(this);
            a.setTitle("Update Information");
           xyz=getLayoutInflater().inflate(R.layout.update_dialog,null);


            e1= (EditText) xyz.findViewById(R.id.Ename);
            e2= (EditText) xyz.findViewById(R.id.EMailID);
            e3= (EditText) xyz.findViewById(R.id.EAge);
            e4= (EditText) xyz.findViewById(R.id.Eweight);
            e5= (EditText) xyz.findViewById(R.id.Edisease);

            editbtn= (Button) xyz.findViewById(R.id.EditBtn);
            cancelbtn= (Button) xyz.findViewById(R.id.Cancelbtn);


            editbtn.setOnClickListener(this);
            cancelbtn.setOnClickListener(this);

            a.setView(xyz);



            abc= a.create();
            abc.show();

           selectedPatientId = c.getInt(0);
            Cursor record=data.getPatientId(selectedPatientId);
            record.moveToFirst();

            e1.setText(record.getString(1));
            e2.setText(record.getString(3));
            e3.setText(record.getString(5));
            e4.setText(record.getString(6));
            e5.setText(record.getString(7));

            String pname=e1.getText().toString();

            String email= e2.getText().toString();
            int weight= Integer.parseInt(e4.getText().toString());
            int age= Integer.parseInt(e3.getText().toString());
            String disease=e5.getText().toString();





        }


        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {

        if(v.getId()==R.id.EditBtn)
        {


            data.updateRecord(selectedPatientId,e1.getText().toString(),e2.getText().toString(), Integer.parseInt(e4.getText().toString()), Integer.parseInt(e3.getText().toString()),e5.getText().toString());

            c=data.viewPatientinfo();
            CurAdp=new MyCursorAdapter(this,c);
            lv.setAdapter(CurAdp);

            abc.dismiss();

        }
        else
        {
            abc.dismiss();


        }

    }


    class MyCursorAdapter extends CursorAdapter
    {


        public MyCursorAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent)
        {
            LayoutInflater inflater=LayoutInflater.from(context);
            View OneRow=inflater.inflate(R.layout.textviewpatient,parent,false);
            return OneRow;

        }

        @Override
        public void bindView(View v, Context context, Cursor cursor)
        {


            TextView t= (TextView)v.findViewById(R.id.Listtextview1);

            String name=cursor.getString(cursor.getColumnIndex("Name_of_patient"));

            System.out.println("*************................."+name);

            t.setText(""+name);

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent i=new Intent(this,Display_Info.class );
        selectedPatientId=c.getInt(0);
        System.out.println(selectedPatientId);


        i.putExtra("id",selectedPatientId+"");


        startActivity(i);


    }
}
