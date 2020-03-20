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
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <!-- InstanceBeginEditable name="main" -->
            <form id="examUserForm" name="examUserForm" action="" method="post">
                <div class="gl_11_3">
                    <span class="gl_11_no" style="margin:-10px -10px 0px -10px;">1、考试基本信息</span>
                    <span class="gl_11_no">2、指定试卷</span>
                    <span class="gl_11_yes">3、安排人员</span>
                    <span class="gl_11_no" style="margin-left:-30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、发布通知</span>
                </div>

                <div id="content_02">
                    <table class="table table-striped table-bordered batchOperation" width="93%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                &nbsp;&nbsp;&nbsp;&nbsp;试卷标题：
                                <font color="red">
                                    ${examInfo.examTitle }
                                </font>&nbsp;&nbsp;<br/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div>
                                    <div class="gl_03" style="overflow:hidden;">
                                        <fieldset class="effectArea" id="deptId">
                                            <legend>单位选择</legend>
                                            <button class="orgSelect btn btn-primary" type="button" style="margin: 20px;">单位选择</button>
                                            <button class="withMsgnum showOrgsPreview btn btn-info" type="button" style="margin: 20px;">单位查看</button>
                                            </span>
                                            <div class="hiddenArea">
                                                <c:if test="${examDeptList != null}">
                                                    <c:forEach var="examDeptForm" items="${examDeptList}" varStatus="status">
                                                        <input type="hidden" name="deptId" value="${examDeptForm.orgId }"/>
                                                    </c:forEach>
                                                </c:if>
                                            </div>
                                            <div class="detailInfoForShow notShow">
                                                <table></table>
                                            </div>
                                        </fieldset>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr><td>&nbsp;&nbsp;</td></tr>
                        <tr><td>&nbsp;&nbsp;</td></tr>
                        <tr>
                            <td>
                                <div>
                                    <div class="gl_03" style="overflow:hidden">
                                        <fieldset class="effectArea" id="uid">
                                            <legend>人员选择</legend>
                                            <button class="userAddMenu  btn btn-primary" type="button" style="margin: 20px;">人员选择</button>
                                            <button class="withMsgnum showUsersPreview btn btn-info" type="button" style="margin: 20px;">人员查看</button>
                                            </span>
                                            <div class="hiddenArea">
                                                <c:if test="${examUserList != null}">
                                                    <c:forEach var="examUserForm" items="${examUserList}" varStatus="status">
                                                        <input type="hidden" name="uid" value="${examUserForm.userId }"/>
                                                    </c:forEach>
                                                </c:if>
                                            </div>
                                            <div class="detailInfoForShow notShow">
                                                <table></table>
                                            </div>
                                        </fieldset>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
            <br/>
            <div class="btnContainer">
                <div class="clearfix">
                    <c:if test="${isPublishFlag != 1}">
                        <a href="../examUser/toPrevious.do" class="btn-blue-l">
                            <span class="btn-blue-r">上一步</span>
                        </a>
                    </c:if>
                    <a href="javascript:examUserDeptInsert();" class="btn-orange-l">
                        <span class="btn-orange-r">下一步</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="bottombody"></div>
<div class="remodal normal noBorder normalModal orgInfo" data-remodal-id="orgInfo" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close"></button>
    </div>
    <div class="wrapper common">
    </div>
</div>

<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script language="JavaScript" src="/js/widget/window.js"></script>
<link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
<link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
<link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
<link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts.css"/>
<link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts-extend.css"/>
<link rel="stylesheet" type="text/css" href="/css/public/basicStyle.css"/>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<script type="text/javascript" src="/js/public/addUsers.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/public/orgAndUsersSelect.js"></script>
<link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>

