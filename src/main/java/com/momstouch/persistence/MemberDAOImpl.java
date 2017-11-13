package com.momstouch.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.momstouch.domain.MemberVO;
import com.momstouch.domain.QnaVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession session;

	private static String namespace="com.momstouch.mapper.Member";
	
	@Override
	public MemberVO login(MemberVO vo) {
		return session.selectOne(namespace + ".login",vo);
	}

	@Override
	public List<QnaVO> listQna(String id) {
		return session.selectList(namespace + ".listQna", id);
	}

	@Override
	public QnaVO getQna(int qseq) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".getQna",qseq);
	}

	@Override
	public void insertQna(QnaVO qvo, String id) {
		
		Map<String,Object> paramMap = new HashMap<String,Object>(); 
		paramMap.put("qvo", qvo);
		paramMap.put("id", id);
		session.insert(namespace + ".insertQna",paramMap);
	}
	
	
}
