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
import com.example.qqche.cy.Class.MyIndepeDentdocument;
import com.example.qqche.cy.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/23.
 */

public class MyIndepeDentdocumentAdapter extends XRecyclerView.Adapter<MyIndepeDentdocumentAdapter.ViewHolder> {
    private Context mContext;
    private List<MyIndepeDentdocument> mmyIndepeDentdocuments;
    static  class  ViewHolder extends XRecyclerView.ViewHolder{
        ImageView documentImage,documentStstus;
        TextView doeumentTitle,documentMoney,documentTime,documentTimes;
        public ViewHolder(View view){
            super(view);
            documentImage = view.findViewById(R.id.documet_image);
            documentStstus = view.findViewById(R.id.document_status);
            doeumentTitle = view.findViewById(R.id.documet_title);
            documentMoney = view.findViewById(R.id.documet_money);
            documentTime = view.findViewById(R.id.documet_time);
            documentTimes = view.findViewById(R.id.documet_times);
        }
    }
    public MyIndepeDentdocumentAdapter(List<MyIndepeDentdocument> myIndepeDentdocumentss){
        mmyIndepeDentdocuments = myIndepeDentdocumentss;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_indepe_dent_document,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       MyIndepeDentdocument myIndepeDentdocument = mmyIndepeDentdocuments.get(position);
       holder.doeumentTitle.setText(myIndepeDentdocument.getDocmentTitle());
       holder.documentMoney.setText(myIndepeDentdocument.getDocmentMoney());
       holder.documentTime.setText(myIndepeDentdocument.getDocmentTime());
       holder.documentTimes.setText(myIndepeDentdocument.getDocumentTimes());
        Glide.with(mContext).load(myIndepeDentdocument.getDocmentImageStatus()).into(holder.documentStstus);
    }

    @Override
    public int getItemCount() {
        return mmyIndepeDentdocuments.size();
    }


}
