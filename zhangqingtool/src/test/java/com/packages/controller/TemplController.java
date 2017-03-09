package com.packages.controller;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.packages.util.FileUtil;

import freemarker.cache.StringTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class TemplController extends Controller {
	public void index(){
		render("/test/template/index.jsp");
	}
	public void createtemp() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		String temp = getPara("temp");
		//申明模版引擎配置
		Configuration config = new Configuration(Configuration.VERSION_2_3_25);
		config.setDefaultEncoding("UTF-8");
		
		//声明一个String类型的模版
		StringTemplateLoader stringTemp = new StringTemplateLoader();
		stringTemp.removeTemplate("testTemp");
		stringTemp.putTemplate("testTemp", temp);
		
		//模版配置引擎设置模版
		config.setTemplateLoader(stringTemp);
		
		//组装数据
		Map<String,String> data = new HashMap<>();
		data.put("username", "地址变更");
		
		//获取模版
		Template template = config.getTemplate("testTemp");
		//设置数据
		StringWriter writer = new StringWriter();
		template.process(data, writer);
		String result = writer.toString();
		
		
		FileUtil.writeFile("d:/test.html", result);
		//renderHtml(result);
		//renderText(result);
		renderFile(new File("d:/test.html"));
	}
}