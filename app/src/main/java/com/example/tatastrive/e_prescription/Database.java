package com.example.tatastrive.e_prescription;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database extends SQLiteOpenHelper
{



        public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);

        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("create table if not exists Registration(_id integer primary key autoincrement,Username text,Password text,CPassword text,PhoneNo text)");

            db.execSQL("create table if not exists Patient_Details(_id integer primary key autoincrement,Name_of_patient text,Contact_no text,Email text,Gender text,Age integer,Weight integer,Disease text)");

            db.execSQL("create table if not exists Prescription(_id integer primary key autoincrement,Form text,Name text,Strength text,Dosage text,Days integer,Instruction text)");

            System.out.println("I was here!");
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {


        }

        public void addInfo(String username,String password,String CPassword,String phoneno)
        {
            ContentValues emptyRow=new ContentValues();
            emptyRow.put("Username",username );
            emptyRow.put("Password", password);
            emptyRow.put("CPassword", CPassword);
            emptyRow.put("PhoneNo", phoneno);





            SQLiteDatabase db=getWritableDatabase();
            db.insert("Registration", null, emptyRow);



        }





    public void addPatientInfo(String Patientname,String contactno,String email,String gender,int age,int weight,String disease)
    {
        ContentValues emptyRow=new ContentValues();
        emptyRow.put("Name_of_patient",Patientname );
        emptyRow.put("Contact_no",contactno );
        emptyRow.put("Email",email);

        emptyRow.put("Gender",gender);
        emptyRow.put("Age",age);
        emptyRow.put("Weight",weight);
        emptyRow.put("Disease", disease);





        SQLiteDatabase db=getWritableDatabase();
        db.insert("Patient_Details", null,emptyRow);

    }


    public void addPrescriptionInfo(String form,String name,String strength,String dosage ,int days,String instruction)
    {
        ContentValues emptyRow=new ContentValues();
        emptyRow.put("Form",form );
        emptyRow.put("Name",name);
        emptyRow.put("Strength",strength);
        emptyRow.put("Dosage",dosage);
        emptyRow.put("Days",days);
        emptyRow.put("Instruction", instruction);


        SQLiteDatabase db=getWritableDatabase();
        db.insert("Prescription", null,emptyRow);

    }



        public void deleteRecord(int id)
        {
            SQLiteDatabase db=getWritableDatabase();
            String[] id1={String.valueOf(id)};
            db.delete("Patient_Details", "_id=?", id1);

        }

    public void deletePrescription(int id)
    {
        SQLiteDatabase db=getWritableDatabase();
        String[] id1={String.valueOf(id)};
        db.delete("Prescription", "_id=?",id1);

    }



        public void updateRecord(int id,String newName,String newemail,int newage,int newweight,String newdisease)
        {

            SQLiteDatabase db=getWritableDatabase();
            ContentValues row=new ContentValues();
            row.put("Name_of_patient", newName);

            row.put("Email", newemail);

            row.put("Age", newage);
            row.put("Weight", newweight);
            row.put("Disease", newdisease);

            String abc[]={id+""};

            db.update("Patient_Details", row, "_id=?", abc);
        }




        public Cursor readInfo()
        {
            SQLiteDatabase db=getReadableDatabase();

            Cursor c=db.query("Registration",null, null,null , null, null, null);

            c.moveToFirst();

            return c;

        }



    public Cursor check(String username, String passWord)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Registration where Username=? and Password=?", new String[]{username, passWord});
        return c;
    }


public Cursor viewPatientinfo()
{
	SQLiteDatabase db=getReadableDatabase();

	Cursor c=db.query("Patient_Details",null, null, null, null, null, null);
	c.moveToFirst();

    for(int i=0;i<c.getCount();i++)
    {
        System.out.println("id=" + c.getInt(0) + "pname=" + c.getString(1)+"contact_no="+c.getInt(2)+"gender="+c.getString(3)+"Age="+c.getInt(4)+"weight="+c.getInt(5)+"disease"+c.getString(6));
        c.moveToNext();
    }
    c.moveToFirst();
	return c;

}

    public Cursor ReadPrescInfo()
    {
        SQLiteDatabase db=getReadableDatabase();

        Cursor c=db.query("Prescription",null, null, null, null, null, null);
        c.moveToFirst();

        for(int i=0;i<c.getCount();i++)
        {
            System.out.println("id=" + c.getInt(0) + "form=" + c.getString(1)+"name="+c.getInt(2)+"strength="+c.getString(3)+"dosage="+c.getInt(4)+"days="+c.getInt(5)+"instructions="+c.getString(6));
            c.moveToNext();
        }
        c.moveToFirst();
        return c;

    }




    public void updatePrescription(int id,String form1,String name,String strength1,String dosage1,int days1,String instructions1)
    {

        SQLiteDatabase db=getWritableDatabase();
        ContentValues row=new ContentValues();
        row.put("Form", form1);
        row.put("Name", name);
        row.put("Strength", strength1);
        row.put("Dosage", dosage1);
        row.put("Days", days1);
        row.put("Instruction", instructions1);

        String lmn[]={id+""};

        db.update("Prescription", row, "_id=?", lmn);
    }



    public Cursor getPatientId(int id)
    {
        SQLiteDatabase db=getReadableDatabase();
        String abc[]={id+""};
        Cursor c=db.query("Patient_Details", null, "_id=?",abc, null, null, null);


        c.moveToFirst();
        return c;
    }


    public Cursor getPrescriptionId(int id)
    {
        SQLiteDatabase db=getReadableDatabase();
        String abc[]={id+""};
        Cursor c=db.query("Prescription", null, "_id=?",abc, null, null, null);


        c.moveToFirst();
        return c;
    }

//this is change

}



