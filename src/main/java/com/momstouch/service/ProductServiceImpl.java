package com.momstouch.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.momstouch.domain.CartVO;
import com.momstouch.domain.OrderVO;
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

	@Override
	public void cartInsert(CartVO cartVO) {
		dao.cartInsert(cartVO);
	}

	@Override
	public List<CartVO> listCart(String id) {
		// TODO Auto-generated method stub
		return dao.listCart(id);
	}

	@Override
	public List<Integer> seqOrderIng(String id) {
		return dao.seqOrderIng(id);
	}

	@Override
	public List<OrderVO> listOrderById(String id, String result, int oseq) {
		// TODO Auto-generated method stub
		return dao.listOrderById(id,result,oseq);
	}

}
