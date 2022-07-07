package com.hlp.exception;

import lombok.Getter;

/**
 * 自定义异常
 *
 * @author Mr.Han
 * @create 2022-07-07 21:06
 */
@Getter
public class ServiceException extends RuntimeException{
    private String code;

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
