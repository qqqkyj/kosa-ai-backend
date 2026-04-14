package com.mycompany.backendapi.di.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backendapi.di.service.DiBoardService;
import com.mycompany.backendapi.di.service.DiMemberService;

import lombok.extern.slf4j.Slf4j;

//일반적인 구조: Controller → Service → DAO → DB
//요청 처리: XXXRequest → Entity 변환 → Service(비즈니스 로직 처리) → DAO
//DB 조회 결과: DAO → Entity → Service → XXXResponse 변환 → Controller 반환
//Service는 비즈니스 로직 중심 계층으로 Entity를 활용해 처리
//Controller는 요청/응답(XXXRequest, XXXResponse)만 담당하는 역할 분리 구조

@Slf4j
@RestController
@RequestMapping("/di")
public class DiController {
	
	// 필드 주입
	@Autowired
	private DiMemberService memberService;
	
	private final DiBoardService boardService;
	// 생성자 주입
	public DiController(DiBoardService boardService) {
		this.boardService = boardService;
	}
	
	@PostMapping("/member-join")
	public String join() {
		memberService.join();
		return "가입 성공";
	}
	
	@GetMapping("/board-list")
	public String boardList() {
		boardService.list();
		return "게시물 목록";
	}
}
