package com.mycompany.backendapi.database.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backendapi.database.dto.MemberJoinRequest;
import com.mycompany.backendapi.database.dto.MemberJoinResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/database/member")
public class MemberController {
	@PostMapping("/join")
	public MemberJoinResponse join(@RequestBody MemberJoinRequest request) {
		MemberJoinResponse res = MemberJoinResponse
								.builder()
								.mid(request.getMid())
								.mname(request.getMname())
								.memail(request.getMemail())
								.menabled(true)
								.mrole("USER_ROLE")
								.build();
		return res;
	}
}
