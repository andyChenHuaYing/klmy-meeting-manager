package com.util.mail;

import java.util.Properties;

import org.springframework.mail.MailSender;

import com.sys.SystemConfigContext;

public class MailSenderInfo {
	// 发送邮件的服务器的IP和端口    
    private static String mailServerHost;    
    private static String mailServerPort = "25";    
    // 邮件发送者的地址    
    private static String fromAddress;    
    // 邮件接收者的地址    
    private String toAddress;    
    // 登陆邮件发送服务器的用户名和密码    
    private static String userName;    
    private static String password;    
    // 是否需要身份验证    
    private static boolean validate = false;    
    // 邮件主题    
    private String subject;    
    // 邮件的文本内容    
    private String content;    
    // 邮件附件的文件名    
    private String[] attachFileNames;
    
    static{
    	mailServerHost = SystemConfigContext.getInstance().getValue("mailServerHost");
    	mailServerPort = SystemConfigContext.getInstance().getValue("mailServerPort");
    	validate = Boolean.valueOf(SystemConfigContext.getInstance().getValue("validate"));
    	userName = SystemConfigContext.getInstance().getValue("userName");
    	password = SystemConfigContext.getInstance().getValue("password");
    	fromAddress = SystemConfigContext.getInstance().getValue("fromAddress");
    }
    
    /**   
      * 获得邮件会话属性   
      */    
    public Properties getProperties(){    
      Properties p = new Properties();    
      p.put("mail.smtp.host", mailServerHost);    
      p.put("mail.smtp.port", mailServerPort);    
      p.put("mail.smtp.auth", validate ? "true" : "false");    
      return p;    
    }    
    public String getMailServerHost() {    
      return mailServerHost;    
    }    
    public String getMailServerPort() {    
      return mailServerPort;    
    }   
    public boolean isValidate() {    
      return validate;    
    }   
    public String[] getAttachFileNames() {    
      return attachFileNames;    
    }   
    public void setAttachFileNames(String[] fileNames) {    
      this.attachFileNames = fileNames;    
    }   
    public String getFromAddress() {    
      return fromAddress;    
    }    
    public String getPassword() {    
      return password;    
    }   
    public String getToAddress() {    
      return toAddress;    
    }    
    public void setToAddress(String toAddress) {    
      this.toAddress = toAddress;    
    }    
    public String getUserName() {    
      return userName;    
    }   
    public String getSubject() {    
      return subject;    
    }   
    public void setSubject(String subject) {    
      this.subject = subject;    
    }   
    public String getContent() {    
      return content;    
    }   
    public void setContent(String textContent) {    
      this.content = textContent;    
    }  
}
