package com.example.qqche.cy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qqche.cy.Class.Rebate;
import com.example.qqche.cy.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25.
 */

public class RebateAdapter extends XRecyclerView.Adapter<RebateAdapter.ViewHolder> {
    private Context mContext;
    private List<Rebate> mrebates;
    private FileAdapter.OnItemClickListener mOnItemClickListener;
    private FileAdapter.OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(FileAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(FileAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }
 static class  ViewHolder extends  XRecyclerView.ViewHolder{
      TextView rebate_interest,rebate_name,rebate_image_size,rebate_h1,rebate_h2,rebate_h3;
      ImageView rebateImage;
     public ViewHolder(View view) {
         super(view);
         rebate_interest = view.findViewById(R.id.rebate_interest);
         rebate_name = view.findViewById(R.id.rebate_name);
         rebate_image_size = view.findViewById(R.id.rebate_image_size);
         rebate_h1 = view.findViewById(R.id.rebate_h1);
         rebate_h2 = view.findViewById(R.id.rebate_h2);
         rebate_h3 = view.findViewById(R.id.rebate_h3);
         rebateImage = view.findViewById(R.id.rebate_logo);
     }
 }
 public RebateAdapter(List<Rebate> rebates){
     mrebates = rebates;
 }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     if(mContext == null){
         mContext = parent.getContext();
     }
     View view = LayoutInflater.from(mContext).inflate(R.layout.rebate,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Rebate rebate = mrebates.get(position);
        holder.rebate_interest.setText(rebate.getRebate_interest());
        holder.rebate_name.setText(rebate.getRebate_name());
        holder.rebate_image_size.setText(rebate.getRebate_image_size());
        holder.rebate_h1.setText(rebate.getRebate_h1());
        holder.rebate_h2.setText(rebate.getRebate_h2());
        holder.rebate_h3.setText(rebate.getRebate_h3());
//        Glide.with(mContext).load(rebate.getRebateImage()).into(holder.rebateImage);
        Glide.with(mContext).load(rebate.getRebateImage()).into(holder.rebateImage);
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

        return mrebates.size();
    }


}
