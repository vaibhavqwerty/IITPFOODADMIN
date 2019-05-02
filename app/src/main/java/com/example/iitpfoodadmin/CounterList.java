package com.example.iitpfoodadmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.iitpfoodadmin.Verify.listOfList;
import static com.example.iitpfoodadmin.Verify.mMessageAdapter;

public class CounterList extends AppCompatActivity {
    private String qwerty1;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private FirebaseDatabase mFirebaseDatabasePay;
    private DatabaseReference mMessageDatabaseReferencePay;
    private FirebaseDatabase mFirebaseDatabaseC;
    private DatabaseReference mMessageDatabaseReferenceC;
    private ChildEventListener mChildEventListner;
    public static MessageAdapter mMessageAdapterQ;
    private ArrayAdapter<String> nameAdapter;
    private ListView mMessageListView;
    public static CounterAdapter mcounterAdapter;
    private ArrayList<String>mKey;
    private ArrayList<String>mOrder;
    private ArrayList<String>mDate;
    private ArrayList<String>mPhone;
    private ArrayList<String>mAddress;
    private ArrayList<String>mName;
    private ArrayList<String>mPrice;
    public static ArrayList<finalFoodList> listOfListQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_list);
        mMessageListView=findViewById(R.id.counterList);
        listOfListQ=new ArrayList<>();

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("Arya Services Counter");
        mFirebaseDatabasePay=FirebaseDatabase.getInstance();
        mMessageDatabaseReferencePay =mFirebaseDatabasePay.getReference().child("Arya Services Payment List");
        mFirebaseDatabaseC=FirebaseDatabase.getInstance();
        mMessageDatabaseReferenceC =mFirebaseDatabaseC.getReference().child("Arya Services Counter");

        final List<String> nameList=new ArrayList<>();
        nameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);

        mKey=new ArrayList<>();
        mOrder=new ArrayList<>();
        mPhone=new ArrayList<>();
        mDate=new ArrayList<>();
        mAddress=new ArrayList<>();
        mName=new ArrayList<>();
        mPrice=new ArrayList<>();
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
        mMessageListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pp=position;
                new AlertDialog.Builder(CounterList.this)
                        .setTitle("Sending Entry to Payment List")
                        .setMessage("Are you sure order is done?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                Toast.makeText(getApplicationContext(),mKey.get(pp),Toast.LENGTH_SHORT).show();
                                mMessageDatabaseReferencePay.child(mKey.get(pp)).setValue(listOfListQ.get(pp));
                                mMessageDatabaseReferencePay.child(mKey.get(pp)).child("OrderCode").setValue(mOrder.get(pp));
                                mMessageDatabaseReferencePay.child(mKey.get(pp)).child("Date and Time").setValue(mDate.get(pp));
                                mMessageDatabaseReferencePay.child(mKey.get(pp)).child("Phone").setValue(mPhone.get(pp));
                                mMessageDatabaseReferencePay.child(mKey.get(pp)).child("Address").setValue(mAddress.get(pp));
                                mMessageDatabaseReferencePay.child(mKey.get(pp)).child("UserName").setValue(mName.get(pp));
                                mMessageDatabaseReferencePay.child(mKey.get(pp)).child("Price").setValue(mPrice.get(pp));

                                mMessageDatabaseReferenceC.child(mKey.get(pp)).removeValue();
                                mKey.remove(pp);
                                mOrder.remove(pp);
                                mAddress.remove(pp);
                                mDate.remove(pp);
                                mPhone.remove(pp);
                                mName.remove(pp);
                                mPrice.remove(pp);
                                listOfListQ.remove(pp);
                                nameList.remove(pp);
                                nameAdapter.notifyDataSetChanged();

                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                //**********


                return true;
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
                qwerty1=dataSnapshot.getKey();
                //  Toast.makeText(getApplicationContext(),dataSnapshot.getKey(),Toast.LENGTH_SHORT).show();
                foodList f1=dataSnapshot.getValue(foodList.class);
                nameAdapter.add(order+"\n"+uname+"\n"+"Date & Time: "+dd+"\nPhone: "+ppp+"\nTotal Price: "+pr+"/-");
                mKey.add(f.getFoodListArrayList().get(0).getKey());
                mOrder.add(order);
                mAddress.add(sss);
                mPhone.add(ppp);
                mDate.add(dd);
                mName.add(uname);
                mPrice.add(pr);
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
        mMessageDatabaseReference.addChildEventListener(mChildEventListner);
    }
}
