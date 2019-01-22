package com.cao.nang.lab3nangcao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity implements View.OnClickListener {
    private Button btnContact;
    private Button btnAppsting;
    private Button btnMedia;
    private Button btnCalllog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnContact = (Button) findViewById(R.id.btnContact);
        btnAppsting = (Button) findViewById(R.id.btnAppsting);
        btnMedia = (Button) findViewById(R.id.btnMedia);
        btnCalllog = (Button) findViewById(R.id.btnCalllog);
        btnContact.setOnClickListener(this);
        btnCalllog.setOnClickListener(this);
        btnMedia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.btnContact){
            Intent intent=new Intent(menu.this,MainActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.btnCalllog){
            Intent intent=new Intent(menu.this,showCallLog.class);
            startActivity(intent)  ;
        }
        else if(id==R.id.btnMedia){
            Intent intent=new Intent(menu.this,mediastore.class);
            startActivity(intent)  ;
        }
    }
}
