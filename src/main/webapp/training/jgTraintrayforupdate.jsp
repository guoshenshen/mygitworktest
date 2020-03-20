<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page
		import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/admin4.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="培训;在线培训;网络培训" name="keywords" />
<!-- InstanceBeginEditable name="doctitle" -->
<title><%=Constants.systemName%></title>
<!-- InstanceEndEditable -->
<link id="styleId" href="<%=Constants.SKINDIR%>/style.css" rel="stylesheet" type="text/css" />

<link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />  
<link id="style_td_Id"  href="<%=Constants.SKINDIR%>/style_td.css" rel="stylesheet" type="text/css" />
<link id="style_gl_Id" href="<%=Constants.SKINDIR%>/style_gl.css" rel="stylesheet" type="text/css" />
<link href="./css/pop_window.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/common/tools.js"></script>
<script type="text/javascript" src="./js/common/home.js"></script>
<script type="text/javascript" src="./ueditor-new/ueditor.config.js"></script>
<script type="text/javascript" src="./ueditor-new/ueditor.all.js"></script> 
	<script type="text/javascript">
		var djConfig = {isDebug: false};
	</script>


<script type="text/javascript" src="./js/dojojs/dojo.js"></script>

<script type="text/javascript" src="./js/jquery-latest.js"></script>
<script type="text/javascript" src="./js/jquery-json.js"></script>
<script type="text/javascript" src="./js/nav/menu.js"></script>
<script type="text/javascript" src="./js/nav/roll.js"></script>
<script type="text/javascript" src="./js/jquery.blockUI.js"></script>
<link href="./css/pop_window.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="./js/My97DatePicker/WdatePicker.js"></script>
<!-- InstanceBeginEditable name="head" -->
<script type="text/javascript" src="./js/widget/window.js"></script>
<script type="text/javascript" src="./js/widget/classify.js"></script>
<script language="javascript" type="text/javascript" src="./js/CheckFunction.js"></script>

	<script type="text/javascript">
		//var djConfig = {isDebug: false};
	</script>
	<script type="text/javascript" >
		dojo.require("dojo.widget.*");
	</script>
	<script type="text/javascript" src="<%=basePath%>js/common/home.js"></script>

