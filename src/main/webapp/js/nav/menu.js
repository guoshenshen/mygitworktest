
/**
*动态获取所有一级菜单和二级菜单;
*@author wxy 
*@version 2011.08.31 
*/
var iconLocation="";  //全局变量
var $left=$(window).width();
var $top=$(window).height();
var exitOptions={		
}

function findMenu(){
   var parentmenu="",parentMenuId="",parent_menu="",currentmenuchildarea="",childmenu="",currentSkinId=0;
   //if($.cookie("parentmenu")!=null)parentmenu=$.cookie("parentmenu");
   var $menuarea=$("#menu1");
   if(parentmenu==''){
     $.ajax({
      url:'../findMenu/getParentMenu.do',
      type:"POST",
      async:false,
      dataType:"json",
      success:function(data){
            var node=data.data;
            parent_menu="<li class=firstmenu><a href='javascript:void(0);' onclick='javascript:adminGoToIndex();' >首&nbsp;页</a></li>"
            $.each(node,function(i,value){
              parent_menu +="<li class=firstmenu><span class='"+node[i]._parentId+"'></span><a href="+node[i].resourceUrl+" onclick='writeLocation("+node[i]._parentId+",0)'>"+node[i].resourceName+"</a><ul class=secondmenu></ul></li>";
              currentSkinId=node[i].currentSkinId;
            })
            //$.cookie("parentmenu",parent_menu);
            parentmenu=parent_menu;
           }
      })
    }
    $menuarea.html(parentmenu);
    var $selectedMenu=$menuarea.find(".firstmenu");
    $selectedMenu.hover(
      function(){
          var self=$(this);
          parentMenuId=self.find("span:eq(0)").attr("class");
          currentmenuchildarea=self.find(".secondmenu");
          $selectedMenu.css("background-image","")
                       .find(".secondmenu")
                       .css("display","none");
          if(currentmenuchildarea.html()==''){
             $.ajax({
                url:'findMenuAction.do?method=getChildMenu',
                type:"POST",
                data:"pId="+parentMenuId+"",
                dataType:"text",
                async:false,
                success:function(data,evt){
                    var node=$.parseJSON(data);
                    var allchildmenu="";
                    $.each(node,function(j,value){
                       var _cId=$.cookie('cId');
                       var newCid=node[j].child_resourceId;
                       if(_cId==newCid){
                           allchildmenu +="<li><a class='"+node[j].child_resourceId+"' href='"+node[j].child_resourceUrl+"' onclick='writeLocation("+parentMenuId+","+node[j].child_resourceId+")' style='color:#ff7e00'>"+node[j].child_resourceName+"</a></li>";
                       } else{
                           allchildmenu +="<li><a class='"+node[j].child_resourceId+"' href='"+node[j].child_resourceUrl+"' onclick='writeLocation("+parentMenuId+","+node[j].child_resourceId+")' onmouseover=style.color='#ff6c00' onmouseout=mouseoutFontColor(this.style,"+currentSkinId+")>"+node[j].child_resourceName+"</a></li>";
                       }
                    })
                   childmenu=allchildmenu;
                   currentmenuchildarea.html(childmenu);
               }
             })
          }
          if(currentmenuchildarea.html()==''){

          } else{
             currentmenuchildarea.parent().end().stop(true,true).show(200);
          }
       },
  function(){
        var self=$(this);
        self.find(".secondmenu")
            .stop(true,true)
            .hide(1);
  });
    var logonContent1="";
    $.ajax({
    		url:'../tenant/findTenantName.do',
    	    type:"POST",
    	    async:false,
    	    dataType:"json",
    	    success:function(data){
            logonContent1 = data.data;
    	}
    })
    var TenantId="";
    $.ajax({
		  url:'../tenant/findTenantId.do',
	      type:"POST",
	      async:false,
	      dataType:"json",
	      success:function(data){
	      TenantId = data.data;
	   }
    })
    if(TenantId==1000){
    	var logonContent = '<div style="float:left;height:70px;width:90px;margin-top:35px;"><img src="/image/nlogo.png"></div>\
        	<div class="leftfloat" style="float:left;height:70px;line-height:35px;float:left;margin-top:5px;font-size:28px;color:#FFF;"><b>中国科学院继续教育网</b></br>\
        	<span style="font-size:18px;color:#FFF;"><b>网络学习平台</b></span></div>';
    }
    else if(TenantId==162 || TenantId==997){
    	var logonContent = '<div style="float:left;height:70px;width:90px;margin-top:35px;"><img src="/image/nlogo.png"></div>\
     	   <div class="leftfloat" style="float:left;height:70px;line-height:35px;float:left;margin-top:5px;font-size:24px;color:#FFF;"><b>'+logonContent1+'</b></br>\
     	   <span style="font-size:18px;color:#FFF;"><b>网络学习平台</b></span></div>';
    } else if(TenantId==2001){
    	var logonContent = '<div style="float:left;height:70px;width:90px;margin-top:35px;"><img src="/image/united_front_wd_adminview_logo.png"></div>\
      	   <div class="leftfloat" style="float:left;height:70px;line-height:35px;float:left;margin-top:5px;font-size:24px;color:#FFF;"><b>'+logonContent1+'</b></br>\
      	   <span style="font-size:18px;color:#FFF;"><b>在线学习平台</b></span></div>';
     } else if(TenantId==1006){
    	var logonContent = '<div style="float:left;height:70px;width:90px;margin-top:35px;"><img src="/image/shanghaitech_background.png"></div>\
      	   <div class="leftfloat" style="float:left;height:70px;line-height:35px;float:left;margin-top:5px;font-size:24px;color:#FFF;"><b>'+logonContent1+'</b></br>\
      	   <span style="font-size:18px;color:#FFF;"><b>在线学习平台</b></span></div>';
     } else if(TenantId==1010){
    	var logonContent = '<div style="float:left;height:70px;width:90px;margin-top:35px;"><img src="/image/niot/niot_background.png"></div>';
     } else{
        var logonContent = '<div style="float:left;height:70px;width:90px;margin-top:35px;"><img src="/image/nlogo.png"></div>\
    	   <div class="leftfloat" style="float:left;height:70px;line-height:35px;float:left;margin-top:5px;font-size:24px;color:#FFF;"><b>中国科学院'+logonContent1+'</b></br>\
    	   <span style="font-size:18px;color:#FFF;"><b>网络学习平台</b></span></div>';
    	   
    }
    $("#pagebody #org_Logo").html(logonContent);
    
}


