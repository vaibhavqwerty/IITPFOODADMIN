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

public class History extends AppCompatActivity {

    private ChildEventListener mChildEventListner;
    private FirebaseDatabase mFirebaseDatabasePay;
    private DatabaseReference mMessageDatabaseReferencePay;
    private ArrayAdapter<String> nameAdapter;
    private ListView mMessageListView;
    public static CounterAdapter HHPayAdapter;
    public static ArrayList<finalFoodList> listOfListHH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mMessageListView=findViewById(R.id.PayListHis);

        mFirebaseDatabasePay=FirebaseDatabase.getInstance();
        mMessageDatabaseReferencePay =mFirebaseDatabasePay.getReference().child("Arya Services Payment History");
        listOfListHH=new ArrayList<>();
        final List<String> nameList=new ArrayList<>();
        nameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);
        mMessageListView.setAdapter(nameAdapter);


        mMessageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String  f=nameList.get(position);
                Intent intent = new Intent(getBaseContext(), HistoryArray.class);
//        intent.putExtra("EXTRA_FOOD_NAME", f.getFoodName());
//        intent.putExtra("EXTRA_FOOD_PRICE", f.getFoodPrice());
//        intent.putExtra("EXTRA_PRICE",f.getPrice());
                HHPayAdapter = new CounterAdapter(History.this, R.layout.counter_list, listOfListHH.get(position).getFoodListArrayList());
                startActivity(intent);

            }
        });

        mChildEventListner= new ChildEventListener() {
            int ii=0;
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                finalFoodList f=dataSnapshot.getValue(finalFoodList.class);
                String order=dataSnapshot.child("OrderCode").getValue().toString();
                String dd=dataSnapshot.child("Date and Time").getValue().toString();
                String ppp=dataSnapshot.child("Phone").getValue().toString();
                String sss=dataSnapshot.child("Address").getValue().toString();
                String uname=dataSnapshot.child("UserName").getValue().toString();
                String pr=dataSnapshot.child("Price").getValue().toString();
                // qwerty1=dataSnapshot.getKey();
                //  Toast.makeText(getApplicationContext(),dataSnapshot.getKey(),Toast.LENGTH_SHORT).show();
                foodList f1=dataSnapshot.getValue(foodList.class);
                nameAdapter.add(order+"\n"+uname+"\nDate and Time: "+dd+"\nPhone: "+ppp+"\nAddress: "+sss+"\nTotal Price: "+pr+"/-");

                listOfListHH.add(f);
//                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
//                    String key=childSnapshot.getKey();
//                    Log.i(TAG,key);
//                }
//                mMessageAdapter.add(f.getFoodListArrayList().get(0));
//                friendlyMessages = f.getFoodListArrayList();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //finalFoodList f=dataSnapshot.getValue(finalFoodList.class);
                //Toast.makeText(getApplicationContext(),dataSnapshot.getKey()+"yo",Toast.LENGTH_SHORT).show();

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
        mMessageDatabaseReferencePay.addChildEventListener(mChildEventListner);
    }

}
