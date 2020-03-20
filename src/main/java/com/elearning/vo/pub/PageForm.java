package com.elearning.vo.pub;

import com.elearning.common.Constants;

/**
 * @author guoshen
 * @create 2019/8/9
 */
public class PageForm {

    protected int pageSize = Constants.pgSize;
    protected int pageNo = Constants.pageNo;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
