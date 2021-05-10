package com.demo.anno;

import com.demo.bean.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yuhao
 * @date: 2021/5/10
 * @description:
 */
public interface UserDao {

	@Select("select * from t_user")
	@Results(id = "user", value = {
			@Result(column = "id",property = "id",id = true),
			@Result(column = "user_tel",property = "telephone"),
			@Result(column = "username",property = "username"),
			@Result(column = "password",property = "password"),
			@Result(column = "registration_time",property = "registrationTime")
	})
	public List<User> getAll();

	@Select("select  * from t_user where id=#{id}")
	@ResultMap("user")
	public User getUserById(Integer id);
}
