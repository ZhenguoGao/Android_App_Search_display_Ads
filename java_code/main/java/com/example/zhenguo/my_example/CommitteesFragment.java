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
import com.example.zhenguo.my_example.models.CommitteeModel;
import com.example.zhenguo.my_example.models.extendAdapter;
import com.example.zhenguo.my_example.models.myPageAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.v4.view.ViewPager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommitteesFragment extends Fragment {

    private String TAG = MainActivity.class.getSimpleName();

    private static String url1 = "http://api.androidhive.info/contacts/";

    //ListView lv;

    private ViewPager legViewPager;

    public ListView listSenate;
//    ArrayList<CommitteeModel> contactList;
    ArrayList<HashMap<String,String>>  committee_list;


    public void Com_tab_select_action(String select){
        String url="";

        if(select.equals("House")){
            url="http://104.198.0.197:8080/committees?fields=chamber,committee_id,name,parent_committee_id,phone,office,member_ids&per_page=all&chamber=house&order=name__asec";
        }
        else if(select.equals("Senate")){
            url="http://104.198.0.197:8080/committees?fields=chamber,committee_id,name,parent_committee_id,phone,office,member_ids&per_page=all&chamber=senate&order=name__asec";
        }
        else if(select.equals(("Joint"))){
            url="http://104.198.0.197:8080/committees?fields=chamber,committee_id,name,parent_committee_id,phone,office,member_ids&per_page=all&chamber=joint&order=name__asec";
        }

            new JSONtask(url).execute();


    }

    public CommitteesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        committee_list= new ArrayList<>();

//


        View view = inflater.inflate(R.layout.fragment_committees, container, false);
//        lv=(ListView)view.findViewById(R.id.committee_state);
//        TabHost tabs=(TabHost) layout.findViewById(R.id.tabhosts);
//        tabs.setup();
        Log.d("step1","step1");
//

        committee_list = new ArrayList<HashMap<String,String>>();

        Com_tab_select_action("House");

        TabLayout tablayout=(TabLayout)view.findViewById(R.id.tablayout);

        TabLayout.Tab page1 = tablayout.newTab();
        page1.setText("House");
//        TabSpec page1 = tabs.newTabSpec("tab1")
//                .setIndicator("By state")
//                .setContent(R.id.tab1);
//        tabs.addTab(page1);

        TabLayout.Tab page2 = tablayout.newTab();
        page2.setText("Senate");
//        TabSpec page2 = tabs.newTabSpec("tab2")
//                .setIndicator("House")
//                .setContent(R.id.tab2);
//        tabs.addTab(page2);

        TabLayout.Tab page3 = tablayout.newTab();
        page3.setText("Joint");
//        TabSpec page3 = tabs.newTabSpec("tab3")
//                .setIndicator("Senate")
//                .setContent(R.id.tab3);
//        tabs.addTab(page3);

        tablayout.addTab(page1);
        tablayout.addTab(page2);
        tablayout.addTab(page3);


        listSenate = new ListView(view.getContext());
        Vector<View> pageVector = new Vector<>();
        pageVector.add(listSenate);


        legViewPager=(ViewPager) view.findViewById(R.id.viewpage);
        myPageAdapter adapter = new myPageAdapter(view.getContext(),pageVector);
        legViewPager.setAdapter(adapter);

        legViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(legViewPager){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText()=="House"){
                    Com_tab_select_action("House");
                    Log.e(TAG, "Response10");
                }
                else if(tab.getText()=="Senate"){
                    Com_tab_select_action("Senate");
                    Log.e(TAG, "Response12");
                }
                else if(tab.getText()=="Joint"){
                    Com_tab_select_action("Joint");
                    Log.e(TAG, "Response13");
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
//        return layout;

    }





    private class JSONtask extends AsyncTask<String,String,ArrayList<HashMap<String,String>>> {

        String com_url;

        public JSONtask(String url) {
            this.com_url=url;
        }


        protected ArrayList<HashMap<String,String>> doInBackground(String... params) {

            HttpHandler sh = new HttpHandler();
            committee_list.clear();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(com_url);

            Log.e(TAG, "Response from url: " + jsonStr);
//            List<CommitteeModel> committeeModelList = new ArrayList<>();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("results");

                    // looping through All Contacts
                    Log.e("return_length",""+contacts.length());
                    for (int i = 0; i < contacts.length(); i++) {
                        HashMap<String,String> map= new HashMap<>();
                        JSONObject c = contacts.getJSONObject(i);
//                        CommitteeModel committeeModel = new CommitteeModel(c.getString("id"),c.getString("email"),c.getString("name"));
                            String committee_id=c.getString("committee_id");
                            String name=c.getString("name");
                            String chamber=c.getString("chamber");
//                            String parent_committee_id=c.getString("parent_committee_id");

//                            String contact=c.getString("phone");
//                            String Office=c.getString("office");


                            map.put("committee_id",committee_id);
                            map.put("name",name);
                            map.put("chamber",chamber);
                            if(c.isNull("phone")){
                                map.put("phone","NA");
                            }
                            else{
                                map.put("phone",c.getString("phone"));
                            }
                            if(c.isNull("parent_committee_id")){
                                map.put("parent_committee_id","NA");
                            }
                            else{
                                map.put("parent_committee_id",c.getString("parent_committee_id"));
                            }

                            if(c.isNull("office")){
                                map.put("office","NA");
                            }
                            else{
                                map.put("office",c.getString("office"));
                            }


//                            if(chamber.equals("house")){
//                                map.put("url","http://cs-server.usc.edu:45678/hw/hw8/images/h.png");
//                            }
//                            else{
//                                map.put("url","http://cs-server.usc.edu:45678/hw/hw8/images/s.svg");
//                            }

//                            map.put("contact",contact);
//                            map.put("Office",Office);



//                        committeeModel.setId(c.getString("id"));
//                        committeeModel.setEmail(c.getString("email"));
//                        committeeModel.setName(c.getString("name"));

                        committee_list.add(map);



                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());


                }
            }

            return committee_list;
        }
        protected void onPostExecute(ArrayList<HashMap<String,String>> result) {
           // super.onPostExecute(result);
            // Dismiss the progress dialog
            ListAdapter committeeAdapter= new extendAdapter(getActivity(),committee_list,R.layout.legislate_item,new String[]{}, new int[]{});
            Log.e(TAG, "Response1");

            listSenate.setAdapter(committeeAdapter);
            //committeeAdapter.notifyDataSetChanged();
            Log.e(TAG, "Response6");

        }


    }

