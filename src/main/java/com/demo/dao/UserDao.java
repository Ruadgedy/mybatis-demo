package com.demo.dao;

import com.demo.bean.User;

/**
 * @author yuhao
 * @date: 2021/5/10
 * @description:
 */
public interface UserDao {

	public User findUserById(Integer id);

	public void insertUser(User user);

	public void deleteUser(Integer id);
}
