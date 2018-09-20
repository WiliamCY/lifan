package com.example.qqche.cy.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qqche.cy.R;

import java.util.ArrayList;
import java.util.List;


public class ExpandFoldTextAdapter extends RecyclerView.Adapter<ExpandFoldTextAdapter.MyViewHolder> {

    private Activity mContent;
    private final int MAX_LINE_COUNT = 1;
    private  int status = 0;
    private final int STATE_UNKNOW = -1;
    private final int STATE_NOT_OVERFLOW = 1;
    private final int STATE_COLLAPSED = 2;
    private final int STATE_EXPANDED = 3;
    private SparseArray<Integer> mTextStateList;
    private List<ExpandFoldTextBean> mList;
    private OnItemClickListener mOnItemClickListener;



    public ExpandFoldTextAdapter(List<ExpandFoldTextBean> list, Activity context) {
        mContent = context;
        this.mList = list;
        mTextStateList = new SparseArray<>();
    }


    public void notifyAdapter(List<ExpandFoldTextBean> myLiveList, boolean isAdd) {
        if (!isAdd) {
            this.mList = myLiveList;
        } else {
            this.mList.addAll(myLiveList);
        }
        notifyDataSetChanged();
    }
    public List<ExpandFoldTextBean> getMyLiveList() {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        return mList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expand_fold_text,parent,false);
        return  new MyViewHolder(itemView);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        int state = mTextStateList.get(mList.get(position).getId(), STATE_UNKNOW);
        if (state == STATE_UNKNOW) {
            holder.content.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    holder.content.getViewTreeObserver().removeOnPreDrawListener(this);
                    if (holder.content.getLineCount() > MAX_LINE_COUNT) {
                        holder.content.setMaxLines(MAX_LINE_COUNT);
                        mTextStateList.put(mList.get(position).getId(), STATE_COLLAPSED);
                    } else {
                    }
                    return true;
                }
            });

            holder.content.setMaxLines(Integer.MAX_VALUE);
            holder.content.setText(mList.get(position).getContent());
        } else {
            switch (state) {
                case STATE_NOT_OVERFLOW:
                    break;
                case STATE_COLLAPSED:
                    holder.content.setMaxLines(MAX_LINE_COUNT);
                    break;
                case STATE_EXPANDED:
                    holder.content.setMaxLines(Integer.MAX_VALUE);
                    break;
            }
            holder.content.setText(mList.get(position).getContent());
        }

        holder.expandOrFold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int state = mTextStateList.get(mList.get(position).getId(), STATE_UNKNOW);
                if (state == STATE_COLLAPSED) {
                    holder.content.setMaxLines(Integer.MAX_VALUE);
                    holder.my_time_1.setTextColor(mContent.getResources().getColor(R.color.orgin));
                    holder.expandOrFold.animate().setDuration(1000).rotation(180).start();
                    mTextStateList.put(mList.get(position).getId(), STATE_EXPANDED);
                } else if (state == STATE_EXPANDED) {
                    holder.content.setMaxLines(MAX_LINE_COUNT);
                    holder.my_time_1.setTextColor(mContent.getResources().getColor(R.color.textColor));
                    holder.expandOrFold.animate().setDuration(1000).rotation(360).start();
                }
            }
        });

        final ExpandFoldTextBean list = mList.get(holder.getAdapterPosition());
        holder.nickname.setText(list.getTv_nickname());
        holder.content.setText(list.getContent());
        holder.my_time_1.setText(list.getMy_time());
        if(list.isSelect()){
            holder.my_time_check.setImageResource(R.mipmap.my_time_select_two);
        }else {
            holder.my_time_check.setImageResource(R.mipmap.my_time_select_one);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClickListener(holder.getAdapterPosition(),mList);
                holder.my_time_check.setImageResource(R.mipmap.my_time_select_two);
            }
        });

        if(status != 0) {
            holder.my_time_check.setVisibility(View.VISIBLE);
            holder.my_time_1.setVisibility(View.GONE);
        }else {
            holder.my_time_check.setVisibility(View.GONE);
            holder.my_time_1.setVisibility(View.VISIBLE);
        }

    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    public void isChangeImage(int status){
        this.status = status;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClickListener(int pos,List<ExpandFoldTextBean> myLiveList);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nickname;
        public TextView content;
        public ImageView expandOrFold;
        public TextView my_time_1;
        public ImageView my_time_check;
        public MyViewHolder(View itemView) {
            super(itemView);
            nickname = (TextView) itemView.findViewById(R.id.tv_nickname);
            content = (TextView) itemView.findViewById(R.id.tv_content);
            expandOrFold = itemView.findViewById(R.id.tv_expand_or_fold);
            my_time_1 = itemView.findViewById(R.id.my_time_1);
            my_time_check  = itemView.findViewById(R.id.my_time_check);
        }
    }
}
