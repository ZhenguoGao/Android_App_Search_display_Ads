package com.example.zhenguo.my_example;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.app.TabActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import com.example.zhenguo.my_example.models.CommitteeModel;
import com.example.zhenguo.my_example.models.Legislator_pageAdapter;
import com.example.zhenguo.my_example.models.extendAdapter;
import com.example.zhenguo.my_example.models.extendAdapter1;
import com.example.zhenguo.my_example.models.myPageAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.v4.view.ViewPager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class LegislatorFragment extends Fragment {


    public LegislatorFragment() {
        // Required empty public constructor
    }
    private String TAG = MainActivity.class.getSimpleName();

    private static String url1 = "http://api.androidhive.info/contacts/";

    //ListView lv;
    Map<String, Integer> mapIndex;
    List<String> indexList;

    private ViewPager legViewPager;

    public ListView listSenate1;
    //    ArrayList<CommitteeModel> contactList;
    ArrayList<HashMap<String,String>>  legislate_list;


    public void Com_tab_select_action(String select){
        String url="";

        if(select.equals("by_state")){
            url="http://104.198.0.197:8080/legislators?per_page=all&order=state__asec,last_name__asec";
        }
        else if(select.equals("by_house")){
            url="http://104.198.0.197:8080/legislators?per_page=all&chamber=house&order=state__asec,last_name__asec";
        }
        else if(select.equals(("by_senate"))){
            url="http://104.198.0.197:8080/legislators?per_page=all&chamber=senate&order=state__asec,last_name__asec";
        }

        new JSONtask(url).execute();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_legislator, container, false);
//        lv=(ListView)view.findViewById(R.id.committee_state);
//        TabHost tabs=(TabHost) layout.findViewById(R.id.tabhosts);
//        tabs.setup();
        Log.d("step1","step1");
//
        indexList= new ArrayList<String>();

        legislate_list = new ArrayList<HashMap<String,String>>();

        Com_tab_select_action("by_state");

        TabLayout tablayout=(TabLayout)view.findViewById(R.id.tablayout1);

        TabLayout.Tab page1 = tablayout.newTab();
        page1.setText("state");
//        TabSpec page1 = tabs.newTabSpec("tab1")
//                .setIndicator("By state")
//                .setContent(R.id.tab1);
//        tabs.addTab(page1);

        TabLayout.Tab page2 = tablayout.newTab();
        page2.setText("house");
//        TabSpec page2 = tabs.newTabSpec("tab2")
//                .setIndicator("House")
//                .setContent(R.id.tab2);
//        tabs.addTab(page2);

        TabLayout.Tab page3 = tablayout.newTab();
        page3.setText("senate");
//        TabSpec page3 = tabs.newTabSpec("tab3")
//                .setIndicator("Senate")
//                .setContent(R.id.tab3);
//        tabs.addTab(page3);

        tablayout.addTab(page1);
        tablayout.addTab(page2);
        tablayout.addTab(page3);


        listSenate1 = new ListView(view.getContext());
        Vector<View> pageVector = new Vector<>();
        pageVector.add(listSenate1);


        legViewPager=(ViewPager) view.findViewById(R.id.viewpage1);
        Legislator_pageAdapter adapter = new Legislator_pageAdapter(view.getContext(),pageVector);
        legViewPager.setAdapter(adapter);

        legViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(legViewPager){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText()=="state"){
                    legislate_list.clear();
                    indexList.clear();
                    Com_tab_select_action("by_state");
                }
                else if(tab.getText()=="house"){
                    legislate_list.clear();
                    indexList.clear();
                    Com_tab_select_action("by_house");
                }
                else if(tab.getText()=="senate"){
                    legislate_list.clear();
                    indexList.clear();
                    Com_tab_select_action("by_senate");
                }

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });


        return view;
    }
    private void getIndexList(List<String> indexList) {

        Log.e(TAG, "Response1111122222221 from url: " + indexList);
        mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < indexList.size(); i++) {
            String m = indexList.get(i);
            String index = m.substring(0, 1);

            if (mapIndex.get(index) == null)
                mapIndex.put(index, i);
        }
    }

    private void displayIndex(View view,Context context) {

        LinearLayout indexLayout = (LinearLayout)view.findViewById(R.id.side_index);

        TextView textView;
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        indexLayout.removeAllViews();
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) inflater.inflate(
                    R.layout.side_index_item, null);
            textView.setText(index);
//          textView.setOnClickListener();
            textView.setOnClickListener(new TextView.OnClickListener(){

                @Override
                public void onClick(View view) {
                    TextView selectedIndex = (TextView) view;
                    listSenate1.setSelection(mapIndex.get(selectedIndex.getText()));
                }


            });


            indexLayout.addView(textView);
        }
    }

//    public void onClick(View view) {
//        TextView selectedIndex = (TextView) view;
//        listSenate1.setSelection(mapIndex.get(selectedIndex.getText()));
//    }

    private class JSONtask extends AsyncTask<String,String,ArrayList<HashMap<String,String>>> {


        String com_url;

        private JSONtask(String url) {
            this.com_url=url;
        }


        protected ArrayList<HashMap<String,String>> doInBackground(String... params) {

            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(com_url);

            Log.e(TAG, "Response1 from url: " + jsonStr);
            List<CommitteeModel> committeeModelList = new ArrayList<>();
            if (jsonStr != null) {
                try {
                    indexList.clear();
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



                        indexList.add(c.getString("state"));
                        Log.e(TAG, "first");
                        String url="http://theunitedstates.io/images/congress/original/"+c.getString("bioguide_id")+".jpg";
                        String name=c.getString("last_name")+c.getString("first_name");
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
            Collections.sort(indexList, new Comparator<String>() {
                @Override
                public int compare( String t1, String t2) {
                    return t1.compareToIgnoreCase(t2);
                }
            });

            Collections.sort(legislate_list, new Comparator<HashMap<String, String>>() {
                @Override
                public int compare(HashMap<String, String> t1, HashMap<String, String> t2) {
                    return t1.get("state").compareToIgnoreCase(t2.get("state"));
                }
            });

            return legislate_list;
        }
        protected void onPostExecute(ArrayList<HashMap<String,String>> result) {
//            super.onPostExecute(result);
            // Dismiss the progress dialog
            ListAdapter committeeAdapter= new extendAdapter1(getActivity(),legislate_list,R.layout.legislate_item,new String[]{}, new int[]{});
            Log.e("sssss", "Response1");
//            getIndexList(indexList);
//            HashMap<String,String> k=legislate_list.get(0);
//            HashMap<String,String> k1=legislate_list.get(1);
//
//            Log.e("www", k.get("first_name"));
//            Log.e("www", k1.get("first_name"));
            listSenate1.setAdapter(committeeAdapter);


            getIndexList(indexList);

//            Log.e(TAG, "second");

            displayIndex(getView(),getActivity());

            //committeeAdapter.notifyDataSetChanged();
//            Log.e(TAG, "Response6");

        }


    }

}
