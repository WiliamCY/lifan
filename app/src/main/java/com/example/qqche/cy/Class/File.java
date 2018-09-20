package com.example.qqche.cy.Class;

/**
 * Created by Administrator on 2018/4/24.
 */

public class File {
    private String fileTitle,fileMessage,fileBank,fileE1,fileE2,fileE3,fileE4,fileLogo,id;
    public File(String fileTitle,String fileMessage,String fileBank,String fileE1,String fileE2,String fileE3,String fileE4,String fileLogo,String id){
        this.fileTitle = fileTitle;
        this.fileMessage = fileMessage;
        this.fileBank =fileBank;
        this.fileE1 = fileE1;
        this.fileE2 = fileE2;
        this.fileE3 = fileE3;
        this.fileE4 = fileE4;
        this.id = id;
        this.fileLogo = fileLogo;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileLogo() {
        return fileLogo;
    }

    public void setFileLogo(String fileLogo) {
        this.fileLogo = fileLogo;
    }

    public String getFileBank() {
        return fileBank;
    }

    public String getFileE1() {
        return fileE1;
    }

    public String getFileE2() {
        return fileE2;
    }

    public String getFileE3() {
        return fileE3;
    }

    public String getFileE4() {
        return fileE4;
    }

    public String getFileMessage() {
        return fileMessage;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileBank(String fileBank) {
        this.fileBank = fileBank;
    }

    public void setFileE1(String fileE1) {
        this.fileE1 = fileE1;
    }

    public void setFileE2(String fileE2) {
        this.fileE2 = fileE2;
    }

    public void setFileE3(String fileE3) {
        this.fileE3 = fileE3;
    }

    public void setFileE4(String fileE4) {
        this.fileE4 = fileE4;
    }

    public void setFileMessage(String fileMessage) {
        this.fileMessage = fileMessage;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }
}
