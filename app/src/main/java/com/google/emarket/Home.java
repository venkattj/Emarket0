package com.google.emarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

   private ImageButton aboutus,seller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        aboutus = (ImageButton)findViewById(R.id.ibAbout);
        seller=(ImageButton)findViewById(R.id.ibSeller);
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Aboutus.class);
                startActivity(intent);
            }
        });
        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,SellerLogin.class);
                startActivity(intent);
            }
        });

    }
}
