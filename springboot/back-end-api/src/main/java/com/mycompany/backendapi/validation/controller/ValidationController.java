package com.mycompany.backendapi.validation.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backendapi.validation.dto.Join;
import com.mycompany.backendapi.validation.dto.JoinRequest;
import com.mycompany.backendapi.validation.dto.Login;
import com.mycompany.backendapi.validation.dto.LoginRequest;
import com.mycompany.backendapi.validation.dto.Member;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/validation")
public class ValidationController {
	
	//그룹별(Join/Login) 유효성 선택 검사 가능 => 가독성이 떨어져 권장하지 않음(@Valid) 
	@PostMapping("/join")
	public String join(@Validated(Join.class) @RequestBody Member member) {
		return "가입 성공";
	}
	
	@PostMapping("/login")
	public String login(@Validated(Login.class) @RequestBody Member member) {
		return "로그인 성공";
	}
	
	@PostMapping("/join1")
	public String join1(@Valid @RequestBody JoinRequest request) {
		return "가입 성공";
	}
	
	@PostMapping("/login1")
	public String login1(@Valid @RequestBody LoginRequest request) {
		return "로그인 성공";
	}
}
