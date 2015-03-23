package com.controller.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.common.BufferService;
import com.util.ResponseUtil;


/**
 * 
 * @className: BufferController 
 * @author: gu.xiaogang
 * @description: 缓存控制器
 * @date: 2014年9月10日
 */
@Controller
@RequestMapping("buffer.spr")
public class BufferController {
	
	@Autowired
	@Qualifier("bufferService")
	private BufferService bufferService;
	
	/*
	 * 刷新缓存
	 */
	@RequestMapping(params="method=refreshBuffer")
	public String refreshBuffer(HttpServletResponse response) throws SQLException{
		ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_HTML, ResponseUtil.CHARENCODING_UTF8);
		PrintWriter pw = null;
		try {
			pw=response.getWriter();
			this.bufferService.refreshBuffer();
			pw.println("刷新完毕！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