function mouseoutFontColor(obj,currentSkinId){
	if(currentSkinId==5){
		obj.color='#3a80c1';
    } else{
		obj.color='white';
    }
}

/**
*保存一级菜单和二级菜单的名称;
*@author wxy 
*@version 2011.09.6 
*/
function writeLocation(parentMenuId,childMenuId){
    $.cookie('cId',null);
    $.cookie('userpath',null);
    $.ajax({
    type:"POST",
    url:"findMenuAction.do?method=setLocation",
    dataType:"text",
    data:"cId="+childMenuId+"&pId="+parentMenuId+"",
    success:function(data,evt){}
    });
}

function getLocation(){
  $.ajax({
    type:"POST",
    url:"../findMenu/getLocation.do",
    dataType:'text',
    success:function(data,evt){
      var node=$.parseJSON(data);
      var parentMenu="",childMenu="",parentUrl="",childUrl="",pId="",cId="",roleId="",adminUrl="";
      $.each(node,function(i,value){
         if(i==0){
           roleId=node[i].roleId;
           adminUrl=node[i].adminUrl;
         }else if(node[i].pName!=null){
           parentMenu=node[i].pName;
           parentUrl=node[i].pUrl;
           pId=node[i].pId;
         }else if(node[i].cName!=null){
           childMenu=node[i].cName;
           childUrl=node[i].cUrl;
           cId=node[i].cId;
         }
      })
      if(cId!="")
          $.cookie('cId',cId); 
      if(parentMenu!=""&&childMenu!=""){
         $('#menu1').find(".firstmenu").find("."+pId).siblings().css("color","#ff7e00");
         userPath="<span><a href='javascript:changeRoleAndOpen(\""+adminUrl+"\","+roleId+");'>我的管理</a>&nbsp;&nbsp;〉<a href='"+parentUrl+"' onclick='writeLocation("+pId+",0)'>"+parentMenu+"</a>&nbsp;&nbsp;〉&nbsp;&nbsp;</span><span><a href='"+childUrl+"' onclick='writeLocation("+pId+","+cId+")'>"+childMenu+"</a></span>";
      }else if(childMenu==""&&parentMenu!=""){
         $('#menu1').find(".firstmenu").find("."+pId).siblings().css("color","#ff7e00");
         userPath="<span><a href='javascript:changeRoleAndOpen(\""+adminUrl+"\","+roleId+");'>我的管理</a>&nbsp;&nbsp;〉&nbsp;&nbsp;</span><span><a href='"+parentUrl+"' onclick='writeLocation("+pId+",0)'>"+parentMenu+"</a></span>";
      }else if(childMenu==""&&parentMenu==""){
         $('#menu1').find(".firstmenu").find(".3").siblings().css("color","#ff7e00");
         userPath="<span><a href='javascript:changeRoleAndOpen(\""+adminUrl+"\","+roleId+");'>我的管理</a>&nbsp;&nbsp;〉<a href='onlineTraining.do?method=searchTrain' onclick='writeLocation(3,0)'>培训实施</a>&nbsp;&nbsp;〉&nbsp;&nbsp;</span><span><a href='onlineTraining.do?method=searchTrain' onclick='writeLocation(3,3004)'>培训班管理</a></span>";     
         $.cookie('cId',3004);
      }
      $("#trace").html(userPath);
     }
    })
}