<script type="text/javascript">
	dojo.require("dojo.lang.*");
	dojo.require("dojo.widget.*");
				$(function(){
				var option = {
				initialFrameWidth:480,
			   initialFrameHeight:250,
			   initialStyle: 'p{line-height:20px;font-size:12px;font-family:宋体};span{font-size:12px;font-family:宋体;line-height:20px}'
			    
			};
                editor = new UE.ui.Editor(option);
                editor.addInputRule(function(root){
	            root.traversal(function (node) {
	                if (node.type == 'element') {
	
	                    switch (node.tagName) {
	                        case 'span':
	                        	 node.setStyle({'font-size':'12px',"font-family":"宋体","line-height":"20px"});
	                        case 'p':
	                             node.setStyle({'font-size':'12px',"font-family":"宋体","line-height":"20px"});
	                        case 'div':
	                             node.setStyle({'font-size':'12px',"font-family":"宋体"});
	                        case 'li':
	                        	 node.setStyle({'font-size':'12px',"font-family":"宋体"});
	                        case 'table':
	                             node.setStyle({'font-size':'12px',"font-family":"宋体"});
	                    }
	
	                }
	
	            })
	
	        });
                editor.render('comment');
		})
		
	$(function(){
            
            	$("input[name=classHour]").blur(function(){
            		var time=$(this).val();
	            	if(time<1){
	            	//培训学时不得少于4个学时
	            		alert("学时不得少于1小时");
	            		$(this).val(4.0);
	            	}
            	});
                $("input[name=ifBJ]").click(function(){
                	if($(this).val()=="1"){
                		//选择外埠，具体地址为必填项

                		$("#_location").css({"display":"none"});
                	}
                	else{
                		//选择本地的，具体地址不是 必填项
                		$("#_location").css({"display":"inline"});
                	}
                });
                $("input[name=casSupport]").click(function(){
                	if($(this).val()=="0"){
                		$("input[name=isNoted]:first").prop("checked",true);
                		$("#_isNoted").css({"display":"none"});
                	}
                	else{
                		$("input[name=isNoted]:last").prop("checked",true);
                		$("#_isNoted").css({"display":"inline"});
                	}
                });
                
                 $("input[name=isNoted]").click(function(){
                	if($(this).val()=="0"){
                		$("input[name=casSupport]:first").prop("checked",true);
                		$("#_isNoted").css({"display":"none"});
                	}
                	else{
                		$("input[name=casSupport]:last").prop("checked",true);
                		$("#_isNoted").css({"display":"inline"});
                	}
                });
    })
    
   	function insertOrUpdate(){
   		if(document.getElementById("trainName").value == "")
   		{
   			alert("培训名称不能为空!");
   			document.getElementById("trainName").focus();
   			return;
   		}
   		
   //		if(document.getElementById("_trainId").value == "")
   	//	{
   	//		alert("培训编号不能为空!");
   	//		document.getElementById("_trainId").focus();
   //			return ;
   //		}
   		if(document.getElementById("year").value == "")
   		{
   			alert("培训年度不能为空!");
   			document.getElementById("year").focus();
   			return ;
   		}
   		if(document.getElementById("year").value!=""&&!is_numeric(document.getElementById("year"),1,"请填写正确格式的培训年度"))
	   	{
	   		document.getElementById("year").focus();
	   		return;
	  	} 	
   		
<%--   		var length = document.getElementsByName("isPlaned").length;--%>
<%--   		var isPlaned_flag = 0;--%>
<%--   		for(var i=0;i<length;i++){--%>
<%--   			if(document.getElementsByName("isPlaned")[i].checked   ==   true)--%>
<%--   			isPlaned_flag =1;--%>
<%--   		}--%>
<%--   		if(isPlaned_flag == 0)--%>
<%--   		{--%>
<%--   			alert("请选择是否计划内培训!");--%>
<%--   			return;--%>
<%--   		}--%>
///		if(!radioIsSelect("isStationTrain")){
 //  			alert("请选择培训形式!");
 //  			return;
 //  		}
		if(document.getElementById("trainTypeId").options!=null){
	   		var length = document.getElementById("trainTypeId").options.length; 		
			var options_flag = 0;
			for(var i=0;i<length;i++){
				if(document.getElementById("trainTypeId").options[i].selected&&document.getElementById("trainTypeId").options[i].value>0)
				options_flag =1;
			}
			if(options_flag == 0)
			{
				alert("请选择培训类别!");
				return;
			}	
		
		}
		//if(document.getElementById("creamProject").options!=null){
		//	var length = document.getElementById("creamProject").options.length;
		//	options_flag = 0
		//	for(var i=0;i<length;i++){
		//		if(document.getElementById("creamProject").options[i].selected&&document.getElementById("creamProject").options[i].value>0)
		//		options_flag =1;
		//	}
		//	if(options_flag == 0)
		//	{
		//		alert("请选择培训项目分类!");
		//		return;
		//	}
		//}
		//if(document.getElementById("trainMode").options!=null){
		//	var length = document.getElementById("trainMode").options.length;
		//	options_flag = 0
		//	for(var i=0;i<length;i++){
		//		if(document.getElementById("trainMode").options[i].selected&&document.getElementById("trainMode").options[i].value>0)
		//		options_flag =1;
		//	}
		//	if(options_flag == 0)
		//	{
		//		alert("请选择培训方式类别!");
		//		return;
		//	}				
		//}
 
   		
  //   	var length = document.getElementsByName("subTrainTypeID").length;
   //		var subPlanType_flag = 0;
   //		for(var i=0;i<length;i++){
  // 			if(document.getElementsByName("subTrainTypeID")[i].checked   ==   true)
  // 			subPlanType_flag =1;
  // 		}
  // 		if(subPlanType_flag == 0)
  // 		{
  // 			alert("请选择培训子类别!");
  // 			return;
  // 		}
   		 		
   		if(document.getElementById("classHour").value == "")
   		{
   			alert("学时不能为空!");
   			document.getElementById("classHour").focus();
   			return;
   		}
   		if(document.getElementById("classHour").value!=""&&!is_greaterZero(document.getElementById("classHour"),1,"学时只能是大于0的实数")) 
   		{
   			document.getElementById("classHour").focus();
   			return;
   		} 	    
   		
       	if(document.getElementById("days").value == "")
   		{
   			alert("培训天数不能为空!");
   			document.getElementById("days").focus();
   			return;
   		}
   		if(document.getElementById("days").value!=""&&!is_greaterZero(document.getElementById("days"),1,"培训天数必须大于0")) 
   		{
   			document.getElementById("days").focus();
   			return;
   		}
   		
	    		
	    var days=$("#days").val();
	    var classHour=$("#classHour").val();
		if(classHour/days<4||classHour/days>8){
			alert("日平均培训时长请控制在4-8学时!");
			return;
		}
	    		
   		
   		if(document.getElementById("attendantCount").value == "")
   		{
   			alert("参加人数不能为空!");
   			document.getElementById("attendantCount").focus();
   			return;
   		}
   		if(document.getElementById("attendantCount").value!=""&&!is_greaterZeroInt(document.getElementById("attendantCount"),1,"计划参加人数只能是正整数")) 
   		{
   			document.getElementById("attendantCount").focus();
   			return;
   		}
   		
   		if(document.getElementById("researchPostNum").value == "")
   		{
   			alert("科研岗人数不能为空!");
   			document.getElementById("researchPostNum").focus();
   			return;
   		}
   		if(document.getElementById("researchPostNum").value!=""&&!is_greaterInt(document.getElementById("researchPostNum"),1,"科研岗人数只能是非负整数",-1)) 
   		{
   			document.getElementById("researchPostNum").focus();
   			return;
   		}
   		
   		if(document.getElementById("managePostNum").value == "")
   		{
   			alert("管理岗人数不能为空!");
   			document.getElementById("managePostNum").focus();
   			return;
   		}
   		if(document.getElementById("managePostNum").value!=""&&!is_greaterInt(document.getElementById("managePostNum"),1,"管理岗人数只能是非负整数",-1)) 
   		{
   			document.getElementById("managePostNum").focus();
   			return;
   		}
   		
   		if(document.getElementById("supportPostNum").value == "")
   		{
   			alert("支撑岗人数不能为空!");
   			document.getElementById("supportPostNum").focus();
   			return;
   		}
   		if(document.getElementById("supportPostNum").value!=""&&!is_greaterInt(document.getElementById("supportPostNum"),1,"支撑岗人数只能是非负整数",-1)) 
   		{
   			document.getElementById("supportPostNum").focus();
   			return;
   		}
   		
   		if(document.getElementById("outSideNum").value == "")
   		{
   			alert("院外人员数不能为空!");
   			document.getElementById("outSideNum").focus();
   			return;
   		}
   		if(document.getElementById("outSideNum").value!=""&&!is_greaterInt(document.getElementById("outSideNum"),1,"院外人数只能是非负整数",-1)) 
   		{
   			document.getElementById("outSideNum").focus();
   			return;
   		}
   		
   		if(document.getElementById("workerNum").value == "")
   		{
   			alert("工作人数不能为空!");
   			document.getElementById("workerNum").focus();
   			return;
   		}
   		if(document.getElementById("workerNum").value!=""&&!is_greaterInt(document.getElementById("workerNum"),1,"工作人数只能是非负整数",-1)) 
   		{
   			document.getElementById("workerNum").focus();
   			return;
   		}
   		
   		if($("input[name=ifBJ]:checked").length==0){
   			alert("请选择培训地点为本地或者外埠!");
			return ;
   		}

   		if($("input[name=ifBJ]:checked").val()=="0"&&document.getElementById("location").value==""){
			alert("选择外埠的,具体地点不得为空!");
			document.getElementById("location").focus();
			return ;
		}
   		
   		 
   		if(document.getElementById("startTime").value == "")
   		{
   			alert("培训开始时间不能为空!");
   			document.getElementById("startTime").focus();
   			return;
   		}
   		if(document.getElementById("endTime").value == "")
   		{
   			alert("培训结束时间不能为空!");
   			document.getElementById("endTime").focus();
   			return ;
   		}
   		if(compare_date(document.getElementById("startTime").value,document.getElementById("endTime").value)){
	    			alert("培训开始时间不能大于培训结束时间!");
	    			return ;
	    }
   		if(document.getElementById("attendantCount").value!=""&&!is_greaterZeroInt(document.getElementById("attendantCount"),1,"计划参加人数只能是大于0的整数")) return;
	    if(document.getElementById("workerNum").value == "")
   		{
   			alert("工作人员数不能为空!");
   			document.getElementById("workerNum").focus();
   			return;
   		}else{
			if($("#workerNum").val()>10||$("#workerNum").val()>$("#attendantCount").val()/5){
	   			alert("工作人员数不能超过10人，且不能超过总参训人数的5%!");
	   			document.getElementById("workerNum").focus();
	   			return;					
			}
   		}	
   		if(document.getElementById("feeChannel").value == "")
   		{
   			alert("列支渠道不能为空!");
   			document.getElementById("feeChannel").focus();
   			return;
   		}
   		if(document.getElementById("fee").value == "")
   		{
   			alert("所需经费不能为空!");
   			document.getElementById("fee").focus();
   			return;
   		}

   		if(document.getElementById("fee").value!=""){
			var attendantCount = document.getElementById("attendantCount").value;
				var fee = document.getElementById("fee").value;
				if(isNaN(parseFloat(fee))){
					alert("经费开支只能为非负数字");
   					document.getElementById("fee").focus();
   					return;
				}
				if(parseFloat(fee)<0){
				    alert("经费开支不得小于0!");  
					document.getElementById("fee").focus();
					return;
				}
				var days = document.getElementById("days").value;
				if((parseInt(attendantCount)*550.00001*parseFloat(days)<parseFloat(fee)*10000))
				{
					alert("超出人均每日最高培训经费550元标准!");
					document.getElementById("fee").focus();
					return;
				}	   
		}
   		if(!$("input[name=stationId]:checked").val()){
				alert("请选择适用岗位");
				return;
		}	
		if(($("textarea[name=attendants]").val().length==0||$("textarea[name=attendants]").val().length>300)){
				alert("请填写培训对象,字数不超过300");
				return;
		}
		if(($("textarea[name=trainGoal]").val().length<10||$("textarea[name=trainGoal]").val().length>300)){
				alert("请填写培训目的,字数10-300");
				return;
		}
		if($("textarea[name=trainingContent]").val().length<10||$("textarea[name=trainingContent]").val().length>300){
				alert("请填写培训内容,字数10-300");
				return;
		}
		
		if(document.getElementById("keyWordsTag").value==""){
 		   alert("培训关键词不能为空!");
 		   document.getElementById("keyWordsTag").focus();
 		   return;
 		}
		 
		if(document.getElementById("_orgsname").value == "")
 		{
   			alert("主办单位不能为空!");
  		    document.getElementById("_orgsname").focus();
   			return;
  		}	
		if(document.getElementById("organizerName").value == "")
		{
			alert("联系人不能为空!");
			document.getElementById("organizerName").focus();
			return;
		}		
		  
		
		if(($("#telephone").val().length>15||$("#telephone").val().length==0))
		{
			alert("请输入正确的电话号码!");
			document.getElementById("telephone").focus();
			return;
		}	

		if(document.getElementById("organizerEmail").value == "")
		{
			alert("联系人Email不能为空!");
			document.getElementById("organizerEmail").focus();
			return;
		}	
		
		var pattern =/^[a-zA-Z0-9_\-\.]{1,}@[a-zA-Z0-9_\-]{1,}\.[a-zA-Z0-9_\-.]{1,}$/;  
		if(document.getElementById("organizerEmail").value != ""&&!pattern.exec(document.getElementById("organizerEmail").value))
		{
			alert("请输入正确的邮箱地址!");
			document.getElementById("organizerEmail").focus();
			return;
		}
		
   		

 //  		if(document.getElementById("organizerName").value == "")
 //  		{
 //  			alert("联系人不能为空!");
 //  			document.getElementById("organizerName").focus();
  // 			return;
  // 		}	   		
   		var length = document.getElementsByName("isEnrolled").length;
   		var isEnrolled_flag = 0;
   		for(var i=0;i<length;i++){
   			if(document.getElementsByName("isEnrolled")[i].checked==true)
   			isEnrolled_flag =1;
   		}
   		if(isEnrolled_flag == 0)
   		{
   			alert("请选择是否允许报名!");
   			return;
   		}
		if(document.getElementsByName("isEnrolled")[0].checked==true){
	   		if(document.getElementById("programStartTime").value == "")
	   		{
	   			alert("培训报名开始时间不能为空!");
	   			document.getElementById("programStartTime").focus();
	   			return;
	   		}
	   		if(document.getElementById("programEndTime").value == "")
	   		{
	   			alert("培训报名结束时间不能为空!");
	   			document.getElementById("programEndTime").focus();
	   			return ;
	   		}		
	   		if(compare_date(document.getElementById("programStartTime").value,document.getElementById("programEndTime").value)||document.getElementById("programStartTime").value==document.getElementById("programEndTime").value){
	    		alert("培训报名开始时间不能等于或晚于培训报名结束时间!");
	    		return ;
	    	}
	    	if(compare_date(document.getElementById("programEndTime").value,document.getElementById("endTime").value)){
	    		alert("培训报名结束时间不得晚于培训结束时间");
	    		return
	    	}
	    	
			var length = document.getElementsByName("isNeedCheck").length;
	   		var isNeedCheck_flag = 0;
	   		for(var i=0;i<length;i++){
	   			if(document.getElementsByName("isNeedCheck")[i].checked==true)
	   			isNeedCheck_flag =1;
	   		}
	   		if(isNeedCheck_flag == 0)
	   		{
	   			alert("请选择报名是否需要审批!");
	   			return;
	   		}			
		}  

		
		
		
	    // var telpattern = /(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^[0−9]3,4[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)|(13\d{9}$)|(15[0135-9]\d{8}$)|(18[267]\d{8}$)/;
	    // if(document.getElementById("telephone").value != ""&&!telpattern.exec(document.getElementById("telephone").value))
   		// {
   		//	alert("请输入正确的电话号码!");
   		//	document.getElementById("telephone").focus();
   		//	return;
   		// } 
	   	
	      	  		
	//	if(document.getElementById("programNo").value!=""&&!is_greaterZeroInt(document.getElementById("programNo"),1,"期号只能是正整数")) return;

		
   		document.getElementById("form1").submit();
   		
   	}
	function radioIsSelect(radioname){
			var radiogroup = document.getElementsByName(radioname); 
   			for(var i=0;i<radiogroup.length;i++){ 
				if(radiogroup[i].checked==true){
					return true;
				} 
			} 
   			return false;
   		
   		}
         



   function getSubTrainTypeList(trainTypeId)
   {
		dojo.io.bind({
			   url: 'onlineTraining.do?method=getSubTrainType',
			   content:{trainTypeId:trainTypeId},
			   method:'POST',
			   handler: getSubTrainTypeCallback
			});
   }
	
	function getSubTrainTypeCallback(type, data, evt)	
	{		
		if (type == 'error')
		{
			alert('读取服务器数据时出错');
		}else{
			document.getElementById("subTrainTypeID").length=0;   //初始化下拉列表，清空下拉数据 
		//	document.getElementById("subTrainType").options[0]=new Option('请选择','0'); //给第一个值 		
			if(data!=null&&$.parseJSON(data).length>0){					
				var nodeArr =$.parseJSON(data);			
				for (var i = 0 ; i < nodeArr.length ; i++)
				{	
					document.getElementById("subTrainTypeID").options[document.getElementById("subTrainTypeID").length]=new Option(nodeArr[i].subTrainTypeName,nodeArr[i].code);   //建立option 
				}	
			}
		}
	
	}
   function switchTrainWay()
   {
    	
    	var way = document.getElementById('trainWay').value;
       	
    	if(way==0) 
		{
			document.getElementById('isStationTrainRow').style.display="block";
		}else
		{
			document.getElementById('isStationTrainRow').style.display="none";
			document.getElementById('isStationTrain1').checked=false;
			document.getElementById('isStationTrain0').checked=true;
		}
    }

	   
	   function isplan()
	   {
	   document.getElementById('isplanrow').style.display="block";
	   }
		function noplan()
		{	
			   document.getElementById('isplanrow').style.display="none";
			   document.getElementById('trainPlanID').value="-1";
			   document.getElementById('trainPlanName').value="";
			var pop_url="<%=basePath%>orgPlanAction.do?method=foradd&fortrain=1";	
 				var pop=$("<div id='_pop_win'><h2>创建培训计划"
						+"<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
						+"<iframe height='500' width='800' scrolling='auto' class='pop_iframe' src='"+pop_url+"'> </iframe></div>"
						+"<div style='clear:both'></div>"
					);
			pop.find("a.pop_close_btn").click(function(){
				$.unblockUI();
			});
			$.blockUI({ message: pop,
					    css: {width:"800px",height:"540px",cursor:"default",left:($left-800)/2-10,top:($top-560)/2-10}
			});
		}	
	   function register(openScope){
	      if(openScope==2201){
	      	  $("#isEnrolledNo:last").attr("checked","checked");	
	   	      document.getElementById('register').style.display="none";
	   	      document.getElementById('programrow').style.display="none";	   	      
	   	  }else{
		   	  document.getElementById('register').style.display="";   
	   	      document.getElementById('programrow').style.display="";
	   	  }		
	  }
		function selectdepts() {
			var pop_url="<%=basePath%>pub/checkboxOrgTree.html";
			var pop=$("<div id='_pop_win'><h2>选择机构名称"
					+"<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
					+"<iframe height='400' scrolling='auto' width='100%' class='pop_iframe' src='"+pop_url+"'> </iframe></div>"
					+"<div style='clear:both'></div>"
					);
			pop.find("a.pop_close_btn").click(function(){
				$.unblockUI();
			});
			$.blockUI({ message: pop,
					    css: {width:"320px",height:"550px",cursor:"default",left:($left-320)/2-10,top:($top-550)/2-10}
				 });
		}   
	
	   function isEnroll()
	   {
	
	      document.getElementById('isNeedCheck').style.display="block";
	      document.getElementById('programrow').style.display="";
	      document.getElementById('isNeeds').style.display="block";
	   }
	   
	   function noEnroll(tear)
	   {	  
	   
		if(tear==0)
		{
		   document.getElementById('programrow').style.display="none";
		}
		else{
	      document.getElementById('isNeedCheck').style.display="none";
	      document.getElementById('programrow').style.display="none";
	      document.getElementById('isNeeds').style.display="none";	      
	      }
	   }
	   
	   function noEnrollValue()
	   {
	 
	      document.getElementById('isNeedCheck').style.display="none";
	      document.getElementById('programrow').style.display="none";
	      document.getElementById('isNeeds').style.display="none";
	      
	      var isCheck=   document.getElementsByName('isNeedCheck');
	      for(var i=0;i<isCheck.length;i++)  
          {    
               if(isCheck[i].type=="radio")  
              {  
          
				  if(isCheck[i].checked)    
				  isCheck[i].checked=false;  
               }   
           }
		   if(dojo.widget.byId("programStartTime").inputNode.value!=null)
				dojo.widget.byId("programStartTime").inputNode.value="";
      		document.getElementsByName("programStartTime")[0].value="";
     
          
      	 	if(dojo.widget.byId("programEndTime").inputNode.value!=null)
        		dojo.widget.byId("programEndTime").inputNode.value="";   
			document.getElementsByName("programEndTime")[0].value="";
	   }
	   
	  function isEnrollValue()
	  {
	      document.getElementById('isNeedCheck').style.display="block";
	      document.getElementById('programrow').style.display="";
	      document.getElementById('isNeeds').style.display="block";
	   }
		    
	    
		function trainPlan()
		{	
		//	var tempPlan=document.getElementById("trainPlanName");
		//	openPlanTrainSelect();		
					var pop_url="<%=basePath%>orgPlanAction.do?method=queryOrgPlanList";	
 				var pop=$("<div id='_pop_win'><h2>相关培训计划"
						+"<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
						+"<iframe height='500' width='800' scrolling='auto' class='pop_iframe' src='"+pop_url+"'> </iframe></div>"
						+"<div style='clear:both'></div>"
					);
			pop.find("a.pop_close_btn").click(function(){
				$.unblockUI();
			});
			$.blockUI({ message: pop,
					    css: {width:"800px",height:"540px",cursor:"default",left:($left-800)/2-10,top:($top-560)/2-10}
			});
		}
		
		function judgeRoot(tag){
		   if(tag==1) window.location.href="<%=basePath%>sponsortraining/sponsorTrainingHome.jsp";
		   
		   else window.location.href="<%=basePath%>onlineTraining.do?method=searchTrain&research=true&trainWay=0";
		}
		
		function judgeModify(tag){
		
		  if(tag==1){}
		   
		   else window.location.href="<%=basePath%>onlineTraining.do?method=addTrainForAll";
		}
		function toObtainTrainPlans(id,name)
		{
			if(confirm("确定要按此计划来实施培训吗?\n如果选'确定'，本页相关字段内容将以此计划内容来填充!"))
			{
				document.getElementById("trainPlanID").value=id;
				document.getElementById("trainPlanName").value=name;
				//alert(id+","+name+","+openScope);
				document.getElementById("form1").action = "<%=basePath%>onlineTraining.do?method=loadTrainByPlan";
				document.getElementById("form1").submit();
			}
		}	
		function checkTrainOrg() {
			if(document.getElementsByName('orgId')[0].value==-1)
				alert("请选择'培训创建部门'！");
			else
				document.getElementById('form1').submit();
		}
		function gbcount(message) 
		{ 
			var max; 
			max = 400; 
			if (message.value.length > max) 
			{ 
				message.value = message.value.substring(0,max); 
				alert("内容不能超过 400 个字!"); 
			} 		
		} 
		function stationtrain(){
			document.getElementById('isEnrolledYes').checked="false";
			document.getElementById('isEnrolledYes').disabled="true";
			document.getElementById('isEnrolledNo').checked="checked";
			noEnroll();
		}
		function stationtrainno(){
			document.getElementById('isEnrolledYes').disabled=false;
		}
		function cleardepts()
		{	
			document.getElementById("orgsname").value = "";
			document.getElementById("orgsid").value = "";		
		}
		function clearusers()
		{
			document.getElementById("organizerName").value = "";
			document.getElementById("organizerId").value = "";
		
		}
		function toObtainTrainPlans(id,name)
		{
			if(confirm("确定要按此计划来实施培训吗?\n如果选'确定'，本页相关字段内容将以此计划内容来填充!"))
			{
				document.getElementById("trainPlanID").value=id;
				document.getElementById("trainPlanName").value=name;
				document.getElementById("form1").action = "<%=basePath%>onlineTraining.do?method=loadTrainByPlan";
				document.getElementById("form1").submit();
			}
		}
		
	</script>
