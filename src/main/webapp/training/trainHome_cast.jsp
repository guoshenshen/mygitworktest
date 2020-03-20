<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String userRolesInString = "";
    try{
        EosOperator user = (EosOperator)session.getAttribute(Constants.USERINFO_KEY);
    }catch(Exception ex){
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
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta content="培训;在线培训;网络培训" name="keywords" />
    <title></title>
    <link href="/js/layui/css/layui.css" rel="stylesheet" type="text/css" />
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css" />
    <link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css" />
    <link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css" />
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css" />
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css" />
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css" />
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.navigation.css" />

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
        <div class="mainContent content" id="trainListFrame">
            <div id="contentbody">

                <div class="searchTrain"><!-- ../onlineTraining/searchTrain.do -->
                    <input id="defaultPageSize" type="hidden" value="${defaultPageSize}" />
                    <form id="form1" name="form1" action="" method="post">
                        <div class="form-inline condition" style="text-align: right;">
                            <div class="form-group">
                                <input class="form-control formInfo" type="text" name="trainYearInput" id="trainYearInput" placeholder="培训年度…" value="${trainYearInput}" />
                                <input class="form-control formInfo" type="text" name="trainNameInput" id="trainNameInput" placeholder="培训名称…" value="${trainNameInput}" />
                                <input class="form-control formInfo" type="text" name="trainKeyWordsTagInput" id="trainKeyWordsTagInput" placeholder="关键词…" value="${trainKeyWordsTagInput}" />
                                <select property="trainTypeInput" name="trainTypeInput" id="trainTypeInput" class="form-control formInfo" title="培训类型">
                                    <option value="0" selected="selected">所有</option>
                                    <c:if test="${trainTypeList != null }" >
                                        <c:forEach var="trainType" items="${trainTypeList}" >
                                            <option value="${trainType.code}"
                                                    <c:if test="${trainType.code == trainTypeInput}">
                                                        selected
                                                    </c:if>>
                                                    ${trainType.name}
                                            </option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                                <button  id = "searchTrain" class="btn btn-primary" style="top:15px;" type="button">查询</button><%--onclick="searchTrain()"--%>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="clr"></div>

                <div id="content_02">
                    <form id="form2" name="form2" action="" method="post" >
                        <table id="trainList" class="table table-striped table-bordered batchOperation" rules="cols" width="100%">
                            <tr>
                                <th width="5%"><input type="checkbox" name="selectAll" onclick="_setSelected(this,'selectbox')" /></th>
                                <th width="15%">培训名称</th>
                                <th width="10%">开始时间</th>
                                <th width="10%">结束时间</th>
                                <th width="15%">联系人</th>
                                <th width="15%">主办单位</th>
                                <th width="10%">审批情况</th>
                                <th width="10%">实施情况</th>
                                <th width="10%">操作</th>
                            </tr>
                        </table>
                    </form>
                </div>

                <div class="condtion">
                    <ul class="pagination-admin" style="float:right"></ul>
                    <div style="clear:both"></div>
                </div>

                <div class="condition" style="margin-top:20px;" id = "handleCourse">
                    <button class="btn btn-primary" onclick="javascript:_add()" >新建培训</button>
                    <button class="btn btn-danger" id = "deletionByQuery"  onclick="delTrainByQuery()" >删除培训</button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="bottombody"></div>

<div class="trainDetailFrame" data-remodal-id="trainDetailFrame" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc' style="min-height:380px;">
    <div class="box">
        <button data-remodal-action="close" class="remodal-close"></button>
    </div>
    <div class="wrapper full" style="height: 500px;">
        <iframe src="" width="width: 100%;" id="trainDetailIframe"></iframe>
        <ul class="vertical-menu float_vertical_menu">
            <li class="green" title="利用该培训计划信息创建新的计划" width="33.4%" id="trainReference"><span class="reference-icon"></span> <a href="javascript:void(0);"><span>引用计划</span>
            </a></li>
            <li class="orange" title="利用该计划信息生成计划模板方便以后计划创建" width="33.4%" id="planTemplateMaker" style="display:none"><span class="focus-icon"></span> <a href="javascript:void(0);"><span>生成模板</span>
            </a></li>
        </ul>
    </div>
</div>


<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/basicFunc.js"></script>
<script type="text/javascript" src="/js/UI/tooltipster.bundle.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>

<%--<script type="text/javascript" src="/js/plan/planList.js"></script>--%>

<script type="text/javascript">

$(function(){

    trainList()

    if($(".trainDetailFrame").length>0){
        var $trainDetailModal=$('[data-remodal-id=trainDetailFrame]').remodal($.defaultRemodalOption());

        var trainDetailModalInfo={
            currentPlanId:""
        };

        $(document).on('opening', '.trainDetailFrame', function () {
            $(".trainDetailFrame iframe").attr("src","../onlineTraining/fordetail.do?mode=copy&trainId="+trainDetailModalInfo.currentTrainId);
        });

        $(document).on('closed', '.trainDetailFrame', function () {
            $(".planDetailFrame iframe").attr("src","");
        });

        $.fn.extend({
            showTrainDetail:function(){
                trainDetailModalInfo.currentTrainId=$("input[name='selectbox']",$(this)).val();
                $trainDetailModal.open();
            },

            //引用培训计划信息
            referenceTrain:function(){
                var link="../onlineTraining/foradd.do?trainId="+trainDetailModalInfo.currentTrainId;
                $.Ntip({
                    'content':"请点击链接<a href='javascript:void(0);' onclick=\"window.open('"+link+"')\" >填充培训信息</a>"
                });
            },
        });

        $("body #trainList").on("click",".trainName",function(){
            $(this).showTrainDetail();
        });
        $("body").on("click","#trainReference",function(){
            $(this).referenceTrain();
        });
    }

    $("#trainList").on("click",".pubstatusLink",function(){

        var $Link=$(this);
        var trainId=$("input[name=trainId]",$Link.parents("tr")).val();

        $.ajax({
            type:"POST",
            url:"../onlineTraining/updatePubStatus.do",
            data:{"trainID": trainId},
            dataType:"json",
            success:function(data){
                if(data.success){
                    var pubstatus=data.data;
                    if(pubstatus=="1091"){
                        $Link.html("未发布");
                    } else{
                        $Link.html("已发布");
                    }
                } else{
                    $.tips("发布状态切换异常,请稍后重试!");
                    return;
                }
            },
            error:function(){
                $.tips("发布状态切换异常,请稍后重试!");
            }
        });
    });

});

function trainList(){

    //1.获取分页工具包
    var paginationTool=$.getPaginationConfig();

    //2.针对某分页任务，就该任务的逻辑覆盖分页工具包中的方法
    var extraPaginationConfig= {
        getUrlForPagination:function(){return "../onlineTraining/listTrains.do"}, //分页器url，访问该url，可获取分页及某页的记录

        //清空之前加载的记录（加载新的分页结果，需要执行该方法）
        actionForClearLoadedData:function(){
            $("#trainList .infoRow").remove();
        },
        actionForSucessLoadingData:function(data){
            var dataList=data.list;
            var dataLength=dataList.length;
            var htmlArray=new Array();

            for(var i = 0; i < dataLength ; i++){
                var currentItem=dataList[i];
                htmlArray.push("<tr class='infoRow' id='tr_"+currentItem.id+"' >");
                htmlArray.push("<td><input id='checkbox_"+currentItem.id+"' type='checkbox' value='"+currentItem.id+"' name='selectbox' />"+"<input type='hidden' name='trainId' value='"+currentItem.id+"' /></td>");
                htmlArray.push("<td align='left'><a href='javascript:void(0);' class='trainName' >" +"<input  type='hidden' value='"+currentItem.id+"' name='selectbox' />"+currentItem.trainName+"</a></td>");
                htmlArray.push("<td align='center'>"+currentItem.startTime+"</td>");
                htmlArray.push("<td align='center'>"+currentItem.endTime+"</td>");
                htmlArray.push("<td align='center'>"+currentItem.organizerName+"</td>");
                htmlArray.push("<td align='center'>"+currentItem.orgName+"</td>");
                htmlArray.push("<td align='center'>"+currentItem.approveStatusName+"</td>");
                htmlArray.push("<td align='center'>"+currentItem.implstatusName+"</td>");
                htmlArray.push("<td align='center'>");
                htmlArray.push("<p><a id='operatorCancelIconCookie' href='../onlineTraining/forupdate.do?id="+currentItem.id+"'>管理</a></p>");
                if(currentItem.pubstatus=="1092"){
                    htmlArray.push("<p><a class='pubstatusLink' style='cursor:pointer;'>已发布</a></p>");
                } else{
                    if ( currentItem.approveStatus == 1048) { //approveStatus 1048驳回修改
                        htmlArray.push("<p title='驳回修改不允许发布'>未发布</p>");
                    } else {
                        htmlArray.push("<p><a class='pubstatusLink' style='cursor:pointer;'>未发布</a></p>");
                    }
                }
                htmlArray.push("<p><a target='_blank' style='cursor:pointer;' href='../onlineTraining/viewTrain4Admin.do?trainId="+currentItem.id+"'>预览</a></p>");
                htmlArray.push("</td>");
                htmlArray.push("</tr>");

                $("#trainList .infoRow").remove();
                $("#trainList").append(htmlArray.join(""));
            }
        }
        //将读取的分页记录组装成html并加载到页面
    };

    //将任务逻辑注入分页工具包
    $.extend(paginationTool,extraPaginationConfig);
    //恢复分页器默认设置
    paginationTool.resetSearchInfo();

    paginationTool.searchInfo.trainYearInput = $("#trainYearInput").val();
    paginationTool.searchInfo.trainNameInput = $("#trainNameInput").val();
    paginationTool.searchInfo.trainKeyWordsTagInput = $("#trainKeyWordsTagInput").val();
    paginationTool.searchInfo.trainTypeInput = $("#trainTypeInput").val();

    //触发分页器
    paginationTool.actionForLoadingPagination();

    $("#trainListFrame")
        .on("click","#searchTrain",function(){
            //paginationTool.resetSearchInfo();
            paginationTool.searchInfo.trainYearInput = $("#trainYearInput").val();
            paginationTool.searchInfo.trainNameInput = $("#trainNameInput").val();
            paginationTool.searchInfo.trainKeyWordsTagInput = $("#trainKeyWordsTagInput").val();
            paginationTool.searchInfo.trainTypeInput = $("#trainTypeInput").val();
            paginationTool.actionForLoadingPagination();
        })

}

/**
 * 全部选中按钮
 * @param id
 * @param type
 * @private
 */
function _setSelected(id,type){
    var chechTemp = id.checked;
    with(document.form2){
        for ( var j=0; j < elements.length; j++ ){
            if ( elements[j].type == "checkbox" && elements[j].name == type) {
                if(chechTemp){
                    document.form2.elements[j].checked = true;
                }else{
                    document.form2.elements[j].checked = false;
                }
            }
        }
    }
}
//新建培训
function _add(){
    document.form1.action="../onlineTraining/foradd.do";
    document.form1.submit();
}

/**
 * 批量删除培训班
 */
function delTrainByQuery(){
    var selectedValues=new Array();
    $(".infoRow input[type=checkbox]:checked").each(function(index,that){
        selectedValues.push(that.value);
    });
    if(selectedValues.length==0){
        $.Ntip({
            'content':"请选择要删除的培训班",
        })
    }else{
        $.Nconfirm({
            'confirmQuestion':"确认要删除已经选择的培训班吗？",
            'onConfirm':function(){
                var $mask = null;
                $mask = showBufferMask();
                $.ajax({
                    url:"../onlineTraining/delTrainById.do",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    type:"POST",
                    data:{"selectbox":selectedValues},
                    dataType:"json",
                    traditional:true,

                    success:function(data){
                        $mask.remove();
                        if(data.status ){
                            $.Ntip({
                                'content':data.msg,
                                'onClose':function(){
                                    trainList();
                                }
                            })
                        }else if(data.statusCode == 1){
                            $.Ntip({
                                'content': data.msg,
                                'onClose':function(){
                                    trainList();
                                }
                            })
                        }else{
                            $.Ntip({
                                'content':"培训班删除失败",
                            })
                        }
                    }
                });
            }
        });
    }
}

</script>
</body>
</html>
