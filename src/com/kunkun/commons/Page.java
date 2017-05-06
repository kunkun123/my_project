package com.kunkun.commons;

import java.util.List;

public class Page {
	private List records;
	public List getRecords() {
		return records;
	}

	public void setRecords(List records) {
		this.records = records;
	}

	private int pageSize=3;//ÿҳ��ʾ������
	private int currentPageNum;//��ǰҳ��
	private int totalPage;//��ҳ��
	private int prePageNum;//��һҳ
	private int nextPageNum;//��һҳ
	
	private int startIndex;//ÿҳ��ʼ��¼������
	private int totalRecords;//�ܼ�¼����
    
    private String url;
    
    public Page(int currentPageNum,int totalRecords){
    	this.currentPageNum = currentPageNum;
    	this.totalRecords = totalRecords;
    	
    	totalPage = totalRecords%pageSize==0?totalRecords/pageSize:totalRecords/pageSize+1;
    	startIndex = (currentPageNum-1)*pageSize;
    }

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPrePageNum() {
		return currentPageNum-1>0?currentPageNum-1:1;
	}

	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}

	public int getNextPageNum() {
		return currentPageNum+1>totalPage?totalPage:currentPageNum+1;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
    
}
