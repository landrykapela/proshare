package tz.co.neelansoft.proshare.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tz.co.neelansoft.proshare.Models.Loan;
import tz.co.neelansoft.proshare.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class LoanRecyclerAdapter extends RecyclerView.Adapter<LoanRecyclerAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Loan> loanList = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int id);
    }
    @Override
    public int getItemCount(){
        return loanList.size();
    }
    public LoanRecyclerAdapter(Context context, ArrayList<Loan> list, OnItemClickListener listener) {
        this.context = context;
        this.loanList = list;
        this.listener = listener;
    }

    public ArrayList<Loan> getListItems(){
        return this.loanList;
    }
    public void setListItems(ArrayList<Loan> listItems){
        this.loanList = listItems;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(context).inflate(R.layout.loan_item,parent,false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder vh, int index){
        vh.bind(index);
    }



        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }

            public void bind(int position){

            }

            @Override
            public void onClick(View view){
                listener.onItemClick(getAdapterPosition());
            }
        }
}