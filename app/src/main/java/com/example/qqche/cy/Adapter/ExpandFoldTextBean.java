package com.example.qqche.cy.Adapter;

/**
 * Author     wildma
 * DATE       2017/8/3
 * Des	      ${TODO}
 */
public class ExpandFoldTextBean {

    private String content;//内容
    private String tv_nickname;
    private String my_time;

    private int id;//该条数据的id
    public boolean isSelect;

    public String getMy_time() {
        return my_time;
    }

    public void setMy_time(String my_time) {
        this.my_time = my_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public String getTv_nickname() {
        return tv_nickname;
    }

    public void setTv_nickname(String tv_nickname) {
        this.tv_nickname = tv_nickname;
    }
}
