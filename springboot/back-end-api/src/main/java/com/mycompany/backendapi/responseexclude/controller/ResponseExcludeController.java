package com.mycompany.backendapi.responseexclude.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backendapi.receivedata.dto.LoginRequest;
import com.mycompany.backendapi.responseexclude.dto.board.Board;
import com.mycompany.backendapi.responseexclude.dto.board.BoardResponse;
import com.mycompany.backendapi.responseexclude.dto.board.BoardSaveRequest;
import com.mycompany.backendapi.responseexclude.dto.board.BoardUpdateRequest;
import com.mycompany.backendapi.responseexclude.dto.login.LoginResponse;
import com.mycompany.backendapi.responseexclude.dto.product.ProductResponse;
import com.mycompany.backendapi.responseexclude.dto.product.ProductSaveRequest;
import com.mycompany.backendapi.responseexclude.dto.userquestion.AiMessageResponse;
import com.mycompany.backendapi.responseexclude.dto.userquestion.UserMessageRequest;
import com.mycompany.backendapi.returntype.controller.ReturnTypeController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/response-exclude")
public class ResponseExcludeController {

    private final ReturnTypeController returnTypeController;

    ResponseExcludeController(ReturnTypeController returnTypeController) {
        this.returnTypeController = returnTypeController;
    }
	//게시물 등록
	@PostMapping("/board-save1")
	public String boardSave1(@ModelAttribute Board board) {
		log.info(board.toString());
		return "저장 성공";
	}
	
	//게시물 등록2
	@PostMapping("/board-save2")
	public String boardSave2(@ModelAttribute BoardSaveRequest request) {
		log.info(request.toString());
		return "저장 성공";
	}
	
	//게시물 보기1
	@GetMapping("/board-read1")
	public Board boardRead1() {
		Board Board = new Board();
		Board.setBno(1);
		Board.setBtitle("제목입니다.");
		Board.setBcontent("내용입니다.");
		Board.setBwriter("사용자");
		Board.setBdate(new Date());
		return Board;
	}
	
	//게시물 보기2
	@GetMapping("/board-read2")
	public BoardResponse boardRead2() {
		BoardResponse Board = new BoardResponse();
		Board.setBno(1);
		Board.setBtitle("제목입니다.");
		Board.setBcontent("내용입니다.");
		Board.setBwriter("사용자");
		Board.setBdate(new Date());
		return Board;
	}
	
	//게시물 수정
	@PostMapping("/board-update1")
	public String boardUpdate1(@ModelAttribute BoardUpdateRequest request) {
		log.info(request.toString());
		return "수정 성공!";
	}
	
	// 상품 저장
	@PostMapping("/product-save")
	public String productSave(@ModelAttribute ProductSaveRequest request) {
		log.info(request.toString());
		return "등록 성공!";
	}
	
	// 상품 조회
	@GetMapping("/product-read")
	public ProductResponse productRead(@RequestParam("pid") int pid) {
		
		log.info("pid:" + pid);
		
		ProductResponse response = new ProductResponse();
		response.setPid(1);
		response.setPname("카메라");
		response.setPprice(500000);
		response.setPcompany("삼성전자");
		response.setPimageoname("original.png");
		response.setPimagesname("save.png");
		response.setPimagetype("image/png");
		return response;
	}
	
	// 로그인
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) {
		LoginResponse response = new LoginResponse();
		response.setMid(request.getMid());
		response.setAccessToken("xxxxx");
		return response;
	}
	
	// 프롬프트
	@PostMapping("/chat")
	public AiMessageResponse chat(@RequestBody UserMessageRequest request) {
		AiMessageResponse response = new AiMessageResponse(request.getQuestion(), "삼성전자의 오늘 주가는 230000원");
		return response;
	}
}
