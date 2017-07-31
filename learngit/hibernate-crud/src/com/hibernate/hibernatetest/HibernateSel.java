package com.hibernate.hibernatetest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.hibernate.entity.User;
import com.hibernate.utils.HibernateUtils;

public class HibernateSel {
	//修改
	@Test
	public void testUpate(){
			//调用工具类得到sessionFactory对象
					SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
					
					//获取session
					Session session = sessionFactory.openSession();
					
					//开启事务
					Transaction tc = session.beginTransaction();
					
					//修改操作
					//修改id=2记录username值
					//根据id查询
					User user = session.get(User.class, 1);
					//向返回值user对象里面设置修改之后的值
					user.setName("孙权");
					//调用session方法update修改
					//执行过程：到user对象里面找到id值，根据id进行修改
					session.update(user);
					
					//提交事务
					tc.commit();
					
					//关闭事务
					session.close();
					sessionFactory.close();
	}
	//查询
	@Test
	public void getId(){
		//调用工具类得到sessionFactory对象
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		
		//获取session
		Session session = sessionFactory.openSession();
		
		//开启事务
		Transaction tc = session.beginTransaction();
		
		//根据id查询
		//调用session里面的get方法
		//第一个参数：实体类的class  第二个参数：id值
		User user = session.get(User.class, 1);
		System.out.println(user);
		
		//提交事务
		tc.commit();
		
		//关闭事务
		session.close();
		sessionFactory.close();
	}
	//删除
		@Test
		public void testDel(){
			//调用工具类得到sessionFactory对象
			SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
			
			//获取session
			Session session = sessionFactory.openSession();
			
			//开启事务
			Transaction tc = session.beginTransaction();
			
			//删除 
			//第一种：根据id查询再删除
			User user = session.get(User.class, 1);
			session.delete(user);
			
			//第二种：
//			User user = new User();
//			user1.setId(2);
//			session.delete(user);
			
			//提交事务
			tc.commit();
			
			//关闭事务
			session.close();
			sessionFactory.close();
		}
	
	//事务规范写法
	@Test
	public void testTransaction(){
//		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tc = null;
		try {
//			sessionFactory = HibernateUtils.getSessionFactory();
//			session = sessionFactory.openSession();
			
			//与本地线程绑定的session
			session = HibernateUtils.getSessionObject();
			//开启事务
			tc = session.beginTransaction();
			
			//添加
			User user = new User();
			user.setAge(21);
			user.setMajor("天文学");
			user.setName("黄月英");
			user.setSex("女");
			user.setTel("13487558987");
			
			session.save(user);
			//提交事务
			tc.commit();
			
		} catch (Exception e) {
			//回滚事务
			tc.rollback();
		}finally{
			//与本地线程绑定了就不需要我们关闭
//			session.close();
//			sessionFactory.close();
		}
	}
	
}
