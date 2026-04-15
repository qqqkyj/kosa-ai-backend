package com.mycompany.backendapi.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.backendapi.database.dao.BoardDao;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
}
