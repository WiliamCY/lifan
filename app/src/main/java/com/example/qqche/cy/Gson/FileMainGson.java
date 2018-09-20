package com.example.qqche.cy.Gson;

import java.util.List;

public class FileMainGson {
    /**
     * data : {"archives":{"id":"2","name":"甬e贷","company":"宁波旺信投资管理有限公司","logo":"upload/logo/201803/30/i6DZY3EKoXIPBR6dxFEGuTxMGiXXJcDdvKMkcFBbxCuCnxGnQHO47GN.png","website":"https://www.yongedai.com/","online_time":"1446393600","depository":"江西银行","register_money":"5000万","paid_money":"5000万","asset_type":"车贷,房贷,个人信用贷,","cridit_attorn":"支持","apr":"16.00","product_limit":"1,3,6,12月标","risk_reserve":"0.00","management_cost":"投资所获利息的5%","withdraw_start":"100.00","present_cycle":"T+0","withdraw_cost":"每笔1元，充值未投资资金千分之六手续费","recharge_cost":"无","other_cost":"vip费用100/年","control_evaluation":"优","cast_background":"秀","join_association":"暂无","icp":"浙ICP证 20160085号","icp_record":"-","about":"甬e贷于2015年11月2日上线运营，是宁波旺信投资管理有限公司推出的创新性互联网金融平台，为需求投资理财服务的用户提供了一条安全高收益的在线理财通道。同时也给需要融资和理财的用户搭建起了一个透明、便捷的网络借贷互动平台。公司总部位于宁波，注册并实缴资金5000万元，公司多位股东均实力雄厚，并且拥有多年金融行业从业经验，对宁波本土市场有着独到的理解。甬e贷自上线以来一直积极拥护国家监管政策，坚持走合法合规的道路，平台于2016年2月24日，成功取得ICP经营许可证，成为首批获得ICP许可证的平台，并于2017年7月26日，正式上线江西银行存管系统！","company_exe":"邢亚义总经理1963年出生于宁波市，负责宁波旺信投资管理有限公司（甬e贷）风控管理。1985年进入金融市场，在金融行业内多年的打拼中不仅积累了丰富的线下放贷和催收经验，同时也对宁波民间借贷市场也有着深入的了解。孔来欢运营总监1983年出生于宁波市，负责宁波旺信投资管理有限公司（甬e贷）线上运营，获得国家高级信用管理师、注册金融风险管理师、融资规划师职称。","status":"1"},"archives_info":{"id":"2","legal_repren":"邢亚义","operating_state":"存续","register_time":"2015-05-21","indestry":"商务服务业","enterprise_type":"有限责任公司(自然人投资或控股)","approval":"2017-08-03","business_limit":"2015-05-02至2035-05-2","organizatin_code":"91330203316983349Y","credit_code":"91330203316983349Y","register_num":"91330203316983349Y","register_autn":"宁波市海曙区市场监督管理局","record_code":"浙ICP备15026462号","record_sub":"宁波旺信投资管理有限公司","reg_add":"宁波市海曙区高桥镇宋家漕村","scope_operation":"投资管理及咨询、实业投资、企业管理咨询、商务信息咨询、房产信息咨询、市场营销策划: 接受金融机构委托从事金融信息技术外包、按受金融机构委托从事金融业务流程外包、接受金融机构委托从事金融知识流程外包。(未经企融等监管部门批准不得从事吸收存款、融资担保、代客理财、向社会公众集( 融)资等金阻业务) (依法须经批准的项目，经相关部门批准后方可开展经营活动)","natural_person":[{"type":"自然人","name":"林安兴","money":"1650万"},{"type":"自然人","name":"汪来君","money":"1650万"}],"legal_person":[{"type":"法人","name":"邢亚义","money":"1700万"}],"company_tel":"0574-87636307","company_com":"640293190","company_type":"有限责任公司","address":"宁波市海曙区冷静街8号银亿时代广场5-15","record_domain":"-","record_domain_time":"-","record_company_type":"-","record_company_name":"-"}}
     * status : 1
     * string : Success
     * msg : 数据获取成功
     */

