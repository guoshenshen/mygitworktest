$(function(){
	var supportH5=$.supportHTML5();
	var $seriesEditor=$("#seriesEditor");
	var $bottombody=$(".bottombody");
	if($seriesEditor.length>0){
	
		//系列专题编辑页面
		var $seriesEditorModal=$("[data-remodal-id=seriesEditorModal]").remodal($.defaultRemodalOption());
		
		var $topicStyleModal=$("[data-remodal-id=topicStyleModal]").remodal($.defaultRemodalOption());
		var $openScopeModal=$("[data-remodal-id=openScopeModal]").remodal($.defaultRemodalOption());
		//专题展示样式选择器
		var $topicSelectModal=$("[data-remodal-id=topicSelectModal]").remodal($.defaultRemodalOption());
		
		$(document).on('closed', '.topicSelectModal', function (e) {
		  //关闭专题展示样式选择器后，重新弹出专题编辑器
			$seriesEditorModal.open();
		});
		
		
		$(document).on("opening",'[data-remodal-id=seriesEditorModal]',function(){
			
			var errorTip=function(){
				$.tips("无法设置封面图片，请确保您选择的是图片文件、大小不超过1M","系统提示",function(){
				    $(".seriesEditorModal #uploadInfo").html("仅支持图片上传"); 
				    $(".seriesEditorModal #fileQueue").empty();   	
				}); 	
			};
			
			var errorTipForMini=function(){
				$.tips("无法设置封面图片，请确保您选择的是图片文件、大小不超过1M","系统提示",function(){
				    $(".seriesEditorModal #miniFile_uploadInfo").html("仅支持图片上传"); 
				    $(".seriesEditorModal #miniFileQueue").empty();   	
				}); 
			};
			
			
			var uploadSuccess=function(file, data, response){
				var resultData=$.parseJSON(data);
				var result=resultData.status;
				var uploadResultInfo="";
				if(result){
					//$(".live-pic img",uploadAction.$currentLive).attr("src",resultData.savePath);
					 uploadResultInfo="您的专题图片更新成功";
					 $("input[name=mainPicUrl]",".seriesEditorModal").val(resultData.data);
					 setTimeout(function(){	 
						 $(".seriesEditorModal #uploadInfo").html("<a href='"+resultData.data+"' target=\"_blank\">查看图片</a>");
					 },3000);
		        }
		        else{
		        	if($.trim(data.cause)!=""){
		        		uploadResultInfo=data.cause;
		        	}	
		        }
	            $(".seriesEditorModal #uploadInfo").html(uploadResultInfo); 
	            $(".seriesEditorModal #fileQueue").empty();   
			};
			
			var uploadSuccessForMini=function(file,data,response){
				var resultData=$.parseJSON(data);
				var result=resultData.status;
				var uploadResultInfo="";
				if(result){
					//$(".live-pic img",uploadAction.$currentLive).attr("src",resultData.savePath);
					 uploadResultInfo="您的首页专题图片更新成功";
					 $("input[name=picUrl]",".seriesEditorModal").val(resultData.data);
					 setTimeout(function(){	 
						 $(".seriesEditorModal #miniFile_uploadInfo").html("<a href='"+resultData.data+"' target=\"_blank\">查看图片</a>");
					 },3000);
		        }
		        else{
		        	if($.trim(data.cause)!=""){
		        		uploadResultInfo=data.cause;
		        	}	
		        }
	            $(".seriesEditorModal #miniFile_uploadInfo").html(uploadResultInfo); 
	            $(".seriesEditorModal #miniFileQueue").empty();   
			};
			
			var dialogClose=function(queueData){
				if(queueData.filesErrored>0){
	            	uploadAction.errorTip();
	            }   
			};
			
			var onUploadStart=function(file){
				$(".seriesEditorModal #uploadInfo").html("");        
			};
			
			var onUploadStartForMini=function(file){
				$(".seriesEditorModal #miniFile_uploadInfo").html(""); 
			};
			if(supportH5){
				$('.seriesEditorModal #file_upload').uploadifive({
					'uploadScript':"/filesTool/uploadPic.do?picWidth=530&picHeight=185&uploadType=0",
					'buttonText': '图片上传',
				    'queueID':'fileQueue',
				    'multi' : false,
				    'fileType' : 'image/*',
				    'fileSizeLimit'  : "1MB", 
				    'onError':errorTip,
				    'onDialogClose' :dialogClose,
				    'onUploadComplete':uploadSuccess
				});					
				$('.seriesEditorModal #miniFile_upload').uploadifive({
					'uploadScript':"/filesTool/uploadPic.do?picWidth=88&picHeight=68&uploadType=0",
					'buttonText': '图片上传',
				    'queueID':'miniFileQueue',
				    'multi' : false,
				    'fileType' : 'image/*',
				    'fileSizeLimit'  : "1MB", 
				    'onError':errorTipForMini,
				    'onDialogClose' :dialogClose,
				    'onUploadComplete': uploadSuccessForMini
				});
				
				
			}
			else{
				//绑定动作到相片上传操作
				$(".seriesEditorModal #miniFile_upload").uploadify({
					 'overrideEvents' : ['onDialogClose'], 
				      'method'         :'get',
					  'swf'            : './js/uploadify.swf',
				      'fileTypeExts'   :'*.jpg;*.png;*.bmp;*.jpeg',
				      'uploader'       : '/filesTool/uploadPic.do?uploadType=0&picWidth=88&picHeight=68',
				      'cancelImg'      : 'image/cancel.png',
				      'fileSizeLimit'  : "1MB", 
				      'auto'           : true,
				      'multi'          : false,
				      "formData":{'picWidth':88,'picHeight':68},
				      'buttonText'     : '图片上传',
				      'onUploadStart'  :onUploadStart,
				      'queueID'        : 'miniFileQueue',
				      'successTimeout' :6000,
				      'onDialogClose' : dialogClose,
				      'itemTemplate'    : false,
				      'onUploadSuccess':uploadSuccess
				});
				
				
				$(".seriesEditorModal #file_upload").uploadify({
					 'overrideEvents' : ['onDialogClose'], 
				      'method'         :'get',
					  'swf'            : './js/uploadify.swf',
				      'fileTypeExts'   :'*.jpg;*.png;*.bmp;*.jpeg',
				      'uploader'       : '/filesTool/uploadPic.do?uploadType=0&picWidth=530&picHeight=185',
				      'cancelImg'      : 'image/cancel.png',
				      'fileSizeLimit'  : "1MB", 
				      'auto'           : true,
				      'multi'          : false,
				      "formData":{'picWidth':530,'picHeight':185},
				      'buttonText'     : '图片上传',
				      'onUploadStart'  :onUploadStart,
				      'queueID'        : 'fileQueue',
				      'successTimeout' :6000,
				      'onDialogClose' : dialogClose,
				      'onUploadSuccess':uploadSuccessForMini
				});
				
				
			}
		})
		
		
		
		
		$(document).on("opening",'[data-remodal-id=topicStyleModal]',function(){
			var errorTip=function(){
				$.tips("无法设置样式图片，请确保您选择的是图片文件、大小不超过1M","系统提示",function(){
				    $(".topicStyleModal #uploadInfo").html("仅支持图片上传"); 
				    $(".topicStyleModal #fileQueue").empty();   	
				}); 	
			};
			var uploadSuccess=function(file, data, response){
				var resultData=$.parseJSON(data);
				var result=resultData.status;
				var uploadResultInfo="";
				if(result){
					//$(".live-pic img",uploadAction.$currentLive).attr("src",resultData.savePath);
					uploadResultInfo="您的图片更新成功";
					$("input[name=bannerPicUrl]",".topicStyleModal").val(resultData.data);
					setTimeout(function(){	 
						 $(".topicStyleModal #uploadInfo").html("<a href='"+resultData.data+"' target=\"_blank\">查看图片</a>");
					},3000);
					
				}
		        else{
		        	if($.trim(data.cause)!=""){
		        		uploadResultInfo=data.cause;
		        	}	
		        }
	            $(".topicStyleModal #uploadInfo").html(uploadResultInfo); 
	            $(".topicStyleModal #fileQueue").empty();   
			};
			var dialogClose=function(queueData){
				if(queueData.filesErrored>0){
	            	uploadAction.errorTip();
	            }   
			};
			var onUploadStart=function(file){
				$(".topicStyleModal #uploadInfo").html("");        
			};
			if(supportH5){
				$('.topicStyleModal #file_upload1').uploadifive({
					'uploadScript':"/filesTool/uploadPic.do?uploadType=2&picWidth=1205&picHeight=340",
					'buttonText': '图片上传',
				    'queueID':'fileQueue',
				    'multi' : false,
				    'fileType' : 'image/*',
				    'fileSizeLimit'  : "1MB", 
				    'onError':errorTip,
				    'onDialogClose' :dialogClose,
				    'onUploadComplete':uploadSuccess
				});
			}
			else{
				//绑定动作到相片上传操作
				$('.topicStyleModal #file_upload1').uploadify({
					 'overrideEvents' : ['onDialogClose'], 
				      'method'         :'get',
					  'swf'            : './js/uploadify.swf',
				      'fileTypeExts'   :'*.jpg;*.png;*.bmp;*.jpeg',
				      'uploader'       : "/filesTool/uploadPic.do?uploadType=2&picWidth=1205&picHeight=340",
				      'cancelImg'      : 'image/cancel.png',
				      'fileSizeLimit'  : "1MB", 
				      'auto'           : true,
				      'multi'          : false,
				      "formData":{'picWidth':1205,'picHeight':340},
				      'buttonText'     : '图片上传',
				      'onUploadStart'  :onUploadStart,
				      'queueID'        : 'fileQueue',
				      'successTimeout' :6000,
				      'onDialogClose' : dialogClose,
				      'onUploadSuccess':uploadSuccess
				});
			}
		})
		
		function clearData(){
			$(".topicStyleModal input[name='title']").val("");
			$(".topicStyleModal input[name='bannerId']").val("");
			$(".topicStyleModal textarea[name='banner-description']").val("");
			$(".topicStyleModal input[name=bannerClass]").prop("checked",false);
			$(".topicStyleModal input[name=templateClass]").each(function(){
				$(this).prop("checked",false);
			});
			$(".topicStyleModal input[name='bannerPicUrl']").val("");
			$(".topicStyleModal input[name='picUrl']").val("");
			$(".topicStyleModal #uploadInfo").empty();
			$(".topicStyleModal #miniFile_uploadInfo").empty();
			
			$(".seriesEditorModal  input[name='title']").val("");
			$(".seriesEditorModal  textarea[name='detail']").val("");
			$(".seriesEditorModal  input[name='seriesId']").val("");
		}


		$.fn.extend({
			clearModal:function(){
				var $currentModal=this;
				var orgId = $("input[name=orgId]").val();
				$("input",$currentModal).val("");
				$("textarea",$currentModal).val("");
				$("#fileQueue",$currentModal).empty();
				$("#uploadInfo",$currentModal).empty();
				$("#fileQueue",$currentModal).empty();
				$("#miniFile_uploadInfo",$currentModal).empty();
				$("#fileQueue",$currentModal).empty();
				$("#uploadInfo",$currentModal).empty();
				$("input[name=orgId]").val(orgId);
				$(".selectedTopicDIV").html("");

			}

		});



		var paginationConfig=$.getPaginationConfig();
		var seriesListPaginationConfig={
			getUrlForPagination:function(){return "/seriesManage/getSeriesList.do"},
			actionForClearLoadedData:function(){
				$("#seriesList .infoRow").remove();
			},
			actionForSucessLoadingData:function(data){
				var dataList=data.list;
				var dataLength=dataList.length;
				var seriesInfoArray=new Array();
				if(dataLength>0){
					for(var i=0; i<dataLength; i++){
						var seriesInfo = dataList[i];
						var id = seriesInfo.id;
						var title = seriesInfo.title;
						var orgName = seriesInfo.sponsorInfo;
						var publishTime = seriesInfo.publishTime;
						seriesInfoArray.push('<tr class="infoRow">');
						seriesInfoArray.push('<td ><input type="checkbox" value='+id+' name="seriesId" /></td>');
						seriesInfoArray.push('<td ><a class="seriesInfo" href="javascript:void(0);">'+title+' </a></th>');
						seriesInfoArray.push('<td >'+orgName+'</td>');
						seriesInfoArray.push('<td >'+publishTime+'</td>');
						seriesInfoArray.push('<td >');
						seriesInfoArray.push('<a href="javascript:void(0);" class="editSeries">编辑</a>&nbsp;&nbsp;');
						seriesInfoArray.push('<a href="javascript:void(0);" class="previewSeries" >预览</a>&nbsp;&nbsp;');
						seriesInfoArray.push('<a href="javascript:void(0);" class="deleteSeries">删除</a>&nbsp;&nbsp;');
						if(seriesInfo.isPublish)
							seriesInfoArray.push('<a href="javascript:void(0);" class="cancelPublish">撤销发布</a>&nbsp;&nbsp;');
						else
							seriesInfoArray.push('<a href="javascript:void(0);" class="publishSeries">发布</a>&nbsp;&nbsp;');

						seriesInfoArray.push('</td>');
						seriesInfoArray.push('</tr>');
					}
				}

				$("#seriesList .infoRow").remove();
				$("#seriesList").append(seriesInfoArray.join(""));
			}
		}
		$.extend(paginationConfig,seriesListPaginationConfig);



        var topicStylePaginationConfig={
            getUrlForPagination:function(){return "/seriesManage/getBannerList.do"},
            actionForClearLoadedData:function(){
                $("#topicStyleList li").html("");
            },
            actionForSucessLoadingData:function(data){
                var dataList=data.list;
                var dataLength=dataList.length;
                var htmlArray=new Array();

                for(var i=0;i<dataLength;i++){
                    var currentItem=dataList[i];
                    var id = currentItem.id;
                    var title = currentItem.title;
                    var picUrl = currentItem.bannerPicUrl;
                    htmlArray.push("<li>");
                    htmlArray.push("<div class=\"section\">");
                    htmlArray.push("<a class=\"series-link\">");
                    htmlArray.push("<div class=\"series-pic\"><img src="+picUrl+"></img><button type='button' class='btn btn-success previewTopic'>预览</button><button type='button' class='btn btn-danger removeTopic'>删除</button></div>");
                    htmlArray.push("<input name=\"bannerId\" value="+id+" type=\"hidden\" />");
                    htmlArray.push("</a><div class=\"series-desc\">");
                    if(title==""){
                        htmlArray.push("<h2 class=\"title\">未命名标题<em></em></h2>");
                    }else {
                        htmlArray.push("<h2 class=\"title\">"+title+"<em></em></h2>");
                    }
                    htmlArray.push("</div>");
                    htmlArray.push("</li>");
                }
                $("#topicStyleList li").remove();
                $("#topicStyleList").append(htmlArray.join(""));
            }
        };
        var topicStylePaginationConfig_={
            getUrlForPagination:function(){return "/seriesManage/getBannerList.do"},
            actionForClearLoadedData:function(){},
            actionForSucessLoadingData:function(data){
                var dataList=data.list;
                var dataLength=dataList.length;
                var htmlArray=new Array();
                for ( var i = 0; i < dataLength; i++) {
                    var currentItem = dataList[i];
                    htmlArray.push("<tr class='infoRow'>");

                    htmlArray.push("<td><input id='checkbox_"
                        + currentItem.id
                        + "' type='checkbox' value='"
                        + currentItem.id
                        + "' name='selectbox' /></td>");
                    htmlArray.push("<td>");
                    htmlArray.push("<div class=\"section\">");
                    htmlArray.push("<a class=\"series-link\">");
                    htmlArray.push("<div class=\"series-pic\">");
                    htmlArray.push("<img src=\""+currentItem.bannerPicUrl+"\">");
                    htmlArray.push("<input type=\"hidden\" name=\"url\" value=\"/recommendSeries/intoSeriesItemFrame.do?seriesId="+currentItem.id+"\">");
                    htmlArray.push("</div>");
                    htmlArray.push("</a>");
                    htmlArray.push("<div class='series-desc'>");
                    htmlArray.push("<h2 class=\"title\">");
                    htmlArray.push(currentItem.title);
                    htmlArray.push("<font>");
                    htmlArray.push("</font>");
                    htmlArray.push("<em></em>");
                    htmlArray.push("</h2>");
                    htmlArray.push("</div>");
                    htmlArray.push("</td>");
                    htmlArray.push("</tr>");
                }
                $("#topicSelectTable .infoRow").remove();
                $("#topicSelectTable").append(htmlArray.join(""));
            }
        };



/*
		var topicStylePaginationConfig={
				getUrlForPagination:function(){return "seriesManageAction.do?method=getBannerLength"},
				actionForClearLoadedData:function(){
					$("#topicStyleList li").html("");
				},
				actionForLoadingData:function(){
					$.ajax({
						url:"seriesManageAction.do?method=getBannerList",
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
									var id = currentItem.id;
									var title = currentItem.title;
									var picUrl = currentItem.bannerPicUrl;
									htmlArray.push("<li>");
									htmlArray.push("<div class=\"section\">");
									htmlArray.push("<a class=\"series-link\">");
									htmlArray.push("<div class=\"series-pic\"><img src="+picUrl+"></img><button type='button' class='btn btn-success previewTopic'>预览</button><button type='button' class='btn btn-danger removeTopic'>删除</button></div>");
									htmlArray.push("<input name=\"bannerId\" value="+id+" type=\"hidden\" />");
									htmlArray.push("</a><div class=\"series-desc\">");
									if(title==""){
										htmlArray.push("<h2 class=\"title\">未命名标题<em></em></h2>");
									}else {
										htmlArray.push("<h2 class=\"title\">"+title+"<em></em></h2>");
									}
									htmlArray.push("</div>");
									htmlArray.push("</li>");
								}
								$("#topicStyleList li").remove();
								$("#topicStyleList").append(htmlArray.join(""));
							}
							else{
								$.Ntip({
				 					'content':"样式信息加载失败"
				 				});
							}
				 	 	}
					})
				}
			};*/
		/*var topicStylePaginationConfig_={
			getUrlForPagination:function(){return "seriesManageAction.do?method=getBannerLength"},
			actionForClearLoadedData:function(){},
			actionForLoadingData:function(){
				$.ajax({
					url:"seriesManageAction.do?method=getBannerList",
					type:"POST",
			 		data:paginationConfig.searchInfo,
			 		dataType:"json",
			 		success:function(data){
						if(data.status){
							var dataList=data.resultList;
							var dataLength=dataList.length;
							var htmlArray=new Array();

							for ( var i = 0; i < dataLength; i++) {
								var currentItem = dataList[i];
								htmlArray.push("<tr class='infoRow'>");

								htmlArray.push("<td><input id='checkbox_"
										+ currentItem.id
										+ "' type='checkbox' value='"
										+ currentItem.id
										+ "' name='selectbox' /></td>");
								htmlArray.push("<td>");
								htmlArray.push("<div class=\"section\">");
								htmlArray.push("<a class=\"series-link\">");
								htmlArray.push("<div class=\"series-pic\">");
								htmlArray.push("<img src=\""+currentItem.bannerPicUrl+"\">");
								htmlArray.push("<input type=\"hidden\" name=\"url\" value=\"recommendSeriesAction.do?method=intoSeriesItemFrame&amp;seriesId="+currentItem.id+"\">");
								htmlArray.push("</div>");
								htmlArray.push("</a>");
								htmlArray.push("<div class='series-desc'>");
								htmlArray.push("<h2 class=\"title\">");
								htmlArray.push(currentItem.title);
								htmlArray.push("<font>");
								htmlArray.push("</font>");
								htmlArray.push("<em></em>");
								htmlArray.push("</h2>");
								htmlArray.push("</div>");
  								htmlArray.push("</td>");


								htmlArray.push("</tr>");
							}
							$("#topicSelectTable .infoRow").remove();
							$("#topicSelectTable").append(htmlArray.join(""));
						}
						else{
							$.Ntip({
			 					'content':"样式信息加载失败"
			 				});
						}
			 	 	}
				})
			}
		};*/

		
		//绑定事件
		$seriesEditor.on("click","input[name=tab]",function(){		//专题与样式切换
				var tabValue=this.value;
				$(".query").siblings("input[name=name]").val("");
				paginationConfig.searchInfo.name=null;
				if(tabValue=="1"){
					$.extend(paginationConfig,seriesListPaginationConfig);
					$(".mainContent").addClass("showSeries");	
				}
				else{
					$.extend(paginationConfig,topicStylePaginationConfig);
					$(".mainContent").removeClass("showSeries");
				}
				paginationConfig.actionForLoadingPagination();
			}).
			on("click",".query",function(){							//点击查询
				var name=$(this).siblings("input[name=name]").val();
				paginationConfig.searchInfo.name=name;
				paginationConfig.actionForLoadingPagination();
			}).
			on("click",".seriesContainer .foradd",function(){
				$(".seriesEditorModal").clearModal();
				var allorgId = $("#allorgId").val();
				var allorgName = $("#allorgName").val();
				$("#orgId").val(allorgId);
				$("#orgName").val(allorgName);
				$seriesEditorModal.open();
				
				//window.location.href = "seriesManageAction.do?method=toAddSeries";
			}).
			on("click",".topicStyleContainer .foradd",function(){	//点击新增样式
				clearData();
				$topicStyleModal.open();
			})
			.on("click","#seriesList .seriesInfo",function(){		//点击专题名
				$(".seriesEditorModal").clearModal();
				var seriesId=$("input[name=seriesId]",$(this).parents("tr")).val();
				//回显系列专题与专题样式关联信息
				//获取系列专题信息
				//回显图片
				$.ajax({
					method:"POST",
					data:{"seriesId":seriesId},
					url:"/seriesManage/findSeriesById.do",
					dataType:"json",
					success:function(data){
						var series = data.data;
						if(series.sponsorInfo!=null && series.sponsorInfo!= ""){
							$(".seriesEditorModal input[name='orgName']").val(series.sponsorInfo);
						}
						else{
							$("input[name=orgName]").val($("#allorgName").val());
						}
						$("input[name=orgId]").val($("#allorgId").val());
						$(".seriesEditorModal input[name='title']").val(series.title);
						$(".seriesEditorModal input[name='seriesId']").val(seriesId);
						$(".seriesEditorModal textarea[name='detail']").val(series.detail);
						$("input[name=mainPicUrl]",".seriesEditorModal").val(series.mainPicUrl);
						if(series.mainPicUrl!=null&&series.mainPicUrl!=""){
							 $(".seriesEditorModal #uploadInfo").html("<a href='"+series.mainPicUrl+"' target=\"_blank\">查看图片</a>");
						};
						$("input[name=picUrl]",".seriesEditorModal").val(series.mainPicUrl);
						if(series.picUrl!=null&&series.picUrl!=""){
							 $(".seriesEditorModal #miniFile_uploadInfo").html("<a href='"+series.picUrl+"' target=\"_blank\">查看图片</a>");
						}
						//$(".seriesEditorModal #uploadInfo").html("<a href='"+series.mainPicUrl+"'>查看图片</a>");
					}
					
				});
				//获取样式信息
				$.ajax({
					method:"POST",
					data:{"seriesId":seriesId},
					url:"/seriesManage/findSeriesBannerByCondition.do",
					dataType:"json",
					success:function(data){
						if(data.status){
							var bannerList = data.data;
							var selectTopicHtml=new Array();
							//将绑定样式回显form表单隐藏域
							for(var index in bannerList) {  
							    var value = bannerList[index];
							    selectTopicHtml.push("<input type='hidden' name='topicBannerId' value='"+value.bannerId+"'  />");   
							};  
							$(".seriesEditorModal .selectedTopicDIV").html(selectTopicHtml.join(""));	
							//
							
						}
					}
					
				});
				$seriesEditorModal.open();
			}).
			on("click","#seriesList .editSeries",function(){		//编辑
				var seriesId=$("input[name=seriesId]",$(this).parents("tr")).val();
				//管理专题资源
				window.open("/seriesManage/toSeriesResourceBinding.do?seriesId="+seriesId,"_blank");
			}).
			on("click","#seriesList .previewSeries",function(){		//预览
				var seriesId=$("input[name=seriesId]",$(this).parents("tr")).val();
				window.open("/recommendSeries/intoSeriesItemFrame.do?seriesId="+seriesId,"_blank");
			}).
			on("click",".series-pic",function(){					//点击样式图片
				clearData();
				var bannerId=$(this).siblings("input[name=bannerId]").val();
				$.ajax({
					method:"POST",
					data:{"bannerId":bannerId},
					url:"/seriesManage/findBannerById.do",
					dataType:"json",
					success:function(data){
						var banner = data.data;
						var bannerId = banner.id;
						var title = banner.title;
						var description = banner.description;
						var bannerClass = banner.bannerClass;
						var templateArray = banner.templateClass.split(",");
						var bannerPicUrl = banner.bannerPicUrl;
						$(".topicStyleModal input[name='bannerId']").val(bannerId);
						$(".topicStyleModal input[name='title']").val(title);
						$(".topicStyleModal textarea[name='banner-description']").val(description);
						$(".topicStyleModal input[name='bannerPicUrl']").val(bannerPicUrl);
						if(bannerPicUrl!=null&&bannerPicUrl!=""){
							 $(".topicStyleModal #uploadInfo").html("<a href='"+bannerPicUrl+"' target=\"_blank\">查看图片</a>");
						}
						$(".topicStyleModal input[name=templateClass]").each(function(){
							var templateValue = $(this).val();
							var isHave = $.inArray(templateValue, templateArray);
							if(isHave!=-1){
								$(this).prop("checked",true);
							}
						});
						if($(".topicStyleModal input[name=bannerClass]").val()==bannerClass){
							$(".topicStyleModal input[name=bannerClass]").prop("checked",true);
						}
						
						$topicStyleModal.open();
					}
					
				});
				
			}).on("click",".previewTopic",function(){
				var seriesId = $(this).parent().siblings("input[name=bannerId]").val();
				window.open("/recommendSeries/intoBannerPreview.do?topicBannerId="+seriesId,"_blank");
				return false;
				
			}).
			on("click",".removeTopic",function(){
				var bannerId=$(this).parent().siblings("input[name=bannerId]").val();
				$.ajax({
					method:"POST",
					data:{"bannerId":bannerId},
					url:"/seriesManage/deleteTopicBanner.do",
					dataType:"json",
					success:function(data){
						if(data.status){
							$.Ntip({
								'content':"删除成功"
							});	
						}else{
							var seriesList = data.data;
							var length = seriesList.length;
							var names = "已关联如下专题无法删除：<br/>";
							if(length>0){
								for(var i=0; i<length; i++){
									if(seriesList[i]!=null)
									names += seriesList[i].title+"<br/>";
								}
							}
							
							$.Ntip({
								'content':names
							});	
							
						}
						paginationConfig.actionForLoadingPagination();
					}
					
				});
				
				return false;
			}).
			on("click",".publishSeries",function(){					//点击发布后弹窗
				$("#fristRadio").get(0).checked=true; 
				var seriesId=$("input[name=seriesId]",$(this).parents("tr")).val();
				$(".openScopeModal input[name='seriesId']").val(seriesId);
				
				$openScopeModal.open();
			}).
			on("keydown","input[name=name]",function(event){		//回车搜索
				if(event.keyCode==13){
					$("button[id='search']").click();
					event.stopPropagation();	
				}
			}).
			on("click",".cancelPublish",function(){					//取消发布
				var seriesId=$("input[name=seriesId]",$(this).parents("tr")).val();
				var openScope = 2201;
				$.ajax({
					method:"POST",
					data:{"seriesId":seriesId,"openScope":openScope},
					url:"/seriesManage/publishSeries.do",
					dataType:"json",
					success:function(data){
						if(data.status){
							if(data.data){
								$.Ntip({
									'content':"发布成功"
								});	
							}else{
								$.Ntip({
									'content':"操作成功"
								});	
							}
						}else{
							$.Ntip({
			 					'content':"操作失败"
			 				});	
						}
						$openScopeModal.close();
						$.extend(paginationConfig,seriesListPaginationConfig);
						paginationConfig.actionForLoadingPagination();
					}
					
				});
			}).
			on("click",".deleteSeries",function(){					//点击删除
				var seriesId=$("input[name=seriesId]",$(this).parents("tr")).val();
				$.Nconfirm({
		        	'confirmQuestion':"确定要删除所选专题吗？",
		        	'onConfirm':function(){
		        		$.ajax({
							method:"POST",
							data:{"seriesId":seriesId},
							url:"/seriesManage/deleteSeries.do",
							dataType:"json",
							success:function(data){
								if(data.status){
									$.Ntip({
					 					'content':"删除成功"
					 				});	
									$.extend(paginationConfig,seriesListPaginationConfig);
									paginationConfig.actionForLoadingPagination();
								}else{
									$.Ntip({
					 					'content':"操作失败"
					 				});	
								}
								
							}
							
						});
		        	},
					'onDeny':function(){}
		       		})
		       		
			});	
			
			$("input[name=tab]:first",$seriesEditor).click();
				
		}
		
	
		$(".openScopeModal").on("click",".publicSeries",function(){	//选择公开范围
			var openScope = $("input[name=seriesOpenScope]:checked").val();
			var seriesId=$(".openScopeModal input[name='seriesId']").val();
			$.ajax({
				method:"POST",
				data:{"seriesId":seriesId,"openScope":openScope},
				url:"/seriesManage/publishSeries.do",
				dataType:"json",
				success:function(data){
					if(data.status){
						if(data.data){
							$.Ntip({
								'content':"发布成功"
							});	
						}else{
							$.Ntip({
								'content':"撤销发布成功"
							});	
						}
					}else{
						$.Ntip({
		 					'content':"操作失败"
		 				});	
					}
					$openScopeModal.close();
					$.extend(paginationConfig,seriesListPaginationConfig);
					paginationConfig.actionForLoadingPagination();
				}
				
			});
			
		})
		
		$(".topicStyleModal").on("click",".updateSection",function(){	//样式修改或提交
			var title = $(".topicStyleModal input[name='title']").val();
			if(title.length==0){
				$.Ntip({
					'content':"风格名称不能为空",
				})
			}else{
				//ajax触发保存或者更新
				$.ajax({
					method:"POST",
					data:$('.topicStyleForm').serialize(),
					url:"/seriesManage/addorEditTopicbanner.do",
					dataType:"json",
					success:function(data){
						if(data.status){
							$.Ntip({
								'content':"操作成功",
							})
							$topicStyleModal.close();
							paginationConfig.actionForLoadingPagination();
						}
					}
					
				});
			}
		});
	
		$(".topicSelectModal").on("click","#selectTopic",function(){
			var selectTopicHtml=new Array();
			$(".topicSelectModal input:checkbox").each(function(){	
				if(this.checked){
					var value=this.value;
					selectTopicHtml.push("<input type='hidden' name='topicBannerId' value='"+value+"'  />");
				}
			})
			$(".seriesEditorModal .selectedTopicDIV").html(selectTopicHtml.join(""));	
			$topicSelectModal.close();
		});
	
		$(".seriesEditorModal").on("click",".addOrEdit",function(){
			var title = $("input[name='title']").val();
			if(title.length==0){
				$.Ntip({
					'content':"专题名称不能为空",
				})
			}else{
				//ajax触发保存或者更新
				$.ajax({
					method:"POST",
					data:$('.form-horizontal').serialize(),
					url:"/seriesManage/addOrEditSeries.do",
					dataType:"json",
					success:function(data){
						if(data.status){
							$.Ntip({
								'content':"操作成功",
							})
							$seriesEditorModal.close();
							$.extend(paginationConfig,seriesListPaginationConfig);
							paginationConfig.actionForLoadingPagination();
						}
					}
					
				});
			}
		}).on("click","#selectBanner",function(){
			//弹出样式库选择器
			//对于已经关联样式的专题，将该专题绑定的样式id放入数组
			var selectedTopicIdArray=new Array();
			//加载可选样式列表
			$.extend(paginationConfig,topicStylePaginationConfig_);
			paginationConfig.actionForLoadingPagination();
			$topicSelectModal.open();
			//检查回显
			checkRedisplay();
			var count = 2;
			var t1=window.setInterval(refreshCount, 1000);
		    function refreshCount() {
		    	if(count-- <0 ){
		    		window.clearInterval(t1);
		    	}
		    	checkRedisplay();
		    }
		});
		
		//检查回显
		function checkRedisplay(){
			var selectedArray = new Array();
			var $input = $(".seriesEditorModal .selectedTopicDIV").find("input[type=hidden]");
			$input.each(function () {
				var value = this.value;
				selectedArray.push(value);
			});
			$(".topicSelectModal input:checkbox").each(function(){	
				
				if(selectedArray.indexOf(this.value)>=0){
					this.checked=true;
				}
					
			});
		}
		
})