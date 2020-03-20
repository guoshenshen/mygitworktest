/**
 * 
 * edited by zq@cnic.cn on 2017-05-23
 * 
 * jquery.ztree.all.min.js and its related css file are needed
 * 
 * to load orgTree, please use "$(selector).loadOrgTree(param,extraSetting)"
 * 
 * param : params for loading orgTree, typical: {"orgId":"2","loadRoot":"true"}
 * extraSetting: params for demonstration of orgTree, action for click on treenode, typical:
 * check:{enable:false},//不显示节点前的选择框
 * check:{
		chkStyle:"radio",
		radioType: "all"
   }，
   callback:{
		onClick:function(event, treeId, treeNode)              //显示机构前的单选框 
 * }
 * 
 *  async: {
		  enable: true,   //设置启用异步加载
		  type: "get",   //异步加载类型:post和get
		  url: orgSelectArea.url,  //定义加载机构树请求路径
		  autoParam: ["orgId=orgId",'parentOrgId=parentOrgId'] //定义提交时参数的名称，=号前面标识节点属性，后面标识提交时json数据中参数的名称
	},
 * 
 * 
 */


;(function($){
	var orgSelectArea={
		nodesForShow:new Array(),
		url:"/eosorgTOrganization/getSubOrgInfoForTree.do",
		$orgDemo:null,
		zTreeObj:null,
		zNodes:new Array(),
		dataLoading:function(param,extraSetting){
			$.ajax({
				dataType:"json",
				url:orgSelectArea.url,
				data:param,
				cache : false, //是否使用缓存
				type : 'post',
				error:function(){},
				success:function(resultData){
					orgSelectArea.zNodes=resultData;
					orgSelectArea.loadTree(orgSelectArea.$orgDemo,extraSetting);
				}
				
			});
		},
		loadTree:function($treeContainer,extraSetting){
			var newSetting=null;
			if(extraSetting!=null){
				newSetting=$.extend(true,{},orgSelectArea.settingForOrg,extraSetting);
			}
			else{
				newSetting=orgSelectArea.settingForOrg;
			}
			orgSelectArea.zTreeObj = $.fn.zTree.init($treeContainer, newSetting, orgSelectArea.zNodes);
			var ztree=orgSelectArea.zTreeObj;
			if(extraSetting!=null){
				var callback=extraSetting.callback;
				if(callback!=null){
					var node = ztree.getNodesByFilter(function (node) { return node.level == 0 }, true);
					var uselessNodes = ztree.getNodesByFilter(function (node) { return node.status == 0 });
					ztree.selectNode(node);//选择点  
					if(callback.onClick!=null){
						callback.onClick(null, ztree.setting.treeId, node);//调用事件  
					}
					if(callback.actionAfterTreeLoaded!=null){
						callback.actionAfterTreeLoaded();
					}
				}
			}
			return orgSelectArea.zTreeObj;
		}
	};
	
	
	orgSelectArea.settingForOrg = {
		 async: {
			  enable: true,   //设置启用异步加载
			  type: "get",   //异步加载类型:post和get
			  url: orgSelectArea.url,  //定义数据请求路径
			  autoParam: ["orgId=orgId",'parentOrgId=parentOrgId'] //定义提交时参数的名称，=号前面标识节点属性，后面标识提交时json数据中参数的名称
		},
		check:{
		 	chkStyle:"checkbox",
		 	enable:"true",
		 	//勾选 checkbox 对于父子节点的关联关系
		 	//Y 属性定义 checkbox 被勾选后的情况； 
		 	//N 属性定义 checkbox 取消勾选后的情况； 
		 	//"p" 表示操作会影响父级节点;
		 	//"s" 表示操作会影响子级节点。
		 	//chkboxType: { "Y": "s", "N": "ps" }
			chkboxType: { "Y": "", "N": "" }
	 	},
	 	data:{
	 		//确定 zTree 初始化时的节点数据、异步加载时的节点数据、或 addNodes 方法中输入的 newNodes 数据是否采用简单数据模式 (Array)
	 		//不需要用户再把数据库中取出的 List 强行转换为复杂的 JSON 嵌套格式
	 		//数据格式：[{orgId:1,parentOrgId:0,name:'父节点1'},{orgId:11,parentOrgId:1,name:'节点11'}]
	 		simpleData:{
	 			enable:true,
	 			idKey:'orgId',
	 			pIdKey:'parentOrgId',
	 		}
	 	},
	 	view: {
			fontCss: function(treeId, node) {
				if(node.status&&node.status=="0"){
					return {"color":"#bbb","font-weight":"bold"};
				}
				else{
					return {};
				}
	 			
			},
			nameIsHTML: true
		}
	};
	
	$.extend({
		//查询组织机构
		searchOrg:function(param){
			var orgTree=orgSelectArea.zTreeObj;
			var allNodes=orgTree.getNodesByFilter(function(){
				return true;
			});
			//查找符合条件的节点(若某节点的子节点符合,则该节点也视为符合)      
	        orgSelectArea.nodesForShow=new Array();
	        for(var j in allNodes){
	        	 var node=allNodes[j];
	        	 for(var i in param){
	 	        	if(node[i].indexOf($.trim(param[i]))!=-1){
	 	        		orgSelectArea.nodesForShow.push(node);
	 	        		var parentNode=node.getParentNode();
	 	        		while(parentNode!=null){
	 	        			orgSelectArea.nodesForShow.push(parentNode);
	 	        			parentNode=parentNode.getParentNode();
	 	        		}
	 	        	}
	 	        }
	        }  
	        orgTree.hideNodes(allNodes);
	        orgTree.showNodes(orgSelectArea.nodesForShow);
		},
		//获取已选中的组织机构(返回组织机构数组,数组包含如下信息：[{orgId,orgName,tenantNane}])
		getSelectedOrg:function(functionAfterSelected){
			var selectedNodeList=orgSelectArea.zTreeObj.getCheckedNodes("true");
			var selectedNodeIdArray=new Array();
			//存储选择的节点的id,用于防止重复选择相同机构
			var orgSelectedArray=new Array();
			for(var i in selectedNodeList){
				var node=selectedNodeList[i];
				/*
				var halfCheck = node.getCheckStatus(); 
				if(!halfCheck.half){
					if($.inArray(node.orgId, selectedNodeIdArray)<0){
						selectedNodeIdArray.push(node.orgId);
						var orgInfo={"orgId":node.orgId,"orgName":node.name,"tenantName":node.tenantName};
						orgSelectedArray.push(orgInfo);
					}	
				}
				*/
				if($.inArray(node.orgId, selectedNodeIdArray)<0){
					selectedNodeIdArray.push(node.orgId);
					var orgInfo={"orgId":node.orgId,"orgName":node.name,"tenantName":node.tenantName};
					orgSelectedArray.push(orgInfo);
				}	
			}
			if(typeof functionAfterSelected=="function"){
				functionAfterSelected(orgSelectedArray);
			}
			else if(typeof window.parent.useOrg=="function"){
				window.parent.useOrg(orgSelectedArray);
			}
			else{}
			return  orgSelectedArray;
		}
	})
	
	$.fn.extend({
		loadOrgTree:function(param,extraSetting){
			this.addClass("ztree");
			orgSelectArea.$orgDemo=this;
			var revisedParam=$.extend({"status":1,"loadRoot":"true","showOrderedCASTenant":"false"},param);
			orgSelectArea.dataLoading(revisedParam,extraSetting);
		},
		
		loadOrgTreeWithSearch:function(param,extraSetting){
			var $treeContainer=this;
			$treeContainer.parent().on("click",".search-icon",function(){
				//查询名称中包含某字符的组织机构
				var searchTxt=$(this).parents(".search").find("#search-info").val();
				var param={'name':searchTxt};
				$.searchOrg(param);
			}).on("keyup","#search-info",function(event){
				if(event.keyCode == "13"){
					$(".search-icon",$(this).parents(".orgTool")).click();
				}
			});
			extraSetting=extraSetting||{};
			var callback=extraSetting.callback||{};
			if(!(typeof callback.actionAfterTreeLoaded=="function")){
				callback.actionAfterTreeLoaded=function(){};
			}
			var originAction=callback.actionAfterTreeLoaded;
			callback.actionAfterTreeLoaded=function(){
				originAction();
				$treeContainer.before("<div class='orgTool'><span class='search'><input id='search-info' name='search' value='' type='text' placeholder='单位'><a href='javascript:void(0);' class='search-icon'><img src='/image/search01.png' alt='搜索'></a></span></div>");
			}
			$treeContainer.loadOrgTree(param,extraSetting);
		}
	});

	
})(jQuery);


$(function(){
	if(!$.isScriptIncluded("jquery.ztree.exhide.min.js")){
		
		$.loadScript("/js/UI/jquery.ztree.exhide.min.js",function(){
		});
	}
})
 

 


   


