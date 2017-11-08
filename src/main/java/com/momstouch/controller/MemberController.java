package com.momstouch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value="/contract" , method=RequestMethod.GET)
	public String contract(Model model){
		
		return "/member/contract";
	}
	
	@RequestMapping(value = "/join_form" , method= RequestMethod.GET)
	public String join_form(Model model){
		
		return "/member/join";
	}
	
	@RequestMapping(value = "/id_check_form", method = RequestMethod.GET)
	public String id_check_form(Model model){
		
		return "/member/idcheck";
	}
	
	
	@RequestMapping(value = "/find_zip_num", method = RequestMethod.GET)
	public String find_zip_num(Model model){
		
		return "/member/findZipNum";
	}
	
	@RequestMapping(value = "/join" , method = RequestMethod.GET)
	public String join(Model model){
		return "/member/login";
	}
	
	@RequestMapping(value = "/login_form" , method = RequestMethod.GET)
	public String login_form(Model model){
		return "/member/login";
	}
	
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public void login(Model model){//로그인 여부에 따라서 수정할것
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model){
		return "redirect:/";
	}
	
	
	

}
