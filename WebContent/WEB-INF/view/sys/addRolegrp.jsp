<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String rolegrpId = request.getParameter("rolegrpId")==null?"":request.getParameter("rolegrpId");	
	String rolegrpName = request.getParameter("rolegrpName")==null?"":java.net.URLDecoder.decode(request.getParameter("rolegrpName"),"UTF-8");
%>

<!DOCTYPE html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>新增修改权限组信息</title>
		
	</head>
 <html> 
  <body>
  		<script type="text/javascript" src ="<%=path %>/js/util/JsUtil.js"></script>
  		<link href="css/common/buttonCommont.css">
		<script type="text/javascript">
			$J.importFile("jquery");
		</script>
		
		<div class="aaron_wrapper">
    	<div class="aaron_add_table">
        	<table style="text-align: center;width: 360px;margin: 0 auto;">
            	<caption style="font-size: 18px;padding-bottom: 10px;padding-top: 10px;"><strong>新建权限组</strong></caption>
            	<tr>
            		<td align="left" colspan="3">权限组名称：<input id="rolegrpName" type="text"></td>
            	</tr>
            	<tr>
            		<td align="left" style="padding-top: 8px;padding-bottom: 8px;">待选权限点列表：</td>
            		<td></td>
            		<td align="left">已选权限点列表：</td>
            	</tr>
            	
            	<tr>
            		<td align="left">
						<select id="roleList" multiple="multiple" style="height: 220px;width: 155px;">
                        </select>
					</td>
					<td>
						<a class="sts-link-btn" onclick="selectAll();" href="javascript:void(0);" style="padding-right: 19px;margin-bottom: 5px;">
							<span class="sts-link-btn-span" style="padding-left: 19px;">>></span>
						</a>
						<a class="sts-link-btn" onclick="selectOne();" href="javascript:void(0);" style="margin-bottom: 5px;">
							<span class="sts-link-btn-span">></span>
						</a>
						<a class="sts-link-btn" onclick="removeOne();" href="javascript:void(0);" style="margin-bottom: 5px;">
							<span class="sts-link-btn-span">&lt;</span>
						</a>
						<a class="sts-link-btn" onclick="removeAll();" href="javascript:void(0);" style="padding-right: 19px;">
							<span class="sts-link-btn-span"  style="padding-left: 19px;">&lt;&lt;</span>
						</a>
					</td>
            		<td align="left">
						<select id="selectedRoleList" multiple="multiple" style="height: 220px;width: 155px;">
                        </select>
					</td>
            	</tr>
            </table>
        </div>
    </div>
    <script type="text/javascript">
    var g_rolegrpId = "<%=rolegrpId%>"; 
    var rolegrpName = "<%=rolegrpName%>";
	var contextPath = "<%=path%>";
	
	$.ajax({
		url:$cntPath+"/rolegrp.spr?method=queryRoleList",
		data:"rolegrpId="+g_rolegrpId,				
		method:"post",
		dataType:"json",
		success:function(data){
			if(data){
				if(data.roleList){
					var html = "";
					for(var i=0;i<data.roleList.length;i++){
						html+="<option value='"+data.roleList[i].ROLE_ID+"'>"+data.roleList[i].ROLE_NAME+"</option>";
					}
					$("#roleList").append(html);
				}
				if(data.selectedRoleList){
					var html = "";
					for(var i=0;i<data.selectedRoleList.length;i++){
						html+="<option value='"+data.selectedRoleList[i].ROLE_ID+"'>"+data.selectedRoleList[i].ROLE_NAME+"</option>";
					}
					$("#selectedRoleList").append(html);
				}
				if(g_rolegrpId!=null && g_rolegrpId!=""){
					$("caption strong").text("修改权限组");
					$("#rolegrpName").val(rolegrpName);
				}
			}
		},
		error:function(msg){
			alert(msg.responseText);
		}
		
	});
  	function selectAll(){
  		$("#roleList option").each(function(){
  			$("#selectedRoleList").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
  			$(this).remove();
  		});
  	}
  	function selectOne(){
  		$("#roleList option:selected").each(function(){
  			$("#selectedRoleList").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
  			$(this).remove();
  		});
  	}
  	function removeAll(){
  		$("#selectedRoleList option").each(function(){
  			$("#roleList").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
  			$(this).remove();
  		});
  	}
  	function removeOne(){
  		$("#selectedRoleList option:selected").each(function(){
  			$("#roleList").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
  			$(this).remove();
  		});
  	}
  </script>
  </body>
</html>