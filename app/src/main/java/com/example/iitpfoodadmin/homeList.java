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
    private FirebaseDatabase mFirebaseDatabasePay;
    private DatabaseReference mMessageDatabaseReferencePay;
    private FirebaseDatabase mFirebaseDatabaseC;
    private DatabaseReference mMessageDatabaseReferenceC;
    public static deliveryAdapter userAdapter;
    //private ArrayAdapter<String> nameAdapter;
    private ArrayAdapter<String> AddressAdapter;
    private ArrayAdapter<String> mobileAdapter;
    private ListView mMessageListView;
    public static CounterAdapter mdeliveryAdapter;
    public static ArrayList<finalFoodList> listOfListP;
    private ArrayList<userDelivery> userArrayList;
    private ArrayList<String>mKey;
    private ArrayList<String>mOrder;
    private ArrayList<String>mDate;
    private ArrayList<String>mPhone;
    private ArrayList<String>mAddress;
    private ArrayList<String>mName;
    private ArrayList<String>mPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_list);
        mMessageListView=findViewById(R.id.counterList);
        listOfListP=new ArrayList<>();
        userArrayList=new ArrayList<>();

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("Arya Services Home Delivery");
        mFirebaseDatabaseC=FirebaseDatabase.getInstance();
        mMessageDatabaseReferenceC =mFirebaseDatabaseC.getReference().child("Arya Services Home Delivery");

        mFirebaseDatabasePay=FirebaseDatabase.getInstance();
        mMessageDatabaseReferencePay =mFirebaseDatabasePay.getReference().child("Arya Services Payment List");
       // mFirebaseDatabase1=FirebaseDatabase.getInstance();
        //mMessageDatabaseReference1 =mFirebaseDatabase1.getReference().child("Arya Services Home Delivery Address");

        final List<String> nameList=new ArrayList<>();
        mKey=new ArrayList<>();
        mOrder=new ArrayList<>();
        mPhone=new ArrayList<>();
        mDate=new ArrayList<>();
        mAddress=new ArrayList<>();
        mName=new ArrayList<>();
        mPrice=new ArrayList<>();
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

        mMessageListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pp=position;
                new AlertDialog.Builder(homeList.this)
                        .setTitle("Sending Entry to Payment List")
                        .setMessage("Are you sure order is done?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                Toast.makeText(getApplicationContext(),mKey.get(pp),Toast.LENGTH_SHORT).show();
                                mMessageDatabaseReferencePay.child(mKey.get(pp)).setValue(listOfListP.get(pp));
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
                                listOfListP.remove(pp);
                                userArrayList.remove(pp);
                                userAdapter.notifyDataSetChanged();

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
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                finalFoodList f=dataSnapshot.getValue(finalFoodList.class);
                qwerty1=dataSnapshot.getKey();

                String Address=dataSnapshot.child("Address").getValue().toString();
                String order=dataSnapshot.child("OrderCode").getValue().toString();
                String dd=dataSnapshot.child("Date and Time").getValue().toString();
                //String ppp=dataSnapshot.child("Phone").getValue().toString();
                //String sss=dataSnapshot.child("Address").getValue().toString();
                String uname=dataSnapshot.child("UserName").getValue().toString();
                String pr=dataSnapshot.child("Price").getValue().toString();

          String Phoneno=dataSnapshot.child("Phone").getValue().toString();
                  //Toast.makeText(getApplicationContext(),Phoneno,Toast.LENGTH_SHORT).show();
                foodList f1=dataSnapshot.getValue(foodList.class);
                String name=order+"\n"+uname+"\nDate and Time: "+dd;
                userDelivery u=new userDelivery(name,"Address: "+Address,"Phone: "+Phoneno+"\nTotal Price:"+pr+"/-");
                listOfListP.add(f);
                mKey.add(f.getFoodListArrayList().get(0).getKey());
                mOrder.add(order);
                mAddress.add(Address);
                mPhone.add(Phoneno);
                mDate.add(dd);
                mName.add(uname);
                mPrice.add(pr);
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
