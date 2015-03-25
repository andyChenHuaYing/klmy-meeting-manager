<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>地区管理</title>

    <script type="text/javascript" src="<%=path %>/js/util/JsUtil.js"></script>

    <script type="text/javascript">
        $J.importFile("jquery,easyui");
        $J.include("<%=path %>/js/component/UserComp/dataGrid.js");
        $J.include("<%=path %>/css/common/buttonCommont.css");
        $J.include("<%=path %>/css/common/selection.css");
    </script>

    <script type="text/javascript">
        var grid;
        var klmyCompanyId;
        var toolbar = [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                var buttons = [{
                    iconCls: 'icon-save',
                    text: '保存',
                    handler: function () {
                        var $addRecordDiv = $('#addRecordDiv');
                        var klmyCompanyName = $.trim($addRecordDiv.find('#klmyCompanyName').val());
                        var addNormalAreaId = $addRecordDiv.find('#addNormalAreaId').val();
                        var addClassificationId = $addRecordDiv.find('#addClassificationId').val();
                        var klmyCompanyAddress = $.trim($addRecordDiv.find('#klmyCompanyAddress').val());
                        var klmyCompanyContactPhone = $.trim($addRecordDiv.find('#klmyCompanyContactPhone').val());
                        var klmyCompanyContactPeople = $.trim($addRecordDiv.find('#klmyCompanyContactPeople').val());

                        if (klmyCompanyName.length <= 0) {
                            $.messager.alert('提示', "请填写单位名称");
                            return false;
                        }
                        if (addNormalAreaId.length <= 0) {
                            $.messager.alert('提示', "请选择地区");
                            return false;
                        }
                        if (addClassificationId.length <= 0) {
                            $.messager.alert('提示', "请选择分类");
                            return false;
                        }
                        if (klmyCompanyAddress.length <= 0) {
                            $.messager.alert('提示', "请填写单位地址");
                            return false;
                        }

                        var obj = {};
                        obj.klmyCompanyName = klmyCompanyName;
                        obj.addNormalAreaId = addNormalAreaId;
                        obj.addClassificationId = addClassificationId;
                        obj.klmyCompanyAddress = klmyCompanyAddress;
                        obj.klmyCompanyContactPhone = klmyCompanyContactPhone;
                        obj.klmyCompanyContactPeople = klmyCompanyContactPeople;


                        $.ajax({
                            url: $cntPath + "/klmyCompany.spr?method=addKlmyCompany",
                            data: "klmyCompanyInfo=" + encodeURI(JSON.stringify(obj)),
                            method: "post",
                            dataType: "json",
                            async: false,
                            success: function (data) {
                                if (data == 0) {
                                    $.messager.alert('提示', "保存成功");
                                    $('#win').dialog("close");
                                    grid.load({}, {toolbar: toolbar});
                                } else if (parseInt(data) > 0) {
                                    $.messager.alert('提示', "名称已存在");
                                } else {
                                    $.messager.alert('提示', "保存失败");
                                }
                            },
                            error: function (msg) {
                                alert(msg.responseText);
                            }
                        });
                    }

                }, {
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        $('#win').dialog("close");
                    }
                }];

                $('#win').dialog({
                    title: '新增单位',
                    width: 600,
                    height: 450,
                    closed: false,
                    cache: false,
                    href: $cntPath + '/klmyCompany.spr?method=toAddKlmyCompany',
                    modal: true,
                    buttons: buttons
                });
            }
        }, '-', {
            text: '修改',
            iconCls: 'icon-edit',
            handler: function () {
                var selectObj = $("#dg").datagrid('getSelected');
                if (selectObj == null) {
                    $.messager.alert('提示', "请选择要修改的单位！");
                    return;
                }

                klmyCompanyId = selectObj.ID;
                var buttons = [{
                    iconCls: 'icon-save',
                    text: '保存',
                    handler: function () {
                        var normalAreaName = $.trim($("#modifyNormalArea").find("#normalAreaName").val());
                        if (normalAreaName.length <= 0) {
                            $.messager.alert('提示', "请填写地区名称！");
                            return;
                        }
                        $.ajax({
                            url: $cntPath + "/normalArea.spr?method=updateNormalAreaName",
                            data: "normalAreaName=" + normalAreaName + "&id=" + klmyCompanyId,
                            method: "post",
                            dataType: "json",
                            async: false,
                            success: function (data) {
                                if (data > 0) {
                                    $.messager.alert('提示', "修改成功");
                                    $('#win').dialog("close");
                                    grid.load({}, {toolbar: toolbar});
                                } else if (parseInt(data) == -1) {
                                    $.messager.alert('提示', "名称已存在");
                                } else {
                                    $.messager.alert('提示', "修改失败");
                                }
                            },
                            error: function (msg) {
                                alert(msg.responseText);
                            }

                        });
                    }

                }, {
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        $('#win').dialog("close");
                    }
                }];

                $('#win').dialog({
                    title: '修改单位信息',
                    width: 600,
                    height: 450,
                    closed: false,
                    cache: false,
                    href: $cntPath + '/klmyCompany.spr?method=toModifyKlmyCompany&klmyCompanyId=' + klmyCompanyId,
                    modal: true,
                    buttons: buttons
                });

            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                var selectObj = $("#dg").datagrid('getSelected');
                if (selectObj == null) {
                    $.messager.alert('提示', "请选择要删除的地区分类！");
                    return;
                } else {

                    $.messager.confirm('确认', '确定要删除该地区分类？', function (r) {
                        if (r) {
                            $.ajax({
                                url: $cntPath + "/normalArea.spr?method=deleteNormalArea",
                                data: "id=" + selectObj.id,
                                method: "post",
                                dataType: "json",
                                async: false,
                                success: function (data) {
                                    if (data > 0) {
                                        $.messager.alert('提示', "删除成功");
                                        grid.load({}, {toolbar: toolbar});
                                    } else {
                                        $.messager.alert('提示', "删除失败");
                                    }
                                },
                                error: function (msg) {
                                    alert(msg.responseText);
                                }

                            });
                        }
                    });


                }
            }
        }];

        $(function () {
            $("#cc").css("height", document.documentElement.clientHeight);
            $("#centerDiv").css("height", document.documentElement.clientHeight - 190 - 40);
            grid = new DataGrid("klmyCompanyManagerGrid", "#dg");
            grid.load({}, {toolbar: toolbar});
            setHeight("cc");
        });
        function query() {
            var inputs = $("#queryConditionUL").find("input");
            var queryCondition = {};
            var selects = $("#queryConditionUL").find("select");
            for (var i = 0; i < inputs.length; i++) {
                if ($(inputs[i]).attr("type") == "text") {
                    queryCondition[$(inputs[i]).attr("id")] = $.trim($(inputs[i]).val());
                }
            }
            for (var i = 0; i < selects.length; i++) {
                queryCondition[$(selects[i]).attr("id")] = $.trim($(selects[i]).val());
            }
            grid.load({queryCondition: JSON.stringify(queryCondition)}, {toolbar: toolbar});
        }
    </script>
