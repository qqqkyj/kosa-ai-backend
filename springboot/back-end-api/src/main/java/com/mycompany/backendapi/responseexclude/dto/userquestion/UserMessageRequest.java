package com.mycompany.backendapi.responseexclude.dto.userquestion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMessageRequest {
	private String mid;
	private String question;
}
