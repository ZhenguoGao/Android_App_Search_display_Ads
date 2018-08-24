package com.example.zhenguo.my_example.models;

/**
 * Created by Zhenguo on 16/11/21.
 */

public class CommitteeModel {
    String id;
    String name;
    String email;
    public CommitteeModel(String id,String name,String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
