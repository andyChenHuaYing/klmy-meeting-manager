<%@page import="com.util.ConstantParam"%>
<%@page import="com.vo.UserVO"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Long userId = ((UserVO) session
			.getAttribute(ConstantParam.USER_INFO)).getUserId();
	String userCode = ((UserVO) session
			.getAttribute(ConstantParam.USER_INFO)).getUserCode();
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0" />  -->
	<title>会务管理系统</title>

<script type="text/javascript" src="home/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/sys/frame.js"></script>
<script type="text/javascript" src="js/util/JsUtil.js"></script>
<script type="text/javascript" src="js/util/md5.js"></script>
<script type="text/javascript">
	$J.importFile("easyui");
</script>

<link href="css/sys/frame.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	.nav_ul {
	    font-size: 14px;
	    position: relative;
	    z-index: 100;
	    height: 58px;
	    float: right;
	    margin-right: 0px;
	    top: -25px;
		right: 100px;
	}
	ul, ol {
	    list-style: none outside none;
	}
	
	.nav_ul a:hover {
	    background: none repeat scroll 0% 0% #003677;
	    color: #FFF;
	    height: 35px;
	    line-height: 35px;
	    border-radius: 5px;
	}
	.nav_menu_current_hover{
	
		background: none repeat scroll 0% 0% #003677 !important;
	}
		
	.nav_ul li {
	    float: left;
	    position: relative;
	}
	.nav_ul a {
	    display: inline-block;
	    padding: 2px 5px;
	    margin: 8px 1px;
	    color: #FFF;
	    text-decoration: none;
	    height: 35px;
	    line-height: 35px;
	    border-radius: 5px;
	}
	.separator {
	    width: 2px;
		height: 44px;
		margin: 2px 10px 0px;
		background: url(images/sys/top_link.gif) no-repeat scroll 0px 0px transparent;
		position: absolute;
		right: 80px;
		top: 6px;
	}
	.userInfo{
		width: 52px;
		height: 44px;
		margin: 2px 10px 0px;
		position: absolute;
		right: 10px;
		top: 15px;
	}
	#moreoption {
	    max-width: 300px;
	    min-width: 150px;
	    width: 200px;
	    padding: 8px 6px;
	    background-color: #FFF;
	    border: 1px solid #ACACAC;
	    opacity: 0.9;
	    position: absolute;
	    top: 54px;
	    right: 20px;
	    z-index: 3;
	    display: none;
	}
	#moreoption dl {
    width: 100%;
    display: block;
    overflow: hidden;
}

#moreoption dt a {
    text-indent: 12px;
}
#moreoption a {
    width: 100%;
    display: block;
    color: #03C;
    border-bottom: 1px dashed #ACACAC;
    height: 25px;
    line-height: 25px;
    font-size: 13px;
	background: no-repeat 6px center;
}
</style>
</head>

<body class="easyui-layout" style="overflow: hidden;">

	<div class="top-bg" onselectstart="return false;" style="-moz-user-select:-moz-none;">
		<!-- <img alt="" src="images/sys/logo.png"> -->
