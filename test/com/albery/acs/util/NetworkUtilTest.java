package com.albery.acs.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.albery.acs.util.NetworkUtil;

@RunWith(BlockJUnit4ClassRunner.class)
public class NetworkUtilTest {

	@Test
	public void test() {
		String url = "http://www.baidu.com";
		NetworkUtil.doGet(url, null);
	}
	
	@Test
	public void patternTest(){
		String str = "HTTP/1.1 200 OK";
		String regex = "([a-zA-Z]{4}/\\d\\.\\d)\\s([0-9]{3})\\s([a-zA-Z]*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()){
			for(int i=1;i<matcher.groupCount()+1;i++){
				System.out.println(matcher.group(i));
			}
		}
	}

}
