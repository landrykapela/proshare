package tz.co.neelansoft.proshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class PasswordActivity extends Activity {

    EditText etPassword;
    Button btnFinish;
    ImageView ivClose;
    Bundle userDetails = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_password);

        btnFinish = findViewById(R.id.btnFinish);
        etPassword = findViewById(R.id.etPassword);
        ivClose = findViewById(R.id.ivClose);

        if(getIntent().hasExtra("detail")){
            userDetails = getIntent().getBundleExtra("detail");
        }

        ivClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(PasswordActivity.this,LoginActivity.class));
                finish();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDetails.putString("password",etPassword.getText().toString());
                sendRegistration(userDetails);
            }
        });
    }

    private void sendRegistration(Bundle userDetails){
        Log.e("Password","Registration complete");
        Intent i = new Intent(PasswordActivity.this,MainActivity.class);
        i.putExtra("detail",userDetails);
        i.putExtra("first_run",false);
        startActivity(i);
        finish();
    }
}
