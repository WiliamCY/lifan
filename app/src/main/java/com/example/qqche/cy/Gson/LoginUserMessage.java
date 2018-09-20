package com.example.qqche.cy.Gson;

public class LoginUserMessage {


    /**
     * status : 1
     * string : Success
     * msg : 登录成功
     * data : {"user_id":"75","username":"chenyu","pwd":"35bbff78dc536b0ed94b352b1b9c8cdc","type":"1","archivers_id":"0","is_clock":"0","invite_userid":"0","invite_money":"0.00","real_status":"0","realname":"","idcard":"","phone_status":"1","phone":"13081962969","email_status":"0","email":"","sex":"0","level":"0","addtime":"1526614143","addip":"","lasttime":"","lastip":""}
     */

    private String status;
    private String string;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 75
         * username : chenyu
         * pwd : 35bbff78dc536b0ed94b352b1b9c8cdc
         * type : 1
         * archivers_id : 0
         * is_clock : 0
         * invite_userid : 0
         * invite_money : 0.00
         * real_status : 0
         * realname :
         * idcard :
         * phone_status : 1
         * phone : 13081962969
         * email_status : 0
         * email :
         * sex : 0
         * level : 0
         * addtime : 1526614143
         * addip :
         * lasttime :
         * lastip :
         */

        private String user_id;
        private String username;
        private String pwd;
        private String type;
        private String archivers_id;
        private String is_clock;
        private String invite_userid;
        private String invite_money;
        private String real_status;
        private String realname;
        private String idcard;
        private String phone_status;
        private String phone;
        private String email_status;
        private String email;
        private String sex;
        private String level;
        private String addtime;
        private String addip;
        private String lasttime;
        private String lastip;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getArchivers_id() {
            return archivers_id;
        }

        public void setArchivers_id(String archivers_id) {
            this.archivers_id = archivers_id;
        }

        public String getIs_clock() {
            return is_clock;
        }

        public void setIs_clock(String is_clock) {
            this.is_clock = is_clock;
        }

        public String getInvite_userid() {
            return invite_userid;
        }

        public void setInvite_userid(String invite_userid) {
            this.invite_userid = invite_userid;
        }

        public String getInvite_money() {
            return invite_money;
        }

        public void setInvite_money(String invite_money) {
            this.invite_money = invite_money;
        }

        public String getReal_status() {
            return real_status;
        }

        public void setReal_status(String real_status) {
            this.real_status = real_status;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getPhone_status() {
            return phone_status;
        }

        public void setPhone_status(String phone_status) {
            this.phone_status = phone_status;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail_status() {
            return email_status;
        }

        public void setEmail_status(String email_status) {
            this.email_status = email_status;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
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

        public String getLasttime() {
            return lasttime;
        }

        public void setLasttime(String lasttime) {
            this.lasttime = lasttime;
        }

        public String getLastip() {
            return lastip;
        }

        public void setLastip(String lastip) {
            this.lastip = lastip;
        }
    }
}
