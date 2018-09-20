package com.example.qqche.cy.Gson;

public class BankMessage {


    /**
     * status : 1
     * string : Success
     * msg : 获取成功
     * data : {"bank":{"id":"10","user_id":"75","account":"889","bank":"ghh","branch":"cbjchjfhddj","addtime":"1528856573","addip":"192.168.0.148","status":"1"},"alipay":{"id":"3","user_id":"75","alipay_account":"ggjfhud","alipay_realname":"作曲家","addtime":"1528856695","addip":"192.168.0.148","status":"1"}}
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
         * bank : {"id":"10","user_id":"75","account":"889","bank":"ghh","branch":"cbjchjfhddj","addtime":"1528856573","addip":"192.168.0.148","status":"1"}
         * alipay : {"id":"3","user_id":"75","alipay_account":"ggjfhud","alipay_realname":"作曲家","addtime":"1528856695","addip":"192.168.0.148","status":"1"}
         */

        private BankBean bank;
        private AlipayBean alipay;

        public BankBean getBank() {
            return bank;
        }

        public void setBank(BankBean bank) {
            this.bank = bank;
        }

        public AlipayBean getAlipay() {
            return alipay;
        }

        public void setAlipay(AlipayBean alipay) {
            this.alipay = alipay;
        }

        public static class BankBean {
            /**
             * id : 10
             * user_id : 75
             * account : 889
             * bank : ghh
             * branch : cbjchjfhddj
             * addtime : 1528856573
             * addip : 192.168.0.148
             * status : 1
             */

            private String id;
            private String user_id;
            private String account;
            private String bank;
            private String branch;
            private String addtime;
            private String addip;
            private String status;

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

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getBank() {
                return bank;
            }

            public void setBank(String bank) {
                this.bank = bank;
            }

            public String getBranch() {
                return branch;
            }

            public void setBranch(String branch) {
                this.branch = branch;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

        public static class AlipayBean {
            /**
             * id : 3
             * user_id : 75
             * alipay_account : ggjfhud
             * alipay_realname : 作曲家
             * addtime : 1528856695
             * addip : 192.168.0.148
             * status : 1
             */

            private String id;
            private String user_id;
            private String alipay_account;
            private String alipay_realname;
            private String addtime;
            private String addip;
            private String status;

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

            public String getAlipay_account() {
                return alipay_account;
            }

            public void setAlipay_account(String alipay_account) {
                this.alipay_account = alipay_account;
            }

            public String getAlipay_realname() {
                return alipay_realname;
            }

            public void setAlipay_realname(String alipay_realname) {
                this.alipay_realname = alipay_realname;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
