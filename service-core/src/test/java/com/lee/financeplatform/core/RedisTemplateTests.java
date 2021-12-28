package com.lee.financeplatform.core;

import com.lee.financeplatform.core.mapper.DictMapper;
import com.lee.financeplatform.core.pojo.entity.Dict;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author helloyore
 * @createTime 2021年12月28日 19:07:00
 * @Description
 */
/**
 * --@RunWith注解作用：
 * --@RunWith就是一个运行器
 * --@RunWith(JUnit4.class)就是指用JUnit4来运行
 * --@RunWith(SpringJUnit4ClassRunner.class)，让测试运行于Spring测试环 境，以便在测试开始的时候自动创建Spring的应用上下文
 * --@RunWith(Suite.class)的话就是一套测试集合
 * */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTests {

    //注入
    @Resource
    private RedisTemplate redisTemplate;

    //注入
    @Resource
    private DictMapper dictMapper;

    @Test
    public void saveDict(){
        //首先用dictMapper选出了一条记录
        Dict dict = dictMapper.selectById(1);
        //用redisTemplate将这条记录以序列化方式存入了我们的redis服务器中，并且有效时间是5分钟，存的方式以字符串去存。
        // opsForValue存字符串 5分钟失效
        redisTemplate.opsForValue().set("dict", dict, 5, TimeUnit.MINUTES);
    }

    @Test
    public void getDict(){
        Dict dict = (Dict)redisTemplate.opsForValue().get("dict");
        System.out.println(dict);
    }
}
