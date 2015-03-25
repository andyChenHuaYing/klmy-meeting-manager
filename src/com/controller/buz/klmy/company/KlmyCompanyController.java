package com.controller.buz.klmy.company;

import com.service.buz.klmy.company.KlmyCompanyService;
import com.service.buz.selection.ClassificationService;
import com.service.buz.selection.NormalAreaService;
import com.util.ResponseUtil;
import com.util.TransferUtil;
import com.vo.buz.selection.ClassificationVO;
import com.vo.buz.selection.KlmyCompanyVo;
import com.vo.buz.selection.SelectionVO;
import com.vo.buz.selection.TbBaseNormalArea;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andychen on 2015/3/24.<br>
 * Version 1.0-SNAPSHOT<br>
 */
@Controller("klmyCompanyController")
@RequestMapping("/klmyCompany.spr")
public class KlmyCompanyController {

    @Autowired
    @Qualifier("klmyCompanyService")
    private KlmyCompanyService klmyCompanyService;

    @Autowired
    @Qualifier("normalAreaService")
    private NormalAreaService normalAreaService;

    @Autowired
    @Qualifier("classificationService")
    private ClassificationService classificationService;

    /**
     * 跳转到单位信息管理界面
     */
    @RequestMapping(params = "method=toKlmyCompanyManagementPage")
    public String toKlmyCompanyManagementPage() {
        return "klmycompany/klmyCompany";
    }

    /**
     * 跳转到新增单位信息界面
     */
    @RequestMapping(params = "method=toAddKlmyCompany")
    public String toAddKlmyCompany() {
        return "klmycompany/addKlmyCompany";
    }

    /**
     * 跳转到修改单位信息界面
     */
    @RequestMapping(params = "method=toModifyKlmyCompany")
    public String toModifyKlmyCompany() {
        return "klmycompany/updateKlmyCompany";
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

    /**
     * 新增单位信息
     */
    @RequestMapping(params = "method=addKlmyCompany")
    public void addKlmyCompany(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
        String klmyCompanyInfo = URLDecoder.decode(request.getParameter("klmyCompanyInfo"), "utf8");
        JSONObject object = JSONObject.fromObject(klmyCompanyInfo);
        Map<String, Object> map = (Map<String, Object>) JSONObject.toBean(object, HashMap.class);
        int flag = this.klmyCompanyService.saveKlmyCompanyInfo(map);
        ResponseUtil.printWrite(response, flag, ResponseUtil.TRANSFER_NONE);
    }

    /**
     * 修改单位信息时回填修改页面显示的单位信息
     */
    @RequestMapping(params = "method=queryKlmyCompanyInfo")
    public void queryKlmyCompanyInfo(Long klmyCompanyId, HttpServletResponse response) throws Exception {
        ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
        //查询所有地区
        List<TbBaseNormalArea> tbBaseNormalAreas = this.normalAreaService.queryAllAreasForVO(null);
        List<SelectionVO> areaList = TransferUtil.transferSelectionList(tbBaseNormalAreas);

        //查询所有分类
        List<ClassificationVO> classificationList = this.classificationService.queryAllClassificationsForVO(null);

        //查询单位信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", klmyCompanyId);
        KlmyCompanyVo companyVo = this.klmyCompanyService.queryAllKlmyCompanyForVO(map).get(0);

        JSONObject object = JSONObject.fromObject(companyVo);
        object.put("areaList", areaList);
        object.put("classificationList", classificationList);
        ResponseUtil.printWrite(response, object, ResponseUtil.TRANSFER_JSONOBJECT);

    }
}