    private DataBean data;
    private String status;
    private String string;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * archives : {"id":"2","name":"甬e贷","company":"宁波旺信投资管理有限公司","logo":"upload/logo/201803/30/i6DZY3EKoXIPBR6dxFEGuTxMGiXXJcDdvKMkcFBbxCuCnxGnQHO47GN.png","website":"https://www.yongedai.com/","online_time":"1446393600","depository":"江西银行","register_money":"5000万","paid_money":"5000万","asset_type":"车贷,房贷,个人信用贷,","cridit_attorn":"支持","apr":"16.00","product_limit":"1,3,6,12月标","risk_reserve":"0.00","management_cost":"投资所获利息的5%","withdraw_start":"100.00","present_cycle":"T+0","withdraw_cost":"每笔1元，充值未投资资金千分之六手续费","recharge_cost":"无","other_cost":"vip费用100/年","control_evaluation":"优","cast_background":"秀","join_association":"暂无","icp":"浙ICP证 20160085号","icp_record":"-","about":"甬e贷于2015年11月2日上线运营，是宁波旺信投资管理有限公司推出的创新性互联网金融平台，为需求投资理财服务的用户提供了一条安全高收益的在线理财通道。同时也给需要融资和理财的用户搭建起了一个透明、便捷的网络借贷互动平台。公司总部位于宁波，注册并实缴资金5000万元，公司多位股东均实力雄厚，并且拥有多年金融行业从业经验，对宁波本土市场有着独到的理解。甬e贷自上线以来一直积极拥护国家监管政策，坚持走合法合规的道路，平台于2016年2月24日，成功取得ICP经营许可证，成为首批获得ICP许可证的平台，并于2017年7月26日，正式上线江西银行存管系统！","company_exe":"邢亚义总经理1963年出生于宁波市，负责宁波旺信投资管理有限公司（甬e贷）风控管理。1985年进入金融市场，在金融行业内多年的打拼中不仅积累了丰富的线下放贷和催收经验，同时也对宁波民间借贷市场也有着深入的了解。孔来欢运营总监1983年出生于宁波市，负责宁波旺信投资管理有限公司（甬e贷）线上运营，获得国家高级信用管理师、注册金融风险管理师、融资规划师职称。","status":"1"}
         * archives_info : {"id":"2","legal_repren":"邢亚义","operating_state":"存续","register_time":"2015-05-21","indestry":"商务服务业","enterprise_type":"有限责任公司(自然人投资或控股)","approval":"2017-08-03","business_limit":"2015-05-02至2035-05-2","organizatin_code":"91330203316983349Y","credit_code":"91330203316983349Y","register_num":"91330203316983349Y","register_autn":"宁波市海曙区市场监督管理局","record_code":"浙ICP备15026462号","record_sub":"宁波旺信投资管理有限公司","reg_add":"宁波市海曙区高桥镇宋家漕村","scope_operation":"投资管理及咨询、实业投资、企业管理咨询、商务信息咨询、房产信息咨询、市场营销策划: 接受金融机构委托从事金融信息技术外包、按受金融机构委托从事金融业务流程外包、接受金融机构委托从事金融知识流程外包。(未经企融等监管部门批准不得从事吸收存款、融资担保、代客理财、向社会公众集( 融)资等金阻业务) (依法须经批准的项目，经相关部门批准后方可开展经营活动)","natural_person":[{"type":"自然人","name":"林安兴","money":"1650万"},{"type":"自然人","name":"汪来君","money":"1650万"}],"legal_person":[{"type":"法人","name":"邢亚义","money":"1700万"}],"company_tel":"0574-87636307","company_com":"640293190","company_type":"有限责任公司","address":"宁波市海曙区冷静街8号银亿时代广场5-15","record_domain":"-","record_domain_time":"-","record_company_type":"-","record_company_name":"-"}
         */

        private ArchivesBean archives;
        private ArchivesInfoBean archives_info;

        public ArchivesBean getArchives() {
            return archives;
        }

        public void setArchives(ArchivesBean archives) {
            this.archives = archives;
        }

        public ArchivesInfoBean getArchives_info() {
            return archives_info;
        }

        public void setArchives_info(ArchivesInfoBean archives_info) {
            this.archives_info = archives_info;
        }

