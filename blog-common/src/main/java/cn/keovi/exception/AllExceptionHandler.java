package cn.keovi.exception;

import cn.keovi.constants.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName AllExceptionHandler
 * @Description 全局异常处理器
 * @Author gustavo
 * @Date 2021/12/26/18:45
 */

@RestControllerAdvice
public class AllExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result handleRRException(BusinessException e){
        return Result.setResult(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        logger.error(e.getMessage(), e);
        return Result.error(500,"数据库中已存在该记录");
    }



    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        logger.error(e.getMessage(), e);
        return Result.error();
    }


    @ExceptionHandler(ServiceException.class)
    public Result ServiceException(Exception e){
        logger.error(e.getMessage(), e);
        return Result.error();
    }

}
