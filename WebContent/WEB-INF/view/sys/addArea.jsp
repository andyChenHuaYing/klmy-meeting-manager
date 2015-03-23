<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String areaId = request.getParameter("areaId")==null?"":request.getParameter("areaId");	
	String areaName = request.getParameter("areaName")==null?"":java.net.URLDecoder.decode(request.getParameter("areaName"),"UTF-8");
	String parentAreaId = request.getParameter("parentAreaId")==null?"":request.getParameter("parentAreaId");
%>

<!DOCTYPE html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>新增修改地区信息</title>
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
            	<caption style="font-size: 18px;padding-bottom: 10px;padding-top: 10px;"><strong>新建地区</strong></caption>
            	<tr>
            		<td align="right">地区名称：</td>
            		<td align="left"><input id="areaName" type="text"></td>
            	</tr>
            	<tr>
            		<td align="right">上级地区：</td>
            		<td align="left">
						<select id="parentAreaId" style="width: 154px;height: 23px;">
						</select>
					</td>
            	</tr>
            </table>
        </div>
    </div>
    <script type="text/javascript">
    var g_areaId = "<%=areaId%>"; 
    var areaName = "<%=areaName%>";
    var parentAreaId = "<%=parentAreaId%>";
	var contextPath = "<%=path%>";
	$.ajax({
		url:$cntPath+"/area.spr?method=queryCondition",
		method:"post",
		dataType:"json",
		success:function(data){
			if(data){
				if(data.areaList){
					var html = "<option value=''>--请选择--</option>";
					for(var i=0;i<data.areaList.length;i++){
						html+="<option value='"+data.areaList[i].areaId+"' style='padding-left:"+15*(data.areaList[i].areaLevel-1)+"px'>"+data.areaList[i].areaName+"</option>";
					}
					$("#parentAreaId").append(html);
				}
				if(g_areaId!=null && g_areaId!=""){
					$("caption strong").text("修改地区");
					$("#areaName").val(areaName);
					$("#parentAreaId option[value='"+parentAreaId+"']").attr("selected","selected");
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
