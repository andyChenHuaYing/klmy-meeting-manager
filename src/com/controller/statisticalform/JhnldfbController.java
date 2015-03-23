package com.controller.statisticalform;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.statisticalform.JhnldfbService;
import com.util.ResponseUtil;


/**
 * 
 * @className: JhnldfbController 
 * @author: gu.xiaogang
 * @description: 结婚年龄段分布控制器
 * @date: 2014年9月11日
 */
@Controller(value="jhnldfbController")
@RequestMapping("jhnldfb.spr")
public class JhnldfbController {
	
	@Autowired
	private JhnldfbService jhnldfbService;
	
	/** 
	 * @Title: toAgePeriodMarriage 
	 * @Description: 跳转到各年龄段婚姻状况
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAgePeriodMarriage")
	public String toAgePeriodMarriage(){
		return "statisticalform/agePeriodMarriage";
	}
	
	/** 
	 * @Title: queryYearMaritalStatus 
	 * @Description: 查询各年龄段婚姻分布
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryAgePeriodMarriage")
	public String queryAgePeriodMarriage(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.jhnldfbService.queryAgePeriodMarriage(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
	
}
