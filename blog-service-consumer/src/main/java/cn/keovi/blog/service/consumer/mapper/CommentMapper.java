package cn.keovi.blog.service.consumer.mapper;

import cn.keovi.crm.dto.CommentDto;
import cn.keovi.crm.dto.PageDto;
import cn.keovi.crm.po.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @ClassName CommentMapper
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface CommentMapper extends BaseMapper<Comment> {
    List<Comment> pageList(CommentDto commentDto);
}