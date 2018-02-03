package com.momstouch.domain;

public class Criteria {
	
	private int page;
	private int perPageNum;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		
		if(page <= 0){
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	//method for MyBatis SQL Mapper
	public int getPageStart(){
		return (this.page -1)* perPageNum;
	}
	
	//method for MyBatis SQL Mapper
	public int getPerPageNum() {
		return this.perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		
		if(perPageNum <= 0 || perPageNum  > 100){
			this.perPageNum = 10;
			return;
		}
		//이게 당연히 있어야 하는데 없어서 9가 set이 안되는거 같아서 넣었다
		this.perPageNum = perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	

}
