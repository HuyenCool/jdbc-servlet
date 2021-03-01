package com.laptrinhjavaweb.dao;

import java.util.List;


import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewDao extends GenericDao<NewsModel>{
	List<NewsModel> findByCategoryId(Long categoryId);
	NewsModel findOne(Long id);
	Long save(NewsModel newModel);
	void update(NewsModel updateNew);
	void delete(long id);
	List<NewsModel> findAll(Pageble pageble);
	int getTotalItem();
}
