package com.mycompany.backendapi.database.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDeleteResponse {
	private String result;
	private String msg;
}
