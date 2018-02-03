package com.momstouch.persistence;

import java.util.List;

import com.momstouch.domain.AddressVO;
import com.momstouch.domain.MemberVO;
import com.momstouch.domain.QnaVO;

public interface MemberDAO {
	
	public MemberVO login(MemberVO vo);
	
	public List<QnaVO> listQna(String id);
	
	public QnaVO getQna(int qesq);
	
	public void insertQna(QnaVO qvo,String id);
	
	public int confirmId(String id);
	
	public List<AddressVO> selectAddressByDong(String dong);
	
	public void joinPost(MemberVO vo);
	
}
