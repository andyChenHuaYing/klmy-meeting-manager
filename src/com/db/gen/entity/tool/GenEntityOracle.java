package com.db.gen.entity.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class GenEntityOracle {
	private String packageOutPath = "com.vo";//指定实体生成所在包的路径                                                                                                         
    private String authorName = "system";//作者名字                                                                                                                             
//    private String tablename = "TB_APP_ARCHIVES";//表名    TB_APP_ADMISSION         TB_APP_AUXILIARY_EXAMINATION
    
//    private String tablename = "TB_APP_ADMISSION";//表名    TB_APP_ADMISSION
//    private String tablename = "TB_APP_AUXILIARY_EXAMINATION";//表名
//    private String tablename = "TB_APP_DISCHARGE";//表名    TB_APP_ADMISSION
    
    private String tablename = "tb_base_rolegrp";
    
    private String[] colnames; // 列名数组                                                                                                                                        
    private String[] colTypes; //列名类型数组                                                                                                                                     
    private int[] colSizes; //列名大小数组                                                                                                                                        
    private boolean f_util = false; // 是否需要导入包java.util.*                                                                                                                  
    private boolean f_sql = false; // 是否需要导入包java.sql.*                                                                                                                    
                                                                                                                                                                                  
    //数据库连接                                                                                                                                                                  
     private static final String URL ="jdbc:oracle:thin:@10.45.35.67:1521:orcl67";                                                                                                    
     private static final String NAME = "etl_pbid";                                                                                                                                  
     private static final String PASS = "etl_pbid";                                                                                                                                  
     private static final String DRIVER ="oracle.jdbc.driver.OracleDriver";                                                                                                       
                                                                                                                                                                                  
    /*                                                                                                                                                                            
     * 构造函数                                                                                                                                                                   
     */                                                                                                                                                                           
    public GenEntityOracle(){                                                                                                                                                     
        //创建连接                                                                                                                                                                
        Connection con;                                                                                                                                                           
        //查要生成实体类的表                                                                                                                                                      
        String sql = "select * from " + tablename;                                                                                                                                
        Statement pStemt = null;                                                                                                                                                  
        try {                                                                                                                                                                     
            try {                                                                                                                                                                 
                Class.forName(DRIVER);                                                                                                                                            
            } catch (ClassNotFoundException e1) {                                                                                                                                 
                // TODO Auto-generated catch block                                                                                                                                
                e1.printStackTrace();                                                                                                                                             
            }                                                                                                                                                                     
            con = DriverManager.getConnection(URL,NAME,PASS);                                                                                                                     
            pStemt = (Statement) con.createStatement();                                                                                                                           
            ResultSet rs = pStemt.executeQuery(sql);                                                                                                                              
            ResultSetMetaData rsmd = rs.getMetaData();                                                                                                                            
            int size = rsmd.getColumnCount();   //统计列                                                                                                                          
            colnames = new String[size];                                                                                                                                          
            colTypes = new String[size];                                                                                                                                          
            colSizes = new int[size];                                                                                                                                             
            for (int i = 0; i < size; i++) {                                                                                                                                      
                colnames[i] = rsmd.getColumnName(i + 1);                                                                                                                          
                colTypes[i] = rsmd.getColumnTypeName(i + 1);                                                                                                                      
                                                                                                                                                                                  
                if(colTypes[i].equalsIgnoreCase("date") || colTypes[i].equalsIgnoreCase("timestamp")){                                                                            
                    f_util = true;                                                                                                                                                
                }                                                                                                                                                                 
                if(colTypes[i].equalsIgnoreCase("blob") || colTypes[i].equalsIgnoreCase("char")){                                                                                 
                    f_sql = true;                                                                                                                                                 
                }                                                                                                                                                                 
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);                                                                                                                   
            }                                                                                                                                                                     
                                                                                                                                                                                  
            String content = parse(colnames,colTypes,colSizes);                                                                                                                   
                                                                                                                                                                                  
            try {                                                                                                                                                                 
                File directory = new File("");                                                                                                                                    
                //System.out.println("绝对路径："+directory.getAbsolutePath());                                                                                                   
                //System.out.println("相对路径："+directory.getCanonicalPath());                                                                                                  
                String path=this.getClass().getResource("").getPath();                                                                                                            
                                                                                                                                                                                  
                System.out.println(path);                                                                                                                                         
                System.out.println("src/?/"+path.substring(path.lastIndexOf("/com/", path.length())) );                                                                           
//              String outputPath = directory.getAbsolutePath()+ "/src/"+path.substring(path.lastIndexOf("/com/", path.length()), path.length()) + initcap(tablename) + ".java";  
                String outputPath = directory.getAbsolutePath()+ "/src/"+this.packageOutPath.replace(".", "/")+"/"+initcap(GenEntityOracle.transferStrTitle(tablename)) + ".java";                                  
                FileWriter fw = new FileWriter(outputPath);                                                                                                                       
                PrintWriter pw = new PrintWriter(fw);                                                                                                                             
                pw.println(content);                                                                                                                                              
                pw.flush();                                                                                                                                                       
                pw.close();                                                                                                                                                       
            } catch (IOException e) {                                                                                                                                             
                e.printStackTrace();                                                                                                                                              
            }                                                                                                                                                                     
                                                                                                                                                                                  
        } catch (SQLException e) {                                                                                                                                                
            e.printStackTrace();                                                                                                                                                  
        } finally{                                                                                                                                                                
//          try {                                                                                                                                                                 
//              con.close();                                                                                                                                                      
//          } catch (SQLException e) {                                                                                                                                            
//              // TODO Auto-generated catch block                                                                                                                                
//              e.printStackTrace();    
//          }                                                                                                                                                                    
        }                                                                                                                                                                        
    }                                                                                                                                                                            
                                                                                                                                                                                 
    /**                                                                                                                                                                          
     * 功能：生成实体类主体代码                                                                                                                                                  
     * @param colnames                                                                                                                                                           
     * @param colTypes                                                                                                                                                           
     * @param colSizes                                                                                                                                                           
     * @return                                                                                                                                                                   
     */                                                                                                                                                                          
    private String parse(String[] colnames, String[] colTypes, int[] colSizes) {                                                                                                 
        StringBuffer sb = new StringBuffer();                                                                                                                                    
                                                                                                                                                                                 
        //判断是否导入工具包                                                       
        sb.append("package " + this.packageOutPath + ";\r\n");   
        if(f_util){                                                                                                                                                              
            sb.append("import java.util.Date;\r\n");                                                                                                                             
        }                                                                                                                                                                        
        if(f_sql){                                                                                                                                                               
            sb.append("import java.sql.*;\r\n");                                                                                                                                 
        }                                                                                                                                                                        
                                                                                                                        
        sb.append("\r\n");                                                                                                                                                       
        //注释部分                                                                                                                                                               
        sb.append("   /**\r\n");                                                                                                                                                 
        sb.append("    * "+GenEntityOracle.transferStrTitle(tablename)+" 实体类\r\n");                                                                                                                             
        sb.append("    * "+new Date()+" "+this.authorName+"\r\n");                                                                                                               
        sb.append("    */ \r\n");                                                                                                                                                
        //实体部分                                                                                                                                                               
        sb.append("\r\n\r\npublic class " + initcap(GenEntityOracle.transferStrTitle(tablename)) + "{\r\n");                                                                                                       
        processAllAttrs(sb);//属性                                                                                                                                               
        processAllMethod(sb);//get set方法                                                                                                                                       
        sb.append("}\r\n");                                                                                                                                                      
                                                                                                                                                                                 
        //System.out.println(sb.toString());                                                                                                                                     
        return sb.toString();                                                                                                                                                    
    }                                                                                                                                                                            
                                                                                                                                                                                 
    /**                                                                                                                                                                          
     * 功能：生成所有属性                                                                                                                                                        
     * @param sb                                                                                                                                                                 
     */                                                                                                                                                                          
    private void processAllAttrs(StringBuffer sb) {                                                                                                                              
                                                                                                                                                                                 
        for (int i = 0; i < colnames.length; i++) {                                                                                                                              
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + GenEntityOracle.transferStr(colnames[i]) + ";\r\n");                                                                               
        }                                                                                                                                                                        
                                                                                                                                                                                 
    }                                                                                                                                                                            
                                                                                                                                                                                 
    /**                                                                                                                                                                          
     * 功能：生成所有方法                                                                                                                                                        
     * @param sb                                                                                                                                                                 
     */                                                                                                                                                                          
    private void processAllMethod(StringBuffer sb) {                                                                                                                             
                                                                                                                                                                                 
        for (int i = 0; i < colnames.length; i++) {                                                                                                                              
            sb.append("\tpublic void set" + initcap(GenEntityOracle.transferStr(colnames[i])) + "(" + sqlType2JavaType(colTypes[i]) + " " +                                                                   
            		GenEntityOracle.transferStr(colnames[i]) + "){\r\n");                                                                                                                                     
            sb.append("\tthis." + GenEntityOracle.transferStr(colnames[i]) + "=" + GenEntityOracle.transferStr(colnames[i]) + ";\r\n");                                                                                                    
            sb.append("\t}\r\n");                                                                                                                                                
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(GenEntityOracle.transferStr(colnames[i])) + "(){\r\n");                                                                  
            sb.append("\t\treturn " + GenEntityOracle.transferStr(colnames[i]) + ";\r\n");                                                                                                                    
            sb.append("\t}\r\n");                                                                                                                                                
        }                                                                                                                                                                        
                                                                                                                                                                                 
    }                                                                                                                                                                            
                                                                                                                                                                                 
    /**                                                                                                                                                                          
     * 功能：将输入字符串的首字母改成大写                                                                                                                                        
     * @param str                                                                                                                                                                
     * @return                                                                                                                                                                   
     */                                                                                                                                                                          
    private String initcap(String str) {                                                                                                                                         
                                                                                                                                                                                 
        char[] ch = str.toCharArray();                                                                                                                                           
        if(ch[0] >= 'a' && ch[0] <= 'z'){                                                                                                                                        
            ch[0] = (char)(ch[0] - 32);                                                                                                                                          
        }                                                                                                                                                                        
                                                                                                                                                                                 
        return new String(ch);                                                                                                                                                   
    }                                                                                                                                                                            
                                                                                                                                                                                 
    /**                                                                                                                                                                          
     * 功能：获得列的数据类型                                                                                                                                                    
     * @param sqlType                                                                                                                                                            
     * @return                                                                                                                                                                   
     */                                                                                                                                                                          
    private String sqlType2JavaType(String sqlType) {                                                                                                                            
                                                                                                                                                                                 
        if(sqlType.equalsIgnoreCase("binary_double")){                                                                                                                           
            return "double";                                                                                                                                                     
        }else if(sqlType.equalsIgnoreCase("binary_float")){                                                                                                                      
            return "float";                                                                                                                                                      
        }else if(sqlType.equalsIgnoreCase("blob")){                                                                                                                              
            return "byte[]";                                                                                                                                                     
        }else if(sqlType.equalsIgnoreCase("blob")){                                                                                                                              
            return "byte[]";                                                                                                                                                     
        }else if(sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar2")                                                                                       
                || sqlType.equalsIgnoreCase("varchar2")){                                                                                                                        
            return "String";                                                                                                                                                     
        }else if(sqlType.equalsIgnoreCase("date") || sqlType.equalsIgnoreCase("timestamp")                                                                                       
                 || sqlType.equalsIgnoreCase("timestamp with local time zone")                                                                                                   
                 || sqlType.equalsIgnoreCase("timestamp with time zone")){                                                                                                       
            return "Date";                                                                                                                                                       
        }else if(sqlType.equalsIgnoreCase("number")){                                                                                                                            
            return "Long";                                                                                                                                                       
        }                                                                                                                                                                        
                                                                                                                                                                                 
        return "String";                                                                                                                                                         
    }                                                                                                                                                                            
                                                                                                                                                                                 
    /**                                                                                                                                                                          
     * 出口                                                                                                                                                                      
     * TODO                                                                                                                                                                      
     * @param args                                                                                                                                                               
     */                                                                                                                                                                          
    public static void main(String[] args) {                                                                                                                                     
                                                                                                                                                                                 
        new GenEntityOracle();                                                                                                                                                   
//       System.out.println(GenEntityOracle.transferStr("TEST_abcd_ef"));                                                                                                                                                                         
    }     
    
    public static String transferStr(String str){
    	StringBuffer rtnStr = new StringBuffer("");
    	String[] strArr = str.split("_");
    	for(int i=0;i<strArr.length;i++){
    		if(i==0){
    			rtnStr.append(strArr[i].toLowerCase());
    		}else{
    			rtnStr.append(strArr[i].substring(0, 1).toUpperCase()+strArr[i].substring(1).toLowerCase());
    		}
    		
    	}
    	return rtnStr.toString();
    }
    
    public static String transferStrTitle(String str){
    	StringBuffer rtnStr = new StringBuffer("");
    	String[] strArr = str.split("_");
    	for(int i=0;i<strArr.length;i++){
    		rtnStr.append(strArr[i].substring(0, 1).toUpperCase()+strArr[i].substring(1).toLowerCase());
    	}
    	return rtnStr.toString();
    }
}
