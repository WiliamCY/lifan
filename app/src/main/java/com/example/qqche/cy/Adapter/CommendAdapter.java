package com.example.qqche.cy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qqche.cy.Class.Commend;
import com.example.qqche.cy.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2.
 */

public class CommendAdapter extends XRecyclerView.Adapter<CommendAdapter.ViewHolder> {
    private Context mContext;
    private List<Commend> mCommend;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener
                                                   mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }
    static  class ViewHolder extends  XRecyclerView.ViewHolder{
        TextView commendH1,commendH2,commendH3,commendH4;
        ImageView commendImage;
        public ViewHolder(View view) {
            super(view);
            commendH1  = view.findViewById(R.id.commend_h1);
            commendH2  = view.findViewById(R.id.commend_h2);
            commendH3 = view.findViewById(R.id.commend_h3);
            commendH4 = view.findViewById(R.id.commend_h4);
            commendImage = view.findViewById(R.id.commend_image);
        }
    }
    public  CommendAdapter(List<Commend> commends){
        mCommend = commends;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.information_commend,parent,false);
     return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Commend commend = mCommend.get(position);
        holder.commendH1.setText(commend.getCommendH1());
        holder.commendH2.setText(commend.getCommendH2());
        holder.commendH3.setText(commend.getCommendH3());
        holder.commendH4.setText(commend.getCommendH4());
        Glide.with(mContext).load(commend.getCommendImage()).into(holder.commendImage);
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
                    mOnItemLongClickListener.onItemLongClick
                            (holder.itemView,position);
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mCommend.size();
    }


}
