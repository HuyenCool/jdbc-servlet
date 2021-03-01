package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet result) {
		UserModel userModel = new  UserModel();
		try {
			userModel.setId(result.getLong("id"));
			userModel.setUserName(result.getString("username"));
			userModel.setPassWord(result.getString("password"));
			userModel.setFullName(result.getString("fullname"));
			userModel.setStatus(result.getInt("status"));
			try {
				RoleModel role = new RoleModel();
				role.setName(result.getString("name"));
				role.setCode(result.getString("code"));
				userModel.setRole(role);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userModel;
	}

}
