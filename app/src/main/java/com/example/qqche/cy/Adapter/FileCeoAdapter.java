package com.example.qqche.cy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qqche.cy.Class.FileCeo;
import com.example.qqche.cy.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */

public class FileCeoAdapter extends XRecyclerView.Adapter<FileCeoAdapter.ViewHolder> {
    private Context mContext;
    private List<FileCeo>  mfileCeos;
    static  class ViewHolder extends XRecyclerView.ViewHolder{
        TextView fileCeoName,fileCeoJob,fileCeoMessage;
        ImageView fileCeoImage;
        public ViewHolder(View view){
            super(view);
            fileCeoName = view.findViewById(R.id.file_ceo_name);
            fileCeoJob = view.findViewById(R.id.file_ceo_job);
            fileCeoMessage = view.findViewById(R.id.file_ceo_message);
            fileCeoImage = view.findViewById(R.id.file_ceo_logo);

        }
    }
    public FileCeoAdapter(List<FileCeo> fileCeos){
        mfileCeos = fileCeos;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.file_ceo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FileCeo fileCeo = mfileCeos.get(position);
        holder.fileCeoName.setText(fileCeo.getFileCeoName());
        holder.fileCeoJob.setText(fileCeo.getFileCeoJob());
        holder.fileCeoMessage.setText(fileCeo.getFileCeoMessage());
        Glide.with(mContext).load(fileCeo.getFileCeoImage()).into(holder.fileCeoImage);

    }

    @Override
    public int getItemCount() {
        return mfileCeos.size();
    }


}
