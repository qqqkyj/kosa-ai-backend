package com.mycompany.backendapi.httpmethod.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/http-method")
@Slf4j
public class HttpMethodController {
	
//	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@GetMapping("/info")
	public String info() {
		log.info("실행");
		return "info outcome";
	}
	
	@PostMapping("/join")
	public String join() {
		log.info("추가");
		return "join outcome";
	}
	
	@PutMapping("/edit")
	public String edit() {
		log.info("수정");
		return "edit outcome";
	}
	
	@DeleteMapping("/remove")
	public String remove() {
		log.info("삭제");
		return "remove outcome";
	}
}
