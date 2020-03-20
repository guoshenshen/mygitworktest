<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.common.Constants" %>
<%@ page import="com.elearning.pojo.pub.EosOperator" %>
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
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css" />
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css" />
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css" />
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css" />
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />

    <style type="text/css">

        .courseContainer,.trainContainer,.seriesContainer{
            display:none;
        }

        .showSeries  .seriesContainer, .showCourse .courseContainer, .showTrain .trainContainer{
            display:block;
        }

    </style>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody" id="seriesResourceEditor">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div class="condition">
                <div class="radio col-sm-6">
                    <label>
                        <input name="tab" type="radio" value="0" aria-label="..." >相关课程
                    </label>
                    <label>
                        <input name="tab" type="radio" value="1" aria-label="...">相关培训
                    </label>
                    <label>
                        <input name="tab" type="radio" value="4" aria-label="...">相关专题
                    </label>
                    <input id="seriesId" name="seriesId" type="hidden" value="${seriesId}">
                </div>
                <form  action="" method="post" class="form-inline col-sm-6" style="text-align: right;">
                    <div class="form-group">
                        <input class="form-control" id="seriesName" type="text"  placeholder="请输入名称…" value="">
                        <button onclick="window.seriesQuery()" type="button" class="btn btn-primary">查询</button>
                    </div>
                </form>

                <div style="clear:both"></div>
            </div>
            <div class="courseContainer">
                <div class="condition" style="padding-left:35px;">
                    <button type="button" class="btn btn-primary foradd" id="courseSearch">新增课程</button>
                    <button onclick="removeItemByPatch()" class="btn btn-danger">批量删除</button>
                </div>
                <table class="table table-striped table-bordered batchOperation " id="courseList">
                    <tr class="tableTh">
                        <th width="5%"><input type="checkbox" id="selectAll" name="selectAll"></th>
                        <th width="20%" style="text-align:center">课程名称</th>
                        <th width="10%" style="text-align:center">科目分类</th>
                        <th width="20%" style="text-align:center">课程时长</th>
                        <th width="20%" style="text-align:center">创建时间</th>
                        <th width="10%" style="text-align:center">权值&nbsp;<i class="fa fa-exclamation-circle fa-lg" style="color: gray" title="值越高排序越靠前，初始值和最小值为0。"></i></th>
                        <th width="15%" style="text-align:center">操作</th>
                    </tr>
                </table>
            </div>
            <div class="trainContainer">
                <div class="condition" style="padding-left:35px;">
                    <button type="button" class="btn btn-primary foradd" id="trainSearch">新增培训</button>
                    <button onclick="removeItemByPatch()" class="btn btn-danger">批量删除</button>
                </div>
                <table class="table table-striped table-bordered batchOperation " id="trainList">
                    <tr class="tableTh">
                        <th width="5%"><input type="checkbox" id="selectAll" name="selectAll"></th>
                        <!-- <th><input type="checkbox" id="selectAll" name="selectAll"/></th> -->
                        <th width="20%" style="text-align:center">培训名称</th>
                        <th width="15%" style="text-align:center">主办单位名称</th>
                        <th width="15%" style="text-align:center">培训开始时间</th>
                        <th width="15%" style="text-align:center">培训结束时间</th>
                        <th width="10%" style="text-align:center">联系人</th>
                        <th width="10%" style="text-align:center">权值&nbsp;<i class="fa fa-exclamation-circle fa-lg" style="color: gray" title="值越高排序越靠前，初始值和最小值为0。"></i></th>
                        <th width="10%">操作</th>
                    </tr>
                </table>
            </div>
            <div class="seriesContainer">
                <div class="condition" style="padding-left:35px;">
                    <button type="button" class="btn btn-primary foradd" id="seriesSearch">新增子专题</button>
                    <button onclick="removeItemByPatch()" class="btn btn-danger">批量删除</button>
                </div>
                <table class="table table-striped table-bordered batchOperation " id="seriesList">
                    <tr class="tableTh">
                        <th width="5%"><input type="checkbox" id="selectAll" name="selectAll"></th>
                        <th width="20%" style="text-align:center">标题</th>
                        <th width="20%" style="text-align:center">专题详情</th>
                        <th width="20%" style="text-align:center"'>主办方信息</th>
                        <th width="10%" style="text-align:center">发布时间</th>
                        <th width="10%" style="text-align:center">权值&nbsp;<i class="fa fa-exclamation-circle fa-lg" style="color: gray" title="值越高排序越靠前，初始值和最小值为0。"></i></th>
                        <th width="15%" style="text-align:center">操作</th>
                    </tr>
                </table>
            </div>
            <div class="condition">
                <ul class="pagination-admin" style="float:right"></ul>
            </div>
        </div>
    </div>