<!-- 		<img alt="" src="images/sys/logo_test.png" style="position: absolute;top:-7px;"> -->
		<img alt="" src="images/sys/index_logo.png" style="position: absolute;top:12px; left:16px" />
		
		<span style="color: #F8F9FB;display: block;font-size: 22px;font-weight: bold;left: 80px;position: relative;top: 16px;width: 300px;">绥化人口库综合查询平台</span>
		
		<div class="moreOwner">
			<ul id="root_menu_ul" class="nav_ul">
			    <li class="">
			        <div id="comprehensivequery" class="nav_menu">
			            <a class="has_children nav_menu_current_hover" href="javascript:void(0);">
			                <div style="background:url(images/sys/comprehensivequery.png);width:24px;height: 24px; float:left; margin-top: 5px; margin-right: 5px;"></div>
			                <div style="display: inline-block;">
								会议管理
			                </div>
			            </a>
			        </div>
			    </li>
			    <li class="">
			        <div id="statisticalform" class="nav_menu">
			            <a class="has_children" href="javascript:void(0);">
			                <div style="background:url(images/sys/statisticalform.png);width:24px;height: 24px; float:left; margin-top: 5px; margin-right: 5px;"></div>
			                <div style="display: inline-block;">
			                    统计报表
			                </div>
			            </a>
			        </div>
			    </li>
			    <li class="">
			        <div id="systemconfig" class="nav_menu">
			            <a class="has_children" href="javascript:void(0);">
			                <div style="background:url(images/sys/systemconfig.png);width:24px;height: 24px; float:left; margin-top: 5px; margin-right: 5px;"></div>
			                <div style="display: inline-block;">
								系统管理
			                </div>
			            </a>
			        </div>
			    </li>
			</ul>
		</div>
		<div class="separator"></div>
		<div class="userInfo">
			<img src="images/sys/userinfo.png"/>
		</div>
		<div id="moreoption" clickcnt=0> 

		    <dl>
		        <dt id="loginUserInfo" style="text-align: center;">
		
		            欢迎您：<%=userCode %>
					<br/>
		        </dt>
		        <dl>
				    <dt>
				        <a onclick="ShowModifyPwd()" href="javascript:void(0);" id="modifyPwd">
				        	修改密码
				        </a>
				    </dt>
				</dl>
				<dl>
				    <dt>
				        <a onclick="Logout()" href="javascript:void(0);">
				           退出
				        </a>
				    </dt>
				
				</dl>
		    </dl>

		</div>
	</div>
	<div class="content">
		<div id="leftPart" class="leftPart">
			<div style="overflow: hidden; width: 100%;">
