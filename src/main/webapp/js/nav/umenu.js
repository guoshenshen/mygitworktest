/**
 *动态获取所有一级菜单和二级菜单;
 *@editor zq@cnic.cn
 *@version 2017.06.01
 */
var iconLocation = "";  //全局变量
var $left = $(window).width();
var $top = $(window).height();
var exitOptions = {}

var operatorInfoOptions = {
    logon: false
}


var menuTraceArray = {
    '0': {
        'declare': '门户',
        'parent': null,
        'trace': '<li><i class="fa fa-home" style="color:#929292;"></i><a href="javascript:void(0);" onclick="javascript:openNavClick(1);" >首页</a></li>'
    },
    '1': {'declare': '课程学习', 'parent': '0', 'trace': '<li><a href="javascript:void(0);">课程学习</a></li>'},
    '2': {
        'declare': '新闻',
        'parent': '0',
        'trace': '<li><a href="javascript:void(0);"onclick="javascript:openNavClick_Sub(21)">新闻</a></li>'
    },
    '3': {
        'declare': '通知',
        'parent': '0',
        'trace': '<li><a href="javascript:void(0);"onclick="javascript:openNavClick_Sub(22)">通知</a></li>'
    },
    '4': {
        'declare': '学习资源',
        'parent': '0',
        'trace': '<li><a href="javascript:void(0);"onclick="javascript:openNavClick(7);">学习资源</a></li>'
    },
    '5': {
        'declare': '培训项目',
        'parent': '0',
        'trace': '<li><a href="javascript:void(0);"onclick="javascript:openNavClick(6);">培训项目</a></li>'
    },
    '6': {'declare': '新闻详情', 'parent': '2', 'trace': '<li>新闻详情</li>'},
    '7': {'declare': '通知详情', 'parent': '3', 'trace': '<li>通知详情</li>'},
    '8': {
        'declare': '教师资源',
        'parent': '0',
        'trace': '<li><a href="javascript:void(0);"onclick="javascript:openNavClick(8);">教师资源</a></li>'
    },
    '9': {'declare': '教师详细', 'parent': '8', 'trace': '<li>教师详细</li>'},
    '10': {'declare': '课程详情', 'parent': '4', 'trace': '<li>课程详情</li>'}
};


//页面模板控制器
//edited by zq@cnic.cn
var menuLoadingOptions = {
    needLoad: true,
    $currentPage: null,
    $scriptInfo: null,
    $scriptPortalFooter: null,
    $scriptPortalNavi: null,
    $scriptSimpleNavi: null,
    $scriptSimpleFooter: null,
    $scriptAside: null,
    url: "/htmlStructure/userSkinStructure.js",
    loginInfo: {
        needLoad: true,
        tenantName: "",
        //驻留站点tenantId
        tenantId: null,
        //用户隶属租户tenantId
        userTenant: null,
        userForm: null,
        showVersionChange: false
    }
}


menuLoadingOptions.initializeInfo = function () {
    var $currentPage = $("body.student");
    menuLoadingOptions.$currentPage = $currentPage;
}

menuLoadingOptions.loadScript = function (actionAfterLoading, url) {
    menuLoadingOptions.ifHasLogined(function () {
        var loginInfo = menuLoadingOptions.loginInfo;
        if (loginInfo.tenantId == 2001) {
            url = "http://resource.casmooc.cn/htmlStructure/skinStructure_united_front_wd.js";
        }
        if (url == null) {
            url = menuLoadingOptions.url;
        }
        if (menuLoadingOptions.$scriptInfo == null) {
            if (menuLoadingOptions.needLoad) {
                menuLoadingOptions.needLoad = false;
                $.getScript(
                    url,
                    function () {
                        var $scriptInfo = $($.parseHTML(structure()));
                        menuLoadingOptions.$scriptPortalFooter = $scriptInfo.find(".footbox");
                        menuLoadingOptions.$scriptSimpleFooter = $scriptInfo.find("#link");
                        menuLoadingOptions.$scriptPortalNavi = $scriptInfo.find(".hand_con");
                        menuLoadingOptions.$scriptSimpleNavi = $scriptInfo.find(".two_hand");
                        menuLoadingOptions.$scriptAside = $scriptInfo.find("#asid_share");
                        menuLoadingOptions.$scriptInfo = $scriptInfo;
                        actionAfterLoading();
                    }
                )
            } else {
                var loadInterval = setInterval(function () {
                    if (menuLoadingOptions.$scriptInfo != null) {
                        clearInterval(loadInterval);
                        actionAfterLoading();
                    }
                }, 1);
            }
        } else {
            actionAfterLoading();
        }
    })
}

