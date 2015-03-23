package com.controller.buz.selection;

import com.service.buz.selection.GroupService;
import com.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Happy daily, happy life.<br>
 * =========================<br>
 * Description:<br>
 * 族别管理控制器
 * Created by andychen on 2015/3/18.<br>
 * Version 1.0-SNAPSHOT<br>
 */
@Controller("groupController")
@RequestMapping("/group.spr")
public class GroupController {
    private static Logger logger = LoggerFactory.getLogger(GroupController.class);
    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;

    /**
     * 跳转到分组管理页面
     */
    @RequestMapping(params = "method=toGroupManagementPage")
    public String toGroupManagementPage() {
        logger.debug("Forward to group.jsp");
        return "selection/group";
    }

    /**
     * 跳转到新增分组界面
     */
    @RequestMapping(params = "method=toAddGroup")
    public String toAddGroup() {
        return "selection/addGroup";
    }

    /**
     * 跳转到修改分组页面
     */
    @RequestMapping(params = "method=toModifyGroup")
    public String toModifyGroup() {
        return "selection/modifyGroup";
    }

    /**
     * 查询分组信息
     */
    @RequestMapping(params = "method=queryGroups")
    public String queryGroups(HttpServletRequest req, HttpServletResponse response, String queryCondition) throws SQLException {
        ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
        Map<String, Object> map = new HashMap<String, Object>();
        if (queryCondition != null && !"".equals(queryCondition)) {
            JSONObject object = JSONObject.fromObject(queryCondition);
            map = (Map<String, Object>) JSONObject.toBean(object, HashMap.class);
        }
        if (req.getParameter("rows") != null) {
            map.put("rows", Integer.valueOf(req.getParameter("rows")));
        }
        if (req.getParameter("page") != null) {
            map.put("page", Integer.valueOf(req.getParameter("page")));
        }

        JSONObject object = new JSONObject();
        List<Map<String, Object>> list = groupService.queryGroups(map);
        int count = groupService.queryGroupsCount(map);
//        List<Map<String, Object>> list = gaRyJbxxService.queryGaRy(map);
//        int count = gaRyJbxxService.queryGaRyJbxxCnt(map);
        object.put("total", count);
        object.put("rows", list);
        ResponseUtil.printWrite(response, object, ResponseUtil.TRANSFER_JSONOBJECT);
        return null;

    }

    /**
     * 新增分组
     */
    @RequestMapping(params = "method=addGroup")
    public String addGroup(String groupName, HttpServletResponse response) throws SQLException {
        if (groupName == null || "".equals(groupName)) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
            JSONObject obj = JSONObject.fromObject(groupName);
            Map map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
            int cnt = this.groupService.addGroup(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
        return null;
    }

    /**
     * 修改分组
     */
    @RequestMapping(params = "method=updateGroupName")
    public String updateGroupName(String groupName, Long id, HttpServletResponse response) throws SQLException {
        if (groupName == null || "".equals(groupName)) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            map.put("groupName", groupName);
            int cnt = this.groupService.modifyGroupName(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
        return null;
    }

    /**
     * 删除分组
     */
    @RequestMapping(params = "method=deleteGroup")
    public void deleteGroup(Long id, HttpServletResponse response) throws SQLException {
        if (id == null) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            int cnt = this.groupService.deleteGroup(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
    }
}
