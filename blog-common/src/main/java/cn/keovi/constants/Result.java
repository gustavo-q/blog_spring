package cn.keovi.constants;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    private Integer code;
//
//    private Integer res;//兼容手机端
//
//    private String msg;

    private String message;

    private String ticket;

    private Boolean status;


    private T data;

//    private int pageSize = 10;


//    private int totalSize = 10;

//    private Boolean encode;

//    private int pageNum = 1;

    private int totalCount;

    public T getData(){
        return this.data;
    }

    private Result() {
    }

    /**
     * 默认正确返回
     *
     * @return
     */
    public static Result ok() {
        Result r = new Result();
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
//        r.setMsg(ResultCodeEnum.SUCCESS.getMessage());
//        r.setRes(ResultCodeEnum.RES_SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        r.setStatus(true);
        return r;
    }

    public static Result ok(Integer code,String message) {
        Result r = new Result();
        r.setCode(code);
        r.setMessage(message);
        r.setStatus(true);
        return r;
    }

    public static Result ok(String message) {
        Result r = new Result();
        r.setCode(200);
        r.setMessage(message);
        r.setStatus(true);
        return r;
    }

    /**
     * 默认错误返回
     *
     * @return
     */
    public static Result error() {
        Result r = new Result();
        r.setCode(ResultCodeEnum.ERROR.getCode());
//        r.setMsg(ResultCodeEnum.ERROR.getMessage());
        r.setMessage(ResultCodeEnum.ERROR.getMessage());
//        r.setRes(ResultCodeEnum.RES_ERROR.getCode());
        r.setStatus(false);
        return r;
    }

    public static Result error(Integer errcode, String errormsg) {
        Result r = new Result();
        r.setCode(errcode);
//        r.setMsg(errormsg);
        r.setMessage(errormsg);
//        r.setRes(errcode);
        r.setStatus(false);
        return r;
    }


    public static Result error(String errormsg) {
        Result r = new Result();
        r.setCode(500);
//        r.setMsg(errormsg);
        r.setMessage(errormsg);
//        r.setRes(errcode);
        r.setStatus(false);
        return r;
    }


    public static Result errorApi(Integer errcode,Integer res,  String errormsg) {
        Result r = new Result();
        r.setCode(errcode);
//        r.setMsg(errormsg);
        r.setMessage(errormsg);
//        r.setRes(res);
        r.setStatus(false);
        return r;
    }
    public Result errcode(Integer errcode) {
        this.setCode(errcode);
        this.setStatus(false);
        return this;
    }

//    public Result errmsg(String errmsg) {
////        this.setMsg(errmsg);
//        this.setStatus(false);
//        return this;
//    }

    public static Result errEnum(ResultCodeEnum codeEnum) {
        Result r = new Result();
        r.setCode(codeEnum.getCode());
        r.setMessage(codeEnum.getMessage());
//        r.setMsg(codeEnum.getMessage());
        return r;
    }

    public Result data(T data) {
        this.setData(data);
        return this;
    }
    public Result data200(T data,Integer totalCount) {
        this.setData(data);
//        this.setPageNum(pageNum);
        this.setTotalCount(totalCount);
//        this.setTotalSize(totalCount);
        this.setCode(200);
        this.setStatus(true);
//        this.setEncode(true);
        return this;
    }



    public Result data(T data,Integer code,Integer totalCount) {
        this.setData(data);
//        this.setPageNum(pageNum);
        this.setTotalCount(totalCount);
//        this.setTotalSize(pageNum);
        this.setCode(code);
        this.setStatus(true);
//        this.setEncode(true);
        return this;
    }
    /**
     * 用于异常返回
     * @param
     * @return
     */
    public static Result setResult(Integer code,String message) {
        Result r = new Result();
        r.setCode(code);
//        r.setMsg(resultCodeEnum.getMessage());
        r.setMessage(message);
        return r;
    }
}
