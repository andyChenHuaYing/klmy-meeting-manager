<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>心衰管理系统</title>
<link href="css/css.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.10.4.min.css"/>
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.4.min.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/script.js"></script>
<script language="javascript">
var old_obj_id = "";
var old_im_id = "";
function switchMenu(){
	obj1 = document.getElementById('tr01');
	obj2 = document.getElementById('tr02');
	im_obj = document.getElementById('im1');
	obj1.style.display==""?obj1.style.display="none":obj1.style.display="";
	obj2.style.display==""?obj2.style.display="none":obj2.style.display="";
	im_obj.src.search("btn-d.jpg")==-1?im_obj.src="images/btn-d.jpg":im_obj.src="images/btn-u.jpg";

}

$(function() {
$.datepicker.regional['zh-CN'] = {
		closeText: '关闭',
		prevText: '&#x3c;上月',
		nextText: '下月&#x3e;',
		currentText: '今天',
		monthNames: ['一月','二月','三月','四月','五月','六月',
		'七月','八月','九月','十月','十一月','十二月'],
		monthNamesShort: ['一','二','三','四','五','六',
		'七','八','九','十','十一','十二'],
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
		dayNamesMin: ['日','一','二','三','四','五','六'],
		weekHeader: '周',
		dateFormat: 'yy-mm-dd',
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: true,
		yearSuffix: ''
		//yearSuffix: '年'
		};
	$.datepicker.setDefaults($.datepicker.regional['zh-CN']);	
	
    $( "#fromdate,#fromdate2,#todate,#todate2" ).datepicker({
	   regional:'zh-CN',
      showOn: "button",
      buttonImage: "images/calendar.gif",
      buttonImageOnly: true
    });
  });
</script>
</head>

<body bgcolor="#FFFFFF" >
<table border="0" cellpadding="0" cellspacing="0" class="Content">
  <tr>
   <td >
   	<form name="form1" method="post" action="" ENCTYPE = "multipart/form-data">
   		<table width="100%" border="0" cellpadding="0" cellspacing="0" id="search">
		  <tr>
		   <td class="tit" >患者：</td>
		   <td ><input name="Title" type="text" value="#" class="txt" ></td>
		  </tr>
		  <tr>
		   <td class="tit" >身份证号：</td>
		   <td ><input name="Title" type="text" value="#" class="txt"></td>
		  </tr>
		  <tr>
		   <td class="tit" >医保卡号：</td>
		   <td ><input name="Title" type="text" value="#" class="txt"></td>
		  </tr>
		  <tr id="tr01" style="display:none" >
		   <td class="tit" >创建时间：</td>
		   <td ><input name="fromdate" type="text" value="#" class="date" id="fromdate"> 到 <input name="todate" type="text" value="#" class="date" id="todate"></td>
		  </tr>
		  <tr id="tr02" style="display:none" >
		   <td class="tit" >修改时间：</td>
		   <td ><input name="fromdate2" type="text" value="#" class="date" id="fromdate2"> 到 <input name="todate2" type="text" value="#" class="date" id="todate2"></td>
		  </tr>
		  <tr>
		   <td class="tit" ></td>
		   <td ><div><input type="submit" id="Action" name="Action" value="查询" class="btn"></div><span onclick="switchMenu();">高级查询&nbsp;&nbsp;<img src="images/btn-d.jpg" id="im1" align="absmiddle"></span></td>
		  </tr></table>
   	</form>
   </td>
  </tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" class="Content">
  <tr>
   <td >
		<ul id="ListBtn">
			<li class="Add"><a href="#">新建</a></li>
			<li class="Edit"><a href="#">修改</a></li>
			<li class="Del"><a href="#">删除</a></li>
		</ul>
   </td>
  </tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" class="Content">
  <tr>
   <td >
	<table width="100%" border="0" cellpadding="0" cellspacing="0" id="ListTable" >
	  <tr class="tit">
        <td width="60"><input type="checkbox"></td>
        <td width="60">患者</td>
        <td >档案号</td>
        <td width="100">最新档案时间</td>
        <td width="100">建档人</td>
        <td width="180">身份证号</td>
        <td width="180">医保卡号</td>
      </tr>
      <tr class="list">
        <td ><input type="checkbox"></td>
        <td >小刚</td>
        <td >201339478</td>
        <td >2014-03-04</td>
        <td >admin</td>
        <td >320102199912303235</td>
        <td >320102199912303235</td>
      </tr>
      <tr class="list">
        <td ><input type="checkbox"></td>
        <td >小刚</td>
        <td >201339478</td>
        <td >2014-03-04</td>
        <td >admin</td>
        <td >320102199912303235</td>
        <td >320102199912303235</td>
      </tr>
      <tr class="list">
        <td ><input type="checkbox"></td>
        <td >小刚</td>
        <td >201339478</td>
        <td >2014-03-04</td>
        <td >admin</td>
        <td >320102199912303235</td>
        <td >320102199912303235</td>
      </tr>
      <tr class="list">
        <td ><input type="checkbox"></td>
        <td >小刚</td>
        <td >201339478</td>
        <td >2014-03-04</td>
        <td >admin</td>
        <td >320102199912303235</td>
        <td >320102199912303235</td>
      </tr>
      <tr class="list">
        <td ><input type="checkbox"></td>
        <td >小刚</td>
        <td >201339478</td>
        <td >2014-03-04</td>
        <td >admin</td>
        <td >320102199912303235</td>
        <td >320102199912303235</td>
      </tr>
    </table>
   </td>
  </tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" class="Content">
  <tr>
   <td id="ListNav">共2页，本页是第1页 每页<select name="Each"><option value="10">10</option><option value="20">20</option><option value="30">30</option></select>记录 <a href="#">首页</a> <a href="#">上一页</a> <a href="#">下一页</a> <a href="#">末页</a> 跳转到第<input type="text" name="to" value="" class="to" >页 <input type="submit" name="jump" value="跳转" class="jump">
   </td>
  </tr>
</table>
</body>
</html>