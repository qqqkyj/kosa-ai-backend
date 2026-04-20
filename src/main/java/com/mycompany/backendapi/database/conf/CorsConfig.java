package com.mycompany.backendapi.database.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/database/**") //"/database/**"로 시작하는 모든 API에 대해 CORS 정책 적용
			.allowedOrigins("*") // 모든 도메인 허용
			.allowedMethods("*") // HTTP 메서드 모두 허용
			.allowedHeaders("*"); //요청 헤더에 포함되는 모든 값 허용
	}
}
