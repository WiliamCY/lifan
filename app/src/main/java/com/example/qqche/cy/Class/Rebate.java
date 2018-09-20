package com.example.qqche.cy.Class;

/**
 * Created by Administrator on 2018/4/25.
 */

public class Rebate {
    private String rebate_interest,rebate_name,rebate_image_size,rebate_h1,rebate_h2,rebate_h3,rebateImage,id;
    public Rebate(String rebate_interest,String rebate_name,String rebate_image_size,String rebate_h1,String rebate_h2,String rebate_h3,String rebateImage,String id){
        this.rebate_interest = rebate_interest;
        this.rebate_name = rebate_name;
        this.rebate_image_size = rebate_image_size;
        this.rebate_h1 = rebate_h1;
        this.rebate_h2 = rebate_h2;
        this.rebate_h3 = rebate_h3;
        this.rebateImage = rebateImage;
      this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRebateImage() {
        return rebateImage;
    }

    public String getRebate_h1() {
        return rebate_h1;
    }

    public String getRebate_h2() {
        return rebate_h2;
    }

    public String getRebate_h3() {
        return rebate_h3;
    }

    public String getRebate_image_size() {
        return rebate_image_size;
    }

    public String getRebate_interest() {
        return rebate_interest;
    }

    public String getRebate_name() {
        return rebate_name;
    }

    public void setRebate_h1(String rebate_h1) {
        this.rebate_h1 = rebate_h1;
    }

    public void setRebate_h2(String rebate_h2) {
        this.rebate_h2 = rebate_h2;
    }

    public void setRebate_h3(String rebate_h3) {
        this.rebate_h3 = rebate_h3;
    }

    public void setRebate_image_size(String rebate_image_size) {
        this.rebate_image_size = rebate_image_size;
    }

    public void setRebate_interest(String rebate_interest) {
        this.rebate_interest = rebate_interest;
    }

    public void setRebate_name(String rebate_name) {
        this.rebate_name = rebate_name;
    }

    public void setRebateImage(String rebateImage) {
        this.rebateImage = rebateImage;
    }
}
