/**
 * 人口比例js代码
 */

$(function() {
	//#98cc3b；#ffcc00；#00c0f0
	$("#btnDiv").css("right",(screen.width-200-800)/2+20);
	$("#btnDiv").css("top",85);
	var title="绥化各地区参保统计";
	var labels=[];
	var data=[{
		name : '职工社会保险',
		value : [],
		color : 'rgba(152,204,59,0.5)'
	}, {
		name : '低保',
		value : [],
		//color : 'rgba(227,194,225,0.4)'
		color : 'rgba(0,192,240,0.5)'
	}, {
		name : '个人补充医疗',
		value : [],
		//color : 'rgba(218,222,223,0.4)'
		color : 'rgba(255,204,0,0.5)'
	} ];
	$.ajax({
		url:$cntPath+"/cbrybl.spr?method=queryAreaInsurance",
		method:"post",
		async:false,
		success:function(tmpData){
			if(tmpData){
				for(var i=0;i<tmpData.length;i++){
					labels.push(tmpData[i]["AREA_NAME"]);
					data[0].value.push(tmpData[i]["SOCIAL_SECURITY"]);
					data[1].value.push(tmpData[i]["LOW_SECURITY"]);
					data[2].value.push(tmpData[i]["PERSONAL_SECURITY"]);
				}
			}
		},
		error:function(msg){
			alert(msg.responseText);
		}
	});
	
	$("#cc").css("height",document.documentElement.clientHeight);
	$("#centerDiv").css("height",document.documentElement.clientHeight);
	/*
	var data = [ {
		name : '15岁以下',
		value : [ 45, 52, 54, 60, 77, 23, 52, 99, 23, 24, 88 ],
		//color : 'rgba(202,230,117,0.4)'
		color : 'rgba(152,204,59,0.5)'
	}, {
		name : '15~65岁',
		value : [ 60, 80, 105, 80, 57, 64, 58, 71, 46, 26, 48 ],
		//color : 'rgba(227,194,225,0.4)'
		color : 'rgba(0,192,240,0.5)'
	}, {
		name : '65岁以上',
		value : [ 50, 70, 120, 100, 67, 34, 42, 39, 33, 24, 98 ],
		//color : 'rgba(218,222,223,0.4)'
		color : 'rgba(255,204,0,0.5)'
	} ];*/
	var chart = new iChart.ColumnStacked3D({
		render : 'canvasDiv',
		data : data,
		//labels : [ "绥化市","北林区","望奎县","兰西县","青冈县","庆安县","明水县","绥棱县","安达市","肇东市","海伦市" ],
		labels : labels,
		title : {
			text : '绥化各地区参保统计',
			color : '#254d70'
		},
//		border:{
//			enable:false
//		},
		width : 800,
		height : 350,
		column_width : 40,
		animation:true,
		background_color : 'rgba(255,255,255,0)',
		shadow : true,
		shadow_blur : 3,
		shadow_color : '#080808',
		shadow_offsetx : 1,
		shadow_offsety : 0,
		sub_option : {
			label : {
				color : '#f9f9f9',
				fontsize : 12,
				fontweight : 600
			},
			border : {
				width : 2,
				color : '#ffffff'
			}
		},
		label : {
			color : '#254d70',
			fontsize : 12,
			fontweight : 600,
			background_color:'#ffffff'
		},
		legend : {
			enable : true,
			background_color : null,
			line_height : 25,
			color : '#254d70',
			fontsize : 12,
			fontweight : 600,
			border : {
				enable : false
			},
			align:'center',
			valign:'bottom',
			column:3,
			offsety:25
		},
		tip : {
			enable : true,
			listeners : {
				// tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
				parseText : function(tip, name, value, text, i) {
					return name + ":" + value + '人';
				}
			}
		},
		percent : true,// 标志为百分比堆积图
		showpercent : true,
		decimalsnum : 1,
		text_space : 16,// 坐标系下方的label距离坐标系的距离。
		zScale : 0.5,
		xAngle : 50,
		bottom_scale : 1.1,
		coordinate : {
			width : '82%',
			height : '80%',
			board_deep : 10,// 背面厚度
			background_color :"#000000",
			pedestal_height : 10,// 底座高度
			left_board : false,// 取消左侧面板
			shadow : true,// 底座的阴影效果
			grid_color : '#6a6a80',// 网格线
			wall_style : [ {// 坐标系的各个面样式
				color : '#6a6a80'
			}, {
				color : '#ceebfb'
				//color : 'rgba(255,255,255,0)'
			}, {
				color : '#a6a6cb'
			}, {
				color : '#6a6a80'
			}, {
				color : '#74749b'
			}, {
				color : '#a6a6cb'
			} ],
			axis : {
				color : '#c0d0e0',
				width : 0
			},
			scale : [ {
				position : 'left',
				scale_enable : false,
				start_scale : 0,
				scale_space : 50,
				label : {
					color : '#254d70',
					fontsize : 11,
					fontweight : 600
				}
			} ]
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