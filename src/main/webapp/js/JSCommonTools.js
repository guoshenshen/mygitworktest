/////////////////////////////////////////////////////////////////////////////////////////////////////
var JSCommonToolsArea={
	infoContent:{},
	path:{}
};


function openwindowtocenter( url, winName, width, height) {

	xposition=0; yposition=0;
	if ((parseInt(navigator.appVersion) >= 4 )) {
	  xposition = (window.screen.availWidth - width) / 2;
	  yposition = (window.screen.availWidth - height) / 2;
	}
	yposition = 0;
	theproperty= "width=" + width + ","
	+ "height=" + height + "," 
	+ "location=0," 
	+ "menubar=0,"
	+ "resizable=1,"
	+ "scrollbars=no,"
	+ "status=0," 
	+ "titlebar=0,"
	+ "toolbar=no,"
	+ "hotkeys=0,"
	+ "screenx=" + xposition + "," 
	+ "screeny=" + yposition + "," 
	+ "left=" + xposition + "," 
	+ "top=" + yposition; 
	window.open( url,winName,theproperty );
}

function iFrameHeight(frameId,margin_bottom) { 
	var ifm= document.getElementById(frameId); 
	if(ifm==null);
	else{	
		var subWeb = document.frames ? document.frames[frameId].document : ifm.contentDocument; 
		$(subWeb.body).css("height","auto");
		if(ifm != null && subWeb != null) { 
			if(margin_bottom!=null){
				ifm.height = $(subWeb.body).height()+margin_bottom; 
			} else{
				ifm.height = $(subWeb.body).height()+10; 
			}
			
		} 
	}
} 

function iFrameWidth(frameId,margin_right) { 
	var ifm= document.getElementById(frameId); 
	var subWeb = document.frames ? document.frames[frameId].document : ifm.contentDocument; 
	if(ifm != null && subWeb != null) { 
		if(margin_right!=null){
			ifm.width = subWeb.body.scrollWidth+margin_right; 
		} else{
			ifm.width = subWeb.body.scrollWidth; 
		}
		
	} 
} 

//jquery.js is needed for this js

//jquery.simplemodal.js is needed 
function addMaskForIDV(jqueryId){
	if(jqueryId.length==0);
	else{
		var maskDiv="<div style='position:absolute;top:0px;left:0px;width:100%;height:100%;'>"
	}
}


//open link by generating an dynamic form and submit this form by post method
function openLink(url,paramObj,formProperty,target){
	 var form=$("<form></form>");
	 var inputStr="";
	 form.attr("action",url);
	 form.attr("method","post");
	 if(paramObj!=null){
		 for(var i in paramObj){
			 if(paramObj[i] instanceof Array){
				 for(var j in paramObj[i]){
					 inputStr="<input type='hidden' name='"+i+"'>";
					 var input=$(inputStr);
					 input.attr("value",paramObj[i][j]);
					 form.append(input);
				 }
			 } else{
				 inputStr="<input type='hidden' name='"+i+"'>";
				 var input=$(inputStr);
				 input.attr("value",paramObj[i]);
				 form.append(input);
			 }
			 
		 }
	 }
	 if(formProperty!=null){
		 for(var j in formProperty){
			 form.attr(j,formProperty[j]);
		 }
	 }
	 if(target!=null){
		 target.append(form);
	 } else{
		 form.appendTo("body");
	 }
     form.css('display','none')
     form.submit();
     return form;	 
}

//jquery.form.js is needed before this 
//options{
//	url:, (needed)
//	type: 'get'/'post',
//	data: {},
//	dataType:'json'/'xml','text',
//	beforeSubmit:function(),
//	success:function(),
//	error:function(),
//	timeout:
//	target:
//}

