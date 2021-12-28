package cn.keovi.blog.service.consumer.controller;

/**
 * @ClassName BaseController
 * @Description
 * @Author gustavo
 * @Date 2021/12/24/22:18
 */

import cn.keovi.utils.JsonResult;
import cn.keovi.utils.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 *
 * @author jianglfa
 * @version 1.0
 * @created Aug 11, 2011 6:59:34 PM
 */
@SuppressWarnings("rawtypes")
public class BaseController {


    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected HttpServletResponse response;
    protected HttpServletRequest request;
    protected HttpSession session;


    @ModelAttribute
    public void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    protected Object toResult() {
        return toResult(null, "操作成功", true);
    }

    protected Object toResult(boolean encode) {
        return toResult(null, "操作成功", encode);
    }

    protected Object toResult(Object data) {
//		return toResult(data, "操作成功");  //换成base64编码返回数据
        return toResult(data,"操作成功",true);
    }

    /**
     *
     * @param data
     * @param message
     * @return
     */
    protected Object toResult(Object data, String message) {
        return toResult(data, message, true);
    }

    /**
     *
     * @param data
     * @param message
     * @return
     */
    protected Object toResult(Object data, String message, boolean encode) {
        Map<String, Object> result = new HashMap<String, Object>();
        String[] zero = new String[0];
        JsonResult jsonResult = new JsonResult();
        jsonResult.setEncode(encode);
        jsonResult.setStatus(true);
        //如果是分页需要设置总条数total
        if(data instanceof PageHelper.Page) {
            PageHelper.Page page = (PageHelper.Page) data;
            jsonResult.setTotal(page.getTotal());
            if(page.getResult()==null){//统一返回[]，避免返回null
                jsonResult.setData(zero);
            }else {
                jsonResult.setData(page.getResult());
            }
        } else if(data instanceof Collection) {
            Collection list = (Collection)data;
            jsonResult.setTotal(list.size());
            jsonResult.setData(list);
        } else {
            jsonResult.setTotal(1);
            jsonResult.setData(data);
        }
        jsonResult.setMessage(message);
        return jsonResult;
    }



}