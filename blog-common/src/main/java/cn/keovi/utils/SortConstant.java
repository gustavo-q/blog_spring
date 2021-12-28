package cn.keovi.utils;


/**
 * 有效类型
 *
 * @author huangad@coracle.com
 * @create 2017-04-05
 */
public enum SortConstant {
    ORDER_FIELD("ORDER_FIELD", "排序字段"),
    ORDER_DIRECTION("ORDER_DIRECTION", "排序方向"),
    ASC("ASC", "升序"),
    DESC("DESC", "降序");

    private String code;
    private String description;

    SortConstant(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
