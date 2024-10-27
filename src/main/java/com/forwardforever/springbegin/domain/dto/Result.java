package com.forwardforever.springbegin.domain.dto;

import com.forwardforever.springbegin.utils.HttpCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data // 自动生成 Getter 和 Setter 方法
@AllArgsConstructor // 生成全参构造方法
public class Result<T> {

    // 响应状态码
    private int code;

    // 响应消息
    private String message;

    // 响应数据
    private T data;

    // 成功时的静态方法

    public static <T> Result<T> success(T data) {
        return new Result<>(HttpCodeEnum.OK.getCode(), HttpCodeEnum.OK.getMessage(), data);
    }

    // 失败时的静态方法
    public static <T> Result<T> failure(String message) {
        return new Result<>(HttpCodeEnum.INTERNAL_SERVER_ERROR.getCode(), message, null);
    }
}

