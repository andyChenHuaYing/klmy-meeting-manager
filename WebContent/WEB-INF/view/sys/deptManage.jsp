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
		<title>组织架构管理</title>
		
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
				var loadTreeGrid = function(){
					var toolbar = [{
						text:'新增',
						iconCls: 'icon-add',
						handler: function(){
							var buttons = [{
								iconCls:'icon-save',
								text:'保存',
								handler:function(){
									var name = $.trim($(".aaron_add_table input[id='name']").val());
									var parentDepartId = $(".aaron_add_table select[id='parentDepartId'] option:selected").val();
									var areaId = $(".aaron_add_table select[id='areaId'] option:selected").val();
									var obj = {};
									if(name.length>0){
										obj.name = name;	
									}else{
										$.messager.alert('提示', "请填写部门名称！");
										return;
									}
									if(parentDepartId.length>0){
										obj.parentDepartId = parentDepartId;
									}
									if(areaId.length>0){
										obj.areaId = areaId;
									}
									$.ajax({
										url:$cntPath+"/dept.spr?method=addDept",
										data:"deptInfo="+JSON.stringify(obj),
										method:"post",
										dataType:"json",
										async:false,
										success:function(data){
											if(data>0){
												$.messager.alert('提示', "保存成功");
												$('#win').dialog("close");
												$('#dg').treegrid('load',{});
											}else if(data == -1){
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
							    title: '新增组织架构',    
							    width: 400,    
							    height: 250,    
							    closed: false,    
							    cache: false,    
							    href: $cntPath+'/dept.spr?method=toAddDept',    
							    modal: true,
							    buttons : buttons
							});  
						}
					},'-',{
						text:'修改',
						iconCls: 'icon-edit',
						handler: function(){
							var selectObj = $("#dg").treegrid('getSelected');
							if(selectObj==null){
								$.messager.alert('提示', "请选择要修改的部门！");
								return;
							}
							var deptId = selectObj.departId;
							var buttons = [{
								iconCls:'icon-save',
								text:'保存',
								handler:function(){
									var name = $.trim($(".aaron_add_table input[id='name']").val());
									var parentDepartId = $(".aaron_add_table select[id='parentDepartId'] option:selected").val();
									var areaId = $(".aaron_add_table select[id='areaId'] option:selected").val();
									var obj = {};
									obj.departId = deptId;
									if(name.length>0){
										obj.name = name;	
									}else{
										$.messager.alert('提示', "请填写部门名称！");
										return;
									}
									if(parentDepartId.length>0){
										obj.parentDepartId = parentDepartId;
									}
									if(areaId.length>0){
										obj.areaId = areaId;
									}
									$.ajax({
										url:$cntPath+"/dept.spr?method=updateDept",
										data:"deptInfo="+JSON.stringify(obj),
										method:"post",
										dataType:"json",
										async:false,
										success:function(data){
											if(data>0){
												$.messager.alert('提示', "修改成功");
												$('#win').dialog("close");
												$('#dg').treegrid('load',{});
											}else if(data == -1){
												 $.messager.alert('提示', "名称已存在");
											 }else{
												$.messager.alert('提示', "修改失败");
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
							    title: '修改组织架构',    
							    width: 400,    
							    height: 250,    
							    closed: false,    
							    cache: false,    
							    href: $cntPath+'/dept.spr?method=toAddDept&deptId='+deptId+"&name="+encodeURI(encodeURI(selectObj.name))+"&areaId="+selectObj.areaId+"&parentDepartId="+selectObj.parentDepartId,    
							    modal: true,
							    buttons : buttons
							});  
							
						}
					},'-',{
						text:'删除',
						iconCls: 'icon-remove',
						handler: function(){
							var selectObj = $("#dg").treegrid('getSelected');
							if(selectObj==null){
								$.messager.alert('提示', "请选择要删除的部门！");	
								return;
							}else{
								
								$.messager.confirm('确认','您确认要删除该部门吗？',function(r){    
								    if (r){    
								    	$.ajax({
											url:$cntPath+"/dept.spr?method=deleteDept",
											data:"deptId="+selectObj.departId,
											method:"post",
											dataType:"json",
											async:false,
											success:function(data){
												if(data>0){
													$.messager.alert('提示', "删除成功");
													$('#dg').treegrid('load',{});
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
					$('#dg').treegrid({    
					    url:$cntPath+'/dept.spr?method=queryDeptTree',    
					    idField:'departId',    
					    treeField:'name',    
					    height:"100%",
					    width:"100%",
					    animate: true,
						collapsible: true,
						fitColumns: true,
						toolbar:toolbar,
					    columns:[[    
							{title:'id',field:'departId',width:180,hidden:true},
					        {title:'部门名称',field:'name',width:180,align:'left'},    
					        {field:'parentDepartId',title:'上级部门ID',width:60,align:'center',hidden:true},
					        {field:'parentDepartName',title:'上级部门名称',width:120,align:'center'},    
					        {field:'areaId',title:'地区ID',width:80,hidden:true},    
					        {field:'areaName',title:'地区名称',width:80,align:'center'}    
					    ]]    
					});  
				};
				
				$("#cc").css("height",document.documentElement.clientHeight);
				$("#centerDiv").css("height",document.documentElement.clientHeight-190-40);
				loadTreeGrid();
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
				for(var i=0;i<selects.length;i++){
					queryCondition[$(selects[i]).attr("id")] = $.trim($(selects[i]).val());
				}
				//grid.load({queryCondition:JSON.stringify(queryCondition)});
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
			width: 100px;
			text-align: left;
			/*background: url('images/sys/tablespan.jpg') repeat scroll 0% 0% transparent;*/
		}
		.queryPropTable .td2{
			width: 140px;
			text-align: right;			
		}
		</style>
	</head>
 <body style="margin: 0px;overflow: hidden;">
 <div id="cc" class="easyui-layout" style="width:100%;height:600px;fit:true;">   
 <!-- 
    <div data-options="region:'north',title:'查询条件',split:true" style="height:130px;">
    	 <ul style="margin-left: 0px;padding-left: 10px;" id="queryConditionUL">
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					部门名称：
    	 				</td>
    	 				<td class="td2">
    	 					<input type="text" id="deptName">
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
    </div>    -->
    <div data-options="region:'center',title:'组织架构'" style="background:#eee;" id="centerDiv">
    	<table id="dg" class="easyui-treegrid">
  		</table>
    </div>   
</div> 
<div id="win">

</div>
  		
</body>
</html>
