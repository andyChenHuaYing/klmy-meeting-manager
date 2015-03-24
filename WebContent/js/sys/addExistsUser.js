/**
 *
 */
var zTree = null;
var currentRoleId = null;//记录当前选中的roleId
var currentRolegrpId = null;//记录当前选中的rolegrpId

var roleMenuMap = {};
var rolegrpMenuMap = {};


$('#dd').tree({
    url: contextPath + "/area.spr?method=queryAreaTree2",
    checkbox: true
});
$('#ee').tree({
    url: contextPath + "/area.spr?method=queryAreaTree2",
    checkbox: true
});
var personId = null;
select_role();
//	double_click_role();
/*
 select_dep();
 select_dep_step2();
 aaron_dep_OK();
 aaron_dep_Cancel();
 */
form_verify();

if (g_userId != '') {
    $("#basicInfo caption strong").text("修改用户");
}

$.ajax({
    url: contextPath + "/user.spr?method=getUserInitInfo",
    data: 'updatedUserId=' + g_userId,
    dataType: "json",
    async: false,
    success: function (data) {
        if (data) {
            /*//部门下拉选项
             if(data.deptList){
             var html = "<option value=''>--请选择--</option>";
             for(var i=0;i<data.deptList.length;i++){
             html+="<option value='"+data.deptList[i].departId+"' style='padding-left:"+15*(data.deptList[i].deptLevel-1)+"px'>"+data.deptList[i].name+"</option>";
             }
             $("#deptId").append(html);
             }*/
            var normalAreaList = data.normalAreaList;
            if (normalAreaList) {
                var html = "<option value=''>--请选择--</option>";
                for (var i = 0; i < normalAreaList.length; i++) {
                    var areaId = normalAreaList[i].id;
                    if (areaId == data.areaId) {
                        html += "<option selected='selected' value='" + areaId + "'>" + normalAreaList[i].areaName + "</option>";
                    } else {
                        html += "<option value='" + areaId + "'>" + normalAreaList[i].areaName + "</option>";
                    }
                }
                $("#normalAreaId").append(html);
            }

            //权限组列表
            if (data.rolegrpList) {
                var html = "";
                for (var i = 0; i < data.rolegrpList.length; i++) {
                    html += '<option value=' + data.rolegrpList[i].rolegrpId + '>' + data.rolegrpList[i].rolegrpName + '</option>';
                }
                $(".rolegrp_list").append(html);
                bindOptDbEvent();
            }

            //权限列表
            if (data.roleList) {
                var html = "";
                for (var i = 0; i < data.roleList.length; i++) {
                    html += '<option value=' + data.roleList[i].ROLE_ID + '>' + data.roleList[i].ROLE_NAME + '</option>';
                }
                $(".role_list").append(html);
                bindOptDbEvent();
            }

        }
    },
    error: function (msg) {
        $.messager.alert("error:" + msg);
    }
});

var initUserInfo = function (userVO) {//初始化页面信息
    $("#ULoginName").val(userVO.userCode);
    $("#userName").val(userVO.userName);
    $("#phone").val(userVO.phone);
    $("#email").val(userVO.email);
    $("#deptId option[value='" + userVO.departId + "']").attr("selected", "selected");
};


