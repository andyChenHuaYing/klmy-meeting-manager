<%@ page language="java" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
 <html> 
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>datagird</title>
		
		<script type="text/javascript" src="<%=path %>/js/util/JsUtil.js"></script>
		
		<script type="text/javascript">
			$J.importFile("jquery,easyui");
			$J.include("<%=path %>/js/component/UserComp/dataGrid.js");
			$J.include("<%=path %>/js/component/My97DatePicker/WdatePicker.js");
			$J.include("<%=path %>/js/component/My97DatePicker/skin/WdatePicker.css");
			$J.include("<%=path %>/css/common/buttonCommont.css");
		</script>
		
		<script type="text/javascript">
			var grid;
			$(function(){
				$("#cc").css("height",document.documentElement.clientHeight);
				$("#centerDiv").css("height",document.documentElement.clientHeight-190-40);
				grid = new DataGrid("logManagerGrid","#dg");
				grid.load({});
				setHeight("cc");

				/*//加载下拉框内容
				$.ajax({
					 url:$cntPath +"/log.spr?method=queryUserTables",
					 dataType:"json",
					 success:function(data){
						 if(data){//加载用户表名
							 var tmpHtml = "<option value=''>请选择...</option>";
							 for(var i=0;i<data.length;i++){
								 tmpHtml += "<option value='"+data[i].TABLE_NAME+"'>"+data[i].TABLE_NAME+"</option>";
							 }
							 $("#resourceTable").append(tmpHtml);
						 }
					 },
					 error:function(msg){
						 alert("error:"+msg);
					 }
				 });*/
				//加载下拉框内容
				$.ajax({
					 url:$cntPath +"/log.spr?method=queryLogType",
					 dataType:"json",
					 success:function(data){
						 if(data){//加载用户表名
							 var tmpHtml = "<option value=''>请选择...</option>";
							 
							 for(var i=0;i<data.length;i++){
								 tmpHtml += "<option value='"+data[i].LOG_TYPE_ID+"'>"+data[i].OPERATION_TYPE+"</option>";
							 }
							 $("#operationType").append(tmpHtml);
						 }
					 },
					 error:function(msg){
						 alert("error:"+msg);
					 }
				 });
				
				
			});
			function query(){
				var conditionUL = $("#queryConditionUL");
				var inputs = conditionUL.find("input");
				var selects = conditionUL.find("select");
				var queryCondition = {};
				for(var i=0;i<inputs.length;i++){
					if($(inputs[i]).attr("type")=="text"){
						queryCondition[$(inputs[i]).attr("id")] = $.trim($(inputs[i]).val());
					}
				}

				for (var j = 0; j < selects.length; j++) {
					if ($(selects[j]).val() != "") {
						queryCondition[$(selects[j]).attr("id")] = $(selects[j]).val();
					}
				}
				grid.load({queryCondition:JSON.stringify(queryCondition)});
			}
		</script>
		<style type="text/css">
			#queryConditionUL > #queryButtonLi {
				width: 10%;
				margin-left: 60px;
			}

			#queryConditionUL li {
				display: inline-block;
				width: 240px;
				padding-right: 5px;
			}

			.queryPropTable {
				width: 100%;
			}

			.queryPropTable .td1 {
				width: 100px;
				text-align: left;
			}

			.queryPropTable .td2 {
				width: 140px;
				text-align: right;
			}

			.queryPropTable select {
				width: 154px;
			}

		</style>
	</head>
	<body style="margin: 0;overflow: hidden;">
 <div id="cc" class="easyui-layout" style="width:100%;height:600px;fit:true;">   
    <div data-options="region:'north',title:'查询条件',split:true" style="height:130px;">
		<ul style="margin-left: 0;padding-left: 10px;" id="queryConditionUL">
			<%--<li>
                 <table class="queryPropTable">
                     <tr>
                         <td class="td1">
                             资源表名：
                         </td>
                         <td class="td2">
                             <select id="resourceTable" class="select"></select>
                         </td>
                     </tr>
                 </table>
             </li>--%>
    	 	
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					操作类型：
    	 				</td>
    	 				<td class="td2">
							<select id="operationType" class="select" title="操作类型"></select>
    	 				</td>
    	 			</tr>
    	 		</table>	
    	 	</li>
    	 	
    	 	
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					用户名称：
    	 				</td>
    	 				<td class="td2">
							<input title="用户名称" type="text" id="userCode">
    	 				</td>
    	 			</tr>
    	 		</table>	
    	 	</li>
    	 	
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
							起始时间：
    	 				</td>
    	 				<td class="td2">
							<input type="text" id="logTime" class="Wdate"
								   onfocus="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" title="起始时间">
    	 				</td>
    	 			</tr>
    	 		</table>	
    	 	</li>

			<li>
				<table class="queryPropTable">
					<tr>
						<td class="td1">
							结束时间：
						</td>
						<td class="td2">
							<input type="text" id="endLogTime" class="Wdate"
								   onfocus="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" title="结束时间">
						</td>
					</tr>
				</table>
			</li>
			<li id="queryButtonLi">
				<table class="queryPropTable ">
    	 			<tr>
    	 				<td class="td1" colspan="2">
    	 					<a class="sts-link-btn" onclick="query();" href="javascript:void(0);" style="margin: 0">
								<span class="sts-link-btn-span">查 询</span>
							</a>
    	 				</td>
    	 			</tr>
    	 		</table>
    	 	</li>
    	 </ul>
<!--     	<div style="height: 30px;width: 100%"> -->
<!--     		<a class="sts-link-btn" onclick="query();" href="javascript:void(0);"> -->
<!-- 				<span class="sts-link-btn-span">查 询</span> -->
<!-- 			</a> -->
<!--     	</div> -->
    </div>   
    <div data-options="region:'center',title:'查询结果'" style="background:#eee;" id="centerDiv">
    	<table id="dg">
  		</table>
    </div>   
</div> 
 
  		
  </body>
</html>
