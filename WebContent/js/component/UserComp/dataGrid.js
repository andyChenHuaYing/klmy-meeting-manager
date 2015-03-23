/**
 * 封装jquery easyui datagrid
 * author gu.xiaogang
 * date 20140819
 */

var g = {columnArr:null};
function DataGrid(id,container){
	var tableConfigData = null;
	$.ajax({
		url:$cntPath+"/tableConfig.spr?method=queryTableConfigInfo",
		data:"tableId="+id+"&userCondition="+1,
		async:false,
		success:function(data){
			if(data){
				tableConfigData = data;
			}
		},
		error:function(msg){
			console.log(msg.responseText);
		}
	});
	
	
	this.id = tableConfigData.tableInfo.id;
	this.url = tableConfigData.tableInfo.url||"";
	this.title = tableConfigData.tableInfo.title||"";
	this.container = container;
	//this.frozenColumns = tableConfigData.tableInfo.frozencolumns!=null?tableConfigData.tableInfo.frozencolumns.split(","):undefined;
	this.method = tableConfigData.tableInfo.method||"post";
	this.singleSelect = tableConfigData.tableInfo.singleselect==1?true:false;
	this.height = tableConfigData.tableInfo.height||"";
	this.width = tableConfigData.tableInfo.width||"";
	//this.isShowRownumbers = tableConfigData.tableInfo.isShowRownumbers==1?true:false;
	this.rownumbers = tableConfigData.tableInfo.rownumbers==1?true:false;
	this.fitColumns = tableConfigData.tableInfo.fitcolumns==1?true:false;
	this.resizeHandle = tableConfigData.tableInfo.resizehandle==null?"right":tableConfigData.tableInfo.resizehandle;
	this.autoRowHeight = tableConfigData.tableInfo.autorowheight==1?true:false;	
	this.striped = tableConfigData.tableInfo.striped==1?true:false;
	this.nowrap = tableConfigData.tableInfo.nowrap==1?true:false;
	this.idField = tableConfigData.tableInfo.idfield;
	this.loadMsg = tableConfigData.tableInfo.loadmsg;
	this.pagination = tableConfigData.tableInfo.pagination==1?true:false;
	this.checkOnSelect = tableConfigData.tableInfo.checkonselect==1?true:false;
	this.selectOnCheck = tableConfigData.tableInfo.selectoncheck==1?true:false;
	this.pagePosition = tableConfigData.tableInfo.pageposition==null || tableConfigData.tableInfo.pageposition==""?"bottom":tableConfigData.tableInfo.pageposition;
	this.pageNumber = tableConfigData.tableInfo.pagenumber==null?1:tableConfigData.tableInfo.pagenumber;
	this.pageSize = tableConfigData.tableInfo.pagesize==null?10:tableConfigData.tableInfo.pagesize;
	this.pageList = tableConfigData.tableInfo.pagelist==null?[10,20,30,40,50]:eval(tableConfigData.tableInfo.pagelist);
	this.sortName = tableConfigData.tableInfo.sortname;
	this.multiSort = tableConfigData.tableInfo.multisort==1?true:false;
	this.remoteSort = tableConfigData.tableInfo.remotesort==1?true:false;
	this.showHeader = tableConfigData.tableInfo.showheader==1?true:false;
	this.showFooter = tableConfigData.tableInfo.showfooter==1?true:false;
	this.scrollbarSize = tableConfigData.tableInfo.scrollbarsize==null?18:tableConfigData.tableInfo.scrollbarsize;
	this.grid = null;
	
	
	/*
	 * 就先支持三级表头，应该够用了
	 * 
	 */
	var header1 = [];
	var header2 = [];
	var header3 = [];
	var frozenheader1=[];
	var frozenheader2=[];
	var frozenheader3=[];
	if(tableConfigData.tableHeaderInfo){//处理一下表头信息
		for(var i=0;i<tableConfigData.tableHeaderInfo.length;i++){
			tableConfigData.tableHeaderInfo[i].hidden = Boolean(tableConfigData.tableHeaderInfo[i].hidden); 
			tableConfigData.tableHeaderInfo[i].checkbox = Boolean(tableConfigData.tableHeaderInfo[i].checkbox);
			tableConfigData.tableHeaderInfo[i].sortable = Boolean(tableConfigData.tableHeaderInfo[i].sortable);
			tableConfigData.tableHeaderInfo[i].order = Boolean(tableConfigData.tableHeaderInfo[i].order);
			tableConfigData.tableHeaderInfo[i].resizable = Boolean(tableConfigData.tableHeaderInfo[i].resizable);
			tableConfigData.tableHeaderInfo[i].fixed = Boolean(tableConfigData.tableHeaderInfo[i].fixed);
			if(tableConfigData.tableHeaderInfo[i].isFrozen==1){
				if(tableConfigData.tableHeaderInfo[i]["headerLevel"]==1){
					frozenheader1.push(tableConfigData.tableHeaderInfo[i]);
				}else if(tableConfigData.tableHeaderInfo[i]["headerLevel"]==2){
					frozenheader2.push(tableConfigData.tableHeaderInfo[i]);
				}else if(tableConfigData.tableHeaderInfo[i]["headerLevel"]==3){
					frozenheader3.push(tableConfigData.tableHeaderInfo[i]);
				}
			}else{
				if(tableConfigData.tableHeaderInfo[i]["headerLevel"]==1){
					header1.push(tableConfigData.tableHeaderInfo[i]);
				}else if(tableConfigData.tableHeaderInfo[i]["headerLevel"]==2){
					header2.push(tableConfigData.tableHeaderInfo[i]);
				}else if(tableConfigData.tableHeaderInfo[i]["headerLevel"]==3){
					header3.push(tableConfigData.tableHeaderInfo[i]);
				}
			}
			
		}
	}
	var header = [];
	var frozenHeader=[];
	if(header1.length>0){
		header.push(header1);
	}
	if(header2.length>0){
		header.push(header2);
	}
	if(header3.length>0){
		header.push(header3);
	}
	
	if(frozenheader1.length>0){
		frozenHeader.push(frozenheader1);
	}
	if(frozenheader2.length>0){
		frozenHeader.push(frozenheader2);
	}
	if(frozenheader3.length>0){
		frozenHeader.push(frozenheader3);
	}
	
	this.headers = header;
	this.frozenColumns = frozenHeader;
}

