package com.albery.acs.util;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.albery.acs.model.Response;

/**
 * 
 * 网络操作工具类
 * 
 * @author Albery
 * @since 1.0
 */
public class NetworkUtil {
	
	/** 表示GET请求 */
	private final static int GET = 0x00000001;
	/** 表示POST请求 */
	private final static int POST = 0x00000002;
	
	/**
	 * 发送get请求
	 * @param url 		url连接
	 * @param params	传入后台的参数
	 */
	public static void doGet(String url, Map<String, String> params){
		String paramsStr = encodeData(params, GET);
		
		try{
			String fullUrl = url + "?" + paramsStr;
			URL realUrl = new URL(fullUrl);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			//设置请求头数据
			conn.setRequestProperty("accepe", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			//设置代理
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			//建立实际的连接
			conn.connect();
			//获取所有的响应头数据
			Map<String, List<String>> responseHeaders = conn.getHeaderFields();
			Response response = decodeResponseHeader(responseHeaders);
			System.out.println(response.getResponseCode());
		}catch(Exception ex){
			//TODO do something here
		}
	}
	
	/**
	 * 发送post请求
	 * @param url		url连接
	 * @param params	传入后台的参数
	 */
	public static void doPost(String url, Map<Object, Object> params){
		
	}
	
	/**
	 * 编码请求数据
	 * @param params	发送给后台请求的参数
	 * @param type
	 * @return
	 */
	private static String encodeData(Map<String, String> params, int type){
		return null;
	}
	
	/**
	 * 解码响应数据
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String decodeResponseContent(String response){
		return null;
	} 
	
	private static Response decodeResponseHeader(Map<String, List<String>> responseHeaders){
		
		Response response = new Response();
		
		List<String> responseStr = responseHeaders.get(null);
		//匹配（HTTP/1.1 200 OK）字符串
		String regex = "([a-zA-Z]{4}/\\d\\.\\d)\\s([0-9]{3})\\s([a-zA-Z]*)";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(responseStr.get(0));
		if(m.find()){
			String protocol = m.group(1);
			int responseCode = Integer.parseInt(m.group(2));
			String responseMsg = m.group(3);
			response.setProtocol(protocol);
			response.setResponseCode(responseCode);
			response.setResponseStr(responseMsg);
		}
		return null;
	}
}
