package com.example.qqche.cy.Gson;

import java.util.List;

public class MianImageBanner {
    /**
     * status : 1
     * string : Success
     * msg : 成功
     * data : [{"id":"6","title":"官网","url":"#","img":"upload/img/201804/25/hwVUqsbOZy3Gu6fr1cDhQKkdo5vsYX1ypYThiGWnmBw63d7ZSMQc3TV.jpg","remark":" ","order":"2","type":"2","status":"1","addtime":"1524639514"},{"id":"5","title":"佩奇游春","url":"#","img":"upload/img/201804/25/s5JYa7Qnfhzr7tn1rpDMqvfvxiCWaRBTAxvRxk5s77s7uBXx17szTcS.jpg","remark":" ","order":"1","type":"2","status":"1","addtime":"1524639478"}]
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
         * id : 6
         * title : 官网
         * url : #
         * img : upload/img/201804/25/hwVUqsbOZy3Gu6fr1cDhQKkdo5vsYX1ypYThiGWnmBw63d7ZSMQc3TV.jpg
         * remark :
         * order : 2
         * type : 2
         * status : 1
         * addtime : 1524639514
         */

        private String id;
        private String title;
        private String url;
        private String img;
        private String remark;
        private String order;
        private String type;
        private String status;
        private String addtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }
}
