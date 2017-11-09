package com.momstouch.persistence;

import java.util.List;

import com.momstouch.domain.ProductVO;

public interface ProductDAO {

	public List<ProductVO> listNewProduct();

	public List<ProductVO> listBestProduct();
	
	public List<ProductVO> listKindProduct(String kind);

	public List<ProductVO> listKindProduct2(String kind);
	
	public List<ProductVO> listKindProduct3(String kind);
	
	public ProductVO product_detail(String pseq);
}
