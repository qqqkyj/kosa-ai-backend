package com.mycompany.backendapi.database.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mycompany.backendapi.database.dao.MemberDao;
import com.mycompany.backendapi.database.dto.MemberJoinRequest;
import com.mycompany.backendapi.database.dto.MemberJoinResponse;
import com.mycompany.backendapi.database.dto.MemberLoginRequest;
import com.mycompany.backendapi.database.dto.MemberLoginResponse;
import com.mycompany.backendapi.database.dto.MemberRemoveResponse;
import com.mycompany.backendapi.database.dto.MemberResponse;
import com.mycompany.backendapi.database.dto.MemberUpdateRequest;
import com.mycompany.backendapi.database.entity.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberDao memberDao;
	private final JwtService jwtService;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public MemberJoinResponse join(MemberJoinRequest request) {
		//Member 객체로 변환
		Member member = Member.builder()
							  .mid(request.getMid())
							  .mname(request.getMname())
							  .mpassword(passwordEncoder.encode(request.getMpassword()))
							  .memail(request.getMemail())
							  .menabled(true)
							  .mrole("ROLE_USER")
							  .build();
		
		memberDao.insert(member);
		
		return MemberJoinResponse.builder()
								.mid(member.getMid())
								.mname(member.getMname())
								.memail(member.getMemail())
								.menabled(true)
								.mrole("ROLE_USER")
								.build();
	}
	
	public MemberLoginResponse login(MemberLoginRequest request) {
		 Member member = memberDao.selectByMid(request.getMid());

		    // 1. 아이디 없음
		    if (member == null) {
		        return MemberLoginResponse.builder()
		                .result("FAIL")
		                .message("아이디가 존재하지 않습니다.")
		                .build();
		    }

		    // 2. 비밀번호 체크
		    if (!passwordEncoder.matches(request.getMpassword(), member.getMpassword())) {
		        return MemberLoginResponse.builder()
		                .result("FAIL")
		                .message("비밀번호가 틀립니다.")
		                .build();
		    }

		    // 3. 성공
		    return MemberLoginResponse.builder()
		            .result("SUCCESS")
		            .message("로그인 성공")
		            //로그인 성공 시 JWT 발급
		            .accessToken(jwtService.createJwt(member.getMid(), member.getMemail()))
		            .build();
	}

	public MemberResponse getMember(String mid) {
		Member member = memberDao.selectByMid(mid);
		
		return MemberResponse.builder()
				.mid(member.getMid())
				.mname(member.getMname())
				.memail(member.getMemail())
				.menabled(member.isMenabled())
				.mrole(member.getMrole())
				.build();
	}

	public MemberResponse modify(MemberUpdateRequest request) {
		Member member = memberDao.selectByMid(request.getMid());

	    if (member == null) {
	        return null;
	    }

	    if (request.getMemail() != null) {
	        member.setMemail(request.getMemail());
	    }

	    if (request.getMname() != null) {
	        member.setMname(request.getMname());
	    }

	    if (request.getMpassword() != null) {
	        member.setMpassword(passwordEncoder.encode(request.getMpassword()));
	    }

	    int result = memberDao.update(member);

	    if (result != 1) return null;

	    return MemberResponse.builder()
	            .mid(member.getMid())
	            .mname(member.getMname())
	            .memail(member.getMemail())
	            .menabled(member.isMenabled())
	            .mrole(member.getMrole())
	            .build();
	}

	public MemberRemoveResponse remove(String mid) {
		int result = memberDao.delete(mid);
		return MemberRemoveResponse.builder()
									.result(result == 1 ? "SUCCESS" : "FAIL")
									.msg(result == 1 ? "멤버를 삭제하였습니다." : "삭제할 멤버가 없습니다.")
									.build();
	}
	
}