function ajaxOpenLink(options){
	 var form=$("<form></form>");
	 var ajaxOptions=new Object();
	 var inputStr="";
	 if(options.url!=null){
		 form.attr("action",options.url);
		 ajaxOptions.url=options.url;
	 }
	 if(options.dataType!=null){
		 ajaxOptions.dataType=options.dataType;
	 }
	 if(options.type!=null){
		 form.attr("method",options.type);
		 ajaxOptions.type=options.type;
	 }
	 if(options.data!=null){
		 for(var i in options.data){
			 inputStr="<input type='hidden' name='"+i+"'>";
			 var input=$(inputStr);
			 input.attr("value",options.data[i]);
			 form.append(input);
		 } 
		 //alert(form.html());
	 }
	 if(options.success!=null){
		 ajaxOptions.success=options.success;
	 }
	 if(options.error!=null){
		 ajaxOptions.error=options.error;
	 }
	 if(options.beforeSubmit!=null){
		 ajaxOptions.beforeSubmit=options.beforeSubmit;
	 }
	 if(options.timeout!=null){
		 ajaxOptions.timeout=options.timeout;
	 }
	 if(options.target!=null){
		 ajaxOptions.target=options.target;
	 }
	 form.appendTo("body")
     form.css('display','none');
     form.submit(function(){
    	 form.ajaxSubmit(ajaxOptions);
    	 return false
     })
     return form;
}


//弹出窗体
//open pop window
//jquery.simplemodal.1.4.4.min.js

function popupIFRAMEWindow(iframeOptions,popWindowOptions){

	var defaultIframeOptions={
		id:"tempSimpleIframe",
		name:"tempSimpleIframe",
		url:"",
		data:{},
		css:{"overflow-x":"hidden","overflow-y":"auto"}
	};
	var defaultPopWinOptions={
			"opacity":60,
			"overlayClose":false,
			"containerId":"window-frame-base",
			"containerCss":{},
			"close":true
	};

	defaultIframeOptions=$.extend(defaultIframeOptions,iframeOptions||{});
	defaultPopWinOptions=$.extend(defaultPopWinOptions,popWindowOptions||{});
	
	var iframeWindow="<iframe ";
	iframeWindow+=" name='"+defaultIframeOptions.name+"' ";
	iframeWindow+=" id='"+defaultIframeOptions.id+"' ";
	
	var iframeStyle="style='";
	for(var cssType in defaultIframeOptions.css){
		iframeStyle+=cssType;
		iframeStyle+=":";
		iframeStyle+=defaultIframeOptions.css[cssType];
		iframeStyle+=";";
	}
	iframeStyle+="'";
	iframeWindow+=iframeStyle;
	iframeWindow+=" ></iframe>";
	
	$("body").append(iframeWindow);

	var openFrame="#"+defaultIframeOptions.id;
	var modal=$(openFrame).modal(defaultPopWinOptions);
	openLink(defaultIframeOptions.url,defaultIframeOptions.data,{"target":defaultIframeOptions.name});	
	
	return modal;
}

//show bufferImage
//description of options 
//userEffect:是否使用特效circleLoader,false:不使用, true:使用（ie10以下因浏览器不兼容故不使用）
//targetUrl:显示缓冲遮罩后的网站跳转链接
//targetUrlWaitTime:遮罩效果执行该时间后跳转到指定链接，默认1500ms ,若未设置targetUrl请不要设置此项
//background_img:遮罩图片

