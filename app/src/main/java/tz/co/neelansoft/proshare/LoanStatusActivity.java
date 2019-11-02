package tz.co.neelansoft.proshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

import tz.co.neelansoft.proshare.Models.LoanInstallment;
import tz.co.neelansoft.proshare.Utils.LoanInstallmentRecyclerAdapter;

public class LoanStatusActivity extends Activity{
    private RecyclerView recyclerView;
    private TextView tvAmountTotal;
    private TextView tvLoanStatus;
    private ImageView ivClose;
    LoanInstallmentRecyclerAdapter adapter;
    ArrayList<LoanInstallment> loanInstallments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loan_status);
        ivClose = findViewById(R.id.ivClose);
        recyclerView = findViewById(R.id.recyclerView);
        tvAmountTotal = findViewById(R.id.tvAmountTotal);
        tvLoanStatus = findViewById(R.id.tvLabelLoanStatus);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Bundle details = new Bundle();
        details.putString("loan_id","LOAN-1-2019");
        details.putDouble("total",3750000);
        details.putDouble("interest",750000);
        details.putString("status","Approved");

        tvAmountTotal.setText(String.format("%.2f",details.getDouble("total")));
        tvLoanStatus.setText(details.getString("status"));

LoanInstallment li1 = new LoanInstallment("1",1000000,250000,0,0, new Date().getTime()+60*60*24*30*1000);
li1.setLoanDetails(details);
        loanInstallments.add(li1);
        LoanInstallment li2 = new LoanInstallment("2",1000000,250000,0,0, new Date().getTime()+60*60*24*30*2*1000);
        li1.setLoanDetails(details);
        loanInstallments.add(li2);
        LoanInstallment li3 = new LoanInstallment("3",1000000,250000,0,0, new Date().getTime()+60*60*24*30*3*1000);
        li1.setLoanDetails(details);
        loanInstallments.add(li3);

        adapter = new LoanInstallmentRecyclerAdapter(this,loanInstallments);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
//
//    @Override
//    public void onItemClick(String itemId) {
//        Toast.makeText(LoanStatusActivity.this, "Clicked item "+itemId,Toast.LENGTH_LONG).show();
//        startActivity(new Intent(LoanStatusActivity.this,PaymentActivity.class));
//        finish();
//    }
}
