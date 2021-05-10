package com.demo;

import com.demo.bean.Dept;
import com.demo.bean.Employee;
import com.demo.bean.User;
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
public class EmployeeTest {

	SqlSession sqlSession = null;
	private static Logger logger = Logger.getLogger(EmployeeTest.class);

	@Before
	public void init(){
		String resource = "mybatis-configuration.xml";
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		sqlSession = factory.openSession();
	}

	@Test
	public void testGetEmployeeById(){
		// TODO:第一种方法：通过左连接查找
		String stmt = "com.demo.mapper.EmployeeMapper.getEmployeeById";

		// TODO: 第二种方法，通过嵌套查找进行查找
		String stmt2 = "com.demo.mapper.EmployeeMapper.getEmployeeById2";
//		List<Employee> emps = sqlSession.selectList(stmt, 1);
//		emps.forEach(System.out::println);
		Employee emp = sqlSession.selectOne(stmt2, 1);
		System.out.println(emp);
	}

	@Test
	public void testGetDeptList(){
		String stmt = "com.demo.mapper.DeptMapper.getDept";
		List<Dept> depts = sqlSession.selectList(stmt);
		depts.forEach(System.out::println);
	}

	// TODO:测试mybatis的一级缓存
	// *mybatis一级缓存是SqlSession层面的，也就是说，在同一个SqlSession下，相同的查询条件，第二次查询不会走数据库，而是通过mybatis缓存拿数据
	@Test
	public void testLocalCache(){
		System.out.println("------------------根据主键id=1查询用户信息  开始-----------------");
		String stmt = "com.demo.mapper.UserMapper.getUserById";
		User user = sqlSession.selectOne(stmt, 1);
		logger.info(user);
		System.out.println("------------------根据主键id=1查询用户信息  结束-----------------");

		// TODO ：有下面三种方式清空缓存
		// !如果第一次查询结束后将SqlSession关闭，那么下一次查询就还要走数据库
//		sqlSession.close();
		// ! 手动清空缓存
//		sqlSession.clearCache();
		// !提交事务，实际上也会清空一级缓存
		sqlSession.commit();

		// 相同id再次执行查询
		init();
		System.out.println("------------------根据主键id=1再次查询用户信息  开始-----------------");
		User res = sqlSession.selectOne(stmt, 1);
		logger.info(res);
		System.out.println("------------------根据主键id=1再次查询用户信息  结束-----------------");
		sqlSession.close();
	}

	// TODO: 测试二级缓存
	@Test
	public void testLocalCache2(){
		System.out.println("----------------根据员工主键id=1 查询员工信息并级联获取部门信息  开始");
		String stmt = "com.demo.mapper.EmployeeMapper.getEmployeeById";
		Employee employee1 = sqlSession.selectOne(stmt, 1);
		logger.info(employee1);
		System.out.println("----------------根据员工主键id=1 查询员工信息并级联获取部门信息  结束");

		// 部门修改
		System.out.println("----------------根据部门id=2 更新部门信息  开始");
		String stmt2 = "com.demo.mapper.DeptMapper.updateDeptByIdSelective";
		Dept dept = new Dept();
		dept.setDeptId(2);
		dept.setDeptName("人事部");
		dept.setDeptCreateDate(new Date());
		dept.setDeptInfo("团队组建");
		int ret = sqlSession.update(stmt2,dept);
		sqlSession.commit();
		logger.info(ret);
		System.out.println("----------------根据部门id=2 更新部门信息  结束");

		System.out.println("----------------根据员工主键id=1 查询员工信息并级联获取部门信息  开始");
		Employee res = sqlSession.selectOne(stmt, 1);
		logger.info(res);
		System.out.println("----------------根据员工主键id=1 查询员工信息并级联获取部门信息  结束");
	}
}
