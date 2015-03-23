/**
 * 人口比例js代码
 */

$(function() {
	// #98cc3b；#ffcc00；#00c0f0
	$("#btnDiv").css("right",(screen.width-200-800)/2+10);
	$("#btnDiv2").css("right",(screen.width-200-800)/2+10);
	$("#btnDiv").css("top",55);
	$("#btnDiv2").css("top",485);
	var title="绥化参合人员按年龄段统计（柱形图）";
	var title2="绥化参合人员按年龄段统计（饼图）";
	 var labels = [];
	 var colorArr = ["#4572a7","#aa4643","#89a54e","#71588f","#4198af","#db8c3d","#93a9cf","#d19392","#b9cd96","#a99bbd","#91c3d5"];
	 var data = [ {
			name : '参加新农合人员数',
			value : [],
			color : '#1385a5'
		}];
	 var data2 = [];
	 var totalCnt = 0;
//	var data = [ {
//		name : '登记离婚',
//		value : [],
//		color : 'rgba(152,204,59,0.5)'
//	}, {
//		name : '协议离婚',
//		value : [],
//		// color : 'rgba(227,194,225,0.4)'
//		color : 'rgba(0,192,240,0.5)'
//	} ];
	$.ajax({
		url : $cntPath + "/chrybl.spr?method=queryApCoMedicalInsurance",
		method : "post",
		async : false,
		success : function(tmpData) {
			if (tmpData) {
				for (var i = 0; i < tmpData.length; i++) {
					totalCnt+=tmpData[i]["NUM"];
				}
				for (var i = 0; i < tmpData.length; i++) {
					labels.push(tmpData[i]["AGE_PERIOD"]);
					data[0].value.push(tmpData[i]["NUM"]/totalCnt*100);
					var obj = {};
					obj.name = tmpData[i]["AGE_PERIOD"];
					obj.value = tmpData[i]["NUM"];
					obj.color = colorArr[i%colorArr.length];
					data2.push(obj);
				}
			}
		},
		error : function(msg) {
			alert(msg.responseText);
		}
	});

	$("#cc").css("height", document.documentElement.clientHeight);
	$("#centerDiv").css("height", document.documentElement.clientHeight);
//	var data = [ {
//		name : 'DPS01A',
//		value : [ 45, 52, 54, 74, 90, 84 ],
//		color : '#1385a5'
//	}, {
//		name : 'DPS01B',
//		value : [ 60, 80, 105, 125, 108, 120 ],
//		color : '#c56966'
//	} ];
	var chart = new iChart.ColumnMulti3D({
		render : 'canvasDiv',
		data : data,
		//labels : [ "一月", "二月", "三月", "四月", "五月", "六月" ],
		labels : labels,
		title : '绥化参合人员按年龄段统计（柱形图）',
		width : 800,
		height : 400,
		background_color : '#ffffff',
		
		tip : {
			enable : true,
			shadow : true,
			listeners : {
				// tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
				parseText : function(tip, name, value, text, i) {
					return "<span style='color:#005268;font-size:12px;'>"
							+ labels[parseInt(i.split('_')[0])]
							+" "
							+ name
							+ "<br/>"
							+ "</span><span style='color:#005268;font-size:20px;'>"
							+ parseInt(value*totalCnt) + "个</span>";
				}
			}
		},
		sub_option:{
			label : {
				color : '#2c2e2a'
			},
			listeners:{
				parseText:function(r,t){
					return t.toFixed(2)+"%";
				}
			}
		},
		legend : {
			enable : true,
			background_color : null,
			border : {
				enable : false
			}
		},
		coordinate : {
			background_color : '#f1f1f1',
			scale : [ {
				position : 'left',
				start_scale : 0,
				scale_share : 8,
				listeners:{
					parseText:function(t,x,y){
						return {text:t+"%"};
					}
				} 
			} ],
			width : 600,
			height : 260
		}
	});
	chart.draw();
	
	
	var chart2 = new iChart.Pie3D({
		render : 'canvasDiv2',
		title : '绥化参合人员按年龄段统计（饼图）',
		padding : '2 10',
		tip : {
			enable : true,
			listeners : {
				// tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
				parseText : function(tip, name, value, text, i) {
					return "<span style='color:#005268;font-size:12px;'>"
							+ name
							+" "
							+ "育龄妇女人数:<br/>"
							+ "</span><span style='color:#005268;font-size:20px;'>"
							+ value + "个</span>";
				}
			}
		},
		width : 800,
		height : 400,
		data : data2,
		shadow : false,
		shadow_color : 'rgba(255,255,255,0)',
		shadow_blur : 8,
		//background_color : '#3b4346',
		background_color : 'rgba(255,255,255,0)',
		gradient : false,
		color_factor : 0.28,
		gradient_mode : 'RadialGradientOutIn',
		showpercent : true,
		decimalsnum : 2,
		legend : {
			enable : false,
			padding : 30,
			color : '#e0e5e8',
			border : {
				width : [ 0, 0, 0, 2 ],
				color : '#343b3e'
			},
			background_color : null,
		},
		sub_option : {
			offsetx : -40,
			border : {
				enable : false
			},
			label : {
				//background_color : '#fefefe',
				background_color : '#fefefe',
				sign : false,// 设置禁用label的小图标
				line_height : 10,
				padding : 4,
				border : {
					enable : true,
					radius : 4,// 圆角设置
					color : '#e0e5e8'
				},
				fontsize : 11,
				fontweight : 600,
				color : '#444444'
			}
		}
	});
	for(var i=0;i<data2.length;i++){
		chart2.bound(i);
	}
	$("#btnDiv img").click(function(event){
		if($(this).attr("isShow")=="0"){
			$("#btnDiv ul").show();
			$(this).attr("isShow","1");
		}else{
			$("#btnDiv ul").hide();
			$(this).attr("isShow","0");
		}
		event.stopPropagation();
	});
	$("#btnDiv #exportBtn").click(function(event){
		if($J.hasAuthority($J.roleMap.chartExport)==false){
			$.messager.alert('提示', "对不起，您没有导出权限！");
			return;
		}
		$J.chartExport(chart,title);
		$("#btnDiv ul").hide();
		$("#btnDiv img").attr("isShow","0");
		event.stopPropagation();
	});
	$("#btnDiv #printBtn").click(function(event){
		if($J.hasAuthority($J.roleMap.chartPrint)==false){
			$.messager.alert('提示', "对不起，您没有打印权限！");
			return;
		}
		$J.chartPrint(chart);
		$("#btnDiv ul").hide();
		$("#btnDiv img").attr("isShow","0");
		event.stopPropagation();
	});
	
	$("#btnDiv2 img").click(function(event){
		if($(this).attr("isShow")=="0"){
			$("#btnDiv2 ul").show();
			$(this).attr("isShow","1");
		}else{
			$("#btnDiv2 ul").hide();
			$(this).attr("isShow","0");
		}
		event.stopPropagation();
	});
	$("#btnDiv2 #exportBtn").click(function(){
		if($J.hasAuthority($J.roleMap.chartExport)==false){
			$.messager.alert('提示', "对不起，您没有导出权限！");
			return;
		}
		$J.chartExport(chart2,title2);
		$("#btnDiv2 ul").hide();
		$("#btnDiv2 img").attr("isShow","0");
	});
	$("#btnDiv2 #printBtn").click(function(){
		if($J.hasAuthority($J.roleMap.chartPrint)==false){
			$.messager.alert('提示', "对不起，您没有打印权限！");
			return;
		}
		$J.chartPrint(chart2);
		$("#btnDiv2 ul").hide();
		$("#btnDiv2 img").attr("isShow","0");
	});
	
	$(document).on("click","body",function(){
		$("#btnDiv ul").hide();
		$("#btnDiv img").attr("isShow","0");
		$("#btnDiv2 ul").hide();
		$("#btnDiv2 img").attr("isShow","0");
	});
	
});