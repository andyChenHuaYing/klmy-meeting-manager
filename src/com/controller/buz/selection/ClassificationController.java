package com.controller.buz.selection;

import com.service.buz.selection.ClassificationService;
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
@Controller("classificationController")
@RequestMapping("/classification.spr")
public class ClassificationController {
    private static Logger logger = LoggerFactory.getLogger(ClassificationController.class);
    @Autowired
    @Qualifier("classificationService")
    private ClassificationService classificationService;

    /**
     * 跳转到分类管理页面
     */
    @RequestMapping(params = "method=toClassificationManagementPage")
    public String toClassificationManagementPage() {
        logger.debug("Forward to classification.jsp");
        return "selection/classification";
    }

    /**
     * 跳转到新增分类界面
     */
    @RequestMapping(params = "method=toAddClassification")
    public String toAddClassification() {
        return "selection/addClassification";
    }

    /**
     * 跳转到修改分类页面
     */
    @RequestMapping(params = "method=toModifyClassification")
    public String toModifyClassification() {
        return "selection/modifyClassification";
    }

    /**
     * 查询分类信息
     */
    @RequestMapping(params = "method=queryClassifications")
    public String queryClassifications(HttpServletRequest req, HttpServletResponse response, String queryCondition) throws SQLException {
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
        List<Map<String, Object>> list = classificationService.queryClassifications(map);
        int count = classificationService.queryClassificationsCount(map);
        object.put("total", count);
        object.put("rows", list);
        ResponseUtil.printWrite(response, object, ResponseUtil.TRANSFER_JSONOBJECT);
        return null;

    }

    /**
     * 新增分类
     */
    @RequestMapping(params = "method=addClassification")
    public String addClassification(String classificationName, HttpServletResponse response) throws SQLException {
        if (classificationName == null || "".equals(classificationName)) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
            JSONObject obj = JSONObject.fromObject(classificationName);
            Map map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
            int cnt = this.classificationService.addClassification(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
        return null;
    }

    /**
     * 修改分类
     */
    @RequestMapping(params = "method=updateClassificationName")
    public String updateClassificationName(String classificationName, Long id, HttpServletResponse response) throws SQLException {
        if (classificationName == null || "".equals(classificationName)) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            map.put("classificationName", classificationName);
            int cnt = this.classificationService.modifyClassificationName(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
        return null;
    }

    /**
     * 删除分类
     */
    @RequestMapping(params = "method=deleteClassification")
    public void deleteClassification(Long id, HttpServletResponse response) throws SQLException {
        if (id == null) {
            ResponseUtil.printWrite(response, 0, ResponseUtil.TRANSFER_NONE);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            int cnt = this.classificationService.deleteClassification(map);
            ResponseUtil.printWrite(response, cnt, ResponseUtil.TRANSFER_NONE);
        }
    }
}
