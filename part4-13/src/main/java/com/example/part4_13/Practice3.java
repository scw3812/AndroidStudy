package com.example.part4_13;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Practice3 extends AppCompatActivity implements AdapterView.OnItemClickListener, TextWatcher {
    EditText editText;
    ListView listView;

    ArrayList<String> base;
    ArrayList<SpannableStringBuilder> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice3);

        editText = (EditText)findViewById(R.id.practice_edit);
        listView = (ListView)findViewById(R.id.practice_list);

        listView.setOnItemClickListener(this);
        editText.addTextChangedListener(this);

        base = new ArrayList<>();
        base.add("우편번호 search");
        base.add("지도 search");
        base.add("채법원 나의 사건 search");
        base.add("주소 search");
        base.add("다음지도 search");
        base.add("search 등록");

        datas = new ArrayList<>();

        ArrayAdapter<SpannableStringBuilder> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        editText.setText(datas.get(i).toString());
        ArrayAdapter<SpannableStringBuilder> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        datas = new ArrayList<>();
        for(String txt : base){
            if(txt.contains(charSequence)){
                int startPos = txt.indexOf(charSequence.toString());
                int endPos = startPos + charSequence.length();
                SpannableStringBuilder builder = new SpannableStringBuilder(txt);
                builder.setSpan(new ForegroundColorSpan(Color.BLUE), startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.setSpan(new StyleSpan(Typeface.BOLD), startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                datas.add(builder);
            }
            ArrayAdapter<SpannableStringBuilder> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
