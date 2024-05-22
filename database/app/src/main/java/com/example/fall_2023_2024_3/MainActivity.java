package com.example.fall_2023_2024_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLData;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase   db=null;
    private Cursor   rs=null;
    private Random  rand=null;

    private EditText edit=null;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.close)
            onBackPressed();
        else if ( item.getItemId()==R.id.createDB) {
            db = openOrCreateDatabase("testtest.db", MODE_PRIVATE, null);

            db.execSQL("create table if not exists emp (id integer primary key , name text, sex char ,BaseSalary Float  , TotalSales Float,CommissionRate Float , NetSalary Float)");
        }
        else if (item.getItemId()==R.id.insert)
        {
            int id= rand.nextInt(1000);
            int BaseSalary = rand.nextInt(50);
            int TotalSales = rand.nextInt(40);
            int CommissionRate = rand.nextInt(40);
            int NetSalary = rand.nextInt(40);
            String name= "maha"+rand.nextInt(50);
            String sex= "female"+rand.nextInt(100);

            rs=db.rawQuery("select * from emp where id="+id,null);

            if ( rs.moveToNext()==false)
            {
                Log.i("home" , " the record with id ="+id +" does not exist");

                db.execSQL("insert into emp values(?,?,?,?,?,?,?);",new Object[]{id,name,sex,BaseSalary,TotalSales,CommissionRate,NetSalary});

            }
            else{

                Log.i("home" , " the record with id ="+id +"exists");
            }

        }
        else if ( item.getItemId()==R.id.delete)
        {
            String S = edit.getText().toString();
            if ( !S.isEmpty()){
                int id=Integer.parseInt(S);
                db.execSQL("delete from emp where id=?;",new Object[]{id});

            }


        }
        else if ( item.getItemId()==R.id.search)
        {
            String S = edit.getText().toString();
            if ( !S.isEmpty()){
                int id=Integer.parseInt(S);
                rs = db.rawQuery("select * from emp where id=?", new String[]{S});
                if (rs.moveToNext()) {
                    Log.i("home", "the record with id=" + id + " exists and this is the record " +
                            rs.getInt(0) + " " +
                            rs.getString(1) + " " +
                            rs.getString(2) + " " +
                            rs.getFloat(3) + " " +
                            rs.getFloat(4) + " " +
                            rs.getFloat(5) + " " +
                            rs.getFloat(6));
                }
                else
                    Log.i("home", "the record with id=" + id + " does not exists");

            }
            }
        else if (item.getItemId() == R.id.display) {
            rs = db.rawQuery("select * from emp", null);
            if (rs.moveToFirst()) {
                do {
                    Log.i("home", "the record exists and its data is " +
                            rs.getInt(0) + " " +
                            rs.getString(1) + " " +
                            rs.getString(2) + " " +
                            rs.getFloat(3) + " " +
                            rs.getFloat(4) + " " +
                            rs.getFloat(5) + " " +
                            rs.getFloat(6));
                } while (rs.moveToNext());
            }
        }





        else if (item.getItemId() == R.id.update) {
            String S = edit.getText().toString();
            if (!S.isEmpty()) {
                int id = Integer.parseInt(S);
                rs = db.rawQuery("select * from emp where id=?", new String[]{S});
                if (rs.moveToNext()) {
                    Toast.makeText(this, "The record with id=" + id + " does exist", Toast.LENGTH_LONG).show();

                    String updateQuery = "update emp set name='suad" + rand.nextInt(500) + "', " +
                            "sex='male" + rand.nextInt(500) + "', BaseSalary=" +
                            rand.nextInt(500) + ", TotalSales=" + rand.nextInt(500) +
                            ", CommissionRate=" + rand.nextInt(500) + ", NetSalary=" +
                            rand.nextInt(500) + " where id=" + id;

                    try {
                        db.execSQL(updateQuery);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "The record with id =" + id + " does not exist", Toast.LENGTH_LONG).show();
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {

       onBackPressed();
       return super.onNavigateUp();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if ( menu instanceof MenuBuilder)
            ((MenuBuilder)menu).setOptionalIconsVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        menu.findItem(R.id.insert).setEnabled(db!=null);
        menu.findItem(R.id.delete).setEnabled(db!=null);
        menu.findItem(R.id.update).setEnabled(db!=null);
       /* menu.findItem(R.id.search).setEnabled(db!=null);
        menu.findItem(R.id.display).setEnabled(db!=null);*/
        return super.onMenuOpened(featureId, menu);
    }

    private ActionBar  bar=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("home", "onCreagte is called");
        setContentView(R.layout.activity_main);

         bar=getSupportActionBar();
        Log.i("Hello", " teh bar-->>"+ bar);
        Log.i("Hello", " teh bar-->>" + bar);
        bar.setDisplayHomeAsUpEnabled(true);
        rand= new Random();

        edit= findViewById(R.id.edit);



    }


}