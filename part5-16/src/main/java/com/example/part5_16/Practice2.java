package com.example.part5_16;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Practice2 extends AppCompatActivity implements View.OnClickListener{

    ImageView callImg, locationImg, internetImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice2);

        callImg = (ImageView)findViewById(R.id.mission2_call);
        locationImg = (ImageView)findViewById(R.id.mission2_location);
        internetImg = (ImageView)findViewById(R.id.mission2_internet);

        callImg.setOnClickListener(this);
        locationImg.setOnClickListener(this);
        internetImg.setOnClickListener(this);

    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if(view == callImg){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:02-120"));
                startActivity(intent);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
            }
        }else if(view == locationImg){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.5662952,126.9779451?q=37.5662952,126.9779451"));
            startActivity(intent);
        }else if(view == internetImg) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wwww.naver.com"));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:02-120"));
                    startActivity(intent);
                }
            }else{
                showToast("no permission");
            }
        }
    }
}
