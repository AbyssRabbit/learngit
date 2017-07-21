package demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.Test;
	
public class StudentDemo {
	//1、系统菜单
	static Scanner s = new Scanner(System.in);
	public static void menu(){
		System.out.println("===学生信息管理系统v1.0===");
		System.out.println("1.初始化系统");
		System.out.println("2.显示学生信息");
		System.out.println("3.添加学生信息");
		System.out.println("4.按学号删除学生信息");
		System.out.println("5.按姓名模糊查询学生信息");
		System.out.println("6.按学号修改学生信息");
		System.out.println("7.按学号升序排序");
		System.out.println("8.备份系统信息");
		System.out.println("9.退出系统");
		System.out.println("======================");
	}
	//2.选择菜单项
	public static void select(){
	do{
		System.out.println("请选择菜单：");		
		int x = s.nextInt();
		switch(x){
					case 1:
						    init();
					        break;
					case 2:
						    dispalay();
					        break;
					case 3:
						    addStu();
					        break;
					case 4://删除
							System.out.println("请输入要删除的学号：");
						    int no1 = s.nextInt();
						    del(no1);
					        break;
					case 5:
						    System.out.println("请输入你要查询的学生姓名信息：");
						    String sid = s.next();
						    findByNo(sid);
						    break;
					
					case 6:
						   System.out.println("请输入你要查询的学生学号：");
				           int id = s.nextInt();
				           updataStu(id);
					        break;
					case 7:
						    sort();
					        break;
					case 8:
					        System.out.println("8.备份系统信息");break;
					
					case 9:
							System.out.println("10.退出系统!");
							System.exit(0);break;
		       }
		}while(true);
	}
	//1.初始化系统
	public static void init(){		
		System.out.println("请输入该系统最多保存的记录数：");
		int m = s.nextInt();//代表开辟的空间长度	
		System.out.println("请输入要输入的学生记录数：");
		int n = s.nextInt();//代表输入的记录数
		if(n<=m){	//记录数小于开辟的空间，否则会越界	
		for(int i = 0;i<n;i++){
			System.out.println("请输入学生信息");
			System.out.println("输入学号：");
			int id = s.nextInt();
			System.out.println("输入姓名：");
			String name = s.next();
			System.out.println("输入性别：");
			String sex = s.next();
			System.out.println("输入年龄：");
			int age = s.nextInt();
			System.out.println("输入专业：");
			String major = s.next();
			System.out.println("输入电话：");
			int tel = s.nextInt();
			String sql = "insert into student values("+id+",'"+name+"','"+sex+"',"
			+age+",'"+major+"',"+tel+")";
			JdbcUtils.update(sql);
		}
		}else{
			System.out.println("系统初始化失败！");
		}
		
	}
	//2.显示系统信息
	public static void dispalay(){
		String sql = "select * from student";
		ResultSet rs = JdbcUtils.query(sql);
		try {
			while(rs.next()){
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)
				+"\t"+rs.getInt(4)+"\t"+rs.getString(5)+"\t"+rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//3.添加学生信息
			public static void addStu(){
				System.out.println("请输入学生信息");
				System.out.println("输入学号：");
				int id = s.nextInt();
				System.out.println("输入姓名：");
				String name = s.next();
				System.out.println("输入性别：");
				String sex = s.next();
				System.out.println("输入年龄：");
				int age = s.nextInt();
				System.out.println("输入专业：");
				String major = s.next();
				System.out.println("输入电话：");
				int tel = s.nextInt();
				String sql = "insert into student values("+id+",'"+name+"','"+sex+"',"
				+age+",'"+major+"',"+tel+")";
				int k=JdbcUtils.update(sql);
				if(k!=0){
					System.out.println("恭喜你添加成功！");
				}else{
					System.out.println("很遗憾你添加失败了！");
				}
				
	}
	//4.按学号删除学生信息
	public static void del(int n){
			String sql = "delete from student where id="+n+"";
			int k=JdbcUtils.update(sql);
			if(k!=0){
				System.out.println("恭喜你删除成功！");
			}else{
				System.out.println("很遗憾你删除失败了！");
			}
	}
	//5.按姓名模糊查找学生信息
	@Test
	public static void findByNo(String sid){
		String sql = "select * from student where name like '%"+sid+"%'";
		ResultSet rs = JdbcUtils.query(sql);
		try {
			while(rs.next()){
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)
				+"\t"+rs.getInt(4)+"\t"+rs.getString(5)+"\t"+rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//6.按学号修改学生信息
	public static void updataStu(int id){
		System.out.println("输入姓名：");
		String name = s.next();
		System.out.println("输入性别：");
		String sex = s.next();
		System.out.println("输入年龄：");
		int age = s.nextInt();
		System.out.println("输入专业：");
		String major = s.next();
		System.out.println("输入电话：");
		int tel = s.nextInt();
		String sql = "update student set name='"+name+"',sex='"+sex+"',age="
	+age+",major='"+major+"',tel="+tel+" where id="+id+"";
		int k=JdbcUtils.update(sql);
		if(k!=0){
			System.out.println("恭喜你修改成功！");
		}else{
			System.out.println("很遗憾你修改失败了！");
		}
	}
	//7.按学号升序排序（冒泡排序）
	public static void sort(){
		String sql = "select * from student order by id ASC";
		ResultSet rs = JdbcUtils.query(sql);
		try {
			while(rs.next()){
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)
				+"\t"+rs.getInt(4)+"\t"+rs.getString(5)+"\t"+rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		menu();
		select();				
	}

}
