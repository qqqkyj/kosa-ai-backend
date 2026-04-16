package com.mycompany.backendapi.database.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backendapi.database.dto.Pager;
import com.mycompany.backendapi.database.entity.Board;
import com.mycompany.backendapi.database.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/database/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	private static final int ROWS_PER_PAGE = 10;
	private static final int PAGES_PER_GROUP = 5;
	
	
	@GetMapping("/list")
	public Map<String, Object> list(@RequestParam(value="pageNo", defaultValue = "1") int pageNo) {
		// 페이징 대상이 되는 전체 행의 수 얻기
		int totalRows = boardService.getTotalRows();
		// 페이징 정보를 담고있는 pager 생성
		Pager pager = new Pager(ROWS_PER_PAGE, PAGES_PER_GROUP, totalRows, pageNo);
		// 현재 페이지 내용 가져오기
		List<Board> list = boardService.getList(pager);
		//JSON 응답을 위한 Map 리턴
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("boards", list);
		return map;
	}
	
	@PostMapping("/create")
	public void create() {
		
	}
	
	@GetMapping("/read")
	public void read() {
		
	}
	
	@PutMapping("/update")
	public void update() {
		
	}
	
	@DeleteMapping("/delete")
	public void delete() {
		
	}
	
	@GetMapping("/battach")
	public void battach() {
		
	}
}
