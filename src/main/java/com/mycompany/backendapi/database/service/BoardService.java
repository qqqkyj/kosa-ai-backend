package com.mycompany.backendapi.database.service;

import org.springframework.stereotype.Service;

import com.mycompany.backendapi.database.dao.BoardDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardDao boardDao;
}