//判断当前用户是否已经登录,并根据具体登录情况执行后续动作
menuLoadingOptions.ifHasLogined = function (functionAfterLoginInfoLoaded) {
    var loginInfo = menuLoadingOptions.loginInfo;
    if (loginInfo.tenantId == null) {
        if (loginInfo.needLoad) {
            loginInfo.needLoad = false;
            $.ajax({
                url: "../eosOperator/hasLogined.do",
                type: "post",
                dataType: "json",
                success: function (data) {
                    if(data.data.userForm!=null){
                        loginInfo.userForm=data.data.userForm;
                    }
                    if(data.data.showVersionChange!=null){
                        loginInfo.showVersionChange=data.data.showVersionChange;
                    }
                    if(data.data.userTenant!=null){
                        loginInfo.userTenant=data.data.userTenant;
                    }
                    loginInfo.tenantName=data.data.tenantName;
                    loginInfo.tenantId=data.data.tenantId;
                    functionAfterLoginInfoLoaded();
                }
            });
            //loginInfo.needLoad = false;
        } else {
            var loadInterval = setInterval(function () {
                if (loginInfo.tenantId != null) {
                    clearInterval(loadInterval);
                    functionAfterLoginInfoLoaded();
                }
            }, 1);
        };
    } else {
        functionAfterLoginInfoLoaded();
    }
    ;
};

//加载企事业单位版权所有标识
menuLoadingOptions.loadAuthentication = function ($target) {
    $target.append(unescape("%3Cspan id='_ideConac' %3E%3C/span%3E%3Cscript src='http://dcs.conac.cn/js/33/000/0000/60451603/CA330000000604516030012.js' type='text/javascript'%3E%3C/script%3E"));
}


//加载门户页脚
menuLoadingOptions.loadFooter = function () {
    menuLoadingOptions.loadScript(function () {
        var $foot = $("#foot", menuLoadingOptions.$currentPage);
        if ($foot.length != 0) {
            $foot.append(menuLoadingOptions.$scriptPortalFooter);
            menuLoadingOptions.loadAuthentication($(".dw", $foot));
        }
    });
}


//加载简易页脚
menuLoadingOptions.loadSimpleFooter = function () {
    menuLoadingOptions.loadScript(function () {
        $("#simplefoot").append(menuLoadingOptions.$scriptSimpleFooter);
    })
}


//加载面包屑
menuLoadingOptions.loadCrumbs = function () {
    if (typeof getCrumbInfo == "function") {
        var crumbInfo = getCrumbInfo();
        var currentTrace = menuTraceArray[crumbInfo];
        var traceArray = new Array();
        while (currentTrace != null) {
            traceArray.push(currentTrace);
            currentTrace = menuTraceArray[currentTrace.parent];
        }
        var traceHTMLArray = new Array();
        var arrayLength = traceArray.length;
        for (var i = arrayLength - 1; i >= 0; i--) {
            traceHTMLArray.push(traceArray[i].trace);
        }
        $(".mainContent").prepend("<div id='Breadcrumbs'><ul class='breadcrumb'></ul></div>");
        $("#Breadcrumbs .breadcrumb").html(traceHTMLArray.join(""));

    }
}


//加载侧边悬挂栏
menuLoadingOptions.loadAside = function () {
    menuLoadingOptions.loadScript(function () {
        var $asideContainer = $(".asideContainer");
        if ($asideContainer.length > 0) {
            $asideContainer.append(menuLoadingOptions.$scriptAside);
        }

        $.getScript("/js/nav/jQuery.hhShare.min.js", function () {
            $('#asid_share').hhShare({
                cenBox: 'asid_share_box',  //里边的小层
                icon: 'adid_icon',
                addClass: 'red_bag',
                titleClass: 'asid_title',
                triangle: 'asid_share_triangle', //鼠标划过显示图层，边上的小三角
                showBox: 'asid_sha_layer' //鼠标划过显示图层
            });
        });

    })
}


//加载简易导航栏
menuLoadingOptions.loadSimpleNavi = function () {
    menuLoadingOptions.loadScript(function () {
        var $simpleNavBar = $("#simpleNavBar");
        if ($simpleNavBar.length != 0) {
            $simpleNavBar.html(menuLoadingOptions.$scriptSimpleNavi);
            if (typeof searchInfo == "function") {
                var $searchArea = $(".t_sera", $simpleNavBar);
                $searchArea.css("display", "block");
                $("#search-txt", $searchArea).keydown(function (event) {
                    if (event.keyCode == 13) {
                        $(".search-icon", $searchArea).click();
                    }
                });
                $(".search-icon", $searchArea).click(function () {
                    var info = $.trim($("#search-txt", $searchArea).val());
                    searchInfo({"searchInfo": info});
                });
            }
            //加载角色切换链接
            menuLoadingOptions.loadTopMenu();
            //加载登录链接及学时提醒
            menuLoadingOptions.loadLoginNav();
            $simpleNavBar.on("mouseenter", ".userAndRoles .headPic", function () {
                var $roleLink = $(".roleLink", "#simpleNavBar .userAndRoles");
                if ($roleLink.find("a").length > 0) {
                    //只有在已经加载链接的情况下才显示链接框
                    $(".roleLink", "#simpleNavBar .userAndRoles").show();
                }
            }).on("mouseleave", ".userAndRoles", function () {
                $(this).find(".roleLink").hide();
            });
        }
    });

}


