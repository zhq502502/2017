package com.packages.controller;

import com.jfinal.core.Controller;

public class TemplController extends Controller {
	public void index(){
		render("/test/template/index.jsp");
	}
	public void createtemp(){
		String temp = getPara("temp");
		
		String result = temp;
		renderText(result);
	}
}
