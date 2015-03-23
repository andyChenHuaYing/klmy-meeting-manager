<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String oprType = request.getParameter("oprType")==null?"add":request.getParameter("oprType");
	String pageTag = request.getParameter("pageTag")==null?"1":request.getParameter("pageTag");
	String recordId = request.getParameter("recordId")==null?"":request.getParameter("recordId");
	
%>

<!DOCTYPE html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>档案</title>
		<!--  --><link rel="stylesheet" href="<%=path %>/css/common/commonPage.css"	type="text/css" />
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/JS/component/xtree2b/css/xtree2.css"> 
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/JS/component/xtree2b/css/xtree2.links.css">
	</head>
	<script type="text/javascript" src ="<%=path %>/js/util/JsUtil.js"></script>
	<script type="text/javascript" src ="<%=path %>/js/common/commonPage.js"></script>	
	<script type="text/javascript" src ="<%=path %>/js/jquery/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=path %>/js/component/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/common/loading.js"></script>
	<script type="text/javascript" >
		var g={
				oprType:"<%=oprType %>",
				pageTag:"<%=pageTag %>",
				recordId:"<%=recordId %>",
				personId:null
		};
		var loadingObj = null;
		
		$(function(){
			loadingObj = new loading(document.getElementById("loader"),{radius:20,circleLineWidth:5});
			var page = new commonPage(g.pageTag,g.recordId);
			$("#loaderDiv").show();
			loadingObj.show();
			if(g.oprType=="add"){
				page.initPage();	
			}else if(g.oprType=="modify" || g.oprType=="view"){
				page.initModifyPage();
			}		
		});
		
	</script>
	
 <html> 
  <body>
<div style="width: 100%;height: 100%" id="containerDiv" class="aaron_wrapper">
</div>  
	<div id="btnDiv" style="width: 100%; height: 40px; text-align: center;">
		<input type="button" value="保存" id="saveBtn" style="display: none;width: 110px;height: 38px;text-align: center;cursor: pointer;border: 0;padding: 0;font-weight: 700;font-size: 14px;vertical-align: baseline;line-height: 38px;outline: 0;background-color: transparent;border-radius: 3px; background-image:url('<%=path%>/images/sys/aaron_btn.png'); background-position:0px 0px;color: #0866C6; box-shadow: 0 2px 5px rgba(0,94,21,.3);">
	</div>
	<div id="loaderDiv" style="position: absolute;width: 100%;height: 100%;z-index: 5;top: 0;left: 0;">
		<canvas id="loader" width="400" height="200" style="margin: 100px auto;display: block;"></canvas>
	</div>
	
  </body>
  
  
</html>
