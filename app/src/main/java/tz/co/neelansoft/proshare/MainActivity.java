package tz.co.neelansoft.proshare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
	boolean firstTime = true;
	ImageView ivSettings;
	ImageView ivSignout;
	ImageView ivRequest;
	ImageView ivCheck;
	ImageView ivRepay;
	
	@Override
	protected void onStart(){
		super.onStart();
		if(firstTime){
		startActivity(new Intent(this,LoginActivity.class));
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if(getIntent().hasExtra("first_run")){
			firstTime = getIntent().getBooleanExtra("first_run",false);
		}

		ivCheck = findViewById(R.id.ivStatus);
		ivRepay = findViewById(R.id.ivPay);
		ivRequest = findViewById(R.id.ivRequest);
		ivSettings = findViewById(R.id.ivSettings);
		ivSignout = findViewById(R.id.ivExit);
	}
}
