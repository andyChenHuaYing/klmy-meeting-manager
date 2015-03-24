/**
 * 根据不同需要初始化下拉框。
 * 注意：引用当前js之前一定要引用jquery！
 *
 * Created by andychen on 2015/3/24.
 * Version 1.0-SNAPSHOT
 */
$(function () {

});

/**
 * * 下拉框初始化基础函数。
 * @param type 下拉框类型：
 *              地区  initAreaSelection
 *              分类  initClassificationSelection
 *              分组  initGroupSelection
 *              民族  initNationSelection
 *
 * @param selectNodeId
 *              下拉选择框节点ID。
 * @param klmyCompanyId
 *              单位ID、用于修改单位时、初始化页面中与单位信息有关的下拉框。
 * @param klmyPersonId
 *              人员ID、用于修改人员信息时、初始化人员信息修改页面中关于人员信息下拉框值回填。
 * @param userId
 *              用户ID、用于管理员新增单位、人员时、初始化新增界面中关于地区管理员所在地区默认选中。
 */
function init(type, selectNodeId, klmyCompanyId, klmyPersonId, userId) {
    if (type == null || selectNodeId == null) {
        return false;
    }

    if (klmyCompanyId == null && klmyCompanyId == null && userId == null) {
        withoutDefaultSelection(type, selectNodeId);
    }
}

/**
 * 初始化无默认值的下拉框
 * @param type
 *          下拉框类型：参见init同名参数
 * @param selectNodeId
 *          下拉选择框节点ID
 */
function withoutDefaultSelection(type, selectNodeId) {
    $.ajax({
        url: contextPath + "/selection.spr?method=" + type,
        dataType: "json",
        async: false,
        success: function (data) {
            if (data) {
                var selectionDataList = data.selectionDataList;
                if (selectionDataList) {
                    var html = "<option value=''>--请选择--</option>";
                    for (var i = 0; i < selectionDataList.length; i++) {
                        var id = selectionDataList[i].id;
                        html += "<option value='" + id + "'>" + selectionDataList[i].selectionName + "</option>";
                    }
                    $("#" + selectNodeId + "").append(html);
                }
            }
        },
        error: function (msg) {
            $.messager.alert("error:" + msg);
        }
    });
}

/**
 * 管理员新增人员信息、单位信息等需要关联管理员所在地区时、将地区选项中默认选项设置为管理员所在地区。
 * @param type
 * @param selectNodeId
 * @param userId
 */
function selectedByUserId(type, selectNodeId, userId) {
    init(type, selectNodeId, null, null, userId);
}

/**
 * 修改单位信息时、用于回填单位信息相关下拉框、并且将当前单位属性设置为默认选中状态。
 * @param type
 * @param selectNodeId
 * @param klmyCompanyId
 */
function selectedByKlmyCompanyId(type, selectNodeId, klmyCompanyId) {
    init(type, selectNodeId, klmyCompanyId, null, null);
}

/**
 * 修改人员信息时、用于回填人员信息相关下拉框、并且将当前人员属性设置为默认选中状态。
 * @param type
 * @param selectNodeId
 * @param klmyCompanyId
 */
function selectedByKlmyPersonId(type, selectNodeId, klmyPersonId) {
    init(type, selectNodeId, null, klmyPersonId, null);
}