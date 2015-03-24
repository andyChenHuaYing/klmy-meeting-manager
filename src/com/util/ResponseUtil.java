package com.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @className: ResponseUtil 
 * @author: gu.xiaogang
 * @description: Response帮助类
 * @date: 2014年9月10日
 */
public class ResponseUtil {
		
		private ResponseUtil(){}
		
		public static final String CONTENTTYPE_JSON = "application/json;charset=utf-8";
		public static final String CONTENTTYPE_HTML = "text/html;charset=UTF-8";
		public static final String CONTENTTYPE_JS = "text/javascript";
		public static final String CONTENTTYPE_XML = "application/xml";	
		public static final String CHARENCODING_UTF8 = "UTF-8";
		public static final String CHARENCODING_GBK = "GBK";
		public static final int TRANSFER_NONE = 0;
		public static final int TRANSFER_JSONOBJECT = 1;
		public static final int TRANSFER_JSONARRAY = 2;
		
	    /**
	     * @param contentType
	     * @param charEncoding
	     * @return HttpServletResponse
	     */
	    public static HttpServletResponse getResp(String contentType,String charEncoding){
	    	ServletWebRequest servletContainer = (ServletWebRequest)RequestContextHolder.getRequestAttributes();
	    	HttpServletResponse resp = servletContainer.getResponse();
	    	if( null != contentType && !"".equals(contentType)){
	    		resp.setContentType(contentType);
	    	}
	    	if( null != charEncoding && !"".equals(charEncoding)){
	    		resp.setCharacterEncoding(charEncoding);
	    	}
			return resp;
		}
	    
	    /**
	     * @param contentType
	     * @param charEncoding
	     * @return HttpServletResponse
	     */
	    public static HttpServletResponse formatResp(HttpServletResponse resp,String contentType,String charEncoding){
	    	if( null != contentType && !"".equals(contentType)){
	    		resp.setContentType(contentType);
	    	}
	    	if( null != charEncoding && !"".equals(charEncoding)){
	    		resp.setCharacterEncoding(charEncoding);
	    	}
			return resp;
		}
	    
	    /**Description
	     * 	将字符串内容写入到http响应
	     * @param response http响应
	     * @param content 响应内容
	     * */
	    public static void send(HttpServletResponse response,String content){
	    	PrintWriter pw=null;
	    	try {
				pw=response.getWriter();
				pw.write(content);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("response error!");
			}finally{
				if(pw!=null){
					pw.flush();
					pw.close();
					pw=null;
				}
			}
	    }
	    public static Map<String,Object> transferAttrToMap(HttpServletRequest request,String prop) throws UnsupportedEncodingException{
	    	String attrStr = new String (request.getParameter(prop).getBytes("iso-8859-1"),"utf8");
			JSONObject obj = JSONObject.fromObject(attrStr);
			Map<String,Object> map = (Map<String, Object>) JSONObject.toBean(obj, HashMap.class);
			return map;
	    }
	    
	    public static String docToStr(Document doc) throws UnsupportedEncodingException{
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			StreamResult result = new StreamResult(output);
			String str = "";
			TransformerFactory trans = TransformerFactory.newInstance();
			
			try {
				Transformer transformer = trans.newTransformer();
				transformer.setOutputProperty(OutputKeys.METHOD,"xml");
				transformer.setOutputProperty(OutputKeys.ENCODING,"GB2312");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.transform(new DOMSource(doc), result);
				str = output.toString("GB2312");
				try {
					output.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e){
				e.printStackTrace();
			}
			return str;
		}
	    
	    /** 
	     * @Title: printWrite
         * @Description: 将返回内容写到PrintWriter中
         * @param response
         * @param transferFlag 根据transferFlag判断输出的是 JSON对象、JSON数组、字符串
         */
        public static void printWrite(HttpServletResponse response,Object obj,int transferFlag){
	    	ResponseUtil.formatResp(response,ResponseUtil.CONTENTTYPE_JSON, ResponseUtil.CHARENCODING_UTF8);	    	
	    	PrintWriter pw = null;
	    	Object rtnObj = obj;
	    	try {
				pw = response.getWriter();
				switch (transferFlag) {
				case ResponseUtil.TRANSFER_JSONOBJECT:
					rtnObj = JSONObject.fromObject(rtnObj);
					break;
				case ResponseUtil.TRANSFER_JSONARRAY:
					rtnObj = JSONArray.fromObject(rtnObj);
				default:
					break;
				}
				pw.println(rtnObj);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(null!=pw){
					pw.close();
				}
			}
	    }
}
