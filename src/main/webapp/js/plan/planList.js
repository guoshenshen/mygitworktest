function operationForPlan(trainPlanId,updateAble,deleteAble,implementAble,publishAble,reportStatus){
    this.trainPlanId=trainPlanId; 		//培训计划id
    this.updateAble=updateAble;   		//是否可以修改
    this.deleteAble=deleteAble;  		//培训计划是否可以删除
    this.implementAble=implementAble;	//培训计划是否可以实施
    this.publishAble=publishAble;		//培训计划可发布、撤销发布
    this.reportStatus=reportStatus;     //培训计划的报备状态
}



var alertInfoOption={
    alertTitle:'计划填报提示',
    confirmTitle:'',
    dialogStyle:'blueStyle'
}

var operationForPlan_Array=new Array();


function addOperationOnEachTrainForLowerPlan(){
    var currentYear=getCurrentYear();
    $("#contentbody .trainPlanList tr").each(function(index,element){
        if(index==0);//跳过表头
        else{
            var operation=new operationForPlan(null,true,true,true,true);
            operationForPlan_Array.push(operation);
            $(this).find(".trainItemInfo").each(function(index,element){
                var elementName=$(element).attr("id");
                operation[elementName]=$(element).val();
            })
            if(operation.reportStatus!=null&&operation.reportStatus=="1"){
                $(this).addClass("tooltip").addClass("invalid").attr("title","本计划已经撤销");
            }
        }
    });
}


//为培训计划列表上的每条培训计划添加操作
function addOperationOnEachTrain(planId){
    var currentYear=getCurrentYear();

    $("#contentbody #content_02 .trainPlanList tr").each(function(index,element){
        if(index==0);//跳过表头
        else{
            var operation=new operationForPlan(null,true,true,true,true);
            operationForPlan_Array.push(operation);
            $(this).find(".trainItemInfo").each(function(index,element){
                var elementName=$(element).attr("id");
                operation[elementName]=$(element).val();
            })

            if(planId!=null){
                if(operation.trainPlanId!=planId){
                    return;
                }
            }


            var reportName="";
            if(operation.reportStatus=="1"){
                reportName="取消撤销";
            }
            else{
                reportName="撤销";
            }

            //培训计划处于撤销状态,则培训计划不可以发布、实施
            if(operation.reportStatus!=null&&operation.reportStatus==1){
                operation.implementAble=false;
                operation.publishStatus="1091";
                operation.publishAble=false;
            }

            //暂存计划可以删除、可以修改，不可以发布、实施
            if(operation.temp!=null&&operation.temp==1){
                operation.implementAble=false;
                operation.publishAble=false;
            }
            //其他计划可以删除、可以修改，不可以发布、不可以实施
            else if(operation.trainLevel!=null&&operation.trainLevel==2){

                operation.implementAble=false;
                operation.publishAble=false;
            }
            else{

                if(operation.publishStatus=="1091"){
                    operation.implementAble=false;
                }

                //如果培训计划的上级机构已经进行了审批操作,则该计划不能修改,可以实施计划
                if(operation.approveAble=="false"){
                    operation.updateAble=false;
                }
                //如果培训计划为年度计划并且所属研究所已经审批通过，则该计划不得删除
                if(operation.ableDelete=="false"){
                    operation.deleteAble=false;
                }
                //如果培训计划为当前年及以前的年度计划,则该计划不得删除
                if(parseInt(operation.trainPlanYear)<=currentYear&&(operation.trainPlanType=="1161"||operation.trainPlanType==null||$.trim(operation.trainPlanType)=="")){
                    operation.deleteAble=false;
                    operation.updateAble=false;
                }
                //若计划未通过上级部门支持且该计划为年度计划
                if(operation.ableDelete=="true"&&operation.trainPlanType=="1161"){
                    operation.implementAble=false;
                    operation.publishAble=false;
                }

                //已实施的培训计划不得删除不得设置发布、未发布状态
                if(operation.implementStatus=="1063"){
                    operation.deleteAble=false;
                    operation.publishAble=false;
                    operation.updateAble=false;
                }

                //若计划的任何上级机构对计划进行了驳回修改、审批未通过操作,则该计划不得发布、不得实施
                if(operation.approveStatus=="1043"){
                    operation.publishAble=false;
                    operation.updateAble=false;
                    operation.implementAble=false;
                }
                if(operation.approveStatus=="1048"||operation.approveStatus=="1042"){
                    operation.publishAble=false;
                    operation.implementAble=false;
                }
                //培训计划的年度如果晚于当前年,则该计划不得实施
                if(operation.trainPlanYear==null||(parseInt(operation.trainPlanYear)>currentYear)){
                    operation.implementAble=false;
                }
            }

            if(operation.tenantId==1){
                operation.deleteAble=true;
                if(operation.publishStatus=="1092"){
                    operation.updateAble=false;
                    operation.implementAble=true;
                }
                else{
                    operation.updateAble=true;
                    operation.implementAble=false;
                }
            }


            if(operation.deleteAble==true){
                if($(this).find(".deleteTD #id_"+operation.trainPlanId).length==0){
                    var str="<input type='checkbox'  name='id' id='id_"+operation.trainPlanId+"' value='"+operation.trainPlanId+"' ></input>";
                    $(this).find(".deleteTD").append(str);
                }

            }
            if(operation.publishAble==true){
                var str="<a href='javascript:void(0);' id='pubstatus_"+operation.trainPlanId+"'>";
                if(operation.publishStatus=="1091"){
                    str+="<div>未发布</div>";
                }
                else{
                    str+="<div>已发布</div>";
                }
                str+="</a>";
                $(this).find(".publishStatusTD").empty().append(str);
                $(this).find("#pubstatus_"+operation.trainPlanId).click(function(){
                    updatePubStatus(operation.trainPlanId);
                })
            }
            if(operation.publishAble==false){
                var str="";
                if(operation.publishStatus=="1091"){
                    str="<div>未发布</div>";
                }
                else{
                    str="<div>已发布</div>";
                }
                $(this).find(".publishStatusTD").empty().append(str);
            }
            if(operation.updateAble==true){
                var str="<a id='update_"+operation.trainPlanId+"' href='javascript:void(0);' >修改</a>";
                if(parseInt(operation.trainPlanYear)==currentYear){
                    str+="&nbsp;&nbsp;<a href='javascript:void(0);'  id='report_"+operation.trainPlanId+"' >"+reportName+"</a>";
                }
                $(this).find(".operationTD").empty().append(str);
                $(this).find("#update_"+operation.trainPlanId).click(function(){
                    openLink("orgPlanAction.do?method=forupdate",{'id':operation.trainPlanId});
                })
                $(this).find("#report_"+operation.trainPlanId).click(function(){
                    if(operation.reportStatus==0){
                        reportTrainPlan(operation.trainPlanId);
                    }
                    else{
                        revokeReportTrainPlan(operation.trainPlanId);
                    }
                })
            }

            if(operation.reportStatus=="1"){
                $(this).find(".implmentStatusTD").empty().append("未实施");
            }

            if(operation.implementAble==true){
                var str="<a id='execute_"+operation.trainPlanId+"' href='javascript:void(0);'>实施计划</a>";
                if(parseInt(operation.trainPlanYear)==currentYear){

                    str+="&nbsp;&nbsp;<a id='report_"+operation.trainPlanId+"' href='javascript:void(0);'>"+reportName+"</a>";
                }
                $(this).find(".operationTD").empty().append(str);
                $(this).find("#report_"+operation.trainPlanId).click(function(){
                    if(operation.reportStatus==0){
                        reportTrainPlan(operation.trainPlanId);
                    }
                    else{
                        revokeReportTrainPlan(operation.trainPlanId);
                    }
                })
                $(this).find("#execute_"+operation.trainPlanId).click(function(){
                    if(operation.temp==1){
                        jAlert("计划处于暂缓状态不得实施","提示信息",function(){
                            return;
                        });
                    }
                    if(operation.publishStatus=="1091"){
                        jAlert("计划尚未发布不得实施","提示信息",function(){
                            return;
                        });
                    }
                    if(operation.approveStatus=="1043"||operation.approveStatus=="1048"){
                        jAlert("因上级机构未审批通过,本计划不得实施","提示信息",function(){
                            return;
                        });
                    }
                    transfertotrain(operation.trainPlanId,'','');
                    return false;
                })
            }
            if(operation.updateAble==false&&operation.implementAble==false){
                if(parseInt(operation.trainPlanYear)==currentYear){
                    var str="&nbsp;&nbsp;<a id='report_"+operation.trainPlanId+"' href='javascript:void(0);'>"+reportName+"</a>";
                    $(this).find(".operationTD").empty().append(str);
                    $(this).find("#report_"+operation.trainPlanId).click(function(){
                        if(operation.reportStatus==0){
                            reportTrainPlan(operation.trainPlanId);
                        }
                        else{
                            revokeReportTrainPlan(operation.trainPlanId);
                        }
                    })
                }
                else{
                    $(this).find(".operationTD").empty().html("--");
                }
            }
            $(this).removeClass("warn").removeClass("invalid");

            if(operation.temp=="1"){
                $(this).addClass("warn").attr("title","本计划处于暂存状态请及时完善并提交!");
            }
            if(operation.reportStatus!=null&&operation.reportStatus=="1"){
                $(this).addClass("tooltip").addClass("invalid").attr("title","本计划已经撤销");
            }

            if(operation.approveStatus=="1043"){
                $(this).addClass("warn").attr("title","本计划未通过上级部门审核请及时删除!");
            }
            if(operation.approveStatus=="1048"){
                $(this).addClass("warn").attr("title","本计划被驳回修改,请根据审批意见进行修改并提交!");
            }
            if($.trim($(this).attr("title"))==""){
                $(this).removeClass("tooltip");
            }
        }
    })
}


