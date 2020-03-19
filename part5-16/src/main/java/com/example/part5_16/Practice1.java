package com.example.part5_16;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Practice1 extends AppCompatActivity implements View.OnClickListener{

    RelativeLayout layout;
    ImageView plusImageView;
    File filePath;

    int reqWidth;
    int reqHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice1);

        layout = (RelativeLayout)findViewById(R.id.main_content);
        plusImageView = (ImageView)findViewById(R.id.main_photo_icon);

        plusImageView.setOnClickListener(this);

        reqWidth = getResources().getDimensionPixelSize(R.dimen.request_image_width);
        reqHeight = getResources().getDimensionPixelSize(R.dimen.request_image_height);
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("촬영");
        builder.setItems(R.array.main_dialog_list, dialogListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            showDialog();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showDialog();
            }else{
                showToast("no permission");
            }
        }
    }

    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if(i == 0){
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

                    Uri photoURI = FileProvider.getUriForFile(Practice1.this, BuildConfig.APPLICATION_ID + ".provider", filePath);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(intent, 10);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else if(i == 1){
                try{
                    String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myApp";
                    File dir = new File(dirPath);
                    if(!dir.exists()){
                        dir.mkdir();
                    }
                    filePath = File.createTempFile("VIDEO", ".mp4", dir);
                    if(!filePath.exists()){
                        filePath.createNewFile();
                    }

                    Uri videoURI = FileProvider.getUriForFile(Practice1.this, BuildConfig.APPLICATION_ID + ".provider", filePath);
                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                    intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 20);
                    intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 1024*1024*10);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, videoURI);
                    startActivityForResult(intent, 20);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK) {
            if(filePath != null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                try{
                    InputStream in = new FileInputStream(filePath);
                    BitmapFactory.decodeStream(in, null, options);
                    in.close();
                    in = null;
                }catch (Exception e){
                    e.printStackTrace();
                }

                final int height = options.outHeight;
                final int width = options.outWidth;
                int inSampleSize = 1;

                if(height > reqWidth || width > reqWidth){
                    final int heightRatio = Math.round((float)height / (float) reqHeight);
                    final int widthRatio = Math.round((float)width / (float)reqWidth);

                    inSampleSize = heightRatio < widthRatio? heightRatio : widthRatio;
                }
                BitmapFactory.Options imgOptions = new BitmapFactory.Options();
                imgOptions.inSampleSize = inSampleSize;
                Bitmap bitmap = BitmapFactory.decodeFile(filePath.getAbsolutePath(), imgOptions);

                ImageView imageView = new ImageView(this);
                imageView.setImageBitmap(bitmap);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layout.addView(imageView, params);
            }
        } else if (requestCode == 20 && resultCode == RESULT_OK) {
            if(filePath != null){
                VideoView videoView = new VideoView(this);
                videoView.setMediaController(new MediaController(this));
                Uri vieoUri = Uri.parse(filePath.getAbsolutePath());

                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                Bitmap bmp = null;
                retriever.setDataSource(filePath.getAbsolutePath());
                bmp = retriever.getFrameAtTime();
                int videoHeight = bmp.getHeight();
                int videoWidth = bmp.getWidth();

                videoView.setVideoURI(vieoUri);

                if(videoWidth >= videoHeight){
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(reqWidth, reqHeight);
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    layout.addView(videoView, params);
                }else{
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(reqHeight, reqWidth);
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    layout.addView(videoView, params);
                }

                videoView.start();
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("filePath", filePath.getAbsolutePath());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            String path = savedInstanceState.getString("filePath");
            if(path != null){
                filePath = new File(path);
            }
        }
    }
}
