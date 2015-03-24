package com.controller.buz.selection;

import com.service.buz.selection.*;
import com.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * 初始化地区下拉框选项
     */
    @RequestMapping(params = "method=initAreaSelection")
    public void initAreaSelection(HttpServletRequest request, HttpServletResponse response) {
        ResponseUtil.formatResp(response, ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);

    }
}
