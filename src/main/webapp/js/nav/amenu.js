
var iconLocation="";  //全局变量
var $left=$(window).width();
var $top=$(window).height();
var $admin=$(".admin");

function updateIconList(flag,iconId){
    $.cookie("selectedIcon",null);
    $.ajax({
        url:'../findMenu/updateIconList.do',
        type:"POST",
        data:"flag="+flag+"&iconid="+iconId+"",
        success:function(data,evt){}
    })
}

if($admin.length>0){

    var menuLoadingOptions={
        needLoad:true,
        $scriptInfo:null,
        $scriptTop:null,
        $scriptFooter:null,
        $scriptTrainApp:null,
        url:"/htmlStructure/adminSkinStructure.js",
    }

    $.fn.extend({

        changeRole:function(actionAfterChanged){
            var $role=this;
            var params={};
            var roleId=$("input[name=roleId]",$role).val();
            params.roleId=roleId;
            $.ajax({
                type:"POST",
                url:"../findMenu/changeRole.do",
                dataType:"json",
                data:params,
                success:function(data){
                    if(data.success){
                        if(typeof actionAfterChanged=="function"){
                            //console.log(actionAfterChanged);
                            actionAfterChanged();
                        } else{
                            window.location.href=data.data.hyperlink;
                        }
                    }else{
                        alert("角色切换失败！");
                    }
                }
			})
		},

		changeLocation:function(actionAfterLocationChanged){
			var params={};
			var $currentMenu=this;
			var $cId=$("input[name=cId]",$currentMenu);
			var $pId=$("input[name=pId]",$currentMenu);
			if($cId.length>0){
				params.cId=$cId.val();
			}
			if($pId.length>0){
				params.pId=$pId.val();
			}

			$.ajax({
			    type:"POST",
			    url:"../findMenu/setLocation.do",
			    dataType:"json",
			    data:params,
			    success:function(data){
					if(data.success){
						if(typeof actionAfterLocationChanged=="function"){
							actionAfterLocationChanged();
						}
					}
					else{

					}
				}
			});
		}

	}),

	$.extend({
		//获取基本信息
		getBasicInfo:function(){
			$.ajax({
				url:'../eosorgTEmployee/JSONDetailOfOperator.do',
				type:"POST",
				dataType:"json",
				success:function(data){
					if(data.success){
						var currentDate = new Date();
						var year=currentDate.getFullYear();
						var operatorInfo=data.data;
						$(".top-wrap .year",$admin).html(year);
						$(".top-wrap .operatorName",$admin).html(operatorInfo.operatorName);
					}
				},
				error:function(data){

                    }
                })
            },


            //登出系统
            logonOutSystem:function(){
                //$.cookie("parentmenu","");
                //$.cookie("myStudyFlag","0");
                //$.cookie('i',"0");   //培训班cookie
                //清空map及session
                $.ajax({
                    url:'../exitSystem/logOut.do',
                    type:"POST",
                    dataType:"json",
                    traditional: true,
                    success:function(data){
                        if(data.success == false){
                            alert("退出系统出现异常！");
                        }else{
                            alert("退出系统！");
                        }
                        if(!(typeof showBufferMask=== "function")){
                            $.getScript("/js/JSCommonTools.js",function(response,status){
                                showBufferMask({"targetUrl":data.data.url});
                            });
                        } else{
                            showBufferMask({"targetUrl":data.data.url});
                        }

                    },
                    error:function(data){
                        alert("退出系统出现异常！");
                    }
                })
            },
            //加载菜单项
            loadMenu:function(){
                var resourceTree={};

                $admin.on("click",".menu",function(){
                    var $currentMenu=$(this);
                    $currentMenu.changeLocation(function(){
                        var link=$("input[name=menuLink]",$currentMenu).val();
                        window.location.href = link;
                    });
                });

                $.ajax({
                    url:'../findMenu/getAdminMenu.do',
                    type:"POST",
                    async:false,
                    dataType:"json",
                    success:function(data){
                        if(data.success){
                            resourceTree=data.data.menuInfo;
                            if(resourceTree["-1"]!=null){
                                var rootMenuList=resourceTree["-1"];
                                var menuLength=rootMenuList.length;
                                var menuArray=new Array();
                                menuArray.push("<ul class='topMenuList'><li class='topMenu toPortal'><input type='hidden' name='roleId' value='1' /><a href='javascript:void(0)'>首页</a></li>");
                                for(var i=0;i<menuLength;i++){
                                    var menu=rootMenuList[i];
                                    menuArray.push("<li  class='topMenu' ><span class='topMenuName menu'><input name='pId' type='hidden' value='"+menu.id+"' /><input type='hidden' name='menuLink' type='hidden' value='"+menu.hyperlink+" '/>"+menu.resourceName+"</span></li>");
                                }
                                menuArray.push("</ul>");
                                $(".navbar",$admin).html(menuArray.join(""));

                                $(".navbar li.topMenu").hover(function(){
                                    var $currentMenu=$(this);
                                    var pId=$(".topMenuName input[name=pId]",$currentMenu).val();
                                    $currentMenu.addClass("showSubMenu");
                                    if($(".subMenuList",$currentMenu).length==0){
                                        var id=$("input[name=pId]",$currentMenu).val();

                                        var subResourceList=resourceTree[id];
                                        var subMenuArray=new Array();
                                        subMenuArray.push("<ul class='subMenuList'><li class='bufferMask'></li>")
                                        if(subResourceList!=null){
                                            var subMenuArrayLength=subResourceList.length;
                                            for(var j=0;j<subMenuArrayLength;j++){
                                                var subMenu=subResourceList[j];
                                                subMenuArray.push("<li class='subMenu menu' title='"+subMenu.resourceName+"'><input name='pId' type='hidden' value='"+pId+"'/><input name='cId' type='hidden' value='"+subMenu.id+"' /><input type='hidden' name='menuLink' type='hidden' value='"+subMenu.hyperlink+" '/>"+subMenu.resourceName+"</li>");
                                            }
                                        }

                                        subMenuArray.push("</ul>");
                                        $currentMenu.append(subMenuArray.join(""));
                                    }
                                },function(){
                                    $(this).removeClass("showSubMenu");
                                })
                            }
                        }
                    }
                })
            },
            //加载面包屑
            getTrace:function(){
                $.ajax({
                    type:"post",
                    url:"../findMenu/getTrace.do",
                    dataType:"json",
                    success:function(data){
                        if(data.success){
                            var traceList=data.data.trace;
                            if(traceList!=null && traceList!="" && traceList.length>0){

                                var parentMenu="",childMenu="",parentUrl="",childUrl="",pId="",cId="",roleId="",adminUrl="";
                                var traceLength=traceList.length;
                                for(var i=0;i<traceLength;i++){
                                    var trace=traceList[i];
                                    if(i==0){
                                        roleId=trace.roleId;
                                        adminUrl=trace.adminUrl;
                                    }else if(trace.pName!=null){
                                        parentMenu=trace.pName;
                                        parentUrl=trace.pUrl;
                                        pId=trace.pId;
                                    }else if(trace.cName!=null){
                                        childMenu=trace.cName;
                                        childUrl=trace.cUrl;
                                        cId=trace.cId;
                                    }
                                }
                                var role=data.data.role;
                                var traceList=data.data.trace;
                                var traceArray=new Array();
                                traceArray.push("<ul><li class='role'><input type='hidden' name='roleId' value='"+role.id+"' />我的管理</li>");
                                var traceLength=traceList.length;
                                if(traceLength>0){
                                    var resourceIdArray=new Array();
                                    var trace=traceList[0];
                                    traceArray.push("<li class='fa fa-angle-right'></li>");
                                    traceArray.push("<li class='menu'><input type='hidden' name='pId' value='"+trace.id+"' /><input type='hidden' name='menuLink' type='hidden' value='"+trace.linkBase+" '/>"+trace.resourceName+"</li>");
                                    if(traceLength==1){
                                    }
                                    else if(traceLength==2){
                                        var subTrace=traceList[1];
                                        traceArray.push("<li class='fa fa-angle-right'></li>");
                                        traceArray.push("<li class='menu'><input type='hidden' name='pId' value='"+trace.id+"' /><input type='hidden' name='cId' value='"+subTrace.id+"' /><input type='hidden' name='menuLink' type='hidden' value='"+subTrace.linkBase+" '/>"+subTrace.resourceName+"</li>");
                                    }
                                }

                                traceArray.push("</ul>");
                                $("#trace",$admin).html(traceArray.join(""));
                                //设置html的title为最后的面包屑
                                var breadcrumbTxt=$("#trace .menu:last").text();
                                $("title").html(breadcrumbTxt);
                            }
                        }
                    }
                })
            },
            //加载角色
            loadRole:function(){
                var roleMap={};

                $(".roleLink",$admin).hover(function(){
                    $(this).addClass("showRole");
                },function(){
                    $(this).removeClass("showRole");
                })

                $("#userRoleMenu",$admin).hover(function(){
                    $(this).addClass("showRole");
                },function(){
                    $(this).removeClass("showRole");
                })

                $.ajax({
                    url:'../role/listRole.do',
                    type:"POST",
                    dataType:"json",
                    success:function(data){
                        var roleList=data.data;
                        if(roleList.length >1) {
                            $(".roleLink",$admin).html(roleList[0].currentRoleName);
                            var roleArray=new Array();
                            roleArray.push("<div id='userRoleMenu'><div class='bufferMask'></div>");
                            var length=roleList.length;
                            for(var i=1;i<length;i++){
                                var role=roleList[i];
                                roleArray.push("<a class='role' href='javascript:void(0);'><input type='hidden' name='roleId' value='"+role.roleId+"'>"+role.roleName+"</a>");
                                roleMap[role.roleId]=role;
                            }
                            roleArray.push("</div>");
                            $(".top-wrap .roleLink",$admin).append(roleArray.join(""));
                        }
                    }
                })
            },

            loadAdminMenuScript:function(actionAfterLoading,url){

                if(menuLoadingOptions.$scriptInfo==null){
                    if(menuLoadingOptions.needLoad){
                        menuLoadingOptions.needLoad=false;
                        if(url==null){
                            url=menuLoadingOptions.url;
                        }
                        $.getScript(
                            url,
                            function(){
                                var $scriptInfo=$($.parseHTML(structure()));
                                menuLoadingOptions.$scriptTop=$scriptInfo.find(".topbody");
                                menuLoadingOptions.$scriptFooter=$scriptInfo.find(".bottombody");
                                menuLoadingOptions.$scriptTrainApp=$scriptInfo.find(".trainApp");
                                menuLoadingOptions.$scriptInfo=$scriptInfo;
                                if(typeof actionAfterLoading=="function"){
                                    actionAfterLoading();
                                }
                            }
                        )
                    } else{
                        var loadInterval=setInterval(function(){
                            if(menuLoadingOptions.$scriptInfo!=null){
                                clearInterval(loadInterval);
                                actionAfterLoading();
                            }
                        },1);
                    }
                }else{
                    actionAfterLoading();
                }
            },

            loadTop:function(){
                $.loadAdminMenuScript(function(){
                    $(".topbody").html(menuLoadingOptions.$scriptTop);
                    $.getBasicInfo();
                    $.loadMenu();
                    $.loadRole();
                })
            },

            loadFooter:function(){
                $.loadAdminMenuScript(function(){
                    $(".bottombody").html(menuLoadingOptions.$scriptFooter);
                })
            },

            loadTrainApp:function(){
                $.loadAdminMenuScript(function(){
                    $(".trainApp").html(menuLoadingOptions.$scriptTrainApp);
                    $.getSelectedIcons();
                    $('#selectImg').toggle(function(){
                        $(this).children('ul').stop(true,true).show('fast');
                        $(this).children('img:eq(1)').attr('src','/image/arrows3Inverse.png');
                    },function(){
                        $(this).children('ul').stop(true,true).hide('fast');
                        $(this).children('img:eq(1)').attr('src','/image/arrows3.png');
                    });
                    $('#button1').toggle(function(){
                        $(this).children('ul').stop(true,true).show('fast');
                        $(this).children('img').attr('src','/image/arrows1Inverse.png');
                    },function(){
                        $(this).children('ul').stop(true,true).hide('fast');
                        $(this).children('img').attr('src','/image/arrows1.png');
                    });

                    $("#selectImg").click(function(){
                        $.ajax({
                            url:'../findMenu/getAllIcons.do',
                            type:'post',
                            dataType:'json',
                            success:function(data,evt){
                                var checkedFlag=1;
                                var temp="";
                                var data1 = data.data;
                                $.each(data1,function(i,value){
                                    checkedFlag=data1[i].count;
                                    if(checkedFlag==1){
                                        temp+="<li><label><input type='checkbox' id='"+data1[i].iconId+"' checked/><span><img src='"+data1[i].iconSrc+"' alt='"+data1[i].iconUrl+"'/>"+data1[i].iconName+"</span></label></li>";
                                    }else
                                        temp+="<li><label><input type='checkbox' id='"+data1[i].iconId+"' /><span><img src='"+data1[i].iconSrc+"' alt='"+data1[i].iconUrl+"'/>"+data1[i].iconName+"</span></label></li>";
                                })
                                $('#selectMenu').html(temp);
                            }
                        })
                    });

                    $(".homezoneall .homezonemore").toggle(function(){
                        $(this).parent().siblings().stop(true,true).hide('slow');
                        $(this).children('img:eq(0)').attr('src','/image/arrows5.png');
                    },function(){
                        $(this).parent().siblings().stop(true,true).show('slow');
                        $(this).children('img:eq(0)').attr('src','/image/arrows5Inverse.PNG');
                    })
                    $('#conBottom .thirdMenu li').mouseover(function(){$(this).addClass('thirdHover')}).mouseout(function(){$(this).removeClass('thirdHover')});
                    $('#conBottom .thirdMenu li').click(function(){$(this).addClass('thirdSelected').siblings().removeClass('thirdSelected')});
                    $("#selectImg").toggle(function(){$(this).siblings().stop(true,true).show('fast');$(this).css('background-image','url(/image/arrows3Inverse.png)')},function(){$(this).siblings().stop(true,true).hide('fast');$(this).css('background-image','url(/image/arrows3.png)')});
                    $("._exitSystemCss").attr("href","#");
                    $(document).on('click',"._exitSystemCss",function(){
                        $.cookie("parentmenu",null);
                        logonOutSystem();
                    });
                    $.getScript("/js/PIE.js",function(){if (window.PIE) { $('.scrollBar').each(function() { PIE.attach(this); }); }});
                    $.getScript("/js/jquery.ui.button.js",function(){$("input:button,input:submit,input:file").button();});


                })
            },

            loadAction:function(){
                $admin.on("click",".role",function(){
                    $(this).changeRole();
                })

                $admin.on("click",".top-wrap .operatorName",function(){
                    window.location.href="eosorgTEmployeeAction.do?method=forupdateSelfInfo";
                })

                $admin.on("click","#exit",function(){
                    $.logonOutSystem();
                });

                $admin.on("click",".toPortal",function(){
                    var url="stuLogonAction.do";
                    /*
                    $.cookie("myStudyFlag","0");
                    $.ajax({
                          type:"POST",
                          url:"findMenuAction.do?method=findMenuForRole",
                          async:false,
                          dataType:"text",
                          data:{roleId:1},
                          success:function(data,evt){
                              openUrl(url);
                          }
                    });
                    */
                    $(this).changeRole(function(){
                        window.location.href="stuLogonAction.do";
                    });
                })
            },


            getSelectedIcons:function(){
                var selectedIcon="",iconTotalNum="",count=0;
                //if($.cookie("selectedIcon")!=null)selectedIcon=$.cookie("selectedIcon");
                var approveStatus = $.cookie("approveStatus");//获取当前培训项目审核状态
                if(selectedIcon==""){
                    $.ajax({
                        url:'../findMenu/getSelectedIcon.do',
                        type:'post',
                        dataType:"text",
                        async:false,
                        success:function(data,evt){
                            var node=$.parseJSON(data).data;
                            var temp="";
                            $.each(node,function(i,value){
                                count=count+1;
                                if (approveStatus!=null && (approveStatus==1043 || approveStatus==1048)) {
                                    temp+="<li id='icon_"+node[i].iconId+"' style='display:inline;overflow: hidden; float: left; width: 120px; height: 120px;padding:10px 0px;' title="+node[i].iconName+"><input value="+count+" type='hidden'/><a href='javascript:alert(\"该培训项目项目审核未通过或驳回修改，不允许操作\")'><img src='"+node[i].iconSrc+"' style='width:110px;height:106px;'/></a></li>";
                                } else {
                                    temp+="<li id='icon_"+node[i].iconId+"' style='display:inline;overflow: hidden; float: left; width: 120px; height: 120px;padding:10px 0px;' title="+node[i].iconName+"><input value="+count+" type='hidden'/><a href="+node[i].iconUrl+"><img src='"+node[i].iconSrc+"' style='width:110px;height:106px;'/></a></li>";
                                }
                            })
                            selectedIcon=temp;
                            $.cookie("selectedIcon",selectedIcon);
                            $.cookie("iconTotalNum",count);
                        }
                    })
                }
                if(selectedIcon=="")selectedIcon="<div id='iconTip' style='font-size:24px;height:100px;line-height:100px;font-family:Microsoft Yahei;padding-left:"+$left/6+"px;color:#CD5555'>请点击右侧“功能管理”，选择培训功能模块!</div>";
                $("#conBottom  .thirdMenu  ul").width(count*120).append(selectedIcon);
                $("#conBottom").scrollbar();
                $('#conBottom > .thirdMenu > ul ').on('click',"li",function(){
                    var iconNum=$(this).find('input').val();
                    var temp=$(this).attr("id");
                    $.cookie("iconLocation",temp);
                    $(this).addClass("thirdSelected");
                    $.cookie("iconNum",iconNum);
                });
                if($.cookie("iconLocation")!="")
                    $('#'+$.cookie("iconLocation")).addClass("thirdSelected");
                if($.cookie("iconNum")!=""&&$.cookie("iconNum")>6&&$.cookie("iconTotalNum")!=""){
                    iconNum=$.cookie("iconNum");
                    iconTotalNum=$.cookie("iconTotalNum");
                    $("#conBottom").find('.barM .bar').css({left : 662 + "px"});
                    $("#conBottom").find('.thirdMenu ul').css({left :(-120)*(iconTotalNum-6)-40 + "px"});
                    $("#conBottom").find('.barL').css('opacity',1);
                    $("#conBottom").find('.barR').css('opacity',0.5);
                }else if($.cookie("iconNum")!=""&&$.cookie("iconNum")<=6&&$.cookie("iconTotalNum")>6){
                    iconNum=$.cookie("iconNum");
                    iconTotalNum=$.cookie("iconTotalNum");
                    $("#conBottom").find('.barM .bar').css({left : 0 + "px"});
                    $("#conBottom").find('.thirdMenu ul').css({left :0 + "px"});
                    $("#conBottom").find('.barL').css('opacity',0.5);
                    $("#conBottom").find('.barR').css('opacity',1);
                }
            },

            updateIconList:function(flag,iconId){
                $.cookie("selectedIcon",null);
                $.ajax({
                    url:'../findMenu/updateIconList.do',
                    type:"POST",
                    data:"flag="+flag+"&iconid="+iconId+"",
                    success:function(data,evt){}
                })
            }
        });
    $(function(){
        $.loadTop();
        $.loadFooter();
        $.getTrace();
        $.loadAction();
        if($('.trainApp').length>0){
            $.loadTrainApp();
        }
    })
}



