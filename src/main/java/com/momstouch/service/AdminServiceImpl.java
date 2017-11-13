package com.momstouch.service;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.momstouch.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService{

	@Inject
	private AdminDAO dao;
	
	@Override
	public int workCheck(String id, String pw) {
		// TODO Auto-generated method stub
		return dao.workCheck(id,pw);
	}

}
