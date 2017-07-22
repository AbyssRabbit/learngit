package com.c3p0;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * c3p0
 * @author asus
 *
 */
public class C3p0 {
	/**
	 * 代码配置
	 * @throws Exception
	 */
	@Test
	public void fun1() throws Exception{
		//创建连接池对象
		ComboPooledDataSource datesource = new ComboPooledDataSource();
		
		//对池进行四大参数的配置
		datesource.setDriverClass("com.mysql.jdbc.Driver");
		datesource.setJdbcUrl("jdbc:mysql://localhost:3306/school");
		datesource.setUser("root");
		datesource.setPassword("123");
		
		//池配置
		datesource.setAcquireIncrement(5);
		datesource.setInitialPoolSize(20);
		datesource.setMinPoolSize(2);
		datesource.setMaxPoolSize(50);
		
		//得到连接对象
		Connection con = datesource.getConnection();
		System.out.println(con);
		con.close();
	}
	/**
	 * 配置文件默认配置
	 * @throws SQLException 
	 */
	@Test
	public void fun2() throws SQLException{
		/**
		 * 在自动创建连接池对象时，这个对象就会自动加载配置文件，不用我们来指定
		 */
		ComboPooledDataSource datesource = new ComboPooledDataSource();
		
		Connection con = datesource.getConnection();
		System.out.println(con);
		con.close();
	}
	/**
	 * 使用命名配置信息
	 * @throws SQLException 
	 */
	@Test
	public void fun3() throws SQLException{
		/**
		 * 模拟oracle，底层还是MySQL的配置信息
		 */
		ComboPooledDataSource datesource = new ComboPooledDataSource("oracle-config");
		
		Connection con = datesource.getConnection();
		System.out.println(con);
		con.close();
	}

}
