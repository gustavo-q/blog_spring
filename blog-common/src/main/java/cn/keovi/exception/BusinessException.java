package cn.keovi.exception;

import cn.keovi.constants.IResultCodeEnum;
import lombok.Data;

/**
 * 自定义全局异常
 */
@Data
public class BusinessException extends RuntimeException {
    private Integer code=0;
    private String msg;

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(IResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
    }

}
