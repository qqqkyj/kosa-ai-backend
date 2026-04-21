package com.mycompany.backendapi.database.interceptor;

import java.util.Map;

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
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";
	
	// Controller 실행 전에 호출되는 전처리 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 요청이 Controller로 전달되기 전에 실행됨
		log.info("나 전처리하고 있어!!!");
		
		// HandlerMethod 체크
		// 컨트롤러에 엔드포인트가 아니라, 정적 리소스를 요청할 경우도 있음
	    if (!(handler instanceof HandlerMethod handlerMethod)) {
	        return true;
	    }
		
		// 엔드포인트에 @AccessTokenCheck가 붙어 있는지 확인
	    // 컨트롤러의 엔드포인트로 요청할때만 HandlerMethod 강제 타입 변환을 할 수 있음
	    AccessTokenCheck atc = handlerMethod.getMethodAnnotation(AccessTokenCheck.class);
	    if (atc == null) return true;

	    // @AccessTokenCheck가 붙어있는 경우
	 	// AccessToken을 얻어내기
	    String authorization = request.getHeader(AUTHORIZATION);
	    if (authorization == null || !authorization.startsWith(BEARER)) {
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "AccessToken이 없습니다.");
	        return false;
	    }
	    
	    String accessToken = authorization.substring(7);
	    boolean isValid = jwtService.validateJwt(accessToken);

	    //유효성 검사 결과에 따른 처리
	    if (isValid) {
	    	//요청한 사용자의 mid를 요청 정보를 추가하기
	    	Map<String, String> map = jwtService.getClaims(accessToken);
	    	String mid = map.get("mid");
	    	request.setAttribute("mid", mid);
	        return true; // 다음 단계(Controller)로 진행
	    } else {
	        response.sendError(HttpServletResponse.SC_FORBIDDEN, "JWT가 유효하지 않습니다.");
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
