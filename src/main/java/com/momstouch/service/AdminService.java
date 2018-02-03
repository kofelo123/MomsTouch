package com.momstouch.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;

import com.momstouch.domain.MemberVO;
import com.momstouch.domain.OrderVO;
import com.momstouch.domain.ProductVO;
import com.momstouch.domain.QnaVO;
import com.momstouch.domain.SearchCriteria;

public interface AdminService {
	public int workCheck(String id,String pw);
	
	public List<ProductVO> listProduct(int tpage, String key); 

	public String pageNumber(int tpage, String key); 
	
	public List<OrderVO>listOrder(String key);
	
	public List<MemberVO>listMember(String key);
	
	public List<QnaVO> listAllQna();
	
	public QnaVO getQna(int qseq);
	
	public void updateQna(QnaVO qnaVo);
	
	public void updateOrderResult(String oseq);
	
	public List<ProductVO> productList(SearchCriteria cri);
	
	public int listSearchCount(SearchCriteria cri)throws Exception;
	
	public String uploadFile(String originalName, byte[] fileData,String uploadPath,HttpServletRequest request,String kind) throws IOException;
	
	public void insertInfo(ProductVO vo);
	
	public ProductVO getProduct(String pseq);
	
	public void updateInfo(ProductVO vo);
	
	public void userBan(String userBan);
	
	
}
