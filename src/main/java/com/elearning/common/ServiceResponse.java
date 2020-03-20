package com.elearning.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//json 是null的对象 key消失
public class ServiceResponse<T> implements Serializable {

    /**
     * 0:成功；1：失败；2:参数错误；3：未登录 （参见ResponseCode）
     */
    private int statusCode;

    private Boolean status;

    private String msg;

    private T data;

    private ServiceResponse(Boolean status){
        this.status = status;
    }
    private ServiceResponse(int statusCode){
        if(statusCode == 0){
            this.status = true;
        }else {
            this.status = false;
        }
        this.statusCode = statusCode;
    }

    private ServiceResponse(int statusCode,T data ){
        this.statusCode = statusCode;
        if(statusCode == 0){
            this.status = true;
        }else{
            this.status = false;
        }
        this.data = data;
    }
    private ServiceResponse(int statusCode,String msg,T data ){
        this.statusCode = statusCode;
        if(statusCode == 0){
            this.status = true;
        }else{
            this.status = false;
        }
        this.msg = msg;
        this.data = data;
    }
    private ServiceResponse(int statusCode,String msg ){
        this.statusCode = statusCode;
        if(statusCode == 0){
            this.status = true;
        }else{
            this.status = false;
        }
        this.msg = msg;
    }
    @JsonIgnore
    //在json中不显示
    public boolean isSuccess(){
        return this.statusCode == ResponseCode.SUCCESS.getCode();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public static <T> ServiceResponse<T> createBySuccess(){
        return  new ServiceResponse<T>(ResponseCode.SUCCESS.getCode());
    }
    public static <T> ServiceResponse<T> createBySuccessMessage(String msg){
        return  new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    public static <T> ServiceResponse<T> createBySuccessMessage(int statusCode ,String msg){
        return  new ServiceResponse<T>(statusCode,msg);
    }
    public static <T> ServiceResponse<T> createBySuccess(T data){
        return  new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    public static <T> ServiceResponse<T> createBySuccess(String msg , T data){
        return  new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServiceResponse<T> createByError(){
        return  new ServiceResponse<T>(ResponseCode.ERROR.getCode() , ResponseCode.ERROR.getDesc());
    }
    public static <T> ServiceResponse<T> createByError(T data){
        return  new ServiceResponse<T>(ResponseCode.ERROR.getCode() ,data);
    }

    public static <T> ServiceResponse<T> createByErrorMessage(String errorMsg){
        return  new ServiceResponse<T>(ResponseCode.ERROR.getCode() , errorMsg);
    }

    public static <T> ServiceResponse<T> createByErrorCodeMessage(int errorCode , String errorMessage){
        return new ServiceResponse<T>(errorCode,errorMessage);
    }



}
