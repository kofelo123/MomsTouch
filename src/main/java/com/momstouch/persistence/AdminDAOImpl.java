package com.momstouch.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.momstouch.domain.MemberVO;
import com.momstouch.domain.OrderVO;
import com.momstouch.domain.ProductVO;
import com.momstouch.domain.QnaVO;
import com.momstouch.domain.SearchCriteria;

@Repository
public class AdminDAOImpl implements AdminDAO{
	@Inject
	private SqlSession session;
	
	
	
	private static String namespace="com.momstouch.mapper.Admin"; 
	
	@Override
	public int workCheck(String id, String pw) {
		
		Map<String, Object> paramMap = new HashMap<String ,Object>();
		paramMap.put("id", id);
		paramMap.put("pw", pw);
		String pwd = session.selectOne(namespace + ".workCheck",paramMap);
		
		if(pw.equals(pwd))
			return 1;
		return 0;
	}

	@Override
	public List<ProductVO> listProduct(int tpage, String key) {
		
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("tpage", tpage);
		paramMap.put("key", key);
		
		return session.selectList(namespace + ".listProduct",paramMap);
	}

	@Override
	public String pageNumber(int tpage, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderVO> listOrder(String key) {
		if(key == "")
			key="%";
		return session.selectList(namespace + ".listOrder",key);
	}

	@Override
	public List<MemberVO> listMember(String key) {
		if(key == "")
			key="%";
		return session.selectList(namespace + ".listMember",key);
	}

	@Override
	public List<QnaVO> listAllQna() {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".listAllQna");
	}

	@Override
	public QnaVO getQna(int qseq) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".getQna",qseq);
	}

	@Override
	public void updateQna(QnaVO qnaVo) {
		
		session.update(namespace + ".updateQna",qnaVo);
	}

	@Override
	public void updateOrderResult(String oseq) {
		session.update(namespace + ".updateOrderResult",oseq);
	}

	@Override
	public List<ProductVO> productList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		//분기문처리  - 굳이 안해도되는거같다. parameter에 %가 붙어버려서 뺀다.
		///파라미터에 keyword= 기본으로 해줘야 에러가 안떴다.-> 왜냐하면 mapper에서 keyword가 필요한데
		/// "" 빈공백은 괜찮지만, null이 되면 에러가 되기 때문, 그래서 아래 로직을 뒀다.
		if(cri.getKeyword()==null){
			cri.setKeyword("");
		}
//		System.out.println("keywordTest:"+cri.getKeyword());
		return session.selectList(namespace + ".productList",cri);
		
	}

	@Override
	public int listSearchCount(SearchCriteria cri) {

		return session.selectOne(namespace + ".listSearchCount",cri);
	}

	@Override
	public void insertInfo(ProductVO vo) {
		
		session.insert(namespace + ".insertInfo",vo);
		
	}

	@Override
	public ProductVO getProduct(String pseq) {
		return session.selectOne(namespace + ".getProduct",pseq);
	}

	@Override
	public void updateInfo(ProductVO vo) {
		session.update(namespace + ".updateInfo",vo);
	}

	@Override
	public void userBan(String userBan) {
		session.update(namespace + ".userBan", userBan);
	}

	
}
