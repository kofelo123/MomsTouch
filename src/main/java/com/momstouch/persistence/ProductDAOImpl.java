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

	/*public List<ProductVO> listNewProduct(){
		return session.selectList(statement)
	}*/
}
