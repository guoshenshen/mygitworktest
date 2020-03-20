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
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/remodal-default-theme.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/remodal.css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts-extend.css"/>
    <link rel="stylesheet" type="text/css" href="/css/onlinetraining/mixtraining.css"/>
    <style type="text/css">
        .admin .btn {
            letter-spacing: 0px;
            text-indent: 0px;
        }
        .gl_03 {
            padding-left: 0px;
        }
    </style>
    <style type="text/css">
        th {
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
        <div class="mainContent content" id="">
            <div id="funcCon" class="trainApp"></div>

            <div id="mainbody">
                <input id="defaultPageSize" type="hidden" value="${defaultPageSize}" />
                <!-- InstanceBeginEditable name="main" -->
                <div class="homezoneall">
                    <div class="homezonehead">
                        <span class="homezonetitle">实际参会人员</span>
                    </div>
                    <div style="float: right; margin-right:90px;margin-top:10px;margin-bottom:10px;" class="searchTrain">
                        <form class="form-inline" method="post" id="form5" name="form5" action="">
                            <input type="hidden" id="trainId" value="${trainId}"/>
                            <div class="form-group">
                                <input type="text" class="form-control" name="userName" id="userName" value="${userName}" placeholder="姓名"/>
                            </div>
                            <div class="form-group">
                                <label for="attendable">回执情况</label>
                                <select name="attendable" id="attendable" class="form-control">
                                    <option value="-2">所有</option>
                                    <option value="1" <c:if test="${ attendable == 1}">selected</c:if> >参加</option>
                                    <option value="0" <c:if test="${ attendable == 0}">selected</c:if> >不参加</option>
                                    <option value="-1" <c:if test="${ attendable == -1}">selected</c:if> >未回执</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputName2">参会分类</label>
                                <select name="attendType" class="form-control" id="attendType">
                                    <option value="" size=13>所有</option>
                                    <c:forEach var="attendT" items="${attendTypeList}">
                                        <option value="${attendT.code }"
                                            <c:if test="${attendT.code == attendType}" >selected</c:if>>${attendT.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <c:if test="${flag == 1}">
                                <div class="form-group">
                                    <label for="attended">是否最终参会</label>
                                    <select name="attended" class="form-control" id="attended">
                                        <option value="-1">所有</option>
                                        <option value="1" <c:if test="${ attended == 1}">selected</c:if> >是</option>
                                        <option value="0" <c:if test="${ attended == 0}">selected</c:if> >否</option>
                                    </select>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="">是否确认学时</label>
                                <select name="classHourVerified" class="form-control" id="classHourVerified">
                                    <option value="-1">所有</option>
                                    <option value="1" <c:if test="${ classHourVerified == 1}">selected</c:if> >是</option>
                                    <option value="0" <c:if test="${ classHourVerified == 0}">selected</c:if> >否</option>
                                </select>
                            </div>
                            <button class="btn btn-primary" id="searchTrainID" style="letter-spacing:5px;text-indent:5px" type="button">查询</button>
                            <%--<button class="btn btn-primary" onclick="updateHours(${train.ID })" style="letter-spacing:5px;text-indent:5px" type="button">更新学时</button>--%>
                        </form>
                    </div>
                    <div class="homezonecontent">
                        <form id="form3" name="form3" action="../mtMixTrainUserTrainInfo/delete.do" method="post">
                            <table id="userList" class="homecontenttable homezonecontentborder table" rules="cols" width="100%" cellspacing="0" cellpadding="0" style="margin-top:50px;">
                                <tr class="tableTh">
                                    <th width='5%'>
                                        <input align="center" type="checkbox" id="selectAll" name="selectAll" onClick="_setSelected(this,'userTrainInfoId', 'form3')"/>
                                    </th>
                                    <th width='10%' align="center">姓名</th>
                                    <th align="center">单位</th>
                                    <th align="center">学时</th>
                                    <th align="center">学时统计</th>
                                    <th align="center">参会分类</th>
                                    <th align="center">回执情况</th>
                                    <th align="center">审核状态</th>
                                    <th align="center">最终参会</th>
                                    <th width='8%'>总结查看</th>
                                    <th align="center">意见/备注</th>
                                    <th width='8%'>操作</th>
                                </tr>
                            </table>
                        </form>

                        <div class="condtion">
                            <ul class="pagination-admin" style="float:right"></ul>
                            <div style="clear:both"></div>
                        </div>

                        <div id="content_03">
                            <div class="gl_03">
                                <a href="javascript:void(0);" onclick="return updateattended(1)" class="btn btn-primary">学时确认</a>
                                <a href="javascript:void(0);" id='addAttendants' class="btn btn-primary" style="margin-left: 5px;">其他人员添加</a>
                                <a href="../mtMixTrainSectionFile/forupload.do" class="btn btn-primary" style="margin-left: 5px;">其他人员导入</a>
                                <c:if test="${flag == 1}">
                                    <eln:button formId="4" fieldAttribute="statusName">
                                        <a href="javascript:void(0);" onclick="return updatestatus(1053)" class="btn btn-primary" style="margin-left: 5px;">审核未通过</a>
                                        <a href="javascript:void(0);" onclick="return updatestatus(1052)" class="btn btn-primary" style="margin-left: 5px;">审核通过</a>
                                    </eln:button>
                                </c:if>
                                <a href="javascript:void(0);" onclick="return _deleteAllSelect();" class="btn btn-danger" style="margin-left: 5px;">删除</a>
                                <eln:button formId="4" fieldAttribute="hurrySummaryName">
                                    <a href="javascript:void(0);" onclick="return hurrysummary();" class="btn btn-primary" style="margin-left: 5px;">总结催促</a>
                                </eln:button>
                                <a href="javascript:void(0);" onclick="javascript:_export('4');" class="btn btn-primary" style="margin-left: 5px;">导出参会人员</a>
                                <a href="javascript:void(0);" onclick="javascript:exportSummary();" class="btn btn-primary" style="margin-left: 5px;">导出总结</a>
                                <a href="javascript:void(0);" onclick="javascript:forAddAddrBook({'trainId':'${trainId}'});" class="btn btn-primary" style="margin-left: 5px;">生成通讯录</a>
                                <div id="download" style='display:none'/>
                            </div>
                            <br/>
                            <div class="clr"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="clr"></div>
    </div>
</div>
</div>
<div class="bottombody"></div>

<div class="hiddenInfo">
    <form id="addTrainUsersForm" action="../mtMixTrainUserTrainInfo/addAttendedUsers.do" method="post">
        <div class=" effectArea" id="memberId">
            <div class="hiddenArea"></div>
        </div>
        <input type="hidden" name="trainId" value="${trainId}"/>
    </form>
</div>

<!--培训班参会人员添加模式选择窗口 -->
<div id="userAddChannel">
    <div id="_pop_win"><h2>
        <a href="javascript:void(0);" class="pop_close_btn">X</a></h2><br/>
        <div>
            <div><a href="javascript:void(0);" onclick="javascript:showAddrBookStaff();" class="simpleButton">从通讯录中添加</a></div>
            <div><a href="javascript:void(0);" onclick="javascript:selectusers();" class="simpleButton">从部门添加</a></div>
        </div>
    </div>
</div>
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
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<script type="text/javascript" src="/js/onlineTraining/mixtraining.js"></script>
<script type="text/javascript" src="/js/mixtraining/addressBookWindow.js"></script>
<script language='javascript'>
    $(function(){
        mixTrainUserInfoList();
    });

    function mixTrainUserInfoList(){

        //1.获取分页工具包
        var paginationTool=$.getPaginationConfig();

        //2.针对某分页任务，就该任务的逻辑覆盖分页工具包中的方法
        var extraPaginationConfig= {
            getUrlForPagination:function(){return "../mtMixTrainUserTrainInfo/searchMtMixTrainUserTrainInfoList.do"}, //分页器url，访问该url，可获取分页及某页的记录

            //清空之前加载的记录（加载新的分页结果，需要执行该方法）
            actionForClearLoadedData:function(){
                $("#userList .infoRow").remove();
            },
            actionForSucessLoadingData:function(data){
                var dataList=data.list;
                var dataLength=dataList.length;
                var htmlArray=new Array();

                for(var i = 0; i < dataLength ; i++){
                    var currentItem=dataList[i];
                    htmlArray.push("<tr class='infoRow' id='tr_"+currentItem.id+"' >");
                    htmlArray.push("<td> <input type='checkbox' class='forAddrBook' name='userTrainInfoId' value='"+currentItem.id+"'/>");
                    htmlArray.push("<input type='hidden' name='operatorId' value='"+currentItem.userId+"'/>");
                    htmlArray.push("</td>");
                    htmlArray.push("<td><a href='javascript:void(0);' class='userName'>"+currentItem.userName+"</a>");
                    htmlArray.push("</td>");
                    htmlArray.push("<td>"+currentItem.orgName+"</td>");
                    htmlArray.push("<td>"+currentItem.classHour+"</td>");
                    htmlArray.push("<td>"+currentItem.statistics+"</td>");
                    htmlArray.push("<td>"+currentItem.attendTypeName+"</td>");
                    htmlArray.push("<td>"+currentItem.attendableName+"</td>");
                    htmlArray.push("<td>"+currentItem.statusName+"</td>");
                    htmlArray.push("<td>"+currentItem.attendedName+"</td>");
                    htmlArray.push("<td width='6%'>");
                    if(currentItem.trainSummaryId!=null && currentItem.trainSummaryId!=''){
                        htmlArray.push("<a href='javascript:trainSummaryDetail("+currentItem.trainSummaryId+")'>查看</a>");
                    }
                    htmlArray.push("</td>");
                    htmlArray.push("<td>"+currentItem.remark+"</td>");
                    htmlArray.push("<td>");
                    htmlArray.push("<a href='javascript:updateInfo("+currentItem.id+");'>修改</a>");
                    htmlArray.push("</td>");
                    htmlArray.push("</tr>");

                    $("#userList .infoRow").remove();
                    $("#userList").append(htmlArray.join(""));
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

        paginationTool.searchInfo.userName = $("#userName").val();
        paginationTool.searchInfo.attendable = $("#attendable").val();
        paginationTool.searchInfo.attendType = $("#attendType").val();
        paginationTool.searchInfo.attended = $("#attended").val();
        paginationTool.searchInfo.classHourVerified = $("#classHourVerified").val();

        //触发分页器
        paginationTool.actionForLoadingPagination();

        $("#mainbody")
            .on("click","#searchTrainID",function(){
                //paginationTool.resetSearchInfo();
                paginationTool.searchInfo.userName = $("#userName").val();
                paginationTool.searchInfo.attendable = $("#attendable").val();
                paginationTool.searchInfo.attendType = $("#attendType").val();
                paginationTool.searchInfo.attended = $("#attended").val();
                paginationTool.searchInfo.classHourVerified = $("#classHourVerified").val();
                paginationTool.actionForLoadingPagination();
            })
    }
</script>
<script language='javascript'>

    var requestString = new Array();

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

    function _setSel(bool, type, formname) {
        document.getElementById('selectAll').checked = bool;
        _setSelected(document.getElementById('selectAll'), type, formname);
    }

    //查询已经选择的用户
    function getSelectedUsers() {
        var operatorIdList = new Array();
        $("#userList input[name=userTrainInfoId]").each(function (index, that) {
            if ($(that).prop('checked')) {
                operatorIdList.push($(that).siblings("input[name=operatorId]").val());
            }
        });
        return operatorIdList;
    }

    function updatestatus(flag) {
        if (!isItemsSelect()) {
            alert("请选择用户！");
            return false;
        }
        document.form3.action = "../mtMixTrainUserTrainInfo/updatestatus.do?flag=" + flag;
        document.form3.submit();
    }

    function updateattended(flag) {
        if (!isItemsSelect()) {
            alert("请选择用户！");
            return false;
        }
        document.form3.action = "../mtMixTrainUserTrainInfo/updateattended.do?flag=" + flag;
        document.form3.submit();
    }

    function hurrysummary() {
        if (!isItemsSelect()) {
            alert("请选择用户！");
            return false;
        }
        document.form3.action = "../mtMixTrainUserTrainInfo/hurrySummary.do";
        document.form3.submit();
    }

    function uploadExcel() {
        var fileName = document.getElementById("picFile").value;
        if (fileName == "") {
            alert("导入文件名称不能为空!");
            document.getElementById("picFile").focus();
            return;
        }
        if (fileName.substring(fileName.lastIndexOf(".") + 1) != "xls") {
            alert("请导入.xl格式的excel文件!");
            document.getElementById("picFile").focus();
            return;
        }
        document.getElementById('form5').submit();
    }

    function updateInfo(id) {
        var pop_url = "../mtMixTrainUserTrainInfo/forupdate.do?id=" + id;
        var pop = $("<div id='_pop_win'><h2>人员培训信息"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='320' scrolling='no' width='100%' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            window.location.reload();
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {width: "325px", height: "380px", cursor: "default", left: "30%", top: "18%"}
        });
    }

    function trainSummaryDetail(summaryId) {
        /*为了遮住整个页面，定义在了top页面*/
        //top.selectusers();

        var pop_url = "../trainSummary/detail.do?summary_id=" + summaryId;

        var pop = $("<div id='_pop_win'><h2>培训总结"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='530' width='630' scrolling='yes' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {
                width: "650px",
                height: "560px",
                cursor: "default",
                left: ($left - 650) / 2 - 10,
                top: ($top - 500) / 2 - 10
            }
        });
    }

    function _deleteAllSelect() {
        var operatorIdList = getSelectedUsers();
        var trainId = $("#trainId").val();
        if (operatorIdList.length == 0) {
            $.tips("请先勾选人员", "系统提示");
            return false;
        } else {
            $.confirm("<font color=red>确定删除?</font>", "删除提示", function (response) {
                if (response == true) {
                    $.ajax({
                        url: "../trainManage/freezeUserRecordUnderTrain.do",
                        data: {"trainId":trainId, "operatorIdList": operatorIdList},
                        traditional: true,
                        dataType: "json",
                        method: "post",
                        success: function (data) {
                            if (data.success) {
                                document.form3.submit();
                            } else {
                                $.tips("人员信息删除异常！", "系统提示");
                            }
                        },
                        error: function () {
                            $.tips("人员信息删除异常！", "系统提示");
                        }
                    })
                }

            });

        }
    }
    function isItemsSelect() {
        var result = false;
        var boxes = document.getElementsByName("userTrainInfoId");
        for (var i = 0; i < boxes.length; i++) {
            if (boxes[i].checked == true) {
                result = true;
            }
        }
        return result;
    }
    function userDetail(operatorId) {
        var pop_url = "eosorgTEmployeeAction.do?method=detail&userid=" + operatorId;
        var pop = $("<div id='_pop_win'><h2>人员详细信息"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='410' scrolling='no' width='100%' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {width: "500px", height: "440px", cursor: "default", left: "30%", top: "10%"}
        });
    }
    //显示人员添加渠道窗口
    function showUserAddChannel() {

    }
    /*
    * 选择人员
    */
    function selectusers() {
        $.unblockUI();
        var pop_url = "<%=basePath%>selectUserAction.do?method=addExamUser&nextStepUrl=mtMixtrainUserTrainInfoAction.do?method=addAttendedUsers";
        var pop = $("<div id='_pop_win'><h2>选择人员"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='580' width='700' scrolling='auto' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {
                width: "700px",
                height: "620px",
                cursor: "default",
                left: ($left - 700) / 2 - 10 + 'px',
                top: ($top - 620) / 2 - 10 + 'px'
            }
        });
        return;
    }
    function _export(_formId) {
        dojo.io.queueBind({
            url: '../exportFile/export.do',
            method: 'POST',
            content: {formId: _formId},
            load: function (type, data, evt) {
                dojo.byId("download").style.display = "block";
                if (data != ""){
                    dojo.byId("download").innerHTML = "<a href=\"" + data + "\" class=\"btn-orange-l\"><span class=\"btn-orange-r\">下载</span></a>";
                }
            }
        });
    }
    function exportSummary() {
        dojo.io.queueBind({
            url: 'trainSummaryAction.do?method=exportTrainSummary',
            method: 'POST',
            load: function (type, data, evt) {
                dojo.byId("download").style.display = "block";
                if (data != ""){
                    dojo.byId("download").innerHTML = "<a href=\"" + data + "\" class=\"btn-orange-l\"><span class=\"btn-orange-r\">下载总结</span></a>";
                }
            }
        });
    }
    function forAddAddrBook1() {
        var $check = $("#form3 input[id!='selectAll']:checked");
        requestString = new Array();
        if ($check.length > 0) {
            $.each($check, function (i, item) {
                requestString.push(item.getAttribute("value"));
            })
            var pop_url = "<%=basePath%>AddrBookAction.do?method=showPrevAddressBook";

            var pop = $("<div id='_pop_win'><h2>通讯录信息"
                + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
                + "<iframe height='580' width='730' scrolling='auto' class='pop_iframe' src='" + pop_url + "'></iframe></div>"
                + "<div style='clear:both'></div>"
            );
            $.blockUI({
                message: pop,
                css: {
                    width: "730px",
                    height: "620px",
                    cursor: "default",
                    left: ($left - 730) / 2 - 10 + 'px',
                    top: ($top - 620) / 2 - 10 + 'px'
                }
            });
            pop.find("a.pop_close_btn").click(function () {
                $.unblockUI();
            });
        }else {
            jAlert('请您先勾选人员', '提示信息');
        }
    }
    function closePop() {
        $.unblockUI();
    }
    function storeAddrBook() {
        var option = $("#addrbook").val();
        var new_addr = $("#new_addr").val();
        var options = {
            success: function (data) {
                jAlert('<center><font color=red>' + data + '</font></center>', '提示信息', function () {
                    $.unblockUI();
                });
            }
        }
        if (option == "-1" && new_addr == "") {
            jAlert('<center><font color=red>请填写新建通讯录名称</font></center>', '提示信息');
        } else {
            $("#generateAddrBook").submit();
        }
    }
</script>
<script type="text/javascript" src="/js/public/orgAndUsersSelect.js"></script>
<script type="text/javascript">
    function loadExtraOptions() {
        return {
            forUsers: {
                "#memberId": {
                    actionAfterUserselect: function () {
                        $.Nconfirm({
                            'confirmBtnText': '继续添加',
                            'denyBtnText': '添加完毕',
                            "confirmQuestion": "人员添加完毕，是否继续添加其他人员？",
                            "onConfirm": function () {
                            },
                            "onDeny": function () {
                                $.fadeOutWindow();
                                showBufferMask();
                                $("#addTrainUsersForm").submit();
                            }
                        });
                    }
                }
            }

        }
    }
    $(function () {
        $("#addAttendants").click(function () {
            $("#addTrainUsersForm #memberId .hiddenArea").showUserAddChannelWin();
        })

        $("#userList").on("click", ".userName", function () {
            var id = $(this).parents("tr").find("input[name=operatorId]").val();
            $.showUserDetail({"operatorId": id});
        })
    })
    //更新学时
    function updateHours(trainId){
        $.ajax({
            url:"../mtMixTrainUserTrainInfo/updateTrainUserStatic.do",
            data:{"trainId":trainId},
            traditional:true,
            dataType:"json",
            method:"post",
            success:function(data){
                $.tips("学时更新成功！","系统提示");
            },
            error:function(){
                $.tips("学时更新异常！","系统提示");
            }
        })
    }
</script>
</body>
</html>