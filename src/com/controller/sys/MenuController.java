package com.controller.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.sys.MenuService;

/**
 * 
 * @className: MenuController 
 * @author: gu.xiaogang
 * @description: 菜单控制器
 * @date: 2014年9月10日
 */
@Controller
@RequestMapping("/menu.spr")
public class MenuController {
	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;
	
	private final static Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	/** 
	 * @Title: getMenuInfo 
	 * @Description: 获取菜单信息
	 * @param 
	 * @return String
	 * @throws 
	*/
	@RequestMapping(params=("method=getMenuInfo"))
	public String getMenuInfo(HttpServletRequest req,HttpServletResponse response,String topMenu){
	
		PrintWriter pw = null;
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		try {
			List<Map<String,Object>> menuList = this.menuService.findMenuByUser(topMenu);
			pw = response.getWriter();
			pw.println(JSONArray.fromObject(menuList));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if(null!=pw){
				pw.close();
			}
		}
		return null;
	}
}
