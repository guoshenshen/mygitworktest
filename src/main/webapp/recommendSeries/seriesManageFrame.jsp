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
    <link rel="stylesheet" type="text/css" href="/css/uploadify.css"/>
    <link rel="stylesheet" type="text/css" href="/css/uploadifive.css"/>
    <style>
        .seriesContainer,.showSeries .topicStyleContainer{
            display:none;
        }
        .topicStyleContainer,.showSeries  .seriesContainer{
            display:block;
        }

        #topicStyleList{
            width:1000px;
            display: block;
            overflow: auto;
            margin: 0 auto;
        }

        #topicStyleList li{
            width: 50%;
            float: left;
            margin-bottom:20px;
        }




        div.section {
            height: 242px;
            width: 480px;
            margin:0 auto;
            background: #f2f3f7;
        }

        .topicBannerPic{
            height: 185px;
            width: 480px;
        }

        div.section .series-pic {
            width: 100%;
            height: 185px;
            background-color: #f7f9fc;
            cursor: pointer;
            position:relative;
        }

        div.section .series-pic button.removeTopic{
            position:absolute;
            bottom:10px;
            right:10px;
            display:none;
        }

        div.section .series-pic:hover button.removeTopic{
            display:block;
        }
        div.section .series-pic button.previewTopic{
            position:absolute;
            bottom:10px;
            right: 130px;
            display:none;
        }

        div.section .series-pic:hover button.previewTopic{
            display:block;
        }

        div.section .series-pic img {
            width: 100%;
            height: 100%;
        }

        div.section .series-desc {
            overflow: hidden;
            margin: 9px;
            height: 40px;
        }

        div.section .series-desc h2 {
            margin-top:0px;
            font-weight: 400;
            color: #333;
            font-size: 1.2em;
            line-height: 1.8em;
        }
        .topicStyleModal .uploadify{margin-left:130px;}
        .seriesEditorModal .uploadify{margin-left:190px;}

    </style>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody" id="seriesEditor">
        <div id="trace" class="content"></div>
        <input type="hidden"  id="allorgName" value="${org.orgName}" name="allorgName"/>
        <input type="hidden"  id="allorgId" value="${org.orgID}" name="allorgId" />
        <div class="mainContent content showSeries">
            <div class="condition">
                <div class="radio col-sm-6">
                    <label>
                        <input name="tab" type="radio" value="1" aria-label="..." checked="checked">系列专题管理
                    </label>
                    <label>
                        <input name="tab" type="radio" value="0" aria-label="...">专题样式库管理
                    </label>
                </div>
                <div class="form-inline col-sm-6" style="text-align: right;">
                    <div class="form-group">
                        <input class="form-control" type="text"  placeholder="请输入名称…" name="name" value="">
                        <button  class="query btn btn-primary" id="search" type="button">查询</button>
                    </div>
                </div>
                <div style="clear:both"></div>
            </div>
            <div class="seriesContainer">
                <div class="condition" style="padding-left:35px;">
                    <button type="button" class="btn btn-primary foradd" >新增</button>
                </div>
                <table class="table table-striped table-bordered batchOperation " id="seriesList">
                    <tr class="tableTh">
                        <th width="5%"><input type="checkbox" id="selectAll" name="selectAll"></th>
                        <th width="40%">名称</th>
                        <th width="25%">主办单位</th>
                        <th width="10%">发布时间</th>
                        <th width="20%">操作</th>
                    </tr>
                    <tr class="infoRow">
                    </tr>
                </table>

            </div>
            <div class="topicStyleContainer">
                <div class="condition"  style="padding-left:35px;">
                    <button type="button" class="btn btn-primary foradd">新增</button>
                </div>
                <ul id="topicStyleList">
                </ul>
            </div>
            <div class="condition">
                <ul class="pagination-admin" style="float:right"></ul>
            </div>
        </div>
    </div>
</div>
<div class="bottombody"></div>

<!-- 样式库选择器 -->
<div class="remodal normal noBorder normalModal topicSelectModal"  data-remodal-id="topicSelectModal" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close" ></button>
    </div>
    <div class="wrapper common" style="max-height:300px;overflow-y:scroll;">
        <div class="condtion" style="text-align: left;margin: 0px 20px 20px;">
            <button id="selectTopic" class="btn btn-primary">确定</button>
        </div>
        <table class="table table-striped table-bordered" id="topicSelectTable">
            <!-- <tr style="padding-bottom: 10px;">
                <td>
                    <input type="checkbox"></input>
                </td>
                <td>
                    <div class="section">
                      <a class="series-link">
                      <div class="series-pic"><img src="http://www.casmooc.cn/uploadFile/topicBanner/zhuimengzhi.jpg "><input type="hidden" name="url" value="recommendSeriesAction.do?method=intoSeriesItemFrame&amp;seriesId=4"></div>
                      </a><div class="series-desc">
                      <h2 class="title">关注健康 珍爱生命<font>心理所、中关村医院等</font><em></em></h2>
                  </div>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="checkbox"></input>
                </td>
                <td>
                    <div class="section">
                      <a class="series-link">
                      <div class="series-pic"><img src="http://www.casmooc.cn/uploadFile/topicBanner/zhuimengzhi.jpg "><input type="hidden" name="url" value="recommendSeriesAction.do?method=intoSeriesItemFrame&amp;seriesId=4"></div>
                      </a><div class="series-desc">
                      <h2 class="title">关注健康 珍爱生命<font>心理所、中关村医院等</font><em></em></h2>
                  </div>
                </td>
            </tr>
            <tr style="padding-bottom: 10px;">
                <td>
                    <input type="checkbox"></input>
                </td>
                <td>
                    <div class="section">
                      <a class="series-link">
                      <div class="series-pic"><img src="http://www.casmooc.cn/uploadFile/topicBanner/zhuimengzhi.jpg "><input type="hidden" name="url" value="recommendSeriesAction.do?method=intoSeriesItemFrame&amp;seriesId=4"></div>
                      </a><div class="series-desc">
                      <h2 class="title">关注健康 珍爱生命<font>心理所、中关村医院等</font><em></em></h2>
                  </div>
                </td>
            </tr> -->
        </table>
        <div class="condtion">
            <ul class="pagination-admin" style="float:right"></ul>
            <div style="clear:both"></div>
        </div>
    </div>