//加载详细导航栏
menuLoadingOptions.loadDetailNavi = function () {
    menuLoadingOptions.loadScript(function () {
        //var $detailNavBar=$("#nav",menuLoadingOptions.$currentPage);
        var $detailNavBar = $("#navBar", menuLoadingOptions.$currentPage);
        if ($detailNavBar.length > 0) {
            $detailNavBar.append(menuLoadingOptions.$scriptPortalNavi);
            //加载角色切换链接
            menuLoadingOptions.loadTopMenu();
            //加载登录链接及学时提醒
            menuLoadingOptions.loadLoginNav();
        }
    });
}


function findMenu() {
    var parentmenu = "", parentMenuId = "", currentmenuchildarea = "", childmenu = "";
    if (typeof $.cookie != 'function') {
        return
    }
    if ($.cookie("parentmenu") != null) {
        parentmenu = $.cookie("parentmenu");
    }
    var $menuarea = $("#aside ul", $('#oldStyle'));
    var $menuarea1 = $("#aside ul", $('#newStyle'));
    if (parentmenu == '') {
        $.ajax({
            url: '../findMenu/getParentMenu.do',
            type: "POST",
            async: false,
            dataType: "text",
            success: function (data, evt) {
                var node = $.parseJSON(data);
                var parent_menu = "";
                $.each(node, function (i, value) {
                    parent_menu += "<li ><span class='" + node[i]._parentId + "'></span><a class='menu_" + node[i]._parentId + "' href='javascript:void(0);' onclick='javascript:showBufferMask({\"targetUrl\":\"" + node[i].resourceUrl + "\"});writeLocation(" + node[i]._parentId + ",0);'>" + node[i].resourceName + "</a></li>";
                })
                $.cookie("parentmenu", parent_menu);
                parentmenu = parent_menu;
            }
        })
    }
    $menuarea.html(parentmenu);
    $menuarea1.html(parentmenu);
}

/**
 *保存一级菜单和二级菜单的名称;
 *@author weixiaoyi
 *@version 2011.09.6
 */
function writeLocation(parentMenuId, childMenuId) {
    $.ajax({
        type: "POST",
        url: "../findMenu/setLocation.do",
        dataType: "text",
        data: "cId=" + childMenuId + "&pId=" + parentMenuId + "",
        success: function (data, evt) {
        }
    });
}

function getLocation() {
    $.ajax({
        type: "POST",
        url: "../findMenu/getLocation.do",
        dataType: 'text',
        success: function (data, evt) {
            var node = $.parseJSON(data);
            //var node = data.data;
            if (!(typeof showBufferMask === "function")) {
                $.getScript("/js/JSCommonTools.js", function (response, status) {
                });
            }
            var parentMenu = "", childMenu = "", parentUrl = "", childUrl = "", pId = "";
            $.each(node, function (i, value) {
                    if (node[i].pName != null) {
                        parentMenu = node[i].pName;
                        parentUrl = node[i].pUrl;
                        pId = node[i].pId;
                    } else {
                        childMenu = node[i].cName;
                        childUrl = node[i].cUrl;
                    }
                }
            )
            //alert($("#aside ul").find('.menu_'+pId).html());
            $("#aside ul", $('#oldStyle')).find('.menu_' + pId).css({
                'background': 'url(/image/student/menuon.png) repeat-x',
                'color': '#FFFFFF'
            });
            $("#aside ul", $('#newStyle')).find('.menu_' + pId).css({
                'background': 'url(/image/student/menuon.png) repeat-x',
                'color': '#FFFFFF'
            });
            if (parentMenu != "") {
                $("#trace", $('#oldStyle')).html("我的学习&nbsp;&nbsp;>&nbsp;&nbsp;" + parentMenu + "");
                $("#trace", $('#newStyle')).html("我的学习&nbsp;&nbsp;>&nbsp;&nbsp;" + parentMenu + "");
            }
        }
    });
}


