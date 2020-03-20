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
    <title></title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/public/material.css"/>
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.navigation.css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

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
<body class="admin" id="trainExamFrame">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <input id="defaultPageSize" type="hidden" value="${defaultPageSize}" />
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="funcCon" class="trainApp"></div>
            <div class="hiddenInfo">
                <input type="hidden" name="trainId" value="${train.ID }"/>
            </div>
            <div class="homezoneall">
                <div class="homezonecontent" style="margin-top:0px">
                    <div id="content_single">
                        <div class="headtitle">
                            <img hspace="4" align="absmiddle" src="/image/folder_table.png"/>
                            考试列表
                        </div>
                        <div class="cls"></div>
                    </div>

                    <div class="homezonecontent">
                        <div class="condition" style="width:100%;">
                            <form method="post" id="form5" name="form5" action="" class="form-inline">
                                <div class="form-group" style="text-align: right;width:100%;">
                                    <div class="col-sm-12">
                                        <label class="control-label">考试类型</label>
                                        <select name="examType" class="form-control " id="examType">
                                            <option value="2">所有</option>
                                            <option value="0" <c:if test="${examType == 0}">selected</c:if> >线上</option>
                                            <option value="1" <c:if test="${examType == 1}">selected</c:if> >线下</option>
                                        </select>
                                        <input class="form-control" id="examTitleContent" type="text" name="examTitleContent"
                                               value="${examTitleContent}" size="15" class="form-control" placeholder="考试名称"/>
                                        <button type="button" class="btn btn-primary" id="searchTrainID" style="letter-spacing:5px;text-indent:5px">查询</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <form id="form1" name="form1" method="post">
                            <table id="examTableId" class="table table-striped table-bordered batchOperation">
                                <tr class="tableTh">
                                    <th align="center">
                                        <input type="checkbox" id="selectAll" name="selectAll" onClick="_setSelected(this,'selectbox','form1')"/>
                                    </th>
                                    <th align="center"> 考试名称</th>
                                    <th> 曝光时间</th>
                                    <th> 答卷管理</th>
                                    <th> 成绩管理</th>
                                    <th> 报名审批</th>
                                    <th> 状态</th>
                                    <th> 操作</th>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <div class="condtion">
                        <ul class="pagination-admin" style="float:right"></ul>
                        <div style="clear:both"></div>
                    </div>
                    <div id="content_03">
                        <div class="container">
                            <button type="button" onclick="javascript:window.location.href='../examManage/toCreateExam.do?examForTrainFlag=1'" class="btn btn-primary">
                                新建考试
                            </button>
                            <button type="button" onclick="javascript:deleteExam();" class="btn btn-danger">删除</button>
                        </div>
                        <div class="clr"></div>
                    </div>
                </div>
                <div class="clr"></div>
            </div>
        </div>
    </div>
</div>
<div class="bottombody"></div>

<!-- 考试人员指派界面 -->
<div class="assignExamUser userSelectPanel" data-remodal-id="assignExamUser" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div id="assignExamUserContainer" class="userListFrame userSelectStatus" style="border-bottom:none">
        <button data-remodal-action="close" class="remodal-close"></button>
        <div class="box">
            <div class="btn-wrapper;">
                <div class="tools">
                    <span class="selectAll"><input type="checkbox">全选</span>
                    <span class="search">
						<input id="search-info" name="search" value="" type="text" placeholder="姓名|单位|邮箱">
						<a href="javascript:void(0);" class="search-icon"><img src="/image/search01.png" alt="搜索"></a>
					</span>
                    <span class="search-info-tip">&nbsp;</span>
                </div>
            </div>
        </div>
        <div class="userSelectTab userList-wrapper forUserSelect list-wrapper ">
            <table class="usersTable standardUserListTable standardTable" id="attendedUsers">
                <thead>
                <tr class="title">
                    <th class="check"></th>
                    <th>姓名</th>
                    <th>所属单位</th>
                    <th>邮箱</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <ul class="vertical-menu float_vertical_menu">
            <li class="green" title="站内派送" width="33.4%" id="dispatch">
                <span class="dispatch-icon"></span>
                <a href="javascript:void(0);"><span>站内派送</span></a>
            </li>
            <li class="orange" title="邮件派送" width="33.4%" id="mailDispatch">
                <span class="mail-icon"></span>
                <a href="javascript:void(0);"><span>邮件派送</span></a>
            </li>
            <li class="red" title="取消指派" width="33.4%" id="removeDispatch">
                <span class="delete-icon"></span>
                <a href="javascript:void(0);"><span>取消派送</span></a>
            </li>
        </ul>
    </div>
