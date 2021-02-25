package com.tanx.music.pojo;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private int code;
    private T data;
    private Throwable message;

    public Result(int code){
        this(code,null,null);
    }
    
    public Result(int code,T data){
        this(code,data,null);
    }

    public Result(int code,T data,Throwable message){
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Throwable getMessage() {
        return message;
    }

    public void setMessage(Throwable message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", data='" + data + '\'' +
                ", message=" + message +
                '}';
    }
}
