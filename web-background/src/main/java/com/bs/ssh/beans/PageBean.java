package com.bs.ssh.beans;

import com.google.gson.annotations.Expose;

public class PageBean<T> {

	//每一页显示的数据条数
	private int pageSize;

	//当前的页数
	private int currentPage;

	//总页数
	@Expose private int pageCount;

	//记录数
	@Expose private int recordCount;

	//下一页
	private int nextPage;

	//上一页
	private int beforePage;

	//记录集合
	@Expose private T result;

	public PageBean() {
	}

	public PageBean(PageRequest request, int recordCount, T result) {
		this.setCurrentPage(request.getPageNumber());
		this.setPageSize(request.getPageSize());
		this.setRecordCount(recordCount);
		this.setResult(result);
	}

	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
		if (recordCount % this.pageSize == 0) {
			this.pageCount = recordCount / this.pageSize;
		} else {
			this.pageCount = recordCount / this.pageSize + 1;
		}
		if (this.pageCount == 1) {
			this.nextPage = 1;
			this.beforePage = 1;
			this.currentPage = 1;
		} else if (this.currentPage >= this.pageCount) {
			this.currentPage = this.pageCount;
			this.nextPage = this.pageCount;
			this.beforePage = this.pageCount - 1;
		} else if (this.currentPage < 1) {
			this.currentPage = 1;
			this.beforePage = 1;
			this.nextPage = 2;
		} else {
			if (this.currentPage == 1) {
				this.beforePage = 1;
			} else {
				this.beforePage = this.currentPage - 1;
			}
			this.nextPage = this.currentPage + 1;
		}
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getBeforePage() {
		return beforePage;
	}
	public void setBeforePage(int beforePage) {
		this.beforePage = beforePage;
	}
}
