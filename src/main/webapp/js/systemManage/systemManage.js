




$(function(){



    if($("#editRole").length>0){
        var canclick=true;
        $("#orgsid").loadDefaultOrgList();

        $("#roleForm").on("click",".goback",function(){
            window.location.href='/system/roleFrame.do';
        })
        .on("click",".submit",function(){
            $("#roleForm").submit();
        })
        .submit(function(){
            if(canclick){
                canclick=false;
                $(this).ajaxSubmit({
                    traditional:true,
                    url:"/role/addRole.do",
                    type:"POST",
                    dataType:"json",
                    success:function(data){
                        canclick=true;
                        var content="";
                        if(data.status){
                            content="修改角色成功!";
                        }
                        else{
                            if($.trim(data.msg)!=""){
                                content=$.trim(data.msg);
                            }
                            else{
                                cotent="修改角色失败!";
                            }
                        }
                        $.Ntip({
                            'content':content,
                            'onClose':function(){
                                if(data.status){
                                    $(".goback").click();
                                }
                            }
                        })
                    },
                    error:function(){
                        canclick=true;
                    }
                });
            }
            return false;
        })

    }

    //角色管理页面
    if($("#roleFrame").length>0){

        var canclick=true;

        var currentOperatorId=$("#currentOperatorId").val();

        //分页
        var paginationConfig=$.getPaginationConfig();
        paginationConfig.getUrlForPagination=function(){return "/role/listRoles.do"};
        paginationConfig.actionForClearLoadedData=function(){
            $("#userList .infoRow").remove();
        };
        paginationConfig.actionForErrorLoadingData=function(){
            if(window.parent&&typeof window.parent.iframeResize=="function"){
                window.parent.iframeResize();
            }
            $.Ntip({
                'content':"人员信息加载失败"
            });
        };
        paginationConfig. actionForSucessLoadingData=function(data){
            var dataList=data.list;
            var dataLength=dataList.length;
            var htmlArray=new Array();
            for(var i=0;i<dataLength;i++){
                var currentItem=dataList[i];
                htmlArray.push("<tr class='infoRow'>");
                if(currentOperatorId==currentItem.creatorId){
                    htmlArray.push("<td><input type='checkbox' value='"+currentItem.id+"' name='roleId' /></td>");
                }
                else{
                    htmlArray.push("<td><input type='hidden' value='"+currentItem.id+"' name='roleId' /></td>");
                }
                if(currentOperatorId==currentItem.creatorId) {
                    htmlArray.push("<td><a href='javascript:void(0);' class='operateRole'>" + currentItem.roleName + "</td>");
                }
                else{
                    htmlArray.push("<td>"+currentItem.roleName+"</td>");
                }
                htmlArray.push("<td>"+currentItem.orgsname+"</td>");
                htmlArray.push("<td>"+currentItem.createTime+"</td>");
                htmlArray.push("<td>"+currentItem.comment+"</td>");
                htmlArray.push("<td>")
                if(currentOperatorId==currentItem.creatorId){
                    htmlArray.push("<a href='javascript:void(0);' class='operateResource'>权限管理</a>");
                }
                else{
                    htmlArray.push("<a href='javascript:void(0);' class='seeResource'>权限查看</a>");
                }
                htmlArray.push("</td>");
                htmlArray.push("</tr>");
            }
            $("#roleList .infoRow").remove();
            $("#roleList").append(htmlArray.join(""));
            if(window.parent&&typeof window.parent.iframeResize=="function"){
                window.parent.iframeResize();
            }

        };
        paginationConfig.actionForLoadingPagination();


        $("#roleFrame")
            .on("click","#searchRole",function(){
                var roleName=$(this).siblings("input[name=roleName]").val();
                paginationConfig.searchInfo.roleName=$.trim(roleName);
                paginationConfig.actionForLoadingPagination();
            }).on("click",".addRole",function(){
            window.location.href="/role/forAddRole.do";
        }).on("click",".seeResource",function(){
            var roleId=$("input[name=roleId]" ,$(this).parents("tr")).val();
            window.location.href="roleResourceAction.do?method=detail&roleId="+roleId;
        }).on("click",".operateResource",function(){
            var roleId=$("input[name=roleId]" ,$(this).parents("tr")).val();
            window.location.href="/role/forAddRole.do?roleId="+roleId;
        }).on("click",".operateRole",function(){
            var roleId=$("input[name=roleId]" ,$(this).parents("tr")).val();
            window.location.href="/role/forAddRole.do?roleId="+roleId;
        }).on("click",".deleteRole",function(){
            var roleIdArray=new Array();
            $(".infoRow input[type=checkbox]:checked").each(function(index,that){
                roleIdArray.push(that.value);
            });
            if(roleIdArray.length==0){
                $.Ntip({
                    'content':"请选择要删除角色",
                })
            }
            else{
                $.Nconfirm({
                    'confirmQuestion':"确认删除已选择的角色吗？",
                    'onConfirm':function(){
                        $.ajax({
                            url:"/role/deleteRole",
                            type:"POST",
                            data:{"roleId":roleIdArray},
                            dataType:"json",
                            traditional:true,
                            success:function(data){
                                if(data.status){
                                    $.Ntip({
                                        'content':"角色删除成功",
                                        'onClose':function(){
                                            paginationConfig.actionForLoadingPagination();
                                        }
                                    })
                                }
                                else{
                                    $.Ntip({
                                        'content':"角色删除失败",
                                    })
                                }
                            }
                        });
                    }

                });
            }

        });
    }

})