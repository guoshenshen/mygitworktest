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
    <title>主页1</title>
</head>
<body>
<h2>index主页</h2>
<div id="test"></div>


<div>
    <form action="../test/login.do">
        <input type="submit" value="登陆" >
    </form>
</div>

</body>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script type="text/javascript">
    $(function(){
    })
    
    function login() {
        $.ajax({
            url:"../test/login.do",
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.status == 10){
                    $("#test").html('<h2>未登录</h2>')
                }else if(data.status == 0){
                    $("#test").html("已登陆")
                }
            }
        })
        
    }
</script>
</html>
