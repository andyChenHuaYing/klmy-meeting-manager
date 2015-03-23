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
		<title>地区管理</title>
		
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
									var areaName = $.trim($(".aaron_add_table input[id='areaName']").val());
									var parentAreaId = $(".aaron_add_table select[id='parentAreaId'] option:selected").val();
									var obj = {};
									if(areaName.length>0){
										obj.areaName = areaName;	
									}else{
										$.messager.alert('提示', "请填写地区名称！");
										return;
									}
									if(parentAreaId.length>0){
										obj.parentAreaId = parentAreaId;
									}
									$.ajax({
										url:$cntPath+"/area.spr?method=addArea",
										data:"areaInfo="+JSON.stringify(obj),
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
							    title: '新增地区',    
							    width: 400,    
							    height: 200,    
							    closed: false,    
							    cache: false,    
							    href: $cntPath+'/area.spr?method=toAddArea',    
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
								$.messager.alert('提示', "请选择要修改的地区！");
								return;
							}
							var areaId = selectObj.areaId;
							var buttons = [{
								iconCls:'icon-save',
								text:'保存',
								handler:function(){
									var areaName = $.trim($(".aaron_add_table input[id='areaName']").val());
									var parentAreaId = $(".aaron_add_table select[id='parentAreaId'] option:selected").val();
									var obj = {};
									obj.areaId = areaId;
									if(areaName.length>0){
										obj.areaName = areaName;	
									}else{
										$.messager.alert('提示', "请填写地区名称！");
										return;
									}
									if(areaId==parentAreaId){
										$.messager.alert('提示', "上级地区不能选择自己！");
										return;
									}
									if(parentAreaId.length>0){
										obj.parentAreaId = parentAreaId;
									}
									$.ajax({
										url:$cntPath+"/area.spr?method=updateArea",
										data:"areaInfo="+JSON.stringify(obj),
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
							    title: '修改地区',    
							    width: 400,    
							    height: 250,    
							    closed: false,    
							    cache: false,    
							    href: $cntPath+'/area.spr?method=toAddArea&areaId='+areaId+"&areaName="+encodeURI(encodeURI(selectObj.areaName))+"&parentAreaId="+selectObj.parentAreaId,    
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
								$.messager.alert('提示', "请选择要删除的地区！");	
								return;
							}else{
								
								$.messager.confirm('确认','您确认要删除该地区吗？',function(r){    
								    if (r){    
								    	$.ajax({
											url:$cntPath+"/area.spr?method=deleteArea",
											data:"areaId="+selectObj.areaId,
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
					    url:$cntPath+'/area.spr?method=queryAreaTreeForVo',    
					    idField:'areaId',    
					    treeField:'areaName',    
					    height:"100%",
					    width:"100%",
					    animate: true,
						collapsible: true,
						fitColumns: true,
						toolbar:toolbar,
					    columns:[[    
							{title:'id',field:'areaId',width:180,hidden:true},
					        {title:'地区名称',field:'areaName',width:180,align:'left'},    
					        {field:'parentAreaId',title:'上级地区ID',width:80,hidden:true},    
					        {field:'parentAreaName',title:'上级地区名称',width:80,align:'center'}    
					    ]]    
					});  
				};
				
				$("#cc").css("height",document.documentElement.clientHeight);
				$("#centerDiv").css("height",document.documentElement.clientHeight-190-40);
				loadTreeGrid();
				setHeight("cc");
			});
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
    <div data-options="region:'center',title:'组织架构'" style="background:#eee;" id="centerDiv">
    	<table id="dg" class="easyui-treegrid">
  		</table>
    </div>   
</div> 
<div id="win">

</div>
  		
</body>
</html>
