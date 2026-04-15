package com.mycompany.backendapi.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.backendapi.database.dao.MemberDao;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;
}