<script language='javascript'>
    var alertInfoOption = {
        alertTitle: '提示信息',
        confirmTitle: '提示信息',
        dialogStyle: 'blueStyle'
    }

    $(function () {
        $.alerts.dialogClass = alertInfoOption.dialogStyle;
        $("#deptId").loadDefaultOrgList();
        $("#uid").loadDefaultUserList();
    });

    /*@Deprecated 2012-10-23 see selectusers*/
    function addUser() {
        //window.open("<%=path%>/pub/selectUserMain1.jsp?nextStepUrl=examUser.do?method=addArrangeExamUsers", "", "width=1000,height=600,toolbar=no,scrollbars=yes,menubar=no,screenX=100,screenY=100");
        window.open("selectUserAction.do?method=addExamUser&nextStepUrl=examUser.do?method=addArrangeExamUsers", "", "width=1000,height=600,toolbar=no,scrollbars=yes,menubar=no,screenX=100,screenY=100");
    }

    var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    if (window.ActiveXObject)
        Sys.ie = ua.match(/msie ([\d.]+)/)[1]
    else if (document.getBoxObjectFor)
        Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1]

    /*@Deprecated 2012-10-23 see selectdepts*/
    function searchdepts() {
        window.open("<%=basePath%>pub/checkboxOrgTreeSubmit.html", "", "width=300,height=500,toolbar=no,scrollbars=yes,menubar=no,screenX=100,screenY=100");
    }

    /*
    * 选择人员
    */
    function selectusers1() {
        var pop_url = "<%=basePath%>selectUserAction.do?method=addExamUser&nextStepUrl=examUser.do?method=addArrangeExamUsers";

        var pop = $("<div id='_pop_win'><h2>选择人员"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='550' width='700' scrolling='auto' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {
                width: "700px",
                height: "580px",
                cursor: "default",
                left: ($left - 700) / 2 - 10 + 'px',
                top: ($top - 580) / 2 - 10 + 'px'
            }
        });
        return;
    }

    /*
    * 选择机构
    */
    function selectdepts() {
        var pop_url = "<%=path%>/pub/checkboxOrgTreeSubmit.html";
        var pop = $("<div id='_pop_win'><h2>选择机构名称"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='330' scrolling='auto' width='100%' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {width: "320px", height: "370px", cursor: "default", left: "30%", top: "18%"}
        });
    }

    function addDept() {
        document.form2.action = "examUser.do?method=listArrangeDept";
        document.form2.submit();
    }

    function isSelect(formname, name) {
        var form = document.getElementById(formname);
        var flag = 0;
        var formelements = form.elements;
        for (var i = 0; i < formelements.length; i++) {
            if (formelements[i].type == "checkbox" && formelements[i].name == name && formelements[i].checked == true) {
                flag = 1;
                break;
            }
        }
        return flag;
    }

    function delDepts() {
        if (1 == isSelect('delDeptsForm', 'deptId')) {
            jConfirm('确定删除这些部门？', '提示', function (flag) {
                if (flag) {
                    document.delDeptsForm.submit();
                }
            });
        } else {
            //alert('请选择删除的部门');
            jAlert('请选择删除的部门', '填写提示');
            return;
        }
    }

    function delUsers() {
        if (1 == isSelect('delUsersForm', 'uid')) {
            jConfirm('确定删除这些用户？', '提示', function (flag) {
                if (flag) {
                    document.delUsersForm.submit();
                }
            });
        } else {
            //alert('请选择删除的用户');
            jAlert('请选择删除的用户', '填写提示');
            return;
        }
    }

    function examUserDeptInsert() {
        document.examUserForm.action = "../examUser/insert.do";
        document.examUserForm.submit();
    }
</script>

<script type='text/javascript'>
    var successParam = function successAction() {
        var operatorIdsOrigin = $("#assignOperatorIdTag").val().split(";");
        var operatorIds = new Array();
        for (var i in operatorIdsOrigin) {
            if (operatorIdsOrigin[i] == null || $.trim(operatorIdsOrigin[i]).length == 0) {
            } else {
                operatorIds.push(operatorIdsOrigin[i]);
            }
        }
        openLink("examUser.do?method=addArrangeExamUsersByOperatorId", {"assignOperatorIdTag": operatorIds});
    };

    setSuccessAction(successParam);

    var $orgInfo = null;
    $orgInfo = $('[data-remodal-id=normalModal]').remodal($.defaultRemodalOption());

    //关闭弹出框执行事情
    $(document).on("closed", "[data-remodal-id=orgInfo]", function () {
        $(".orgInfo .wrapper.common").empty();
    });

    //打开弹出框执行事情
    $(document).on("opening", "[data-remodal-id=orgInfo]", function () {
        //如remodal中嵌套iframe,可以在此设置iframe的open链接
    });

    //打开弹出框：
    $orgInfo.open();
</script>
</body>
</html>
