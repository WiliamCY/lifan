package com.example.qqche.cy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qqche.cy.Class.MyCapitaRecord;
import com.example.qqche.cy.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/23.
 */

public class MyCapitaRecordAdapter extends XRecyclerView.Adapter<MyCapitaRecordAdapter.ViewHolder>{
    private Context mContext;
    private List<MyCapitaRecord> mmyCapitaRecords;
    static  class ViewHolder extends XRecyclerView.ViewHolder{
        TextView crAmountofcollection,crAllTotal,crbalance,crTotal,crblockedBalances,crMessage;
        CardView cardView;
        public  ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            crAmountofcollection = view.findViewById(R.id.capital_crAmountofcollection);
            crAllTotal = view.findViewById(R.id.crAllTotal);
            crbalance = view.findViewById(R.id.crbalance);
            crTotal = view.findViewById(R.id.crTotal);
            crblockedBalances = view.findViewById(R.id.crblockedBalances);
            crMessage = view.findViewById(R.id.crMessage);
        }

    }
   public MyCapitaRecordAdapter(List<MyCapitaRecord> myCapitaRecordss){
        mmyCapitaRecords = myCapitaRecordss;

   }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if(mContext == null){
           mContext = parent.getContext();
       }
       View view  = LayoutInflater.from(mContext).inflate(R.layout.capital_recored,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       MyCapitaRecord myCapitaRecord = mmyCapitaRecords.get(position);
       holder.crAmountofcollection.setText(myCapitaRecord.getCrAmountofcollection());
       holder.crAllTotal.setText(myCapitaRecord.getCrAllTotal());
       holder.crbalance.setText(myCapitaRecord.getCrbalance());
       holder.crTotal.setText(myCapitaRecord.getCrTotal());
       holder.crblockedBalances.setText(myCapitaRecord.getCrblockedBalances());
       holder.crMessage.setText(myCapitaRecord.getCrMessage());

    }

    @Override
    public int getItemCount() {
        return mmyCapitaRecords.size();
    }


}