$(document).ready(function () {

    //初始化信息
    menuLoadingOptions.initializeInfo();

    //加载用户导航栏
    menuLoadingOptions.loadDetailNavi();

    //加载简易导航栏信息
    menuLoadingOptions.loadSimpleNavi();

    //加载页脚
    menuLoadingOptions.loadFooter();

    //加载简易页脚
    menuLoadingOptions.loadSimpleFooter();

    menuLoadingOptions.loadCrumbs();

    //加载侧边悬浮窗
    menuLoadingOptions.loadAside();

    var LoginBinding = function () {
        if (!(typeof showLoginWin === "function")) {
            $.getScript("/js/login/login.js", function (response, status) {
            });
        }
    };

    if (typeof(JSCommonToolsArea) == "undefined") {
        $.getScript("/js/JSCommonTools.js", function () {
            LoginBinding();
        });
    }
    else {
        LoginBinding();
    }

    $.getScript("/js/UI/jquery.alerts.js", function () {
        $.alerts.dialogClass = "blueStyle";
    });


    findMenu();      //加载系统菜单
    getLocation();

    if (typeof $.cookie == "function") {
        if ($.cookie("myStudyFlag") == 1) {
            $("#nav #menu_home>li:eq(2)", $('#newStyle')).find("a").css("color", "#f38f00");
        }
    }

    $("._exitSystemCss").attr("href", "#");
    $("._exitSystemCss").on('click', function () {
            $.cookie("parentmenu", null);
            logonOutSystem();
        }
    );
    //$('.selectpicker').selectpicker({'selectedText': 'cat'});


    $.ajax({
        url: '../findMenu/findStudentMenuForRole.do',
        type: "POST",
        async: false,
        dataType: "text",
        success: function (data, evt) {
            var node = $.parseJSON(data);
            if (node.name != null && node.name != "") {
                operatorInfoOptions.logon = true;
                //$.cookie("myStudyFlag","1");
            } else {
                operatorInfoOptions.logon = false;
            }

        }
    })


});

/*
* load student interface top menu 
* by ffr
* 2012-09-13
*/

function logonOutSystem() {
    //先清空cookie
    $.cookie("parentmenu", "");
    $.cookie("myStudyFlag", "0");
    $.cookie('i', "0");   //培训班cookie
    //清空map及session

    $.ajax({
        url: '../exitSystem/logOut.do',
        type: "POST",
        dataType: "json",
        traditional: true,
        success: function (data) {
            if (data.result == "false") {
                jAlert("退出系统出现异常！", '错误提示');
            }
            var bufferCss = {"width": "200px", "height": "130px"};
            if (!(typeof showBufferMask === "function")) {
                $.getScript("/js/JSCommonTools.js", function (response, status) {
                    showBufferMask({
                        "bufferCss": bufferCss,
                        "targetUrl": data.url,
                        "background_img": "url(/image/buffer/goodBye.png)"
                    });
                });
            }
            else {
                showBufferMask({
                    "bufferCss": bufferCss,
                    "targetUrl": data.url,
                    "background_img": "url(/image/buffer/goodBye.png)"
                });
            }
        },
        error: function (data) {
            jAlert("退出系统出现异常！", '错误提示');
        }
    })

}