</div>


<!-- 专题信息编辑 -->
<div class="remodal normal noBorder normalModal seriesEditorModal"  data-remodal-id="seriesEditorModal" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close" ></button>
    </div>
    <div class="wrapper common" >
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-3 control-label">专题名称</label>
                <div class="col-sm-6" >
                    <input type="text"  value='' name ="title" class="form-control" />
                    <input name="seriesId" value="" type="hidden" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">样式选择</label>
                <div class="col-sm-2" >
                    <button id="selectBanner" class="btn btn-info" type="button" >选择</button>
                    <div style="display:none" class="selectedTopicDIV"></div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">组织单位</label>
                <div class="col-sm-6" >
                    <input type="text" id="orgName" value="${org.orgName }" name="orgName" class="form-control" />
                    <input type="hidden" id="orgId" name="orgId" value="${org.orgID }"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">专题详情</label>
                <div class="col-sm-8" >
                    <textarea class="form-control" rows="4" name="detail"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">上传专题图片</label>
                <input type="file" name="file_upload" id="file_upload" class="file_upload"/>
                <input type="hidden" name="mainPicUrl" />
                <div class="col-sm-3"></div>
                <div class="col-sm-6" >
                    <p id="uploadInfo" style="text-align: left;line-height:2em;"></p>
                    <div id="fileQueue"></div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">上传首页专题图片</label>
                <input type="file" name="miniFile_upload" id="miniFile_upload" />
                <input type="hidden" name="picUrl" />
                <div class="col-sm-3"></div>
                <div class="col-sm-6" >
                    <p id="miniFile_uploadInfo" style="text-align: left;line-height:2em;"></p>
                    <div id="miniFileQueue"></div>
                </div>
            </div>
            <div class="form-group">
                <button id="addOrEdit" class="btn btn-primary updateSection addOrEdit" type="button" >提交</button>
            </div>
        </form>
    </div>
</div>

<!-- 设置公开范围 -->
<div class="remodal normal noBorder normalModal openScopeModal"  data-remodal-id="openScopeModal" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close" ></button>
    </div>
    <div class="wrapper common" >
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-3">选择公开范围:</label>
                <div class="col-sm-7" >
                    <input class="openScope" name="seriesId" value="" type="hidden" />
                    <input type="radio" id="fristRadio" name="seriesOpenScope" checked="checked" value="2201"/>不公开
                    <input type="radio" name="seriesOpenScope" value="2202"/>本机构公开
                    <input type="radio" name="seriesOpenScope" value="2203"/>本单位公开
                    <input type="radio" name="seriesOpenScope" value="2204"/>全院公开
                    <input type="radio" name="seriesOpenScope" value="2205"/>完全公开
                </div>
            </div>
            <div class="form-group">
                <button id="publicSeries" class="btn btn-primary updateSection publicSeries" type="button" >发布</button>
            </div>
        </form>
    </div>
</div>


<!-- 专题风格编辑 -->
<div class="remodal normal noBorder normalModal topicStyleModal"  data-remodal-id="topicStyleModal" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close" ></button>
    </div>
    <div class="wrapper common" >
        <form class="form-horizontal topicStyleForm">
            <div class="form-group">
                <input type="hidden" name="bannerId" />
                <label class="col-sm-2 control-label">风格名称</label>
                <div class="col-sm-6" >
                    <input type="text"  value='' class="form-control" name="title"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">内容描述</label>
                <div class="col-sm-8" >
                    <textarea class="form-control" rows="4" name="banner-description"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">专题样式</label>
                <div class="col-sm-8" style="text-align: left;">
                    <label class="checkbox-inline">
                        <input type="checkbox" name="templateClass" value="background-history">历史系
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" name="templateClass" value="background-light">朦胧
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" name="templateClass" value="background-none-blue">淡蓝
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" name="templateClass" value="background-none-red">淡红
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" name="templateClass" value="violetStyle">淡紫
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">封面样式</label>
                <div class="col-sm-8" style="text-align: left;">
                    <label class="checkbox-inline">
                        <input type="checkbox" name="bannerClass" value="standard">标准
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">上传图片</label>
                <input type="hidden" name="bannerPicUrl" />
                <input type="file" name="file_upload1" id="file_upload1" class="file_upload"/>
                <div class="col-sm-2"></div>
                <div class="col-sm-6" style="text-align: left;line-height:2em;">
                    <p id="uploadInfo"></p>
                    <div id="fileQueue"></div>
                </div>
            </div>
            <div class="condition">
                <button class="btn btn-primary updateSection" type ="button">提交</button>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script type="text/javascript" src="/js/swfobject.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.js"></script>
<script type="text/javascript" src="/js/jquery.uploadifive.js"></script>
<script type="text/javascript" src="/js/recommend/seriesEditor.js"></script>
</body>
</html>
