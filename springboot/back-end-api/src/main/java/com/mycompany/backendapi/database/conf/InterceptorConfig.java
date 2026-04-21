package com.mycompany.backendapi.database.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mycompany.backendapi.database.interceptor.AccessTokenCheckInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer{
	
	// JWT(AccessToken) 검증을 담당하는 인터셉터
	private final AccessTokenCheckInterceptor atci;
	
	// 인터셉터를 Spring MVC 요청 처리 흐름에 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(atci)
				// 인터셉터 적용 범위 설정
				// /database/** 하위 모든 요청에 대해 인터셉터가 실행됨
				.addPathPatterns("/database/**");
				
				// 프로젝트 시 전체 요청에 적용하려면 아래처럼 설정
				// .addPathPatterns("/**");
	}
}
