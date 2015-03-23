/**
 * 
 */

var loadRoleInfo = function(topMenu){
	if(roleMenuObj==null){
		roleMenuObj = {};
		$.ajax({
			 type:"POST",
			 url:contextPath +"/role.spr?method=getRoleInfo",	
			 data:"roleId="+g_roleId,
			 dataType:"json",
			 async:false,
			 success:function(data){
				 if(data){
					 $("#ULoginName").val(data.roleInfo.ROLE_NAME);
					 if(data.menuList && data.menuList.length>0){
//						 zTree.expandAll(true);
						 for(var i=0;i<data.menuList.length;i++){//设置选中的菜单
							 roleMenuObj[data.menuList[i].MENU_ID]=data.menuList[i].MENU_ID;
							 //zTree.checkNode(zTree.getNodeByParam("id",data.menuList[i].MENU_ID),true);								 
						 }
					 }
				 }
			 },
			 error:function(msg){
				 alert("error:"+msg);
			 }
		 });
	}
	
	
	var nodes = getAllNodes($("#dd").tree('getRoots'),"#dd");
	for(var i=0;i<nodes.length;i++){
		if(roleMenuObj[nodes[i].id]){
			$("#dd").tree('check',nodes[i].target);
		}
	}
	
	
	
};
/*
 * 存放权限菜单关联对象
 */
var roleMenuObj = null;

var zTree = null;
var setting = {
		view: {
			dblClickExpand: false
		},
		check: {
			enable: true
		}
	};

	var zNodes =[
		{id:1, name:"无右键菜单 1", open:true, noR:true,
			children:[
				   {id:11, name:"节点 1-1", noR:true},
				   {id:12, name:"节点 1-2", noR:true}

			]},
		{id:2, name:"右键操作 2", open:true,
			children:[
				   {id:21, name:"节点 2-1"},
				   {id:22, name:"节点 2-2"},
				   {id:23, name:"节点 2-3"},
				   {id:24, name:"节点 2-4"}
			]},
		{id:3, name:"右键操作 3", open:true,
			children:[
				   {id:31, name:"节点 3-1"},
				   {id:32, name:"节点 3-2"},
				   {id:33, name:"节点 3-3"},
				   {id:34, name:"节点 3-4"}
			]}
	 	];
/*
	$('#dd').tree({    
	    url:contextPath +"/area.spr?method=queryAreaTree2&topMenu=comprehensivequery",
	    checkbox:true
	});
*/	
	
$(function(){	
	if(g_roleId != ''){
		$("table caption strong").text("修改权限");
	}
	
		loadMenu("comprehensivequery");
		
	$("#confirmBtn").click(function(){
		if($.trim($("#ULoginName").val()).length==0){
			alert("请先填写权限名！");
			return;
		}
		manageRoleMenu();
		var roleName = $.trim($("#ULoginName").val());
		//var nodes = zTree.getCheckedNodes();
		var menuList = "";
		for(var prop in roleMenuObj){
			menuList += prop+",";
		}
//		for(var i=0;i<nodes.length;i++){
//			menuList += nodes[i].id+",";
//		}
		menuList = menuList.substring(0,menuList.length-1);
		
		$.ajax({
			 type:"POST",
			 url:contextPath +"/role.spr?method=saveRoleInfo",	
			 data:"roleName="+roleName +"&menuListStr="+menuList+"&in_roleId="+g_roleId,
			 dataType:"html",
			 success:function(data){
				 if(data){
					 if(parseInt(data)>0){
						 alert((g_roleId==null || g_roleId=="")?'新增权限成功！':'修改权限成功');
						 window.returnValue = true;
						 window.close();
					 }else{
						 (g_roleId==null || g_roleId=="")?'新增权限失败！':'修改权限失败';
					 }
				 }
			 },
			 error:function(msg){
				 alert("error:"+msg);
			 }
		 });
		
		
		
	});
	
	var bindOptDbEvent = function(){
		$(".role_list option").unbind("dblclick");
		$(".selected_role_list option").unbind("dblclick");
		$(".role_list option").bind("dblclick",function(){
			$(".selected_role_list").append('<option value="'+$(this).val()+'">'+$(this).text()+'</option>');
			$(this).remove();
			bindOptDbEvent();
		});
		$(".selected_role_list option").bind("dblclick",function(){
			$(".role_list").append('<option value="'+$(this).val()+'">'+$(this).text()+'</option>');
			$(this).remove();
			bindOptDbEvent();
		});
	};
	
	
	$(".select_all").click(function(){
		if($(".role_list option").length > 0){
			$(".role_list option").each(function() {
				$(".selected_role_list").append('<option value="'+$(this).val()+'">'+$(this).text()+'</option>');
				$(this).remove();	
       		 });
		}
		bindOptDbEvent();
	});
	////////////////////////////////////////
	$(".select_one").click(function(){
		if($(".role_list option:selected").length > 0){
			$(".role_list option:selected").each(function() {
				$(".selected_role_list").append('<option value="'+$(this).val()+'">'+$(this).text()+'</option>');
				$(this).remove();	
       		 });
		}else{
			alert("请在待选角色里选择要添加的角色");	
		}
		bindOptDbEvent();
	});
	
	/////////////////////////////////////////
	$(".remove_one").click(function(){
		if($(".selected_role_list option:selected").length > 0){
			$(".selected_role_list option:selected").each(function() {
				$(".role_list").append('<option value="'+$(this).val()+'">'+$(this).text()+'</option>');
				$(this).remove();	
       		 });
		}else{
			alert("请在已选角色里选择要删除的角色");	
		}
		bindOptDbEvent();
	});
	/////////////////////////////////////////
	$(".remove_all").click(function(){
		if($(".selected_role_list option").length > 0){
			$(".selected_role_list option").each(function() {
				$(".role_list").append('<option value="'+$(this).val()+'">'+$(this).text()+'</option>');
				$(this).remove();	
       		 });
		}
		bindOptDbEvent();
	});
});
/*
 * manageRoleMenu
 * 如果当前树已经加装了，再做切换，先维护最终的roleMenuObj
 * 
 */
