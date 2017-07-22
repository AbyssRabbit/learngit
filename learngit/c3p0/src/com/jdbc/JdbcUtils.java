package com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	private static ComboPooledDataSource datasource = new ComboPooledDataSource();
	
	/**
	 * 使用连接池返回一个连接对象
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return datasource.getConnection();
	}
	
	/**
	 * 返回连接池对象
	 */
	
	public static DataSource getdatasource(){
		return datasource;
	}

}
