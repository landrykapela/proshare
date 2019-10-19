package tz.co.neelansoft.proshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Landry Kapela on 2019-10-16.
 */
public class LoginActivity extends Activity {
	
	Button btnLogin;
	TextView tvSignup;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		
		btnLogin = findViewById(R.id.btnLogin);
		tvSignup = findViewById(R.id.tvSignup);
		
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("first_run",false));
				finish();
			}
		});
		
		tvSignup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(LoginActivity.this, SignupActivity.class));
				finish();
			}
		});
	}
}
