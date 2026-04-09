package com.mycompany.backendapi.restcontroller.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backendapi.restcontroller.dto.Board;


@RestController
@RequestMapping("/restcontroller")
public class RestControllerTest {

	@GetMapping("/")
	public String index() {
		return "Spring Back End Home";
	}
	
	@GetMapping(value="/getBoard1", produces = "application/json")
	public Board getBoard1() {
		Board board = new Board(1, "제목1", "내용1", "사용자1", 0, new Date());
		return board;
	}
	
	@GetMapping(value="/getBoard2", produces = "application/xml")
	public Board getBoard2() {
		Board board = new Board(1, "제목1", "내용1", "사용자1", 0, new Date());
		return board;
	}
}
