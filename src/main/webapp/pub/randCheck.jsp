<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.regex.Pattern,java.util.regex.Matcher"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
</head>
<body>
<%

    String randCode = (String)request.getSession().getAttribute("rand");
    String randInput = request.getParameter("rand");
    String tmp="";
    
    if(randCode.equalsIgnoreCase(randInput)) tmp="true";
    else tmp="false";
    
    request.setAttribute("randTips",tmp);

 %>
 <div ><input type="text" id="_randTips" value="${randTips}"/></div>
</body>
</html>