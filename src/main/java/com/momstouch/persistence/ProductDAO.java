package com.momstouch.persistence;

import java.util.ArrayList;
import java.util.List;

import com.momstouch.domain.CartVO;
import com.momstouch.domain.OrderVO;
import com.momstouch.domain.ProductVO;

public interface ProductDAO {

	public List<ProductVO> listNewProduct();

	public List<ProductVO> listBestProduct();
	
	public List<ProductVO> listKindProduct(String kind);

	public List<ProductVO> listKindProduct2(String kind);
	
	public List<ProductVO> listKindProduct3(String kind);
	
	public ProductVO product_detail(String pseq);
	
	public void cartInsert(CartVO cartVO);
	
	public List<CartVO> listCart(String id);
	
	public List<Integer> seqOrderIng(String id);
	
	public List<OrderVO> listOrderById(String id,String result , int oseq);
}
