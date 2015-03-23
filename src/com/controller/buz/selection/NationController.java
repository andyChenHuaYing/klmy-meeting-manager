package com.controller.buz.selection;

import com.service.buz.selection.NationService;
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
@Controller("nationController")
@RequestMapping("/nation.spr")
public class NationController {
    private static Logger logger = LoggerFactory.getLogger(NationController.class);
    @Autowired
    @Qualifier("nationService")
    private NationService nationService;

    /**
     * 跳转到民族管理页面
     */
    @RequestMapping(params = "method=toNationManagementPage")
    public String toNationManagementPage() {
        logger.debug("Forward to nation.jsp");
        return "selection/nation";
    }

    /**
     * 跳转到新增民族界面
     */
    @RequestMapping(params = "method=toAddNation")
    public String toAddNation() {
        return "selection/addNation";
    }

    /**
     * 跳转到修改民族页面
     */
    @RequestMapping(params = "method=toModifyNation")
    public String toModifyNation() {
        return "selection/modifyNation";
    }

    /**
     * 查询民族信息
     */
    @RequestMapping(params = "method=queryNations")
    public String queryNations(HttpServletRequest req, HttpServletResponse response, String queryCondition) throws SQLException {
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
        List<Map<String, Object>> list = nationService.queryNations(map);
        int count = nationService.queryNationsCount(map);
//        List<Map<String, Object>> list = gaRyJbxxService.queryGaRy(map);
//        int count = gaRyJbxxService.queryGaRyJbxxCnt(map);
        object.put("total", count);
        object.put("rows", list);
        ResponseUtil.printWrite(response, object, ResponseUtil.TRANSFER_JSONOBJECT);
        return null;

    }

    /**
     * 新增民族
     */
    @RequestMapping(params = "method=addNation")
    public String addNation(String nationName, HttpServletResponse response) throws SQLException {
        if (nationName == null || "".equals(nationName)) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
            JSONObject obj = JSONObject.fromObject(nationName);
            Map map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
            int cnt = this.nationService.addNation(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
        return null;
    }

    /**
     * 修改民族
     */
    @RequestMapping(params = "method=updateNationName")
    public String updateNationName(String nationName, Long id, HttpServletResponse response) throws SQLException {
        if (nationName == null || "".equals(nationName)) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            map.put("nationName", nationName);
            int cnt = this.nationService.modifyNationName(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
        return null;
    }

    /**
     * 删除民族
     */
    @RequestMapping(params = "method=deleteNation")
    public void deleteNation(Long id, HttpServletResponse response) throws SQLException {
        if (id == null) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            int cnt = this.nationService.deleteNation(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
    }
}
