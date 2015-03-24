package com.controller.buz.klmy.person;

import com.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andychen on 2015/3/24.<br>
 * Version 1.0-SNAPSHOT<br>
 */
@Controller("KlmyPersonController")
@RequestMapping("klmyPerson.spr")
public class KlmyPersonController {

    /**
     * 跳转到人员信息管理界面
     */
    @RequestMapping(params = "method=toKlmyPersonManagementPage")
    public String toKlmyPersonManagementPage() {
        return "klmyperson/klmyPerson";
    }

    /**
     * 查询人员信息
     */
    @RequestMapping(params = "method=queryKlmyPerson")
    public void queryKlmyPerson(HttpServletRequest request, HttpServletResponse response, String queryCondition) {
        ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
        Map<String, Object> map = new HashMap<String, Object>();
        if (queryCondition != null && "".equals(queryCondition)) {
            JSONObject object = JSONObject.fromObject(queryCondition);
            map = (Map<String, Object>) JSONObject.toBean(object, HashMap.class);
        }
        if (request.getParameter("rows") != null) {
            map.put("rows", Integer.parseInt(request.getParameter("rows")));
        }
        if (request.getParameter("page") != null) {
            map.put("page", Integer.parseInt(request.getParameter("page")));
        }

        JSONObject jsonObject = new JSONObject();

        /*List<Map<String, Object>> list ;
        int count;
        jsonObject.put("total", count);
        jsonObject.put("rows", list);*/
        ResponseUtil.printWrite(response, jsonObject, ResponseUtil.TRANSFER_JSONARRAY);
    }
}
