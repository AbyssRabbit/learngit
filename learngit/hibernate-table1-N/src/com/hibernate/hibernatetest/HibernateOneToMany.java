package com.hibernate.hibernatetest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.hibernate.entity.Customer;
import com.hibernate.entity.LinkMan;
import com.hibernate.utils.HibernateUtils;

public class HibernateOneToMany {
	//演示一对多级联保存(简单写法)
	@Test
	public void testAdd1(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tc = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
				
			//开启事务
			tc = session.beginTransaction();
				
			//添加一个客户，为这个客户添加一个联系人
			//创建客户和联系人对象
			Customer customer = new Customer();
			customer.setCustName("三国");
			customer.setCustLevel("VIP");
			customer.setCustSource("历史");
			customer.setCustPhone("5388");
			customer.setCustMobile("6666");
				
			LinkMan linkMan = new LinkMan();
			linkMan.setLkm_name("黄月英");
			linkMan.setLkm_gender("女");
			linkMan.setLkm_phone("4399");
				
			//在客户表示所有联系人，在联系人表示客户
			//建立客户对象和联系人对象关系
			   //把联系人对象放到客户对象的set集合里
			customer.getSetLinkMan().add(linkMan);
				
			//保存到数据库
			session.save(customer);
				
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
	//演示一对多级联保存(复杂写法)
	@Test
	public void testAdd2(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tc = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			
			//开启事务
			tc = session.beginTransaction();
			
			//添加一个客户，为这个客户添加一个联系人
			//创建客户和联系人对象
			Customer customer = new Customer();
			customer.setCustName("传智播客");
			customer.setCustLevel("VIP");
			customer.setCustSource("网络");
			customer.setCustPhone("9527");
			customer.setCustMobile("8888");
			
			LinkMan linkMan = new LinkMan();
			linkMan.setLkm_name("刘艺");
			linkMan.setLkm_gender("男");
			linkMan.setLkm_phone("8585");
			
			//在客户表示所有联系人，在联系人表示客户
			//建立客户对象和联系人对象关系
			   //把联系人对象放到客户对象的set集合里
			customer.getSetLinkMan().add(linkMan);
			   //把客户对象放到联系人里面
			linkMan.setCustomer(customer);
			
			//保存到数据库
			session.save(customer);
			session.save(linkMan);
			
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
	//演示一对多级联删除
	@Test
	public void testdelete(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tc = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
				
			//开启事务
			tc = session.beginTransaction();
				
			//根据ID查找到对象，调用session的delete方法
			Customer customer = session.get(Customer.class, 1);
			session.delete(customer);
			
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
