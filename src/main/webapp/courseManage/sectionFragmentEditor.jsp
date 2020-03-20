<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants"%>
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
    <link href="../css/skinCss/Normalize.css" rel="stylesheet" type="text/css" />
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="../css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css" />
    <link href="../css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
</head>
<body class="admin" id="courseSectionFragmentEditor">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody" >
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div style="padding-bottom: 20px">
                <div class="condition" style="display:inline">
                    <button class="btn btn-primary" id="addFragment">新添片段</button>
                    <button class="btn btn-primary" id="saveSection">保存</button>
                </div>
                <div style="float:right;"><input type="text"   placeholder="片段名称..."
                                                 id="courseName1"
                                                 value="" />

                    <button id = "serchCourse" class="btn btn-primary" >查询</button>	</div>
            </div>

            <div style="clear:both;"></div>
            <table class="table table-striped table-bordered batchOperation" >
                <tr class="tableTh">
                    <th width="6%"></th>
                    <th width="5%">序号</th>
                    <th width="25%">片段名称</th>
                    <th width="25%">课件类型</th>
                    <th width="10%">课时</th>

                    <th width="30%">操作</th>
                </tr>
                <tbody id="sectionList">

                </tbody>
            </table>
        </div>
    </div>
</div>
<input type="hidden" id="token" name="token" value="${token}"/>
<div class="bottombody"></div>
<input type="hidden" id="chapterId" name="chapterId" value="${chapterId}" />
<div class="remodal normal noBorder normalModal sectionFragmentEditor"  data-remodal-id="sectionFragmentEditor" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close" ></button>
    </div>
    <div class="wrapper common" >
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-3 control-label">片段名称：</label>
                <div class="col-sm-6" >
                    <input type="hidden" id="courseId" name="courseId" value="" />
                    <input type="text" id="courseName" name="courseName" value='' class="form-control" />
                </div>

            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">课时：</label>
                <div class="col-sm-6" >
                    <input type="text" id="classHour" name="classHour" value='' class="form-control" />
                </div>

            </div>
            <div class="form-group">

                <label class="col-sm-3 control-label">请选择课件类型：</label>
                <div class="col-sm-6" >
                    <select property="kindId" name="kindId" id="kindId"  class="form-control"  style="width:180px;float:left;">
                        <option value="0" >请选择课件类型</option>
                        <c:if test="${kinds != null }">
                            <c:forEach var="kind" items="${kinds}">
                                <option
                                        value="${kind.key}"
                                        <c:if  test='${kind.key == course.kindId}'>selected</c:if>>
                                        ${kind.value}
                                </option>
                            </c:forEach>
                        </c:if>
                    </select>

                </div>
            </div>
            <div class="form-group">
                <button class="btn btn-primary updateFragment" type="button">提交</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/nav/amenu.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/JSCommonTools.js"></script>
