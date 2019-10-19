package tz.co.neelansoft.proshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Created by Landry Kapela on 2019-10-17.
 */
public class SignupActivity extends Activity implements AdapterView.OnItemSelectedListener{
	
	Spinner spIdType;
	EditText etIdNumber;
	Button btnNext;
	ConstraintLayout clIDNumber;
	String[] idTypes = new String[]{};
	boolean hasNationalId = false;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_signup);
		
		spIdType = findViewById(R.id.spIdType);
		clIDNumber = findViewById(R.id.clIDNumber);
		etIdNumber = findViewById(R.id.etIDNumber);
		btnNext = findViewById(R.id.btnNext);

		idTypes = getResources().getStringArray(R.array.idtypes);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,idTypes);
		adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
		spIdType.setAdapter(adapter);

		spIdType.setOnItemSelectedListener(this);

		if(savedInstanceState != null){
			spIdType.setSelection(savedInstanceState.getInt("id_index"));
			etIdNumber.setText(savedInstanceState.getString("id"));
		}

		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Bundle userDetail = new Bundle();
				userDetail.putString("id",etIdNumber.getText().toString());
				if(hasNationalId){
					String[] details = requestNidaInfo(etIdNumber.getText().toString());
					userDetail.putString("fname",details[0]);
					userDetail.putString("mname",details[1]);
					userDetail.putString("lname",details[2]);
					userDetail.putString("dob",details[3]);
					userDetail.putString("email",details[4]);
					userDetail.putString("phone",details[5]);
					userDetail.putString("address",details[6]);
					userDetail.putString("dob",details[7]);
					userDetail.putBoolean("has_nida",hasNationalId);
				}
				startActivity(new Intent(SignupActivity.this,Signup2Activity.class).putExtra("detail",userDetail));

			}
		});
	}

	@Override
	public void onSaveInstanceState(Bundle outState){
		outState.putString("id",etIdNumber.getText().toString());
		outState.putBoolean("has_nida",hasNationalId);
		outState.putInt("id_index",spIdType.getSelectedItemPosition());
		super.onSaveInstanceState(outState);
	}
	@Override
	public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
		clIDNumber.setVisibility(View.VISIBLE);
		switch(i){
			case 0:
				hasNationalId = true;
				etIdNumber.setHint(R.string.nida_id_hint);
				break;
			case 1:
				hasNationalId = false;
				etIdNumber.setHint(R.string.driver_id_hint);
				break;
			case 2:
				hasNationalId = false;
				etIdNumber.setHint(R.string.voter_id_hint);
				break;
			default:

				hasNationalId = false;
				etIdNumber.setHint(R.string.id_hint);
				break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> adapterView) {

	}

	private String[] requestNidaInfo(String id){
		Toast.makeText(this,R.string.nida_get, Toast.LENGTH_LONG).show();
		return new String[]{"Landry","Kampangala","Kapela","02091984","landrykapela@gmail.com","+255784086726","Boko Basihaya\nKinondoni\nDar es Salaam","02-09-1984",id};
	}
}