        public static class ArchivesBean {
            /**
             * id : 2
             * name : 甬e贷
             * company : 宁波旺信投资管理有限公司
             * logo : upload/logo/201803/30/i6DZY3EKoXIPBR6dxFEGuTxMGiXXJcDdvKMkcFBbxCuCnxGnQHO47GN.png
             * website : https://www.yongedai.com/
             * online_time : 1446393600
             * depository : 江西银行
             * register_money : 5000万
             * paid_money : 5000万
             * asset_type : 车贷,房贷,个人信用贷,
             * cridit_attorn : 支持
             * apr : 16.00
             * product_limit : 1,3,6,12月标
             * risk_reserve : 0.00
             * management_cost : 投资所获利息的5%
             * withdraw_start : 100.00
             * present_cycle : T+0
             * withdraw_cost : 每笔1元，充值未投资资金千分之六手续费
             * recharge_cost : 无
             * other_cost : vip费用100/年
             * control_evaluation : 优
             * cast_background : 秀
             * join_association : 暂无
             * icp : 浙ICP证 20160085号
             * icp_record : -
             * about : 甬e贷于2015年11月2日上线运营，是宁波旺信投资管理有限公司推出的创新性互联网金融平台，为需求投资理财服务的用户提供了一条安全高收益的在线理财通道。同时也给需要融资和理财的用户搭建起了一个透明、便捷的网络借贷互动平台。公司总部位于宁波，注册并实缴资金5000万元，公司多位股东均实力雄厚，并且拥有多年金融行业从业经验，对宁波本土市场有着独到的理解。甬e贷自上线以来一直积极拥护国家监管政策，坚持走合法合规的道路，平台于2016年2月24日，成功取得ICP经营许可证，成为首批获得ICP许可证的平台，并于2017年7月26日，正式上线江西银行存管系统！
             * company_exe : 邢亚义总经理1963年出生于宁波市，负责宁波旺信投资管理有限公司（甬e贷）风控管理。1985年进入金融市场，在金融行业内多年的打拼中不仅积累了丰富的线下放贷和催收经验，同时也对宁波民间借贷市场也有着深入的了解。孔来欢运营总监1983年出生于宁波市，负责宁波旺信投资管理有限公司（甬e贷）线上运营，获得国家高级信用管理师、注册金融风险管理师、融资规划师职称。
             * status : 1
             */

            private String id;
            private String name;
            private String company;
            private String logo;
            private String website;
            private String online_time;
            private String depository;
            private String register_money;
            private String paid_money;
            private String asset_type;
            private String cridit_attorn;
            private String apr;
            private String product_limit;
            private String risk_reserve;
            private String management_cost;
            private String withdraw_start;
            private String present_cycle;
            private String withdraw_cost;
            private String recharge_cost;
            private String other_cost;
            private String control_evaluation;
            private String cast_background;
            private String join_association;
            private String icp;
            private String icp_record;
            private String about;
            private String company_exe;
            private String status;

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

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public String getOnline_time() {
                return online_time;
            }

            public void setOnline_time(String online_time) {
                this.online_time = online_time;
            }

            public String getDepository() {
                return depository;
            }

            public void setDepository(String depository) {
                this.depository = depository;
            }

            public String getRegister_money() {
                return register_money;
            }

            public void setRegister_money(String register_money) {
                this.register_money = register_money;
            }

            public String getPaid_money() {
                return paid_money;
            }

            public void setPaid_money(String paid_money) {
                this.paid_money = paid_money;
            }

            public String getAsset_type() {
                return asset_type;
            }

            public void setAsset_type(String asset_type) {
                this.asset_type = asset_type;
            }

            public String getCridit_attorn() {
                return cridit_attorn;
            }

            public void setCridit_attorn(String cridit_attorn) {
                this.cridit_attorn = cridit_attorn;
            }

            public String getApr() {
                return apr;
            }

            public void setApr(String apr) {
                this.apr = apr;
            }

            public String getProduct_limit() {
                return product_limit;
            }

            public void setProduct_limit(String product_limit) {
                this.product_limit = product_limit;
            }

            public String getRisk_reserve() {
                return risk_reserve;
            }

            public void setRisk_reserve(String risk_reserve) {
                this.risk_reserve = risk_reserve;
            }