//加载登录用户的“我的学习”、“我的管理”链接
menuLoadingOptions.loadTopMenu = function () {
    menuLoadingOptions.ifHasLogined(function () {
        var loginInfo = menuLoadingOptions.loginInfo;
        var loginUserInfo = loginInfo.userForm;
        if (loginUserInfo != null) {
            //当前用户已经登录
            $.ajax({
                url: '../findMenu/findStudentMenuForRole.do',
                type: "POST",
                async: false,
                dataType: "text",
                success: function (data, evt) {
                    var node = $.parseJSON(data).data;
                    if ($("#top-bar", menuLoadingOptions.$currentPage).length > 0) {
                        var infoArray = new Array();
                        var greetingInfo = "中国科协智能学习平台！";
                        infoArray.push("<div id='customizeLink'><a></a></div>");
                        infoArray.push("您好，<span  class='intoZone' title='点击进入学员空间'>" + node.name + "</span>！欢迎登录");
                        infoArray.push(greetingInfo);
                        infoArray.push("【<a href='javascript:void(0);' onclick='javascript:logonOutSystem();' style='color:#333'>注销</a>】");
                        if (node.admin != null && node.admin != "") {
                            infoArray.push("【<a href='javascript:void(0);' style='color:#333' id='toAdmin'><input type='hidden' name='roleId'  value='" + node.adminRoleId + "'>我的管理</a>】");
                        }
                        if (node.stu != null && node.stu != "") {
                            infoArray.push("【<a href='javascript:void(0);' onclick='javascript:showBufferMask();openUrl(\"" + node.stu + "\");return false;' style='color:#333'>我的学习</a>】");
                        }
                        $("#top-bar", menuLoadingOptions.$currentPage).on("click", "#toAdmin", function () {
                            $(this).changeRole();
                        })
                        $("#top-bar", menuLoadingOptions.$currentPage).html(infoArray.join(""));
                        $("#head").on("click", "#top-bar .intoZone", function () {
                            openLink("interactionAction.do?method=intoUserZone", {
                                "operatorId": loginUserInfo.operatorId,
                                "itemType": 1
                            }, {"target": "_blank"});

                        });

                        if (loginInfo.showVersionChange == "true") {
                            $("#top-wrap").addClass("logined");
                            var $versionLink = $("#top-bar #customizeLink a");
                            if (loginInfo.userTenant == loginInfo.tenantId) {
                                //当前处于专业版
                                $versionLink.attr("title", "返回全院").html("返回全院").show();
                            }
                            else {
                                //当前处于普适版
                                $versionLink.attr("title", "前往本单位").html("前往本单位").show();
                            }
                            $versionLink.click(function () {
                                window.location.href = "stuLogonInfoAction.do?method=getCustomizeVersionInfo";
                            })
                        }
                        else {
                            $("#top-wrap").addClass("logined");
                        }
                    }
                    else {
                        $("#simpleNavBar .userAndRoles").removeClass("unlogonStatus");
                        var headPicAddr = loginUserInfo.headPic;
                        var gender = loginUserInfo.gender;
                        if (headPicAddr == null || $.trim(headPicAddr) == "") {
                            headPicAddr = "/image/headPic/male1.jpg";
                        }
                        $("#simpleNavBar .userAndRoles .headPic img").attr("src", headPicAddr);
                        $("#simpleNavBar .userAndRoles .operatorName").html(loginUserInfo.operatorName);
                        var myStudyInfo = new Array();
                        //myStudyInfo.push("<a href='javascript:void(0);' onclick='javascript:showBufferMask();openLink(\""+node.stu+"\");return false;'>我的学习</a>");
                        myStudyInfo.push("<input type='hidden' id='currentOperatorId' value='" + loginUserInfo.operatorId + "'/>");
                        $("#simpleNavBar .myStudy").html(myStudyInfo.join(""));
                        $("#simpleNavBar .userAndRoles").on("click", ".headPic", function () {
                            openLink("interactionAction.do?method=intoUserZone", {
                                "operatorId": loginUserInfo.operatorId,
                                "itemType": 1
                            });
                        });
                        var $roleLink = $("#simpleNavBar .userAndRoles").find(".roleLink");
                        $roleLink.on("click", "#toAdmin", function () {
                            $(this).changeRole();
                        })
                        var linksArray = new Array();
                        linksArray.push("<div class='bufferMask'></div>");
                        linksArray.push("<a href='javascript:void(0);' onclick='javascript:openLink(\"" + node.stu + "\")'>我的学习</a>");
                        if (node.admin != null && node.admin != "") {
                            linksArray.push("<a href='javascript:void(0);' id='toAdmin'><input type='hidden' name='roleId'  value='" + node.adminRoleId + "'>我的管理</a>");
                        }
                        linksArray.push("<a href='javascript:void(0);' onclick='javascript:logonOutSystem();'>注销</a>");
                        $roleLink.html(linksArray.join(""));

                    }

                }
            })

        }
        else {
            if ($("#top-bar", menuLoadingOptions.$currentPage).length > 0) {
                $("#top-bar", menuLoadingOptions.$currentPage).html("&nbsp;&nbsp;");
            }
            else {

            }

        }
    });

}


//对未登录学员,加载登录链接;对已登录学员加载当前学时提示
menuLoadingOptions.loadLoginNav = function () {
    menuLoadingOptions.ifHasLogined(function () {
        if ($(".logo", menuLoadingOptions.$currentPage).length > 0) {
            var TenantId = menuLoadingOptions.loginInfo.tenantId;
            var logoimg = "";
            var logonContent = "";
            var logonContentInfoArray = new Array();
            if (menuLoadingOptions.loginInfo.userForm != null) {
                //当前用户已经登录
                $.ajax({
                    url: '../trainingHourReminder/getTrainingHour.do',
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        var node = data.data;
                        var year = node.year;
                        var remainingTrainingTime = 0;
                        var remainingTrainingHours = 0;
                        var totalTime = 100;
                        if (node.remainingTrainingTime != null && node.remainingTrainingTime != "") {
                            remainingTrainingTime = node.remainingTrainingTime;
                        }
                        if (node.remainingTrainingHours != null && node.remainingTrainingHours != "") {
                            remainingTrainingHours = node.remainingTrainingHours;
                        }
                        if (node.totalTime != null && node.totalTime != "") {
                            totalTime = node.totalTime;
                        }

                        logonContentInfoArray.push(node.year);
                        logonContentInfoArray.push("年度您已完成");
                        logonContentInfoArray.push(totalTime);
                        if ($.trim(node.noNeed) == "true") {
                            logonContentInfoArray.push("</div>");
                        } else {
                            if ($.trim(remainingTrainingTime) == "0小时") {
                                logonContentInfoArray.push("，恭喜完成学习目标！</div>");
                            } else {
                                logonContentInfoArray.push("，再学");
                                logonContentInfoArray.push(remainingTrainingTime);
                                logonContentInfoArray.push("将达到100学时的目标！");
                            }
                        }
                        logonContent = logonContentInfoArray.join("");
                        $(".hand_con .rightfloat", menuLoadingOptions.$currentPage).html(logoimg + logonContent);
                    }
                });
            } else {
                //logonContentInfoArray.push("<a href='javascript:void(0);' class='logonAction'>登录</a><a href='javascript:void(0);' class='escienceLogin'><img src='./image/escienceLogin.png' /></a></div>");
                logonContentInfoArray.push("<a href='javascript:void(0);' class='logonAction'>登录</a></div>");
                logonContent = logonContentInfoArray.join("");
                $(".hand_con .rightfloat", menuLoadingOptions.$currentPage).html(logoimg + logonContent);
            }

        }
    });
}

