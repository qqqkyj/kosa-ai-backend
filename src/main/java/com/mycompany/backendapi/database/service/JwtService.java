package com.mycompany.backendapi.database.service;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtService {
	private final SecretKey secretkey;
	private static final long JWTDURATION = 30 * 24 * 60 * 60 * 1000;
	
	public JwtService(@Value("${jwt.secret.key}") String jwtSecretKey) throws Exception {
		log.info("jwt.secret.key: "+jwtSecretKey);
		byte[] bytes = jwtSecretKey.getBytes("UTF-8");
		secretkey = Keys.hmacShaKeyFor(bytes);
	}
	
	// 식별값과 필요정보를 매개변수로 받아 토큰 생성
	public String createJwt(String midm, String memail) {
		return  Jwts.builder()
				.subject(memail) //사용자의 식별값
				.claim("email", memail) // 사용자의 기타 정보
				.expiration(new Date(new Date().getTime() + JWTDURATION)) //JWT 만료 기간
				.signWith(secretkey) //서명
				.compact(); // 위 내용을 암호화해서 JWT를 반환
	}
	
	public boolean validateJwt(String jwt) {
		boolean result = false;
		try {
			JwtParser jwtParser = Jwts.parser()
										.verifyWith(secretkey)
										.build();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public Map<String, String> getClaims(String jwt){
		return null;
	}
}
