var structureInfo = "";

function getCookie(c_name) {

    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=")
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1
            c_end = document.cookie.indexOf(";", c_start)
            if (c_end == -1) c_end = document.cookie.length
            return unescape(document.cookie.substring(c_start, c_end))
        }
    }
    return ""
}

function structure() {
    var trainName = "培训项目";
    if (getCookie("trainName") != null) {
        trainName = getCookie("trainName");
    }

    if (structureInfo != "") {
        alert('123');
    } else {
        var structureArray = new Array();
        structureArray.push('<div>');
        structureArray.push('<!-- 管理员页眉 -->');
        structureArray.push('<div class="topbody">')
        structureArray.push('<div class="headerContent content">');
        structureArray.push('<div class="purple"></div>');
        structureArray.push('<div class="blue"></div>');
        structureArray.push('<div class="green"></div>');
        structureArray.push('<div class="pink"></div>');
        structureArray.push('<div class="top-wrap">');
        structureArray.push('<span style="width:50px;">培训年度</span>');
        structureArray.push('<span class="year" style="width:30px;margin-left: 5px;">2xxx</span>');
        structureArray.push('<i class="fa fa-user" style="width: 20px;"></i>');
        structureArray.push('<span style="width: 50px;">当前用户</span>');
        structureArray.push('<a class="operatorName" style="max-width: 100px;min-width:60px;margin-left:5px" href="javascript:void(0);">&nbsp;&nbsp;</a>');
        structureArray.push('<span style="width: 50px;margin-left: 50px;">当前角色</span>');
        structureArray.push('<span class="roleLink" style="margin-left:5px;position:relative;color: #ffc600;cursor:pointer;max-width: 150px;min-width:60px;" title="切换角色"></span>');
        structureArray.push('<a  href="javascript:void(0);" id="exit">退出登录</a>');
        structureArray.push('</div>');
        structureArray.push('<div class="logo">');
        structureArray.push('<img src="/image/skin/adminLogo.png" />');
        structureArray.push('<img src="/image/skin/adminLogoTitle.png" />');
        structureArray.push('</div>');
        structureArray.push('<div class="navbar"></div>');
        structureArray.push('</div>');
        structureArray.push('</div>');


        structureArray.push('<!-- 管理员培训应用-->');
        structureArray.push('<div class="trainApp">');
        structureArray.push('<div id="conTop">');
        structureArray.push('<span class="funcTitle" style="cursor:pointer;" onclick="window.location.href=\'../onlineTraining/fordetail.do\'">' + trainName + '</span>');
        structureArray.push('<div id="funcCheck">');
        structureArray.push('<div id="selectBox">');
        structureArray.push('<div id="selectImg">');
        structureArray.push('<div id="selectText">功能管理</div>');
        structureArray.push('</div>');
        structureArray.push('<ul id="selectMenu">');
        structureArray.push('</ul>');
        structureArray.push('</div>');
        structureArray.push('</div>');
        structureArray.push('</div>');
        structureArray.push('<div id="conBottom">');
        structureArray.push('<img class="barL" src="/image/arrows4l.png"/>');
        structureArray.push('<div class="thirdMenu">');
        structureArray.push('<ul>');
        structureArray.push('</ul>');
        structureArray.push('</div>');
        structureArray.push('<img class="barR" src="/image/arrows4r.png"/>');
        structureArray.push('<div style="clear:both;"></div>');
        structureArray.push('<div class="scrollBarWapper">');
        structureArray.push('<div class="scrollBar">');
        structureArray.push(' <div class="barM">');
        structureArray.push('<div class="bar">');
        structureArray.push('<div class="l"></div>');
        structureArray.push(' <div class="r"></div>');
        structureArray.push('</div>');
        structureArray.push('</div>');
        structureArray.push('</div>');
        structureArray.push('</div>');
        structureArray.push('</div>');
        structureArray.push('</div>');


        structureArray.push('<!-- 管理员页脚-->');
        structureArray.push('<div class="bottombody">');
        structureArray.push('<div class="content" id="copyright"><span中国科学技术协会培训和人才服务中心</span>&nbsp;&nbsp;<span>版权所有</span>&nbsp;&nbsp;<span>京ICP备09112257号</span></div>');
        structureArray.push('<div class="content" id="support"><span>技术支持：中国科学院计算机网络信息中心</span><span>技术：010-588137(07/10/15)</span><span>邮箱：train@cnic.cn</span> </div>');
        structureArray.push('<div class="leftBackground"></div>');
        structureArray.push('<div class="rightBackground"></div>');
        structureArray.push('</div>');
        structureArray.push('</div>');

        structureArray.push('</div>');


        structureInfo = structureArray.join("");
    }
    return structureInfo;

}