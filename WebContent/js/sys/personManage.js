/**
 *  add by xiaogang 20140305
 */


$(function(){
	var obj = {colCnt:5,hasCheckBox:true,containerId:'tableContainer',styleArr:[
	                                                                      {name:'PERSON_NAME',desc:"人员姓名",left:"35%"},
	                                                                      {name:'ID_CARD',desc:"身份证号",left:"35%"},
	                                                                      {name:'MEDICARE_CARD',desc:"医保卡号",left:"35%"},
	                                                                      {name:'IDENTITY_DESC',desc:"身份",left:"35%"}
	                                                                      ]};
	
	var tmpRtnObj = null;
	
	var table = new myTable();
	table.init(obj);
	
	$("#queryBtn").click(function(){//查询数据
		queryMethod(1);		
	});
	
	$("#personName").on("keydown",function(e){
		if(e.keyCode==13){
			queryMethod(1);
		}
	});
	$("#idCard").on("keydown",function(e){
		if(e.keyCode==13){
			queryMethod(1);
		}
	});
	$("#medicareCard").on("keydown",function(e){
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
		var personName = $.trim($("#personName").val());
		var idCard = $.trim($("#idCard").val());
		var identityId = $("#identityId").val();
		var medicareCard = $.trim($("#medicareCard").val());
		var pageSize = parseInt($(".pageSelect").val());
		var obj = {};
		if(personName.length>0){
			obj.personName = personName;
		}
		if(idCard.length>0){
			obj.idCard = idCard;
		}
		if(medicareCard.length>0){
			obj.medicareCard = medicareCard;
		}
		obj.identityId = identityId;
		obj.isPatient = 1;
		var totalRecord = 0;
		var pageSum = 0;
		$.ajax({
			 url:$cntPath +"/person.spr?method=queryPersonCntForMap",
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
			 url:$cntPath +"/person.spr?method=queryPersonForMap",
			 data:"queryInfo=" + encodeURI(JSON.stringify(obj)),
			 dataType:"json",
			 success:function(data){
				 tmpRtnObj = data;
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
			 url:$cntPath +"/user.spr?method=deleteUser",			 
			 data:"userIdList="+ids,
			 dataType:"html",
			 success:function(data){
				 if(parseInt($.trim(data))>0){
					 alert("删除成功！");
//					 for(var i=0;i<checkBoxs.length;i++){
//						if($(checkBoxs[i]).attr("checked")=="checked"){
//							$(checkBoxs[i]).parent().parent().remove();
//						}
//					 }
					queryMethod(parseInt($("#currPage").text()));
				 }else{
					 alert("删除失败！");
				 }
			 },
			 error:function(msg){
				 alert("error:"+msg);
			 }
		 });
	});
//	queryMethod(1);//默认查询第一页
	
	$("#addBtn").click(function(){
		var flag = window.showModalDialog($cntPath+"/user.spr?method=toAddExistsUser",null,"dialogHeight:800px;dialogWidth:600px;center:1;");
		if(flag && flag == true){
			queryMethod(1);
		}
	});
	
	$("#confirmBtn").click(function(){
		var checkBoxs = $("td input[type=checkbox]");
		var personId = null;
		var rtnObj = null;
		for(var i=0;i<checkBoxs.length;i++){
			if($(checkBoxs[i]).attr("checked")=="checked"){
				personId = $(checkBoxs[i]).attr("val");
				break;
			}
		}
		
		if(personId==null){
			alert("请先选择人员！");
			return;
		}
		if(tmpRtnObj){
			for(var i=0;i<tmpRtnObj.length;i++){
				if(personId == tmpRtnObj[i].ID){
					rtnObj = tmpRtnObj[i];
					break;
				}
			}
		}
		window.returnValue = rtnObj;
		window.close();
	});
	
	$("#modifyBtn").click(function(){
		var checkBoxs = $("td input[type=checkbox]");
		var userId = null;
		for(var i=0;i<checkBoxs.length;i++){
			if($(checkBoxs[i]).attr("checked")=="checked"){
				userId = $(checkBoxs[i]).attr("val");
				break;
			}
		}
		
		if(!userId){
			alert("请选择需要修改的用户！");
			return;
		}
		var flag = window.showModalDialog($cntPath+"/user.spr?method=toAddExistsUser?userId="+userId,null,"dialogHeight:800px;dialogWidth:600px;center:1;");
		if(flag && flag == true){
			queryMethod(parseInt($("#currPage").text()));
		}
	});
	
	
});