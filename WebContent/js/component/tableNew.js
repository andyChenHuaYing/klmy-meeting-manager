/**
 * add by xiaogang 20140305
 */

function myTable(){
	this.colCnt = 0;
	this.hasCheckBox = true;	
	this.containerId = null;
}


myTable.prototype.init = function(opts){
	this.colCnt = opts.colCnt;
	this.hasCheckBox = opts.hasCheckBox;
	this.containerId = opts.containerId;
	this.styleArr = opts.styleArr;
	this.createTable();
};
// data [{name:'',desc:''}]
myTable.prototype.createTable = function(){
	var html = '<table width="100%" border="1" cellpadding="0" cellspacing="0" id="ListTable" bordercolor="#e3e4e8">';
	html+= '<tr class="tit">';
	if(this.hasCheckBox == true){
		html += '<td width="60"><input type="checkbox" id="select_all" isSelected="0"></td>';
	}
	for(var i=0;i<this.styleArr.length;i++){
		html += '<td width="'+this.styleArr[i].left+'" name = "'+this.styleArr[i].name+'">'+this.styleArr[i].desc+'</td>';
	}	
	html += '</tr>';
	html += '</table>';
	$("#"+this.containerId).append(html);
};

myTable.prototype.addData = function(data){	
	$("#"+this.containerId).html("");
	this.createTable();
	var html = "";
	for(var i=0;i<data.length;i++){
		html += '<tr class="list">';
		if(this.hasCheckBox == true){
			html += '<td><input type="checkbox" val="'+data[i].ID+'"/></td>';
		}
		for(var j=0;j<this.styleArr.length;j++){
			html += '<td>'+ (data[i][this.styleArr[j].name]==null?"":data[i][this.styleArr[j].name]) + '</td>';
		}
		html += '</tr>';
	}
	$("#"+this.containerId+" table").append(html);
	this.hoverEvent();
};

myTable.prototype.hoverEvent = function(){
	table_change_color();
	select_all();
	selectOne();
	advanced_query_switch();
	advanced_off();
};

//鼠标移入表格变色功能
function table_change_color(){
	$('.aaron_table table tr').hover(function(){
	$(this).addClass("hover_col");
	//alert("ddddd");
	},function(){
		$(this).removeClass("hover_col");
		});
}

//全选功能
function select_all(){
	$("#select_all").change(function(){
		if($(this).attr("isSelected")=='0'){
			//alert("select all");
			$(".list :input[type=checkbox]").prop("checked",true);
			$(this).attr("isSelected","1");
		}else{
			$(".list :input[type=checkbox]").removeAttr("checked");
			$(this).attr("isSelected","0");
		}
	});
}
//单选功能
function selectOne(){
	$("td input[type=checkbox]").click(function(){
		if($(this).attr("checked")=='checked'){
			$(this).removeAttr("checked");
		}else{
			$(this).attr("checked","checked");
		}
	});
}


//显示高级查询选项
function advanced_query_switch(){
	$('.advanced_query_switch').click(function(){
		//alert("I want you");
		$(".aaron_query_advanced").slideDown(1000);	
	});	
}

//关闭高级查询选项
function advanced_off(){
	$('.advance_off').click(function(){
		//alert("I want you");
		$(".aaron_query_advanced").slideUp(1000);	
	});	
}