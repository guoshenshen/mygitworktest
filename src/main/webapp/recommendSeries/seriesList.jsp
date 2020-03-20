<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.common.Constants" %>
<%@ page import="com.elearning.pojo.pub.EosOperator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    try{
        EosOperator user = (EosOperator) session.getAttribute(Constants.USERINFO_KEY);
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
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="中国科学院;继续教育;网络课程;空间"  />
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css" />
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css" />
    <link href="/css/skinCss/skin.css" rel="stylesheet" type="text/css" />
    <link href="/css/skinCss/studentStyle.css" rel="stylesheet" type="text/css" />
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="/css/jquery-UI/jquery.infobox.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css" />
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css" />
    <link href="/css/skinCss/portalStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/studentStyle.css" rel="stylesheet" type="text/css" />
    <style>

        .seriesFrame div.section .series-pic {
            position: relative;
        }
        div.section .series-pic:hover button.studySeries{
            display:block;
        }
        div.section .series-pic button.studySeries{
            position:absolute;
            bottom:10px;
            right: 20px;
            display:none;
        }
        .courseSelectFrame{
            padding: 35px 35px 60px;
        }
        .form-control {
            display: block;
            width: 100%;
            height: 34px;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            color: #555;
            background-color: #fff;
            background-image: none;
            border: 1px solid #ccc;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
            -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
            transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        }
        span.Button {
            cursor: pointer;
            float: left;
            display: block;
            min-width: 84px;
            font-size: 16px;
            letter-spacing: 5px;
            text-align: center;
            font-family: 微软雅黑;
            color: rgb(0, 0, 0);
            font-weight: normal;
            box-shadow: rgb(187, 187, 187) 0px 1px 2px;
            padding: 10px 16px;
            border-radius: 4px;
        }
        /* .redButton {
            background-color: #ca483b;
        } */
        span{
            margin:0;
        }

    </style>
    <script type='text/javascript'>
        function imgError(imgObj){
            if(imgObj.className.indexOf("orgPic")>-1){
                imgObj.src="./image/headPic/defaultOrg.png";
            }
            else{
                imgObj.src="./image/headPic/male1.jpg";
            }
        }
    </script>
    <title>系列专题</title>
</head>
<body class="seriesBody student">
<div id="headbody" class="seriesLogo">
    <div id="pageInfo">
    </div>
</div>
<div id="mainbody" class="seriesFrame">
    <div class="main-container">
        <div class="container">
            <div id="loadingInfo">
                <input type='hidden' name='orgSEQ' value='${orgSEQ}' />
                <input type='hidden' name='tenantOrgSEQ' value="${tenantOrgSEQ}" />
                <input type="hidden" id="infoSearch" />
            </div>
            <div class="title_nav" id="seriesTitle">
                <h2>
                    <a class="tab allSeries leftTab" href="javascript:void(0);" >全部专题</a>
                    <a class="tab selfSeries" href="javascript:void(0);" >本单位专题</a>
                </h2>
            </div>
            <ul id="seriesList" class="content">
                <li class="seriesLi">
                    <div class="section">
                        <a class="series-link">
                            <div class="series-pic">
                            </div>
                        </a>
                        <div class="series-desc">
                            <h2 class="title"><font></font><em></em></h2>
                            <div class="description"><span></span></div>
                        </div>
                    </div>
                </li>
                <li class="seriesLi">
                    <div class="section">
                        <a class="series-link">
                            <div class="series-pic">
                            </div>
                        </a>
                        <div class="series-desc">
                            <h2 class="title"><font></font><em></em></h2>
                            <div class="description"><span></span></div>
                        </div>
                    </div>
                </li>
                <li class="seriesLi">
                    <div class="section">
                        <a class="series-link">
                            <div class="series-pic">
                            </div>
                        </a>
                        <div class="series-desc">
                            <h2 class="title"><font></font><em></em></h2>
                            <div class="description"><span></span></div>
                        </div>
                    </div>
                </li>
                <li class="seriesLi">
                    <div class="section">
                        <a class="series-link">
                            <div class="series-pic">
                            </div>
                        </a>
                        <div class="series-desc">
                            <h2 class="title"><font></font><em></em></h2>
                            <div class="description"><span></span></div>
                        </div>
                    </div>
                </li>
                <li class="seriesLi">
                    <div class="section">
                        <a class="series-link">
                            <div class="series-pic">
                            </div>
                        </a>
                        <div class="series-desc">
                            <h2 class="title"><font></font><em></em></h2>
                            <div class="description"><span></span></div>
                        </div>
                    </div>
                </li>
                <li class="seriesLi">
                    <div class="section">
                        <a class="series-link">
                            <div class="series-pic">
                            </div>
                        </a>
                        <div class="series-desc">
                            <h2 class="title"><font></font><em></em></h2>
                            <div class="description"><span></span></div>
                        </div>
                    </div>
                </li>
                <div style="clear:both"></div>
            </ul>
        </div>
    </div>

