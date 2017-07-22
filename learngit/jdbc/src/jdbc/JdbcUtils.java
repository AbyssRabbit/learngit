package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
	private static Properties props = null;
	//只在JdbcUtils类中被加载时执行一次
	static {
		/*
		 * 1.加载配置文件
		 *    配置文件里有四大参数(com.mysql.jdbc.Driver
         *                     jdbc:mysql://localhost:3306/ithost
         *                     username
         *                     password)
		 *    若想连接不同的数据库，只需要修改配置文件的内容
		 * 2.加载驱动类
		 * 3.调用DriverManager.getConnection()
		 */
		
		//加载配置文件
		//给props初始化，即加载dbconfig.properties文件到props对象中
		try {
		InputStream in = JdbcUtils.class.getClassLoader()
				.getResourceAsStream("dbconfig.properties");
		props = new Properties();
		
			props.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		//加载驱动类
		try {
			Class.forName(props.getProperty("driverClassName"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	public static Connection getConnection() throws SQLException{
	
	//得到connection
	return DriverManager.getConnection(props.getProperty("url"),
			props.getProperty("username"),props.getProperty("password"));
   }

}
