<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String userId = request.getParameter("userId")==null?"":request.getParameter("userId");	
%>

<!DOCTYPE html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>用户管理</title>
		<link rel="stylesheet" href="<%=path %>/js/component/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<link rel="stylesheet" href="<%=path %>/js/component/zTree_v3/css/demo.css" type="text/css">
		<link rel="stylesheet" href="<%=path %>/css/sys/addExistsUser.css"	type="text/css" />
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/js/component/xtree2b/css/xtree2.css"> 
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/js/component/xtree2b/css/xtree2.links.css">
		
	</head>
	<script type="text/javascript" src ="<%=path %>/js/util/JsUtil.js"></script>
	<script type="text/javascript" src ="<%=path %>/js/jquery/jquery-1.9.1.js"></script>
<%-- 	<script type="text/javascript" src ="<%=path %>/js/component/zTree_v3/js/jquery-1.4.4.min.js"></script> --%>
	<script type="text/javascript" src ="<%=path %>/js/sys/addExistsUser.js"></script>
	<script type="text/javascript" src="<%=path %>/js/component/xtree2b/js/xtree2.js"></script> 
	<script type="text/javascript" src="<%=path %>/js/component/xtree2b/js/xloadtree2.js"></script>
	<script type="text/javascript" src="<%=path %>/js/component/departTree.js"></script>
	<script type="text/javascript" src="<%=path %>/js/component/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/util/md5.js"></script>
	<script type="text/javascript" src ="<%=path %>/js/component/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src ="<%=path %>/js/component/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src ="<%=path %>/js/component/zTree_v3/js/jquery.ztree.exedit-3.5.js"></script>
	<script type="text/javascript">
		var g_userId = "<%=userId%>"; 
		var contextPath = "<%=path%>";
	</script>
 <html> 
  <body>
		<div class="aaron_wrapper">
    	<div class="aaron_add_table">
        	<table id="basicInfo">
            	<caption><strong>新建用户</strong></caption>
            	<tr class="aaron_table_selected_col">
                	<td align="right"><span class="aaron_red_star">*</span>用户名：</td>
                    <td class="middle_td"><input name="aaron_user_name" class="aaron_add_txt" type="text" id="ULoginName"/></td>
                    <td>用户名由3-15个字符组成（不含中文字符）</td>
                </tr>
                <tr>
                	<td align="right"><span class="aaron_red_star">*</span>密码：</td>
                    <td><input name="aaron_pass" class="aaron_add_txt" type="password" id="addUser_uPassword"/></td>
                    <td>请填写密码，最小长度为6个字符（不含中文字符）</td>
                </tr>
                <tr class="aaron_table_selected_col">
                	<td align="right"><span class="aaron_red_star">*</span>确认密码：</td>
                    <td><input name="aaron_repass" class="aaron_add_txt" type="password" id="addUser_testUPassword"/></td>
                    <td>请再次输入密码（不含中文字符）</td>
                </tr>
                <tr>
                	<td align="right"><span class="aaron_red_star">*</span>姓名：</td>
                    <td><input name="aaron_name" class="aaron_add_txt" type="text" id="userName"/></td>
                    <td>请填写您的真实姓名</td>                	
                </tr>
                <tr class="aaron_table_selected_col">
                	<td align="right"><span class="aaron_red_star">*</span>手机号码：</td>
                    <td><input type="text" maxLength="11" onblur="checkphone()" id="phone" name="phone"></td>
                    <td>绑定的手机号码注册后不能修改</td>
                </tr>
                
                <tr class="aaron_table_selected_col">
                	<td align="right"><span class="aaron_red_star">*</span>电子邮箱：</td>
                    <td><input type="text" id="email" name="email"></td>
                    <td>请填写正确的电子邮箱</td>
                </tr>
                
                <tr class="aaron_table_selected_col">
                	<td align="right">部门：</td>
                    <td><input name="aaron_dep" class="aaron_add_txt_dep" readonly="readonly" type="text" value="请选择……" /></td>
                    <td><input class="select_dep" type="button" value="选择" /></td>
                </tr>
                
                <tr>
                	<td align="right" valign="top">角色：</td>
                    <td colspan="2">
                    	<table>
                        	<tr>
                            	<td>待选角色</td>
                                <td></td>
                                <td>已选角色</td>
                                <td>区域信息</td>
                            </tr>
                        	<tr>
                            	<td width="155">
                                	<select class="role_list" name="role_list" multiple="multiple" style="height: 220px;">
                                    	<!-- <option value="1">科室主任</option>
                                        <option value="2">主任医师</option>
                                        <option value="3">副主任医师</option>
                                        <option value="4">医师</option>
                                        <option value="5">实习医师</option> -->
                                    </select>
                                </td>
                                <td align="center" width="100" valign="top" style="vertical-align: middle;">
                                    	<div style="margin-bottom:15px;"><input class="select_all" type="button" value=">>" /></div>
                                        <div style="margin-bottom:15px;"><input class="select_one" type="button" value=">" /></div>
                                        <div style="margin-bottom:15px;"><input class="remove_one" type="button" value="&lt;" /></div>
                                        <div><input class="remove_all" type="button" value="&lt;&lt;" /></div>
                                </td>
                                <td align="left" width="100" style="width:175px;">
                                	<select class="selected_role_list" name="selected_role_list" multiple="multiple" style="height: 220px;">
                                    </select>
                                </td>
                                <td style="vertical-align: top;" align="left">
                            		<div id="menuSelectDiv" class="ztree" style="min-height: 100px;width: 200px;padding: 0px;">
                                    
                                    </div>
                            	</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                
                
                <tr>
                    <td colspan="3" style="padding-left:20%;padding-top:40px;">
                    	<input type="button" value="确定" id="confirmBtn"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
  </body>
</html>
