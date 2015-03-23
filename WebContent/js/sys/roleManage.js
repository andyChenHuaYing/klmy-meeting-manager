/**
 *  add by xiaogang 20140305
 */


$(function(){
	var obj = {colCnt:5,hasCheckBox:true,containerId:'tableContainer',styleArr:[{name:'ROLE_NAME',desc:"角色名称",left:"60"},
	                                                                      {name:'CREATE_DATE',desc:"创建时间",left:"100"},
	                                                                      {name:'CREATE_USER_NAME',desc:"创建用户",left:"100"},
	                                                                      {name:'MODIFY_DATE',desc:"修改时间",left:"100"}
	                                                                      ]};
	var table = new myTable();
	table.init(obj);
	
	$("#queryBtn").click(function(){//查询数据
		queryMethod(1);		
	});
	
	$("#roleName").on("keydown",function(e){
		if(e.keyCode==13){
			queryMethod(1);
		}
	});
	$("#personName").on("keydown",function(e){
		if(e.keyCode==13){
			queryMethod(1);
		}
	});
	$("#skipPage").on("keydown",function(e){
		if(e.keyCode==13){
			skipBtnEvent();
		}
	});
	
	var queryMethod = function(currPage,flag){
		var roleName = $.trim($("#roleName").val());
		//var currPage = $("#currPage").val();
		var pageSize = parseInt($(".pageSelect").val());
		var obj = {};
		if(roleName.length>0){
			obj.asRoleName = roleName;
		}
		var totalRecord = 0;
		var pageSum = 0;
		$.ajax({
			 url:$cntPath +"/role.spr?method=queryRoleCntForMap",
			 data:"queryInfo=" + encodeURI(JSON.stringify(obj)),
			 dataType:"json",
			 async:false,
			 success:function(data){
				 totalRecord = data.totalRecord;
				 pageSum = Math.ceil(totalRecord/pageSize);
				 $("#pageSum").text(pageSum);
				 $("#currPage").text(currPage);
				 if(flag && flag=="last"){
					 currPage = pageSum;
				 }
			 },
			 error:function(msg){
				 alert("error1:"+msg);
			 }
		 });
		
//		obj.startIdx = pageSize*(currPage-1);
//		obj.endIdx = pageSize*currPage;
		obj.pageSize = pageSize;
		obj.currPage = currPage;
		$.ajax({
			 url:$cntPath +"/role.spr?method=queryRoleForMap",
			 data:"queryInfo=" + encodeURI(JSON.stringify(obj)),
			 dataType:"json",
			 success:function(data){
				 table.addData(data);
			 },
			 error:function(msg){
				 alert("error:"+msg);
			 }
		 });
	};
	
	$("#firstPage").click(function(){
		queryMethod(1);
	});
	
	$("#lastPage").click(function(){
		queryMethod($("#pageSum").text(),"last");
	});
	
	$("#nextPage").click(function(){
		var currPage = parseInt($("#currPage").text());
		if(currPage == parseInt($("#pageSum").text())){
//			alert("当前已到最后一页！");
			return;
		}
		queryMethod(currPage+1);
	});
	
	$("#prevPage").click(function(){
		var currPage = parseInt($("#currPage").text());
		if(currPage <= 1){
//			alert("当前已到首页！");
			return;
		}
		queryMethod(currPage-1);
	});
	$("#skipBtn").click(function(){
		skipBtnEvent();
	});
	var skipBtnEvent = function(){
		if($.trim($("#skipPage").val()).length==0){
			return;
		}
		if(parseInt($("#currPage").text())==parseInt($("#skipPage").val())){
			return;
		}else if(parseInt($("#skipPage").val())>parseInt($("#pageSum").text())){
			alert("请填入小于等于总页数的数字！");
			return;
		}else if(parseInt($("#skipPage").val())<1){
			alert("请填入大于0的数字！");
			return;
		}else{
			queryMethod(parseInt($("#skipPage").val()));
		}
	};
	
	$("#deleteBtn").click(function(){
		var checkBoxs = $("td input[type=checkbox]");
		var ids = "";
		for(var i=0;i<checkBoxs.length;i++){
			if($(checkBoxs[i]).attr("checked")=="checked"){
				ids += $(checkBoxs[i]).attr("val")+",";
			}
		}
		ids = ids.substring(0,ids.length-1);
		$.ajax({
			 type:"POST",
			 url:$cntPath +"/role.spr?method=deleteRole",			 
			 data:"roleIdList="+ids,
			 dataType:"html",
			 success:function(data){
				 if(parseInt($.trim(data))>0){
					 alert("删除成功！");
					 queryMethod(parseInt($("#currPage").text()));
//					 for(var i=0;i<checkBoxs.length;i++){
//						if($(checkBoxs[i]).attr("checked")=="checked"){
//							$(checkBoxs[i]).parent().parent().remove();
//						}
//					}
				 }else{
					 alert("删除失败！");
				 }
			 },
			 error:function(msg){
				 alert("error:"+msg);
			 }
		 });
	});
	queryMethod(1);//默认查询第一页
	
	$("#addBtn").click(function(){
		var flag = window.showModalDialog($cntPath+"/role.spr?method=toAddRole",null,"dialogHeight:800px;dialogWidth:600px;center:1;");
		if(flag && flag == true){
			queryMethod(1);
		}
	});
	$("#modifyBtn").click(function(){
		var checkBoxs = $("td input[type=checkbox]");
		var roleId = null;
		for(var i=0;i<checkBoxs.length;i++){
			if($(checkBoxs[i]).attr("checked")=="checked"){
				roleId = $(checkBoxs[i]).attr("val");
				break;
			}
		}
		
		if(!roleId){
			alert("请选择需要修改的权限！");
			return;
		}
		var flag = window.showModalDialog($cntPath+"/role.spr?method=toAddRole&roleId="+roleId,null,"dialogHeight:800px;dialogWidth:600px;center:1;");
		if(flag && flag == true){
			queryMethod(parseInt($("#currPage").text()));
		}
	});
});