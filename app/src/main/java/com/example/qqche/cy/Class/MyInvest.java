package com.example.qqche.cy.Class;

import android.widget.ImageView;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2018/4/23.
 */

public class MyInvest {
    private String inveestImage;
    private String tenderAmount;
    private String amountofRebate;
    private String investTime;
    public  MyInvest(String inveestImage,String tenderAmount,String amountofRebate,String investTime){
        this.inveestImage = inveestImage;
        this.tenderAmount = tenderAmount;
        this.amountofRebate = amountofRebate;
        this.investTime = investTime;
    }

    public String getInveestImage() {
        return inveestImage;
    }

    public void setInveestImage(String inveestImage) {
        this.inveestImage = inveestImage;
    }

    public String getAmountofRebate() {
        return amountofRebate;
    }

    public String getTenderAmount() {
        return tenderAmount;
    }

    public void setAmountofRebate(String amountofRebate) {
        this.amountofRebate = amountofRebate;
    }


    public void setTenderAmount(String tenderAmount) {
        this.tenderAmount = tenderAmount;
    }

    public String getInvestTime() {
        return investTime;
    }

    public void setInvestTime(String investTime) {
        this.investTime = investTime;
    }
}
