
package com.example.zhenguo.my_example.models;

        import android.content.Context;
        import android.content.Intent;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import com.example.zhenguo.my_example.Legislator_detail_Activity;
        import com.example.zhenguo.my_example.R;
        import com.squareup.picasso.Picasso;

        import java.util.ArrayList;
        import java.util.HashMap;

        import android.widget.SimpleAdapter;
/**
 * Created by Zhenguo on 16/11/22.
 */

public class extendAdapter1 extends SimpleAdapter {
    private Context thiscontext;

//    private List<CommitteeModel> committeeModelList;//定义数据。

    private int  resource;
    private LayoutInflater inflater = null;
    private ArrayList<HashMap<String,String>> legislate_list;



    /*
    定义构造器，在Activity创建对象Adapter的时候将数据data和Inflater传入自定义的Adapter中进行处理。
    */
    public extendAdapter1(Context context, ArrayList<HashMap<String,String>> data, int resource, String[] from, int[] to){

        super(context,data,resource,from, to);
//        mcontext=context;
        // Log.e(Integer.toString(objects.size()),"ssss");
        //  committeeModelList=objects;

//        Log.e(TAG, "Response2"+committeeModelList.get(0).getName());
//        Log.e(TAG, "Response2"+committeeModelList.get(1).getName());
//        Log.e(TAG, "Response2"+committeeModelList.get(2).getName());
        this.legislate_list=data;
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
            k = inflater.inflate(R.layout.legislate_item,null);
        }
        //获得学生对象
        //Log.e(TAG, "Response4");
        final HashMap<String,Object> data = (HashMap<String, Object>) getItem(position);


        TextView name = (TextView) k.findViewById(R.id.name);
        ImageView bioguide_id = (ImageView) k.findViewById(R.id.bioguide_id);
        TextView other = (TextView) k.findViewById(R.id.other);
        LinearLayout row= (LinearLayout) k.findViewById(R.id.one_row);
        row.setOnClickListener(new LinearLayout.OnClickListener(){
            @Override
            public void onClick(View v){
                Context main = v.getContext();
                Intent intent= new Intent(main, Legislator_detail_Activity.class);
                intent.putExtra("name",data.get("name").toString());
                intent.putExtra("email",data.get("email").toString());
                intent.putExtra("chamber",data.get("chamber").toString());
                intent.putExtra("contact",data.get("contact").toString());
                intent.putExtra("start_term",data.get("start_term").toString());
                intent.putExtra("end_term",data.get("end_term").toString());
                intent.putExtra("Office",data.get("Office").toString());
                intent.putExtra("state",data.get("state").toString());
                intent.putExtra("fax",data.get("fax").toString());
                intent.putExtra("birthday",data.get("birthday").toString());
                intent.putExtra("url",data.get("url").toString());
                intent.putExtra("facebook",data.get("facebook").toString());
                intent.putExtra("twitter",data.get("twitter").toString());
                intent.putExtra("website",data.get("website").toString());
                intent.putExtra("id",data.get("id").toString());
                intent.putExtra("party",data.get("party").toString());
                intent.putExtra("state_name",data.get("state_name").toString());

                intent.putExtra("district",data.get("district").toString());







                main.startActivity(intent);

            }
        });



        //将数据一一添加到自定义的布局中。
//        Log.e(committeeModelList.get(position).getName(), "Response4");
//        CommitteeModel obj = getItem(position);
//        HashMap<String,String> ll= (HashMap<String, String>) data.get(position);
        String url=(String)data.get("url");
        Picasso.with(thiscontext).load(url).into(bioguide_id);
        name.setText((String)data.get("name"));

        other.setText((String)"("+data.get("party")+")"+(String)data.get("state_name")+"-"+(String)data.get("state_name")+"district"+" "+(String)data.get("district"));

        return k ;
    }

}
