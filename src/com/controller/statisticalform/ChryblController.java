package com.controller.statisticalform;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.statisticalform.ChryblService;
import com.util.ResponseUtil;


/**
 * 
 * @className: ChryblController 
 * @author: gu.xiaogang
 * @description: 参合人员分区统计分布控制器
 * @date: 2014年9月11日
 */
@Controller(value="chryblController")
@RequestMapping("chrybl.spr")
public class ChryblController {
	
	@Autowired
	private ChryblService chryblService;
	
	/** 
	 * @Title: toApCoMedicalInsurance 
	 * @Description: 跳转到参合人员分区统计页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toApCoMedicalInsurance")
	public String toApCoMedicalInsurance(){
		return "statisticalform/apCoMedicalInsurance";
	}
	
	/** 
	 * @Title: toSexCoMedicalInsurance 
	 * @Description: 跳转到参合人员分区统计页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toSexCoMedicalInsurance")
	public String toSexCoMedicalInsurance(){
		return "statisticalform/apSexMedicalInsurance";
	}
	
	/** 
	 * @Title: queryApCoMedicalInsurance 
	 * @Description: 查询加新农合人员年龄段分布
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryApCoMedicalInsurance")
	public String queryApCoMedicalInsurance(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.chryblService.queryApCoMedicalInsurance(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	/** 
	 * @Title: querySexCoMedicalInsurance 
	 * @Description: 查询参加新农合人员男女分布
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=querySexCoMedicalInsurance")
	public String querySexCoMedicalInsurance(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.chryblService.querySexCoMedicalInsurance(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
}
