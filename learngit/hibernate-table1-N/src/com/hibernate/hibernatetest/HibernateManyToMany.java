package com.hibernate.hibernatetest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import com.hibernate.manytomany.Role;
import com.hibernate.manytomany.User;
import com.hibernate.utils.HibernateUtils;

public class HibernateManyToMany {
	//演示多对多级联保存
	@Test
	public void testSave(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tc = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
					
			//开启事务
			tc = session.beginTransaction();
					
			//添加两个用户，为这两个用户添加多个角色
			//创建用户和角色对象
			User user1 = new User();
			user1.setUser_name("刘备");
			user1.setUser_password("123");
			
			User user2 = new User();
			user2.setUser_name("曹操");
			user2.setUser_password("456");
			
			Role r1 = new Role();
			r1.setRole_name("王者");
			r1.setRole_password("234");
			
			Role r2 = new Role();
			r2.setRole_name("皇帝");
			r2.setRole_password("567");
			
			Role r3 = new Role();
			r3.setRole_name("奸雄");
			r3.setRole_password("890");
			
			//建立关系，把角色放到用户里面
			//u1---r1/r2
			user1.getSetRole().add(r1);
			user1.getSetRole().add(r2);
			
			//u2---r2/r3
			user2.getSetRole().add(r2);
			user2.getSetRole().add(r3);
			
			//保存用户
			session.save(user1);
			session.save(user2);
					
			//提交事务
			tc.commit();
					
		} catch (Exception e) {
			//回滚事务
			tc.rollback();
		}finally{
			//与本地线程绑定了就不需要我们关闭
//			session.close();
			sessionFactory.close();
		}
	}
	//演示多对多级联删除
	@Test
	public void testDelete(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tc = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
					
			//开启事务
			tc = session.beginTransaction();
					
			//查询ID然后删除
			User user = session.get(User.class, 1);
			session.delete(user);
					
			//提交事务
			tc.commit();
					
		} catch (Exception e) {
			//回滚事务
			tc.rollback();
		}finally{
			//与本地线程绑定了就不需要我们关闭
//			session.close();
			sessionFactory.close();
		}
	}
	//维护第三张表
	@Test
	public void testTable(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tc = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
					
			//开启事务
			tc = session.beginTransaction();
					
			//让某个用户有某个角色
			//让刘备有奸雄这个角色
			//查询刘备和奸雄
			User user = session.get(User.class, 1);
			Role role = session.get(Role.class, 3);
			//把奸雄放到刘备的set集合里面去
			user.getSetRole().add(role);
			
			//让某个用户没有某个角色
			//让刘备没有奸雄这个角色
			//查询刘备和奸雄
			User user1 = session.get(User.class, 1);
			Role role1 = session.get(Role.class, 3);
			//把奸雄放到刘备的set集合里面去
			user1.getSetRole().remove(role1);
					
			//提交事务
			tc.commit();
					
		} catch (Exception e) {
			//回滚事务
			tc.rollback();
		}finally{
			//与本地线程绑定了就不需要我们关闭
//			session.close();
			sessionFactory.close();
		}
	}

}
