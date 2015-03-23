package com.controller.statisticalform;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.statisticalform.CbryblService;
import com.service.statisticalform.NnblService;
import com.util.ResponseUtil;


/**
 * 
 * @className: CbryblController 
 * @author: gu.xiaogang
 * @description: 参保比例控制器
 * @date: 2014年9月11日
 */
@Controller(value="cbryblController")
@RequestMapping("cbrybl.spr")
public class CbryblController {
	
	@Autowired
	private CbryblService cbryblService;
	
	/** 
	 * @Title: toAreaInsurance 
	 * @Description: 跳转到地区参保情况页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAreaInsurance")
	public String toAreaInsurance(){
		return "statisticalform/areaInsurance";
	}
	/** 
	 * @Title: toAgePeriodInsurance 
	 * @Description: 跳转到各年龄段参保页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAgePeriodInsurance")
	public String toAgePeriodInsurance (){
		return "statisticalform/agePeriodInsurance";
	}
	
	/** 
	 * @Title: toSexInsurance 
	 * @Description: 跳转到各性别参保页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toSexInsurance")
	public String toSexInsurance (){
		return "statisticalform/sexInsurance";
	}
	
	/** 
	 * @Title: queryAreaInsurance 
	 * @Description: 查询地区参保情况
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryAreaInsurance")
	public String queryAreaInsurance(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.cbryblService.queryAreaInsurance(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
	/** 
	 * @Title: queryAgePeriodInsurance 
	 * @Description: 查询各年龄段参保情况
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryAgePeriodInsurance")
	public String queryAgePeriodInsurance(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.cbryblService.queryAgePeriodInsurance(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
	/** 
	 * @Title: querySexInsurance 
	 * @Description: 查询男女参保情况
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=querySexInsurance")
	public String querySexInsurance(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.cbryblService.querySexInsurance(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
}
