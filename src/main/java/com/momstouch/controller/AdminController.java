package com.momstouch.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.momstouch.domain.PageMaker;
import com.momstouch.domain.ProductVO;
import com.momstouch.domain.QnaVO;
import com.momstouch.domain.SearchCriteria;
import com.momstouch.service.AdminService;
import com.momstouch.web.MediaUtils;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	private AdminService service;
	
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	
	@RequestMapping(value = "admin_login_form" , method = RequestMethod.GET)
	public String admin_login_form(Model model){
		return "/admin/main";
	}
	@RequestMapping(value = "admin_login" , method = RequestMethod.POST)
	public String admin_login(Model model,@RequestParam("workerId")String workerId,@RequestParam("workerPwd")String workerPwd,HttpServletRequest request){
		
		int result = service.workCheck(workerId,workerPwd);
		if(result == 0)
			return "redirect:/admin/admin_login_form";
		
		HttpSession session = request.getSession();
		session.setAttribute("workerId", workerId); //어디서 인지 모르겠지만, 세션이 없으면 튕기도로고 설계되어있는데 그부분이 어디일지..
		return "redirect:/admin/admin_product_list";
	}
	@RequestMapping(value = "admin_logout" , method = RequestMethod.GET)
	public String admin_logout(Model model){
		return "redirect:/admin/admin_login_form";
	}
