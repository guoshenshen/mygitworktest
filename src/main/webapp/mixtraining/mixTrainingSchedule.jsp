<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page
        import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <title><%=Constants.systemName%>
    </title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <%--<link rel="stylesheet" type="text/css" href="/css/demos.css"/>--%>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/uploadify.css">
    <link rel="stylesheet" type="text/css" href="/css/uploadifive.css">
    <style type="text/css">
        #_preview_table {
            position: absolute;
            height: 500px;
            overflow-y: auto;
            overflow-x: hidden;
            width: 882px;
            z-index: 0;
            left: 20px;
            top: 40px;
        }

        .leftInput {
            margin-top: 10px;
        }

        .file {
            width: 150px;
            position: relative;
            display: inline-block;
            border: 1px solid #d3d3d3;
            background: #8398D7;
            font-weight: normal;
            color: white;
            border-radius: 4px;
            padding: .4em 1em;
            font-size: 12px;
            overflow: hidden;
            line-height: 20px;
            text-align: center;
        }
        .file input {
            position: absolute;
            font-size: 200px;
            right: 0;
            top: 0;
            opacity: 0;
        }
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

            <c:if test="${train.trainWay != 0}">
                <ul id="tabs">
                    <li class="selected"><a href="#tabs"><span>培训日程</span></a></li>
                    <li><a href="../mtMixTrainSchedule/listScheduleTeacher.do"><span>教师信息</span></a></li>
                    <li><a href="../mtMixTrainSchedule/listScheduleCourse.do"><span>课程信息</span></a></li>
                    <div class='clr'></div>
                </ul>
            </c:if>
            <div id="scheduleTab">
                <div id="mainbody" class="">
                    <div class="homezoneall">
                        <!-- InstanceBeginEditable name="main" -->
                        <div class="homezonehead">
                            <span class="homezonetitle">新建日程</span>
                        </div>
                        <div class="homezonecontent">
                            <div class="addNew">
                                <input type='hidden' id='_trainEndDate' value=${train.endTime }/>
                                <div class="clr1"></div>
                                <form action="../mtMixTrainSchedule/forAddOffLineScheduleItemInfo.do" id="form1" method="post"  class="form-inline">
                                    <%--enctype="multipart/form-data"--%>
                                    <c:if test="${train.trainWay == 0}">
                                        <div>
                                            <script></script>
                                            <div class="clr1"></div>
                                        </div>
                                        <div>
                                          <span class="leftInput">
                                            <span style="float: left;">
                                                安排课程：<input type="text" name="courseName" id="courseName" class="textInput longInput form-control" size=55 maxlength=10000 readonly style="width:380px;"/>
                                            </span>
                                            <span style="display:inline;">
                                                <a href="javascript:_selectCourse1(${train.trainWay })" class="btn-orange-l">
                                                    <span class="btn-orange-r">指定</span>
                                                </a>
                                                <a href="javascript:_concelSelectCourse(${train.trainWay })" class="btn-orange-l">
                                                    <span class="btn-orange-r">清空</span>
                                                </a>
                                                &nbsp;&nbsp;<font color=red>*</font>
                                                <input type="hidden" id="courseId" value=""/>
                                            </span>
                                          </span>
                                            <span class="leftInput">
                                            <span>
                                                指定分类：<input type="text" name="sortLable" id="sortLable" class="textInput longInput form-control" size="20" maxlength="150">
                                            </span>
                                          </span>
                                        </div>
                                        <div class="clr1"></div>
                                        <div style="float:right;margin-right:20px;">
                                            <a href="javascript:resetScheduleForm();" class="btn-orange-l">
                                                <span class="btn-orange-r">重&nbsp;&nbsp;置</span>
                                            </a>
                                        </div>
                                        <div style="float:right;margin-right:10px;">
                                            <a name="_addSchedule" id="_addSchedule" href="javascript:addOnLineScheduleItem(${train.trainWay });" class="btn-blue-l">
                                                <span class="btn-blue-r">提&nbsp;&nbsp;交</span>
                                            </a>
                                        </div>
                                    </c:if>

                                    <!--   分隔符，分隔符，分隔符，分隔符，分隔符   -->

                                    <c:if test="${train.trainWay == 1}">
                                        <div>
                                           <span class="leftInput">
                                               日&nbsp;&nbsp;&nbsp;期：
                                               <input name="scheduleDate" id="scheduleDate" class="Wdate textInput shortInput rightMar form-control" type="text"
                                                      onClick="WdatePicker({onpicking:function(dp){judgeEndDate( dp.cal.getNewDateStr());},dateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true})"
                                                      size=15 value="${recentOfflineScheduleDate }"/>
                                                时间段：
                                                    从<input name="scheduleStartTime" id="scheduleStartTime"
                                                            onFocus="WdatePicker({dateFmt:'HH:mm',isShowClear:false,readOnly:true,maxDate:'#F{$dp.$D(\'scheduleEndTime\',{m:-1});}',onpicked:function(){scheduleEndTime.focus();}})"
                                                            class="Wdate textInput form-control" type="text" size=24/>
                                                    到<input name="scheduleEndTime" id="scheduleEndTime"
                                                            onFocus="WdatePicker({dateFmt:'HH:mm',isShowClear:false,readOnly:true,minDate:'#F{$dp.$D(\'scheduleStartTime\',{m:-1});}'})"
                                                            class="Wdate textInput form-control" type="text" size=24/>
                                           </span>
                                            <div class="clr1"></div>
                                        </div>
                                        <div>
                                            <span class="leftInput">
                                                <span style="float: left;">
                                                    内&nbsp;&nbsp;&nbsp;容：
                                                    <input type="text" name="courseName" id="courseName" class="textInput shortInput form-control" size=15/><font color=red>*</font>
                                                    <span style="margin-left:94px;">
                                                        是否纳入课程：
                                                        <select name="isTrainTheme" id="isTrainTheme">
                                                            <option value="1">是</option>
                                                            <option value="0">否</option>
                                                        </select>
                                                        <font color=red>*</font>
                                                    </span>
                                                </span>
                                            </span>
                                        </div>
                                        <div class="clr1"></div>
                                        <div>
                                            <span class="leftInput">
                                                报告人：<input type="text" name="teacherName" id="teacherName" class="textInput shortInput form-control" size=10/>
                                            </span>
                                            <span style="display: inline;">
                                                <a href="javascript:_selectTeacher()" class="btn-orange-l"><span class="btn-orange-r">选择</span></a>
                                                <input type="hidden" id="teacherId" value="" calss="form-control"/>
                                            </span>
                                            <span class="leftInput">主持人：
                                                <input type="text" name="hoster" id="hoster" class="textInput shortInput form-control" size=10/>
                                            </span>
                                        </div>
                                        <div class="clr1"></div>

                                        <div>
                                            <span class="leftInput">地&nbsp;&nbsp;&nbsp;点：
                                                <input type="text" name="location" id="location" value='' class="textInput shortInput form-control"/>
                                            </span>
                                        </div>
                                        <div class="form-group " style="width:800px;padding-top:10px;">
                                            <span class="leftInput">
                                                资&nbsp;&nbsp;&nbsp;料：
                                            </span>
                                            <span class=" btn btn-primary">
                                                <span class="usebg">
                                                    <input style="float: right;" type="file" name="uploadFile" id="uploadFile" size=15/>
                                                    <input type="hidden" name="mainPicUrl"/>
                                                </span>
                                                <div class="col-sm-6" style="margin-left:-200px;">
                                                    <p id="uploadInfo" style="text-align: left;line-height:2em;"></p>
                                                    <div id="fileQueue"></div>
                                                </div>
                                            </span>
                                        </div>
                                        <div class="clr1"></div>
                                        <div style="float:right;margin-right:20px;">
                                            <a href="javascript:resetScheduleForm();" class="btn-orange-l">
                                                <span class="btn-orange-r">重&nbsp;&nbsp;置</span>
                                            </a>
                                        </div>
                                        <div style="float:right;margin-right:10px;">
                                            <a href="javascript:addOffLineScheduleItem(${train.trainWay });" class="btn-blue-l">
                                                <span class="btn-blue-r">保&nbsp;&nbsp;存</span>
                                            </a>
                                        </div>
                                    </c:if>

                                    <c:if test="${train.trainWay == 2}">
                                        <div style="font-size:13px;font-weight:bold;">
                                            <span class="leftInput">
                                                属&nbsp;&nbsp;于：
                                                <label>
                                                    <input type="radio" name="scheduleType" value="0" checked/>线上学习
                                                </label>
                                                <label>
                                                    <input type="radio" name="scheduleType" value="1"/>线下讲座
                                                </label>
                                            </span>
                                            <span style="float:right;padding:0px 20px 20px 0px;">
                                                (<font color=red>*</font><font color=#8C8C8C>为必填项</font>)
                                            </span>
                                        </div>
                                        <div class="clr1"></div>

                                        <div id="onlineSchedule">
                                            <div>
                                               <span class="leftInput">
                                                   开始日期：
                                                   <input name="scheduleStartDate" class="Wdate textInput shortInput rightMar form-control" type="text"
                                                          id="scheduleStartDate" onFocus="WdatePicker()" size=15 value="${recentOnlineScheduleDate }"/>
                                                   截止日期：
                                                   <input name="scheduleEndDate" class="Wdate textInput shortInput rightMar form-control" type="text" id="scheduleEndDate" onFocus="WdatePicker()"  size=15  />
                                                </span>
                                                <div class="clr1"></div>
                                            </div>
                                            <div>
                                              <span class="leftInput">
                                                <span style="float: left;">
                                                    安排课程：
                                                    <input type="text" name="onLineCourseName" id="onLineCourseName" class="textInput longInput form-control"
                                                           size=15 maxlength=100000 readonly style="width:376px;"/>
                                                </span>
                                                <span style="display: inline;">
                                                    <a href="javascript:_selectCourse2(${train.trainWay })" class="btn-orange-l">
                                                        <span class="btn-orange-r">指定</span>
                                                    </a>
                                                    <a href="javascript:manageMySelectCourse(0,${train.trainWay })" class="btn-orange-l">
                                                        <span class="btn-orange-r">查看已选</span>
                                                    </a>
                                                    <a href="javascript:_concelSelectCourse(${train.trainWay })" class="btn-orange-l">
                                                        <span class="btn-orange-r">清空</span>
                                                    </a>
                                                    &nbsp;&nbsp;<font color=red>*</font>
                                                    <input type="hidden" id="courseId" value=""/>
                                                </span>
                                              </span>
                                            </div>
                                            <div class="clr1"></div>
                                            <div style="float:right;margin-right:20px;margin-top:35px">
                                                <a href="javascript:resetScheduleForm();" class="btn-orange-l">
                                                    <span class="btn-orange-r">重&nbsp;&nbsp;置</span>
                                                </a>
                                            </div>
                                            <div style="float:right;margin-right:10px;margin-top:35px">
                                                <a name="_addSchedule" id="_addSchedule" href="javascript:addOnLineScheduleItem(${train.trainWay });" class="btn-blue-l">
                                                    <span class="btn-blue-r">提&nbsp;&nbsp;交</span>
                                                </a>
                                            </div>
                                        </div>

                                        <div id="offlineSchedule" style="display:none">
                                            <div>
                                               <span class="leftInput">
                                                   日&nbsp;&nbsp;&nbsp;期：
                                                   <input name="scheduleDate" id="scheduleDate" class="Wdate textInput shortInput rightMar form-control" type="text"
                                                          onClick="WdatePicker({onpicking:function(dp){judgeEndDate( dp.cal.getNewDateStr());},dateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true})"
                                                          size=15 value="${recentOfflineScheduleDate }"/>
                                                    时间段：
                                                        从<input name="scheduleStartTime" id="scheduleStartTime"
                                                                onFocus="WdatePicker({dateFmt:'HH:mm',isShowClear:false,readOnly:true,maxDate:'#F{$dp.$D(\'scheduleEndTime\',{m:-1});}',onpicked:function(){scheduleEndTime.focus();}})"
                                                                class="Wdate textInput form-control" type="text" size=24/>
                                                        到<input name="scheduleEndTime" id="scheduleEndTime"
                                                                onFocus="WdatePicker({dateFmt:'HH:mm',isShowClear:false,readOnly:true,minDate:'#F{$dp.$D(\'scheduleStartTime\',{m:-1});}'})"
                                                                class="Wdate textInput form-control" type="text" size=24/>
                                               </span>
                                               <div class="clr1"></div>
                                            </div>
                                            <div>
                                              <span class="leftInput">
                                                <span style="float: left;">
                                                   内&nbsp;&nbsp;&nbsp;容：
                                                        <input type="text" name="courseName" id="courseName" class="textInput longInput "/><font color=red>*</font>
                                                        <span style="margin-left:105px;">是否纳入课程：
                                                            <select name="isTrainTheme" id="isTrainTheme">
                                                                <option value="1">是</option><option value="0">否</option>
                                                            </select>
                                                            <font color=red>*</font>
                                                        </span>
                                                </span>
                                              </span>
                                            </div>

                                            <div class="clr1"></div>
                                            <div>
                                               <span class="leftInput">
                                                报告人：<input type="text" name="teacherName" id="teacherName" class="textInput shortInput form-control" size=10/>
                                               </span>

                                                <span style="display: inline;">
                                                    <a href="javascript:_selectTeacher()" class="btn-orange-l">
                                                        <span class="btn-orange-r">选择</span>
                                                    </a>
                                                    <input type="hidden" id="teacherId" value="" class="form-control"/>
                                                </span>

                                                <span class="leftInput">主持人：
                                                    <input type="text" name="hoster" id="hoster" class="textInput shortInput form-control" size=10/>
                                                </span>
                                            </div>
                                            <div class="clr1"></div>
                                            <div>
                                                <span class="leftInput">地&nbsp;&nbsp;&nbsp;点：
                                                    <input type="text" name="location" id="location" value='' class="textInput longInput"/></span>
                                            </div>
                                            <div class="clr1"></div>
                                            <div class="form-group " style="width:800px;padding-top:10px;">
                                                <span class="leftInput">资&nbsp;&nbsp;&nbsp;料：</span>
                                                <span class=" btn btn-primary">
                                                    <span class="usebg">
                                                        <input style="float: right;" type="file" name="uploadFile" id="uploadFile" size=15/>
                                                        <input type="hidden" name="mainPicUrl"/>
                                                    </span>
                                                    <div class="col-sm-6" style="margin-left:-200px">
                                                        <p id="uploadInfo" style="text-align: left;line-height:2em;"></p>
                                                        <div id="fileQueue"></div>
                                                    </div>
                                                </span>
                                            </div>
                                            <div> <!--<span class="leftInput">备注:<input type="text" name="remark" id="remark" class="textInput longInput" size=15/></span>--></div>
                                            <div class="clr1"></div>
                                            <div style="float:right;margin-right:20px">
                                                <a href="javascript:resetScheduleForm();" class="btn-orange-l">
                                                    <span class="btn-orange-r">重&nbsp;&nbsp;置</span>
                                                </a>
                                            </div>
                                            <div style="float:right;margin-right:10px">
                                                <a href="javascript:addOffLineScheduleItem(${train.trainWay });"
                                                   class="btn-blue-l"><span class="btn-blue-r">提&nbsp;&nbsp;交</span></a>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="clr1"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!--   分隔符，分隔符，分隔符，分隔符，分隔符   -->

                    <div>
                        <c:if test="${train.trainWay == 0}">
                            <form>
                                <%--继续教育网中没有neededHours这个字段--%>
                                <%--<span>必修学时：
                                    <input name="neededHours" id="neededHours" value="${train.neededHours }"/>
                                    <a onclick="javascript:setNeededHours(${train.id})" style="background-color: orange;border-radius:4px;text-indent: 10px;color: #fff;padding: 6px 12px;cursor: pointer;">确定</a>
                                </span>--%>
                            </form>
                            <form style="padding-top: 16px;">
                                <span>线上班总结题目：
                                    <textarea id="trainSummary" rows="3" cols="20">${summary.summaryName }</textarea>
                                    <a onclick="javascript:setSummary(${train.ID})" style="background-color: orange;border-radius:4px;text-indent: 10px;color: #fff;padding: 6px 12px;cursor: pointer;">确定</a>
                                </span>
                            </form>
                        </c:if>
                    </div>

                    <div class="homezoneall">
                        <div class="homezonehead">
                            <span class="homezonetitle">
                                当前日程&nbsp;&nbsp;<%-- <span style="font-weight:normal">[<a href="javascript:previewSchedule(${scheduleId })">&nbsp;预览&nbsp;</a>]</span> --%>
                            </span>
                        </div>
                        <div class="homezonecontent">
                            <div id="item_table">

                                <c:if test="${train.trainWay == 0}">
                                    <c:if test="${reviseOnlineItemList != null}">
                                        <table class="homecontenttable table table-striped table-bordered batchOperation" id="_table1">&nbsp;&nbsp;&nbsp;&nbsp;
                                            <span style="color:red">已选课程学时总计：<%--${totalClassHours }--%>学时</span>
                                            <!-- 学时和分类，继续教育网数据库还没有添加这两个字段 -->
                                            <tr class="tableTh">
                                                <th>课程</th>
                                                <th>学时</th>
                                                <th>主讲人</th>
                                                <th>分类</th>
                                                <th>操作</th>
                                            </tr>
                                            <c:forEach var="itemList" items="${reviseOnlineItemList}" >
                                                <c:forEach var="item" items="${itemList}" >
                                                    <tr id="item1_${item.id}">
                                                        <td align='left' width="30%">
                                                            <a href="javascript:void(0);"
                                                               onclick="window.open('../courseStudy/previewStudy.do?courseID=${item.courseId}',
                                                                       '课件预览','height=700,width=900,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no')">
                                                                    ${item.courseName }
                                                            </a>
                                                        </td>
                                                        <td align='center' width="10%">
                                                                ${item.classHour }
                                                        </td>
                                                        <td align='center' width="10%">
                                                                ${item.teacherName }
                                                        </td>
                                                        <td align='center' width="10%">
                                                                ${item.sortLable }
                                                        </td>
                                                        <td align='center' width="15%">
                                                            <c:if test="${preview==null}">
                                                                <a href="javascript:editScheduleItem(${train.trainWay},${item.onOrOffLineFlag},${item.id})">编辑</a>
                                                                |<a href="javascript:deleteScheduleItem(${item.id})">删除</a>
                                                            </c:if>
                                                            <c:if test="${preview!=null}">
                                                                <a href="javascript:void(0);"
                                                                   onclick="window.open('../courseStudy/scormStudy.do?courseID=${item.courseId}','课件预览','height='
                                                                           +(screen.availHeight-45)+',width='+(screen.availWidth-8)+',top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no')">学习
                                                                </a>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:forEach>
                                        </table>
                                    </c:if>
                                </c:if>

                                <c:if test="${train.trainWay == 1}">
                                    <c:if test="${reviseOfflineItemList != null}">
                                        <table class="homecontenttable" id="_table2">
                                            <tr class="tableTh">
                                                <th>日期</th>
                                                <th>时间</th>
                                                <th>地点</th>
                                                <th>内容</th>
                                                <th>报告人</th>
                                                <c:if test="${scheduleHasHoster != null}">
                                                    <th>主持人</th>
                                                </c:if>
                                                <c:if test="${preview == null}">
                                                    <th>操作</th>
                                                </c:if>
                                                <c:if test="${preview != null}">
                                                    <th>资料</th>
                                                </c:if>
                                            </tr>
                                            <c:forEach var="itemList" items="${reviseOfflineItemList}" >
                                                <c:forEach var="item" items="${itemList}"  varStatus="sta">
                                                    <tr id="item2_${item.id}">
                                                        <c:if test="${sta.first}">
                                                            <td align="center" width="15%" rowspan="${fn:length(itemList)}">
                                                                <fmt:formatDate value="${item.scheduleDate}" pattern="yyyy年MM月dd日"/>
                                                            </td>
                                                        </c:if>
                                                        <td align='center' width="10%">
                                                            ${item.scheduleStartTime}~${item.scheduleEndTime}
                                                        </td>
                                                        <td align='center' width="15%">
                                                            ${item.location}
                                                        </td>
                                                        <td align='left' width="30%">
                                                            <c:if test="${item.hasCourseUrl == 0}">
                                                                ${item.courseName }
                                                            </c:if>
                                                            <c:if test="${item.hasCourseUrl == 1}">
                                                                <a href="javascript:void(0);"
                                                                   onclick="window.open('../courseStudy/previewStudy.do?courseID=${item.courseId}',
                                                                           '课件预览','height=700,width=900,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no')">
                                                                    ${item.courseName}
                                                                </a>
                                                            </c:if>
                                                        </td>
                                                        <td align='center'>
                                                            ${item.teacherName}
                                                        </td>
                                                        <c:if test="${scheduleHasHoster != null}">
                                                            <td align='center'>
                                                                ${item.hoster}
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${preview == null}">
                                                            <td align='center' width="15%">
                                                                <a href="javascript:editScheduleItem(${train.trainWay },${item.onOrOffLineFlag},${item.id})">编辑</a>
                                                                | <a href="javascript:deleteScheduleItem2(${item.id})">删除</a>
                                                                <c:if test="${item.fileName != ''}">
                                                                    | <a href="../mixtraining/downloadScheduleItemFile.jsp?fileName=${item.fileName }&filePath=${item.filePath }">下载</a>
                                                                </c:if>
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${preview != null}">
                                                            <td align='center' width="15%">
                                                                <c:if test="${item.fileName != ''}">
                                                                    <a href="../mixtraining/downloadScheduleItemFile.jsp?fileName=${item.fileName }&filePath=${item.filePath }">下载</a>
                                                                </c:if>
                                                                <c:if test="${item.fileName == ''}">
                                                                    --
                                                                </c:if>
                                                            </td>
                                                        </c:if>
                                                    </tr>
                                                </c:forEach>
                                            </c:forEach>
                                        </table>
                                    </c:if>
                                </c:if>

                                <c:if test="${train.trainWay == 2}">
                                    <c:if test="${reviseOnlineItemList != null}">
                                        <div style="text-align:center;font-size:13px;font-weight:bold;background-color:#F2F2F2;height:40px;line-height:40px;">
                                            线上学习安排
                                        </div>
                                        <table class="homecontenttable" id="_table1">
                                            <tr class="tableTh">
                                                <th>时间</th>
                                                <th>课程</th>
                                                <th>主讲人</th>
                                                <th>操作</th>
                                            </tr>
                                            <c:forEach var="itemList" items="${reviseOnlineItemList}" >
                                                <c:forEach var="item" items="${itemList}" >
                                                    <tr id="item1_${item.id}">
                                                        <td align='center' width="20%">
                                                            ${item.scheduleStartTime}~${item.scheduleEndTime}
                                                        </td>
                                                        <td align='center' width="40%">
                                                            <a href="javascript:void(0);"
                                                               onclick="window.open('../courseStudy/previewStudy.do?courseID=${item.courseId}&info=admin','课件预览',
                                                                       'height=700,width=900,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no')">
                                                                ${item.courseName}
                                                            </a>
                                                        </td>
                                                        <td align='center' width="10%">
                                                            ${item.teacherName}
                                                        </td>
                                                        <td align='center' width="15%">
                                                            <c:if test="${preview == null}">
                                                                <a href="javascript:deleteScheduleItem(${item.id})">删除</a>
                                                            </c:if>
                                                            <c:if test="${preview != null}">
                                                                <a href="javascript:void(0);"
                                                                   onclick="window.open('../courseStudy/scormStudy.do?courseID=${item.courseId}','课件预览','height='
                                                                           +(screen.availHeight-45)+',width='+(screen.availWidth-8)+',top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no')">学习</a>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:forEach>
                                        </table>
                                    </c:if>

                                    <br/><br/>

                                    <c:if test="${reviseOfflineItemList != null}">
                                        <div style="text-align:center;font-size:13px;font-weight:bold;background-color:#F2F2F2;height:40px;line-height:40px;">
                                            线下讲座安排
                                        </div>
                                        <table class="homecontenttable" id="_table2">
                                            <tr class="tableTh">
                                                <th>日期</th>
                                                <th>时间</th>
                                                <th>地点</th>
                                                <th>内容</th>
                                                <th>报告人</th>
                                                <c:if test="${scheduleHasHoster != null}">
                                                    <th>主持人</th>
                                                </c:if>
                                                <c:if test="${preview == null}">
                                                    <th>操作</th>
                                                </c:if>
                                                <c:if test="${preview != null}">
                                                    <th>资料</th>
                                                </c:if>
                                            </tr>

                                            <c:forEach var="itemList" items="${reviseOfflineItemList}" >
                                                <c:forEach var="item" items="${itemList}" varStatus="status">
                                                    <tr id="item2_${item.id}">
                                                        <c:if test="${status.first}">
                                                            <td align="center" width="15%" rowspan="${fn:length(itemList)}">
                                                                <fmt:formatDate value="${item.scheduleDate}" pattern="yyyy年MM月dd日"/>
                                                            </td>
                                                        </c:if>
                                                        <td align='center' width="10%">
                                                                ${item.scheduleStartTime}~${item.scheduleEndTime}
                                                        </td>
                                                        <td align='center' width="15%">
                                                                ${item.location}
                                                        </td>
                                                        <td align='center' width="30%">
                                                            <c:if test="${item.hasCourseUrl == 0}">
                                                                ${item.courseName }
                                                            </c:if>
                                                            <c:if test="${item.hasCourseUrl == 1}">
                                                                <a href="javascript:void(0);"
                                                                   onclick="window.open('../courseStudy/previewStudy.do?courseID=${item.courseId}',
                                                                           '课件预览','height=700,width=900,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no')">
                                                                        ${item.courseName}
                                                                </a>
                                                            </c:if>
                                                        </td>
                                                        <td align='center'>
                                                                ${item.teacherName}
                                                        </td>
                                                        <c:if test="${scheduleHasHoster != null}">
                                                            <td align='center'>
                                                                    ${item.hoster}
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${preview == null}">
                                                            <td align='center' width="15%">
                                                                <a href="javascript:editScheduleItem(${train.trainWay },${item.onOrOffLineFlag},${item.id})">编辑</a>
                                                                | <a href="javascript:deleteScheduleItem(${item.id})">删除</a>
                                                                <c:if test="${item.fileName != ''}">
                                                                    | <a href="../mixtraining/downloadScheduleItemFile.jsp?fileName=${item.fileName }&filePath=${item.filePath }">下载</a>
                                                                </c:if>
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${preview != null}">
                                                            <td align='center' width="15%">
                                                                <c:if test="${item.fileName != ''}">
                                                                    <a href="../mixtraining/downloadScheduleItemFile.jsp?fileName=${item.fileName }&filePath=${item.filePath }">下载</a>
                                                                </c:if>
                                                                <c:if test="${item.fileName == ''}">
                                                                    --
                                                                </c:if>
                                                            </td>
                                                        </c:if>
                                                    </tr>
                                                </c:forEach>
                                            </c:forEach>
                                        </table>
                                    </c:if>
                                </c:if>
                            </div>
                            <div id="renameschedule" style="display:none;cursor:default">
                                <div id='_pop_win'>
                                    <h2>重命名<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>
                                </div>
                                <p></p>
                                <h2 class="ptext">重命名培训日程</h2>
                                请填写日程名称:<input type="text" id="rename" name="rename"/>
                                <br/><br/>
                                <input type="button" id="rename_no" value="返回"/> &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="button" id="rename_yes" value="保存"/>
                            </div>
                            <br/>
                        </div>
                    </div>
                </div>
                <div id="_preview" style="display:none;cursor:default;overflow:hidden;">
                    <div id='_pop_win'><h2>预览<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2></div>
                    <c:if test="${preview != null}">
                        ${previewScheduleName}
                    </c:if>
                    <div id="_preview_table"></div>
                </div>

                <div id="editOnLineItem" style="display:none;cursor:default;overflow:hidden;">
                    <div id='_pop_win'><h2>编辑<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2></div>
                    <div id="_editItem_table" class="homezonecontent">
                        <table class="homecontenttable" align="center">
                            <tr>
                                <td align="right">课程:</td>
                                <td>
                                    <input type="text" class="textInput" name="courseName" id="editItem_courseName" size=25 readonly="readonly" UNSELECTABLE="on"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">分类标签:</td>
                                <td><input type="text" class="textInput" name="sortLable" id="edit_sortLable" size=25 UNSELECTABLE="on"/></td>
                            </tr>
                        </table>
                        <input type="hidden" id="editItem_courseId" name="editItem_courseId" value=""/>
                        <input type="hidden" id="editItem_id" name="editItem_id" value=""/>
                    </div>
                    <p></p>
                    <div id="updateScheduleItem">
                        <input type="button" name="_addSchedule" value="更&nbsp;&nbsp;新" onclick="updateOnLineScheduleItem();"/>
                    </div>
                    <br/>
                    <div id="courseSelect" style="display:none"></div>
                </div>
                <div id="editOffLineItem" style="display:none;cursor:default">
                    <div id='_pop_win'><h2>编辑<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2></div>
                    <div id="_editItem_table" class="homezonecontent">
                        <form action="../mtMixTrainSchedule/updateOffLineScheduleItem.do" id="form3" method="post" enctype="multipart/form-data">
                            <table class="homecontenttable" align="center" style="width:95%">
                                <tr>
                                    <td align="right" colspan="2">日期:</td>
                                    <td align="left">
                                        <input name="editItem_trainDate" id="editItem_trainDate" class="Wdate textInput" type="text"
                                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true})" value="" size=25/>
                                    </td>
                                    <td align="right" colspan="2">起止时间：</td>
                                    <td align="left">
                                        <input name="editItem_startTrainTime" id="editItem_startTrainTime" class="Wdate textInput" type="text"
                                                            onClick="WdatePicker({dateFmt:'HH:mm',isShowClear:false,readOnly:true})" value="" size=25/>
                                        ~<input name="editItem_endTrainTime" id="editItem_endTrainTime" class="Wdate textInput"
                                                type="text" onClick="WdatePicker({dateFmt:'HH:mm',isShowClear:false,readOnly:true})" value="" size=25/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" colspan="2"><font color=red>*</font>内容:</td>
                                    <td align="left"><input type="text" class="textInput" name="editItem_courseName" id="editItem_courseName" size=25/></td>
                                    <td align="right" colspan="2"><font color=red>*</font>是否纳入课程：</td>
                                    <td align="left"><select name="editItem_isTrainTheme" id="editItem_isTrainTheme">
                                        <option value="1">是</option>
                                        <option value="0">否</option>
                                    </select></td>
                                </tr>
                                <tr>
                                    <td align="right" colspan="2">报告人:</td>
                                    <td align="left">
                                        <input type="text" class="textInput" name="editItem_teacher" id="editItem_teacher" size=25/>
                                        <input type="button" name="selectTeacher" value="选&nbsp;&nbsp;择" onclick="editSchedule_selectTeacher()"/>
                                    </td>
                                    <td align="right" colspan="2">主持人：</td>
                                    <td align="left">
                                        <input type="text" name="editItem_hoster" id="editItem_hoster" class="textInput shortInput" size=10/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" colspan="2">地点:</td>
                                    <td align="left" colspan="4">
                                        <input type="text" class="textInput longInput" name="editItem_location" id="editItem_location"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" colspan="2">资料:</td>
                                    <td align="left" colspan="4">
                                        <div class="file">资&nbsp;料&nbsp;上&nbsp;传
                                            <input type="file" name="uploadFile" id="uploadFile" size=15/>
                                        </div>
                                        <font class="showFileName"></font>
                                    </td>
                                </tr>
                            </table>
                            <input type="hidden" id="editItem_id" name="editItem_id" value=""/>
                            <input type="hidden" id="editItem_teacherId" name="editItem_teacherId" value=""/>
                        </form>
                    </div>
                    <div id="updateScheduleItem" style="padding-top:20px;">
                        <input type="button" name="_addSchedule" value="更&nbsp;&nbsp;新" onclick="updateOffLineScheduleItem();"/>
                    </div>
                    <div id="teacherSelect" style="display:none"></div>
                </div>
                <div id="_teacherCenter" style="display:none;cursor:default">
                    <div id='_pop_win'><h2>添加教师<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2></div>
                    <div id="add_teacherSelect"></div>
                </div>
                <div id="_courseCenter" style="display:none;cursor:default;overflow:hidden;">
                    <div id='_pop_win'><h2>添加课程<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2></div>
                    <div id="add_courseSelect">
                    </div>
                </div>
            </div>
            <div id="mySelectCourse" style="display:none;cursor:default;overflow:hidden;">
                <div id='_pop_win'><h2>已指定课程列表<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2></div>
                <div id="isMySelectCourse"></div>
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
<script type="text/javascript" src="/js/jquery.uploadify.js"></script>
<script type="text/javascript" src="/js/jquery.uploadifive.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    var supportH5 = $.supportHTML5();
    $(function () {
        $('#tabs li').click(function () {
            $(this).addClass('selected').siblings().removeClass('selected');
        });
        $('input[name=scheduleType]').bind('click', function () {
            var flag = $('input[name=scheduleType]:checked').val();
            if (flag == 0) {
                $('#onlineSchedule').css('display', "block");
                $('#offlineSchedule').css('display', "none");
                $.cookie("scheduleType", "onlineSchedule");
            } else {
                $('#onlineSchedule').css('display', "none");
                $('#offlineSchedule').css('display', "block");
                $.cookie("scheduleType", "offlineSchedule");
            }
        });
        if ($.cookie("scheduleType") != null) {
            if ($.cookie("scheduleType") == "onlineSchedule") {
                $('input[name=scheduleType]:eq(0)').attr("checked", "true");
                $('#onlineSchedule').css('display', "block");
                $('#offlineSchedule').css('display', "none");
            } else {
                $('input[name=scheduleType]:eq(1)').attr("checked", "true");
                $('#onlineSchedule').css('display', "none");
                $('#offlineSchedule').css('display', "block");
            }
        }
        /* 上传资料开始 */
        var uploadSuccess = function (file, data, response) {
            var resultData = $.parseJSON(data);
            var result = resultData.result;
            var uploadResultInfo = "";
            if (result == "true") {
                uploadResultInfo = "";
                $.ajax({
                    data: {"image": resultData.savePath},
                    url: "eosorgTOrganizationAction.do?method=uploadImage",
                    method: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data.status) {
                            uploadResultInfo = "您的资料上传成功";
                            $("#fileQueue").hide();
                        } else {
                            uploadResultInfo = "资料上传失败";
                        }
                        $.Ntip({
                            'content': uploadResultInfo
                        })
                    },
                    error: function () {
                        uploadResultInfo = "资料上传失败";
                        $.Ntip({
                            'content': uploadResultInfo
                        })

                    }

                })
            } else {
                if ($.trim(data.cause) != "") {
                    uploadResultInfo = data.cause;
                }
            }
        };

        if (supportH5) {
            $("#file_upload").uploadifive({
                'uploadScript': "filesToolAction.do?method=uploadPic&uploadType=10&picWidth=160&picHeight=60",
                'buttonText': '上传',
                'queueID': 'fileQueue',
                'multi': false,
                'fileType': 'image/*',
                'fileSizeLimit': "1MB",
                'onUploadComplete': uploadSuccess
            });

        } else {
            //绑定动作到相片上传操作
            $("#file_upload").uploadify({
                'overrideEvents': ['onDialogClose'],
                'method': 'get',
                'swf': './js/uploadify.swf',
                'fileTypeExts': '*.jpg;*.png;*.bmp;*.jpeg',
                'uploader': 'filesToolAction.do?method=uploadPic&uploadType=10&picWidth=70&picHeight=70',
                'fileSizeLimit': "1MB",
                'auto': true,
                'multi': false,
                "formData": {'picWidth': 70, 'picHeight': 70},
                'buttonText': '上传',
                'queueID': 'fileQueue',
                'successTimeout': 6000,
                'onUploadSuccess': uploadSuccess

            });
        }
        /* 上传资料结束 */
        //rowspan("#_table1",1);  rowspan("#_table2",1);
    })


    function citeSchedule(sid) {
        $.ajax({
            url: "mtMixTrainScheduleAction.do?method=citeSchedule",
            type: "post",
            dataType: "text",
            data: "sid=" + sid + "",
            success: function (data, evt) {
                jAlert('<font color=red>引用日程成功!</font>', '提示信息', function () {
                    document.location.reload();
                });
            }
        })
    }

    function previewSchedule(sid) {

        $.ajax({
            url: 'mtMixTrainScheduleAction.do?method=previewSchedule',
            type: 'post',
            dataType: 'text',
            data: "scheduleid=" + sid + "",
            success: function (data) {
                var temp = $(data).find('#item_table').html();
                if (temp.indexOf("日期") > -1) {
                    $('#_preview_table').html($(data).find('#item_table').html());
                    rowspan("#_preview_table #_table1", 1);
                    rowspan("#_preview_table #_table2", 1);
                    $.blockUI({
                        message: $('#_preview'),
                        css: {width: '900px', height: '550px', left: ($left - 900) / 2 - 10, top: ($top - 550) / 2}
                    });
                } else
                    alert("当前日程无任何内容，请添加后再预览！");
            }
        });
        $("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
    }

    function deleteScheduleItem(stid) {

        $.confirm("<font color=red>确定删除?</font>", "删除提示", function (response) {
            if (response == true) {
                var $mask = null;
                $mask = showBufferMask();
                $.ajax({
                    url: '../mtMixTrainSchedule/deleteAjax.do',
                    type: 'post',
                    data: {itemid: stid},
                    dataType: 'JSON',
                    success: function (data) {
                        $mask.remove();
                        if (data.status) {
                            $("#item1_" + stid).remove();
                            var rowSpan = parseInt($("#item2_" + stid + " td:first").attr("rowspan"));
                            if (rowSpan == 1) {
                                $("#item2_" + stid).remove();
                            } else {
                                var $nextLine = $("#item2_" + stid).next("tr");
                                var td1 = $("td:first", $("#item2_" + stid)).html();
                                td1 = "<td rowSpan='" + (rowSpan - 1) + "'>" + td1 + "</td>";
                                $("#item2_" + stid).remove();
                                $nextLine.prepend(td1);
                            }
                        }
                    }
                })
            }

        });
    }

    function deleteScheduleItem2(stid) {

        $.confirm("<font color=red>确定删除?</font>", "删除提示", function (response) {
            if (response == true) {
                var $mask = null;
                $mask = showBufferMask();
                $.ajax({
                    url: '../mtMixTrainSchedule/deleteAjax.do',
                    type: 'post',
                    data: {itemid: stid},
                    dataType: 'JSON',
                    success: function (data) {
                        $mask.remove();
                        if (data.status) {
                            $("#item1_" + stid).remove();
                            var rowSpan = parseInt($("#item2_" + stid + " td:first").attr("rowspan"));
                            if (rowSpan == 1) {
                                $("#item2_" + stid).remove();
                            } else {
                                var $nextLine = $("#item2_" + stid).next("tr");
                                var td1 = $("td:first", $("#item2_" + stid)).html();
                                td1 = "<td rowSpan='" + (rowSpan - 1) + "'>" + td1 + "</td>";
                                $("#item2_" + stid).remove();
                                $nextLine.prepend(td1);
                            }
                        }
                    }
                })
            }

        });
    }

    function editScheduleItem(typeId, onOrOffId, stid) {
        $('#courseSelect').css("display", "none");
        $.ajax({
            url: "../mtMixTrainSchedule/editScheduleItem.do",
            type: "POST",
            dataType: "json",
            data: "itemid=" + stid + "",
            success: function (data) {
                //var nodeArr = $.parseJSON(data);
                var nodeArr = data.data;
                var _isTrainTheme, _hoster, id, _createDate, _startTime, _endTime, _courseName, _teacher, _teacherId,
                    _courseId;
                $.each(nodeArr, function (i, value) {
                    _createDate = nodeArr[i].scheduleDate == 'null' ? "" : nodeArr[i].scheduleDate;
                    _startTime = nodeArr[i].startTime == 'null' ? "" : nodeArr[i].startTime;
                    _endTime = nodeArr[i].endTime == 'null' ? "" : nodeArr[i].endTime;
                    _courseName = nodeArr[i].courseName == 'null' ? "" : nodeArr[i].courseName;
                    _teacher = nodeArr[i].teacher == 'null' ? "" : nodeArr[i].teacher;
                    _id = nodeArr[i].Id;
                    _teacherId = nodeArr[i].teacherId;
                    _courseId = nodeArr[i].courseId;
                    _isTrainTheme = nodeArr[i].isTrainTheme;
                    _hoster = nodeArr[i].hoster;
                    _location = nodeArr[i].location;
                    _sortLable = nodeArr[i].sortLable;
                })
                if (typeId == 2 && onOrOffId == 0 || typeId == 0) {
                    $("#editOnLineItem").find('#editItem_trainStartDate').val(_startTime);
                    $("#editOnLineItem").find('#editItem_trainEndDate').val(_endTime);
                    $("#editOnLineItem").find('#editItem_courseName').val(_courseName);
                    $("#editOnLineItem").find('#editItem_id').val(_id);
                    $("#editOnLineItem").find('#edit_').val(_id);
                    $("#editOnLineItem").find('#editItem_courseId').val(_courseId);
                    $("#editOnLineItem").find('#edit_sortLable').val(_sortLable);
                    $.blockUI({
                        message: $("#editOnLineItem"),
                        css: {width: '840px', height: '600px', left: ($left - 840) / 2, top: ($top - 570) / 2}
                    });
                } else if (typeId == 2 && onOrOffId == 1 || typeId == 1) {
                    $("#editOffLineItem").find('#editItem_trainDate').attr("value", _createDate);
                    $("#editOffLineItem").find('#editItem_startTrainTime').attr("value", _startTime);
                    $("#editOffLineItem").find('#editItem_endTrainTime').attr("value", _endTime);
                    $("#editOffLineItem").find('#editItem_courseName').attr("value", _courseName);
                    $("#editOffLineItem").find('#editItem_teacher').attr("value", _teacher);
                    $("#editOffLineItem").find('#editItem_id').attr("value", _id);
                    $("#editOffLineItem").find('#editItem_teacherId').attr("value", _teacherId);
                    $("#editOffLineItem").find('#editItem_isTrainTheme').attr("value", _isTrainTheme);
                    $("#editOffLineItem").find('#editItem_hoster').attr("value", _hoster);
                    $("#editOffLineItem").find('#editItem_location').attr("value", _location);
                    $.blockUI({
                        message: $("#editOffLineItem"),
                        css: {width: '840px', height: '570px', left: ($left - 840) / 2, top: ($top - 570) / 2}
                    });
                }
                $("a.pop_close_btn").click(function () {
                    $.unblockUI();
                    $('#updateScheduleItem').css('display', 'block');
                });
            }
        })
    }

    function editSchedule_selectCourse(trainWay) {
        var iframeContent = "<iframe height='390' scrolling='auto' width='100%' class='pop_iframe' src='courseCoursetype.do?method=listAllOrgCourseToSchedule&fromSchedule=true&step=-1&trainWay=" + trainWay + "'> </iframe>";
        $('#editOnLineItem #updateScheduleItem').css('display', 'none');
        $('#courseSelect').html(iframeContent).css("display", "block");
    }

    function setNeededHours(trainId) {
        $.ajax({
            url: 'onlineTraining.do?method=setNeededHours',
            type: 'post',
            dataType: 'text',
            data: {
                trainId: trainId,
                neededHours: $('#neededHours').val(),
            },
            success: function (data, evt) {
                jAlert('<font color=red>更新成功!</font>', '提示信息', function () {
                    document.location.reload();
                });
            }
        })
    }

    function setSummary(trainId) {
        $.ajax({
            url: 'trainSummaryAction.do?method=setTrainSummarySubject',
            type: 'post',
            dataType: 'json',
            data: {
                trainId: trainId,
                summaryName: $('#trainSummary').val(),
            },
            success: function (data, evt) {
                if (data.result) {
                    alert("更新成功");
                    $("#trainSummary").val(data.summary.summaryName);
                } else {
                    alert("失败，请刷新后重试");
                }
            }
        })
    }

    function updateOnLineScheduleItem() {
        $.ajax({
            url: '../mtMixTrainSchedule/updateOnLineScheduleItem.do?timestamp=' + new Date().getTime(),
            type: 'post',
            dataType: 'text',
            data: {
                editItem_id: $("#editOnLineItem").find('#editItem_id').val(),
                editItem_startTrainTime: $('#editOnLineItem').find('#editItem_trainStartDate').val(),
                editItem_endTrainTime: $('#editOnLineItem').find('#editItem_trainEndDate').val(),
                editItem_courseId: $('#editOnLineItem').find('#editItem_courseId').val(),
                editItem_sortLable: $('#editOnLineItem').find('#edit_sortLable').val()
            },
            success: function (data, evt) {
                jAlert('<font color=red>更新成功!</font>', '提示信息', function () {
                    document.location.reload();
                });
            }
        })
        $.unblockUI();
    }

    function updateOffLineScheduleItem() {
        if (checkoffLineCourse()) {
            jAlert("<font color=red>更新成功!</font>", "提示", function () {
                $('#form3').submit();
                $.unblockUI();
            });
        }
    }

    function editSchedule_selectTeacher() {
        var iframeContent = "<iframe height='355' scrolling='auto' width='100%' class='pop_iframe' src='../tchrBaseInfo/listAllTeacher.do?fromSchedule=true&step=-1&teacherSourceOutSide=2'> </iframe>";
        $('#editOffLineItem #updateScheduleItem').css('display', 'none');
        $('#teacherSelect').html(iframeContent).css("display", "block");
    }

    function _selectTeacher() {

        $.showSelectableTeacher({
            "actionForSubmit": function (data) {
                data = data.teacherList[0];
                $('#teacherName').val(data.teacherName);
                $('#teacherId').val(data.tenantId);
                $.closeTeacherModal();
            }
        });
    }

    function _selectCourse1(trainWay) {

        $.showSelectableCourse({
            "actionForSubmit": function (data) {
                data = data.courseList;

                var courseName = "";
                var courseId = "";
                for (var i = 0; i < data.length; i++) {
                    courseName += data[i].courseName + ",";
                    courseId += data[i].courseId + ",";
                }
                $('#courseName').val(courseName);
                $('#courseId').val(courseId);
                $.closeCourseModal();
            }
        });
    }


    function _selectCourse2(trainWay) {

        $.showSelectableCourse({
            "actionForSubmit": function (data) {
                data = data.courseList;

                var courseName = "";
                var courseId = "";
                for (var i = 0; i < data.length; i++) {
                    courseName += data[i].courseName + ",";
                    courseId += data[i].courseId + ",";
                }
                $('#onLineCourseName').val(courseName);
                $('#courseId').val(courseId);
                $.closeCourseModal();
            }
        });
    }


    function manageMySelectCourse(step, trainWay) {
        var selectedCourseIdStr = "";
        if (step == 0){
            selectedCourseIdStr = $("#courseId").val();
        } else if (step == -1){
            selectedCourseIdStr = top.$('#editOnLineItem').find('#editItem_courseId').val();
        }
        if (selectedCourseIdStr != "") {
            var temp = $top - $top / 8;
            var iframeContent = "<iframe height='" + temp + "' scrolling='auto' width='100%' class='pop_iframe' src='../courseCourseType/listMySelectCourseToSchedule.do?step=" + step + "&trainWay=" + trainWay + "&courseIdStr=" + selectedCourseIdStr + "'> </iframe>"
            $("#isMySelectCourse").html(iframeContent);
            $.blockUI({message: $("#mySelectCourse"), css: {width: '90%', top: '5%', left: '5%'}});
            $("a.pop_close_btn").click(function () {
                $.unblockUI();
            });
        } else{
            alert("当前没有指定任何课程，请先指定！");
        }
    }

    function _concelSelectCourse(trainWay) {
        if (trainWay == 0) {
            $('#courseName').val("");
            $('#courseId').val("");
        } else if (trainWay == 2) {
            $('#onLineCourseName').val("");
            $('#courseId').val("");
        }
        $.post("../courseCourseType/clearMySelectCourseSession.do");
        alert("清除成功！");
    }

    function addOnLineScheduleItem(typeId) {
        var trainEndTime = $('#_trainEndDate').val();
        var inputDate = $('#scheduleEndDate').val();
        var scheduleStartDate = $('#scheduleStartDate').val();
        var b = (Date.parse(scheduleStartDate) - Date.parse(inputDate)) / 3600 / 1000;

        var a = (Date.parse(inputDate) - Date.parse(trainEndTime)) / 3600 / 1000;
        if (a > 0) {
            jAlert("您选择的截止日期已大于培训结束的日期[" + trainEndTime + "]，请重新选择，谢谢！");
            return false;
        }
        if (b > 0) {
            jAlert("您选择的开始日期已大于截止的日期[" + trainEndTime + "]，请重新选择，谢谢！");
            return false;
        }
        if (typeId == 0 && $('#courseName').val() != "" || typeId == 2 && $('#onlineSchedule').find('#onLineCourseName').val() != "") {
            $.post("../mtMixTrainSchedule/forAddOnLineScheduleItemInfo.do",
                {
                    scheduleStartDate: typeId == 2 ? $('#onlineSchedule').find('#scheduleStartDate').val() : $('#scheduleStartDate').val(),
                    scheduleEndDate: typeId == 2 ? $('#onlineSchedule').find('#scheduleEndDate').val() : $('#scheduleEndDate').val(),
                    courseName: typeId == 2 ? $('#onlineSchedule').find('#onLineCourseName').val() : $('#courseName').val(),
                    courseId: typeId == 2 ? $('#onlineSchedule').find('#courseId').val() : $('#courseId').val(),
                    sortLable: typeId == 2 ? $('#onlineSchedule').find('#sortLable').val() : $('#sortLable').val()
                },
                function (data) {
                    jAlert("<font color=red>添加成功!</font>", "提示", function () {
                        document.location.reload();
                    });
                }
            )
        } else {
            jAlert("<font color=red>课程不能为空！</font>", "提示");
        }
    }

    function addOffLineScheduleItem(typeId) {
        if (checkCourse()) {
            jAlert("<font color=red>添加成功!</font>", "提示", function () {
                $('#form1').submit();
            });
        }
    }

    function checkCourse() {
        if ($('#form1').find('#courseName').val() != "") {
            if ($('#isTrainTheme').val() == 1) {
                var flag = window.confirm('新添加内容是培训主题，系统将自动将其添加至课程库，是否继续？');
                return flag;
            } else
                return true;
        }
        else if ($('#form1').find('#courseName').val() == "") {
            jAlert("<font color=red>内容不能为空！</font>", "提示");
            return false;
        }
    }

    function checkoffLineCourse() {
        if ($('#form3').find('#editItem_courseName').val() != ""){
            return true;
        } else if ($('#form3').find('#editItem_courseName').val() == "") {
            jAlert("<font color=red>课程不能为空！</font>", "提示");
            return false;
        }
    }

    function resetScheduleForm() {
        var flag = confirm("确定要重置吗？");
        if (flag == true) {
            document.location.href = "../mtMixTrainSchedule/resetScheduleForm.do";
        }
    }

    function judgeEndDate(inputDate) {
        var trainEndTime = $('#_trainEndDate').val();
        var a = (Date.parse(inputDate) - Date.parse(trainEndTime)) / 3600 / 1000;
        if (a > 0) {
            alert("您选择的日期已大于培训结束的日期[" + trainEndTime + "]，请重新选择，谢谢！");
            $('#scheduleEndDate').focus();
            $('#scheduleEndDate').val("");
            $('#scheduleDate').val("");
        }
    }
</script>
</body>
</html>