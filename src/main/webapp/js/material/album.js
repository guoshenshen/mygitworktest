var albumArea={
	photoMap:{},
    isAdminStyle:false,
    functionAfterAlbumLoaded:null
}

/*************
 * 基本功能函数
 ************/


//显示某相册基本信息
function showAlbumInfo(albumInfo){
	if(albumInfo!=null){
		$(".albumEditor input[name=albumName]").val(albumInfo.albumName);
		$(".albumEditor input[name=albumId]").val(albumInfo.id);
		$(".albumEditor .albumDescription").val(albumInfo.description);
	} else{
		$(".albumEditor input[name=albumName]").val("");
		$(".albumEditor input[name=albumId]").val("");
		$(".albumEditor .albumDescription").val("");
	}
}

//生成相册列表
function appendAlbumListInfo(albumList){
	var albumHTMLArray = new Array();
	var photoMap = albumArea.photoMap;
	var length = albumList.length;
	for(var i=0;i<length;i++){
		var album = albumList[i];
		var albumId = album.id;
		if(photoMap[albumId]==null){
			photoMap[albumId]={};	
			albumHTMLArray.push("<li class='albumItem' id=album_"+album.id+" >");
		    albumHTMLArray.push("<div class='leftRotate photoSkin'></div>");
	        albumHTMLArray.push("<div class='rightRotate photoSkin'></div>");
	        albumHTMLArray.push("<div class='noneRotate photoSkin'>");
	        if($.trim(album.cover)==""){
	        	album.cover='/image/material/albumBackground.jpg';
	        }
	        albumHTMLArray.push("<img src='"+album.cover+"' />");
	        albumHTMLArray.push("<span class='edit' title='编辑'></span>");
	        albumHTMLArray.push("<span class='delete' title='删除'></span>");
	        albumHTMLArray.push("<span class='albumTag' title='"+album.albumName+"'>"+album.albumName+"</span>");
	        albumHTMLArray.push("<input type='hidden' name='albumId' value='"+album.id+"'/>");
	        albumHTMLArray.push("</div></li>");
		}
		photoMap[albumId]['album']=album;
		
	}
	$("ul#albumList").append(albumHTMLArray.join(""));
}

//读取后台,加载相关相册信息
function loadAlbumInfo(){
	var resourceId=$(".albumHiddenInfo input[name=resourceId]").val();
    var type=$(".albumHiddenInfo input[name=type]").val();
  	$.ajax({
  		type:"POST",
		data:{
			"resourceId":resourceId,
			"type":type
		},
		url:"../album/loadAlbumList.do",
		dataType:"json",
		success:function(data){
			if(data.status){
				var albumList = data.data;
				var length = albumList.length;
				appendAlbumListInfo(albumList);
				if(typeof albumArea.functionAfterAlbumLoaded=="function"){
					albumArea.functionAfterAlbumLoaded();
				}
			}
		}
  	})
}

//在幻灯片主板显示某照片基本信息
function showPhotoDetail(photoInfo){
	 $("#currentPhoto img").attr("src",photoInfo.pictureUrl);	
	 $("#currentPhoto #currentPhotoTitle span.photoTitle").html(photoInfo.photoName);
	 $("#currentPhoto #currentPhotoTitle input[name=photoId]").val(photoInfo.photoId);
	 $("#currentPhoto #currentPhotoTitle input[name=albumId]").val(photoInfo.albumId);
	 $("#currentPhoto #currentPhotoTitle .editPhotoTitle").val(photoInfo.photoName);
	 $("#currentPhoto #currentPhotoTitle span.photoContent").html(photoInfo.description);
	 $("#currentPhoto #currentPhotoTitle .editPhotoContent").html(photoInfo.description);
	 $("#currentPhoto #currentPhotoPower").html(photoInfo.operatorName+"  "+photoInfo.uploadDate);
	 $("#currentPhoto").removeClass("editStatus").removeClass("cursor").addClass("notCursor");
	
}

//清空幻灯片主板上的照片信息
function clearPhotoInfo(){
	$("#currentPhoto img").attr("src","/image/material/empty.jpg");
	 $("#currentPhoto #currentPhotoTitle span.photoTitle").html("");
	 $("#currentPhoto #currentPhotoTitle input[name=photoId]").val("");
	 $("#currentPhoto #currentPhotoTitle input[name=albumId]").val("");
	 $("#currentPhoto #currentPhotoTitle .editPhotoTitle").val("");
	 $("#currentPhoto #currentPhotoTitle span.photoContent").html("");
	 $("#currentPhoto #currentPhotoTitle .editPhotoContent").html("");
	 $("#currentPhoto #currentPhotoPower").html("");
	 $("#currentPhoto").addClass("noPhoto");
	 $("#photoDisplayer #photoBar").empty();	
	 showLeftRightArrow();
}

