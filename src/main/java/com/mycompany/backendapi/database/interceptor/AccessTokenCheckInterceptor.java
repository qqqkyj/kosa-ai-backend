package com.mycompany.backendapi.database.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mycompany.backendapi.database.service.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccessTokenCheckInterceptor implements HandlerInterceptor {
	private final JwtService jwtService;
	
	// Controller 실행 전에 호출되는 전처리 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 요청이 Controller로 전달되기 전에 실행됨
		log.info("나 전처리하고 있어!!!");
		
		// HandlerMethod 체크
	    if (!(handler instanceof HandlerMethod handlerMethod)) {
	        return true;
	    }
		
		// 엔드포인트에 @AccessTokenCheck가 붙어 있는지 확인
	    AccessTokenCheck atci = handlerMethod.getMethodAnnotation(AccessTokenCheck.class);
	    if (atci == null) return true;

	    // @AccessTokenCheck가 붙어있는 경우
	 	// AccessToken을 얻어내기
	    String authorization = request.getHeader("Authorization");
	    boolean isValid = false;

	    if (authorization != null && authorization.startsWith("Bearer ")) {
	        String accessToken = authorization.substring(7);
	        isValid = jwtService.validateJwt(accessToken);
	    }

	    //유효성 검사 결과에 따른 처리
	    if (isValid) {
	        return true; // 다음 단계(Controller)로 진행
	    } else {
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT가 유효하지 않습니다.");
	        return false; // Controller로 가지 않고 요청 종료
	    }
	}
	
	//후처리 메서드
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		log.info("나 후처리하고 있어!");
//	}
}
