package com.example.manguostore;

import android.widget.EditText;
import android.widget.TextView;

public class POJO_user {
    public String brandName;
    public String email;
    public String id;

    public POJO_user(TextView brandName, String email, String id){

    }

    public POJO_user(String brandName, String email, String id) {
        this.brandName = brandName;
        this.email = email;
        this.id= id;
    }
}
