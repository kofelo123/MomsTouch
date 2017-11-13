package com.momstouch.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.momstouch.domain.CartVO;
import com.momstouch.domain.MemberVO;
import com.momstouch.domain.OrderVO;
import com.momstouch.service.ProductService;
import com.momstouch.web.HomeController;


@Controller
@RequestMapping("/product/*")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private ProductService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "index";
	}

	@RequestMapping(value = "product_detail", method = RequestMethod.GET)
	public String productDetail(Model model,@RequestParam("pseq")String pseq) {
		
		model.addAttribute("productVO",service.product_detail(pseq));
		
		return "/product/productDetail";
	}

	@RequestMapping(value = "category", method = RequestMethod.GET)
	public String category(Model model,@RequestParam("kind") String kind) {
		
		model.addAttribute("productKindList", service.listKindProduct(kind));
		
		return "/product/productKind";
	}

	@RequestMapping(value = "category2", method = RequestMethod.GET)
	public String category2(Model model,@RequestParam("kind") String kind) {

		model.addAttribute("productKindList", service.listKindProduct2(kind));
		
		return "/product/productKind2";

	}

	@RequestMapping(value = "category3", method = RequestMethod.GET)
	public String category3(Model model,@RequestParam("kind") String kind) {
		
		model.addAttribute("productKindList", service.listKindProduct3(kind));//2로 수정할것

		return "/product/productKind3";

	}
	
	@RequestMapping(value = "cart_insert", method = RequestMethod.POST)
	public String cart_insert(Model model , HttpServletRequest request,@RequestParam("quantity") String quantity,@RequestParam("pseq") String pseq){
		//session으로 로그인여부 검사 - x - 로그인페이지  o - 장바구니넣기
		
		HttpSession session = request.getSession();
	    MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	    if(loginUser == null)
	    	return "redirect:/member/login_form";
	    
	    CartVO cartVO = new CartVO();
	    cartVO.setQuantity(Integer.parseInt(quantity)); 
	    cartVO.setPseq(Integer.parseInt(pseq));
	    cartVO.setId(loginUser.getId());
	    
	    service.cartInsert(cartVO);
//	    service.cartInsert();
	    	
		return "redirect:/product/cart_list";
	}

	@RequestMapping(value = "cart_list" , method = RequestMethod.GET)
	public String cart_list(Model model,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if(loginUser == null)
			return "redirect:/member/login_form";
		
		List<CartVO> cartList = service.listCart(loginUser.getId()); 
		
		int totalPrice = 0;
		for(CartVO cartVO  : cartList){
			totalPrice += cartVO.getPrice2() * cartVO.getQuantity();
			
		model.addAttribute("cartList", cartList);
		model.addAttribute("totalPrice", totalPrice);
	}
		
		return "/mypage/cartList";
	}

	@RequestMapping(value = "cart_delete" , method = RequestMethod.GET)
	public String cart_delete(Model model){
		return "redirect:/product/cart_list";
	}
	@RequestMapping(value = "order_insert" , method = RequestMethod.GET)
	public String order_insert(Model model){
		
		return "redirect:/product/mypage";
	}
	@RequestMapping(value = "order_list" , method = RequestMethod.GET)
	public String order_list(Model model){
		return "/mypage/orderList";
	}
	@RequestMapping(value = "mypage" , method = RequestMethod.POST)
	public String mypage(Model model,HttpServletRequest request){
		
		 HttpSession session = request.getSession();
		 MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		 if(loginUser == null)
				return "redirect:/member/login_form";
		 
		List<Integer> oseqList = service.seqOrderIng(loginUser.getId());
		 
		for(int oseq : oseqList){
			List<OrderVO> orderListIng = service.listOrderById(loginUser.getId(),"1",oseq);
		}
			
		return "/mypage/mypage";
	}
	
	@RequestMapping(value = "mypage" , method = RequestMethod.GET)
	public String mypage2(Model model,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null)
			return "redirect:/member/login_form";
		
		List<Integer> oseqList = service.seqOrderIng(loginUser.getId());
		
		for(int oseq : oseqList){
			List<OrderVO> orderListIng = service.listOrderById(loginUser.getId(),"1",oseq);
		}
		
		return "/mypage/mypage";
	}
	
	
	@RequestMapping(value = "order_detail" , method = RequestMethod.GET)
	public String order_detail(Model model){
		return "/mypage/orderDetail";
	}
	@RequestMapping(value = "order_all" , method = RequestMethod.GET)
	public String order_all(Model model){
		return "/mypage/mypage";
	}

	
}
