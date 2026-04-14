package com.mycompany.backendapi.validation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
	@NotBlank(message = "mid는 필수 입력 정보입니다.")
	@Size(min = 5, max = 10, message = "mid는 5자 이상, 10자 이하이어야 합니다.")
	private String mid;
	
	@NotBlank(message = "mpassword는 필수 입력 정보입니다.")
	@Size(min = 5, max = 10, message = "mid는 5자 이상, 10자 이하이어야 합니다.")
	@Pattern(
			regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]).+$",
	        message = "비밀번호는 대문자, 소문자, 특수문자를 각각 최소 1개 이상 포함해야 합니다."
			)//정규표현식
	private String mpassword;
}
