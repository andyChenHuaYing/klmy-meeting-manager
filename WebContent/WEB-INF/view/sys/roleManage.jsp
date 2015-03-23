<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
			var bodyStr = '<div id="cc" class="easyui-layout" style="width:100%;height:600px;fit:true;">'
				+'    <div data-options="region:\'north\',title:\'查询条件\',split:true" style="height:120px;">'
				+'    	 <ul style="margin-left: 0px;padding-left: 10px;" id="queryConditionUL">'
				+'    	 	<li>'
				+'    	 		<table class="queryPropTable">'
				+'    	 			<tr>'
				+'    	 				<td class="td1">'
				+'    	 					权限名：'
				+'    	 				</td>'
				+'    	 				<td class="td2">'
				+'    	 					<input type="text" id="asRoleName">'
				+'    	 				</td>'
				+'    	 			</tr>'
				+'    	 		</table>'
				+'    	 	</li>'
				+'    	 </ul>'
				+'    	<div style="height: 30px;width: 100%">'
				+'    		<a class="sts-link-btn" onclick="query();" href="javascript:void(0);">'
				+'				<span class="sts-link-btn-span">查 询</span>'
				+'			</a>'
				+'    	</div>'
				+'    </div>'
				+'    <div data-options="region:\'center\',title:\'查询结果\'" style="background:#eee;" id="centerDiv">'
				+'    	<table id="dg">'
				+'  		</table>'
				+'    </div>'
				+'</div> ';	
			var toolbar = [{
					text:'新增',
					iconCls: 'icon-add',
					handler: function(){
						var buttons = [{
							iconCls:'icon-save',
							text:'保存',
							handler:function(){
								if($.trim($("#ULoginName").val()).length==0){
									$.messager.alert('提示', "请填写权限名称！");
									return;
								}
								manageRoleMenu();
								var roleName = $.trim($("#ULoginName").val());
								var menuList = "";
								for(var prop in roleMenuObj){
									menuList += prop+",";
								}
								menuList = menuList.substring(0,menuList.length-1);
								
								$.ajax({
									 type:"POST",
									 url:$cntPath +"/role.spr?method=saveRoleInfo",	
									 data:"roleName="+roleName +"&menuListStr="+menuList+"&in_roleId="+g_roleId,
									 dataType:"html",
									 success:function(data){
										 if(data){
											 if(parseInt(data)>0){
												 $.messager.alert('提示', "修改成功");
												 $('#win').dialog("close");
												 query();
											 }else if(parseInt(data) == -1){
												 $.messager.alert('提示', "名称已存在");
											 }else{
												 $.messager.alert('提示', "保存失败");
											 }
										 }
									 },
									 error:function(msg){
										 $.messager.alert('提示', msg.responseText);
									 }
								 });
							}

						},{
							text:'取消',
							iconCls:'icon-cancel',
							handler:function(){
								$('#win').dialog("close");
							}
						}];
						if($("#win").length==0){
							$("body").append("<div id='win'></div>");	
						}
						
						$('#win').dialog({    
						    title: '新增权限',    
						    width: 400,    
						    height: 360,    
						    closed: false,    
						    cache: false,    
						    href: $cntPath+"/role.spr?method=toAddRole",    
						    modal: true,
						    buttons : buttons
						}); 
						
						
						/*var flag = window.showModalDialog($cntPath+"/role.spr?method=toAddRole",null,"dialogHeight:400px;dialogWidth:360px;center:1;");
						if(flag && flag == true){
							query();
						}*/
					}
				},'-',{
					text:'修改',
					iconCls: 'icon-edit',
					handler: function(){
						var selectObj = $("#dg").datagrid('getSelected');
						if(selectObj==null){
							$.messager.alert('提示', "请选择要修改的权限！");
							return;
						}
						var roleId = selectObj.ID;
						var buttons = [{
							iconCls:'icon-save',
							text:'保存',
							handler:function(){
								if($.trim($("#ULoginName").val()).length==0){
									$.messager.alert('提示', "请填写权限名称！");
									return;
								}
								manageRoleMenu();
								var roleName = $.trim($("#ULoginName").val());
								var menuList = "";
								for(var prop in roleMenuObj){
									menuList += prop+",";
								}
								menuList = menuList.substring(0,menuList.length-1);
								
								$.ajax({
									 type:"POST",
									 url:$cntPath +"/role.spr?method=saveRoleInfo",	
									 data:"roleName="+roleName +"&menuListStr="+menuList+"&in_roleId="+roleId,
									 dataType:"html",
									 success:function(data){
										 if(data){
											 if(parseInt(data)>0){
												 $.messager.alert('提示', "修改成功");
												 $('#win').dialog("close");
												 query();
												 
											 }else if(parseInt(data) == -1){
												 $.messager.alert('提示', "名称已存在");
											 }else{
												 $.messager.alert('提示', "修改失败");
											 }
										 }
									 },
									 error:function(msg){
										 $.messager.alert('提示', msg.responseText);
									 }
								 });
							}
						},{
							text:'取消',
							iconCls:'icon-cancel',
							handler:function(){
								$('#win').dialog("close");
							}
						}];
						
						
						if($("#win").length==0){
							$("body").append("<div id='win'></div>");	
						}
						
						$('#win').dialog({    
						    title: '修改权限',    
						    width: 400,    
						    height: 360,    
						    closed: false,    
						    cache: false,    
						    href: $cntPath+"/role.spr?method=toAddRole&roleId="+roleId,    
						    modal: true,
						    buttons : buttons
						}); 
					}
				},'-',{
					text:'删除',
					iconCls: 'icon-remove',
					handler: function(){
						var ids = grid.getSelectedRowIds();
						if(ids.length>0){
							$.ajax({
								 type:"POST",
								 url:$cntPath +"/role.spr?method=deleteRole",			 
								 data:"roleIdList="+ids.join(","),
								 dataType:"html",
								 success:function(data){
									 if(parseInt($.trim(data))>0){
										 //alert("删除成功！");
										 $.messager.alert('提示', "删除成功！");
										 query();
									 }else{
										 $.messager.alert('提示', "删除失败！");
									 }
								 },
								 error:function(msg){
									 $.messager.alert('提示', msg.responseTest);
								 }
							 });
						}else{
							$.messager.alert('提示', "请选择要删除的权限信息");
						}
					}
				}];

			$(function(){
				$("#cc").css("height",document.documentElement.clientHeight);
				$("#centerDiv").css("height",document.documentElement.clientHeight-190-40);
				grid = new DataGrid("roleManagerGrid","#dg");
				grid.load({},{toolbar:toolbar});
				setHeight("cc");
			});
			function query(){
				var inputs = $("#queryConditionUL input");
				var queryCondition = {};
				for(var i=0;i<inputs.length;i++){
					if($(inputs[i]).attr("type")=="text"){
						queryCondition[$(inputs[i]).attr("id")] = $.trim($(inputs[i]).val());
					}
				}
				grid.load({queryCondition:JSON.stringify(queryCondition)},{toolbar:toolbar});
			}
		</script>
		<style type="text/css">		
		.titleTd{
			background: url("images/sys/tablespan.jpg");
		}
		#queryConditionUL li{
			display: inline-block;
			width: 240px;
			padding-right: 5px;
		}
		.queryPropTable{
			width: 100%;
		}
		.queryPropTable .td1{
			width: 45px;
			text-align: left;
			/*background: url('images/sys/tablespan.jpg') repeat scroll 0% 0% transparent;*/
		}
		.queryPropTable .td2{
			width: 140px;
			text-align: left;			
		}
		</style>
	</head>
 <body style="margin: 0px;overflow: hidden;">
 <div id="cc" class="easyui-layout" style="width:100%;height:600px;fit:true;">   
    <div data-options="region:'north',title:'查询条件',split:true" style="height:90px;">
    	
    	 <ul style="margin-left: 0px;padding-left: 10px;" id="queryConditionUL">
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					权限名：
    	 				</td>
    	 				<td class="td2">
    	 					<input type="text" id="asRoleName">
    	 				</td>
    	 			</tr>
    	 		</table>
    	 	</li>
    	 	<li>
    	 		<table class="queryPropTable">
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