function adminGoToIndex(){
	url="stuLogonAction.do";
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
}

function changeRoleAndOpen(url,adminRoleId)
{
   showBufferMask();
   $.ajax({
      type:"POST",
      url:"findMenuAction.do?method=findMenuForRole",
      dataType:"text",
      data:{roleId:adminRoleId},
      success:function(data,evt){ 
      	//alert("切换成功");
      	 openUrl(url);
      }
   });
}



function logonOutSystem(){
//先清空cookie
	$.cookie("parentmenu","");
	$.cookie("myStudyFlag","0");
	$.cookie('i',"0");   //培训班cookie
	//清空map及session
	$.ajax({
		url:'exitSystem.do?method=logOut',
		type:"POST",
		dataType:"json",
		traditional: true,  
		success:function(data){
			if(data.result=="false"){
				alert("退出系统出现异常！");
			}
			if(!(typeof showBufferMask=== "function")){
		    	  $.getScript("/js/JSCommonTools.js",function(response,status){
		    		  showBufferMask({"targetUrl":data.url});
		    	});
		    } 
			else{
				showBufferMask({"targetUrl":data.url});
			}
			
		},
		error:function(data){
			alert("退出系统出现异常！");
		}
	})
}



function openUrl(url){
	$.cookie("parentmenu","");
	window.location.replace(url);//切换角色后，禁止用户后退
}

