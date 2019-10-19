package tz.co.neelansoft.proshare;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOError;
import java.io.IOException;

public class UploadIdActivity extends Activity {

    ImageView ivUploadImage;
    ImageView ivClose;
    ImageView ivImage;
    Button btnNext;
    private final int RC_CAMERA = 1;
    Bitmap uploadedImage;
    Bundle userDetails  = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_upload_id);

        ivUploadImage = findViewById(R.id.ivAddImageID);
        ivClose = findViewById(R.id.ivClose);
        btnNext = findViewById(R.id.btnNext);
        ivImage = findViewById(R.id.ivUploadedImage);

        if(getIntent().hasExtra("detail")){
            userDetails = getIntent().getBundleExtra("detail");
        }
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UploadIdActivity.this,LoginActivity.class));
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    ByteArrayOutputStream baus = new ByteArrayOutputStream();
                    uploadedImage.compress(Bitmap.CompressFormat.PNG,50,baus);
                    byte[] imageByte = baus.toByteArray();
                    userDetails.putByteArray("id_card",imageByte);
                startActivity(new Intent(UploadIdActivity.this,PasswordActivity.class).putExtra("detail",userDetails));
            }
        });
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    //check if permission granted
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        captureImage();
                    }
                    else{
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, RC_CAMERA);
                    }
                }
            }
        });
        ivUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    //check if permission granted
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        captureImage();
                    }
                    else{
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, RC_CAMERA);
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case RC_CAMERA:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(UploadIdActivity.this, "Permission granted",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode == RC_CAMERA){
            uploadedImage = (Bitmap) data.getExtras().get("data");
            ivImage.setImageBitmap(uploadedImage);
            ivImage.setVisibility(View.VISIBLE);
            ivUploadImage.setVisibility(View.GONE);
        }
    }
    private void captureImage(){
        Toast.makeText(UploadIdActivity.this,"Capture Image",Toast.LENGTH_LONG).show();
        startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),RC_CAMERA);
    }
}
