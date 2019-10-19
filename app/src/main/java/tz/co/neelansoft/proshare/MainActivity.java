package tz.co.neelansoft.proshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
	boolean firstTime = true;
	
	@Override
	protected void onStart(){
		super.onStart();
		if(firstTime){
		
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
}
