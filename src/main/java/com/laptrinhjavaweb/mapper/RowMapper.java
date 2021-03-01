package com.laptrinhjavaweb.mapper;



import java.sql.ResultSet;

import com.laptrinhjavaweb.model.NewsModel;

public interface RowMapper<T> {
	T mapRow(ResultSet result);
}
