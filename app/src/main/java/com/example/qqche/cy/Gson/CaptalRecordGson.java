package com.example.qqche.cy.Gson;

import java.util.List;

public class CaptalRecordGson {
    /**
     * status : 1
     * string : Success
     * msg : 获取成功
     * data : [{"id":"68","user_id":"75","type":"system_add","total":"5000.00","money":"5000.00","use_money":"5000.00","no_use_money":"0.00","to_user":"0","remark":"06071008","addtime":"1528337304","addip":"","tenderid":"0","task_id":"0","type_name":"后台资金充值"}]
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
         * id : 68
         * user_id : 75
         * type : system_add
         * total : 5000.00
         * money : 5000.00
         * use_money : 5000.00
         * no_use_money : 0.00
         * to_user : 0
         * remark : 06071008
         * addtime : 1528337304
         * addip :
         * tenderid : 0
         * task_id : 0
         * type_name : 后台资金充值
         */

        private String id;
        private String user_id;
        private String type;
        private String total;
        private String money;
        private String use_money;
        private String no_use_money;
        private String to_user;
        private String remark;
        private String addtime;
        private String addip;
        private String tenderid;
        private String task_id;
        private String type_name;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getUse_money() {
            return use_money;
        }

        public void setUse_money(String use_money) {
            this.use_money = use_money;
        }

        public String getNo_use_money() {
            return no_use_money;
        }

        public void setNo_use_money(String no_use_money) {
            this.no_use_money = no_use_money;
        }

        public String getTo_user() {
            return to_user;
        }

        public void setTo_user(String to_user) {
            this.to_user = to_user;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getAddip() {
            return addip;
        }

        public void setAddip(String addip) {
            this.addip = addip;
        }

        public String getTenderid() {
            return tenderid;
        }

        public void setTenderid(String tenderid) {
            this.tenderid = tenderid;
        }

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }
    }
}
