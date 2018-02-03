package com.momstouch.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.momstouch.domain.CartVO;
import com.momstouch.domain.Criteria;
import com.momstouch.domain.OrderVO;
import com.momstouch.domain.ProductVO;
import com.momstouch.persistence.ProductDAO;

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
	public List<ProductVO> listKindProduct(String kind,Criteria cri) {
		return dao.listKindProduct(kind,cri);
	}
	/*@Override
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
	}*/

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
	public List<Integer> seqOrderIng(String id,String result) {
		return dao.seqOrderIng(id,result);
	}

	@Override
	public List<OrderVO> listOrderById(String id, String result, int oseq) {
		// TODO Auto-generated method stub
		return dao.listOrderById(id,result,oseq);
	}

	@Override
	public void deleteCart(int cseq) {
		dao.deleteCart(cseq);
	}


	@Override
	public int insertOrder(List<CartVO> cartList, String id) {
		int maxOseq = 0;
		//참고로 insertOrder와 maxOseq가 순서가 반대였는데 잘못된 로직이라고 생각되서,그리고 그로인해 사용자가 바뀔떄 첫주문이 실패하는(oders테이블과 oser_detail 테이블의 oseq가 하나씩 오차가 나는현상)것을 해결
		dao.insertOrder(id);//주문간단입력 (oseq(한번의 주문세트(카트안의 여러개에 대한))에 대한것을  넣어준다 미리)

		maxOseq = dao.oseqMax();/// 실제 주문의 oesq의 최대값 가져온다. 아래 for문안에서 detail에 넘기기위해서 가져옴

		for(CartVO cartVO : cartList){ //odseq (한번의 주문세트내에서의 각개의 식별하는게 odseq, 카트의 각 객체들을 하나씩 넣어준다)  
			dao.insertOrderDetail(cartVO,maxOseq);//(위에서 입력한 하나의 주문세트 에 대한 정보 + 각 카트의 객체를 하나씩 주문에 넣는과정)
		}
		return maxOseq;
	}

	@Override
	public int listKindProductCount(String kind) {

		return dao.listKindProductCount(kind);
	}


	
	

}
