/**
 *  add by xiaogang 20140305
 */


$(function(){
	var obj = {colCnt:7,hasCheckBox:true,containerId:'tableContainer',styleArr:[{name:'USER_CODE',desc:"用户名称",left:"60"},
	                                                                      {name:'REMOTE_IP',desc:"操作IP",left:"40"},
	                                                                      {name:'RESOURCE_TABLE',desc:"资源表名",left:"60"},
	                                                                      {name:'RESOURCE_ID',desc:"资源ID",left:"40"},
	                                                                      {name:'OPERATION_TYPE_DESC',desc:"操作类型",left:"40"},
	                                                                      {name:'DESCPTION',desc:"操作描述",left:"160"},
	                                                                      {name:'LOG_TIME',desc:"记录时间",left:"100"}
	                                                                      ]};
	var table = new myTable();
	table.init(obj);
	
	$("#queryBtn").click(function(){//查询数据
		queryMethod(1);		
	});
	$("#userCode").on("keydown",function(e){
		if(e.keyCode==13){
			queryMethod(1);
		}
	});
	$("#resourceTableName").on("keydown",function(e){
		if(e.keyCode==13){
			queryMethod(1);
		}
	});
	$("#skipPage").on("keydown",function(e){
		if(e.keyCode==13){
			skipBtnEvent();
		}
	});
	
	//加载下拉框内容
	$.ajax({
		 url:$cntPath +"/log.spr?method=queryUserTables",
		 dataType:"json",
		 async:false,
		 success:function(data){
			 if(data){//加载用户表名
				 var tmpHtml = "<option value=''>请选择...</option>";
				 for(var i=0;i<data.length;i++){
					 tmpHtml += "<option value='"+data[i].TABLE_NAME+"'>"+data[i].TABLE_NAME+"</option>";
				 }
				 $("#resourceTableName").append(tmpHtml);
			 }
		 },
		 error:function(msg){
			 alert("error:"+msg);
		 }
	 });
	//加载下拉框内容
	$.ajax({
		 url:$cntPath +"/log.spr?method=queryLogType",
		 dataType:"json",
		 async:false,
		 success:function(data){
			 if(data){//加载用户表名
				 var tmpHtml = "<option value=''>请选择...</option>";
				 
				 for(var i=0;i<data.length;i++){
					 tmpHtml += "<option value='"+data[i].LOG_TYPE_ID+"'>"+data[i].OPERATION_TYPE+"</option>";
				 }
				 $("#oprType").append(tmpHtml);
			 }
		 },
		 error:function(msg){
			 alert("error:"+msg);
		 }
	 });
	
	var queryMethod = function(currPage,flag){
//		var roleName = $.trim($("#roleName").val());
		var userCode = $.trim($("#userCode").val());
		var oprType = $("#oprType").val();
		var resourceTableName = $("#resourceTableName").val();
		var oprTime = $("#oprTime").val();
		//var currPage = $("#currPage").val();
		var pageSize = parseInt($(".pageSelect").val());
		var obj = {};
		
		if(oprTime.length>0){
			obj.logTime = oprTime;
		}
		if(resourceTableName.length>0){
			obj.resourceTable = resourceTableName;
		}
		if(oprType.length>0){
			obj.operationType = oprType;
		}
		if(userCode.length>0){
			obj.userCode = userCode;
		}
		var totalRecord = 0;
		var pageSum = 0;
		$.ajax({
			 url:$cntPath +"/log.spr?method=queryLogCnt",
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
				 alert("error:"+msg);
			 }
		 });
		
//		obj.startIdx = pageSize*(currPage-1);
//		obj.endIdx = pageSize*currPage;
		obj.pageSize = pageSize;
		obj.currPage = currPage;
		$.ajax({
			 url:$cntPath +"/log.spr?method=queryLog",
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
					 for(var i=0;i<checkBoxs.length;i++){
						if($(checkBoxs[i]).attr("checked")=="checked"){
							$(checkBoxs[i]).parent().parent().remove();
						}
					}
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
		window.showModalDialog($cntPath+"/sysConfig/toAddRole.action",null,"dialogHeight:800px;dialogWidth:600px;center:1;");
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
		window.showModalDialog($cntPath+"/sysConfig/toAddRole.action?roleId="+roleId,null,"dialogHeight:800px;dialogWidth:600px;center:1;");
	});
});