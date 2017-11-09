package com.momstouch.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.momstouch.domain.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO{
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.momstouch.mapper.ProductMapper";

	@Override
	public List<ProductVO> listNewProduct() {
		return session.selectList(namespace + ".listNewProduct");
	}

	@Override
	public List<ProductVO> listBestProduct() {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".listBestProduct");
	}

	@Override
	public List<ProductVO> listKindProduct(String kind) {
		return session.selectList(namespace + ".listKindProduct",kind);
	}

	@Override
	public List<ProductVO> listKindProduct2(String kind) {
		return session.selectList(namespace + ".listKindProduct2",kind);
	}

	@Override
	public List<ProductVO> listKindProduct3(String kind) {
		return session.selectList(namespace + ".listKindProduct3", kind);
	}

	@Override
	public ProductVO product_detail(String pseq) {
		return session.selectOne(namespace + ".product_detail",pseq);
	}

}
