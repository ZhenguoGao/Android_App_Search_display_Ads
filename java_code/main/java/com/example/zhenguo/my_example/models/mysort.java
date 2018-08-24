package com.example.zhenguo.my_example.models;

import java.util.Comparator;
import java.util.HashMap;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Zhenguo on 16/12/1.
 */


class mysort implements Comparator<HashMap<String,String>> {

    @Override
    public int compare(HashMap<String,String> e1, HashMap<String,String> e2) {
        return e1.get("name").compareToIgnoreCase(e1.get("name"));

    }
}