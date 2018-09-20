package com.example.qqche.cy.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qqche.cy.Class.RebateRequirement;
import com.example.qqche.cy.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class RebateMainRequirementAdapter extends RecyclerView.Adapter<RebateMainRequirementAdapter.ViewHolder> {
    private static final String TAG = "RebateMainRequirementAdapter";
    private Context mContext;
    private List<RebateRequirement> rebateRequirements;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }

    static  class  ViewHolder extends RecyclerView.ViewHolder{
        TextView borrow_limit,borrow_apr,fanli_type,retender,invest_area,remark;
        public ViewHolder(View view) {
            super(view);
            borrow_limit = view.findViewById(R.id.borrow_limit);
            borrow_apr = view.findViewById(R.id.borrow_apr);
            fanli_type = view.findViewById(R.id.fanli_type);
            retender = view.findViewById(R.id.retender);
            invest_area = view.findViewById(R.id.invest_area);
            remark = view.findViewById(R.id.remarks);
        }
    }
    public  RebateMainRequirementAdapter(List<RebateRequirement> list){
        rebateRequirements = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rebate_tab3,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;


        //        if(mContext == null){
//            mContext = parent.getContext();
//        }
//        View view = LayoutInflater.from(mContext).inflate(R.layout.rebate_tab3,parent,false);
//        return new ViewHolder(view);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
    RebateRequirement lists = rebateRequirements.get(position);
    holder.invest_area.setText(lists.getInvest_area());
    holder.fanli_type.setText(lists.getFanli_type());
    holder.borrow_apr.setText(lists.getBorrow_apr());
    holder.borrow_limit.setText(lists.getBorrow_limit());
    String c = lists.getRemark();
    Log.i(TAG,"onBindViewHolder"+c);
    holder.remark.setText(lists.getRemark());
    if(lists.getRetender().equals("0")){
        holder.retender.setText("不支持复投");
    }else {
        holder.retender.setText("支持复投");
    }
        holder.retender.setText(lists.getRetender());
        if(mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
        if(mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                    return true;
                }
            });
        }

    }

    @SuppressLint("LongLogTag")
    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount =" + rebateRequirements.size());
        return rebateRequirements.size();

    }


}
