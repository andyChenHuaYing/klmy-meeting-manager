/**
 * add by gu.xiaogang
 */

function commonPage(pageTag,recordId){
	this.pageTag = pageTag;	
	this.recordId = recordId;
}

commonPage.prototype.initPage = function(){
	var html = "";
	var tmpThis = this;
	$.ajax({
		 url:$cntPath +"/pageConfig.spr?method=getPageByPageTag",
		 data:"pageTag=" + this.pageTag,
		 dataType:"html",
//		 async:false,
		 success:function(data){
			 html = data;
			 $("#containerDiv").append(html);
			 $("#saveBtn").show();
			 tmpThis.initEvent();
			 $("#loaderDiv").hide();
			 loadingObj.hide();
		 },
		 error:function(msg){
			 alert("error:"+msg);
		 }
	 });
	
	
};
commonPage.prototype.initModifyPage = function(){
	var html = "";
	var tmpThis = this;
	$.ajax({
		 url:$cntPath +"/pageConfig.spr?method=getUpdatePageById",
		 data:"pageTag=" + this.pageTag +"&recordId="+this.recordId,
		 dataType:"html",
//		 async:false,
		 success:function(data){
			 html = data;
			 $("#containerDiv").append(html);
			 if(g.oprType=="modify"){
				 $("#saveBtn").show();
			 }
			 tmpThis.initEvent();
			 $("#loaderDiv").hide();
			 loadingObj.hide();
		 },
		 error:function(msg){
			 alert("error:"+msg);
		 }
	 });
	
	
};
commonPage.prototype.initEvent = function(){
	$(".aaron_add_profiles_title ul li").click(function(){
		$(".aaron_title_on").removeClass();
		$(this).addClass("aaron_title_on");
		var cur_index=$(".aaron_add_profiles_title ul li").index(this);
		$(".aaron_item_on").removeClass("aaron_item_on");
		$(".aaron_item").eq(cur_index).addClass("aaron_item_on");
		
		
	});

	$(".aaron_next").click(function(){
		var cur_index=$(".aaron_next").index(this);
		
		$(".aaron_add_profiles_title ul li").removeClass("aaron_title_on");
		$(".aaron_add_profiles_title ul li").eq(cur_index+1).addClass("aaron_title_on");
		
		$(".aaron_item_on").removeClass("aaron_item_on");
		$(".aaron_item").eq(cur_index+1).addClass("aaron_item_on");
		
		
	});

	$(".aaron_previous").click(function(){
		var cur_index=$(".aaron_previous").index(this);
		
		$(".aaron_add_profiles_title ul li").removeClass("aaron_title_on");
		$(".aaron_add_profiles_title ul li").eq(cur_index).addClass("aaron_title_on");
		
		$(".aaron_item_on").removeClass("aaron_item_on");
		$(".aaron_item").eq(cur_index).addClass("aaron_item_on");
		
		
	});



	$(".aaron_add_profiles_title ul li").hover(function(){
		//$(".aaron_title_on").removeClass();
		$(this).addClass("aaron_title_hover");	
	},function(){
		$(this).removeClass("aaron_title_hover");	
	});

	$(".aaron_sub_item").click(function(){
		$(".aaron_sub_item_on").removeClass("aaron_sub_item_on");
		$(this).next(".aaron_sub_item_content").addClass("aaron_sub_item_on");
	});


	//////////////////--------radio show begin---------//////////////////////

	//////////////////////////////////////////////////////
	$(".zhengduanshi_show").click(function(){
		$(".zhengduanshi_content_hide").css("display","block");	
	});

	$(".zhengduanshi_hide").click(function(){
		$(".zhengduanshi_content_hide").css("display","none");	
	});

	/////////////////////////////////////////////////////
	$(".smoking").click(function(){
		$(".smoking_hide").css("display","block");
		$(".smoked_hide").css("display","none");	
	});

	$(".smoked").click(function(){
		$(".smoked_hide").css("display","block");
		$(".smoking_hide").css("display","none");	
	});

	$(".not_smoke").click(function(){
		$(".smoked_hide").css("display","none");
		$(".smoking_hide").css("display","none");	
	});

	/////////////////////////////////////////////////////
	$(".drinking").click(function(){
		$(".drinking_hide").css("display","block");
		$(".drinked_hide").css("display","none");	
	});

	$(".drinked").click(function(){
		$(".drinked_hide").css("display","block");
		$(".drinking_hide").css("display","none");	
	});

	$(".not_drink").click(function(){
		$(".drinked_hide").css("display","none");
		$(".drinking_hide").css("display","none");	
	});

	/////////////////////////////////////////////////////
	$(".ill_history").click(function(){
		$(".ill_history_hide").css("display","block");
	});

	$(".no_ill_history").click(function(){
		$(".ill_history_hide").css("display","none");
	});
	$(".aaron_table tr:even").attr("class",$(".aaron_table tr:even").attr("class")+" aaron_tr_selected");
	$(".aaron_table tr:odd").attr("class",$(".aaron_table tr:even").attr("class")+" aaron_tr_unselected");
	
	
	var getSaveInfo = function(arr){	
		
		var obj = {};
		for(var i=0;i<arr.length;i++){
			if(($(arr[i]).attr("type")=="checkbox" || $(arr[i]).attr("type")=="radio") && arr[i].checked!=true){
				continue;
			}else if($(arr[i]).attr("type")=="button"){
				continue;
			}			
			if(obj[$(arr[i]).attr("name")] == null ){
				obj[$(arr[i]).attr("name")] = [$(arr[i])];
			}else{
				obj[$(arr[i]).attr("name")].push($(arr[i]));
			}
		}
		return obj;
	};
	
	$("#saveBtn").click(function(){
		if(g.pageTag == 1 && $.trim($("input[name='recordNumber']").val()).length==0){
			alert("请填写档案号！");
			return;
		}
		
		
		if(g.pageTag == 2 && g.personId == null && g.oprType=="add"){
			alert("请先选择患者！");
			return;
		}
		
		var inputs = $("input").filter(function(index){
			return $(this).attr("isbasicinfo")==null && $(this).attr("isTableInput")==null;
		});
		var data;
		var basicInputs = $("input[isbasicinfo='1']");
		var obj = getSaveInfo(inputs);
		var basicObj = getSaveInfo(basicInputs);
		var paramArr = [];//存放保存信息 ### 分隔符
		var basicParamObj = {};//存放患者基本信息
		for(var prop in obj){
			var tmpArr = obj[prop];
			var tmpObj = {};
			tmpObj.nodeId = prop;
			var tmpStr = "";
			for(var j=0;j<tmpArr.length;j++){
//				tmpStr +=  (tmpArr[j].attr("type")=="text"?tmpArr[j].val():tmpArr[j].attr("value"))+"###";
				tmpStr += (tmpArr[j][0].value==null?"":tmpArr[j].val()) + "###";
			}
			tmpObj.value = tmpStr.substring(0,tmpStr.length-3);
			paramArr.push(tmpObj);
		}
		for(var prop in basicObj){
			var tmpArr = basicObj[prop];
			var tmpObj = {};
//			tmpObj.nodeId = prop;
			var tmpStr = "";
			for(var j=0;j<tmpArr.length;j++){
//				tmpStr +=  (tmpArr[j].attr("type")=="text"?tmpArr[j].val():tmpArr[j].attr("value"))+"###";
				tmpStr += (tmpArr[j][0].value==null?"":tmpArr[j].val()) + "###";
			}
//			tmpObj.value = tmpStr.substring(0,tmpStr.length-3);
			basicParamObj[prop] = tmpStr.substring(0,tmpStr.length-3);
			
		}
		
		var tables = $("table").filter(function(index){
			return $(this).attr("tableId")!=null;
		});
		
		var tableArr = [];
		for(var i=0;i<tables.length;i++){
			var tmpTableObj = {};
			tmpTableObj.tableId = $(tables[i]).attr("tableId");
			var tableInputs = $("table[id='"+$(tables[i]).attr("id")+"'] input");
			var tmpObj = {};
			for(var j=0;j<tableInputs.length;j++){
				if(tmpObj[$(tableInputs[j]).attr("name")]){
					tmpObj[$(tableInputs[j]).attr("name")].push($.trim($(tableInputs[j]).val()));
				}else{					
					tmpObj[$(tableInputs[j]).attr("name")] = [$.trim($(tableInputs[j]).val())];
				}				
				
			}
			tmpTableObj.param = tmpObj;
			tableArr.push(tmpTableObj);
		}
		
		if(g.pageTag == 1 ){
			data = "param=" + JSON.stringify(paramArr)+"&basicInfo="+encodeURI(JSON.stringify(basicParamObj))+"&tableParams="+encodeURI(JSON.stringify(tableArr));
		}else if(g.pageTag == 2 ){

			data = "param=" + JSON.stringify(paramArr)+"&tableParams="+encodeURI(JSON.stringify(tableArr));
		}
		
		
		$.ajax({
			 url:$cntPath +"/pageConfig.spr?method=save&recordId="+g.recordId+"&pageTag="+g.pageTag+"&personId="+(g.personId==null?0:g.personId)+"&recordNumber="+$("input[name='recordNumber']").val(),
			 method:"post",
			 data:data,
			 dataType:"html",
			 async:false,
			 success:function(data){
				 alert(data);
			 },
			 error:function(msg){
				 alert("error:"+msg);
			 }
		 });
		
	});
	
	
	$("#choosePatientBtn").click(function(){
		var rtnObj = window.showModalDialog($cntPath+"/person.spr?method=toPersonManage",null,"dialogHeight:1000px;dialogWidth:900px;center:1;");
		if(rtnObj){//初始化信息
			$("input[name='personName']").val("");
			$("input[name='sex']").removeAttr("checked");
			$("input[name='birth']").val("");
			$("input[name='national']").removeAttr("checked");
			$("input[name='education']").removeAttr("checked");
			$("input[name='job']").removeAttr("checked");
			$("input[name='income']").removeAttr("checked");			
			$("input[name='mobile']").val("");
			$("input[name='tel']").val("");
			$("input[name='address']").val("");
			$("input[name='contact']").val("");
			$("input[name='postcode']").val();
			$("input[name='idCard']").val();
			$("input[name='medicareCard']").val();
			
			
			$("input[name='personName']").val(rtnObj.PERSON_NAME);
			if(rtnObj.SEX!=null){
				$("input[name='sex'][value='"+rtnObj.SEX+"']").attr("checked","checked");
			}
			$("input[name='birth']").val(rtnObj.BIRTH);
			if(rtnObj.NATIONAL!=null){
				$("input[name='national'][value='"+rtnObj.NATIONAL+"']").attr("checked","checked");
			}
			
			if(rtnObj.EDUCATION!=null){
				$("input[name='education'][value='"+rtnObj.EDUCATION+"']").attr("checked","checked");
			}
			
			if(rtnObj.JOB!=null){
				$("input[name='job'][value='"+rtnObj.JOB+"']").attr("checked","checked");
			}
			
			if(rtnObj.INCOME!=null){
				$("input[name='income'][value='"+rtnObj.INCOME+"']").attr("checked","checked");
			}
			
			
			$("input[name='mobile']").val(rtnObj.MOBILE);
			$("input[name='tel']").val(rtnObj.TEL);
			$("input[name='address']").val(rtnObj.ADDRESS);
			if(rtnObj.CONTACT && rtnObj.CONTACT.length>0){
				var contacts = $("input[name='contact']");
				var contactVals = rtnObj.CONTACT.split("###");
				for(var i=0;i<contacts.length;i++){
					if(contactVals[i]){
						$(contacts[i]).val(contactVals[i]);
					}else{
						$(contacts[i]).val("");
					}
				}	
			}else{
				$("input[name='contact']").val("");
			}
			
//			$("input[name='contact']").val(rtnObj.CONTACT);
			
			$("input[name='postcode']").val(rtnObj.POSTCODE);
			$("input[name='idCard']").val(rtnObj.ID_CARD);
			$("input[name='medicareCard']").val(rtnObj.MEDICARE_CARD);
		}
		
	});
	
	//选择档案号
	$("#chooseRecordBtn").click(function(){
		var rtnObj = window.showModalDialog($cntPath+"/record.spr?method=toQueryRecord&pageTag=1&isChoose=1",null,"dialogHeight:1000px;dialogWidth:900px;center:1;");
		if(rtnObj){
			g.personId = rtnObj.PATIENT_ID;
			$("input[name='recordNumber']").val(rtnObj.RECORD_NUMBER);
			$("input[name='personName']").val(rtnObj.PATIENT_NAME);
			var obj = {};
			obj.personId = rtnObj.PATIENT_ID;
			$.ajax({
				 url:$cntPath +"/person.spr?method=queryPersonForMap",
				 data:"queryInfo=" + encodeURI(JSON.stringify(obj)),
				 dataType:"json",
				 success:function(data){
					 if(data && data.length>0){
						 var sex = data[0].SEX;
						 var birth = data[0].BIRTH;
						 var mobile = data[0].MOBILE;
						 var tel = data[0].TEL;
						 var contact = data[0].CONTACT;
						 var contacts = contact.split("###");
						 if(sex==0){
							 $("input[name='sex']").eq(0).attr("checked","checked");
						 }else if(sex==1){
							 $("input[name='sex']").eq(0).attr("checked","checked");
						 }
						 $("input[name='mobile']").val(mobile);
						 $("input[name='tel']").val(tel);
						 
						 if(contacts.length>0){
							 $("input[name='contact']").eq(0).val(contacts[0]);
						 }
						 if(contacts.length>1){
							 $("input[name='contact']").eq(1).val(contacts[1]);
						 }
						 if(birth && birth!=''){
							 var times = birth.split("-");
							 var age = (new Date().getFullYear()) - times[0]+1;
							 $("input[name='age']").val(age);
						 }
					 }
					 
				 },
				 error:function(msg){
					 alert("error:"+msg);
				 }
			 });
			
			
		}
	});
};