</head>
<body style="margin: 0;overflow: hidden;">
<div id="cc" class="easyui-layout" style="width:100%;height:600px;fit:true;">
    <div data-options="region:'north',title:'查询条件',split:true" style="height:100px;">
        <ul style="margin-left: 0px;padding-left: 10px;" id="queryConditionUL">
            <li>
                <table class="queryPropTable">
                    <tr>
                        <td class="td1">
                            地区名称：
                        </td>
                        <td class="td2">
                            <select id="normalAreaId" style="width:155px;">

                            </select>
                        </td>
                    </tr>
                </table>
            </li>
            <li>
                <table class="queryPropTable">
                    <tr>
                        <td class="td1">
                            分类名称：
                        </td>
                        <td class="td2">
                            <select id="classificationId" style="width:155px;">

                            </select>
                        </td>
                    </tr>
                </table>
            </li>
            <li>
                <table class="queryPropTable">
                    <tr>
                        <td class="td1">
                            关键字：
                        </td>
                        <td class="td2">
                            <input type="text" title="单位名称地址或者联系人" id="keyWord">
                        </td>
                    </tr>
                </table>
            </li>
            <li>
                <table class="queryPropTable">
                    <tr>
                        <td class="td1" colspan="2">
                            <a class="sts-link-btn" onclick="query();" href="javascript:void(0);" style="margin: 0">
                                <span class="sts-link-btn-span">查 询</span>
                            </a>
                        </td>
                    </tr>
                </table>

            </li>
        </ul>
    </div>
    <div data-options="region:'center',title:'查询结果'" style="background:#eee;" id="centerDiv">
        <table id="dg">
        </table>
    </div>
    <div id="win"></div>
</div>
</body>
<script>
    var contextPath = "<%=path%>";
</script>
<script src="<%=path%>/js/common/selectionInit.js"></script>
<script>
    withoutDefaultSelection("initAreaSelection", "normalAreaId");
    withoutDefaultSelection("initClassificationSelection", "classificationId");
</script>
</html>
