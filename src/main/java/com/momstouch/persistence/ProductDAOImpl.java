package com.momstouch.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.momstouch.domain.CartVO;
import com.momstouch.domain.Criteria;
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
	public List<ProductVO> listKindProduct(String kind,Criteria cri) {
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("kind", kind);
		paramMap.put("cri", cri);
				
		return session.selectList(namespace + ".listKindProduct",paramMap);
	}

/*	@Override
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
*/
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
	public List<Integer> seqOrderIng(String id,String result) {
		
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", id);
		paramMap.put("result", result);
		
		return session.selectList(namespace + ".seqOrderIng", paramMap);
	}
	

	@Override
	public List<OrderVO> listOrderById(String id, String result, int oseq) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id",id);
		paramMap.put("result", result);
		paramMap.put("oseq", oseq);
		
		return session.selectList(namespace + ".listOrderById", paramMap) ;
	}

	@Override
	public void deleteCart(int cseq) {
		session.delete(namespace + ".deleteCart", cseq);  
	}


	@Override
	public int oseqMax() {
		
		return session.selectOne(namespace + ".oseqMax");
	}

	@Override
	public void insertOrder(String id) {
		session.insert(namespace + ".insertOrder",id);
	}

	@Override
	public void insertOrderDetail(CartVO cartVO, int maxOseq) {
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("cartVO", cartVO);
		paramMap.put("maxOseq", maxOseq);
		session.insert(namespace + ".insertOrderDetail",paramMap);
		
		session.update(namespace + ".updateCartResult",cartVO);//result값을 2로 처리(서버에서 주문처리) 언래 있던 로직인데 내가봤을떄 이거는 하면안된다.(서버주문처리는 따로 관리자쪽에서 해야..) -> 다시 주석해제한다, 착각한것인데 cart의 result는 카트처리이고 주문의 서버쪽 처리는 order_detail에서 result가 따로있다.
	}

	@Override
	public int listKindProductCount(String kind) {
		return session.selectOne(namespace + ".listKindProductCount",kind);
	}
	
	

}
