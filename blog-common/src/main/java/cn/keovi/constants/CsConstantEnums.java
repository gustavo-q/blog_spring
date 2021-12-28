package cn.keovi.constants;


import cn.keovi.utils.Encript;

/**
 * Created by Administrator on 2017/10/27.
 */
public interface CsConstantEnums {

    //账号来源：1-员工(cs_system_employee)；2-微信(cs_customer)
    enum ACCOUNT_SOURCE_TYPE {
        EMPLOYEE(1), CUSTOMER(2) ;
        private final int type;
        ACCOUNT_SOURCE_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
    //账号状态：0-禁用；1-启用；2-待审核
    enum ACCOUNT_STATUS_TYPE {
        INVALID(0), VALID(1), UNCHECKED(2);
        private final int type;
        ACCOUNT_STATUS_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
    //有效性（1有效,启用,在职,发布,是；0失效，禁用,离职，撤回,否）
    enum ACTIVE_STATUS {
        DIABLE(0), ENABLE(1);
        private final int type;
        ACTIVE_STATUS(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }

    //审核意见：0-拒绝；1-同意
    enum AUDIT_OPINION_TYPE {
        DISAGREE(0), AGREE(1);
        private final int type;
        AUDIT_OPINION_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }

    //员工审核意见：0-失效；1-待审核；2已审核；3已拒绝'
    enum STATUS_FLAG {
        INVALID(0),TOAUDITED(1), AGREE(2),DISAGREE(3);
        private final int type;
        STATUS_FLAG(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }

    //岗位（1店长；0店员）
    enum STORE_POST {
        EMPLOYEE(0), MANAGER(1);
        private final int type;
        STORE_POST(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }

    //类型(1微信（wechat），2工程师(APP)，3品牌商客服(PC)，4网点客服(APP))
    enum IM_TYPE {
        WECHAT(1), ENGINEER(2),SERVICE(3),DOT(4);
        private final int type;
        IM_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }

    //状态（0已结束，1已接入，2已转接 )
    enum IM_ACTIVE_STATUS {
        END(0), ACCESS(1),TRANSFERRED(2);
        private final int type;
        IM_ACTIVE_STATUS(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }

    //顾客入库方式（入库方式：0：扫码, 1:扫码后关注，2：搜索微信号后关注，3：录入，4：已关注的用户进行实名认证 )
    enum CUSTOMER_INPUT_TYPE {
        SM(0), SMGZ(1),SSGZ(2),LR(3),GZSMRZ(4);
        private final int type;
        CUSTOMER_INPUT_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }

    //工单处理状态枚举:0-待品牌商确单、1-待工程师接单、2-工程师已接单、3-返单、4-已预约、5-已出发、6-已到达、7-开始处理,、8-待完工确认、9-已完工、10-已取消、11-暂挂
    enum ORDER_DEAL_STATUS{
        ORDER_DEAL_0(0,"待品牌商确单"),ORDER_DEAL_1(1,"待工程师接单"),ORDER_DEAL_2(2,"工程师已接单"),ORDER_DEAL_3(3,"返单"),ORDER_DEAL_4(4,"已预约"),ORDER_DEAL_5(5,"已出发"),
        ORDER_DEAL_6(6,"已到达"),ORDER_DEAL_7(7,"开始处理"),ORDER_DEAL_8(8,"待完工确认"),ORDER_DEAL_9(9,"已完工"),ORDER_DEAL_10(10,"已取消"),ORDER_DEAL_11(11,"暂挂");
        private final Integer type;
        private final String text;

        ORDER_DEAL_STATUS(Integer type, String text) {
            this.type = type;
            this.text = text;
        }

        public Integer getType() {
            return type;
        }

        public String getText() {
            return text;
        }
    }

