<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page
        import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <base href="<%=basePath%>">

    <title>在线培训</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link rel="stylesheet" type="text/css" href="/css/common/home.css"/>
    <link href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/widget/window.js"></script>

    <style type="text/css">
        .Constants_IS_SINGLE_ANSWER_item {
        }

        .Constants_IS_MULTIPLE_CHOICE_ANSWERS_item {
        }

        .Constants_IS_FILL_IN_THE_BLANK_ANSWERS_space {
            border-top: 0px;
            border-left: 0px;
            border-right: 0px;
        }

        .Constants_IS_QUESTIONS_AND_ANSWERS_answer {
        }

        .Constants_IS_DISCUSSION_ESSAYS_answer {
        }
    </style>

</head>
<body>
<div id="gl_01" style="white-space:normal;word-break:break-all;width:100%">
    ${paperManageErrorMessage}

    <c:if test="${tpaPaperStrategyquestypeFormList != null}">
        <c:set var="questionCount" scope="page" value="0" target=""/>
        <c:set var="typeCount" scope="page" value="0" target=""/>
        <fieldset>
            <table>
                <c:forEach var="tpaPaperStrategyquestype" items="${tpaPaperStrategyquestypeFormList}" varStatus="status">
                    <tr>
                        <td>
                            <div style="font-weight:bold;font-size:13px">
                                <c:set var="typeCount" scope="page" value="${typeCount+1}"/>
                                <c:if test="${typeCount == 1}">一.</c:if>
                                <c:if test="${typeCount == 2}">二.</c:if>
                                <c:if test="${typeCount == 3}">三.</c:if>
                                <c:if test="${typeCount == 4}">四.</c:if>
                                ${tpaPaperStrategyquestype.strategyQuesTypeName}(共有${tpaPaperStrategyquestype.strategyQuesNum}道题，每道题${tpaPaperStrategyquestype.perStrategyQuesScore}分，共计${tpaPaperStrategyquestype.strategyQuesScore}分)
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>

                        <c:forEach var="tpaQuestion" items="${tpaPaperStrategyquestype.questionList}" varStatus="status" >
                            <c:if test="${tpaQuestion != null}">
                                <c:if test="${tpaQuestion.qustioinTypeID == Constants.IS_SINGLE_ANSWER}">
                                    <tr>
                                        <td>
                                            <c:set var="questionCount" scope="page" value="${questionCount+1}"/>
                                            ${questionCount}.${tpaQuestion.qContent}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <c:forEach var="item" items="${tpaQuestion.items}" varStatus="status">
                                                <c:if test="${item != null}">
                                                    <input class="Constants_IS_SINGLE_ANSWER_item" type="radio" name="${tpaQuestion.ID}" value="${item.qItem_name}"/>
                                                    ${item.qItem_name}.${item.qItemContent}
                                                    <br/>
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                    </tr>
                                </c:if>

                                <c:if test="${tpaQuestion.qustioinTypeID == Constants.IS_MULTIPLE_CHOICE_ANSWERS }">
                                    <tr>
                                        <td>
                                            <c:set var="questionCount" scope="page" value="${questionCount+1}"/>
                                                ${questionCount}.${tpaQuestion.qContent}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <c:forEach var="item" items="${tpaQuestion.items }" varStatus="status">
                                                <c:if test="${item != null}">
                                                    <input type="checkbox" class="Constants_IS_MULTIPLE_CHOICE_ANSWERS_item" name="${tpaQuestion.ID}" value="${item.qItem_name}"/>
                                                    ${item.qItem_name}.${item.qItemContent}
                                                    <br/>
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                    </tr>
                                </c:if>


                                <c:if test="${tpaQuestion.qustioinTypeID == Constants.IS_FILL_IN_THE_BLANK_ANSWERS}">
                                    <tr>
                                        <td>
                                            <c:set var="questionCount" scope="page" value="${questionCount+1}"/>
                                            <c:set var="repS" value="<input class='Constants_IS_FILL_IN_THE_BLANK_ANSWERS_space' type='text' name='${tpaQuestion.ID}'/>"/>
                                                ${questionCount}.${fn:replace(tpaQuestion.qContent,"%space%",repS)}<br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                    </tr>
                                </c:if>

                                <c:if test="${tpaQuestion.qustioinTypeID == Constants.IS_QUESTIONS_AND_ANSWERS }">
                                    <tr>
                                        <td>
                                            <c:set var="questionCount" scope="page" value="${questionCount+1}"/>
                                                ${questionCount}.${tpaQuestion.qContent}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <textarea class="Constants_IS_QUESTIONS_AND_ANSWERS_answer" name='${tpaQuestion.ID}' cols="60" rows="8"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                    </tr>
                                </c:if>

                                <c:if test="${tpaQuestion.qustioinTypeID == Constants.IS_DISCUSSION_ESSAYS }">
                                    <tr>
                                        <td>
                                            <c:set var="questionCount" scope="page" value="${questionCount+1}"/>
                                                ${questionCount}.${tpaQuestion.qContent}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <textarea class="Constants_IS_DISCUSSION_ESSAYS_answer" name='${tpaQuestion.ID}' cols="60" rows="8"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                    </tr>
                                </c:if>

                                <c:if test="${tpaQuestion.qustioinTypeID == Constants.IS_PAPER_SIGN}">
                                    <tr>
                                        <td>
                                            ${tpaQuestion.qContent}
                                        </td>
                                    </tr>
                                </c:if>
                            </c:if>
                        </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                </tr>
            </table>
        </fieldset>
    </c:if>

    <c:if test="${tpaPaperStrategyquestypeFormList == null }">
        试卷为空
    </c:if>
</div>
<!-- <div style="text-align:right;float:right;"><br/>
 <a href="javascript:window.close();" class="btn-orange-l"><span class="btn-orange-r">关&nbsp;&nbsp;闭</span></a>
</div> -->
</body>
</html>
					