<!-- 				<dl style="width: 100%;"> -->
<!-- 					<dt class="secondsubmenu" url="undefined" isShow="1"> -->
<!-- 						<span> <img class="smallicon_url" -->
<!-- 							src="images/sys/arrow_1.png"> -->
<!-- 						</span> <a href="javascript:void(0);"> <img -->
<!-- 							src="images/sys/left_ico2_dsqkz.png"> -->
<!-- 							交通路况可视化 -->
<!-- 						</a> -->
<!-- 					</dt> -->
<!-- 					<dd id="12234" class="child-of-12233" style="display: block;"> -->
<!-- 						<div class="menuparttop"></div> -->
<!-- 						<ul class="content_pic" name="contentPic"> -->
<!-- 							<li id="6301" class="child-of-12234" -->
<!-- 								url="modules/convergent-monitoring/unifiedview/UnifiedView.jsp"> -->
<!-- 								<a href="javascript:void(0);"> <img -->
<!-- 									src="images/sys/left_ico2_sjzx.png"> -->
<!-- 									<em>GIS交通路况显示</em> -->
<!-- 							</a> -->
<!-- 							</li> -->
<!-- 							<li id="6327" class="child-of-12234" -->
<!-- 								url="modules/traffic-management/inspectctrl/tracequery/TraceQuery.jsp"> -->
<!-- 								<a href="javascript:void(0);"> <img -->
<!-- 									src="images/sys/top_ico_4.png"> -->
<!-- 									<em>轨迹查询</em> -->
<!-- 							</a> -->
<!-- 							</li> -->
<!-- 						</ul> -->
<!-- 					</dd> -->
<!-- 					<dt class="secondsubmenu" url="undefined" > -->
<!-- 						<span> <img class="smallicon_url" -->
<!-- 							src="images/sys/arrow_2.png"> -->
<!-- 						</span> <a href="javascript:void(0);"> <img -->
<!-- 							src="images/sys/left_ico2_dsqkz.png"> -->
<!-- 							基础信息可视化 -->
<!-- 						</a> -->
<!-- 					</dt> -->
<!-- 					<dd id="12239" class="child-of-12233" style="display: none;"> -->
<!-- 						<div class="menuparttop"></div> -->
<!-- 						<ul class="content_pic" name="contentPic"> -->
<!-- 							<li id="6306" class="child-of-12239" -->
<!-- 								url="modules/convergent-monitoring/unifiedview/UnifiedView.jsp"> -->
<!-- 								<a href="javascript:void(0);"> <img -->
<!-- 									src="images/sys/left_ico3_zbmb.png"> -->
<!-- 									<em>路口信息查询定位</em> -->
<!-- 							</a> -->
<!-- 							</li> -->
<!-- 							<li id="6320" class="child-of-12239" -->
<!-- 								url="modules/infra-dataresource/vehicle/pubvehicle/VehicleInfoMgr.jsp"> -->
<!-- 								<a href="javascript:void(0);"> <img -->
<!-- 									src="images/sys/page_white_text.png"> -->
<!-- 									<em>重点车辆基本信息管理</em> -->
<!-- 							</a> -->
<!-- 							</li> -->
<!-- 						</ul> -->
<!-- 					</dd> -->
<!-- 					<dt class="secondsubmenu" url="undefined"> -->
<!-- 						<span> <img class="smallicon_url" -->
<!-- 							src="images/sys/arrow_2.png"> -->
<!-- 						</span> <a href="javascript:void(0);"> <img -->
<!-- 							src="images/sys/left_ico2_dsqkz.png"> -->
<!-- 							警力资源可视化 -->
<!-- 						</a> -->
<!-- 					</dt> -->
<!-- 					<dd id="12244" class="child-of-12233" style="display: none;"> -->
<!-- 						<div class="menuparttop"></div> -->
<!-- 						<ul class="content_pic" name="contentPic"> -->
<!-- 						</ul> -->
<!-- 					</dd> -->
<!-- 					<dt class="secondsubmenu" url="undefined"> -->
<!-- 						<span> <img class="smallicon_url" -->
<!-- 							src="images/sys/arrow_2.png"> -->
<!-- 						</span> <a href="javascript:void(0);"> <img -->
<!-- 							src="images/sys/top_ico_3.png"> -->
<!-- 							预警信息管理 -->
<!-- 						</a> -->
<!-- 					</dt> -->
<!-- 					<dd id="12247" class="child-of-12233" style="display: none;"> -->
<!-- 						<div class="menuparttop"></div> -->
<!-- 						<ul class="content_pic" name="contentPic"> -->
<!-- 							<li id="6305" class="child-of-12247" -->
<!-- 								url="modules/convergent-monitoring/warningpublish/WarningPublish.jsp"> -->
<!-- 								<a href="javascript:void(0);"> <img -->
<!-- 									src="images/sys/top_ico_3.png"> -->
<!-- 									<em>恶劣天气预警</em> -->
<!-- 							</a> -->
<!-- 							</li> -->
<!-- 							<li id="6374" class="child-of-12247" -->
<!-- 								url="modules/traffic-management/inspectctrl/realtimepass/popwin/ShowRealTimePassInfo.jsp"> -->
<!-- 								<a href="javascript:void(0);"> <img -->
<!-- 									src="images/sys/3_left_gzbx.png"> -->
<!-- 									<em>重点车辆越界告警图层</em> -->
<!-- 							</a> -->
<!-- 							</li> -->
<!-- 						</ul> -->
						
<!-- 					</dd> -->
<!-- 				</dl> -->
			</div>
		</div>
		<div class="splitDiv"></div>
		<div class="rightPart">
			<div class="tabs">
				<div class="tabOwner" style="width: 350px;padding-left: 8px;">
					<ul>
						<li id="_home" class="current" target="1">
							<a>
							<div class="tab-left"></div>
							<div class="titleCenter">主页</div>
							<div class="tab-Right"></div>
							</a>
						</li>
					
					<!-- 
					
					<li id="6301" class="" target="2">
						<a>
						<div class="tab-left"></div>
						<div class="titleCenter">GIS交通路况显示</div>
						<div class="titleClose">
						<div class="closeOut"></div>
						</div>
					<div class="tab-Right"></div>
					</a>
					</li>
					<li id="6320" class="" target="3">
					<a>
					<div class="tab-left"></div>
					<div class="titleCenter">重点车辆基本信息管理</div>
					<div class="titleClose">
					<div class="closeOut"></div>
					</div>
					<div class="tab-Right"></div>
					</a>
					</li>
					
					 -->
					</ul>
					</div>
			</div>
			
			<div class="contentDiv">
				<div class="1" style="width: 100%;height: 100%;">
					这是主页
				</div>
			</div>
			
		</div>
	</div>
	<div id="win11">	
	</div>
</body>
</html>
