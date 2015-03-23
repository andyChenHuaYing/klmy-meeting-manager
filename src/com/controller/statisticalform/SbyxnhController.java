package com.controller.statisticalform;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.statisticalform.SbyxnhService;
import com.util.ResponseUtil;


/**
 * 
 * @className: SbyxnhController 
 * @author: gu.xiaogang
 * @description: 社保与新农合情况控制器
 * @date: 2014年9月11日
 */
@Controller(value="sbyxnhController")
@RequestMapping("sbyxnh.spr")
public class SbyxnhController {
	
	@Autowired
	private SbyxnhService sbyxnhService;
	
	/** 
	 * @Title: toAreaSsncms 
	 * @Description: 跳转到地区参保与新农合情况
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAreaSsncms")
	public String toAreaSsncms(){
		return "statisticalform/areaSsncms";
	}
	
	/** 
	 * @Title: queryAreaSsncms 
	 * @Description: 查询男女参保与新农合情况
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryAreaSsncms")
	public String queryAreaSsncms(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.sbyxnhService.queryAreaSsncms(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
}
