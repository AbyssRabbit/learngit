package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;


public class JDBC_Demo1 {
	//增删改
	@Test
	public void fun1() throws Exception{
		/*
		 * 一 、准备四大参数
		 */
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/ithost";
		String username = "root";
		String password = "123";
		//加载驱动类
		Class.forName(driverClassName);
		//使用DriverManager以及剩下的三个参数，得到connection对象
		Connection con = DriverManager.getConnection(url,username,password);
		/*
		 * 二 、对数据进行增删改
		 * 通过connection对象得到Statement
		 * Statement语句发送器，它的功能就是向数据库发送sql语句
		 * 通过它的int executeUpdate（String sql），他可以发送DML，DDL
		 */
		
		//通过connection得到Statement
		Statement stmt = con.createStatement();
		//使用Statement发送sql语句
		String sql = "insert into user value(1008,'是否','456')";//插入一条sql语句，update，delete
		int r = stmt.executeUpdate(sql);//使用executeUpdate方法发送sql语句
		//Integer it = r;
		System.out.println(r);//打印影响的行数
	}
	//查询
	@Test
	public void fun2() throws Exception{//查询
		/*
		 * 1.得到connection对象
		 * 2.得到statement,发送select语句
		 * 3.对查询返回的表格进行解析
		 */
		
		/*
		 * 一.得到连接
		 * 1.准备四大参数
		 */
		
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/school";
		String username = "root";
		String password = "123";
		
		/*
		 * 2.加载驱动类
		 */
		
		Class.forName(driverClassName);
		
		/*
		 * 3.使用Drivermanager的getConnction方法通过剩下的三个参数得到连接
		 */
		
		Connection con = DriverManager.getConnection(url, username, password);
		
		/*
		 * 二.得到statement，执行查询语句
		 * 1.得到statement对象：使用connection的createStatement方法
		 */
		
		Statement stmt = con.createStatement();
		
		/*
		 * 2.调用Statement的ResultSet rs = executeQuery(String querysql)
		 * ResultSet : 结果集
		 */
		
		ResultSet rs = stmt.executeQuery("select * from student where id=1");
		
		/*
		 * 三.解析ResultSet
		 * 1.把行光标移动到第一行，可以调用next()来完成
		 */
		
		while(rs.next()){//把光标向下移动一行，并判断下一行是否存在
			int empno = rs.getInt(1);//通过列编号来获取该列的值
			String ename = rs.getString("name");//通过该列的名称来获取该列的值
			System.out.println(empno+","+ename);
		}
		
		/*
		 * 四.关闭资源
		 */
		
		rs.close();
		stmt.close();
		con.close();//必须关
	}
	//规范化
	@Test
	public void fun3() throws Exception{
		//定义引用
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			/*
			 * 一 得到连接
			 */
			String driverClassName = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/school";
			String username = "root";
			String password = "123";
			
			//加载驱动类		
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, username, password);//实例化
			/*
			 * 二 创建Statement
			 */
			stmt = con.createStatement();
			String sql = "select * from student";
			rs = stmt.executeQuery(sql);
			/*
			 * 循环遍历rs，打印其中数据
			 */
//			while(rs.next()){
//				System.out.println(rs.getObject(1)+","+rs.getString("t_name"));
//			}
			
			//打印完整的表格数据
			int count = rs.getMetaData().getColumnCount();
			while(rs.next()){//遍历数据库中表格的行
				for(int i=1;i<=count;i++){//遍历数据库中表格的列
					System.out.print(rs.getObject(i));
					if(i<count){
						System.out.print(",");
					}					
				}
				System.out.println();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			//关闭
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(con != null) con.close();
			
		}
	
	}
	//使用jdbc工具类
	@Test
	public void fun4() throws SQLException{
		Connection con = JdbcUtils.getConnection();
		System.out.println(con);
	}

}
