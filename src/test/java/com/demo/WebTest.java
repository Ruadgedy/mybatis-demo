package com.demo;

import com.demo.bean.User;
import com.demo.dao.UserMapper;
import com.demo.dao.impl.UserDaoImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author yuhao
 * @date: 2021/5/10
 * @description:
 */
public class WebTest {

	private SqlSessionFactory sqlSessionFactory;
	private static Logger log = Logger.getLogger(WebTest.class);

	@Before
	public void init(){
		String resource = "mybatis-configuration.xml";
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
	}

	@Test
	public void testFindUserById(){
		UserDaoImpl userDao = new UserDaoImpl(sqlSessionFactory);
		User user = userDao.findUserById(1);
		log.info(user);
	}

	@Test
	public void testGetUserById(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User userById = userMapper.getUserById(1);
		log.info(userById);

		List<User> all = userMapper.getAll();
		all.forEach(log::info);

		User user = new User();
		user.setUsername("袁承志");
		user.setPassword("abcdefg");
		user.setTelephone("13880000014");
		user.setRegisteredDate(new Date());
		userMapper.insertUserSelective(user);
		sqlSession.commit();
		sqlSession.close();
	}
}
