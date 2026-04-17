package com.mycompany.backendapi.database.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
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
	public String createJwt(String mid, String memail) {
		return  Jwts.builder()
				.subject(mid) //사용자의 식별값
				.claim("email", memail) // 사용자의 기타 정보
				.expiration(new Date(new Date().getTime() + JWTDURATION)) //JWT 만료 기간
				.signWith(secretkey) //서명
				.compact(); // 위 내용을 암호화해서 JWT를 반환
	}
	
	//JWT 유효성 검증
	public boolean validateJwt(String jwt) {
		try {
			JwtParser jwtParser = Jwts.parser()
										.verifyWith(secretkey)
										.build();
			
			Jws<Claims> jws = jwtParser.parseSignedClaims(jwt);
			return true;	
		} catch (ExpiredJwtException e) {
			log.info("기간이 만료된 토큰입니다.");
		} catch (SecurityException e) {
			log.info("잘못 서명된 토큰입니다.");
		}
		return false;
	}
	
	//JWT의 사용자 정보 조회
	public Map<String, String> getClaims(String jwt){
		JwtParser jwtParser = Jwts.parser()
				.verifyWith(secretkey)
				.build();
		
		Claims claims = jwtParser.parseSignedClaims(jwt).getPayload();
		
		Map<String, String> map = new HashMap<>();
		map.put("mid", claims.getSubject());
		map.put("memail", claims.get("memail").toString());
		
		return map;
	}
}
