package com.packages.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.packages.core.ResultBean;
import com.packages.util.Error;
import com.packages.util.PathUtil;
import com.packages.util.PropUtil;
import com.packages.util.StringUtil;
import com.packages.util.TokenUtil;



public class IndexController extends Controller {
	private Logger log = Logger.getLogger("根目录");
	
	public void index(){
		//this.testPro();
		render("index.jsp");
    }
	public void init(){
		PropUtil.getInstance().initProp();
		renderText("初始化配置完成");
	}
	public void fileupload(){//文件上传
		HttpServletRequest request = this.getRequest();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			// return mv;
		}
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		//setSessionAttr("proInfo", new ProcessInfo());
		upload.setProgressListener(new ProgressListener() {
			public void update(long pBytesRead, long pContentLength, int pItems) {
				/*ProcessInfo pri = new ProcessInfo();
				pri.itemNum = pItems;
				pri.readSize = pBytesRead;
				pri.totalSize = pContentLength;
				pri.show = pBytesRead + "/" + pContentLength + " byte";
				pri.rate = Math.round(new Float(pBytesRead) / new Float(pContentLength) * 100);
				setSessionAttr("proInfo", pri);*/
			}
		});
		ResultBean result = new ResultBean();
		//ProcessInfo process = new ProcessInfo();
		try {
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString();
				} else {
					String fieldName = item.getFieldName();
					String fileName = item.getName();
					String extension = fileName.substring(fileName.lastIndexOf("."));
					String contentType = item.getContentType();
					boolean isInMemory = item.isInMemory();
					long sizeInBytes = item.getSize();
					String filename = StringUtil.getRandomFilename()  + extension;
					File uploadedFile = new File(PathUtil.getProjectPath()+"/u/" +filename);
					String path = this.getRequest().getContextPath();
					String basePath = this.getRequest().getScheme()+"://"+this.getRequest().getServerName()+":"+this.getRequest().getServerPort()+path+"/";
					String url = basePath+"/u/";
					Map<String,String> data = new HashMap<String, String>();
					data.put("url", url+filename);
					data.put("filename",fileName);
					data.put("hzm",contentType.split("/")[0]);
					result.setCode(0);
					result.setData(data);
					item.write(uploadedFile);
				}
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.renderJson(result);
	}
}
