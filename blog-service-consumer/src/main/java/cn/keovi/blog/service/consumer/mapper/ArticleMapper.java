package cn.keovi.blog.service.consumer.mapper;

import cn.keovi.crm.dto.BaseDto;
import cn.keovi.crm.po.Article;
import cn.keovi.crm.vo.ArticleVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ArticleMapper
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2021/12/23/21:07
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleVo> pageList(BaseDto baseDto);

    long pageListCount(BaseDto baseDto);

    List<Map<String,Object>> statisticalBlogByMonth();

    List<Map> getLineData();

    List<Map> getMyLineData(Long id);

    List<Article> getMyLoveList(@Param("page") Integer page, @Param("showCount")Integer showCount, @Param("collect")List<Long> collect);


}