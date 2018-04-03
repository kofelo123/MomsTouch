package com.momstouch.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.momstouch.domain.CartVO;
import com.momstouch.domain.Criteria;
import com.momstouch.domain.MemberVO;
import com.momstouch.domain.OrderVO;
import com.momstouch.domain.PageMaker;
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
		
		String kind = service.product_detail(pseq).getKind();
		String uploadPath2="";
		if(kind.equals("치킨 메뉴")){
			uploadPath2="/menu_1";
		}else if(kind.equals("씨푸드 메뉴")){
			uploadPath2="/menu_2";
		}else if(kind.equals("버거 메뉴")){
			uploadPath2="/menu_3";
		}else if(kind.equals("사이드 메뉴")){
			uploadPath2="/menu_4";
		}else if(kind.equals("음료류")){
			uploadPath2="/menu_5";
		}
		
		model.addAttribute("subfolder",uploadPath2);
		
		return "/product/productDetail";
	}

	@RequestMapping(value="category", method=RequestMethod.GET)
	public String category(Model model,@RequestParam("kind")String kind,@ModelAttribute("cri")Criteria cri){
		cri.setPerPageNum(9);
		System.out.println("criTest:"+cri);
		model.addAttribute("productKindList", service.listKindProduct(kind,cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		pageMaker.setTotalCount(service.listKindProductCount(kind));
		
		model.addAttribute("pageMaker",pageMaker);
		
		return "/product/productKind";
	}
	
	
	@RequestMapping(value = "cart_insert", method = RequestMethod.POST)//그냥 파라미터에 CartVO로 받았어도 됬지 않았나 싶다.
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

	@RequestMapping(value = "cart_delete" , method = RequestMethod.POST)
	public String cart_delete(Model model,@RequestParam("cseq")String[] cseq){
		
		for(String cseq2:cseq){
			service.deleteCart(Integer.parseInt(cseq2));
		}
		
		return "redirect:/product/cart_list";
	}
	@RequestMapping(value = "order_insert" , method = RequestMethod.POST)
	public String order_insert(Model model,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		 MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		 if(loginUser == null)
				return "redirect:/member/login_form";
		
		 List<CartVO> cartList =  service.listCart(loginUser.getId()); //카트에 있던것들을 가져와서
		 
		 int maxOseq= service.insertOrder(cartList, loginUser.getId()); //주문에 카트를 추가하는과정
		
		return "redirect:/product/order_list?oseq="+maxOseq;
	}
	@RequestMapping(value="order_instant", method=RequestMethod.POST)
	public String order_instant(Model model,HttpServletRequest request,CartVO vo){
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null)
			return "redirect:/member/login_form";
		//기존의 order_insert로직을 재활용하기 위해서  CartList로 해서 넣은것. 원래는 OderVO하나만 있으면 되겠지만 일부러 CartVO로 만들어서 하나만 리스트에 넣음
		List<CartVO> cartList = new ArrayList<CartVO>();
		cartList.add(vo);
		
		int maxOseq = service.insertOrder(cartList, loginUser.getId());
		
		
		return "redirect:/product/order_list?oseq="+maxOseq;
		
		
	}
	
	@RequestMapping(value = "order_list" , method = RequestMethod.GET)//주문하기->지금 처리되는 주문에 대한 정보표기 주문직후에만 보여지는 페이지인 orderlist.jsp와 연결됨 
	public String order_list(Model model,HttpServletRequest request,@RequestParam("oseq")String oseq){
		
		 HttpSession session = request.getSession();
		 MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		 if(loginUser == null)
				return "redirect:/member/login_form";
		 
		 int oseq2 = Integer.parseInt(oseq);
		 List<OrderVO> orderList = service.listOrderById(loginUser.getId(), "1", oseq2);
		
		 int totalPrice = 0 ;
		 for(OrderVO orderVO : orderList){
			 totalPrice +=orderVO.getPrice2()*orderVO.getQuantity();
		 }
		 model.addAttribute("orderList",orderList);
		 model.addAttribute("totalPrice",totalPrice);
		 
		return "/mypage/orderList";
	}

	@RequestMapping(value = "mypage" , method = RequestMethod.GET)//조금 복잡한 로직이다. //mypage.jsp에 주문리스트를 뿌리기위한 정보를 가져오는 로직.
	public String mypage2(Model model,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		 MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		 if(loginUser == null)
				return "redirect:/member/login_form";
		 
		 
		List<Integer> oseqList = service.seqOrderIng(loginUser.getId(),"1");//로그인유저의 주문번호(서버에서 미처리된(result=1))를 중복되지않게 리스트로 가져옴
		 
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
		
		for(int oseq : oseqList){//위에서 가져온 주문번호 리스트 값을 하나씩 뽑아서 
			List<OrderVO> orderListIng = service.listOrderById(loginUser.getId(),"1",oseq);//주문번호(한번의 주문에 여러상품 들어있음)에 들어있는 각 상품을 리스트로 받음
			OrderVO orderVO = orderListIng.get(0);//첫번째놈을 꺼내서 정보표기
			if(orderListIng.size()==1){
				orderVO.setPname(orderVO.getPname());
			}else{
				orderVO.setPname(orderVO.getPname()+"외" + (orderListIng.size()-1) + "건");
			}
			int totalPrice = 0;
			for(OrderVO ovo : orderListIng){
				totalPrice += ovo.getPrice2() * ovo.getQuantity();//하나의 주문번호안의 각 주문객체들의 가격을 total가격에 더함 (mypage에서 보여주기위한것)
			}
			orderVO.setPrice2(totalPrice);
			orderList.add(orderVO);
		}
			model.addAttribute("title","진행 중인 주문 내역");
			model.addAttribute("orderList",orderList);
		
			return "/mypage/mypage";
	}
	
	
	@RequestMapping(value = "order_detail" , method = RequestMethod.GET)
	public String order_detail(Model model,HttpServletRequest request,@RequestParam(value="oseq",required=false)String oseq){
		
		HttpSession session = request.getSession();
		 MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		 if(loginUser == null)
				return "redirect:/member/login_form";
		int oseq2=Integer.parseInt(oseq);
		List<OrderVO>  orderList = service.listOrderById(loginUser.getId(), "%", oseq2);
		
		int totalPrice=0;
		for(OrderVO ovo : orderList){
			totalPrice += ovo.getPrice2()*ovo.getQuantity();
		}
		model.addAttribute("orderDetail",orderList.get(0));
		model.addAttribute("orderList", orderList);
		model.addAttribute("totalPrice",totalPrice);
		
		return "/mypage/orderDetail";
	}
	@RequestMapping(value = "order_all" , method = RequestMethod.GET) //mypage 메소드와 사실 같다(주문처리 전+후 모두출력한다는 것빼고(result) 로직이 같다
	public String order_all(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		 MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		 if(loginUser == null)
				return "redirect:/member/login_form";
		 
		List<Integer> oseqList = service.seqOrderIng(loginUser.getId(),"%");
		 
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
		
		for(int oseq : oseqList){
			List<OrderVO> orderListIng = service.listOrderById(loginUser.getId(),"%",oseq);
			OrderVO orderVO = orderListIng.get(0);
			if(orderListIng.size()==1){
				orderVO.setPname(orderVO.getPname());
			}else{
				orderVO.setPname(orderVO.getPname()+"외" + (orderListIng.size()-1) + "건");
			}
			int totalPrice = 0;
			for(OrderVO ovo : orderListIng){
				totalPrice += ovo.getPrice2() * ovo.getQuantity();
			}
			orderVO.setPrice2(totalPrice);
			orderList.add(orderVO);
		}
			model.addAttribute("title","총 주문 내역");
			model.addAttribute("orderList",orderList);
		
			return "/mypage/mypage";
	}

}
