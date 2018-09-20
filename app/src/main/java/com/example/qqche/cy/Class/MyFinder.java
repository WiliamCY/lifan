package com.example.qqche.cy.Class;

import android.widget.ImageView;

/**
 * Created by Administrator on 2018/4/24.
 */

public class MyFinder {
    private String finderName,finderPhone,finderH1,finderH2,finderH3;
    private int finderImage;

    public MyFinder(String finderName,String finderPhone,String finderH1,String finderH2,String finderH3,int finderImage){
        this.finderName = finderName;
        this.finderPhone = finderPhone;
        this.finderH1 = finderH1;
        this.finderH2 = finderH2;
        this.finderH3 = finderH3;
        this.finderImage = finderImage;
    }

    public int getFinderImage() {
        return finderImage;
    }

    public String getFinderH1() {
        return finderH1;
    }

    public String getFinderH2() {
        return finderH2;
    }

    public String getFinderH3() {
        return finderH3;
    }

    public String getFinderName() {
        return finderName;
    }

    public String getFinderPhone() {
        return finderPhone;
    }

    public void setFinderH1(String finderH1) {
        this.finderH1 = finderH1;
    }

    public void setFinderH2(String finderH2) {
        this.finderH2 = finderH2;
    }

    public void setFinderH3(String finderH3) {
        this.finderH3 = finderH3;
    }

    public void setFinderImage(int finderImage) {
        this.finderImage = finderImage;
    }

    public void setFinderName(String finderName) {
        this.finderName = finderName;
    }

    public void setFinderPhone(String finderPhone) {
        this.finderPhone = finderPhone;
    }
}
