<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="home/css/responsive-tables.css">
<link rel="stylesheet" href="home/css/style.default.css" type="text/css" />
<link href="css/css.css" rel="stylesheet" type="text/css">

<title>主界面</title>
<script type="text/javascript" src = "<%=path %>/js/util/JsUtil.js"></script>
<script type="text/javascript" src="home/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="home/js/jquery-ui-1.10.3.min.js"></script>
<script type="text/javascript" src ="<%=path %>/js/component/tableNew.js"></script>
<script type="text/javascript" src ="<%=path %>/js/sys/mainContent.js"></script>

	<script type="text/javascript">
		$(function(){
			jQuery('#datepicker').datepicker();	
		});
		
	</script>
</head>
<body style="background-color: #F4FAFC;overflow-y: auto;">
<div class="extendDiv"></div>
<div style="width: 100%;position: absolute;left:0px;top:0px;z-index: 2;height: 40px;display: none;" class="menuDiv">
<img src="home/images/close.png" style="position: absolute;top:0;right:0;z-index: 3;" width="30" class="closeMenu">
<ul id="tabs"><li tabId="mainPage" class="checked"><span>主页</span></li></ul>
<div style="background: url('images/ui-icons.png') no-repeat; width: 24px;height: 24px;position: relative;top:10px;left:40%;"></div>
</div>
<div class="rightpanel" id="mainPage" style="" isTab="1">
        <div class="pageheader" id="pageheader" style="background:#f4fafc;">
           <div class="xinshuai_content">
           	<div class="content_top">
           		<div class="top_doctor">
           			<a class="doctor_img"><img width="100" height="100" src="home/images/photos/thumb1.png" /></a>
           			<div class="doctor_details">
           				<p class="p_user">早上好！</p>
						<p class="p_user">欢迎登录心衰管理系统</p>
           				<p class="p_time">登录时间：<%=new Date().toLocaleString() %></p>
           				<p class="p_job">全科医师</P>
           			</div>
           			<div style="clear:both;float:left;margin-top:30px;">
           			<ul>
           				<li>已建档案<a>(20)</a></li>
           				<li>随访记录<a>(20)</a></li>
           				<li>私信<a>(20)</a></li>
           			</ul>
           			</div>
           		</div><!-- top_doctor-->
           		<div class="top_news">
					<div class="t_news_top">
						<span>最新公告</span>
						<div class="t_news_more"><a href="">更多>></a></div>
					</div>
					<ul>
						<li>2013世界主题宣传活动<span>2013-12-22</span></li>
						<li>创建社会转化平台<span>2013-12-22</span></li>
						<li>落实慢病纺织规划<span>2013-12-22</span></li>
						<li>2013世界主题宣传活动<span>2013-12-22</span></li>
						<li>创建社会转化平台<span>2013-12-22</span></li>
					</ul>
				</div>
           	</div>
           	<div class="content_footer">
				 <div id="searchDiv" style="width: 100%;height: 80px;text-align: center;">
					<span style="margin-right: 1%;font-size: 28px;">患者名称:</span><input id="personName" type="text" style="width: 30%;margin-right: 3%">
					<span style="margin-right: 1%;font-size: 28px;">类型:</span>
					<select id="searchType" style="width: 15%;margin-right: 3%;vertical-align: top;height: 30px;font-size: 23px;">
						<option value="">全部</option>
						<option value="1">档案</option>
						<option value="2">随访</option>
					</select>
					<input id="queryBtn" type="button" value="查询" style="background: none repeat scroll 0 0 #008B7F;border: 0 none;color: #E8E8E8;font-size: 14px;height: 30px;text-align: center;width: 100px;vertical-align: top;">
				</div>
				
				<div id="tableDiv" style="width: 100%;min-height: 80px;display: none;">
				<div id="tableContainer" style="width: 100%;">
				
				</div>
					<table border="0" cellpadding="0" cellspacing="0" class="Content">
					  <tr>
					   <td id="ListNav">共<label id="pageSum" style="display: inline-block;">0</label>页，本页是第<label id="currPage" style="display: inline-block;">0</label>页 每页<select name="Each" class="pageSelect" style="width: 43px;"><option value="10">10</option><option value="20">20</option><option value="50">50</option></select>记录 <a href="javascript:void(0)" id="firstPage">首页</a> <a href="javascript:void(0)" id="prevPage">上一页</a> <a href="javascript:void(0)" id="nextPage">下一页</a> <a href="javascript:void(0)" id="lastPage">末页</a> 跳转到第<input id="skipPage" type="text" name="to" value="" class="to"  onkeyup="this.value=this.value.replace(/\D/g,'')" style="margin-bottom: 3px;">页 <input type="button" name="jump" value="跳转" class="jump" id="skipBtn">
					   </td>
					  </tr>
					</table>
				</div>
           	</div>
           </div>
            
        </div><!--pageheader-->
        
        <div class="maincontent" style="display:none">
            <div class="maincontentinner">
                <div class="row-fluid">
                    <div id="dashboard-left" class="span8">
                        
                        <h5 class="subtitle">快捷按钮</h5>
                        <ul class="shortcuts">
                            <li class="events">
                                <a href="">
                                    <span class="shortcuts-icon iconsi-event"></span>
                                    <span class="shortcuts-label">档案搜索</span>
                                </a>
                            </li>
                            <li class="products">
                                <a href="">
                                    <span class="shortcuts-icon iconsi-cart"></span>
                                    <span class="shortcuts-label">统计图表</span>
                                </a>
                            </li>
                            <li class="archive">
                                <a href="">
                                    <span class="shortcuts-icon iconsi-archive"></span>
                                    <span class="shortcuts-label">统计图表</span>
                                </a>
                            </li>
                            <li class="help">
                                <a href="">
                                    <span class="shortcuts-icon iconsi-help"></span>
                                    <span class="shortcuts-label">新建档案</span>
                                </a>
                            </li>
							 <li class="add">
                                <a href="">
                                    <span class="shortcuts-icon iconsi-add"></span>
                                    <span class="shortcuts-label">快捷添加</span>
                                </a>
                            </li>
                        </ul>
                        
                </div><!--row-fluid-->
            </div><!--maincontentinner-->
        </div><!--maincontent-->
        
    </div>
    </div>
    <!-- 
    <div id="archiveManage" style="width: 100%;height: 100%;" isTab="1">
    	<iframe src="archive.spr?method=toManageArchive&oprFlag=add" style="width: 100%;min-height: 625px;"></iframe>
    </div>
     -->
    
</body>
</html>