</div>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<%--<script language="javascript" src="/js/pagination.js"></script>--%>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/widget/classify.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/common/select.js"></script>

<script language='javascript'>
    $(function(){
        examList();
    });
    function examList(){

        //1.获取分页工具包
        var paginationTool=$.getPaginationConfig();

        //2.针对某分页任务，就该任务的逻辑覆盖分页工具包中的方法
        var extraPaginationConfig= {
            getUrlForPagination:function(){return "../examManage/searchExamList.do"}, //分页器url，访问该url，可获取分页及某页的记录

            //清空之前加载的记录（加载新的分页结果，需要执行该方法）
            actionForClearLoadedData:function(){
                $("#examTableId .infoRow").remove();
            },
            actionForSucessLoadingData:function(data){
                var dataList = data.list;
                var dataLength = dataList.length;
                var htmlArray = new Array();

                if(dataList != null && dataLength > 0){
                    for(var i = 0; i < dataLength ; i++){
                        var exam = dataList[i];
                        htmlArray.push("<tr class='infoRow' id='tr_"+exam.id+"' >");

                        htmlArray.push("<td>");
                        if(exam.examType == 0){
                            if(exam.isPublish == 0){
                                htmlArray.push("<input type='checkbox' name='selectbox' value='"+exam.id+"' name='examId'/>");
                            }else if(exam.isPublish == 1){
                                htmlArray.push("<input type='checkbox' name='examId' value='"+exam.id+"'disabled/>");
                            }
                        }else if(exam.examType == 1){
                            htmlArray.push("<input type='checkbox' name='selectbox' value='"+exam.id+"'/>");
                        }

                        htmlArray.push("</td>");

                        htmlArray.push("<td width='140px' align='center'>");
                        htmlArray.push("<a href='../examManage/viewExamInfoAdmin.do?eid="+exam.id+"&examForTrainFlag=1' title='"+exam.examTitle+"'>");
                        var examTitle = exam.examTitle;

                        if(examTitle.length>10){
                            examTitle = examTitle.substring(0,9)+"...";
                        }else{
                            examTitle
                        }
                        htmlArray.push(examTitle);
                        htmlArray.push("</a>");
                        htmlArray.push("</td>");

                        htmlArray.push("<td>");
                        htmlArray.push(exam.startTime)
                        htmlArray.push("~")
                        htmlArray.push(exam.endTime)
                        htmlArray.push("</td>");

                        htmlArray.push("<td>");
                        htmlArray.push("&nbsp;<a href='../examResultManage/toOnlinePaperReading.do?eid="+exam.id+"&examForTrainFlag=1'>阅卷</a>");
                        htmlArray.push("</td>");

                        htmlArray.push("<td>");
                        htmlArray.push("<a href='../examResultManage/examScores.do?examForTrainFlag=1&eid="+exam.id+"'>成绩管理</a>");
                        htmlArray.push("&nbsp;("+${joinCount }+"人参加 "+exam.finishCount+"人完成)");
                        htmlArray.push("</td>");

                        htmlArray.push("<td>");
                        htmlArray.push("<a href='../examReg/toExamRegAudit.do?examForTrainFlag=1&eid="+exam.id+"&pageNo=1'>报名审批</a>");

                        if(exam.regCount != null){
                            if(exam.regCount != 0){
                                htmlArray.push("(<font color='red'>"+exam.regCount+"</font>人)");
                            }
                        }
                        htmlArray.push("</td>");

                        htmlArray.push("<td>");
                        if(exam.examType == 0){
                            if(exam.isPublish == 0){
                                htmlArray.push("未发布");
                            }else if(exam.isPublish == 1){
                                htmlArray.push("<font color=\"red\">已发布</font>");
                            }
                        }
                        htmlArray.push("</td>");

                        htmlArray.push("<td>");
                        if(exam.examType == 0){
                            if(exam.isPublish == 0){
                                htmlArray.push("<a href='../examInfo/toEditExamInfoById.do?examForTrainFlag=1&eid="+exam.id+"'>修改</a>&nbsp;");
                                if(exam.paperId != null && exam.paperId != ""){
                                    htmlArray.push("| <a href='javascript:void(0);' onclick='publishExam("+exam.id+",1)'>发布</a>");
                                }
                            }else if(exam.isPublish == 1){
                                if(exam.paperId != null){
                                    htmlArray.push("<a href='javascript:previewPaper("+exam.paperId+")'>预览试卷</a> |");
                                    htmlArray.push("<a href='javascript:void(0);' onclick='publishExam("+exam.id+",0)'>取消发布</a>|");
                                    htmlArray.push("<a href='javascript:void(0);' class='assignExam'>考生指派</a>");
                                }
                            }
                        }else if(exam.examType == 1){
                            htmlArray.push("<a href='../examInfo/toEditExamInfoById.do?examForTrainFlag=1&eid="+exam.id+"'>修改</a>&nbsp;");
                        }
                        htmlArray.push("</td>");

                        htmlArray.push("</tr>");

                        $("#examTableId .infoRow").remove();
                        $("#examTableId").append(htmlArray.join(""));
                    }
                } else{
                    /*当返回结果没有数据的时候,这个方法就进不来,所以这个else基本不用判断了*/
                    htmlArray.push("<tr class='infoRow'>");
                    htmlArray.push("<td colspan='8' align='left'>暂无数据</td>");
                    htmlArray.push("</tr>");

                    $("#examTableId .infoRow").remove();
                    $("#examTableId").append(htmlArray.join(""));
                }
            }
            //将读取的分页记录组装成html并加载到页面
        };

        //将任务逻辑注入分页工具包
        $.extend(paginationTool,extraPaginationConfig);
        //恢复分页器默认设置
        paginationTool.resetSearchInfo();

        //向分页器传递查询条件
        paginationTool.searchInfo.examType = $("#examType").val();
        paginationTool.searchInfo.examTitleContent = $("#examTitleContent").val();

        //触发分页器
        paginationTool.actionForLoadingPagination();

        $(".mainbody").on("click","#searchTrainID",function(){

            paginationTool.searchInfo.examType = $("#examType").val();
            paginationTool.searchInfo.examTitleContent = $("#examTitleContent").val();
            paginationTool.actionForLoadingPagination();
        })
    }
