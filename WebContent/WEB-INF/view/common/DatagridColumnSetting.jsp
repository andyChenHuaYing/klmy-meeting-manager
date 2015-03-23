<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String param = request.getParameter("param");
%>

<!DOCTYPE html>
 <html> 
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>设置列头</title>
		
		
	</head>
 <body style="margin: 0px;overflow: hidden;">
 	<ul id="columnUL">
 		<li style="width: 100%;margin-bottom: 10px;">请选择要显示的列</li>
 		<!-- 
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li>
 		<li>
 			<input type="checkbox" id="rybh"> 人员编号
 		</li> -->
 	</ul>
 	<!-- 
 	<div id="btnDiv1">
 		<a class="sts-link-btn" onclick="confirm();" href="javascript:void(0);">
				<span class="sts-link-btn-span">确定</span>
		</a>
		
		<a class="sts-link-btn" onclick="cancel();" href="javascript:void(0);">
				<span class="sts-link-btn-span">取消</span>
		</a>
 	</div>
 	 -->
 	<script type="text/javascript" src="<%=path %>/js/util/JsUtil.js"></script>
		
		<script type="text/javascript">
			//$J.importFile("jquery");
			$J.include("<%=path %>/js/jquery/jquery-1.9.1.js");
			$J.include("<%=path %>/css/common/buttonCommont.css");
		</script>
		
		
		<style type="text/css">		
			#columnUL{
				width:600px;height: auto;
				margin: 0 auto;
				padding-top:20px;
				padding-left: 0px;
			}
			#columnUL li{
				width: 150px;
				display: inline-block;
				padding-top: 5px;
				vertical-align: top;
			}
			#btnDiv1{
				width:600px;
				margin: 20px auto 20px;
				height: 30px;
				
			}
		</style>
 	<script type="text/javascript">
			
			
			//var obj = window.dialogArguments;
			//var propArr = window.dialogArguments;
			var propArr = g.columnArr;
			//$(function(){
				var html = "";
				if(propArr){//初始化表头
					for(var i=0;i<propArr.length;i++){
						html+='<li><input type="checkbox" id="'+propArr[i].field+'" '+(propArr[i].hidden==true?'':'checked="checked"')+'>'+propArr[i].title+'</li>';
					}
				}
				$("#columnUL").append(html);
			//});
			/*
			function confirm(){
				var inputs = $("#columnUL input[type='checkbox']");
				var rtnArr = [];
				for(var i=0;i<inputs.length;i++){
					if($(inputs[i]).prop("checked")==true){
						rtnArr.push({checked:true,id:$(inputs[i]).attr("id")});
					}else{
						rtnArr.push({checked:false,id:$(inputs[i]).attr("id")});
					}
				}
				window.returnValue = rtnArr;
				window.close();
			}
			function cancel(){
				window.close();
			}*/
			
		</script>
 </body>
</html>
