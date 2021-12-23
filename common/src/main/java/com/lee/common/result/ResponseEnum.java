package com.lee.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author helloyore
 * @createTime 2021年12月23日 23:08:00
 * @Description 响应结果的枚举类
 */
//创建get方法 构造方法 toString方法 都是lombok生成的
@Getter
@AllArgsConstructor
@ToString
public enum ResponseEnum {
    SUCCESS(0, "成功"),
    ERROR(1,"服务器内部错误");

    //响应状态码
    private Integer code;
    //响应信息
    private String message;
}
