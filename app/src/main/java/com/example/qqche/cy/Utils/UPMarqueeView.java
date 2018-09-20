package com.example.qqche.cy.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.example.qqche.cy.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class UPMarqueeView extends ViewFlipper {
    private Context mContext;
    private  boolean isSetAnimDuration = false;
    private int interval = 4000;
    private int animduration = 2000;
    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }
    private OnItemClickListener onItemClickListener;

    public UPMarqueeView(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context,attrs,0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        setFlipInterval(interval);
        Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in);
        if(isSetAnimDuration)animIn.setDuration(animduration);
        setInAnimation(animIn);
        Animation animOut = AnimationUtils.loadAnimation(mContext,R.anim.anim_marquee_out);
        setInAnimation(animOut);
    }
    public void setViews(final List<View> views){
        if(views == null || views.size() == 0) return;
        removeAllViews();
        for(int i = 0 ;i<views.size();i++){
            final  int position = i;
            views.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onItemClick(position,views.get(position));
                    }
                }
            });
            addView(views.get(i));
        }
        startFlipping();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
