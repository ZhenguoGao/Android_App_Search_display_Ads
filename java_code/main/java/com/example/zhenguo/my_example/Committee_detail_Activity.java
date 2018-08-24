package com.example.zhenguo.my_example;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Committee_detail_Activity extends AppCompatActivity {
    Context mcontext;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_detail_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mcontext=this.getApplication();
        sp = mcontext.getSharedPreferences("legislator1",Context.MODE_PRIVATE);

        ImageButton back_button = (ImageButton) findViewById(R.id.back1);
        back_button.setOnClickListener(new LinearLayout.OnClickListener(){
            public void onClick(View v){
                finish();

            }
        });
        Intent intent =getIntent();
        final String tcommittee_id=intent.getStringExtra("committee_id");
        String tname=intent.getStringExtra("name");

        String tparent_committee_id=intent.getStringExtra("parent_committee_id");
//        String turl=intent.getStringExtra("url");
        String toffice=intent.getStringExtra("office");
        String tphone=intent.getStringExtra("phone");

//        Log.e("url",turl);


//        String tstart_term=intent.getStringExtra("contact");
//        String tend_term=intent.getStringExtra("Office");




        String tchamber=intent.getStringExtra("chamber");

        final HashMap<String,String> map= new HashMap<>();
        map.put("committee_id",tcommittee_id);
        map.put("name",tname);
        map.put("parent_committee_id",tparent_committee_id);
        map.put("office",toffice);


        map.put("chamber",tchamber);
        map.put("phone",tphone);

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();

        final JSONObject object = new JSONObject();

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            try {
                object.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        TextView committee_id= (TextView)findViewById(R.id.committee_id);
        TextView name= (TextView)findViewById(R.id.name);
        TextView chamber= (TextView)findViewById(R.id.chamber);
        TextView parent_committee= (TextView)findViewById(R.id.parent_committee);
        TextView contact= (TextView)findViewById(R.id.contact);
        TextView Office= (TextView)findViewById(R.id.Office);
        ImageView bioguide_id1= (ImageView)findViewById(R.id.bioguide_id1);
        final ImageView committee_fav= (ImageView)findViewById(R.id.committee_fav);



        name.setText(tname);
        contact.setText(tphone);
        Office.setText(toffice);
        chamber.setText(tchamber);
        committee_id.setText(tcommittee_id);
        parent_committee.setText(tparent_committee_id);
        if(tchamber.equals("senate")){
            Log.e("url",tchamber);
            bioguide_id1.setImageResource(R.drawable.s);
        }
        else{
            Log.e("url",tchamber);
            bioguide_id1.setImageResource(R.drawable.hh);
        }

        SharedPreferences.Editor editor1 =sp.edit();
//                editor1.clear();
//                editor1.commit();
        int m=0;
        Map<String,?> keys = sp.getAll();

//                    JSONArray arr = new JSONArray();
        for(Map.Entry<String,?> entry : keys.entrySet()) {

            if(entry.getKey().toString().equals(tcommittee_id.toString()+"committee")){
                committee_fav.setImageResource(R.drawable.star_on);
                m=1;

            }

        }
        if(m==0){
            committee_fav.setImageResource(R.drawable.star_off);
        }

        committee_fav.setOnClickListener(new LinearLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor1 =sp.edit();
//                editor1.clear();
//                editor1.commit();
                Map<String,?> keys = sp.getAll();
                int k=0;
//                    JSONArray arr = new JSONArray();
                for(Map.Entry<String,?> entry : keys.entrySet()) {

                    if(entry.getKey().toString().equals(tcommittee_id.toString()+"committee")){
                        editor1.remove(tcommittee_id+"committee");
                        editor1.apply();
                        Log.e("ResponseResponse " , "ssssss");

                        k=1;
                        committee_fav.setImageResource(R.drawable.star_off);
                    }
                }

//                HttpHandler sh = new HttpHandler();
//                String com_url="http://104.198.0.197:8080/legislators?bioguide_id="+id;
//
//
//                String jsonStr =sh.makeServiceCall("http://104.198.0.197:8080/legislators?per_page=all&order=state__asec,last_name__asec");
//                ArrayList<HashMap<String,String>> array = new Jsoning(com_url).doInBackground();
//                Log.e("Response1111111: " , jsonStr.toString());
                if(k==0){
                    editor1.putString(tcommittee_id+"committee",object.toString());
//                editor1.putString("bill"+id,jsonStr.toString());
                    Log.e("ResponseResponse " , "vvvvvvvvv");
                    committee_fav.setImageResource(R.drawable.star_on);
//                editor1.putString("committee"+id,jsonStr.toString());
                    editor1.commit();
                }


            }
        });



    }



}