function addConditionForTrainToExport(){
    var pop=$("#exportOptions");
    var currentWindow=window.parent;
    currentWindow.$.blockUI({message:pop,
        css:{width:"400px",cursor:"default",left:(currentWindow.$left-400)/2-10,top:(currentWindow.$top-310)/2-10}
    });

    $("#exportOptions select[name=level] option").each(function(){
        if($(this).val()==1){
            $(this).attr("selected","selected");
        }
    })

    $("#exportOptions select[name=casSupport] option").each(function(){
        if($(this).val()==-1){
            $(this).attr("selected","selected");
        }
    })

    $("#exportOptions select[name=planType] option").each(function(){
        if($(this).val()==1161){
            $(this).attr("selected","selected");
        }
    })

    $("#exportOptions select[name=approveStatus] option").each(function(){
        if($(this).val()==1){
            $(this).attr("selected","selected");
        }
    })

    $("#exportOptions select[name=subOrgTrain] option").each(function(){
        if($(this).val()==1){
            $(this).attr("selected","selected");
        }
    })

    var currentYear=getCurrentYear();
    var $year=$("#exportOptions input[name=year]");
    $year.val(currentYear+1);

    //设置关闭操作
    pop.find("a.pop_close_btn").click(function(){
        currentWindow.$.unblockUI();
        $("#exportButtonGroup").addClass("showConditionOfExportTrains");
    });
}


function chooseSupportedPlan(){
    var pop_url="orgPlanAction.do?method=showCasSupportPlan";

    var pop=$("<div id='_pop_win'><h2>资助计划筛选"
        +"<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
        +"<iframe height='580' width='980' scrolling='auto' class='pop_iframe' src='"+pop_url+"'></iframe></div>"
        +"<div style='clear:both'></div>"
    );
    window.parent.$.blockUI({
        message: pop,
        css: {width:"980px",height:"620px",cursor:"default",left:(window.parent.$left - 980) /2-10 + 'px',top:(window.parent.$top-620) /2 -10+ 'px'}
    });

    pop.find("a.pop_close_btn").click(function(){
        window.parent.$.unblockUI();
    });
}







//切换培训计划的发布与未发布状态
function updatePubStatus(planId){
    var options={
        type:"POST",
        url:"orgPlanAction.do?method=updatePlanPubStatus",
        dataType:"json",
        data:{"planId":planId},
        success:function(data){
            if(data.result=="true"){
                $("#contentbody #content_02 .trainPlanList #pubstatus_"+planId+" div").html(data.info);
                $("#contentbody #content_02 .trainPlanList #pubstatus_"+planId).parents("tr").find("#publishStatus").val(data.code);
                addOperationOnEachTrain(planId);
            }
            else{
                jAlert("更新培训计划发布状态异常,请稍后尝试","信息提示");
            }
        },
        error:function(data){
            jAlert("更新培训计划发布状态异常,请稍后尝试","信息提示");
        }
    };
    $.ajax(options);

}


//实施某培训计划
function transfertotrain(trainplanid, trainplanname,sponsorType){
    jConfirm("您确定实施本培训计划吗？","提示信息",function(response){
        if(response){
            writeLocation(3,3004);
            var url="onlineTraining.do?method=forupdate";
            var data={"trainPlanID":trainplanid,"isPlaned":1,"trainWay":0,"executePlan":"true"};
            openLink(url,data);
        }
    })
//	if(confirm("确定实施此培训计划？")){
//		if(sponsorType==0){
//			document.getElementById("trainPlanID").value=trainplanid;
//			document.getElementById("trainName").value=trainplanname;
//			document.getElementById("transferform").submit();
//		}else{
//			window.location.href = "outsideTrainingFileAction.do?method=importExcelOrg&trainPlanId="+trainplanid;
//		}
//	}
    //路径发生变化
}


//删除培训计划前的检查
function checkBeforeDeleteTrainPlan(){
    if($(".trainPlanList .deleteTD input[type=checkbox]:checked").length==0){
        jAlert("请选择删除对象","信息提示");
    }
    else{
        jConfirm("您确认对选中培训计划进行删除操作吗？","提示信息",function(response){
            if(response){
                $("#content_02 #form3").attr("action","orgPlanAction.do?method=delete");
                $("#content_02 #form3").submit();
            }
        })
    }
}



