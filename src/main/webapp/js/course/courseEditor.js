var courseEditorArea={};


$(function(){
	
	var supportH5=$.supportHTML5();
	
	//课程基本信息编辑
	if($("#courseEditor").length>0){
		courseEditorArea={
				rootDomain:2,
				$teacherSearch:null
		}
		
		$.extend({
			searchTeacher:function(){
				courseEditorArea.$teacherSearch.open();
			},
			
			bindTeacher:function(actionAfterCourseBinded){
				var teacherId=null;
				var $checkRadio=$("#teacherSelectTable input[type=radio]:checked");
				if($checkRadio.length>0){
					var $selectTr=$checkRadio.parents("tr");
					var teacherName=$("input[name=teacherName]",$selectTr).val();
					teacherId=$checkRadio.val();
					if(typeof actionAfterCourseBinded=="function"){
						actionAfterCourseBinded({"teacherId":teacherId,"teacherName":teacherName});
					}
				}
				else{
					$.Ntip({
						'content':"您尚未指定需要关联的教师"
					});
					return;
				}
			},
			
			
			loadTerritory:function(){
				
				$(".territoryEditor .territory").each(function(index,that){
					if(index==0){
						$(that).empty();
					}
					else{
						$(that).remove();
					}
				});
			
				var traceBack=new Array();
				var treeInfo=new Array();
				var historyInfo=new Array();
				
				$(".territoryEditor").on("click",".territory",function(){
					var value=parseInt($(this).val());
					var index=-1;
					$(".territoryEditor .territory").each(function(index1,that){
						if(that.value==value){
							index=index1+1;
							return false;
						}
					})
					
					var $next= $(".territoryEditor .territory").eq(index);
					
					$(".territoryEditor .selectTerritory").removeClass("selectTerritory");
					if(treeInfo[value]==null){
						//当前领域没有子领域
						if($next.length!=0){
							$next.parent().remove();
						}
						$(this).addClass("selectTerritory");
					}
					else{
						//当前领域具有子领域
						if($next.length==0){
							var $nextContainer=$("<div class='col-sm-2'><select class='territory form-control col-sm-3'></select></div>").appendTo($(this).parents(".territoryEditor"));
							$next=$(".territory",$nextContainer);
						}
						else{
							$next.empty();
						}
						$next.addClass("selectTerritory");
						var leaves=treeInfo[value];
						var leavesIength=leaves.length;
						var leaveshtmlArray=new Array();
						leaveshtmlArray.push("<option value='-1'>请选择</option>");
						var trigger=false;
						var territoryId=null;
						for(var i=0;i<leavesIength;i++){
							leaveshtmlArray.push("<option ");
							if($.inArray(leaves[i].courseTypeID,historyInfo)>=0){
								leaveshtmlArray.push(" selected='selected' ");
								trigger=true;
								territoryId=leaves[i].courseTypeID;
							}
							leaveshtmlArray.push(" value='"+leaves[i].courseTypeID+"'>"+leaves[i].courseTypeName+"</option>");
							
						}
						$($next).append(leaveshtmlArray.join(""));
						if(trigger){
							$(".territoryEditor .territory:last").click();
							historyInfo.splice($.inArray(territoryId,historyInfo),1);
						}
					}
				});
				
				var $resourceCourseType=$("#resourceCourseType");
				
				

				$.ajax({
					method:"POST",
					data:{"rootDomain":courseEditorArea.rootDomain},
					url:"../onlineStudy/loadDomainSystem.do",
					dataType:"json",
					success:function(data){
						if(data.status ){
							var treeData = data.data;
							var branchesLength=treeData.length;
							
							for(var i=0;i<branchesLength;i++){
								var branch=treeData[i];
								var domainId=branch.domainId;
								if(traceBack[domainId]==null){
									traceBack[domainId]={
										layer:0,
										parent:null
									}
								}
								var leaves=branch.subdomain;
								treeInfo[domainId]=leaves;
								var parentTrace=traceBack[domainId];
								var leavesLength=leaves.length;
								for(var j=0;j<leavesLength;j++){
									var leaf=leaves[j];
									var leafId=leaf.courseTypeID;
									traceBack[leafId]={
										layer:parentTrace.layer+1,
										parent:domainId,
										info:leaf
									}
								}
							}
							var leaveshtmlArray=new Array();
							var leaves=treeInfo[courseEditorArea.rootDomain];
							var leavesIength=leaves.length;
							if($resourceCourseType!=null&&$resourceCourseType.length>0){
								var leafNow=parseInt($resourceCourseType.val());
								historyInfo.push(leafNow);
								while(leafNow!=null&&traceBack[leafNow]!=null){
									var parentLeaf=traceBack[leafNow].parent;
									if(parentLeaf!="2"){
										historyInfo.push(parentLeaf);
									}
									leafNow=parentLeaf;
								}		
							}
							leaveshtmlArray.push("<option value='-1'>请选择</option>");
							var trigger=false;
							for(var i=0;i<leavesIength;i++){
								leaveshtmlArray.push("<option ");
								if($.inArray(leaves[i].courseTypeID,historyInfo)>=0){
									leaveshtmlArray.push(" selected='selected' ");
									trigger=true;
								}
								leaveshtmlArray.push("value='"+leaves[i].courseTypeID+"'>"+leaves[i].courseTypeName+"</option>");
							}
							$(".territoryEditor .territory:last").append(leaveshtmlArray.join(""));
							if(trigger){
								$(".territoryEditor .territory:first").click();
							}
							
						}
						else{
							
						}
					}
				});
			}
		});
		
		
		
		
		
		
		courseEditorArea.$teacherSearch=$('[data-remodal-id=teacherSearch]').remodal($.defaultRemodalOption());

		var paginationConfig=$.getPaginationConfig();
        var teacherListPaginationConfig={
            getUrlForPagination:function(){return "../tchrBaseInfo/listAllTeacherForCourse.do"},
            actionForClearLoadedData:function(){
            	$("#teacherSelectTable .infoRow").remove();
        	},
            actionForSucessLoadingData:function(data){
                    var dataList=data.list;
                    var dataLength=dataList.length;
                    var htmlArray=new Array();
                    for(var i=0;i<dataLength;i++){
                        var currentItem=dataList[i];
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

        

		//打开弹出框执行事情
		$(document).on("opening","[data-remodal-id=teacherSearch]",function(){
            ajaxTeacher();
            $(".teacherSearch .tools input[name=search]").val("");
		});
		
		$("#searchTeacher").click(function(){
			$.searchTeacher();
		});
		
		$(".teacherSearch .tools").on("click",".search-icon",function(){
            ajaxTeacher();
		}).on("keydown","input[name=search]",function(event){
			if(event.keyCode == "13"){
				$(this).parents(".search").find(".search-icon").click();
			}
		});
		
		$("#submitTeacherSelect").click(function(){
			$.bindTeacher(function(){
				var htmlInfo='<input type="text" id="teacherId" name="teacherId" value="'+arguments[0].teacherId+'" style="display: none;">';
				$("#courseEditor #teacherId").remove();
				var $creator=$("#courseEditor #creator");
				$creator.val(arguments[0].teacherName);
				$creator.parent("div").append(htmlInfo);
				$("#addTeacer").hide();
				courseEditorArea.$teacherSearch.close();
			});
		})	
	}
	
	
	//课程资料上传
	var $courseMaterialEditor=$("#courseMaterialEditor");
	if($courseMaterialEditor.length>0){
		
		var errorTip=function(){
			$.tips("无法设置课程封面图片，请确保您选择的是图片文件、大小不超过1M","系统提示",function(){
			    $("#coursePicEditor #uploadInfo").html("仅支持图片上传"); 
			    $("#coursePicEditor #imgQueue").empty();   	
			}); 	
		};
		var uploadSuccess=function(file, data, response){
			var courseId=$("#coursePicEditor input[name=courseId]").val();
			var resultData=$.parseJSON(data);
			var result=resultData.status;
			var uploadResultInfo="";
			if(result == 0 ){
				uploadResultInfo="";
				$.ajax({
					data:{"pictureUrl":resultData.data,"courseId":courseId},
					url:"../courseCourseType/updateCoursePic.do",
					method:"post",
					dataType:"json",
					success:function(data){
						if(data.status  ){
							uploadResultInfo="您的课程图片更新成功";
						}
						else{
							uploadResultInfo="课程图片更新失败";
						}
						 //$("input[name=mainPicUrl]",".seriesEditorModal").val(resultData.savePath);
						 $("#coursePicEditor #imgUploadInfo").html(uploadResultInfo);
						 setTimeout(function(){	 
							 $("#coursePicEditor #imgUploadInfo").empty();
							 $("#coursePicEditor #imgQueue").empty();   	
						 },3000);
						 var $coursePic=$("#coursePicEditor .coursePic");
						 if($coursePic.length==0){
							 $("#coursePicEditor .coursePicContainer").html('<img  class="coursePic" style="width:450px;height:240px;"   />');
						 }
						 $("#coursePicEditor .coursePic").attr("src",resultData.data);
						 $("#courseImage img").attr("src",resultData.data)
					},
					error:function(){
						uploadResultInfo="课程图片更新失败";
						 $("#coursePicEditor #imgUploadInfo").html(uploadResultInfo);
						 setTimeout(function(){	 
							 $("#coursePicEditor #imgUploadInfo").empty();
						 },3000);
					}
					
				})
				
				
				 
				
				 
	        }
	        else{
	        	if($.trim(data.cause)!=""){
	        		uploadResultInfo=data.cause;
	        	}	
	        }
            $("#coursePicEditor #imgUploadInfo").html(uploadResultInfo); 
            $("#coursePicEditor #fileQueue").empty();   
		};
		var dialogClose=function(queueData){
			if(queueData.filesErrored>0){
            	uploadAction.errorTip();
            }   
		};
		var onUploadStart=function(file){
			$("#coursePicEditor #uploadInfo").html("");        
		};
		if(supportH5){
			$('#coursePicEditor #coursePicUploader').uploadifive({
				'uploadScript':"../filesTool/uploadPic.do?picWidth=450&picHeight=240&uploadType=3",
				'buttonText': '图片上传',
			    'queueID':'imgQueue',
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
			$("#coursePicEditor #coursePicUploader").uploadify({
				 'overrideEvents' : ['onDialogClose'], 
			      'method'         :'get',
				  'swf'            : '../js/uploadify.swf',
			      'fileTypeExts'   :'*.jpg;*.png;*.bmp;*.jpeg',
			      'uploader'       : '../filesTool/uploadPic.do?uploadType=3&picWidth=450&picHeight=240',
			      'cancelImg'      : 'image/cancel.png',
			      'fileSizeLimit'  : "1MB", 
			      'auto'           : true,
			      'multi'          : false,
			      "formData":{'picWidth':450,'picHeight':240},
			      'buttonText'     : '图片上传',
			      'onUploadStart'  :onUploadStart,
			      'queueID'        : 'imgQueue',
			      'successTimeout' :6000,
			      'onDialogClose' : dialogClose,
			      'onUploadSuccess':uploadSuccess
			});
		}	
	}
	
	
	/*
	var $fileUpload=$("#file_upload");
	
	if($fileUpload.length>0){
		//课程上传
		var fileUploadStart=function(file){
			$("#file_upload").uploadify("settings", "formData",{'uploadCourseId':$('#uploadCourseId').val(),
																					'operatorOrgId':$('#operatorOrgId').val(),
																					'uploadCourseTypeId':$('#uploadCourseTypeId').val(),
																					'orgDomainName':$('#orgDomainName').val()});
			var docType="swf,Swf,doc,docx,ppt,pptx,xls,html,pdf,HTML,DOC,DOCX,PPTX,XLSX,PDF";
			var fileType=file.name.substr(file.name.indexOf(".")+1);
			if(docType.indexOf(fileType)>-1){
			  $('#convertVideo').css('display','block');
			} 
			$('.btnContainer').css('display','none');     
		}

		var fileUploadComplete=function(file, data){
			 $('#uploadFileName').val(file.name);
		     $('#form1').submit(); 
		     $('.btnContainer').css('display','block');    
		}
		
		function judgeCourseExist(id){
	        $.ajax({
	            url:'fileUpload.do?method=checkIsExistCourse',
	            type:'post',
	            dataType:'text',
	            data:{courseId:id},
	            success:function(data,evt){
	               if(data=='true'){
	            		$.Nconfirm({
	    					'confirmQuestion':"已有课件，是否覆盖？",
	    					'onConfirm':function(){
	    						 $('#file_upload').uploadify('upload');
	    					},
	                      	'onDeny':function(){
	                      		$('#file_upload').uploadify('cancel','*');
	                      	}
	                      })
	               }else
	                  $('#file_upload').uploadify('upload');
	            }
	        })
	     }
	     
		var onSelectFileToUpload=function(){
			judgeCourseExist($('#uploadCourseId').val());	
		}
        
		if(false){
			$.fn.uploadify=$.fn.uploadifive;
			$fileUpload.uploadify({
				'uploadScript':$('#uploadAddress').val()+'/uploadVideo?uploadCourseId='+$('#uploadCourseId').val()+"&operatorOrgId="+$('#operatorOrgId').val()+"&uploadCourseTypeId="+$('#uploadCourseTypeId').val()+"&orgDomainName="+$('#orgDomainName').val(),
				'buttonText': '选择视频或文档',
				'queueID':'fileQueue',
				'method': 'get',
				'multi' : false,
				'auto'  : true,
				'onError':errorTip,
				//'onSelect':onSelectFileToUpload,
				//'onDialogClose' :dialogClose,
				'onUploadComplete':fileUploadComplete
			});
		}
		else{
			$fileUpload.uploadify({
				 'overrideEvents' : ['onDialogClose'], 
				  'method'         :'get',
				  'swf'            : '../js/uploadify.swf',
				  'fileTypeExts'   :'*.json;*.JSON;*.mts;*.swf;*.SWF;*.doc;*.docx;*.ppt;*.pptx;*.xls;*.xlsx;*.html;*.HTML;*.DOC;*.DOCX;*.PPTX;*.XLSX;*.PDF;*.wmv;*.avi;*.dat;*.asf;*.rm; *.rmvb;*.ram;*.mpg;*.mpeg;*.3gp;*.mov;*.mp4;*.m4v;*.dvix;*.dv;*.dat;*.mkv;*.flv;*.vob;*.ram;*.qt;*.divx;*.cpk;*.fli;*.flc;*.mod',
				  'uploader'       : $('#uploadAddress').val()+'/uploadVideo',
				  'cancelImg'      : 'image/cancel.png',
				  'auto'           : false,
				  'multi'          : false,
				  'formData':{'uploadCourseId':$('#uploadCourseId').val(),
						'operatorOrgId':$('#operatorOrgId').val(),
						'uploadCourseTypeId':$('#uploadCourseTypeId').val(),
						'orgDomainName':$('#orgDomainName').val()},
				  'buttonText'     : '选择视频或文档',
				  'onUploadStart'  :fileUploadStart,
				  'queueID'        : 'fileQueue',
				  'successTimeout' :6000,
				  //'onDialogClose' : dialogClose,
				  'onSelect':onSelectFileToUpload,
				  'onUploadSuccess':fileUploadComplete
			});
		}
	}
	*/
	
	//课程章节编辑界面
	var $courseSectionEditor=$("#courseSectionEditor");
	if($courseSectionEditor.length>0){
		
		var $sectionEditor=null;
		var $currentChapterLine=null;
		
		$sectionEditor=$('[data-remodal-id=sectionEditor]').remodal($.defaultRemodalOption());
		$(document).on("closed","[data-remodal-id=sectionEditor]",function(){
			//$(".sectionEditor .wrapper.common").empty();
		});

		//打开弹出框执行事情
		$(document).on("opening","[data-remodal-id=sectionEditor]",function(){
		});
		
		$courseSectionEditor.on("click","#addSection",function(){
			$currentChapterLine=null;
			$sectionEditor.open();
			$("#chapterid").val("");
			$("#chapterName").val("");
		})
		
		$courseSectionEditor
		.on("click",".forUpdateSection",function(){
			$currentChapterLine=$(this).parents("tr");
			$sectionEditor.open();
			//var $sectionId=$("input[name=sectionId]",$currentChapterLine);
			var chapterid = $("input[name='selectbox']",$(this)).val();
			$("#chapterName").val($("input[name='chapterName']",$(this)).val());
			$("#chapterid").val(chapterid);
			
		})
		.on("click",".editSectionFragment",function(){
			var chapterid = $("input[name='selectbox']",$(this)).val();
			window.open("../addLessonInfo/intoSectionFragmentEditor.do?chapterId="+chapterid, "_blank");
		})
		.on("click",".updateSection",function(){
			/*var chapterInfo={};
			$("input",$sectionEditor).each(function(index,that){
				chapterInfo[that.name]=that.value;
			});*/
			var chapterName = $("#chapterName").val();
			var chapterId = $("#chapterid").val();
			var courseId = $("#courseId").val();
			 if(chapterName==null||chapterName==""){
                 $.Ntip({
                     'content':"章节名称不能为空或者为空格!",
                 })
			        return false;
			    }
			$.ajax({
				url:"../addLessonInfo/insertChapter.do",
				method:"POST",
				data:{chapterName:chapterName,chapterId:chapterId,courseId:courseId},
				dataType:"json",
				success:function(data){
					if(data.status ){
						init();
						$sectionEditor.close();
                        $.Ntip({'content':"操作成功"});
					}else{
                        $.Ntip({'content':"操作失败"});
					}
				},
				error:function () {
                    $.Ntip({'content':"操作失败"});
                }
			});
			 return false;
		});	
	}
	
	//章节片段编辑界面
	var $courseSectionFragmentEditor=$("#courseSectionFragmentEditor");
	if($courseSectionFragmentEditor.length>0){
		
		var $sectionFragmentEditor=null;
		var $currentFragmentLine=null;
		
		$sectionFragmentEditor=$('[data-remodal-id=sectionFragmentEditor]').remodal($.defaultRemodalOption());
		$(document).on("closed","[data-remodal-id=sectionFragmentEditor]",function(){
			//$(".sectionFragmentEditor .wrapper.common").empty();
		});

		//打开弹出框执行事情
		$(document).on("opening","[data-remodal-id=sectionFragmentEditor]",function(){
		});
		
		$courseSectionFragmentEditor.on("click","#addFragment",function(){
			$currentFragmentLine=null;
			$sectionFragmentEditor.open();
			$("#courseId").val("");
			$("#courseName").val("");
			$("#classHour").val("");
			$("#kindId").val(0);
			
			
		})
		.on("click",".forUpdateFragment",function(){
			var courseid = $("input[name='selectbox']",$(this)).val();
			$("#courseName").val( $("input[name='courseName']",$(this)).val())
			$("#classHour").val( $("input[name='classHour']",$(this)).val())
			$("#kindId").val( $("input[name='sliceType']",$(this)).val())
			$("#courseId").val(courseid);
			$currentFragmentLine=$(this).parents("tr");
			$.ajax({
				url:"../addLessonInfo/isCourseHold.do",
				method:"POST",
				data:{courseId:courseid},
				dataType:"json",
				success:function(data){
					if(data.status ){
						$sectionFragmentEditor.open();
					}
					if(data.statusCode == 1){
                        $.Ntip({'content':"该课程已被使用不可以修改"});
					}


				}
			})
			/*var $fragmentId=$("input[name=fragmentId]",$currentChapterLine);
			$.ajax({
				url:"获取对应片段信息的url",
				method:"POST",
				data:{"fragmentId":$fragmentId.val()},
				dataType:"json",
				success:function(data){
					if(data.status){
						var $sectionEditor=$(".sectionFragmentEditor .wrapper.common");
						var chapter=data.chapter;
						$("input[name=chapterName]",$sectionEditor).val(chapter.chapterName);
						$("input[name=classHour]",$sectionEditor).val(chapter.classHour);
						$("input[name=chapterId]",$sectionEditor).val(chapter.chapterId);
					}
				}
			})*/
		})
		.on("click",".editFragmentMaterial",function(){
			var sliceType = $("input[name='sliceType']",$(this)).val();
			var courseid = $("input[name='selectbox']",$(this)).val();
			var chapterId = $("input[name='chapterId']",$(this)).val();
			$.ajax({
				url:"../addLessonInfo/isCourseHold.do",
				method:"POST",
				data:{courseId:courseid},
				dataType:"json",
				success:function(data){
					if(data.status){
						window.location.href = "../addLessonInfo/uploadFileOrVideo.do?sliceType="+sliceType+"&courseId="+courseid+"&chapterId="+chapterId;
					}
					if(data.statusCode == 1){
                        $.Ntip({'content':"该课程已被使用不可以编辑"});
					}

				}
			})
		})
		.on("click",".updateFragment",function(){

			var courseName = $("#courseName").val();
			var classHour = $("#classHour").val();
			var courseId = $("#courseId").val();
			var kindId = $("#kindId").val();
			var chapterId = $("#chapterId").val();
			if(courseName==null||courseName==""){
                $.Ntip({'content':'片段名称不能为空或者为空格!'});
			    return ;
			}
			if(classHour==null||classHour==""){
				$.Ntip({'content':'课时不能为空或者为空格!'});
				return ;
			} else{
				reg=/^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
				if(!reg.test(classHour) || classHour == 0 ){
					$.Ntip({'content':"您输入课时的类型格式不正确!"});
					return ;
				}
			}
			if(kindId == null || kindId == 0){
                $.Ntip({'content':"请选择课件类型!"});
                return;
			}

			$.ajax({
				url:"../addLessonInfo/insertChapterSecond.do",
				method:"POST",
				data:{courseName:courseName,classHour:classHour,courseId:courseId,kindId:kindId,chapterId:chapterId},
				dataType:"json",
				success:function(data){
					if(data.status ){
						init();
						$sectionFragmentEditor.close();
                        $.Ntip({'content':"操作成功"});
					}
					if(data.statusCode == 1){
                        $.Ntip({'content':"操作失败"});
					}
                    if(data.status == 2){
                        $.Ntip({'content':"参数错误"});
                    }

				}
				
			})

			
		})
	}
	
	
	//课程资料上传
	var $courseRelatedMaterialEditor=$("#courseRelatedMaterialEditor");
	if($courseRelatedMaterialEditor.length>0){
		
		
		var courseId=$("#courseRelatedMaterialEditor input[name=courseId]").val();
		
		
		var errorTip=function(){
			$.tips("无法上传相关资源，请确保大小不超过100 MB","系统提示",function(){
			    $("#courseRelatedMaterialEditor #uploadInfo").html("请确保大小不超过100MB"); 
			    $("#courseRelatedMaterialEditor #fileQueue").empty();   	
			}); 	
		};
		var uploadSuccess=function(file, data, response){
			
			
			var lectrueName=$("#courseRelatedMaterialEditor input[name=lectrueName]").val();
			if(lectrueName == ""){
				$.Ntip({'content':"资料名称不能为空"});
				return
			}
			var resultData=$.parseJSON(data);
			var result=resultData.status;
			var uploadResultInfo="";
			if( result == 0){
				uploadResultInfo="";
				$.ajax({
					data:{"lectrueName":lectrueName,"courseId":courseId,"lecturefilePath":resultData.data},
					url:"../fileUpload/saveScheduleCourseLecture.do",
					method:"post",
					dataType:"json",
					success:function(data){
						if(data.status ){
							uploadResultInfo="您的资料更新成功";
							$("#selectLectureList").show();
							$("#deleteLectureList").show();
							addLectureList();
						}
						else{
							uploadResultInfo="资料更新失败";
						}	 
						$("#courseRelatedMaterialEditor #uploadInfo").html(uploadResultInfo);
						setTimeout(function(){	 
							 $("#courseRelatedMaterialEditor #fileQueue").empty();   	
						},3000);
					},
					error:function(){
						uploadResultInfo="资料更新失败";
						 $("#courseRelatedMaterialEditor #uploadInfo").html(uploadResultInfo);
						 setTimeout(function(){	 
							 $("#courseRelatedMaterialEditor #fileQueue").empty();
						 },3000);
					}
					
				})
				
				
				 
				
				 
	        }
	        else{
	        	if($.trim(data.cause)!=""){
	        		uploadResultInfo=data.cause;
	        	}	
	        }
            $("#coursePicEditor #imgUploadInfo").html(uploadResultInfo); 
            $("#coursePicEditor #fileQueue").empty();   
		};
		var dialogClose=function(queueData){
			if(queueData.filesErrored>0){
            	uploadAction.errorTip();
            }   
		};
		var onUploadStart=function(file){
			$("#coursePicEditor #uploadInfo").html("");        
		};
		if(supportH5){
			$('#courseRelatedMaterialEditor #relatedMaterialUploader').uploadifive({
				'uploadScript':"../filesTool/uploadCourseRelatedMaterial.do?courseId="+courseId,
				'buttonText': '资料上传',
			    'queueID':'fileQueue',
			    'multi' : false,
			    'fileSizeLimit'  : "100MB", 
			    'onError':errorTip,
			    'onDialogClose' :dialogClose,
			    'onUploadComplete':uploadSuccess
			});
		}
		else{
			$("#courseRelatedMaterialEditor #relatedMaterialUploader").uploadify({
				 'overrideEvents' : ['onDialogClose'], 
			      'method'         :'get',
				  'swf'            : '../js/uploadify.swf',
			      'uploader'       : '../filesTool/uploadCourseRelatedMaterial.do',
			      'cancelImg'      : 'image/cancel.png',
			      'fileSizeLimit'  : "100MB", 
			      'auto'           : true,
			      'multi'          : false,
			      "formData":{'courseId':courseId},
			      'buttonText'     : '资料上传',
			      'onUploadStart'  :onUploadStart,
			      'queueID'        : 'fileQueue',
			      'successTimeout' :6000,
			      'onDialogClose' : dialogClose,
			      'onUploadSuccess':uploadSuccess
			});
		}	
	}
	
	
})





