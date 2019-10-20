package tz.co.neelansoft.proshare;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class UploadProofActivity extends Activity {

    private ImageView ivClose;
    private ImageView ivUploadFile;
    private Button btnUpload;
    private TextView tvLabelProof;
    private ProgressBar pbUpload;
    private final int PICK_FILE_REQUEST_CODE = 2;
    private final int PICK_FILE_RESULT_CODE = 3;
    private String filepath;
    Bundle loan = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_upload_proof);

        btnUpload = findViewById(R.id.btnUpload);
        ivClose = findViewById(R.id.ivClose);
        ivUploadFile = findViewById(R.id.ivUploadFile);
        tvLabelProof = findViewById(R.id.tvLabelUploadProof);
        pbUpload = findViewById(R.id.uploadProgress);

        if(getIntent().hasExtra("loan")){
            loan = getIntent().getBundleExtra("loan");
        }
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbUpload.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pbUpload.setVisibility(View.GONE);
                        Toast.makeText(UploadProofActivity.this,"Proof document uploaded",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(UploadProofActivity.this,MainActivity.class);
                        loan.putString("proof",filepath);
                        i.putExtra("loan",loan);
                        i.putExtra("first_run",false);
                        startActivity(i);
                        finish();
                    }
                },1000);
            }
        });

        ivUploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        uploadFile();
                    }
                    else{
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PICK_FILE_REQUEST_CODE);
                    }

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch(requestCode){
            case PICK_FILE_REQUEST_CODE:
                if(permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(UploadProofActivity.this, "Permission granted",Toast.LENGTH_LONG).show();
                    uploadFile();
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case PICK_FILE_RESULT_CODE:
                if(resultCode == -1){
                    Uri fileUri = data.getData();
                    filepath = fileUri.getPath();
                    ivUploadFile.setImageResource(R.drawable.ic_insert_drive_file_black_24dp);
                    tvLabelProof.setText(filepath);
                }
        }
    }
    private void uploadFile() {

//        Toast.makeText(UploadProofActivity.this,"")
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("image/*");
        chooseFile = Intent.createChooser(chooseFile,"Select file");
        startActivityForResult(chooseFile,PICK_FILE_RESULT_CODE);
    }
}