/*
 * 初始化对象
 * 入参是查询条件
 * queryParamObj 是查询参数
 * paramObj 是覆盖参数，弥补数据库配置的不足
 */
DataGrid.prototype.load = function(queryParamObj,paramObj){//
	
	var tmpObj = {
//			url: $cntPath+'/file/json/datagrid_data1.json',
			url: $cntPath+"/"+this.url,
			idField: this.idField,
			title: this.title,
			width: this.width,
			height: this.height,			
			method:this.method,
			singleSelect:this.singleSelect,
			rownumbers:this.rownumbers,
			loadMsg:this.loadMsg,
			fitColumns: this.fitColumns,
			resizeHandle:this.resizeHandle,
			autoRowHeight:this.autoRowHeight,
			striped:this.striped,
			pagination:this.pagination,
			checkOnSelect:this.checkOnSelect,
			selectOnCheck:this.selectOnCheck,
			pagePosition:this.pagePosition,
			pageNumber:this.pageNumber,
			pageSize:this.pageSize,
			pageList:this.pageList,
			sortName:this.sortName,
			multiSort:this.multiSort,
			remoteSort:this.remoteSort,
			showHeader:this.showHeader,
			showFooter:this.showFooter,
			scrollbarSize:this.scrollbarSize,
			columns:this.headers,
			frozenColumns:this.frozenColumns,
			queryParams:queryParamObj
		};
	var destObj = $.extend({},tmpObj,paramObj);
	$(this.container).datagrid(destObj);	
	this.initPageButtons();
};

DataGrid.prototype.getCheckedRows = function(){
	return $(this.container).datagrid("getChecked");	
};


DataGrid.prototype.clearSelections = function(){
	$(this.container).datagrid("clearSelections");	
};

/*
 * 获取选中行ID
 * 
 */
DataGrid.prototype.getSelectedRowId = function(){
	var row = $(this.container).datagrid('getSelected');
	if (row){
		//$.messager.alert('Info', row[this.idField]);
		return row[this.idField];
	}else{
		return null;
	}
};
/*
 * 获取选中所有选中行ID数组
 * 
 */
DataGrid.prototype.getSelectedRowIds = function(){
	var rows = $(this.container).datagrid('getSelections');
	if (rows && rows.length>0){
		//$.messager.alert('Info', row[this.idField]);
		var rtnArr = [];
		for(var i=0;i<rows.length;i++){
			rtnArr.push(rows[i][this.idField]);
		}
		return rtnArr;
	}else{
		return [];
	}
};
/*
 * 分页栏增加“设置”菜单
 * 
 */
DataGrid.prototype.initPageButtons = function(){
	var pager = $(this.container).datagrid().datagrid('getPager');
	$this = this;
	pager.pagination({
		buttons:[{
			iconCls:'icon-setting',
			handler:function(e){
				//var frozenColumns = $('#dg').datagrid('getColumnFields', true);
				var opts = $($this.container).datagrid('getColumnFields');
				var arr = [];
				var i=0;
				while(i<opts.length){
					arr.push($($this.container).datagrid('getColumnOption',opts[i]));
					i++;
				}
				if(arr.length>0){//如果数组大于0，弹出选择列界面，返回选择结果
					if($("#win").length==0){
						$("body").append('<div id="win"></div>');
					}
					var buttons = [{
						iconCls:'icon-save',
						text:'确定',
						handler:function(){
							var inputs = $("#columnUL input[type='checkbox']");
							for(var i=0;i<inputs.length;i++){
								if($(inputs[i]).prop("checked")==true){
									$($this.container).datagrid('showColumn',$(inputs[i]).attr("id"));
								}else{
									$($this.container).datagrid('hideColumn',$(inputs[i]).attr("id"));
								}
							}
							$('#win').dialog("close");
						}

					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$('#win').dialog("close");
						}
					}];	
					var toolbar =  [{
						iconCls:'icon-setting',
						text:'全选',
						handler:function(){
							$("#columnUL input[type='checkbox']").each(function(){
								$(this).prop("checked",true);
							});
						}

					},{
						text:'反选',
						iconCls:'icon-setting',
						handler:function(){
							$("#columnUL input[type='checkbox']").each(function(){
								$(this).prop("checked",!this.checked);
							});
						}
					}];	
						 g.columnArr = arr;
					     $('#win').dialog({    
							    title: '表头设置',    
							    width: 700,    
							    height: 350,    
							    closed: false,    
							    cache: false,    
							    href: $cntPath+"/tableConfig.spr?method=toColumnSetting",    
							    modal: true,
							    buttons : buttons,
							    toolbar : toolbar
							});
				}
			}
		}]
	});
};

function setHeight(containerId){
	var c = $('#'+containerId);
	var p = c.layout('panel','center');	// get the center panel
	var oldHeight = p.panel('panel').outerHeight();
	p.panel('resize', {height:'auto'});
	var newHeight = p.panel('panel').outerHeight();
	c.layout('resize',{
		height: (c.height() + newHeight - oldHeight)
	});
}