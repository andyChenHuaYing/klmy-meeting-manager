package com.controller.buz.selection;

import com.service.buz.klmy.company.KlmyCompanyService;
import com.service.buz.selection.*;
import com.service.sys.UserService;
import com.util.ResponseUtil;
import com.util.TransferUtil;
import com.vo.buz.selection.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by andychen on 2015/3/24.<br>
 * Version 1.0-SNAPSHOT<br>
 */
@Controller("selectionController")
@RequestMapping("/selection.spr")
public class SelectionController {

    @Autowired
    @Qualifier("normalAreaService")
    private NormalAreaService normalAreaService;

    @Autowired
    @Qualifier("classificationService")
    private ClassificationService classificationService;

    @Autowired
    @Qualifier("levelService")
    private LevelService levelService;

    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;

    @Autowired
    @Qualifier("nationService")
    private NationService nationService;

    @Autowired
    @Qualifier("klmyCompanyService")
    private KlmyCompanyService klmyCompanyService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    /**
     * 初始化地区下拉框选项
     */
    @RequestMapping(params = "method=initAreaSelection")
    public void initAreaSelection(Long userId, HttpServletResponse response) throws SQLException {
        ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
        List<TbBaseNormalArea> tbBaseNormalAreas = this.normalAreaService.queryAllAreasForVO(null);
        List<SelectionVO> list = TransferUtil.transferSelectionList(tbBaseNormalAreas);
        JSONObject object = new JSONObject();
        Long userAreaId = null;
        if (userId != null) {
            userAreaId = userService.queryUserAreaId(userId);
        }
        object.put("list", list);
        object.put("userAreaId", userAreaId);
        ResponseUtil.printWrite(response, object, ResponseUtil.TRANSFER_NONE);
    }

    /**
     * 初始化分类下拉框选项
     */
    @RequestMapping(params = "method=initClassificationSelection")
    public void initClassificationSelection(HttpServletResponse response) throws SQLException {
        ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
        List<ClassificationVO> list = this.classificationService.queryAllClassificationsForVO(null);
        JSONObject object = new JSONObject();
        object.put("list", list);
        ResponseUtil.printWrite(response, object, ResponseUtil.TRANSFER_NONE);
    }

    /**
     * 初始化分组下拉框选项
     */
    @RequestMapping(params = "method=initGroupSelection")
    public void initGroupSelection(HttpServletResponse response) throws SQLException {
        ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
        List<GroupVO> list = this.groupService.queryAllGroupsForVO(null);
        JSONObject object = new JSONObject();
        object.put("list", list);
        ResponseUtil.printWrite(response, object, ResponseUtil.TRANSFER_NONE);
    }

    /**
     * 初始化级别下拉框选项
     */
    @RequestMapping(params = "method=initLevelSelection")
    public void initLevelSelection(HttpServletResponse response) throws SQLException {
        ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
        List<LevelVO> list = this.levelService.queryAllLevelsForVO(null);
        JSONObject object = new JSONObject();
        object.put("list", list);
        ResponseUtil.printWrite(response, object, ResponseUtil.TRANSFER_NONE);
    }

    /**
     * 初始化单位下拉框选项
     */
    @RequestMapping(params = "method=initKlmyCompanySelection")
    public void initKlmyCompanySelection(HttpServletResponse response) throws SQLException {
        ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
        List<KlmyCompanyVo> list = this.klmyCompanyService.queryAllKlmyCompanyForVO(null);
        JSONObject object = new JSONObject();
        object.put("list", list);
        ResponseUtil.printWrite(response, object, ResponseUtil.TRANSFER_NONE);
    }

    /**
     * 初始化民族下拉框选项
     */
    @RequestMapping(params = "method=initNationSelection")
    public void initNationSelection(HttpServletResponse response) throws SQLException {
        ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
        List<NationVO> list = this.nationService.queryAllKlmyNationForVO(null);
        JSONObject object = new JSONObject();
        object.put("list", list);
        ResponseUtil.printWrite(response, object, ResponseUtil.TRANSFER_NONE);
    }



}
