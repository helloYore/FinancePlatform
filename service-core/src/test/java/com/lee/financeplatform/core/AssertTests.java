package com.lee.financeplatform.core;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * @author helloyore
 * @createTime 2021年12月24日 16:48:00
 * @Description
 */
public class AssertTests {
    @Test
    public void test1(){
        Object o = null;
        if(o == null){
            throw new IllegalArgumentException("参数错误");
        }
    }

    @Test
    public void test2(){
        //使用断言来替代if
        Object o = null;
        Assert.notNull(o, "参数错误");
    }
}
