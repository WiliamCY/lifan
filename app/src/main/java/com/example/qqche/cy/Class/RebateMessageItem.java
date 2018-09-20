package com.example.qqche.cy.Class;

/**
 * Created by Administrator on 2018/4/27.
 */

public class RebateMessageItem {
    private String rebateH1,rebateH2;


    public RebateMessageItem(String rebateH1,String rebateH2){
        this.rebateH1 = rebateH1;
     this.rebateH2 = rebateH2;
    }

    public String getRebateH1() {
        return rebateH1;
    }

    public String getRebateH2() {
        return rebateH2;
    }

    public void setRebateH1(String rebateH1) {
        this.rebateH1 = rebateH1;
    }

    public void setRebateH2(String rebateH2) {
        this.rebateH2 = rebateH2;
    }
}
