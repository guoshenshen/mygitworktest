<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher"%>
<%--<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>--%>
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
<body class="admin" id="courseSectionEditor">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody" >
        <div id="trace" class="content"></div>
        <div class="mainContent content" >
            <div style="padding-bottom: 20px">
                <div class="condition" style="display:inline">
                    <button class="btn btn-primary" id="addSection">新添章节</button>
                    <button class="btn btn-primary" id="saveSection">保存</button>
                </div>
                <div style="float:right;"><input type="text"   placeholder="章节名称..."
                                                 id="chapterName1"
                                                 value="" />
                    <button id = "serchCourse" class="btn btn-primary" >查询</button>
                </div>
            </div>
            <table class="table table-striped table-bordered batchOperation" >
                <tr class="tableTh">
                    <th width="8%"></th>
                    <th width="5%">序号</th>
                    <th width="60%">章节</th>
                    <th width="30%">操作</th>
                </tr>
                <tbody id="sectionList">

                </tbody>
            </table>
            <div style="height:40px;">
                <div style="float:right;" class="condition">

                    <c:if test="${ courseDescription != null }">
                        <%-- <a href="addLessonInfo.do?method=foruploadcourseware&courseTypeId=${courseTypeId}&trainId=${train.id }&courseDescription=${courseDescription}&chapterId=${chapterId}" class="btn btn-primary">返回</a> --%>
                        <a href="javascript:void(0)" onclick = "window.history.back()" class="btn btn-primary">返回</a>
                        <a href="../courseType/otherFileUpload.do?serverId=${serverId}&fileSize=${fileSize}&courseID=${courseId}&courseTypeId=${courseTypeId}&trainId=${train.id}&chapter=1&chapterId=${chapterId}" class="btn btn-primary">下一步</a>
                    </c:if>
                    <c:if test="${courseDescription == null }">
                        <a href="../addLessonInfo/updateCourse.do?courseId=${courseId}&courseTypeId=0" class="btn btn-primary">返回</a>
                        <!-- <a href="courseCoursetype.do?method=listOffLineAllCourse" class="btn btn-primary">完成</a> -->
                        <a href="../courseType/otherFileUpload.do?serverId=${serverId}&fileSize=${fileSize}&courseID=${courseId}&courseTypeId=${courseTypeId}&trainId=${train.id}&chapter=1&chapterId=${chapterId}" class="btn btn-primary">下一步</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="bottombody"></div>
<div class="remodal normal noBorder normalModal sectionEditor"  data-remodal-id="sectionEditor" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close" ></button>
    </div>
    <div class="wrapper common" >
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-3 control-label">章节名称：</label>
                <div class="col-sm-6" >
                    <input type="text" id="chapterName" name="chapterName" value='' class="form-control" />
                    <input type="hidden" id="chapterid" name="chapterid" value='' class="form-control" />
                </div>
            </div>
            <div class="form-group">
                <button class="btn btn-primary updateSection" tyep ="button">提交</button>
            </div>
        </form>
    </div>
</div>
<input type="hidden" id="token" name="token" value="${token}"/>
<input type = "hidden" value = "${courseId}" id = 'courseId'/>
<script type="text/javascript" src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/nav/amenu.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/JSCommonTools.js"></script>
<script type="text/javascript" src="../js/aTool.js"></script>
<script type="text/javascript" src="../js/UI/remodal.js"></script>
<script type="text/javascript" src="../js/course/courseEditor.js"></script>
<script type="text/javascript">

    $(function(){
        init();
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
            var courseId = $("#courseId").val();
            $("#sectionList .chapterId").each(function(){
                ids.push(this.value);
            });
            console.log('a',ids);
            $.ajax({
                url:"../addLessonInfo/orderChapter.do",
                type:"post",
                data:{chapterIds:ids,courseId:courseId},
                traditional:true,
                dataType:"json",
                success:function(data) {
                    if (data.status ) {
                        $.Ntip({
                            'content':"保存成功",
                        })
                        init();
                    }
                }
            });

        });

        $("#serchCourse").on("click",function(){
            init();
        })

    })

    function init() {
        var chapterName = $("#chapterName1").val();
        var courseId = $("#courseId").val();
        $.ajax({
            url:"../addLessonInfo/getChapter.do",
            type:"post",
            data:{chapterName:chapterName,courseId:courseId},
            dataType:"json",
            success:function(data){
                if(data.status  ){
                    $("#sectionList").empty();
                    var dataList=data.data;
                    var dataLength=dataList.length;
                    var htmlArray=new Array();
                    for(var i=0;i<dataLength;i++){
                        var currentItem=dataList[i];
                        htmlArray.push("<tr class='infoRow' id='tr_"+currentItem.chapterId+"' >");
                        htmlArray.push("<td ><i class='fa fa-angle-up upArrow'></i>&nbsp;&nbsp;&nbsp;<i class='fa fa-angle-down downArrow'></i></td>");
                        htmlArray.push("<td  >"+(i+1)+"</td>");
                        htmlArray.push("<td  >"+currentItem.chapterName+"</td>");
                        htmlArray.push("<td> <a class='forUpdateSection' style = 'cursor:pointer' >"
                            +"<input class='chapterId'  type='hidden' value='"+currentItem.chapterId+"' name='selectbox' />"
                            +"<input class=''  type='hidden' value='"+currentItem.chapterName+"' name='chapterName' />"+
                            "修改</a>	<a class='editSectionFragment' style = 'cursor:pointer' >"+
                            "<input  type='hidden' value='"+currentItem.chapterId+"' name='selectbox' />"+
                            "片段编辑</a>&nbsp;&nbsp;<a href='javascript:delChapter(\""+currentItem.chapterId+"\")'>删除</a></td>");
                        htmlArray.push("</tr>");
                    }
                    $("#sectionList").append(htmlArray.join(""));
                }else{
                    $.Ntip({
                        'content':"获取失败",
                    })
                }
            }

        });
    }
    // 删除章节
    function delChapter(chaterId) {
        var courseId = $("#courseId").val();
        $.ajax({
            url:"../addLessonInfo/deletechapter.do",
            type:"post",
            data:{chapterId:chaterId,courseId:courseId},
            dataType:"json",
            success:function(data) {
                if (data.status ) {
                    $.Ntip({'content':"删除成功"});
                    init();
                }
                if(data.statusCode == 1){
                    $.Ntip({'content':"该章节已被使用，不可以删除"});
                }
            }
        });

    }
</script>

</body>
</html>
