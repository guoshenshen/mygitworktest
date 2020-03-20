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
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="培训;在线培训;网络培训" name="keywords"/>
    <!-- InstanceBeginEditable name="doctitle" -->
    <title><%=Constants.systemName%>
    </title>
    <SCRIPT language="JavaScript" src="/js/widget/window.js"></SCRIPT>
    <script type="text/javascript" src="/js/dojojs/dojo.js"></script>
    <script type="text/javascript"> dojo.require("dojo.widget.*");</script>

    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/common/tools.js"></script>
    <script type="text/javascript" src="/js/common/home.js"></script>
    <script type="text/javascript" src="/js/dojojs/dojo.js"></script>
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript" src="/js/jquery-json.js"></script>
    <script type="text/javascript" src="/js/nav/menu.js"></script>
    <script type="text/javascript" src="/js/nav/roll.js"></script>
    <script language="javascript" src="/js/jquery.cookie-min.js"></script>
    <script type="text/javascript" src="/js/jquery.blockUI.js"></script>

    <style type="text/css">
        .condition {
            margin: 0;
            padding: 0;
            min-height: 37px;
            float: left;
            width: 940px;
        }

        .condition h3 {
            float: left;
            color: white;
            background: url("/image/student/01_arrow.png") no-repeat;
            padding: 1px 25px 5px 10px;
            margin: 2px 0 10px;
            font-size: 13px;
            width: 80px;
            text-align: left;
            line-height: 25px;
        }

        .condition a:link, .condition a:visited {
            color: #3091B5;
            font-family: "Microsoft Yahei";
            font-size: 12px;
            margin-bottom: 5px;
            text-decoration: none;
            line-height: 25px;
        }

        .searchCourse {
            position: relative;
            float: right;
            padding-top: 16px;
            padding-bottom: 0px;
            padding-right: 80px;
        }
    </style>

</head>
<body style="background-color:#F6F6F6">
<input value="${step} " type="hidden" id="operatorFlag"/>
<div id="mySelectCourseSize" style="font-size:14px;margin:5px 0px 5px 15px;font-family:'微软雅黑'">
    当前共指定<font color='green' size='14px'><span>${courseSize }</span></font>门课程
    <c:if test="${noEnterUrlNum > 0}">
        ，其中有<font color='red' size='14px'>${noEnterUrlNum }</font>门课程无课件链接地址，我们建议您删除，否则学员无法学习
    </c:if>
    <c:if test="${noEnterUrlNum == 0}">
        ，课件状态全部正常可使用
    </c:if>
</div>
<div class="homezoneall" style="border:0px">
    <div id="content_02">
        <form id="form3" name="form3" action="" method="post">
            <table class="homecontenttable homezonecontentborder">
                <tr class="tableTh">
                    <th width="16%"> 课程名称</th>
                    <th width="8%"> 主讲人</th>
                    <th width="10%"> 适用岗位</th>
                    <th width="10%"> 专业领域</th>
                    <th width="10%"> 关键词</th>
                    <th width="10%"> 所属机构</th>
                    <th width="10%">公开状态</th>
                    <th width="8%">课件状态</th>
                    <th width="12%"> 操作</th>
                </tr>
                <c:if test="${courseFormList != null }">
                    <c:forEach var="courseForm" items="${courseFormList}">
                        <tr>
                            <td align='center'>${courseForm.courseName }</td>
                            <td align='center'>${courseForm.creator }</td>
                            <td align='center'>${courseForm.courseStations }</td>
                            <td align='center'>${courseForm.expertAreas }</td>
                            <td align='center'>${courseForm.keyWords }</td>
                            <td align='center'>${courseForm.orgName }</td>
                            <td align='center'>
                                <c:if test="${courseForm.openScope == 2201 }">不公开</c:if>
                                <c:if test="${courseForm.openScope == 2202 }">本单位公开</c:if>
                                <c:if test="${courseForm.openScope == 2203 }">本机构公开</c:if>
                                <c:if test="${courseForm.openScope == 2204 }">全院公开</c:if>
                                <c:if test="${courseForm.openScope == 2205 }">完全公开</c:if>
                            </td>
                            <td align='center'>
                                <c:if test="${courseForm.enterUrl != '' }">
                                    <img src="/image/greenStatus.png" style="vertical-align:top;"/>可使用
                                </c:if>
                                <c:if test="${courseForm.enterUrl == '' }">
                                    <img src="/image/redStatus.png" style="vertical-align:top;"/>不可用
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${courseForm.enterUrl != '' }">
                                    <a href="javascript:void(0);"
                                       onclick="window.open('../courseStudy/previewStudy.do?courseID=${courseForm.course_id}')">预览</a>
                                </c:if>
                                <c:if test="${courseForm.enterUrl == '' }">预览</c:if>
                                <a href="javascript:void(0);"
                                   onclick='javascript:concelSelectCourse(${courseForm.course_id },"${courseForm.courseName }",${step })'>删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </form>
    </div>
    <div>
        <div>
            <a href="javascript:void(0);" onclick="javascript:continueSelectCourse();" class="btn-orange-l">
                <span class="btn-orange-r">继续指定</span>
            </a>
            <logic:equal name="step" value="-1">
                <a href="javascript:void(0);" onclick="javascript:closeCourse();" class="btn-orange-l">
                    <span class="btn-orange-r">关&nbsp;&nbsp;闭</span>
                </a>
            </logic:equal>
        </div>
    </div>
