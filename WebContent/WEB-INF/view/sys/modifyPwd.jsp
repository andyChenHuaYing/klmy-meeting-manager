<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>修改密码</title>
	</head>
 <html> 
  <body>
  		<link rel="stylesheet" href="<%=path %>/js/jquery-easyui-1.4/themes/default/easyui.css" type="text/css" />
  		<link rel="stylesheet" href="<%=path %>/js/jquery-easyui-1.4/themes/icon.css" type="text/css" />
  		<script type="text/javascript" src ="<%=path %>/js/util/JsUtil.js"></script>
  		<script type="text/javascript" src ="<%=path %>/js/jquery/jquery-1.9.1.js"></script>
  		<script type="text/javascript" src ="<%=path %>/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
  		<script type="text/javascript" src ="<%=path %>/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
  		
		<div class="aaron_wrapper">
    	<div class="aaron_add_table">
        	<table style="text-align: center;width: 360px;margin: 0 auto;">
            	<caption style="font-size: 18px;padding-bottom: 10px;padding-top: 10px;"><strong>修改密码</strong></caption>
            	<tr>
            		<td align="right" style="padding-bottom: 10px;">新密码：</td>
            		<td align="left" style="padding-bottom: 10px;"><input id="pwd" type="password"></td>
            		<td style="padding-bottom: 10px;">最小长度为6个字符</td>
            	</tr>
            	<tr>
            		<td align="right">重复新密码：</td>
            		<td align="left">
						<input id="repeatPwd" type="password">
					</td>
					<td></td>
            	</tr>
            </table>
        </div>
    </div>
  </body>
</html>
