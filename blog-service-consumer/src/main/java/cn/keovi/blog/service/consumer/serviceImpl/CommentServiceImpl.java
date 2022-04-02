package cn.keovi.blog.service.consumer.serviceImpl;

import cn.hutool.core.collection.CollectionUtil;
import cn.keovi.crm.dto.CommentDto;
import cn.keovi.crm.vo.CommentVo;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentVo> pageList(CommentDto commentDto) {

        List<CommentVo> comments = commentMapper.pageList(commentDto);
        if (CollectionUtil.isNotEmpty(comments)) {
            comments.forEach(comment -> {
                List<Map> byChild = commentMapper.listByChild(comment.getId(), commentDto.getTopicId());
                    comment.setChild(byChild);
            });
        }
        return comments;
    }

    @Override
    public long pageListCount(CommentDto commentDto) {
        List<CommentVo> comments = commentMapper.pageAllList(commentDto);
        if (CollectionUtil.isNotEmpty(comments)) {
            comments.forEach(comment -> {
                List<Map> byChild = commentMapper.listByChild(comment.getId(), commentDto.getTopicId());
                if (CollectionUtil.isNotEmpty(byChild)) {
                    if (comment.getIsDelete() == 1) {
                        comment.setContent("该评论已删除");
                    }
                    comment.setChild(byChild);
                }

            });
            for (int i = 0; i < comments.size(); i++) {
                //没有关联行程就删除
                if (CollectionUtil.isEmpty(comments.get(i).getChild()) && comments.get(i).getIsDelete() == 1) {
                    comments.remove(i);
                    i--;
                }
            }
            return comments.size();
        }else {
            return 0;
        }

    }
}
