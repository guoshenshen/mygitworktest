<%--
  Created by IntelliJ IDEA.
  User: lixiaoxiong
  Date: 2019/6/21
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程</title>
</head>
<body>
    <h2>课程</h2>
    <h2>当前课程的课程名称为${course.courseName}</h2>

    <div id = "test"></div>
</body>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script type="text/javascript">
    $(function(){
      $.ajax({
          url:"../test/get.do",
          type:"post",
          data:{courseId:${course.courseId}},
          dataType:"json",
          success:function(data){
            if(data.status == 10){
                $("#test").html('<h2>未登录</h2>')
            }else if(data.status == 0){

                var listCourse = data.data.list;

                $("#test").html('<h2>登录成功,查询出来的最后一门课程的课程名称为:'+listCourse[9].courseName+'</h2>')
            }
          }
      })
    })



</script>

</html>
