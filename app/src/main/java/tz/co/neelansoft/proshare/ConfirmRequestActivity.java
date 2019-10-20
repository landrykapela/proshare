package tz.co.neelansoft.proshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ConfirmRequestActivity extends Activity {

    private TextView tvLoanAmount;
    private TextView tvInterestAmount;
    private TextView tvInstallment;
    private TextView tvLoanType;
    private TextView tvTotalAmount;
    private Button btnConfirm;
    private ProgressBar progressBar;
    private Bundle loan = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirm_request);

        tvInstallment = findViewById(R.id.tvInstallment);
        tvLoanAmount = findViewById(R.id.tvLoanAmount);
        tvInterestAmount = findViewById(R.id.tvInterestAmount);
        tvLoanType = findViewById(R.id.tvLoanType);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        btnConfirm = findViewById(R.id.btnConfirm);
        progressBar = findViewById(R.id.progressBar);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                sendLoanRequest();
            }
        });

        if(getIntent().hasExtra("loan")){
            loan = getIntent().getBundleExtra("loan");

            String[] types = getResources().getStringArray(R.array.loan_types);
            String[] periods = getResources().getStringArray(R.array.loan_periods);

            String period = periods[loan.getInt("period")];
            Log.e("ConfirmRequest",period.split(" ")[0]);
            int installments = Integer.parseInt(period.split(" ")[0]);
            double interest = loan.getDouble("interest");
            double loan_amount = Double.parseDouble(Objects.requireNonNull(loan.getString("loan_amount")));
            String loanType = types[loan.getInt("type")];

            String monthlyInstallment = String.valueOf((loan_amount + interest)/installments);
            tvLoanAmount.setText(String.valueOf(loan_amount));
            tvInstallment.setText(monthlyInstallment);
            tvInterestAmount.setText(String.valueOf(interest));
            tvLoanType.setText(loanType);
            tvTotalAmount.setText(String.valueOf(loan_amount + interest));
        }

    }

    private void sendLoanRequest() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ConfirmRequestActivity.this,"Application Sent",Toast.LENGTH_LONG).show();
                Intent i = new Intent(ConfirmRequestActivity.this,MainActivity.class);
                i.putExtra("loan",loan);
                i.putExtra("first_run",false);
                startActivity(i);
                finish();
            }
        },1000);
    }
}
