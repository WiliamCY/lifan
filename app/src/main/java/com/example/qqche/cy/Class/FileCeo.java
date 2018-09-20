package com.example.qqche.cy.Class;

/**
 * Created by Administrator on 2018/4/24.
 */

public class FileCeo {
    private String fileCeoName,fileCeoJob,fileCeoMessage;
    private int fileCeoImage;
    public FileCeo(String fileCeoName,String fileCeoJob,String fileCeoMessage,int fileCeoImage){
        this.fileCeoName = fileCeoName;
        this.fileCeoJob = fileCeoJob;
        this.fileCeoMessage = fileCeoMessage;
        this.fileCeoImage =fileCeoImage;
    }

    public int getFileCeoImage() {
        return fileCeoImage;
    }

    public String getFileCeoJob() {
        return fileCeoJob;
    }

    public String getFileCeoMessage() {
        return fileCeoMessage;
    }

    public String getFileCeoName() {
        return fileCeoName;
    }

    public void setFileCeoImage(int fileCeoImage) {
        this.fileCeoImage = fileCeoImage;
    }

    public void setFileCeoJob(String fileCeoJob) {
        this.fileCeoJob = fileCeoJob;
    }

    public void setFileCeoMessage(String fileCeoMessage) {
        this.fileCeoMessage = fileCeoMessage;
    }

    public void setFileCeoName(String fileCeoName) {
        this.fileCeoName = fileCeoName;
    }
}
