package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.ICategoryDao;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryDao extends AbstractDao<CategoryModel> implements ICategoryDao{

	@Override
	public List<CategoryModel> findAll() {
		String sql = "select * from category";
		return select_search(sql, new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(long id) {
		String sql = "select * from  category where id = ? ";
		List<CategoryModel> categorys = select_search(sql, new CategoryMapper(), id);
		return categorys.isEmpty()?null:categorys.get(0);
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		String sql = "select * from  category where code = ? ";
		List<CategoryModel> categorys = select_search(sql, new CategoryMapper(), code);
		System.out.print("Huyeefnnnn"+ categorys.get(0).getId());
		return categorys.isEmpty()?null:categorys.get(0);
	}

}
