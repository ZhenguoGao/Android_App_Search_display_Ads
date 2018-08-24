package com.example.zhenguo.my_example;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.zhenguo.my_example.HttpHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Legislator_detail_Activity extends AppCompatActivity {

    Context mcontext;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislator_detail_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mcontext=this.getApplication();
        sp = mcontext.getSharedPreferences("legislator1",Context.MODE_PRIVATE);

        String url1="http://cs-server.usc.edu:45678/hw/hw8/images/d.png";
        String url2="http://cs-server.usc.edu:45678/hw/hw8/images/r.png";
        String url3="http://independentamericanparty.org/wp-content/themes/v/images/logo-american-heritage-academy.png";
        ImageButton back_button = (ImageButton) findViewById(R.id.back);
        back_button.setOnClickListener(new LinearLayout.OnClickListener(){
            public void onClick(View v){
//                super. onResume();
                finish();

            }
        });
        Intent intent =getIntent();
        String tname=intent.getStringExtra("name");
        String temail=intent.getStringExtra("email");
        String tchamber=intent.getStringExtra("chamber");
        String tcontact=intent.getStringExtra("contact");
        String tstart_term=intent.getStringExtra("start_term");
        String tend_term=intent.getStringExtra("end_term");
        String tOffice=intent.getStringExtra("Office");
        String tstate=intent.getStringExtra("state");
        String tfax=intent.getStringExtra("fax");
        String tbirthday=intent.getStringExtra("birthday");
        String tdistrict=intent.getStringExtra("district");
        String tparty=intent.getStringExtra("party");
        String tstate_name=intent.getStringExtra("state_name");
        String url=intent.getStringExtra("url");

        final String id=intent.getStringExtra("id");
        final String tfacebook=intent.getStringExtra("facebook");
        final String ttwitter=intent.getStringExtra("twitter");
        final String twebsite=intent.getStringExtra("website");


        final HashMap<String,String> map= new HashMap<>();
        map.put("url",url);
        map.put("name",tname);
        map.put("party",tparty);
        map.put("district",tdistrict);


        map.put("email",temail);
        map.put("state_name",tstart_term);
        map.put("chamber",tchamber);
        map.put("contact",tcontact);
        map.put("start_term",tstart_term);
        map.put("end_term",tend_term);
        map.put("Office",tOffice);
        map.put("state",tstate);
        map.put("fax",tfax);
        map.put("birthday",tbirthday);
        map.put("facebook",tfacebook);
        map.put("twitter",ttwitter);
        map.put("website",twebsite);
        map.put("id",id);

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

        TextView name= (TextView)findViewById(R.id.name);
        TextView email= (TextView)findViewById(R.id.email);
        TextView chamber= (TextView)findViewById(R.id.chamber);
        TextView contact= (TextView)findViewById(R.id.contact);
        TextView start_term= (TextView)findViewById(R.id.start_term);
        TextView end_term= (TextView)findViewById(R.id.end_term);
        TextView Office= (TextView)findViewById(R.id.Office);
        TextView state= (TextView)findViewById(R.id.state);
        TextView fax= (TextView)findViewById(R.id.fax);
        TextView birthday= (TextView)findViewById(R.id.birthday);
        ImageView bioguide_id= (ImageView)findViewById(R.id.bioguide_id);
        ImageView facebook= (ImageView)findViewById(R.id.facebook);
        ImageView twitter= (ImageView)findViewById(R.id.twitter);
        ImageView website= (ImageView)findViewById(R.id.website);
        ImageView party1= (ImageView)findViewById(R.id.party1);
        TextView party2= (TextView)findViewById(R.id.party2);
        final ImageView leg_fav= (ImageView)findViewById(R.id.leg_fav);
        ProgressBar progressBar= (ProgressBar)findViewById(R.id.progressBar);


        if(tparty.equals("D")){
            party2.setText("Democrat");
            Picasso.with(this).load(url1).into(party1);
        }
        else if(tparty.equals("R")){
            party2.setText("Republic");
            Picasso.with(this).load(url2).into(party1);
        }
        else{
            party2.setText("Independent");
            Picasso.with(this).load(url3).into(party1);
        }

        name.setText(tname);
        email.setText(temail);
        chamber.setText(tchamber);
        contact.setText(tcontact);
        start_term.setText(tstart_term);
        end_term.setText(tend_term);
        Office.setText(tOffice);
        state.setText(tstate);
        fax.setText(tfax);
        birthday.setText(tbirthday);
//        Date start = new Date(tstart_term),
//                end = new Date(e.term_end),
//                today = new Date();
//        Date date1 = new Date();
//        Date date2 = new Date();
//        Date now = new Date();
//
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String now1=sdf.format(now);
////        try {
////            date1 = sdf.parse(tstart_term);
////            date2 = sdf.parse(tend_term);
////            now=sdf.parse(now1);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//
//        int k=Math.round((new Date(tstart_term) - tstart_term.getTime()) / (tend_term.getTime() - tstart_term.getTime()) * 100);
//
//         Date today = new Date();
//        int dynamic = Math.round((new Date(today) - new Date(tstart_term)) / (new Date(tend_term) - new Date(tstart_term)) * 100);
        Date date_current = new java.util.Date();
        double percent = 0;
        String inputPattern = "yyyy-MM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        try {
            Date date_start = inputFormat.parse(tstart_term);
            Date date_end = inputFormat.parse(tend_term);
            Long num1 = date_current.getTime() - date_start.getTime();
            Long num2 = date_end.getTime() - date_start.getTime();
            percent = (double)num1 / num2;
        } catch (Exception e) {
            e.printStackTrace();
        }



        DecimalFormat format = new DecimalFormat("0.00");
        String result = format.format(percent);
        float temp = Float.valueOf(result);
        int num = Math.round(temp*100);
        progressBar.setProgress(num);

        SharedPreferences.Editor editor1 =sp.edit();
