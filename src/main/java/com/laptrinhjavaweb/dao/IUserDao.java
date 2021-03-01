package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.UserModel;

public interface IUserDao extends GenericDao<UserModel>{
	UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
}
