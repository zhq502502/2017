package com.packages.controller;

import java.util.List;

import javax.servlet.ServletContext;

import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.packages.core.ResultBean;
import com.packages.util.Error;
import com.packages.util.PropUtil;
import com.packages.util.TokenUtil;



public class IndexController extends Controller {
	private Logger log = Logger.getLogger("根目录");
	
	public void index(){
		//this.testPro();
		render("index.jsp");
    }
	public void testPro(){
		Object[] strArray={"7"};
		/**增加记录*/
		//System.err.println(Db.update(String.format(PropUtil.getInstance().getValue("sql.select.test1"), strArray)));//返回值为当前增加的记录数
		/**更新一条语句*/
		//System.err.println(Db.update(String.format(PropUtil.getInstance().getValue("sql.select.test2"), strArray)));//返回当前更新的记录数
		/**查询list集合*/
		//System.err.println(Db.find(String.format(PropUtil.getInstance().getValue("sql.select.test3"), strArray)));
		/*List<Record> test=Db.find(String.format(PropUtil.getInstance().getValue("sql.select.test3"), strArray));
		  for (Record record : test) {
		   System.err.println(record.getInt("id"));
		   System.err.println(record.getStr("name"));
		   System.err.println(record.getInt("age"));
		  }*/
		/**查询一条记录*/
		//System.err.println(Db.findFirst(String.format(PropUtil.getInstance().getValue("sql.select.test5"), strArray)));
		/**删除一条记录*/
		System.err.println(Db.update(String.format(PropUtil.getInstance().getValue("sql.select.test4"), strArray)));//返回的删除记录数
	}
	public void add(){
		renderJson(getResultSimple());
	}
	public void update(){
		renderJson(getResultSimple());
	}
	public void info(){
		renderJson(getResultInfo());
	}
	public void delete(){
		renderJson(getResultSimple());
	}
	public void list(){
		renderJson(getResultList());
	}
	private ResultBean getResultSimple(){
		ResultBean result = new ResultBean();
		String m = getPara("m");
		String p = getPara("p");
		String t = getPara("t");
		/*if(t!=null){
			if(!TokenUtil.validateToken(t)){
				result.setCode(Error.TOKEN_ERROR);
				return result;
			}
		}*/
		if(m==null){
			result.setCode(Error.FAIL);
			return result;
		}else if(PropUtil.getInstance().getValue(m)==null){
			result.setCode(Error.FAIL);
			return result;
		}
		if(p==null){
			int flag = Db.update(PropUtil.getInstance().getValue(m));
			result.setCode(flag>0?Error.SUCCESS:Error.FAIL);
		}else{
			Object [] param = p.split("|");
			int flag = Db.update(String.format(PropUtil.getInstance().getValue(m), param));
			result.setCode(flag>0?Error.SUCCESS:Error.FAIL);
		}
		return result;
	}
	private ResultBean getResultInfo(){
		ResultBean result = new ResultBean();
		String m = getPara("m");
		String p = getPara("p");
		String t = getPara("t");
		if(t!=null){
			if(!TokenUtil.validateToken(t)){
				result.setCode(Error.TOKEN_ERROR);
				return result;
			}
		}
		if(m==null){
			result.setCode(Error.FAIL);
			return result;
		}else if(PropUtil.getInstance().getValue(m)==null){
			result.setCode(Error.FAIL);
			return result;
		}
		if(p==null){
			Record flag = Db.findFirst(PropUtil.getInstance().getValue(m));
			result.setCode(Error.SUCCESS);
			result.setData(flag);
		}else{
			Object [] param = p.split("|");
			Record flag = Db.findFirst(String.format(PropUtil.getInstance().getValue(m), param));
			result.setCode(Error.SUCCESS);
			result.setData(flag);
		}
		return result;
	}
	private ResultBean getResultList(){
		ResultBean result = new ResultBean();
		String m = getPara("m");
		String p = getPara("p");
		String t = getPara("t");
		if(t!=null){
			if(!TokenUtil.validateToken(t)){
				result.setCode(Error.TOKEN_ERROR);
				return result;
			}
		}
		if(m==null){
			result.setCode(Error.FAIL);
			return result;
		}else if(PropUtil.getInstance().getValue(m)==null){
			result.setCode(Error.FAIL);
			return result;
		}
		if(p==null){
			List<Record> flag = Db.find(PropUtil.getInstance().getValue(m));
			result.setCode(Error.SUCCESS);
			result.setData(flag);
		}else{
			Object [] param = p.split("|");
			List<Record> flag = Db.find(String.format(PropUtil.getInstance().getValue(m), param));
			result.setCode(Error.SUCCESS);
			result.setData(flag);
		}
		return result;
	}
	public void init(){
		PropUtil.getInstance().initProp();
		renderText("初始化配置完成");
	}
}
