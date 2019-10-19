package tz.co.neelansoft.proshare;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Created by Landry Kapela on 2019-10-17.
 */
public class SignupActivity extends AppCompatActivity {
	
	Spinner spIdType;
	EditText etIdNumber;
	Button btnNext;
	ConstraintLayout clIDNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_signup);
		
		spIdType = findViewById(R.id.spIdType);
		clIDNumber = findViewById(R.id.clIDNumber);
		etIdNumber = findViewById(R.id.etIDNumber);
		btnNext = findViewById(R.id.btnNext);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<Strin>();g
	
	}
}
