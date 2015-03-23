<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Long userId = Long.valueOf(request.getParameter("userId"));
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置用户查询界面表头</title>
</head>
<body>
	<script type="text/javascript" src ="<%=path %>/js/util/JsUtil.js"></script>
	<script type="text/javascript">
		$J.importFile("jquery,easyui");
	</script>
	<style type="text/css">		
		#columnUL1 {
		    height: auto;
		    margin: 0 auto;
		    padding-left: 0;
		    padding-top: 20px;
		    width: 600px;
		}
		#columnUL1 li {
		    display: inline-block;
		    padding-top: 5px;
		    vertical-align: top;
		    width: 150px;
		}
	</style>
	<div class="aaron_wrapper">
    	<div class="aaron_add_table">
    		<input type="hidden" id="deptId">
        	<table style="text-align: center;width: 360px;margin: 0 auto;">
            	<caption style="font-size: 18px;padding-bottom: 10px;padding-top: 10px;"><strong>设置查询展示列</strong></caption>
            	<tr>
            		<td align="right">查询页面：</td>
            		<td align="left">
						<select id="tableId" style="width: 154px;height: 23px;" onchange="loadTabColumn(this.value);">
						</select>
					</td>
            	</tr>
            </table>
        </div>
    </div>
	<ul id="columnUL1">
		<li style="width: 100%;margin-bottom: 10px;">请选择该用户能显示的列&nbsp&nbsp&nbsp&nbsp
		<a class="sts-link-btn" onclick="selectAll();" href="javascript:void(0);" style="margin: 0">
								<span class="sts-link-btn-span">全选</span>
							</a>
 			<a class="sts-link-btn" onclick="offsetSelect();" href="javascript:void(0);" style="margin: 0">
								<span class="sts-link-btn-span">反选</span>
			</a>
		
		</li>
	</ul>
	<script type="text/javascript">
	var userId = <%=userId%>;
	var tabColumnMap = {};   //键为tableId,值为表头数组
	var currentTableId = null;
	
	//初始化用户信息
	$.ajax({
		url:$cntPath+"/tableConfig.spr?method=queryUserTabHeaderMap",
		data:"userId="+userId,
		method:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if(data){
				tabColumnMap = data;
			}
		},
		error:function(msg){
			$.messager.alert(msg.responseText);
		}
	});
	
	$.ajax({
		url:$cntPath+"/tableConfig.spr?method=queryAllTable",
		method:"post",
		dataType:"json",
		success:function(data){
			if(data){
				var html = "";
				for(var i=0;i<data.length;i++){
					html+="<option value="+data[i].id+">"+data[i].note+"</option>";
				}
				$("#tableId").append(html);
				loadTabColumn($("#tableId").val());
			}
		},
		error:function(msg){
			$.messager.alert(msg.responseText);
		}
		
	});
	
	
	function loadTabColumn(tableId){
			if(currentTableId){
				if(!tabColumnMap[currentTableId]){
					tabColumnMap[currentTableId] = [];
				}
				var checkboxs = $("#columnUL1 li input");
				for(var i=0;i<checkboxs.length;i++){
					if($(checkboxs[i]).prop("checked")){
						tabColumnMap[currentTableId].push($(checkboxs[i]).attr("id"));
					}
				}
				$.ajax({
					url:$cntPath+"/tableConfig.spr?method=queryTableConfigInfo&tableId="+tableId,
					method:"post",
					dataType:"json",
					success:function(data){
						if(data){
							
							$("#columnUL1").html('<li style="width: 100%;margin-bottom: 10px;">请选择该用户能显示的列&nbsp&nbsp&nbsp&nbsp' +
									'<a class="sts-link-btn" onclick="selectAll();" href="javascript:void(0);" style="margin: 0"> '+
									'								<span class="sts-link-btn-span">全选</span> '+
									'							</a> '+
									' 			<a class="sts-link-btn" onclick="offsetSelect();" href="javascript:void(0);" style="margin: 0"> '+
									'								<span class="sts-link-btn-span">反选</span> '+
									'			</a></li> '
							
							);
							var html = "";
							if(data.tableHeaderInfo){//初始化表头
								for(var i=0;i<data.tableHeaderInfo.length;i++){
									if(data.tableInfo.idfield!=data.tableHeaderInfo[i].field){
										html+='<li><input type="checkbox" id="'+data.tableHeaderInfo[i].field+'">'+data.tableHeaderInfo[i].title+'</li>';	
									}else{
										html+='<li><input type="checkbox" id="'+data.tableHeaderInfo[i].field+'" disabled="disabled" checked="checked">'+data.tableHeaderInfo[i].title+'</li>';
									}
									
								}
							}
							$("#columnUL1").append(html);
							
							//
							if(tabColumnMap[tableId]){
								for(var j=0;j<tabColumnMap[tableId].length;j++){
									$("#"+tabColumnMap[tableId][j]).prop("checked",true);
								}
							}
							currentTableId = tableId;
						}
					},
					error:function(msg){
						$.messager.alert(msg.responseText);
					}
					
				});
				
				
			}else if(tabColumnMap[tableId]){
				$.ajax({
					url:$cntPath+"/tableConfig.spr?method=queryTableConfigInfo&tableId="+tableId,
					method:"post",
					dataType:"json",
					success:function(data){
						if(data){
							
							$("#columnUL1").html('<li style="width: 100%;margin-bottom: 10px;">请选择该用户能显示的列&nbsp&nbsp&nbsp&nbsp' +
									'<a class="sts-link-btn" onclick="selectAll();" href="javascript:void(0);" style="margin: 0"> '+
									'								<span class="sts-link-btn-span">全选</span> '+
									'							</a> '+
									' 			<a class="sts-link-btn" onclick="offsetSelect();" href="javascript:void(0);" style="margin: 0"> '+
									'								<span class="sts-link-btn-span">反选</span> '+
									'			</a></li> ');
							var html = "";
							if(data.tableHeaderInfo){//初始化表头
								for(var i=0;i<data.tableHeaderInfo.length;i++){
									if(data.tableInfo.idfield!=data.tableHeaderInfo[i].field){
										html+='<li><input type="checkbox" id="'+data.tableHeaderInfo[i].field+'">'+data.tableHeaderInfo[i].title+'</li>';	
									}else{
										html+='<li><input type="checkbox" id="'+data.tableHeaderInfo[i].field+'" disabled="disabled" checked="checked">'+data.tableHeaderInfo[i].title+'</li>';
									}
									
								}
							}
							$("#columnUL1").append(html);
							if(tabColumnMap[tableId]){
								for(var j=0;j<tabColumnMap[tableId].length;j++){
									$("#"+tabColumnMap[tableId][j]).prop("checked",true);
								}
							}
							currentTableId = tableId;
						}
					},
					error:function(msg){
						$.messager.alert(msg.responseText);
					}
					
				});
			}else{
				$.ajax({
					url:$cntPath+"/tableConfig.spr?method=queryTableConfigInfo&tableId="+tableId,
					method:"post",
					dataType:"json",
					success:function(data){
						if(data){
							
							$("#columnUL1").html('<li style="width: 100%;margin-bottom: 10px;">请选择该用户能显示的列&nbsp&nbsp&nbsp&nbsp' +
									'<a class="sts-link-btn" onclick="selectAll();" href="javascript:void(0);" style="margin: 0"> '+
									'								<span class="sts-link-btn-span">全选</span> '+
									'							</a> '+
									' 			<a class="sts-link-btn" onclick="offsetSelect();" href="javascript:void(0);" style="margin: 0"> '+
									'								<span class="sts-link-btn-span">反选</span> '+
									'			</a> </li>');
							var html = "";
							if(data.tableHeaderInfo){//初始化表头
								for(var i=0;i<data.tableHeaderInfo.length;i++){
									if(data.tableInfo.idfield!=data.tableHeaderInfo[i].field){
										html+='<li><input type="checkbox" id="'+data.tableHeaderInfo[i].field+'">'+data.tableHeaderInfo[i].title+'</li>';	
									}else{
										html+='<li><input type="checkbox" id="'+data.tableHeaderInfo[i].field+'" disabled="disabled" checked="checked">'+data.tableHeaderInfo[i].title+'</li>';
									}
									
								}
							}
							$("#columnUL1").append(html);
							currentTableId = tableId;
							tabColumnMap[currentTableId] = [];
						}
					},
					error:function(msg){
						$.messager.alert(msg.responseText);
					}
					
				});
			}
			
	}
	function selectAll(){
		$("#columnUL1 input").prop("checked",true);
	}
	function offsetSelect(){
		$("#columnUL1 input").each(function(){
			if(!$(this).prop("disabled")){
				$(this).prop("checked",!$(this).prop("checked"));	
			}
		});
	}
	</script>
	
	
</body>
</html>