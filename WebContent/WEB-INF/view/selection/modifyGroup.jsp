<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String id = request.getParameter("id") == null ? "" : request.getParameter("id");
    String groupName = request.getParameter("groupName") == null ? "" : java.net.URLDecoder.
            decode(request.getParameter("groupName"), "UTF-8");
%>

<!DOCTYPE html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>修改分组信息</title>
    <link rel="stylesheet" href="<%=path %>/css/sys/addRole.css" type="text/css"/>
    <script type="text/javascript" src="<%=path %>/js/util/JsUtil.js"></script>
    <script type="text/javascript">
        $J.importFile("jquery,easyui");
        $J.include("<%=path %>/js/component/UserComp/dataGrid.js");
        $J.include("<%=path %>/css/common/buttonCommont.css");
    </script>
    <script type="text/javascript">
        var id = "<%=id%>";
        var groupName = "<%=groupName%>";
        var contextPath = "<%=path%>";
    </script>
</head>
<html>
<body>
<div id="modifyGroup" align="center">
    <h2>修改分组信息</h2>
    请输入新名称：<input id="groupName" type="text" name="groupName" title="分组名称"/>
</div>
</body>


</html>
