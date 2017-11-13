package com.momstouch.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.momstouch.domain.CartVO;
import com.momstouch.domain.OrderVO;
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

	@Override
	public void cartInsert(CartVO cartVO) {
		// TODO Auto-generated method stub
		session.insert(namespace + ".cartInsert", cartVO);
	}

	@Override
	public List<CartVO> listCart(String id) {
		
		return session.selectList(namespace +".listCart" , id);
	}

	@Override
	public List<Integer> seqOrderIng(String id) {
		return session.selectList(namespace + ".seqOrderIng", id);
	}

	@Override
	public List<OrderVO> listOrderById(String id, String result, int oseq) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id",id);
		paramMap.put("result", result);
		paramMap.put("oseq", oseq);
		
		return session.selectList(namespace + ".listOrderById", paramMap) ;
	}

}
