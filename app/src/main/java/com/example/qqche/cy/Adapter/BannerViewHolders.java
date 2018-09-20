package com.example.qqche.cy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.qqche.cy.R;
import com.example.qqche.cy.Utils.Constant;
import com.zhouwei.mzbanner.holder.MZViewHolder;

/**
 * Created by Administrator on 2018/5/11.
 */

public class BannerViewHolders implements MZViewHolder<String> {
    private ImageView mImageView;
    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
        mImageView =  view.findViewById(R.id.banner_image);
        return view;
    }

    @Override
    public void onBind(Context context, int position, String uri) {
        // 数据绑定
//        mImageView.setImageResource(data);
//        Glide.with(context).load("http://192.168.0.228/"+uri).into(mImageView);
        Glide.with(context).load(Constant.SERVER+uri).into(mImageView);
    }
}
