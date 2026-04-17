package com.mycompany.backendapi.database.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	@Autowired
	private AccessTokenCheckInterceptor atci;
	
	//인터셉터 추가
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//언제 추가할 것 인지?
		registry.addInterceptor(atci)
				// 프로젝트 진행 시 전체 패키지에서 검사하도록 설정할 것
				//.addPathPatterns("/**");
				.addPathPatterns("/database/**");
	}
}
