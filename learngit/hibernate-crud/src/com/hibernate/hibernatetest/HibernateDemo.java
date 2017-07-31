package com.hibernate.hibernatetest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.hibernate.entity.User;
import com.hibernate.utils.HibernateUtils;

public class HibernateDemo {
	@Test
	public void testadd(){
//		第一步：加载Hibernate核心配置文件
		//到src下面找到名称是hibernate.cfg.xml
		//在hibernate里面封装对象
//		Configuration cfg = new Configuration();
//		cfg.configure();
				
//		第二步：创建SessionFactory对象
		//读取hibernate核心配置文件内容，创建sessionFactory
		//在过程中，根据映射关系，在配置数据库里面把表创建
//		SessionFactory sessionfactory = cfg.buildSessionFactory();
		
		//使用工具类获取SessionFactory对象，可以把上面两步操作合为一步，并减少资源的消耗
		SessionFactory sessionfactory = HibernateUtils.getSessionFactory();
				
//		第三步：使用SessionFactory创建session对象
		//类似于连接
		Session session = sessionfactory.openSession();
		
//		第四步：开始事务
		Transaction tc = session.beginTransaction();
		
//		第五步：写具体逻辑crud操作
		//添加功能
		User user = new User();
		user.setName("曹操");
		user.setAge(25);
		user.setMajor("人力资源");
		user.setSex("男");
		user.setTel("15648523645");
		//调用session的方法实现添加
		session.save(user);
		
//		第六步：提交事务
		tc.commit();
		
//		第七步：关闭资源
		session.close();
		sessionfactory.close();
		
	}

}
