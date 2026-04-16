package com.mycompany.backendapi.database.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backendapi.database.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/database/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("/list")
	public void list() {
		
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