//为资助计划添加排序功能
function sortCasSupportPlan(){
    $("body").on("click",".upArrow",function(){
        var $currentLine=$(this).parents(".casSupportTrainLine");
        var $prev=$currentLine.prev();
        if($prev.length>0){
            var currentInfo=$currentLine.html();
            var prevInfo=$prev.html();
            $prev.html(currentInfo);
            $currentLine.html(prevInfo);
        }
    })
    $("body").on("click",".downArrow",function(){
        var $currentLine=$(this).parents(".casSupportTrainLine");
        var $next=$currentLine.next();
        if($next.length>0){
            var currentInfo=$currentLine.html();
            var nextInfo=$next.html();
            $next.html(currentInfo);
            $currentLine.html(nextInfo);
        }
    })
}

//资助计划筛选确定
function finishCasSupportPlanSort(){
    var submitSortSeries="";
    $(".casSupportTrainLine input[name=trainPlanId]").each(function(){
        submitSortSeries+=$(this).val()+"#";
    })

    var param={"submitSortSeries":submitSortSeries};
    var options={
        type:"POST",
        url:"orgPlanAction.do?method=sortCasSupportPlan",
        data:{"submitSortSeries":submitSortSeries},
        dataType:"json",
        success:function(data){
            if(data.result=="true"){
                jAlert("系统已保存的资助项目优先级排位","系统提示");
            }
            else{
                jAlert(data.reason,"系统提示");
            }
        }
    };
    $.ajax(options);
}


//进入详细查询检索页面
function forDetailSearch(){
    var pop=$("#searchByDetail");
    window.parent.$.blockUI({message:pop,
        css:{width:"400px",cursor:"default",left:(window.parent.$left-400)/2-10,top:(window.parent.$top-380)/2-10}
    });

    //设置关闭操作
    pop.find("a.pop_close_btn").click(function(){
        window.parent.$.unblockUI();
    });
}

//进行培训计划精细检索
function detailSearch(){
    var formPara={};
    var url=$("#searchByDetail form").attr("action");
    var self=$("body");
    var target=$(window.parent.document.body);
    $("#searchByDetail input",target).each(function(index){
        var value=$(this).val();
        if($.trim(value)==""){
        }
        else{
            formPara[this.name]=$.trim(value);
        }
    })
    $("#searchByDetail select",target).each(function(index){
        var value=$(this).val();
        if($.trim(value)==""){
        }
        else{
            formPara[this.name]=value;
        }
    })
    var maskOption={"element":"#searchByDetail"};
    openLink(url,formPara,{},self);
    window.parent.$.unblockUI();
}

//显示培训计划的审核信息
function showSuperiorMessages(trainPlanId){
    $.ajax({
        type:"POST",
        url:"orgPlanAction.do?method=showSuperiorMessage",
        data:{"trainPlanId":trainPlanId},
        traditional:true,
        dataType:"json",
        success:function(data){
            //设置弹出窗体
            var pop=$("#superiorOpinion");
            $("#superiorOpinion tbody").empty();//important
            //装载数据
            for(i in data){
                var str="<tr><td >"+data[i].orgName+"</td><td >"+data[i].status+"</td><td>"+data[i].comment+"</td></tr>";
                $("#superiorOpinion tbody").append(str);
            }

            $.blockUI({message:pop,
                css:{width:"600px",height:"400px",cursor:"default",left:($left-600)/2-10,top:($top-400)/2-10}
            });

            //设置关闭操作
            pop.find("a.pop_close_btn").click(function(){
                $.unblockUI();
            });
        }

    });
}



function showPlanCheck(planId){
    var pop_url="planCheckAction.do?method=forcheck&id="+planId;
    if($("#backUrlDiv #backUrl").length==0){
        var pop=$("<div id='_pop_win'><h2>计划信息审批"
            +"<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            +"<iframe height='620' width='980' scrolling='auto' class='pop_iframe' src='"+pop_url+"'></iframe></div>"
            +"<div style='clear:both'></div>"
        );
        $.blockUI({
            message: pop,
            css: {width:"980px",height:"660px",cursor:"default",left:($left - 980) /2-10 + 'px',top:($top-660) /2 -10+ 'px'}
        });

        pop.find("a.pop_close_btn").click(function(){
            $.unblockUI();
        });
    }
    else{
        var url="planCheckAction.do?method=forcheck";
        var backUrl=$("#backUrlDiv #backUrl").val();
        var param={"id":planId,"backUrl":backUrl};
        openLink(url,param,{});
    }

}


//撤销计划
function reportTrainPlan(planId){
    var pop_url="tpReportAction.do?method=showTpReportForm&trainPlanId="+planId;
    var pop=$("<div id='_pop_win'><h2>计划撤销"
        +"<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
        +"<iframe id='reportPlanIFrame' name='reportPlanIFrame' height='260' width='380' scrolling='none' class='pop_iframe' src='"+pop_url+"'></iframe></div>"
        +"<div style='clear:both'></div>"
    );
    $.blockUI({
        message: pop,
        css: {width:"380px",height:"300px",cursor:"default",left:($left - 380) /2-10 + 'px',top:($top-300) /2 -10+ 'px'}
    });

    pop.find("a.pop_close_btn").click(function(){
        if($(window.frames["reportPlanIFrame"].document).find("#result").val()=="1"){
            var $currentLine=$("#contentbody #content_02 .trainPlanList #report_"+planId).parents("tr");
            $("#reportStatus",$currentLine).val(1);
            $("#publishStatus",$currentLine).val(1091);
            $("#implementStatus",$currentLine).val(1091);
            addOperationOnEachTrain(planId);
        }
        $.unblockUI();
    });
}

//取消撤销计划
function revokeReportTrainPlan(planId){
    jConfirm("确定撤销已撤销的培训计划","撤销计划撤销提示信息",function(){
        var submitForm=null;
        var options={
            url:"tpReportAction.do?method=removeTpReportRecord",
            type:"POST",
            dataType:"json",
            data:{"trainPlanId":planId},
            success:function(message){
                if(message.result=="true"){
                    jAlert("计划撤销撤销成功","撤销计划撤销提示信息");
                    var $currentLine=$("#contentbody #content_02 .trainPlanList #report_"+planId).parents("tr");
                    $("#reportStatus",$currentLine).val(0);
                    $("#implementStatus",$currentLine).val(1061);
                    $("#publishStatus",$currentLine).val(1091);
                    addOperationOnEachTrain(planId);
                    if(submitForm!=null){
                        submitForm.remove();
                    }
                }
                else{
                    jAlert("计划撤销失败","撤销计划提示信息");
                    if(submitForm!=null){
                        submitForm.remove();
                    }
                }
            },
            error:function(){
                jAlert("计划撤销失败","撤销计划提示信息");
                if(submitForm!=null){
                    submitForm.remove();
                }
            }
        };
        submitForm=ajaxOpenLink(options);
        submitForm.submit();
    })
}


//审批某下级机构计划
function approvePlan(planId){
    $("#contentbody form").attr("action","planCheckAction.do?method=forcheck&id="+planId).submit();

}



