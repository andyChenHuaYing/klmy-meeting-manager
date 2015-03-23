<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String userName = request.getParameter("userName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>心衰管理系统</title>
<link href="css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript">

	function logout(){
		parent.location.href = "<%=path%>/user.spr?method=logout";
	}
</script>
</head>

<body bgcolor="#008b7f" >
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
   <td width="273"><img src="images/logo.jpg"></td>
   <td>
   	<dl class="UserState">
		<dt ><img src="images/icon01.jpg" align="left"><%=userName %><br><span>admin@*****.com</span></dt>
		<dd ><a href="javascript:logout()">退出</a></dd>
   	</dl>
   </td>
  </tr>
</table>
</body>
</html>