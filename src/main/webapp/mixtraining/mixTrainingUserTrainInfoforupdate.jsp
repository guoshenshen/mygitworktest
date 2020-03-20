<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page
        import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String userRolesInString = "";
    try {
        EosOperator user = (EosOperator) session.getAttribute(Constants.USERINFO_KEY);
    } catch (Exception ex) {
    }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="培训;在线培训;网络培训" name="keywords"/>
    <title>培训项目基本信息管理</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/common/tools.js"></script>
    <script type="text/javascript" src="/js/common/home.js"></script>
    <script type="text/javascript" src="/js/dojojs/dojo.js"></script>
    <script type="text/javascript" src="/js/json.js"></script>
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript">
        function SubmitTemp() {
            if (document.getElementById("classHour").value == "") {
                alert("培训时间不能为空!");
                document.getElementById("classHour").focus();
                return;
            }
            document.getElementById("form1").submit();
        }
    </script>
</head>

<body>
<div class="homezonecontent">
    <form name="form1" id="form1" method="post" action="../mtMixTrainUserTrainInfo/update.do">
        <table align=center border="0" cellspacing="0" class="table1">
            <tr>
                <th align="right" width="30%">人员姓名:</th>
                <td align="left">${userTrainInfo.userName}
                    <input name="id" type="hidden" value="${userTrainInfo.id}"/>
                </td>
            </tr>
            <tr>
                <th align="right">培训时间:</th>
                <td align="left">
                    <input name="classHour" id="classHour" type="text" value="${userTrainInfo.classHour}" size="4" maxLength="4"/>小时
                </td>
            </tr>
            <tr>
                <th align="right">总结催促:</th>
                <td align="left">
                    <select name="hurrySummary">
                        <option name="1" value="1" <c:if test="${userTrainInfo.hurrySummary == 1 }">selected</c:if>>是</option>
                        <option name="2" value="0" <c:if test="${userTrainInfo.hurrySummary == 0 }">selected</c:if>>否</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th align="right"> 备注:</th>
                <td class="td-content-l" colspan='2'>
                    <textarea name="remark" id='remark' cols="25" rows="3">${userTrainInfo.remark}</textarea>
                </td>
            </tr>
            <tr>
                <td colspan='2' align='center'>
                    <br/>
                    <a href="javascript:SubmitTemp();" class="btn-blue-l local" style="margin-left: 70px;"><span class="btn-blue-r">确定</span></a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
