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
    <title>单位信息管理</title>

    <script type="text/javascript" src="<%=path %>/js/util/JsUtil.js"></script>

    <script type="text/javascript">
        $J.importFile("jquery,easyui");
        $J.include("<%=path %>/js/component/UserComp/dataGrid.js");
        $J.include("<%=path %>/js/component/My97DatePicker/WdatePicker.js");
        $J.include("<%=path %>/js/component/My97DatePicker/skin/WdatePicker.css");
        $J.include("<%=path %>/css/common/buttonCommont.css");
        $J.include("<%=path %>/css/common/selection.css");
    </script>

    <script type="text/javascript">
        var grid;
        var toolbar = [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                var buttons = [{
                    iconCls: 'icon-save',
                    text: '保存',
                    handler: function () {
                        if (!validate()) {
                            return;
                        }
                        if (currentRoleId != null) {
                            roleMenuMap[currentRoleId] = [];
                            var checkedNodes = $('#dd').tree('getChecked');
                            for (var i = 0; i < checkedNodes.length; i++) {
                                roleMenuMap[currentRoleId].push(checkedNodes[i].id);
                            }
                        }
                        if (currentRolegrpId != null) {
                            rolegrpMenuMap[currentRolegrpId] = [];
                            var checkedNodes = $('#ee').tree('getChecked');
                            for (var i = 0; i < checkedNodes.length; i++) {
                                rolegrpMenuMap[currentRolegrpId].push(checkedNodes[i].id);
                            }
                        }

                        var loginName = $.trim($("#ULoginName").val());
                        var password = $.trim($("#addUser_uPassword").val());
                        var userName = $.trim($("#userName").val());
                        var phone = $.trim($("#phone").val());
                        var email = $.trim($("#email").val());
                        var normalAreaId = $("#normalAreaId option:selected").val();
//                        var departId = $("#deptId option:selected").val();
                        var roleList = "";
                        var rolegrpList = "";
                        var selectedRole = $(".selected_role_list option");
                        for (var i = 0; i < selectedRole.length; i++) {
                            roleList += $(selectedRole[i]).val() + ",";
                        }
                        roleList = roleList.substring(0, roleList.length - 1);

                        var selectedRolegrp = $(".selected_rolegrp_list option");
                        for (var i = 0; i < selectedRolegrp.length; i++) {
                            rolegrpList += $(selectedRolegrp[i]).val() + ",";
                        }
                        rolegrpList = rolegrpList.substring(0, rolegrpList.length - 1);

                        var obj = {
                            userCode: loginName, userName: userName,
                            phone: phone, email: email
                        };
                        if (password && password != '') {
                            obj.userPassword = MD5.encrypt(password);
                        }
                        if (normalAreaId) {
//                            obj.departId = departId;
                            obj.normalAreaId = normalAreaId;
                        }
                        if (roleList.length > 0) {
                            obj.roleList = roleList;
                        }
                        $.ajax({
                            url: $cntPath + "/user.spr?method=register",
                            data: "userInfo=" + encodeURI(JSON.stringify(obj)) + (roleList.length > 0 ? "&roleList=" + roleList : "") + "&roleMenuMap=" + JSON.stringify(roleMenuMap) + "&rolegrpMenuMap=" + JSON.stringify(rolegrpMenuMap) + "&rolegrpList=" + rolegrpList,
                            dataType: "json",
                            success: function (data) {
                                if (data.userId && data.userId > 0) {
                                    $.messager.alert('提示', "保存成功");
                                    $('#win').dialog("close");
                                    query();

                                } else if (parseInt(data.userId) == -1) {
                                    $.messager.alert('提示', "名称已存在");
                                } else {
                                    $.messager.alert('提示', "保存失败");
                                }
                            },
                            error: function (msg) {
                                $.messager.alert('提示', "error:" + msg.responseText);
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
                    title: '新增用户',
                    width: 600,
                    height: 450,
                    closed: false,
                    cache: false,
                    href: $cntPath + '/user.spr?method=toAddSysUser',
                    modal: true,
                    buttons: buttons
                });
            }
        }, '-', {
            text: '修改用户',
            iconCls: 'icon-edit',
            handler: function () {
                var userId = grid.getSelectedRowId();
                if (userId == null || userId == "") {
                    $.messager.alert('提示', "请选择要修改的用户");
                    return;
                }

                var buttons = [{
                    iconCls: 'icon-save',
                    text: '保存',
                    handler: function () {
                        if (!validate()) {
                            return;
                        }
                        if (currentRoleId != null) {
                            roleMenuMap[currentRoleId] = [];
                            var checkedNodes = $('#dd').tree('getChecked');
                            for (var i = 0; i < checkedNodes.length; i++) {
                                roleMenuMap[currentRoleId].push(checkedNodes[i].id);
                            }
                        }

                        if (currentRolegrpId != null) {
                            rolegrpMenuMap[currentRolegrpId] = [];
                            var checkedNodes = $('#ee').tree('getChecked');
                            for (var i = 0; i < checkedNodes.length; i++) {
                                rolegrpMenuMap[currentRolegrpId].push(checkedNodes[i].id);
                            }
                        }

                        var loginName = $.trim($("#ULoginName").val());
                        var password = $.trim($("#addUser_uPassword").val());
                        var userName = $.trim($("#userName").val());
                        var phone = $.trim($("#phone").val());
                        var email = $.trim($("#email").val());
//                        var departId = $("#deptId option:selected").val();
                        var normalAreaId = $("#normalAreaId option:selected").val();
                        var roleList = "";
                        var rolegrpList = "";
                        var selectedRole = $(".selected_role_list option");
                        for (var i = 0; i < selectedRole.length; i++) {
                            roleList += $(selectedRole[i]).val() + ",";
                        }
                        roleList = roleList.substring(0, roleList.length - 1);
                        var selectedRolegrp = $(".selected_rolegrp_list option");
                        for (var i = 0; i < selectedRolegrp.length; i++) {
                            rolegrpList += $(selectedRolegrp[i]).val() + ",";
                        }
                        rolegrpList = rolegrpList.substring(0, rolegrpList.length - 1);

                        var obj = {
                            userCode: loginName, userName: userName,
                            phone: phone, email: email
                        };
                        if (password && password != '') {
                            obj.userPassword = MD5.encrypt(password);
                        }
                        if (normalAreaId) {
//                            obj.departId = departId;
                            obj.normalAreaId = normalAreaId;
                        }
                        if (roleList.length > 0) {
                            obj.roleList = roleList;
                        }
                        obj.userId = userId + "";
                        $.ajax({
                            url: $cntPath + "/user.spr?method=update",
                            data: "userInfo=" + encodeURI(JSON.stringify(obj)) + (roleList.length > 0 ? "&roleList=" + roleList : "") + "&roleMenuMap=" + JSON.stringify(roleMenuMap) + "&rolegrpMenuMap=" + JSON.stringify(rolegrpMenuMap) + "&rolegrpList=" + rolegrpList,
                            dataType: "json",
                            success: function (data) {
                                if (data.cnt && data.cnt > 0) {
                                    $.messager.alert('提示', "修改成功");
                                    $('#win').dialog("close");
                                    query();
                                } else if (data.cnt == -1) {
                                    $.messager.alert('提示', "名称已存在");
                                } else {
                                    $.messager.alert('提示', "修改失败");
                                }
                            },
                            error: function (msg) {
                                $.messager.alert('提示', "error:" + msg.responseText);
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
                    title: '修改用户',
                    width: 600,
                    height: 450,
                    closed: false,
                    cache: false,
                    href: $cntPath + '/user.spr?method=toAddSysUser&userId=' + userId,
                    modal: true,
                    buttons: buttons
                });
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                var ids = grid.getSelectedRowIds();
                if (ids.length > 0) {


                    $.messager.confirm('确认', '您确认要删除该用户吗？', function (r) {
                        if (r) {
                            $.ajax({
                                type: "POST",
                                url: $cntPath + "/user.spr?method=deleteUser",
                                data: "userIdList=" + ids.join(","),
                                dataType: "html",
                                success: function (data) {
                                    if (parseInt($.trim(data)) > 0) {
                                        //alert("删除成功！");
                                        $.messager.alert('提示', "删除成功！");
                                        //$('#userManagerGrid').datagrid('loadData', { total: 0, rows: [] });
                                        query();
                                    } else {
                                        $.messager.alert('提示', "删除失败！");
                                    }
                                },
                                error: function (msg) {
                                    $.messager.alert('提示', msg.responseText);
                                }
                            });
                        }
                    });
                } else {
                    $.messager.alert('提示', "请选择要删除的用户");
                }
            }
        }];
        /**
         * 初始化页面
         */
        $(function () {
            $("#cc").css("height", document.documentElement.clientHeight);
            $("#centerDiv").css("height", document.documentElement.clientHeight - 190 - 40);
            grid = new DataGrid("klmyCompanyManagerGrid", "#dg");
            grid.load({}, {toolbar: toolbar});
            setHeight("cc");
        });

        /**
         * 统一查询方法
         */
        function query() {
            var inputs = $("#queryConditionUL input");
            var selects = $("#queryConditionUL select");
            var queryCondition = {};
            for (var i = 0; i < inputs.length; i++) {
                if ($(inputs[i]).attr("type") == "text") {
                    queryCondition[$(inputs[i]).attr("id")] = $.trim($(inputs[i]).val());
                }
            }
            for (var j = 0; j < selects.length; j++) {
                queryCondition[$(selects[j]).attr("id")] = $.trim($(selects[j].val()));
            }

            grid.load({queryCondition: JSON.stringify(queryCondition)}, {toolbar: toolbar});
        }

        function validate() {
            var loginName = $.trim($("#ULoginName").val());
            var password = $.trim($("#addUser_uPassword").val());
            var upassword = $.trim($("#addUser_testUPassword").val());
            var email = $.trim($("#email").val());

            if (/.*[\u4e00-\u9fa5]+.*$/.test(loginName)) {
                $.messager.alert("提示", "用户名含有中文！");
                return false;
            }
            if (loginName.indexOf('%') >= 0) {
                $.messager.alert("提示", "用户名含有百分号！");
                return false;
            }
            if (loginName.indexOf('_') >= 0) {
                $.messager.alert("提示", "用户名含有下划线，请重新输入");
                return false;
            }
            if (loginName.length < 6) {
                $.messager.alert("提示", "用户名长度小于6，请重新输入");
                return false;
            }

            if (password.length < 6) {
                $.messager.alert("提示", "密码长度小于6，请重新输入");
                return false;
            }

            if (password != upassword) {
                $.messager.alert("提示", "两次密码输入不一样，请重新输入");
                return false;
            }

            if (email.length > 0 && !/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(email)) {
                $.messager.alert("提示", "您输入的电子邮箱格式不对！");
                return false;
            }
            return true;
        }

    </script>
</head>
<body style="margin: 0px;overflow: hidden;">
<div id="cc" class="easyui-layout" style="width:100%;height:600px;fit:true;">
    <div data-options="region:'north',title:'查询条件',split:true" style="height:90px;">
        <ul style="margin-left: 0px;padding-left: 10px;" id="queryConditionUL">
            <li>
                <table class="queryPropTable">
                    <tr class="aaron_table_selected_col">
                        <td align="right">地区：</td>
                        <td>
                            <select id="normalAreaId" style="width:155px;">
                            </select>
                        </td>
                        <td></td>
                    </tr>
                    <tr class="aaron_table_selected_col">
                        <td align="right">分类：</td>
                        <td>
                            <select id="classificationId" style="width:155px;">
                            </select>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="td1">
                            关键字：
                        </td>
                        <td class="td2">
                            <input type="text" title="匹配公司名称地址及联系人" id="keyWord">
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
</div>

<div id="win"></div>
<script>
    var contextPath = "<%=path%>";
</script>
<script src="<%=path%>/js/common/selectionInit.js"></script>
<script>
    $(function () {
        withoutDefaultSelection("initAreaSelection", "normalAreaId")
    });
</script>
</body>
</html>
