package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sort.Sorter;

public class PageRequest implements Pageble {
	private Integer page;
	private Integer maxPageItem;
	private Sorter sorter;
	
	public PageRequest(Integer page, Integer maxPageItem, Sorter sorter) {
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.sorter = sorter;
	}
	
	@Override
	public Integer getPage() {
		// TODO Auto-generated method stub
		return this.page;
	}

	@Override
	public Integer getOffset() {
		// TODO Auto-generated method stub
		if(this.page!= null && this.maxPageItem!= null) {
			return (this.page - 1)*this.maxPageItem;
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		
		return this.maxPageItem;
	}

	public Integer getMaxPageItem() {
		return maxPageItem;
	}

	public void setMaxPageItem(Integer maxPageItem) {
		this.maxPageItem = maxPageItem;
	}

	public void setSorter(Sorter sorter) {
		this.sorter = sorter;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public Sorter getSorter() {
		
		return this.sorter;
	}
	
	

}