function openUrl(url) {
    $.cookie("parentmenu", "");
    writeLocation(8005, 0);  //2:resource表的id ——我的培训班
    window.location.replace(url);
}


function openNavClickInTagetWindow(num, target) {
    var openTarget = null;
    var $mask = null;
    if (target == null) {
        $mask = showBufferMask();
    }
    else {
        openTarget = {"target": target};
    }
    var TenantId = menuLoadingOptions.loginInfo.tenantId;
    var url = "";
    var inner_outter_flag = 0;
    if (TenantId != 1000 && TenantId != 2001) //研究所
        inner_outter_flag = 1;
    if (num == 1) {
        url = "stuLogonAction.do";
        var obj = {};
        openLink(url, obj, openTarget);
        return false;
    }
    else if (num == -1) {
        if (operatorInfoOptions.logon == false) {
            jAlert("&nbsp;&nbsp;&nbsp;&nbsp;请您先登录学习平台！", '友情提示', function () {
                resetTargetUrlAfterLogon("eosorgTEmployeeAction.do?method=forupdateSelfInfo");
                $mask.remove();
                showLoginWin();
            });
        }
        else {
            url = "eosorgTEmployeeAction.do?method=forupdateSelfInfo";
            var obj = {}
            openLink(url, obj, openTarget);
        }
        return false;
    }
    else if (num == 2) {
        url = "mtMixTrainNewsAction.do";
        var obj = {
            method: "queryAllNews",
            newsType: "0",
            inner_outter_flag: inner_outter_flag
        }
        openLink(url, obj, openTarget);
        return false;
    }
    else if (num == 3) {
        url = "msgMessageInfoAction.do";
        var obj = {
            method: "queryNotice",
            inner_outter_flag: inner_outter_flag
        }
        openLink(url, obj, openTarget);
        return false;
    }
    else if (num == 4) {
        url = "mtMixTrainNewsAction.do";
        var obj = {
            method: "queryAllNews",
            policyAndProcess: "true",
            inner_outter_flag: inner_outter_flag
        }
        openLink(url, obj, openTarget);
        return false;
    }
    else if (num == 5) {
        url = "mtMixTrainScheduleListAction.do";
        var obj = {}
        openLink(url, obj, openTarget);
        return false;
    }
    else if (num == 6) {
        var CurrentMonth = new Date().getMonth() + 1;
        url = "selectTrainingListAction.do?method=intoFrame";
        var obj = {
            month: CurrentMonth
        }
        openLink(url, obj, openTarget);
        return false;
    }
    else if (num == 7) {
        url = "onlineStudy.do?method=intoFrame";
        var obj = {}
        openLink(url, obj, openTarget);
        return false;
    }
    else if (num == 8) {
        url = "teacherFrame.do?method=intoFrame";
        var obj = {}
        openLink(url, obj, openTarget);
        return false;
    }
    else if (num == 9) {
        url = "frmThreadListAction.do";
        var obj = {}
        openLink(url, obj, openTarget);
        return false;
    }
    else if (num == 11) {
        url = "/recommendSeries/intoSeriesFrame.do";
        var obj = {}
        openLink(url, obj, openTarget);
        return false;
    }
    else if (num == 10)
        url = "#";
    window.location.replace(url);

}

function openNavClick(num) {
    openNavClickInTagetWindow(num, null);
}


