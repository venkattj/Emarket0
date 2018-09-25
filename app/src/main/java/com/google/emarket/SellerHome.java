package com.google.emarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SellerHome extends AppCompatActivity {

    private FirebaseAuth auth;
    private ImageButton Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);
        auth=FirebaseAuth.getInstance();
        Logout=(ImageButton)findViewById(R.id.ibLogout);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Toast.makeText(SellerHome.this, "Loging you out!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SellerHome.this,Home.class);
                startActivity(intent);
            }
        });
    }
}
