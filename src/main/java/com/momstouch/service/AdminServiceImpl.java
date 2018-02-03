package com.momstouch.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.momstouch.domain.MemberVO;
import com.momstouch.domain.OrderVO;
import com.momstouch.domain.ProductVO;
import com.momstouch.domain.QnaVO;
import com.momstouch.domain.SearchCriteria;
import com.momstouch.persistence.AdminDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service
public class AdminServiceImpl implements AdminService{

	
	@Inject
	private AdminDAO dao;

	@Override
	public int workCheck(String id, String pw) {
		// TODO Auto-generated method stub
		return dao.workCheck(id,pw);
	}

	@Override
	public List<ProductVO> listProduct(int tpage, String key) {
		// TODO Auto-generated method stub
		return dao.listProduct(tpage,key);
	}

	@Override
	public String pageNumber(int tpage, String key) {
		// TODO Auto-generated method stub
		return dao.pageNumber(tpage,key);
	}

	@Override
	public List<OrderVO> listOrder(String key) {
		return dao.listOrder(key);
	}

	@Override
	public List<MemberVO> listMember(String key) {
		// TODO Auto-generated method stub
		return dao.listMember(key);
	}

	@Override
	public List<QnaVO> listAllQna() {
		// TODO Auto-generated method stub
		return dao.listAllQna();
	}

	@Override
	public QnaVO getQna(int qseq) {
		// TODO Auto-generated method stub
		return dao.getQna(qseq);
	}

	@Override
	public void updateQna(QnaVO qnaVo) {
		// TODO Auto-generated method stub
		dao.updateQna(qnaVo);
	}

	@Override
	public void updateOrderResult(String oseq) {
		dao.updateOrderResult(oseq);
	}

	@Override
	public List<ProductVO> productList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return dao.productList(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchCount(cri);
	}

	/*@Override
	public void uploadFile(HttpServletRequest request) throws IOException {
		
		HttpSession session = request.getSession();
		
		int sizeLimit = 5 * 1024 * 1024;
		String savePath = "/resources/product_images";
		
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		//(cos.jar에 대한 dependency 넣고 쓰는것.)
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, sizeLimit,"UTF-8", new DefaultFileRenamePolicy() );
		
	}
*/
	@Override
	public void insertInfo(ProductVO vo) {
		dao.insertInfo(vo);
	}

	@Override
	public String uploadFile(String originalName, byte[] fileData,String uploadPath,HttpServletRequest request,String kind) throws IOException {

		//맘스터치 특성상 UUID 굳이 안쓰려고함.
//		UUID uid = UUID.randomUUID();
//		String savedName = uid.toString() + "_"+originalName;
		
		String savePath = "resources/image/momstouch";

		if(kind.equals("치킨 메뉴")){
			savePath+="/menu_1";
		}else if(kind.equals("씨푸드 메뉴")){
			savePath+="/menu_2";
		}else if(kind.equals("버거 메뉴")){
			savePath+="/menu_3";
		}else if(kind.equals("사이드 메뉴")){
			savePath+="/menu_4";
		}else if(kind.equals("음료류")){
			savePath+="/menu_5";
		}
		
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("테스트:"+uploadFilePath);
		
		File target = new File(uploadFilePath,originalName);
		
		FileCopyUtils.copy(fileData, target);
		
		return originalName;
	}

	@Override
	public ProductVO getProduct(String pseq) {
		return dao.getProduct(pseq); 
	}

	@Override
	public void updateInfo(ProductVO vo) {
		dao.updateInfo(vo);
		
	}

	@Override
	public void userBan(String userBan) {
		dao.userBan(userBan);
	}

	
	
	

}
