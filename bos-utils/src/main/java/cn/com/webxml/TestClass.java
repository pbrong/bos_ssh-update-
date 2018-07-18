package cn.com.webxml;

import java.util.List;

import org.junit.Test;

public class TestClass {
	@Test
	public void fun1(){
		TranslatorWebService proxy = new TranslatorWebService();
		TranslatorWebServiceSoap soap = proxy.getTranslatorWebServiceSoap();
		String word = "你好";
		ArrayOfString array = soap.getEnCnTwoWayTranslator(word);
		List<String> list = array.getString();
		System.out.println(list);
	}
}
