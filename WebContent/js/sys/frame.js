/**
 * 
 */

$(function(){
	
	$(".content").css("height",document.documentElement.clientHeight-62);
	$(".contentDiv").css("height",document.documentElement.clientHeight-62-30);
	$(".splitDiv").css("height",document.documentElement.clientHeight-62);	
	$(".leftPart").css("height",document.documentElement.clientHeight-62);
	$(".rightPart").css("width",screen.width-200);
	$(".rightPart").css("height",document.documentElement.clientHeight-62);
	$(".tabs ul").css("width",screen.width-200-10);
	
	var loadMenu = function(menuList){
		var html = '<dl style="width: 100%;">';
		for(var i=0;i<menuList.length;i++){
			if(menuList[i].PARENT_MENU_ID == null){
				html+='<dt class="secondsubmenu" url="undefined" isShow="'+(i==0?'1':'0')+'">';
				html+='<span> <img class="smallicon_url"	src="'+(i==0?'images/sys/arrow_1.png':'images/sys/arrow_2.png')+'">';
				html+='</span> <a href="javascript:void(0);"> <img	src="'+((menuList[i].ICON==null || menuList[i].ICON=='' )?'images/sys/left_ico2_dsqkz.png':menuList[i].ICON)+'">';
				html+=menuList[i].NAME+'</a></dt>';
				html+='<dd id="'+menuList[i].MENU_ID+'" class="child-of-12233" style="display: '+(i==0?'block':'none')+';">';
				html+='<div class="menuparttop"></div>';
				html+='<ul class="content_pic" name="contentPic">';
				for(var j=i;j<menuList.length;j++){
					if(menuList[i].MENU_ID==menuList[j].PARENT_MENU_ID){
						html += '<li id="'+menuList[j].MENU_ID+'" title="'+menuList[j].NAME+'"';
						html += 'url="'+menuList[j].URI+'">';
						html += '<a href="javascript:void(0);"> <img src="'+((menuList[j].ICON==null || menuList[j].ICON=='' )?'images/sys/left_ico2_dsqkz.png':menuList[j].ICON)+'">';
						html += '<em>'+menuList[j].NAME+'</em></a></li>';
					}
				}
				html+="</ul></dd>";
			}
		}
		html +='</dl>';
		$("#leftPart div").html(html);
		
		//menu事件
		$(".secondsubmenu").click(function(){
			var isShow = $(this).attr("isShow");
			$("#leftPart dt").attr("isShow","0");
			//$("#leftPart dd").hide("normal");
			$("#leftPart dd").slideUp("slow");
			$("#leftPart dt span img").attr("src","images/sys/arrow_2.png");
			if(isShow=="1"){
				$(this).attr("isShow","0");
			}else{
				$(this).attr("isShow","1");
				$(this).find("span img").attr("src","images/sys/arrow_1.png");
//				$(this).next().show("normal");
				$(this).next().slideDown("normal");
			}
		});
		//二级menu点击事件
		$(".content_pic li").click(function(){
			//增加tab
			if($(".tabOwner li[target='"+$(this).attr("id")+"']").length==0){
				var html='<li class="" target="'+$(this).attr("id")+'">';
				html+='<a><div class="tab-left"></div><div class="titleCenter">'+$(this).attr('title')+'</div>';
				html+='<div class="titleClose"><div class="closeOut"></div></div>';
				html+='<div class="tab-Right"></div></a></li>';
				$(".tabOwner ul").append(html);
				
				//增加content
				html = '<div class="'+$(this).attr("id")+'" style="width: 100%;height: 100%;"><iframe frameborder=0 height="100%" width="100%" src="'+$cntPath+"/"+$(this).attr("url")+'"></iframe></div>';
				$(".contentDiv div").hide();
				$(".contentDiv").append(html);
				
				tabUnBindEvent();
				tabBindEvent();
				
				$(".tabOwner li[target='"+$(this).attr("id")+"']")[0].click();
			}else{
				$(".tabOwner li").attr("class","");
				//$(".tabOwner li[target='"+$(this).attr("id")+"']").attr("class","current");
				$(".tabOwner li[target='"+$(this).attr("id")+"']")[0].click();
			}
			
		});
		
	};
	
	
	var queryMenu = function(topMenu){
		var cnt = 0;
		$.ajax({
			type:"POST",
			url:$cntPath+"/menu.spr?method=getMenuInfo&topMenu="+topMenu+"&dt="+new Date().getTime(),
			dataType:"json",
			async:false,
			success:function(data){
				//menuList = data;
				if(data.length>0){
					loadMenu(data);
				}
				cnt = data.length;
			},
			error:function(msg){
				alert(msg);
			}
		});
		return cnt;
	}; 
	queryMenu("comprehensivequery");
	
	var tabBindEvent = function(){
		$(".tabOwner li").bind("click",function(){
			$(".tabOwner li").attr("class","");
			$(this).attr("class","current");
			$(".contentDiv div").hide();
			$(".contentDiv div[class='"+$(this).attr("target")+"']").show();
		});
		$(".closeOut").bind("click",function(){
			if($(this).parent().parent().parent().attr("class")=="current"){
				$(this).parent().parent().parent().prev()[0].click();
			}
			$(".contentDiv div[class='"+$(this).parent().parent().parent().attr("target")+"']").remove();
			$(this).parent().parent().parent().remove();
		});
	};
	
	var tabUnBindEvent = function(){
		$(".tabOwner li").unbind("click");
		$(".closeOut").unbind("click");
	};
	
	$("#root_menu_ul li a").click(function(){
		var cnt = queryMenu($(this).parent().attr("id"));
		if(cnt > 0){
			$("#root_menu_ul li a").attr("class","has_children");
			$(this).attr("class","has_children nav_menu_current_hover");	
		}else{
			//$(this).css("background","gray");
			$.messager.alert('提示', "您没有"+$.trim($(this).find("div").eq(1).text())+"的权限！");
		}
		
	});
	$(".userInfo").click(function(){
		if($("#moreoption").attr("clickcnt")==0){
			$("#moreoption").show();	
			$("#moreoption").attr("clickcnt",1);
		}else{
			$("#moreoption").hide();
			$("#moreoption").attr("clickcnt",0);
		}
		
	});
	$("#moreoption").on("mouseleave",function(){
		$("#moreoption").hide();
		$("#moreoption").attr("clickcnt",0);
	});
	//tabBindEvent();
	win11 = $('#win11');
	$("#modifyPwd").click(
			function(){
				var buttons = [{
					iconCls:'icon-save',
					text:'保存',
					handler:function(){
						var pwd = $.trim($(".aaron_add_table input[id='pwd']").val());
						var repeatPwd = $.trim($(".aaron_add_table input[id='repeatPwd']").val());
						if(pwd.length<6){
							$.messager.alert("提示","密码长度小于6，请重新输入");
							return false;
						}
						
						if(pwd!=repeatPwd){
							$.messager.alert("提示","两次密码输入不一样，请重新输入");
							return false;
						}
						
						$.ajax({
							url:$cntPath+"/user.spr?method=modifyPwd",
							data:"password="+MD5.encrypt(pwd),
							method:"post",
							dataType:"json",
							async:false,
							success:function(data){
								if(data==1){
									$.messager.alert('提示', "修改成功，请重新登录系统",null,function(){
										win11.dialog("close");
										location.href = $cntPath+"/user.spr?method=logout";
									});
									
								}else if(data==2){
									$.messager.alert('提示', "新旧密码一样，请重新设置");
								}else{
									$.messager.alert('提示', "修改失败");
								}
							},
							error:function(msg){
								alert(msg.responseText);
							}
							
						});
					}

				},{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						win11.dialog("close");
					}
				}];
				
				win11.dialog({    
				    title: '修改密码',    
				    width: 400,    
				    height: 200,    
				    closed: false,    
				    cache: false,    
				    href: $cntPath+'/user.spr?method=toModifyPwd',    
				    modal: true,
				    buttons : buttons
				});  
			}	
	);
});

function Logout(){
	location.href = $cntPath+"/user.spr?method=logout";
}

