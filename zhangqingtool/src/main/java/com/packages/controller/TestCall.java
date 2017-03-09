package com.packages.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.ICallback;

public class TestCall implements ICallback{
	public int a = 0;  
    public int b=0;  
    int result;  
    public String reason="数据库意外,请重试";  
	@Override
	public Object run(Connection conn) throws SQLException {
		
		System.err.println("执行run方法-------");
		// TODO 自动生成的方法存根
		 CallableStatement proc = null;  
         try {             
 
            proc = (CallableStatement) conn.prepareCall("{ call sp_add3(?,?,?) }"); // borrow为mysql的存储过程名，其中有两个参数，1个返回值  
           proc.setInt(1, a);//设置参数值  
           proc.setInt(2, b);  
           proc.registerOutParameter(3, java.sql.Types.INTEGER);//设置返回值类型  
           proc.execute();  
 
          result =  proc.getInt(3);//得到返回值  
          }catch(Exception e){  
              e.printStackTrace();  
          } finally {  
          //DbKit.closeQuietly(conn);  
          conn.close();
        }  
     	return null;
  }  
	//内部类结束  
	//调用内部类方法  
	  public static void trackresult(int a,int b){//可以加参数  
		        TestCall  test =new TestCall();  
		        test.a=a;  
		        test.b=b;  
	            Db.execute(test);  
	            int reason=test.result;
	            System.err.println("输出的是啥："+reason);
	  }  
 
	  public static void main(String[] args) {
		  trackresult(1,1);
	}
}
