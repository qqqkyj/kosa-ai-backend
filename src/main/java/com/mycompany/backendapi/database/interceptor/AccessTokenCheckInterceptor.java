package com.mycompany.backendapi.database.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccessTokenCheckInterceptor implements HandlerInterceptor {
	// Controller 실행 전에 호출되는 전처리 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 요청이 Controller로 전달되기 전에 실행됨
		log.info("나 전처리하고 있어!!!");

		// true → 다음 단계(Controller)로 진행
		// false → Controller로 가지 않고 요청 종료
		return true;
	}
}
