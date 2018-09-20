package com.example.qqche.cy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qqche.cy.Class.RebateMessageItem;
import com.example.qqche.cy.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/27.
 */

public class RebateMessageItemAdapter extends XRecyclerView.Adapter<RebateMessageItemAdapter.ViewHolder>{
    private Context mContexr;
    private List<RebateMessageItem> mrebateMessageItem;
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
    static  class  ViewHolder extends XRecyclerView.ViewHolder {
        TextView rebateH1,rebateH2;
        public ViewHolder(View view) {
            super(view);
            rebateH1 = view.findViewById(R.id.rebt_h1s);
            rebateH2 = view.findViewById(R.id.rebt_h2s);
        }
    }
    public RebateMessageItemAdapter(List<RebateMessageItem> rebateMessageItems){
        mrebateMessageItem = rebateMessageItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContexr == null){
            mContexr = parent.getContext();
        }
        View view = LayoutInflater.from(mContexr).inflate(R.layout.rebate_messgae_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        RebateMessageItem rebateMessageItem = mrebateMessageItem.get(position);
        holder.rebateH1.setText(rebateMessageItem.getRebateH1());
        holder.rebateH2.setText(rebateMessageItem.getRebateH2());
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

    @Override
    public int getItemCount() {
        return mrebateMessageItem.size();
    }


}
