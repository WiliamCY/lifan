package com.example.qqche.cy.Class;

/**
 * Created by Administrator on 2018/5/2.
 */

public class Commend {
    private String commendH1,commendH2,commendH3,commendH4,commendImage,newUri;


    public Commend(String commendH1,String commendH2,String commendH3,String commendH4,String commendImage,String newUri){
        this.commendH1 = commendH1;
        this.commendH2  =commendH2;
        this.commendH3 = commendH3;
        this.commendH4 = commendH4;
        this.commendImage = commendImage;
        this.newUri = newUri;

    }

    public String getNewUri() {
        return newUri;
    }

    public void setNewUri(String newUri) {
        this.newUri = newUri;
    }

    public String getCommendH1() {
        return commendH1;
    }

    public String getCommendH2() {
        return commendH2;
    }

    public String getCommendH3() {
        return commendH3;
    }

    public String getCommendH4() {
        return commendH4;
    }

    public void setCommendH1(String commendH1) {
        this.commendH1 = commendH1;
    }

    public void setCommendH2(String commendH2) {
        this.commendH2 = commendH2;
    }

    public void setCommendH3(String commendH3) {
        this.commendH3 = commendH3;
    }

    public void setCommendH4(String commendH4) {
        this.commendH4 = commendH4;
    }

    public String getCommendImage() {
        return commendImage;
    }

    public void setCommendImage(String commendImage) {
        this.commendImage = commendImage;
    }
}