//查看培训计划基本信息
function showPlanDetail(planId){


    var pop_url="planCheckAction.do?method=planwithcheck&id="+planId;
    if($("#backUrlDiv #backUrl").length==0){
        var pop=$("<div id='_pop_win'><h2>计划信息查看"
            +"<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            +"<iframe height='620' width='980' scrolling='auto' class='pop_iframe' src='"+pop_url+"'></iframe></div>"
            +"<div style='clear:both'></div>"
        );
        $.blockUI({
            message: pop,
            css: {width:"980px",height:"660px",cursor:"default",left:($left - 980) /2-10 + 'px',top:($top-660) /2 -10+ 'px'}
        });

        pop.find("a.pop_close_btn").click(function(){
            $.unblockUI();
        });
    }
    else{
        var url="planCheckAction.do?method=planwithcheck";
        var backUrl=$("#backUrlDiv #backUrl").val();
        var param={"id":planId,"backUrl":backUrl};
        openLink(url,param,{});
    }


}

function simpleDetailInfo(){
    $(".trainNameInfo").each(function(){
        var trainName=$(this).html();
        if(trainName!=null&&trainName.length>15){
            trainName=$.trim(trainName).substr(0,12)+"..";
            $(this).html(trainName);
        }
    })

    $("p.checkInfo").each(function(){
        var checkInfo=$(this).html();
        $(this).attr("title",checkInfo);
        if(checkInfo!=null&&checkInfo.length>12){
            checkInfo=$.trim(checkInfo).substr(0,10)+"..";
            $(this).html(checkInfo);
        }
    })
    $("font.orgNameForTrain").each(function(){
        var orgName=$(this).html();
        if(orgName!=null&&orgName.length>15){
            orgName=$.trim(orgName).substr(0,12)+"..";
            $(this).html(orgName);
        }
    })
}


function generateDownloadTrainPath(){
    var target=window.parent.document.body;
    var args={};
    var maskOption={"element":"#exportOptions"};
    var bufferMask=window.parent.showBufferMask(maskOption);
    $("#exportOptions .exportOption",target).each(function(){
        args[this.name]=this.value;
    })

    var options={
        data:args,
        url:"planExportExcelFileAction.do?method=export",
        type:"POST",
        dataType:"json",
        success:function(data){
            if(data.result=="true"){
                $("#exportButtonGroup",target).removeClass("showConditionOfExportTrains");
                $("#exportOptions #doExportTrains",target).attr("href",data.downloadPath);
            }
            else{
                $("#exportOptions #exportInfo",target).empty().html("计划信息导出失败，请联系运维人员");
            }
            bufferMask.remove();
        },
        error:function(){
            $("#exportOptions #exportInfo",target).empty().append("计划信息导出失败，请联系运维人员");
            bufferMask.remove();
        }
    }

    $.ajax(options);
};


$(function(){

    //为提示框设置显示样式
    if($.alerts!=null){
        $.alerts.dialogClass=alertInfoOption.dialogStyle;
    }


    //为当前机构每条培训计划条目添加操作
    if($("#trainListOfCurrentOrg").length>0){
        addOperationOnEachTrain();
    }

    //为下级机构每条培训计划设施操作
    if($("#lowerPlanList").length>0){
        addOperationOnEachTrainForLowerPlan();
        showLowerPlan();
    }


    //新建计划按钮
    $("#buttonForNewPlan").click(function(){
        window.location.href="orgPlanAction.do?method=foradd";
    })

    //计划批量删除按钮
    $("#buttonForBatchDelete").click(function(){
        checkBeforeDeleteTrainPlan();
    })

    $("#detailSearchButton").click(function(){
        forDetailSearch();
    })

    $("#subDetailSearchButton").click(function(){
        forDetailSearch();
    })

    $("#searchByDetailButton").click(function(){
        detailSearch();
    })

    $("#searchSubByDetailButton").click(function(){
        detailSearch();
    })


    $("#casSupportButton").click(function(){
        finishCasSupportPlanSort();
    })

    $("#casSupportChooseButton").click(function(){
        chooseSupportedPlan();
    })



    $("#exportOptions #generateExportTrains").click(function(){
        generateDownloadTrainPath();
    })

    $("#exportTrainPlanButton").click(function(){
        //_exportFileToSeal(1);
        addConditionForTrainToExport();
    })


    try{
        $('.tooltip').tooltipster({
            theme: 'tooltipster-punk'
        });
    }catch(e){

    }




    simpleDetailInfo();

    sortCasSupportPlan();
})


//显示指定机构的培训计划
function showLowerPlan(){
    $("#contentbody .org").each(function(){
        $(this).click(function(){
            var orgId=$(this).find("input[name=orgId]").val();
            openLink("lowerPlanCheckListAction.do",{"orgId":orgId,"pageNo":1});
        })
    })

}


