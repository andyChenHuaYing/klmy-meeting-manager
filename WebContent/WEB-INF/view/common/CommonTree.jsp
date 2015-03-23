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
		<title>组织架构</title>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/js/component/xtree2b/css/xtree2.css"> 
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/js/component/xtree2b/css/xtree2.links.css">
	</head>
	<script type="text/javascript" src ="<%=path %>/js/util/JsUtil.js"></script>
	<script type="text/javascript" src ="<%=path %>/js/jquery/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=path %>/js/component/xtree2b/js/xtree2.js"></script> 
	<script type="text/javascript" src="<%=path %>/js/component/xtree2b/js/xloadtree2.js"></script>
	<script type="text/javascript" src="<%=path %>/js/component/departTree.js"></script>
	<script type="text/javascript">
		var rtnObj = {};	
		$(function(){
			var departmentTree = new departTree("departSelectDiv");
			$("#confirmBtn").click(function(){
				window.returnValue = rtnObj;	
				window.close();
			});
			
			$("#cancelBtn").click(function(){
				window.returnValue = null;	
				window.close();
			});
		});
		function departClick(departId,departName){
			rtnObj.departId = departId;
			rtnObj.departName = departName;
		}
	</script>
	
 <html> 
  <body style="background-color: rgba(201,201,201,0.3)">
            <div id="departSelectDiv" style="display: block;margin: 0 auto;position: relative;top: 10px;width: 160px;">                        
            </div>
            <div id="btnDiv" style="width:160px;position: relative;display: block;margin: 0 auto;top:30px">
            	<span id="confirmSpan">
            		<input type="button" value="确认" id="confirmBtn">
            		<input type="button" value="取消" id="cancelBtn">
            	</span>            	           	
            </div>
  </body>
</html>
