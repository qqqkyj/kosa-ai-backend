package com.mycompany.backendapi.database.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mycompany.backendapi.database.dao.BoardDao;
import com.mycompany.backendapi.database.dto.Pager;
import com.mycompany.backendapi.database.entity.Board;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardDao boardDao;

	public int getTotalRows() {
		return boardDao.countAllRow();
	}

	public List<Board> getList(Pager pager) {
		return boardDao.selectPage(pager);
	}
}
