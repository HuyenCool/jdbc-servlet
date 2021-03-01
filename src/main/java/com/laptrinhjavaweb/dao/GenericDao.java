package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.mapper.RowMapper;

public interface GenericDao<T> {
	List<T> select_search(String sql, RowMapper<T> rowMapper, Object... parameters);// hàm chung(sql, đối tượng trả về,giá trị
																			// tham số

	void update_delete(String sql, Object... parameters);

	Long insert(String sql, Object... parameters);

	int count(String sql, Object... parameters);
}
