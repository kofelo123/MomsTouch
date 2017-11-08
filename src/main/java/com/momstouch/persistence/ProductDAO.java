package com.momstouch.persistence;

import java.util.List;

import com.momstouch.domain.ProductVO;

public interface ProductDAO {

	public List<ProductVO> listNewProduct();

	public List<ProductVO> listBestProduct();
}
