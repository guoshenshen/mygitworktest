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
<!-- InstanceBegin template="/Templates/admin_xy.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="培训;在线培训;网络培训" name="keywords"/>
    <!-- InstanceBeginEditable name="doctitle" -->
    <title><%=Constants.systemName%>
    </title>
    <SCRIPT language="JavaScript" src="/js/widget/window.js"></SCRIPT>
    <script type="text/javascript" src="/js/dojojs/dojo.js"></script>
    <script type="text/javascript"> dojo.require("dojo.widget.*");</script>
    <!-- InstanceEndEditable -->
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/demos.css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript" src="/js/dojojs/dojo.js"></script>
    <script type="text/javascript" src="/js/jquery-json.js"></script>
    <script language="javascript" src="/js/jquery.cookie-min.js"></script>
    <script type="text/javascript" src="/js/jquery.blockUI.js"></script>
    <script type="text/javascript" src="/js/common/tools.js"></script>
    <script type="text/javascript" src="/js/common/home.js"></script>
    <!-- <script type="text/javascript" src="./js/jquery-latest.js"></script> -->
    <script type="text/javascript" src="/js/nav/amenu.js"></script>
    <script type="text/javascript" src="/js/nav/roll.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/JSCommonTools.js"></script>
    <script type="text/javascript" src="/js/jquery.uploadify.js"></script>
    <script type="text/javascript" src="/js/jquery.uploadifive.js"></script>
    <script type="text/javascript" src="/js/aTool.js"></script>

    <style type="text/css">
        .searchCourse {
            position: relative;
            float: right;
            padding-top: 16px;
            padding-bottom: 0px;
            padding-right: 80px;
        }
    </style>

</head>
<body style="background-color:#F6F6F6" class="admin">
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
                <c:if test="${courseFormList != null}">
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
                                       onclick="window.open('../courseStudy/previewStudy.do?courseID=${courseForm.secondCourseId}')">预览</a>
                                </c:if>
                                <c:if test="${courseForm.enterUrl == '' }">预览</c:if>
                                <a href="javascript:void(0);"
                                   onclick='javascript:concelSelectCourse(${courseForm.course_id },"${courseForm.courseName }",${step },${trainWay })'>
                                    删除
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </form>
    </div>
    <div>
        <div>
            <a href="javascript:void(0);" onclick="javascript:_selectCourse(${step },${trainWay});" class="btn-orange-l">
                <span class="btn-orange-r">继续指定</span>
            </a>
            <c:if test="${ step == -1 }">
                <a href="javascript:void(0);" onclick="javascript:closeCourse();" class="btn-orange-l">
                    <span class="btn-orange-r">关&nbsp;&nbsp;闭</span>
                </a>
            </c:if>
        </div>
    </div>
</div>
<!--首页 内容 end -->
<div class="mainHidden">
    <input type="hidden" name="convertFlag" value="${convertFlag}"/>
</div>
<div id="mySelectCourse" style="display:none;cursor:default;overflow:hidden;">
    <div id='_pop_win'><h2>已指定课程列表<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2></div>
    <div id="isMySelectCourse"></div>
    <input type="hidden" id="courseId" value=""/>
</div>
</body>
<script type="text/javascript">

    function _setSelected(id, type) {
        var chechTemp = id.checked;
        with (document.form3) {
            for (var j = 0; j < elements.length; j++) {
                if (elements[j].type == "checkbox" && elements[j].name == type) {
                    if (chechTemp) {
                        document.form3.elements[j].checked = true;
                    } else {
                        document.form3.elements[j].checked = false;
                    }
                }
            }
        }
    }

    function _selectCourse(stepId, trainWay) {

        $.showSelectableCourse({
            "actionForSubmit": function (data) {
                data = data.courseList;
                var courseNameArr = new Array();
                var courseIdArr = new Array();
                for (var i = 0; i < data.length; i++) {
                    courseNameArr += data[i].courseName + ",";
                    courseIdArr += data[i].courseId + ",";
                }
                $('#onLineCourseName').val(courseNameArr);
                $('#courseId').val(courseIdArr);
                if (stepId == 0 && trainWay == 0) {
                    top.$('#courseName').val(top.$('#courseName').val() + courseNameArr);
                    top.$('#courseId').val(top.$('#courseId').val() + courseIdArr);
                    top.$.unblockUI();

                } else if (stepId == 0 && trainWay == 2) {
                    top.$('#onLineCourseName').val(top.$('#onLineCourseName').val() + courseNameArr);
                    top.$('#courseId').val(top.$('#courseId').val() + courseIdArr);
                    top.$.unblockUI();
                } else if (stepId == -1) {
                    top.$('#editOnLineItem').find('#editItem_courseName').val(top.$('#editOnLineItem').find('#editItem_courseName').val() + courseNameArr);
                    top.$('#editOnLineItem').find('#editItem_courseId').val(top.$('#editOnLineItem').find('#editItem_courseId').val() + courseIdArr);
                }

            }
        });
    }

    function closeCourse() {
        top.$('#editOnLineItem').find('#courseSelect').css("display", "none");
        top.$('#editOnLineItem').find('#updateScheduleItem').css('display', 'block');
    }

    function concelSelectCourse(cid, cname, step, trainWay) {
        if (step == 0 && trainWay == 0) {
            top.$('#courseName').val(top.$('#courseName').val().replace(cname + ",", ""));
            top.$('#courseId').val(top.$('#courseId').val().replace(cid + ",", ""));
        } else if (step == 0 && trainWay == 2) {
            top.$('#onLineCourseName').val(top.$('#onLineCourseName').val().replace(cname + ",", ""));
            top.$('#courseId').val(top.$('#courseId').val().replace(cid + ",", ""));
        } else if (step == -1) {
            top.$('#editOnLineItem').find('#editItem_courseName').val(top.$('#editOnLineItem').find('#editItem_courseName').val().replace(cname + ",", ""));
            top.$('#editOnLineItem').find('#editItem_courseId').val(top.$('#editOnLineItem').find('#editItem_courseId').val().replace("," + cid, ""));
        }
        var remainCourseNum = $("#mySelectCourseSize span").html();
        if (remainCourseNum == 1) {
            top.$('#courseName').val("");
            top.$('#onLineCourseName').val("");
            top.$('#editOnLineItem').find('#editItem_courseName').val("");
            top.$('#courseId').val("");
            top.$('#editOnLineItem').find('#editItem_courseId').val("");
            top.$('#editOnLineItem').find('#editItem_courseId').val("");
            $.post("../courseCourseType/clearMySelectCourseSession.do");
        }
        document.location.href = "../courseCourseType/deleteMySelectCourseToSchedule.do?step=" + step + "&trainWay=" + trainWay + "&courseId=" + cid;
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