            public String getManagement_cost() {
                return management_cost;
            }

            public void setManagement_cost(String management_cost) {
                this.management_cost = management_cost;
            }

            public String getWithdraw_start() {
                return withdraw_start;
            }

            public void setWithdraw_start(String withdraw_start) {
                this.withdraw_start = withdraw_start;
            }

            public String getPresent_cycle() {
                return present_cycle;
            }

            public void setPresent_cycle(String present_cycle) {
                this.present_cycle = present_cycle;
            }

            public String getWithdraw_cost() {
                return withdraw_cost;
            }

            public void setWithdraw_cost(String withdraw_cost) {
                this.withdraw_cost = withdraw_cost;
            }

            public String getRecharge_cost() {
                return recharge_cost;
            }

            public void setRecharge_cost(String recharge_cost) {
                this.recharge_cost = recharge_cost;
            }

            public String getOther_cost() {
                return other_cost;
            }

            public void setOther_cost(String other_cost) {
                this.other_cost = other_cost;
            }

            public String getControl_evaluation() {
                return control_evaluation;
            }

            public void setControl_evaluation(String control_evaluation) {
                this.control_evaluation = control_evaluation;
            }

            public String getCast_background() {
                return cast_background;
            }

            public void setCast_background(String cast_background) {
                this.cast_background = cast_background;
            }

            public String getJoin_association() {
                return join_association;
            }

            public void setJoin_association(String join_association) {
                this.join_association = join_association;
            }

            public String getIcp() {
                return icp;
            }

            public void setIcp(String icp) {
                this.icp = icp;
            }

            public String getIcp_record() {
                return icp_record;
            }

            public void setIcp_record(String icp_record) {
                this.icp_record = icp_record;
            }

            public String getAbout() {
                return about;
            }

            public void setAbout(String about) {
                this.about = about;
            }

            public String getCompany_exe() {
                return company_exe;
            }

