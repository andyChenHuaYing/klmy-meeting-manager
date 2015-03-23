/**
 * 人口比例js代码
 */

$(function() {
	// #98cc3b；#ffcc00；#00c0f0
	$("#btnDiv").css("right",(screen.width-200-800)/2+20);
	$("#btnDiv").css("top",85);
	var title="绥化各地区婚姻状况";
	 var labels = [];
	 
	 var data = [ {
			name : '登记离婚',
			value : [],
			color : '#1385a5'
		}, {
			name : '协议离婚',
			value : [],
			color : '#c56966'
		} ];
	$.ajax({
		url : $cntPath + "/hyzktj.spr?method=queryAreaMaritalStatus",
		method : "post",
		async : false,
		success : function(tmpData) {
			if (tmpData) {
				for (var i = 0; i < tmpData.length; i++) {
					labels.push(tmpData[i]["AREA_NAME"]);
					data[0].value.push(tmpData[i]["MARRIED_NUMBER"]);
					data[1].value.push(tmpData[i]["UNMARRIED_NUMBER"]);
				}
			}
		},
		error : function(msg) {
			alert(msg.responseText);
		}
	});

	$("#cc").css("height", document.documentElement.clientHeight);
	$("#centerDiv").css("height", document.documentElement.clientHeight);

	var chart = new iChart.ColumnMulti3D({
		render : 'canvasDiv',
		data : data,
		//labels : [ "一月", "二月", "三月", "四月", "五月", "六月" ],
		labels : labels,
		title : '绥化各地区婚姻状况',
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
							+ ""
							+ "人数:<br/>"
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
		column_width : 8,//柱形宽度
		zScale:8,//z轴深度倍数
		xAngle : 50,
		bottom_scale:1.1, 
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