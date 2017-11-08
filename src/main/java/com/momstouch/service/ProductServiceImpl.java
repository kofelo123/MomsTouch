package com.momstouch.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.momstouch.domain.ProductVO;
import com.momstouch.persistence.ProductDAOImpl;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Inject
	private ProductDAOImpl dao;
	
	@Override
	public List<ProductVO> listNewProduct() {
		return dao.listNewProduct();
	}

	@Override
	public List<ProductVO> listBestProduct() {
		return dao.listBestProduct();
	}

}
