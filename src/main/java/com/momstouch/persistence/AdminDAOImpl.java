package com.momstouch.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAOImpl implements AdminDAO{
	@Inject
	private SqlSession session;
	
	private static String namespace="com.momstouch.mapper.Admin"; 
	
	@Override
	public int workCheck(String id, String pw) {
		
		Map<String, Object> paramMap = new HashMap<String ,Object>();
		paramMap.put("id", id);
		paramMap.put("pw", pw);
		String 	pwd = session.selectOne(namespace + ".workCheck",paramMap);
		
		if(pw.equals(pwd))
			return 1;
		return 0;
	}

	
}
