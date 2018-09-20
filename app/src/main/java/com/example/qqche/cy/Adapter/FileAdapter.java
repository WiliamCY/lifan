package com.example.qqche.cy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qqche.cy.Class.File;
import com.example.qqche.cy.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */

public class FileAdapter extends XRecyclerView.Adapter<FileAdapter.ViewHolder> {
    private Context mContext;
    private List<File> mfiles;
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


    static class ViewHolder extends XRecyclerView.ViewHolder{
        TextView fileTitle,fileMessage,fileBank,fileE1,fileE2,fileE3,fileE4;
        ImageView fileLogo,fileBankLogo;
        public  ViewHolder(View view){
            super(view);
            fileBank = view.findViewById(R.id.file_bank);
            fileTitle = view.findViewById(R.id.file_title);
            fileMessage = view.findViewById(R.id.file_messgae);
            fileE1 = view.findViewById(R.id.file_e1);
            fileE2 = view.findViewById(R.id.file_e2);
            fileE3 = view.findViewById(R.id.file_e3);
            fileE4 = view.findViewById(R.id.file_e4);
            fileLogo = view.findViewById(R.id.file_logo);
            fileBankLogo = view.findViewById(R.id.file_bank_logo);

        }
    }
    public FileAdapter(List<File> files){
        mfiles = files;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.flie,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        File file = mfiles.get(position);
        holder.fileTitle.setText(file.getFileTitle());
        holder.fileMessage.setText(file.getFileMessage());
        holder.fileE1.setText(file.getFileE1());
        holder.fileE2.setText(file.getFileE2());
        holder.fileE3.setText(file.getFileE3());
        holder.fileE4.setText(file.getFileE4());
        holder.fileBank.setText(file.getFileBank());
        Glide.with(mContext).load(file.getFileLogo()).into(holder.fileLogo);
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
        return mfiles.size();
    }


}
