package com.controller.statisticalform;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.statisticalform.YlfndqfbService;
import com.util.ResponseUtil;


/**
 * 
 * @className: YlfndqfbController 
 * @author: gu.xiaogang
 * @description: 婚姻状况控制器
 * @date: 2014年9月11日
 */
@Controller(value="ylfndqfbController")
@RequestMapping("ylfndqfb.spr")
public class YlfndqfbController {
	
	@Autowired
	private YlfndqfbService ylfndqfbService;
	
	/** 
	 * @Title: toAreaMaritalStatus 
	 * @Description: 跳转到地区婚姻状况
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAreaFWomanDistribution")
	public String toAreaFWomanDistribution(){
		return "statisticalform/areaFWomanDistribution";
	}	
	
	/** 
	 * @Title: queryAreaFWomanDistribution 
	 * @Description: 查询各地区育龄妇女状况
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryAreaFWomanDistribution")
	public String queryAreaFWomanDistribution(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.ylfndqfbService.queryAreaFWomanDistribution(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
	
}
