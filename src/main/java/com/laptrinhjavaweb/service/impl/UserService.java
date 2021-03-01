package com.laptrinhjavaweb.service.impl;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IUserDao;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.IUserService;

public class UserService implements IUserService{

	@Inject IUserDao userDao;
	
	@Override
	public UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
		
		return userDao.findByUsernameAndPasswordAndStatus(username, password, status);
	}

}
