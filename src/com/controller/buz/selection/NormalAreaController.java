package com.controller.buz.selection;

import com.service.buz.selection.NormalAreaService;
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
 * 地区管理控制器
 * Created by andychen on 2015/3/18.<br>
 * Version 1.0-SNAPSHOT<br>
 */
@Controller("normalAreaController")
@RequestMapping("/normalArea.spr")
public class NormalAreaController {
    private static Logger logger = LoggerFactory.getLogger(NormalAreaController.class);
    @Autowired
    @Qualifier("normalAreaService")
    private NormalAreaService normalAreaService;

    /**
     * 跳转到地区管理页面
     */
    @RequestMapping(params = "method=toNormalAreaManagementPage")
    public String toNormalAreaManagementPage() {
        logger.debug("Forward to normalArea.jsp");
        return "selection/normalArea";
    }

    /**
     * 跳转到新增地区界面
     */
    @RequestMapping(params = "method=toAddNormalArea")
    public String toAddNormalArea() {
        return "selection/addNormalArea";
    }

    /**
     * 跳转到修改地区页面
     */
    @RequestMapping(params = "method=toModifyNormalArea")
    public String toModifyNormalArea() {
        return "selection/modifyNormalArea";
    }

    /**
     * 查询地区信息
     */
    @RequestMapping(params = "method=queryNormalAreas")
    public String queryNormalAreas(HttpServletRequest req, HttpServletResponse response, String queryCondition) throws SQLException {
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
        List<Map<String, Object>> list = normalAreaService.queryAreas(map);
        int count = normalAreaService.queryAreasCount(map);
//        List<Map<String, Object>> list = gaRyJbxxService.queryGaRy(map);
//        int count = gaRyJbxxService.queryGaRyJbxxCnt(map);
        object.put("total", count);
        object.put("rows", list);
        ResponseUtil.printWrite(response, object, ResponseUtil.TRANSFER_JSONOBJECT);
        return null;

    }

    /**
     * 新增地区
     */
    @RequestMapping(params = "method=addNormalArea")
    public String addNormalArea(String normalAreaName, HttpServletResponse response) throws SQLException {
        if (normalAreaName == null || "".equals(normalAreaName)) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
            JSONObject obj = JSONObject.fromObject(normalAreaName);
            Map map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
            int cnt = this.normalAreaService.addArea(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
        return null;
    }

    /**
     * 修改地区
     */
    @RequestMapping(params = "method=updateNormalAreaName")
    public String updateNormalAreaName(String normalAreaName, Long id, HttpServletResponse response) throws SQLException {
        if (normalAreaName == null || "".equals(normalAreaName)) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            map.put("normalAreaName", normalAreaName);
            int cnt = this.normalAreaService.modifyAreaName(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
        return null;
    }

    /**
     * 删除地区
     */
    @RequestMapping(params = "method=deleteNormalArea")
    public void deleteNormalArea(Long id, HttpServletResponse response) throws SQLException {
        if (id == null) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            int cnt = this.normalAreaService.deleteArea(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
    }
}
