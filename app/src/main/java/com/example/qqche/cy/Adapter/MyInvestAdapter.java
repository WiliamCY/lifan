package com.example.qqche.cy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qqche.cy.Class.MyInvest;
import com.example.qqche.cy.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/23.
 */

public class MyInvestAdapter extends XRecyclerView.Adapter<MyInvestAdapter.ViewHolder> {
    private Context mContext;
    private List<MyInvest> mmyInvests;
    static  class ViewHolder extends  XRecyclerView.ViewHolder{
        CardView cardView;
        ImageView investImage;
        TextView investtenderAmount,amountofRebate,investTime;
        public  ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            investImage = view.findViewById(R.id.my_invest_background);
            investtenderAmount = view.findViewById(R.id.my_invest_e1);
            amountofRebate  = view.findViewById(R.id.my_invest_e2);
            investTime = view.findViewById(R.id.my_invest_time);
        }
    }
    public  MyInvestAdapter(List<MyInvest> myInvests){
        mmyInvests = myInvests;
    }
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_invset,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyInvest myInvest = mmyInvests.get(position);
        holder.investtenderAmount.setText(myInvest.getTenderAmount());
        holder.amountofRebate.setText(myInvest.getAmountofRebate());
        holder.investTime.setText(myInvest.getInvestTime());
        Glide.with(mContext).load(myInvest.getInveestImage()).into(holder.investImage);

    }

    @Override
    public int getItemCount() {
        return mmyInvests.size();
    }


}
