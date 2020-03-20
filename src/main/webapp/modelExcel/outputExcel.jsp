<%@page import="com.elearning.common.documentTool.*"%>
<%
    String filename = "";
    if (request.getParameter("file") != null) {
        filename =  request.getParameter("file");
    }
    response.setContentType("application/msexcel");
    response.setHeader("Content-disposition","attachment; filename="+filename);
    ExportFileTool.streamOutFromURL(request.getScheme()+"://"+ request.getServerName()
            + ":" + request.getServerPort()
            +"/modelExcel/" + filename,response.getOutputStream());
%>