//                editor1.clear();
//                editor1.commit();
        int m=0;
        Map<String,?> keys = sp.getAll();

//                    JSONArray arr = new JSONArray();
        for(Map.Entry<String,?> entry : keys.entrySet()) {

            if(entry.getKey().toString().equals(id.toString()+"legislator")){
                leg_fav.setImageResource(R.drawable.star_on);
                m=1;

            }

        }
        if(m==0){
               leg_fav.setImageResource(R.drawable.star_off);
        }


        Picasso.with(this).load(url).into(bioguide_id);
        final SharedPreferences sharedpreferences;
//        sharedpreferences = getSharedPreferences("leg_fav1", Context.MODE_PRIVATE);
        facebook.setOnClickListener(new LinearLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tfacebook.toString().equals("")) {
                }
                else{
                    Intent link = new Intent(Intent.ACTION_VIEW, Uri.parse(tfacebook.toString()));
                    startActivity(link);
                }
            }
        });
        twitter.setOnClickListener(new LinearLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent link=new Intent(Intent.ACTION_VIEW, Uri.parse(ttwitter.toString()));
                startActivity(link);
            }
        });
        website.setOnClickListener(new LinearLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent link=new Intent(Intent.ACTION_VIEW, Uri.parse(twebsite.toString()));
                startActivity(link);
            }
        });
        leg_fav.setOnClickListener(new LinearLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor1 =sp.edit();
//                editor1.clear();
//                editor1.commit();
                Map<String,?> keys = sp.getAll();
                int k=0;
//                    JSONArray arr = new JSONArray();
                for(Map.Entry<String,?> entry : keys.entrySet()) {

                    if(entry.getKey().toString().equals(id.toString()+"legislator")){
                        editor1.remove(id+"legislator");
                        editor1.apply();
                        Log.e("ResponseResponse " , "ssssss");

                        k=1;
                        leg_fav.setImageResource(R.drawable.star_off);
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
                    editor1.putString(id+"legislator",object.toString());
//                editor1.putString("bill"+id,jsonStr.toString());
                    Log.e("ResponseResponse " , "vvvvvvvvv");
                    leg_fav.setImageResource(R.drawable.star_on);
//                editor1.putString("committee"+id,jsonStr.toString());
                    editor1.commit();
                }


            }
        });

    }


}
