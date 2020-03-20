
//***************封装了评论的工具函数**********************
//created by zq@cinc.cn on 2017-06-29
//jquery-latest.js is needed here
//JSCommonTools.js is needed here for loadDiscussEditor

;(function($){
	var discussArea={
			canclick:true
	};
	
	$.fn.extend({
		//加载评论编辑器
		//params {
		//   *必填
		//   *bookId 
		//   *isReply 
		//   *discussType
		//   *operatorName
		//   operatorId
		//   gender
		//	 address(头像)
		//   score(默认得分)
		//   
		//}
		loadDiscussEditor:function(params){
			var $currentElement=this;
			var $discussEditorContainer=$(".discussEditorContainer",$currentElement);
			$.loadStructureHTML("/htmlStructure/BasicTools.html","discussEditor",function($discussEditor){
				var headPic= $("#imagePic").val();
				if($.trim(headPic)==""){
					if($.trim(params.gender+"")=="1"){
						headPic="/image/headPic/male1.jpg";
					}
					else{
						headPic="/image/headPic/female1.jpg";
					}
				}
			
				$(".discuss-operator img",$discussEditor).attr("src",headPic);
				$(".discuss-operator .discuss-name",$discussEditor).html(params.operatorName);
				$(".replyRequrire",$discussEditor).each(function(index,that){
					that.value=params[that.name];
				});
				
				var score=params.score;
				if($.trim(score)==""){
					score=4;
					params.score=score;
				};
				$discussEditor.find(".needInfo").each(function(index,such){
					such.value=params[such.name];
				});
				
				$(".u-rating .star",$discussEditor).each(function(index,that){
					if(index<score){
						$(that).addClass("on");
					}
				});
				
				//绑定打分函数
				$discussEditorContainer.on("click",".u-rating .star",function(){
					var currentStar=this;
					var score=-1;
					$(".u-rating .star",$discussEditorContainer).each(function(index,that){
						if(score==-1){
							$(that).addClass("on");
						}
						else{
							$(that).removeClass("on");
						}
						if(that==currentStar){
							score=index+1;
							$("input[name=score]",$discussEditorContainer).val(score);
						}
					})
				});
				
				var tip=params.tip;
				if($.trim(tip)==""){
					tip="请进行评价..."
				}
				$("textarea",$discussEditor).attr("placeholder",tip).html("");
				
				$discussEditorContainer.html($discussEditor.html());
				
				$(".discuss-operator img",$discussEditorContainer).one("error",function(){
           		 	$(this).attr('src',"/image/headPic/male1.jpg");
            	});
			});
		},
		
		//显示评论列表
		loadDiscussItemList:function(params){
			if(!discussArea.canclick){
				return
			}
			else{
				discussArea.canclick=false;
				var $currentElement=this;
				$.loadStructureHTML("/htmlStructure/BasicTools.html","otherReply",function($discussReply){
					$.ajax({
						url:"/rsmRcmBookDiscuss/getDiscussListInFixedNum.do",
						data:params,
						dataType:"json",
						method:"post",
						success:function(dataInfo){
							if(dataInfo.status){
								var discussList=dataInfo.data.list;
								var replyArray=new Array();
								var discusssListLength=discussList.length;
								if(discusssListLength>0){
									for(var i=0;i<discusssListLength;i++){
										var reply=discussList[i];
										var score=reply.score;
										if($.trim(score)==""){
											score=4;
											reply.score=score;
										};
										$(".replyRequrire",$discussReply).each(function(index,that){
											that.value=reply[that.name];
										});
										var headPic=reply.picUrl;
										if($.trim(headPic)==""){
											if($.trim(reply.gender+"")=="1"){
												headPic="/image/headPic/male1.jpg";
											}
											else{
												headPic="/image/headPic/female1.jpg";
											}
										}
										$(".discuss-operator img",$discussReply).attr("src",headPic);
										$(".discuss-operator .discuss-name",$discussReply).html(reply.operatorName);
										$(".discuss-content .commentPublisher",$discussReply).html(reply.operatorName);
										$(".discuss-time span",$discussReply).html(reply.discussTime);
											
										$(".u-rating .star",$discussReply).each(function(index,that){
											if(index<score){
												$(that).addClass("on");
											}
											else{
												$(that).removeClass("on");
											}
										});
										$(".discuss-content p.commentInfo",$discussReply).html(reply.content);
										var discussId=reply.discussId;
										$(".discuss-reply",$discussReply).remove();
										if(reply.discussReplies !=null && reply.discussReplies.length>0){
											$.loadStructureHTML("/htmlStructure/BasicTools.html","replyForReply",function($replyForReply){
												//有学员对某条评论进行了回复
												var replyForReplyArray=new Array();
												for(var j in reply.discussReplies ){
													var replyForReply=reply.discussReplies[j];
													$(".operatorName",$replyForReply).html(replyForReply.operatorName);
													$(".info",$replyForReply).html(replyForReply.content);
													$(".discuss-time span",$replyForReply).html(replyForReply.discussTime);
													replyForReplyArray.push($replyForReply.html());
												}
												$(".discuss-body",$discussReply).append(replyForReplyArray.join(""));
											});
										}
										replyArray.push($discussReply.html());
									}
									var $loadMoreButton=$(".discussReplyContainer #loadMore",$currentElement);
									var nextLength=100;
									if($.trim(params.nextLength)!=""){
										nextLength=params.nextLength;
									}
									params.nextLength=nextLength;
									if(dataInfo.comeToEnd=="false"){
										//还有更多评论可供加载
										if($loadMoreButton.length==0){
											replyArray.push('<div id="loadMore" class="np-load-more"><span>加载更多...</span></div>');
											$(".discussReplyContainer",$currentElement).on("click","#loadMore",function(){
												if($.trim(params.startIndex)!=""){
													params.startIndex=params.startIndex+params.length;
													params.length=params.nextLength;
													$currentElement.loadDiscussItemList(params);
												}
											});
										}
									}
									else{
										if($loadMoreButton.length==0){
											replyArray.push('<div id="loadMore" class="np-load-more nomore"><span>没有更多评论了...</span></div>');
										}
										else{
											$(".discussReplyContainer #loadMore").addClass("nomore");
											$(".discussReplyContainer #loadMore span",$currentElement).html("没有更多评论了...");
											$(".discussReplyContainer",$currentElement).off("click","#loadMore");
										}	
									};
								}
								else{
									if(params.startIndex!=0){
										replyArray.push('<div id="loadMore" class="np-load-more nomore"><span>没有更多评论了...</span></div>');
									}
									else{
										replyArray.push("<div class='empty'><div class='emptyImg'><h3>尚无评论</h3></div></div>");	
									}
								}
								
								$(".discussReplyContainer",$currentElement).append(replyArray.join(""));
								$("img.headPic",$currentElement).one("error",function(){
				            		 $(this).attr('src',"/image/headPic/male1.jpg");
				            	});
							}
						},
						complete:function(){
							discussArea.canclick=true;
						}
					});
					
				});
			}
		},
		
		//提交评论
		//param:
		//verify:functon,评论提交前的附加校验
		//success:function,评论成功创建后的反馈
		//error:function,评论失败创建后的反馈
		submitDiscussItem:function(param){
			var $parentDIV=$(this).parents(".discuss-body");
			//评论提交表单
			var $commentForm=$parentDIV.find("form");
			var $replyContent=$commentForm.find('textArea');
			if($.trim($replyContent.val())==""){
				$replyContent.val("");
				$replyContent.attr('placeholder','请回复内容后再提交...');
				return;
			}
			var dataInfo={};
			dataInfo['content']=$replyContent.val();
			$parentDIV.find("input[type=hidden]").each(function(index,such){
				dataInfo[$(such).attr('name')]=$(such).val();
			})
			if(typeof param.verify=="function"){
				param.verify();
			}
			if(!discussArea.canclick){
				return;
			}
			else{
				discussArea.canclick=false;
				$.ajax({
					   url: '/rsmRcmBookDiscuss/addComment.do',
					   type:'POST',
					   dataType:"json",
					   data: dataInfo,
					   success: function(data){
							if(typeof param.success=="function"){
								param.success(data);
							}
							if(data.status==0){
								$(".discuss-content p",$parentDIV).html(dataInfo['content']);
								$parentDIV.addClass("replyed");
							    $(".discuss-rating",$parentDIV).html("评分：");
							    $(".discuss-time",$parentDIV).html("<span>评论时间： "+data.data.discussTime+"</span>");
							    location.reload();
							    setTimeout(function(){
							    	discussArea.canclick=true;
							    },2000);       
						   }
						   else{
							   if(typeof param.error=="function"){
									param.error(data);
								}
							    var message='抱歉，暂时不能提交您的评论，请稍后重试...';
							    if($.trim(data.cause)!=""){
							    	message=$.trim(data.cause);
							    }
							   $replyContent.attr('placeholder',message);
							   discussArea.canclick=true;
							   $.Ntip({
									'content':message,
								})
						   }
							
					   },
					   error:function(data){
						   discussArea.canclick=true;
						   if(typeof param.error=="function"){
								param.error(data);
							}
							$replyContent.val("").attr('placeholder','抱歉，暂时不能提交您的评论，请稍后重试...');
					   }
				});	
			}
		},		
		//显示回复他人评论编辑器
		showReplyOtherDiscussEditor:function(){
			var $currentItem=this;
			$.loadStructureHTML("/htmlStructure/BasicTools.html","replyOther",function($reply){
				var $parentDIV=$currentItem.parents(".discuss-content");
				var $commentDIV=$parentDIV.find(".reply-box-wrap");
				var alreayShowReplyBox=$parentDIV.parents(".discussReplyContainer").find(".reply-box");
				var $commentPublisherName=$parentDIV.find(".commentPublisher");
				if(alreayShowReplyBox.length>0){
					alreayShowReplyBox.remove();
				}
				$commentDIV.append($reply.html());
				var $commentForm=$commentDIV.find("form");
				$commentForm.find("textarea[name=content]").attr('placeholder',"回复："+$commentPublisherName.text());
			});
		},
		
		//取消回复他人评论
		cancleReply:function(){
			//取消回复评论
			this.parents(".reply-box").remove();
		},
		//提交对他人的回复
		replyOtherReply:function(param){
			var $parentDIV=this.parents(".discuss-content");
			var $commentDIV=$parentDIV.find(".reply-box-wrap");
			var $commentForm=$commentDIV.find("form");
			var $commentInfo=$commentForm.find("textarea[name=content]");
			if($.trim($commentInfo.val()).length==0){
				$commentInfo.val("").attr('placeholder','请进行内容回复...');
				return;
			}
			if(typeof param.verify=="function"){
				param.verify();
			}
			
			if(discussArea.canclick=true){
				var dataInfo={};
				 discussArea.canclick=false;
				dataInfo['content']=$commentInfo.val();
				$parentDIV.find("input[type=hidden]").each(function(index,such){
					dataInfo[$(such).attr('name')]=$(such).val();
				})
				$.ajax({
					   url: '/rsmRcmBookDiscuss/addComment.do',
					   type:'POST',
					   dataType:"json",
					   data: dataInfo,
					   success: function(data){
						   if(data.status  ){
							   var currentReplyLength=($parentDIV.parents(".discuss-body").find(".reply-num").length)+1;
						       var appendCommentStr="<div class='discuss-reply'><span class='reply-num'>" +''+
						       		"</span><span class='operatorName'>" +data.data.operatorName+
						       		"</span><span>回复说：</span><div style='float:right;'><span style=''> " +data.data.discussTime+
						       		"</span></div><p class='info'>" +dataInfo['content']+
						       		"</p></div>";     
						       $commentDIV.find(".reply-box").fadeOut(500,null,function(){
						    	   $commentDIV.find('.reply-box').remove();
						    	   if(currentReplyLength==1){
							    	   $(appendCommentStr).appendTo($parentDIV.parents(".discuss-body"));
							       }
							       else{
							    	   $(appendCommentStr).insertBefore($parentDIV.parents(".discuss-body").find(".discuss-reply:first"));
							       }
						       });
						       if(typeof param.verify=="function"){
									param.verify();
								};
						       setTimeout(function(){
						    	   discussArea.canclick=true;
						       },2000);       
						   }
						   else{
							   if(typeof param.error=="function"){
									param.error(data);
							   }
							   var message='抱歉，暂时不能提交您的评论，请稍后重试...';
							    if($.trim(data.cause)!=""){
							    	message=$.trim(data.cause);
							    }
							   $commentInfo.val("").attr('placeholder',message);
							   discussArea.canclick=true;
						   }
					   },
					   error:function(data){
						   if(typeof param.error=="function"){
								param.error(data);
							}
						   $commentInfo.val("").attr('placeholder','抱歉，暂时不能提交您的评论，请稍后重试...');
						   discussArea.canclick=true;
					   }
				});
			}	
		}
	})
	
	
})(jQuery); 