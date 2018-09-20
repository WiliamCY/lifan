package com.example.qqche.cy.Gson;

import java.util.List;

public class ThenderRecordGson {

    /**
     * status : 1
     * string : Success
     * msg : 获取成功
     * data : [{"id":"24","user_id":"75","ctender_id":"1","cooperation_id":"1","p2p_tenderid":"","money":"10000.00","p2p_borrowid":"","p2p_borrowname":"13081962969","time_limit_type":"0","time_limit":"1","borrow_apr":"0.00","tender_time":"1528300800","addtime":"1528341102","status":"1","tender_phone":"13081962969","remark":"后台审核通过","adduser":"1","name":"甬e贷","logo":"upload/logo/201805/04/KlOdZqliwVwtX3GoHBekpAD5wYwo7cwgSDe2KVhUZkRiiaKPwZKt2WM.jpg","fanli_type":"1","fanli_num":"100.00","tender_limit_s":"10000.00","tender_limit_e":"100000.00","invest_term":"1月"}]
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
         * id : 24
         * user_id : 75
         * ctender_id : 1
         * cooperation_id : 1
         * p2p_tenderid :
         * money : 10000.00
         * p2p_borrowid :
         * p2p_borrowname : 13081962969
         * time_limit_type : 0
         * time_limit : 1
         * borrow_apr : 0.00
         * tender_time : 1528300800
         * addtime : 1528341102
         * status : 1
         * tender_phone : 13081962969
         * remark : 后台审核通过
         * adduser : 1
         * name : 甬e贷
         * logo : upload/logo/201805/04/KlOdZqliwVwtX3GoHBekpAD5wYwo7cwgSDe2KVhUZkRiiaKPwZKt2WM.jpg
         * fanli_type : 1
         * fanli_num : 100.00
         * tender_limit_s : 10000.00
         * tender_limit_e : 100000.00
         * invest_term : 1月
         */

        private String id;
        private String user_id;
        private String ctender_id;
        private String cooperation_id;
        private String p2p_tenderid;
        private String money;
        private String p2p_borrowid;
        private String p2p_borrowname;
        private String time_limit_type;
        private String time_limit;
        private String borrow_apr;
        private String tender_time;
        private String addtime;
        private String status;
        private String tender_phone;
        private String remark;
        private String adduser;
        private String name;
        private String logo;
        private String fanli_type;
        private String fanli_num;
        private String tender_limit_s;
        private String tender_limit_e;
        private String invest_term;

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

        public String getCtender_id() {
            return ctender_id;
        }

        public void setCtender_id(String ctender_id) {
            this.ctender_id = ctender_id;
        }

        public String getCooperation_id() {
            return cooperation_id;
        }

        public void setCooperation_id(String cooperation_id) {
            this.cooperation_id = cooperation_id;
        }

        public String getP2p_tenderid() {
            return p2p_tenderid;
        }

        public void setP2p_tenderid(String p2p_tenderid) {
            this.p2p_tenderid = p2p_tenderid;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getP2p_borrowid() {
            return p2p_borrowid;
        }

        public void setP2p_borrowid(String p2p_borrowid) {
            this.p2p_borrowid = p2p_borrowid;
        }

        public String getP2p_borrowname() {
            return p2p_borrowname;
        }

        public void setP2p_borrowname(String p2p_borrowname) {
            this.p2p_borrowname = p2p_borrowname;
        }

        public String getTime_limit_type() {
            return time_limit_type;
        }

        public void setTime_limit_type(String time_limit_type) {
            this.time_limit_type = time_limit_type;
        }

        public String getTime_limit() {
            return time_limit;
        }

        public void setTime_limit(String time_limit) {
            this.time_limit = time_limit;
        }

        public String getBorrow_apr() {
            return borrow_apr;
        }

        public void setBorrow_apr(String borrow_apr) {
            this.borrow_apr = borrow_apr;
        }

        public String getTender_time() {
            return tender_time;
        }

        public void setTender_time(String tender_time) {
            this.tender_time = tender_time;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTender_phone() {
            return tender_phone;
        }

        public void setTender_phone(String tender_phone) {
            this.tender_phone = tender_phone;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getAdduser() {
            return adduser;
        }

        public void setAdduser(String adduser) {
            this.adduser = adduser;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getFanli_type() {
            return fanli_type;
        }

        public void setFanli_type(String fanli_type) {
            this.fanli_type = fanli_type;
        }

        public String getFanli_num() {
            return fanli_num;
        }

        public void setFanli_num(String fanli_num) {
            this.fanli_num = fanli_num;
        }

        public String getTender_limit_s() {
            return tender_limit_s;
        }

        public void setTender_limit_s(String tender_limit_s) {
            this.tender_limit_s = tender_limit_s;
        }

        public String getTender_limit_e() {
            return tender_limit_e;
        }

        public void setTender_limit_e(String tender_limit_e) {
            this.tender_limit_e = tender_limit_e;
        }

        public String getInvest_term() {
            return invest_term;
        }

        public void setInvest_term(String invest_term) {
            this.invest_term = invest_term;
        }
    }
}
