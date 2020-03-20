<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="cn.kepu.elearning.common.Uploader" %>



<%
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");

    Uploader up = new Uploader(request);
    up.setSavePath("ueditor"); //保存路径
    String[] fileType = {".rar" , ".doc" , ".docx" , ".ppt", ".pptx", ".zip" , ".pdf" , ".txt" , ".swf", ".wmv",".xls",".xlsx",".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};  //允许的文件类型
    up.setAllowFiles(fileType);
    up.setMaxSize(100000);        //允许的文件最大尺寸，单位KB
    up.upload();
    response.getWriter().print("{'url':'"+up.getUrl()+"','fileType':'"+up.getType()+"','state':'"+up.getState()+"','original':'"+up.getOriginalName()+"'}");

%>
