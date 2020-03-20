package com.elearning.common;

public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),//成功
    ERROR(1,"ERROR"),//失败
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),//参数错误
    NEED_LOGIN(3,"NEED_LOGIN"),
    NOTFOUNDBYID(4,"NOT_FOUND_BY_ID");//未能根据id检索到信息

    private final int code;

    private final String desc;

    ResponseCode(int code , String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }
}
