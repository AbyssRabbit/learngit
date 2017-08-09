package com.hibernate.utils;
/**
 * ** 创建sessionFactory过程中，这个过程特别耗资源
 *	(1)在hibernate操作中，建议一个项目一般创建一个sessionFactory对象
 *	** 具体实现
 *	(1)写工具类，写静态代码块实现
 *	- 静态代码块在类加载时候执行，执行一次
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
	static Configuration cfg =null;
	static SessionFactory sessionFactory = null;
	
	//静态代码块实现
	static {
		//加载核心配置文件
		cfg = new Configuration();
		cfg.configure();
		sessionFactory = cfg.buildSessionFactory();
	}
	//提供返回与本地线程绑定的session方法
	public static Session getSessionObject(){
		return sessionFactory.getCurrentSession();
	}
	//提供方法返回sessionFactory
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	public static void main(String[] args) {
		
	}

}
