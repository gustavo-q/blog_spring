package cn.keovi.constants;

/**
 * dms系统全局常量
 * @author
 *
 */
public interface DmsModuleEnums {

	String ROLE_CODE_ADMIN = "ADMIN";
	//门店导入
	String EXCEL_STORE_IMPORT = "excel_store_import";
	//渠道导入
	String EXCEL_CHANNEL_IMPORT = "excel_channel_import";
	//产品导入
	String EXCEL_PRODUCT_IMPORT = "excel_product_import";
	//账号（人员）导入
	String EXCEL_USER_IMPORT = "excel_user_import";
	//产品分类导入
	String EXCEL_CATEGORY_IMPORT = "excel_category_import";
	//产品规格导入
	String EXCEL_SPEC_IMPORT = "excel_spec_import";
	String fileDownloadUrl = "/api/v1/dms/commonAttachUpload/getFile?id=";
	String DEFAULT_PRODUCT_IMG = "/image/product_default.png";
	String DMS_STORAGE_TRANSACTION_ID = "dms_storage_transaction_id";

	String SYSTEM_DMS = "dms";
	String SYSTEM_PROCENTER = "procenter";
	String SYSTEM_YOUKEE = "youkee";
	String SYSTEM_HAOROBOT = "haoRobot";

	/**
	 * 发布范围关联类型：1-组织；2-区域
	 */
	enum RANGE_TYPE {
		ORGANIZATION(1), AREA(2);
		private final int type;

		RANGE_TYPE(int type) {
			this.type = type;
		}

		public int getType() {
			return this.type;
		}
	}




	/***
	 * @Author zhuxiongbin
	 * @Description //sfa签到类型
	 * @Date 2020-09-24 11:22
	 * @Param
	 * @return
	 **/
	enum SIGN_TYPE {
		MORNINGSTART("morningStart","上班签到"),AFTERNOONEND("afternoonEnd","下班签退"),SINGIN("signIn","签到"),SINGOUT("signOut","签退");
		private final String type;
		private final String desc;

		SIGN_TYPE(String type,String desc) {
			this.type = type;
			this.desc = desc;
		}

		public String getDesc(){
			return desc;
		}

		public String getType(){
			return type;
		}

		public static String getDesc(String type){
			if (type == null) {
				return null;
			}
			for (SIGN_TYPE e : SIGN_TYPE.values()) {
				if (e.type.equals(type)) {
					return e.getDesc();
				}
			}
			return null;
		}

	}

