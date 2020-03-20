

;(function($){
	
	$.fn.extend({
		
		exportBigTable:function(options){
	
			var revisedOptions={
				limit:50,
				errorTip:function(){
					try{
						$.Ntip({
					    	"content":"表格信息导出失败"
					    })
					}
					catch(e){
						alert("表格信息导出失败");
					}
				},
				beforeExport:function(){	
					if($("#downloadButton").length>0){
						$("#downloadButton").hide();
					}
				},
				optionForExcel:{
					traditional:true,
					dataType:"json",
					type:"POST",
					url:"../excelTool/exportCurrentPageByTempData.do",
					success:function(dataInfo){
						if(dataInfo.status == 0 ){
							if($("#downloadButton").length==0){
								var downloadButton="<a href='"+dataInfo.data.downloadPath+"' id='downloadButton' class='btn-orange-l' style='margin-left: 5px;'><span class='btn-orange-r'>下载</span></a>";
								$(downloadButton).insertAfter($("#forExportAddressBook"));
							}
							else{
								$("#downloadButton").attr("href",dataInfo.data.downloadPath);
								$("#downloadButton").show();
							}
						}
						else{
							this.errorTip();
						}
					},
					error:function(dataInfo){
						this.errorTip();
					}
				}
			};
			
			$.extend(true,revisedOptions,options);
		
			var $table=this;
		
			var getTaskId=function(params){
				var dtd = $.Deferred();
				$.ajax({
					url:"../excelTool/getTaskId.do",
					dataType:"json",
					type:"POST",
					data:params,
					traditional:true,
					success:function(dataInfo){
						if(dataInfo.status == 0 ){
							dtd.resolve(dataInfo);
						}
					}
				})
				return dtd;
			};
			
			var errorDeal=function(options){
				$.ajax({
					url:"../excelTool/removeTempInfo.do",
					dataType:"json",
					type:"POST",
					data:options.params,
					success:function(dataInfo){
						
					},
					complete:function(){
						if(typeof options.errorTip=="function");
						else{
							options.errorTip=revisedOptions.errorTip;
						}
						options.errorTip();
					}
				})
			};
			
			var transmitData=function(params){
				var dtd = $.Deferred();
				$.ajax({
					url:"../excelTool/tempSaveInfo.do",
					dataType:"json",
					type:"POST",
					traditional:true,
					data:params,
					success:function(dataInfo){
						if(dataInfo.status == 0){
							dtd.resolve(dataInfo);
						}
						else{
							dtd.reject();
						}
					},
					error:function(){
						dtd.reject();
					}
				})
				return dtd;
			};
			
			var generateExcel=function(params){
				var options=revisedOptions.optionForExcel;
				options.data=params;
				$.ajax(options);
			}
			
			var getData=function(){
				var params={};
				params.rowCount=$table.find("tr").length;
				params.columnCount=$table.find("tr:first th").not(".notExport").length;
				if($table.find("tr th").length>0){
					$table.find("tr").each(function(index,that){
						var $currentLine=$(that);
						$currentLine.find("th").not(".notExport").each(function(index1,that){
							if(params["column"+index1]==null){
								params["column"+index1]=new Array();
							}
							params["column"+index1].push(($(that).text()||""));
						})
						
					})
				}
				
				$table.find("tr").each(function(index,that){
					var $currentLine=$(that);
					$currentLine.find("td").not(".notExport").each(function(index1,that){
						if(params["column"+index1]==null){
							params["column"+index1]=new Array();
						}
						params["column"+index1].push(($(that).text()||""));
					})		
				})
				return params;
			}
			
			revisedOptions.beforeExport()
			var limit=revisedOptions.limit;
			var tableDataInfo=getData();
			var index=0;
			var actionArray=new Array();
			var paramsArray=new Array();
			var count=0;
			for(;index<tableDataInfo.rowCount;index++){
				count++;
				if(index%limit==0){
					var params={};
					params.index=index;
					params.count=(params.index+limit)>tableDataInfo.rowCount?tableDataInfo.rowCount-index:limit;
					paramsArray.push(params);
					actionArray.push(transmitData);
					for(var index1=0;index1<tableDataInfo.columnCount;index1++){
						params["column"+index1]=tableDataInfo["column"+index1].slice(index,index+params.count);
					}
				}
			}
			actionArray.push(generateExcel);
			paramsArray.push({});
			
			getTaskId({"columnCount":tableDataInfo.columnCount,"rowCount":tableDataInfo.rowCount}).then(function(data){
				var evalFuncArray=new Array("actionArray[0](paramsArray[0])");
				var errorFunc=function(){
					errorDeal({params:{exportTaskId:data.exportTaskId},errorTip:revisedOptions.errorTip});
				};
				for(var i=0;i<paramsArray.length;i++){
					paramsArray[i].exportTaskId=data.exportTaskId;
					if(i==paramsArray.length-1){
						evalFuncArray.push(".then(function(){return actionArray["+i+"](paramsArray["+i+"])},function(){errorFunc();})");
					}
					else{
						evalFuncArray.push(".then(function(){return actionArray["+i+"](paramsArray["+i+"])})");
					}
					
				}	
				eval(evalFuncArray.join(""));
			},function(){
				
			});
		},
		
		
		exportTableInCurrentView:function(options){

			var resultData={};
			var defaultSubmitOptions={
				traditional:true,
				dataType:"json",
				type:"POST",
				url:"../excelTool/defaultExportCurrentPage.do",
				data:resultData,
				beforeSend:function(data){
					if($("#downloadButton").length>0){
						$("#downloadButton").hide();
					}
				},
				success:function(dataInfo){
					if(dataInfo.success){
						if($("#downloadButton").length==0){
							var downloadButton="<a href='"+dataInfo.data.downloadPath+"' id='downloadButton' class='btn-orange-l' style='margin-left: 5px;'><span class='btn-orange-r'>下载</span></a>";
							$(downloadButton).insertAfter($("#forExportAddressBook"));
						} else{
							$("#downloadButton").attr("href",dataInfo.data.downloadPath);
							$("#downloadButton").show();
						}
					} else{
						try{
							jAlert("表格信息导出失败",'提示信息');
						}
						catch(e){
							alert("表格信息导出失败");
						}
					}
				},
				error:function(dataInfo){
					try{
						jAlert("表格信息导出失败",'提示信息');
					}
					catch(e){
						alert("表格信息导出失败");
					}
				}
			}
			
			$.extend(defaultSubmitOptions,options);
			
			resultData.rowCount=this.find("tr").length;
			if(this.find("tr th").length>0){
				resultData.columnCount=this.find("tr:first th").not(".notExport").length;
				this.find("tr").each(function(index,that){
					var $currentLine=$(that);
					$currentLine.find("th").not(".notExport").each(function(index1,that){
						if(resultData["column"+index1]==null){
							resultData["column"+index1]=new Array();
						}
						resultData["column"+index1].push(($(that).text()||""));
					})
					
				})
			}
			else{
				resultData.columnCount=this.find("tr:first td").not(".notExport").length;
			}
			
			this.find("tr").each(function(index,that){
				var $currentLine=$(that);
				$currentLine.find("td").not(".notExport").each(function(index1,that){
					if(resultData["column"+index1]==null){
						resultData["column"+index1]=new Array();
					}
					resultData["column"+index1].push(($(that).text()||""));
				})
				
			})
			
			$.ajax(defaultSubmitOptions);
		}
	});
	
})(jQuery)