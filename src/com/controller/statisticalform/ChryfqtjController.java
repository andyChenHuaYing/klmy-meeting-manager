package com.controller.statisticalform;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.statisticalform.ChryfqtjService;
import com.util.ResponseUtil;


/**
 * 
 * @className: ChryfqtjController 
 * @author: gu.xiaogang
 * @description: 参合人员分区统计分布控制器
 * @date: 2014年9月11日
 */
@Controller(value="chryfqtjController")
@RequestMapping("chryfqtj.spr")
public class ChryfqtjController {
	
	@Autowired
	private ChryfqtjService chryfqtjService;
	
	/** 
	 * @Title: toAreaCoMedicalInsurance 
	 * @Description: 跳转到参合人员分区统计页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAreaCoMedicalInsurance")
	public String toAreaCoMedicalInsurance(){
		return "statisticalform/areaCoMedicalInsurance";
	}
	
	/** 
	 * @Title: queryAreaCoMedicalInsurance 
	 * @Description: 查询各地区参合人员分布
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryAreaCoMedicalInsurance")
	public String queryAreaCoMedicalInsurance(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.chryfqtjService.queryAreaCoMedicalInsurance(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
	
}