function showBufferMask(options){
	var maskCss=null;
	var bufferCss=null;
	var element=null;
	//是否使用动态特效
	var useEffect=true;
	var background_img=null;
	if(options!=null){
		maskCss=options.maskCss;
		bufferCss=options.bufferCss;
		background_img=options.background_img
		if(options.element!=null){
			element=options.element;
		}	
		if(options.useEffect!=null){
			useEffect=options.useEffect;
		}
		if(options.background_img!=null){
			useEffect=false;
		}
	}
	
	var maskId='maskDiv';
	var bufferId='bufferDiv';
	var maskDiv=new Array();
	maskDiv.push("<div class='bufferMask' id='"+maskId);
	maskDiv.push("' style='width:100%;height:100%;background-color:#252525;filter: alpha(opacity=0);opacity:0;z-index:1000;top:0px;left:0px'></div>");
	maskDiv.push("<div class='bufferMask' id='"+bufferId+"' style='top:45%;left:45%;z-index:1001;width:100px;height:100px;'></div>");
	if(element==null){
		element=window.document.body;
	}
	$(element).append(maskDiv.join(""));

	if(element=="body"){
		$(element).find("#maskDiv").css("height",document.body.clientHeight).css("width",document.body.clientWidth).css("position","fixed");
		$(element).find("#bufferDiv").css("position","fixed");
	} else{
		$(element).find("#maskDiv").css("height",$(element).css("height")).css("width",$(element).css("width")).css("position","absolute");
		$(element).find("#bufferDiv").css("position","absolute");
	}
	
	
	$(element).find("#maskDiv").animate({opacity:"0.7"},500);

	if (!$.support.leadingWhitespace){
		useEffect=false;
	}
	
	if(useEffect==false||useEffect==null){
		if(background_img!=null){
			$(element).find("#bufferDiv").css("background-image",background_img);
		}
		else{
			$(element).find("#bufferDiv").css("background-image","url(/image/banner-load.gif)");
		}
		$(element).find("#bufferDiv").css("background-repeat","no-repeat");
		$(element).find("#bufferDiv").css("background-position","50% 50%");
		$(element).find("#bufferDiv").css("background-size","100% 100%;");
	} else{
		try{
			$(element).find("#bufferDiv").shCircleLoader({
			    color: '#f00'
			});
		}catch(e){
			$.getScript("/js/UI/jquery.shCircleLoader.js",function(response,status){
				$(element).find("#bufferDiv").shCircleLoader({
				    color: '#f00'
				});
			});
		}
	}

	if(bufferCss!=null){
		for(var css in bufferCss){
			$(element).find("#bufferDiv").css(css,bufferCss[css]);
		}
	}
	if(options!=null){
		if(options.targetUrl!=null&&$.trim(options.targetUrl).length>0){
			var waitTime=500;
			if(options.targetUrlWaitTime!=null&&$.trim(options.targetUrlWaitTime).length>0){
				waitTime=$.trim(options.targetUrlWaitTime);
			}
			setTimeout(function(){
				window.location.href=options.targetUrl;
			}, 500);
		}
	}
	else{
	}
	
	return $(element).find(".bufferMask");
}


//判断浏览器类型
function searchBrowserTypeAndVersion(){
	if(window.typeAndVersion!=null){
		return window.typeAndVersion;
	}
	else{
		var typeAndVersion={};
		var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串 
	    if (userAgent.indexOf("MSIE") > -1 ) {
	    	typeAndVersion.type=  "IE";
	    	
	    	var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
	    	reIE.test(userAgent);
	    	var IEVersion = parseFloat(RegExp["$1"]);
	    	typeAndVersion.version=IEVersion;
	    }
	    else if (userAgent.indexOf("Firefox") > -1) {
	    	typeAndVersion.type=  "FF";
	    } 
	    else if (userAgent.indexOf("Chrome") > -1){
	    	typeAndVersion.type= "Chrome";
	    }
	    else if (userAgent.indexOf("Safari") > -1) {
	    	typeAndVersion.type=  "Safari";
	    } 
	    else if (userAgent.indexOf("Opera")>-1) {
	    	typeAndVersion.type= "Opera"
	    }
	    window.typeAndVersion=typeAndVersion;
	    return window.typeAndVersion;
	} 
}


//判断当前页面显示媒体是pc还是移动设备
function isPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}
 

