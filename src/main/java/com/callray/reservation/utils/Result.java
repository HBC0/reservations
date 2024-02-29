package com.callray.reservation.utils;

import lombok.Getter;

@Getter
public class Result<T> {

    private String code;
    private String mag;
    private T data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result<?> success(String mag) {
        Result<?> result = new Result<>();
        result.setCode("0");
        result.setMag(mag);
        result.setData(null);
        return result;
    }

    public static<T> Result<?> success(T data) {
        Result<T> result = new Result<>();
        result.setCode("0");
        result.setMag("成功");
        result.setData(data);

        return result;
    }

    public static<T> Result<T> error(String code, String mag) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMag(mag);

        return result;
    }
    public static<T> Result<T> error(String mag) {
        Result<T> result = new Result<>();
        result.setCode("-1");
        result.setMag(mag);
        result.setData(null);
        return result;
    }

}

