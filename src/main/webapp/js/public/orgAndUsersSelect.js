/***********************************
 * 
 * edited by zq@cnic.cn on 2017-06-12
 * 
 * This js file can be used as a tool to select person-list and org-list
 * 
 * *********************************/

var orgAndUsersSelectArea={
	baseDoc:null,
	baseWindow:window.parent,
	$orgAndUsersSelectBaseWindow:null,
};

$(function(){

	//绑定动作到组织机构、人员选择相关按钮
	
	//预览已选择人员
	$(".effectArea").on("click",".showUsersPreview",function(){
		$(this).showUsersSelectedFrame();
	});
	//预览已选择机构
	$(".effectArea").on("click",".showOrgsPreview",function(){
		$(this).showOrgsSelectedFrame();
	});
	//显示人员选择界面
	$(".effectArea").on("click",".userAddMenu",function(){
		$(this).showUserAddChannelWin();
	});
	//显示机构选择界面
	$(".effectArea").on("click",".orgSelect",function(){
		$(this).showOrgListWin();
	});
	//显示机构单选界面
	$("body").on("click",".effectArea .singleOrgSelect",function(){
		$(this).showOrgListWin({
			check:{
				chkStyle:"radio",
				radioType: "all"
			}
		});
	});
	
	

});

;(function($){
	//config info for selecting person-list
    var canTrigger=true;
    
    var orgAndUsersArea={
    	$scriptInfo:null,
		loadScript:function(actionAfterLoading,url){
			if(url==null){
				url="/htmlStructure/ModalWin.html";
			}
			if(orgAndUsersArea.$scriptInfo==null){
				$.ajax({
			        url:url,
			        dataType:'text',
			        success:function(data){
						var $scriptInfo=$($.parseHTML(data));
						orgAndUsersArea.$scriptInfo=$scriptInfo;
						$(".modalWin",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).parents(".remodal-wrapper").remove();
						orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow.append($scriptInfo.html());	
						
						var h = document.documentElement.clientHeight || document.body.clientHeight;
						
						var originHeight=$(".list-wrapper").css("max-height");
						var resetHeight=parseInt(originHeight)>h*0.75?h*0.75:originHeight;	
						$(".list-wrapper").css("max-height",resetHeight);

						
						
						
						//orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow.append('<link href="./css/jquery-UI/remodal.css" rel="stylesheet" type="text/css" /><link href="./css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css" /><link href="./css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css" /><link href="./css/jquery-UI/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />');
						addOrgArea.$orgSelectWindow=$("[data-remodal-id=modal_orgSelectWindow]",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).remodal({
							appendTo:orgAndUsersSelectArea.baseDoc,
						});
						
						addOrgArea.$orgsShowFrame=$("[data-remodal-id=modal_orgList]",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).remodal({
							appendTo:orgAndUsersSelectArea.baseDoc,
						});
						
						addUsersArea.$usersSelectFrame=$("[data-remodal-id=modal_userSelectWay]",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).remodal({
							appendTo:orgAndUsersSelectArea.baseDoc,
						});
						addUsersArea.$usersShowFrame=$("[data-remodal-id=modal_userList]",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).remodal({
							appendTo:orgAndUsersSelectArea.baseDoc,
									
						});
						addUsersArea.$usersSelectByOrgWindow=$("[data-remodal-id=modal_userSelectByOrgWindow]",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).remodal({
							appendTo:orgAndUsersSelectArea.baseDoc,
						});
						addUsersArea.$userSelectByaddrBookWindow=$("[data-remodal-id=modal_userSelectByaddrBookWindow]",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).remodal({
							appendTo:orgAndUsersSelectArea.baseDoc,
						});
						addUsersArea.$userSelectByImportWindow=$("[data-remodal-id=modal_userSelectByImportWindow]",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).remodal({
							appendTo:orgAndUsersSelectArea.baseDoc,
						});

						//绑定动作到组织机构列表链接
						var $orgSelectWindow=$(".orgSelectWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow);
						$orgSelectWindow.on("click",".search-icon",function(){
							//查询名称中包含某字符的组织机构
							var searchTxt=$(this).parents(".search").find("#search-info").val();
							var param={'name':searchTxt};
							$.searchOrg(param);
						}).on("keydown","input[name=search]",function(event){
							if(event.keyCode == "13"){
								$(this).parents(".search").find(".search-icon").click();
							}
						}).on("click",".showCasTenant",function(){
							//显示中科院全院组织机构
							if($(this).hasClass("casStatus")){
								$(this).removeClass("casStatus");
								$("#orgList",$orgSelectWindow).loadOrgTree({});
							}
							else{
								$(this).addClass("casStatus");
								$("#orgList",$orgSelectWindow).loadOrgTree({"orgId":"2","loadRoot":"true"});
							}	
						}).on("click",".remodal-confirm",function(){
							//确认已选择组织机构
							$.getSelectedOrg(function(orglist){
								addOrgArea.pushOrgsIntoCache(orglist);
								addOrgArea.$orgSelectWindow.close();
							});
							
						}).on("closed",function(){
							$("#orgList",$(this)).empty();
						});
						
						
						//绑定动作到已选择机构链接
						var $orgListWindow=$(".orgListWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow);
						$orgListWindow.on("closed",function(){
							addOrgArea.clearOrgFrameHistroy();
						}).on("click","td input[type=checkbox]",function(event){
							event.stopPropagation();
						}).on("click","tr",function(){
							var $checkBox=$("input[type=checkbox]",$(this));
							$checkBox.prop("checked",!$checkBox.prop("checked"));

						}).on("click",".selectAll input[type=checkbox]",function(){
							var checkStatus=this.checked;
							$orgListWindow.find("table input[name=orgId]").each(function(){
								if($(this).parents("tr").hasClass("notShow"));
								//不处理隐藏的行
								else{
									this.checked=checkStatus;
								}
							});
						}).on("click",".delete",function(){
							var deleteOrgIdArray=new Array();
							$orgListWindow.find("table input[name=orgId]:checked").each(function(){
								if($(this).parents("tr").hasClass("notShow"));
								//不处理隐藏的行
								else{
									deleteOrgIdArray.push(this.value);
									$(this).parents("tr").remove();
								}
							});
							addOrgArea.deleteOrgsFromCache(deleteOrgIdArray);
							
						}).on("click",".search-icon",function(){
							$(".selectAll input[type=checkbox]",$orgListWindow)[0].checked=false;
							var searchTxt=$(this).parents(".search").find("#search-info").val();
							if(searchTxt==null||$.trim(searchTxt)==""){
								//显示全部
								$(".highlight").removeClass("highlight");
								$orgListWindow.find("table tr.notShow").removeClass("notShow");
							}
							else{
								searchTxt=$.trim(searchTxt);
								$orgListWindow.find("table tr").each(function(index,that){
									if(!$(that).hasClass("title")){
										var infoContained=false;
										$(that).find("td").each(function(index1,that1){
											var text=$(that1).text();
											if(text!=null&&text.indexOf(searchTxt)!=-1){
												//包含
												infoContained=true;
												$(that1).addClass("highlight");
											} else{
												$(that1).removeClass("highlight");
											}
										});	
										if(infoContained==false){
											$(that).addClass("notShow");
										} else{
											$(that).removeClass("notShow");
										}
									}
								})
							}
							orgAndUsersSelectArea.$currentWindow.$modal.loadStatisticsTip();
						}).on("keydown","#search-info",function(event){
							if(event.keyCode == "13"){
								$(this).parents(".search").find(".search-icon").click();
							}
						});
						
						//绑定动作到通过组织机构查找人员
						var $userSelectByOrgWindow=$(".userSelectByOrgWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow);
						
						
						$userSelectByOrgWindow.on("click",".orgTool .search-icon",function(){
							var searchTxt=$(this).parents(".search").find("#search-info").val();
							var param={'name':searchTxt};
							$.searchOrg(param);
						}).on("keydown","#search-info",function(event){
							if(event.keyCode == "13"){
								$(this).parents(".search").find(".search-icon").click();
							}
						}).on("click",".showCasTenant",function(){
							var $currentMode=$(this).parents(".userSelectMode");
							var setting=orgAndUsersArea.treeSettingForLoadingUserFromOrg;
							//显示中科院全院组织机构
							if($(this).hasClass("casStatus")){
								$(this).removeClass("casStatus");
								
								$("#orgList",$currentMode).loadOrgTree({},setting);
							}
							else{
								$(this).addClass("casStatus");
								$("#orgList",$currentMode).loadOrgTree({"orgId":"2","loadRoot":"true"},setting);
							}	
						})
						
						
						//绑定动作到通过excel表格导入选择人员
						var $userSelectByImportWindow=$(".userSelectByImportWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow);
						$userSelectByImportWindow.on("keydown","#search-info",function(event){
							if(event.keyCode == "13"){
								$(this).parents(".search").find(".search-icon").click();
							}
						});
						
						
						//绑定动作到通过通讯录查找人员
						var $userSelectByaddrBookWindow=$(".userSelectByAddrBookWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow);
						
						$userSelectByaddrBookWindow.on("click",".orgTool .search-icon",function(){
							var searchTxt=$(this).parents(".search").find("#search-info").val();
							$("#selectUserByAddressBook #addreBooklist",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).loadAddressBookList({'keyWords':searchTxt},function(){
								$(".bookItem:first a",$(".userSelectByAddrBookWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow)).click();	
							});	
						}).on("keydown","#search-info",function(event){
							if(event.keyCode == "13"){
								$(this).parents(".search").find(".search-icon").click();
							}
						});
						
						$(".userSelectByAddrBookWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).on("click",".bookItem a",function(){
							var bookId=$("input[name=addrbookId]",$(this)).val();
							$.getStaffOfAddressBook({"addrbookId":bookId},function(data){
								$("#selectUserByAddressBook #userList table tbody",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).html(orgAndUsersArea.loadUsersFromServer(data));
								orgAndUsersSelectArea.$currentWindow.$modal.loadStatisticsTip();
							});
							
						});
						
						//绑定动作到通用人员选择查找窗口
						var canClickSearchIcon=true;
						var $userSelectMode=$(".userSelectMode",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow);
						$userSelectMode.on("click",".selectAll input[type=checkbox]",function(){
							var $currentMode=$(this).parents(".userSelectMode");
							var checkStatus=this.checked;
							$currentMode.find("table input[name=operatorId]").each(function(){
								if($(this).parents("tr").hasClass("notShow"));
								//不处理隐藏的行
								else{
									this.checked=checkStatus;
								}
							});
							
						}).on("click","td input[type=checkbox]",function(event){
							event.stopPropagation();
						}).on("click","tr",function(){
							var $checkBox=$("input[type=checkbox]",$(this));
							$checkBox.prop("checked",!$checkBox.prop("checked"));

						}).on("click",".remodal-confirm",function(){
							var $currentMode=$(this).parents(".userSelectMode");
							var originalList=addUsersArea.currenteffectArea.originalInfoArray;
							var userList=new Array();
							$("table tbody tr",$currentMode).each(function(index,such){
							   var $checkbox=$("input[type=checkbox]",$(such));
							   if($checkbox.length>0&&$checkbox.prop("checked")){
								   userList.push(originalList[index-1]);
							   }   
						    });
							addUsersArea.pushUsersIntoCache(userList);
							addUsersArea.currenteffectArea.actionAfterUserselect();
						}).on("click",".operatorTool .search-icon",function(){
							var $currentMode=$(this).parents(".userSelectMode");
							if(!canClickSearchIcon){
								return;
							}
							canClickSearchIcon=false;
							$(".selectAll input[type=checkbox]",$currentMode)[0].checked=false;
							var searchTxt=$.trim($(this).parents(".search").find("#search-info").val());
							if($currentMode.find(".tip").length>0){
								//因该机构下人员信息过多未加载数据
								//触发模糊后台查询
								var orgId=$(".tip input[name=orgId]",$currentMode).val();
								$.ajax({
									data:{"fuzzCondition":searchTxt,"orgId":orgId},
									url:"systemAction.do?method=getUserinfoByFuzzyCondition",
									method:"post",
									dataType:"json",
									success:function(data){
										if(data.result=="true"){
											$("#userList table tbody",$currentMode).html(orgAndUsersArea.loadUsersFromServer(data.userList));
											$("#userList table tbody",$currentMode).append("<div class='tip'><input type='hidden' name='orgId' value='"+orgId+"'></input></div>");
										} else{
											$("#userList table tbody",$currentMode).html("<div class='tip'>"+data.cause+"<input type='hidden' name='orgId' value='"+orgId+"'></input></div>");
										}
										orgAndUsersSelectArea.$currentWindow.$modal.loadStatisticsTip();
									},
									complete:function(){
										canClickSearchIcon=true;
									}
								})
							}
							else{
								if(searchTxt==""){
									//显示全部
									$(".highlight").removeClass("highlight");
									$currentMode.find("table tr.notShow").removeClass("notShow");
								} else{
									if($currentMode.find(".tip").length>0){
										
									} else{
										$currentMode.find("table tr").each(function(index,that){
											if(!$(that).hasClass("title")){
												var infoContained=false;
												$(that).find("td").each(function(index1,that1){
													var text=$(that1).text();
													if(text!=null&&text.indexOf(searchTxt)!=-1){
														//包含
														infoContained=true;
														$(that1).addClass("highlight");
													} else{
														$(that1).removeClass("highlight");
													}
												});	
												if(infoContained==false){
													$(that).addClass("notShow");
												} else{
													$(that).removeClass("notShow");
												}
											}
										});
									}	
								}
								orgAndUsersSelectArea.$currentWindow.$modal.loadStatisticsTip();
								canClickSearchIcon=true;
							}

						}).on("keydown",".tools #search-info",function(event){
							if(event.keyCode == "13"){
								$(this).parents(".search").find(".search-icon").click();
							}
						});

						
						
						//绑定动作到已选择人员查询页面
						var $userListWindow=$(".userListWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow);
						$userListWindow.on("closed",function(){
							addUsersArea.clearUserFrameHistory();
						}).on("click",".selectAll input[type=checkbox]",function(){
							var checkStatus=this.checked;
							$userListWindow.find("table input[name=operatorId]").each(function(){
								if($(this).parents("tr").hasClass("notShow"));
								//不处理隐藏的行
								else{
									this.checked=checkStatus;
								}
							});
						}).on("click","td input[type=checkbox]",function(event){
							event.stopPropagation();
						}).on("click","tr",function(){
							var $checkBox=$("input[type=checkbox]",$(this));
							$checkBox.prop("checked",!$checkBox.prop("checked"));

						}).on("click",".delete",function(){
							var deleteOperatorIdArray=new Array();
							$userListWindow.find("table input[name=operatorId]:checked").each(function(){
								if($(this).parents("tr").hasClass("notShow"));
								//不处理隐藏的行
								else{
									deleteOperatorIdArray.push(this.value);
									$(this).parents("tr").remove();
								}
							});
							addUsersArea.deleteUsersFromCache(deleteOperatorIdArray);
							
						}).on("click",".search-icon",function(){
							$(".selectAll input[type=checkbox]",$userListWindow)[0].checked=false;
							var searchTxt=$(this).parents(".search").find("#search-info").val();
							if($.trim(searchTxt)==""){
								//显示全部
								$(".highlight").removeClass("highlight");
								$userListWindow.find("table tr.notShow").removeClass("notShow");
							} else{
								searchTxt=$.trim(searchTxt);
								$userListWindow.find("table tr").each(function(index,that){
									if(!$(that).hasClass("title")){
										var infoContained=false;
										$(that).find("td").each(function(index1,that1){
											var text=$(that1).text();
											if(text!=null&&text.indexOf(searchTxt)!=-1){
												//包含
												infoContained=true;
												$(that1).addClass("highlight");
											}
											else{
												$(that1).removeClass("highlight");
											}
										});	
										if(infoContained==false){
											$(that).addClass("notShow");
										}
										else{
											$(that).removeClass("notShow");
										}
									}
								})
							}
							orgAndUsersSelectArea.$currentWindow.$modal.loadStatisticsTip();
						}).on("keydown",".tools #search-info",function(event){
							if(event.keyCode == "13"){
								$(this).parents(".search").find(".search-icon").click();
							}
						});
						
						if(typeof actionAfterLoading=="function"){
							actionAfterLoading(data);
						}	
			        },
			        complete:function(ev){
			        }
			    })
			}else{
				actionAfterLoading();	
			}
    	},
    	
    	
    	
    	loadUsersFromServer:function(userList,appendMode){
    		var defaultAppendMode=false;
    		if(appendMode!=null){
    			defaultAppendMode=appendMode;
    		}
    		var currentEffectArea=addUsersArea.currenteffectArea;
    		if(defaultAppendMode){
    			if(currentEffectArea.originalInfoArray==null){
    				currentEffectArea.originalInfoArray=new Array();
    			}
    			currentEffectArea.originalInfoArray=currentEffectArea.originalInfoArray.concat(userList);
    		} else{
    			currentEffectArea.originalInfoArray=userList;
    		}
    		var operatorBasicInfoArray=new Array();
			
			if(!defaultAppendMode){
				operatorBasicInfoArray.push("<tr class='title'><th class='check'></th><th>用户姓名</th><th>单位</th><th>账号</th><th>邮箱</th><th></th></tr>");
			}
			var zebraLine="";
			var baseNum=0;
			if(defaultAppendMode){
				baseNum=currentEffectArea.originalInfoArray.length;
			}
			var userListLength=userList.length;
			if(userListLength>5000){
				userListLength=5000;
			}
			for(var i=0;i<userListLength;i++){
				var user=userList[i];
				if((i+userListLength+baseNum)&1){
					zebraLine="even";
				} else{
					zebraLine="odd";
				}
				if($.trim(user.operatorId)==""){
					user.operatorId="";
				}
				operatorBasicInfoArray.push("<tr class='"+zebraLine+"' id='row_"+user.operatorId+"'><td class='check'>");
				if(user.operatorId!=""){
					var $existedRow=$("#userList table #row_"+user.operatorId,orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow);
					if($existedRow.length>0){
						$existedRow.remove();
					}
					operatorBasicInfoArray.push("<input  name='operatorId' type='checkbox' value='"+user.operatorId+"'/>");
				} else{
				}
				operatorBasicInfoArray.push("</td>");
				operatorBasicInfoArray.push("<td class='operatorName' title='"+user.operatorName+"'>"+user.operatorName+"</td>");
				operatorBasicInfoArray.push("<td title='"+user.orgName+"'>"+user.orgName+"</td>");
				operatorBasicInfoArray.push("<td title='"+user.userId+"'>"+user.userId+"</td>");
				operatorBasicInfoArray.push("<td title='"+user.email+"'>"+user.email+"</td>");
				operatorBasicInfoArray.push("<td></td></tr>");
			}
			return operatorBasicInfoArray.join("");
    	},
    	treeSettingForLoadingUserFromOrg:{
				check:{enable:false},
				callback:{
					onClick:function(event, treeId, treeNode){
						var orgId=treeNode.orgId;
						//显示某机构对应的人员列表信息
						if(orgId=="1"||orgId=="2"){
							$("#selectUserByOrg #userList table tbody",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).html("<div class='tip'>因该机构下人员信息过多无法直接加载，请您输入条件检索指定对象或指定子机构进行检索<input type='hidden' name='orgId' value='"+orgId+"'></input></div>");
						} else{
							$.ajax({
								url:"/eosorgTEmployee/getUserinfoOfSpecifiedOrg.do",
								method:"post",
								dataType:"json",
								data:{"orgId":orgId},
								success:function(result){
									if(result.status){
										$("#selectUserByOrg #userList table tbody",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).html(orgAndUsersArea.loadUsersFromServer(result.data));
									}
									orgAndUsersSelectArea.$currentWindow.$modal.loadStatisticsTip();
								}
							})
						}
						
					}
				}	
			}
    		
    };
    
    var addOrgArea={
    		//显示机构候选界面
    		$orgsSelectFrame:null,
    		//显示已选择机构列表
    		$orgsShowFrame:null,
    		//当前机构选择作用域,特别适用于同一个页面有多处机构选择的地方
    		currenteffectArea:null,
    		options:{
				orgCache:new Array(),	
				extraOptions:{}
			},
    		clearOrgFrameHistroy:function(){
				var $orgListWindow=$(".orgListWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow);
				$(".selectAll input[type=checkbox]",$orgListWindow)[0].checked=false;
				$("#search-info",$orgListWindow).val("");
				$(".showCasTenant",$orgListWindow).removeClass("casStatus");
    		},
    		//重置当前已选机构数提示
			resetOrgsNumHint:function(){
				var currenteffectArea=addOrgArea.currenteffectArea;
				var length=0;
				if(currenteffectArea!=null&&currenteffectArea.orgCache!=null){
					length=currenteffectArea.orgCache.length;
					var $currentTarget=$(currenteffectArea.id);
					$currentTarget.find("em").html(length);
				}
			},
    		//将选中的机构添加至备选列表
			pushOrgsIntoCache:function(orgInfoArray){
    			var newOrgInfoArray=new Array();
				//存储新选中的人员
				var currenteffectArea=addOrgArea.currenteffectArea;
				var clearBefore=currenteffectArea.clearBefore;
				var $currentTarget=$(currenteffectArea.id);
				var $hiddenTable=$currentTarget.find(".detailInfoForShow table");
				var $hiddenArea=$currentTarget.find(".hiddenArea");
				var currenteffectAreaId=$currentTarget.attr("id");
				var orgLength=orgInfoArray.length;
				if(clearBefore){
					currenteffectArea.orgCache=new Array();
				}
				for(var i=0;i<orgLength;i++){
					var orgId=orgInfoArray[i].orgId;
					if($.inArray(orgId, currenteffectArea.orgCache)<0){
						newOrgInfoArray.push(orgInfoArray[i]);
					} else{
						//已经包含某人员信息
					}	
				}
				var newOrgLength=newOrgInfoArray.length;
				var alreadyLength=$("tbody tr",$hiddenTable).length;
				if(alreadyLength==0){
					alreadyLength=1;
				}
				if(newOrgLength>0){
					var hiddenOrgIdArray=new Array();
					var orgBasicInfoArray=new Array();
					var zebraLine="";
					for(var i=0;i<newOrgLength;i++){
						var detail=newOrgInfoArray[i];
						hiddenOrgIdArray.push("<input type='hidden' name='"+currenteffectAreaId+"' id='hidden_"+detail.orgId+"' value='"+detail.orgId+"'>");
						if((i+alreadyLength)&1){
							zebraLine="even";
						} else{
							zebraLine="odd";
						}
						orgBasicInfoArray.push("<tr class='"+zebraLine+"' id='row_"+detail.orgId+"'><td class='check'><input  name='orgId' type='checkbox' value='"+detail.orgId+"'/></td>");
						orgBasicInfoArray.push("<td title='"+detail.orgName+"'>"+detail.orgName+"</td>");
						orgBasicInfoArray.push("<td title='"+detail.tenantName+"'>"+detail.tenantName+"</td>");
						orgBasicInfoArray.push("<td></td></tr>");
						currenteffectArea.orgCache.push($.trim(detail.orgId+""));
					}
					
					if($hiddenArea.length==0){
						$hiddenArea=$("<div class='hiddenArea'></div>").appendTo($currentTarget);
					}
					if(clearBefore){
						$hiddenArea.html(hiddenOrgIdArray.join(""));
					} else{
						$hiddenArea.append(hiddenOrgIdArray.join(""));
					}	
					
					var tbodyInfo="<tbody><tr class='title'><th class='check'></th><th>名称</th><th>隶属系统</th><th></th></tbody>";
					if($hiddenTable.length==0){
						$hiddenTable=$("<div class='detailInfoForShow notShow'><table>"+tbodyInfo+"</table></div>").appendTo($currentTarget).find("table");
					}
					if($hiddenTable.find("tbody").length==0){
						$hiddenTable.append(tbodyInfo);
					}
					if(clearBefore){
						$("tbody",$hiddenTable).empty(orgBasicInfoArray.join(""));
					} else{
						$("tbody",$hiddenTable).append(orgBasicInfoArray.join(""));
					}
					addOrgArea.resetOrgsNumHint();
				}
    		},
    		//删除已选中机构
			deleteOrgsFromCache:function(orgIdArray){
    			var currentEffectArea=addOrgArea.currenteffectArea;
				var $currentTarget=$(currentEffectArea.id);
				var orgLength=orgIdArray.length;
				for(var i=0;i<orgLength;i++){
					var orgId=orgIdArray[i];
					$currentTarget.find("#row_"+orgId).remove();
					$currentTarget.find("#hidden_"+orgId).remove();
					//从缓存数组中将该元素删除
					if(orgId!=null){
						var index=$.inArray($.trim(orgId),currentEffectArea.orgCache);
						currentEffectArea.orgCache.splice(index,1);
					}
				}
				addOrgArea.resetOrgsNumHint();
    		}		
    }
    
    
	var addUsersArea={
			//显示用户选择菜单
			$usersSelectFrame:null,
			//显示已选择用户列表
			$usersShowFrame:null,
			//显示通讯录信息
			$userSelectByaddrBookWindow:null,
			//显示通过组织机构查找人员面板
			$usersSelectByOrgWindow:null,
			$currentWindow:null,
			//当前人员选择作用域,特别适用于同一个页面有多处人员选择的地方
			currenteffectArea:null,
			clearUserFrameHistory:function(){
				var $userListWindow=$(".userListWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow);
				$(".selectAll input[type=checkbox]",$userListWindow)[0].checked=false;
				$("#search-info",$userListWindow).val("");
			},
			clearUserSelectHistory:function(){
				var $userSelectWindow=$(".userSelectByOrgWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow);
				$(".selectAll input[type=checkbox]",$userSelectWindow)[0].checked=false;
				$("#search-info",$userSelectWindow).val("");
				$("table tbody",$userSelectWindow).empty("");
				$("#orgList",$userSelectWindow).empty();
			},
			//重置当前总人数提示
			resetUsersNumHint:function(lengthInfo){
				var length=0;
				var currenteffectArea=addUsersArea.currenteffectArea;
				if(lengthInfo!=null){
					length=parseInt($.trim(lengthInfo));
				}
					
				if(currenteffectArea!=null&&currenteffectArea.usersCache!=null){
					if(lengthInfo==null){
						length=currenteffectArea.usersCache.length;
					}	
					var $currentTarget=$(currenteffectArea.id);
					var originLength=parseInt($currentTarget.find("em").html());
					$currentTarget.find("em").html((originLength+length));
				}
						
			},
			//将选中的人添加至人员备选列表
			pushUsersIntoCache:function(operatorInfoArray){
				var newOperatorInfoArray=new Array();
				//存储新选中的人员
				var currenteffectArea=addUsersArea.currenteffectArea;
				var $currentTarget=$(currenteffectArea.id);
				var currentEffectAreaId=$currentTarget.attr("id");
				var operatorLength=operatorInfoArray.length;
				for(var i=0;i<operatorLength;i++){
					var operatorId=operatorInfoArray[i].operatorId;
					if($.inArray(""+operatorId, currenteffectArea.usersCache)<0){
						newOperatorInfoArray.push(operatorInfoArray[i]);
						currenteffectArea.usersCache.push($.trim(operatorInfoArray[i].operatorId+""));
					}
					else{
						//已经包含某人员信息
					}	
					
				}
				var newOperatorInfoLength=newOperatorInfoArray.length;
				if(newOperatorInfoLength>0){
					var hiddenOperatorIdArray=new Array();
					var operatorBasicInfoArray=new Array();
					var zebraLine="";
					var $hiddenArea=$currentTarget.find(".hiddenArea");
					var $hiddenTable=$currentTarget.find(".detailInfoForShow table");
					addUsersArea.resetUsersNumHint(newOperatorInfoLength);
					var alreadyLength=$("tbody tr",$hiddenTable).length;
					if(alreadyLength==0){
						alreadyLength=1;
					}
					for(var i=0;i<newOperatorInfoLength;i++){
						var detail=newOperatorInfoArray[i];
						hiddenOperatorIdArray.push("<input type='hidden' name='"+currentEffectAreaId+"' id='hidden_"+detail.operatorId+"' value='"+detail.operatorId+"'>");
						if((i+alreadyLength)&1){
							zebraLine="even";
						}
						else{
							zebraLine="odd";
						}
						operatorBasicInfoArray.push("<tr class='"+zebraLine+"' id='row_"+detail.operatorId+"'><td class='check'><input  name='operatorId' type='checkbox' value='"+detail.operatorId+"'/></td>");
						operatorBasicInfoArray.push("<td class='operatorName' title='"+detail.operatorName+"'>"+detail.operatorName+"</td>");
						operatorBasicInfoArray.push("<td title='"+detail.orgName+"'>"+detail.orgName+"</td>");
						operatorBasicInfoArray.push("<td title='"+detail.userId+"'>"+detail.userId+"</td>");
						operatorBasicInfoArray.push("<td title='"+detail.email+"'>"+detail.email+"</td>");
						operatorBasicInfoArray.push("<td></td></tr>");
					}
					if($hiddenArea.length==0){
						$hiddenArea=$("<div class='hiddenArea'></div>").appendTo($currentTarget);
					}
					$hiddenArea.append(hiddenOperatorIdArray.join(""));
					var tbodyInfo="<tbody><tr class='title'><th class='check'></th><th>姓名</th><th>单位</th><th>登录账号</th><th>邮箱</th><th></th></tbody>";
					if($hiddenTable.length==0){
						$hiddenTable=$("<div class='detailInfoForShow notShow'><table>"+tbodyInfo+"</table></div>").appendTo($currentTarget).find("table");
					}
					if($hiddenTable.find("tbody").length==0){
						$hiddenTable.append(tbodyInfo);
					}
					$("tbody",$hiddenTable).append(operatorBasicInfoArray.join(""));
					
				}
				
			},
			//删除人员
			deleteUsersFromCache:function(operatorIdArray){
				var currentEffectArea=addUsersArea.currenteffectArea;
				var $currentTarget=$(currentEffectArea.id);
				var operatorIdLength=operatorIdArray.length;
				for(var i=0;i<operatorIdLength;i++){
					var operatorId=operatorIdArray[i];
					$currentTarget.find("#row_"+operatorId).remove();
					$currentTarget.find("#hidden_"+operatorId).remove();
					//从缓存数组中将该元素删除
					if(operatorId!=null){
						var index=$.inArray($.trim(operatorId),currentEffectArea.usersCache);
						currentEffectArea.usersCache.splice(index,1);
					}
				}
				addUsersArea.resetUsersNumHint(-operatorIdLength);
				
			},
			options:{
				extraOptions:{},
				$idTag:null,
				$nameTag:null,
				usersExhibitionType:"0",
				originalInfoArray:new Array(),
				usersCache:new Array(),
				//选中用户展示方式（0:默认值,在新窗口中以列表形式显示）
				successImportAction:function(){
					try{
						jAlert("添加人员成功,若不再添加其他人员,请关闭窗口","提示信息",function(){
							$("#addressBookList")[0].contentWindow.addressBookInfoArea=false;
						});
					}catch(e){
						$("#addressBookList")[0].contentWindow.addressBookInfoArea=false;
					}
				},
				failImportAction: function(){
						try{
							jAlert("添加人员失败","提示信息",function(){
								$("addressBookList")[0].contentWindow.addressBookInfoArea=false;
							});
						}
						catch(e){
							alert("添加人员失败","提示信息");
							$("addressBookList")[0].contentWindow.addressBookInfoArea=false;
						}
				},
				successAddrImportAction:null,
				failAddrImportAction:null,
				successOrgUserImportAction:null,
				failOrgUserImportAction:null,
				actionAfterUserselect:function(){
					$.Nconfirm({
						'confirmBtnText':'继续添加',
						'denyBtnText':'添加完毕',
						"confirmQuestion":"人员添加完毕，是否继续添加其他人员？",
						"onConfirm":function(){},
						"onDeny":function(){
							orgAndUsersSelectArea.$currentWindow.close();
							addUsersArea.clearUserSelectHistory();
						}
					});
				}
			},
	
	};
	
	
	$.extend({
		
		fadeOutWindow:function(){
			orgAndUsersSelectArea.$currentWindow.close();
		},
		
		
		//加载之前已存储的组织机构信息
		initializeOrgListData:function(targetId,orgInfoArray){
			if(orgInfoArray==null){
				return;
			}
			var effectAreaId="#"+targetId;
			if(addOrgArea[effectAreaId]!=null){
				
			} else{
				var options={};
				options.id=effectAreaId;
				//important 这里注意是深拷贝
				$.extend(true,options,addOrgArea.options);
				addOrgArea[effectAreaId]=options;
			}
			addOrgArea.currenteffectArea=addOrgArea[effectAreaId];
			addOrgArea.pushOrgsIntoCache(orgInfoArray);

		},
		
		//加载之前已存储的人员信息
		initializeOperatorListData:function(targetId,operatorInfoArray){
			if(operatorInfoArray==null){
				return;
			}
			
			var effectAreaId="#"+targetId;

			if(addUsersArea[effectAreaId]!=null){
				
			} else{
				var options={};
				options.id=effectAreaId;
				//important 这里注意是深拷贝
				$.extend(true,options,addUsersArea.options);
				addUsersArea[effectAreaId]=options;
			}
			addUsersArea.currenteffectArea=addUsersArea[effectAreaId];
			addUsersArea.pushUsersIntoCache(operatorInfoArray);
		}
	})
	
	
	
	
	$.fn.extend({
		
		loadDefaultUserList:function(){
			var effectAreaId=this.attr("id");
			var name="input[name="+effectAreaId+"]";
			var $operatorId=$(name,$(this));
			if($operatorId.length>0){
				var operatorIdList=new Array();
				$operatorId.each(function(index,that){
					operatorIdList.push(that.value);
				})
				
				$.ajax({
					url:"eosorgTEmployeeAction.do?method=selectUserListById",
					method:"post",
					dataType:"json",
					data:{"operatorIdList":operatorIdList},
					traditional:true,
					success:function(result){
						if(result.status){
							$operatorId.remove();
							$.initializeOperatorListData(effectAreaId,result.userList);
						}
					}
				})
			}
		},
		
		
		loadDefaultOrgList:function(){
			var effectAreaId=this.attr("id");
			var name="input[name="+effectAreaId+"]";
			var $orgId=$(name,$(this));
			if($orgId.length>0){
				var orgList=new Array();
				$orgId.each(function(index,that){
					orgList.push(that.value);
				})
				
				$.ajax({
					url:"/eosorgTOrganization/selectOrgListById.do",
					method:"post",
					dataType:"json",
					data:{"orgIdList":orgList},
					traditional:true,
					success:function(result){
						if(result.status){
							$orgId.remove();
							$.initializeOrgListData(effectAreaId,result.data);
						}
					}
				})
			}
			
		},
		
		
		//显示组织机构选择列表
		showOrgListWin:function(optionsForOrgTree){
			if(optionsForOrgTree==null){
				optionsForOrgTree={};
			}
			var effectAreaId="#"+this.parents(".effectArea").attr("id");
			if(addOrgArea[effectAreaId]!=null){
			}
			else{
				var options={};
				var clearBefore=this.parents(".effectArea").hasClass("clearBefore");
				options.clearBefore=clearBefore;
				options.id=effectAreaId;
				var defaultOptions=addOrgArea.options;
				var extraOption=defaultOptions.extraOptions[effectAreaId];
				//important 这里注意是深拷贝
				$.extend(true,options,addOrgArea.options,extraOption);
				addOrgArea[effectAreaId]=options;
			}
			addOrgArea.currenteffectArea=addOrgArea[effectAreaId];
			
			orgAndUsersArea.loadScript(function(){
				$(".orgSelectWindow #orgList",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).loadOrgTree(null,optionsForOrgTree);
				orgAndUsersSelectArea.$currentWindow=addOrgArea.$orgSelectWindow;
				orgAndUsersSelectArea.$currentWindow.open();
			});
		
		},
		
		//显示备选名单中的机构
		showOrgsSelectedFrame:function(){
			var $orgsSelectedFrame=this;	
			orgAndUsersArea.loadScript(function(){
				//获取当前作用域
				var $effectArea=$orgsSelectedFrame.parents(".effectArea");
				var effectArea="#"+$effectArea.attr("id");	
				addOrgArea.currenteffectArea=addOrgArea[effectArea];
				var currenteffectArea=addOrgArea.currenteffectArea;
				if(currenteffectArea!=null){
					var $currentTarget=$(currenteffectArea.id);
					$(".orgList-wrapper .standardOrgListTable",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).html($currentTarget.find(".detailInfoForShow table").html());
				} else{
					$(".orgList-wrapper .standardOrgListTable",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).html("");
				}
				if($effectArea.hasClass("readOnly")){
					$(".orgListWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).addClass("readOnly");
				} else{
					$(".orgListWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).removeClass("readOnly");
				}
				orgAndUsersSelectArea.$currentWindow=addOrgArea.$orgsShowFrame;
				orgAndUsersSelectArea.$currentWindow.$modal.loadStatisticsTip();
				orgAndUsersSelectArea.$currentWindow.open();
				
			});
		},
		
		
		//show ways of selecting users to specified area
		showUserAddChannelWin:function(){
			var $currentObj=this;
			var effectAreaId="#"+$currentObj.parents(".effectArea").attr("id");		
			
			if(addUsersArea[effectAreaId]!=null){
				
			}
			else{
				var options={};
				options.id=effectAreaId;
				var defaultOptions=addUsersArea.options;
				var extraOption=defaultOptions.extraOptions[effectAreaId];
				//important 这里注意是深拷贝
				$.extend(true,options,addUsersArea.options,extraOption);
				addUsersArea[effectAreaId]=options;
			}
			addUsersArea.currenteffectArea=addUsersArea[effectAreaId];
		
			orgAndUsersArea.loadScript(function(){
				$(".userSelectWay-wrapper .addrBookWay",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).click(function(){
					//显示通讯录查找人员入口
					$currentObj.showAddressBookWin();		
				});
				$(".userSelectWay-wrapper .importWay",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).click(function(){
					//显示excel表格导入查找人员入口

					$currentObj.showImportUsers();
					
					var h5=$.supportHTML5();
					var randNum=Math.floor(Math.random()*10000+1);
					if(h5){
						$('#file_upload').uploadifive({
							'uploadScript':"eosorgTEmployeeImportFileAction.do?method=achieveUser&progress_id="+randNum,
							'buttonText':'表格导入',
							'multi':false,
						    'queueID':'fileQueue',
						    'fileType':["application\/excel","application\/vnd.ms-excel","application\/x-excel","application\/x-msexcel"],
						    'fileSizeLimit':"10MB",
						    'onSelect':function(){
								$currentObj.clearImportUserTips();
							},
						    'onError':function(file, fileType, data) {
						    	$.Ntip({
						    		"content":"无法上传您选择的文件,请确保文件为excel表格、大小不超过10M"
						    	})
						    },  
						    'onQueueComplete':function(){
						    	$currentObj.achieveImportUser(randNum)
						    }
						});
					} else{
						//绑定动作到相片上传操作
						$("#file_upload").uploadify({
							 'overrideEvents' : ['onDialogClose'], 
						      'method'         :'get',
							  'swf'            : './js/uploadify.swf',
						      'fileTypeExts'   :'*.xlsx;*.xls',
						      'uploader'       : "eosorgTEmployeeImportFileAction.do?method=achieveUser",
						      'fileSizeLimit'  : "10MB", 
						      'auto'           : true,
						      'multi'          : false,
						      "formData":{'progress_id':randNum},
						      'buttonText'     : '表格导入',
						      'queueID'        : 'fileQueue',
						      'successTimeout' :6000,
						      'onSelect':function(){
						    	  $currentObj.clearImportUserTips();
						    	  $();
							   },
						      'onUploadError':function(file, fileType, data) {
							    	$.Ntip({
							    		"content":"无法上传您选择的文件,请确保文件为excel表格、大小不超过10M"
							    	})
							  },
						      'onQueueComplete':function(){
							    	$currentObj.achieveImportUser(randNum)
							  }
						});
					}
					
				});
				$(".userSelectWay-wrapper .orgWay",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).click(function(){
					//显示从组织机构进行人员查找入口
					$currentObj.showUsersSelectByOrg();
				});
				orgAndUsersSelectArea.$currentWindow=addUsersArea.$usersSelectFrame;
				orgAndUsersSelectArea.$currentWindow.open();
			});
		},
		//从组织机构中选择人员
		showUsersSelectByOrg:function(){
			var setting=orgAndUsersArea.treeSettingForLoadingUserFromOrg;
			$("#selectUserByOrg #orgList",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).loadOrgTree({},setting);
			orgAndUsersSelectArea.$currentWindow=addUsersArea.$usersSelectByOrgWindow;
			orgAndUsersSelectArea.$currentWindow.open();
		},
		//显示通讯录选择人员控制面板
		showAddressBookWin:function(){
			orgAndUsersSelectArea.$currentWindow=addUsersArea.$userSelectByaddrBookWindow;
			$("#selectUserByAddressBook #addreBooklist",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).loadAddressBookList({},function(){
				$(".bookItem:first a",$(".userSelectByAddrBookWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow)).click();	
			});	
			orgAndUsersSelectArea.$currentWindow.open();
		},	
		//显示excel表格导入控制面板
		showImportUsers:function(){
			var $currentObj=this;
			$currentObj.clearImportUserTips();
			orgAndUsersSelectArea.$currentWindow=addUsersArea.$userSelectByImportWindow;
			$("#selectUserByImport #userList table tbody",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).html(orgAndUsersArea.loadUsersFromServer([]));
			orgAndUsersSelectArea.$currentWindow.open();
		},
		
		achieveImportUser:function(progressId){
			var tipIndex=0;
			var dataIndex=0;
			var params={
				tipIndex:0,
				dataIndex:0,
				progress_id:progressId
			}
			$("#selectUserByImport .progressBar").css("display","block");
			var getProgress=function(){$.ajax({
				url:"progressAction.do?method=getProgress",
				method:"post",
				dataType:"json",
				data:params,
				success:function(response){
					var tips=response.tips;
					var data=response.data;
					var comeToEnd=response.comeToEnd;
					var currentRate=response.rate;
					var $progressTip=$("#selectUserByImport .progressBar .tip");
					var lastProgress=$.trim($progressTip.html());
					if($.trim(lastProgress)==""){
						lastProgress="0%";
					}
					lastProgress=parseInt(lastProgress.replace("%"));
					var increase=currentRate-lastProgress;
					var increaseFunc=function(){
						lastProgress+=1;
						$progressTip.html(lastProgress+"%");
					}
					for(var i=1;i<=increase;i++){
						setTimeout(function(){
							increaseFunc();
						},1000/increase*i);
					}
					var tipsLength=0;
					if(tips!=null){
						tipsLength=tips.length;
						params.tipIndex+=tipsLength;
					}
					if(data!=null){
						$("#selectUserByImport #userList table tbody",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).append(orgAndUsersArea.loadUsersFromServer(data,true));
						params.dataIndex+=data.length;
					}
					var $uploadInfo=$("#selectUserByImport #uploadInfo");
					for(var j=0;j<tipsLength;j++){
						$uploadInfo.append("<p>"+tips[j]+"</p>");
					}
					
					$("#selectUserByImport .progressBar .barFinished").animate({width:currentRate+"%"},1000,function(){
						
						if(comeToEnd=="true"){
						} else{
							getProgress();
						}	
					});
				}
			})};
			getProgress();
		},
		
		clearImportUserTips:function(){
			$("#selectUserByImport .progressBar").css("display","none");
			$("#selectUserByImport .progressBar .barFinished").css("width","0");
			$("#selectUserByImport .progressBar .tip").html("");
			$("#selectUserByImport #uploadInfo").html("");
			$("#selectUserByImport #userList table tbody",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).html(orgAndUsersArea.loadUsersFromServer([]));
		},
		
		
		//显示备选名单中的人员
		showUsersSelectedFrame:function(){
			var $userFrame=this;	
			orgAndUsersArea.loadScript(function(){
				//获取当前作用域
				var $effectArea=$userFrame.parents(".effectArea");
				var effectArea="#"+$effectArea.attr("id");	
				addUsersArea.currenteffectArea=addUsersArea[effectArea];
				var currenteffectArea=addUsersArea.currenteffectArea;
				if(currenteffectArea!=null){
					var $currentTarget=$(currenteffectArea.id);
					$(".userList-wrapper .standardUserListTable",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).html($currentTarget.find(".detailInfoForShow table").html());
				}
				else{
					$(".userList-wrapper .standardUserListTable",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).html("");
				}
				if($effectArea.hasClass("readOnly")){
					$(".userListWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).addClass("readOnly");
				}
				else{
					$(".userListWindow",orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow).removeClass("readOnly");
				}
				orgAndUsersSelectArea.$currentWindow=addUsersArea.$usersShowFrame;
				orgAndUsersSelectArea.$currentWindow.$modal.loadStatisticsTip();
				orgAndUsersSelectArea.$currentWindow.open();
			});
		},
		
		loadStatisticsTip:function(){	
			var $ToolWidget=$(".tools",this);
			var allRow=$("table.standardTable tr",this).length-1;
			var unselectRow=$("table.standardTable tr.notShow",this).length
			var selectRow=allRow-unselectRow;
			if(allRow<=0){
				$(".search-info-tip",$ToolWidget).hide();
			}
			else{
				$(".search-info-tip",$ToolWidget).html(selectRow+"/"+allRow).attr("title","当前列表已选择"+selectRow).show();	
			}
		},
		
		prepareForSurveyAssign:function(params,dataFieldMapping){
			
			var mapping={};
			$.extend(mapping,{
				orgType:{
					"orgIdlist":"orgIdlist"
				}
				,
				operatorType:{
					"operatorIdList":"operatorIdList"
				}
			},dataFieldMapping);
			$.ajax({
				traditional:true,
				method:"POST",
				data:params,
				url:"tnAssignAction.do?method=getAssignUsersAndDepts",
				dataType:"json",
				success:function(data){
					if(data.result=="true"){
						var orgTypeMapping=mapping.orgType;
						var operatorMapping=mapping.operatorType;
						for(var i in orgTypeMapping){
							var dataList=data[i];
							$.initializeOrgListData(orgTypeMapping[i],dataList);
						}
						for(var i in operatorMapping){
							var dataList=data[i];
							$.initializeOperatorListData(operatorMapping[i],dataList);
						}
						
					} else{
						$.tips("无法加载已选择人员信息名单","推送提示");
					}
				}
			});
			
		},
		
		//for messageUser&org select
		prepareForMessageReceiver:function(params,dataFieldMapping){
			var mapping={};
			$.extend(mapping,{
				orgType:{
					"orgSelect":"orgSelect"
				}
				,
				operatorType:{
					"secretaryList":"secretaryList",
					"directorList":"directorList",
					"studentList":"studentList"
				}
			},dataFieldMapping);
			$.ajax({
				traditional:true,
				method:"POST",
				data:params,
				url:"../msgMessageInfo/getClassifiedReceivers.do",
				dataType:"json",
				success:function(data){
					if(data.success){
						var orgTypeMapping=mapping.orgType;
						var operatorMapping=mapping.operatorType;
						for(var i in orgTypeMapping){
							var dataList=data.data[i];
							$.initializeOrgListData(orgTypeMapping[i],dataList);
						}
						for(var i in operatorMapping){
							var dataList=data.data[i];
							$.initializeOperatorListData(operatorMapping[i],dataList);
						}
						
					} else{
						$.tips("无法加载已选择人员信息名单","推送提示");
					}
				}
			});	
		}
	});
	
	
	
	$(function(){
		var canLoadArea={
			remodal:true,
			ztree:true,
			orgSelect:true,
			addressBook:true
		}
		if(typeof resetCurrentBase=="function"){
			orgAndUsersSelectArea.baseWindow=resetCurrentBase();
		}
		
		var currentWindow=orgAndUsersSelectArea.baseWindow;
		orgAndUsersSelectArea.baseDoc=currentWindow.document.body;
		orgAndUsersSelectArea.$orgAndUsersSelectBaseWindow=$(orgAndUsersSelectArea.baseDoc);
		
		
		if(!currentWindow.$.isScriptIncluded("remodal.css")){
			currentWindow.$.loadScript("/css/jquery-UI/remodal.css",function(){
			});
			
		}
		
		if(!currentWindow.$.isScriptIncluded("remodal-default-theme.css")){
			currentWindow.$.loadScript("/css/jquery-UI/remodal-default-theme.css");
		}
		if(!currentWindow.$.isScriptIncluded("zTreeStyle.css")){
			
			currentWindow.$.loadScript("/css/jquery-UI/zTreeStyle/zTreeStyle.css",function(){
				
			});		
		}

		if(!currentWindow.$.isScriptIncluded("remodal.min.js")){
			canLoadArea.remodal=false;
			currentWindow.$.loadScript("/js/UI/remodal.min.js",function(){
				canLoadArea.remodal=true;
			});
		}
		if(!currentWindow.$.isScriptIncluded("jquery.ztree.all.min.js")){
			canLoadArea.ztree=false;
			$.loadScript("/js/UI/jquery.ztree.all.min.js",function(){
				canLoadArea.ztree=true;
			});
		}
		if(!currentWindow.$.isScriptIncluded("orgSelect.js")){
			canLoadArea.orgSelect=false;
			$.loadScript("/js/public/orgSelect.js",function(){
				canLoadArea.orgSelect=true;
			});
		}
		if(!currentWindow.$.isScriptIncluded("addressBookTool.js")){
			canLoadArea.addressBook=false;
			$.loadScript("/js/public/addressBookTool.js",function(){
				canLoadArea.addressBook=true;
			});
		}
		
		
		if(!$.isScriptIncluded("swfobject.js")){
			$.loadScript("/js/swfobject.js");
		}
		var h5=$.supportHTML5();
		if(!h5){
			if(!$.isScriptIncluded("jquery.uploadify.js")){
				$.loadScript("/js/jquery.uploadify.js");
			}
			if(!$.isScriptIncluded("css/uploadify.css")){
				$.loadScript("/css/uploadify.css");
			}
		}
		else{
			if(!$.isScriptIncluded("jquery.uploadifive.js")){
				$.loadScript("/js/jquery.uploadifive.js");
			}
			if(!$.isScriptIncluded("css/uploadifive.css")){
				$.loadScript("/css/uploadifive.css");
			}
		}	
		
		
		
		var interval=setInterval(function(){
			
			if(canLoadArea.orgSelect&&canLoadArea.ztree&&canLoadArea.remodal&&canLoadArea.addressBook){
				clearInterval(interval);
				orgAndUsersArea.loadScript();
				if(typeof loadExtraOptions=="function"){	
					var extraOptions=loadExtraOptions();
					if(typeof extraOptions.forUsers=="undefined");
					else{
						addUsersArea.options.extraOptions=extraOptions.forUsers;
					}
					if(typeof extraOptions.forOrgs=="undefined");
					else{
						addOrgArea.options.extraOptions=extraOptions.forOrgs;
					}
				}
			}
			
			
		},300)
	});
	
	
})(jQuery);








				



