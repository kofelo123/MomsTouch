package com.momstouch.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.momstouch.domain.AddressVO;
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

	@Override
	public int confirmId(String id) {
		return dao.confirmId(id);
	}

	@Override
	public List<AddressVO> selectAddressByDong(String dong) {
		return dao.selectAddressByDong(dong);
	}

	@Override
	public void joinPost(MemberVO vo) {
		// TODO Auto-generated method stub
		dao.joinPost(vo);
	}

}
