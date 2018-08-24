package com.example.zhenguo.my_example.models;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.zhenguo.my_example.Bill_detail_Activity;
import com.example.zhenguo.my_example.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Zhenguo on 16/11/29.
 */

public class extendAdapter2 extends SimpleAdapter {
    private Context thiscontext;

//    private List<CommitteeModel> committeeModelList;//定义数据。

    private int  resource;
    private LayoutInflater inflater = null;
    private ArrayList<HashMap<String,String>> Bill_list;



    /*
    定义构造器，在Activity创建对象Adapter的时候将数据data和Inflater传入自定义的Adapter中进行处理。
    */
    public extendAdapter2(Context context, ArrayList<HashMap<String,String>> data, int resource, String[] from, int[] to){

        super(context,data,resource,from, to);
//        mcontext=context;
        // Log.e(Integer.toString(objects.size()),"ssss");
        //  committeeModelList=objects;

//        Log.e(TAG, "Response2"+committeeModelList.get(0).getName());
//        Log.e(TAG, "Response2"+committeeModelList.get(1).getName());
//        Log.e(TAG, "Response2"+committeeModelList.get(2).getName());
        this.Bill_list=data;
        // this.resource =resource;
        thiscontext=context;
        inflater=(LayoutInflater)thiscontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.e("iiii", "Response3");





    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获得ListView中的view
        View k = convertView;
        //List<CommitteeModel> newList =  (ArrayList<CommitteeModel>)getItem(position);
        Log.e("ssisisi", "Response5");
        if(convertView==null){
            k = inflater.inflate(R.layout.bill_item,null);
        }
        //获得学生对象
        //Log.e(TAG, "Response4");
        final HashMap<String,Object> data = (HashMap<String, Object>) getItem(position);


        TextView bill_id = (TextView) k.findViewById(R.id.bill_id);
        TextView short_title = (TextView) k.findViewById(R.id.short_title);
        TextView introduced_on = (TextView) k.findViewById(R.id.introduced_on);


        LinearLayout row= (LinearLayout) k.findViewById(R.id.one_row2);
        row.setOnClickListener(new LinearLayout.OnClickListener(){
            @Override
            public void onClick(View v){
                Context main = v.getContext();
                Intent intent= new Intent(main, Bill_detail_Activity.class);
                intent.putExtra("bill_id",data.get("bill_id").toString());
                intent.putExtra("official_title",data.get("official_title").toString());
                intent.putExtra("bill_type",data.get("bill_type").toString());
                intent.putExtra("sponsor",data.get("sponsor").toString());
                intent.putExtra("chamber",data.get("chamber").toString());
                intent.putExtra("status",data.get("status").toString());
                intent.putExtra("introduced_on",data.get("introduced_on").toString());
//                intent.putExtra("Congress",data.get("Congress").toString());
                intent.putExtra("Version_Status",data.get("Version_Status").toString());
                intent.putExtra("url",data.get("url").toString());



                main.startActivity(intent);

            }
        });



        //将数据一一添加到自定义的布局中。
//        Log.e(committeeModelList.get(position).getName(), "Response4");
//        CommitteeModel obj = getItem(position);
//        HashMap<String,String> ll= (HashMap<String, String>) data.get(position);

        bill_id.setText((String)data.get("bill_id"));
        short_title.setText((String)data.get("official_title"));
        introduced_on.setText((String)data.get("introduced_on"));



        return k ;
    }

}
