package cn.keovi.handler;

import cn.keovi.constants.Result;
import cn.keovi.exception.BusinessException;
import cn.keovi.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLSyntaxErrorException;

/**
 * @ClassName AllExceptionHandler
 * @Description 全局异常处理器
 * @Author gustavo
 * @Date 2021/12/26/18:45
 */
@Slf4j
@RestControllerAdvice
public class AllExceptionHandler {


    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result handleRRException(BusinessException e){
        return Result.setResult(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return Result.error(500,"数据库中已存在该记录");
    }



    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage(), e);
        return Result.error();
    }


    @ExceptionHandler(ServiceException.class)
    public Result ServiceException(Exception e){
        log.error(e.getMessage(), e);
        return Result.error();
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public Result SQLSyntaxErrorException(Exception e){
        log.error("sql错误", e);
        return Result.error("sql错误");
    }

    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("空指针异常", e);
        return Result.error("空指针异常");
    }


}
