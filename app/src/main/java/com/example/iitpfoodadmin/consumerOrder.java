package com.example.iitpfoodadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.iitpfoodadmin.Verify.mMessageAdapter;

public class consumerOrder extends AppCompatActivity {
private ListView OrderView;
    //private MessageAdapter mMessageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_order);
        OrderView=findViewById(R.id.orderOfConsumer);
//        final List<foodList> friendlyMessages = new ArrayList<>();
//         mMessageAdapter = new MessageAdapter(this, R.layout.final_list,friendlyMessages);
       OrderView.setAdapter(mMessageAdapter);

    }
}
