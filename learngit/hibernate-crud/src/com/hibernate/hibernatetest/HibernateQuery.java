package com.hibernate.hibernatetest;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.hibernate.entity.User;
import com.hibernate.utils.HibernateUtils;

public class HibernateQuery {
	//使用Query对象
	@Test
	public void testUpate(){
		
		//调用工具类得到sessionFactory对象
	    SessionFactory sessionFactory = null;					
		//获取session
		Session session = null;
		//开启事务
		Transaction tc = null; 
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tc = session.beginTransaction();
			
			//1 创建Query对象
			//方法里面写hql语句
			Query query = session.createQuery("from User");
			//2 调用query对象里面方法得到结果
			List<User> list = query.list();
			//遍历集合
			for(User user:list){
				System.out.println(user);
			}
			
			//提交事务
			tc.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭事务
			session.close();
			sessionFactory.close();
			
		}
	}
	//使用Criteria对象
	@Test
	public void testCriteria(){
			
		//调用工具类得到sessionFactory对象
		SessionFactory sessionFactory = null;					
		//获取session
		Session session = null;
		//开启事务
		Transaction tc = null; 
			
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tc = session.beginTransaction();
				
			//1 创建Criteria对象
			//方法里面参数是实体类class
			Criteria criteria = session.createCriteria(User.class);
			//2 调用query对象里面方法得到结果
			List<User> list = criteria.list();
			//遍历集合
			for(User user:list){
				System.out.println(user);
			}
				
			//提交事务
			tc.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭事务
			session.close();
			sessionFactory.close();
				
		}
	}
	//使用SQLQuery对象
	@Test
	public void testSQLQuery(){
				
		//调用工具类得到sessionFactory对象
		SessionFactory sessionFactory = null;					
		//获取session
		Session session = null;
		//开启事务
		Transaction tc = null; 
				
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tc = session.beginTransaction();
					
			//1 创建Criteria对象
			//方法里面参数是实体类class
			SQLQuery sqlQuery = session.createSQLQuery("select * from student");
			//2 调用query对象里面方法得到结果
			//返回list集合，默认里面每部分都是数组结构
//			List<Object[]> list = sqlQuery.list();
//				//遍历集合
			//返回的是数组形式
//			for(Object[] objects:list){
//				System.out.println(Arrays.toString(objects));
//			}
			
			//返回list中每部分是对象形式
			sqlQuery.addEntity(User.class);
			List<User> list = sqlQuery.list();
			for(User user : list){
				System.out.println(user);
			}
			
			//提交事务
			tc.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭事务
			session.close();
			sessionFactory.close();
					
		}
	}	
}
