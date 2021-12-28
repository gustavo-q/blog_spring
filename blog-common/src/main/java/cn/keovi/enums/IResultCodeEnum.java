package cn.keovi.enums;

public interface IResultCodeEnum {

    /**
     * 返回枚举项的 key
     */
    Integer getCode();

    /**
     * 返回枚举项的 value
     */
    String getMessage();

    String getMsg();

}
