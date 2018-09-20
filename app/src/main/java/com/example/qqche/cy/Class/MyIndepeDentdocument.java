package com.example.qqche.cy.Class;

/**
 * Created by Administrator on 2018/4/23.
 */

public class MyIndepeDentdocument {
    private String docmentTitle,docmentMoney,docmentTime,documentTimes;
    private int docmentImageMessage,docmentImageStatus;


    public MyIndepeDentdocument(String docmentTitle,String docmentMoney,String docmentTime,String documentTimes,int docmentImageMessage,int docmentImageStatus){
        this.docmentTitle = docmentTitle;
        this.docmentMoney = docmentMoney;
        this.docmentTime = docmentTime;
        this.documentTimes = documentTimes;
        this.docmentImageMessage = docmentImageMessage;
        this.docmentImageStatus = docmentImageStatus;

    }

    public int getDocmentImageMessage() {
        return docmentImageMessage;
    }

    public int getDocmentImageStatus() {
        return docmentImageStatus;
    }

    public String getDocmentMoney() {
        return docmentMoney;
    }

    public String getDocmentTime() {
        return docmentTime;
    }

    public String getDocmentTitle() {
        return docmentTitle;
    }

    public void setDocmentImageMessage(int docmentImageMessage) {
        this.docmentImageMessage = docmentImageMessage;
    }

    public void setDocmentImageStatus(int docmentImageStatus) {
        this.docmentImageStatus = docmentImageStatus;
    }

    public void setDocmentMoney(String docmentMoney) {
        this.docmentMoney = docmentMoney;
    }

    public void setDocmentTime(String docmentTime) {
        this.docmentTime = docmentTime;
    }

    public void setDocmentTitle(String docmentTitle) {
        this.docmentTitle = docmentTitle;
    }

    public String getDocumentTimes() {
        return documentTimes;
    }

    public void setDocumentTimes(String documentTimes) {
        this.documentTimes = documentTimes;
    }
}
