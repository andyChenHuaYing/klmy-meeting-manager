/**
 * 人口比例js代码
 */

$(function() {
	// #98cc3b；#ffcc00；#00c0f0
	$("#btnDiv").css("right",(screen.width-200-800)/2+20);
	$("#btnDiv").css("top",85);
	var title="绥化参合人员分区统计";
	 var labels = [];
	 
	 var data = [ {
			name : '参加新农合人员数',
			value : [],
			color : '#1385a5'
		}];
	 
	 
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
		url : $cntPath + "/chryfqtj.spr?method=queryAreaCoMedicalInsurance",
		method : "post",
		async : false,
		success : function(tmpData) {
			if (tmpData) {
				for (var i = 0; i < tmpData.length; i++) {
					labels.push(tmpData[i]["AREA_NAME"]);
					data[0].value.push(tmpData[i]["NUM"]);
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
		title : '绥化参合人员分区统计',
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
							+ value + "个</span>";
				}
			}
		},
		
		sub_option : {
			label : false
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
				scale_share : 8
			} ],
			width : 600,
			height : 260
		}
	});
	chart.draw();
	
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
	$("#exportBtn").click(function(event){
		if($J.hasAuthority($J.roleMap.chartExport)==false){
			$.messager.alert('提示', "对不起，您没有导出权限！");
			return;
		}
		$J.chartExport(chart,title);
		$("#btnDiv ul").hide();
		$("#btnDiv img").attr("isShow","0");
		event.stopPropagation();
	});
	$("#printBtn").click(function(event){
		if($J.hasAuthority($J.roleMap.chartPrint)==false){
			$.messager.alert('提示', "对不起，您没有打印权限！");
			return;
		}
		$J.chartPrint(chart);
		$("#btnDiv ul").hide();
		$("#btnDiv img").attr("isShow","0");
		event.stopPropagation();
	});
	$(document).on("click","body",function(){
		$("#btnDiv ul").hide();
		$("#btnDiv img").attr("isShow","0");
	});
});