//生成照片列表
function appendPhotoListToBar(albumId,photoList){
	var photoHTMLArray=new Array();
	var photoMap=albumArea.photoMap;
	var albumPhotoMap=photoMap[albumId+""]['photoArray'];
	for(var photoIndex in photoList){
		var photo=photoList[photoIndex];
		if($("#currentPhoto").hasClass("noPhoto")){
			$("#photoDisplayer #photoBar").css("left","0px");
			$("#photoDisplayer #currentPhoto").removeClass("noPhoto");
			$("#photoDisplayer #currentPhoto img").attr("src",photo.pictureUrl);	
			showPhotoDetail(photo);
		}
		albumPhotoMap[photo.photoId]=photo;
		if(albumArea.isAdminStyle){
			photoHTMLArray.push("<div class='otherPhoto'><span class='deleteMini'></span><input type='hidden' name='photoId' value='"+photo.photoId+"'/><input type='hidden' name='albumId' value='"+albumId+"'/><img src='"+photo.pictureUrl+"' /></div>");
		}
		else{
			photoHTMLArray.push("<div class='otherPhoto'><input type='hidden' name='photoId' value='"+photo.photoId+"'/><input type='hidden' name='albumId' value='"+albumId+"'/><img src='"+photo.pictureUrl+"' /></div>");
		}
		
	}
	$("#photoDisplayer #photoBar").append(photoHTMLArray.join(""));	
}

//判定是否在幻灯片照片显示机展示向左、右滑动箭头
function showLeftRightArrow(){
	var photoContainerWidth=$("#otherPhotoList").width();
	var photoListWidth=$("#photoBar").width();
	var position=$("#photoBar").position();
	var leftLength=position.left;
	 if(photoListWidth+leftLength>photoContainerWidth){
	 	$(".photoContainer .toRight").show();
	 }
	 else{
		 $(".photoContainer .toRight").hide();
	 }
	 if(leftLength>=0){
	 	$(".photoContainer .toLeft").hide();
	 }
	 else{
		$(".photoContainer .toLeft").show();
	 }
}

//读取后台,加载某相册照片信息
function loadPhotoList(params){
	var photoMap=albumArea.photoMap;
	var loadMiniPhoto=function(photoData){
		if(photoMap[params.albumId]['photoArray']!=null){
		
		} else{
			photoMap[params.albumId]['photoArray']={};
		}
		var photoList=photoData;
		if(photoList)
		appendPhotoListToBar(params.albumId,photoList);
		showLeftRightArrow();
	}
	if(photoMap[params.albumId]['photoArray']!=null){
		loadMiniPhoto(photoMap[params.albumId]['photoArray']);
	} else{
		$.ajax({
			type:"POST",
			data:params,
			url:"../album/loadPhotoList.do",
			dataType:"json",
			success:function(data){
				if(data.status){
					loadMiniPhoto(data.data);
				}
			}
		})
	}
}

//下载某相册照片
function downloadPic(photoList){
	var options={};
	options.imgArray=new Array();
	options.imgName=new Array();
	options.exportFileName="trainPictures";
	for(var i in photoList){
		var picUrl=photoList[i].pictureUrl
		options.imgArray.push(photoList[i].pictureUrl);
		options.imgName.push(photoList[i].photoName+ picUrl.substring( picUrl.lastIndexOf("."), picUrl.length));
	}
	
	$.ajax({
		url:'exportToolAction.do?method=defaultExportURLFile',
			type:'post',
			data:options,
			traditional:true,
			dataType:"json",
		    success:function(data){
				if(data.result=="true"){
					window.open(data.path);
				}
			},
			error:function(data){
			}
	})

}

