<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String deptId = request.getParameter("deptId")==null?"":request.getParameter("deptId");	
	String name = request.getParameter("name")==null?"":java.net.URLDecoder.decode(request.getParameter("name"),"UTF-8");
	String areaId = request.getParameter("areaId")==null?"":request.getParameter("areaId");
	String parentDepartId = request.getParameter("parentDepartId")==null?"":request.getParameter("parentDepartId");
%>

<!DOCTYPE html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>新增修改组织架构信息</title>
		<link rel="stylesheet" href="<%=path %>/js/component/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<link rel="stylesheet" href="<%=path %>/css/sys/addRole.css" type="text/css" />
		<link rel="stylesheet" href="<%=path %>/js/component/zTree_v3/css/demo.css" type="text/css">
	</head>
	
	
 <html> 
  <body>
  		<script type="text/javascript" src ="<%=path %>/js/util/JsUtil.js"></script>
		<script type="text/javascript">
			$J.importFile("jquery");
		</script>
		<div class="aaron_wrapper">
    	<div class="aaron_add_table">
    		<input type="hidden" id="deptId">
        	<table style="text-align: center;width: 360px;margin: 0 auto;">
            	<caption style="font-size: 18px;padding-bottom: 10px;padding-top: 10px;"><strong>新建部门</strong></caption>
            	<tr>
            		<td align="right">部门名称：</td>
            		<td align="left"><input id="name" type="text"></td>
            	</tr>
            	<tr>
            		<td align="right">上级部门：</td>
            		<td align="left">
						<select id="parentDepartId" style="width: 154px;height: 23px;">
						</select>
					</td>
            	</tr>
            	
            	<tr>
            		<td align="right">所属地区：</td>
            		<td align="left">
						<select id="areaId" style="width: 154px;height: 23px;"></select>
					</td>
            	</tr>
            </table>
        </div>
    </div>
    <script type="text/javascript">
    var g_deptId = "<%=deptId%>"; 
    var name = "<%=name%>";
    var areaId = "<%=areaId%>";
    var parentDepartId = "<%=parentDepartId%>";
	var contextPath = "<%=path%>";
  	$("#deptId").val(g_deptId);
	$.ajax({
		url:$cntPath+"/dept.spr?method=queryCondition",
		method:"post",
		dataType:"json",
		success:function(data){
			if(data){
				if(data.areaList){
					var html = "<option value=''>--请选择--</option>";
					for(var i=0;i<data.areaList.length;i++){
						html+="<option value='"+data.areaList[i].areaId+"' style='padding-left:"+15*(data.areaList[i].areaLevel-1)+"px'>"+data.areaList[i].areaName+"</option>";
					}
					$("#areaId").append(html);
				}
				if(data.deptList){
					var html = "<option value=''>--请选择--</option>";
					for(var i=0;i<data.deptList.length;i++){
						html+="<option value='"+data.deptList[i].departId+"' style='padding-left:"+15*(data.deptList[i].deptLevel-1)+"px'>"+data.deptList[i].name+"</option>";
					}
					$("#parentDepartId").append(html);
				}
				if(g_deptId!=null && g_deptId!=""){
					$("caption strong").text("修改部门");
					$("#name").val(name);
					$("#areaId option[value='"+areaId+"']").attr("selected","selected");
					$("#parentDepartId option[value='"+parentDepartId+"']").attr("selected","selected");
				}
			}
		},
		error:function(msg){
			alert(msg.responseText);
		}
		
	});
  
  </script>
  </body>
</html>