<script type="text/javascript">
  function _setSelected(_this,itemId){
	    var id = _this.id;
	    var checkTemp=_this.checked;
	    if(checkTemp){
	       $("#"+id).checked = true;
	       $.ajax({
	               url:'onlineTraining.do?method=updateTrainTipItemStatus',
	               type:"post",
                   dataType:"text",
	               data:"itemid="+itemId+"&flag=true",
	               success:function(){}
	               })
	    }else{
	       $("#"+id).checked = false;
	       $.ajax({
	               url:'onlineTraining.do?method=updateTrainTipItemStatus',
	               type:"post",
                   dataType:"text",
	               data:"itemid="+itemId+"&flag=false",
	               success:function(){}
	               })
	             }
          }
</script>

<!-- InstanceEndEditable -->
</head>
<body>
<div id="pagebody">
<div id="topbody">
<div id="navbar">
  <div id="rolebar">
  <div id="_juese"></div>
   <ul>
   </ul>
  </div>
  <div class="firstRow">培训年度：<%=session.getAttribute(Constants.YEAR_KEY)%> |  当前用户：<%=((Eosoperator)session.getAttribute(Constants.USERINFO_KEY)).getOperatorName()%>
  <a class="_exitSystemCss" href="exitSystem.do">&nbsp;退出</a>
