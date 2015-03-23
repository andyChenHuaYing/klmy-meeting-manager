<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String dictionaryList = request.getAttribute("dictionaryList")==null?"":(String)request.getAttribute("dictionaryList");
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
			$J.importFile("jquery,easyui");
			$J.include("<%=path %>/js/component/UserComp/dataGrid.js");
			$J.include("<%=path %>/js/component/My97DatePicker/WdatePicker.js");
			$J.include("<%=path %>/js/component/My97DatePicker/skin/WdatePicker.css");
			$J.include("<%=path %>/css/common/buttonCommont.css");
		</script>
		
		<script type="text/javascript">
			var dictionaryListStr = '<%=dictionaryList%>';
			var dictionaryObj = (dictionaryListStr==""?null:eval("("+dictionaryListStr+")"));
			var grid;
			$(function(){
				$("#cc").css("height",document.documentElement.clientHeight);
				$("#centerDiv").css("height",document.documentElement.clientHeight-190-40);
				grid = new DataGrid("gaPersonQueryGrid","#dg");
				grid.load({});
				setHeight("cc");
				
				var conditionSel = $(".queryPropTable select");
				for(var i=0;i<conditionSel.length;i++){
					if($(conditionSel[i]).attr("keyword")!=null){
						if(dictionaryObj&&dictionaryObj[$(conditionSel[i]).attr("keyword")]){
							var html = "<option value=''>--请选择--</option>";
							for(var j=0;j<dictionaryObj[$(conditionSel[i]).attr("keyword")].length;j++){
								html+="<option value="+dictionaryObj[$(conditionSel[i]).attr("keyword")][j].name+">"+dictionaryObj[$(conditionSel[i]).attr("keyword")][j].name+"</option>";
							}
							$(conditionSel[i]).append(html);
						}
					}
				}
			});
			function query(){
				var inputs = $("#queryConditionUL input");
				var queryCondition = {};
				var selects = $("#queryConditionUL select");
				for(var i=0;i<inputs.length;i++){
					if($(inputs[i]).attr("type")=="text"){
						queryCondition[$(inputs[i]).attr("id")] = $.trim($(inputs[i]).val());
					}
				}
				for(var i=0;i<selects.length;i++){
					queryCondition[$(selects[i]).attr("id")] = $.trim($(selects[i]).val());
				}
				
				grid.load({queryCondition:JSON.stringify(queryCondition)});
			}
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
			/*background: url('images/sys/tablespan.jpg') repeat scroll 0% 0% transparent;*/
		}
		.queryPropTable .td2{
			width: 140px;
			text-align: right;			
		}
		</style>
	</head>
 <body style="margin: 0px;overflow: hidden;">
 <div id="cc" class="easyui-layout" style="width:100%;height:600px;fit:true;">   
    <div data-options="region:'north',title:'查询条件',split:true" style="height:160px;">
    	 <ul style="margin-left: 0px;padding-left: 10px;" id="queryConditionUL">
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					证件号码：
    	 				</td>
    	 				<td class="td2">
    	 					<input type="text" id="zjhm">
    	 				</td>
    	 			</tr>
    	 		</table>
    	 	</li>
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					身份证姓名：
    	 				</td>
    	 				<td class="td2">
    	 					<input type="text" id="sfzxm">
    	 				</td>
    	 			</tr>
    	 		</table>
    	 		
    	 	</li>
    	 	
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					身份证性别：
    	 				</td>
    	 				<td class="td2">
    	 					<!-- <input type="text" id="sfzxb"> -->
    	 					<select id="sfzxb" keyword="SEX">
    	 					</select>
    	 				</td>
    	 			</tr>
    	 		</table>
    	 	</li>
    	 	
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					户籍类型：
    	 				</td>
    	 				<td class="td2">
    	 					<input type="text" id="hjlx">
    	 				</td>
    	 			</tr>
    	 		</table>
    	 		
    	 	</li>
    	 	
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					兵役状况：
    	 				</td>
    	 				<td class="td2">
    	 					<input type="text" id="byqk">
    	 				</td>
    	 			</tr>
    	 		</table>
    	 		
    	 	</li>
    	 	
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					电话：
    	 				</td>
    	 				<td class="td2">
    	 					<input type="text" id="dh">
    	 				</td>
    	 			</tr>
    	 		</table>
    	 		
    	 	</li>
    	 	
    	 	
    	 	
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					政治面貌：
    	 				</td>
    	 				<td class="td2">
<!--     	 					<input type="text" id="zzmm"> -->
    	 					<select id="zzmm" keyword="POLITICAL_STATUS"></select>
    	 				</td>
    	 			</tr>
    	 		</table>
    	 		
    	 	</li>
    	 	
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					死亡类型：
    	 				</td>
    	 				<td class="td2">
    	 					<input type="text" id="swlx">
    	 				</td>
    	 			</tr>
    	 		</table>
    	 		
    	 	</li>
    	 	
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					暂住证编号：
    	 				</td>
    	 				<td class="td2">
    	 					<input type="text" id="zzzbh">
    	 				</td>
    	 			</tr>
    	 		</table>
    	 		
    	 	</li>
    	 	
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					迁入时间：
    	 				</td>
    	 				<td class="td2">
    	 					<input type="text" id="qrsj" class="Wdate" onfocus="new WdatePicker({dateFmt:'yyyy-MM-dd'});">
    	 				</td>
    	 			</tr>
    	 		</table>
    	 		
    	 	</li>
    	 	
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1">
    	 					迁出时间：
    	 				</td>
    	 				<td class="td2">
    	 					<input type="text" id="qcsj" class="Wdate" onfocus="new WdatePicker({dateFmt:'yyyy-MM-dd'});">
    	 				</td>
    	 			</tr>
    	 		</table>
    	 		
    	 	</li>
    	 	
    	 	<li>
    	 		<table class="queryPropTable">
    	 			<tr>
    	 				<td class="td1" colspan="2">
    	 					<a class="sts-link-btn" onclick="query();" href="javascript:void(0);" style="margin: 0">
								<span class="sts-link-btn-span">查 询</span>
							</a>
    	 				</td>
    	 			</tr>
    	 		</table>
    	 		
    	 	</li>
    	 	
    	 </ul>
    	<!-- <div style="height: 30px;width: 100%">
    		<a class="sts-link-btn" onclick="query();" href="javascript:void(0);">
				<span class="sts-link-btn-span">查 询</span>
			</a>
    	</div> -->
    </div>   
    <div data-options="region:'center',title:'查询结果'" style="background:#eee;" id="centerDiv">
    	<table id="dg">
  		</table>
    </div>   
</div> 
 
  		
  </body>
</html>
