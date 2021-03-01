package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel>{

	@Override
	public CategoryModel mapRow(ResultSet result) {
		CategoryModel categoryModel = new  CategoryModel();
		try {
			
			categoryModel.setId(result.getLong("id"));
			categoryModel.setName(result.getString("name"));
			categoryModel.setCode(result.getString("code"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryModel;
		
	}

}
