<%@ page import="com.elearning.common.Constants" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>courseware.jsp</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />
        <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
        <style type="text/css">
            body { background:#FFF;}
            TEXTAREA.content {
                OVERFLOW: visible;
            }
        </style>
        <script type="text/javascript" src="../js/common/texterea.js"></script>
        <script language=javascript>
            /****************************************************************************
             **
             ** Function: LMSIsInitialized()
             ** Input:   none
             ** Output:  boolean
             **
             ** Description:  This function returns a boolean that represents where or
             **               no LMSInitialize() has been called by the SCO.
             **
             ***************************************************************************/
            function GetScoId()
            {
                // Determines if the API (LMS) is in an initialized state.
                // There is no direct method for determining if the LMS API is initialized
                // for example an LMSIsInitialized function defined on the API so we'll try
                // a simple LMSGetValue and trap for the LMS Not Initialized Error
                var api = getAPIHandle();
                if (api == null)
                {
                    // alert("Unable to locate the LMS's API Implementation.\nLMSFinish was not successful.");
                    return "false";
                }
                else
                {
                    // call the LMSFinish function that should be implemented by the API
                    var value = API.LMSGetValue("cmi.core.student_name");
                    alter("the name is"+value);
                    var scoId = API.getSCOID();
                    alter("the scoId is"+scoId);
                }
            }
            function _submit(){
                if(document.getElementById("textarea").value != ""&&document.getElementById("textarea").value.length>999)
                {
                    alert("笔记内容字符总数不能多于1000字!");
                    document.getElementById("textarea").focus();
                    return;
                }else return document.getElementById("form1").submit();

            }
        </script>
    </head>
    <body >
    <c:if test="${preMode != 1}"   >
        <form   name="form1" id="form1" action="../noteBook.do?method=insert" method="post">
            <table width="90%" border="0">
                <tr>
                    <input type="hidden" name="id"    value=${courseNoteBook.id}   >
                    <td>
                    <textarea style='border: 1px solid #94BBE2;width:100%;overflow-y:visible'   id=textarea   name="noteBookContent"  onKeyDown="gbcount(this,1000);" onKeyUp="gbcount(this,1000);">${courseNoteBook.noteContent}</textarea>
                    </td>
                </tr>
                <tr align="right">
                    <td>
                        <a href="javascript:_submit();" ><img src="../image/images_xy/button5.jpg" hspace="10" border="0" /></a>
                    </td>
                </tr>
            </table>
        </form>
    </c:if>
    </body>
</html>
