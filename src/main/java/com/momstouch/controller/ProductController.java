package com.momstouch.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

		model.addAttribute("productKindList", service.listKindProduct2(kind));//2로 수정할것
		
		return "/product/productKind2";

	}

	@RequestMapping(value = "category3", method = RequestMethod.GET)
	public String category3(Model model,@RequestParam("kind") String kind) {
		
		model.addAttribute("productKindList", service.listKindProduct3(kind));//2로 수정할것

		return "/product/productKind3";

	}
	
	@RequestMapping(value = "cart_insert", method = RequestMethod.GET)
	public String cart_insert(Model model){
		
		return "redirect:/product/cart_list";
	}

	@RequestMapping(value = "cart_list" , method = RequestMethod.GET)
	public String cart_list(Model model){
		return "/mypage/cartList";
	}

	@RequestMapping(value = "cart_delete" , method = RequestMethod.GET)
	public String cart_delete(Model model){
		return "redirect:/product/cart_list";
	}
	@RequestMapping(value = "order_insert" , method = RequestMethod.GET)
	public String order_insert(Model model){
		return "redirect:/product/order_insert";
	}
	@RequestMapping(value = "order_list" , method = RequestMethod.GET)
	public String order_list(Model model){
		return "/mypage/orderList";
	}
	@RequestMapping(value = "mypage" , method = RequestMethod.GET)
	public String mypage(Model model){
		return "/mypage/mypage" ;
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
