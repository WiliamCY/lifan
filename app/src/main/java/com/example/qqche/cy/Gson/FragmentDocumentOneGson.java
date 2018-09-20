package com.example.qqche.cy.Gson;

import java.util.List;

public class FragmentDocumentOneGson {
    /**
     * status : 1
     * string : Success
     * msg : 获取成功
     * data : [{"id":"42","user_id":"75","cooperation_id":"1","ctender_id":"0","money":"5000.00","p2p_borrowname":"13081962969","time_limit_type":"0","time_limit":"1","tender_time":"1528905600","addtime":"1528340951","status":"0","tender_phone":"13081962969","remark":"","pic":"upload/pic/201806/07/DOJrLWjfpHXBUKKboBuJAlJmJdHPixrqAeCYmMgUorueYQNxSqe7EjI.jpg","examine_userid":"0","examine_time":"0","platform_name":"甬e贷"}]
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
         * id : 42
         * user_id : 75
         * cooperation_id : 1
         * ctender_id : 0
         * money : 5000.00
         * p2p_borrowname : 13081962969
         * time_limit_type : 0
         * time_limit : 1
         * tender_time : 1528905600
         * addtime : 1528340951
         * status : 0
         * tender_phone : 13081962969
         * remark :
         * pic : upload/pic/201806/07/DOJrLWjfpHXBUKKboBuJAlJmJdHPixrqAeCYmMgUorueYQNxSqe7EjI.jpg
         * examine_userid : 0
         * examine_time : 0
         * platform_name : 甬e贷
         */

        private String id;
        private String user_id;
        private String cooperation_id;
        private String ctender_id;
        private String money;
        private String p2p_borrowname;
        private String time_limit_type;
        private String time_limit;
        private String tender_time;
        private String addtime;
        private String status;
        private String tender_phone;
        private String remark;
        private String pic;
        private String examine_userid;
        private String examine_time;
        private String platform_name;

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

        public String getCooperation_id() {
            return cooperation_id;
        }

        public void setCooperation_id(String cooperation_id) {
            this.cooperation_id = cooperation_id;
        }

        public String getCtender_id() {
            return ctender_id;
        }

        public void setCtender_id(String ctender_id) {
            this.ctender_id = ctender_id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getExamine_userid() {
            return examine_userid;
        }

        public void setExamine_userid(String examine_userid) {
            this.examine_userid = examine_userid;
        }

        public String getExamine_time() {
            return examine_time;
        }

        public void setExamine_time(String examine_time) {
            this.examine_time = examine_time;
        }

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }
    }
}
