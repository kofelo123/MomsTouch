package com.momstouch.service;

import java.util.ArrayList;
import java.util.List;

import com.momstouch.domain.CartVO;
import com.momstouch.domain.Criteria;
import com.momstouch.domain.OrderVO;
import com.momstouch.domain.ProductVO;
import com.momstouch.domain.SearchCriteria;

public interface ProductService {

	public List<ProductVO> listNewProduct();

	public List<ProductVO> listBestProduct();
	
	public List<ProductVO> listKindProduct(String kind,Criteria cri);
	
	/*public List<ProductVO> listKindProduct(String kind);
	
	public List<ProductVO> listKindProduct2(String kind);

	public List<ProductVO> listKindProduct3(String kind);*/
	
	public ProductVO product_detail(String pseq);
	
	public void cartInsert(CartVO cartVO);
	
	public List<CartVO> listCart(String id);
	
	public List<Integer> seqOrderIng(String id,String result);
	
	public List<OrderVO> listOrderById(String id,String result,int oseq );
	
	public void deleteCart(int cesq);	
	
	public int insertOrder(List<CartVO> cartList, String id);
	
	public int listKindProductCount(String kind);
			
	

	
}
