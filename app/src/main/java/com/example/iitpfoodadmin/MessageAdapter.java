package com.example.iitpfoodadmin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<foodList> {
    public MessageAdapter(Context context, int resource, List<foodList> objects) {
        super(context, resource, objects);
    }

//    public interface BtnClickListener {
//        public abstract void onBtnClick(int position);
//    }
//
//    private BtnClickListener mClickListener = null;
//    public MessageAdapter(Context context, List<foodList> lista, BtnClickListener listener) {
//        mContext = context;
//        mLayoutInflater = (LayoutInflater) mContext
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        listaPerfiles=lista;
//        mClickListener = listener;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = convertView;
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.final_list, parent, false);
        }

        //ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView quantityTextView = (TextView) convertView.findViewById(R.id.quantityTextView);
        TextView totalPriceTextView = (TextView) convertView.findViewById(R.id.totalPriceTextView);

        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        Button b=(Button)convertView.findViewById(R.id.correct);
        foodList message = getItem(position);
//         b.setTag(position);
//         b.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 view.setBackgroundResource(R.drawable.greencolor);
//
//             }
//         });

        messageTextView.setVisibility(View.VISIBLE);
        //photoImageView.setVisibility(View.GONE);
        messageTextView.setText(message.getText());
        quantityTextView.setText(message.getQuantity());
        totalPriceTextView.setText(message.getTotalPrice());
        authorTextView.setText(message.getName());

        return convertView;
    }
}

