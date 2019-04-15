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
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.iitpfoodadmin.Verify.listOfList;

public class homeList extends AppCompatActivity {
    private String qwerty1;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener mChildEventListner;
    public static deliveryAdapter userAdapter;
    //private ArrayAdapter<String> nameAdapter;
    private ArrayAdapter<String> AddressAdapter;
    private ArrayAdapter<String> mobileAdapter;
    private ListView mMessageListView;
    public static CounterAdapter mdeliveryAdapter;
    public static ArrayList<finalFoodList> listOfListP;
    private ArrayList<userDelivery> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_list);
        mMessageListView=findViewById(R.id.counterList);
        listOfListP=new ArrayList<>();
        userArrayList=new ArrayList<>();

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("Arya Services Home Delivery");
       // mFirebaseDatabase1=FirebaseDatabase.getInstance();
        //mMessageDatabaseReference1 =mFirebaseDatabase1.getReference().child("Arya Services Home Delivery Address");

        final List<String> nameList=new ArrayList<>();
       // nameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);

        userAdapter=new deliveryAdapter(this,R.layout.user_list,userArrayList);
        mMessageListView.setAdapter(userAdapter);

//        mMessageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String  f=nameList.get(position);
//                Intent intent = new Intent(getBaseContext(), homeOrderArray.class);
////        intent.putExtra("EXTRA_FOOD_NAME", f.getFoodName());
////        intent.putExtra("EXTRA_FOOD_PRICE", f.getFoodPrice());
////        intent.putExtra("EXTRA_PRICE",f.getPrice());
//                mdeliveryAdapter = new CounterAdapter(homeList.this, R.layout.counter_list, listOfListP.get(position).getFoodListArrayList());
//                startActivity(intent);
//
//            }
//        });
        mMessageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), homeOrderArray.class);
//        intent.putExtra("EXTRA_FOOD_NAME", f.getFoodName());
//        intent.putExtra("EXTRA_FOOD_PRICE", f.getFoodPrice());
//        intent.putExtra("EXTRA_PRICE",f.getPrice());
                mdeliveryAdapter = new CounterAdapter(homeList.this, R.layout.counter_list, listOfListP.get(position).getFoodListArrayList());
                startActivity(intent);

            }
        });
        mChildEventListner= new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                finalFoodList f=dataSnapshot.getValue(finalFoodList.class);
                qwerty1=dataSnapshot.getKey();

                String Address=dataSnapshot.child("Address").getValue().toString();


          String Phoneno=dataSnapshot.child("Mobile").getValue().toString();
                  //Toast.makeText(getApplicationContext(),Phoneno,Toast.LENGTH_SHORT).show();
                foodList f1=dataSnapshot.getValue(foodList.class);
                String name=f.getFoodListArrayList().get(0).getName();
                userDelivery u=new userDelivery(name,Address,Phoneno);
                listOfListP.add(f);
                userArrayList.add(u);
//                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
//                    String key=childSnapshot.getKey();
//                    Log.i(TAG,key);
//                }
//                mMessageAdapter.add(f.getFoodListArrayList().get(0));
//                friendlyMessages = f.getFoodListArrayList();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                finalFoodList f=dataSnapshot.getValue(finalFoodList.class);
                Toast.makeText(getApplicationContext(),dataSnapshot.getKey()+"yo",Toast.LENGTH_SHORT).show();

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

//        mChildEventListner1= new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//               // finalFoodList f=dataSnapshot.getValue(finalFoodList.class);
//                String Address=dataSnapshot.child("Address").getValue().toString();
//                String Phoneno=dataSnapshot.child("Mobile").getValue().toString();
//                qwerty1=dataSnapshot.getKey();
//                AddressAdapter.add(Address);
//                mobileAdapter.add(Phoneno);
//                //  Toast.makeText(getApplicationContext(),dataSnapshot.getKey(),Toast.LENGTH_SHORT).show();
//                //foodList f1=dataSnapshot.getValue(foodList.class);
//               // nameAdapter.add(f.getFoodListArrayList().get(0).getKey());
//                //listOfListP.add(f);
////                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
////                    String key=childSnapshot.getKey();
////                    Log.i(TAG,key);
////                }
////                mMessageAdapter.add(f.getFoodListArrayList().get(0));
////                friendlyMessages = f.getFoodListArrayList();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                finalFoodList f=dataSnapshot.getValue(finalFoodList.class);
//                //Toast.makeText(getApplicationContext(),dataSnapshot.getKey()+"yo",Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
//        mMessageDatabaseReference1.addChildEventListener(mChildEventListner1);
    }
}