//    public class CommitteeAdapter extends ArrayAdapter{
//         public Context mcontext;
//
//        private List<CommitteeModel> committeeModelList;//定义数据。
//        private int  resource;
//        private LayoutInflater inflater = null;
//
//
//        /*
//        定义构造器，在Activity创建对象Adapter的时候将数据data和Inflater传入自定义的Adapter中进行处理。
//        */
//        public CommitteeAdapter(Context context, int resource,List<CommitteeModel> objects){
//            super(context,resource,objects);
//            committeeModelList=objects;
//
//            Log.e(TAG, "Response2"+committeeModelList.get(0).getName());
//            Log.e(TAG, "Response2"+committeeModelList.get(1).getName());
//            Log.e(TAG, "Response2"+committeeModelList.get(2).getName());
//            this.resource =resource;
//            mcontext=context;
//            inflater=(LayoutInflater)mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            Log.e(TAG, "Response3");
//
//
//
//        }
//
//
//        @Override
//
//        public View getView(int position, View convertView, ViewGroup parent) {
//            //获得ListView中的view
//            View k = convertView;
//            Log.e("ssisisi", "Response5");
//            if(convertView==null){
//                k = inflater.inflate(R.layout.committee_item,null);
//            }
//            //获得学生对象
//            Log.e(TAG, "Response4");
//            TextView name = (TextView) k.findViewById(R.id.name);
//
//            TextView email = (TextView) k.findViewById(R.id.email);
//            TextView mobile = (TextView) k.findViewById(R.id.mobile);
//
//            //将数据一一添加到自定义的布局中。
//            Log.e(committeeModelList.get(position).getName(), "Response4");
//            name.setText(committeeModelList.get(position).getName());
//            email.setText(committeeModelList.get(position).getEmail());
//            mobile.setText(committeeModelList.get(position).getId());
//
//            return convertView ;
//        }
//    }




}
