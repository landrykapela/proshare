package tz.co.neelansoft.proshare.Models;

public class Loan {

    private String id;
    private double principal;
    private int period;
    private double rate;
    private int status;

    private int date_created;

    public Loan(String id, double principal, int period, double rate, int status, int date_created) {
        this.id = id;
        this.principal = principal;
        this.period = period;
        this.rate = rate;
        this.status = status;
        this.date_created = date_created;
    }

    public int getDate_created() {
        return date_created;
    }

    public void setDate_created(int date_created) {
        this.date_created = date_created;
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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
