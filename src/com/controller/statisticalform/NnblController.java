package com.controller.statisticalform;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.statisticalform.NnblService;
import com.util.ResponseUtil;


/**
 * 
 * @className: NnblController 
 * @author: gu.xiaogang
 * @description: 男女比例控制器
 * @date: 2014年9月11日
 */
@Controller(value="nnblController")
@RequestMapping("nnbl.spr")
public class NnblController {
	
	@Autowired
	private NnblService nnblService;
	
	/** 
	 * @Title: toSexAreaNumber 
	 * @Description: 跳转到男女比例页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toSexAreaNumber")
	public String toSexAreaNumber(){
		return "statisticalform/sexAreaNumber";
	}
	/** 
	 * @Title: toSexAgePeriodNumber 
	 * @Description: 跳转到各年龄段男女人口数页面
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=toSexAgePeriodNumber")
	public String toSexAgePeriodNumber(){
		return "statisticalform/sexAgePeriodNumber";
	}
	
	/** 
	 * @Title: queryAreaHhRatio 
	 * @Description: 各地区男女数
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=querySexAreaNumber")
	public String querySexAreaNumber(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.nnblService.querySexAreaNumber(), ResponseUtil.TRANSFER_JSONARRAY);
		return null;
	}
	/** 
	 * @Title: querySexAgePeriodNumber 
	 * @Description: 各年龄段男女数
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params="method=querySexAgePeriodNumber")
	public String querySexAgePeriodNumber(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);
		ResponseUtil.printWrite(response, this.nnblService.querySexAgePeriodNumber(), ResponseUtil.TRANSFER_JSONOBJECT);
		return null;
	}
	
}
