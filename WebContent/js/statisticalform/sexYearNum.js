/**
 * 人口比例js代码
 */

$(function() {
	$("#btnDiv").css("right",(screen.width-200-800)/2+20);
	$("#btnDiv").css("top",85);
	var title="绥化市每年男女人数";
	var labels = [];
	
	var data = [ {
		name : '男',
		value : [],
		color : '#1385a5',
		line_width : 2
	}, {
		name : '女',
		value : [],
		color : '#c56966',
		line_width : 2
	} ];
	
	$.ajax({
		url : $cntPath + "/rkbl.spr?method=querySexYearNum",
		method : "post",
		async : false,
		success : function(tmpData) {
			if (tmpData) {
				
				for (var i = 0; i < tmpData.length; i++) {
					labels.push(tmpData[i]["YEAR_DESC"]);
					data[0].value.push(tmpData[i]["MALE_NUMBER"]);
					data[1].value.push(tmpData[i]["FEMALE_NUMBER"]);
				}
			}
		},
		error : function(msg) {
			alert(msg.responseText);
		}
	});
	$("#cc").css("height", document.documentElement.clientHeight);
	$("#centerDiv").css("height", document.documentElement.clientHeight);

	
	var chart = new iChart.LineBasic2D(
			{
				render : 'canvasDiv',
				data : data,
				align : 'center',
//				border : 0,
				title : {
					text : '绥化市每年男女人数'
				},
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
				coordinate : {
					width : 640,
					height : 260,
					// striped_factor : 0.18,
					background_color : '#f6f9fa',
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
	// 利用自定义组件构造左侧说明文本
	chart.plugin(new iChart.Custom({
		drawFn : function() {
			// 计算位置
			var coo = chart.getCoordinate(), x = coo.get('originx'), y = coo
					.get('originy'), w = coo.width, h = coo.height;
			// 在左上侧的位置，渲染一个单位的文字
			chart.target.textAlign('start').textBaseline('bottom').textFont(
					'600 11px 微软雅黑').fillText('人数(万个)', x - 40, y - 12, false,
					'').textBaseline('top').fillText('(年份)', x + w + 22,
					y + h + 8, false, '');
		}
	}));
	// 开始画图
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