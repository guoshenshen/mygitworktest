package com.elearning.pojo.pub;

public class SignEwmCode {
    private Long code;

    private Integer state;

    public SignEwmCode(Long code, Integer state) {
        this.code = code;
        this.state = state;
    }

    public SignEwmCode() {
        super();
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}