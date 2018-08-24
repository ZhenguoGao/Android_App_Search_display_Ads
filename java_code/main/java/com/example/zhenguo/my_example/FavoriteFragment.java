package com.example.zhenguo.my_example;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
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

import com.example.zhenguo.my_example.models.Legislator_pageAdapter;
import com.example.zhenguo.my_example.models.extendAdapter;
import com.example.zhenguo.my_example.models.extendAdapter1;
import com.example.zhenguo.my_example.models.extendAdapter2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.

 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ViewPager legViewPager;

    public ListView listSenate1;

    Context mcontext;

    ArrayList<HashMap<String,String>>  legislate_list;
    ArrayList<HashMap<String,String>>  Bill_list;
    ArrayList<HashMap<String,String>>  committee_list;



    public FavoriteFragment() {
        // Required empty public constructor
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

        final View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        Log.d("step1","step1");
        mcontext=getActivity();
        legislate_list = new ArrayList<HashMap<String,String>>();
        Bill_list = new ArrayList<HashMap<String,String>>();
        committee_list = new ArrayList<HashMap<String,String>>();

        TabLayout tablayout=(TabLayout)view.findViewById(R.id.tablayout3);

        TabLayout.Tab page1 = tablayout.newTab();
        page1.setText("legislator");

        TabLayout.Tab page2 = tablayout.newTab();
        page2.setText("bill");


        TabLayout.Tab page3 = tablayout.newTab();
        page3.setText("committee");

        tablayout.addTab(page1);
        tablayout.addTab(page2);
        tablayout.addTab(page3);


        listSenate1 = new ListView(view.getContext());
        Vector<View> pageVector = new Vector<>();
        pageVector.add(listSenate1);




        legViewPager=(ViewPager) view.findViewById(R.id.viewpage3);
        Legislator_pageAdapter adapter = new Legislator_pageAdapter(view.getContext(),pageVector);
        legViewPager.setAdapter(adapter);

        legViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        legislate_list.clear();
        SharedPreferences sp = mcontext.getSharedPreferences("legislator1",Context.MODE_PRIVATE);
        sp.getAll();
        Map<String,?> keys = sp.getAll();
//                    JSONArray arr = new JSONArray();
        for(Map.Entry<String,?> entry : keys.entrySet()){

            if(entry.getKey().endsWith("legislator")) {

//
                Log.e("Response55555555", entry.toString());
                Log.e("Response444444444444", entry.getKey());
                Log.e("Response444444444444", entry.getValue().toString());
                JSONObject array;
                sp.getString(entry.getKey(), "");
                HashMap<String, String> itemMap = new HashMap<String, String>();
                try {
                    array = new JSONObject(sp.getString(entry.getKey(), ""));
                    JSONArray names = array.names();
                    if (names != null) {
                        for (int j = 0; j < names.length(); j++) {
                            String namee = names.getString(j);
                            String value = array.getString(namee);
                            itemMap.put(namee, value);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                legislate_list.add(itemMap);
            }
        }

        ListAdapter adapter3= new extendAdapter1(getActivity(),legislate_list,R.layout.legislate_item,new String[]{}, new int[]{});
        listSenate1.setAdapter(null);
        listSenate1.setAdapter(adapter3);

        tablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(legViewPager){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().equals("legislator")){
                    legislate_list.clear();
                    SharedPreferences sp = mcontext.getSharedPreferences("legislator1",Context.MODE_PRIVATE);
                    sp.getAll();
                    Map<String,?> keys = sp.getAll();
//                    JSONArray arr = new JSONArray();
                    for(Map.Entry<String,?> entry : keys.entrySet()){

//
                        if(entry.getKey().endsWith("legislator")) {
                            Log.e("Response55555555", entry.toString());
                            Log.e("Response444444444444", entry.getKey());
                            Log.e("Response444444444444", entry.getValue().toString());
                            JSONObject array;
                            sp.getString(entry.getKey(), "");
                            HashMap<String, String> itemMap = new HashMap<String, String>();
                            try {
                                array = new JSONObject(sp.getString(entry.getKey(), ""));
                                JSONArray names = array.names();
                                if (names != null) {
                                    for (int j = 0; j < names.length(); j++) {
                                        String namee = names.getString(j);
                                        String value = array.getString(namee);
                                        itemMap.put(namee, value);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            legislate_list.add(itemMap);
                        }
                    }


                    ListAdapter adapter= new extendAdapter1(getActivity(),legislate_list,R.layout.legislate_item,new String[]{}, new int[]{});
                    listSenate1.setAdapter(null);
                    listSenate1.setAdapter(adapter);
                }
                else if(tab.getText().equals("bill")){
                    Bill_list.clear();
                    SharedPreferences sp = mcontext.getSharedPreferences("legislator1",Context.MODE_PRIVATE);
                    sp.getAll();
                    Map<String,?> keys = sp.getAll();
//                    JSONArray arr = new JSONArray();
                    for(Map.Entry<String,?> entry : keys.entrySet()){

//
                        if(entry.getKey().endsWith("bill")) {
                            Log.e("Response55555555", entry.toString());
                            Log.e("Response444444444444", entry.getKey());
                            Log.e("Response444444444444", entry.getValue().toString());
                            JSONObject array;
                            sp.getString(entry.getKey(), "");
                            HashMap<String, String> itemMap = new HashMap<String, String>();
                            try {
                                array = new JSONObject(sp.getString(entry.getKey(), ""));
                                JSONArray names = array.names();
                                if (names != null) {
                                    for (int j = 0; j < names.length(); j++) {
                                        String namee = names.getString(j);
                                        String value = array.getString(namee);
                                        itemMap.put(namee, value);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            Bill_list.add(itemMap);
                        }
                    }
                    ListAdapter adapter= new extendAdapter2(getActivity(),Bill_list,R.layout.bill_item,new String[]{}, new int[]{});
                    listSenate1.setAdapter(null);
                    listSenate1.setAdapter(adapter);
                }
                else if(tab.getText().equals("committee")){

                    committee_list.clear();
                    SharedPreferences sp = mcontext.getSharedPreferences("legislator1",Context.MODE_PRIVATE);
                    sp.getAll();
                    Map<String,?> keys = sp.getAll();
//                    JSONArray arr = new JSONArray();
                    for(Map.Entry<String,?> entry : keys.entrySet()){

//
                        if(entry.getKey().endsWith("committee")) {
                            Log.e("Response55555555", entry.toString());
                            Log.e("Response444444444444", entry.getKey());
                            Log.e("Response444444444444", entry.getValue().toString());
                            JSONObject array;
                            sp.getString(entry.getKey(), "");
                            HashMap<String, String> itemMap = new HashMap<String, String>();
                            try {
                                array = new JSONObject(sp.getString(entry.getKey(), ""));
                                JSONArray names = array.names();
                                if (names != null) {
                                    for (int j = 0; j < names.length(); j++) {
                                        String namee = names.getString(j);
                                        String value = array.getString(namee);
                                        itemMap.put(namee, value);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            committee_list.add(itemMap);
                        }
                    }

                    ListAdapter adapter= new extendAdapter(getActivity(),committee_list,R.layout.committee_item,new String[]{}, new int[]{});
                    listSenate1.setAdapter(null);
                    listSenate1.setAdapter(adapter);

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

//    public void onResume(){
//        super.onResume();
//
//    }






    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
