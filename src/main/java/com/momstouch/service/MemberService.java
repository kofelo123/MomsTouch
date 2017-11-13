package com.momstouch.service;

import java.util.List;

import com.momstouch.domain.MemberVO;
import com.momstouch.domain.QnaVO;

public interface MemberService {

	public MemberVO login(MemberVO vo);
	
	public List<QnaVO> listQna(String id);
	
	public QnaVO getQna(int qseq);
	
	public void insertQna(QnaVO qvo,String id);
}