<script type="text/javascript" src="../js/aTool.js"></script>
<script type="text/javascript" src="../js/course/courseEditor.js"></script>
<script type="text/javascript" src="../js/UI/remodal.min.js"></script>
<script type="text/javascript">

    $(function(){
        init();
        var chapterId = $("#chapterId").val();
        $("#sectionList").on("click",".upArrow",function(){
            var $currentLine=$(this).parents("tr");
            var $prev=$currentLine.prev();
            if($prev.length>0){
                var currentInfo=$currentLine.html();
                var prevInfo=$prev.html();
                $prev.html(currentInfo);
                $currentLine.html(prevInfo);
            }
        });
        $("#sectionList").on("click",".downArrow",function(){
            var $currentLine=$(this).parents("tr");
            var $next=$currentLine.next();
            if($next.length>0){
                var currentInfo=$currentLine.html();
                var nextInfo=$next.html();
                $next.html(currentInfo);
                $currentLine.html(nextInfo);
            }
        });
        $("#saveSection").on("click",function(){
            var ids = [];
            $("#sectionList .courseId").each(function(){
                ids.push(this.value);
            });
            console.log('a',ids);
            $.ajax({
                url:"../addLessonInfo/orderSecondCourseId.do",
                type:"post",
                data:{courseIds:ids,chapterId:chapterId},
                traditional:true,
                dataType:"json",
                success:function(data) {
                    if (data.status ) {
                        $.Ntip({'content':"保存成功",})
                        init();
                    }else{
                        $.Ntip({'content':"保存失败",})
                    }
                }
            });

        });

        $("#serchCourse").on("click",function(){
            init();
        })

    })

    function init(){
        var chapterId = $("#chapterId").val();
        var courseName = $("#courseName1").val();
        $.ajax({
            url:"../addLessonInfo/getsecondCourse.do",
            type:"post",
            data:{courseName:courseName,chapterId:chapterId},
            dataType:"json",
            success:function(data){
                if(data.status ){
                    $("#sectionList").empty();
                    var dataList=data.data;
                    var dataLength=dataList.length;
                    var htmlArray=new Array();
                    for(var i=0;i<dataLength;i++){
                        var currentItem=dataList[i];
                        htmlArray.push("<tr class='infoRow' id='tr_"+currentItem.courseId+"' >");
                        htmlArray.push("<td ><i class='fa fa-angle-up upArrow'></i>&nbsp;&nbsp;&nbsp;<i class='fa fa-angle-down downArrow'></i></td>");
                        htmlArray.push("<td  >"+(i+1)+"</td>");
                        htmlArray.push("<td  >"+currentItem.courseName+"</td>");
                        var kind = currentItem.sliceType;
                        var sliceType;
                        if(kind == 3){
                            sliceType = '单一视频/文档课件';
                        }
                        if(kind == 1){
                            sliceType = '单一网址课件';
                        }
                        if(kind == 2){
                            sliceType = 'SCORM 课件';
                        }
                        htmlArray.push("<td  >"+sliceType+"</td>");
                        htmlArray.push("<td  >"+currentItem.classHour+"</td>");
                        htmlArray.push("<td> <a class='forUpdateFragment' style = 'cursor:pointer' >"
                            +"<input  type='hidden' value='"+currentItem.courseId+"' name='selectbox' />"
                            +"<input  type='hidden' value='"+currentItem.courseName+"' name='courseName' />"
                            +"<input  type='hidden' value='"+currentItem.classHour+"' name='classHour' />"
                            +"<input  type='hidden' value='"+currentItem.sliceType+"' name='sliceType' />"+
                            "修改</a>	<a class='editFragmentMaterial' style = 'cursor:pointer' >"
                            +"<input class='courseId' type='hidden' value='"+currentItem.courseId+"' name='selectbox' />"
                            +"<input  type='hidden' value='"+currentItem.sliceType+"' name='sliceType' />"+
                            "<input  type='hidden' value='"+chapterId+"' name='chapterId' />"+
                            "片段编辑</a>&nbsp;&nbsp;<a href='javascript:delCourse("+currentItem.courseId+")'>删除</a>"
                            +"&nbsp;&nbsp;<a href='javascript:void(0);' onclick='window.open(\"/courseStudy/previewStudy.do?courseID="+currentItem.courseId+"&info=admin\")'>预览</a>"
                            +"</td>");
                        htmlArray.push("</tr>");
                    }
                    $("#sectionList").append(htmlArray.join(""));
                }else if(data.status == 1){
                    $.Ntip({
                        'content':"获取失败",
                    })
                }else if(data.status == 2){
                    $.Ntip({
                        'content':"参数错误",
                    })

                }

            }

        });

    }
    function delCourse(courseId) {
        var chapterId = $("#chapterId").val();
        $.ajax({
            url:"../addLessonInfo/deleteSecond.do",
            type:"post",
            data:{courseId:courseId,chapterId:chapterId},
            dataType:"json",
            success:function(data) {
                if (data.status ) {
                    $.Ntip({'content':"删除成功"});
                    init();
                }
                if(data.statusCode == 1 ){
                    $.Ntip({'content':"删除失败"});
                }
                if(data.statusCode == 7){
                    $.Ntip({'content':data.msg});
                }
            }
        });
    }


</script>
</body>
</html>
