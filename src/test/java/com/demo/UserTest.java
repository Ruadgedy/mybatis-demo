package com.demo;

import com.demo.bean.QueryBean;
import com.demo.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yuhao
 * @date: 2021/5/10
 * @description:
 */
public class UserTest {

	SqlSession sqlSession = null;

	@Before
	public void init(){
		// mybatis全局配置文件
		String resource = "mybatis-configuration.xml";
		// 加载全局配置文件
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(resource);
		// 创建session工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		// 通过工厂生成sqlSession
		sqlSession = factory.openSession();
	}

	@Test
	public void testGetUser(){
		/*
			这个字符串由两部分组成
			1. <mapper namespace="com.demo.mapper.UserMapper">的namespace的值
			2. <select id="getUserById"> 中的id值
		 */
		String stmt = "com.demo.mapper.UserMapper.getUserById";
		User user = sqlSession.selectOne(stmt, 1);
		// *User{id=1, telephone='null', username='金庸', password='0b4e7a0e5fe84ad35fb5f95b9ceeac79', registeredTime=null}
		// TODO:上面的查询结果可以看出，还是有问题,部分字段显示为null值
		// !原因其实很好理解，数据库字段的名字和POJO对象属性的名字不一样导致的
		System.out.println(user);
	}

	@Test
	public void testGetAll(){
		String stmt = "com.demo.mapper.UserMapper.getAll";
		List<User> users = sqlSession.selectList(stmt);
		for (User user : users) {
			System.out.println(user);
		}
	}

	@Test
	public void testInsert(){
		User user = new User();
		user.setTelephone("110");
		user.setUsername("韦小宝");
		user.setPassword("1231dfsdas");
		user.setRegisteredDate(new Date());
		String stmt = "com.demo.mapper.UserMapper.insertUser";
		int ret = sqlSession.insert(stmt, user);
		System.out.println(ret);
		// !一定记住：对数据表的修改默认不会自动生效，一定要加入commit()语句
		// ! 增删改语句执行完成后一定要提交。否则数据库中的数据不会变化
		sqlSession.commit();

		// TODO:提交之后测试主键回填功能
		System.out.println(user.getId());
	}

	@Test
	public void deleteUserById(){
		String stmt = "com.demo.mapper.UserMapper.deleteUserById";
		int ret = sqlSession.delete(stmt, 10);
		System.out.println(ret);
		// !增删改一定要 commit()
		sqlSession.commit();
	}

	@Test
	public void testUserUpdate(){
		User user = new User();
		user.setUsername("令狐冲");
		user.setPassword("123456");
		user.setTelephone("8008208820");
		user.setId(9);

		String stmt = "com.demo.mapper.UserMapper.updateUserById";
		int ret = sqlSession.update(stmt, user);
		System.out.println(ret);

		sqlSession.commit();
	}

	@Test
	public void testSelective(){
		String stmt = "com.demo.mapper.UserMapper.getUserBySelective";
		User user = new User();
//		user.setId(4);
		user.setUsername("张");
		List<User> users = sqlSession.selectList(stmt, user);
		System.out.println(users.size());
		System.out.println(users.get(0));
	}

	@Test
	public void testUpdateSelective(){
		User user = new User();
		user.setId(9);
		user.setUsername("屁屁婧");
		user.setPassword("asdfdsa");

		String stmt = "com.demo.mapper.UserMapper.updateUserSelective";
		sqlSession.update(stmt,user);
		sqlSession.commit();
	}

	@Test
	public void testInsertSelective(){
		User user = new User();
		user.setUsername("冯宇豪1111");
		user.setPassword("fyh123");
		user.setTelephone("1888888888");

		String stmt = "com.demo.mapper.UserMapper.insertUserSelective";
		int ret = sqlSession.insert(stmt, user);

		System.out.println("执行结果:" + ret);
		sqlSession.commit();
		System.out.println("callback primary key: " + user.getId());
	}

	@Test
	public void testGetUserByQueryIds(){
		String stmt = "com.demo.mapper.UserMapper.getUserByQueryIds";
		// 封装QueryBean并传入id
		QueryBean bean = new QueryBean();
		List<Integer> ids = new ArrayList<Integer>(){
			{
				add(1);
				add(4);
				add(5);
				add(8);
			}
		};
		bean.setIds(ids);
		List<User> users = sqlSession.selectList(stmt, bean);
		users.forEach(System.out::println);
	}
}
