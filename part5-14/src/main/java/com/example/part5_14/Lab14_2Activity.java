package com.example.part5_14;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;


public class Lab14_2Activity extends AppCompatActivity implements View.OnClickListener{
    Button contactsBtn;
    Button cameraDataBtn;
    Button cameraFileBtn;
    Button speechBtn;
    Button mapBtn;
    Button browserBtn;
    Button callBtn;

    TextView resultView;
    ImageView resultImageView;

    File filePath;

    int reqWidth;
    int reqHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab14_2);

        resultView=findViewById(R.id.resultView);
        contactsBtn=findViewById(R.id.btn_contacts);
        cameraDataBtn=findViewById(R.id.btn_camera_data);
        cameraFileBtn=findViewById(R.id.btn_camera_file);
        speechBtn=findViewById(R.id.btn_speech);
        mapBtn=findViewById(R.id.btn_map);
        browserBtn=findViewById(R.id.btn_browser);
        callBtn=findViewById(R.id.btn_call);
        resultImageView=findViewById(R.id.resultImageView);

        contactsBtn.setOnClickListener(this);
        cameraDataBtn.setOnClickListener(this);
        cameraFileBtn.setOnClickListener(this);
        speechBtn.setOnClickListener(this);
        mapBtn.setOnClickListener(this);
        browserBtn.setOnClickListener(this);
        callBtn.setOnClickListener(this);
        resultImageView.setOnClickListener(this);

        reqWidth = getResources().getDimensionPixelSize(R.dimen.request_image_width);
        reqHeight = getResources().getDimensionPixelSize(R.dimen.request_image_height);
    }

    @Override
    public void onClick(View v) {
        if(v == contactsBtn){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            startActivityForResult(intent, 10);
        }else if(v == cameraDataBtn){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 30);
        }else if(v == cameraFileBtn){
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED){
                try{
                    String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myApp";
                    File dir = new File(dirPath);
                    if(!dir.exists()){
                        dir.mkdir();
                    }
                    filePath = File.createTempFile("IMG", ".jpg", dir);
                    if(!filePath.exists()){
                        filePath.createNewFile();
                    }

                    Uri photoURI = FileProvider.getUriForFile(Lab14_2Activity.this,
                            BuildConfig.APPLICATION_ID + ".provider", filePath);
                    Intent intent = new Intent(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(intent, 40);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
