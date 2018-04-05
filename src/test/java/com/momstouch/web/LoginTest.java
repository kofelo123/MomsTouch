//package com.momstouch.web;
//
//import javax.inject.Inject;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.momstouch.domain.MemberVO;
//import com.momstouch.persistence.MemberDAO;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
//public class LoginTest {
//	
//	@Inject
//	private MemberDAO dao;
//	
//	private static final Logger logger=LoggerFactory.getLogger(LoginTest.class);
//	
//	@Test
//	public void testLogin() throws Exception{
//		MemberVO mvo = new MemberVO();
//		mvo.setId("kofelo123");
//		mvo.setPwd("gj0123");
//		MemberVO vo =dao.login(mvo);
//	}
//	
//	@Test
//	public void cartInsert() throws Exception{
//		
//	}
//
//}
