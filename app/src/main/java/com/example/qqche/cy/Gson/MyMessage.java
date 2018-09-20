package com.example.qqche.cy.Gson;

public class MyMessage {

    /**
     * status : 1
     * string : Success
     * msg : 获取成功
     * data : {"total":"0","use_money":"0","no_use_money":"0","income":"0","unaudited_num":"0","invest_recently":"0","bank_status":"0","imperfect_phone":"130****2969"}
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
         * total : 0
         * use_money : 0
         * no_use_money : 0
         * income : 0
         * unaudited_num : 0
         * invest_recently : 0
         * bank_status : 0
         * imperfect_phone : 130****2969
         */

        private String total;
        private String use_money;
        private String no_use_money;
        private String income;
        private String unaudited_num;
        private String invest_recently;
        private String bank_status;
        private String imperfect_phone;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
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

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getUnaudited_num() {
            return unaudited_num;
        }

        public void setUnaudited_num(String unaudited_num) {
            this.unaudited_num = unaudited_num;
        }

        public String getInvest_recently() {
            return invest_recently;
        }

        public void setInvest_recently(String invest_recently) {
            this.invest_recently = invest_recently;
        }

        public String getBank_status() {
            return bank_status;
        }

        public void setBank_status(String bank_status) {
            this.bank_status = bank_status;
        }

        public String getImperfect_phone() {
            return imperfect_phone;
        }

        public void setImperfect_phone(String imperfect_phone) {
            this.imperfect_phone = imperfect_phone;
        }
    }
}
