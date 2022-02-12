package com.example.dancedb;

import androidx.appcompat.app.AppCompatActivity;

import com.example.course24db.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id, name, mob, dtype;
    Spinner dp;
    Button saveBtn, lstBtn, showBtn, updateBtn, deleteBtn;
    Intent intent;
    String str;
    DatabaseHandler db;
    String uid, uname, umob, udan;
    String []country={"Hip Hop","Free Style","Manipuri","Bharatanatyam"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);
        id = findViewById(R.id.txtId);
        name = findViewById(R.id.txtName);
        mob = findViewById(R.id.txtdrtn);
        dp=findViewById(R.id.spinner2);
        saveBtn = findViewById(R.id.btnSave);
        lstBtn = findViewById(R.id.btnList);
        updateBtn = findViewById(R.id.btnUpdate);
        deleteBtn = findViewById(R.id.btnDelete);
        showBtn = findViewById(R.id.btnShowRec);
        ArrayAdapter aa=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,country);
        dp.setAdapter(aa);
       dp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                str=country[position];

            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Save record
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = id.getText().toString();
                uname = name.getText().toString();
                umob = mob.getText().toString();
                udan = str;

                try {
                    db.addDance(new Dance(Integer.parseInt(uid),uname,umob,udan));
                    Toast.makeText(getApplicationContext(), "Record Added", Toast.LENGTH_SHORT).show();
                    id.setText("");
                    name.setText("");
                    mob.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uid = id.getText().toString();
                uname = name.getText().toString();
                umob= mob.getText().toString();
                udan=str;
                try {
                    db.updateDance(new Dance(Integer.parseInt(uid), uname, umob,udan));
                    Toast.makeText(getApplicationContext(), "Record Updated",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uid = id.getText().toString();
                try {
                    db.deleteDance(Integer.parseInt(uid));
                    Toast.makeText(getApplicationContext(), "Record Deleted",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // To Display all records from table
        lstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

        // To Display a single record from table
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = id.getText().toString();
                intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("userid", uid);
                startActivity(intent);
            }
        });

    }
}
