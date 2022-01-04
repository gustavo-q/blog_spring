package cn.keovi.blog.service.consumer.serviceImpl;

import cn.keovi.crm.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.blog.service.consumer.mapper.CommentMapper;
import cn.keovi.crm.po.Comment;
import cn.keovi.blog.service.consumer.service.CommentService;
/**
 * @ClassName CommentServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> pageList(CommentDto commentDto) {
        return commentMapper.pageList(commentDto);
    }
}
