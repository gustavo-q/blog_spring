package cn.keovi.blog.service.consumer.serviceImpl;

import cn.keovi.blog.service.consumer.session.IPUtil;
import cn.keovi.crm.po.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.keovi.blog.service.consumer.mapper.SiteMapper;
import cn.keovi.blog.service.consumer.service.SiteService;

/**
 * @ClassName SiteServiceImpl
 * @Description ${DESCRIPTION}
 * @Author gustavo
 * @Date 2022/04/03/14:42
 */
@Service
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements SiteService {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public void visitor(HttpServletRequest request, HttpServletResponse response) {
        String ip = IPUtil.getIpAddr(request);
        System.out.println(ip);
        String addr =IPUtil.getRealAddress(ip);
        System.out.println(addr);
        if (redisTemplate.opsForValue().get(ip) == null) {
            //在规定周期内第一次访问，存入redis
            redisTemplate.opsForValue().set(ip, "1",
                    1,
                    TimeUnit.MINUTES);
            Site site = new Site();
            site.setCreateTime(new Date());
            site.setIp(ip);
            site.setAddr(addr);
            save(site);
        } else {
            redisTemplate.expire(ip, 1, TimeUnit.MINUTES);
        }
    }


}
