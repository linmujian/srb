package com.hy.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    private Integer code;
    private String message;
    private Map<String, Object> data=new HashMap<>();

    /**
     * 构造器私有
     */
    private Result(){}

    /**
     * 返回成功
     */
    public static Result ok(){
        Result r = new Result();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setMessage(ResponseEnum.SUCCESS.getMessage());
        return r;
    }

    /**
     * 返回失败
     */
    public static Result error(){
        Result r = new Result();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMessage(ResponseEnum.ERROR.getMessage());
        return r;
    }

    /**
     * 设置特定结果
     */
    public static Result getResult(ResponseEnum responseEnum){
        Result r = new Result();
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }


    public Result data(Map data){
        this.data=data;
        return this;
    }

    public Result data(String s,Object o){
        this.data.put(s, o);
        return this;
    }

    /**
     * 给message设置更多详细的成功后的信息
     */
    public Result setMessage(String message){
        this.message=message;
        return this;
    }

    public Result setCode(Integer code){
        this.code=code;
        return this;
    }

}
