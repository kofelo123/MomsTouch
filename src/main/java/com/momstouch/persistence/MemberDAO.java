package com.momstouch.persistence;

import java.util.List;

import com.momstouch.domain.MemberVO;
import com.momstouch.domain.QnaVO;

public interface MemberDAO {
	
	public MemberVO login(MemberVO vo);
	
	public List<QnaVO> listQna(String id);
	
	public QnaVO getQna(int qesq);
	
	public void insertQna(QnaVO qvo,String id);
}
