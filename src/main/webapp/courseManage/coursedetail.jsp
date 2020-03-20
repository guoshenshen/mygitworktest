<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page
        import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher" %>
<%--<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    String userRolesInString = "";
    try {
        EosOperator user = (EosOperator) session
                .getAttribute(Constants.USERINFO_KEY);

    } catch (Exception ex) {
        //do nothing
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- InstanceBegin template="/Templates/admin4.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="培训;在线培训;网络培训" name="keywords"/>
    <!-- InstanceBeginEditable name="doctitle" -->
    <title><%=Constants.systemName%>
    </title>
    <!-- InstanceEndEditable -->

    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link id="styleId" href="../css/style.css" rel="stylesheet" type="text/css"/>
    <link id="style_td_Id" href="../css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="../css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="../css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="../css/uploadify.css"/>
    <link rel="stylesheet" type="text/css" href="../css/uploadifive.css"/>
    <link href="../css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="../css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>


    <style>
        .table_inner {
            border-top-width: 0px;
            border-left-width: 1px;
            border-right-width: 0px;
        }

        table.homecontenttable tr td span {
            line-height: normal;
        }

        table.homecontenttable tr:hover {
            background-color: #ffffff;
        }
    </style>
    <script type="text/javascript">
        function checkPicValues() {

            if (document.getElementById("pictureFile").value == "") {
                alert("缩略图上传地址不能为空!");
                document.getElementById("pictureFile").focus();
                return;
            } else {
                var picName = document.getElementById("pictureFile").value;
                var pickind = picName.substring(picName.lastIndexOf(".") + 1).toLowerCase();
                if (pickind != "jpg" && pickind != "jpeg" && pickind != "gif" && pickind != "png" && pickind != "bmp") {
                    alert("上传图片只能是jpg,jpeg,gif,png,bmp格式!");
                    document.getElementById("pictureFile").focus();
                    return;
                }
            }
            if (document.getElementById("courseUrl") != null)
                document.getElementById("courseUrl1").value = document.getElementById("courseUrl").value;
            return document.getElementById("coursePic").submit();

        }

    </script>
</head>

<body style="background-color:white">
<div id="courseMaterialEditor">
    <div id="mainbody" class="valuediv" style="width:80%;margin:auto">
        <div id="content_02">

            <div class="gl_06_title">
                课程详情
            </div>
            <!--<table width="90%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="#c9e5f1"  bgcolor="#feffff" style="border-collapse: collapse">-->

            <table class="homecontenttable homezonecontentborder" rules="cols" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="2" width="50%">
                        <table height="100%" width="100%" class="homecontenttable homezonecontentborder" cellspacing="0"
                               cellpadding="0">
                            <tr>
                                <td width='40%' align="right">课程编号：
                                </th>
                                <td width='60%' align="left">${course.courseNo}</td>
                            </tr>
                            <tr>
                                <td align="right">课程名称：
                                </th>
                                <td align="left">${course.courseName}
                            </tr>
                            <tr>
                                <td align="right">创建日期：
                                </th>
                                <td align="left">${course.createDateStr}</td>
                            </tr>
                            <tr>
                                <td align="right">创建人：
                                </th>
                                <td align="left" class="table_inner">${course.maker}</td>
                            </tr>
                            <tr>
                                <td align="right">主讲人：</td>
                                <td align="left">${course.creator}</td>
                            </tr>
                        </table>
                    </td>
                    <td colspan="2" width="50%">
                        <div style="margin-bottom:5px" id="courseImage">
                            <img src="${course.pictureUrl}" onerror="imgError({type:0,target:this})" width="90%"
                                 height="135px">
                        </div>
                        <div class="file-box" style="float:left;height:30px">
                            <form class="form-horizontal report" id="coursePicEditor"
                                  style="padding: 0px;box-shadow :#000 0px 0px 0px;border:0px;margin-left: 70px">
                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <input type="hidden" name="courseId" value="${course.courseId}"/>
                                        <input type="file" name="coursePic" id="coursePicUploader"/>
                                        <p id="imgUploadInfo" style="text-align: left;line-height:2em;"></p>
                                        <div id="imgQueue"></div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </td>
                </tr>

                <tr>

                    <td width="20%" align="right">
                        学时：
                    </td>
                    <td width="30%" align="left">${course.classHour}
                    </td>
                    <td width="20%" align="right">
                        专业领域：
                    </td>
                    <td width="30%" align="left">${course.expertAreas}
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        科目分类：
                    </td>
                    <td align="left">${course.categoryName}
                    </td>
                    <td align="right">
                        资源分类：
                    </td>
                    <td align="left">${course.classficationName}
                    </td>

                </tr>
                <tr>
                    <td align="right">
                        课件类别：
                    </td>
                    <td align="left">

                        <c:if test="${course.sliceType == 2}">SCORM课件</c:if>
                        <c:if test="${course.sliceType == 1}">三分屏课件</c:if>
                        <c:if test="${course.sliceType == 3}">单一文档/视频课件</c:if>
                    </td>
                    <td align="right">
                        是否推荐：
                    </td>
                    <td align="left">
                        <c:if test="${course.isNoted == 1}">是</c:if>
                        <c:if test="${course.isNoted == 0 || course.isNoted == null}">否</c:if>
                    </td>
                </tr>

                <tr>
                    <td align="right">
                        是否分院展示：
                    </td>
                    <td align="left" colspan="3">
                        <c:if test="${course.upTenantId == 4}">是</c:if>
                        <c:if test="${course.upTenantId == 0 ||course.upTenantId == null}">否</c:if>
                    </td>
                </tr>
                <tr>
                    <td width="20%" align="right">
                        制作单位：
                    </td>
                    <td width="30%" align="left">${course.produceOrgName}
                    </td>
                    <td width="20%" align="right">
                        资助单位：
                    </td>
                    <td width="30%" align="left">${course.fundingOrgName}
                    </td>

                <tr>
                    <td align="right">
                        关键词：
                    </td>
                    <td colspan="3" align="left">${course.keyWords}
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        适用岗位：
                    </td>
                    <td colspan="3" align="left">${course.courseStations}
                    </td>

                </tr>
                <tr>
                    <td align="right">
                        适用对象：
                    </td>
                    <td colspan="3" align="left">${course.suitableObject}
                    </td>
                </tr>
                <tr style="">
                    <td align="right">
                        主要内容：
                    </td>
                    <td colspan="3" align="left">${course.mainContent}
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        备注：
                    </td>
                    <td colspan="3" align="left">${course.remark}
                    </td>
                </tr>
            </table>
            <div class="clr"></div>
        </div><!-- contentbody -->
    </div><!-- main body -->
</div>
</body>
</html>
