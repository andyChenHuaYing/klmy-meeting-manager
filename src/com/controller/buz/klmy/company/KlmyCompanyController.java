package com.controller.buz.klmy.company;

import com.service.buz.klmy.company.KlmyCompanyService;
import com.util.ResponseUtil;
import net.sf.json.JSONObject;
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
 * Created by andychen on 2015/3/24.<br>
 * Version 1.0-SNAPSHOT<br>
 */
@Controller("klmyCompanyController")
@RequestMapping("klmyCompany.spr")
public class KlmyCompanyController {

    @Autowired
    @Qualifier("klmyCompanyService")
    private KlmyCompanyService klmyCompanyService;

    /**
     * 跳转到单位信息管理界面
     */
    @RequestMapping(params = "method=toKlmyCompanyManagementPage")
    public String toKlmyCompanyManagementPage() {
        return "klmycompany/klmyCompany";
    }

    /**
     * 查询单位信息
     */
    @RequestMapping(params = "method=queryKlmyCompany")
    public void queryKlmyCompany(HttpServletRequest request, HttpServletResponse response, String queryCondition) throws SQLException {
        ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
        Map<String, Object> map = new HashMap<String, Object>();
        if (queryCondition != null && !"".equals(queryCondition)) {
            JSONObject object = JSONObject.fromObject(queryCondition);
            map = (Map<String, Object>) JSONObject.toBean(object, HashMap.class);
        }

        if (request.getParameter("rows") != null) {
            map.put("rows", Integer.parseInt(request.getParameter("rows")));
        }
        if (request.getParameter("page") != null) {
            map.put(("page"), Integer.parseInt(request.getParameter("page")));
        }
        JSONObject object = new JSONObject();
        List<Map<String, Object>> list = klmyCompanyService.queryKlmyCompany(map);
        int count = klmyCompanyService.queryKlmyCompanyCount(map);
        object.put("total", count);
        object.put("rows", list);
        ResponseUtil.printWrite(response, object, ResponseUtil.TRANSFER_JSONOBJECT);

    }

}
