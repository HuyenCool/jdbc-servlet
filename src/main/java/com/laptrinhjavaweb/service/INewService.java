package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewService {
	List<NewsModel> findByCategoryId(Long categoryId);
	NewsModel save(NewsModel newModel);
	NewsModel update(NewsModel updateNew);
	void delete(Long[] longs);
	List<NewsModel> findAll(Pageble pageble);
	int getTotalItem();
	NewsModel findOne(Long id);
}
