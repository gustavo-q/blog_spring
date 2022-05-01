package cn.keovi.blog.service.consumer.mapper;

import cn.keovi.crm.dto.CommentDto;
import cn.keovi.crm.dto.PageDto;
import cn.keovi.crm.po.Comment;
import cn.keovi.crm.vo.CommentVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CommentMapper
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface CommentMapper extends BaseMapper<Comment> {
    List<CommentVo> pageList(CommentDto commentDto);

    List<Map> listByChild(@Param("id")Long id, @Param("topicId")String topicId);

    List<CommentVo> pageAllList(CommentDto commentDto);

    List<Map> getLineData();

}