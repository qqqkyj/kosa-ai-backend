package com.mycompany.backendapi.database.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberLoginResponse {
	private String result; //SUCCESS, WRONG-mid, WRONG-mpassword
	private String message; //detail error msg
}
