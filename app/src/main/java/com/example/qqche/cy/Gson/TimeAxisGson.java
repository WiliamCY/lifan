package com.example.qqche.cy.Gson;

import java.util.List;

public class TimeAxisGson {
    /**
     * status : 1
     * string : Success
     * msg : 获取成功
     * data : [{"id":"15","user_id":"75","message_id":"1","send_userid":"1","addtime":"1523857326","readtime":"0","batch":null,"status":"1","title":"测试","content":"将于5.16休息"},{"id":"16","user_id":"75","message_id":"1","send_userid":"1","addtime":"1523857326","readtime":"0","batch":null,"status":"1","title":"测试","content":"将于5.16休息"},{"id":"17","user_id":"75","message_id":"1","send_userid":"1","addtime":"1523857326","readtime":"0","batch":null,"status":"1","title":"测试","content":"将于5.16休息"}]
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
         * id : 15
         * user_id : 75
         * message_id : 1
         * send_userid : 1
         * addtime : 1523857326
         * readtime : 0
         * batch : null
         * status : 1
         * title : 测试
         * content : 将于5.16休息
         */

        private String id;
        private String user_id;
        private String message_id;
        private String send_userid;
        private String addtime;
        private String readtime;
        private Object batch;
        private String status;
        private String title;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getMessage_id() {
            return message_id;
        }

        public void setMessage_id(String message_id) {
            this.message_id = message_id;
        }

        public String getSend_userid() {
            return send_userid;
        }

        public void setSend_userid(String send_userid) {
            this.send_userid = send_userid;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getReadtime() {
            return readtime;
        }

        public void setReadtime(String readtime) {
            this.readtime = readtime;
        }

        public Object getBatch() {
            return batch;
        }

        public void setBatch(Object batch) {
            this.batch = batch;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
