package com.elearning.common;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/22 16:03
 */
public enum  OpenScopeType {

    NotOpen((short)2201,"不公开"),

    BranchOpen((short)2202,"机构内公开"),

    UnitOpen((short)2203,"本单位公开"),

    CASOpen((short)2204,"全院公开"),

    CompleteOpen((short)2205,"完全公开");

    private OpenScopeType(Short code,String name){
        this.code=code;
        this.name=name;
    }

    private final Short code;

    private final String name;

    public Short getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static OpenScopeType findByCode(short code){
        switch(code){
            case (short)2201:return NotOpen;
            case (short)2202:return BranchOpen;
            case (short)2203:return UnitOpen;
            case (short)2204:return CASOpen;
            case (short)2205:return CompleteOpen;
            default:return null;
        }
    }
}
