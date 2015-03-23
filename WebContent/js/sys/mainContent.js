/**
 *  add by xiaogang 20140305
 */


$(function(){
	var obj = {colCnt:5,hasCheckBox:false,containerId:'tableContainer',styleArr:[{name:'A_PATIENT_NAME',desc:"患者",left:"40"},
	                                                                            {name:'RECORD_NUMBER',desc:"档案或随访号",left:"40"},
	  	                                                                      {name:'SEARCH_TYPE_DESC',desc:"类型",left:"40"},
	                                                                      {name:'CREATE_TIME',desc:"创建时间",left:"60"},
	                                                                      {name:'CREATE_PERSON_NAME',desc:"建档人",left:"40"}
	                                                                      ]};
	
	$(".extendDiv").css("left",(screen.width-273)/2-12);
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
	$("#skipPage").on("keydown",function(e){
		if(e.keyCode==13){
			skipBtnEvent();
		}
	});
	
	var queryMethod = function(currPage,flag){
		var patientName = $.trim($("#personName").val());
		var searchType = $("#searchType").val();
		var pageSize = parseInt($(".pageSelect").val());
		var obj = {};
		if(patientName.length>0){
			obj.personName = patientName;
		}
		
		if(searchType.length>0){
			obj.searchType = searchType;
		}
		
		var totalRecord = 0;
		var pageSum = 0;
		$.ajax({
			 url:$cntPath +"/archive.spr?method=queryArchiveAndFollowUpCntForMap",
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
			 url:$cntPath +"/archive.spr?method=queryArchiveAndFollowUpForMap",
			 data:"queryInfo=" + encodeURI(JSON.stringify(obj)),
			 dataType:"json",
			 success:function(data){
				 tmpRtnObj = data;
				 table.addData(data);
				 $("#tableDiv").show();
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
		if(g.userProperty && g.userProperty == 1){
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
				 url:$cntPath +"/archive.spr?method=deleteArchiveForBatch",			 
				 data:"recordIdList="+ids,
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
		}else{
			alert("对不起，您没有该操作权限！");
		}
	});
//	queryMethod(1);//默认查询第一页
	
	$("#addBtn").click(function(){
		if(g.userProperty && g.userProperty == 1){
			var flag = window.showModalDialog($cntPath+"/archive.spr?method=toManageArchive&oprFlag=add",null,"dialogHeight:1200px;dialogWidth:1280px;center:1;");
			if(flag && flag == true){
				queryMethod(parseInt($("#currPage").text()));
			}	
		}else{
			alert("对不起，您没有该操作权限！");
		}
	});
	$("#modifyBtn").click(function(){
		if(g.userProperty && g.userProperty == 1){
			var checkBoxs = $("td input[type=checkbox]");
			var recordId = null;
			for(var i=0;i<checkBoxs.length;i++){
				if($(checkBoxs[i]).attr("checked")=="checked"){
					recordId = $(checkBoxs[i]).attr("val");
					break;
				}
			}
			
			if(!recordId){
				alert("请选择需要修改的记录！");
				return;
			}
			var flag = window.showModalDialog($cntPath+"/archive.spr?method=modify&archiveId="+recordId,null,"dialogHeight:1200px;dialogWidth:1280px;center:1;");
			if(flag && flag == true){
				queryMethod(parseInt($("#currPage").text()));
			}
		}else{
			alert("对不起，您没有该操作权限！");
		}
	});
	
	$("#confirmBtn").click(function(){
		var checkBoxs = $("td input[type=checkbox]");
		var recordId = null;
		var rtnObj = null;
		for(var i=0;i<checkBoxs.length;i++){
			if($(checkBoxs[i]).attr("checked")=="checked"){
				recordId = $(checkBoxs[i]).attr("val");
				break;
			}
		}
		
		if(recordId==null){
			alert("请先选择档案！");
			return;
		}
		if(tmpRtnObj){
			for(var i=0;i<tmpRtnObj.length;i++){
				if(recordId == tmpRtnObj[i].ID){
					rtnObj = tmpRtnObj[i];
					break;
				}
			}
		}
		window.returnValue = rtnObj;
		window.close();
	});
	$(document).on("click",".closeImg",function(){
		$("#"+$(this).parent().attr("tabId")).remove();
		$(this).parent().remove();
		if($(this).parent().attr("class")=="checked"){
			$("#mainPage").show();
			$("#tabs li[tabId='mainPage']").attr("class","checked");
		}		
	});
	
	$(document).on("click","#tabs li span",function(){
		if($(this).parent().attr("class")!="checked"){
			$("#tabs li").removeAttr("class");
			$(this).parent().attr("class","checked");
			$("div[isTab='1']").hide();
			$("#"+$(this).parent().attr("tabId")).show();
		}
	});
	
	$(document).on("mouseover","#tabs li",function(){
		$(this).find("img").css("display","inline-block");
	});
	$(document).on("mouseout","#tabs li",function(){
		$(this).find("img").hide();
	});
	$(".closeMenu").click(function(){
		$(".menuDiv").hide();
		$(".extendDiv").show();
	});
	$(".extendDiv").click(function(){
		$(this).hide();
		$(".menuDiv").show();
	});
});

function view(recordId){
	
	var flag = window.showModalDialog($cntPath+"/archive.spr?method=view&archiveId="+recordId,null,"dialogHeight:1200px;dialogWidth:1280px;center:1;");
	if(flag && flag == true){
		queryMethod(parseInt($("#currPage").text()));
	}
}