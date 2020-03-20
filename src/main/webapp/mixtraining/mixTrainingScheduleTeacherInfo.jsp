<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page
        import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--[if IE 6 ]> <html id="ne_wrap" class="ne_ua_ie6 ne_ua_ielte8"> <![endif]-->
<!--[if IE 7 ]> <html id="ne_wrap" class="ne_ua_ie7 ne_ua_ielte8"> <![endif]-->
<!--[if IE 8 ]> <html id="ne_wrap" class="ne_ua_ie8 ne_ua_ielte8"> <![endif]-->
<!--[if IE 9 ]> <html id="ne_wrap" class="ne_ua_ie9"> <![endif]-->
<!--[if (gte IE 10)|!(IE)]><!-->
<html>
<!--<![endif]-->
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="培训;在线培训;网络培训" name="keywords"/>
    <title>培训项目基本信息管理</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/demos.css"/>
    <style type="text/css">
        th {
            text-align:center; /*设置水平居中*/
            vertical-align:middle;/*设置垂直居中*/
        }
        td {
            text-align:center; /*设置水平居中*/
            vertical-align:middle;/*设置垂直居中*/
        }
    </style>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="funcCon" class="trainApp"></div>
            <ul id="tabs">
                <li><a href="../mtMixTrainSchedule/listSchedule.do"><span>培训日程</span></a></li>
                <li class="selected"><a href="#tabs"><span>教师信息</span></a></li>
                <li><a href="../mtMixTrainSchedule/listScheduleCourse.do"><span>课程信息</span></a></li>
                <div class='clr'></div>
            </ul>
            <div id="mainbody">
                <div class="homezoneall">
                    <div class="homezonehead">
                        <span class="homezonetitle"><font color=red>线下讲座教师信息</font></span>
                    </div>
                    <div class="homezonecontent" id="otherAllSchedule">
                        <div id="teacherTab">
                            <c:if test="${editFlag != null }">
                                <div id="editTeacherArea">
                                    <form id="form5" action="" enctype="multipart/form-data" method="post">
                                        <table class="table1" width="100%" cellspacing="0" cellpadding="0">
                                            <tr>
                                                <th align="center">教师姓名</th>
                                                <td>
                                                    <input type="text" id="teacherName" name="teacherName" value="${teacherForm.teacherName }" size="20" maxlength="20"/>
                                                    <font color=red>&nbsp;*</font></td>
                                                <th>电话</th>
                                                <td>
                                                    <input type="text" id="telephone" name="telephone" value="${teacherForm.telephone }" size="20" maxlength="20"/>
                                                    <font color=red>&nbsp;*</font>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>专业领域</th>
                                                <td>
                                                    <select property="expertAreaId" name="expertAreaId" id="expertAreaId" class="selectStyle" onchange="getSubExpertAreaList(this.value)">
                                                        <c:if test="${expertAreaList != null}">
                                                            <c:forEach var="expert" items="${expertAreaList}">
                                                                <option value="${expert.courseTypeId}" <c:if  test='${ expert.courseTypeId == teacherForm.expertAreaId}'>selected</c:if>>
                                                                        ${expert.courseTypeName}
                                                                </option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                    <font color=red>&nbsp;*</font>
                                                </td>

                                                <th>领域子分类</th>
                                                <td>
                                                    <select property="subExpertAreaId" name="subExpertAreaId" id="subExpertAreaId" class="selectStyle">
                                                        <c:if test="${subExpertAreaList != null}">
                                                            <c:forEach var="expert" items="${subExpertAreaList}">
                                                                <option value="${expert.courseTypeId}" <c:if  test='${ expert.courseTypeId == teacherForm.subExpertAreaId}'>selected</c:if>>
                                                                        ${expert.courseTypeName}
                                                                </option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                    <font color=red>&nbsp;*</font>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>工作单位</th>
                                                <td>
                                                    <input type="text" id="workPlace" name="workPlace" value="${teacherForm.workPlace }" size="20" maxlength="20"/>
                                                </td>
                                                <th>职务</th>
                                                <td>
                                                    <input type="text" id="post" name="post" value="${teacherForm.post }" size="20" maxlength="20"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>职称</th>
                                                <td>
                                                    <input type="text" id="title" name="title" value="${teacherForm.title }" size="20" maxlength="20"/>
                                                </td>
                                                <th>email</th>
                                                <td>
                                                    <input type="text" id="email" name="email" value="${teacherForm.email }" size="20" maxlength="20"/>
                                                </td>
                                            <tr>
                                                <th>讲授课程</th>
                                                <td>
                                                    <input type="text" id="courseName" name="courseName" value="${teacherForm.courseName}" size="40" maxlength="20"/>
                                                    <font color='red'>两个课程名称之间请用;分隔</font>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>性别</th>
                                                <td>
                                                    <select name="gender" id="gender">
                                                        <option value="0" selected>男</option>
                                                        <option value="1">女</option>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>内/外聘</th>
                                                <td>
                                                    <select name="inner" id="employedStyle">
                                                        <option value="0">内聘</option>
                                                        <option value="1">外聘</option>
                                                    </select>
                                                </td>
                                            </tr>
                                        </table>
                                        <div style="text-align:center; margin:20px;">
                                            <input type="submit" value="完成" onclick='javascript:_submitTeacher(${teacherForm.teacherId});'/>
                                        </div>
                                    </form>
                                </div>
                            </c:if>
                            <div id="scheduleTeacher" class="homezonecontent homezonecontentborder" style="border:none">
                                <table class="homecontenttable" width="100%" cellspacing="0" cellpadding="0">
                                    <tr class="tableTh">
                                        <th align="center">姓名</th>
                                        <th align="center">性别</th>
                                        <th align="center">专业领域</th>
                                        <th align="center">领域子分类</th>
                                        <th align="center">工作单位</th>
                                        <th align="center">email</th>
                                        <th align="center">内/外聘</th>
                                        <th align="center">关键词</th>
                                        <th width="7%">所属机构</th>
                                    </tr>
                                    <c:if test="${teacherFormList != null}">
                                        <c:forEach var="teacherForm" items="${teacherFormList}">
                                            <tr>
                                                <td align="center">${teacherForm.teacherName}</td>
                                                <td align="center">${teacherForm.gender}</td>
                                                <td align="center">${teacherForm.expertAreaId}</td>
                                                <td align="center">${teacherForm.subExpertAreaId}</td>
                                                <td align="center">${teacherForm.workPlace}</td>
                                                <td align="center">${teacherForm.email}</td>
                                                <td align="center">${teacherForm.inner}</td>
                                                <td align="center">${teacherForm.keyWords}</td>
                                                <td align="center">${teacherForm.isUnderScope}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="bottombody"></div>
