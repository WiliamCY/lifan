package com.example.qqche.cy.Utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by Administrator on 2018/5/14.
 */

public class CustomLinearLayoutManager extends LinearLayoutManager {
     private boolean isScrollEndabled = true;
    public CustomLinearLayoutManager(Context context) {
        super(context);
    }
    public void setScrollEndabled(Boolean flag){
        this.isScrollEndabled = false;
    }

    @Override
    public boolean canScrollVertically() {

        return  isScrollEndabled && super.canScrollVertically();
    }
}
