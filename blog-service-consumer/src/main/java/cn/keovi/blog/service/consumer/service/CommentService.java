package cn.keovi.blog.service.consumer.service;

import cn.keovi.crm.dto.CommentDto;
import cn.keovi.crm.po.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName CommentService
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface CommentService extends IService<Comment>{


        List<Comment> pageList(CommentDto commentDto);
    }
