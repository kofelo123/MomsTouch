package com.momstouch.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.annotation.Resource;
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

import com.momstouch.domain.MemberVO;
import com.momstouch.domain.QnaVO;
import com.momstouch.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@Inject
	private MemberService service;
	
	@RequestMapping(value="contract" , method=RequestMethod.GET)
	public String contract(Model model){
		
		return "/member/contract";
	}
	
	@RequestMapping(value = "join_form" , method= RequestMethod.GET)
	public String join_form2(Model model){
		
		return "/member/join";
	}
	@RequestMapping(value = "join_form" , method= RequestMethod.POST)
	public String join_form(Model model){
		
		return "/member/join";
	}
	
	@RequestMapping(value = "id_check_form", method = RequestMethod.GET)
	public String id_check_form(Model model,@RequestParam("id")String id){
		
		model.addAttribute("message",service.confirmId(id));
		model.addAttribute("id",id);
		
		return "/member/idcheck";
	}
	
	@RequestMapping(value = "id_check_form", method = RequestMethod.POST)
	public String id_check_form2(Model model,@RequestParam("id")String id){
		
		model.addAttribute("message",service.confirmId(id));
		model.addAttribute("id",id);
		
		return "/member/idcheck";
	}
	
	
	
	@RequestMapping(value = "find_zip_num", method = RequestMethod.GET)
	public String find_zip_num(Model model){
		
		
		return "/member/findZipNum";
	}
	
	@RequestMapping(value = "find_zip_num", method = RequestMethod.POST)
	public String find_zip_num2(Model model,@RequestParam("dong")String dong){
		
		if(dong!=null && dong.trim().equals("")==false){
			model.addAttribute("addressList",service.selectAddressByDong(dong.trim()));
		}
		return "/member/findZipNum";
	}
	
	/*	@RequestMapping(value = "join" , method = RequestMethod.GET)
	public String join(Model model){
		return "/member/login";
	}
	*/	
	@RequestMapping(value = "join" , method = RequestMethod.POST)
	public String join(Model model,MemberVO vo){
		
		service.joinPost(vo);
		
		return "/member/login";
	}

	@RequestMapping(value = "login_form" , method = RequestMethod.GET)
	public String login_form(Model model){
		return "/member/login";                                                                                                                
	}
	
	@RequestMapping(value = "login" , method = RequestMethod.POST)
	public String login(Model model,MemberVO vo,HttpServletRequest request){//로그인 여부에 따라서 수정할것
		MemberVO mvo = service.login(vo); 
		if(mvo == null)
			return "/member/login_fail";
		
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", mvo);
		return "redirect:/";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(Model model,HttpServletRequest request){
		
		   HttpSession session=request.getSession(false);
		    if(session!=null){
		      session.invalidate();
		    }    
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "qna_list" , method = RequestMethod.GET)
	public String qna_list(Model model,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null)
			return "redirect:/member/login_form";
		model.addAttribute("qnaList",service.listQna(loginUser.getId()));
		return "/qna/qnaList";
	}
	
	@RequestMapping(value = "qna_write_form" , method = RequestMethod.GET)
	public String qna_write_form(Model model,HttpServletRequest request){
		
		HttpSession session = request.getSession();
	    MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");    
	    
	    if (loginUser == null) 
	      return "redirect:/member/login_form";
	    
		return "/qna/qnaWrite";
	}
	@RequestMapping(value = "qna_write" , method = RequestMethod.POST)
	public String qna_write(Model model,HttpServletRequest request,@RequestParam("subject")String subject,@RequestParam("content")String content){
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");    
	    
	    if (loginUser == null) 
	    	return "redirect:/member/login_form";
	
		QnaVO qnaVO = new QnaVO();
		qnaVO.setSubject(subject);
		qnaVO.setContent(content.replaceAll("\n", "<br/>"));//jw:set할때 replaceAll 해야한다. 이전에 따로 replaceAll 해놓고 set할때 content만 넣으니까 개행처리가 안되었다.
		service.insertQna(qnaVO, loginUser.getId());
		return "redirect:/member/qna_list";
	}
	@RequestMapping(value = "qna_view" , method = RequestMethod.GET)
	public String qna_view(Model model,HttpServletRequest request,@RequestParam("qseq")int qseq){
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null)
			return "redirect:/member/login_form";
		
		model.addAttribute("qnaVO",service.getQna(qseq));
		
		return "/qna/qnaView";
	}
	

}
