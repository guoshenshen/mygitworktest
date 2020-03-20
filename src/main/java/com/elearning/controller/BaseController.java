package com.elearning.controller;


import com.elearning.vo.pub.PageForm;

import javax.servlet.http.HttpServletRequest;



public class BaseController {

    protected void setSession(HttpServletRequest request,String key,Object obj){
        request.getSession().setAttribute(key, obj);
    }

    protected Object getSession(HttpServletRequest request,String key){
        return request.getSession().getAttribute(key);
    }

    protected void removeSession(HttpServletRequest request,String key){
        request.getSession().removeAttribute(key);
    }

    protected boolean isExistSession(HttpServletRequest request,String key){
        if(request.getSession().getAttribute(key)!=null){
            return true;
        }else{
            return false;
        }
    }

    protected void setPageInfo(HttpServletRequest request, PageForm pageForm) {
        if(request.getParameter("research") != null) {
            if(request.getParameter("research").equalsIgnoreCase("false")) {
                if(request.getParameter("pageNo") == null && request.getParameter("pageSize") == null) {
                    if(this.getSession(request, "pageNo") != null) pageForm.setPageNo((Integer)this.getSession(request, "pageNo"));
                    if(this.getSession(request, "pageSize") != null) pageForm.setPageSize((Integer)this.getSession(request, "pageSize"));
                } else {
                    this.setSession(request, "pageNo", Integer.parseInt(request.getParameter("pageNo")));
                    this.setSession(request, "pageSize", Integer.parseInt(request.getParameter("pageSize")));
                }
            } else if(request.getParameter("research").equalsIgnoreCase("true")) {
                this.setSession(request, "pageNo", pageForm.getPageNo());
                this.setSession(request, "pageSize", pageForm.getPageSize());
            }
        } else if( request.getParameter("researchbydept") != null) {
            if(request.getParameter("researchbydept").equalsIgnoreCase("false")) {
                if(request.getParameter("pageNo") == null && request.getParameter("pageSize") == null) {
                    pageForm.setPageNo((Integer)this.getSession(request, "pageNo"));
                    pageForm.setPageSize((Integer)this.getSession(request, "pageSize"));
                } else {
                    this.setSession(request, "pageNo", Integer.parseInt(request.getParameter("pageNo")));
                    this.setSession(request, "pageSize", Integer.parseInt(request.getParameter("pageSize")));
                }
            } else if(request.getParameter("researchbydept").equalsIgnoreCase("true")) {
                this.setSession(request, "pageNo", pageForm.getPageNo());
                this.setSession(request, "pageSize", pageForm.getPageSize());
            }
        } else {
            this.setSession(request, "pageNo", pageForm.getPageNo());
            this.setSession(request, "pageSize", pageForm.getPageSize());
        }
    }

}
