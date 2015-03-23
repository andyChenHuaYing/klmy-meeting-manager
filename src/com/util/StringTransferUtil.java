package com.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringTransferUtil {
	/*
	 * 处理字符串
	 * @@ 输入框
	 * 
	 */
	public static String transferToInput(String sourceStr,String inputName){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		while(sourceStr.indexOf("@")>=0){
			int beginIndex = 0;
			int endIndex = sourceStr.indexOf("@");
			Map<String,String> map1 = new HashMap<String, String>();
			Map<String,String> map2 = new HashMap<String, String>();
			map1.put("str", sourceStr.substring(beginIndex,endIndex));
			map1.put("type", "string");
			if(beginIndex!=endIndex){//如果相同的话，说明都是0的情况
				list.add(map1);
			}
			beginIndex = endIndex;
			StringBuffer sb = new StringBuffer();
			sourceStr = sourceStr.substring(endIndex,sourceStr.length());
			beginIndex = 0;
			while(sourceStr.length()>0 && sourceStr.substring(beginIndex,beginIndex+1).equals("@")){
				sb.append(sourceStr.substring(beginIndex,beginIndex+1));
				sourceStr = sourceStr.substring(beginIndex+1,sourceStr.length());
			}
			map2.put("str", sb.toString());
			map2.put("type", "input");
			list.add(map2);
		}
		if(sourceStr!=null && sourceStr.length()>0){
			Map<String,String> map = new HashMap<String, String>();
			map.put("str", sourceStr);
			map.put("type", "string");
			list.add(map);
		}		
		StringBuffer sb1 = new StringBuffer();
		//加一个序号，便于前台组装值
		int idx = 0;
		for(int i=0;i<list.size(); i++){
			if(list.get(i).get("type").equals("input")){
				sb1.append("<input  type=\"text\" style=\"width:"+10*list.get(i).get("str").length()+"px\" name=\""+inputName+"\" idx=\""+idx+"\"></input>");
				idx++;
			}else{
				sb1.append(list.get(i).get("str"));
			}
			
		}
		return sb1.toString();
	}
	
	public static String transferToInputForUpdate(String sourceStr,String inputName,String val){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		String[] vals = val.split("###");
		while(sourceStr.indexOf("@")>=0){
			int beginIndex = 0;
			int endIndex = sourceStr.indexOf("@");
			Map<String,String> map1 = new HashMap<String, String>();
			Map<String,String> map2 = new HashMap<String, String>();
			map1.put("str", sourceStr.substring(beginIndex,endIndex));
			map1.put("type", "string");
			if(beginIndex!=endIndex){//如果相同的话，说明都是0的情况
				list.add(map1);
			}
			beginIndex = endIndex;
			StringBuffer sb = new StringBuffer();
			sourceStr = sourceStr.substring(endIndex,sourceStr.length());
			beginIndex = 0;
			while(sourceStr.length()>0 && sourceStr.substring(beginIndex,beginIndex+1).equals("@")){
				sb.append(sourceStr.substring(beginIndex,beginIndex+1));
				sourceStr = sourceStr.substring(beginIndex+1,sourceStr.length());
			}
			map2.put("str", sb.toString());
			map2.put("type", "input");
			list.add(map2);
		}
		if(sourceStr!=null && sourceStr.length()>0){
			Map<String,String> map = new HashMap<String, String>();
			map.put("str", sourceStr);
			map.put("type", "string");
			list.add(map);
		}		
		StringBuffer sb1 = new StringBuffer();
		//加一个序号，便于前台组装值
		int idx = 0;
		for(int i=0;i<list.size(); i++){
			if(list.get(i).get("type").equals("input")){
				if(idx<vals.length){
					sb1.append("<input  type=\"text\" style=\"width:"+10*list.get(i).get("str").length()+"px\" name=\""+inputName+"\" idx=\""+idx+"\" value=\""+vals[idx]+"\"></input>");
				}else{
					sb1.append("<input  type=\"text\" style=\"width:"+10*list.get(i).get("str").length()+"px\" name=\""+inputName+"\" idx=\""+idx+"\"></input>");	
				}
				
				idx++;
			}else{
				sb1.append(list.get(i).get("str"));
			}
			
		}
		return sb1.toString();
	}
	
	/*
	 * 处理字符串
	 * @@ 输入框
	 * 
	 */
	public static String transferToInput(String sourceStr,String inputName,String prevId){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		while(sourceStr.indexOf("@")>=0){
			int beginIndex = 0;
			int endIndex = sourceStr.indexOf("@");
			Map<String,String> map1 = new HashMap<String, String>();
			Map<String,String> map2 = new HashMap<String, String>();
			map1.put("str", sourceStr.substring(beginIndex,endIndex));
			map1.put("type", "string");
			if(beginIndex!=endIndex){//如果相同的话，说明都是0的情况
				list.add(map1);
			}
			beginIndex = endIndex;
			StringBuffer sb = new StringBuffer();
			sourceStr = sourceStr.substring(endIndex,sourceStr.length());
			beginIndex = 0;
			while(sourceStr.length()>0 && sourceStr.substring(beginIndex,beginIndex+1).equals("@")){
				sb.append(sourceStr.substring(beginIndex,beginIndex+1));
				sourceStr = sourceStr.substring(beginIndex+1,sourceStr.length());
			}
			map2.put("str", sb.toString());
			map2.put("type", "input");
			list.add(map2);
		}
		if(sourceStr!=null && sourceStr.length()>0){
			Map<String,String> map = new HashMap<String, String>();
			map.put("str", sourceStr);
			map.put("type", "string");
			list.add(map);
		}		
		StringBuffer sb1 = new StringBuffer();
		int idx = 0;
		for(int i=0;i<list.size(); i++){
			if(list.get(i).get("type").equals("input")){//prevId
				sb1.append("<input  type=\"text\" style=\"width:"+10*list.get(i).get("str").length()+"px\" name=\""+inputName+"\"");				
				if(prevId!=null && !"".equals(prevId)){
					sb1.append(" prevId = \""+prevId+"\"");
				}
				sb1.append(" idx=\""+idx+"\"></input>");
				idx++;
			}else{
				sb1.append(list.get(i).get("str"));
			}
			
		}
		return sb1.toString();
	}
	
	public static String transferToInput(String sourceStr,String inputName,String prevId,String val){
		String[] vals = val.split("###");
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		while(sourceStr.indexOf("@")>=0){
			int beginIndex = 0;
			int endIndex = sourceStr.indexOf("@");
			Map<String,String> map1 = new HashMap<String, String>();
			Map<String,String> map2 = new HashMap<String, String>();
			map1.put("str", sourceStr.substring(beginIndex,endIndex));
			map1.put("type", "string");
			if(beginIndex!=endIndex){//如果相同的话，说明都是0的情况
				list.add(map1);
			}
			beginIndex = endIndex;
			StringBuffer sb = new StringBuffer();
			sourceStr = sourceStr.substring(endIndex,sourceStr.length());
			beginIndex = 0;
			while(sourceStr.length()>0 && sourceStr.substring(beginIndex,beginIndex+1).equals("@")){
				sb.append(sourceStr.substring(beginIndex,beginIndex+1));
				sourceStr = sourceStr.substring(beginIndex+1,sourceStr.length());
			}
			map2.put("str", sb.toString());
			map2.put("type", "input");
			list.add(map2);
		}
		if(sourceStr!=null && sourceStr.length()>0){
			Map<String,String> map = new HashMap<String, String>();
			map.put("str", sourceStr);
			map.put("type", "string");
			list.add(map);
		}		
		StringBuffer sb1 = new StringBuffer();
		int idx = 0;
		for(int i=0;i<list.size(); i++){
			if(list.get(i).get("type").equals("input")){//prevId
				sb1.append("<input  type=\"text\" style=\"width:"+10*list.get(i).get("str").length()+"px\" name=\""+inputName+"\"");				
				if(prevId!=null && !"".equals(prevId)){
					sb1.append(" prevId = \""+prevId+"\"");
				}
				if(idx<vals.length){
					sb1.append(" idx=\""+idx+"\" value=\""+vals[idx]+"\"></input>");
				}else{
					sb1.append(" idx=\""+idx+"\"></input>");
				}
				idx++;
			}else{
				sb1.append(list.get(i).get("str"));
			}
			
		}
		return sb1.toString();
	}
	
	
	public static void main(String[] args) {
		System.out.println(StringTransferUtil.transferToInput("AA@@","test","aaa"));
//		String[] arr = "aa|||||bb".split("\\|\\|\\|\\|");
//		System.out.println(arr);
	}
}