</div>
<!--首页 内容 end -->
<div class="mainHidden">
    <input type="hidden" name="convertFlag" value="${convertFlag}"/>
</div>
</body>

<script type="text/javascript">

    function continueSelectCourse() {
        var $left = $(top.window).width();
        var $top = $(top.window).height();
        var temp = $top - $top / 8;
        var iframeContent = "<iframe height='" + temp + "' scrolling='auto' width='100%' class='pop_iframe' src='../courseCourseType/listAllCourseToSetRelativity.do?fromRelaCourse=true&step=0'> </iframe>"
        top.$("#add_courseSelect").html(iframeContent);
        top.$.blockUI({message: top.$("#_courseCenter"), css: {width: '90%', top: '5%', left: '5%'}});
        $.unblockUI();
        top.$("a.pop_close_btn").click(function () {
            top.$.unblockUI();
        });
    }

    function closeCourse() {
        top.$('#editOnLineItem').find('#courseSelect').css("display", "none");
        top.$('#editOnLineItem').find('#updateScheduleItem').css('display', 'block');
    }

    function concelSelectCourse(cid, cname, step) {
        if (step == 0) {
            top.$('#onLineCourseName').val(top.$('#onLineCourseName').val().replace(cname + ",", ""));
            top.$('#rele_courseIds').val(top.$('#rele_courseIds').val().replace("," + cid, ""));
        } else if (step == -1) {
            top.$('#editOnLineItem').find('#editItem_courseName').val(top.$('#editOnLineItem').find('#editItem_courseName').val().replace(cname + ",", ""));
            top.$('#editOnLineItem').find('#editItem_courseId').val(top.$('#editOnLineItem').find('#editItem_courseId').val().replace("," + cid, ""));
        }
        var remainCourseNum = $("#mySelectCourseSize span").html();
        if (remainCourseNum == 1) {
            top.$('#courseName').val("");
            top.$('#onLineCourseName').val("");
            top.$('#editOnLineItem').find('#editItem_courseName').val("");
            top.$('#rele_courseIds').val("");
            top.$('#editOnLineItem').find('#editItem_courseId').val("");
            top.$('#editOnLineItem').find('#editItem_courseId').val("");
            $.post("courseCoursetype.do?method=clearMySelectCourseSession");
        }
        document.location.href = "../courseCourseType/deleteMySelectCourseToSchedule.do?step=" + step + "&courseId=" + cid + "&relevantCoursePageFlag=1";
    }

    var flag = null;
    $(function () {
        //flag=setInterval('getLatestCourseListConvertStatus()',6000);
        var operatorFlag = $('#operatorFlag').val();
        if (operatorFlag == -1) {
            $('.homezoneall').css('background-color', '#F5F5F5');
            $('#content_02').find('.tableTh').css('background-color', '#F5F5F5');
        }

        $('#content .selected-condition').find('ul').find('li').on('click', function () {
            var $this = $('#content .selected-condition').find('ul');
            var searchConditionStr = "";
            $(this).remove();
            $this.find('li').each(function () {
                searchConditionStr = searchConditionStr + $(this).attr("id") + "-";
            })
            $('#conditionString').val(searchConditionStr);
            $('#form3').submit();
        }).each(function () {
            var idStr = $(this).attr("id");
            var selectId = idStr.substring(0, idStr.length);
            $('#a' + selectId).css({'background-color': '#FFEFEF', 'border': '1px solid #C00', 'color': '#C00'});
        })
    })

    function getLatestCourseListConvertStatus() {
        $.ajax({
            url: 'courseCoursetype.do?method=listOffLineAllCourse&timestamp=' + new Date().getTime(),
            type: 'post',
            dataType: 'text',
            data: {
                pageSize: $("select[name=pageSize] option:selected").text(),
                pageNo: $("select[name=pageNo] option:selected").text()
            },
            success: function (data, evt) {
                var convertFlag = $('.mainHidden input').val();
                if (convertFlag == "<%=Constants.VIDEO_CONVERT_STATUS%>") {
                    $('#form3').html($(data).find('#form3').html());
                } else {
                    clearInterval(flag);
                    flag = null;
                }

            }
        })
    }

</script>
</html>