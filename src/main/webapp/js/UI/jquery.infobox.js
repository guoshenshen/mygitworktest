;(function($){
	var infoBoxArea={
			$trigger:null,
			$infoBox:null,
			dataInfo:{},
			resetDataInfo:function(){},
			//是否可以弹出信息框
			canInfoBoxShow:function(){return true},
			//是否需要向后台请求获取数据
			needAjaxDataForInfoBox:function(){return false},
			//加载提示信息框结构脚本
			loadInfoBoxFrame:function(){},
		    //加载提示信息框内容及其样式
			loadInfoBoxContentStyle:function(){},
			//提示信息框动作绑定
			actionForInfoBox:function(){},
			
			//显示提示信息
			showInfoBox:function(){
				if(infoBoxArea.$infoBox==null){
					infoBoxArea.loadInfoBoxFrame();
				}
				else{
					infoBoxArea.actionForInfoBox();
					(infoBoxArea.showInfoBox=(function(){
						infoBoxArea.resetDataInfo();
						if(infoBoxArea.canInfoBoxShow()){
							infoBoxArea.loadInfoBoxContentStyle();
						}
						else{
						}
						infoBoxArea.needAjaxDataForInfoBox();
					}))();	
				}
				return infoBoxArea.$infoBox;
				
			},
			//隐藏提示信息
			hideInfoBox:function(){
				infoBoxArea.$infoBox.hide();
			}
	};
	
	
	infoBoxArea.userCardOptions={
			resetDataInfo:function(){
				infoBoxArea.dataInfo={};
				var dataSource=infoBoxArea.$trigger;
				var $userNameElem=dataSource.find(".infobox_userName");
				var $userIdElem=dataSource.find(".infobox_userId");
				var $userTypeElem=dataSource.find(".infobox_itemType");
				var $pictureURLElem=dataSource.find(".infobox_headPic");
				var $cacheArray=infoBoxArea.$trigger.find(".cache .cacheInfo");
				if($cacheArray.length==0){
					//首次加载，尚未存储缓存信息
					if($userNameElem.length>0){
						infoBoxArea.dataInfo.operatorName=$userNameElem.val();
					}
					if($userIdElem.length>0){
						infoBoxArea.dataInfo.operatorId=$userIdElem.val();
					}
					if($userTypeElem.length>0){
						infoBoxArea.dataInfo.itemType=$userTypeElem.val();
					}
					if($pictureURLElem.length>0){
						infoBoxArea.dataInfo.address=$pictureURLElem.attr("src");
					}
				}
				else{
					$cacheArray.each(function(index,that){
						infoBoxArea.dataInfo[that.name]=that.value;
					})
				}
			},
			actionForInfoBox:function(){
				$(".u-userCard").on("click",".name,a.j-imglink",function(){
					var dataInfo=infoBoxArea.dataInfo;
					if(dataInfo.currentOperatorId!=null&&$.trim(dataInfo.currentOperatorId)!=""){
						var param={};
						$("#linkInfo input").each(function(){
							param[this.name]=this.value;
						});
						intoUserZone(param);
					}	
				});
				$(".u-usrAttOp").on("click","a.disableInfo",function(){
					unfocusUser(function(data){
						if(data.result=="true"){
							var $userCard=infoBoxArea.$infoBox;
							$userCard.find(".j-abnum").html(data.followerNum);
							$userCard.find(".j-banum").html(data.fansNum);
							infoBoxArea.$trigger.find(".cache input[name=followerNum]").val(data.followerNum);
							infoBoxArea.$trigger.find(".cache input[name=fansNum]").val(data.fansNum);
							infoBoxArea.$trigger.find(".cache input[name=hasFocused]").val("false");
							$userCard.find(".u-usrAttOp").removeClass("j-disable-1").addClass("j-able-1");
							
						}
					});
				});
				$(".u-usrAttOp").on("click","a.ableInfo",function(){
					focusUser(function(data){
						if(data.result=="true"){
							var $userCard=infoBoxArea.$infoBox;
							$userCard.find(".j-abnum").html(data.followerNum);
							$userCard.find(".j-banum").html(data.fansNum);
							infoBoxArea.$trigger.find(".cache input[name=followerNum]").val(data.followerNum);
							infoBoxArea.$trigger.find(".cache input[name=fansNum]").val(data.fansNum);
							infoBoxArea.$trigger.find(".cache input[name=hasFocused]").val("true");
							$userCard.find(".u-usrAttOp").removeClass("j-able-1").addClass("j-disable-1");
						}
					});
				});

			},
			loadInfoBoxFrame:function(){
				$.ajax({
			        url:"/htmlStructure/infoBox.html",
			        dataType:'text',
			        method:"get",
			        success:function(htmlInfo){
						//如html页面已经指定了明信片的默认位置,则该明信片存储至该位置，否则添加至body底端
						if($("#userCardPosition").length==0){
							$("body").append(htmlInfo);
						}
						else{
							$("#userCardPosition").html(htmlInfo);
						}
						infoBoxArea.$infoBox=$("body").find(".u-userCard");
						var $userCard=infoBoxArea.$infoBox;
						$userCard.find(".j-img").error("error", function(e){
						     $(this).attr("src", "/image/headPic/male1.jpg");
						});
						infoBoxArea.userCardOptions.$infoBox=$userCard;
						infoBoxArea.showInfoBox();
			        },
			        error:function(){
			        	
			        }
			    })	
			},
			
			loadInfoBoxContentStyle:function(){
				var data=infoBoxArea.dataInfo;
				var $userCard=infoBoxArea.$infoBox;
				var $trigger=infoBoxArea.$trigger;
				var $hoverContainer=$trigger.parents(".hoverContainer");
				if($hoverContainer.length==0){
					$hoverContainer=$("body");
				}
				var hoverContainerId=$hoverContainer.attr("id");
				var position=$trigger.position();
				var positionTop=position.top;
				var positionLeft=position.left;
				var hoverContainerOffset=null;
				var top=null;
				var left=null;
				var containerOffset=null;
				
				if(containerOffset==null){
					containerOffset=$hoverContainer.offset();
				}
				var containerHeight=$hoverContainer.height();
				var cardHeight=$userCard.height();
				var triggerHeight=$trigger.height();
	
				top=(positionTop+triggerHeight)>containerHeight-cardHeight?positionTop-cardHeight:positionTop+triggerHeight;
				if($("#userCardPosition",$hoverContainer).length>0){
					
				}
				else{
					top=top+containerOffset.top;
				}
					
				
			
				if(containerOffset==null){
					containerOffset=$hoverContainer.offset();
				}
				var containerWidth=$hoverContainer.width();
				var cardWidth=$userCard.width();
				left=(positionLeft+cardWidth)>containerWidth?containerWidth-cardWidth:positionLeft;
				if($("#userCardPosition",$hoverContainer).length>0){
					
				}
				else{
					left=left+containerOffset.left;
				}
					
				$userCard.css("top",top).css("left",left);
				if(data.address!=null){
					$userCard.find(".j-img").attr("src",data.address);
				}
				if(data.operatorName!=null){
					$userCard.find(".j-name").html(data.operatorName);
				}
				if(data.operatorId!=null){
					$userCard.find(".j-operatorId").val(data.operatorId);
				}
				if(data.orgName!=null){
					$userCard.find(".j-orgName").html(data.orgName);
					$userCard.find(".j-orgName").attr("title",data.orgName);
				}
				if(data.followerNum!=null){
					$userCard.find(".j-abnum").html(data.followerNum);
				}
				if(data.fansNum!=null){
					$userCard.find(".j-banum").html(data.fansNum);
				}
				if(data.currentOperatorId!=null&&$.trim(data.currentOperatorId)!=""&&data.operatorId!=data.currentOperatorId){
					if(data.hasFocused!=null&&data.hasFocused=="true"){
						$userCard.find(".u-usrAttOp").removeClass("j-able-1").addClass("j-disable-1");
					}
					else{
						$userCard.find(".u-usrAttOp").removeClass("j-disable-1").addClass("j-able-1");
					}
				}
				else{
					$userCard.find(".u-usrAttOp").removeClass("j-disable-1").removeClass("j-able-1");
				}

				$userCard.show();
				$("#linkInfo",$userCard).empty();
				var infoArray=new Array();
				for(var i in data){
					infoArray.push("<input class='cacheInfo' type='hidden' name='"+i+"' value='"+data[i]+"' />");
				}
				$("#linkInfo",$userCard).html(infoArray.join());
				var $cache=infoBoxArea.$trigger.find(".cache");
				if($cache.length==0){
					infoBoxArea.$trigger.append("<div style='display:none' class='cache'>"+infoArray.join("")+"</div>");
				}
				else{
					infoBoxArea.$trigger.find(".cache").html(infoArray.join());
				}	
			},
			canInfoBoxShow:function(){
				var userName=infoBoxArea.dataInfo.operatorName
				var operatorId=infoBoxArea.dataInfo.operatorId;
				var address=infoBoxArea.dataInfo.address;
				if(userName&&operatorId&&address){
					return true;
				}
				else{
					return false;
				}
			},
			needAjaxDataForInfoBox:function(){
				var dataInfo = infoBoxArea.dataInfo;
				if(dataInfo==null || dataInfo.fansNum==null){
					var param=dataInfo;
					$.ajax({
						url:"../interaction/getBasicFollowingInfo.do",
						data:param,
						dataType:'json',
						method:"post",
						success:function(data){
							var userForm = data.data.userForm;
							for(var i in userForm){
								if(userForm[i]!=null){
									dataInfo[i]=userForm[i];
								}
							}
							for(var j in data.data){
								if(j!="userForm"){
									dataInfo[j]=data.data[j];
								}
							}
							if(infoBoxArea.canInfoBoxShow()){
								infoBoxArea.loadInfoBoxContentStyle();
							}
						}
					})
				}
				else{
				}
			}
			
			
	}
	
	$.fn.extend({
		"showUserCard":function(){
			if(infoBoxArea.userCardOptions.$infoBox==null||infoBoxArea.$infoBox!=infoBoxArea.userCardOptions.$infoBox){
				$.extend(infoBoxArea,infoBoxArea.userCardOptions);
			}
			infoBoxArea.$trigger=this;
			return infoBoxArea.showInfoBox();
		},
		"hoverUserCard":function(){
			var $userCard=null;
			var $currentUser=null;
			var timeInterval=null;
			this.on("click",function(event){
				var dataInfo=infoBoxArea.dataInfo;
				if(dataInfo.currentOperatorId!=null&&$.trim(dataInfo.currentOperatorId)!=""){
					intoUserZone(dataInfo);
				}	
			});
			this.on("mouseover",function(event){
				if($currentUser==$(this)){
					clearTimeout(timeInterval);
				}else{
					clearTimeout(timeInterval);
					$currentUser=$(this);
					timeInterval=setTimeout(		
							function(){
									$userCard=$currentUser.showUserCard();
							},100
					)
				}		
			}).on("mouseout",function(event){
				clearTimeout(timeInterval);
				$currentUser=$(this);
				timeInterval=setTimeout(function(){
					$.hideUserCard();
					$userCard=null;
				},150);	
			});
			
			$("body").on("mouseover",".u-userCard",function(event){
					clearTimeout(timeInterval);
			}).on("mouseout",".u-userCard",function(event){
				timeInterval=setTimeout(function(){
					$.hideUserCard();
					$userCard=null;
				},100);
			});
		}
		
	});
	
	$.extend({
		hideUserCard:function(){
			return infoBoxArea.hideInfoBox();
		},
		hoverUserCard:function(){
			var $userCard=null;
			var $currentUser=null;
			var timeInterval=null;
			$("body").on("click",".u-userCardTrigger",function(event){
				var dataInfo=infoBoxArea.dataInfo;
				if(dataInfo.currentOperatorId!=null&&$.trim(dataInfo.currentOperatorId)!=""){
					intoUserZone(dataInfo);
				}	
			}).on("mouseover",".u-userCardTrigger",function(event){
				if($currentUser==$(this)){
					clearTimeout(timeInterval);
				}else{
					clearTimeout(timeInterval);
					$currentUser=$(this);
					timeInterval=setTimeout(		
							function(){
									$userCard=$currentUser.showUserCard();
							},100
					)
				}		
			}).on("mouseout",".u-userCardTrigger",function(event){
				clearTimeout(timeInterval);
				$currentUser=$(this);
				timeInterval=setTimeout(function(){
					$.hideUserCard();
					$userCard=null;
				},150);	
			});
			
			$("body").on("mouseover",".u-userCard",function(event){
					clearTimeout(timeInterval);
			}).on("mouseout",".u-userCard",function(event){
				timeInterval=setTimeout(function(){
					$.hideUserCard();
					$userCard=null;
				},150);
			});	
		}
	});
	
})(jQuery); 


