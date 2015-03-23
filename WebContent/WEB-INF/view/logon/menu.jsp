<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	Long userId = Long.valueOf(request.getParameter("userId"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>功能树</title>
<link href="css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="home/js/jquery-1.9.1.min.js"></script>
<script language="javascript">
var userId = <%=userId%>;
var old_obj_id = "";
var old_im_id = "";
function switchMenu(obj_id,im_id){
		obj = document.getElementById(obj_id);
		im_obj = document.getElementById(im_id);
		old_obj = document.getElementById(old_obj_id);
		old_im_obj = document.getElementById(old_im_id);
	if(old_obj_id == ""){
		old_obj_id = obj_id;
		old_im_id = im_id;
		obj.style.display==""?obj.style.display="none":obj.style.display="";
		im_obj.src.search("-.png")==-1?im_obj.src="images/-.png":im_obj.src="images/+.png";
	}else if(old_obj_id != obj_id){
		old_obj_id = obj_id;
		old_im_id = im_id;
		obj.style.display==""?obj.style.display="none":obj.style.display="";
		im_obj.src.search("-.png")==-1?im_obj.src="images/-.png":im_obj.src="images/+.png";
		old_obj.style.display==""?old_obj.style.display="none":old_obj.style.display="";
		old_im_obj.src.search("-.png")==-1?old_im_obj.src="images/-.png":old_im_obj.src="images/+.png";
	}else{
		obj.style.display==""?obj.style.display="none":obj.style.display="";
		im_obj.src.search("-.png")==-1?im_obj.src="images/-.png":im_obj.src="images/+.png";
		old_obj_id = "";
		old_im_id = "";
	}
}
function onclickUrl(Url,obj){
	$("#LeftMenu div").css("background","#00A78A");
	//$(obj).css("background","#81B304");
	$(obj).css("background","#ADD8E6");	
	//parent.centerframe.location=Url;
	
	createTab(Url,obj.innerHTML,$(obj).attr("id"));
}

function createTab(url,title,id){
	var html = '<div id="'+id+'" style="width: 100%;height: 100%;" isTab="1">'+
    	'<iframe src="'+url+'" style="width: 100%;min-height: 625px;"></iframe>'+
        '</div>';
        
        if($($("#centerframe",parent.parent.document)[0].contentWindow.document).find("#tabs li[tabId='"+id+"']").length>0){
        	return;
        }
        $($("#centerframe",parent.parent.document)[0].contentWindow.document).find("#tabs li").removeAttr("class");
        $($("#centerframe",parent.parent.document)[0].contentWindow.document).find("#tabs").append('<li tabId="'+id+'" class="checked"><span>'+title+'</span><img src="home/images/close.png" width="20" style="position: absolute;top:0;right:0;display: none;" class="closeImg"></li>');
        $($("#centerframe",parent.parent.document)[0].contentWindow.document).find("div[isTab='1']").hide();
        $($("#centerframe",parent.parent.document)[0].contentWindow.document).find("body").append(html);
}

$(function(){
	$.ajax({
		type:"POST",
		url:"<%=path%>/menu.spr?method=getMenuInfo&dt="+new Date().getTime(),
		dataType:"json",
		success:function(data){
			//menuList = data;
			loadMenu(data);
		},
		error:function(msg){
			alert(msg);
		}
	
	});
	
	var loadMenu = function(menuList){
		var html="";
		for(var i=0;i<menuList.length;i++){
			if(menuList[i].PARENT_MENU_ID == null){
				html+='<tr id='+menuList[i].MENU_ID+'><td class="Item" onclick="switchMenu(\'td'+menuList[i].MENU_ID+'\',\'im'+menuList[i].MENU_ID+'\');"><img id="im'+menuList[i].MENU_ID+'" src="images/+.png" align="absmiddle" />&nbsp;&nbsp;&nbsp;&nbsp;'+menuList[i].NAME+'</td></tr>';
				//html+='<li class="dropdown"><a href="javascript:void(0)" menuId='+menuList[i].MENU_ID+' uri="'+menuList[i].URI+'"><span class="iconfa-briefcase"></span> '+menuList[i].NAME+'</a><ul></ul></li>';
				html+='<tr><td id="td'+menuList[i].MENU_ID+'" style="display:none">';
				for(var j=i;j<menuList.length;j++){
					if(menuList[i].MENU_ID==menuList[j].PARENT_MENU_ID){
						html+='<div id="'+menuList[j].MENU_ID+'" class="Name"  onmouseover="this.className=\'Name1\'"  onmouseout="this.className=\'Name\'" onclick="onclickUrl(\''+menuList[j].URI+'\',this);" >'+menuList[j].NAME+'</div>';	
					}
					
				}
				html+="</td></tr>";
			}
		}
		$("#menuTable").html(html);
	};
})
</script>
<script language="JavaScript" type="text/JavaScript" src="js/script.js"></script>
</head>

<body bgcolor="#008b7f" id="LeftMenu">
<table cellpadding="0" cellspacing="0">
  <tr>
    <td class="Title">主界面</td>
  </tr>
</table>
<table id="menuTable" cellpadding="0" cellspacing="0">
  <tr>
    <td class="Item" onclick="switchMenu('td1','im1');"><img id="im1" src="images/+.png" align="absmiddle" />&nbsp;&nbsp;&nbsp;&nbsp;档案管理</td>
  </tr>
  <tr>
   <td id="td1" style="display:none">
	<div class="Name"  onmouseover="this.className='Name1'"  onmouseout="this.className='Name'" onclick="onclickUrl('list.html');" >列表页</div>
	<div class="Name"  onmouseover="this.className='Name1'"  onmouseout="this.className='Name'" onclick="onclickUrl('check.html');" >辅助检查</div>
	</td>
  </tr>
  <tr>
    <td class="Item" onclick="switchMenu('td2','im2');"><img id="im2" src="images/+.png" align="absmiddle" />&nbsp;&nbsp;&nbsp;&nbsp;随访记录</td>
  </tr>
  <tr>
   <td id="td2" style="display:none">
	<div class="Name"  onmouseover="this.className='Name1'"  onmouseout="this.className='Name'" onclick="onclickUrl('item-list.php');" >随访记录1</div>
	</td>
  </tr>
  <tr>
    <td class="Item" onclick="switchMenu('td3','im3');"><img id="im3" src="images/+.png" align="absmiddle" />&nbsp;&nbsp;&nbsp;&nbsp;系统设置</td>
  </tr>
  <tr>
   <td id="td3" style="display:none">
	<div class="Name"  onmouseover="this.className='Name1'"  onmouseout="this.className='Name'" onclick="onclickUrl('changePW.php');" >权限管理</div>
	<div class="Name"  onmouseover="this.className='Name1'"  onmouseout="this.className='Name'" >用户管理</div>
	<div class="Name"  onmouseover="this.className='Name1'"  onmouseout="this.className='Name'" >日志管理</div>
	</td>
  </tr>
</table>
</body>
</html>