	//产品标签枚举 1-新品2-推荐3-热销4-爆款
	enum PRODUCT_LABEL{
		NEW(1), RECOMMEND(2), SELLING(3),EXPLOSION(4);
		private final int type;
		PRODUCT_LABEL(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

	//等级类型枚举
	enum RANK_TYPE{
		RANK_QT(0), RANK_BY(1), RANK_HJ(2),RANK_ZS(3);
		private final int type;
		RANK_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
	}

	//渠道类型枚举：1-销售公司；2-代理商；3-经销商；4-分销商
	enum CHANNEL_TYPE{
		SALE_COMPANY(0), AGENT(1), DEALER(2), DISTRIBUTOR(3);
		private final int type;
		CHANNEL_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
	}

	//联系信息关联表类型枚举：1-渠道联系人；2-门店联系人；3-员工
	enum CONTACT_RELATED_TABLE_TYPE{
		CHANNEL_CONTACT(1), STORE_CONTACT(2), EMPLOYEE(3);
		private final int type;
		CONTACT_RELATED_TABLE_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
	}

	//联系信息联系方式类型枚举：1-手机；2-座机；3-Email；4-QQ；5-微信；6-钉钉
	enum CONTACT_TYPE{
		MOBILE(1), PHONE(2), EMAIL(3), QQ(4), WECHAT(5);
		private final int type;
		CONTACT_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
	}
	//备注关联表类型枚举：1-渠道（dms_channel）；2-渠道联系人（dms_channel_contacts）；3-门店（dms_shop）；4-门店店员（dms_seller）
	enum REMARK_RELATED_TABLE_TYPE{
		CHANNEL_MAIN(1), CHANNEL_CONTACT(2), STORE_SHOP(1), STORE_SELLER(2);
		private final int type;
		REMARK_RELATED_TABLE_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
	}
	//附件关联表类型枚举：1-渠道（dms_channel）；2-渠道联系人（dms_channel_contacts）；3-门店（dms_shop）；4-门店店员（dms_seller）；
	//5-订单评价（dms_order_evaluation）；6-转账凭证（dms_order_payment）7-设备（dc_equipment）8-产品 10设备检查 9 不知道被谁使用了
	//41退货凭证
	enum ATTACHMENT_RELATED_TABLE_TYPE{
		CHANNEL_MAIN(1), CHANNEL_CONTACT(2), STORE_SHOP(1), STORE_SELLER(2), ORDER_PRODUCT_EVALUATION(5), TRANSFER_VOUCHER(6),DC_EQUIPMENT(7),DC_PRODUCT(8),Equipment_Check(10),
		ORDER_RETURN(41);
		private final int type;
		ATTACHMENT_RELATED_TABLE_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
	}

	//账号来源：1-员工(dms_employee)；2-渠道商(dms_channel_contacts)；3-门店(dms_seller)
	enum ACCOUNT_SOURCE_TYPE {
		EMPLOYEE(1), CHANNEL_CONTACTS(2), SELLER(3);
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
		DISABLE(0), ENABLE(1);
		private final int type;
		ACTIVE_STATUS(int type) {
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
	//新闻资讯类型（0新闻资讯包括下面两个都是走数据字典,）
	enum NEWS_TYPE {
		NEWS("0"),NOTICE("1"),INFO("2");
		private final String type;
		NEWS_TYPE(String type) {
			this.type = type;
		}
		public String getType() {
			return type;
		}
	}
	//通知类型（0通知，1促销）
	enum NOTICE_TYPE {
		NOTICE(0),PROMOTION(1);
		private final int type;
		NOTICE_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}
	//渠道赋能类型类型（0渠道政策，1理论学习）
	enum CHANNEL_INFOMATION_TYPE {
		POLICY(0),STUDY(1);
		private final int type;
		CHANNEL_INFOMATION_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}
	//审批状态（0待提交，1待审批，2审批通过，3审批不通过）
	enum APPROVE_STATUS {
		APPROVE_STATUS_DTJ(0),APPROVE_STATUS_DSP(1),APPROVE_STATUS_TG(2),APPROVE_STATUS_BTG(3);
		private final int type;
		APPROVE_STATUS(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

	public final static String PROXY_TEMPLATE_KEY = "proxyTemplate";

	//home_images图片类型（0首页banner图）
	enum HOME_IMAGES_TYPE {
		BANNER(0);
		private final int type;
		HOME_IMAGES_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}
	//有效性（1已删除,是；0未删除）
	enum REMOVE_FLAG_STATUS {
		NOREMOVE(0), REMOVE(1);
		private final int type;
		REMOVE_FLAG_STATUS(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}
	//调货类型 1-渠道铺货；2-门店之间调货；3-门店向渠道调货
	enum TRANSFER_APPLY_TYPE {
		CHANNEL_PH(1), STORE_DH(2),CHANNEL_DH(3);
		private final int type;
		TRANSFER_APPLY_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}
	//处理状态1-已处理；0-未处理
	enum PROCESS_STATUS {
		UNPROCESS(0), PROCESSED(1);
		private final int type;
		PROCESS_STATUS(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

	// 我发起的-发货状态 1-等待对方发货；2-待收货；3-已完成；4-取消
	enum DELIVERY_STATUS {
		WAIT(1), WAIT_DELIVERY(2), FINISH(3), CANCEL(4);
		private final int type;

		DELIVERY_STATUS(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}

	// 我收到的-收货状态（1-待发货；2-已发货；3-已完成）；4-取消
	enum RECEIVE_STATUS {
		WAIT(1), RECEIVED(2), FINISH(3) ,CANCEL(4);
		private final int type;

		RECEIVE_STATUS(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}

	// 订单状态：1-待审核；2-待发货(待厂商发货)；3-待客户收获(待收货)；4-已完成；5-已取消。 注：标注的是厂商端的状态，括号中是订货端的状态
	enum ORDER_STATUS_TYPE {
		WAIT_CONFIRM(0), AUDIT_AWAIT(1), DELIVER_AWAIT(2), RECEIVE_AWAIT(3), FINISHED(4), CANCEL(5);
        private final int type;
	    ORDER_STATUS_TYPE(int type) {
	    	this.type = type;
		}
		public int getType() {
	    	return type;
		}
	}

	// 订单收款状态：1-未付款；2-待确认收款；3-部分到账；4-已收款。
	enum PAYMENT_STATUS_TYPE {
		UNPAY(1), CONFIRM_AWAIT(2), PART_PAID(3), FINISHED(4);
		private final int type;
		PAYMENT_STATUS_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

	// 订单收款确认状态：0-未确认；1-已确认
	enum PAYMENT_CONFIRM_TYPE {
	    UNCONFIRMED(0), CONFIRMED(1);
		private final int type;
		PAYMENT_CONFIRM_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
		    return type;
        }
	}


	// 订单支付类型：1-对公转账；2-在线支付
	enum PAYMENT_TYPE {
		PUBLIC_TRANSFER(1), ONLINE_PAYMENT(2);
		private final int type;
		PAYMENT_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

	/*
	订单发货全部都是PC发
	*/
	enum MESSAGE_TYPE {
		MESSAGE_SYSTEM("0", "普通消息"), MESSAGE_NOTICE("1", "公告"),
		MESSAGE_PROMOTION("2", "促销"), MESSAGE_ORDER("3", "订单"),
		MESSAGE_GOODS("4", "要货"), MESSAGE_LOGISTICS("5", "物流"),
		MESSAGE_COUPONS("6", "卡券"), MESSAGE_ACCOUNT("7", "账号开通申请"),
		MESSAGE_INVOICE("8", "增值税发票审核"), MESSAGE_RETURN("9", "退货"),
		MESSAGE_ORDER_APP("10", "APP端订单"), MESSAGE_ORDER_PC("11", "PC端订单"),
		MESSAGE_LOGISTICS_APP("12", "APP端物流"), MESSAGE_LOGISTICS_PC("13", "PC端物流"),
		MESSAGE_INVOICE_PC("14", "增值税发票(PC审核消息)"), MESSAGE_RETURN_APP("15", "APP端退换货"),
		MESSAGE_RETURN_PC("16", "PC端退换货"), MESSAGE_ORDER_PURCHASE("17", "采购订单"),
		MESSAGE_ORDER_PURCHASE_PC("18", "PC端采购订单"), MESSAGE_ORDER_PURCHASE_APP("19", "APP端采购订单"),
		MESSAGE_RETURN_PURCHASE("20", "采购退换货"), MESSAGE_RETURN_PURCHASE_PC("21", "PC端采购退换货"),
		MESSAGE_RETURN_PURCHASE_APP("22", "APP端采购退换货"), MESSAGE_LOGISTICS_PURCHASE("23", "采购物流"),
		MESSAGE_RETURN_LOGISTICS_PURCHASE("24", "采购退换货物流");

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
		// 消息实体类型：0-未获取到实体类型,系统消息；1-dms_notice；2-dms_order；3-dms_transfer_apply；4-dms_Invoice；5-dms_user；6-dms_order_return；7-sfa_schedule;
		SYS_MESSAGE(0),DMS_NOTICE(1), DMS_ORDER(2), DMS_TRANSFER_APPLY(3),DMS_INVOICE(4),DMS_USER(5),DMS_ORDER_RETURN(6),SFA_SCHEDULE(7);
		private final int type;
		MESSAGE_ENTITY_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

	//  树形结构类型：0-未获取到实体类型；1-dms_sys_org_main：组织；2-dms_sys_area：区域；
	enum TREE_RELATED_TYPE {
		NO_ENTITY(0), DMS_SYS_ORG_MAIN(1), DMS_AREA(2);
		private final int type;
		TREE_RELATED_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

	enum NEWS_RELATED_TYPE {
		//  资讯类型状态：0-未获取到实体类型；1-dms_news:新闻；2-dms_notice：公告；3-dms_channel_information：渠道赋能；4-；5-；6-
		NO_ENTITY(0),DMS_NEWS(1), DMS_NOTICE(2), DMS_CHANNEL_INFORMATION(3);
		private final int type;
		NEWS_RELATED_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}
	//退换货类型（1-退换；2-换货）
	enum RETURN_ORDER_TYPE {
	    RETURN(1), CHANGE(2);
        private final int type;
        RETURN_ORDER_TYPE(int type) {
	    	this.type = type;
		}
		public int getType() {
	    	return type;
		}
	}
	enum RETURN_ORDER_SOURCE {
	    APP(0), PC(1);
        private final int type;
        RETURN_ORDER_SOURCE(int type) {
	    	this.type = type;
		}
		public int getType() {
	    	return type;
		}
	}
	//退换货配送类型（1-渠道收货；2-品牌商收货）
	enum RETURN_ORDER_DELIVERY_TYPE {
		CHANNEL_RECEIVERY(1), BRAND_RECEIVERY(2);
        private final int type;
        RETURN_ORDER_DELIVERY_TYPE(int type) {
	    	this.type = type;
		}
		public int getType() {
	    	return type;
		}
	}
	//退换货审核状态   0-待审核；1-已审核；2-拒绝
	enum RETURN_AUDIT_STATUS {
		WAIT_PEND(0), AUDITED(1),REFUSE(2);
        private final int type;
        RETURN_AUDIT_STATUS(int type) {
	    	this.type = type;
		}
		public int getType() {
	    	return type;
		}
	}
	//退换货订货端状态（1-待审核；2-待退货；3-退货中（待厂商收货）；4-待发货；5-待收货；6-已完成；7-交易取消；8-待确认退款）
	enum RETURN_BUYER_STATUS {
		WAIT_PEND(1), WAIT_RETURN(2), RETURNING(3), WAIT_DELIVERY(4), WAIT_RECEIVERY(5), FINISHED(6), CANCEL(7),WAIT_PAY(8);
		private final int type;

		RETURN_BUYER_STATUS(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}

	//品牌商状态（1-待审核；2-待客户发货；3-待收货；4-待发货；5-待客户收货；6-已完成；7-取消；8-待确认退款）
	enum RETURN_SELLER_STATUS {
		WAIT_PEND(1), WAIT_CUSTOMER_DELIVERY(2), WAIT_RECEIVERY(3), WAIT_DELIVERY(4), WAIT_CUSTOMER_RECEIVERY(5), FINISHED(6), CANCEL(7),WAIT_PAY(8);
		private final int type;

		RETURN_SELLER_STATUS(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}

	//发票类型：1-普通发票；2-增值税发票
	enum INVOICE_TYPE {
		NONE(0), NORMAL(1), VAT(2);
		private final int type;

		INVOICE_TYPE(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}

	//仓库类型（1-品牌商；2-渠道；3-门店）
	enum STORAGE_TYPE {
		BRAND(1), CHANNEL(2),STORE(3);
        private final int type;
        STORAGE_TYPE(int type) {
	    	this.type = type;
		}
		public int getType() {
	    	return type;
		}
	}

	//关联单据类型（1-调货 2-订单发货 3- 退换货（渠道到品牌商）4-退换货（品牌商到渠道）
	enum RELATION_TYPE {
		TRANSFER(1), SEND(2), RETURN_CHANNEL(3),RETURN_BRAND(4);
		private final int type;
		RELATION_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

	//出入库(1出库，2入库)
	enum STORAGE_OUTIN {
		OUT(1), IN(2);
		private final int type;
		STORAGE_OUTIN(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

	//出入库类型
	enum STORAGE_OUTIN_TYPE {
		I001("研发入库"),I002("生产入库"),I003("盘盈入库"),I004("初始化入库"),O001("研发领用"),O002("维修领用"),O003("盘亏出库"),S001("销售出库"),
		S002("销售退库"),S003("销售换货出库"),S004("销售换货退库"),P001("采购入库"),P002("采购退货"),M001("调拨单出库"),M002("调拨单入库"),
		M003("子库存转移出库"),M004("子库存转移入库"),M005("安装出库"),M006("安装入库"),M007("回收出库"),M008("回收入库"),C001("手动撤回入库"),
		R001("退货入库"),R002("退货出库"),R003("换货入库"),R004("换货出库");

		private final String value;
		STORAGE_OUTIN_TYPE(String value){
			this.value = value;
		}
		public String getValue() {
			return value;
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

	//发货单类型：1-订单发货；2-退换货单发货；3-退换货
	enum ORDER_TYPE {
		ORDER(1), ORDER_RETURN(2),TRANSFER(3);
		private final int type;
		ORDER_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

	enum ORDER_OPER_TYPE {
		SELLER(1), BUYER(2);
		private final int type;
		ORDER_OPER_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}
	
	//门店运营方式类型：0-直营；1-分销
	enum STORE_OPERATE_WAY {
	    DIRECT(0), DISTRIBUTE(1);
		private final int type;

		STORE_OPERATE_WAY(int type) {
			this.type = type;
		}

		public int getType() {
		    return type;
		}
	}

	//促销活动状态：0-失效；1-有效
	enum PROMOTION_ACTIVE_TYPE {
		INVALID(0), VALID(1);
		private final int type;

		PROMOTION_ACTIVE_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

	//促销适用范围类型：0-区域；1-渠道
	enum PROMOTION_SCOPE_TYPE {
		AREA(0), CHANNEL(1);
		private final int type;

		PROMOTION_SCOPE_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

	//组织机构类型，用在dms_sys_org_main表中的type字段：1-品牌商；2-渠道；3-门店
	enum ORG_TYPE {
		BRAND(1), CHANNEL(2), STORE(3);
		private final int type;

		ORG_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

    //人员类型，用在dms_sys_emp_main表中的type字段：1-员工；2-渠道联系人；3-门店店员,12-经销商；13-店长；15-客户;31-售后工程师
	enum EMP_TYPE {
		EMPLOYEE(1), CHANNEL_CONTACT(2), SELLER(3),DEALER(12),SHOPOWNER(13),CUSTOMER(15),AFTER_SALES_ENGINEER(31);
        private final int type;

        EMP_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }

	//新增组织、岗位、资源、区域等数据时的上下级选择：1-新增上一级；2-新增下一级
	enum DIRECTION_TYPE {
	    UP(1), DOWN(2);
	    private final int type;

	    DIRECTION_TYPE(int type) {
	    	this.type = type;
		}
		public int getType() {
	    	return type;
		}
	}

	//用户来源：1-内部；2-外部
	enum USER_SOURCE_TYPE {
		INTERNAL(1), EXTERNAL(2);
		private final int type;

		USER_SOURCE_TYPE(int type) {
			this.type = type;
		}

		public int getType() {
		    return type;
		}
	}
	//1-资讯新闻类；2-产品销售区域类；3-仓库辐射区域类；4-促销区域类；
	enum RANGE_RELATION_TYPE {
		INFORMATION(1), PRODUCT(2), STORAGE(3), PROMOTION(4);
		private final int type;

		RANGE_RELATION_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
		    return type;
		}
	}

	//收/退款状态；1：未核销，2：已核销，3：部分核销，4：已取消 5:已冲销
	enum RECUND_COLLECTION_STATUS {
		NOT_WRITTEN_OFF(1), WRITTEN_OFF(2), PARTIAL_WRITTEN_OFF(3), CANCEL(4),PASSIVE_CHARGE_AGAINST(5),CHARGE_AGAINST(6);
		private final int type;

		RECUND_COLLECTION_STATUS(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}
	//收/退款审批状态；1 已审批  2 待审批
	enum RECUND_COLLECTION_APPROVAL_STATUS {
		APPROVED(1), PENDING_APPROVAL(2),NO_APPROVED(3);
		private final int type;

		RECUND_COLLECTION_APPROVAL_STATUS(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

	//收/退款类型；1：定金、押金，2：订单款
	enum RECUND_COLLECTION_TYPE {
		DEPOSIT(1), ORDER(2);
		private final int type;

		RECUND_COLLECTION_TYPE(int type) {
			this.type = type;
		}
		public int getType() {
			return type;
		}
	}

	//系统默认的角色类型
	enum DEFAULT_ROLE_TYPE {
		SYSADMIN("SYSADMIN", "系统管理员"), ADMIN("CHANADMIN", "DMS渠道管理员"), STORE("STORE", "DMS渠道店员")
		, CUSTSTORE("DMSCUS", "DMS渠道客户"), CRMSALESMAN("CRMSALESMAN", "CRM销售员"), SUPPLIER("SUPPLIER", "DMS供应商");

		private final String code;
		private final String name;

		DEFAULT_ROLE_TYPE(String code, String name) {
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
			for (DEFAULT_ROLE_TYPE roleType : DEFAULT_ROLE_TYPE.values()) {
				if (code.equals(roleType.code)) {
					return roleType.getName();
				}
			}
			return "";
		}
	}

 	//dms全局变量key值常量
	//默认密码
	public final static String DEFAULT_PASSWORD = "default_password";
	//是否支持0库存下单
	public final static String ALLOW_RESERVATION = "allow_reservation";
	//是否显示库存量
	public final static String SHOW_INVENTORY = "show_inventory";
	//运费
	public static final String FREIGHT = "freight";
	//销项税
	public static final String VAT = "vat";


	//需要生成code的key值
	//门店编码
	public final static String STORE_SERIAL_KEY = "store_serial_key";
	//渠道编码
	public final static String CHANNEL_SERIAL_KEY = "channel_serial_key";
	//订单，含退换货订单
	public final static String ORDER_SERIAL_KEY = "order_serial_key";
	//销售出库单(
	public final static String SELL_OUT_SERIAL_KEY = "sell_out_serial_key";
	//销售退货
	public final static String SALES_RETURNS_SERIAL_KEY = "sales_returns_serial_key";
	//采购入库单
	public final static String PURCHASE_IN_STORAGE_SERIAL_KEY = "purchase_in_storage_serial_key";
	//其他入库单
	public final static String OTHER_IN_STORAGE_SERIAL_KEY = "other_in_storage_serial_key";
	//库存调整
	public final static String INVENTORY_ADJUSTMENT_SERIAL_KEY = "inventory_adjustment_serial_key";
	//（门店）调货单
	public final static String TRANSFER_SERIAL_KEY = "transfer_serial_key";
	//促销活动编号
	public final static String PROMOTION_SERIAL_KEY = "promotion_serial_key";


	//excel 导出文件的存放地址
	public static final String excelAddress ="/excel";
	//服务器后台保存文件地址 begin
	public static final String BASEADDRESS="/uf";


	/************V2使用的单据生成规则********************************************/
	//销售订单
	public final static String ORDER = "ORDER";
	//批次 单据规则
	public final static String LOT_NUM = "LOT_NUM";
	//退换货订单
	public final static String RETURN_ORDER = "RETURN_ORDER";
	//发货单
	public final static String DELIVERY_NUM = "DELIVERY_NUM";
	//入库单
	public final static String RECEIPT_NUM = "RECEIPT_NUM";
	//渠道编码
	public final static String DIS_NUM = "DIS_NUM";
	//门店编码
	public final static String STORE_NUM = "STORE_NUM";
	//库存调整单
	public final static String ADJUST_NUM = "ADJUST_NUM";
	//调拨单
	public final static String MOVE_ORDER = "MOVE_ORDER";
	//价格表 促销活动编号
	public final static String PRICE = "PRICE";
	//支付单号
	public final static String TRANSACTION_NO = "TRANSACTION_NO";
	//资格证书编号
	public final static String QUAL_CERTIFY = "QUAL_CERTIFY";
	//数据字典key值
	//促销类型
	public final static String PROMOTION_CATEGORY_PROMOTING = "promoting";
	//定价类型
	public final static String PROMOTION_CATEGORY_PRICING = "pricing";
	//性别
	public final static String GENDER = "gender";

	//杂项出库单
	public final static String OUTBOUND_NUM = "OUTBOUND_NUM";

	//收款单号
	public final static String COLLECTION_NUM = "COLLECTION_NUM";

	//退款单号
	public final static String REFUND_NUM = "REFUND_NUM";

	//EDI订单标号
	public final static String EDI_ORDER_NUM = "EDI_ORDER_NUM";


	//库存导入
	String EXCEL_STORAGE_IMPORT = "excel_storage_import";
	//修改价目表导入
	String EXCEL_PRODUCTPRICE_IMPORT = "excel_productPrice_import";

	String MXM_SUCCESS_CODE = "0";

	//轻应用key
	enum FUNCTION_KEY {
		CRM("CRM", "crm轻应用"), CS("cs-home", "cs轻应用"), YUKEE("yk-main", "yukee轻应用"), DMS("dms-home", "dms轻应用");

		private final String code;
		private final String name;

		FUNCTION_KEY(String code, String name) {
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
			for (FUNCTION_KEY functionKey : FUNCTION_KEY.values()) {
				if (code.equals(functionKey.code)) {
					return functionKey.getName();
				}
			}
			return "";
		}
	}
}
