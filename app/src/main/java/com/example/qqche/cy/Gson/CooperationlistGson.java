package com.example.qqche.cy.Gson;

import java.util.List;

public class CooperationlistGson {
    /**
     * status : 1
     * string : Success
     * msg : 获取成功
     * data : [{"id":"1","name":"甬e贷"},{"id":"2","name":"陆金服"},{"id":"3","name":"微贷网"},{"id":"4","name":"人人贷"},{"id":"5","name":"投哪网"},{"id":"6","name":"爱钱进"},{"id":"7","name":"拍拍贷"},{"id":"8","name":"点融"},{"id":"9","name":"有利网"},{"id":"11","name":"测试甬e贷"}]
     */

    private String status;
    private String string;
    private String msg;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 甬e贷
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
