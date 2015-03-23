<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String pageTag = request.getParameter("pageTag");
%>

<!DOCTYPE html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>档案</title>
		<link rel="stylesheet" href="<%=path %>/css/sys/userManage.css"	type="text/css" />
	</head>
	<script type="text/javascript" src ="<%=path %>/js/util/JsUtil.js"></script>
	<script type="text/javascript" src ="<%=path %>/js/jquery/jquery-1.9.1.js"></script>
	<script type="text/javascript" src ="<%=path %>/js/component/table.js"></script>
	<script type="text/javascript" src ="<%=path %>/js/sys/personManage.js"></script>
	<script type="text/javascript" src="<%=path %>/js/component/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		var g = {pageTag:'<%=pageTag %>'};
	</script>
 <html> 
  <body>
  <div class="aaron_wrapper">
    	<div class="aaron_query">
        	<div class="aaron_query_simple">
            	<ul class="queryCondition">
        		<li>
        			<span>姓名：<input type="text" id="personName"/></span>
        			<span> 身份证号：<input type="text" id="idCard"/></span>
        		</li>
            	<li><span>  医保卡号：<input type="text" id="medicareCard"/></span>
<!--             	<span>身份：<select id="identityId" > -->
<!--      	<option value="2">患者</option> -->
<!--      	<option value="1">医生</option>     	 -->
<!--      </select></span> -->
                <span><input type="button" value="查询" id="queryBtn" style="margin-left: 239px;margin-right: 26px;"/></span>
            	</li>                
           </ul>
            </div>
        </div>
        <div class="aaron_control">
        <input type="button" style="width: 110px;height: 38px;float: left;text-align: center;cursor: pointer;border: 0;padding: 0;font-weight: 700;font-size: 14px;display: inline-block;vertical-align: baseline;line-height: 38px;outline: 0;background-color: transparent;border-radius: 3px; background-image:url('<%=path%>/images/sys/aaron_btn.png'); background-position:0px 0px;color: #0866C6; box-shadow: 0 2px 5px rgba(0,94,21,.3);margin-left: 10px;" value="确认" id="confirmBtn"/>
        	<!-- <input type="button" style="width: 110px;height: 38px;float: left;text-align: center;cursor: pointer;border: 0;padding: 0;font-weight: 700;font-size: 14px;display: inline-block;vertical-align: baseline;line-height: 38px;outline: 0;background-color: transparent;border-radius: 3px; background-image:url('<%=path%>/images/sys/aaron_btn.png'); background-position:0px 0px;color: #0866C6; box-shadow: 0 2px 5px rgba(0,94,21,.3);" value="新建" id="addBtn"/>
            <input type="button" style="width: 110px;height: 38px;float: left;text-align: center;cursor: pointer;border: 0;padding: 0;font-weight: 700;font-size: 14px;display: inline-block;vertical-align: baseline;line-height: 38px;outline: 0;background-color: transparent;border-radius: 3px; background-image:url('<%=path%>/images/sys/aaron_btn.png'); background-position:0px 0px;color: #0866C6; box-shadow: 0 2px 5px rgba(0,94,21,.3);margin-left: 10px;" value="修改" id="modifyBtn"/>
            <input type="button" style="width: 110px;height: 38px;float: left;text-align: center;cursor: pointer;border: 0;padding: 0;font-weight: 700;font-size: 14px;display: inline-block;vertical-align: baseline;line-height: 38px;outline: 0;background-color: transparent;border-radius: 3px; background-image:url('<%=path%>/images/sys/aaron_btn.png'); background-position:0px 0px;color: #0866C6; box-shadow: 0 2px 5px rgba(0,94,21,.3);margin-left: 10px;" value="删除" id="deleteBtn"/>
             -->
            
        </div>
        <div class="aaron_table" id="tableContainer">
        <!-- 
        	<table cellpadding="0" cellspacing="0">
            	<tr>
                	<th align="left"><input type="checkbox" id="select_all" name="name1_all[]" /></th>
                    <th>
                        <div style=" height:18px; float:left; line-height:18px;">行列一</div>
                        <div style="width:11px; height:18px; float:left;">
                            <a href="#"><img src="./images/s_asc.png" /></a>
                           
                            <a href="#"><img src="./images/s_desc.png" /></a>
                        </div>
                    </th>
                    <th>
                    
                     	<div style=" height:18px; float:left; line-height:18px;">行列二</div>
                        <div style="width:11px; height:18px; float:left;">
                            <a href="#"><img src="./images/s_asc.png" /></a>
                           
                            <a href="#"><img src="./images/s_desc.png" /></a>
                        </div>
                    </th>
                    <th>
                    	<div style=" height:18px; float:left; line-height:18px;">行列三</div>
                        <div style="width:11px; height:18px; float:left;">
                            <a href="#"><img src="./images/s_asc.png" /></a>
                           
                            <a href="#"><img src="./images/s_desc.png" /></a>
                        </div>
                    </th>
                    <th>
                     	<div style=" height:18px; float:left; line-height:18px;">行列四</div>
                        <div style="width:11px; height:18px; float:left;">
                            <a href="#"><img src="./images/s_asc.png" /></a>
                           
                            <a href="#"><img src="./images/s_desc.png" /></a>
                        </div>
                    </th>
                    <th> 
                   		<div style=" height:18px; float:left; line-height:18px;">行列五</div>
                        <div style="width:11px; height:18px; float:left;">
                            <a href="#"><img src="./images/s_asc.png" /></a>
                           
                            <a href="#"><img src="./images/s_desc.png" /></a>
                        </div>
                    </th>
                </tr>
                <tr class="aaron_table_selected_col">
                	<td><input type="checkbox" name="name1[]" /></td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                </tr>
                <tr>
                	<td><input type="checkbox" name="name1[]" /></td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                </tr>
                <tr class="aaron_table_selected_col">
                	<td><input type="checkbox" name="name1[]" /></td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                </tr>
                 <tr>
                	<td><input type="checkbox" name="name1[]" /></td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                </tr>
                <tr class="aaron_table_selected_col">
                	<td><input type="checkbox" name="name1[]" /></td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                    <td>用户名</td>
                </tr>
            </table>
            
             -->
        </div>
        <div class="aaron_pages">
        	共<label id="pageSum">0</label>页，本页是第<label id="currPage">0</label>页  每页
            <select class="pageSelect">
            	<option value="10">10</option>
                <option value="20">20</option>
                <option value="50">50</option>
            </select>
            记录
               <a href="javascript:void(0)" id="firstPage">首页</a>
               <a href="javascript:void(0)" id="prevPage">上一页</a>
               <a href="javascript:void(0)" id="nextPage">下一页</a>
               <a href="javascript:void(0)" id="lastPage">末页</a>
               
               跳转到第<input style="width:20px;height: 18px;margin: 0;padding: 0;width: 20px;" type="text" id="skipPage" onkeyup="this.value=this.value.replace(/\D/g,'')"/>页  <input type="button" value="跳转" id="skipBtn"/>
        </div>
    </div>
  
  </body>
</html>
