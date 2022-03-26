package cn.keovi.blog.service.consumer.controller;

import cn.keovi.constants.Result;
import cn.keovi.crm.po.Article;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SiteController
 * @Description
 * @Author gustavo
 * @Date 2022/03/26/23:59
 */

@Slf4j
@RestController
@RequestMapping("/site")
public class SiteController {


    //博客显示
    @GetMapping
    public Result site() {
        try {
            return Result.ok();
        }catch (Exception e){
            log.error("site失败!",e);
            return Result.error(500,e.getMessage());
        }
    }
}