function manageRoleMenu(){
//	if(zTree){
//		if(roleMenuObj){
//			var nodes = zTree.getNodes();
//			for(var i=0;i<nodes.length;i++){
//				if(nodes[i].children!=null && nodes[i].children.length>0){
//					for(var j=0;j<nodes[i].children.length;j++){
//						delete roleMenuObj[nodes[i].children[j].id];
//					}
//				}
//				delete roleMenuObj[nodes[i].id];
//			}
//		}
//		
//		var checkedNodes = zTree.getCheckedNodes();
//		for(var i=0;i<checkedNodes.length;i++){
//			roleMenuObj[checkedNodes[i].id]=checkedNodes[i].id;
//		}
//	}
	
	if(roleMenuObj){
		var nodes = getAllNodes($("#dd").tree('getRoots'),"#dd");
		for(var i=0;i<nodes.length;i++){
			delete roleMenuObj[nodes[i].id];
		}
		var checkedNodes = $('#dd').tree('getChecked');
		for(var i=0;i<checkedNodes.length;i++){
			roleMenuObj[checkedNodes[i].id]=checkedNodes[i].id;
		}
	}
	
	
	
}

function loadMenu(topMenu){
	
	manageRoleMenu();
	
	
	$('#dd').tree({    
	    url:contextPath +"/role.spr?method=queryMenuTree&topMenu="+topMenu,
	    checkbox:true,
	    onLoadSuccess:function(){
	    	if(g_roleId != ''){//如果权限ID不为空，则要初始化界面
	   		 loadRoleInfo();
	   	 }else if(roleMenuObj==null){
	   		 roleMenuObj = {};
	   	 }
	    }
	});
	
	
	
//	$.ajax({
//		 type:"POST",
//		 url:contextPath +"/role.spr?method=queryTreeMenuInfo&topMenu="+topMenu,			 
//		 dataType:"json",
//		 success:function(data){
//			 //$("#menuSelectDiv").html("");
//			 if(data){
//				 zNodes = data;
//				 /*
//				 $.fn.zTree.init($("#menuSelectDiv"), setting, zNodes);
//				 zTree = $.fn.zTree.getZTreeObj("menuSelectDiv");
//				 */
//				 if(g_roleId != ''){//如果权限ID不为空，则要初始化界面
//					 loadRoleInfo();
//				 }else if(roleMenuObj==null){
//					 roleMenuObj = {};
//				 }
//			 }
//		 },
//		 error:function(msg){
//			 $("#menuSelectDiv").html("");
//			 alert("error:"+msg);
//		 }
//	 });
}
function getAllNodes(nodes,container){
	var	rtnNodes=[];
	for(var i=0;i<nodes.length;i++){
		if($(container).tree("isLeaf",nodes[i].target)==true){
			rtnNodes.push(nodes[i]);
		}
		var children = $(container).tree("getChildren",nodes[i].target);
		
		if(children){
			for(var j=0;j<children.length;j++){
				if($(container).tree("isLeaf",children[j].target)==true){
					rtnNodes.push(children[j]);
				}
			}
		}
	}
	return rtnNodes;
}