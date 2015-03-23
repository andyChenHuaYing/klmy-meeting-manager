/**
 * 人口比例js代码
 */

$(function() {
	// #98cc3b；#ffcc00；#00c0f0
	$("#btnDiv").css("right",(screen.width-200-800)/2+20);
	$("#btnDiv").css("top",85);
	var title="户籍类型分布情况";
	var data = [ {
		name : '非农户口',
		value : 68.34,
		color : '#3883bd'
	}, {
		name : '农业户口',
		value : 26.83,
		color : '#3F5C71'
	}];
	
	
//	var labels = [];
//	var data = [ {
//		name : '非农户口',
//		value : [],
//		color : 'rgba(152,204,59,0.5)'
//	}, {
//		name : '农业户口',
//		value : [],
//		// color : 'rgba(227,194,225,0.4)'
//		color : 'rgba(0,192,240,0.5)'
//	} ];
	$.ajax({
		url : $cntPath + "/rkbl.spr?method=queryHhRatio",
		method : "post",
		async : false,
		success : function(tmpData) {
			if (tmpData) {
				for (var i = 0; i < tmpData.length; i++) {
					data[0].value = tmpData[i]["NYHK_NUM"];
					data[1].value = tmpData[i]["FNHK_NUM"];
				}
			}
		},
		error : function(msg) {
			alert(msg.responseText);
		}
	});

	$("#cc").css("height", document.documentElement.clientHeight);
	$("#centerDiv").css("height", document.documentElement.clientHeight);
	
	var chart = new iChart.Pie3D({
		render : 'canvasDiv',
		title : {
			text : '户籍类型分布情况',
			color : '#444444',
			height : 40,
			border : {
				enable : true,
				width : [ 0, 0, 0, 0 ],
				color : '#343b3e'
			}
		},
		padding : '2 10',
		/*footnote : {
			text : 'StatCounter Global Stats,design by ichartjs',
			color : '#e0e5e8',
			height : 30,
			border : {
				enable : true,
				width : [ 2, 0, 0, 0 ],
				color : '#343b3e'
			}
		},*/
		width : 800,
		height : 400,
		data : data,
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
//		,
//		border : {
//			width : [ 0, 0, 0, 0 ],
//			color : '#1e2223'
//		}
	});
	
	for(var i=0;i<data.length;i++){
		chart.bound(i);
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