</script>

<script language='javascript'>
    function publishExam(eid, type) {
        if (type == 1) {
            jConfirm('确定发布考试？', '提示', function (r) {
                if (r) {
                    window.location.href = "../examInfo/publishExam.do?examForTrainFlag=1&eid=" + eid;
                }
            });
        } else {
            jConfirm('取消发布考试？', '提示', function (r) {
                if (r) {
                    window.location.href = "../examInfo/publishExam.do?examForTrainFlag=1&eid=" + eid;
                }
            });
        }
    }

    function deleteExam() {
        var tagcheck = 0;
        var formElement = document.getElementById("form1");
        var eles = formElement.elements;

        for (var j = 0; j < eles.length; j++) {
            if (eles[j].type == "checkbox" && eles[j].name == "selectbox") {
                if (eles[j].checked) {
                    tagcheck = 1;
                    break;
                }
            }
        }

        if (tagcheck == 1) {
            jConfirm('确认删除选定的考试？', '提示', function (r) {
                if (r) {
                    document.form1.action = '../examManage/delExamInfoByIds.do?examForTrainFlag=1';
                    document.getElementById('form1').submit();
                } else {
                    return;
                }
            });
        } else {
            jAlert("请选择要操作的考试！", '填写提示');
        }
    }

    function previewPaper(paperId) {
        var pop_url = "../paperManage/previewTestPaper.do?paperId=" + paperId;
        var pop = $("<div id='_pop_win'><h2>试卷预览"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='450' width='900' scrolling='auto' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {
                width: "900px",
                height: "480px",
                cursor: "default",
                left: ($left - 900) / 2 - 10,
                top: ($top - 400) / 2 - 10
            }
        });
    }
</script>
<c:if test="${alertString != null}">
    <script type="text/javascript">
        jAlert("${alertString}", '');
    </script>
