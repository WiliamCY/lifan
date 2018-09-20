package com.example.qqche.cy.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qqche.cy.Class.Document;
import com.example.qqche.cy.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class DocumentAdapter extends XRecyclerView.Adapter<DocumentAdapter.ViewHolder> {
    private Context mContext;
    private List<Document> mDocumentList;

    static  class  ViewHolder extends XRecyclerView.ViewHolder{
        TextView document1,document2,document3,document4,documentBtn;
        ImageView documentImage;

        public ViewHolder(View view) {
            super(view);
            document1 = view.findViewById(R.id.document_h1);
            document2 = view.findViewById(R.id.document_h2);
            document3 = view.findViewById(R.id.documet_h3);
            document4 = view.findViewById(R.id.docuemt_h4);
            documentBtn = view.findViewById(R.id.docuemt_btn);
            documentImage = view.findViewById(R.id.doucment_image_1);
        }
    }
    public DocumentAdapter(List<Document> documents){
        mDocumentList = documents;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.doucment_message,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Document document = mDocumentList.get(position);
        holder.document1.setText(document.getDocumenth1());
        holder.document2.setText(document.getDocumenth2());
        holder.document3.setText(document.getDocumenth3());
        holder.document4.setText(document.getDocumenth4());
        Glide.with(mContext).load(document.getDocumentImage()).into(holder.documentImage);
        holder.documentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog builder = new AlertDialog.Builder(v.getContext()).create();
                builder.show();
                if (builder.getWindow() == null) return;
                builder.getWindow().setContentView(R.layout.pop_user);
                TextView msg = builder.findViewById(R.id.tv_msg);
                Button cancle = builder.findViewById(R.id.btn_cancle);
                Button sure = builder.findViewById(R.id.btn_sure);
                if (msg == null || cancle == null || sure == null) return;
                msg.setText("删除后不可恢复，是否删除该条目？");

                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();
                    }
                });
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeData(position);
                        builder.dismiss();
                    }
                });

            }
        });


    }
    private void removeData(int position){
        mDocumentList.remove(position);
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return mDocumentList.size();
    }


}
