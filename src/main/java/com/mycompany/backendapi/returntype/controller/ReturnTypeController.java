package com.mycompany.backendapi.returntype.controller;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backendapi.returntype.dto.Board;

@RestController
@RequestMapping("/return-type")
public class ReturnTypeController {
	@GetMapping("/string")
	public String returnString() {
		return "Hello, World!";
	}
	
//	@GetMapping("/dto")
	@GetMapping(value = "/dto", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Board dto() {
		Board board = new Board();
		board.setBno(1);
		board.setBtitle("제목입니다.");
		board.setBcontent("내용입니다.");
		board.setBwriter("작성자 입니다.");
		board.setBhitcount(0);
		board.setBdate(new Date());
		return board;
	}
}
