package tz.co.neelansoft.proshare.Models;

import android.os.Bundle;

public class LoanInstallment {

    private String id;
    private double principal;
    private int status;
    private double interest;
    private double fees;
    private long dueDate;

    private String loanId;
    private double totalLoanAmount;
    private double totalLoanInterest;


    public Bundle getLoanDetails(){
        Bundle bundle = new Bundle();
        bundle.putString("loan_id",loanId);
        bundle.putDouble("total",totalLoanAmount);
        bundle.putDouble("interest",totalLoanInterest);

        return bundle;
    }

    public LoanInstallment(String id, double principal, double interest,double fees,int status, long dueDate) {
        this.id = id;
        this.principal = principal;
        this.interest = interest;
        this.fees = fees;
        this.status = status;
        this.dueDate = dueDate;
    }
    public void setLoanDetails(Bundle details){
        this.loanId = details.getString("loan_id");
        this.totalLoanAmount = details.getDouble("total");
        this.totalLoanInterest = details.getDouble("interest");
    }
    public void setLoanDetails(String id, double amount, double interest){
        this.loanId = id;
        this.totalLoanAmount = amount;
        this.totalLoanInterest = interest;
    }
    public double getFees() {
        return fees;
    }
public void setInterest(double interest){
        this.interest = interest;
}
public double getInterest(){
        return this.interest;
}
    public void setFees(double fees) {
        this.fees = fees;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
