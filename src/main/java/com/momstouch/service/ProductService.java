package com.momstouch.service;

import java.util.List;

import com.momstouch.domain.ProductVO;

public interface ProductService {

	public List<ProductVO> listNewProduct();

	public List<ProductVO> listBestProduct();
}
