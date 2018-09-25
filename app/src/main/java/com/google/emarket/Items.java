package com.google.emarket;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Items extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText name,price;
    private ProgressBar progressBar;
    private ListView listView;
    private FirebaseDatabase mdatabase;
    private DatabaseReference mdatabaseref;
    private List<Item> list = new ArrayList<>();
    private Item selecteditem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Items");
        name=(EditText)findViewById(R.id.name);
        price=(EditText)findViewById(R.id.price);
        listView=(ListView)findViewById(R.id.list_data);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Item item =(Item)parent.getItemAtPosition(position);
                selecteditem=item;
                name.setText(selecteditem.getName());
                price.setText(selecteditem.getPrice());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        initFirebase();
        addEventListioner();
        setSupportActionBar(toolbar);
    }

    private void addEventListioner() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
        mdatabaseref.child(" items ").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(list.size()>0)
                    list.clear();
                    for (DataSnapshot postsnapsort:dataSnapshot.getChildren())
                    {
                        Item item=postsnapsort.getValue(Item.class);
                        list.add(item);
                    }

                    ListViewAdapter listViewAdapter= new ListViewAdapter(Items.this,list);
                    listView.setAdapter(listViewAdapter);
                    progressBar.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        mdatabase=FirebaseDatabase.getInstance();
        mdatabaseref=mdatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.menu_add)
        {
            createItem();
        }
        else if(item.getItemId()==R.id.menu_save)
        {
            Item item1=new Item(selecteditem.getUid(),name.getText().toString(),price.getText().toString());
            update(item1);
        }
        else if(item.getItemId()==R.id.menu_delete)
        {
            deleteItem(selecteditem);
        }
        return true;
    }

    private void deleteItem(Item selecteditem) {
        mdatabaseref.child("items").child(selecteditem.getUid()).removeValue();
        clearEdittext();
    }

    private void update(Item item) {
        mdatabaseref.child("items").child(item.getUid()).child("name").setValue(item.getName());
        mdatabaseref.child("items").child(item.getUid()).child("price").setValue(item.getName());
        clearEdittext();

    }

    private void createItem() {

        Item item = new Item(UUID.randomUUID().toString(),name.getText().toString(),price.getText().toString());
        mdatabaseref.child("items").child(item.getUid()).setValue(item);
        clearEdittext();
    }

    private void clearEdittext() {
        name.setText("");
        price.setText("");
    }


}