function bindOptDbEvent() {
    $(".role_list option").unbind("dblclick");
    $(".selected_role_list option").unbind("dblclick");
    $(".rolegrp_list option").unbind("dblclick");
    $(".selected_rolegrp_list option").unbind("dblclick");

    $(".role_list option").bind("dblclick", function () {
        $(".selected_role_list").append('<option value=' + $(this).val() + '>' + $(this).text() + '</option>');
        $(this).remove();
        bindOptDbEvent();
    });
    $(".selected_role_list option").bind("dblclick", function () {
        $(".role_list").append('<option value=' + $(this).val() + '>' + $(this).text() + '</option>');
        $(this).remove();
        bindOptDbEvent();
    });

    $(".rolegrp_list option").bind("dblclick", function () {
        $(".selected_rolegrp_list").append('<option value=' + $(this).val() + '>' + $(this).text() + '</option>');
        $(this).remove();
        bindOptDbEvent();
    });
    $(".selected_rolegrp_list option").bind("dblclick", function () {
        $(".rolegrp_list").append('<option value=' + $(this).val() + '>' + $(this).text() + '</option>');
        $(this).remove();
        bindOptDbEvent();
    });

}
///////////
function form_verify() {
    $("input[name=aaron_user_name]").focusin(function (e) {
        $(this).parent().next().text("用户名由3-15个字符组成（不含中文、百分号、下划线）");
    });

    $("input[name=aaron_user_name]").focusout(function (e) {
        if ($(this).val().length < 3 || $(this).val().length > 15) {
            $(this).parent().next().empty().append('<span style="color:red;">用户名必须由3-15个字符组成（不含中文、百分号、下划线）</span>');
        }

    });
}

/////
function aaron_dep_OK() {
    $(".aaron_dep_OK").click(function () {
        $(".select_dep_content").hide();
    });
}

//////
function aaron_dep_Cancel() {
    $(".aaron_dep_Cancel").click(function () {
        $(".aaron_add_txt_dep").val($("input[name=original_val]").val());
        $(".select_dep_content").hide();
    });
}

/////////
function select_dep_step2() {
    $(".dep_list tr").click(function () {
        $(".aaron_add_txt_dep").val($(this).children("td").first().text());
        $(".dep_list tr").css("background", "");
        $(this).css("background", "red");
    });
}

////////
function select_dep() {
    $(".aaron_add_txt_dep").dblclick(function () {
        $(".aaron_add_txt_dep").val("请选择……");
        $(".aaron_add_txt_dep").attr("departId", null);
    });
    $(".select_dep").click(function () {
        /*
         $(".select_dep_content").show();
         $("input[name=original_val]").val($(".aaron_add_txt_dep").val());
         */
//		var departmentTree = new departTree("departSelectDiv");
        var departObj = window.showModalDialog(contextPath + "/user.spr?method=toCommonTree", null, "dialogHeight:400px;dialogWidth:300px;center:1;");
//		alert(departObj);
        if (departObj != null) {
            $(".aaron_add_txt_dep").val(departObj.departName);
            $(".aaron_add_txt_dep").attr("departId", departObj.departId);
        }

    });


    $(".select_dep_close").click(function () {
        $(".select_dep_content").hide();
    });
}


