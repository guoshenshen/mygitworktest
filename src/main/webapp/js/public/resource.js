;(function($){
	
	
	var defaultTrainPicMap={
		'2120':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2120.jpg",
		'2121':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2121.jpg",
		'2170':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2170.jpg",
		'2180':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2180.jpg",
		'2192':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2192.jpg",
		'2193':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2193.jpg",
		'2197':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2197.jpg",
		'2160':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2160.jpg",
		'2263':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2263.jpg",
		'2264':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2264.jpg",
		'2265':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2265.jpg",
		'2241':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2241.jpg",
		'2242':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2242.jpg",
		'2243':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2243.jpg",
		'2244':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2244.jpg",
		'2255':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2255.jpg",
		'2256':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2256.jpg" 			
 	}
	
	
	$.extend({
		
		loadSelectKnowledge:function(list) {
			var htmlArray = new Array();
			for (var i=0; i<list.length; i++) {
				var o = list[i];

				htmlArray.push('<tr style="table-layout：fixed;word-wrap:break-word;word-break:break-all;white-space:normal;">');
				htmlArray.push('<td>'+o.name+'</td>');
				htmlArray.push('<td>'+o.describe+'</td>');
				htmlArray.push('<td>'+o.order+'</td>');
				//点击button去关联数据
				htmlArray.push('<td nowrap id="s_'+o.id+'"><button type="button" class="btn btn-primary btn-sm" onclick="saveKnowledgeResource(\''+o.id+'\')">关联</button>&nbsp;');
				htmlArray.push('</td>');
				htmlArray.push('</tr>');
			}
			return htmlArray.join("");
		},
	
		loadKnowledge:function(list) {
			var htmlArray = new Array();
			for (var i=0; i<list.length; i++) {
				var o = list[i];

				htmlArray.push('<tr style="table-layout：fixed;word-wrap:break-word;word-break:break-all;white-space:normal;">');
				htmlArray.push('<td>'+o.name+'</td>');
				htmlArray.push('<td>'+o.describe+'</td>');
				htmlArray.push('<td>'+o.order+'</td>');
				htmlArray.push('<td nowrap><button type="button" class="btn btn-primary btn-sm" onclick="updateKnowledge(\''+o.id+'\')">修改</button>&nbsp;');
				htmlArray.push('<button type="button" class="btn btn-danger btn-sm" onclick="delKnowledge(\''+o.id+'\')">删除</button></td>');
				//htmlArray.push('<button type="button" class="btn btn-danger btn-sm" onclick="javascript:relatedKnowledge(\''+o.id+'\')">关联</button></td>');
				htmlArray.push('</tr>');
			}
			return htmlArray.join("");
		},
		loadSQueryKnowledge:function(list) {
			var htmlArray = new Array();
			for (var i=0; i<list.length; i++) {
				var o = list[i];
				
				htmlArray.push('<tr style="table-layout：fixed;word-wrap:break-word;word-break:break-all;white-space:normal;">');
				htmlArray.push('<td>'+o.name+'</td>');
				htmlArray.push('<td>'+o.describe+'</td>');
				htmlArray.push('<td id="s_'+o.id+'">未关联</td>');
				htmlArray.push('<td nowrap><a href="javascript:void(0);" onclick="getKnowledgeRelatedByKid(\''+o.id+'\')">详情</a>&nbsp;&nbsp;</td>');
				htmlArray.push('</tr>');
			}
			return htmlArray.join("");
		},
		loadKnowledgeTrain:function(list) {
			var htmlArray = new Array();
			for (var i=0; i<list.length; i++) {
				var o = list[i];
				
				htmlArray.push('<tr id="s_'+o.id+'">');
				htmlArray.push('<td>'+o.trainName+'</td>');
				htmlArray.push('<td>'+o.startTime+'</td>');
				htmlArray.push('<td>'+o.endTime+'</td>');
				htmlArray.push('<td>'+o.organizerName+'</td>');
				htmlArray.push('<td>'+o.sponsorName+'</td>');
				htmlArray.push('<td nowrap><button type="button" class="btn btn-danger btn-sm" onclick="delKnowledgeResource(\''+o.id+'\')">解绑</button>&nbsp;</td>');
				
				htmlArray.push('</tr>');
			}
			return htmlArray.join("");
		},
		loadKnowledgeCourse:function(list) {
			var htmlArray = new Array();
			for (var i=0; i<list.length; i++) {
				var o = list[i];
				htmlArray.push('<tr id="s_'+o.id+'">');
				htmlArray.push('<td>'+o.courseName+'</td>');
				htmlArray.push('<td>'+o.creator+'</td>');
				if(o.keyWords==null){
					htmlArray.push('<td>&nbsp;</td>');
				}else{
					htmlArray.push('<td>'+o.keyWords+'</td>');
				}
				htmlArray.push('<td>'+o.createDate+'</td>');
				htmlArray.push('<td nowrap><button type="button" class="btn btn-danger btn-sm" onclick="delKnowledgeResource(\''+o.id+'\')">解绑</button>&nbsp;</td>');
				htmlArray.push('</tr>');
			}
			return htmlArray.join("");
		},
		loadKnowledgeTeacher:function(list) {
			var htmlArray = new Array();
			for (var i=0; i<list.length; i++) {
				var o = list[i];
				
				htmlArray.push('<tr id="s_'+o.id+'">');
				htmlArray.push('<td>'+o.teacherName+'</td>');
				htmlArray.push('<td>'+o.gender+'</td>');
				htmlArray.push('<td>'+o.workPlace+'</td>');
				htmlArray.push('<td>'+o.courseName+'</td>');
				htmlArray.push('<td nowrap><button type="button" class="btn btn-danger btn-sm" onclick="delKnowledgeResource(\''+o.id+'\')">解绑</button>&nbsp;</td>');
				htmlArray.push('</tr>');
			}
			return htmlArray.join("");
		},
		loadKnowledgeQuestion:function(list) {
			var htmlArray = new Array();
			for (var i=0; i<list.length; i++) {
				var o = list[i];
				htmlArray.push('<tr id="s_'+o.id+'">');
				htmlArray.push('<td>'+o.qcontent+'</td>');
				htmlArray.push('<td>'+o.tqQuestionsName+'</td>');
				htmlArray.push('<td>'+o.operatorName+'</td>');
				htmlArray.push('<td nowrap><button type="button" class="btn btn-danger btn-sm" onclick="delKnowledgeResource(\''+o.id+'\')">解绑</button>&nbsp;</td>');
				htmlArray.push('</tr>');
			}
			return htmlArray.join("");
		},
		
		loadVStudentInfo:function(list) {
			var htmlArray = new Array();
			for (var i=0; i<list.length; i++) {
				var o = list[i];
				htmlArray.push('<tr id="s_'+o.id+'">');
				htmlArray.push('<td>'+o.operatorName+'</td>');
				if(o.gender==null){
					htmlArray.push('<td>&nbsp;</td>');
				}else{
					if(o.gender==1){
						htmlArray.push('<td>男</td>');
					}else{
						htmlArray.push('<td>女</td>');
					}
				}
				htmlArray.push('<td>'+o.national+'</td>');
				htmlArray.push('<td>'+o.mobile+'</td>');
				htmlArray.push('<td>'+o.certificateType+'</td>');
				htmlArray.push('<td>'+o.certificateNum+'</td>');
				htmlArray.push('<td>'+o.job+'</td>');
				htmlArray.push('<td>'+o.studentLevel+'</td>');
				htmlArray.push('<td>'+o.workUnit+'</td>');
				htmlArray.push('<td>'+o.unitOfNature+'</td>');
				htmlArray.push('<td>'+o.trainName+'</td>');
				htmlArray.push('<td>'+o.contactinName+'</td>');
				htmlArray.push('<td nowrap><button type="button" class="btn btn-primary btn-sm" onclick="updateVStudentinfo(\''+o.id+'\')">修改</button>&nbsp;');
				htmlArray.push('<button type="button" class="btn btn-danger btn-sm" onclick="delVStudentinfo(\''+o.id+'\',\''+o.shift+'\')">删除</button></td>');
				htmlArray.push('</tr>');
			}
			return htmlArray.join("");
		},
		
		loadLiaisonInfo:function(list) {
			var htmlArray = new Array();
			for (var i=0; i<list.length; i++) {
				var o = list[i];
				htmlArray.push('<tr id="s_'+o.id  +'">');
				htmlArray.push('<td>'+o.contactinName+'</td>');
				htmlArray.push('<td>'+o.tel+'</td>');
				htmlArray.push('<td>'+o.mobile+'</td>');
				htmlArray.push('<td>'+o.email+'</td>');
				htmlArray.push('<td nowrap><button type="button" class="btn btn-primary btn-sm" onclick="updateLiaisonInfo(\''+o.id+'\')">修改</button>&nbsp;');
				htmlArray.push('<button type="button" class="btn btn-danger btn-sm" onclick="delLiaisonInfo(\''+o.id+'\')">删除</button></td>');
				htmlArray.push('</tr>');
			}
			return htmlArray.join("");
		},
		
		loadStudentInfomation:function(list) {
			var htmlArray = new Array();
			for (var i=0; i<list.length; i++) {
				var o = list[i];
				htmlArray.push('<tr id="s_'+o.id+'">');
				htmlArray.push("<td><input id='checkbox_"+o.id+"' type='checkbox' value='"+o.id+"' name='selectbox' /></td>");
				htmlArray.push('<td>'+o.operatorName+'</td>');
				if(o.gender==null){
					htmlArray.push('<td>&nbsp;</td>');
				}else{
					if(o.gender==1){
						htmlArray.push('<td>男</td>');
					}else{
						htmlArray.push('<td>女</td>');
					}
				}
				htmlArray.push('<td>'+o.national+'</td>');
				htmlArray.push('<td>'+o.mobile+'</td>');
				htmlArray.push('<td>'+o.certificateType+'</td>');
				htmlArray.push('<td>'+o.certificateNum+'</td>');
				htmlArray.push('<td>'+o.job+'</td>');
				htmlArray.push('<td>'+o.studentLevel+'</td>');
				htmlArray.push('<td>'+o.workUnit+'</td>');
				htmlArray.push('<td>'+o.unitOfNature+'</td>');
				htmlArray.push('<td>'+o.trainName+'</td>');
				htmlArray.push('<td>'+o.contactinName+'</td>');
				htmlArray.push('<td>'+o.contactinTel+'</td>');
				htmlArray.push('<td nowrap><button type="button" class="btn1 btn-primary btn-sm" onclick="updateStudentinfomation(\''+o.id+'\')">修改</button>&nbsp;');
				htmlArray.push('<button type="button" class="btn1 btn-danger btn-sm" onclick="delVStudentinfo(\''+o.id+'\',\''+o.shift+'\')">删除</button></td>');
				htmlArray.push('</tr>');
			}
			return htmlArray.join("");
		},
		loadVStudentUser:function(list) {
			var htmlArray = new Array();
			for (var i=0; i<list.length; i++) {
				var o = list[i];
				htmlArray.push('<tr id="s_'+o.operatorId+'">');
				htmlArray.push('<td>'+o.operatorName+'</td>');
				htmlArray.push('<td>'+o.userId+'</td>');
				if(o.gender==null){
					htmlArray.push('<td>&nbsp;</td>');
				}else{
					if(o.gender==1){
						htmlArray.push('<td>男</td>');
					}else{
						htmlArray.push('<td>女</td>');
					}
				}
				htmlArray.push('<td>'+o.mobile+'</td>');
				htmlArray.push('<td>'+o.job+'</td>');
				htmlArray.push('<td>'+o.orgName+'</td>');
				htmlArray.push('<td nowrap><button type="button" class="btn btn-primary btn-sm" onclick="getSelectStudentUser(\''+o.operatorId+'\')">选择</button></td>;');
				htmlArray.push('</tr>');
			}
			return htmlArray.join("");
		},
		
		loadMySeriesList:function(seriesList) {
			var seriesListHtmlArray = new Array();
			for (var i=0; i<seriesList.length; i++) {
				var series= seriesList[i];
				seriesListHtmlArray.push('<div class="pn_828x158 pn_185x100">');
				seriesListHtmlArray.push('<span class="pn_img"><img src="'+series.mainPicUrl+'" onerror="imgError({type:1,target:this});" /></span>');
				seriesListHtmlArray.push('<div class="pn_422">');
				seriesListHtmlArray.push('<ul>');
				seriesListHtmlArray.push('<li class="tit">'+series.title+'</li>');
				seriesListHtmlArray.push('<li class="time">地点：'+series.sponsorInfo+'</li>');
				seriesListHtmlArray.push('<li>时间：'+series.publishTime+'</li>');
				seriesListHtmlArray.push('</ul>');
				seriesListHtmlArray.push('</div>');
				//seriesListHtmlArray.push('<span class="statebox1"><a href="#"></a></span>');
				seriesListHtmlArray.push('<span class="pn_btn"><a target="_blank" href="/recommendSeries/intoSeriesItemFrame.do?seriesId='+series.id+'">查看课程</a></span>');
				seriesListHtmlArray.push('</div>');
			}
			return seriesListHtmlArray.join("")
		},
		
		loadMyTrainList:function(trainList) {
			var trainListHtmlArray = new Array();
			for (var i=0; i<trainList.length; i++) {
				var train = trainList[i];
				trainListHtmlArray.push('<div class="pn_828x158 pn_185x100">');
				trainListHtmlArray.push('<span class="pn_img"><img src="'+train.imgUrl+'" onerror="imgError({type:1,target:this});" /></span>');
				trainListHtmlArray.push('<div class="pn_422">');
				trainListHtmlArray.push('<ul>');
				trainListHtmlArray.push('<li class="tit">'+train.trainName+'</li>');
				trainListHtmlArray.push('<li class="time">地点：'+train.location+'</li>');
				trainListHtmlArray.push('<li>时间：'+train.startTime+'</li>');
				trainListHtmlArray.push('</ul>');
				trainListHtmlArray.push('</div>');
				//trainListHtmlArray.push('<span class="statebox"><a href="#"></a></span>');
				trainListHtmlArray.push('<span class="pn_btn"><a target="_blank" href="onlineTraining.do?method=viewTrain4Admin&trainId='+train.train_id+'">查看培训</a></span>');
				trainListHtmlArray.push('</div>');
			}
			return trainListHtmlArray.join("")
		},
		
		loadTrainCardList:function(trainListArray){
			var trainListHtmlArray=new Array();
			for (var i=0; i<trainListArray.length; i++) {
				var train = trainListArray[i];
				var nowDate = new Date();
				var beginDate = new Date(train.startTime);
				var endDate = new Date(train.endTime);
				trainListHtmlArray.push('<div class="trainCard">');
				trainListHtmlArray.push('<a target="_blank" href="../onlineTraining/viewTrain4Admin.do?trainId='+train.trainId+'">')
				trainListHtmlArray.push('<div class="banner">');
				trainListHtmlArray.push('<img src="'+train.imgUrl+'" onerror="imgError({type:1,target:this});" />');
				trainListHtmlArray.push('<div class="mask"></div>');	
				/*if (train.isEnrolled == 1) {
					trainListHtmlArray.push('<div class="CanRegister">可以报名</div>')	;
				} else {
					trainListHtmlArray.push('<div class="CanRegister" style="background:orange">不可以报名</div>')	;
				}*/
				if(nowDate.getTime()>beginDate.getTime()&&nowDate.getTime()<endDate.getTime()){
					trainListHtmlArray.push('<div class="CanRegister">正在进行</div>')	;
				}
				if(nowDate.getTime()<beginDate.getTime()){
					trainListHtmlArray.push('<div class="CanRegister" style="background:orange">尚未开始</div>')	;
				}
				if(nowDate.getTime()>endDate.getTime()){
					trainListHtmlArray.push('<div class="CanRegister" style="background:#948f8f">已结束</div>')	;
				}
				trainListHtmlArray.push('	<div class="desc">'+train.trainName+'</div>');
				trainListHtmlArray.push('</div>');
				trainListHtmlArray.push('<div class="detail">');
				trainListHtmlArray.push('	<p><span>时间：</span>'+train.startTime+'-'+train.endTime+'</p>');
				trainListHtmlArray.push('	<p title="'+train.location+'"><span >地点：</span>'+train.location+'</p>');
				trainListHtmlArray.push('	<p><span>单位：</span>'+train.orgName+'</p>');
				trainListHtmlArray.push('</div>');
				trainListHtmlArray.push('<span class="top"></span>');
				trainListHtmlArray.push('<span class="left"></span>');
				trainListHtmlArray.push('<span class="right"></span>');
				trainListHtmlArray.push('<span class="bottom"></span>');
				trainListHtmlArray.push('</a>')
				trainListHtmlArray.push('</div>');
			}
			return trainListHtmlArray.join("");
		},
		
		
		//根据获取的课程显示可视化课程列表信息
		loadVisualTrainList:function(trainListArray){
			var trainListHtmlArray=new Array();
			$(".j-infoBox").on("click",".trainPic",function(){
				var trainId = $("input[name=trainId]",$(this)).val();
				window.open("../onlineTraining/viewTrain4Admin.do?trainId=" + trainId);
			});
			
			var trainListLength = trainListArray.length;
			if(trainListLength!=0){
				trainListHtmlArray.push('<ul id="trainList" class="visualTrainList">');
			} else{
				trainListHtmlArray.push("<div class='empty'><div class='emptyImg'></div></div>");
			}
			for(var i=0;i<trainListLength;i++){
				var train = trainListArray[i];
				trainListHtmlArray.push("<li class='trainContent'><div class='trainPic'><div class='mask'></div><div class='trainDetail'>");
				if(!train.train_id){
					train.train_id=train.trainId;
				}
				trainListHtmlArray.push("<input type='hidden' name='trainId' value='"+ train.train_id +"'>");
				trainListHtmlArray.push("<span>主办方:" + train.sponsorName + "</span>");
				if(!train.startDate){
					train.startDate=train.startTimeStr;
				}
				trainListHtmlArray.push("<span>起始时间:" + train.startTime+ "</span>");
				if(!train.endDate){
					train.endDate=train.endTimeStr;
				}
				trainListHtmlArray.push("<span>截止时间:" + train.endTime + "</span>");
				if(train.location==null||$.trim(train.location)==""){
				}	
				else{
					trainListHtmlArray.push("<span>地点:" + train.location + "</span>");
				}
				trainListHtmlArray.push("<span class='orgName'>" + train.tenantName + "</span></div>");
				trainListHtmlArray.push("<img class='lazy' src='" + "/image/lazyload/lazyload.png'"+" data-original='" +defaultTrainPicMap[train.trainTypeId+""]+ "'/></div>");
				trainListHtmlArray.push("<div class='trainBasicInfo' title='"+train.trainName+"'>" + train.trainName + "</div></li>");				
			}
			if(trainListLength!=0){
				trainListHtmlArray.push('</ul>');
			}
			trainListHtmlArray.push('<div style="clear:both"></div>');
			return trainListHtmlArray.join("");
		},
		
		
		//根据获取的课程显示可视化课程列表信息
		loadVisualCourseList:function(courseListArray){
			var courseListHtmlArray=new Array();	
			$(".j-infoBox").on("click",".coursePic",function(){
				var courseId=$("input[name=courseId]",$(this)).val();
				window.open("../onlineCourse/detail.do?bookId="+courseId);
			});
			var courseListLength=courseListArray.length;
			if(courseListLength!=0){
				courseListHtmlArray.push('<ul id="courseList" class="visualCourseList">');
			}
			else{
				courseListHtmlArray.push("<div class='empty'><div class='emptyImg'></div></div>");
			}
			for(var i=0;i<courseListLength;i++){
				//use course to generate html-courseinfo
				
				var course=courseListArray[i];
				courseListHtmlArray.push("<li class='courseContent'><div class='coursePic' ><div class='mask'></div><div class='courseDetail'>");
				courseListHtmlArray.push("<input type='hidden' name='courseId' value='"+ course.course_id +"'>");
				courseListHtmlArray.push("<span>学时:" + course.classHour + "小时</span>");
				courseListHtmlArray.push("<span>作者:" + course.creator + "</span>");
				courseListHtmlArray.push("<span>领域:" + course.courseTypeName + "</span>");
				courseListHtmlArray.push("<span class='orgName'>" + course.tenantName + "</span></div>");
				courseListHtmlArray.push("<img class='lazy' src='" + course.pictureURL + "' onerror='imgError({type:0,target:this})'/></div>");
				courseListHtmlArray.push("<div class='courseBasicInfo' title='"+course.courseName+"'>" + course.courseName + "</div></li>");				
			}
			if(courseListLength!=0){
				courseListHtmlArray.push('</ul>');
			}	
			courseListHtmlArray.push('<div style="clear:both"></div>');
			return courseListHtmlArray.join("");
		},
	
	
		loadVisibleCourseList:function(courseListArray){
			var courseListHtmlArray=new Array();
			var courseListLength=courseListArray.length;
			for(var i=0;i<courseListLength;i++){
				var course=courseListArray[i];
				courseListHtmlArray.push("<li class='courseContent'><div class='coursePic' ><div class='mask'></div><div class='courseDetail'>");
				courseListHtmlArray.push("<input type='hidden' name='courseId' value='"+ course.courseId+"'>");
				courseListHtmlArray.push("<span>学时:" + course.classHour + "小时</span>");
				courseListHtmlArray.push("<span>作者:" + course.creator + "</span>");
				courseListHtmlArray.push("<span>课件形式:" + course.classificationStr + "</span>");
				courseListHtmlArray.push("<span class='orgName'>" + course.tenantName + "</span></div>");
				courseListHtmlArray.push("<img class='lazy' src='" + course.pictureUrl + "' onerror='imgError({type:0,target:this})'/></div>");
				courseListHtmlArray.push("<div class='courseBasicInfo'  title='"+course.courseName+"'>" + course.courseName + "</div></li>");				
			}
			return courseListHtmlArray.join("");
		},
		
		
		loadLineStyleCourseList:function(courseList){
			var courseLength=courseList.length;
			var courseInfoArray=new Array();
			if(courseLength>0){
				for(var i=0;i<courseLength;i++){
					var courseInfo=courseList[i];
					var score=courseInfo.score;
					if(score==null){
						score=0.0;
					}
					var scoreHtml=new Array();
					scoreHtml.push("<div class='courseScore'>");
					var k=0;
					for(k=0;k<5;k++){
						if(k<=score-1){
							scoreHtml.push("<i class='fa fa-star on' aria-hidden='true'></i>");
						}
						else if(k<score&&k+1>score){
							scoreHtml.push("<i class='fa fa-star-half-full on' aria-hidden='true'></i>");
						}
						else{
							scoreHtml.push("<i class='fa fa-star' aria-hidden='true'></i>");
						}
					}
					if(score>0){
						scoreHtml.push("<span style='margin-left: 5px;'>"+score.toFixed(1)+"</span>");
					}
					scoreHtml.push("</div>");
					courseInfoArray.push("<div class='courseList-item'><input type='hidden' name='courseId' value='"+courseInfo.courseId+"' />");
					courseInfoArray.push(scoreHtml.join(""));
					courseInfoArray.push("<div class='coursePicContainer'><img class='coursePic lazy' src='"+courseInfo.pictureUrl+"' data-original='"+courseInfo.pictureUrl+"' onerror=\"imgError({type:0,target:this})\"></img></div>");
					courseInfoArray.push("<div class='courseTitle' title='"+courseInfo.courseName+"'>"+courseInfo.courseName+"</div>");
					courseInfoArray.push("<h3 class='courseCreator'>"+courseInfo.creator+"</h3>");
				  /*courseInfoArray.push("<div class='courseSource' title='"+courseInfo.tenantName+"'>"+courseInfo.tenantName+"</div>");*/
					courseInfoArray.push("<div class='WatchCourse'>观看课程</div>");
					courseInfoArray.push("</div>");
				}
			}
			return courseInfoArray.join("");	
		},
		
		
		loadCouseList:function(courseList){
			
			var courseLength=courseList.length;
			var courseInfoArray=new Array();
			if(courseLength>0){
				for(var i=0;i<courseLength;i++){
					var courseInfo=courseList[i];
					var score=courseInfo.score;
					if(score==null){
						score=0.0;
					}
					var scoreHtml=new Array();
					scoreHtml.push("<div class='courseScore'>");
					var k=0;
					for(k=0;k<5;k++){
						if(k<=score-1){
							scoreHtml.push("<i class='fa fa-star on' aria-hidden='true'></i>");
						}
						else if(k<score&&k+1>score){
							scoreHtml.push("<i class='fa fa-star-half-full on' aria-hidden='true'></i>");
						}
						else{
							scoreHtml.push("<i class='fa fa-star' aria-hidden='true'></i>");
						}
					}
					if(score>0){
						scoreHtml.push("<span style='margin-left: 5px;'>"+score.toFixed(1)+"</span>");
					}
					scoreHtml.push("</div>");
					courseInfoArray.push("<div class='courseList-item setType' ><input type='hidden' name='courseId' value='"+courseInfo.courseId+"' />");
					courseInfoArray.push("<div class='coursePicContainer'><img class='coursePic lazy' src='"+courseInfo.pictureUrl+"' data-original='"+courseInfo.pictureUrl+"'  onerror=\"imgError({type:0,target:this})\"></img></div>");
					courseInfoArray.push("<div class='courseTitle' title='"+courseInfo.courseName+"'>"+courseInfo.courseName+"</div>");
					courseInfoArray.push("<div class='courseOrgName stand'>制作单位："+courseInfo.produceOrgName+"</div>");
					courseInfoArray.push("<div class='courseCreator transverse' style = 'display:none;'><span>主讲人："+courseInfo.creator+"</span></div>");
					courseInfoArray.push("<div class='courseCreator stand'><span>主讲人："+courseInfo.creator+"</span><span style = 'margin-left:50px;'>学时："+courseInfo.classHour+"</span><span style = 'margin-left:50px;display:none'>时长："+courseInfo.classHour * 60+"分钟</span></div>");
					courseInfoArray.push(scoreHtml.join(""));
					courseInfoArray.push("<div class='courseSource' title='"+courseInfo.tenantName+"'>"+courseInfo.tenantName+"</div>");
					courseInfoArray.push("<div class='WatchCourse'>观看课程</div>");
					courseInfoArray.push("</div>");
				}
			}
			return courseInfoArray.join("");	
		}
	
	});		
})(jQuery);

