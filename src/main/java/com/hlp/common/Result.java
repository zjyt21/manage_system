package com.hlp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口统一的返回包装类
 *
 * @author Mr.Han
 * @create 2022-07-07 20:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String code;
    private String msg;
    private Object data;

    public static Result normal(boolean flag){
        if(flag){
            return success();
        }else {
            return error();
        }
    }

    public static Result success(){
        return new Result(Constants.CODE_200, "", null);
    }


    public static Result success(Object data){
        return new Result(Constants.CODE_200, "", data);
    }

    public static Result error(String code, String msg){
        return new Result(code, msg, null);
    }

    public static Result error(){
        return new Result(Constants.CODE_500, "系统错误", null);
    }

}
