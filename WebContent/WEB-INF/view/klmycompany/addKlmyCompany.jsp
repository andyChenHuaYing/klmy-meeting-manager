<%@ page import="com.util.UserHolder" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    Long userId = UserHolder.getUserContext().getUserId();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>新增单位信息</title>
</head>
<body>
<link rel="stylesheet" href="<%=path %>/css/sys/addExistsUser.css" type="text/css"/>
<link rel="stylesheet" href="<%=path %>/css/common/addRecordDiv.css" type="text/css"/>

<script type="text/javascript" src="<%=path %>/js/util/md5.js"></script>
<script type="text/javascript" src="<%=path %>/js/util/JsUtil.js"></script>

<script type="text/javascript">
    $J.importFile("jquery,easyui");
    $J.include("<%=path %>/js/component/UserComp/dataGrid.js");
    $J.include("<%=path %>/css/common/buttonCommont.css");
</script>

<div id="tt" class="easyui-tabs" style="width:100%;height:376px;">
    <div title="单条录入单位信息" style="height: 100%;">
        <div id="addRecordDiv" class="addRecordDiv">
            <table style="margin-left: 28%">
                <caption style="color: #000000;font-size: 18px;padding-bottom: 5px;padding-top: 10px;">
                    <strong>单位基本信息</strong>
                </caption>
                <tr>
                    <td align="left">
                        <span class="aaron_red_star">*</span>单位名称：
                    </td>
                    <td class="middle_td">
                        <input name="klmyCompanyName" class="aaron_add_txt" id="klmyCompanyName"
                               type="text" title="单位名称"/>
                    </td>
                </tr>
                <tr>
                    <td align="left"><span class="aaron_red_star">*</span>地区：</td>
                    <td>
                        <select id="addNormalAreaId" style="width:155px;">

                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="left"><span class="aaron_red_star">*</span>分类：</td>
                    <td>
                        <select id="addClassificationId" style="width:155px;height: 24px;">
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        <span class="aaron_red_star">*</span>单位地址：
                    </td>
                    <td class="middle_td">
                        <input name="klmyCompanyAddress" class="aaron_add_txt"
                               id="klmyCompanyAddress" title="单位地址" type="text"/>
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        联系人：
                    </td>
                    <td class="middle_td">
                        <input name="klmyCompanyContactPeople" class="aaron_add_txt"
                               id="klmyCompanyContactPeople" title="联系人" type="text"/>
                    </td>
                </tr>

                <tr>
                    <td align="left">联系电话：</td>
                    <td>
                        <input type="text" maxLength="11"
                               id="klmyCompanyContactPhone"
                               name="klmyCompanyContactPhone" title="联系电话"
                               onkeyup="this.value=this.value.replace(/\D/g,'')"
                               onafterpaste="this.value=this.value.replace(/\D/g,'')">
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div title="批量录入单位信息" style="height: 100%;padding:  0 40px 0 20px;">
        <div class="aaron_wrapper">
            <div class="aaron_add_table">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var g_userId = "<%=userId%>";
    var contextPath = "<%=path%>";
</script>
<script src="<%=path%>/js/common/selectionInit.js" type="application/javascript"></script>
<script type="application/javascript">
    withoutDefaultSelection("initClassificationSelection", "addClassificationId");
    selectedAreaByUserId("initAreaSelection", "addNormalAreaId", g_userId);
</script>
</body>
</html>