</div>
</div>
<div id='org_Logo'><!--<img src="<%=Constants.CUSTOMELEARNINGLOGO%>" width='60px' height='50px'/>--></div>
<!-- <div id='org_Name'><%=Constants.CUSTOMELEARNINGNAME%></div>-->
<div id="menubody">
   <ul id="menu1"></ul>
</div>
</div>

<div id="location1"></div>
<div id="mainbody" class="valuediv">
<div id="trace">
</div>
<div id="funcCon">
<div id="conTop">
<span class="funcTitle" style="cursor:pointer;" onclick="window.location.href='onlineTraining.do?method=fordetail'">${train.trainName}</span>
<div id="funcCheck">
<div id="selectBox">
<div id="selectImg">
<div id="selectText">功能管理</div>
</div>
<ul id="selectMenu">

</ul>
</div>
</div>
</div>
<div id="conBottom">
<img class="barL" src="./image/arrows4l.png"/>
<div class='thirdMenu'>
<ul>
</ul>
</div>
<img class="barR" src="./image/arrows4r.png"/>
<div style="clear:both;"></div>
<div class="scrollBarWapper">
<div class="scrollBar">
        <div class="barM">
            <div class="bar">
                <div class="l"></div>
                <div class="r"></div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
<div id="mainbody">
    <!-- InstanceBeginEditable name="main" -->
     <div id="contentbody">
	<div id="content_02">
     <form id="form2" method="post" action="" class="gl_06" style="border:none;width:800px">
	      <br /><logic:present name="currentTrainTipList">
	      <table class="table0" width="100%" cellspacing="0" cellpadding="0">
	      <tr  class="tableTh" bgcolor="#f1f1f1">

	        <td colspan="4" align="left" bgcolor="#c9e5f1" style="font-size:13px;"><strong>${trainTipName}</strong>提醒您:<font color=red>[&nbsp;${train.trainName}&nbsp;]今天需关注如下内容</font></td>
	      </tr>
	         <logic:iterate id="mtTipItem" name="currentTrainTipList" type="cn.kepu.elearning.bean.mixtraining.MtMixtrainingtipItem" scope="request">
	      <tr>
	      
	        <td style="border:0px">
	           <logic:equal name="mtTipItem" property="status" value="1">
	             <input type="checkbox" id="itemId_<bean:write name="mtTipItem" property="id"/>" name="selectAll"  onclick="_setSelected(this,<bean:write name="mtTipItem" property="id"/>)" checked/> 
	           </logic:equal> 
	           <logic:equal name="mtTipItem" property="status" value="0">
	             <input type="checkbox" id="itemId_<bean:write name="mtTipItem" property="id"/>"  name="selectAll" onclick="_setSelected(this,<bean:write name="mtTipItem" property="id"/>)" /> 
	           </logic:equal> 
	       <br /></td>
	           <td  align="left" colspan='3' style="border:0px"><bean:write name="mtTipItem" property="itemName"/></td>
	      </tr>
	      </logic:iterate>
	      </table>
	       </logic:present> 
	 </form>
	  <form id="form1" method="post"
							action="onlineTraining.do?method=update"
							name="onlinetrainingbasicinfo" class="gl_06" style="border:none;width:800px">
	    <input type="hidden" name="trainID" id="trainID" value="${trainID}" />
	    <input type="hidden" name="filterFlag" value="false"/>    
	    <input name="programNo" type='hidden' value="${train.programNo}" />
		<input name="topbandId" type='hidden' value="${train.topbandId}" />
		<input name="iconId" type='hidden' value="${train.iconId}" />
		<input name="trainId" type='hidden' value="${train.trainId}" />
		<input name="executePlan" type="hidden" value="${executePlan}" />
	 <table class="table0" width="100%" cellspacing="0" cellpadding="0" >
	    
	      <tr bgcolor="#f1f1f1">
	        <td colspan="4" align="left" bgcolor="#c9e5f1" style="font-size:13px;"><strong>基本信息</strong></td>
	      </tr>
	      <tr>
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='trainName' pageType='0'>  
				<input name="trainName" id="trainName" class="textInput"  type="text" value="${train.trainName}" size="60" maxLength="60"/><font color=red>&nbsp;*</font>
			</eln:td>
			<input name="year" class="textInput" id="year" type="hidden" value="${train.year}" size="4" maxLength="4" style="width: 148px;"/><font color=red>&nbsp;*</font>       
	      </tr>
	      <tr>
	      	<th width="20%" align="right">培训形式</th>
			<td>	      
				<select name="trainMode" id="trainMode" style="width: 154px;" class="selectStyle">
		            <option value="-1" > 请选择 </option>
		            <logic:iterate id="trainMode" name="trainModeList" type="cn.kepu.elearning.bean.pub.Ddictionary">
		              	<option value="${trainMode.code}" <logic:equal name="trainMode" property="code" value="${train.trainMode}">selected</logic:equal>> ${trainMode.name} </option>
		            </logic:iterate>
	          	</select>	
				<span id='_trainMode' style="color:red;">&nbsp;*</span>	
			</td>	
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='trainTypeId' pageType='0'> 
				<select name="trainTypeId" id="trainTypeId" class="selectStyle"  style="width: 148px;">
		            <option value="-1" > 请选择 </option>
		            <logic:iterate id="trainType" name="trainTypeList"
														type="cn.kepu.elearning.bean.pub.Ddictionary">
		              <option value="${trainType.code}" <logic:equal name="train" property="trainTypeId" value="${trainType.code}">selected</logic:equal> > ${trainType.name} </option>
		            </logic:iterate>
		        </select>
		        <font color=red>&nbsp;*</font>
			</eln:td>
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='trainWay' pageType='0'>  
		        	<select id="trainWay" name="trainWay" onchange="switchTrainWay()" class="selectStyle" style="width: 148px;">
			            <option value="0" <logic:equal name="train" property="trainWay" value="0">selected</logic:equal>> 线上培训 </option>
			            <option value="1" <logic:equal name="train" property="trainWay" value="1">selected</logic:equal>> 线下培训 </option>
			            <option value="2" <logic:equal name="train" property="trainWay" value="2">selected</logic:equal>> 混合培训 </option>            
		          	</select>
		          <font color=red>*</font>       
		     </eln:td>
	          <input name="isStationTrain" type="hidden" value="0"/>    
	     
	        <!-- 
		       <div id="isStationTrainRow" <logic:notEqual name="train" property="trainWay" value="0">style="display:none"</logic:notEqual>>
	        	<input id="isStationTrain1" type="radio" name="isStationTrain" value="1" <logic:equal name="train" property="isStationTrain" value="1">checked</logic:equal> onclick="stationtrain()" />岗位培训 
	        	<input id="isStationTrain0" type="radio" name="isStationTrain"  value="0" <logic:equal name="train" property="isStationTrain" value="0">checked</logic:equal> onclick="stationtrainno()"/>其他线上培训       
	        	<font color=red>&nbsp;*</font>          
	           </div>   -->   
	    
	      </tr>
	      <tr>
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='isPlaned' pageType='0'>  
			<input type="radio" name="isPlaned" value="1" onclick="isplan();" <logic:equal name="train" property="isPlaned" value="1">checked</logic:equal> />是
			  <input name="isPlaned" type="radio" value="0" onclick="noplan();" <logic:equal name="train" property="isPlaned" value="0">checked</logic:equal> />否 <font color=red>*</font>
			  </eln:td>
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='trainPlanID' pageType='0'>  
						<input name="trainPlanName" type="text" id="trainPlanName"
							value="${train.trainPlanName}" readonly="readonly" />
 			<input id="isplanrow" name="按钮" type="button" value="选择" onclick="javascript:trainPlan();"
			<logic:equal name="train" property="isPlaned" value="0">style="display:none"</logic:equal>
			/>
			</eln:td>
			<input id="trainPlanID" type="hidden" name="trainPlanID" value="${train.trainPlanID}" readonly="readonly" />
	        </tr>
		<tr>
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='creamProject' pageType='0'> 
				<select name="creamProject" id="creamProject" class="selectStyle" style="width: 148px;">
	           <logic:empty name="train" property="creamProject"><option value="-1">请选择</option></logic:empty>
				<logic:equal name="train" property="creamProject" value="-1"><option value="-1">请选择</option></logic:equal>
	            <logic:iterate id="creamProject" name="creamProjectList"
													type="cn.kepu.elearning.bean.pub.Ddictionary">
	              <option value="${creamProject.code}" <logic:notEmpty name="train" property="creamProject"> <logic:equal name="train" property="creamProject" value="${creamProject.code}">selected</logic:equal></logic:notEmpty>> ${creamProject.name} </option>
	            </logic:iterate>
	          	</select>	<font color=red>&nbsp;*</font>
		   </eln:td>
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='trainMode' pageType='0'> 
			  <select name="trainMode" id="trainMode" class="selectStyle" style="width: 148px;">
		            <logic:empty name="train" property="trainMode"><option value="-1">请选择</option></logic:empty>
					<logic:equal name="train" property="creamProject" value="-1"><option value="-1">请选择</option></logic:equal>
		            <logic:iterate id="trainMode" name="trainModeList"
														type="cn.kepu.elearning.bean.pub.Ddictionary">
		              <option value="${trainMode.code}" <logic:notEmpty name="train" property="trainMode"> <logic:equal name="train" property="trainMode" value="${trainMode.code}">selected</logic:equal></logic:notEmpty>> ${trainMode.name} </option>
		            </logic:iterate>
	          </select>	<font color=red>&nbsp;*</font>
			</eln:td>
			</tr>      
      <tr>
	   	<eln:tdDate formfieldAttributesMap='${formfieldAttributesMap}' attribute='startTime' value='${train.startTime}' pageType='0'>  
        <font color=red>&nbsp;*</font>
		</eln:tdDate>
	   	<eln:tdDate formfieldAttributesMap='${formfieldAttributesMap}' attribute='endTime' value='${train.endTime}' pageType='0'>  
        <font color=red>&nbsp;*</font>
		</eln:tdDate>
      </tr>	 
      <tr>
 		<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='classHour' pageType='0'>  
		<input name="classHour" id="classHour" class="textInput" type="text" value="${train.classHour}" /><font color=red>&nbsp;*</font>
		</eln:td>
 		<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='days' pageType='0'>  
		<input name="days" id="days" class="textInput" type="text" value="${train.days}" size="10" maxLength="10" style="width: 148px;"/>天<font color=red>&nbsp;*</font>
        </eln:td>
      </tr>
      <tr>
 	 	<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='attendantCount' pageType='0'>  
		<input name="attendantCount" id="attendantCount" class="textInput" type="text" value="${train.attendantCount}" size="10" maxLength="10" style="width: 148px;"/>人<font color=red>&nbsp;*</font>
        </eln:td>
 		<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='researchPostNum' pageType='0'>  
		<input name="researchPostNum" id="researchPostNum" class="textInput" type="text" value="${train.researchPostNum}" />人<font color=red>&nbsp;*</font>
		</eln:td>
      </tr>      
      <tr>
       	<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='managePostNum' pageType='0'>  
		<input name="managePostNum" id="managePostNum" class="textInput" type="text" value="${train.managePostNum}" size="10" maxLength="10" style="width: 148px;"/>人<font color=red>&nbsp;*</font>
        </eln:td>
 		<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='supportPostNum' pageType='0'>  
		<input name="supportPostNum" id="supportPostNum" class="textInput" type="text" value="${train.supportPostNum}" />人<font color=red>&nbsp;*</font>
		</eln:td>
      </tr>      
      <tr>
       	<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='outSideNum' pageType='0'>  
		<input name="outSideNum" id="outSideNum" class="textInput" type="text" value="${train.outSideNum}" size="10" maxLength="10" style="width: 148px;"/>人<font color=red>&nbsp;*</font>
        </eln:td>
 		<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='workerNum' pageType='0'>  
		<input name="workerNum" id="workerNum" class="textInput" type="text" value="${train.workerNum}" />人<font color=red>&nbsp;*</font>
		</eln:td>
      </tr>   		
	  <tr>
		 	<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='feeChannel' pageType='0'>  
			<input name="feeChannel" id="feeChannel" class="textInput" type="text" value="${train.feeChannel}" size="10" maxLength="10" style="width: 148px;"/><font color=red>&nbsp;*</font>
	        </eln:td>
		    <eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='fee' pageType='0'>  
			<input name="fee" id="fee" class="textInput" type="text" value="${train.fee}" size="10" maxLength="10" style="width: 148px;"/><font color=red>&nbsp;*</font>
	        </eln:td>
      </tr>
		<tr style="display:none">
         <eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='isNoted' pageType='0'>  
		   <logic:equal name="train" property="isNoted" value="1">
		      <input type="radio" name="isNoted" value="1" checked/>是&nbsp;
              <input name="isNoted" type="radio" value="0"  />否
		   </logic:equal>
		   <logic:equal name="train" property="isNoted" value="0">
		      <input type="radio" name="isNoted" value="1" />是
              <input name="isNoted" type="radio" value="0" checked/>否
		   </logic:equal>
		    <font color=red>*</font>
		 </eln:td>
   			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='upTenantId' pageType='0'>  
	        	<input type="radio" name="upTenantId" value="4" <logic:equal name="train" property="upTenantId" value="4">checked</logic:equal> />是
  				<input name="upTenantId" type="radio" value="0" <logic:equal name="train" property="upTenantId" value="0">checked</logic:equal> />否 
	          <font color=red>*</font>   
			  </eln:td>
      </tr>
  		<tr>
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='location' pageType='0'>  
				<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='ifBJ' pageType='0'> 
 				<input type="radio" name="ifBJ" value="1" 
 				<logic:equal name="train" property="ifBJ" value="1">checked</logic:equal>
 				 ></input>
				本地
				<input type="radio" name="ifBJ" value="0" 
					<logic:equal name="train" property="ifBJ" value="0">checked</logic:equal>			
				></input>
				外埠
				&nbsp;
				 <font color=red>*</font>  
				</eln:td>
				<span style="margin-left:20px;">具体地点&nbsp;&nbsp;<input id="location" type="text" name="location" maxlength="25" style="width: 250px;" value="${train.location}"/></span>
            	<span id="_location" style="color:red;<logic:equal name='train' property='ifBJ' value='1'>display:none</logic:equal>">&nbsp;*</span>
			    <!-- <input name="location" class="textInput" type="text" value="${train.location}" size='50' maxLength='50' style="width: 50;"/>  -->
			</eln:td>
		</tr>
	    <tr>
            <eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='stationId' pageType='0'>    
                    <input type="checkbox" name="stationId" value="1" 
                   <logic:notEmpty name="train" property="stationId">
                    <logic:match name="train" property="stationId" value="1">checked</logic:match>
                    </logic:notEmpty>
                    />
					科研类
					<input type="checkbox" name="stationId" value="2" 
					                   <logic:notEmpty name="train" property="stationId">
                  <logic:match name="train" property="stationId" value="2">checked</logic:match>					
                    </logic:notEmpty>
					/>
					支撑类
					<input type="checkbox" name="stationId" value="3" 
					                   <logic:notEmpty name="train" property="stationId">
                  <logic:match name="train" property="stationId" value="3">checked</logic:match>					
                    </logic:notEmpty>
					/>
					管理类
  				<span id="_stationId" style="color:red" >&nbsp;*</span>
            </eln:td>
            <eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='cad_report' pageType='0'> 
	       	    	<input type="radio" name="cad_report" value="5001" 
	       	    		<logic:equal name="train" property="cad_report" value="5001">checked</logic:equal>
	       	    	></input>
					项目培训
					<input type="radio" name="cad_report" value="5002" 
						<logic:equal name="train" property="cad_report" value="5002">checked</logic:equal>
					></input>
					学术报告
					 <font color=red>*</font> 
	        </eln:td>		
          </tr>
	      <tr>
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='attendants' pageType='0'>  
			<textarea class="areaInput" name="attendants" id="attendants" rows="3" cols="40" onKeyDown="gbcount(document.getElementById('attendants'));" onKeyUp="gbcount(document.getElementById('attendants'));" style="width: 380px;">${train.attendants}</textarea>       
			<span id="_attendants" style="color:red">*</span>
			</eln:td>
	      </tr>
	      <tr>
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='trainGoal' pageType='0'>  
			<textarea class="areaInput" name="trainGoal" id="trainGoal" rows="3" cols="40" onKeyDown="gbcount(document.getElementById('trainGoal'));" onKeyUp="gbcount(document.getElementById('trainGoal'));" style="width: 380px;">${train.trainGoal}</textarea>
			<span id="_trainGoal" style="color:red">*</span>
			</eln:td>
	      </tr>
	      <tr>
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='trainingContent' pageType='0'>  
			<textarea class="areaInput" name="trainingContent" id="trainingContent" rows="3" cols="40"  onKeyDown="gbcount(document.getElementById('trainingContent'));" onKeyUp="gbcount(document.getElementById('trainingContent'));" style="width: 380px;">${train.trainingContent}</textarea>
			<span id="_trainContent" style="color:red">*</span>
			</eln:td>
	      </tr>
	      <tr>
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='keyWordsTag' pageType='0'>  
			<input class="textInput" type='text' name="keyWordsTag" id="keyWordsTag" value="${train.keyWordsTag }" size='50' maxLength='50' style="width: 380px;"/>&nbsp;(关键词间用 ; 隔开)<font color=red>&nbsp;*</font>
			</eln:td>
	      </tr>
	      <tr>
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='comment' pageType='0'>  
		<textarea class="areaInput" name="comment"  id="comment" rows="3" cols="485"  onKeyDown="gbcount(document.getElementById('comment'));" onKeyUp="gbcount(document.getElementById('comment'));" style="width:485px;">${train.comment}</textarea>
			</eln:td>
	      </tr>
	            <tr bgcolor="#f1f1f1">
	        <td colspan="4" align="left" bgcolor="#c9e5f1" style="font-size:13px;"><strong>主办单位与联系人</strong></td>
	      </tr>
	
	
			<tr>
				<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='sponsorName' pageType='0'> 
	                <input name="orgsname" class="textInput" id="_orgsname" type="text" value="${train.sponsorName}" size="40" maxLength="64"  style="width: 380px;"/>     
				    <span id='_sponsorName' style="color:red;">*</span>
				</eln:td>
	      </tr>
	      <tr>
				<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='organizerName' pageType='0'>  
					<input class="textInput" name="organizerName" type="text" id="organizerName" value="${train.organizerName}" size="40" maxLength="64" style="width: 380px;"/>
	 				<span id='_organizerName' style="color:red;">&nbsp;*</span>	
	 			</eln:td>
	 			</tr>
	 			<tr>
				<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='telephone' pageType='0'>
					<input id="telephone" name="telephone" class="textInput" type="text" value="${train.telephone}" size="20" maxLength="20"/>   
					<span id='_telephone' style="color:red;">&nbsp;*&nbsp;</span>     
				</eln:td>
			    <input type='hidden' name='orgId' id='orgId' value='${train.orgId}' />
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='organizerEmail' pageType='0'>  
	        <input name="organizerEmail" class="textInput"  id="organizerEmail" type="text" value="${train.organizerEmail}"/>
	        <span id='_organizerEmail' style="color:red;">&nbsp;*</span>
			</eln:td>
	      </tr>
	      <!-- 
	      <tr>
	        <th style="font-weight:normal;"  align="right"> 面向范围: 
	        </th>
	        <td align="left" colspan=3>
			<div style="float: left; width: 65%">
	        <textarea class="areaInput" name="facescopeName" id="orgsname" rows="3" cols="40"  readonly>${facescopeName}</textarea>   
	        <input type="hidden" name="facescopeId" id="orgsid" value="${facescopeId}"/>                 

			</div>
	        <div style="float:left;padding-top:20px;">
			<a href="javascript:selectdepts();" class="btn-blue-l"><span class="btn-blue-r">选&nbsp;&nbsp;择</span></a>
		    <a href="javascript:cleardepts();" class="btn-orange-l"><span class="btn-orange-r">清&nbsp;&nbsp;空</span></a>
			</div>
			</td>
			</tr>
			 -->
	      <tr bgcolor="#f1f1f1">
	        <td colspan="4" bgcolor="#c9e5f1" style="font-size:13px;"><strong>报名与证书</strong></td>
	      </tr>
	    <tr>
			<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='openScope' pageType='0'>       	      
				<logic:iterate id="openScope" name="openScopeList" scope="request">
	      		        <input type="radio" name="openScope" value="${openScope.code }" onclick="register(${openScope.code})" <logic:equal name="train" property="openScope" value="${openScope.code }" >checked</logic:equal>/>${openScope.name} 
				</logic:iterate>		
	        </eln:td>
	      </tr>
	      <tr id="register" <logic:equal name="train" property="openScope" value="2201">style="display:none"</logic:equal>>
	        <th style="font-weight:bold;"   align="right"> 是否允许报名 </th>
	        <td  align="left"><input name="isEnrolled" id="isEnrolledYes" <logic:equal name="train" property="isStationTrain" value="1">disabled</logic:equal> type="radio" value="1" onclick="isEnroll()" 
	          <logic:equal name="train" property="isEnrolled" value="1">checked</logic:equal>/>
	          是
	          <input name="isEnrolled" id="isEnrolledNo" type="radio" onclick="noEnroll()"  value="0" <logic:equal name="train" property="isEnrolled" value="0">checked</logic:equal> />
	          否&nbsp;<font color=red>*</font></td>
	        <td  align="right"><div id="isNeeds" <logic:equal name="train" property="isEnrolled" value="0">style="display:none"</logic:equal>> 报名是否需要审批: </div></td>
	        <td align="left"><div id="isNeedCheck"  <logic:equal name="train" property="isEnrolled" value="0">style="display:none"</logic:equal>>                    
	          <input name="isNeedCheck" type="radio" value="1" <logic:equal name="train" property="isNeedCheck" value="1">checked</logic:equal>/>是
	          <input name="isNeedCheck" type="radio" value="0" <logic:equal name="train" property="isNeedCheck" value="0">checked</logic:equal>/>否
	        <font color=red>&nbsp;*</font></div></td>
	      </tr>
	      <tr id="programrow" <logic:equal name="train" property="isEnrolled" value="0">style="display:none"</logic:equal>>
	        <td align="right"> 报名开始时间:</td>
	        <td align="left">
			 <input name="programStartTime" id="programStartTime" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false,readOnly:true})"  value='${train.programStartTime}'/>         				 			  										
				<font color=red>*</font></td>
	        <td align="right"> 报名结束时间:</td>
	        <td align="left">
			 <input name="programEndTime" id="programEndTime" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false,readOnly:true})" value='${train.programEndTime}' />   
			 	<font color=red>*</font>		</td>
	      </tr>      <input name="isIssued" type="hidden" value="0" />
	      <!--  
	      <tr>
	        <th style="font-weight:normal;"  align="right">是否颁发证书: </th>
	        <td  align="left"><input name="isIssued" type="radio" value="1"
	          <logic:equal name="train" property="isIssued" value="1">checked</logic:equal> />
	          是
	          <input name="isIssued" type="radio" value="0" <logic:equal name="train" property="isIssued" value="0">checked</logic:equal> />
	          否</td>
	        <td width="110" align="right">&nbsp;</td>
	        <td align="left">&nbsp;</td>
	      </tr>
	      -->
	    </table>
	    <div class="btnContainer" style="margin-top:10px;padding-left:40%;">
			<a href="onlineTraining.do?method=searchTrain" class="btn-blue-l"><span class="btn-blue-r">返&nbsp;&nbsp;回</span></a>
			<a href="javascript:insertOrUpdate();" class="btn-orange-l"><span class="btn-orange-r">提&nbsp;&nbsp;交</span></a> 
		</div>
	  </form>
  </div><!-- content02 -->
 <!-- InstanceEndEditable --> </div>
  <div class="clr"></div>
</div><!-- mainbody -->
</div>
<div id="bottombody">
  <%=Constants.systemBottom %>
  
</div>
</body>
<!-- InstanceEnd --></html>