$(function(){
	//判断当前显示模式是管理员管理模式or学员查看模式
	
	if($("#albumInfo").hasClass("adminStyle")){
		albumArea.isAdminStyle=true;
	}
	
	var isAdminStyle=albumArea.isAdminStyle;
	var photoMap=albumArea.photoMap;
	var currentAlbumId=-1;
	loadAlbumInfo();

	var $albumEditor=null;
	var $photoAlbum =null;
	var $uploader =null;
	
	$photoAlbum = $('[data-remodal-id=photoContainer]').remodal();
 	if(isAdminStyle){
 		$albumEditor=$('[data-remodal-id=albumEditor]').remodal();
 		$uploader = $('[data-remodal-id=uploaderPanel]').remodal();
 		var getCurrentUploadUrl=function(){
			return "albumAction.do?method=uploadPhoto&albumId="+currentAlbumId;
		};
		var supportHTML5=$.supportHTML5();
		var uploadAction={
			errorTip:function(){
				$.tips("无法上传您选择的文件到服务器,请确保文件为压缩包、大小不超过10M","系统提示",function(){
				    $("#fileQueue").empty();   	
				}); 
				  
			},
			onUploadStart:function(file){
				$("#uploadInfo").html("");   
				$("#file_upload").uploadify("settings", "formData",{'albumId':currentAlbumId}); 
			},
			onDialogClose:function(queueData){
				if(queueData.filesErrored>0){
	            	uploadAction.errorTip();
	            }   
			},
			onUploadSuccess:function(file, data, response){
				var resultData=$.parseJSON(data);
				var result=resultData.result;
				if(result=="true"){
					var photoList=resultData.photoList;
					appendPhotoListToBar(currentAlbumId,photoList);
				}	
			},
			onQueueCompleteFive:function(uploads){
              	var uploadResultInfo=uploads.successful+"张照片上传成功！";
              	var errorCount=uploads.errors;
              	if(errorCount!=0){
              		uploadResultInfo+=errorCount+"张照片上传失败！";
              	}
              	$("#uploadInfo").html(uploadResultInfo);
              	$("#fileQueue").empty();
              	uploads.successful=0;
              	uploads.errorCount=0;
			},
			onQueueComplete:function(queueData){
              	var uploadResultInfo=queueData.uploadsSuccessful+"张照片上传成功！";
              	var errorCount=queueData.uploadsErrored;
              	if(errorCount!=0){
              		uploadResultInfo+=errorCount+"张照片上传失败！";
              	}
              	$("#uploadInfo").html(uploadResultInfo);
              	$("#fileQueue").empty();
              	queueData.uploadsSuccessful=0;
              	queueData.uploadsErrored=0;
			}
		};
		$(document).on("closed","[data-remodal-id=uploaderPanel]",function(){
			$photoAlbum.open();
		});
 		$(document).on("opening","[data-remodal-id=uploaderPanel]",function(){	
 			$("#uploadInfo").html("");
 			//绑定动作到相片上传操作
 			if(supportHTML5){
 				$('#file_upload').uploadifive({
 					'uploadScript':getCurrentUploadUrl(),
 					'buttonText': '附件上传',
 				    'queueID':'fileQueue',
 				    'fileType' : 'image/*',
 				    'fileTypeExts':'*.jpg;*.jpeg;*.png',
 				    'fileSizeLimit'  : "5MB", 
 				    'onError':function(file, fileType, data) {
 				    	uploadAction.errorTip();
 				    },
 				    'onDialogClose' :uploadAction.onDialogClose,
 				    'onUploadComplete':uploadAction.onUploadSuccess,   
 				    'onQueueComplete':uploadAction.onQueueCompleteFive
 				});
 			}
 			else{
 				//绑定动作到相片上传操作
 				$("#file_upload").uploadify({
 					 'overrideEvents' : ['onDialogClose'], 
 				      'method'         :'get',
 					  'swf'            : './js/uploadify.swf',
 				      'fileTypeExts'   :'*.jpg;*.png;*.bmp;*.gif',
 				      'uploader'       : 'albumAction.do?method=uploadPhoto',
 				      'cancelImg'      : 'image/cancel.png',
 				      'fileSizeLimit'  : "5MB", 
 				      'auto'           : true,
 				      'multi'          : false,
 				      'buttonText'     : '批量上传',
 				      'onUploadStart'  :uploadAction.onUploadStart,
 				      'queueID'        : 'fileQueue',
 				      'successTimeout' :6000,
 				      'onDialogClose' :uploadAction.onDialogClose,
 				      'onUploadSuccess':uploadAction.onUploadSuccess,
 				      'onQueueComplete':uploadAction.onQueueComplete
 				});
 			}	
 		});
 	}
		
	$(document).on("opening","[data-remodal-id=photoContainer]",function(){
 		clearPhotoInfo();
 		loadPhotoList({"albumId":currentAlbumId});
 	});
	$(document).on("closed","[data-remodal-id=uploader]",function(){
 		$photoAlbum.open();
 	});
	$(document).on("closed","[data-remodal-id=photoContainer]",function(){
		$("#currentPhoto .favourite").removeClass("favourite").addClass("unfavourite");
	});
	
	
	$("#albumList").on("click",".albumTag",function(){
		currentAlbumId=$("input[name=albumId]",$(this).parents(".noneRotate")).val();
		if($.trim(currentAlbumId)!=""){
			$photoAlbum.open();
		}
		else{
			showAlbumInfo();
			$albumEditor.open();
		}
	});
	
	//绑定动作：主板展示相片的编辑/保存操作
	$("#photoDisplayer #currentPhoto").hover(
	  function(){
		if($(this).hasClass("noPhoto"));
		else{
			$(this).addClass("cursor").removeClass("notCursor");
		}
	  	
	  },
	  function(){
		if($(this).hasClass("noPhoto"));
		else{
			$(this).removeClass("cursor").addClass("notCursor");
		}
	});
	
	//绑定动作到相片主板展示
	$("#otherPhotoList #photoBar").on("click",".otherPhoto",function(){
		$("#photoBar img.selectedPhoto").removeClass("selectedPhoto");
		$("#currentPhoto .favourite").removeClass("favourite").addClass("unfavourite");
		$("img",$(this)).addClass("selectedPhoto");
		var photoId=$("input[name='photoId']",$(this)).val();
		var albumId=$("input[name='albumId']",$(this)).val();
		showPhotoDetail(photoMap[albumId]['photoArray'][photoId]);
	});
	
	//绑定动作到相册幻灯片展示左右轮播操作
	$(".photoContainer .toLeft").click(function(){
		var width=$("#photoBar .otherPhoto").css("width");
		$("#photoBar").animate({"left":"+="+width},400,function(){
			showLeftRightArrow();
		})
	})
	$(".photoContainer .toRight").click(function(){
		var width=$("#photoBar .otherPhoto").css("width")
		$("#photoBar").animate({"left":"-="+width},400,function(){
			showLeftRightArrow();
		});
	})
	
	$("#downloadPhoto").click(function(){

		downloadPic(photoMap[currentAlbumId]['photoArray']);
	});
	
	
	if(isAdminStyle){
		//绑定动作：相册编辑/删除链接显示
		$("#albumList").on("mouseover mouseout",".noneRotate",function(event){
			if(event.type == "mouseover"){
				$(this).addClass("cursor").removeClass("notCursor");
			}
			else if(event.type == "mouseout"){
				$(this).removeClass("cursor").addClass("notCursor");
			}
		})
		
		//绑定动作到相册编辑操作
		$("#albumList").on("click",".noneRotate .edit",function(){
			currentAlbumId=$("input[name=albumId]",$(this).parents(".noneRotate")).val();
			showAlbumInfo(photoMap[currentAlbumId]['album']);
			$albumEditor.open();
		})
		
		//绑定动作到相册信息确认更改操作
		$(document).on('confirmation', '[data-remodal-id=albumEditor]', function () {
			  var resourceId=$(".albumHiddenInfo input[name=resourceId]").val();
		      var albumType=$(".albumHiddenInfo input[name=type]").val();
			  var albumName=$(".albumEditor input[name='albumName']").val();
			  var albumDescription=$(".albumEditor textarea.albumDescription").val();
			  var albumId=$(".albumEditor input[name=albumId]").val();
			  $.ajax({
				  type:"POST",
				  url:"../album/saveAlbum.do",
				  dataType:"json",
				  data:{
				  			"albumName":albumName,
				  			"albumDescription":albumDescription,
				  			"albumId":albumId,
				  			"albumType":albumType,
				  			"resourceId":resourceId
			  		   },
			  	  success:function(data){
			  			   if(data.status){
			  				   var albumInfoList=[];
			  				   var $currentAlbum=$("#album_"+currentAlbumId);
			  				   var updatedAlbumInfo=data.data;
			  				   albumInfoList.push(updatedAlbumInfo);
			  				   appendAlbumListInfo(albumInfoList);
			  				   $(".albumTag",$currentAlbum).attr("title",updatedAlbumInfo.albumName).html(updatedAlbumInfo.albumName);
			  				   $("input[name=albumId]",$currentAlbum).val(updatedAlbumInfo.id);
			  				   $.confirm("相册更新成功,是否进行照片管理？","系统提示",function(gotoPhotoPanel){
			  					 if(gotoPhotoPanel){
			  						$(".albumTag",$("#albumList #album_"+updatedAlbumInfo.id)).click();
			  					 } else{
			  						$albumEditor.close();
			  					 }
			  				 })
			  			   }
			  		   }
			  })
		});

		//绑定动作到相册删除操作
		$("#albumList").on("click",".noneRotate .delete",function(){
			currentAlbumId=$("input[name=albumId]",$(this).parents(".noneRotate")).val();
			$.confirm("确定删除该相册吗？删除后将无法恢复","系统提示",function(deleteStatus){
				if(deleteStatus){
					$.ajax({
						type:"post",
						url:"../album/deleteAlbum.do",
						dataType:"json",
						data:{
							"albumId":currentAlbumId
						},
						success:function(data){
							if(data.status){
								$("li#album_"+currentAlbumId).remove();
							} else{
								$tips("删除失败，请稍后尝试","系统提示");
							}
						}
					})
				}
			})
		});

		//相片上传操作结束后返回原相册幻灯片模式
		$("#uploadPhoto").click(function(){
			$uploader.open();
		});
		
		//绑定动作到相片基本信息编辑操作
		$("#currentPhoto .edit").click(function(){
			if(!$("#currentPhoto").hasClass("editStatus")){
				$("#currentPhoto").addClass("editStatus");
			}
			else{
				$("#currentPhoto").removeClass("editStatus");
			}
		})
		
		//绑定动作到确认修改相片基本信息操作
		$("#currentPhoto .save").click(function(){
			var photoId=$("#currentPhotoTitle input[name=photoId]").val();
			var content=$("#currentPhotoTitle textarea.editPhotoContent").val();
			var title=$("#currentPhotoTitle  input.editPhotoTitle").val();
			$.ajax({
				type:"POST",
				data:{
					"photoId":photoId,
					"content":content,
					"title":title
				},
				url:"albumAction.do?method=updatePhotoInfo",
				dataType:"json",
				success:function(data){
					if(data.result=="true"){
						$.tips("照片信息修改成功","系统提示",function(){
							var photo=data.photo;
							var photoMap=albumArea.photoMap;
							photoMap[photo.albumId]['photoArray'][photo.photoId]=photo;
							showPhotoDetail(photo);
							$("#photoDisplayer #currentPhoto").removeClass("editStauts");
						})
					}
					else{
						$.tips("照片信息修改失败,请稍后尝试","系统提示");
					}
				}
			})
			
		})
		
		
		//绑定动作到相片删除操作
		$("#currentPhoto .delete").click(function(){
			var photoId=$("#currentPhotoTitle input[name='photoId']").val();
			$.ajax({
				type:"POST",
				data:{"photoId":photoId},
				url:"albumAction.do?method=deletePhoto",
				dataType:"json",
				success:function(data){
					if(data.result=="true"){
						delete photoMap[currentAlbumId]['photoArray'][photoId];
						
					}
				}
			})
		})
		
		//绑定动作：将相片设置为相册封面操作
		$("#currentPhoto .unfavourite").click(function(){
			var albumMap=photoMap[currentAlbumId];
			var photoId=$("#currentPhotoTitle input[name='photoId']").val();
			var albumInfo=albumMap['album'];
			albumInfo.cover=albumMap['photoArray'][photoId].pictureUrl;
			$(this).hide();
			$.ajax({
				  type:"POST",
				  url:"../album/saveAlbum.do",
				  dataType:"json",
				  data:{
		  			"albumName":albumInfo.albumName,
		  			"albumDescription":albumInfo.description,
		  			"albumId":albumInfo.id,
		  			"albumType":albumInfo.type,
		  			"resourceId":albumInfo.resourceId,
		  			"cover":albumInfo.cover
			  	  },
			  	  success:function(data){
			  		if(data.status){
			  			$("#currentPhoto .unfavourite").removeClass("unfavourite").addClass("favourite").show();
				  		$(".noneRotate img",$("li#album_"+albumInfo.id)).attr("src",albumInfo.cover);
			  		}
			  	  }
			});
		})
		
		
		//绑定动作到相片删除操作
		$("#otherPhotoList #photoBar").on("click",".deleteMini",function(){
			//删除某照片
			var $photoDIV=$(this).parents(".otherPhoto");
			var photoId=$("input[name=photoId]",$photoDIV).val();
			$.confirm("确定删除该照片吗?","系统提示",function(deleteFlag){
				if(deleteFlag){
					$.ajax({
						type:"POST",
						data:{"photoId":photoId},
						url:"albumAction.do?method=deletePhoto",
						dataType:"json",
						success:function(data){
							if(data.result=="true"){
								delete photoMap[currentAlbumId]['photoArray'][photoId];	
								var $nextShow=null;
								if(($nextShow=$photoDIV.next())!=null&&$nextShow.length>0){
									$nextShow.click();
								}
								else if(($nextShow=$photoDIV.prev())!=null&&$nextShow.length>0){
									$nextShow.click();
									$(".photoContainer .toLeft").click();
								} else{
								
								}
								$photoDIV.remove();
								showLeftRightArrow();
							}
						}
					})
				}
			});
		})
	}
});