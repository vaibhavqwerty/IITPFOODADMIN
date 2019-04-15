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

public class CounterList extends AppCompatActivity {
    private String qwerty1;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener mChildEventListner;
    public static MessageAdapter mMessageAdapterQ;
    private ArrayAdapter<String> nameAdapter;
    private ListView mMessageListView;
    public static CounterAdapter mcounterAdapter;
    public static ArrayList<finalFoodList> listOfListQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_list);
        mMessageListView=findViewById(R.id.counterList);
        listOfListQ=new ArrayList<>();

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("Arya Services Counter");

        final List<String> nameList=new ArrayList<>();
        nameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);


        mMessageListView.setAdapter(nameAdapter);

        mMessageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String  f=nameList.get(position);
                Intent intent = new Intent(getBaseContext(), CounterOrderArray.class);
//        intent.putExtra("EXTRA_FOOD_NAME", f.getFoodName());
//        intent.putExtra("EXTRA_FOOD_PRICE", f.getFoodPrice());
//        intent.putExtra("EXTRA_PRICE",f.getPrice());
                mcounterAdapter = new CounterAdapter(CounterList.this, R.layout.counter_list, listOfListQ.get(position).getFoodListArrayList());
                startActivity(intent);

            }
        });
        mChildEventListner= new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                finalFoodList f=dataSnapshot.getValue(finalFoodList.class);
                qwerty1=dataSnapshot.getKey();
                //  Toast.makeText(getApplicationContext(),dataSnapshot.getKey(),Toast.LENGTH_SHORT).show();
                foodList f1=dataSnapshot.getValue(foodList.class);
                nameAdapter.add(f.getFoodListArrayList().get(0).getName());
                listOfListQ.add(f);
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
    }
}