//点击二级菜单
function openNavClick_Sub(num) {
    var TenantId = menuLoadingOptions.loginInfo.tenantId;
    if (operatorInfoOptions.logon == false && (num == 22 || num == 41 || num == 42)) ;
    else {
        showBufferMask();
    }

    var url = "";
    var inner_outter_flag = 0;
    if (TenantId != 1000 && TenantId != 2001) //研究所
        inner_outter_flag = 1;
    //if(num==1)
    //url="stuLogonAction.do";
    //新闻通知
    if (num == 21) {
        //新闻
        url = "mtMixTrainNewsAction.do";
        var obj = {
            method: "queryAllNews",
            newsType: "0",
            inner_outter_flag: inner_outter_flag
        }
        openLink(url, obj);
        return false;

    }
    else if (num == 22) {
        //通知
        if (operatorInfoOptions.logon == false) {
            jAlert("&nbsp;&nbsp;&nbsp;&nbsp;请您先登录学习平台！", '友情提示', function () {
                resetTargetUrlAfterLogon("msgMessageInfoAction.do?method=queryNotice&inner_outter_flag=" + inner_outter_flag);
                showLoginWin();
            });
        }
        else {
            url = "msgMessageInfoAction.do"
            var obj = {
                method: "queryNotice",
                inner_outter_flag: inner_outter_flag
            }
            openLink(url, obj);
        }
        return false;
    }
    //学习资源
    else if (num == 91) {
        url = "onlineStudy.do?method=intoFrame";
        var obj = {
            resourceType: "21"
        }
        openLink(url, obj);
        return false;
    }
    else if (num == 92) {
        url = "onlineStudy.do?method=intoFrame";
        var obj = {
            resourceType: "22"
        }
        openLink(url, obj);
        return false;
    }
    else if (num == 93) {
        url = "onlineStudy.do?method=intoFrame";
        var obj = {
            resourceType: "23"
        }
        openLink(url, obj);
        return false;
    }
    else if (num == 94) {
        url = "onlineStudy.do?method=intoFrame";
        var obj = {
            resourceType: "24"
        }
        openLink(url, obj);
        return false;
    }

    //教师资源
    else if (num == 81) {
        //内聘
        url = "teacherFrame.do?method=intoFrame";
        var obj = {
            conditionString: "-t0"
        }
        openLink(url, obj);
        return false;
    }
    else if (num == 82) {
        //外聘
        url = "teacherFrame.do?method=intoFrame";
        var obj = {
            conditionString: "-t1"
        }
        openLink(url, obj);
        return false;
    }
    //培训项目
    else if (num == 61) {
        //培训项目
        var CurrentMonth = new Date().getMonth() + 1;
        url = "selectTrainingListAction.do?method=intoFrame";
        var obj = {
            month: CurrentMonth,
            cad_report: 5001
        }
        openLink(url, obj);
        return false;
    }
    else if (num == 62) {
        //学术报告
        var CurrentMonth = new Date().getMonth() + 1;
        url = "selectTrainingListAction.do?method=intoFrame";
        var obj = {
            month: CurrentMonth,
            cad_report: 5002
        }
        openLink(url, obj);
        return false;
    }
    //政策与工作流程
    else if (num == 41) {
        //政策
        if (operatorInfoOptions.logon == false) {
            jAlert("&nbsp;&nbsp;&nbsp;&nbsp;请您先登录学习平台！", '友情提示', function () {
                resetTargetUrlAfterLogon("mtMixTrainNewsAction.do?method=queryAllNews&newsType=1&inner_outter_flag=" + inner_outter_flag);
                showLoginWin();
            });
        }
        else {
            //url="mtMixTrainNewsAction.do?method=queryAllNews&newsType=1&inner_outter_flag="+inner_outter_flag;
            url = "mtMixTrainNewsAction.do";
            var obj = {
                method: "queryAllNews",
                newsType: "1",
                inner_outter_flag: inner_outter_flag
            }
            openLink(url, obj);
        }
        return false;
    }
    else if (num == 42) {

        //工作流程
        if (operatorInfoOptions.logon == false) {
            jAlert("&nbsp;&nbsp;&nbsp;&nbsp;请您先登录学习平台！", '友情提示', function () {
                resetTargetUrlAfterLogon("mtMixTrainNewsAction.do?method=queryAllNews&newsType=2&inner_outter_flag=" + inner_outter_flag);
                showLoginWin();
            });
        }
        else {
            //url="mtMixTrainNewsAction.do?method=queryAllNews&newsType=2&inner_outter_flag="+inner_outter_flag;
            url = "mtMixTrainNewsAction.do";
            var obj = {
                method: "queryAllNews",
                newsType: "2",
                inner_outter_flag: inner_outter_flag
            }
            openLink(url, obj);
        }

        return false;
    }
    window.location.replace(url);
}


function menuimgError(imgObj) {
    imgObj.src = "/image/headPic/male1.jpg";
}


function clickMyStudy() {
    $.ajax({
        url: '../findMenu/findStudentMenuForRole.do',
        type: "POST",
        async: false,
        dataType: "text",
        success: function (data, evt) {
            var node = $.parseJSON(data);
            if (node.name != null && node.name != "") {
                var url = node.stu;
                openUrl(url);
                $.cookie("myStudyFlag", "1");
            } else
                jAlert("请您先登录学习平台！", '友情提示', function () {

                });
        }
    })
}

