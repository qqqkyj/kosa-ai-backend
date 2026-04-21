package com.mycompany.backendapi.database.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mycompany.backendapi.database.dao.BoardDao;
import com.mycompany.backendapi.database.dto.BoardCreateResponse;
import com.mycompany.backendapi.database.dto.BoardDeleteResponse;
import com.mycompany.backendapi.database.dto.BoardListItemResponse;
import com.mycompany.backendapi.database.dto.BoardReadResponse;
import com.mycompany.backendapi.database.dto.BoardUpdateResponse;
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
	
	/**
	 * 게시글 생성 메서드
	 * 1. 전달받은 Board(Entity)를 DB에 insert
	 * 2. insert 시 MyBatis(useGeneratedKeys)가 동작하여
	 *    생성된 PK(bno)를 매개변수로 받은 board 객체에 자동 세팅
	 * 3. 세팅된 board.bno 값을 이용해 DB에서 다시 조회(select)
	 * 4. 조회 결과를 ResponseDTO로 변환하여 반환
	 */
	public BoardCreateResponse create(Board board) {
		boardDao.insert(board);
		Board dbBoard = boardDao.selectByBno(board.getBno());
		
		return BoardCreateResponse.builder()
								.bno(dbBoard.getBno())
								.btitle(dbBoard.getBtitle())
								.bwriter(dbBoard.getBwriter())
								.bhitcount(dbBoard.getBhitcount())
								.battachoname(dbBoard.getBattachoname())
								.battachtype(dbBoard.getBattachtype())
								.build();
	}

	public BoardReadResponse getReadBoard(int bno) {
		Board board = boardDao.selectByBno(bno);
		
		return BoardReadResponse.builder()
				.bno(board.getBno())
				.btitle(board.getBtitle())
				.bwriter(board.getBwriter())
				.bhitcount(board.getBhitcount())
				.battachoname(board.getBattachoname())
				.battachtype(board.getBattachtype())
				.battachsname(board.getBattachsname())
				.build();
	}
	
	public Board getBoard(int bno) {
		return boardDao.selectByBno(bno);
	}

	public BoardUpdateResponse update(Board board) {
		boardDao.update(board);
		Board dbBoard = boardDao.selectByBno(board.getBno());
		
		return BoardUpdateResponse.builder()
								.bno(dbBoard.getBno())
								.btitle(dbBoard.getBtitle())
								.bwriter(dbBoard.getBwriter())
								.bhitcount(dbBoard.getBhitcount())
								.battachoname(dbBoard.getBattachoname())
								.battachtype(dbBoard.getBattachtype())
								.build();
	}

	public BoardDeleteResponse delete(int bno) {
		int result = boardDao.delete(bno);
		return BoardDeleteResponse.builder()
								.result(result==1 ?"SUCCESS":"FAIL")
								.msg(result==1 ? "게시물을 삭제했습니다." : "삭제할 게시물이 없습니다.")
								.build();
	}
}
