<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>新增分类</title>
    <link rel="stylesheet" href="<%=path %>/css/sys/addRole.css" type="text/css"/>
    <script type="text/javascript" src="<%=path %>/js/util/JsUtil.js"></script>
    <script type="text/javascript">
        $J.importFile("jquery,easyui");
        $J.include("<%=path %>/js/component/UserComp/dataGrid.js");
        $J.include("<%=path %>/css/common/buttonCommont.css");
    </script>
</head>
<html>
<body>
<div id="addClassification" align="center">
    <h2>新增分类</h2>

    <p>请输入名称：<input id="classificationName" type="text" title="分类名称" name="classificationName"/></p>
</div>
</body>
<script type="text/javascript">
    var contextPath = "<%=path%>";
</script>
</html>