;(function($){
	$.fn.extend({
		
		"allSelect":function(elements){
			var $controller=this;
			$controller.click(function(){
				var status=$controller.prop("checked");
				$(""+elements).each(function(index,that){
					$(that).prop("checked", status);
				})
				
			})
			
		},
		
		"toggle":function( fn, fn2 ) {
		    var args = arguments,guid = fn.guid || $.guid++,i=0,
		    toggle = function( event ) {
		      var lastToggle = ( $._data( this, "lastToggle" + fn.guid ) || 0 ) % i;
		      $._data( this, "lastToggle" + fn.guid, lastToggle + 1 );
		      event.preventDefault();
		      return args[ lastToggle ].apply( this, arguments ) || false;
		    };
		    toggle.guid = guid;
		    while ( i < args.length ) {
		      args[ i++ ].guid = guid;
		    }
		    return this.click( toggle );
		 },
		 
		 
		//scrollpagination.js is needed here
		//options:the options needed to activate scrollPagination
		scrollLoadInfo:function(options){
			if(options.heightOffset==null||options.heightOffset<0){
				options.heightOffset=10;
			}
			$(this).scrollPagination({
				'contentPage': options.url, //the url where you are searching for results 
				'contentData': options.params, 
				'scrollTarget': $(window),        //$(window) 
				'dataType':options.dataType,
				'heightOffset': options.heightOffset,// how many pixels before reaching end of the page would loading start?
				'beforeLoad':options.beforeLoad,
				'afterLoad':options.afterLoaded,
				'loader':options.loader,
				'reload':options.reload
			});
		},
		
		//生成二维码
		//params:
		//contentInfo (needed)
		qrCodeImg:function(params){
			var $currentImg=this;
			var basicParams={
				contentInfo:"",
				logoPath:"http://www.casmooc.cn/uploadFile/userFile/492649007.jpg",
				imgType:"jpg",
				needCompress:"true",
				size:160,
				logo_width:20,
				logo_height:20,
			}
			$.extend(basicParams,params);
			$.ajax({
				data:basicParams,
				dataType:"text",
				type:"POST",
				url:"QRCodeGenerator.do?method=generateBufferImg",
				success:function(data){
					$currentImg.attr("src", data);
				}
			})
		},
		
		
		//图片查看器
		imgViewer:function(){
			var $img=this;
			var url=$img.attr("src");
			if(window.imgViewer==null){
				var $imgViewer=null;
				var initialFunc=function(){
					var imgViewerArray=new Array();
					imgViewerArray.push('<div class="remodal photo noBorder normalModal bannerPicModal"  data-remodal-id="bannerPicModal" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc">');
					imgViewerArray.push('<div class="imgContainer">');
					imgViewerArray.push('<button data-remodal-action="close" class="remodal-close" ></button>');
					imgViewerArray.push('<img src="###" id="bannerPic" />');
					imgViewerArray.push('</div>');
					imgViewerArray.push('</div>');
					$("body").append(imgViewerArray.join(""));
					window.imgViewer=$("[data-remodal-id=bannerPicModal]").remodal($.defaultRemodalOption());
					$(".bannerPicModal #bannerPic").attr("src",url);
					window.imgViewer.open();
				}
				if($.isScriptIncluded("/js/UI/remodal.min.js")){
					initialFunc();
				}
				else{
					$.loadScript("/js/UI/remodal.min.js",function(){
						initialFunc();
					});
				}	
			} else{
				$(".bannerPicModal #bannerPic").attr("src",url);
				window.imgViewer.open();
			}
				
		}
	});
	
	$.extend({
		
		//判断脚本是否已经加载
		isScriptIncluded:function (name){

		    var js= /js$/i.test(name);

		    var es=document.getElementsByTagName(js?'script':'link');

		    for(var i=0;i<es.length;i++) 

		    if(es[i][js?'src':'href'].indexOf(name)!=-1)return true;

		    return false;

		},

		
		loadScript:function(url,actionAfterScriptLoaded){
			if(/\.css[^\.]*$/.test(url)){
				$("<link type='text/css' rel='stylesheet' href='"+url+"'></link>").insertAfter("head title");
			} else{
				$.getScript(url, function() {
					  if(typeof actionAfterScriptLoaded=="function"){
						  actionAfterScriptLoaded();
					  }
				});
			}
		},
		
		defaultRemodalOption:function(){
			return {
			hashTracking:false,
			closeOnOutsideClick:false,
			closeOnEscape:false
			}
		},
		
		supportHTML5:function(){
			
			/*if (window.applicationCache){
				return true;
			}
			else{
				return false;
			}
			//判断浏览器是否不支持插件 （true为不支持 false为支持）
			var swf;     
			if(typeof window.ActiveXObject != "undefined"){
				//ie浏览器
				swf = new  ActiveXObject("ShockwaveFlash.ShockwaveFlash");
			}else{
				//谷歌和火狐浏览器
				swf = navigator.plugins['Shockwave Flash'];
			}
			return !swf ? true : false;*/
			 var elem = document.createElement('canvas');
			    return !!(elem.getContext && elem.getContext('2d'));
		  
		},
		
		greetingByTime:function(){
			var greeting="";
		 	var currentHour=new Date().getHours();
			if(currentHour>=6&&currentHour<9){
				greeting="早上好";
			}
			else if(currentHour>=9&&currentHour<12){
				greeting="上午好";
			}
			else if(currentHour>=12&&currentHour<14){
				greeting="中午好";
			}
			else if(currentHour>=14&&currentHour<18){
				greeting="下午好";
			}
			else if(currentHour>=18&&currentHour<24){
				greeting="晚上好";
			}
			return greeting;
		},
		
		
		//loading overlay
		
		
		//默认提示信息框
		tips:function(info,title,action,style){
			var dialogStyle='blueStyle';
			if($.trim(style)==""){
				dialogStyle='blueStyle';
			}
			else{
				dialogStyle=style;
			}
			if(typeof jAlert!="function"){
				$.getScript("/js/UI/jquery.alerts.js",function(response,status){
					$.alerts.dialogClass=dialogStyle;
					jAlert(info,title,action);
				});
			}
			else{
				$.alerts.dialogClass=dialogStyle;
				jAlert(info,title,action);
			}
		},
		//默认确认框
		confirm:function(info,title,action,style){
			var dialogStyle='blueStyle';
			if($.trim(style)==""){
				dialogStyle='blueStyle';
			}
			else{
				dialogStyle=style;
			}
			if(typeof jConfirm!="function"){
				$.getScript("/js/UI/jquery.alerts.js",function(response,status){
					$.alerts.dialogClass=dialogStyle;
					jConfirm(info,title,action);
				});
			}
			else{
				$.alerts.dialogClass=dialogStyle;
				jConfirm(info,title,action);
			}
		},
		
		Ntip:function(params){
			var defaultInfo={
					'title':'系统提示',
					'content':"",
					'theme':"blue",
					'onClose':function(){},
					'type':'modal'
			};
			$.extend(defaultInfo,params);

			window.top.$.jAlert(defaultInfo);
			
		},
		
		//新型确认框
		Nconfirm:function(params){
			var defaultInfo={
					'title':'系统提示',
					'confirmQuestion':"",
					'theme':"blue",
					'btns': { 'text': '关闭' },
					'type':'confirm',
					'confirmBtnText':'是',
					'denyBtnText':'否',
					'onConfirm':function(){},
					'onDeny':function(){}	
			};
			$.extend(defaultInfo,params);

			window.top.$.jAlert(defaultInfo);

		},
		
		
		loadStructureHTMLByJS:function(path,signForLoadingStructure,elementId,actionAfterLoaded){
			if(typeof actionAfterLoaded!="function"){
				actionAfterLoaded=function(){
					
				}
			}
			if(JSCommonToolsArea.infoContent[elementId]!=null){
				actionAfterLoaded(JSCommonToolsArea.infoContent[elementId]);
			}
			else{
				if(JSCommonToolsArea.path[elementId]==null){
					JSCommonToolsArea.path[elementId]=path;
					$.getScript(
				        path,
				        function(){
				        	var functionForLoadingStructure=new Function("return "+signForLoadingStructure);
							var $scriptInfo=$($.parseHTML(functionForLoadingStructure()));
							JSCommonToolsArea.infoContent[elementId]=$scriptInfo.find("#"+elementId);
							actionAfterLoaded(JSCommonToolsArea.infoContent[elementId]);
							$scriptInfo.find(".separatePart").each(function(index,that){
								JSCommonToolsArea.infoContent[that.id]=$(that);
							});
				        }
					);
				}
				else{
					var interval=setInterval(function(){
						if(JSCommonToolsArea.infoContent[elementId]!=null){
							clearInterval(interval);
							actionAfterLoaded(JSCommonToolsArea.infoContent[elementId]);
						}
					},10);
				}
			}	
		},
		
		
		//加载某信息的html模板
		loadStructureHTML:function(path,elementId,actionAfterLoaded){
			if(typeof actionAfterLoaded!="function"){
				actionAfterLoaded=function(){
					
				}
			}
			if(JSCommonToolsArea.infoContent[elementId]!=null){
				actionAfterLoaded(JSCommonToolsArea.infoContent[elementId]);
			}
			else{
				if(JSCommonToolsArea.path[elementId]==null){
					JSCommonToolsArea.path[elementId]=path;
					$.ajax({
				        url:path,
				        dataType:'text',
				        success:function(data){
							var $scriptInfo=$($.parseHTML(data));
							JSCommonToolsArea.infoContent[elementId]=$scriptInfo.find("#"+elementId);
							actionAfterLoaded(JSCommonToolsArea.infoContent[elementId]);
							$scriptInfo.find(".separatePart").each(function(index,that){
								JSCommonToolsArea.infoContent[that.id]=$(that);
							});
				        },
				        complete:function(ev){
				        }
				    });
				}
				else{
					var interval=setInterval(function(){
						if(JSCommonToolsArea.infoContent[elementId]!=null){
							clearInterval(interval);
							actionAfterLoaded(JSCommonToolsArea.infoContent[elementId]);
						}
					},10);
				}
			}	
		},

		
		
		//分页器配置信息
		getPaginationConfig:function(){
			if(!$.isScriptIncluded("jqPaginator.min.js")){
				$.loadScript("/js/UI/jqPaginator.min.js",function(){
				});
			}
			
			
			var $pagination=null;
			if($("body").hasClass("admin")){
				$pagination=$(".pagination-admin");
			}
			else{
				$pagination=$(".pagination");
			}
			
			var pageSize=20;
			
			if(typeof $.getPageSize=="function"){
				pageSize=$.getPageSize();
			}
			
			
			var configInfo={
                pagination:$pagination,
				searchInfo:{startIndex:1,count:pageSize,pageIndex:1},
				resetSearchInfo:function(){
					var $config=this;
					$config.searchInfo={startIndex:1,count:pageSize,pageIndex:1};
				},
				resetPartSearchInfo:function(){
					var $config=this;
					$.extend($config.searchInfo,{startIndex:1,count:pageSize,pageIndex:1});
				},
				getUrlForPagination:function(){},
				actionForSucessLoadingData:function(data){},
				actionForErrorLoadingDate:function(){
                    var $config=this;
                    $config.actionForClearLoadedData();
                    try{
                        $pagination.jqPaginator('destroy');
                    }
                    catch(e){
                    }
                    if(window.parent&&typeof window.parent.iframeResize=="function"){
                        window.parent.iframeResize();
                    }
				},
				actionForClearLoadedData:function(){},
				actionForLoadingPagination:function(){
					var $config=this;
					var searchInfo=$config.searchInfo;
					var startIndex=1;
					var count=searchInfo.count;
					var pageIndex=Math.ceil((startIndex+1)/count);
					$.ajax({
						method:"POST",
						data:searchInfo,
						url:this.getUrlForPagination(),
						dataType:"json",
						traditional:true,
						success:function(response){
							if(response.status){
								var data=response.data;
								var length=data.total;
								$config.length=length;
								//$pagination.siblings(".pagination-length").html("共"+length+"项");
								if(length>0){
									$pagination.jqPaginator({
										first: '<li><a href="javascript:;">首页</a></li>',
								        prev: '<li><a href="javascript:;">上一页</a></li>',
								        next: '<li><a href="javascript:;">下一页</a></li>',
								        last: '<li><a href="javascript:;">第{{totalPages}}页</a></li>',
								        page: '<li><a href="javascript:;">{{page}}</a></li>',
										totalCounts: length,
									    visiblePages: 6,
									    currentPage:pageIndex,
									    pageSize:searchInfo.count,
									    onPageChange: function (num, type) {
											if(num==1){
                                                searchInfo.startIndex=num;
                                                $config.actionForSucessLoadingData(data);
											}
											else{
                                                searchInfo.startIndex=num;
                                                $.ajax({
                                                    method:"POST",
                                                    data:searchInfo,
                                                    url:$config.getUrlForPagination(),
                                                    dataType:"json",
                                                    traditional:true,
                                                    success:function(response1){
                                                        if(response1.status){
                                                            $config.actionForSucessLoadingData(response1.data);
                                                        }
                                                    },
                                                    error:function(){
                                                        $config.actionForErrorLoadingDate();
                                                    }
                                                })
											}
							    			$("#pagination input[name=startIndex]").val(searchInfo.startIndex);
							    			$("#pagination input[name=count]").val(searchInfo.count);
									    }
									});
								}
								else{
									$config.actionForClearLoadedData();
							    	try{
							    		$pagination.jqPaginator('destroy');
							    	}
							    	catch(e){	
							    	}
							    	if(window.parent&&typeof window.parent.iframeResize=="function"){
										window.parent.iframeResize();
									}
							    }
                                var $paginationLength=$config.pagination.siblings(".pagination-length");
                                if($paginationLength.length>0){
                                    $paginationLength.html("共"+length+"项");
                                }
                                else{
                                    $("<span class='pagination-length'>共"+length+"项</span>").insertBefore($config.pagination);
                                }
							}
							else{
                                $config.actionForErrorLoadingDate();
							}
						},
						error:function(){
                            $config.actionForErrorLoadingDate();
						}
					})
				},
				
			};
			
			
			
			return configInfo;
		
		},
		
		getResourceTypeName:function(type){
			if(type=="0"){
				return {
					name:"课程"
						
				}
			}
			else if(type=="1"){
				return {
					name:"培训"
				}
			}
			else if(type=="2"){
				return {
					name:"新闻"
				}
			}
			else if(type=="3"){
				return {
					name:"通知"
				}
			}
			else if(type=="4"){
				return {
					name:"专题"
				}
			}
			else if(type=="5"){
				return {
					name:"直播"
				}
			}
			else if(type=="6"){
				return {
					name:"计划"
				}
			}
			else if(type=="7"){
				return {
					name:"单位"
				}
			}
			else if(type=="8"){
				return {
					name:"师资"
				}
			}
			else{
				return {
					name:"&nbsp;&nbsp;"
				}
			}
		}
	})
})(jQuery);


$(function(){	
	if(typeof $.jAlert!="function"){
		$("head",window.top.document).append("<link type='text/css' href='/css/jquery-UI/jAlert.css' rel='stylesheet'></link>");
		$("body",window.top.document).append("<script type='text/javascript' src='/js/UI/jAlert.js'></script>");
	}
	
	if(typeof $.jqPaginator!="function"){
		$("body",window.top.document).append("<script type='text/javascript' src='/js/UI/jqPaginator.min.js'></script>");
	}
})




