package com.zjh.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.zjh.vo.User;

public class UserDAOImpl extends JdbcDaoSupport implements IUserDAO {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		UserDAOImpl udao = ctx.getBean("udao", UserDAOImpl.class);
		udao.queryAll();
	}

	@Override
	public void addUser() {
		// TODO Auto-generated method stub
		String sql = "insert into user(username,password) values(?,?)";
		Object[] os = { "中文", "789" };
		int[] types = { java.sql.Types.VARCHAR, java.sql.Types.VARCHAR };
		this.getJdbcTemplate().update(sql, os, types);
	}

	@Override
	public void delUser() {
		// TODO Auto-generated method stub
		String sql = "delete from user where id = ?";
		this.getJdbcTemplate().update(sql, 4);
	}

	@Override
	public void updateUser() {
		// TODO Auto-generated method stub
		String sql = "update user set username='我是',password='中文' where id=?";
		this.getJdbcTemplate().update(sql, 3);
	}

	@Override
	public void queryById() {
		// TODO Auto-generated method stub
		// String sql = "select username from user where id=?";
		// String username=this.getJdbcTemplate().queryForObject(sql,
		// String.class, 2);
		// System.out.println(username);

		String sql = "select * from user where id=?";
		Object[] os = { 2 };
		User user = this.getJdbcTemplate().queryForObject(sql, os,
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User u = new User();
						u.setId(rs.getInt("id"));
						u.setUsername(rs.getString("username"));
						u.setPassword(rs.getString("password"));
						return u;
					}

				});
		System.out.println(user.getUsername() + "---" + user.getPassword());
	}

	@Override
	public void queryAll() {
		// TODO Auto-generated method stub
		String sql = "select * from user";
		List<User> list = this.getJdbcTemplate().query(sql,
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User u = new User();
						u.setId(rs.getInt("id"));
						u.setUsername(rs.getString("username"));
						u.setPassword(rs.getString("password"));
						return u;
					}
				});
		for(User user:list){
			System.out.println(user.getUsername() + "---" + user.getPassword());
		}
	}
}
