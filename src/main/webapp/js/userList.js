
var batchOperation=[
                    {'title':'是否有学时要求','argumentName':'isCtnEduRequire','html':"<select name='isCtnEduRequire' class='argumentInfo'><option value='1'>是</option><option value='0'>否</option></select>"}
                    ,{'title':'职称','argumentName':'title','html':"<input class='argumentInfo' type='text' name='title'></input>"}
                    ,{'title':'职务','argumentName':'job','html':"<input class='argumentInfo' type='text' name='job'></input>"}
                    ,{'title':'入职时间','argumentName':'regDate','html':"<input name='regDate'  class='Wdate argumentInfo' style='height: 20px;' type='text' onclick='WdatePicker({readOnly:true})' value='' readonly=''>"}
                    ,{'title':'学员当前状态是否有效','argumentName':'status','html':"<select name='status' class='argumentInfo'><option value='1'>是</option><option value='0'>否</option></select>"}
                    ,{'title':'机构调整','argumentName':'orgId','html':"<div style='width:100%;height:100%;'><div class='effectArea clearBefore argumentInfo' id='orgId' style='margin:0 auto;width:100px;'><a href='javascript:void(0);' class='btn-orange-l singleOrgSelect' ><span class='btn-orange-r'>机构选择</span></a><div class='hiddenArea'></div></div></div>"}
                   ];
	
var batchUserList=[];

var canBatchUpdate=true;

var $popWin=null;

window.REMODAL_GLOBALS = {
		  NAMESPACE: 'modal',
		  DEFAULTS: {
		    hashTracking: false
		  }
		};

function initialUserListTool(){
	console.log("###");
    if($("#batchOperationWin").length==0){
        createBatchOperationModal();
    }

    $(document).on("closed","[data-remodal-id='batchOperationWin']",function(){
        if($(window.frames["orgUserMain"].document).length>0){
            $(window.frames["orgUserMain"].document).find("#form3").submit();
        }
        $("tr",$("#batchOperationWin .popWinTable")).each(function(index,that){
            if(index>0){
                $(that).remove();
            }
        })

        $("#batchOperationWin #messageInfo").empty().html("");
        $("#batchOperationWin input[name=operatorId]").remove();
    });

    $(document).on("closed","[data-remodal-id='modal_orgSelectWindow']",function(){
        $popWin.open();
    });
}


function createBatchOperationModal(){
		var htmlArray=new Array();
	htmlArray.push('<div class="remodal" data-remodal-id="batchOperationWin" id="batchOperationWin" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc">');
    htmlArray.push('<button data-remodal-action="close" class="remodal-close" ></button>');
    htmlArray.push('<div id="_pop_win">');
    htmlArray.push('<div class="box" style="background-color:#d2d2d2">');
    htmlArray.push('<div class="btn-wrapper"></div>');
    htmlArray.push('</div>');
    htmlArray.push('<form id="batchUpdateForm" accept-charset="UTF-8">');
    htmlArray.push('<table class="popWinTable" accept-charset="UTF-8">');
    htmlArray.push('<tr><td width="40%">信息名称</td><td width="20%">是否修改</td><td width="40%">修改信息</td></tr>');
    htmlArray.push('</table>');
    htmlArray.push('</form>');
    htmlArray.push('<div class="btnContainer"><a href="javascript:void(0);" class="btn-orange-l" id="sureToReset"><span class="btn-orange-r">确&nbsp;&nbsp;定</span></a></div><br/><div id="messageInfo"></div>');
    htmlArray.push('</div>');
    htmlArray.push('</div>');
    $("body").append(htmlArray.join(""));
}


function generateBatchOperationWin(){
	$popWin=$("[data-remodal-id='batchOperationWin']").remodal({});
	$popWin.open();
	var $operationTable=$("#batchOperationWin .popWinTable");
	var $operationForm=$("#batchOperationWin #batchUpdateForm");
	for(var i in batchOperation){
		var operation="<tr><th>"+batchOperation[i].title+"</th><td class='ifReset'><input class='radio' type='radio' name='"+batchOperation[i].argumentName+"_radio' value='0' checked>否</input><input type='radio' class='radio' name='"+batchOperation[i].argumentName+"_radio' value='1'>是</input></td><td >"+batchOperation[i].html+"</td></tr>";
		$operationTable.append(operation);
	}
	
	var operatorIdStr=""
	for(var i in batchUserList){
		operatorIdStr+="<input type='hidden' name='operatorId' value='"+batchUserList[i]+" '/>";
	}
	$operationForm.append(operatorIdStr);
	
	setResetOption();
	
	
	var ajaxOptions={
			url:'eosorgTEmployeeAction.do?method=batchUpdate',
			method:'POST',
			dataType:'json',
			success:function(resultInfo){
				if(resultInfo.result=="true"){
					$("#batchOperationWin #messageInfo").empty().html("信息批量更新成功");
					
				}
				else{
					$("#batchOperationWin #messageInfo").empty().html(resultInfo.message);
				}
				canBatchUpdate=true;
			},
			error:function(){
				canBatchUpdate=true;
			}
	}
	
	$("#batchUpdateForm").submit(function(){
		$(this).ajaxSubmit(ajaxOptions);
		return false;
	});
	
	
	$("#sureToReset").on("click",function(){
		batchUpdate();
	});
	
}


function batchUpdate(){
	
	if(canBatchUpdate){
		canBatchUpdate=false;
		$("#batchOperationWin .ifReset input[type=radio]:checked").each(function(){
			var $operationControl=$(this).parents("tr").find(".argumentInfo");
			if($(this).val()=="1"){
				$operationControl.removeAttr('disabled');
				//options[$operationControll.attr('name')]=$operationControl.val();
			}
			else{
				$operationControl.attr('disabled','disabled');
				$(".hiddenArea",$operationControl).empty();
			}
			
		});
		
		$("#batchUpdateForm").submit();
	}
	
} 


function setResetOption(){
	$("#batchOperationWin .ifReset input[type=radio]").on("click",function(){
		var $operationControl=$(this).parents("tr").find(".argumentInfo");
		$operationControl.removeAttr('disabled');
		if($(this).val()=='0'){
			$operationControl.hide();
		}
		else{
			$operationControl.show();
		}
	})
}

	