</div>
<div class="bottombody"></div>
<!-- 设置推荐权值 -->
<div class="remodal normal noBorder normalModal weightModal"  data-remodal-id="weightModal" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close" ></button>
    </div>
    <div class="wrapper common" >
        <form class="form-horizontal">
            <div class="form-group">
                <div class="col-sm-12" >
                    <input class="seriesItemId" name="seriesItemId" value="" type="hidden" />
                    <input type="radio" id="fristRadio" name="seriesitemWeight" checked="checked" value="add"/>权值+1<br />
                    <input type="radio" name="seriesitemWeight" value="sub"/>权值-1<br />
                    <input type="radio" name="seriesitemWeight" value="toZero"/>设置为0<br />
                    <input type="radio" name="seriesitemWeight" value="set"/>修改为<input type="text" name="weightInput" style="width: 35px"  />
                </div>
            </div>
            <div class="form-group">
                <button id="publicSeries" class="btn btn-primary updateSection editSeriesitemWeight" type="button" >确定</button>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript">
    window.isCloseAllModal = true;
</script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script type="text/javascript">
    //定义全局变量
    window.seriseTab = "course";
    window.seriesQuery;
</script>
<script type="text/javascript" src="/js/recommend/seriesResourceBinding.js"></script>
<script type="text/javascript">
    //var $courseSearchModal = null;

    $(function(){
        $("#courseSearch").on("click",function(){
            //$('[data-remodal-id=courseSearch]').remodal($.defaultRemodalOption()).open();
            $.showSelectableCourse(params);
        });
        var params = {
            actionForSubmit:function(courseArrayObject){
                var courseObjectList = courseArrayObject.courseList;
                var courseList = new Array();
                for(var i=0;i<courseObjectList.length;i++){
                    courseList.push(courseObjectList[i].courseId);
                }
                if(courseList.length == 0){
                    $.Ntip({
                        'content' : "您尚未指定需要关联的课程"
                    });
                    return;
                }
                else{
                    $.ajax({
                        method:"POST",
                        data:{
                            "courseIdList":JSON.stringify(courseList),
                            "seriesId":$("#seriesId").val()
                        },
                        url:"/seriesResourceBinding/addCourseForSeries.do",
                        traditional:true,
                        dataType:"json",
                        success:function(result){
                            if(result.status){
                                $.closeCourseModal();
                                $.Ntip({
                                    'content':"绑定成功！"
                                });
                                $("input[name=tab]:eq(0)",$("#seriesResourceEditor")).click();
                            }else{
                                if(result.statusCode == 2 ){
                                    $.Ntip({
                                        'content':result.msg
                                    });
                                }
                            }
                        }
                    })
                }
            }
        };

    });
</script>

<script type="text/javascript">

    $(function(){
        $("#trainSearch").on("click",function(){
            $.showSelectTableTrain(params);
        });
        var params = {
            actionForSubmit:function(trainArrayObject){
                var trainObjectList = trainArrayObject.trainList;
                var trainList = new Array();
                for(var i=0;i<trainObjectList.length;i++){
                    trainList.push(trainObjectList[i].trainId);
                }
                if(trainList.length == 0){
                    $.Ntip({
                        'content' : "您尚未指定需要关联的培训"
                    });
                    return;
                }
                else{
                    $.ajax({
                        method:"POST",
                        data:{
                            "trainIdList":JSON.stringify(trainList),
                            "seriesId":$("#seriesId").val()
                        },
                        url:"/seriesResourceBinding/addTrainForSeries.do",
                        traditional:true,
                        dataType:"json",
                        success:function(result){
                            if(result.status){
                                $.closeTrainModal();
                                $.Ntip({
                                    'content':"绑定成功！"
                                });
                                $("input[name=tab]:eq(1)",$("#seriesResourceEditor")).click();
                            }else{
                                if(result.statusCode == 2 ){
                                    $.Ntip({
                                        'content':result.msg
                                    });
                                }

                            }

                        }
                    })
                }
            }
        };

    });
</script>
<script type="text/javascript">
    $(function(){
        $("#seriesSearch").on("click",function(){
            $.showSelectableSeries(params);
        });
        var params = {
            actionForSubmit:function(seriesArrayObject){
                var seriesObjectList = seriesArrayObject.seriesList;
                var seriesList = new Array();
                for ( var i = 0; i < seriesObjectList.length; i++) {
                    seriesList.push(seriesObjectList[i].id);
                }
                if(seriesList.length == 0){
                    $.Ntip({
                        'content' : "您尚未指定需要关联的子专题"
                    });
                    return;
                }
                else{
                    $.ajax({
                        method:"POST",
                        data:{
                            "subSeriesIdList":JSON.stringify(seriesList),
                            "seriesId":$("#seriesId").val()
                        },
                        url:"/seriesResourceBinding/addSubSeriesForSeries.do",
                        traditional:true,
                        dataType:"json",
                        success:function(result){
                            if(result.status){
                                $.closeSeriesModal();
                                $.Ntip({
                                    'content':"绑定成功！"
                                });
                                $("input[name=tab]:eq(2)",$("#seriesResourceEditor")).click();
                            }else{
                                if(result.statusCode == 2){
                                    $.Ntip({
                                        'content':result.msg
                                    });
                                }
                            }
                        }
                    })
                }
            }
        };

    });

    $(".seriesSearch #selectAll").on("click",function(){
        $(".seriesSearch .infoRow input[name=seriesId]").each(function(index,that){
            that.checked = true;
        });
    });
</script>
</body>
</html>
