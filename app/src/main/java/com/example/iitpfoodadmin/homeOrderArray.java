package com.example.iitpfoodadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import static com.example.iitpfoodadmin.CounterList.mcounterAdapter;
import static com.example.iitpfoodadmin.homeList.mdeliveryAdapter;

public class homeOrderArray extends AppCompatActivity {
    private ListView OrderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_order_array);
        OrderView=findViewById(R.id.homeArrayList);
//        final List<foodList> friendlyMessages = new ArrayList<>();
//         mMessageAdapter = new MessageAdapter(this, R.layout.final_list,friendlyMessages);
        OrderView.setAdapter(mdeliveryAdapter);

    }
}
