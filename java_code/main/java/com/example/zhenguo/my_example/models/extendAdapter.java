package com.example.zhenguo.my_example.models;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhenguo.my_example.Committee_detail_Activity;
import com.example.zhenguo.my_example.Legislator_detail_Activity;
import com.example.zhenguo.my_example.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.widget.SimpleAdapter;
/**
 * Created by Zhenguo on 16/11/22.
 */

public class extendAdapter extends SimpleAdapter {
    private Context mcontext;

//    private List<CommitteeModel> committeeModelList;//定义数据。

    private int  resource;
    private LayoutInflater inflater = null;
    private ArrayList<HashMap<String,String>> committee_list;


    /*
    定义构造器，在Activity创建对象Adapter的时候将数据data和Inflater传入自定义的Adapter中进行处理。
    */
    public extendAdapter(Context context, ArrayList<HashMap<String,String>> data,int resource,String[] from, int[] to){

        super(context,data,resource,from, to);
//        mcontext=context;
       // Log.e(Integer.toString(objects.size()),"ssss");
        this.committee_list=data;

//        Log.e(TAG, "Response2"+committeeModelList.get(0).getName());
//        Log.e(TAG, "Response2"+committeeModelList.get(1).getName());
//        Log.e(TAG, "Response2"+committeeModelList.get(2).getName());
       // this.resource =resource;
        mcontext=context;
        inflater=(LayoutInflater)mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.e("iiii", "Response3");



    }

//    public void setItemList(List<CommitteeModel> objects){
//        this.committeeModelList = objects;
//        this.notifyDataSetChanged();
//    }
//    public int getCount(){
//        return this.committeeModelList == null ? committeeModelList.size():0;
//    }
//    public CommitteeModel getItem(int position){
//        return committeeModelList.get(position);
//    }
//    public long getItemId(int position){
//        return position;
//    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获得ListView中的view
        View k = convertView;
        //List<CommitteeModel> newList =  (ArrayList<CommitteeModel>)getItem(position);
        Log.e("ssisisi", "Response5");
        if(convertView==null){
            k = inflater.inflate(R.layout.committee_item,null);
        }
        //获得学生对象
        //Log.e(TAG, "Response4");
        final HashMap<String,Object> data = (HashMap<String, Object>) getItem(position);
        TextView committee_id = (TextView) k.findViewById(R.id.committee_id);

        TextView committee_name = (TextView) k.findViewById(R.id.committee_name);
        TextView chamber = (TextView) k.findViewById(R.id.chamber);

        //将数据一一添加到自定义的布局中。
//        Log.e(committeeModelList.get(position).getName(), "Response4");
//        CommitteeModel obj = getItem(position);
        committee_id.setText((String) data.get("committee_id").toString());
        committee_name.setText((String)data.get("name").toString());
        chamber.setText((String)data.get("chamber").toString());
        LinearLayout row= (LinearLayout) k.findViewById(R.id.one_row1);
        row.setOnClickListener(new LinearLayout.OnClickListener(){
            @Override
            public void onClick(View v){
                Context main = v.getContext();
                Intent intent= new Intent(main, Committee_detail_Activity.class);
                intent.putExtra("name",data.get("name").toString());
                intent.putExtra("committee_id",data.get("committee_id").toString());
                intent.putExtra("chamber",data.get("chamber").toString());
                intent.putExtra("parent_committee_id",data.get("parent_committee_id").toString());
                intent.putExtra("office",data.get("office").toString());
                intent.putExtra("phone",data.get("phone").toString());
//                intent.putExtra("url",data.get("url").toString());

//                intent.putExtra("contact",data.get("contact").toString());

//                intent.putExtra("Office",data.get("Office").toString());
//                intent.putExtra("state",data.get("state").toString());
//                intent.putExtra("fax",data.get("fax").toString());
//                intent.putExtra("birthday",data.get("birthday").toString());
//                intent.putExtra("url",data.get("url").toString());
//                intent.putExtra("facebook",data.get("facebook").toString());
//                intent.putExtra("twitter",data.get("twitter").toString());
//                intent.putExtra("website",data.get("website").toString());



                main.startActivity(intent);

            }
        });

        return k ;
    }

}