function select_role() {
    /////////////////////////////////////
    $(".select_all").click(function () {
        if ($(".role_list option").length > 0) {
            $(".role_list option").each(function () {
                $(".selected_role_list").append('<option value=' + $(this).val() + '>' + $(this).text() + '</option>');
                roleMenuMap[parseInt($(this).val())] = [];
                $(this).remove();
            });
        }
        bindOptDbEvent();
    });
    ////////////////////////////////////////
    $(".select_one").click(function () {
        if ($(".role_list option:selected").length > 0) {
            $(".role_list option:selected").each(function () {
                $(".selected_role_list").append('<option value=' + $(this).val() + '>' + $(this).text() + '</option>');
                roleMenuMap[parseInt($(this).val())] = [];
                $(this).remove();
            });
        } else {
            alert("请在待选角色里选择要添加的角色");
        }
        bindOptDbEvent();
    });

    /////////////////////////////////////////
    $(".remove_one").click(function () {
        if ($(".selected_role_list option:selected").length > 0) {
            $(".selected_role_list option:selected").each(function () {
                $(".role_list").append('<option value=' + $(this).val() + '>' + $(this).text() + '</option>');
                delete roleMenuMap[parseInt($(this).val())];
                $(this).remove();

            });
        } else {
            alert("请在已选角色里选择要删除的角色");
        }
        bindOptDbEvent();
    });
    /////////////////////////////////////////
    $(".remove_all").click(function () {
        if ($(".selected_role_list option").length > 0) {
            $(".selected_role_list option").each(function () {
                $(".role_list").append('<option value=' + $(this).val() + '>' + $(this).text() + '</option>');
                delete roleMenuMap[parseInt($(this).val())];
                $(this).remove();
            });
        }
        bindOptDbEvent();
    });


    $(".select_all_rolegrp").click(function () {
        if ($(".rolegrp_list option").length > 0) {
            $(".rolegrp_list option").each(function () {
                $(".selected_rolegrp_list").append('<option value=' + $(this).val() + '>' + $(this).text() + '</option>');
                rolegrpMenuMap[parseInt($(this).val())] = [];
                $(this).remove();
            });
        }
        bindOptDbEvent();
    });
    ////////////////////////////////////////
    $(".select_one_rolegrp").click(function () {
        if ($(".rolegrp_list option:selected").length > 0) {
            $(".rolegrp_list option:selected").each(function () {
                $(".selected_rolegrp_list").append('<option value=' + $(this).val() + '>' + $(this).text() + '</option>');
                rolegrpMenuMap[parseInt($(this).val())] = [];
                $(this).remove();
            });
        } else {
            alert("请在待选角色里选择要添加的角色");
        }
        bindOptDbEvent();
    });

    /////////////////////////////////////////
    $(".remove_one_rolegrp").click(function () {
        if ($(".selected_rolegrp_list option:selected").length > 0) {
            $(".selected_rolegrp_list option:selected").each(function () {
                $(".rolegrp_list").append('<option value=' + $(this).val() + '>' + $(this).text() + '</option>');
                delete rolegrpMenuMap[parseInt($(this).val())];
                $(this).remove();

            });
        } else {
            alert("请在已选角色里选择要删除的角色");
        }
        bindOptDbEvent();
    });
    /////////////////////////////////////////
    $(".remove_all_rolegrp").click(function () {
        if ($(".selected_rolegrp_list option").length > 0) {
            $(".selected_rolegrp_list option").each(function () {
                $(".rolegrp_list").append('<option value=' + $(this).val() + '>' + $(this).text() + '</option>');
                delete rolegrpMenuMap[parseInt($(this).val())];
                $(this).remove();
            });
        }
        bindOptDbEvent();
    });


    /////////////////////////////////////////

    $("#confirmBtn").click(function () {
        if (currentRoleId) {
            roleMenuMap[currentRoleId] = [];
            var checkedNodes = zTree.getCheckedNodes();
            for (var i = 0; i < checkedNodes.length; i++) {
                roleMenuMap[currentRoleId].push(checkedNodes[i].id);
            }
        }


        var loginName = $.trim($("#ULoginName").val());
        var password = $.trim($("#addUser_uPassword").val());
        var userName = $.trim($("#userName").val());
        var phone = $.trim($("#phone").val());
        var email = $.trim($("#email").val());
        var departId = $("#deptId option:selected").val();
        var roleList = "";
        var selectedRole = $(".selected_role_list option");
        for (var i = 0; i < selectedRole.length; i++) {
            roleList += $(selectedRole[i]).val() + ",";
        }
        roleList = roleList.substring(0, roleList.length - 1);
        var obj = {
            userCode: loginName, userName: userName,
            phone: phone, email: email
        };
        if (password && password != '') {
            obj.userPassword = MD5.encrypt(password);
        }
        if (departId) {
            obj.departId = departId;
        }
        if (roleList.length > 0) {
            obj.roleList = roleList;
        }
        if (g_userId.length > 0) {
            obj.userId = g_userId;
            $.ajax({
                url: contextPath + "/user.spr?method=update",
                data: "userInfo=" + encodeURI(JSON.stringify(obj)) + (roleList.length > 0 ? "&roleList=" + roleList : "") + "&roleMenuMap=" + JSON.stringify(roleMenuMap),
                dataType: "json",
                success: function (data) {
                    if (data.cnt && data.cnt > 0) {
                        alert("修改用户成功！");
                        window.returnValue = true;
                        window.close();
                    } else {
                        alert("修改用户失败！");
                    }
                },
                error: function (msg) {
                    alert("error:" + msg);
                }
            });
        } else {
            $.ajax({
                url: contextPath + "/user.spr?method=register",
                data: "userInfo=" + encodeURI(JSON.stringify(obj)) + (roleList.length > 0 ? "&roleList=" + roleList : "") + "&roleMenuMap=" + JSON.stringify(roleMenuMap),
                dataType: "json",
                success: function (data) {
                    if (data.userId && data.userId > 0) {
                        alert("添加用户成功！");
                        window.returnValue = true;
                        window.close();
                    } else {
                        alert("添加用户失败！");
                    }
                },
                error: function (msg) {
                    alert("error:" + msg);
                }
            });
        }

    });

    var validate = function () {
        var loginName = $("#ULoginName").val();

        if (/.*[\u4e00-\u9fa5]+.*$/.test($.trim(loginName))) {
            alert("用户名含有中文！");

        }
    };

    $("#resetBtn").click(function () {
        $("#ULoginName").val("");
        $("#addUser_uPassword").val("");
        $("#addUser_testUPassword").val("");
        $("#addUser_uCard").val("");
        $("#medicareCard").val("");
        $("#userName").val("");
        $("input[name='sex']").eq(0).attr("checked", "checked");
        $("#birthday").val("");
        $("#phoneNo").val("");
        $("#telNo").val("");
        $("#education").val("");
        $("#national").val("");
        $("#nativePlace").val("");
        $("#ismarried").val(0);
        $("#height").val("");
        $("#address").val("");
        $("#uComments").val("");
        $("#medicalRecordNo").val("");
        $(".aaron_add_txt_dep").val("");
        $(".aaron_add_txt_dep").attr("departId", null);

        if ($(".selected_role_list option").length > 0) {
            $(".selected_role_list option").each(function () {
                $(".role_list").append('<option value=' + $(this).val() + '>' + $(this).text() + '</option>');
                $(this).remove();
            });
        }

    });
    $(document).on("click", ".selected_role_list option", function () {
        if (currentRoleId) {
            roleMenuMap[currentRoleId] = [];
            var checkedNodes = $('#dd').tree('getChecked');
            for (var i = 0; i < checkedNodes.length; i++) {
                roleMenuMap[currentRoleId].push(checkedNodes[i].id);
            }
            for (var i = 0; i < checkedNodes.length; i++) {
                $('#dd').tree('uncheck', checkedNodes[i].target);
            }

            if (roleMenuMap[$(this).val()] != null) {
                var roleMenuStr = roleMenuMap[$(this).val()].join(",");
                var nodes = getAllNodes($("#dd").tree('getRoots'), "#dd");
                for (var i = 0; i < nodes.length; i++) {
                    if (roleMenuStr.indexOf(nodes[i].id) >= 0) {
                        $("#dd").tree('check', nodes[i].target);
                    }
                }
            }
            currentRoleId = $(this).val();
        } else if (roleMenuMap[$(this).val()]) {
            var checkedNodes = $('#dd').tree('getChecked');
            for (var i = 0; i < checkedNodes.length; i++) {
                $('#dd').tree('uncheck', checkedNodes[i]);
            }

            var roleMenuStr = roleMenuMap[$(this).val()].join(",");
            var nodes = getAllNodes($("#dd").tree('getRoots'), "#dd");
            for (var i = 0; i < nodes.length; i++) {
                if (roleMenuStr.indexOf(nodes[i].id) >= 0) {
                    $("#dd").tree('check', nodes[i].target);
                }
            }
            currentRoleId = $(this).val();
        } else {
            var nodes = $('#dd').tree('getChecked');
            for (var i = 0; i < nodes.length; i++) {
                $('#dd').tree('uncheck', nodes[i]);
            }
            currentRoleId = $(this).val();
            roleMenuMap[currentRoleId] = [];
        }

    });

    $(document).on("click", ".selected_rolegrp_list option", function () {
        if (currentRolegrpId) {
            rolegrpMenuMap[currentRolegrpId] = [];
            var checkedNodes = $('#ee').tree('getChecked');
            for (var i = 0; i < checkedNodes.length; i++) {
                rolegrpMenuMap[currentRolegrpId].push(checkedNodes[i].id);
            }
            for (var i = 0; i < checkedNodes.length; i++) {
                $('#ee').tree('uncheck', checkedNodes[i].target);
            }

            if (rolegrpMenuMap[$(this).val()] != null) {
                var roleMenuStr = rolegrpMenuMap[$(this).val()].join(",");
                var nodes = getAllNodes($("#ee").tree('getRoots'), "#ee");
                for (var i = 0; i < nodes.length; i++) {
                    if (roleMenuStr.indexOf(nodes[i].id) >= 0) {
                        $("#ee").tree('check', nodes[i].target);
                    }
                }

            }
            currentRolegrpId = $(this).val();
        } else if (rolegrpMenuMap[$(this).val()]) {
            var checkedNodes = $('#ee').tree('getChecked');
            for (var i = 0; i < checkedNodes.length; i++) {
                $('#ee').tree('uncheck', checkedNodes[i]);
            }

            var roleMenuStr = rolegrpMenuMap[$(this).val()].join(",");
            var nodes = getAllNodes($("#ee").tree('getRoots'), "#ee");
            for (var i = 0; i < nodes.length; i++) {
                if (roleMenuStr.indexOf(nodes[i].id) >= 0) {
                    $("#ee").tree('check', nodes[i].target);
                }
            }
            currentRolegrpId = $(this).val();
        } else {
            var nodes = $('#ee').tree('getChecked');
            for (var i = 0; i < nodes.length; i++) {
                $('#ee').tree('uncheck', nodes[i]);
            }
            currentRolegrpId = $(this).val();
            rolegrpMenuMap[currentRolegrpId] = [];
        }

    });

}
/*
 * 返回所有节点
 */