</c:if>
<script type="text/javascript">
    $(function () {
        var $assignExamUser = $("[data-remodal-id=assignExamUser]").remodal($.defaultRemodalOption());
        var currentInfo = {
            eid: ""
        };

        $("#trainExamFrame").on("click", ".assignExam", function () {
            var eid = $(this).parents("tr").find("input[name=examId]").val();
            currentInfo.eid = eid;
            $assignExamUser.open();
        });

        function loadStatisticsTip() {
            var $ToolWidget = $(".userListFrame .tools");
            var allRow = $(".userListFrame table.currentTable tr").length - 1;
            var unselectRow = $(".userListFrame table.currentTable tr.notShow").length
            var selectRow = allRow - unselectRow;
            if (allRow <= 0) {
                $(".search-info-tip", $ToolWidget).hide();
            } else {
                $(".search-info-tip", $ToolWidget).html(selectRow + "/" + allRow).attr("title", "当前列表人数" + selectRow).show();
            }
        }

        $(document).on('opening', '[data-remodal-id=assignExamUser]', function () {
            var $trainId = $(".hiddenInfo input[name=trainId]");
            $.ajax({
                type: "post",
                data: {"trainId": $trainId.val()},
                url: "../mtMixTrainUserTrainInfo/getAttendedList.do",
                dataType: "json",
                success: function (data) {
                    if (data.status) {
                        var userList = data.data.userList;
                        var length = userList.length;
                        var userListArray = new Array();
                        for (var i = 0; i < length; i++) {
                            var currentUser = userList[i];
                            if (i % 2 == 0) {
                                userListArray.push("<tr class='even'>");
                            } else {
                                userListArray.push("<tr class='odd'>");
                            }
                            userListArray.push("<td><input type='checkbox' name='operatorId' value='" + currentUser.operatorId + "'></td>");
                            userListArray.push("<td>" + currentUser.operatorName + "</td>");
                            userListArray.push("<td>" + currentUser.orgName + "</td>");
                            userListArray.push("<td>" + currentUser.email + "</td>");
                            userListArray.push("</tr>");
                        }
                        $("#attendedUsers tbody").empty().append(userListArray.join(""));
                        loadStatisticsTip();
                    }
                }
            })
        });

        var getSelectedUsers = function () {
            var selectedUsers = new Array();
            $("#attendedUsers input[type=checkbox]:checked").each(function (index, that) {
                selectedUsers.push(that.value);
            })
            return selectedUsers;
        };

        $("#removeDispatch").click(function () {
            var selectedUsers = getSelectedUsers();

            if (selectedUsers.length == 0) {
                $.tips("您尚未指定考试派送对象", "系统提示");
            } else {
                $.ajax({
                    traditional: true,
                    data: {"operatorId": selectedUsers, "eid": currentInfo.eid},
                    url: "../examUser/removeExamUser.do",
                    dataType: "json",
                    type: "post",
                    success: function (data) {
                        if (data.status) {
                            $.tips("考试指派对象删除成功", "系统提示");
                        } else {
                            $.tips("考试指派对象删除失败", "系统提示");
                        }
                    },
                    error: function () {
                        $.tips("考试指派对象删除失败，请稍后重试", "系统提示");
                    }
                })
            }
        });
        $("#mailDispatch").click(function () {
            var selectedUsers = getSelectedUsers();

            if (selectedUsers.length == 0) {
                $.tips("您尚未指定考试派送对象", "系统提示");
            } else {
                $.ajax({
                    traditional: true,
                    data: {"operatorId": selectedUsers, "eid": currentInfo.eid},
                    url: "../examUser/insertExamUser.do",
                    dataType: "json",
                    type: "post",
                    success: function (data) {
                        if (data.status) {
                            $.ajax({
                                traditional: true,
                                url: "../examManage/sendExamNoticetoUser.do",
                                data: {"operatorId": selectedUsers, "eid": currentInfo.eid},
                                dataType: "json",
                                type: "post",
                                success: function (data) {
                                    if (data.status) {
                                        $.tips("考试通知派送成功", "系统提示");
                                    }
                                }
                            })
                        } else {
                            $.tips("考试通知派送失败", "系统提示");
                        }
                    },
                    error: function () {
                        $.tips("考试通知派送失败，请稍后重试", "系统提示");
                    }
                })
            }
        });

        $("#dispatch").click(function () {
            var selectedUsers = getSelectedUsers();

            if (selectedUsers.length == 0) {
                $.tips("您尚未指定考试派送对象", "系统提示");
            } else {
                $.ajax({
                    traditional: true,
                    data: {"operatorId": selectedUsers, "eid": currentInfo.eid},
                    url: "../examUser/insertExamUser.do",
                    dataType: "json",
                    type: "post",
                    success: function (data) {
                        if (data.status) {
                            $.tips("考试指派成功", "系统提示");
                        } else {
                            $.tips("考试指派失败", "系统提示");
                        }
                    },
                    error: function () {
                        $.tips("考试指派失败，请稍后重试", "系统提示");
                    }
                })
            }
        });
    });
</script>
</body>
</html>
