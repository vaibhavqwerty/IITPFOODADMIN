package com.example.iitpfoodadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class Verify extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener mChildEventListner;
    private ListView mMessageListView;
    public static MessageAdapter mMessageAdapter;
    private ArrayAdapter<String> nameAdapter;
    public static ArrayList<finalFoodList> listOfList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);


        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("Arya Services");
        mMessageListView=findViewById(R.id.adminlist);
        final List<foodList> friendlyMessages = new ArrayList<>();
        listOfList=new ArrayList<>();
        final List<String> nameList=new ArrayList<>();
        //mMessageAdapter = new MessageAdapter(this, R.layout.final_list, friendlyMessages);
        nameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);
        mMessageListView.setAdapter(nameAdapter);

mMessageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String  f=nameList.get(position);
     Intent intent = new Intent(getBaseContext(), consumerOrder.class);
//        intent.putExtra("EXTRA_FOOD_NAME", f.getFoodName());
//        intent.putExtra("EXTRA_FOOD_PRICE", f.getFoodPrice());
//        intent.putExtra("EXTRA_PRICE",f.getPrice());
        mMessageAdapter = new MessageAdapter(Verify.this, R.layout.final_list, listOfList.get(position).getFoodListArrayList());
      startActivity(intent);

    }
});


        mChildEventListner= new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                finalFoodList f=dataSnapshot.getValue(finalFoodList.class);
               foodList f1=dataSnapshot.getValue(foodList.class);
                nameAdapter.add(f.getFoodListArrayList().get(0).getName());
                listOfList.add(f);
//                mMessageAdapter.add(f.getFoodListArrayList().get(0));
//                friendlyMessages = f.getFoodListArrayList();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mMessageDatabaseReference.addChildEventListener(mChildEventListner);
    }
}
