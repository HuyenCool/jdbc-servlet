package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDao;
import com.laptrinhjavaweb.dao.INewDao;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.INewService;

public class NewService implements INewService{

	@Inject
	private INewDao newDao;
	
	@Inject
	private ICategoryDao catoryDao;

	public List<NewsModel> findByCategoryId(Long categoryId) {
	
		return newDao.findByCategoryId(categoryId);
	}


	@Override
	public NewsModel save(NewsModel newModel) {
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		newModel.setCategoryId(catoryDao.findOneByCode(newModel.getCategoryCode()).getId());
		Long newId = newDao.save(newModel);
		return newDao.findOne(newId);
		
	}


	@Override
	public NewsModel update(NewsModel updateNew) {
		NewsModel oldNew = newDao.findOne(updateNew.getId());
		updateNew.setCreatedBy(oldNew.getCreatedBy());
		updateNew.setCreatedDate(oldNew.getCreatedDate());
		updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		updateNew.setCategoryId(catoryDao.findOneByCode(updateNew.getCategoryCode()).getId());
		newDao.update(updateNew);
		return newDao.findOne(updateNew.getId());
	}





	@Override
	public void delete(Long[] longs) {
		// TODO Auto-generated method stub
		for (long id: longs) {
			//1.delete comment (khoa ngoai new_id)
			//2.delete news
			newDao.delete(id);
		}
	}


	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		
		return newDao.findAll(pageble);
	}


	@Override
	public int getTotalItem() {
		
		return newDao.getTotalItem();
	}


	@Override
	public NewsModel findOne(Long id) {
		NewsModel newModel = new NewsModel();
		newModel = newDao.findOne(id);
		newModel.setCategoryCode(catoryDao.findOne(newModel.getCategoryId()).getCode());
		return newModel;
	}

	
	
}
