package com.demo.dao.impl;

import com.demo.bean.User;
import com.demo.dao.UserDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author yuhao
 * @date: 2021/5/10
 * @description:
 */
public class UserDaoImpl implements UserDao {

	private SqlSessionFactory sqlSessionFactory;

	public UserDaoImpl(SqlSessionFactory factory){
		sqlSessionFactory = factory;
	}

	@Override
	public User findUserById(Integer id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = sqlSession.selectOne("getUserById", id);
		sqlSession.close();
		return user;
	}

	@Override
	public void insertUser(User user) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("insertUserSelective",user);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public void deleteUser(Integer id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("deleteUserById",id);
		sqlSession.commit();
		sqlSession.close();
	}
}
