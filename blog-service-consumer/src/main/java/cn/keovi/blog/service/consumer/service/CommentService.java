package cn.keovi.blog.service.consumer.service;

import cn.keovi.crm.dto.CommentDto;
import cn.keovi.crm.po.Comment;
import cn.keovi.crm.vo.CommentVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CommentService
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface CommentService extends IService<Comment>{


        List<CommentVo> pageList(CommentDto commentDto);

    long pageListCount(CommentDto commentDto);
}
