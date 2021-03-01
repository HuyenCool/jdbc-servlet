package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.IUserDao;
import com.laptrinhjavaweb.mapper.UserMapper;
import com.laptrinhjavaweb.model.UserModel;

public class UserDao extends AbstractDao<UserModel> implements IUserDao{
	@Override
	public UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
		sql.append(" INNER JOIN role AS r ON r.id = u.roleid");
		sql.append(" WHERE username = ? AND password = ? AND status = ?");
		List<UserModel> users = select_search(sql.toString(), new UserMapper(), username, password, status);
		return users.isEmpty() ? null : users.get(0);
	}

	

}
