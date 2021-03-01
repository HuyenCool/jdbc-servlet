package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.NewsModel;

public class NewMapper implements RowMapper<NewsModel>{

	@Override
	public NewsModel mapRow(ResultSet result) {
		NewsModel newmModel = new  NewsModel();
		try {
			newmModel.setId(result.getLong("id"));
			newmModel.setTitle(result.getString("title"));
			newmModel.setThumbnail(result.getString("thumbnail"));
			newmModel.setShortdescription(result.getString("shortdescription"));
			newmModel.setContent(result.getString("content"));
			newmModel.setCategoryId(result.getLong("categoryid"));
			newmModel.setCreatedDate(result.getTimestamp("createddate"));
			newmModel.setCreatedBy(result.getString("createdby"));
			if(result.getTimestamp("modifieddate")!= null) {
				newmModel.setModifiedDate(result.getTimestamp("modifieddate"));
			}
			if(result.getTimestamp("modifiedby")!= null) {
				newmModel.setModifiedDate(result.getTimestamp("modifiedby"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newmModel;
	}

}
