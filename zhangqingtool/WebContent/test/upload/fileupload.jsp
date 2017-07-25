<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>

<head>
<style>
body,html{
font-family: 微软雅黑;
}
</style>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.setAttribute("basePath",basePath);
%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<base href="${basePath}"></base>
<title>${pagetitle }</title>
<link type="text/css" rel="stylesheet" href="${basePath}plugin/uploadfive/uploadifive.css" />
<script type="text/javascript" src="${basePath}js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}plugin/uploadfive/jquery.uploadifive.min.js"></script>
</head>
<body>
<div>
	<div id="tip-queue1"></div>
	<input type="file" id="file" />
</div>
<script type="text/javascript">
$(function() {
    $('#file').uploadifive({
        'auto' : true,
        'uploadScript' : 'fileupload',
        'fileObjName' : 'file',
        'buttonText' : '上传照片',
        'queueID' : 'tip-queue1',
        'fileType' : false,
        'multi' : true,
        'fileSizeLimit'   : 5242880,
        'uploadLimit' : 4,
        'queueSizeLimit'  : 4,   
        'onUploadComplete' : function(file, data) {
            console.log("onUploadComplete"+file);
            console.log("onUploadComplete"+data);
        },
        onCancel : function(file) {
        	console.log("onCancel"+file);
          
        },
        onFallback : function() {
            alert("该浏览器无法使用!");
        },
        onUpload : function(file) {
        	console.log("onUpload"+file);
            //document.getElementById("submit").disabled = true;//当开始上传文件，要防止上传未完成而表单被提交
        },
    });
});
</script>
</body>
</html>