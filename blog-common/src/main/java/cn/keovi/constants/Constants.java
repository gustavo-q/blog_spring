package cn.keovi.constants;

public class Constants {
	public static final class MQType {
		public static final String MQ_TYPE_MCHL_MQ = "mq_type_mchl_mq";//系统间订阅
		public static final String MQ_TYPE_MCHL_IM = "mq_type_mchl_im";//IM订阅
		public static final String MQ_TYPE_MCHL_PUSH = "mq_type_mchl_push";//push订阅
		public static final String B2B_OCSS_TO_HAOROBOT = "B2B_ocss_to_haorobot";//发布消息到好萝卜
		public static final String B2B_HAOROBOT_TO_OCSS = "B2B_haorobot_to_ocss";//订阅好萝卜消息
	}
	public static final class MQContentType {
		public static final String MQ_CONTENT_TYPE_SYS_MQ = "mq_content_type_sys_mq";
		public static final String MQ_CONTENT_TYPE_USER_MQ = "mq_content_type_user_mq";

		public static final String OCSS_TO_HAOROBOT_CUS = "ocssToHaorobotCus";//客户同步到好萝卜
		public static final String OCSS_TO_HAOROBOT_CATEGORY = "ocssToHaorobotCategory";//商品分类同步到好萝卜
		public static final String OCSS_TO_HAOROBOT_CHANNEL = "ocssToHaorobotChannel";//渠道同步到好萝卜
		public static final String OCSS_TO_HAOROBOT_ORDER_SEND = "ocssToHaorobotOrderSend";//订单发货同步到好萝卜
		public static final String OCSS_TO_HAOROBOT_ORDER_SYNC = "ocssToHaorobotOrderSync";//订单发货同步到好萝卜
		public static final String OCSS_TO_HAOROBOT_LEASE_ORDER_SEND = "ocssToHaorobotLeaseOrderSend";//订单发货同步到好萝卜
	}
	public static final class User {
		public static final String MCHL_USER = "0";
		public static final String PUSH_USER = "0";
		public static final String MXM_USER = "0";
		public static final String CRM_USER = "0";
		public static final String CS_USER ="0";
		public static final String YUKEE_USER = "0";
		public static final String SFA_USER = "0";
		private static final Object REDIS_KEY_USER_PREFIX = "corTicket:userId:";

	}
}
