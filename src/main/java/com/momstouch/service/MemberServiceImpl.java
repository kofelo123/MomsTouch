package com.momstouch.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.momstouch.domain.MemberVO;
import com.momstouch.domain.QnaVO;
import com.momstouch.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{

	@Inject
	private MemberDAO dao;
	
	@Override
	public MemberVO login(MemberVO vo) {
		return dao.login(vo);
	}
	
	@Override
	public List<QnaVO> listQna(String id) {
		// TODO Auto-generated method stub
		return dao.listQna(id);
	}

	@Override
	public QnaVO getQna(int qseq) {
		return dao.getQna(qseq);
	}

	@Override
	public void insertQna(QnaVO qvo, String id) {
		dao.insertQna(qvo,id);
	}

}
