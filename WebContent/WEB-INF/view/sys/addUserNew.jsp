<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String userId = request.getParameter("userId") == null ? "" : request.getParameter("userId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增修改用户</title>
</head>
<body>
	<link rel="stylesheet" href="<%=path %>/css/sys/addExistsUser.css"	type="text/css" />
	
	<script type="text/javascript" src="<%=path %>/js/util/md5.js"></script>
	<script type="text/javascript" src="<%=path %>/js/util/JsUtil.js"></script>
		
		<script type="text/javascript">
			$J.importFile("jquery,easyui");
			$J.include("<%=path %>/js/component/UserComp/dataGrid.js");
			$J.include("<%=path %>/css/common/buttonCommont.css");
		</script>
	
	<div id="tt" class="easyui-tabs" style="width:100%;height:376px;">   
    <div title="基本信息" style="height: 100%;">   
        <div style="width: 100%;height: 100%;">
        	<div class="aaron_wrapper">
    	<div class="aaron_add_table">
        	<table>
            	<caption style="color: #000000;font-size: 18px;padding-bottom: 5px;padding-top: 10px;"><strong>用户基本信息</strong></caption>
            	<tr class="aaron_table_selected_col">
                	<td align="right"><span class="aaron_red_star">*</span>用户名：</td>
                    <td class="middle_td"><input name="aaron_user_name" class="aaron_add_txt" type="text" id="ULoginName" maxlength="15"/></td>
                    <td>用户名由3-15个字符组成（不含中文、百分号、下划线）</td>
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
                	<td align="right">姓名：</td>
                    <td><input name="aaron_name" class="aaron_add_txt" type="text" id="userName"/></td>
                    <td></td>                	
                </tr>
                <tr class="aaron_table_selected_col">
                	<td align="right">手机号码：</td>
                    <td><input type="text" maxLength="11" onblur="checkphone()" id="phone" name="phone" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></td>
                    <td></td>
                </tr>
                
                <tr class="aaron_table_selected_col">
                	<td align="right">电子邮箱：</td>
                    <td><input type="email" id="email" name="email"></td>
                    <td></td>
                </tr>
                
                <tr class="aaron_table_selected_col">
                    <td align="right">地区：</td>
                    <td>
                        <select id="normalAreaId" style="width:155px;">
                    		
                    	</select>
                    </td>
                    <td></td>
                </tr>
                <%--<tr class="aaron_table_selected_col">
                	<td align="right">部门：</td>
                    <td>
                    	<select id="deptId" style="width:155px;height: 24px;">

                    	</select>
                    </td>
                    <td></td>
                </tr>--%>
            </table>
            
        </div>
    </div>
        </div>    
    </div>   
    <div title="关联权限组" style="height: 100%;padding:  0 40px 0 20px;">   
        <div class="aaron_wrapper">
    	<div class="aaron_add_table">
        	<table>
        	<caption style="color: #000000;font-size: 18px;padding-bottom: 5px;padding-top: 10px;"><strong>权限组</strong></caption>
        		<tr>
                    <td colspan="2">
                    	<table>
                        	<tr>
                            	<td>待选权限组</td>
                                <td></td>
                                <td>已选权限组</td>
                                <%--<td>区域信息</td>--%>
                            </tr>
                        	<tr>
                            	<td width="155" valign="top">
                                	<select class="rolegrp_list" name="rolegrp_list" multiple="multiple" style="height: 220px;">
                                    </select>
                                </td>
                                <td align="center" width="100" valign="top" valign="top">
                                    	<div style="margin-bottom:15px;margin-top: 40px;"><input class="select_all_rolegrp" type="button" value=">>" /></div>
                                        <div style="margin-bottom:15px;"><input class="select_one_rolegrp" type="button" value=">" /></div>
                                        <div style="margin-bottom:15px;"><input class="remove_one_rolegrp" type="button" value="&lt;" /></div>
                                        <div><input class="remove_all_rolegrp" type="button" value="&lt;&lt;" /></div>
                                </td>
                                <td align="left" width="100" style="width:175px;" valign="top">
                                	<select class="selected_rolegrp_list" name="selected_rolegrp_list" multiple="multiple" style="height: 220px;">
                                    </select>
                                </td>
                                <%--<td style="vertical-align: top;" align="left">--%>
                                <%--<div id="menuSelectDiv" style="min-height: 100px;width: 150px;padding: 0px;">--%>
                                <%--<ul id="ee"></ul> --%>
                                <%--</div>--%>
                                <%--</td>--%>
                            </tr>
                        </table>
                    </td>
                </tr>
        	
        	</table>   
        	</div>
        	</div>
    </div>   
    <div title="关联权限点" style="height: 100%;padding:  0 40px 0 20px;">   
    <div class="aaron_wrapper">
    	<div class="aaron_add_table">
        	<table>
        	<caption style="color: #000000;font-size: 18px;padding-bottom: 5px;padding-top: 10px;"><strong>权限点</strong></caption>
        		<tr>
                    <td colspan="2">
                    	<table>
                        	<tr>
                            	<td>待选权限点</td>
                                <td></td>
                                <td>已选权限点</td>
                                <%--<td>区域信息</td>--%>
                            </tr>
                        	<tr>
                            	<td width="155" valign="top">
                                	<select class="role_list" name="role_list" multiple="multiple" style="height: 220px;">
                                    </select>
                                </td>
                                <td align="center" width="100" valign="top" valign="top">
                                    	<div style="margin-bottom:15px;margin-top: 40px;"><input class="select_all" type="button" value=">>" /></div>
                                        <div style="margin-bottom:15px;"><input class="select_one" type="button" value=">" /></div>
                                        <div style="margin-bottom:15px;"><input class="remove_one" type="button" value="&lt;" /></div>
                                        <div><input class="remove_all" type="button" value="&lt;&lt;" /></div>
                                </td>
                                <td align="left" width="100" style="width:175px;" valign="top">
                                	<select class="selected_role_list" name="selected_role_list" multiple="multiple" style="height: 220px;">
                                    </select>
                                </td>
                                <%--<td style="vertical-align: top;" align="left">--%>
                                <%--<div id="menuSelectDiv" style="min-height: 100px;width: 150px;padding: 0px;">--%>
                                <%--<ul id="dd"></ul> --%>
                                <%--</div>--%>
                                <%--</td>--%>
                            </tr>
                        </table>
                    </td>
                </tr>
        	
        	</table>   
        	</div>
        	</div>
    </div>   
</div>
<script type="text/javascript">
		var g_userId = "<%=userId%>"; 
		var contextPath = "<%=path%>";
</script>
<script type="text/javascript" src ="<%=path %>/js/sys/addExistsUser.js"></script>
</body>
</html>