function menuimgError(imgObj,pic){
	imgObj.src=pic;	
}

$(function(){
	
	var $userDetailModal=null;
	var $courseSearchModal=null;
	var $teacherSearchModal=null;
	var $trainSearchModal=null
	var $seriesSearchModal = null;
	
	if(!$.isScriptIncluded("remodal.css")){
		$.loadScript("../css/jquery-UI/remodal.css",function(){
		});
		
	}
	if(!$.isScriptIncluded("remodal-default-theme.css")){
		$.loadScript("../css/jquery-UI/remodal-default-theme.css");
	}
	
	if(!$.isScriptIncluded("remodal.min.js")){
		$.loadScript("../js/UI/remodal.min.js",function(){
		});
	}
	
	
	$("body").on("click",".batchOperation th #selectAll",function(){
		var checkedStatus=$(this).is(':checked');
		$("input[type=checkbox]",$(this).parents("tr").siblings()).each(function(){
			$(this).prop("checked",checkedStatus);
		})
	});
	
	
	;$.extend({
		
		getPageSize:function(){
			var defaultSize=20;
			if($("#defaultPageSize").length>0){
				defaultSize=parseInt($("#defaultPageSize").val());
			}
			return defaultSize;
		},
		
		
		closeTeacherModal:function(){
			if($teacherSearchModal!=null){
				$teacherSearchModal.close();
			}
		},
		
		showSelectableTeacher:function(params){
			var teacherInfoCache={};
			if($(".teacherSearch").length==0){
				var teacherSearchModalArray=new Array();
				teacherSearchModalArray.push('<div class="remodal normal noBorder normalModal teacherSearch"  data-remodal-id="teacherSearch" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc">');
				teacherSearchModalArray.push('<div class="box">');
				 teacherSearchModalArray.push('<div class="tools">');
				 teacherSearchModalArray.push('<span class="search">');
				 teacherSearchModalArray.push('<input id="search-info" name="search" type="text" placeholder="教师名称" value="">');
				 teacherSearchModalArray.push('<a href="javascript:void(0);" class="search-icon"><img src="../image/search01.png" alt="搜索"></a>');
				 teacherSearchModalArray.push('</span>');
				 teacherSearchModalArray.push('</div>');
				 teacherSearchModalArray.push('<button data-remodal-action="close" class="remodal-close" ></button>');
				 teacherSearchModalArray.push(' </div>');
				 teacherSearchModalArray.push(' <div class="wrapper noPadding" style="max-height:600px;overflow-y:auto;" >');
				 teacherSearchModalArray.push('<table class="table table-striped table-bordered batchOperation" id="teacherSelectTable">');
				 teacherSearchModalArray.push('<tbody>');
				 teacherSearchModalArray.push('<tr class="tableTh">');
				 teacherSearchModalArray.push('<th></th>');
				 teacherSearchModalArray.push('<th>姓名</th>');
				 teacherSearchModalArray.push('<th>性别</th>');
				 teacherSearchModalArray.push('<th>工作单位</th>');
				 teacherSearchModalArray.push('<th>email</th>');
				 teacherSearchModalArray.push('</tr>');
				 teacherSearchModalArray.push('</tbody>');
				 teacherSearchModalArray.push('</table>');
				 teacherSearchModalArray.push('<div class="condition">');
				 teacherSearchModalArray.push('<ul class="pagination-admin" style="float:right"></ul>');
				 teacherSearchModalArray.push('<div style="clear:both"></div>');
				 teacherSearchModalArray.push('</div>');
				 teacherSearchModalArray.push('<div class="condition" style="text-align: right;">');
				 teacherSearchModalArray.push('<button class="btn btn-success" id="submitTeacherSelect">提交</button>');
				 teacherSearchModalArray.push('</div>');
				 teacherSearchModalArray.push('</div>');
				 teacherSearchModalArray.push('</div>');
				 
				$("body").append(teacherSearchModalArray.join(""));
				if(params!=null && params.actionForSubmit!=null && typeof params.actionForSubmit=="function"){
					$(".teacherSearch").on("click","#submitTeacherSelect",function(){
						var teacherArray=new Array();
						$(".teacherSearch .infoRow input[name=teacherId]:checked").each(function(index,that){
							teacherArray.push(teacherInfoCache[that.value]);
						});
						params.actionForSubmit({"teacherList":teacherArray});
					})
				}
				
			}
			if($teacherSearchModal==null){
				$teacherSearchModal=$('[data-remodal-id=teacherSearch]').remodal($.defaultRemodalOption());
				$(document).on("closed","[data-remodal-id=teacherSearch]",function(){
					paginationConfig.resetSearchInfo();
					$(".teacherSearch .tools input[name=search]").val("");
				});
                var paginationConfig=$.getPaginationConfig();
                var teacherListPaginationConfig={
                    getUrlForPagination:function(){return "../tchrBaseInfo/listAllTeacherForCourse.do"},
                    actionForClearLoadedData:function(){
                        $("#teacherSelectTable .infoRow").remove();
                    },
                    actionForSucessLoadingData:function(data){
                        teacherInfoCache={};
                        var dataList=data.list;
                        var dataLength=dataList.length;
                        var htmlArray=new Array();
                        for(var i=0;i<dataLength;i++){
                            var currentItem=dataList[i];
                            teacherInfoCache[currentItem.id]=currentItem;
                            htmlArray.push("<tr class='infoRow'>");
                            htmlArray.push("<td>"+"<input type='radio' value='"+currentItem.id+"' name='teacherId' >"+"</td>");
                            htmlArray.push("<td><input type='hidden' name='teacherName' value='"+currentItem.teacherName+"'>"+currentItem.teacherName+"</td>");
                            var gender="";
                            if(currentItem.gender=="1"){
                                gender="男";
                            }
                            else{
                                gender="女"
                            }
                            htmlArray.push("<td>"+gender+"</td>");
                            htmlArray.push("<td>"+currentItem.workPlace+"</td>");
                            htmlArray.push("<td>"+currentItem.email+"</td>");
                            htmlArray.push("</tr>");
                        }
                        $("#teacherSelectTable .infoRow").remove();
                        $("#teacherSelectTable").append(htmlArray.join(""));
                    }

                }
                $.extend(paginationConfig,teacherListPaginationConfig);
                function ajaxTeacher(){
                    paginationConfig.resetSearchInfo();
                    var searchInfo=$(".teacherSearch .tools input[name=search]").val();
                    paginationConfig.searchInfo.teacherName=searchInfo;
                    paginationConfig.actionForLoadingPagination();
                }
				$(document).on("opening","[data-remodal-id=teacherSearch]",function(){
					ajaxTeacher();
				});
				$(".teacherSearch .tools").on("click",".search-icon",function(){
					ajaxTeacher();
				}).on("keydown","input[name=search]",function(event){
					if(event.keyCode == "13"){
						$(this).parents(".search").find(".search-icon").click();
					}
				});
			}
			
			$teacherSearchModal.open();
			
		},	
		/************系列专题**********/
		closeSeriesModal:function(){
			if($seriesSearchModal!=null){
				$seriesSearchModal.close();
			}
		},
		
		showSelectableSeries:function(params){
			var seriesInfoCache={};
			if($(".seriesSearch").length==0){
				var seriesSearchModalArray=new Array();
				seriesSearchModalArray.push('<div class="remodal normal noBorder normalModal seriesSearch"  data-remodal-id="seriesSearch" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc">');
				seriesSearchModalArray.push('<div class="box">');
				seriesSearchModalArray.push('<div class="tools">');
				seriesSearchModalArray.push('<span class="search">');
				seriesSearchModalArray.push('<input id="search-info" name="search" type="text" placeholder="专题名称" value="">');
				seriesSearchModalArray.push('<a href="javascript:void(0);" class="search-icon"><img src="../image/search01.png" alt="搜索"></a>');
				seriesSearchModalArray.push('</span>');
				seriesSearchModalArray.push('</div>');
				seriesSearchModalArray.push('<button data-remodal-action="close" class="remodal-close" ></button>');
				seriesSearchModalArray.push(' </div>');
				seriesSearchModalArray.push(' <div class="wrapper noPadding" style="max-height:600px;overflow-y:auto;" >');
				seriesSearchModalArray.push('<table class="table table-striped table-bordered batchOperation" id="seriesSelectTable">');
				seriesSearchModalArray.push('<tbody>');
				seriesSearchModalArray.push('<tr class="tableTh">');
				seriesSearchModalArray.push('<th><input type="checkbox" id="selectAll" name="selectAll"/></th>');
				seriesSearchModalArray.push('<th>专题名称</th>');
				seriesSearchModalArray.push('<th>专题标题</th>');
				seriesSearchModalArray.push('<th>专题详情</th>');
				seriesSearchModalArray.push('<th>专题创建人</th>');
				seriesSearchModalArray.push('<th>专题发布时间</th>');
				seriesSearchModalArray.push('</tr>');
				seriesSearchModalArray.push('</tbody>');
				seriesSearchModalArray.push('</table>');
				seriesSearchModalArray.push('<div class="condition">');
				seriesSearchModalArray.push('<ul class="pagination-admin" style="float:right"></ul>');
				seriesSearchModalArray.push('<div style="clear:both"></div>');
				seriesSearchModalArray.push('</div>');
				seriesSearchModalArray.push('<div class="condition" style="text-align: right;">');
				seriesSearchModalArray.push('<button class="btn btn-success" id="submitSeriesSelect">提交</button>');
				seriesSearchModalArray.push('</div>');
				seriesSearchModalArray.push('</div>');
				seriesSearchModalArray.push('</div>');
				 
				$("body").append(seriesSearchModalArray.join(""));
				if(params!=null&&params.actionForSubmit!=null&&typeof params.actionForSubmit=="function"){
					$(".seriesSearch").on("click","#submitSeriesSelect",function(){
						var seriesArray=new Array();
						$(".seriesSearch .infoRow input[name=seriesId]:checked").each(function(index,that){
							seriesArray.push(seriesInfoCache[that.value]);
							//seriesArray.push(that.value);
						});
						params.actionForSubmit({"seriesList":seriesArray});
					}).on("click","#selectAll",function(){
						if(this.checked){
							$(".seriesSearch .infoRow input[name=seriesId]").each(function(index,that){
								that.checked = true;
							});
						}
						else{
							$(".seriesSearch .infoRow input[name=seriesId]").each(function(index,that){
								that.checked = false;
							});
						}
						
					});
					
				}
				
			}
			if($seriesSearchModal==null){
				$seriesSearchModal=$('[data-remodal-id=seriesSearch]').remodal($.defaultRemodalOption());
				$(document).on("closed","[data-remodal-id=seriesSearch]",function(){
					paginationConfig.resetSearchInfo();
					$(".seriesSearch .tools input[name=search]").val("");
				});
				var paginationConfig=$.getPaginationConfig();
                var seriesListPaginationConfig={
                    getUrlForPagination:function(){return "/seriesResourceBinding/ListSubSeriesBySubSeriesName.do"},
                    actionForClearLoadedData:function(){
                        $("#seriesSelectTable .infoRow").remove();
                    },
                    actionForSucessLoadingData:function(data){
                        var dataList=data.list;
                        var dataLength=dataList.length;
                        var htmlArray=new Array();
                        for(var i=0;i<dataLength;i++){
                            var currentItem=dataList[i];
                            seriesInfoCache[currentItem.id]=currentItem;
                            htmlArray.push("<tr class='infoRow'>");
                            htmlArray.push("<td>"+"<input type='checkbox' value='"+currentItem.id+"' name='seriesId' id='checkbox_"
                                + currentItem.id
                                + "'>"+"</td>");
                            htmlArray.push("<td align='center'>"
                                + currentItem.title + "</td>");
                            htmlArray.push("<td align='center'>"
                                + currentItem.title + "</td>");
                            htmlArray.push("<td align='center' >"
                                + currentItem.detail + "</td>");
                            htmlArray.push("<td align='center' >"
                                + currentItem.sponsorInfo + "</td>");
                            htmlArray
                                .push("<td align='center' class = 'coursedisplayYes' >"
                                    + currentItem.createTime
                                    + "</td>");
                            htmlArray.push("</tr>");
                        }
                        $("#seriesSelectTable .infoRow").remove();
                        $("#seriesSelectTable").append(htmlArray.join(""));
                    }

                }
                $.extend(paginationConfig,seriesListPaginationConfig);

				/*paginationConfig.getUrlForPagination=function(){return "seriesResourceBindingAction.do?method=getSubSeriesLengthBySubSeriesName"};
				paginationConfig.actionForClearLoadedData=function(){
					$("#seriesSelectTable .infoRow").remove();
				};
				paginationConfig.actionForLoadingData=function(){
					teacherInfoCache={};
					$(".batchOperation th #selectAll").prop("checked",false);
					$.ajax({
						url:"seriesResourceBindingAction.do?method=ListSubSeriesBySubSeriesName",
						type:"POST",
				 		data:paginationConfig.searchInfo,
				 		dataType:"json",
				 		success:function(data){
							if(data.status){
								var dataList=data.resultList;
								var dataLength=dataList.length;
								var htmlArray=new Array();
								for(var i=0;i<dataLength;i++){
									var currentItem=dataList[i];
									seriesInfoCache[currentItem.id]=currentItem;
									htmlArray.push("<tr class='infoRow'>");
									htmlArray.push("<td>"+"<input type='checkbox' value='"+currentItem.id+"' name='seriesId' id='checkbox_"
										+ currentItem.id
										+ "'>"+"</td>");
									htmlArray.push("<td align='center'>"
											+ currentItem.title + "</td>");
									htmlArray.push("<td align='center'>"
											+ currentItem.title + "</td>");
									htmlArray.push("<td align='center' >"
											+ currentItem.detail + "</td>");
									htmlArray.push("<td align='center' >"
											+ currentItem.sponsorInfo + "</td>");
									htmlArray
											.push("<td align='center' class = 'coursedisplayYes' >"
													+ currentItem.createTime
													+ "</td>");
									htmlArray.push("</tr>");
								}
								$("#seriesSelectTable .infoRow").remove();
								$("#seriesSelectTable").append(htmlArray.join(""));
							}
							else{
								$.Ntip({
				 					'content':"信息加载失败"
				 				});
							}
				 	 	}
					})
				};*/
				$(document).on("opening","[data-remodal-id=seriesSearch]",function(){
					paginationConfig.searchInfo.seriesId=$("#seriesId").val();
					paginationConfig.actionForLoadingPagination();
				});
				$(".seriesSearch .tools").on("click",".search-icon",function(){
					var searchInfo=$(".seriesSearch .tools input[name=search]").val();
					paginationConfig.searchInfo.seriesId=$("#seriesId").val();
					paginationConfig.searchInfo.name=searchInfo;
					paginationConfig.actionForLoadingPagination();
				}).on("keydown","input[name=search]",function(event){
					if(event.keyCode == "13"){
						$(this).parents(".search").find(".search-icon").click();
					}
				});
			}
			
			$seriesSearchModal.open();
			
		},	
		/**********************************************/
		
		closeCourseModal:function(){
			if($courseSearchModal!=null){
				$courseSearchModal.close();
			}
		},
		
		showSelectableCourse:function(params){
			var courseInfoCache={};
			if($(".courseSearch").length==0){
				var courseSearchModalArray=new Array();
				courseSearchModalArray.push('<div class="remodal normal noBorder normalModal courseSearch"  data-remodal-id="courseSearch" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc">');
				courseSearchModalArray.push('<div class="box">');
				courseSearchModalArray.push('<div class="tools">');
				courseSearchModalArray.push('<span class="search">');
				courseSearchModalArray.push('<input id="search-info" name="search" type="text" placeholder="课程名称" value="">');
				courseSearchModalArray.push('<a href="javascript:void(0);" class="search-icon"><img src="../image/search01.png" alt="搜索"></a>');
				courseSearchModalArray.push('</span>');
				courseSearchModalArray.push('</div>');
				courseSearchModalArray.push('<button data-remodal-action="close" class="remodal-close" ></button>');
				courseSearchModalArray.push('</div>');
				courseSearchModalArray.push('<div class="wrapper noBorder"  style="max-height:600px;overflow-y:scroll;">');
				courseSearchModalArray.push('<table class="table table-striped table-bordered batchOperation" id="courseSelectTable">');
				courseSearchModalArray.push('<tbody>');
				courseSearchModalArray.push('<tr class="tableTh">');
				courseSearchModalArray.push('<th><input type="checkbox" id="selectAll" name="selectAll"/></th>');
				courseSearchModalArray.push('<th align="center">课程名称</th>');
				courseSearchModalArray.push('<th align="center">主讲人</th>');
				courseSearchModalArray.push('<th align="center">关键字</th>');
				courseSearchModalArray.push('<th align="center">课程创建人</th>');
				courseSearchModalArray.push('<th align="center">创建时间</th>');
				courseSearchModalArray.push('</tr>');
				courseSearchModalArray.push('</tbody>');
				courseSearchModalArray.push('</table>');
				courseSearchModalArray.push('<div class="condition">');
				courseSearchModalArray.push('<ul class="pagination-admin" style="float:right"></ul>');
				courseSearchModalArray.push('<div style="clear:both"></div>');
				courseSearchModalArray.push('</div>');
				courseSearchModalArray.push('<div class="condition" style="text-align: right;">');
				courseSearchModalArray.push('<button class="btn btn-success" id="submitCourseSelect">提交</button>');
				courseSearchModalArray.push('</div>');
				courseSearchModalArray.push(' </div>');
				courseSearchModalArray.push(' </div>');
				
				$("body").append(courseSearchModalArray.join(""));
				if(params!=null&&params.actionForSubmit!=null&&typeof params.actionForSubmit=="function"){
					$(".courseSearch").on("click","#submitCourseSelect",function(){
						var courseArray=new Array();
						$(".courseSearch .infoRow input[name=courseId]:checked").each(function(index,that){
							courseArray.push(courseInfoCache[that.value]);
						});
						params.actionForSubmit({"courseList":courseArray});
					}).on("click","#selectAll",function(){
						if(this.checked){
							$(".courseSearch .infoRow input[name=courseId]").each(function(index,that){
								that.checked = true;
							});
						}
						else{
							$(".courseSearch .infoRow input[name=courseId]").each(function(index,that){
								that.checked = false;
							});
						}
						
					});
				}
				
			}
			if($courseSearchModal==null){
				$courseSearchModal=$('[data-remodal-id=courseSearch]').remodal($.defaultRemodalOption());
				$(document).on("closed","[data-remodal-id=courseSearch]",function(){
					$("#courseSelectTable .infoRow").remove();
				});
                var paginationConfig=$.getPaginationConfig();
                var courseListPaginationConfig={
                    getUrlForPagination:function(){return "../courseCourseType/listCourse.do"},
                    actionForClearLoadedData:function(){
                        $(".courseSearch #courseSelectTable .infoRow").remove();
                    },
                    actionForSucessLoadingData:function(data){
						var dataList=data.list;
						var dataLength=dataList.length;
						var htmlArray=new Array();
						for(var i=0;i<dataLength;i++){
							var currentItem=dataList[i];
							htmlArray.push("<tr class='infoRow'>");
							courseInfoCache[currentItem.courseId]=currentItem;
							htmlArray.push("<td><input  type='checkbox' value='"+currentItem.courseId+"' name='courseId' /></td>");
							htmlArray.push("<td align='center'>"+currentItem.courseName+"</td>");
							htmlArray.push("<td align='center'>"+currentItem.creator+"</td>");
							if(currentItem.keyWords==null||currentItem.keyWords==""){
								htmlArray.push("<td align='center' >"+"—"+"</td>");
							}else
								htmlArray.push("<td align='center' >"+currentItem.keyWords+"</td>");
							htmlArray.push("<td align='center' >"+currentItem.maker+"</td>");
							htmlArray.push("<td align='center' class = 'coursedisplayYes' >"+currentItem.createDate+"</td>");

							htmlArray.push("</tr>");
						}
						$("#courseSelectTable .infoRow").remove();
						$("#courseSelectTable").append(htmlArray.join(""));

                    }
                }
                $.extend(paginationConfig,courseListPaginationConfig);
                function ajaxCourse(){
                    paginationConfig.resetSearchInfo();
                    var searchInfo=$(".courseSearch .tools input[name=search]").val();
                    paginationConfig.searchInfo.courseName=searchInfo;
                    paginationConfig.actionForLoadingPagination();
                }
				$(document).on("opening","[data-remodal-id=courseSearch]",function(){
					ajaxCourse();
				});
				$(".courseSearch .tools").on("click",".search-icon",function(){
					ajaxCourse();
				}).on("keydown","input[name=search]",function(event){
					if(event.keyCode == "13"){
						$(this).parents(".search").find(".search-icon").click();
					}
				});
			}
			
			$courseSearchModal.open();
			
		},	
		
		/*********************************培训项目******************************/
		closeTrainModal:function() {
			if($trainSearchModal!=null){
				$trainSearchModal.close();
			}
		},
		
		showSelectTableTrain:function(params) {
			var trainInfoCache={};
			if($(".trainSearch").length==0){
				var trainSearchModalArray=new Array();
				trainSearchModalArray.push('<div class="remodal normal noBorder normalModal trainSearch"  data-remodal-id="trainSearch" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc">');
				trainSearchModalArray.push('<div class="box">');
				trainSearchModalArray.push('<div class="tools">');
				trainSearchModalArray.push('<span class="search">');
				trainSearchModalArray.push('<input id="search-info" name="search" type="text" placeholder="培训名称" value="">');
				trainSearchModalArray.push('<a href="javascript:void(0);" class="search-icon"><img src="../image/search01.png" alt="搜索"></a>');
				trainSearchModalArray.push('</span>');
				trainSearchModalArray.push('</div>');
				trainSearchModalArray.push('<button data-remodal-action="close" class="remodal-close" ></button>');
				trainSearchModalArray.push('</div>');
				trainSearchModalArray.push('<div class="wrapper noBorder"  style="max-height:600px;overflow-y:scroll;">');
				trainSearchModalArray.push('<table class="table table-striped table-bordered batchOperation" id="trainSelectTable">');
				trainSearchModalArray.push('<tbody>');
				trainSearchModalArray.push('<tr class="tableTh">');
				trainSearchModalArray.push('<th><input type="checkbox" id="selectAll" name="selectAll"/></th>');
				trainSearchModalArray.push('<th align="center">培训名称</th>');
				trainSearchModalArray.push('<th align="center">开始时间</th>');
				trainSearchModalArray.push('<th align="center">结束时间</th>');
				trainSearchModalArray.push('<th align="center">联系人</th>');
				trainSearchModalArray.push('<th align="center">主办单位</th>');
				trainSearchModalArray.push('</tr>');
				trainSearchModalArray.push('</tbody>');
				trainSearchModalArray.push('</table>');
				trainSearchModalArray.push('<div class="condition">');
				trainSearchModalArray.push('<ul class="pagination-admin" style="float:right"></ul>');
				trainSearchModalArray.push('<div style="clear:both"></div>');
				trainSearchModalArray.push('</div>');
				trainSearchModalArray.push('<div class="condition" style="text-align: right;">');
				trainSearchModalArray.push('<button class="btn btn-success" id="submitTrainSelect">提交</button>');
				trainSearchModalArray.push('</div>');
				trainSearchModalArray.push(' </div>');
				trainSearchModalArray.push(' </div>');
				
				$("body").append(trainSearchModalArray.join(""));
				if(params!=null&&params.actionForSubmit!=null&&typeof params.actionForSubmit=="function"){
					$(".trainSearch").on("click","#submitTrainSelect",function(){
						var trainArray=new Array();
						$(".trainSearch .infoRow input[name=trainId]:checked").each(function(index,that){
							trainArray.push(trainInfoCache[that.value]);
						});
						params.actionForSubmit({"trainList":trainArray});
					})
					.on("click","#selectAll",function(){
						if(this.checked){
							$(".trainSearch .infoRow input[name=trainId]").each(function(index,that){
								that.checked = true;
							});
						}
						else{
							$(".trainSearch .infoRow input[name=trainId]").each(function(index,that){
								that.checked = false;
							});
						}
						
					});
				}
			}
			
			if($trainSearchModal==null){
				$trainSearchModal=$('[data-remodal-id=trainSearch]').remodal($.defaultRemodalOption());
				$(document).on("closed","[data-remodal-id=trainSearch]",function(){
					$("#trainSelectTable .infoRow").remove();
				});
                var paginationConfig=$.getPaginationConfig();
                var trainListPaginationConfig={
                    getUrlForPagination:function(){return "/selectTrainingList/trainList.do"},
                    actionForClearLoadedData:function(){
                        $(".trainSearch #trainSelectTable .infoRow").remove();
                    },
                    actionForSucessLoadingData:function(data){
                        var dataList=data.list;
                        var dataLength=dataList.length;
                        var htmlArray=new Array();
                        for(var i=0;i<dataLength;i++){
                            var currentItem=dataList[i];
                            htmlArray.push("<tr class='infoRow'>");
                            trainInfoCache[currentItem.trainId]=currentItem;
                            htmlArray.push("<td><input  type='checkbox' value='"+currentItem.trainId+"' name='trainId' /></td>");
                            htmlArray.push("<td align='center'>"+currentItem.trainName+"</td>");
                            htmlArray.push("<td align='center'>"+currentItem.programStartTime+"</td>");
                            htmlArray.push("<td align='center'>"+currentItem.programEndTime+"</td>");
                            htmlArray.push("<td align='center'>"+currentItem.organizerName+"</td>");
                            htmlArray.push("<td align='center'>"+currentItem.orgName+"</td>");
                            htmlArray.push("</tr>");
                        }
                        $("#trainSelectTable .infoRow").remove();
                        $("#trainSelectTable").append(htmlArray.join(""));

                    }
                }
                $.extend(paginationConfig,trainListPaginationConfig);

/*
				var paginationConfig=$.getPaginationConfig();
				paginationConfig.getUrlForPagination=function(){return "selectTrainingListAction.do?method=trainLength"};
				paginationConfig.actionForClearLoadedData=function(){
					$(".trainSearch #trainSelectTable .infoRow").remove();
				};
				paginationConfig.actionForLoadingData=function(){
					trainInfoCache={};
					$(".trainSearch .batchOperation th #selectAll").prop("checked",false);
					$.ajax({
						url:"selectTrainingListAction.do?method=trainList",
						type:"POST",
				 		data:paginationConfig.searchInfo,
				 		dataType:"json",
				 		success:function(data){
							if(data.status){
								var dataList=data.trainList;
								var dataLength=dataList.length;
								var htmlArray=new Array();
								for(var i=0;i<dataLength;i++){
									var currentItem=dataList[i];
									htmlArray.push("<tr class='infoRow'>");
									trainInfoCache[currentItem.trainId]=currentItem;
									htmlArray.push("<td><input  type='checkbox' value='"+currentItem.trainId+"' name='trainId' /></td>");
									htmlArray.push("<td align='center'>"+currentItem.trainName+"</td>");
									htmlArray.push("<td align='center'>"+currentItem.programStartTime+"</td>");
									htmlArray.push("<td align='center'>"+currentItem.programEndTime+"</td>");
									htmlArray.push("<td align='center'>"+currentItem.organizerName+"</td>");
									htmlArray.push("<td align='center'>"+currentItem.orgName+"</td>");
									htmlArray.push("</tr>");
								}
								$("#trainSelectTable .infoRow").remove();
								$("#trainSelectTable").append(htmlArray.join(""));
							}
							else{
								$.Ntip({
				 					'content':"信息加载失败"
				 				});	
							}
				 	 	}
					})
				};*/
				$(document).on("opening","[data-remodal-id=trainSearch]",function(){
					paginationConfig.actionForLoadingPagination();	
				});
				$(".trainSearch .tools").on("click",".search-icon",function(){
					var searchInfo=$(".trainSearch .tools input[name=search]").val();
					paginationConfig.searchInfo.trainName=searchInfo;
					paginationConfig.actionForLoadingPagination();
				}).on("keydown","input[name=search]",function(event){
					if(event.keyCode == "13"){
						$(this).parents(".search").find(".search-icon").click();
					}
				});
			}
			
			$trainSearchModal.open();
		},
		
		/*******************************************************************/
		
		showUserDetail:function(params){
			if($(".userDetailModal").length==0){
				var userDetailModalArray=new Array();
				userDetailModalArray.push('<div class="remodal normal noBorder normalModal userDetailModal"  data-remodal-id="userDetailModal" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc">');
				userDetailModalArray.push('<div class="box"><button data-remodal-action="close" class="remodal-close" ></button></div>');
				userDetailModalArray.push('<div class="wrapper common" style="padding-top:0px;"></div>');
				userDetailModalArray.push('</div>');
				$("body").append(userDetailModalArray.join(""));
				
			}
			if($userDetailModal==null){
				$userDetailModal=$('[data-remodal-id=userDetailModal]').remodal($.defaultRemodalOption());
				$(document).on("closed","[data-remodal-id=userDetailModal]",function(){
					$(".userDetailModal .wrapper.common").empty();
				});
			}
			$userDetailModal.open();
			var operatorId=params.operatorId;
			$.ajax({
				type:"POST",
			    url:"../eosorgTEmployee/JSONFullDetail.do",
			    dataType:"json",
			    data:{"operatorId":operatorId},
				success:function(data){
			    	var userInfoArray=new Array();
					userInfoArray.push("<table>");
			    	if(data.success){
						var userInfo=data.data;
						userInfoArray.push("<tr>");
						userInfoArray.push("<td rowspan='4' style='padding-bottom: 15px;'><img style='width:110px;height:110px;'  src='");
						var defaultPic="";
						if(userInfo.gender=="2"){
							defaultPic="/image/headPic/female1.jpg";
						}
						else{
							defaultPic="/image/headPic/male1.jpg";
						}
						if($.trim(userInfo.headPic)==""){
							userInfo.headPic=defaultPic;
						}
						
						userInfoArray.push(userInfo.headPic);
						userInfoArray.push("' onerror='menuimgError(this,\""+defaultPic+"\")'");
						userInfoArray.push("/></td>");
						userInfoArray.push("<td  colspan='3'><span style='width: 80px;display: inline-block;font-weight: bold;'>姓名:</span>");
						userInfoArray.push(userInfo.operatorName);
						userInfoArray.push("</td>");
						userInfoArray.push("</tr>");
						userInfoArray.push("<tr>");
						userInfoArray.push("<td  colspan='3'><span style='width: 80px;display: inline-block;font-weight: bold;'>单位(部门):</span>");
						userInfoArray.push(userInfo.orgName);
						userInfoArray.push("</td>");
						userInfoArray.push("</tr>");
						userInfoArray.push("<tr>");
						userInfoArray.push("<td  colspan='3'><span style='width: 80px;display: inline-block;font-weight: bold;'>账号:</span>");
						userInfoArray.push(userInfo.userId);
						userInfoArray.push("</td>");
						userInfoArray.push("</tr>");
						userInfoArray.push("<tr>");
						userInfoArray.push("<td  colspan='3'><span style='width: 80px;display: inline-block;font-weight: bold;'>邮箱:</span>");
						userInfoArray.push(userInfo.email);
						userInfoArray.push("</td>");
						userInfoArray.push("</tr>");
						userInfoArray.push("<tr>");
						userInfoArray.push("<th style='text-align: left;'>座机</th><td >");
						userInfoArray.push(userInfo.tel);
						userInfoArray.push("</td>");
						userInfoArray.push("<th style='text-align: left;'>手机</th><td >");
						userInfoArray.push(userInfo.mobile);
						userInfoArray.push("</td>");
						userInfoArray.push("</tr>");
						userInfoArray.push("<tr>");
						userInfoArray.push("<th style='text-align: left;'>出生日期</th><td >");
						userInfoArray.push(userInfo.birthDate);
						userInfoArray.push("</td>");
						userInfoArray.push("<th style='text-align: left;'>入职时间</th><td >");
						userInfoArray.push(userInfo.regDate);
						userInfoArray.push("</td>");
						userInfoArray.push("</tr>");
						userInfoArray.push("<tr>");
						userInfoArray.push("<th style='text-align: left;'>账户创建时间</th><td >");
						userInfoArray.push(userInfo.createTime);
						userInfoArray.push("</td>");
						userInfoArray.push("<th style='text-align: left;'>最后修改时间</th><td >");
						userInfoArray.push(userInfo.lastModifyTime);
						userInfoArray.push("</td>");
						userInfoArray.push("</tr>");
					}
			    	userInfoArray.push("</table>");
					$(".userDetailModal .wrapper.common").html(userInfoArray.join(""));
				}	
			});
		},
		

	})
})