function getSelectedIcons(){
  var selectedIcon="",iconTotalNum="",count=0;
  //if($.cookie("selectedIcon")!=null)selectedIcon=$.cookie("selectedIcon");
  if(selectedIcon==""){
    $.ajax({
     url:'findMenuAction.do?method=getSelectedIcon',
     type:'post',
     dataType:"text",
     async:false,
     success:function(data,evt){
        var node=$.parseJSON(data);
        var temp="";
        $.each(node,function(i,value){
           count=count+1;
           temp+="<li id='icon_"+node[i].iconId+"' style='display:inline;overflow: hidden; float: left; width: 120px; height: 102px;padding:10px 0px;' title="+node[i].iconName+"><input value="+count+" type='hidden'/><a href="+node[i].iconUrl+"><img src='"+node[i].iconSrc+"' style='width:80px;height:106px;'/></a></li>";
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
}

function updateIconList(flag,iconId){
  $.cookie("selectedIcon",null);
  $.ajax({
     url:'findMenuAction.do?method=updateIconList',
     type:"POST",
     data:"flag="+flag+"&iconid="+iconId+"",
     success:function(data,evt){
    	//alert("测试结果！"); 
    	
     }
  });
}
/**
*动态获取用户角色列表；
*@author wxy 
*@version 2011.08.31
*/

function findRole()
{  
   $.ajax({
     url:'../role/listRole.do',
     type:"POST",
     dataType:"json",
     success:function(data){
          var node = data.data;
          if(node.length >1) {
            var temp="";
            $("#rolebar ul").attr("id","Layer0");
            $.each(node,function(i,value){
              var count=node[i].count;
              if(count==0){
              $("#_juese").append("<a href='javascript:void(0);' id='showRole' style='display:block;font-weight:bold;color:#E8E8E8; padding-top:5px; border:0;padding-bottom:2px;'>"+node[i].currentRoleName+"</a>");
              }else
                 temp+="<li><a href="+node[i].roleUrl+" class='top_an_l' onclick='changeRole("+node[i].roleId+")'>"+node[i].roleName+"</a></li>";
            });
            $("#Layer0").append(temp).hide();
            $("#_juese").bind('mouseover',function(){
              $("#Layer0").show();
            });
            $("#rolebar").bind('mouseleave',function(){
              $("#Layer0").hide();
            })
          }
     }
   })
}
/**
*作用：处理用户的角色改变;
*@author wxy 
*@version 2011.09.14 
*/
function changeRole(roleId)
{
   showBufferMask();
   $.ajax({
      type:"POST",
      url:"findMenuAction.do?method=findMenuForRole",
      async:false,
      dataType:"text",
      data:"roleId="+roleId+"",
      success:function(data,evt){ $.cookie("parentmenu",null);
        if(roleId==1)writeLocation(8005,0);  //8005:resource表的id ——个人设置
      }
   });
}



/**
*页面初始化动态加载信息;
*@author wxy 
*@version 2011.09.6 
*/

$(document).ready(function(){
	  $.getScript("/js/JSCommonTools.js",function(){});
      findMenu();      //加载系统菜单
      getLocation();
      findRole();      //加载用户角色
      if($('#funcCon').html()!=null){
        getSelectedIcons();
        $('#selectImg').toggle(function(){
		$(this).children('ul').stop(true,true).show('fast');
		$(this).children('img:eq(1)').attr('src','././image/arrows3Inverse.png');
	    },function(){
		  $(this).children('ul').stop(true,true).hide('fast');
		  $(this).children('img:eq(1)').attr('src','././image/arrows3.png');
	    });
	    $('#button1').toggle(function(){
		$(this).children('ul').stop(true,true).show('fast');
		$(this).children('img').attr('src','././image/arrows1Inverse.png');
	    },function(){
		$(this).children('ul').stop(true,true).hide('fast');
		$(this).children('img').attr('src','././image/arrows1.png');
	    });

	    $("#selectImg").click(function(){
          $.ajax({
            url:'findMenuAction.do?method=getAllIcons',
            type:'post',
            dataType:'json',
            success:function(data,evt){
              var checkedFlag=1;
              var temp="";
              $.each(data,function(i,value){
                checkedFlag=data[i].count;
                if(checkedFlag==1){
                  temp+="<li><label><input type='checkbox' id='"+data[i].iconId+"' checked/><span><img src='"+data[i].iconSrc+"' alt='"+data[i].iconUrl+"'/>"+data[i].iconName+"</span></label></li>";
                }else
                  temp+="<li><label><input type='checkbox' id='"+data[i].iconId+"' /><span><img src='"+data[i].iconSrc+"' alt='"+data[i].iconUrl+"'/>"+data[i].iconName+"</span></label></li>";
             })
            $('#selectMenu').html(temp);
         }
      })
    });

	$(".homezoneall .homezonemore").toggle(function(){
		$(this).parent().siblings().stop(true,true).hide('slow');
		$(this).children('img:eq(0)').attr('src','././image/arrows5.png');
	},function(){
	    $(this).parent().siblings().stop(true,true).show('slow');
		$(this).children('img:eq(0)').attr('src','././image/arrows5Inverse.PNG');	
	})
    $('#conBottom .thirdMenu li').mouseover(function(){$(this).addClass('thirdHover')}).mouseout(function(){$(this).removeClass('thirdHover')});
    $('#conBottom .thirdMenu li').click(function(){$(this).addClass('thirdSelected').siblings().removeClass('thirdSelected')});
    $("#selectImg").toggle(function(){$(this).siblings().stop(true,true).show('fast');$(this).css('background-image','url(././image/arrows3Inverse.png)')},function(){$(this).siblings().stop(true,true).hide('fast');$(this).css('background-image','url(././image/arrows3.png)')});
  }
      
      $("._exitSystemCss").attr("href","#");
      $(document).on('click',"._exitSystemCss",function(){
    	  $.cookie("parentmenu",null);
      	  logonOutSystem();  
      });
      $.getScript("/js/PIE.js",function(){if (window.PIE) { $('.scrollBar').each(function() { PIE.attach(this); }); }});
      //$("#pagebody").css("width",$(window).width()-3);//动态修改屏幕宽度
      $.getScript("/js/jquery.ui.button.js",function(){$("input:button,input:submit,input:file").button();});
      
      
    
})




