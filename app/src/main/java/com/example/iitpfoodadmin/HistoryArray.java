package com.example.iitpfoodadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import static com.example.iitpfoodadmin.History.HHPayAdapter;

public class HistoryArray extends AppCompatActivity {
    private ListView OrderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_array);
        OrderView=findViewById(R.id.PaymentArrayListH);
        OrderView.setAdapter(HHPayAdapter);
    }
}
