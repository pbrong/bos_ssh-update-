package com.iteason.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 
 * @author 阿荣
 * @Description: 封装PageBean分页工具类
 * @date: 2018年7月10日 下午1:58:14
 */
public class PageBean {
	private int currentPage;//当前页
	private int pageSize;//每页显示的条数
	private DetachedCriteria dc;//查询条件
	private int total;//总记录数
	private List rows;//当前需要显示的数据
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public DetachedCriteria getDc() {
		return dc;
	}
	public void setDc(DetachedCriteria dc) {
		this.dc = dc;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	
}
