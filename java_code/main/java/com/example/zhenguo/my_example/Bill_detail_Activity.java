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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Bill_detail_Activity extends AppCompatActivity {
    Context mcontext;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mcontext=this.getApplication();
        sp = mcontext.getSharedPreferences("legislator1",Context.MODE_PRIVATE);

        ImageButton back_button = (ImageButton) findViewById(R.id.back2);
        back_button.setOnClickListener(new LinearLayout.OnClickListener(){
            public void onClick(View v){
                finish();

            }
        });
        Intent intent =getIntent();
        final String tbill_id=intent.getStringExtra("bill_id");
        String turl=intent.getStringExtra("url");
        String tofficial_title=intent.getStringExtra("official_title");
        String tbill_type=intent.getStringExtra("bill_type");
        String tchamber=intent.getStringExtra("chamber");
        String tstatus=intent.getStringExtra("status");
        String tintroduced_on=intent.getStringExtra("introduced_on");
//        String tCongress=intent.getStringExtra("Congress");
        String tVersion_Status=intent.getStringExtra("Version_Status");
        String tsponsor=intent.getStringExtra("sponsor");

        Log.e("url",turl);

        final HashMap<String,String> map= new HashMap<>();
        map.put("bill_id",tbill_id);
        map.put("url",turl);
        map.put("official_title",tofficial_title);
        map.put("bill_type",tbill_type);


        map.put("chamber",tchamber);
        map.put("status",tstatus);
        map.put("introduced_on",tintroduced_on);
//        map.put("Congress",tCongress);
        map.put("Version_Status",tVersion_Status);
        map.put("sponsor",tsponsor);

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


//        String tstart_term=intent.getStringExtra("contact");
//        String tend_term=intent.getStringExtra("Office");





        TextView bill_id= (TextView)findViewById(R.id.bill_id);
        TextView url= (TextView)findViewById(R.id.url);
        TextView official_title= (TextView)findViewById(R.id.official_title);
        TextView bill_type= (TextView)findViewById(R.id.bill_type);
        TextView chamber= (TextView)findViewById(R.id.chamber);
        TextView status= (TextView)findViewById(R.id.status);
        TextView introduced_on= (TextView)findViewById(R.id.introduced_on);
        TextView Congress= (TextView)findViewById(R.id.Congress_URL);
        TextView Version_Status= (TextView)findViewById(R.id.Version_Status);
        TextView sponsor= (TextView)findViewById(R.id.sponsor);
        final ImageView bill_fav= (ImageView)findViewById(R.id.bill_fav);

        bill_id.setText(tbill_id);
        url.setText(turl);
        official_title.setText(tofficial_title);
        bill_type.setText(tbill_type);
        chamber.setText(tchamber);
        status.setText(tstatus);
        introduced_on.setText(tintroduced_on);
//        Congress.setText(tCongress);
        Version_Status.setText(tVersion_Status);
        sponsor.setText(tsponsor);

        SharedPreferences.Editor editor1 =sp.edit();
//                editor1.clear();
//                editor1.commit();
        int m=0;
        Map<String,?> keys = sp.getAll();

//                    JSONArray arr = new JSONArray();
        for(Map.Entry<String,?> entry : keys.entrySet()) {

            if(entry.getKey().toString().equals(tbill_id.toString()+"bill")){
                bill_fav.setImageResource(R.drawable.star_on);
                m=1;

            }

        }
        if(m==0){
            bill_fav.setImageResource(R.drawable.star_off);
        }

        bill_fav.setOnClickListener(new LinearLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor1 =sp.edit();
//                editor1.clear();
//                editor1.commit();
                Map<String,?> keys = sp.getAll();
                int k=0;
//                    JSONArray arr = new JSONArray();
                for(Map.Entry<String,?> entry : keys.entrySet()) {

                    if(entry.getKey().toString().equals(tbill_id.toString()+"bill")){
                        editor1.remove(tbill_id+"bill");
                        editor1.apply();
                        Log.e("ResponseResponse " , "ssssss");

                        k=1;
                        bill_fav.setImageResource(R.drawable.star_off);
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
                    editor1.putString(tbill_id+"bill",object.toString());
//                editor1.putString("bill"+id,jsonStr.toString());
                    Log.e("ResponseResponse " , "vvvvvvvvv");
                    bill_fav.setImageResource(R.drawable.star_on);
//                editor1.putString("committee"+id,jsonStr.toString());
                    editor1.commit();
                }


            }
        });


    }

}
