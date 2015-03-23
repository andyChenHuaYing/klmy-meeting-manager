package com.controller.statisticalform;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.statisticalform.RkblService;
import com.util.ResponseUtil;


/**
 * 
 * @className: RkblController 
 * @author: gu.xiaogang
 * @description: 人口比例控制器
 * @date: 2014年9月11日
 */
@Controller(value="rkblController")
@RequestMapping("rkbl.spr")
public class RkblController {
	
	@Autowired
	private RkblService rkblService;
	
	/** 
	 * @Title: toGaPersonAgeRatio 
	 * @Description: 跳转到人口比例查询页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toGaPersonAgeRatio")
	public String toGaPersonAgeRatio(){
		return "statisticalform/gaPersonAgeRatio";
	}
	
	/** 
	 * @Title: toAreaHhRatio 
	 * @Description: 跳转到地区户籍类型比例页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toAreaHhRatio")
	public String toAreaHhRatio(){
		return "statisticalform/areaHhRatio";
	}
	
	/** 
	 * @Title: toHhRatio 
	 * @Description: 跳转到地区户籍比例页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toHhRatio")
	public String toHhRatio(){
		return "statisticalform/hhRatio";
	}
	
	/** 
	 * @Title: toSexYearNum 
	 * @Description: 跳转到每年男女人数页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toSexYearNum")
	public String toSexYearNum(){
		return "statisticalform/sexYearNum";
	}
	/**
	 * @throws SQLException  
	 * @Title: queryAreaAgeRatio 
	 * @Description: 地区人口比例查询
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryAreaAgeRatio")
	public String queryAreaAgeRatio(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.rkblService.queryAreaAgeRatio(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	/** 
	 * @Title: queryAreaHhRatio 
	 * @Description: 地区户籍类型比例查询
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryAreaHhRatio")
	public String queryAreaHhRatio(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.rkblService.queryAreaHhRatio(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	/** 
	 * @Title: queryHhRatio 
	 * @Description: 户籍类型比例查询
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=queryHhRatio")
	public String queryHhRatio(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.rkblService.queryHhRatio(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
	/** 
	 * @Title: querySexYearNum 
	 * @Description: 查询男女每年人数
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=querySexYearNum")
	public String querySexYearNum(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.rkblService.querySexYearNum(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	
}
