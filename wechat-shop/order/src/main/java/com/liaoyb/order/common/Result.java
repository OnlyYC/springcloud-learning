package com.liaoyb.order.common;

import lombok.Data;

/**
 * @author liaoyb
 */
@Data
public class Result<T> {
    private static final int SUCCESS = 0;
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }


    public static Result<?> fail(Integer code, String msg) {
        Result<?> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public boolean isSuccess() {
        return SUCCESS == code;
    }
}
