package tz.co.neelansoft.proshare.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tz.co.neelansoft.proshare.LoanStatusActivity;
import tz.co.neelansoft.proshare.Models.LoanInstallment;
import tz.co.neelansoft.proshare.PaymentActivity;
import tz.co.neelansoft.proshare.R;

public class LoanInstallmentRecyclerAdapter extends RecyclerView.Adapter<LoanInstallmentRecyclerAdapter.ViewHolder>{

    private Context context;
    private ArrayList<LoanInstallment> loanList = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(String itemId);
    }
    @Override
    public int getItemCount(){
        return loanList.size();
    }
    public LoanInstallmentRecyclerAdapter(Context context, ArrayList<LoanInstallment> list) {
        this.context = context;
        this.loanList = list;
//        this.listener = listener;
    }

    public ArrayList<LoanInstallment> getListItems(){
        return this.loanList;
    }
    public void setListItems(ArrayList<LoanInstallment> listItems){
        this.loanList = listItems;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(context).inflate(R.layout.loan_recyclerview_item,parent,false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder vh, int index){
        vh.bind(index);
    }



        public class ViewHolder extends RecyclerView.ViewHolder{

            private TextView tvInstallmentName;
            private TextView tvInstallmentPrincipal;
            private TextView tvInstallmentInterest;
            private TextView tvInstallmentFee;
            private TextView tvInstallmentAmount;
            private TextView tvInstallmentDate;

            private Button btnRepay;

        public ViewHolder(@NonNull View itemView) {
                super(itemView);

                tvInstallmentAmount = itemView.findViewById(R.id.tvInstallmentAmount);
                tvInstallmentFee = itemView.findViewById(R.id.tvInstallmentFee);
                tvInstallmentInterest = itemView.findViewById(R.id.tvInstallmentInterest);
                tvInstallmentName = itemView.findViewById(R.id.tvInstallmentName);
                tvInstallmentPrincipal = itemView.findViewById(R.id.tvInstallmentPrincipal);
                tvInstallmentDate = itemView.findViewById(R.id.tvInstallmentDate);
                btnRepay = itemView.findViewById(R.id.btnRepay);

            }

            public void bind(int position){

                LoanInstallment loan = getListItems().get(position);
                String name = "Installment " + loan.getId();
                tvInstallmentAmount.setText(String.format("%.2f",(loan.getInterest() + loan.getPrincipal() + loan.getFees())));
                tvInstallmentPrincipal.setText(String.format("%.2f",(loan.getPrincipal())));
                tvInstallmentName.setText(name);
                tvInstallmentInterest.setText(String.format("%.2f",(loan.getInterest())));
                tvInstallmentFee.setText(String.format("%.2f",loan.getFees()));
                tvInstallmentDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date(loan.getDueDate())));

                btnRepay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"Clicked Repay button", Toast.LENGTH_LONG).show();
                        context.startActivity(new Intent(context, PaymentActivity.class));

                    }
                });
            }

//            @Override
//            public void onClick(View view){
//                LoanInstallment li = getListItems().get(getAdapterPosition());
//                listener.onItemClick(li.getId());
//            }
        }
}