package com.anjuxing.platform.security.handler;

/**
 * @author xiongt
 * @Description
 */
public class SecurityResponse<T> {

    private T content;

    private int code = -1;


    public SecurityResponse(T content){
        this.content = content;
    }

    public SecurityResponse(T content , int code){
        this.content = content;
        this.code = code;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
