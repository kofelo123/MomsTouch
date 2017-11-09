package com.momstouch.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.momstouch.domain.ProductVO;
import com.momstouch.persistence.ProductDAO;
import com.momstouch.persistence.ProductDAOImpl;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Inject
	private ProductDAO dao;
	
	@Override
	public List<ProductVO> listNewProduct() {
		return dao.listNewProduct();
	}

	@Override
	public List<ProductVO> listBestProduct() {
		return dao.listBestProduct();
	}

	@Override
	public List<ProductVO> listKindProduct(String kind) {
		return dao.listKindProduct(kind);
	}

	@Override
	public List<ProductVO> listKindProduct2(String kind) {
		return dao.listKindProduct2(kind);
	}

	@Override
	public List<ProductVO> listKindProduct3(String kind) {
		return dao.listKindProduct3(kind);
	}

	@Override
	public ProductVO product_detail(String pseq) {
		// TODO Auto-generated method stub
		return dao.product_detail(pseq);
	}

}
