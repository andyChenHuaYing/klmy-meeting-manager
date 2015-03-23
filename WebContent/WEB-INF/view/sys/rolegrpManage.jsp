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
			var toolbar = [{
				text:'新增',
				iconCls: 'icon-add',
				handler: function(){
					var buttons = [{
						iconCls:'icon-save',
						text:'保存',
						handler:function(){
							var name = $.trim($(".aaron_add_table input[id='rolegrpName']").val());
							var selectedRoleList = $(".aaron_add_table select[id='selectedRoleList'] option");
							var obj = {};
							var list = [];
							if(name.length>0){
								obj.rolegrpName = name;	
							}else{
								$.messager.alert('提示', "请填写权限组名称！");
								return;
							}
							if(selectedRoleList.length>0){
								for(var i=0;i<selectedRoleList.length;i++){
									list.push($(selectedRoleList[i]).val());
								}
								obj.roleList = list;
							}else{
								$.messager.alert('提示',"请选择关联权限点！");
								return;
							}
							$.ajax({
								url:$cntPath+"/rolegrp.spr?method=addRolegrp",
								data:"rolegrpInfo="+JSON.stringify(obj),
								method:"post",
								dataType:"json",
								async:false,
								success:function(data){
									if(data>0){
										$.messager.alert('提示', "保存成功");
										//$('#win').parent().remove();
										$('#win').dialog("close");
										grid.load({},{toolbar:toolbar});
									}else if(parseInt(data) == -1){
										 $.messager.alert('提示', "名称已存在");
									 }else{
										$.messager.alert('提示', "保存失败");
									}
								},
								error:function(msg){
									alert(msg.responseText);
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
					
					$('#win').dialog({    
					    title: '新增权限组',    
					    width: 500,    
					    height: 450,    
					    closed: false,    
					    cache: false,    
					    href: $cntPath+'/rolegrp.spr?method=toAddRolegrp',    
					    modal: true,
					    buttons : buttons
					});  
				}
			},'-',{
				text:'修改',
				iconCls: 'icon-edit',
				handler: function(){
					var selectObj = $("#dg").datagrid('getSelected');
					if(selectObj==null){
						$.messager.alert('提示', "请选择要修改的部门！");
						return;
					}
					var rolegrpId = selectObj.rolegrpId;
					var buttons = [{
						iconCls:'icon-save',
						text:'保存',
						handler:function(){
							var name = $.trim($(".aaron_add_table input[id='rolegrpName']").val());
							var selectedRoleList = $(".aaron_add_table select[id='selectedRoleList'] option");
							var obj = {};
							var list = [];
							if(name.length>0){
								obj.rolegrpName = name;	
							}else{
								$.messager.alert('提示', "请填写权限组名称！");
								return;
							}
							if(selectedRoleList.length>0){
								for(var i=0;i<selectedRoleList.length;i++){
									list.push($(selectedRoleList[i]).val());
								}
								obj.roleList = list;
							}else{
								$.messager.alert('提示',"请选择关联权限点！");
								return;
							}
							$.ajax({
								url:$cntPath+"/rolegrp.spr?method=updateRolegrp",
								data:"rolegrpInfo="+JSON.stringify(obj)+"&rolegrpId="+rolegrpId+$J.getCurrTime(),
								method:"post",
								dataType:"json",
								async:false,
								success:function(data){
									if(data>0){
										$.messager.alert('提示', "保存成功");
										$('#win').dialog("close");
										grid.load({},{toolbar:toolbar});
									}else if(parseInt(data) == -1){
										 $.messager.alert('提示', "名称已存在");
									 }else{
										$.messager.alert('提示', "保存失败");
									}
								},
								error:function(msg){
									alert(msg.responseText);
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
					
					$('#win').dialog({    
					    title: '修改权限组',    
					    width: 500,    
					    height: 450,    
					    closed: false,    
					    cache: false,    
					    href: $cntPath+'/rolegrp.spr?method=toAddRolegrp&rolegrpId='+rolegrpId+"&rolegrpName="+encodeURI(encodeURI(selectObj.rolegrpName)),    
					    modal: true,
					    buttons : buttons
					});  
					
				}
			},'-',{
				text:'删除',
				iconCls: 'icon-remove',
				handler: function(){
					var selectObj = $("#dg").datagrid('getSelected');
					if(selectObj==null){
						$.messager.alert('提示', "请选择要删除的权限组！");	
						return;
					}else{
						
						$.messager.confirm('确认','您确认要删除该权限组吗？',function(r){    
						    if (r){    
						    	$.ajax({
									url:$cntPath+"/rolegrp.spr?method=deleteRolegrp",
									data:"rolegrpId="+selectObj.rolegrpId,
									method:"post",
									dataType:"json",
									async:false,
									success:function(data){
										if(data>0){
											$.messager.alert('提示', "删除成功");
											grid.load({},{toolbar:toolbar});
										}else{
											$.messager.alert('提示', "删除失败");
										}
									},
									error:function(msg){
										alert(msg.responseText);
									}
									
								});  
						    }    
						});
						
						
					}
				}
			}];

			$(function(){
				$("#cc").css("height",document.documentElement.clientHeight);
				$("#centerDiv").css("height",document.documentElement.clientHeight-190-40);
				grid = new DataGrid("rolegrpManagerGrid","#dg");
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
    	 				<td class="td1" style="width:80px;">
    	 					权限组名称：
    	 				</td>
    	 				<td class="td2">
    	 					<input type="text" id="asRolegrpName">
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
 
  <div id="win"></div>
  </body>
</html>
