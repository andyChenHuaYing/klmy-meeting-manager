<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String errMsg = session.getAttribute("loginErrMsg")==null?"":(String)session.getAttribute("loginErrMsg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<link href="css/login/login.css" rel="stylesheet" type="text/css">
<script language="javaScript" type="text/javaScript" src="js/script.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=path %>/js/util/md5.js"></script>


<script type="text/javascript">
	if(window !=top){  
	    top.location.href=location.href;  
	}

	var errMsg = '<%=errMsg%>';
	var code ; //在全局 定义验证码  
    function createCode()  
    {   
      code = "";  
      var codeLength = 6;//验证码的长度  
      var checkCode = document.getElementById("checkCode");  
      var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');//所有候选组成验证码的字符，当然也可以用中文的  
         
      for(var i=0;i<codeLength;i++)  
      {  
       
         
      var charIndex = Math.floor(Math.random()*36);  
      code +=selectChar[charIndex];  
        
        
      }  
//      alert(code);  
      if(checkCode)  
      {  
        //checkCode.className="code";  
        //checkCode.value = code;  
        //checkCode.textContent = code;
        $(checkCode).text(code);
      }  
        
    }
	
	
    function validate ()  
    {  
      var inputCode = document.getElementById("input1").value;  
      if(inputCode.length <=0)  
      {  
          alert("请输入验证码！");  
      }  
      else if(inputCode.toUpperCase() != code )  
      {  
         alert("验证码错误！");  
         createCode();//刷新验证码  
      }  
      } 
 
 function errorMsg(){
	  if(errMsg!=null && errMsg!=""){
		  $("#errorMsg").text(errMsg);
	  }
 }
 
 $(function(){
	 errorMsg(); 
 });
 
 var Sys = {};
 var ua = navigator.userAgent.toLowerCase();
	function onSubmit() {
		var userName = $('#ULoginName').val();
		var userPwd = $('#UPassword').val();
		if (userName.length == 0) {
			$('#ULoginName').focus();
			return false;
		}
		if (userPwd.length == 0) {
			$('#UPassword').focus();
			return false;
		}
		$('#loginInputForm').submit();
	}
	
	function gotoReg()
	{
		
		if(Sys.chrome)
		{
		window.location.href = "userAction!gotoReg.action";
		}
		else if(Sys.ie)
		{
		window.location.href = "../../userAction!gotoReg.action";
		}
	}
	
	function register(){
		location.href = "<%=path %>/user.spr?method=toAddUserNew";
	}
	
	function doctorPatientQa(){
		location.href = "<%=path %>/dpaq.spr?method=toDoctorPatientQaList";
	}
	
	function login(){// /user/logon.action
		if($.trim($("#userName1").val())==""){
			alert("用户名为空，请输入用户名！");
			return;
		}
		if($.trim($("#password1").val())==""){
			alert("密码为空，请输入密码！");
			return;
		}
		/*
		if($.trim($("#input1").val())==""){
			alert("验证码为空，请输入验证码！");
			return;
		}else if($.trim($("#input1").val().toUpperCase())!=code.toUpperCase()){
			alert("验证码输入有误，请重新输入！");
			return;
		}*/
		
		$("#userName").val($("#userName1").val());
		$("#password").val(MD5.encrypt($("#password1").val()));
		$("#loginInputForm").attr("action","<%=path %>/user.spr?method=logon");
		$("#loginInputForm").submit();
	}
	
	function isActiveXObject()
	{
		 

        if (window.ActiveXObject)

            Sys.ie = ua.match(/msie ([\d.]+)/)[1]

        else if (document.getBoxObjectFor)

            Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1]

        else if (window.MessageEvent && !document.getBoxObjectFor)

            Sys.chrome = ua.match(/chrome\/([\d.]+)/)[1]

        else if (window.opera)

            Sys.opera = ua.match(/opera.([\d.]+)/)[1]

        else if (window.openDatabase)

            Sys.safari = ua.match(/version\/([\d.]+)/)[1];
	
	}
	
	function keydownEvent(e){
		if(e.keyCode == 13){
			login();
		}
	}
	
	$(function(){
		createCode();
	});
	
</script>
</head>
 <body>
 	<div id="bigBox">
		<div id="top"></div>
	    <div id="middle">
					<form name="loginInputForm" id="loginInputForm" action="" method="post">
							<input type="text" name="userName1" id="userName1" class="loginInput inputUsn" autofocus="autofocus" value=""/>
							<input type="hidden" name="userName" id="userName">
	  						<input type="hidden" name="password" id="password">
							<input type="password" name="password1" id="password1" class="loginInput inputPsw"  onkeydown="keydownEvent(event);"/>
							<input type="button" class="loginButton" onclick="login();"/>
					</form>
		</div>
	    <div id="bottom"></div>			
		<p class="copyRight">绥化人口库综合查询平台 版权所有 ©中兴软创科技股份有限公司	2014-2020&nbsp;&nbsp;技术支持：中兴软创科技股份有限公司</p>
		<p class="copyRight">地址：南京市江宁区正方大道888号 电话：025-66669555</p>
		<p class="copyRight">最低显示分辨率：1024*768px</p>
	</div>
</body>
</html>