<%@ page language="java" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String roleId = request.getParameter("roleId")==null?"":request.getParameter("roleId");	
%>

<!DOCTYPE html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>新增修改权限</title>
		
	</head>
 <html> 
  <body>
	<link rel="stylesheet" href="<%=path %>/css/sys/addRole.css" type="text/css" />
	<script type="text/javascript" src="<%=path %>/js/util/JsUtil.js"></script>
		
		<script type="text/javascript">
			$J.importFile("jquery,easyui");
			$J.include("<%=path %>/js/component/UserComp/dataGrid.js");
			$J.include("<%=path %>/css/common/buttonCommont.css");
		</script>
		<div class="aaron_wrapper">
    	<div class="aaron_add_table">
        	<table style="text-align: center;width: 360px;margin: 0 auto;">
            	<caption style="font-size: 18px;padding-bottom: 10px;padding-top: 10px;color: #000000;font-weight: bold;"><strong>新建权限</strong></caption>
            	<tr>
                	<td align="right" style="width:120px;">权限名：</td>
                    <td class="middle_td" style="width: 240px;" align="left"><input name="aaron_user_name" class="aaron_add_txt" type="text" id="ULoginName"/></td>
                </tr>
                
                <tr>
                	<td align="right">菜单分类：</td>
                    <td class="middle_td" align="left">
                    	<select id="topMenu" onchange="loadMenu(this.value);">
							<option value="comprehensivequery" selected="selected">会议管理</option>
				               <option value="statisticalform">统计报表</option>
							<option value="systemconfig">系统管理</option>
                    	</select>
                    </td>
                </tr>
                
                <tr class="aaron_table_selected_col">
                	<td align="right" style="vertical-align: top">菜单：</td>
                    <td colspan="2">
                    	<div id="menuSelectDiv" class="ztree" style="min-height: 100px;width: 200px;text-align: left;">
                    	     <ul id="dd"></ul>       	
                    	</div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <script type="text/javascript">
		var g_roleId = "<%=roleId%>"; 
		var contextPath = "<%=path%>";
	</script>
	<script type="text/javascript" src ="<%=path %>/js/sys/addRole.js"></script>
  </body>
</html>
