package com.mycompany.backendapi.database.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mycompany.backendapi.database.dao.BoardDao;
import com.mycompany.backendapi.database.dto.BoardListItemResponse;
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
	
	public List<BoardListItemResponse> getListResponse(Pager pager) {
	    List<Board> boards = boardDao.selectPage(pager);	   
	    				
	    return boards.stream()
	            .map(board -> BoardListItemResponse.builder()
	                    .bno(board.getBno())
	                    .btitle(board.getBtitle())
	                    .bwriter(board.getBwriter())
	                    .bdate(board.getBdate())
	                    .bhitcount(board.getBhitcount())
	                    .build())
	            .toList();
	}
	
	public Board create(Board board) {
		boardDao.insert(board);
		return board;
	}
}
