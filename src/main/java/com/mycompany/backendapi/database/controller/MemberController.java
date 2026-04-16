package com.mycompany.backendapi.database.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backendapi.database.dto.MemberJoinRequest;
import com.mycompany.backendapi.database.dto.MemberJoinResponse;
import com.mycompany.backendapi.database.dto.MemberLoginRequest;
import com.mycompany.backendapi.database.dto.MemberLoginResponse;
import com.mycompany.backendapi.database.dto.MemberRemoveResponse;
import com.mycompany.backendapi.database.dto.MemberResponse;
import com.mycompany.backendapi.database.dto.MemberUpdateRequest;
import com.mycompany.backendapi.database.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/database/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping("/join")
	public MemberJoinResponse join(@RequestBody MemberJoinRequest request) {
		//MemberService로 회원가입 처리 위임
		return memberService.join(request);
	}
	
	@PostMapping("/login")
	public MemberLoginResponse login(@RequestBody MemberLoginRequest request) {
		return memberService.login(request);
	}
	
	@GetMapping("/info")
	public MemberResponse info(@RequestParam("mid") String mid) {
		return memberService.getMember(mid);
	}
	
	@PutMapping("/modify")
	public MemberResponse modify(@RequestBody MemberUpdateRequest request) {
		return memberService.modify(request);
	}
	
	@DeleteMapping("/remove/{mid}")
	public MemberRemoveResponse remove(@PathVariable("mid") String mid) {
		return memberService.remove(mid);
	}
	
}
