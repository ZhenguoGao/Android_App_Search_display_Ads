package com.example.zhenguo.my_example.models;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;

import com.example.zhenguo.my_example.HttpHandler;
import com.example.zhenguo.my_example.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Zhenguo on 16/11/30.
 */


public class Jsoning extends AsyncTask<String,String,ArrayList<HashMap<String,String>>> {


    String com_url;
    ArrayList<HashMap<String,String>>  legislate_list;

    public Jsoning(String url) {
        this.com_url=url;
    }


    public ArrayList<HashMap<String,String>> doInBackground(String... params) {

        legislate_list = new ArrayList<HashMap<String,String>>();

        HttpHandler sh = new HttpHandler();

        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(com_url);

        Log.e(TAG, "Response1111111 from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("results");
//                    String i = (String)contacts.length();
//                    Log.e("senselessness",i);
//                    HashMap<String,String> map= new HashMap<>();
                // looping through All Contacts
                Log.e("return_length1",""+contacts.length());
                for (int i = 0; i < contacts.length(); i++) {

                    HashMap<String,String> map= new HashMap<>();

                    JSONObject c = contacts.getJSONObject(i);
//                        CommitteeModel committeeModel = new CommitteeModel(c.getString("id"),c.getString("email"),c.getString("name"));




                    Log.e(TAG, "first");
                    String url="http://theunitedstates.io/images/congress/original/"+c.getString("bioguide_id")+".jpg";
                    String name=c.getString("first_name")+c.getString("last_name");
                    String email=c.getString("oc_email");
                    String party=c.getString("party");

                    String state_name=c.getString("state_name");
                    String chamber=c.getString("chamber");
                    String contact=c.getString("phone");
                    String start_term=c.getString("term_start");
                    String end_term=c.getString("term_end");
                    String Office=c.getString("office");
                    String state=c.getString("state");
                    String fax=c.getString("fax");
                    String birthday=c.getString("birthday");
                    String district=c.getString("district");
                    String facebook="http://www.facebook.com/"+c.getString("facebook_id");
                    String twitter="http://www.twitter.com/"+c.getString("twitter_id");;
                    String website=c.getString("website");
                    String id=c.getString("bioguide_id");






                    Log.e("mmmmmm", c.getString("first_name"));


                    map.put("url",url);
                    map.put("name",name);

                    map.put("party",party);
                    map.put("email",email);
                    map.put("state_name",state_name);
                    map.put("chamber",chamber);
                    map.put("contact",contact);
                    map.put("start_term",start_term);
                    map.put("end_term",end_term);
                    map.put("Office",Office);
                    map.put("state",state);
                    map.put("fax",fax);
                    map.put("birthday",birthday);
                    map.put("district",district);
                    map.put("facebook",facebook);
                    map.put("twitter",twitter);
                    map.put("website",website);
                    map.put("id",id);





//                        committeeModel.setId(c.getString("id"));
//                        committeeModel.setEmail(c.getString("email"));
//                        committeeModel.setName(c.getString("name"));

                    legislate_list.add(map);




                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());


            }
        }

        return legislate_list;
    }
    protected void onPostExecute(ArrayList<HashMap<String,String>> result) {

    }


}
