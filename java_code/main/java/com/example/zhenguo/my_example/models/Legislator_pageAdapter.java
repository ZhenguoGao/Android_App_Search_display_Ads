package com.example.zhenguo.my_example.models;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Vector;

/**
 * Created by Zhenguo on 16/11/23.
 */


import android.content.Context;
        import android.support.v4.view.PagerAdapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import java.util.List;
        import java.util.Vector;

public class Legislator_pageAdapter extends PagerAdapter {
    private Context context;
    private Vector<View> allpage;
    private LayoutInflater layoutInflater;
    public Legislator_pageAdapter(Context context, Vector<View> pages){
        this.context = context;
        this.allpage = pages;
    }
    @Override
    public int getCount() {
        return allpage.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View page=allpage.get(position);
        container.addView(page);
        return page;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