<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>

<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>

<script>
    $(function () {
        $('#tabs li').click(function () {
            $(this).addClass('selected').siblings().removeClass('selected');
        });
        $("#tabs").find("li:eq(0)").click(function () {
            window.location.href = "../mtMixTrainSchedule/listSchedule.do";
        });
        $("#tabs").find("li:eq(2)").click(function () {
            window.location.href = "../mtMixTrainSchedule/listScheduleCourse.do";
        });
    });

    function getSubExpertAreaList(expertAreaId) {
        $.ajax({
            url: 'courseType.do?method=getSubExpertAreaList',
            type: "POST",
            data: {expertAreaId: expertAreaId},
            dataType: "text",
            success: function (data, evt) {
                document.getElementById("subExpertAreaId").length = 0;
                var node = $.parseJSON(data);
                for (var i = 0; i < node.length; i++) {
                    document.getElementById("subExpertAreaId").options[document.getElementById("subExpertAreaId").length] = new Option(node[i].subExpertAreaName, node[i].subExpertAreaId);   //建立option
                }
            }
        })
    }
</script>
<script type="text/javascript">
    function editTeacher(tId, trainId) {
        document.location.href = "tchrFileAction.do?method=editTeacher&trainId=" + trainId + "&tid=" + tId;
    }

    function _submitTeacher(teacherId) {
        $("#form5").attr("action", "tchrFileAction.do?method=EditTeacherInSchedule&tid=" + teacherId);
        var options = {
            beforeSubmit: checkTeacherInput,
            success: function (data) {
                $("#editTeacherArea").animate({opacity: 1.0}, 300).fadeOut("normal", function () {
                    $(this).css("display", "none")
                });
                $('#scheduleTeacher').html($(data).find('#scheduleTeacher').html());
            }
        }
        $("#form5").submit(function () {
            $(this).ajaxSubmit(options);
            return false;
        });
    }

    function checkTeacherInput() {
        if ($("#teacherName").val() == "") {
            jAlert("<font color=red>教师姓名不能为空!</font>", "提示");
            $("#teacherName").focus();
            return false;
        }
        var _tele = $("#telephone").val();
        var regNum = /^[0-9]*[1-9][0-9]*$/;
        if (_tele == "") {
            jAlert("<font color=red>教师电话不能为空!</font>", "提示");
            $("#tele").focus();
            return false;
        } else {
            if (regNum.test(_tele)) {
                $("#form5").submit();

            } else {
                jAlert("<font color=red>电话号码必须是数字!</font>", "提示");
                $("#tele").focus();
                return false;
            }
        }
    }
</script>
</body>
</html>