    //工程师工单关联状态：0-已接单，1-处理中，2-处理完成，3-返单、4-已取消
    enum ORDER_ENGINEER_RELA_STATUS{
        ORDER_ENGINEER_0(0),ORDER_ENGINEER_1(1),ORDER_ENGINEER_2(2),ORDER_ENGINEER_3(3),ORDER_ENGINEER_4(4);
        private final int type;
        ORDER_ENGINEER_RELA_STATUS(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
    //工单流程状态：0-否、无效、禁用；1-是、有效、启用
    enum COMMON_STATUS{
        DISENABLE(0),ENABLE(1);
        private final int type;
        COMMON_STATUS(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
    //工单与其他表管理：1-完工确认单表，2-三包凭证表（保修卡），3-投诉单表
    enum ORDER_OTHER_RELATION{
        FINISH(1),REPAIR(2),COMPLAIN(3);
        private final int type;
        ORDER_OTHER_RELATION(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
    //附件关联类型：1-设备；2-三包凭证；3-投诉；4-故障代码；5-解决方案；6-工单；7-工单安装（维修）记录；8-工单完工确认；9-工单解决方案(取消，合并到7中)；10-工单评价；11-人员信息; 12-备件
    enum COMMON_ATTACH_FILE_TYPE{
        FILE_TYPE_1(301),FILE_TYPE_2(302),FILE_TYPE_3(303),FILE_TYPE_4(304),FILE_TYPE_5(305),FILE_TYPE_6(306),
        FILE_TYPE_7(307),FILE_TYPE_8(308),FILE_TYPE_9(307),FILE_TYPE_10(310),FILE_TYPE_11(311),FILE_TYPE_12(312);
        private final int type;
        COMMON_ATTACH_FILE_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
    //附件关联类型：1-工单；
    enum COMMON_REMARK_TYPE{
        REMARK_TYPE_1(1);
        private final int type;
        COMMON_REMARK_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
    //完工单结算状态：0-待结算；1-结算中；2-已结算；
    enum ORDER_BALANCE_STATUS{
        WAIT_BALANCE(0),IN_BALANCE(1),ALREADY_BALANCE(2);
        private final int type;
        ORDER_BALANCE_STATUS(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
    //工单校验状态：0-未校验；1-校验通过，2-校验不通过；
    enum ORDER_CHECK_STATUS{
        NO_CHECK(0),TG_CHECK(1),BTG_CHECK(2);
        private final int type;
        ORDER_CHECK_STATUS(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
    //工单审核状态审核状态；1-待审批；2-已审批；3-拒绝
	enum ACC_ORDER_AUDIT_STATUS {
		PENDING(1), AUDITED(2), REFUSE(3);
		private final int type;

		ACC_ORDER_AUDIT_STATUS(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}
	//工单结算明细数据状态审核状态；1-同意；2-拒绝
	enum ACC_ORDER_DETAIL_AUDIT_STATUS {
		AGREE(1), REFUSE(2);
		private final int type;

		ACC_ORDER_DETAIL_AUDIT_STATUS(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}
	//工单结算状态 -结算状态：0-待结算；1-结算中；2-已结算
	enum ORDER_ACC_STATUS {
		ACCPEND(0), ACCING(1), ACCED(2);
		private final int type;

		ORDER_ACC_STATUS(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}

    /*enum MESSAGE_TYPE {
        // 消息类型状态：0-普通消息；1-投诉类；2-工单类；3-备件类；4-商城订单类；5-费用结算类;6-账号开通申请；
        MESSAGE_SYSTEM(0),MESSAGE_COMPLAINT(1), MESSAGE_ORDER(2), MESSAGE_SPARE_PART(3), MESSAGE_MALL_ORDER(4), MESSAGE_FEE_BALANCE(5),MESSAGE_ACCOUNT(6);
        private final int type;
        MESSAGE_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }*/

    enum MESSAGE_TYPE {
        MESSAGE_SYSTEM("0", "普通消息"), MESSAGE_COMPLAINT("1", "投诉类消息"),
        MESSAGE_ORDER("2", "工单类消息"), MESSAGE_SPARE_PART("3", "备件类消息"),
        MESSAGE_MALL_ORDER("4", "商城订单类消息"), MESSAGE_FEE_BALANCE("5", "费用结算类消息"),
        MESSAGE_ACCOUNT("6", "账号开通申请类消息");
        private final String code;
        private final String name;

        MESSAGE_TYPE(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static String getName(String code) {
            for (MESSAGE_TYPE messageType : MESSAGE_TYPE.values()) {
                if (code.equals(messageType.code)) {
                    return messageType.getName();
                }
            }
            return "";
        }
    }
    enum MESSAGE_ENTITY_TYPE {
        // 消息实体类型：0-未获取到实体类型,系统消息；1-cs_order_info：工单表；2-cs_complaint：投诉表；3-cs_spare_part_order：备件订单表；4-cs_acc_order-工单费用结算表；5-cs_order_finish-工单完工单表;6-cs_system_user；
        SYS_MESSAGE(0),CS_ORDER_INFO(1), CS_COMPLAINT(2), CS_SPARE_PART_ORDER(3),CS_ACC_ORDER(4),CS_ORDER_FINISH(5),CS_USER(6);
        private final int type;
        MESSAGE_ENTITY_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
    enum PAY_RELATION_TYPE {
        // 关联类型：1-工单；2-备件订单
        ORDER(1),SPARE_ORDER(2);
        private final int type;
        PAY_RELATION_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
    enum PAY_TYPE {
        // 关联类型：1-微信；
        WECHAT(1);
        private final int type;
        PAY_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
    enum PAY_RECORD_STATUS {
        //支付状态：0-待支付；1-支付成功；2-支付超时（订单取消）; 3-支付失败
        WAIT(0),SUCCESS(1),CANCEL(2),FAIL(3);
        private final int type;
        PAY_RECORD_STATUS(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
    //第三方支付记录业务类型
    enum BUSINESS_TYPE {
        //业务类型：1-工单；2-备件订单
        ORDER(1),SPARE_ORDER(2);
        private final int type;
        BUSINESS_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }

    //数据字段中的类别key
    enum DICTIONARY_KEY{
        //产品单位
        product_key("product_unit");
        private final String dickey;
        DICTIONARY_KEY(String dickey){
            this.dickey = dickey;
        }
        public String getDickey(){
            return dickey;
        }
    }

    //各类型单据序列号编码前缀
    enum BILL_PREFIX{
        //三包凭证前缀
        THREE_PACKAGE_PREFIX("RC");
        private String prefixCode;
        BILL_PREFIX(String prefixCode) {
            this.prefixCode = prefixCode;
        }
        public String getPrefixCode() {
            return prefixCode;
        }
    }
    //价目表关联类型.1-安装；2-维修；3-备件；4-服务费
    enum CATALOGUE_RELATED_TYPE {
    	INSTALL(1),REPAIR(2),SPARE(3),SERVICE(4);
        private final int type;
        CATALOGUE_RELATED_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }
    //工单流程记录操作类型：0-品牌商添加工单、1-品牌商确单、2-工程师接单、3-返单、4-预约、5-出发、6-到达、7-开始处理,、8-完工确认、9-微信客户新增工单、10-取消、11-修改工单、12-暂挂
    enum PROCESS_OPERATE_TYPE {
        OPERATE_0(0),OPERATE_1(1),OPERATE_2(2),OPERATE_3(3),OPERATE_4(4),OPERATE_5(5),OPERATE_6(6),OPERATE_7(7),OPERATE_8(8),OPERATE_9(9),OPERATE_10(10),OPERATE_11(11),OPERATE_12(12);
        private final int type;
        PROCESS_OPERATE_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }

    //pc 端工单明细表相关字段
    enum ORDER_REPORT_CELL{
        CELL_0("orderNo","工单编号",true,true,"string"),CELL_1("createdDateTime","创建时间",true,true,"date"),CELL_2("orderTypeName","工单类型",false,true,"string"),CELL_3("productTypeName","产品类型",false,true,"string"),
        CELL_4("productModel","产品型号",false,true,"string"),CELL_5("productSerialNo","产品序列号",false,true,"string"),CELL_6("customerName","客户名称",false,true,"string"),CELL_7("serviceDotName","网点名称",true,true,"string"),
        CELL_8("engineerName","工程师",false,true,"string"),CELL_9("orderStatusText","状态",false,true,"string"),CELL_10("finishDate","完工时间",false,true,"date"),CELL_11("isGuaranteeText","是否保内",false,true,"string"),
        CELL_12("totalMoneyText","收费金额(元)",false,false,"string"),CELL_18("payWayText","收费方式",false,false,"string"),CELL_13("distributionTime","派单时长（分钟）",false,false,"integer"),CELL_14("acceptTime","接单时长（分钟）",false,false,"integer"),CELL_15("planTime","预约时长（分钟）",false,false,"integer"),
        CELL_16("lateTime","到达迟到时间（分钟）",false,false,"integer"),CELL_17("handleTime","处理时长（分钟）",false,false,"integer");
        private String column; //字段英文名
        private String property; //字段中文名
        private boolean isDefaultShow; //作为查询字段是否默认显示
        private boolean isAllowSearch; //是否作为查询字段
        private String type; //字段类型

        ORDER_REPORT_CELL(String column, String property, boolean isDefaultShow, boolean isAllowSearch, String type) {
            this.column = column;
            this.property = property;
            this.isDefaultShow = isDefaultShow;
            this.isAllowSearch = isAllowSearch;
            this.type = type;
        }

        public String getColumn() {
            return column;
        }
        public String getProperty() {
            return property;
        }
        public boolean isDefaultShow() {
            return isDefaultShow;
        }
        public boolean isAllowSearch() {
            return isAllowSearch;
        }
        public String getType() {
            return type;
        }
    }

    //pc 端工单完工明细表相关字段
    enum ORDER_FINISH_REPORT_CELL{
        CELL_0("finishOrderNo","完工确认单编号",true,true,"string"),CELL_1("createdDateTime","创建时间",true,true,"date"),CELL_2("orderTypeName","工单类型",false,true,"string"),CELL_3("productTypeName","产品类型",false,true,"string"),
        CELL_4("productModel","产品型号",false,true,"string"),CELL_5("productSerialNo","产品序列号",false,true,"string"),CELL_6("customerName","客户名称",false,true,"string"),CELL_7("serviceDotName","网点名称",true,true,"string"),
        CELL_8("engineerName","工程师",false,true,"string"),CELL_9("statusText","完工确认单状态",false,true,"string"),CELL_10("finishCheckDateTime","用户确认时间",false,true,"date"),CELL_11("isGuaranteeText","是否保内",false,true,"string"),
        CELL_12("voucherNo","三包凭证号",false,true,"string"),CELL_13("invoiceDate","发票日期",false,true,"date"),CELL_14("projectCostType","项目类型(保内备件/保外备件/其他费用)",false,true,"string"),
        CELL_15("costTypeName","项目名称(备件名称/费用名称)",false,true,"string"), CELL_16("isCostText","是否收费",false,true,"string"),CELL_17("payWayText","收费方式",false,false,"string"),
        CELL_18("num","数量",false,false,"integer"),CELL_19("unitPriceText","单价(元)",false,false,"string"),CELL_20("sumMoney","收费金额(元)",false,false,"string");
        private String column; //字段英文名
        private String property; //字段中文名
        private boolean isDefaultShow; //作为查询字段是否默认显示
        private boolean isAllowSearch; //是否作为查询字段
        private String type; //字段类型

        ORDER_FINISH_REPORT_CELL(String column, String property, boolean isDefaultShow, boolean isAllowSearch, String type) {
            this.column = column;
            this.property = property;
            this.isDefaultShow = isDefaultShow;
            this.isAllowSearch = isAllowSearch;
            this.type = type;
        }

        public boolean isAllowSearch() {
            return isAllowSearch;
        }

        public String getColumn() {
            return column;
        }
        public String getProperty() {
            return property;
        }
        public boolean isDefaultShow() {
            return isDefaultShow;
        }
        public String getType() {
            return type;
        }
    }

    //pc 端工单故障明细表相关字段
    enum ORDER_FAULT_REPORT_CELL{
        CELL_0("finishOrderNo","完工确认单编号",true,true,"string"),CELL_1("createdDateTime","创建时间",true,true,"date"),CELL_2("orderTypeName","工单类型",false,true,"string"),CELL_3("productTypeName","产品类型",false,true,"string"),
        CELL_4("productModel","产品型号",false,true,"string"),CELL_5("productSerialNo","产品序列号",false,true,"string"),CELL_6("customerName","客户名称",false,true,"string"),CELL_7("serviceDotName","网点名称",true,true,"string"),
        CELL_8("engineerName","工程师",false,true,"string"),CELL_9("statusText","完工确认单状态",false,true,"string"),CELL_10("finishCheckDateTime","用户确认时间",false,true,"date"),CELL_11("isGuaranteeText","是否保内",false,true,"string"),
        CELL_12("voucherNo","三包凭证号",false,true,"string"),CELL_13("invoiceDate","发票日期",false,true,"date"),CELL_14("faultCode","故障代码",false,true,"string"),CELL_15("faultCodeName","故障代码名称",false,true,"string"),
        CELL_16("handleTime","处理时长（分钟）",false,false,"integer");
        private String column; //字段英文名
        private String property; //字段中文名
        private boolean isDefaultShow; //作为查询字段是否默认显示
        private boolean isAllowSearch; //是否作为查询字段
        private String type; //字段类型

        ORDER_FAULT_REPORT_CELL(String column, String property, boolean isDefaultShow, boolean isAllowSearch, String type) {
            this.column = column;
            this.property = property;
            this.isDefaultShow = isDefaultShow;
            this.isAllowSearch = isAllowSearch;
            this.type = type;
        }

        public boolean isAllowSearch() {
            return isAllowSearch;
        }

        public String getColumn() {
            return column;
        }
        public String getProperty() {
            return property;
        }
        public boolean isDefaultShow() {
            return isDefaultShow;
        }
        public String getType() {
            return type;
        }
    }

    //完工确认单状态
    enum ORDER_FINISH_STATUS{
        STATUS_1(1,"待确认"),STATUS_2(2,"已确认");
        private Integer type;
        private String text;

        ORDER_FINISH_STATUS(Integer type, String text) {
            this.type = type;
            this.text = text;
        }

        public Integer getType() {
            return type;
        }

        public String getText() {
            return text;
        }
    }

    //pc 端工单报表类型：
    public static final String ORDER = "order";//工单明细表
    public static final String ORDER_FINISH = "order_finish";//完工确认单报表
    public static final String ORDER_FAULT = "order_fault";//工单故障明细报表

    //功能标识key
    public static final String CS_ORDER_INFO = "cs_order_info";//工单信息
    public static final String CS_SYSTEM_EMPLOYEE = "cs_system_employee";//员工信息
    public static final String CS_EQUIPMENT = "cs_equipment";//设备
    public static final String CS_THREE_PACKAGE_VOUCHER = "cs_three_package_voucher";//三包凭证
    public static final String CS_SPARE_PART = "cs_spare_part";//备件
    public static final String CS_ORDER_FINISH = "cs_order_finish";//完工单
    public static final String CS_KNOWLEDGE_SOLUTION = "cs_knowledge_solution";//解决方案
    public static final String CS_ACC_ORDER = "cs_acc_order";//结算
    public static final String CS_COMPLAINT = "cs_complaint";//投诉
    public static final String CS_PRODUCT = "cs_product";//产品

    //excel 导出文件的存放地址
    public static final String EXCEL_ADDRESS ="/excel";
    //服务器后台保存文件地址 begin
    public static final String BASE_ADDRESS="/uf";

    //工单编号缓存key
    public final static String ORDER_NO_STR_KEY = "order_no_str_key";
    //产品类型缓存key
    public final static String PRODUCT_CATEGORY_TYPE_KEY = "product_category_type_key";
    //工单类型缓存key
    public final static String ORDER_TYPE_KEY = "order_type_key";
    //产品信息缓存key
    public final static String PRODUCT_INFO_KEY = "product_info_key";
    //省份缓存key
    public final static String PROVINCE_INFO_KEY = "province_info_key";
    //设备关联的产品信息缓存key
    public final static String EQUIPMENT_PRODUCT_KEY = "equipment_product_key";
    //数据字典中的产品单位缓存key
    public final static String PRODUCT_UNIT_KEY = "product_unit_key";
    //产品类别里面的备件类别缓存key
    public final static String SPART_CATEGORY_TYPE_KEY = "spart_category_type_key";
    //产品型号所对应的产品id集合字符串缓存key
    public final static String PRODUCT_ID_STR_KEY = "product_id_str_key";

    //工单导入类型
    public final static String INSTALL_ORDER_IMPORT_TYPE = "install_order_import_type";//安装
    public final static String REPAIR_ORDER_IMPORT_TYPE = "repair_order_import_type";//维修
    //设备导入类型
    public final static String EQUIPMENT_IMPORT_TYPE = "equipment_import_type";
    //三包凭证导入类型
    public final static String THREE_PACKAGE_IMPORT_TYPE = "three_package_import_type";
    //备件导入类型
    public final static String SPARE_PART_IMPORT_TYPE = "spare_part_import_type";

    //初始化密码
    public final static String DEFAULT_PASSWORD = Encript.DEFAULT_PASSWORD;
    //产品导入类型
    public final static String PRODUCT_IMPORT_TYPE = "product_import_type";

    public final static Integer exportMaxNum = 800;
}
