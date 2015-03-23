/**
 * 人口比例js代码
 */

$(function() {
	// #98cc3b；#ffcc00；#00c0f0
	$("#btnDiv").css("right",(screen.width-200-800)/2+10);
	$("#btnDiv2").css("right",(screen.width-200-800)/2+10);
	$("#btnDiv").css("top",55);
	$("#btnDiv2").css("top",435);
	var title="绥化每年各年龄段婚姻状况统计图";
	var title2="绥化每年各年龄段婚姻状况趋势图";
	 var labels = [];
	 
	 var data = [ {
			name : '早婚',
			value : [],
			color : '#009900'
		}, {
			name : '正常婚姻',
			value : [],
			color : '#1385a5'
		}, {
			name : '晚婚',
			value : [],
			color : '#c56966'
		} ];
	 
	 
	 var data2 = [ {
			name : '早婚',
			value : [],
			color : '#009900'
		}, {
			name : '正常婚姻',
			value : [],
			color : '#1385a5'
		}, {
			name : '晚婚',
			value : [],
			color : '#c56966'
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
		url : $cntPath + "/jhnldfb.spr?method=queryAgePeriodMarriage",
		method : "post",
		async : false,
		success : function(tmpData) {
			if (tmpData) {
				for (var i = 0; i < tmpData.length; i++) {
					labels.push(tmpData[i]["YEAR_DESC"]);
					data[0].value.push(tmpData[i]["EARLY_MARRIAGE_NUMBER"]);
					data[1].value.push(tmpData[i]["MARRIAGE_NUMBER"]);
					data[2].value.push(tmpData[i]["LATE_MARRIAGE_NUMBER"]);
					data2[0].value.push(tmpData[i]["EARLY_MARRIAGE_NUMBER"]);
					data2[1].value.push(tmpData[i]["MARRIAGE_NUMBER"]);
					data2[2].value.push(tmpData[i]["LATE_MARRIAGE_NUMBER"]);
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
	
	
//	var chart = new iChart.ColumnMulti2D({
//		render : 'canvasDiv',
//		data : data,
//		//labels : [ "一月", "二月", "三月", "四月", "五月", "六月" ],
//		labels : labels,
//		title : '绥化每年各年龄段婚姻状况统计图',
//		width : 800,
//		height : 400,
//		background_color : '#ffffff',
//		
//		tip : {
//			enable : true,
//			shadow : true,
//			listeners : {
//				// tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
//				parseText : function(tip, name, value, text, i) {
//					return "<span style='color:#005268;font-size:12px;'>"
//							+ labels[parseInt(i.split('_')[0])]
//							+" "
//							+ name
//							+ ""
//							+ "人数:<br/>"
//							+ "</span><span style='color:#005268;font-size:20px;'>"
//							+ value + "个</span>";
//				}
//			}
//		},
//		
//		sub_option : {
//			label : false
//		},
//		legend : {
//			enable : true,
//			background_color : null,
//			border : {
//				enable : false
//			}
//		},
//		coordinate : {
//			background_color : '#f1f1f1',
//			scale : [ {
//				position : 'left',
//				start_scale : 0,
//				scale_share : 8
//			} ],
//			axis : {
//				color : '#252525',
//				width : [ 0, 0, 2, 2 ]
//			},
//			width : 600,
//			height : 260
//		}
//	});
//	
//	// 利用自定义组件构造左侧说明文本
//	chart.plugin(new iChart.Custom({
//		drawFn : function() {
//			// 计算位置
//			var coo = chart.getCoordinate(), x = coo.get('originx'), y = coo
//					.get('originy'), w = coo.width, h = coo.height;
//			// 在左上侧的位置，渲染一个单位的文字
//			chart.target.textAlign('start').textBaseline('bottom').textFont(
//					'600 11px 微软雅黑').fillText('人数(个)', x - 40, y - 12, false,
//					'').textBaseline('top').fillText('(年份)', x + w + 12,
//					y + h + 7, false, '');
//		}
//	}));
//	
//	chart.draw();
	
	var chart = new iChart.ColumnStacked3D({
		render : 'canvasDiv',
		data : data,
		//labels : [ "绥化市","北林区","望奎县","兰西县","青冈县","庆安县","明水县","绥棱县","安达市","肇东市","海伦市" ],
		labels : labels,
		title : {
			text : '绥化每年各年龄段婚姻状况统计图'
			//color : '#254d70'
		},
		border:{
			enable:true
		},
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
				//color : '#f9f9f9',
				fontsize : 12,
				fontweight : 600
			},
			border : {
				width : 2,
				color : '#ffffff'
			}
		},
		label : {
			//color : '#254d70',
			fontsize : 12,
			fontweight : 600,
			background_color:'#ffffff'
		},
		legend : {
			enable : true,
			background_color : null,
			line_height : 25,
			//color : '#254d70',
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
					return labels[parseInt(i.split("_")[0])]+" " + name + ":" + value + '人';
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
			background_color :"#f1f1f1",
			pedestal_height : 10,// 底座高度
			left_board : false,// 取消左侧面板
			shadow : true,// 底座的阴影效果
			grid_color : '#6a6a80',// 网格线
			wall_style : [ {// 坐标系的各个面样式
				color : '#f1f1f1'
			}, {
				color : '#f1f1f1'
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
				//color : '#c0d0e0',
				width : 0
			},
			scale : [ {
				position : 'left',
				scale_enable : false,
				start_scale : 0,
				scale_space : 50,
				label : {
					//color : '#254d70',
					fontsize : 11,
					fontweight : 600
				}
			} ]
		}
	});
	chart.draw();
	
	var chart2 = new iChart.LineBasic2D(
			{
				render : 'canvasDiv2',
				data : data2,
				align : 'center',
				title : '绥化每年各年龄段婚姻状况趋势图',
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