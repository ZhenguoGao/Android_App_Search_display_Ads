package com.example.zhenguo.my_example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import static com.example.zhenguo.my_example.R.id.toolbar;

public class LegislatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setTitle("legislator");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);









        Intent intent =getIntent();
        String last_name=intent.getStringExtra("last_name");
        TextView name= (TextView)findViewById(R.id.legis_name);
        name.setText(last_name);

    }
}
