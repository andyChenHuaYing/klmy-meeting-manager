<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    int klmyCompanyId = Integer.parseInt(request.getParameter("klmyCompanyId"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>修改单位信息</title>
    <link rel="stylesheet" href="<%=path %>/css/common/addRecordDiv.css" type="text/css"/>
</head>
<body>

<script type="text/javascript" src="<%=path %>/js/util/JsUtil.js"></script>

<script type="text/javascript">
    $J.importFile("jquery,easyui");
    $J.include("<%=path %>/js/component/UserComp/dataGrid.js");
    $J.include("<%=path %>/css/common/buttonCommont.css");
</script>

<div title="修改单位信息" style="height: 100%;">
    <div id="updateRecordDiv" class="addRecordDiv">
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
                    <select id="addNormalAreaId" style="width:155px;" title="地区">

                    </select>
                </td>
            </tr>
            <tr>
                <td align="left"><span class="aaron_red_star">*</span>分类：</td>
                <td>
                    <select id="addClassificationId" style="width:155px;height: 24px;" title="分类">
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
                           onkeyup="this.value=this.value.replace(/\D/g,'')">
                </td>
            </tr>
        </table>
    </div>
</div>
<script type="text/javascript">
    var contextPath = "<%=path%>";
    var klmyCompanyId = "<%=klmyCompanyId%>"
</script>
<script src="<%=path%>/js/common/selectionInit.js" type="application/javascript"></script>
<script type="application/javascript">
    $(function () {
        $.ajax({
            url: contextPath + "/klmyCompany.spr?method=queryKlmyCompanyInfo",
            data: "klmyCompanyId=" + klmyCompanyId,
            dataType: "json",
            async: false,
            success: function (data) {
                if (data) {
                    //获取父div元素、用于进一步获取节点信息
                    var $updateRecordDiv = $("#updateRecordDiv");

                    //初始化地区、分类下拉框。默认选中原单位信息。
                    var areaList = data.areaList;
                    var areaId = data.areaId;
                    var classificationList = data.classificationList;
                    var classificationId = data.classificationId;
                    //地区下拉框
                    if (areaList) {
                        var html = "<option value=''>--请选择--</option>";
                        for (var i = 0; i < areaList.length; i++) {
                            var id = areaList[i].id;
                            if (areaId == id) {
                                html += "<option selected=selected value='" + id + "'>" + areaList[i].name + "</option>";
                            } else {
                                html += "<option value='" + id + "'>" + areaList[i].name + "</option>";
                            }
                        }
                        $updateRecordDiv.find("#addNormalAreaId").append(html);
                    }
                    //分类下拉框
                    if (classificationList) {
                        var cHtml = "<option value=''>--请选择--</option>";
                        for (var j = 0; j < classificationList.length; j++) {
                            var currentClassificationId = classificationList[j].id;
                            if (classificationId == currentClassificationId) {
                                cHtml += "<option selected=selected value='" + currentClassificationId + "'>" + classificationList[j].name + "</option>";
                            } else {
                                cHtml += "<option value='" + currentClassificationId + "'>" + classificationList[j].name + "</option>";
                            }
                        }
                        $updateRecordDiv.find("#addClassificationId").append(cHtml);
                        //普通输入框
                        $updateRecordDiv.find("#klmyCompanyName").val(data.name);
                        $updateRecordDiv.find("#klmyCompanyAddress").val(data.address);
                        $updateRecordDiv.find("#klmyCompanyContactPeople").val(data.contactPeople);
                        $updateRecordDiv.find("#klmyCompanyContactPhone").val(data.contactPhone);
                    }
                }
            },
            error: function (msg) {
                $.messager.alert("error: " + msg);
            }
        });
    })
</script>
</body>
</html>