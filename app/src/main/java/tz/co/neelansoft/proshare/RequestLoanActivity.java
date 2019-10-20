package tz.co.neelansoft.proshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RequestLoanActivity extends Activity {

    Spinner spLoanType;
    Spinner spLoanPeriod;
    EditText etLoanAmount;
    Button btnSummary;
    Bundle loanApplication = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_request_loan);

        spLoanPeriod = findViewById(R.id.spLoanPeriod);
        spLoanType = findViewById(R.id.spLoanType);
        etLoanAmount = findViewById(R.id.etLoanAmount);
        btnSummary = findViewById(R.id.btnSummary);

        final String[] loanTypes = getLoanTypes();
        final String[] loanPeriods = getLoanPeriods();

        if(savedInstanceState != null){
            loanApplication = savedInstanceState.getBundle("loan");

            int loanType = loanApplication.getInt("type");
            int loanPeriod = loanApplication.getInt("period");

            spLoanPeriod.setSelection(loanPeriod);
            spLoanType.setSelection(loanType);
            String loanAmount = loanApplication.getString("loan_amount");
            etLoanAmount.setText(loanAmount);
        }

        ArrayAdapter<String> loanTypeAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,loanTypes);
        ArrayAdapter<String> loanPeriodsAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,loanPeriods);

        loanPeriodsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loanTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spLoanType.setAdapter(loanTypeAdapter);
        spLoanPeriod.setAdapter(loanPeriodsAdapter);

        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double interest = .18 * Integer.parseInt(etLoanAmount.getText().toString());
                loanApplication.putString("loan_amount",etLoanAmount.getText().toString());
                loanApplication.putDouble("interest",interest);
                loanApplication.putInt("period",spLoanPeriod.getSelectedItemPosition());
                loanApplication.putInt("type",spLoanType.getSelectedItemPosition());

                startActivity(new Intent(RequestLoanActivity.this,ConfirmRequestActivity.class).putExtra("loan",loanApplication));
                finish();
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putBundle("loan",loanApplication);

        super.onSaveInstanceState(outState);
    }
    private String[] getLoanTypes() {
        return getResources().getStringArray(R.array.loan_types);
    }
    private String[] getLoanPeriods(){
        return getResources().getStringArray(R.array.loan_periods);
    }
}
