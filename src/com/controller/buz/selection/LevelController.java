package com.controller.buz.selection;

import com.service.buz.selection.LevelService;
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
 * 分类管理控制器
 * Created by andychen on 2015/3/18.<br>
 * Version 1.0-SNAPSHOT<br>
 */
@Controller("levelController")
@RequestMapping("/level.spr")
public class LevelController {
    private static Logger logger = LoggerFactory.getLogger(LevelController.class);
    @Autowired
    @Qualifier("levelService")
    private LevelService levelService;

    /**
     * 跳转到分类管理页面
     */
    @RequestMapping(params = "method=toLevelManagementPage")
    public String toLevelManagementPage() {
        logger.debug("Forward to level.jsp");
        return "selection/level";
    }

    /**
     * 跳转到新增分类界面
     */
    @RequestMapping(params = "method=toAddLevel")
    public String toAddLevel() {
        return "selection/addLevel";
    }

    /**
     * 跳转到修改分类页面
     */
    @RequestMapping(params = "method=toModifyLevel")
    public String toModifyLevel() {
        return "selection/modifyLevel";
    }

    /**
     * 查询分类信息
     */
    @RequestMapping(params = "method=queryLevels")
    public String queryLevels(HttpServletRequest req, HttpServletResponse response, String queryCondition) throws SQLException {
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
        List<Map<String, Object>> list = levelService.queryLevels(map);
        int count = levelService.queryLevelsCount(map);
//        List<Map<String, Object>> list = gaRyJbxxService.queryGaRy(map);
//        int count = gaRyJbxxService.queryGaRyJbxxCnt(map);
        object.put("total", count);
        object.put("rows", list);
        ResponseUtil.printWrite(response, object, ResponseUtil.TRANSFER_JSONOBJECT);
        return null;

    }

    /**
     * 新增分类
     */
    @RequestMapping(params = "method=addLevel")
    public String addLevel(String levelName, HttpServletResponse response) throws SQLException {
        if (levelName == null || "".equals(levelName)) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
            JSONObject obj = JSONObject.fromObject(levelName);
            Map map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
            int cnt = this.levelService.addLevel(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
        return null;
    }

    /**
     * 修改分类
     */
    @RequestMapping(params = "method=updateLevelName")
    public String updateLevelName(String levelName, Long id, HttpServletResponse response) throws SQLException {
        if (levelName == null || "".equals(levelName)) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            map.put("levelName", levelName);
            int cnt = this.levelService.modifyLevelName(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
        return null;
    }

    /**
     * 删除分类
     */
    @RequestMapping(params = "method=deleteLevel")
    public void deleteLevel(Long id, HttpServletResponse response) throws SQLException {
        if (id == null) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            int cnt = this.levelService.deleteLevel(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
    }
}