function checkLogon(type) {
    var TenantId = menuLoadingOptions.loginInfo.tenantId;
    if (operatorInfoOptions.logon == false) {
        jAlert("&nbsp;&nbsp;&nbsp;&nbsp;请您先登录学习平台！", '友情提示', function () {
            var inner_outter_flag = 0;
            if (TenantId != 1000 && TenantId != 2001) //研究所
                inner_outter_flag = 1;
            resetTargetUrlAfterLogon("mtMixTrainNewsAction.do?method=queryAllNews&policyAndProcess=true&inner_outter_flag=" + inner_outter_flag);
            showLoginWin();
        });
    }
    else {
        openNavClick(type);
    }
}

/**
 * 搜索课件、培训班、新闻等
 * */
function searchAndFind1() {
    var hasSelect = false;
    var choice = "";
    var searchTxt = document.getElementById("search-txt");
    if (searchTxt.value == null || searchTxt.value == "") {
        jAlert("请输入搜索内容", '友情提示');
        return false;
    } else {
        showBufferMask({"targetUrl": encodeURI(encodeURI("advancedSearchListAction.do?keywords_=" + searchTxt.value + "&source_category=1,2,3"))});
    }
}

/**
 * 高级搜索
 * */
function advancedSearch() {
    var searchTxt = document.getElementById("search-txt");
    var url = "advancedSearchAction.do?method=forAdvancedSearch";
    if (searchTxt.value != null && searchTxt.value != "") {
        url = url + "&keyWords=" + searchTxt.value;
    }
    url = encodeURI(encodeURI(url));
    window.open(url);
}

/*
 *搜索课件、书籍或培训，搜索后记录到cookie中
 */
function searchAndFind() {
    var hasSelect = false;
    var choice = "";
    var searchTxt = document.getElementById("search-txt");
    var conditions = document.getElementsByName("condition");
    for (var i = 0; i < conditions.length; i++) {
        if (conditions[i].checked) {
            hasSelect = true;
            choice = conditions[i].value;
        }
    }
    if (hasSelect == false) {
        hasSelect = true;
        choice = $("#selectType").val();
    }
    if (searchTxt.value == null || $.trim(searchTxt.value).length == 0) {
        jAlert("请输入搜索内容", '友情提示');
        return false;
    }
    /*
        如果选中的某一个，
        首先将form表单的action填写，将form的input的name属性和value属性填写，并且提交查询表单
        其次，将查询选项和查询内容写入到cookie
    */
    if (hasSelect && choice != "") {
        switch (choice) {
            case "1" :
                $("#studentSearchForm").attr("action", "selectTrainingListAction.do");
                $("#studentSearchFormInput").attr("name", "trainName").attr("value", searchTxt.value);
                $("#studentSearchForm").submit();
                break;
            case "2" :
                $("#studentSearchForm").attr("action", "selectCourseListAction.do");
                $("#studentSearchFormInput").attr("name", "courseName").attr("value", searchTxt.value);
                $("#studentSearchForm").submit();
                break;
            case "3" :
                $("#studentSearchForm").attr("action", "rsmRcmbookReadListAction.do");
                $("#studentSearchFormInput").attr("name", "bookName").attr("value", searchTxt.value);
                $("#studentSearchForm").submit();
                break;
            default :
                jAlert("没有这个选项", '友情提示');
        }
    } else {
        jAlert("请选择搜索类别", '友情提示');
    }
}


function mouseoutFontColor(obj, currentSkinId) {
    if (currentSkinId == 5){
        obj.color = '#3a80c1';
    } else{
        obj.color = 'white';
    }
}


;(function ($) {
    $.extend({
        "loadSimpleNavi": function () {
            menuLoadingOptions.loadSimpleNavi();
        },

        "reloadLoginInfo": function () {
            //加载角色切换链接
            menuLoadingOptions.loadTopMenu();

            //加载登录链接及学时提醒
            menuLoadingOptions.loadLoginNav();
        }

    });


    $.fn.extend({
        changeRole: function (actionAfterChanged) {
            var $role = this;
            var params = {};
            var roleId = $("input[name=roleId]", $role).val();
            params.roleId = roleId;
            $.ajax({
                type: "POST",
                url: "../findMenu/changeRole.do",
                dataType: "json",
                data: params,
                success: function (data) {
                    if (data.status) {
                        if (typeof actionAfterChanged == "function") {
                            actionAfterChanged();
                        }
                        else {
                            window.location.href = data.link;
                        }
                    }
                }
            })
        }
    })
})(jQuery);

function checkedadmin() {
    if (operatorInfoOptions.logon == false) {
        jAlert("&nbsp;&nbsp;&nbsp;&nbsp;集体报名请登录后再点此处", '友情提示', function () {
            showLoginWin();
        });
    } else {
        if (document.getElementById("toAdmin")) {
            if (document.getElementById("toAdmin").innerText == "我的管理") {
                /*window.location.href ="javascript:openNavClick(7);"; */
                document.getElementById("toAdmin").click();
            } else {
                jAlert("对不起，您没有管理员角色！", '提示');
            }
        } else {
            jAlert("对不起，您没有管理员角色！", '提示');
        }
    }

}  
 