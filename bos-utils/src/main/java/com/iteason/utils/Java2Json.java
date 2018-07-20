package com.iteason.utils;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class Java2Json {
	public static void ObjectToJson(Object object,String[] excludes) throws IOException{
		JsonConfig config = new JsonConfig();
		config.setExcludes(excludes);
		String json = JSONObject.fromObject(object,config).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
	
	public static <T> void ArrayToJson(List<T> list,String[] excludes) throws IOException{
		JsonConfig config = new JsonConfig();
		config.setExcludes(excludes);
		String json = JSONArray.fromObject(list,config).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
}