            public void setCompany_exe(String company_exe) {
                this.company_exe = company_exe;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

        public static class ArchivesInfoBean {
            /**
             * id : 2
             * legal_repren : 邢亚义
             * operating_state : 存续
             * register_time : 2015-05-21
             * indestry : 商务服务业
             * enterprise_type : 有限责任公司(自然人投资或控股)
             * approval : 2017-08-03
             * business_limit : 2015-05-02至2035-05-2
             * organizatin_code : 91330203316983349Y
             * credit_code : 91330203316983349Y
             * register_num : 91330203316983349Y
             * register_autn : 宁波市海曙区市场监督管理局
             * record_code : 浙ICP备15026462号
             * record_sub : 宁波旺信投资管理有限公司
             * reg_add : 宁波市海曙区高桥镇宋家漕村
             * scope_operation : 投资管理及咨询、实业投资、企业管理咨询、商务信息咨询、房产信息咨询、市场营销策划: 接受金融机构委托从事金融信息技术外包、按受金融机构委托从事金融业务流程外包、接受金融机构委托从事金融知识流程外包。(未经企融等监管部门批准不得从事吸收存款、融资担保、代客理财、向社会公众集( 融)资等金阻业务) (依法须经批准的项目，经相关部门批准后方可开展经营活动)
             * natural_person : [{"type":"自然人","name":"林安兴","money":"1650万"},{"type":"自然人","name":"汪来君","money":"1650万"}]
             * legal_person : [{"type":"法人","name":"邢亚义","money":"1700万"}]
             * company_tel : 0574-87636307
             * company_com : 640293190
             * company_type : 有限责任公司
             * address : 宁波市海曙区冷静街8号银亿时代广场5-15
             * record_domain : -
             * record_domain_time : -
             * record_company_type : -
             * record_company_name : -
             */

            private String id;
            private String legal_repren;
            private String operating_state;
            private String register_time;
            private String indestry;
            private String enterprise_type;
            private String approval;
            private String business_limit;
            private String organizatin_code;
            private String credit_code;
            private String register_num;
            private String register_autn;
            private String record_code;
            private String record_sub;
            private String reg_add;
            private String scope_operation;
            private String company_tel;
            private String company_com;
            private String company_type;
            private String address;
            private String record_domain;
            private String record_domain_time;
            private String record_company_type;
            private String record_company_name;
            private List<NaturalPersonBean> natural_person;
            private List<LegalPersonBean> legal_person;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLegal_repren() {
                return legal_repren;
            }

            public void setLegal_repren(String legal_repren) {
                this.legal_repren = legal_repren;
            }

            public String getOperating_state() {
                return operating_state;
            }

            public void setOperating_state(String operating_state) {
                this.operating_state = operating_state;
            }

            public String getRegister_time() {
                return register_time;
            }

            public void setRegister_time(String register_time) {
                this.register_time = register_time;
            }

            public String getIndestry() {
                return indestry;
            }

            public void setIndestry(String indestry) {
                this.indestry = indestry;
            }

            public String getEnterprise_type() {
                return enterprise_type;
            }

            public void setEnterprise_type(String enterprise_type) {
                this.enterprise_type = enterprise_type;
            }

            public String getApproval() {
                return approval;
            }

            public void setApproval(String approval) {
                this.approval = approval;
            }

            public String getBusiness_limit() {
                return business_limit;
            }

            public void setBusiness_limit(String business_limit) {
                this.business_limit = business_limit;
            }

            public String getOrganizatin_code() {
                return organizatin_code;
            }

            public void setOrganizatin_code(String organizatin_code) {
                this.organizatin_code = organizatin_code;
            }

            public String getCredit_code() {
                return credit_code;
            }

            public void setCredit_code(String credit_code) {
                this.credit_code = credit_code;
            }

            public String getRegister_num() {
                return register_num;
            }

            public void setRegister_num(String register_num) {
                this.register_num = register_num;
            }

            public String getRegister_autn() {
                return register_autn;
            }

            public void setRegister_autn(String register_autn) {
                this.register_autn = register_autn;
            }

            public String getRecord_code() {
                return record_code;
            }

            public void setRecord_code(String record_code) {
                this.record_code = record_code;
            }

            public String getRecord_sub() {
                return record_sub;
            }

            public void setRecord_sub(String record_sub) {
                this.record_sub = record_sub;
            }

            public String getReg_add() {
                return reg_add;
            }

            public void setReg_add(String reg_add) {
                this.reg_add = reg_add;
            }

            public String getScope_operation() {
                return scope_operation;
            }

            public void setScope_operation(String scope_operation) {
                this.scope_operation = scope_operation;
            }

            public String getCompany_tel() {
                return company_tel;
            }

            public void setCompany_tel(String company_tel) {
                this.company_tel = company_tel;
            }

            public String getCompany_com() {
                return company_com;
            }

            public void setCompany_com(String company_com) {
                this.company_com = company_com;
            }

            public String getCompany_type() {
                return company_type;
            }

            public void setCompany_type(String company_type) {
                this.company_type = company_type;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getRecord_domain() {
                return record_domain;
            }

            public void setRecord_domain(String record_domain) {
                this.record_domain = record_domain;
            }

            public String getRecord_domain_time() {
                return record_domain_time;
            }

            public void setRecord_domain_time(String record_domain_time) {
                this.record_domain_time = record_domain_time;
            }

            public String getRecord_company_type() {
                return record_company_type;
            }

            public void setRecord_company_type(String record_company_type) {
                this.record_company_type = record_company_type;
            }

            public String getRecord_company_name() {
                return record_company_name;
            }

            public void setRecord_company_name(String record_company_name) {
                this.record_company_name = record_company_name;
            }

            public List<NaturalPersonBean> getNatural_person() {
                return natural_person;
            }

            public void setNatural_person(List<NaturalPersonBean> natural_person) {
                this.natural_person = natural_person;
            }

            public List<LegalPersonBean> getLegal_person() {
                return legal_person;
            }

            public void setLegal_person(List<LegalPersonBean> legal_person) {
                this.legal_person = legal_person;
            }

            public static class NaturalPersonBean {
                /**
                 * type : 自然人
                 * name : 林安兴
                 * money : 1650万
                 */

                private String type;
                private String name;
                private String money;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }
            }

            public static class LegalPersonBean {
                /**
                 * type : 法人
                 * name : 邢亚义
                 * money : 1700万
                 */

                private String type;
                private String name;
                private String money;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }
            }
        }
    }
}
