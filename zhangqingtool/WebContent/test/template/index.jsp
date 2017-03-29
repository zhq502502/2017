<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.setAttribute("basePath",basePath);
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${basePath}"/>
<title>java template</title>
<style type="text/css">
html,body{
	height: 100%;
	width: 100%;
	box-sizing:border-box!important; 
	margin: 0; 
}
</style>
</head>
<body>
<form action="temp/createtemp" method="post" style="width: 100%;height: 100%;margin: 0;">
<textarea style="width:100%;height: 90% " name="temp">${result}</textarea>
<br/>
<button type="submit">提交</button>
</form>
</body>
</html>