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
import com.example.qqche.cy.Class.MyFinder;
import com.example.qqche.cy.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */

public class MyFinderAdapter extends XRecyclerView.Adapter<MyFinderAdapter.ViewHolder> {
    private Context mContext;
    private List<MyFinder> mmyFinders;
    static  class  ViewHolder extends  XRecyclerView.ViewHolder{
        TextView finderName,finderPhone,finderH1,finderH2,finderH3;
        ImageView finderImage;
        public ViewHolder(View view){
            super(view);
            finderName = view.findViewById(R.id.finder_name);
            finderPhone = view.findViewById(R.id.finder_pohone);
            finderH1 = view.findViewById(R.id.finderH1);
            finderH2 = view.findViewById(R.id.finderH2);
            finderH3 = view.findViewById(R.id.finderH3);
            finderImage = view.findViewById(R.id.finder_image);
        }
    }
    public MyFinderAdapter(List<MyFinder> myFinders){
        mmyFinders = myFinders;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext =parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_finder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyFinder myFinder = mmyFinders.get(position);
        holder.finderName.setText(myFinder.getFinderName());
        holder.finderPhone.setText(myFinder.getFinderPhone());
        holder.finderH1.setText(myFinder.getFinderH1());
        holder.finderH2.setText(myFinder.getFinderH2());
        holder.finderH3.setText(myFinder.getFinderH3());
        Glide.with(mContext).load(myFinder.getFinderImage()).into(holder.finderImage);

    }

    @Override
    public int getItemCount() {
        return mmyFinders.size();
    }


}
