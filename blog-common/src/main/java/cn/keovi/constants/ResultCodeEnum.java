package cn.keovi.constants;

import lombok.Getter;

@Getter
public enum ResultCodeEnum implements IResultCodeEnum {
    SUCCESS(0, "ok"),

    STATUS(0, "true"),


    RES_SUCCESS(1, "1"),

    RES_ERROR(0, "0"),

    ERROR(500, "未知错误"),

    SERVER_ERROR(500, "服务器异常，请稍后重试"),

    PARAMS_CHECK_ERROR(20001, "参数错误"),

    FORM_CHECK_ERROR(20002, "form校验出错!"),

    UNKNOWN_ERROR(20004, "未知错误!"),

    OPERATE_FAIL(20005, "操作失败"),

    PASSWORD_CHECK_ERROR(20006, "密码错误"),

    CODE_CHECK_ERROR(20007, "验证码错误"),

    USER_CHECK_ERROR(20007, "用户不存在"),

    FILE_CHECK_ERROR(20008, "文件不存在"),

    ADMIN_CHECK_ERROR(20009, "该帐号无法登陆客户端"),

    APILOGIN_CHECK_ERROR(20010, "错误次数过多,帐号已锁定,请联系管理员"),

    USER_CHECK_ACCPUNT_ERROR(20011, "用户已存在"),

    USER_CHECK_DISABLE_ERROR(20012, "用户被禁用"),

    USER_CHECK_USER_PHONE_ERROR(20013, "手机号码不存在"),

    USER_PHONE_MESSAGE_ERROR(20014, "短信验证码错误"),

    LIMIT_LOGIN_TIMES_ERROR(20015, "账号已锁定，请联系IT人员解锁"),

    PASSWORD_CHECK_POLICY_THIRD_ERROR(20016, "密码至少为8位以上（含8位）的数字+字母+特殊字符组合"),

    PASSWORD_CHECK_POLICY_ONE_ERROR(20017, "密码至少为6位数字或字母，例如123456"),

    PASSWORD_CHECK_POLICY_TWO_ERROR(20018, "密码至少为8位以上（含8位）的数字+字母组合，例如12345678a"),

    APPLICATION_CHECK_ERROR(30000, "应用程序不存在"),

    ROLE_CHECK_RESOURCE_ERROR(40000, "该角色没有权限访问"),

    USER_CHECK_ROLE_ERROR(40001, "初始化角色不存在！请联系管理员"),

    QRCODE_IS_TEMPORARAY_NO(50001, "已绑定固定二维码，无需新建"),

    USER_ORG_CHECK_DISABLE_ERROR(50002, "网点未审核，请联系网点客服审核后重试!"),
    ;

    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMsg() {
        this.message = message;
        return message;
    }
}
