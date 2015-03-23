<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
 <html> 
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>datagird</title>
		
		<script type="text/javascript" src="<%=path %>/js/util/JsUtil.js"></script>
		
		<script type="text/javascript">
			$J.importFile("jquery,easyui,ichart");
			$J.include("<%=path %>/js/component/UserComp/dataGrid.js");
			$J.include("<%=path %>/js/component/My97DatePicker/WdatePicker.js");
			$J.include("<%=path %>/js/component/My97DatePicker/skin/WdatePicker.css");
			$J.include("<%=path %>/css/common/buttonCommont.css");
			$J.include("<%=path %>/js/statisticalform/apSexMedicalInsurance.js");
		</script>
		
		<script type="text/javascript">
			
		</script>
		<style type="text/css">		
		.titleTd{
			background: url("images/sys/tablespan.jpg");
		}
		#queryConditionUL li{
			display: inline-block;
			width: 240px;
			padding-right: 5px;
		}
		.queryPropTable{
			width: 100%;
		}
		.queryPropTable .td1{
			width: 100px;
			text-align: left;			
		}
		.queryPropTable .td2{
			width: 140px;
			text-align: right;			
		}
		</style>
	</head>
 <body style="margin: 0px;overflow: hidden;">
 <div id="cc" class="easyui-layout" style="width:100%;height:600px;fit:true;"> 
 
    <div data-options="region:'center',title:'统计结果'" id="centerDiv" style="position: relative;">
    <div id="btnDiv">
		<img alt="" src="images/sys/view_list.png" style="margin-left:66px;" title="功能按钮" isShow="0" width="30px;">
		<ul id="btnUL" style="display: none;">
			<li style="border-bottom: 1px dotted #777777;"><a href="javascript:void(0)" id="exportBtn">导出</a></li>
			<li style=""><a href="javascript:void(0)" id="printBtn">打印</a></li>
		</ul>
	</div>
	
	<div id="btnDiv2">
		<img alt="" src="images/sys/view_list.png" style="margin-left:66px;" title="功能按钮" isShow="0" width="30px;">
		<ul id="btnUL" style="display: none;">
			<li style="border-bottom: 1px dotted #777777;"><a href="javascript:void(0)" id="exportBtn">导出</a></li>
			<li style=""><a href="javascript:void(0)" id="printBtn">打印</a></li>
		</ul>
	</div>    
    	<div id="canvasDiv" style="width: 100%;padding-top:50px;">
    	
    	</div>
    	<div id="canvasDiv2" style="width: 100%;padding-top:30px;padding-bottom:100px;">
    	
    	</div>
    </div> 
    
</div> 
 
  		
  </body>
</html>
