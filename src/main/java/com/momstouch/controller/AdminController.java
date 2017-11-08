package com.momstouch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value = "admin_login_form" , method = RequestMethod.GET)
	public String admin_login_form(Model model){
		return "/admin/main";
	}
	@RequestMapping(value = "admin_login" , method = RequestMethod.GET)
	public String admin_login(Model model){
		return "redirect:/Admin/admin_login_form";
	}
	@RequestMapping(value = "admin_logout" , method = RequestMethod.GET)
	public String admin_logout(Model model){
		return "redirect:/Admin/admin_login_form";
	}
	@RequestMapping(value = "admin_product_list" , method = RequestMethod.GET)
	public String admin_product_list(Model model){
		return "/admin/product/productList";
	}
	@RequestMapping(value = "admin_product_write_form" , method = RequestMethod.GET)
	public String admin_product_write_form(Model model){
		return "/admin/product/productWrite";
	}
	@RequestMapping(value = "admin_product_write" , method = RequestMethod.GET)
	public String admin_product_write(Model model){
		return "redirect:/Admin/admin_product_list";
	}
	@RequestMapping(value = "admin_product_detail" , method = RequestMethod.GET)
	public String admin_product_detail(Model model){
		return "/admin/product/productDetail";
	}
	@RequestMapping(value = "admin_product_update_form" , method = RequestMethod.GET)
	public String admin_product_update_form(Model model){
		return "/admin/product/productUpdate";
	}
	@RequestMapping(value = "admin_product_update" , method = RequestMethod.GET)
	public String admin_product_update(Model model){
		return "redirect:/Admin/admin_product_list";
	}
	@RequestMapping(value = "admin_order_list" , method = RequestMethod.GET)
	public String admin_order_list(Model model){
		return "/admin/order/orderList";
	}
	@RequestMapping(value = "admin_order_save" , method = RequestMethod.GET)
	public String admin_order_save(Model model){
		return "redirect:/Admin/admin_order_list";
	}
	@RequestMapping(value = "admin_member_list" , method = RequestMethod.GET)
	public String admin_member_list(Model model){
		return "/admin/member/memberList";
	}
	@RequestMapping(value = "admin_qna_list" , method = RequestMethod.GET)
	public String admin_qna_list(Model model){
		return "/admin/qna/qnaList";
	}
	@RequestMapping(value = "admin_qna_detail" , method = RequestMethod.GET)
	public String admin_qna_detail(Model model){
		return "/admin/qna/qnaDetail";
	}
	@RequestMapping(value = "admin_qna_repsave" , method = RequestMethod.GET)
	public String admin_qna_repsave(Model model){
		return "redirect:/Admin/admin_product_list";
	}
}