$(function(){


    if($(".planDetailFrame").length>0){
        var $planDetailModal=$('[data-remodal-id=planInfo]').remodal($.defaultRemodalOption());

        var planDetailModalInfo={
            currentPlanId:""
        };

        $(document).on('opening', '.planDetailFrame', function () {
            $(".planDetailFrame iframe").attr("src","planCheckAction.do?method=planwithcheck&id="+planDetailModalInfo.currentPlanId);
        });

        $(document).on('closed', '.planDetailFrame', function () {
            $(".planDetailFrame iframe").attr("src","");
        });

        ;$.extend({
            showPlanDetailForFrame:function(params){
                planDetailModalInfo.currentPlanId=params.planId;
                $planDetailModal.open();
            }
        })


        ;$.fn.extend({


            showPlanDetail:function(){
                planDetailModalInfo.currentPlanId=$(this).siblings("input[name=planId]").val();
                $planDetailModal.open();
            },



            //引用培训计划信息
            referencePlan:function(){
                var actionAfterGenerateTp=function(data){
                    if(data.status="true"){
                        var templateInfo=data.templateInfo;
                        $.ajax({
                            url:"orgPlanAction.do?method=addPlanByTemplate",
                            type:"POST",
                            data:{"templateId":templateInfo.id},
                            dataType:"json",
                            success:function(data){
                                var tp_info=data.tp_info;
                                var link="./orgPlanAction.do?method=foradd&tp_info="+tp_info;
                                $.Ntip({
                                    'content':"请点击链接<a href='javascript:void(0);' onclick=\"window.open('"+link+"')\" >填充计划信息</a>"
                                });
                            }
                        })
                    }
                    else{
                        var cause="计划引用失败";
                        if($.trim(data.cause)!=""){
                            cause+="，失败原因："+data.cause;
                        }
                        $.Ntip({
                            'content':data.cause
                        });
                    }
                };
                this.generatePlanTemplate(actionAfterGenerateTp);
            },

            //创建培训计划模板信息
            generatePlanTemplate:function(actionAfterGenerated){
                $.ajax({
                    url:"tpTemplateAction.do?method=saveTpTemplate",
                    type:"POST",
                    data:{"trainId":planDetailModalInfo.currentPlanId},
                    dataType:"json",
                    success:function(data){
                        if(typeof actionAfterGenerated=="function"){
                            actionAfterGenerated(data);
                        }
                        else{
                            if(data.status=="true"){
                                var info="创建成功,进入<a href='javascript:void(0);' onclick=\"window.open('./tpTemplateAction.do?method=list');\">模板管理服务可查看模板信息详情"
                                $.Ntip({
                                    'content':info
                                });
                            }
                            else{
                                var cause="计划模板创建失败";
                                if($.trim(data.cause)!=""){
                                    cause+="，失败原因："+data.cause;
                                }
                                $.Ntip({
                                    'content':data.cause
                                });
                            }
                        }
                    }
                })
            }
        })

        $("body").on("click",".trainPlanDetail",function(){
            $(this).showPlanDetail();
        });

        $("body").on("click","#planTemplateMaker",function(){
            $(this).generatePlanTemplate();
        })

        $("body").on("click","#planReference",function(){
            $(this).referencePlan();
        })

    }










    if($("#tpTemplateMain").length>0){

        var $planTemplateModal=$('[data-remodal-id=planTemplateInfo]').remodal($.defaultRemodalOption());

        var templateInfo={
            searchInfo:{
                startIndex:0,
                count:20
            },
            currentTemplateId:"",
            actionMode:0
        };

        $(document).on('opening', '.planTemplateFrame', function () {
            var url="tpTemplateAction.do?method=forEditTpTemplate";
            if(templateInfo.currentTemplateId!=null){
                url+="&templateId="+templateInfo.currentTemplateId;
            }
            if(templateInfo.actionMode!=null){
                url+="&mode="+templateInfo.actionMode
            }
            $(".planTemplateFrame iframe").attr("src",url);
        });
        $(document).on('closed', '.planTemplateFrame', function () {
            $(".planTemplateFrame iframe").attr("src","");
        });

        $.fn.extend({
            showPlanTemplateDetail:function(){
                templateInfo.currentTemplateId=$(this).siblings("input[name=templateIdInfo]").val();
                templateInfo.actionMode=0;
                $planTemplateModal.open();
            }

        })


        $.extend({

            makePlanByTemplate:function(){
                $.ajax({
                    url:"orgPlanAction.do?method=addPlanByTemplate",
                    type:"POST",
                    data:{"templateId":templateInfo.currentTemplateId},
                    dataType:"json",
                    success:function(data){
                        var tp_info=data.tp_info;
                        var link="./orgPlanAction.do?method=foradd&tp_info="+tp_info;
                        $.Ntip({
                            'content':"请点击链接<a href='javascript:void(0);' onclick=\"window.open('"+link+"')\" >填充计划信息</a>"
                        });
                    }
                })
            },

            saveTemplate:function(){
                $('#planTemplateIframe')[0].contentWindow.$.saveTemplate();
            },

            updatedTemplate:function(){
                var $currentRow=$("#planTemplateList #template_"+templateInfo.id);
                $(".templateName",$currentRow).html(templateInfo.templateName);
                $(".periodInfo",$currentRow).html(templateInfo.period);
                $(".orgName",$currentRow).html(templateInfo.orgName);
                $(".organizerName",$currentRow).html(templateInfo.organizerName);
                $planTemplateModal.close();
            },
            savedTemplate:function(templateInfo){
                window.parent.$.loadTemplatePagination();
                $planTemplateModal.close();
            },

            loadTemplatePagination:function(){
                var searchInfo=templateInfo.searchInfo;
                $.ajax({
                    method:"POST",
                    data:searchInfo,
                    url:"tpTemplateAction.do?method=getTemplateNum",
                    dataType:"json",
                    traditional:true,
                    success:function(data){
                        var tpTemplateLength=data.tpTemplateLength;
                        if(tpTemplateLength>0){
                            $(".pagination-admin").jqPaginator({
                                first: '<li><a href="javascript:;">首页</a></li>',
                                prev: '<li><a href="javascript:;">上一页</a></li>',
                                next: '<li><a href="javascript:;">下一页</a></li>',
                                last: '<li><a href="javascript:;">尾页</a></li>',
                                page: '<li><a href="javascript:;">{{page}}</a></li>',
                                totalCounts: tpTemplateLength,
                                visiblePages: 6,
                                currentPage: 1,
                                pageSize:searchInfo.count,
                                onPageChange: function (num, type) {
                                    searchInfo.startIndex=(num-1)*searchInfo.count;
                                    $.loadTemplateList(searchInfo);
                                }
                            });
                        }
                        else{
                            $("#planTemplateList .templateInfoRow").remove();
                            try{
                                $('.pagination-admin').jqPaginator('destroy');
                            }
                            catch(e){

                            }

                        }
                    }
                })

            },

            loadTemplateList:function(){
                $.ajax({
                    url:"tpTemplateAction.do?method=loadTemplateList",
                    type:"POST",
                    data:templateInfo.searchInfo,
                    dataType:"json",
                    success:function(data){
                        if(data.status=="true"){
                            var templateList=data.resultList;
                            var templateLength=templateList.length;
                            var templateArray=new Array();
                            for(var i=0;i<templateLength;i++){
                                var currentTemplate=templateList[i];
                                templateArray.push("<tr class='templateInfoRow' id='template_"+currentTemplate.id+"'>");
                                templateArray.push("<td><input type='checkbox' name='templateId' value='"+currentTemplate.id+"'></td>");
                                templateArray.push("<td>"+currentTemplate.trainTypeName+"</td>");
                                templateArray.push("<td class='templateName'><input type='hidden' name='templateIdInfo' value='"+currentTemplate.id+"'><a href='javascript:void(0);' class='templateInfo'>"+currentTemplate.templateName+"</a></td>");
                                var periodInfo="";
                                if($.trim(currentTemplate.period)!=""){
                                    periodInfo=currentTemplate.period;
                                }
                                templateArray.push("<td class='periodInfo'>"+periodInfo+"</td>");
                                templateArray.push("<td class='orgName'>"+currentTemplate.orgName+"</td>");
                                templateArray.push("<td class='organizerName'>"+currentTemplate.organizerName+"</td>");
                                templateArray.push("<td><a href='javascript:void(0);' class='forEditTemplate'>编辑</a>&nbsp;&nbsp;<a href='javascript:void(0);' class='forRemoveTemplate'>删除</a></td>");
                                templateArray.push("</tr>");
                            }
                            $("#planTemplateList .templateInfoRow").remove();
                            $("#planTemplateList").append(templateArray.join(""));
                        }
                        else{
                            $.Ntip({
                                'content':"模板信息加载失败"
                            });
                        }
                    }
                })
            }
        });

        $.loadTemplatePagination();

        $("#searchTemplate").click(function(){
            $conditionForm=$(this).parents("form");
            var info=templateInfo.searchInfo;
            $(".conditio-item",$conditionForm).each(function(index,that){
                info[that.name]=that.value;
                info.startIndex=0;
            })
            $.loadTemplatePagination();
        });

        $("body").on("click",".templateInfo",function(){
            $(this).showPlanTemplateDetail();
        });


        $("#planTemplateList input[name=selectAll]").allSelect("#planTemplateList input[name=templateId]");



        $("body").on("click","#makePlanByTemplate",function(){
            $.makePlanByTemplate();
        })

        $("body").on("click","#saveTemplate",function(){
            $.saveTemplate();
        })

        $("body").on("click",".forEditTemplate",function(){
            $(".templateInfo",$(this).parents("tr")).click();
        })

        $("body").on("click",".forRemoveTemplate",function(){
            $("input[name=templateId]",$(this).parents("tr")).click();
            $("#tpTemplateMain #batchDeleteTpTemplate").click()
        })

        //新建培训计划模板
        $("#tpTemplateMain #buttonForNewTemplate").click(function(){
            templateInfo.currentTemplateId=null;
            templateInfo.actionMode=1;
            $planTemplateModal.open();
        })

        $("#tpTemplateMain #batchDeleteTpTemplate").click(function(){
            var toDeleteArray=new Array();
            $("#planTemplateList input[name=templateId]").each(function(index,that){
                if($(that).prop("checked")){
                    toDeleteArray.push(that.value);
                }
            })

            if(toDeleteArray.length==0){
                $.Ntip({
                    'content':"您尚未选择任何培训模板"
                });
                return;
            }
            else{
                $.ajax({
                    url:"tpTemplateAction.do?method=deleteTpTemplate",
                    type:"POST",
                    traditional:true,
                    data:{"templateId":toDeleteArray},
                    dataType:"json",
                    success:function(data){
                        if(data.result=="true"){
                            $.Ntip({
                                'content':"模板删除成功",
                                'onClose':function(){
                                    $.loadTemplatePagination();
                                    $("#planTemplateList input[name=selectAll]").prop("checked", false);
                                }
                            });
                        }
                        else{
                            var cause="模板删除失败";
                            if(!$.trim(data.cause)==""){
                                cause=data.cause;
                            }
                            $.Ntip({
                                'content':cause
                            });
                        }

                    }

                })
            }
        })
    }


    //下级培训计划审批
    if($("#trainPlanApproveMain").length>0){

        var planApproveSearchInfo={
            searchInfo:{
                startIndex:0,
                count:20
            }
        };

        var planSearchInfo={

        }

        window.top.$.settingsForTreeFrameWindow({
            "opening":function(){
                if(window){
                    $("#treeWindowIframe",window.top.document).attr("src","planCheckAction.do?method=planwithcheck&id="+planSearchInfo.planId)
                }

            },
            "closed":function(){
                if(window){
                    $("#treeWindowIframe",window.top.document).attr("src","");
                }

            }
        })

        $.fn.extend({

            showPlanApproveDetail:function(param){
                planSearchInfo.planId=param.planId;
                window.parent.$.openTreeFrameWindow();
            }

        });

        $.extend({
            loadPlanApprovePagination:function(){
                var searchInfo=planApproveSearchInfo.searchInfo;
                var startIndex=searchInfo.startIndex;
                var count=searchInfo.count;
                var pageIndex=Math.ceil((startIndex+1)/count);
                $.ajax({
                    method:"POST",
                    data:searchInfo,
                    url:"lowerPlanCheckListAction.do?method=getPlanApproveNum",
                    dataType:"json",
                    traditional:true,
                    success:function(data){
                        var planApproveLength=data.planApproveLength;
                        $(".pagination-admin").siblings(".pagination-length").html("共"+planApproveLength+"项");
                        if(planApproveLength>0){
                            $(".pagination-admin").jqPaginator({
                                first: '<li><a href="javascript:;">首页</a></li>',
                                prev: '<li><a href="javascript:;">上一页</a></li>',
                                next: '<li><a href="javascript:;">下一页</a></li>',
                                last: '<li><a href="javascript:;">尾页</a></li>',
                                page: '<li><a href="javascript:;">{{page}}</a></li>',
                                totalCounts: planApproveLength,
                                visiblePages: 6,
                                currentPage: pageIndex,
                                pageSize:searchInfo.count,
                                onPageChange: function (num, type) {
                                    searchInfo.startIndex=(num-1)*searchInfo.count;
                                    $.loadPlanApproveList(searchInfo);

                                    $("#searchPlanApprove input[name=startIndex]").val(searchInfo.startIndex);
                                    $("#searchPlanApprove input[name=count]").val(searchInfo.count);
                                }
                            });
                        }
                        else{
                            $("#trainPlanApproveMain").removeClass("notShow");
                            $("#lowerPlanList .planInfoRow").remove();
                            try{
                                $('.pagination-admin').jqPaginator('destroy');
                            }
                            catch(e){
                            }
                            if(window.parent&&typeof window.parent.iframeResize=="function"){
                                window.parent.iframeResize();
                            }
                        }
                    }
                })
            },

            loadPlanApproveList:function(){
                $.ajax({
                    url:"lowerPlanCheckListAction.do?method=loadPlanApproveList",
                    type:"POST",
                    data:planApproveSearchInfo.searchInfo,
                    dataType:"json",
                    success:function(data){
                        if(data.status=="true"){
                            var planList=data.resultList;
                            var planListLength=planList.length;
                            var planArray=new Array();
                            for(var i=0;i<planListLength;i++){
                                var plan=planList[i];
                                planArray.push("<tr class='planInfoRow' id='plan_"+plan.id+"'>");
                                planArray.push("<td><input type='hidden' name='planId' value='"+plan.id+"'/>"+plan.planTypeName+"</td>");
                                planArray.push("<td class='trainName'><a href='javascript:void(0);'>"+plan.trainName+"</a></td>");
                                planArray.push("<td>"+plan.startTime+"</td>");
                                planArray.push("<td class='orgName'>"+plan.orgName+"</td>");
                                planArray.push("<td class='organizerName'>"+plan.organizerName+"</td>");
                                var approveList=plan.approveFormList;
                                var approveFormLength=approveList.length;
                                planArray.push("<td class='approveStatus'>");
                                for(var j=0;j<approveFormLength;j++){
                                    var approveItem=approveList[j];
                                    planArray.push("<a href='javascript:window.parent.showSuperiorMessages("+approveItem.approverId+")' style='cursor:pointer'>");
                                    var approveInfo=approveItem.orgName+"("+approveItem.approveStatusName+")";
                                    planArray.push("<p class='tooltip checkInfo approve_"+approveItem.approveStatus+"' title='"+approveInfo+"' >");
                                    planArray.push(approveInfo+" </p></a>");
                                }
                                planArray.push("</td>");
                                planArray.push("<td class='implStatus'>"+plan.implStatusName+"</td>");
                                if(plan.approveAble){
                                    planArray.push("<td><a href='javascript:void(0);' class='forApprove'>审批</a></td>");
                                }
                                else{
                                    planArray.push("<td>--</td>");
                                }
                                planArray.push("</tr>");
                            }
                            $("#lowerPlanList .planInfoRow").remove();
                            $("#lowerPlanList").append(planArray.join(""));
                        }
                        else{
                            $.Ntip({
                                'content':"培训审核信息加载失败"
                            });
                        }

                        $("#trainPlanApproveMain").removeClass("notShow");
                        if(window.parent&&typeof window.parent.iframeResize=="function"){
                            window.parent.iframeResize();
                        }
                    }
                })
            }

        });

        (function(){
            var searchInfo=planApproveSearchInfo.searchInfo;
            $(".conditionItem",$("#searchPlanApprove")).each(function(index,that){
                searchInfo[that.name]=that.value;
            })
            if($.trim(searchInfo.startIndex)==""){
                searchInfo.startIndex=0
            }
            else{
                searchInfo.startIndex=parseInt(searchInfo.startIndex);
            }
            if($.trim(searchInfo.count)==""){
                searchInfo.count=20
            }
            else{
                searchInfo.count=parseInt(searchInfo.count);
            }
            $.loadPlanApprovePagination();

        })();




        $("#searchPlan").click(function(){
            var searchInfo=planApproveSearchInfo.searchInfo;
            $(".conditionItem",$("#searchPlanApprove")).each(function(index,that){
                if(that.name!="startIndex"&&that.name!="count"){
                    searchInfo[that.name]=that.value;
                }

            })
            searchInfo.startIndex=0;
            $.loadPlanApprovePagination();
        })


        $("#lowerPlanList").on("click",".trainName",function(){
            var $tr=$(this).parents("tr");
            var planId=$("input[name=planId]",$tr).val();
            $(this).showPlanApproveDetail({
                "planId":planId
            });

        })

        $("#lowerPlanList").on("click",".forApprove",function(){
            var $tr=$(this).parents("tr");
            var planId=$("input[name=planId]",$tr).val();
            $("#contentbody #searchPlanApprove").attr("action","planCheckAction.do?method=forcheck&id="+planId).submit();
        })
    }



    //下级培训项目审批
    if($("#trainApproveMain").length>0){

        var trainApproveSearchInfo={
            searchInfo:{
                startIndex:0,
                count:20
            }
        };


        var trainSearchInfo={

        }

        window.top.$.settingsForTreeFrameWindow({
            "opening":function(){
                if(window){
                    $("#treeWindowIframe",window.top.document).attr("src","resourceApproveAction.do?method=getTrainCheckInfo&id="+trainSearchInfo.trainId)
                }

            },
            "closed":function(){
                if(window){
                    $("#treeWindowIframe",window.top.document).attr("src","");
                }

            }
        })

        $.fn.extend({

            showTrainApproveDetail:function(param){
                trainSearchInfo.trainId=param.trainId;
                window.parent.$.openTreeFrameWindow();
            }

        });

        $.extend({
            loadTrainApprovePagination:function(){
                var searchInfo=trainApproveSearchInfo.searchInfo;
                var startIndex=searchInfo.startIndex;
                var count=searchInfo.count;
                var pageIndex=Math.ceil((startIndex+1)/count);
                $.ajax({
                    method:"POST",
                    data:searchInfo,
                    url:"resourceApproveAction.do?method=getTrainApproveNum",
                    dataType:"json",
                    traditional:true,
                    success:function(data){
                        var trainApproveLength=data.trainApproveLength;
                        $(".pagination-admin").siblings(".pagination-length").html("共"+trainApproveLength+"项");
                        if(trainApproveLength>0){
                            $(".pagination-admin").jqPaginator({
                                first: '<li><a href="javascript:;">首页</a></li>',
                                prev: '<li><a href="javascript:;">上一页</a></li>',
                                next: '<li><a href="javascript:;">下一页</a></li>',
                                last: '<li><a href="javascript:;">尾页</a></li>',
                                page: '<li><a href="javascript:;">{{page}}</a></li>',
                                totalCounts: trainApproveLength,
                                visiblePages: 6,
                                currentPage:pageIndex,
                                pageSize:searchInfo.count,
                                onPageChange: function (num, type) {
                                    searchInfo.startIndex=(num-1)*searchInfo.count;
                                    $.loadTrainApproveList(searchInfo);

                                    $("#searchTrainApprove input[name=startIndex]").val(searchInfo.startIndex);
                                    $("#searchTrainApprove input[name=count]").val(searchInfo.count);
                                }
                            });
                        }
                        else{
                            $("#trainApproveMain").removeClass("notShow");
                            $("#lowerTrainList .trainInfoRow").remove();
                            try{
                                $('.pagination-admin').jqPaginator('destroy');
                            }
                            catch(e){
                            }
                            if(window.parent&&typeof window.parent.iframeResize=="function"){
                                window.parent.iframeResize();
                            }
                        }
                    }
                })
            },

            loadTrainApproveList:function(){
                $.ajax({
                    url:"resourceApproveAction.do?method=loadTrainApproveList",
                    type:"POST",
                    data:trainApproveSearchInfo.searchInfo,
                    dataType:"json",
                    success:function(data){
                        if(data.status=="true"){
                            var trainList=data.resultList;
                            var trainListLength=trainList.length;
                            var trainArray=new Array();
                            for(var i=0;i<trainListLength;i++){
                                var train=trainList[i];
                                trainArray.push("<tr class='trainInfoRow' id='train_"+train.trainId+"'>");
                                /*trainArray.push("<td><input type='hidden' name='trainId' value='"+train.trainId+"'/>"+train.itemTypeStr+"</td>");*/
                                trainArray.push("<td class='trainName'><input type='hidden' name='trainId' value='"+train.trainId+"'/><a href='javascript:void(0);'>"+train.trainName+"</a></td>");
                                trainArray.push("<td>"+train.startTimeStr+"</td>");
                                trainArray.push("<td class='orgName'>"+train.orgName+"</td>");
                                trainArray.push("<td class='organizerName'>"+train.organizerName+"</td>");
                                trainArray.push("<td class='approveStatus'>"+train.approveStatusName+"</td>");
                                trainArray.push("<td class='implStatus'>"+train.implStatus+"</td>");
                                trainArray.push("<td><a href='javascript:void(0);' class='forApprove'>审批</a></td>");
                                trainArray.push("</tr>");
                            }
                            $("#lowerTrainList .trainInfoRow").remove();
                            $("#lowerTrainList").append(trainArray.join(""));
                        }
                        else{
                            $.Ntip({
                                'content':"培训审核信息加载失败"
                            });
                        }

                        $("#trainApproveMain").removeClass("notShow");
                        if(window.parent&&typeof window.parent.iframeResize=="function"){
                            window.parent.iframeResize();
                        }
                    }
                })
            }
        });

        (function(){
            var searchInfo=trainApproveSearchInfo.searchInfo;
            $(".conditionItem",$("#searchTrainApprove")).each(function(index,that){
                searchInfo[that.name]=that.value;
            })
            if($.trim(searchInfo.startIndex)==""){
                searchInfo.startIndex=0
            }
            else{
                searchInfo.startIndex=parseInt(searchInfo.startIndex);
            }
            if($.trim(searchInfo.count)==""){
                searchInfo.count=20
            }
            else{
                searchInfo.count=parseInt(searchInfo.count);
            }
            $.loadTrainApprovePagination();
        })();




        $("#searchTrain").click(function(){
            var searchInfo=trainApproveSearchInfo.searchInfo;
            $(".conditionItem",$("#searchTrainApprove")).each(function(index,that){
                if(that.name!="startIndex"&&that.name!="count"){
                    searchInfo[that.name]=that.value;
                }

            })
            searchInfo.startIndex=0;
            $.loadTrainApprovePagination();
        })



        $("#lowerTrainList").on("click",".trainName",function(){
            var $tr=$(this).parents("tr");
            var trainId=$("input[name=trainId]",$tr).val();
            $(this).showTrainApproveDetail({
                "trainId":trainId
            });
        })

        $("#lowerTrainList").on("click",".forApprove",function(){
            var $train=$(this).parents("tr");
            var trainId=$("input[name=trainId]",$train).val();
            $("#contentbody #searchTrainApprove").attr("action","resourceApproveAction.do?method=forTrainCheck&id="+trainId).submit();

        })

    }


    if($("#trainListFrame").length>0){

        var  approveArrayForImpl=[1041,1044,1045,1046,1047];
        var  approveArrayForOperation=[1041,1042,1044,1045,1046,1047,1048];

        var trainSearchInfo={

        }

        var $trainAproveWindow=$("[data-remodal-id='trainAproveWindow']").remodal($.defaultRemodalOption());
        $(document).on("opening",".trainAproveWindow",function(){
            $("#trainAproveWindowIframe").attr("src","resourceApproveAction.do?method=getTrainCheckInfo&id="+trainSearchInfo.trainId)
        })

        $(document).on("closed",".trainAproveWindow",function(){
            $("#trainAproveWindowIframe").attr("src","");
        })
        var trainSearchInfo={

        }

        $.fn.extend({
            showTrainApproveDetail:function(param){
                trainSearchInfo.trainId=param.trainId;
                $trainAproveWindow.open();
            }

        });

        $("#trainList").on("click",".approveStatus",function(){
            var $tr=$(this).parents("tr");
            var trainId=$("input[name=trainId]",$tr).val();
            $(this).showTrainApproveDetail({
                "trainId":trainId
            });
        })


        $("#trainList tr.trainInfo").each(function(index,that){
            var trainId=$("input[name=trainId]",$(that)).val();
            var approveStatus=$("input[name=approveStatus]",$(that)).val();
            var pubstatus=$("input[name=pubstatus]",$(that)).val();
            if($.trim(approveStatus)==""){
                approveStatus=1041;
            }
            else{
                approveStatus=parseInt(approveStatus);
            }
            if($.inArray(approveStatus,approveArrayForImpl)>=0){
                //该培训项目可以实施
                var implInfo=$(".implStatus",$(that)).html();

                $(".implStatus",$(that)).html("<a href='javascript:void(0);'>"+implInfo+"</a>");
                if(approveStatus==1041){
                    $(".approveStatus",$(that)).html("<a href='javascript:void(0)'>未上报</a>");
                }
                else{
                    $(".approveStatus",$(that)).html("<a href='javascript:void(0)'>审批通过</a>");
                }

            }

            if($.inArray(approveStatus,approveArrayForOperation)>=0){
                var operationArray=new Array();
                operationArray.push("<p><a id='operatorCancelIconCookie' href='onlineTraining.do?method=forupdate&id="+trainId+"'>管理</a></p>");
                if(pubstatus=="1092"){
                    operationArray.push("<p><a class='pubstatusLink' style='cursor:pointer;'>已发布</a></p>");
                }
                else{
                    if (approveStatus==1048) { //approveStatus 1048驳回修改
                        operationArray.push("<p title='驳回修改不允许发布'>未发布</p>");
                    } else {
                        operationArray.push("<p><a class='pubstatusLink' style='cursor:pointer;'>未发布</a></p>");
                    }
                }
                operationArray.push("<p><a target='_blank' style='cursor:pointer;' href='onlineTraining.do?method=viewTrain4Admin&trainId="+trainId+"'>预览</a></p>");
                operationArray.push("<p><a style='cursor:pointer;' href='javascript:knowledgeSelect(\""+trainId+"\")'>关联知识点</a></p>");

                $(".operation",$(that)).html(operationArray.join(""));
            }
        })


        $("#trainList tr.trainInfo")
            .on("click","td.implStatus",function(){
                var $currentTd=$(this);
                var trainId=$("input[name=trainId]",$currentTd.parents("tr")).val();
                $.ajax({
                    type:"POST",
                    url:"onlineTraining.do?method=changeImplstatus",
                    data:{"trainID": trainId},
                    dataType:"json",
                    success:function(data){
                        var response=data.result;
                        if(response=='true'){
                            $("a",$currentTd).empty().html(data.status);
                        }
                        else{
                            alert(data.cause);
                            return;
                        }
                    },
                    error:function(){
                        $.tips("实施状态切换异常,请稍后重试!");
                    }
                })
            }).on("click",".pubstatusLink",function(){
            //切换培训项目的发布状态
            var $Link=$(this);
            var trainId=$("input[name=trainId]",$Link.parents("tr")).val();
            $.ajax({
                type:"POST",
                url:"onlineTraining.do?method=updatePubStatus ",
                data:{"trainID": trainId},
                dataType:"json",
                success:function(data){
                    if(data.status){
                        var pubstatus=data.pubStatus;
                        if(pubstatus=="1091"){
                            $Link.html("未发布");
                        }
                        else{
                            $Link.html("已发布");
                        }
                    }
                    else{
                        $.tips("发布状态切换异常,请稍后重试!");
                        return;
                    }
                },
                error:function(){
                    $.tips("发布状态切换异常,请稍后重试!");
                }
            })
        });

        if($(".trainDetailFrame").length>0){
            var $trainDetailModal=$('[data-remodal-id=trainDetailFrame]').remodal($.defaultRemodalOption());

            var trainDetailModalInfo={
                currentPlanId:""
            };

            $(document).on('opening', '.trainDetailFrame', function () {
                $(".trainDetailFrame iframe").attr("src","../onlineTraining/fordetail.do?mode=copy&trainId="+trainDetailModalInfo.currentTrainId);
            });

            $(document).on('closed', '.trainDetailFrame', function () {
                $(".planDetailFrame iframe").attr("src","");
            });

            $.fn.extend({
                showTrainDetail:function(){
                    trainDetailModalInfo.currentTrainId=$("input[name='selectbox']",$(this)).val();
                    $trainDetailModal.open();
                },

                //引用培训计划信息
                referenceTrain:function(){
                    var link="onlineTraining/foradd.do?trainId="+trainDetailModalInfo.currentTrainId;
                    $.Ntip({
                        'content':"请点击链接<a href='javascript:void(0);' onclick=\"window.open('"+link+"')\" >填充培训信息</a>"
                    });
                },
            });

            $("body #trainList").on("click",".trainName",function(){
                $(this).showTrainDetail();
            });
            $("body").on("click","#trainReference",function(){
                $(this).referenceTrain();
            });
        }


    }
});


