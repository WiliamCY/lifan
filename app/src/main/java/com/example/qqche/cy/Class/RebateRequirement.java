package com.example.qqche.cy.Class;

public class RebateRequirement {
    private String  borrow_limit,borrow_apr,fanli_type,retender,invest_area,remark;
    public  RebateRequirement(String borrow_limit,String borrow_apr,String fanli_type,String retender,String invest_area,String remark){
        this.borrow_limit = borrow_limit;
        this.borrow_apr = borrow_apr;
        this.fanli_type = fanli_type;
        this.retender = retender;
        this.invest_area = invest_area;
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBorrow_apr() {
        return borrow_apr;
    }

    public String getBorrow_limit() {
        return borrow_limit;
    }

    public String getFanli_type() {
        return fanli_type;
    }

    public String getInvest_area() {
        return invest_area;
    }

    public String getRetender() {
        return retender;
    }

    public void setBorrow_apr(String borrow_apr) {
        this.borrow_apr = borrow_apr;
    }

    public void setBorrow_limit(String borrow_limit) {
        this.borrow_limit = borrow_limit;
    }

    public void setFanli_type(String fanli_type) {
        this.fanli_type = fanli_type;
    }

    public void setInvest_area(String invest_area) {
        this.invest_area = invest_area;
    }

    public void setRetender(String retender) {
        this.retender = retender;
    }
}