</div>
<div id="foot"></div>
<div id="simpleNavBar" style="position:fixed;top:0px;"></div>
<div class="remodal normal" id="studySeriesModal" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close" ></button>
    </div>
    <div class="courseSelectFrame" >
        <div class="scrollable-wrapper">
            <table>
                <tr>
                    <th>专题名称</th>
                    <td  colspan="3">
                        <!-- <input type="text" style="background:#fff" value='' name ="title" class="form-control" disabled="disabled"/> -->
                        <span id="title"></span>
                        <input name="seriesId" value="" type="hidden" />
                    </td>
                </tr>
                <tr>
                    <th>组织单位</th>
                    <td  colspan="3">
                        <input type="hidden" style="background:#fff" id="orgName" value="${org.orgName }" name="orgName" class="form-control" disabled="disabled"/>
                        <span id="orgNameSpan">${org.orgName }</span>
                        <input type="hidden" id="orgId" name="orgId" value="${org.orgId }"/>
                    </td>
                </tr>
                <tr>
                    <th>专题详情</th>
                    <td  colspan="3">
                        <span id="detail"></span>
                    </td>
                </tr>
            </table>
            <br/>
            <div class="btnContainer">
                <div class="tips"></div>
                <div style="float:right">
                    <span class="redButton Button" id="seriesOK" style="margin:0;padding:8px 14px 8px 15px">确认选学</span>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function getCrumbInfo(){
        return "2";
    }
</script>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js" ></script>
<script type="text/javascript" src="/js/UI/scrollpagination-extend.js" ></script>
<script type="text/javascript" src="/js/UI/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/UI/jquery.infobox.js"></script>
<script type="text/javascript" src="/js/basicUserFunc.js" ></script>
<script type="text/javascript" src="/js/recommend/seriesCore.js"></script>
<script type="text/javascript" src="/js/recommend/seriesList.js"></script>
<script type="text/javascript" src="/js/nav/umenu.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type='text/javascript'>
    var $studySeriesModal=$("#studySeriesModal").remodal();
    window.series = new Object();
    window.series.focusSeriesId = null;
    window.series.focusSeriesStudy = null;
    window.series.$focusButton = null;
    var loginData={
        nextActionUrl:"/recommendSeries/intoSeriesFrame.do"
    }
    $(function(){

        //触发系列专题加载
        $.triggerLoadingSeriesList({startIndex:0,showAll:"true",length:7});

        //绑定动作到查询特定类型的专题
        $("#seriesTitle .allSeries").click(function(){
            if($(this).hasClass("currentTab"));
            else{
                $("#seriesTitle .currentTab").removeClass("currentTab");
                $.triggerLoadingSeriesList({startIndex:0,showAll:"true",length:7});
                $(this).addClass("currentTab");
            }

        });
        $("#seriesTitle .selfSeries").click(function(){
            if($(this).hasClass("currentTab"));
            else{
                $("#seriesTitle .currentTab").removeClass("currentTab");
                $.triggerLoadingSeriesList({startIndex:0,showAll:"false",length:7});
                $(this).addClass("currentTab");
            }
        });

        $("#infoSearch").click(function(){
            var titleInfo=$(this).val();
            $.triggerLoadingSeriesList({startIndex:0,length:7,title:titleInfo});
        });

    })
    $("#seriesOK").on('click',function(){
        if(window.series.focusSeriesStudy=="选学"){
            $.ajax({
                method:"POST",
                data:{"seriesId":window.series.focusSeriesId,"isStudy":1},
                url:"recommendSeriesAction.do?method=studySeries",
                dataType:"json",
                success:function(data){
                    if(data.status){
                        /* $.Ntip({
                             'content':"选学成功"
                         }); */
                        window.series.$focusButton.html("退选").removeClass("btn-success").addClass("btn-danger");
                    }else{
                        /* $.Ntip({
                             'content':"操作失败"
                         });	 */
                    }
                }
            });
        }else{
            $.ajax({
                method:"POST",
                data:{"seriesId":window.series.focusSeriesId,"isStudy":0},
                url:"recommendSeriesAction.do?method=studySeries",
                dataType:"json",
                success:function(data){
                    if(data.status){
                        /* $.Ntip({
                             'content':"退选成功"
                         }); */
                        window.series.$focusButton.html("选学").removeClass("btn-danger").addClass("btn-success");
                    }else{
                        /* $.Ntip({
                             'content':"操作失败"
                         }); */
                    }
                }
            });
        }
        $studySeriesModal.close();

    });
    $("#seriesList").on("click",".studySeries",function(){

        var operator = "<%=session.getAttribute(Constants.USERINFO_KEY)%>";
        if(operator=="null"){
            $.Nconfirm({
                'confirmQuestion':"请您先登录学习平台！",
                'onConfirm':function(){
                    $(".logonAction").click();
                    return false;
                },
                'onDeny':function(){
                    return false;
                }

            });
            return false;
        }
        var $_this = $(this);
        window.series.$focusButton = $(this);
        window.series.focusSeriesId = $(this).val();
        window.series.focusSeriesStudy = $(this).html();
        $studySeriesModal.open();
        if($(this).html()=="选学"){
            $("#seriesOK").html("确认选学").removeClass("btn-danger").addClass("btn-success");
        }
        else{
            $("#seriesOK").html("确认退选").removeClass("btn-success").addClass("btn-danger");
        }
        $.ajax({
            method:"POST",
            data:{"seriesId":window.series.focusSeriesId},
            url:"seriesManageAction.do?method=findSeriesById",
            dataType:"json",
            success:function(data){
                var series = data.series;
                if(series.sponsorInfo!=null && series.sponsorInfo!= ""){
                    $("input[name='orgName']").val(series.sponsorInfo);
                }
                $("#studySeriesModal #title").html(series.title);
                $("#studySeriesModal input[name='seriesId']").val(series.id);
                $("#studySeriesModal #detail").html(series.detail);
                $("#studySeriesModal #orgNameSpan").html($("#studySeriesModal input[name='orgName']").val());

            }

        });


        return false;
    });
</script>
<script type="text/javascript" src="/js/jquery.cookie-min.js"></script>
</body>
</html>
