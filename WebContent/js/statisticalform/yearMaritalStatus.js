/**
 * 人口比例js代码
 */

$(function() {
	$("#btnDiv").css("right",(screen.width-200-800)/2+10);
	$("#btnDiv2").css("right",(screen.width-200-800)/2+10);
	$("#btnDiv").css("top",55);
	$("#btnDiv2").css("top",485);
	// #98cc3b；#ffcc00；#00c0f0
	 var labels = [];
	 var title="绥化每年婚姻状况统计图";
	 var title2="绥化每年姻状况趋势图";
	 var data = [ {
			name : '登记离婚',
			value : [],
			color : '#1385a5'
		}, {
			name : '协议离婚',
			value : [],
			color : '#c56966'
		} ];
	 
	 
	 var data2 = [ {
			name : '登记离婚',
			value : [],
			color : '#1385a5',
			line_width : 2
		}, {
			name : '协议离婚',
			value : [],
			color : '#c56966',
			line_width : 2
		} ];
	 
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
		url : $cntPath + "/hyzktj.spr?method=queryYearMaritalStatus",
		method : "post",
		async : false,
		success : function(tmpData) {
			if (tmpData) {
				for (var i = 0; i < tmpData.length; i++) {
					labels.push(tmpData[i]["YEAR_DESC"]);
					data[0].value.push(tmpData[i]["MARRIED_NUMBER"]);
					data[1].value.push(tmpData[i]["UNMARRIED_NUMBER"]);
					data2[0].value.push(tmpData[i]["MARRIED_NUMBER"]);
					data2[1].value.push(tmpData[i]["UNMARRIED_NUMBER"]);
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
		title : '绥化每年婚姻状况统计图',
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
			axis : {
				color : '#252525',
				width : [ 0, 0, 2, 2 ]
			},
			width : 600,
			height : 260
		}
	});
	
	// 利用自定义组件构造左侧说明文本
	chart.plugin(new iChart.Custom({
		drawFn : function() {
			// 计算位置
			var coo = chart.getCoordinate(), x = coo.get('originx'), y = coo
					.get('originy'), w = coo.width, h = coo.height;
			// 在左上侧的位置，渲染一个单位的文字
			chart.target.textAlign('start').textBaseline('bottom').textFont(
					'600 11px 微软雅黑').fillText('人数(个)', x - 40, y - 12, false,
					'').textBaseline('top').fillText('(年份)', x + w + 12,
					y + h + 7, false, '');
		}
	}));
	
	chart.draw();
	
	
	
	var chart2 = new iChart.LineBasic2D(
			{
				render : 'canvasDiv2',
				data : data2,
				align : 'center',
				title : '绥化每年姻状况趋势图',
				width : 800,
				height : 400,
				tip : {
					enable : true,
					shadow : true,
					listeners : {
						// tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
						parseText : function(tip, name, value, text, i) {
							return "<span style='color:#005268;font-size:12px;'>"
									+ name
									+ " "
									+ labels[i]
									+ "人数:<br/>"
									+ "</span><span style='color:#005268;font-size:20px;'>"
									+ value + "个</span>";
						}
					}
				},
				crosshair : {
					enable : true,
					line_color : '#1f7e92'
				},
				sub_option : {
					smooth : true,
					hollow_inside : false,// 设置一个点的亮色在外环的效果
					point_size : 12,
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
					width : 640,
					height : 260,
					// striped_factor : 0.18,
					background_color : '#f1f1f1',
					// grid_color : '#4e4e4e',
					axis : {
						color : '#252525',
						width : [ 0, 0, 2, 2 ]
					},
					scale : [ {
						position : 'left',
						start_scale : 0,
						scale_share : 8
					}, {
						position : 'bottom',
						labels : labels
					} ]
				}
			});
	
	
	chart2.plugin(new iChart.Custom({
		drawFn : function() {
			// 计算位置
			var coo = chart2.getCoordinate(), x = coo.get('originx'), y = coo
					.get('originy'), w = coo.width, h = coo.height;
			// 在左上侧的位置，渲染一个单位的文字
			chart2.target.textAlign('start').textBaseline('bottom').textFont(
					'600 11px 微软雅黑').fillText('人数(个)', x - 40, y - 12, false,
					'').textBaseline('top').fillText('(年份)', x + w + 22,
					y + h + 8, false, '');
		}
	}));
	
	// 开始画图
	chart2.draw();
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