package tz.co.neelansoft.proshare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

		ivRepay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this,PaymentActivity.class));
			}
		});
		ivSignout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				signout();
			}
		});
		ivRequest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, RequestLoanActivity.class));
//				finish();
			}
		});

		ivCheck.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this,LoanStatusActivity.class));
			}
		});
	}

	private void signout(){
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
		alertBuilder.setMessage(R.string.confirm_exit)
				.setTitle(R.string.confirm_exit_title)
				.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						return;
					}
				})
				.setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						finish();
					}
				});
		if(!alertBuilder.create().isShowing()){
			alertBuilder.create().show();
		}

	}
}
