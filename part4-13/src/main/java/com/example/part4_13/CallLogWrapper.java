package com.example.part4_13;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CallLogWrapper {
    public ImageView personImageView;
    public TextView nameView;
    public TextView dateView;
    public ImageView dialerImageView;
    public CallLogWrapper(View root){
        personImageView = root.findViewById(R.id.item_person);
        nameView = root.findViewById(R.id.itme_name);
        dateView = root.findViewById(R.id.item_date);
        dialerImageView = root.findViewById(R.id.item_dialer);
    }
}
