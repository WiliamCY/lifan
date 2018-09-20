package com.example.qqche.cy.Class;

public class Document {
    private String documentImage;
    private String documenth1,documenth2,documenth3,documenth4;
    public Document(String documentImage,String documenth1,String documenth2,String documenth3,String documenth4){
        this.documenth1 = documenth1;
        this.documenth2 = documenth2;
        this.documenth3 = documenth3;
        this.documenth4 = documenth4;
        this.documentImage = documentImage;
    }



    public String getDocumenth1() {
        return documenth1;
    }

    public String getDocumenth2() {
        return documenth2;
    }

    public String getDocumenth3() {
        return documenth3;
    }

    public String getDocumenth4() {
        return documenth4;
    }

    public void setDocumenth1(String documenth1) {
        this.documenth1 = documenth1;
    }

    public void setDocumenth2(String documenth2) {
        this.documenth2 = documenth2;
    }

    public void setDocumenth3(String documenth3) {
        this.documenth3 = documenth3;
    }

    public void setDocumenth4(String documenth4) {
        this.documenth4 = documenth4;
    }

    public String getDocumentImage() {
        return documentImage;
    }

    public void setDocumentImage(String documentImage) {
        this.documentImage = documentImage;
    }
}
