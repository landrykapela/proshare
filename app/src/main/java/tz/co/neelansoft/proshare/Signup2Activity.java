package tz.co.neelansoft.proshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Signup2Activity extends Activity {

    EditText etFName;
    EditText etMName;
    EditText etLName;
    EditText etIDNumber;
    EditText etEmail;
    EditText etPhone;
    EditText etDob;
    EditText etAddress;

    Button btnNext;
    Button btnPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup_2);

//        Bundle userDetail = new Bundle();
        etFName = findViewById(R.id.etFName);
        etMName = findViewById(R.id.etMName);
        etLName = findViewById(R.id.etLName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etMobile);
        etAddress = findViewById(R.id.etAddress);
        etIDNumber = findViewById(R.id.etIdNumber);
        etDob = findViewById(R.id.etDob);

        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnBack);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        if(intent.hasExtra("detail")){
            final Bundle userDetail = intent.getBundleExtra("detail");

            etIDNumber.setText(userDetail.getString("id"));
            if(userDetail.getBoolean("has_nida")){
                etIDNumber.setEnabled(false);
                etFName.setText(userDetail.getString("fname"));
                etFName.setEnabled(false);
                etMName.setText(userDetail.getString("mname"));
                etMName.setEnabled(false);
                etLName.setText(userDetail.getString("lname"));
                etLName.setEnabled(false);
                etEmail.setText(userDetail.getString("email"));
                etEmail.setEnabled(false);
                etPhone.setText(userDetail.getString("phone"));
                etPhone.setEnabled(false);
                etAddress.setText(userDetail.getString("address"));
                etAddress.setEnabled(false);
            }
            else{
                userDetail.putString("fname",etFName.getText().toString());
                userDetail.putString("mname",etMName.getText().toString());
                userDetail.putString("lname",etLName.getText().toString());
                userDetail.putString("dob",etDob.getText().toString());
                userDetail.putString("email",etEmail.getText().toString());
                userDetail.putString("phone",etPhone.getText().toString());
                userDetail.putString("address",etAddress.getText().toString());
                userDetail.putBoolean("has_nida",false);
            }
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Signup2Activity.this,UploadIdActivity.class).putExtra("detail",userDetail));
                }
            });
        }
    }
}
