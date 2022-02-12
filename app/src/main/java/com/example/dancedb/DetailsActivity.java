package com.example.dancedb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.course24db.R;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    DatabaseHandler db;
    ListView lv;
    int i = 0,uid;
    ArrayList<Dance> dance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        db = new DatabaseHandler(this);
        lv = findViewById(R.id.user_list);
        Intent intent=getIntent();
        if (intent.hasExtra("userid")) {
            uid = Integer.parseInt(intent.getStringExtra("userid"));
            dance = db.GetUserByUserId(uid);
        }
        else
        {
//code to read all contacts
            dance = db.getAllDance();
        }
        List<String> ls = new ArrayList<String>();
        for (Dance cn : dance) {
            String s1 = " Id: " + cn.get_id() + "\t Name: " + cn.get_name() + "\n Mobile Number: " +
                    cn.get_mobno()+"\n Dance Type: "+cn.get_dancetype();
            ls.add(s1);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (DetailsActivity.this,
                        android.R.layout.simple_expandable_list_item_1,
                        ls);
        lv.setAdapter(adapter);
    }
}