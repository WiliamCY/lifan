package com.example.qqche.cy.Class;

/**
 * Created by qqche on 2018/5/6.
 */

public class MyTime {

    private String my_time_h1,my_time_h2,my_time_h3;
    private int id;//该条数据的id
//    public MyTime(String my_time_h1,String my_time_h2,String my_time_h3){
//        this.my_time_h1 = my_time_h1;
//        this.my_time_h2 = my_time_h2;
//        this.my_time_h3 = my_time_h3;
//
//    }


    public String getMy_time_h1() {
        return my_time_h1;
    }

    public String getMy_time_h2() {
        return my_time_h2;
    }

    public String getMy_time_h3() {
        return my_time_h3;
    }

    public void setMy_time_h1(String my_time_h1) {
        this.my_time_h1 = my_time_h1;
    }

    public void setMy_time_h2(String my_time_h2) {
        this.my_time_h2 = my_time_h2;
    }

    public void setMy_time_h3(String my_time_h3) {
        this.my_time_h3 = my_time_h3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
