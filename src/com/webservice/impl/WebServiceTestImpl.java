package com.webservice.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.webservice.IWebServiceTest;

@Service(value = "webServiceTest")
@WebService(endpointInterface = "com.webservice.IWebServiceTest")
public class WebServiceTestImpl implements IWebServiceTest{
	@Override
	public String test1(String str) {
		System.out.println("***************str1**************"+str);
		
		String rtnStr = "default";
		if("1".equals(str)){
			rtnStr = "str1";
		}else if("2".equals(str)){
			rtnStr = "str2";
		}
		return rtnStr;
	}

	@Override
	public String test2(String str) {
		System.out.println("***************str2**************"+str);
		// TODO Auto-generated method stub
		String rtnStr = "我是default";
		if("str1".equals(str)){
			rtnStr = "我是str1";
		}else if("str2".equals(str) ){
			rtnStr = "我是str2";
		}
		return rtnStr;
	}
}