function getAllNodes(nodes, container) {
    var rtnNodes = [];
    for (var i = 0; i < nodes.length; i++) {
        if ($(container).tree("isLeaf", nodes[i].target) == true) {
            rtnNodes.push(nodes[i]);
        }
        var children = $(container).tree("getChildren", nodes[i].target);

        if (children) {
            for (var j = 0; j < children.length; j++) {
                if ($(container).tree("isLeaf", children[j].target) == true) {
                    rtnNodes.push(children[j]);
                }
            }
        }
    }
    return rtnNodes;
}
if (g_userId.length > 0) {//修改，需要初始化页面
    $.ajax({
        url: contextPath + "/user.spr?method=queryUserInfo",
        data: "userId=" + g_userId,
        dataType: "json",
        async: false,
        success: function (data) {
            if (data.userVO) {//初始化用户信息
                initUserInfo(data.userVO);
            }
            if (data.roleAreaMap) {
                roleMenuMap = data.roleAreaMap;
            }
            if (data.rolegrpAreaMap) {
                rolegrpMenuMap = data.rolegrpAreaMap;
            }

            if (data.roleList) {//初始化权限点
                for (var i = 0; i < data.roleList.length; i++) {
                    $(".selected_role_list").append($(".role_list option[value=" + data.roleList[i].ROLE_ID + "]"));
                }
                bindOptDbEvent();
            }
            if (data.rolegrpList) {//初始化权限组
                for (var i = 0; i < data.rolegrpList.length; i++) {
                    $(".selected_rolegrp_list").append($(".rolegrp_list option[value=" + data.rolegrpList[i].ROLEGRP_ID + "]"));
                }
                bindOptDbEvent();
            }

        },
        error: function (msg) {
            alert("error:" + msg);
        }
    });
}