/*	@RequestMapping(value = "admin_product_list" , method = RequestMethod.GET)
	public String admin_product_list(Model model,@RequestParam(value="key",required=false)String key,@RequestParam(value="tpage",required=false)String tpage){
		
		if(key==null){
			key="";
		}
		if(tpage==null){
			tpage="1";
		}else if(tpage.equals("")){
			tpage="1";
		}
		model.addAttribute("key",key);
		model.addAttribute("tpage",tpage);
		
		service.listProduct(Integer.parseInt(tpage),key);
		service.pageNumber(Integer.parseInt(tpage),key);
		
		return "/admin/product/productList";
	}*/
	@RequestMapping(value = "admin_product_list" , method = RequestMethod.GET)
	public String admin_product_list(Model model,@ModelAttribute("cri") SearchCriteria cri) throws Exception{
//		System.out.println("criTest:"+cri);
		model.addAttribute("list", service.productList(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMaker",pageMaker); //paging을 위한.
		
		return "/admin/product/productList";
	}
	@RequestMapping(value = "product_write_form" , method = RequestMethod.GET)
	public String admin_product_write_form(Model model){

		 String kindList[] = { "치킨 메뉴", "씨푸드 메뉴", "버거 메뉴", "사이드 메뉴",
			        "음료류"};    
		 model.addAttribute("kindList",kindList);
		 
		return "/admin/product/productWrite";
	}
	@RequestMapping(value = "product_write" , method = RequestMethod.POST) 
	public String admin_product_write(Model model,ProductVO vo,MultipartFile file,HttpServletRequest request) throws IOException{
		
//		System.out.println("Testvo:"+vo); autobinding이 여기서 post로 왜 안되는지 모르겠다. 이 프로젝트만 그렇다. ->아마 multipart가 스프링용이 아니라서 그랫을지도.. jsp에서만 되는..
		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType: " + file.getContentType());
		
		String kind=vo.getKind();
		System.out.println("kindTest:"+vo.getKind());
		String savedName = service.uploadFile(file.getOriginalFilename(),file.getBytes(),uploadPath,request,kind);
		
		//(html form에서 image는 따로 input이 없어서 , file로 들어온것으로부터의 이름을 다시 넣어줘야한다.)
		vo.setImage(savedName);
		
		service.insertInfo(vo);
		
		//2가지 작업 (업로드,DB저장)
		/*service.uploadFile(request);
		
		service.insertInfo(vo);
		*/
		return "redirect:/admin/admin_product_list";
	}
	@RequestMapping(value = "admin_product_detail" , method = RequestMethod.GET)
	public String admin_product_detail(Model model,@RequestParam("page")String page,@RequestParam("pseq")String pseq) throws Exception{
		model.addAttribute(service.getProduct(pseq));
		String fileName = (service.getProduct(pseq)).getImage();
		String kind = (service.getProduct(pseq).getKind());
		String uploadPath2="";//uploadPath+=/menu 이런식으로 했을때 여러번 사용시 중첩되는문제떄문에 생성
		InputStream in = null;
		
		if(kind.equals("치킨 메뉴")){
			uploadPath2=uploadPath+"/menu_1";
		}else if(kind.equals("씨푸드 메뉴")){
			uploadPath2=uploadPath+"/menu_2";
		}else if(kind.equals("버거 메뉴")){
			uploadPath2=uploadPath+"/menu_3";
		}else if(kind.equals("사이드 메뉴")){
			uploadPath2=uploadPath+"/menu_4";
		}else if(kind.equals("음료류")){
			uploadPath2=uploadPath+"/menu_5";
		}
		
		/*	(switch문은 primitive형만 가능해서 String을 쓸 수 없다.)
		
			*/
		System.out.println("test:"+uploadPath2);
		model.addAttribute("subfolder",uploadPath2.substring(uploadPath2.lastIndexOf('/')));
		
		try{
		    String formatName = fileName.substring(fileName.lastIndexOf(".")+1);

		    MediaType mType = MediaUtils.getMediaType(formatName); //JPG,PNG,GIF

		    HttpHeaders headers = new HttpHeaders();

		    in = new FileInputStream(uploadPath2+"/"+fileName);
		    
		    if(mType != null) //Image
		      headers.setContentType(mType);
		    
		    
		    model.addAttribute("fileName",fileName);
		   
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			in.close();
		}
		
		model.addAttribute("page",page);
		
		return "/admin/product/productDetail";
		
	}
	@RequestMapping(value = "admin_product_update_form" , method = RequestMethod.GET)
	public String admin_product_update_form(Model model,@RequestParam("page")String page,@RequestParam("pseq")String pseq){
		
		model.addAttribute("productVO",service.getProduct(pseq)); 
		model.addAttribute("page",page);
		String kindList[] = { "치킨 메뉴","씨푸드 메뉴","버거 메뉴","사이드 메뉴","음료류" };
		model.addAttribute("kindList",kindList);
		
		String kind = (service.getProduct(pseq).getKind());
		String subfolder="";
		if(kind.equals("치킨 메뉴")){
			subfolder="/menu_1";
		}else if(kind.equals("씨푸드 메뉴")){
			subfolder="/menu_2";
		}else if(kind.equals("버거 메뉴")){
			subfolder="/menu_3";
		}else if(kind.equals("사이드 메뉴")){
			subfolder="/menu_4";
		}else if(kind.equals("음료류")){
			subfolder="/menu_5";
		}
			
		model.addAttribute("subfolder",subfolder);
		
		
		return "/admin/product/productUpdate";
	}
	@RequestMapping(value = "admin_product_update" , method = RequestMethod.POST)
	public String admin_product_update(Model model,ProductVO vo,MultipartFile file,HttpServletRequest request) throws IOException{
			//requestParam 다시해볼것..
		
		if(!file.isEmpty()){
			String kind=vo.getKind();
			//좀더 간단하게 file,request 혹은 file,vo,upload만 넘겨서 service에서 file.~ 해서 써도되지만 일단 시행착오로 위에서 만들때 만든거라 그냥쓴다.(write메소드 쓸떄 만든 로직)
			service.uploadFile(file.getOriginalFilename(),file.getBytes(), uploadPath, request, kind);
			vo.setImage(file.getOriginalFilename());//파일명.jpg
		}
		
		ProductVO vo2 = service.getProduct(Integer.toString(vo.getPseq()));
		
		vo.setImage(vo2.getImage());
		System.out.println("voTest:"+vo);
		
		service.updateInfo(vo);
		//update하는 문 만들것.
		
		return "redirect:/admin/admin_product_list";
	}
	
	//key 파라미터는 쿼리에서 mname(주문자이름 검색)에 쓰인다.
	@RequestMapping(value = "admin_order_list" , method = RequestMethod.GET)
	public String admin_order_list(Model model,@RequestParam(value="key",required=false)String key){
		
		String key2="";
		if(key != null)
			key2=key;
		
		model.addAttribute("orderList",service.listOrder(key2));
		
		return "/admin/orderList";
	}
	@RequestMapping(value = "admin_order_save" , method = RequestMethod.POST)
	public String admin_order_save(Model model,@RequestParam("result")String[] resultArr){
		for(String oseq:resultArr){
			service.updateOrderResult(oseq);
		}
		
		return "redirect:/admin/admin_order_list";
	}
	@RequestMapping(value = "admin_member_list" , method = RequestMethod.GET)
	public String admin_member_list(Model model,@RequestParam(value="key",required=false)String key){

		String key2="";
		if(key != null)
			key2=key;
		
		model.addAttribute("memberList",service.listMember(key2));
		
		return "/admin/memberList";
	}
	@RequestMapping(value = "admin_qna_list" , method = RequestMethod.GET)
	public String admin_qna_list(Model model){
		
		model.addAttribute("qnaList",service.listAllQna());
		
		return "/admin/qnaList";
	}
	@RequestMapping(value = "admin_qna_list" , method = RequestMethod.POST)
	public String admin_qna_list2(Model model){
		
		model.addAttribute("qnaList",service.listAllQna());
		
		return "/admin/qnaList";
	}
	@RequestMapping(value = "admin_qna_detail" , method = RequestMethod.POST)
	public String admin_qna_detail(Model model,@RequestParam("qseq")String qseq){
		
		model.addAttribute("qnaVO",service.getQna(Integer.parseInt(qseq)));
		
		return "/admin/qnaDetail";
	}
	@RequestMapping(value = "admin_qna_repsave" , method = RequestMethod.POST)
	public String admin_qna_repsave(Model model,@RequestParam("qseq")String qseq,@RequestParam("reply")String reply){
		
		QnaVO qnaVO = new QnaVO();
		qnaVO.setQseq(Integer.parseInt(qseq));
		qnaVO.setReply(reply);
		service.updateQna(qnaVO);
		return "redirect:/admin/admin_qna_list";
	}
	
	@RequestMapping(value = "userBan", method= RequestMethod.POST)
	public String userBan(Model model,@RequestParam("useyn")String[] useyn){
		
		for(String userBan:useyn){
			service.userBan(userBan);
		}
		
		return "redirect:/admin/admin_member_list";
	}
	
}

