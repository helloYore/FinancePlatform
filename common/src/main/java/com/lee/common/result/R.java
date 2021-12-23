package com.lee.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author helloyore
 * @createTime 2021年12月23日 23:38:00
 * @Description 统一结果类
 */
@Data
public class R {
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    //防止被外部调用，使用一些自定义的静态方法进行调用
    /**
     * 构造函数私有化
     * */
    private R(){}

    /**
     * 成功时候返回正确结果
     * @return
     * */
    public static R ok(){
        R r = new R();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setMessage(ResponseEnum.SUCCESS.getMessage());
        return r;
    }
    /**
     * 失败时候返回错误结果
     * @return
     * */
    public static R error(){
        R r = new R();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMessage(ResponseEnum.ERROR.getMessage());
        return r;
    }
    /**
     * 动态的传输枚举类型参数，设置特定的结果
     * @param responseEnum
     * @return
     * */
    public static R setResult(ResponseEnum responseEnum){
        R r = new R();
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }
    /**
     * 设置特定的响应message
     * @return
     * */
    public R setMessage(String message){
        this.setMessage(message);
        return this;
    }
    /**
     * 设置特定的响应code
     * @return
     * */
    public R setCode(Integer code){
        this.setCode(code);
        return this;
    }
    /**
     * 定义data方法，用作返回时候调用
     * */
    public R data(String key, Object value){
        this.data.put(key,value);
        return this;
    }
    //方法重载
    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
