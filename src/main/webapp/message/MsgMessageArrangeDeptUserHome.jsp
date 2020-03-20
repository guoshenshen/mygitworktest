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

    <link rel="stylesheet" type="text/css" href="/css/pagination.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts-extend.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/remodal-default-theme.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/remodal.css"/>
    <link rel="stylesheet" type="text/css" href="/css/onlinetraining/mixtraining.css"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>

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
                <li><a href="../msgMessageInfo/foradd.do"><span>新建通知</span></a></li>
                <li class="selected"><a href="#tabs"><span>回执统计</span></a></li>
                <li><a href="../msgMessageInfo/forPreviousTrainingNotice.do"><span>以往培训通知</span></a></li>
                <div class="clr"></div>
            </ul>
            <div id="mainbody">
                <div class="homezoneall">
                    <div class="homezonehead">
                        <span class="homezonetitle">回执统计</span>
                    </div>
                    <div style="float: right; margin-right:90px;margin-top:10px;">
                        <form id="form5" name="form5" action="" method="post">
                            用户姓名：<input type="text" name="operatorName" id="operatorName" value="${operatorName}"/>
                            通知标题：<input type="text" name="title" id="title" value="${title}"/>
                            回执情况：
                            <select property="attendable" name="attendable" id="attendable">
                                <option value=""> 所有类型</option>
                                <option value="1" <c:if test="${attendable == 1}">selected</c:if> >参加</option>
                                <option value="0" <c:if test="${attendable == 0}">selected</c:if>>不参加</option>
                                <option value="-1" <c:if test="${attendable == -1}">selected</c:if>>未回执</option>
                            </select>
                            <a href="javascript:_query();" class="btn-orange-l location"><span class="btn-orange-r">查询</span></a>
                        </form>
                    </div>
                    <div class="homezonecontent">

                        <div id="content_02">
                            <form id="form3" name="form3" action="<%=path%>/mtMixtrainUserTrainInfoAction.do?method=delete" method="post">
                                <table id="arrangeUserList" class="homecontenttable homezonecontentborder" rules="cols" width="100%" cellspacing="0" cellpadding="0" style="margin-top:50px;">
                                    <tr class="tableTh">

                                        <th width='5%' class="notExport">
                                            <input type="checkbox" id="selectAll" name="selectAll" onClick="_setSelected(this,'msgMessageUserId', 'form3')"/>
                                        </th>
                                        <th width=5%>序号</th>
                                        <th width=20%>通知标题</th>
                                        <th width=0% style="display:none">单位</th>
                                        <th width=15%>部门</th>
                                        <th width=12%>人员姓名</th>
                                        <th width=10%>是否参加</th>
                                        <th width=13%>回执时间</th>
                                        <th width=20%>备注</th>
                                    </tr>
                                </table>
                            </form>
                        </div>

                        <div class="condtion">
                            <ul class="pagination-admin" style="float:right"></ul>
                            <div style="clear:both"></div>
                        </div>

                        <div id="content_03">
                            <div class="gl_03">
                                <a href="javascript:void(0);" id="forExportAddressBook" class="btn-blue-l" style="margin-left: 5px;">
                                    <span class="btn-blue-r">导出</span>
                                </a>
                                <a href="javascript:void(0);" onclick="javascript:forAddAddrBook({'trainId':'${train.ID}','flag':'fromMsgMessageUserForm'});" class="btn-blue-l" style="margin-left: 5px;">
                                    <span class="btn-blue-r">生成通讯录</span>
                                </a>
                            </div>

                            <div class="clr"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clr"></div>
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
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<%--<script language="javascript" src="/js/pagination.js"></script>--%>
<script type="text/javascript" src="/js/onlineTraining/mixtraining.js"></script>
<script type="text/javascript" src="/js/mixtraining/addressBookWindow.js"></script>
<script type="text/javascript" src="/js/public/jquery.excel.js"></script>
<script type="text/javascript">
    $(function(){
        arrangeUserList();
    });

    function arrangeUserList(){
        //1.获取分页工具包
        var paginationTool=$.getPaginationConfig();
        //2.针对某分页任务，就该任务的逻辑覆盖分页工具包中的方法
        var extraPaginationConfig= {
            getUrlForPagination:function(){return "../msgMessageArrangeList/listArrangeUser.do"}, //分页器url，访问该url，可获取分页及某页的记录

            //清空之前加载的记录（加载新的分页结果，需要执行该方法）
            actionForClearLoadedData:function(){
                $("#arrangeUserList .infoRow").remove();
            },
            actionForSucessLoadingData:function(data){
                var dataList=data.list;
                var dataLength=dataList.length;
                var htmlArray=new Array();

                for(var i = 0; i < dataLength ; i++){
                    var currentItem=dataList[i];

                    htmlArray.push("<tr class='infoRow'>");

                    htmlArray.push("<td align='center' class='noExport'>");
                    htmlArray.push("<input type='checkbox' class='forAddrBook' name='msgMessageUserId' value='"+currentItem.id+"'/>");
                    htmlArray.push("<input type='hidden' name='operatorId' value='"+currentItem.userId+"'/>");
                    htmlArray.push("</td>");
                    htmlArray.push("<td align='center'>"+(i+1)+"</td>");
                    htmlArray.push("<td align='center'>"+currentItem.title+"</td>");
                    htmlArray.push("<td align='center' style='display:none'>"+currentItem.parentOrgName+"</td>");
                    htmlArray.push("<td align='center'>"+currentItem.orgName+"</td>");
                    htmlArray.push("<td align='center'>"+currentItem.operatorName+"</td>");
                    htmlArray.push("<td align='center'>");
                    if(currentItem.attendable == -1){
                        htmlArray.push("未回执");
                    }else if(currentItem.attendable == 0){
                        htmlArray.push("不参加");
                    }else if(currentItem.attendable == 1){
                        htmlArray.push("参加");
                    }else{
                        htmlArray.push("&nbsp;");
                    }
                    htmlArray.push("</td>");
                    htmlArray.push("<td align='center'>"+currentItem.sendbackTime+"</td>");
                    htmlArray.push("<td align='center'>"+currentItem.comment+"</td>");

                    htmlArray.push("</tr>");

                    $("#arrangeUserList .infoRow").remove();
                    $("#arrangeUserList").append(htmlArray.join(""));

                }

            }
            //将读取的分页记录组装成html并加载到页面
        };

        //将任务逻辑注入分页工具包
        $.extend(paginationTool,extraPaginationConfig);
        //恢复分页器默认设置
        paginationTool.resetSearchInfo();
        //向分页器传递查询条件
        $(".searchTrain .formInfo").each(function(index,that){
            paginationTool.searchInfo[that.name]=that.value;

        });

        paginationTool.searchInfo["operatorName"] = $("#operatorName").val();
        paginationTool.searchInfo["title"] = $("#title").val();
        paginationTool.searchInfo["attendable"] = $("#attendable").val();

        //触发分页器
        paginationTool.actionForLoadingPagination();
    }

    function _query() {
        arrangeUserList();
    }

    function _export() {
        alert("执行导出！");
        var form3 = document.getElementById("form3");
        form3.action = "msgMessageArrangeUserAction.do?method=exportRegisterTable";
        form3.submit();
    }

    $('#tabs li').click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
    });

    function _setSelected(id, type, formname) {
        var chechTemp = id.checked;
        with (document.getElementById(formname)) {
            var form1 = document.getElementById(formname);
            var eles = form1.elements;
            for (var j = 0; j < eles.length; j++) {
                if (eles[j].type == "checkbox" && eles[j].name == type) {
                    if (chechTemp) {
                        eles[j].checked = true;
                    } else {
                        eles[j].checked = false;
                    }
                }
            }
        }
    }
</script>

<script type="text/javascript">
    $(function () {
        $("#forExportAddressBook").click(function () {
            //xxx为要导出表格的jquery选择器
            $("#arrangeUserList").exportTableInCurrentView({});
        })
    });
</script>
</body>
</html>