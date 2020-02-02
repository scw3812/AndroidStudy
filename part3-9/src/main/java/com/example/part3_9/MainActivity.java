package com.example.part3_9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText contentView;
    Button btn;

    boolean fileReadPermission, fileWritePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentView = (EditText)findViewById(R.id.content);
        btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            fileReadPermission = true;
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            fileWritePermission = true;
        }

        if(!fileReadPermission || !fileWritePermission){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 200 && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                fileReadPermission = true;
            }
            if(grantResults[1] == PackageManager.PERMISSION_GRANTED){
                fileWritePermission = true;
            }
        }
    }

    @Override
    public void onClick(View view) {
        String content = contentView.getText().toString();

        if(fileReadPermission && fileWritePermission){
            FileWriter fileWriter;
            try{
                String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myApp";
                File dir = new File(dirPath);
                if(!dir.exists()){
                    dir.mkdir();
                }

                File file = new File(dir + "/myfile.txt");
                if(!file.exists()){
                    file.createNewFile();
                }

                fileWriter = new FileWriter(file, true);
                fileWriter.write(content);
                fileWriter.flush();
                fileWriter.close();

                Intent intent = new Intent(this, ReadFileActivity.class);
                startActivity(intent);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "permission이 부여 안되어 기능을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}