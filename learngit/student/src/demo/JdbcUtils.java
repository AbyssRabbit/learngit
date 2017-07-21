package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class JdbcUtils {
	//1. 取得数据库的连接对象 
	@Test
	  public static Connection getConn(){
		  Connection conn=null; //连接对象 
		  try{
			 Class.forName("com.mysql.jdbc.Driver");//加载数据库的驱动 
			 String url="jdbc:mysql://localhost:3306/school";
			 conn=DriverManager.getConnection(url,"root","123");
		  }catch(Exception e){
			  e.printStackTrace();
			  System.out.println("数据库连接失败!");
		  }
		  return conn;
	 }
	  //2. 关闭操作对象
	  public static void closeDB(Connection conn,Statement stmt,ResultSet rs){
		  try{
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		  }catch(Exception e){
			  e.printStackTrace();
			  System.out.println("数据库操作对象关闭失败!");
		  }
	  }
	  //3. 更新操作(插入、删除、修改记录)
	  public static int update(String sql){
		  int k=0; 
		  Connection conn=null; 
		  Statement stmt=null; 
		  try{
			   conn=getConn();
			   stmt=conn.createStatement();
			  k=stmt.executeUpdate(sql); //执行更新操作
		  }catch(Exception e){
			  e.printStackTrace();
		  }finally{
			  closeDB(conn,stmt,null);
			  //更新操作时，关闭这些对象是没问题的，但是查询操作方法中不能关闭 
		  }
		  return k;
	  }
	  //4. 查询操作
	  public static ResultSet query(String sql){
		  ResultSet rs=null;  
		  try{
			  Statement stmt=getConn().createStatement();
			  rs=stmt.executeQuery(sql); //查询
		  }catch(Exception e){
			  e.printStackTrace();
			  System.out.println("数据查询失败!");
		  }
		  return rs;
	  }

}
