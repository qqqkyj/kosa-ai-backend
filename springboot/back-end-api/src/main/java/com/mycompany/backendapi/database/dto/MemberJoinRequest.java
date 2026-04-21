package com.mycompany.backendapi.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinRequest {
	private String mid;
	private String mname;
	private String mpassword;
	private String memail;
}
