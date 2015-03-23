<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String errMsg = session.getAttribute("loginErrMsg")==null?"":(String)session.getAttribute("loginErrMsg");
%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>">
<title>用户登录</title>
  <style type="text/css">
    .code  
        {  
            
            font-family:Arial;  
            font-style:italic;  
            color:Red;  
            border:0;  
            padding:2px 3px;  
            letter-spacing:3px;  
            font-weight:bolder;  
        }  
        .unchanged  
        {  
            border:0;  
        }  
  
    body {
    background: url(images/p_login1.png) no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
    }
	.p_login ul li{ list-style-type:none; margin-bottom:5%;}
	.p_login ul{ margin-top:30px; }
	.p_login{background-image: url("images/p_login2.png");height: 450px;margin: 15% auto;width: 380px;}
	.p_login p{ color:#fff; padding-top:140px; margin-left:5%; /*_margin-top:-0px; _margin-left:10px;*/}
	.p_login ul li input { height:36px; line-height:36px; width:70%; border-radius:5px; border:0; border:none; background:#e6e6e4; border:1px solid #d1d1cf;}
	.p_login ul li .yanzheng { /*width:35%;*/ width:43%; /*_width:70%; _padding-left:10px;*/}
	.p_login ul li .bt_submit{ margin-left:19%;color:#fff;background:#4188c8; width:70%;}
	.p_login ul li .bt_submit:hover{ filter:alpha(opacity=80);opacity:0.5;cursor:pointer; }
	.p_login ul li span a{  margin-left:22%;text-decoration: none;font-weight: bold; }
	.p_login ul li span a{ /*text-decoration: underline;*/ font-size:12px; font-weight:normal; }
	.p_login ul li span .pwd{color:#a4a4a6; margin-left:20%;}
	.p_login ul li span .pwd:hover{color:#4188c8; cursor:pointer;}
	.p_login ul li span .reg{color:#4188c8; }
	.p_login ul li .repwd{ width:15px; }
	
	.p_login ul li span .aaron_reg{color:#4188c8; margin-left:34%;}
	.aaron_reg:hover{
		cursor: pointer;
	}
	.aaron_copyright{
	width:380px; height:30px; line-height:30px; /*bottom:5px;*/ text-align:center; /*position: absolute;*/ color:#516f8e; padding-top:140px;
	}
	
	.p_login ul li .code{ background:#ebf6fc; border:0px;}
  </style>
  <script type="text/javascript" src="<%=path %>/js/jquery/jquery-1.9.1.js"></script>
  <script type="text/javascript" src="<%=path %>/js/util/md5.js"></script>
  <script type="text/javascript">
  var errMsg = '<%=errMsg%>';
  /**
   * 返回此字符串去除前后所有空格后的字符串。
   */
  String.prototype.trim = function()
  {
      return this.trimRight().trimLeft();
  };

  /*
   * 返回此字符串去除开始位置所有空格后的字符串。
   */
  String.prototype.trimLeft = function()
  {
      return this.replace(/^\s*/, "");
  };

  /**
   * 返回此字符串去除后面所有空格后的字符串。
   */
  String.prototype.trimRight = function()
  {
      return this.replace(/\s*$/, "");
  };
    
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
//       alert(code);  
       if(checkCode)  
       {  
         checkCode.className="code";  
         checkCode.value = code;  
       }  
         
     }  
       
      function validate ()  
     {  
       var inputCode = document.getElementById("input1").value;  
       if(inputCode.length <=0)  
       {  
           alert("请输入验证码！");  
       }  
       else if(inputCode != code )  
       {  
          alert("验证码错误！");  
          createCode();//刷新验证码  
       }  
       } 
  
  function errorMsg(){
	  if(errMsg!=null && errMsg!=""){
		  alert(errMsg);
	  }
  }
  
$(document).ready(function(){
	//判断浏览器类型
	
//	isActiveXObject();
	
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
		location.href = "<%=path %>/user.spr?method=toAddUser";
	}
	
	function login(){// /user/logon.action
		if(($("#userName").val()).trim()==""){
			alert("用户名为空，请输入用户名！");
			return;
		}
		if(($("#password").val()).trim()==""){
			alert("密码为空，请输入密码！");
			return;
		}
		if(($("#input1").val()).trim()==""){
			alert("验证码为空，请输入验证码！");
			return;
		}else if(($("#input1").val()).trim()!=code){
			alert("验证码输入有误，请重新输入！");
			return;
		}
		
		$("#password").val(MD5.encrypt($("#password").val()));
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
</script>
  
 </head>

 <body onload="createCode()">
 <form id="loginInputForm" method="post" action="#">
  <div class="p_login">
	  <p>早上好，希望您有一个好心情开始一天的工作！</p>
	  <ul>
		<li>
			用户名：<input name="userName" id="userName" class="user_text" type="text"/>
		</li>
		<li>
			密&emsp;码：<input name="password" id="password" class="user_text" type="password"/>
			<input name="oprFlag" type="hidden" value="1"/>
		</li>
		<li>
			验证码：<input class="yanzheng" onblur="" onkeydown="keydownEvent(event);" type="text" id="input1">
			<input type="text" onclick="createCode()" readonly id="checkCode" class="unchanged" style="width: 80px"  />
		</li>
		
		<li>
			<input class="bt_submit" type="button" value="登&emsp;&emsp;录" onclick="validate();login();"/>
			
		</li>
		<li>
			<span><a class="pwd" >忘记密码？</a></span>
            <span><a class="aaron_reg" onclick="register()">立即注册</a></span>
		</li>
	  </ul>
      	<div class="aaron_copyright">
 			&copy;2014&nbsp;心衰后台管理系统V1.1
 		</div>
  </div>
 </form>
 
 </body>
</html>
