package com.example.zhenguo.my_example;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.zhenguo.my_example.models.Bill_pageAdapter;
import com.example.zhenguo.my_example.models.CommitteeModel;
import com.example.zhenguo.my_example.models.Legislator_pageAdapter;
import com.example.zhenguo.my_example.models.extendAdapter1;
import com.example.zhenguo.my_example.models.extendAdapter2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BillsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String TAG = MainActivity.class.getSimpleName();

    private static String url1 = "http://api.androidhive.info/contacts/";

    //ListView lv;

    private ViewPager BillViewPager;

    public ListView listSenate2;
    //    ArrayList<CommitteeModel> contactList;
    ArrayList<HashMap<String,String>> Bill_list;


    public void Com_tab_select_action(String select){
        String url="";

        if(select.equals("active")){
            url="http://104.198.0.197:8080/bills?history.active=true&order=introduced_on&per_page=50";
        }
        else if(select.equals("notactive")) {
            url = "http://104.198.0.197:8080/bills?history.active=false&order=introduced_on&per_page=50";
        }

        new JSONtask1(url).execute();


    }



    public BillsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BillsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BillsFragment newInstance(String param1, String param2) {
        BillsFragment fragment = new BillsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,mParam1);
        Log.d(TAG,mParam2);

        View view = inflater.inflate(R.layout.fragment_bills, container, false);
//        lv=(ListView)view.findViewById(R.id.committee_state);
//        TabHost tabs=(TabHost) layout.findViewById(R.id.tabhosts);
//        tabs.setup();
        Log.d("step1","step1");
//

        Bill_list = new ArrayList<HashMap<String,String>>();

        Com_tab_select_action("active");

        TabLayout tablayout=(TabLayout)view.findViewById(R.id.tablayout2);

        TabLayout.Tab page1 = tablayout.newTab();
        page1.setText("Active Bills");
//        TabSpec page1 = tabs.newTabSpec("tab1")
//                .setIndicator("By state")
//                .setContent(R.id.tab1);
//        tabs.addTab(page1);

        TabLayout.Tab page2 = tablayout.newTab();
        page2.setText("New Bills");
//        TabSpec page2 = tabs.newTabSpec("tab2")
//                .setIndicator("House")
//                .setContent(R.id.tab2);
//        tabs.addTab(page2);


//        TabSpec page3 = tabs.newTabSpec("tab3")
//                .setIndicator("Senate")
//                .setContent(R.id.tab3);
//        tabs.addTab(page3);

        tablayout.addTab(page1);
        tablayout.addTab(page2);



        listSenate2 = new ListView(view.getContext());
        Vector<View> pageVector = new Vector<>();
        pageVector.add(listSenate2);


        BillViewPager=(ViewPager) view.findViewById(R.id.viewpage2);
        Bill_pageAdapter adapter = new Bill_pageAdapter(view.getContext(),pageVector);
        BillViewPager.setAdapter(adapter);

        BillViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(BillViewPager){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText()=="Active Bills"){
                    Bill_list.clear();
                    Com_tab_select_action("active");
                }
                else if(tab.getText()=="New Bills"){
                    Bill_list.clear();
                    Com_tab_select_action("notactive");
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

    public class JSONtask1 extends AsyncTask<String,String,ArrayList<HashMap<String,String>>> {

        String com_url;

        private JSONtask1(String url) {
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


                        String url=c.getJSONObject("last_version").getJSONObject("urls").getString("pdf");
                        String bill_id=c.getString("bill_id");
                        String official_title=c.getString("official_title");
                        String bill_type=c.getString("bill_type");
                        String chamber=c.getString("chamber");
                        JSONObject cc=c.getJSONObject("sponsor");
                        String sponsor=cc.getString("title");
                        String status="true";
                        String introduced_on=c.getString("introduced_on");
                        String Congress_URL=c.getJSONObject("urls").getString("congress");
                        String Version_Status=c.getJSONObject("last_version").getString("version_name");





                        map.put("url",url);
                        map.put("bill_id",bill_id);
                        map.put("chamber",chamber);
                        map.put("official_title",official_title);
                        map.put("bill_type",bill_type);
                        map.put("sponsor",sponsor);
                        map.put("status",status);

                        map.put("introduced_on",introduced_on);
                        if(c.getJSONObject("urls").isNull("congress")){
                            map.put("Congress","NA");
                        }
                        else{
                            map.put("Congress",Congress_URL);
                        }
//                        map.put("Congress_URL",Congress_URL);
                        map.put("Version_Status",Version_Status);







//                        committeeModel.setId(c.getString("id"));
//                        committeeModel.setEmail(c.getString("email"));
//                        committeeModel.setName(c.getString("name"));

                        Bill_list.add(map);




                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());


                }
            }

            return Bill_list;
        }
        protected void onPostExecute(ArrayList<HashMap<String,String>> result) {
//            super.onPostExecute(result);
            // Dismiss the progress dialog
            ListAdapter committeeAdapter= new extendAdapter2(getActivity(),Bill_list,R.layout.bill_item,new String[]{}, new int[]{});
            Log.e("sssss", "Response1");

//            HashMap<String,String> k=legislate_list.get(0);
//            HashMap<String,String> k1=legislate_list.get(1);
//
//            Log.e("www", k.get("first_name"));
//            Log.e("www", k1.get("first_name"));
            listSenate2.setAdapter(committeeAdapter);
            //committeeAdapter.notifyDataSetChanged();
            Log.e(TAG, "Response20");

        }


    }

}
