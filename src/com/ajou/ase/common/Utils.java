package com.ajou.ase.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;


public class Utils {
	
	public static String yesterDay(String date){
	long chStart = 0;  
	DateFormat df = new SimpleDateFormat("yyyyMMdd");

		if(date != ""){
		date = date.replaceAll("-","");       
  			try {
			    chStart = df.parse(date).getTime();  
			    chStart -= 86400000;    
			                              
			    Date aa = new Date(chStart); 
			    date = df.format(aa);       
                    
  			} catch (ParseException e) {
	  			e.printStackTrace();
  			}
		}
		return date;  
	}
	
	@SuppressWarnings("unchecked")
	public static RequestParameter extractRequestParameters(HttpServletRequest request){
		RequestParameter parameters = new RequestParameter();
		Enumeration enumParameters = request.getParameterNames();
		
		while (enumParameters.hasMoreElements()){
			String parameterName = (String)enumParameters.nextElement();
			String value = (String)request.getParameter(parameterName);
			parameters.put(parameterName, value);
		}
		
		String queryString = request.getQueryString();
		
		if(queryString != null){
			StringTokenizer token1 = new StringTokenizer(queryString, "&");
			while(token1.hasMoreTokens()){
				String param = token1.nextToken();
				StringTokenizer token2 = new StringTokenizer(param, "=");
				String key = null;
				String value = null;
				int i = 0;
				while(token2.hasMoreTokens()){
					switch(i){
					case 0:
						key = token2.nextToken();
						break;
					case 1:
						value = token2.nextToken();
						break;					
					}
					i++;
				}
				
				if(value != null){
					try{
						value = URLDecoder.decode(value,"UTF-8");
		//				System.out.println("test = " +test);
		//				value = new String(value.getBytes("8859_1"), "UTF-8");						
					}
					catch(UnsupportedEncodingException e){
						e.printStackTrace();
					}
				}
				
				parameters.put(key, value);
			}
			
		}
		return parameters;
	}	
}




