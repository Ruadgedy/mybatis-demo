package com.demo.dao;

import com.demo.bean.User;

import java.util.List;

/**
 * @author yuhao
 * @date: 2021/5/10
 * @description:
 */
public interface UserMapper {

	public User getUserById(Integer id);

	public void insertUserSelective(User user);

	public void deleteUserById(Integer id);

	public List<User> getAll();
}
