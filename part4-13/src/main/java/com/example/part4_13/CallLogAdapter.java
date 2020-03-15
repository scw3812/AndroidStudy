package com.example.part4_13;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

public class CallLogAdapter extends ArrayAdapter<CallLogVO> {
    Context context;
    int resId;
    ArrayList<CallLogVO> datas;

    public CallLogAdapter(Context context, int resId, ArrayList<CallLogVO> datas){
        super(context, resId);
        this.context = context;
        this.resId = resId;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);
            CallLogWrapper wrapper = new CallLogWrapper(convertView);
            convertView.setTag(wrapper);
        }
        CallLogWrapper wrapper = (CallLogWrapper)convertView.getTag();

        ImageView personImageView = wrapper.personImageView;
        TextView nameView = wrapper.nameView;
        TextView dateVew = wrapper.dateView;
        ImageView dialerImageView = wrapper.dialerImageView;

        final CallLogVO vo = datas.get(position);

        nameView.setText(vo.name);
        dateVew.setText(vo.date);

        if(vo.photo != null && vo.photo.equals("yes")){
            personImageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.hong, null));
        }else{
            personImageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_person, null));
        }
        if(vo.phone != null && !vo.phone.equals("")){
            dialerImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + vo.phone));
                    context.startActivity(intent);
                }
            });
        }
        return convertView;
    }
}
