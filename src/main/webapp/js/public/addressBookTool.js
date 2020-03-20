


;$.extend({
	getStaffOfAddressBook:function(params,actionAfterStaffLoaded){
		$.ajax({
		   url: '../addrBook/showStaffOfAddrBook.do',
		   type:'POST',
		   dataType:"json",
		   data: params,
		   success: function(data){
			   if(data.data.length>0){
				   if(typeof actionAfterStaffLoaded=="function"){
					   actionAfterStaffLoaded(data.data);
				   }
				   
			   }
			   else{
				  //不显示任何信息
			   }
		   },
		   error:function(){
		   }
		})
	},
	
	getAddressBookList:function(params,actionAfterAddressBookLoaded){
		$.ajax({
			url:"../addrBook/getAddressBookListInfo.do",
			type:"POST",
			dataType:"json",
			data:params,
			success:function(data){
				if(data.success){
					 if(typeof actionAfterAddressBookLoaded=="function"){
						 actionAfterAddressBookLoaded(data.data.addressBookList);
					 }
				} else{
					var info="通讯录加载失败";
					/*if($.trim(data.cause)!=""){
						info+="，原因："+data.cause;
					}*/
					$.tips(info,"系统提示");
				}
			},
			error:function(){
				$.tips("通讯录加载失败","系统提示");
			}
		})
	}
})

;$.fn.extend({
	
	loadAddressBookList:function(params,extraAction){
		var $container=this;
		$.getAddressBookList(params,function(addressBookList){
			var $addressList=null;
			if(($addressList=$("ul.addressList",$container)).length>0);
			else{
				$container.append("<ul class='addressList'></ul>");
				$addressList=$("ul.addressList",$container);
			}
			$addressList.empty();
			var bookLength=(addressBookList==null)?0:addressBookList.length;
			var bookListInfo=new Array();
			for(var i=0;i<bookLength;i++){
				var book=addressBookList[i];
				bookListInfo.push("<li class='bookItem'><a href='javascript:void(0);'><input type='hidden' value='");
				bookListInfo.push(book.addrbookId);
				bookListInfo.push("' name='addrbookId' />");
				bookListInfo.push(book.addrbookName);
				bookListInfo.push("</li></a>");
			}
			$addressList.append(bookListInfo.join(""));
			if(typeof extraAction=="function"){
				extraAction(addressBookList);
			}
		});
	}
})