package com.example.manguostore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class LandingOwnerActivity extends AppCompatActivity {

    String name_menu[] = {"user_details","help","faq","logout","exit"};
    CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_owner);

        //For the circle menu
        circleMenu = (CircleMenu) findViewById(R.id.menu_owner);
        //Now to put images inside the menus
        circleMenu.setMainMenu(Color.parseColor("#4554a4"),R.drawable.ic_menu,R.drawable.ic_remove)
                .addSubMenu(Color.parseColor("#4554a4"),R.drawable.ic_account_click)
                .addSubMenu(Color.parseColor("#4554a4"),R.drawable.ic_help)
                .addSubMenu(Color.parseColor("#4554a4"),R.drawable.ic_question)
                .addSubMenu(Color.parseColor("#4554a4"),R.drawable.ic_question)
                .addSubMenu(Color.parseColor("#4554a4"),R.drawable.ic_cancel)

                // add click listener
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        if(i==0){
                            Intent l = new Intent(LandingOwnerActivity.this, AccountActivity.class);
                            startActivity(l);
                        }else if(i==1){
                            Intent m = new Intent(LandingOwnerActivity.this, HelpActivity.class);
                            startActivity(m);
                        }else if(i==2){

                        }else if(i==3){

                        }else if(i==4){

                        }
                    }
                });
    }

    public void myStore(View view) {
        Intent intent = new Intent(LandingOwnerActivity.this,StoreActivity.class);
        startActivity(intent);
    }
}
