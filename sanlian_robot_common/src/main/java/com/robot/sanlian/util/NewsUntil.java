package com.robot.sanlian.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import net.sf.json.JSONObject;


public class NewsUntil {
	
	private static PropertiesLoader loader = new PropertiesLoader("resource/jdbc.properties");
	
	//发送短信
	public static StringBuffer sendNews(String mobile,String yzm){
		String newsUrl=loader.getProperty("newsUrl");
		String appkey=loader.getProperty("appkey");
		
		
		 HttpURLConnection con = null;  
	     OutputStreamWriter osw = null;  
	     BufferedReader br = null;  
	     StringBuffer resultBuffer = null;  
	        try{
	    		String content="【打豆豆教育科技】验证码为："+yzm+"，有效时间为1分钟！";
	    		StringBuffer urlParams=new StringBuffer();
	    		urlParams.append("mobile=")
	    		.append(mobile)
	    		.append("&content=")
	    		.append(content)
	    		.append("&appkey=")
	    		.append(appkey);
	    		 URL url = new URL(newsUrl.toString());  
	             con = (HttpURLConnection) url.openConnection();  
	             con.setRequestMethod("POST");  
	             con.setDoOutput(true);  
	             con.setDoInput(true);  
	             con.setUseCaches(false);  
	             System.out.println("str:"+urlParams.substring(0, urlParams.length() - 1));
	             con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
	             if (urlParams != null && urlParams.length() > 0) {  
	            	 osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");  
	                 // 发送请求参数  
	                 osw.write(urlParams.toString());  
	                 // flush输出流的缓冲  
	                 osw.flush();  
	             } 
	             
	             resultBuffer = new StringBuffer();  
	             int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));  
	             if (contentLength > 0) {  
	                 br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));  
	                 String temp;  
	                 while ((temp = br.readLine()) != null) {  
	                     resultBuffer.append(temp);  
	                 }  
	             }
	             System.out.println("resultBuffer:"+resultBuffer);
	        }catch(Exception e){
	        	
	        	e.printStackTrace();
	        }finally {  
	            if (osw != null) {  
	                try {  
	                    osw.close();  
	                } catch (IOException e) {  
	                    osw = null;  
	                    throw new RuntimeException(e);  
	                }  
	            }  
	            if (br != null) {  
	                try {  
	                    br.close();  
	                } catch (IOException e) {  
	                    br = null;  
	                    throw new RuntimeException(e);  
	                }  
	            }  
	        }  
		return resultBuffer;
	}
	
	public static void main(String[] args){
			StringBuffer sbuffer=NewsUntil.sendNews("18725518633","789300");
			JSONObject jsonObject = JSONObject.fromObject(sbuffer.toString());
			String code=(String) jsonObject.get("code");
			System.out.println("code:"+code);
	}

}
