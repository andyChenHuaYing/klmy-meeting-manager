package com.controller.common;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.misc.BASE64Decoder;

@Controller(value="chartExportController")
@RequestMapping("/chartExport.spr")
public class ChartExportController {
	
	@RequestMapping(params="method=export")
	public String export(HttpServletResponse response,String base64Str,String title){
		if(base64Str==null || "".equals(base64Str)){
			return null;
		}else{
			String[] strArr = base64Str.split(",");
			//base64Str = base64Str.split(",")[1];
			if(strArr.length>1){
				base64Str = base64Str.split(",")[1];
			}
			try {
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] b = decoder.decodeBuffer(base64Str);
				for(int i=0;i<b.length;++i)  
	            {  
	                if(b[i]<0)  
	                {//调整异常数据  
	                    b[i]+=256;  
	                }  
	            }
				
				Date date = new Date();
				String tmpStr = "chart"+DateFormat.getDateInstance().format(date);
				if(title!=null && !"".equals(title)){
					tmpStr = new String( title.getBytes("gb2312"), "ISO8859-1" )+DateFormat.getDateInstance().format(date);
				}
				
				response.setHeader("Content-Disposition", "attachment; filename=\""+tmpStr+".png" + "\"");
				//response.setContentType("image/jpeg");
				OutputStream os = response.getOutputStream();
				os.write(b);  
	            os.flush();  
	            os.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
